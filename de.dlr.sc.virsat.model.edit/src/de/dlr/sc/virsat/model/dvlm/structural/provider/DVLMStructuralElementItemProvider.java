/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural.provider;

import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.util.DVLMItemNaming;

import org.eclipse.emf.common.notify.AdapterFactory;

/**
 * This item provider makes sure to override the getText method 
 * to return it with the abbreviation of the StructuralElementInstance
 * @author lobe_el
 *
 */
public class DVLMStructuralElementItemProvider extends StructuralElementItemProvider {
	
	/**
	 * this class constructor is an instance of the factory and notifier 
	 * @param adapterFactory 
	 */
	public DVLMStructuralElementItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public String getText(Object object) {
		String label = ((StructuralElement) object).getName();
		String abbr = DVLMItemNaming.getAbbreviation((IQualifiedName) object, "");
		String output = getString("_UI_StructuralElement_type");
		if (label != null && label.length() > 0) {
			output = output  + " " + label;
			if (abbr != null && abbr.length() > 0) {
				output = output  + " (" + abbr + ")";
			}
		}
		return output;  
	}

}
