/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.provider;

import org.eclipse.emf.common.notify.AdapterFactory;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.util.DVLMItemNaming;

/**
 * This item provider makes sure to override the getText method 
 * to return it with the abbreviation of the Category Assignment
 * @author lobe_el
 *
 */
public class DVLMCategoryAssignmentItemProvider extends CategoryAssignmentItemProvider {

	/**
	 * this class constructor is an instance of the factory and notifier 
	 * @param adapterFactory 
	 */
	public DVLMCategoryAssignmentItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public String getText(Object object) {
		CategoryAssignment ca = (CategoryAssignment) object;
		String label = ca.getName();
		String abbr = DVLMItemNaming.getAbbreviation((IQualifiedName) ca.getType(), getString("_UI_CategoryAssignment_type"));
		return ((label == null || label.length() == 0) ? (abbr + ":") : (abbr + ": " + label));
	}
}
