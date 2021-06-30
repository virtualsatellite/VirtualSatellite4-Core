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

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.json.IUuidAdapter;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * General bean for a quantity kind of the type U_TYPE
 * 
 * @param <QK_TYPE> type of the wrapped quantity kind
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
@ApiModel(
	description = "Abstract model class for bean quantity kinds."
		+ " Resources that return this will instead return concrete bean quantity kinds.",
	subTypes = {
		BeanQuantityKindSimple.class,
		BeanQuantityKindDerived.class
})
public abstract class ABeanQuantityKind<QK_TYPE extends AQuantityKind> implements IBeanQuantityKind<QK_TYPE> {

	protected QK_TYPE quantityKind;
	
	public ABeanQuantityKind() { }
	
	@SuppressWarnings("unchecked")
	public ABeanQuantityKind(AQuantityKind quantityKind) {
		this.quantityKind = (QK_TYPE) quantityKind;
	}
	
	@ApiModelProperty(name = "uuid", required = true,
			value = "Unique identifier for a bean",
			example = "b168b0df-84b6-4b7f-bede-69298b215f40")
	@XmlElement(name = "uuid")
	@XmlJavaTypeAdapter(IUuidAdapter.class)
	@Override
	public AQuantityKind getAQuantityKind() {
		return quantityKind;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setAQuantityKind(AQuantityKind quantityKind) {
		this.quantityKind = (QK_TYPE) quantityKind;
	}

	@ApiModelProperty(hidden = true)
	@Override
	public QK_TYPE getQuantityKind() {
		return quantityKind;
	}

	@Override
	public void setQuantityKind(QK_TYPE quantityKind) {
		this.quantityKind = quantityKind;
	}

	@ApiModelProperty(hidden = true)
	@Override
	public String getUuid() {
		return quantityKind.getUuid().toString();
	}

	@ApiModelProperty(required = true)
	@XmlElement(nillable = true)
	@Override
	public String getName() {
		return quantityKind.getName();
	}

	@Override
	public void setName(String name) {
		quantityKind.setName(name);
	}

	@Override
	public Command setName(EditingDomain ed, String name) {
		return SetCommand.create(ed, quantityKind, GeneralPackage.Literals.INAME__NAME, name);
	}

	@ApiModelProperty(required = true)
	@XmlElement(nillable = true)
	@Override
	public String getSymbol() {
		return quantityKind.getSymbol();
	}

	@Override
	public void setSymbol(String symbol) {
		quantityKind.setSymbol(symbol);
	}

	@Override
	public Command setSymbol(EditingDomain ed, String symbol) {
		return SetCommand.create(ed, quantityKind, QudvPackage.Literals.AQUANTITY_KIND__SYMBOL, symbol);
	}

}
