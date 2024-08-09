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

import jakarta.xml.bind.annotation.XmlElement;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;
import io.swagger.v3.oas.annotations.media.Schema;


public class BeanUnitLinearConversion extends ABeanConversionBasedUnit<LinearConversionUnit> {

	public BeanUnitLinearConversion() { 
		super();
	}
	
	public BeanUnitLinearConversion(LinearConversionUnit unit) {
		super(unit);
	}
	
	@Schema(required = true)
	@XmlElement
	public double getFactor() {
		return unit.getFactor();
	}
	
	public void setFactor(double factor) {
		unit.setFactor(factor);
	}
	
	public Command setFactor(EditingDomain ed, double factor) {
		return SetCommand.create(ed, unit, QudvPackage.Literals.LINEAR_CONVERSION_UNIT__FACTOR, factor);
	}
}
