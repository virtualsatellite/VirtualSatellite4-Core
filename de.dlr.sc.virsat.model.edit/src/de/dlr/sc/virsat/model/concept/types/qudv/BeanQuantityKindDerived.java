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

import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;
import io.swagger.annotations.ApiModelProperty;

public class BeanQuantityKindDerived extends ABeanQuantityKind<DerivedQuantityKind> {

	public BeanQuantityKindDerived() { 
		super();
	}
	
	public BeanQuantityKindDerived(DerivedQuantityKind quantityKind) {
		super(quantityKind);
	}
	
	/**
	 * Get all factors
	 * @return List of factors
	 */
	@ApiModelProperty(required = true)
	@XmlElement
	public List<BeanFactorQuantityKind> getFactorBeans() {
		List<BeanFactorQuantityKind> factors = new ArrayList<BeanFactorQuantityKind>();
		
		for (QuantityKindFactor factor : quantityKind.getFactor()) {
			factors.add(new BeanFactorQuantityKind(factor));
		}
		
		return factors;
	}
	
	/**
	 * Set all factors
	 * @param newBeanFactors List of new factors
	 */
	public void setFactorBeans(List<BeanFactorQuantityKind> newBeanFactors) {
		List<QuantityKindFactor> currentFactors = quantityKind.getFactor();

		List<QuantityKindFactor> newFactors = new ArrayList<QuantityKindFactor>();
		for (BeanFactorQuantityKind beanFactor : newBeanFactors) {
			newFactors.add(beanFactor.getFactor());
		}
		
		currentFactors.clear();
		currentFactors.addAll(newFactors);
	}
	
	public void addFactor(BeanFactorQuantityKind beanFactor) {
		quantityKind.getFactor().add(beanFactor.getFactor());
	}
	
	public void removeFactor(BeanFactorQuantityKind beanFactor) {
		quantityKind.getFactor().remove(beanFactor.getFactor());
	}
	
	public Command addFactor(EditingDomain ed, BeanFactorQuantityKind beanFactor) {
		return AddCommand.create(ed, quantityKind, QudvPackage.Literals.DERIVED_QUANTITY_KIND__FACTOR, beanFactor.getFactor());
	}
	
	public Command removeFactor(EditingDomain ed, BeanFactorQuantityKind beanFactor) {
		return RemoveCommand.create(ed, quantityKind, QudvPackage.Literals.DERIVED_QUANTITY_KIND__FACTOR, beanFactor.getFactor());
	}
}
