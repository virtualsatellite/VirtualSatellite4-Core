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
import org.eclipse.emf.edit.provider.IItemLabelProvider;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;

/**
 * Overrides the getText method for reference property instances
 * @author muel_s8
 *
 */
public class DVLMComposedPropertyInstanceItemProvider extends ComposedPropertyInstanceItemProvider {
	
	/**
	 * this class constructor is an instance of the factory and notifier 
	 * @param adapterFactory 
	 */
	public DVLMComposedPropertyInstanceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public String getText(Object object) {
		
		if (object instanceof ComposedPropertyInstance) {
			ComposedPropertyInstance cpi = (ComposedPropertyInstance) object;
			CategoryAssignment ca = cpi.getTypeInstance();
			
			StringBuilder res = new StringBuilder();
			res.append(ca.getName());
			
			if (!ca.getPropertyInstances().isEmpty()) {
				res.append(": ");
			}
			
			for (int i = 0; i < ca.getPropertyInstances().size(); ++i) {
				APropertyInstance pi = ca.getPropertyInstances().get(i);
				IItemLabelProvider propertyLabelProvider = (IItemLabelProvider) adapterFactory.adapt(pi, IItemLabelProvider.class);
				res.append(propertyLabelProvider.getText(pi));

				if (i != ca.getPropertyInstances().size() - 1) {
					res.append(", ");
				}
			}
			
			return res.toString();
		}
		
		return super.getText(object);
	}
	
	@Override
	public Object getImage(Object object) {
		ComposedPropertyInstance cpi = (ComposedPropertyInstance) object;
		CategoryAssignment ca = cpi.getTypeInstance();
		
		IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory.adapt(ca, IItemLabelProvider.class);
		if (itemLabelProvider != null) {
			return itemLabelProvider.getImage(ca);
		} else {
			return super.getImage(object);
		}
	}

}
