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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

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
import io.swagger.v3.oas.annotations.media.Schema;



@XmlAccessorType(XmlAccessType.NONE)
//Ensure that the factor (by uuid) gets unmarshalled first
@XmlType(propOrder = {"factor", "exponent", "quantityKindBean"})
@Schema(description = "Model class for bean quantity kind factor.")
public class BeanFactorQuantityKind implements IBeanUuid {
	
	private QuantityKindFactor factor;
	
	public BeanFactorQuantityKind() { }
	
	public BeanFactorQuantityKind(QuantityKindFactor factor) {
		this.factor = factor;
	}
	
	@Schema(hidden = true)
	@Override
	public String getUuid() {
		return factor.getUuid().toString();
	}
	
	@Schema(name = "uuid", required = true,
			description = "Unique identifier for a bean",
			example = "b168b0df-84b6-4b7f-bede-69298b215f40")
	@XmlElement(name = "uuid")
	@XmlJavaTypeAdapter(IUuidAdapter.class)
	public QuantityKindFactor getFactor() {
		return factor;
	}
	
	public void setFactor(QuantityKindFactor factor) {
		this.factor = factor;
	}
	
	@Schema(required = true)
	@XmlElement
	public double getExponent() {
		return factor.getExponent();
	}
	
	public void setExponent(double exponent) {
		factor.setExponent(exponent);
	}
	
	public Command setExponent(EditingDomain ed, double exponent) {
		return SetCommand.create(ed, factor, QudvPackage.Literals.QUANTITY_KIND_FACTOR__EXPONENT, exponent);
	}
	
	@SuppressWarnings("rawtypes")
	@Schema(required = true)
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanQuantityKindAdapter.class)
	public ABeanQuantityKind getQuantityKindBean() {
		return (ABeanQuantityKind) new BeanQuantityKindFactory().getInstanceFor(factor.getQuantityKind());
	}
	
	@SuppressWarnings("rawtypes")
	public void setQuantityKindBean(ABeanQuantityKind beanQuantityKind) {
		factor.setQuantityKind(beanQuantityKind.getQuantityKind());
	}
	
	public Command setQuantityKindBean(EditingDomain ed, IBeanQuantityKind<? extends AQuantityKind> beanQuantityKind) {
		return SetCommand.create(ed, factor, QudvPackage.Literals.QUANTITY_KIND_FACTOR__QUANTITY_KIND, beanQuantityKind.getQuantityKind());
	}
}
