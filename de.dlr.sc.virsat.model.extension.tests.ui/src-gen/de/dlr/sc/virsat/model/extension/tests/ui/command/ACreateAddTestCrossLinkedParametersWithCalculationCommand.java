/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.ui.command;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import org.eclipse.emf.ecore.EObject;


import de.dlr.sc.virsat.model.extension.tests.model.TestCrossLinkedParametersWithCalculation;

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * 
 * 
 */	
public abstract class ACreateAddTestCrossLinkedParametersWithCalculationCommand {
	public Command create(EditingDomain editingDomain, EObject owner, Concept activeConcept) {
		TestCrossLinkedParametersWithCalculation conceptObject = new TestCrossLinkedParametersWithCalculation(activeConcept);
		CategoryAssignment ca = conceptObject.getTypeInstance();
		return AddCommand.create(editingDomain, owner, CategoriesPackage.Literals.ICATEGORY_ASSIGNMENT_CONTAINER__CATEGORY_ASSIGNMENTS, ca);
	}
}
