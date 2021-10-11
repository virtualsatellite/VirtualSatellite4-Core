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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.IBeanUuid;
import de.dlr.sc.virsat.model.concept.types.factory.BeanUnitFactory;
import de.dlr.sc.virsat.model.dvlm.json.ABeanUnitAdapter;
import de.dlr.sc.virsat.model.dvlm.json.IUuidAdapter;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;
import de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlAccessorType(XmlAccessType.NONE)
//Ensure that the factor (by uuid) gets unmarshalled first
@XmlType(propOrder = {"factor", "exponent", "unitBean"})
@ApiModel(description = "Model class for bean unit factor.")
public class BeanFactorUnit implements IBeanUuid {

	private UnitFactor factor;
	
	public BeanFactorUnit() { }
	
	public BeanFactorUnit(UnitFactor factor) {
		this.factor = factor;
	}
	
	@ApiModelProperty(hidden = true)
	@Override
	public String getUuid() {
		return factor.getUuid().toString();
	}
	
	@ApiModelProperty(name = "uuid", required = true,
			value = "Unique identifier for a bean",
			example = "b168b0df-84b6-4b7f-bede-69298b215f40")
	@XmlElement(name = "uuid")
	@XmlJavaTypeAdapter(IUuidAdapter.class)
	UnitFactor getFactor() {
		return factor;
	}
	
	void setFactor(UnitFactor factor) {
		this.factor = factor;
	}
	
	@ApiModelProperty(required = true)
	@XmlElement(nillable = true)
	double getExponent() {
		return factor.getExponent();
	}
	
	void setExponent(double exponent) {
		factor.setExponent(exponent);
	}
	
	public Command setExponent(EditingDomain ed, double exponent) {
		return SetCommand.create(ed, factor, QudvPackage.Literals.UNIT_FACTOR__EXPONENT, exponent);
	}
	

	@ApiModelProperty(required = true)
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanUnitAdapter.class)
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
