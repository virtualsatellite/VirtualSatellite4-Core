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
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;

public class ABeanQuantityKind<QK_TYPE extends AQuantityKind> implements IBeanQuantityKind {

	protected QK_TYPE quantityKind;
	
	public ABeanQuantityKind() { }
	
	@SuppressWarnings("unchecked")
	public ABeanQuantityKind(AUnit quantityKind) {
		this.quantityKind = (QK_TYPE) quantityKind;
	}
	
	@Override
	public AQuantityKind getQuantityKind() {
		return quantityKind;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setQuantityKind(AQuantityKind quantityKind) {
		this.quantityKind = (QK_TYPE) quantityKind;

	}

	@Override
	public String getUuid() {
		return quantityKind.getUuid().toString();
	}

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

	@Override
	public String getSymbol() {
		return quantityKind.getSymbol();
	}

	@Override
	public void setSymbol(String symbol) {
		quantityKind.getSymbol();
	}

}
