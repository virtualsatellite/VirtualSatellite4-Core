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
	 * @return the unit
	 */
	U_TYPE getUnit();
	
	/**
	 * Set the concrete unit wrapped in this bean
	 * @param unit the unit
	 */
	void setUnit(U_TYPE unit);
	
	/**
	 * Get the unit wrapped in this bean
	 * @return the wrapped unit
	 */
	AUnit getAUnit();
	
	/**
	 * Set the unit wrapped in this bean
	 * @param unit the wrapped unit
	 */
	void setAUnit(AUnit unit);
	
	/**
	 * Get the symbol of the wrapped unit
	 * @return the symbol of the unit
	 */
	String getSymbol();
	
	/**
	 * Set the symbol of the wrapped unit
	 * @param symbol the new symbol of the unit
	 */
	void setSymbol(String symbol);
	
	/**
	 * Set the symbol of the wrapped unit
	 * @param ed The EditingDomain in which the command should act.
	 * @param symbol the symbol
	 * @return EMF command to set the symbol
	 */
	Command setSymbol(EditingDomain ed, String symbol);
	
	/**
	 * Get the quantityKindBean
	 * @return quantityKindBean bean wrapping a quantityKind
	 */
	ABeanQuantityKind<? extends AQuantityKind> getQuantityKindBean();
	
	/**
	 * Set the quantityKind of the wrapped unit
	 * @param quantityKindBean bean wrapping a quantityKind
	 */
	void setQuantityKindBean(ABeanQuantityKind<? extends AQuantityKind> quantityKindBean);
	
	/**
	 * Set the quantityKind of the wrapped unit
	 * @param ed The EditingDomain in which the command should act.
	 * @param quantityKind the quantitity kind
	 * @return EMF command to set the quantityKind
	 */
	Command setQuantityKindBean(EditingDomain ed, ABeanQuantityKind<? extends AQuantityKind> quantityKindBean);
}
