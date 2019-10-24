/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.validator;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

import de.dlr.sc.virsat.build.marker.util.VirSatValidationMarkerHelper;
import de.dlr.sc.virsat.build.validator.external.IRepositoryValidator;
import de.dlr.sc.virsat.build.validator.external.IStructuralElementInstanceValidator;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * Eclipse builder that runs validators on the model
 *
 */
public class VirSatValidatorBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = "de.dlr.sc.virsat.build.validator";
	private VirSatResourceSet resourceSet;
	private Set<IStructuralElementInstanceValidator> seiValidators = new HashSet<>();
	private Set<IRepositoryValidator> repoValidators = new HashSet<>();

	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatValidatorBuilder: Try to trigger custom build", null));
		
		IProject project = getVirSatProject();
		VirSatTransactionalEditingDomain virSatTed = VirSatEditingDomainRegistry.INSTANCE.getEd(project);
		if (virSatTed == null) {
			return null;
		}
		
		if (!getResourceSet().isOpen()) {
			return null;
		}

		readConceptValidatorExtensionPoints();
		
		switch (kind) {  
			case FULL_BUILD:
				fullBuild(monitor, seiValidators, repoValidators);
				break;  
			case INCREMENTAL_BUILD:
				IResourceDelta delta = getDelta(project);
				if (delta == null) {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatValidatorBuilder: Performing full build", null));
					fullBuild(monitor, seiValidators, repoValidators);
				} else {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatValidatorBuilder: Performing incremental build", null));
					incrementalBuild(monitor, seiValidators, repoValidators);
				}
				break;
			case AUTO_BUILD:
				fullBuild(monitor, seiValidators, repoValidators);
				break;
			default:
				break;  
		}
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatValidatorBuilder: Finished Custom build", null));
		
		return null; //new IProject[]{project};
	}
	

	/**
	 * the fullBuild method is executed to trigger a build with all necessary steps.
	 * @param monitor ProgressMonitor to show progress to Eclipse Framework 
	 * @param seiValidators The validators for the seis to be used with this run 
	 * @param repoValidators The repo specific validators to be used with this run
	 */
	protected void fullBuild(IProgressMonitor monitor, Set<IStructuralElementInstanceValidator> seiValidators, Set<IRepositoryValidator> repoValidators) {
	
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatValidatorBuilder: Started full build", null));
		
		Set<StructuralElementInstance> seis = getResourceSet().getAllSeisInProject();
		Repository repo = getResourceSet().getRepository();
		VirSatValidationMarkerHelper vvmHelper = new VirSatValidationMarkerHelper();
		
		int maxTasks = seis.size() * seiValidators.size() + repoValidators.size();
		
		SubMonitor subMonitor = SubMonitor.convert(monitor, maxTasks);
		subMonitor.beginTask("Executing Validation of Repository and SEIs", maxTasks);
		
		// check all the resources and delete the existing markers 
		vvmHelper.deleteAllMarkersInWorkspace();
		
		// now execute all Validators for the Repository
		subMonitor.subTask("Validating Repository: " + repo.getUuid());
		for (IRepositoryValidator validator : repoValidators) {
			try {
				validator.validate(repo);
			} catch (Exception e) {
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "VirSatValidatorBuilder: Could not execute Validator on Repository"  + repo.getUuid(), e));
				vvmHelper.createDVLMValidationMarker(IMarker.SEVERITY_ERROR, "Could not execute validation on Repository " + repo.getUuid(), repo);	
			}
			subMonitor.worked(1);
		}
		
		// now execute all Validators for all the StructuralElementInstances
		for (StructuralElementInstance sei : seis) {
			subMonitor.subTask("Validating SEI: " + sei.getFullQualifiedInstanceName());
			for (IStructuralElementInstanceValidator validator : seiValidators) {
				try {
					validator.validate(sei);
				} catch (Exception e) {
					Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "VirSatValidatorBuilder: Could not execute Validator on SEI" + sei.getFullQualifiedInstanceName(), e));
					vvmHelper.createDVLMValidationMarker(IMarker.SEVERITY_ERROR, "Could not execute validation on SEI " + sei.getFullQualifiedInstanceName(), sei);	
				}
				subMonitor.worked(1);
			}
		}
		
		subMonitor.done();
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatValidatorBuilder: Executed full build", null));

	}
	
	public static final String EXTENSION_POINT_ID_CONCEPT_VALIDATOR = "de.dlr.sc.virsat.build.DvlmValidator";
	
	/**
	 * call this method to read the extension point for the concept images
	 * It registers the found images in the bundles ImgageRegistry
	 */
	private void readConceptValidatorExtensionPoints() {
		seiValidators.clear();
		repoValidators.clear();
		
		Set<String> activeConceptIds = resourceSet.getRepository().getActiveConcepts().stream()
				.map(c -> c.getFullQualifiedName()).collect(Collectors.toSet());
		
		// Now go to the registry and read them again. This is just a bug
		// fix and may lead to performance issues.It should be considered
		// to initialize them in a singleton manner later on
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		
		IConfigurationElement[] configElements = registry.getConfigurationElementsFor(EXTENSION_POINT_ID_CONCEPT_VALIDATOR);
		
		for (IConfigurationElement configElement : configElements) {
			for (IConfigurationElement validatorConfigElement : configElement.getChildren()) {
				String contributor = validatorConfigElement.getContributor().getName();
				boolean conceptSpecific = contributor.startsWith("de.dlr.sc.virsat.model.extension");
				if (!conceptSpecific || activeConceptIds.contains(contributor)) {
					try {
						Object validator = validatorConfigElement.createExecutableExtension("class");
						
						if (validator instanceof IStructuralElementInstanceValidator) {
							IStructuralElementInstanceValidator seiValidator = (IStructuralElementInstanceValidator) validator;
							seiValidators.add(seiValidator);
						} else if (validator instanceof IRepositoryValidator) {
							IRepositoryValidator repoValidator = (IRepositoryValidator) validator;
							repoValidators.add(repoValidator);
						}
					} catch (CoreException e) {
						Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "VirSatValidatorBuilder: Could not create validator for " + validatorConfigElement, e));
					}
				}
			}
		}
	}

	/**
	 * the method triggers an incremental build which should re-use some previously build artifacts.
	 * Currently we do not distinguish between full build and incremental build.
	 * @param monitor ProgressMonitor to show progress to Eclipse Framework 
	 * @param seiValidators The validators for the seis to be used with this run 
	 * @param repoValidators The repo specific validators to be used with this run
	 */
	protected void incrementalBuild(IProgressMonitor monitor, Set<IStructuralElementInstanceValidator> seiValidators, Set<IRepositoryValidator> repoValidators) {
		fullBuild(monitor, seiValidators, repoValidators);
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
		if (resourceSet == null) {
			resourceSet = VirSatResourceSet.getResourceSet(getVirSatProject());
		}
		return resourceSet;
	}
	
	@Override
	public ISchedulingRule getRule(int kind, Map<String, String> args) {
		return null;
	}
}
