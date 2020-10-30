/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.verification.build;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.resource.Resource;

import de.dlr.sc.virsat.build.inheritance.AVirSatTransactionalBuilder;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.requirements.Activator;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 *
 */
public class RequirementsVerificationBuilder extends AVirSatTransactionalBuilder {

	public static final String BUILDER_ID = "de.dlr.sc.virsat.model.extension.requirements.build.verification";
	
	protected List<IVerificationStep> verificationSteps = new ArrayList<IVerificationStep>();
	
	/**
	 * @param builderName
	 * @param vpmHelper
	 * @param redirectIncrementalToAutoBuild
	 * @param dvlmOnly
	 */
	public RequirementsVerificationBuilder() {
		super("Requirements Verification Builder", new VirSatProblemMarkerHelper(), true, true);
		verificationSteps.add(new RequirementVerificationRunner());
		verificationSteps.add(new RequirementsStatusUpdater());
	}

	@Override
	protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VerificationBuilder: Starting incremental build!"));
		
		try {
			VirSatResourceSet resourceSet = getResourceSet();
			
			if (resourceSet != null && !resourceSet.isOpen()) {
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VerificationBuilder: Exited since project is closed!"));
				return;
			}
			
			delta.accept(new IResourceDeltaVisitor() {
				public boolean visit(IResourceDelta delta) {
					IResource iResource = delta.getResource();
				
					if (iResource instanceof IFile) {
						// We should only process DVLM resources in terms of model updates
						IFile iFile = (IFile) iResource;
												
						if (!iFile.exists() || !VirSatProjectCommons.isDvlmFile(iFile)) {
							return true;
						}

						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(),
								"VerificationBuilder: Processing incremental build on resource (" + iResource + ")"));
						
						Resource resource = resourceSet.safeGetResource(iFile, false);
						if (resource.getContents().get(0) instanceof StructuralElementInstance) {
							buildSei(new BeanStructuralElementInstance((StructuralElementInstance) resource.getContents().get(0)), monitor);
						}
						
					}

					return true; // visit children too
				}
			});
			
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "VerificationBuilder: Received an exception", e));
		}
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VerificationBuilder: Finished incremental build!"));
	}

	@Override
	protected void fullBuild(IProgressMonitor monitor) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VerificationBuilder: Starting full build!"));
		try {

			VirSatResourceSet resourceSet = getResourceSet();
			if (resourceSet != null && !resourceSet.isOpen()) {
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VerificationBuilder: Exited since project is closed!"));
				return;
			}
			
			Set<StructuralElementInstance> seis = getResourceSet().getAllSeisInProject();
			
			SubMonitor subMonitor = SubMonitor.convert(monitor, seis.size());
			subMonitor.beginTask("Executing Verification of SEIs", seis.size());
			
			for (StructuralElementInstance sei : seis) {
				BeanStructuralElementInstance bean = new BeanStructuralElementInstance(sei);
				buildSei(bean, subMonitor);
				subMonitor.worked(1);
			}
			
			
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VerificationBuilder: Finished full build!"));
		
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "VerificationBuilder: Error occured!", e));
		}
		
	}
	
	/**
	 * Run the verification on a SEI within the project
	 * @param seiBean the SEI Bean
	 * @param monitor the progress monitor
	 */
	protected void buildSei(BeanStructuralElementInstance seiBean, IProgressMonitor monitor) {
		List<CategoryAssignment> caSpecifications = seiBean.getStructuralElementInstance().getCategoryAssignments().stream().
				filter(ca -> ca.getType().getFullQualifiedName().equals(RequirementsSpecification.FULL_QUALIFIED_CATEGORY_NAME)).
				collect(Collectors.toList());
		
		int verificationTaskNumber = caSpecifications.size() * verificationSteps.size();
		SubMonitor subMonitor = SubMonitor.convert(monitor, verificationTaskNumber);
		subMonitor.beginTask("Executing Verification of SEI", verificationTaskNumber);
		
		for (CategoryAssignment specification : caSpecifications) {
			RequirementsSpecification specBean = new RequirementsSpecification(specification);
			for (IVerificationStep step : verificationSteps) {
				step.execute(specBean, virSatTed, subMonitor);
				subMonitor.worked(1);
			}
			subMonitor.worked(1);
		}
	}

}
