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


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;
import de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor;
import io.swagger.annotations.ApiModelProperty;

public class BeanUnitDerived extends ABeanUnit<DerivedUnit> {

	public BeanUnitDerived() { 
		super();
	}
	
	public BeanUnitDerived(DerivedUnit unit) {
		super(unit);
	}
	
	/**
	 * Get all factors
	 * @return List of factors
	 */
	@ApiModelProperty(required = true)
	@XmlElement
	public List<BeanFactorUnit> getFactorBeans() {
		List<BeanFactorUnit> factors = new ArrayList<BeanFactorUnit>();
		
		for (UnitFactor factor : unit.getFactor()) {
			factors.add(new BeanFactorUnit(factor));
		}
		
		return factors;
	}
	
	/**
	 * Set all factors
	 * @param newBeanFactors List of new factors
	 */
	public void setFactorBeans(List<BeanFactorUnit> newBeanFactors) {
		List<UnitFactor> currentFactors = unit.getFactor();

		List<UnitFactor> newFactors = new ArrayList<UnitFactor>();
		for (BeanFactorUnit beanFactor : newBeanFactors) {
			newFactors.add(beanFactor.getFactor());
		}
		
		currentFactors.clear();
		currentFactors.addAll(newFactors);
	}
	
	public void addFactor(BeanFactorUnit beanFactor) {
		unit.getFactor().add(beanFactor.getFactor());
	}
	
	public void removeFactor(BeanFactorUnit beanFactor) {
		unit.getFactor().remove(beanFactor.getFactor());
	}
	
	public Command addFactor(EditingDomain ed, BeanFactorUnit beanFactor) {
		return AddCommand.create(ed, unit, QudvPackage.Literals.DERIVED_UNIT__FACTOR, beanFactor.getFactor());
	}
	
	public Command removeFactor(EditingDomain ed, BeanFactorUnit beanFactor) {
		return RemoveCommand.create(ed, unit, QudvPackage.Literals.DERIVED_UNIT__FACTOR, beanFactor.getFactor());
	}
}
