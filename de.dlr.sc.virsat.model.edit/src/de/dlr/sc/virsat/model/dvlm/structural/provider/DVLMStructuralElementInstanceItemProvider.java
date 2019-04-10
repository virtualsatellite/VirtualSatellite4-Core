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

import org.eclipse.emf.common.notify.AdapterFactory;

import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.util.DVLMItemNaming;

/**
 * This item provider makes sure to override the getText method 
 * to return it with the abbreviation of the StructuralElementInstance
 * @author lobe_el
 *
 */
public class DVLMStructuralElementInstanceItemProvider extends StructuralElementInstanceItemProvider {

	/**
	 * this class constructor is an instance of the factory and notifier 
	 * @param adapterFactory 
	 */
	public DVLMStructuralElementInstanceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public String getText(Object object) {
		StructuralElementInstance sei = (StructuralElementInstance) object;
		String label = sei.getName();
		String abbr = DVLMItemNaming.getAbbreviation((IQualifiedName) sei.getType(), getString("_UI_StructuralElementInstance_type"));
		return ((label == null || label.length() == 0) ? (abbr + ":") : (abbr + ": " + label));
	}
	
	// To get rid of the small green icon on the image in left top corner 
	// Was added by the ItemProviderAdapter when the object was "controlled"
	@Override
	protected Object overlayImage(Object object, Object image) {
		return image;
	}
}
