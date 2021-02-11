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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;

/**
 * Class that runs customized verification methods on requirements
 *
 */
public class RequirementVerificationRunner implements IVerificationStep {

	protected EditingDomain editingDomain;
	
	@Override
	public void execute(RequirementsSpecification specification, EditingDomain editingDomain, IProgressMonitor monitor) {
		this.editingDomain = editingDomain;
	}

}
