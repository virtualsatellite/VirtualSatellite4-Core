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

public class ABeanUnit<U_TYPE extends AUnit> implements IBeanUnit {

	protected U_TYPE unit;
	
	public ABeanUnit() { }
	
	@SuppressWarnings("unchecked")
	public ABeanUnit(AUnit unit) {
		this.unit = (U_TYPE) unit;
	}
	
	@Override
	public AUnit getUnit() {
		return unit;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void setUnit(AUnit unit) {
		this.unit = (U_TYPE) unit;
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
		unit.getSymbol();
	}

}
