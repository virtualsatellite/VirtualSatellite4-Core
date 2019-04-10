/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.transactional.handler;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * Common interface for transactional handlers that add virsat elements
 * @author muel_s8
 *
 */

public interface ITransactionalVirSatAddHandler {
	
	/**
	 * Creates the virsat object and an add command to add it to the passed parent
	 * @param ed the editing domain
	 * @param parent the parent
	 * @return the add command
	 */
	Command createObjectAndAddCommand(EditingDomain ed, EObject parent);
	
	/**
	 * Initializes the fields of the add handler using the passed parent
	 * @param parent the parent
	 */
	void initializeFieldsFromParentObject(EObject parent);
}
