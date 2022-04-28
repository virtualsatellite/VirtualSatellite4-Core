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

public interface IBeanQuantityKind<QK_TYPE extends AQuantityKind> extends IBeanUuid, IBeanName {

	/**
	 * Get the concrete quantityKind wrapped in this bean
	 * @return QK_TYPE
	 */
	QK_TYPE getQuantityKind();
	
	/**
	 * Set the concrete quantityKind wrapped in this bean
	 * @param QK_TYPE
	 */
	void setQuantityKind(QK_TYPE quantityKind);
	
	/**
	 * Get the quantityKind wrapped in this bean
	 * @return quantityKind
	 */
	AQuantityKind getAQuantityKind();
	
	/**
	 * Set the quantityKind wrapped in this bean
	 * @param quantityKind
	 */
	void setAQuantityKind(AQuantityKind quantityKind);
	
	/**
	 * Get the symbol of the wrapped quantity kind
	 * @return symbol
	 */
	String getSymbol();
	
	/**
	 * Set the symbol of the wrapped quantity kind
	 * @param symbol
	 */
	void setSymbol(String symbol);
	
	/**
	 * Set the symbol of the wrapped quantity kind
	 * @param ed The EditingDomain in which the command should act.
	 * @param symbol
	 * @return EMF command to set the symbol
	 */
	Command setSymbol(EditingDomain ed, String symbol);
}
