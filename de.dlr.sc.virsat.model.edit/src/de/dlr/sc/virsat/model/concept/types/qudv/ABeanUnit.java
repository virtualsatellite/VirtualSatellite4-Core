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

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;

public class ABeanUnit<U_TYPE extends AUnit> implements IBeanUnit<U_TYPE> {

	protected U_TYPE unit;
	
	public ABeanUnit() { }
	
	public ABeanUnit(U_TYPE unit) {
		this.unit = unit;
	}
	
	@Override
	public AUnit getAUnit() {
		return unit;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setAUnit(AUnit unit) {
		this.unit = (U_TYPE) unit;
	}
	
	@Override
	public U_TYPE getUnit() {
		return unit;
	}
	
	@Override
	public void setUnit(U_TYPE unit) {
		this.unit = unit;
	}
	
	@Override
	public String getUuid() {
		return unit.getUuid().toString();
	}

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

}
