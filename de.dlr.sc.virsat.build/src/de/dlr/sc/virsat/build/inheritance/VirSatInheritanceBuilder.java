/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.inheritance;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.build.marker.util.VirSatInheritanceMarkerHelper;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceCopier;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritanceCopier;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * the VirSatProjectbuilder our contribution to automatically 'build' our model. As a first implementation  
 * @author fisc_ph
 *
 */
public class VirSatInheritanceBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = "de.dlr.sc.virsat.build.inheritance";

	/**
	 * public constructor
	 */
	public VirSatInheritanceBuilder() {
	}

	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		IProject project = getVirSatProject();
		
		// The builder is supposed to build the project in an unmanaged resourceSet
		// but since we can have inconsistencies between the resource on the eclipse workspace and the in memory representation
		// of EMF currently displayed by the managed ResourceSet in the navigator, it can happen that newly added elements disappear because the resources in the WS doesn't knwo references
		// and stores them once the builder is finished. This behavior is prevented by first asking the projects managed resource set
		
	
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatInheritanceBuilder: Try to trigger build", null));
		
		if (isInterrupted() || monitor.isCanceled()) {
			rememberLastBuiltState();
			return null;
		}
		
		switch (kind) {  
			case FULL_BUILD:
				transactionalFullBuild(monitor, new InheritanceCopier());
				break;  
			case INCREMENTAL_BUILD:
			case AUTO_BUILD:
				IResourceDelta delta = getDelta(project);
				if (delta == null) {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatInheritanceBuilder: Performing full build", null));
					transactionalFullBuild(monitor, new InheritanceCopier());
				} else {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatInheritanceBuilder: Performing incremental build", null));
					transactionalIncrementalBuild(delta, monitor, new InheritanceCopier());
				}
				break;
			default:
				break;  
		}
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatInheritanceBuilder: Finished build", null));
		
		return null; 
	}
	
	/**
	 * the transactionalfullBuild method is executed to trigger a build with all necessary steps using an editing domain
	 * @param monitor monitor the progress
	 * @param inheritanceCopier  the copier to be actually used
	 */
	protected void transactionalFullBuild(IProgressMonitor monitor, IInheritanceCopier inheritanceCopier) {
		VirSatTransactionalEditingDomain virSatTed = VirSatEditingDomainRegistry.INSTANCE.getEd(getVirSatProject());
		
		if (virSatTed == null) {
			return;
		}
		
		// Clean all Markers in Workspace and nested resources
		vimHelper.deleteAllMarkersInWorkspace();
		
		Command cmd = new AbstractCommand() {
			@Override
			public void execute() {
				fullBuild(monitor, inheritanceCopier);
				virSatTed.saveAll(true, false);
			}

			@Override
			public void redo() {
				
			}
			
			@Override
			public boolean canUndo() {
				return true;
			}
			
			@Override
			public boolean canExecute() {
				return true;
			}
		};
		
		if (virSatTed.getActiveTransaction() != null) {
			rememberLastBuiltState();
			return;
		}
		
		virSatTed.getVirSatCommandStack().executeNoUndo(cmd);
	}
	
	/**
	 * Method for incremental build using an editing domain
	 * @param delta the delta from the BUilderManager 
	 * @param monitor a progress monitor to track what is going on
	 * @param inheritanceCopier  the copier to be actually used
	 */
	protected void transactionalIncrementalBuild(IResourceDelta delta, IProgressMonitor monitor, IInheritanceCopier inheritanceCopier) {
		VirSatTransactionalEditingDomain virSatTed = VirSatEditingDomainRegistry.INSTANCE.getEd(getVirSatProject());
		
		if (virSatTed == null) {
			return;
		}

		Command cmd = new AbstractCommand() {
			@Override
			public void execute() {
				incrementalBuild(delta, monitor, inheritanceCopier);
				virSatTed.saveAll(true, false);
			}

			@Override
			public void redo() {
				
			}
			
			@Override
			public boolean canUndo() {
				return true;
			}
			
			@Override
			public boolean canExecute() {
				return true;
			}
		};
		
		if (virSatTed.getActiveTransaction() != null) {
			rememberLastBuiltState();
			return;
		}
		
		virSatTed.getVirSatCommandStack().executeNoUndo(cmd);
	}
	
	private VirSatInheritanceMarkerHelper vimHelper = new VirSatInheritanceMarkerHelper();
	
	/**
	 * the fullBuild method is executed to trigger a build with all necessary steps.
	 * @param monitor monitor the progress
	 * @param inheritanceCopier  the copier to be actually used
	 */
	protected void fullBuild(IProgressMonitor monitor, IInheritanceCopier inheritanceCopier) {
		final int MAX_TASKS = 3;
		SubMonitor subMonitor = SubMonitor.convert(monitor, MAX_TASKS);
		subMonitor.beginTask("Run Full Inheritance Build...", MAX_TASKS);
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatInheritanceBuilder: Starting full build", null));
		
		// we need do bypass any UI calls because of invalid thread access during the builder run
		// the trick is to create a separate resource set which has no listeners 
		subMonitor.subTask("Get Unmanaged ResourceSet...");
		VirSatResourceSet resourceSet = getResourceSet();
		
		if (!resourceSet.isOpen()) {
			return;
		}
		
		Repository repo = resourceSet.getRepository();
		subMonitor.worked(1);
		
		subMonitor.subTask("Call Inheritance Copier... " + Thread.currentThread().getName());
		inheritanceCopier.updateAllInOrder(repo, subMonitor.newChild(1));

		// Need some code here to report correct Problem Markers
		
		subMonitor.subTask("Saving Resource...");
		//resourceSet.saveAllResources(new NullProgressMonitor());
		subMonitor.worked(1);
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatInheritanceBuilder: Executed full build", null));
	}
	
	/**
	 * Call this method to get all StructuralElementInstances from a given EMF Resource
	 * @param resource The EMF Resource to look in for SEIs
	 * @return A Set with all contained SEIs
	 */
	@SuppressWarnings("unchecked")
	private Set<StructuralElementInstance> getAllSeiInResource(Resource resource) {
		Set<StructuralElementInstance> listOfCAs = new HashSet<>();
		
		try {
			EcoreUtil.resolveAll(resource);
			
			if (!resource.getErrors().isEmpty()) {
				return Collections.EMPTY_SET;
			}
			
			EcoreUtil.getAllProperContents(resource, true).forEachRemaining((object) -> {
				if (object instanceof StructuralElementInstance) {
					StructuralElementInstance ca = (StructuralElementInstance) object;
					listOfCAs.add(ca);
				}
			});
				
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "Could not read active StructuralElementInstance from Repository", e));
		}
	
		return listOfCAs;
	};

	/**
	 * Method for incremental build 
	 * @param delta the delta from the BUilderManager 
	 * @param monitor a progress monitor to track what is going on
	 * @param inheritanceCopier  the copier to be actually used
	 */
	protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor, IInheritanceCopier inheritanceCopier) {
		final int MAX_TASKS = 3;
		
		SubMonitor subMonitor = SubMonitor.convert(monitor, MAX_TASKS);
		subMonitor.beginTask("Run Incremental Inheritance Build...", MAX_TASKS);
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatInheritanceBuilder: Starting incremental build", null));
		VirSatResourceSet resourceSet = getResourceSet();
		
		if (!resourceSet.isOpen()) {
			return;
		}
		
		subMonitor.worked(1);
		
		SubMonitor loopMonitor = SubMonitor.convert(monitor);
		try {
			delta.accept(new IResourceDeltaVisitor() {
			    public boolean visit(IResourceDelta delta) {
			    	IResource iResource = delta.getResource();
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatInheritanceBuilder: Finished incremental build on resource (" + iResource + ")", null));

				   	if (iResource instanceof IFile) {
			    		// We should only process DVLm resources in terms of inheritance
			    		IFile iFile = (IFile) iResource;
			    		boolean fileIsNoDVLMResource = !VirSatProjectCommons.FILENAME_EXTENSION.equalsIgnoreCase(iFile.getFileExtension());
			    		boolean fileDoesNotExist = !iFile.exists();
			    		if (fileDoesNotExist || fileIsNoDVLMResource) {
			    			return true;
			    		}

			    		Resource resource = resourceSet.safeGetResource(iFile, false);
				    	Repository repository = resourceSet.getRepository();
			    		Set<StructuralElementInstance> seis = getAllSeiInResource(resource);

			    		seis.forEach((sei) -> {
			    			try {
			    				final int SUB_TASKS = 100;
			    				inheritanceCopier.updateInOrderFrom(sei, repository, loopMonitor.setWorkRemaining(SUB_TASKS).newChild(1));
			    				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatInheritanceBuilder: Started incremental build on SEI (" + sei.getType().getName() + " " + sei.getName() + " " + sei.getUuid() + ")"));
			    			} catch (Exception e) {
			    				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "VirSatInheritanceBuilder: Could not execute Inheritance Copier on SEI: " + sei.getFullQualifiedInstanceName()));
			    				vimHelper.createInheritanceMarker(IMarker.SEVERITY_ERROR, "Could not execute Inheritance Build on SEI " + sei.getFullQualifiedInstanceName(), sei);
			    			}
			    		}); 
			    	}
			    	
			        return true; // visit children too
			    }
			});
		} catch (CoreException e) {
			e.printStackTrace();
		}

		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatInheritanceBuilder: Finished incremental build", null));
	}
	
	/**
	 * Facade method to be able to override getProject method
	 * for the test cases of this tester
	 * @return hands back the current project the builder is triggered on
	 */
	protected IProject getVirSatProject() {
		return getProject();
	}
	
	/**
	 * Overrideable method to provide the resource set for the use of non transactional testers
	 * @return gets the resource set this builder operates on
	 */
	protected VirSatResourceSet getResourceSet() {
		return VirSatResourceSet.getResourceSet(getVirSatProject());
	}
	
	@Override
	public ISchedulingRule getRule(int kind, Map<String, String> args) {
		return null;
	}
}
