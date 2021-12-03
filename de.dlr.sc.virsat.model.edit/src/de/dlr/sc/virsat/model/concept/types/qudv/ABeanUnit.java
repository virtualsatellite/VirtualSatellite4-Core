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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.factory.BeanQuantityKindFactory;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.json.ABeanQuantityKindAdapter;
import de.dlr.sc.virsat.model.dvlm.json.IUuidAdapter;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * General bean for a unit of the type U_TYPE
 * 
 * @param <U_TYPE> type of the wrapped unit
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
@ApiModel(
	description = "Abstract model class for bean units."
		+ " Resources that return this will instead return concrete bean units.",
	subTypes = {
		ABeanConversionBasedUnit.class,
		BeanUnitSimple.class,
		BeanUnitDerived.class
})
public abstract class ABeanUnit<U_TYPE extends AUnit> implements IBeanUnit<U_TYPE> {

	protected U_TYPE unit;
	
	public ABeanUnit() { }
	
	public ABeanUnit(U_TYPE unit) {
		this.unit = unit;
	}
	
	@ApiModelProperty(name = "uuid", required = true,
			value = "Unique identifier for a bean",
			example = "b168b0df-84b6-4b7f-bede-69298b215f40")
	@XmlElement(name = "uuid")
	@XmlJavaTypeAdapter(IUuidAdapter.class)
	@Override
	public AUnit getAUnit() {
		return unit;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setAUnit(AUnit unit) {
		this.unit = (U_TYPE) unit;
	}
	
	@ApiModelProperty(hidden = true)
	@Override
	public U_TYPE getUnit() {
		return unit;
	}
	
	@Override
	public void setUnit(U_TYPE unit) {
		this.unit = unit;
	}
	
	@ApiModelProperty(hidden = true)
	@Override
	public String getUuid() {
		return unit.getUuid().toString();
	}

	@ApiModelProperty(required = true)
	@XmlElement(nillable = true)
	@Override
	public String getName() {
		return unit.getName();
	}

	@Override
	public void setName(String name) {
		unit.setName(name);
	}

	@Override
	public Command setName(EditingDomain ed, String name) {
		return SetCommand.create(ed, unit, GeneralPackage.Literals.INAME__NAME, name);
	}

	@ApiModelProperty(required = true)
	@XmlElement(nillable = true)
	@Override
	public String getSymbol() {
		return unit.getSymbol();
	}

	@Override
	public void setSymbol(String symbol) {
		unit.setSymbol(symbol);
	}

	@Override
	public Command setSymbol(EditingDomain ed, String symbol) {
		return SetCommand.create(ed, unit, QudvPackage.Literals.AUNIT__SYMBOL, symbol);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiModelProperty(required = true)
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ABeanQuantityKindAdapter.class)
	@Override
	public ABeanQuantityKind getQuantityKindBean() {
		if (unit.getQuantityKind() == null) {
			return null;
		}
		
		return (ABeanQuantityKind<? extends AQuantityKind>) new BeanQuantityKindFactory().getInstanceFor(unit.getQuantityKind());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setQuantityKindBean(ABeanQuantityKind quantityKindBean) {
		unit.setQuantityKind(quantityKindBean.getQuantityKind());
	}

	@Override
	public Command setQuantityKindBean(EditingDomain ed, ABeanQuantityKind<? extends AQuantityKind> quantityKindBean) {
		return SetCommand.create(ed, unit, QudvPackage.Literals.AUNIT__QUANTITY_KIND, quantityKindBean.getQuantityKind());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ABeanUnit<?>) {
			ABeanUnit<?> beanUnit = (ABeanUnit<?>) obj;
			return unit.equals(beanUnit.getUnit());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return unit.hashCode();
	}
}
