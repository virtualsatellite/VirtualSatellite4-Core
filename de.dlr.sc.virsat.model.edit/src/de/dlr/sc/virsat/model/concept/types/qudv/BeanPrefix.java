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

import de.dlr.sc.virsat.model.concept.types.IBeanName;
import de.dlr.sc.virsat.model.concept.types.IBeanUuid;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.json.IUuidAdapter;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlAccessorType(XmlAccessType.NONE)
//Ensure that the prefix (by uuid) gets unmarshalled first
@XmlType(propOrder = {"prefix", "factor", "name", "symbol"})
@ApiModel(description = "Model class for bean prefix.")
public class BeanPrefix implements IBeanUuid, IBeanName {

	private Prefix prefix;
	
	public BeanPrefix() { }
	
	public BeanPrefix(Prefix prefix) {
		this.prefix = prefix;
	}
	
	@ApiModelProperty(hidden = true)
	@Override
	public String getUuid() {
		return prefix.getUuid().toString();
	}
	
	@ApiModelProperty(required = true)
	@XmlElement(nillable = true)
	@Override
	public String getName() {
		return prefix.getName();
	}
	
	@Override
	public void setName(String name) {
		prefix.setName(name);
	}
	
	@Override
	public Command setName(EditingDomain ed, String name) {
		return SetCommand.create(ed, prefix, GeneralPackage.Literals.INAME__NAME, name);
	}
	
	@ApiModelProperty(name = "uuid", required = true,
			value = "Unique identifier for a bean",
			example = "b168b0df-84b6-4b7f-bede-69298b215f40")
	@XmlElement(name = "uuid")
	@XmlJavaTypeAdapter(IUuidAdapter.class)
	public Prefix getPrefix() {
		return prefix;
	}
	
	public void setPrefix(Prefix prefix) {
		this.prefix = prefix;
	}
	
	@ApiModelProperty(required = true)
	@XmlElement(nillable = true)
	public String getSymbol() {
		return prefix.getSymbol();
	}

	public void setSymbol(String symbol) {
		prefix.setSymbol(symbol);
	}
	
	public Command setSymbol(EditingDomain ed, String symbol) {
		return SetCommand.create(ed, prefix, QudvPackage.Literals.PREFIX__SYMBOL, symbol);
	}
	
	@ApiModelProperty(required = true)
	@XmlElement(nillable = true)
	double getFactor() {
		return prefix.getFactor();
	}
	
	void setFactor(double factor) {
		prefix.setFactor(factor);
	}
	
	public Command setFactor(EditingDomain ed, double factor) {
		return SetCommand.create(ed, prefix, QudvPackage.Literals.PREFIX__FACTOR, factor);
	}
}
