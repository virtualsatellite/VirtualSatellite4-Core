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
 * Interface to have a common access to name related methods of beans
 * @author muel_s8
 *
 */

public interface IBeanName {
	/**
	 * Use this method to access the name of the object wrapped by this bean
	 * @return The Name as String
	 */
	String getName();
	
	/**
	 * Use this method to set the name of the object wrapped by this bean
	 * @param name the new name as string
	 */
	void setName(String name);
	
	/**
	 * Use this method to set the name of the object wrapped by this bean
	 * @param ed The EditingDomain in which the command should act.
	 * @param name the new name as string
	 * @return hands back an EMF command to manipulate the name of the object wrapped by this bean
	 */
	Command setName(EditingDomain ed, String name);
}
