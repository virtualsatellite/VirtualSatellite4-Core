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
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;

/**
 * Overrides the getText method for array instances
 * @author muel_s8
 *
 */
public class DVLMArrayInstanceItemProvider extends ArrayInstanceItemProvider {

	/**
	 * this class constructor is an instance of the factory and notifier 
	 * @param adapterFactory 
	 */
	public DVLMArrayInstanceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public String getText(Object object) {
		
		if (object instanceof ArrayInstance) {
			ArrayInstance arrayInstance = (ArrayInstance) object;
			StringBuilder arrayString = new StringBuilder();
			
			for (int i = 0; i < arrayInstance.getArrayInstances().size(); ++i) {
				APropertyInstance pi = arrayInstance.getArrayInstances().get(i);
				IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory.adapt(pi, IItemLabelProvider.class);
				arrayString.append(itemLabelProvider.getText(pi));
				
				if (i != arrayInstance.getArrayInstances().size() - 1) {
					arrayString.append(", ");
				}
			}
			
			return arrayInstance.getType().getName() + ": {" + arrayString.toString() + "}";
		}
		
		return super.getText(object);
	}
	
	@Override
	public Object getImage(Object object) {
		ArrayInstance ai = (ArrayInstance) object;
		ATypeDefinition td = ai.getType();
		
		if (td instanceof ComposedProperty) {
			td = ((ComposedProperty) td).getType();
		}
		
		ComposeableAdapterFactory composeableAdapterFactory = (DVLMPropertyinstancesItemProviderAdapterFactory) adapterFactory;
		AdapterFactory adapterFactory = composeableAdapterFactory.getRootAdapterFactory();
		
		IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory.adapt(td, IItemLabelProvider.class);
		if (itemLabelProvider != null) {
			return itemLabelProvider.getImage(td);
		} else {
			return super.getImage(object);
		}
	}

}
