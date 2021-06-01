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

import de.dlr.sc.virsat.model.concept.types.IBeanName;
import de.dlr.sc.virsat.model.concept.types.IBeanUuid;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;

public interface IBeanUnit<U_TYPE extends AUnit> extends IBeanUuid, IBeanName {

	/**
	 * Get the concrete unit wrapped in this bean
	 * @return U_TYPE
	 */
	U_TYPE getUnit();
	
	/**
	 * Set the concrete unit wrapped in this bean
	 * @param U_TYPE
	 */
	void setUnit(U_TYPE unit);
	
	/**
	 * Get the unit wrapped in this bean
	 * @return AUnit
	 */
	AUnit getAUnit();
	
	/**
	 * Set the unit wrapped in this bean
	 * @param AUnit
	 */
	void setAUnit(AUnit unit);
	
	/**
	 * Get the symbol of the wrapped unit
	 * @return symbol
	 */
	String getSymbol();
	
	/**
	 * Set the symbol of the wrapped unit
	 * @param symbol
	 */
	void setSymbol(String symbol);
	
	/**
	 * Set the symbol of the wrapped unit
	 * @param ed The EditingDomain in which the command should act.
	 * @param symbol
	 * @return EMF command to set the symbol
	 */
	Command setSymbol(EditingDomain ed, String symbol);
	
	/**
	 * Get the quantityKindBean
	 * @return quantityKindBean bean wrapping a quantityKind
	 */
	IBeanQuantityKind<? extends AQuantityKind> getQuantityKindBean();
	
	/**
	 * Set the quantityKind of the wrapped unit
	 * @param quantityKindBean bean wrapping a quantityKind
	 */
	void setQuantityKindBean(IBeanQuantityKind<? extends AQuantityKind> quantityKindBean);
	
	/**
	 * Set the quantityKind of the wrapped unit
	 * @param ed The EditingDomain in which the command should act.
	 * @param quantityKind
	 * @return EMF command to set the quantityKind
	 */
	Command setQuantityKindBean(EditingDomain ed, IBeanQuantityKind<? extends AQuantityKind> quantityKindBean);
}
