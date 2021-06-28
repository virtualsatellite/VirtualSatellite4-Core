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

import de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;

public class ABeanConversionBasedUnit<U_TYPE extends AConversionBasedUnit> extends ABeanUnit<U_TYPE> implements IBeanConversionBasedUnit {

	public ABeanConversionBasedUnit() {
		super();
	}
	
	public ABeanConversionBasedUnit(U_TYPE unit) {
		super(unit);
	}
	
	@Override
	public Boolean getIsInvertible() {
		return unit.isIsInvertible();
	}
	
	@Override
	public void setIsInvertible(Boolean isInverible) {
		unit.setIsInvertible(isInverible);
	}

	@Override
	public Command setIsInvertible(EditingDomain ed, Boolean isInverible) {
		return SetCommand.create(ed, unit, QudvPackage.Literals.ACONVERSION_BASED_UNIT__IS_INVERTIBLE, isInverible);
	}
}
