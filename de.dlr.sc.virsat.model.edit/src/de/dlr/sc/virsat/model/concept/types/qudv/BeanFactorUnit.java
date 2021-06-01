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
import de.dlr.sc.virsat.model.concept.types.factory.BeanUnitFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;
import de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor;

public class BeanFactorUnit implements IBeanUuid {

	private UnitFactor factor;
	
	public BeanFactorUnit() { }
	
	public BeanFactorUnit(UnitFactor factor) {
		this.factor = factor;
	}
	
	@Override
	public String getUuid() {
		return factor.getUuid().toString();
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
	
	public Command setExponent(EditingDomain ed, Double exponent) {
		return SetCommand.create(ed, factor, QudvPackage.Literals.UNIT_FACTOR__EXPONENT, exponent);
	}
	
	IBeanUnit<? extends AUnit> getUnitBean() {
		return new BeanUnitFactory().getInstanceFor(factor.getUnit());
	}
	
	void setUnitBean(IBeanUnit<? extends AUnit> beanUnit) {
		factor.setUnit(beanUnit.getUnit());
	}
	
	public Command setUnitBean(EditingDomain ed, IBeanUnit<? extends AUnit> beanUnit) {
		return SetCommand.create(ed, factor, QudvPackage.Literals.UNIT_FACTOR__UNIT, beanUnit.getUnit());
	}
}
