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

import de.dlr.sc.virsat.model.concept.types.factory.BeanUnitFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor;

public class BeanUnitFactor {

	private UnitFactor factor;
	
	public BeanUnitFactor() { }
	
	public BeanUnitFactor(UnitFactor factor) {
		this.factor = factor;
	}
	
	UnitFactor getFactor() {
		return factor;
	}
	
	void setFactor(UnitFactor factor) {
		this.factor = factor;
	}
	
	Double getExponent() {
		return factor.getExponent();
	}
	
	void setExponent(Double exponent) {
		factor.setExponent(exponent);
	}
	
	IBeanUnit getUnit() {
		return new BeanUnitFactory().getInstanceFor(factor.getUnit());
	}
	
	void setUnit(IBeanUnit beanUnit) {
		factor.setUnit(beanUnit.getUnit());
	}
}
