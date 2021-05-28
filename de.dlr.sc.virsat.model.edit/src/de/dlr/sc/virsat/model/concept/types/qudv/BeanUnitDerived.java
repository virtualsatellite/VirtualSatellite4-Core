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


import java.util.ArrayList;
import java.util.List;

import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor;

public class BeanUnitDerived extends ABeanUnit<DerivedUnit> {

	/**
	 * Get all factors
	 * @return List of factors
	 */
	List<BeanUnitFactor> getFactors() {
		List<BeanUnitFactor> factors = new ArrayList<BeanUnitFactor>();
		
		for (UnitFactor factor : unit.getFactor()) {
			factors.add(new BeanUnitFactor(factor));
		}
		
		return factors;
	}
	
	/**
	 * Set all factors
	 * @param newBeanFactors List of new factors
	 */
	void setFactors(List<BeanUnitFactor> newBeanFactors) {
		List<UnitFactor> currentFactors = unit.getFactor();

		List<UnitFactor> newFactors = new ArrayList<UnitFactor>();
		for (BeanUnitFactor beanFactor : newBeanFactors) {
			newFactors.add(beanFactor.getFactor());
		}
		
		currentFactors.clear();
		currentFactors.addAll(newFactors);
	}
	
	void addFactor(BeanUnitFactor beanFactor) {
		unit.getFactor().add(beanFactor.getFactor());
	}
	
	void removeFactor(BeanUnitFactor beanFactor) {
		unit.getFactor().remove(beanFactor.getFactor());
	}
}
