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

import javax.xml.bind.annotation.XmlElement;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;
import io.swagger.annotations.ApiModelProperty;

public class BeanUnitAffineConversion extends ABeanConversionBasedUnit<AffineConversionUnit> {

	public BeanUnitAffineConversion() { 
		super();
	}
	
	public BeanUnitAffineConversion(AffineConversionUnit unit) {
		super(unit);
	}
	
	@ApiModelProperty(required = true)
	@XmlElement
	public double getFactor() {
		return unit.getFactor();
	}
	
	public void setFactor(double factor) {
		unit.setFactor(factor);
	}
	
	public Command setFactor(EditingDomain ed, Double factor) {
		return SetCommand.create(ed, unit, QudvPackage.Literals.AFFINE_CONVERSION_UNIT__FACTOR, factor);
	}
	
	@ApiModelProperty(required = true)
	@XmlElement
	public double getOffset() {
		return unit.getOffset();
	}
	
	public void setOffset(double offset) {
		unit.setOffset(offset);
	}
	
	public Command setOffset(EditingDomain ed, double offset) {
		return SetCommand.create(ed, unit, QudvPackage.Literals.AFFINE_CONVERSION_UNIT__OFFSET, offset);
	}
}
