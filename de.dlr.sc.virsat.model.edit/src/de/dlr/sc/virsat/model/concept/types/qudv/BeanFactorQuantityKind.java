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

import de.dlr.sc.virsat.model.concept.types.factory.BeanQuantityKindFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor;

public class BeanFactorQuantityKind {
	private QuantityKindFactor factor;
	
	public BeanFactorQuantityKind() { }
	
	public BeanFactorQuantityKind(QuantityKindFactor factor) {
		this.factor = factor;
	}
	
	QuantityKindFactor getFactor() {
		return factor;
	}
	
	void setFactor(QuantityKindFactor factor) {
		this.factor = factor;
	}
	
	Double getExponent() {
		return factor.getExponent();
	}
	
	void setExponent(Double exponent) {
		factor.setExponent(exponent);
	}
	
	IBeanQuantityKind getQuantityKind() {
		return new BeanQuantityKindFactory().getInstanceFor(factor.getQuantityKind());
	}
	
	void setQuantityKind(IBeanQuantityKind beanQuantityKind) {
		factor.setQuantityKind(beanQuantityKind.getQuantityKind());
	}
}
