/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider;

import org.eclipse.emf.common.notify.AdapterFactory;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.general.IName;

/**
 * Overrides the getText method for reference property instances
 * @author muel_s8
 *
 */
public class DVLMRefererencePropertyInstanceItemProvider extends ReferencePropertyInstanceItemProvider {
	
	/**
	 * this class constructor is an instance of the factory and notifier 
	 * @param adapterFactory 
	 */
	public DVLMRefererencePropertyInstanceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public String getText(Object object) {
		
		if (object instanceof ReferencePropertyInstance) {
			ReferencePropertyInstance rpi = (ReferencePropertyInstance) object;
			ATypeInstance reference = rpi.getReference();
			String prefix = rpi.getType().getName() + " -> ";
			if (reference instanceof IName) {
				return prefix + ((IName) reference).getName();
			} else if (reference != null) {
				return prefix + ActiveConceptHelper.getContainerQualifedNameForInstance(reference);
			} else {
				return prefix;
			}
		}
		
		return super.getText(object);
	}

}
