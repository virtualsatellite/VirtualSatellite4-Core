/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.verification.impl;

import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.extension.requirements.model.IVerification;
import de.dlr.sc.virsat.model.extension.requirements.verification.build.steps.IAutomaticVerification;

/**
 * Abstract verification engine class with setter methods for the verification status
 *
 */
public abstract class AbstractAutomaticVerification implements IAutomaticVerification {
	
	protected EditingDomain editingDomain;
	protected IVerification verification;
	
	protected void setStatusCompliant() {
		editingDomain.getCommandStack().execute(verification.setStatus(editingDomain, IVerification.STATUS_FullyCompliant_NAME));
	}
	
	protected void setStatusNonCompliant() {
		editingDomain.getCommandStack().execute(verification.setStatus(editingDomain, IVerification.STATUS_NonCompliant_NAME));
	}
	
	protected void setStatusPartlyCompliant() {
		editingDomain.getCommandStack().execute(verification.setStatus(editingDomain, IVerification.STATUS_PartialCompliant_NAME));
	}
	
	protected void setStatusOpen() {
		editingDomain.getCommandStack().execute(verification.setStatus(editingDomain, IVerification.STATUS_Open_NAME));
	}

}
