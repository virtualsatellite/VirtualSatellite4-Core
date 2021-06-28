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

import de.dlr.sc.virsat.model.concept.types.IBeanName;
import de.dlr.sc.virsat.model.concept.types.IBeanUuid;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;

public class BeanPrefix implements IBeanUuid, IBeanName {

	private Prefix prefix;
	
	public BeanPrefix() { }
	
	public BeanPrefix(Prefix prefix) {
		this.prefix = prefix;
	}
	
	@Override
	public String getUuid() {
		return prefix.getUuid().toString();
	}
	
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
	public Prefix getPrefix() {
		return prefix;
	}
	
	public void setPrefix(Prefix prefix) {
		this.prefix = prefix;
	}
	
	public String getSymbol() {
		return prefix.getSymbol();
	}

	public void setSymbol(String symbol) {
		prefix.setSymbol(symbol);
	}
	
	public Command setSymbol(EditingDomain ed, String symbol) {
		return SetCommand.create(ed, prefix, QudvPackage.Literals.PREFIX__SYMBOL, symbol);
	}
	
	Double getFactor() {
		return prefix.getFactor();
	}
	
	void setFactor(Double factor) {
		prefix.setFactor(factor);
	}
	
	public Command setFactor(EditingDomain ed, Double factor) {
		return SetCommand.create(ed, prefix, QudvPackage.Literals.PREFIX__FACTOR, factor);
	}
}
