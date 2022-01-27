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

import org.eclipse.core.runtime.IProgressMonitor;

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
		IAutomaticVerification verificationRunner = getCustomVerification(verification);
		if (verificationRunner != null) {
			editingDomain.getCommandStack().execute(verificationRunner.runCustomVerification(editingDomain, requirement, monitor));
		}
	}
	
	
	/**
	 * Get the verification engine for the verification model element
	 * @return the label provider
	 */
	protected IAutomaticVerification getCustomVerification(IVerification modelElement) {
		if (modelElement instanceof IAutomaticVerification) {
			return (IAutomaticVerification) modelElement;
		}
		return null;
	}

}
