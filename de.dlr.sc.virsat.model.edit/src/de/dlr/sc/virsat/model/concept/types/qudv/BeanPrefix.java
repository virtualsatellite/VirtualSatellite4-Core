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

import de.dlr.sc.virsat.model.concept.types.IBeanName;
import de.dlr.sc.virsat.model.concept.types.IBeanUuid;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.json.IUuidAdapter;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;
import io.swagger.v3.oas.annotations.media.Schema;



@XmlAccessorType(XmlAccessType.NONE)
//Ensure that the prefix (by uuid) gets unmarshalled first
@XmlType(propOrder = {"prefix", "factor", "name", "symbol"})
@Schema(description = "Model class for bean prefix.")
public class BeanPrefix implements IBeanUuid, IBeanName {

	private Prefix prefix;
	
	public BeanPrefix() { }
	
	public BeanPrefix(Prefix prefix) {
		this.prefix = prefix;
	}
	
	@Schema(hidden = true)
	@Override
	public String getUuid() {
		return prefix.getUuid().toString();
	}
	
	@Schema(required = true)
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
	
	@Schema(name = "uuid", required = true,
			description = "Unique identifier for a bean",
			example = "b168b0df-84b6-4b7f-bede-69298b215f40")
	@XmlElement(name = "uuid")
	@XmlJavaTypeAdapter(IUuidAdapter.class)
	public Prefix getPrefix() {
		return prefix;
	}
	
	public void setPrefix(Prefix prefix) {
		this.prefix = prefix;
	}
	
	@Schema(required = true)
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
	
	@Schema(required = true)
	@XmlElement
	public double getFactor() {
		return prefix.getFactor();
	}
	
	public void setFactor(double factor) {
		prefix.setFactor(factor);
	}
	
	public Command setFactor(EditingDomain ed, double factor) {
		return SetCommand.create(ed, prefix, QudvPackage.Literals.PREFIX__FACTOR, factor);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BeanPrefix) {
			BeanPrefix beanPrefix = (BeanPrefix) obj;
			return prefix.equals(beanPrefix.getPrefix());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return prefix.hashCode();
	}
}
