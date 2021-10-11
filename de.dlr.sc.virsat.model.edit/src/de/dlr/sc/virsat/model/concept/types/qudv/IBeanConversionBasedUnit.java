/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.qudv;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;

public interface IBeanConversionBasedUnit {
	
	/**
	 * Returns if the unit is invertible
	 * @return Boolean
	 */
	Boolean getIsInvertible();
	
	/**
	 * Set value of isInverible
	 * @param isInverible
	 */
	void setIsInvertible(Boolean isInverible);
	
	/**
	 * Set value of isInverible via EMF command
	 * @param ed The EditingDomain in which the command should act.
	 * @param isInverible
	 * @return EMF command to set the quantityKind
	 */
	Command setIsInvertible(EditingDomain ed, Boolean isInverible);
}
