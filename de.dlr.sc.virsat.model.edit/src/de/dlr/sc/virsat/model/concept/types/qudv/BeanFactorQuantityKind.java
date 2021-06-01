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
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.IBeanUuid;
import de.dlr.sc.virsat.model.concept.types.factory.BeanQuantityKindFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;

public class BeanFactorQuantityKind implements IBeanUuid {
	private QuantityKindFactor factor;
	
	public BeanFactorQuantityKind() { }
	
	public BeanFactorQuantityKind(QuantityKindFactor factor) {
		this.factor = factor;
	}
	
	@Override
	public String getUuid() {
		return factor.getUuid().toString();
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
	
	public Command setExponent(EditingDomain ed, Double exponent) {
		return SetCommand.create(ed, factor, QudvPackage.Literals.QUANTITY_KIND_FACTOR__EXPONENT, exponent);
	}
	
	IBeanQuantityKind getQuantityKindBean() {
		return new BeanQuantityKindFactory().getInstanceFor(factor.getQuantityKind());
	}
	
	void setQuantityKindBean(IBeanQuantityKind beanQuantityKind) {
		factor.setQuantityKind(beanQuantityKind.getQuantityKind());
	}
	
	public Command setQuantityKindBean(EditingDomain ed, IBeanQuantityKind beanQuantityKind) {
		return SetCommand.create(ed, factor, QudvPackage.Literals.QUANTITY_KIND_FACTOR__QUANTITY_KIND, beanQuantityKind.getQuantityKind());
	}
}
