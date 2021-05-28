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

import de.dlr.sc.virsat.model.concept.types.IBeanName;
import de.dlr.sc.virsat.model.concept.types.IBeanUuid;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;

public interface IBeanUnit extends IBeanUuid, IBeanName {

	/**
	 * Get the unit wrapped in this bean
	 * @return unit
	 */
	AUnit getUnit();
	
	/**
	 * Set the unit wrapped in this bean
	 * @param unit
	 */
	void setUnit(AUnit unit);
	
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
}
