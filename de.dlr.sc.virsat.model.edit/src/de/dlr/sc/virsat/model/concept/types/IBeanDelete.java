/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.concept.types;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * Interface to have a common access to the deletion of beans
 * @author muel_s8
 *
 */

public interface IBeanDelete {
	
	/**
	 * Use this method to delete the wrapped object
	 */
	void delete();
	
	/**
	 * Use this method to delete the wrapped object
	 * @param ed The EditingDomain in which the command should act.
	 * @return hands back an EMF command to delete the wrapped object
	 */
	Command delete(EditingDomain ed);
}
