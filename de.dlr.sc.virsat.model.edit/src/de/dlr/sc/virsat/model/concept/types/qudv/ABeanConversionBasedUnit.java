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

import de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit;

public class ABeanConversionBasedUnit<U_TYPE extends AConversionBasedUnit> extends ABeanUnit<U_TYPE> {

	public ABeanConversionBasedUnit() {
		super();
	}
	
	public ABeanConversionBasedUnit(U_TYPE unit) {
		super(unit);
	}
	
	Boolean getIsInvertible() {
		return unit.isIsInvertible();
	}
	
	void setIsInvertible(Boolean isInverible) {
		unit.setIsInvertible(isInverible);
	}
}
