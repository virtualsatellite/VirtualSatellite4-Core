/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.propertyTester;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.dvlm.roles.RightsHelper;

/**
 * Checks if the user has write permission to an eObject.
 * @author muel_s8
 *
 */

public class WritePermissionTester extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (receiver instanceof EObject) {
			EObject eObject = (EObject) receiver;
			return RightsHelper.hasSystemUserWritePermission(eObject);
		} else {
			return false;
		}
	}

}
