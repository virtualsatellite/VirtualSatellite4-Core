/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.propertyTester;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.runtime.CoreException;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;

/**
 * This class tests if the OpenStateMachine handler for opening state machine diagrams
 * should be shown in the context menu.
 *
 */

public class OpenStateMachineTester extends PropertyTester {
	
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		CategoryAssignment ca = (CategoryAssignment) receiver;
		BeanCategoryAssignmentFactory beanCaFactory = new BeanCategoryAssignmentFactory();
		try {
			IBeanCategoryAssignment beanBa = beanCaFactory.getInstanceFor(ca);
			return beanBa instanceof StateMachine;
		} catch (CoreException e) {
			// Unknown category assignment
		}
		
		return false;
	}
}
