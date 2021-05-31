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

import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor;

public class BeanQuantityKindDerived extends ABeanQuantityKind<DerivedQuantityKind> {

	/**
	 * Get all factors
	 * @return List of factors
	 */
	List<BeanFactorQuantityKind> getFactors() {
		List<BeanFactorQuantityKind> factors = new ArrayList<BeanFactorQuantityKind>();
		
		for (QuantityKindFactor factor : quantityKind.getFactor()) {
			factors.add(new BeanFactorQuantityKind(factor));
		}
		
		return factors;
	}
	
	/**
	 * Set all factors
	 * @param newBeanFactors List of new factors
	 */
	void setFactors(List<BeanFactorQuantityKind> newBeanFactors) {
		List<QuantityKindFactor> currentFactors = quantityKind.getFactor();

		List<QuantityKindFactor> newFactors = new ArrayList<QuantityKindFactor>();
		for (BeanFactorQuantityKind beanFactor : newBeanFactors) {
			newFactors.add(beanFactor.getFactor());
		}
		
		currentFactors.clear();
		currentFactors.addAll(newFactors);
	}
	
	void addFactor(BeanFactorQuantityKind beanFactor) {
		quantityKind.getFactor().add(beanFactor.getFactor());
	}
	
	void removeFactor(BeanFactorQuantityKind beanFactor) {
		quantityKind.getFactor().remove(beanFactor.getFactor());
	}
}
