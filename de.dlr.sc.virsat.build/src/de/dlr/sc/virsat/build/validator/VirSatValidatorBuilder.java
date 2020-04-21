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

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

import de.dlr.sc.virsat.build.inheritance.AVirSatBuilder;
import de.dlr.sc.virsat.build.marker.util.VirSatValidationMarkerHelper;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.validator.IRepositoryValidator;
import de.dlr.sc.virsat.model.dvlm.validator.IStructuralElementInstanceValidator;
import de.dlr.sc.virsat.model.dvlm.validator.RepoValidatorsInstantiator;
import de.dlr.sc.virsat.project.Activator;

/**
 * Eclipse builder that runs validators on the model
 *
 */
public class VirSatValidatorBuilder extends AVirSatBuilder {

	public VirSatValidatorBuilder() {
		super("Validation Builder", new VirSatValidationMarkerHelper());
		this.vvmHelper = (VirSatValidationMarkerHelper) this.vpmHelper;
	}

	protected VirSatValidationMarkerHelper vvmHelper;

	public static final String BUILDER_ID = "de.dlr.sc.virsat.build.validator";

	/**
	 * This method hands back all SEI validators found for the project
	 * @return List of Sei Validators
	 */
	protected List<IStructuralElementInstanceValidator> getSeiValidators() {
		RepoValidatorsInstantiator validatorsInstantiator = new RepoValidatorsInstantiator(getResourceSet().getRepository());
		return validatorsInstantiator.getSeiValidators();
	}
	
	/**
	 * This method hands back all Repo validators found for the project
	 * @return List of Repo Validators
	 */
	protected List<IRepositoryValidator> getRepoValidators() {
		RepoValidatorsInstantiator validatorsInstantiator = new RepoValidatorsInstantiator(getResourceSet().getRepository());
		return validatorsInstantiator.getRepoValidators();
	}
	
	@Override
	protected void fullBuild(IProgressMonitor monitor) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatValidatorBuilder: Started full build", null));
		
		List<IStructuralElementInstanceValidator> seiValidators = getSeiValidators();
		List<IRepositoryValidator> repoValidators = getRepoValidators();
		
		Set<StructuralElementInstance> seis = getResourceSet().getAllSeisInProject();
		Repository repo = getResourceSet().getRepository();
		
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

	@Override
	protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) {
		fullBuild(monitor);
	}
	
	@Override
	public ISchedulingRule getRule(int kind, Map<String, String> args) {
		// Validation Builder is not critical and does not need specific locking on the workspace
		return null;
	}
}
