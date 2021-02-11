/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.verification.build.steps;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;

import de.dlr.sc.virsat.model.extension.requirements.model.IVerification;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;

/**
 * Verifications step that checks the registry if there is a custom verification implementation for the specified verification model element
 * and if so executes it 
 *
 */
public class VerificationConfigurationElementRunner extends AbstractRequirementVerificationStep {

	public static final String EXTENSION_POINT_ID_CUSTOM_VERIFICATION = "de.dlr.sc.virsat.model.extension.requirements.verification";
	public static final String MODEL_ELEMENT_ATTRIBUTE_NAME = "model_element";
	public static final String VERIFICATION_ELEMENT_ATTRIBUTE_NAME = "verification_impl";
	
	@Override
	protected void doVerificationStep(IVerification verification, Requirement requirement, IProgressMonitor monitor) {
		System.out.println(verification.getClass().getPackage() + "." + verification.getClass().getName());
		IAutomaticVerification verificationRunner = getCustomVerification(verification);
		if (verificationRunner != null) {
			verificationRunner.runCustomVerification(editingDomain, verification, requirement, monitor);
		}
	}
	
	
	/**
	 * Get the verification engine for the verification model element
	 * @return the label provider
	 */
	public IAutomaticVerification getCustomVerification(IVerification modelElement) {
		IVerification verificationModelElement = null;
		IAutomaticVerification verificationEngine = null;
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] configElements = registry.getConfigurationElementsFor(EXTENSION_POINT_ID_CUSTOM_VERIFICATION);
		for (IConfigurationElement element : configElements) {
			try {
				verificationModelElement = (IVerification) element.createExecutableExtension(MODEL_ELEMENT_ATTRIBUTE_NAME);
				verificationEngine = (IAutomaticVerification) element.createExecutableExtension(VERIFICATION_ELEMENT_ATTRIBUTE_NAME);
			} catch (CoreException e) {
				de.dlr.sc.virsat.model.extension.requirements.Activator.getDefault().getLog().error("Could not get custom verification", e);
				return null;
			}
			if (verificationModelElement.getFullQualifiedCategoryName().equals(modelElement.getFullQualifiedCategoryName())) {
				return verificationEngine;
			}
		}
		return null;
	}

}
