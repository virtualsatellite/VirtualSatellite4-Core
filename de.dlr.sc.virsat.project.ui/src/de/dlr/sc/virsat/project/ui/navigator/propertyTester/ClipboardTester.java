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

import de.dlr.sc.virsat.project.editingDomain.commands.VirSatClipboardCommandHelper;

/**
 * PropertyTester for core Expressions to check if the ClipBoard contains objects of certain type 
 */
public class ClipboardTester extends PropertyTester {
	
	public static final String CUT = "cut";
	public static final String COPY = "copy";
	public static final String PASTE = "paste";
	
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		
		if (property.equals(PASTE)) {
			return VirSatClipboardCommandHelper.isValidTarget(receiver);
		} else if (property.equals(COPY)) {
			return VirSatClipboardCommandHelper.isValidSource(receiver);
		} else if (property.equals(CUT)) {
			return VirSatClipboardCommandHelper.isValidSource(receiver);
		}
		
		return false;
	}

}
