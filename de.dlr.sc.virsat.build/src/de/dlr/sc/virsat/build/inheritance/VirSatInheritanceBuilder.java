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
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.build.marker.util.VirSatInheritanceMarkerHelper;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceCopier;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritanceCopier;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * the VirSatProjectbuilder our contribution to automatically 'build' our model. As a first implementation  
 *
 */
public class VirSatInheritanceBuilder extends AVirSatTransactionalBuilder {

	public static final String BUILDER_ID = "de.dlr.sc.virsat.build.inheritance";

	protected VirSatInheritanceMarkerHelper vimHelper; 
	
	/**
	 * public constructor
	 */
	public VirSatInheritanceBuilder() {
		super("Inheritance Builder", new VirSatInheritanceMarkerHelper(), true, true);
		this.vimHelper = (VirSatInheritanceMarkerHelper) this.vpmHelper;
	}

	@Override
	protected void fullBuild(IProgressMonitor monitor) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatInheritanceBuilder: Starting full build"));
		final int MAX_TASKS = 3;
		IInheritanceCopier inheritanceCopier = createInheritanceCopier();
		
		SubMonitor subMonitor = SubMonitor.convert(monitor, MAX_TASKS);
		subMonitor.beginTask("Run Full Inheritance Build...", MAX_TASKS);
		
		// we need do bypass any UI calls because of invalid thread access during the builder run
		// the trick is to create a separate resource set which has no listeners 
		subMonitor.subTask("Get Unmanaged ResourceSet...");
		VirSatResourceSet resourceSet = getResourceSet();
		
		if (!resourceSet.isOpen()) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatInheritanceBuilder: Exited since project is closed"));
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
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatInheritanceBuilder: Executed full build"));
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
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "VirSatInheritanceBuilder: Could not read active StructuralElementInstance from Repository", e));
		}
	
		return listOfCAs;
	};

	@Override
	protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatInheritanceBuilder: Starting incremental build"));
		final int MAX_TASKS = 3;
		IInheritanceCopier inheritanceCopier = createInheritanceCopier();
		
		SubMonitor subMonitor = SubMonitor.convert(monitor, MAX_TASKS);
		subMonitor.beginTask("Run Incremental Inheritance Build...", MAX_TASKS);
		VirSatResourceSet resourceSet = getResourceSet();
		
		if (!resourceSet.isOpen()) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatInheritanceBuilder: Exited since project is closed"));
			return;
		}
		
		subMonitor.worked(1);
		
		SubMonitor loopMonitor = SubMonitor.convert(monitor);
		try {
			delta.accept(new IResourceDeltaVisitor() {
				public boolean visit(IResourceDelta delta) {
					IResource iResource = delta.getResource();
				
					if (iResource instanceof IFile) {
						// We should only process DVLm resources in terms of inheritance
						IFile iFile = (IFile) iResource;
						boolean fileIsNoDVLMResource = !VirSatProjectCommons.FILENAME_EXTENSION.equalsIgnoreCase(iFile.getFileExtension());
						boolean fileDoesNotExist = !iFile.exists();
						if (fileDoesNotExist || fileIsNoDVLMResource) {
							return true;
						}

						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(),
								"VirSatInheritanceBuilder: Processing incremental build on resource (" + iResource + ")"));
						
						Resource resource = resourceSet.safeGetResource(iFile, false);
						Repository repository = resourceSet.getRepository();
						Set<StructuralElementInstance> seis = getAllSeiInResource(resource);

						seis.forEach((sei) -> {
							try {
								// First remember to save all resources since a sei has been actually build.
								// This is referring to the ticket #714 which raised an issue with resource properties in
								// a document CA. Adding a new file is triggering an incremental build but actually no DVLM
								// file is touched or changed. Finally the editor was set into an incorrect state.
								triggerSaveAfterIncrementalBuild();
								
								// Now call the incremental inheritance processing on the affected SEI
								final int SUB_TASKS = 100;
								inheritanceCopier.updateInOrderFrom(sei, repository, loopMonitor.setWorkRemaining(SUB_TASKS).newChild(1));
								Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(),
										"VirSatInheritanceBuilder: Started incremental build on SEI (" + sei.getType().getName() + " " + sei.getName() + " " + sei.getUuid() + ")"));
							} catch (Exception e) {
								Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(),
										"VirSatInheritanceBuilder: Could not execute Inheritance Copier on SEI: " + sei.getFullQualifiedInstanceName()));
								vimHelper.createInheritanceMarker(IMarker.SEVERITY_ERROR, "Could not execute Inheritance Build on SEI " + sei.getFullQualifiedInstanceName(), sei);
							}
						});
					}

					return true; // visit children too
				}
			});
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "VirSatInheritanceBuilder: Received an exception", e));
		}

		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatInheritanceBuilder: Finished incremental build"));
	}
	
	/**
	 * This method creates an instance of the Inheritance Copier
	 * @return A new instance of the Inheritance Copier
	 */
	protected IInheritanceCopier createInheritanceCopier() {
		return new InheritanceCopier(virSatTed);
	}
}
