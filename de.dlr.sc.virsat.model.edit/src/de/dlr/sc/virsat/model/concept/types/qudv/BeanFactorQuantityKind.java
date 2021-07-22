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
import de.dlr.sc.virsat.model.concept.types.factory.BeanQuantityKindFactory;
import de.dlr.sc.virsat.model.dvlm.json.ABeanQuantityKindAdapter;
import de.dlr.sc.virsat.model.dvlm.json.IUuidAdapter;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlAccessorType(XmlAccessType.NONE)
//Ensure that the factor (by uuid) gets unmarshalled first
@XmlType(propOrder = {"factor", "exponent", "quantityKindBean"})
@ApiModel(description = "Model class for bean quantity kind factor.")
public class BeanFactorQuantityKind implements IBeanUuid {
	
	private QuantityKindFactor factor;
	
	public BeanFactorQuantityKind() { }
	
	public BeanFactorQuantityKind(QuantityKindFactor factor) {
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
	QuantityKindFactor getFactor() {
		return factor;
	}
	
	void setFactor(QuantityKindFactor factor) {
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
		return SetCommand.create(ed, factor, QudvPackage.Literals.QUANTITY_KIND_FACTOR__EXPONENT, exponent);
	}
	
	@SuppressWarnings("rawtypes")
	@ApiModelProperty(required = true)
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanQuantityKindAdapter.class)
	ABeanQuantityKind getQuantityKindBean() {
		return (ABeanQuantityKind) new BeanQuantityKindFactory().getInstanceFor(factor.getQuantityKind());
	}
	
	@SuppressWarnings("rawtypes")
	void setQuantityKindBean(ABeanQuantityKind beanQuantityKind) {
		factor.setQuantityKind(beanQuantityKind.getQuantityKind());
	}
	
	public Command setQuantityKindBean(EditingDomain ed, IBeanQuantityKind<? extends AQuantityKind> beanQuantityKind) {
		return SetCommand.create(ed, factor, QudvPackage.Literals.QUANTITY_KIND_FACTOR__QUANTITY_KIND, beanQuantityKind.getQuantityKind());
	}
}
