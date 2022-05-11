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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.command.SetValuePropertyInstanceCommand;

/**
 * Overrides the getText method for unit value property instances
 * @author muel_s8
 *
 */
public class DVLMUnitValuePropertyInstanceItemProvider extends UnitValuePropertyInstanceItemProvider {

	/**
	 * this class constructor is an instance of the factory and notifier 
	 * @param adapterFactory  to be used for initialization
	 */
	public DVLMUnitValuePropertyInstanceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public String getText(Object object) {
		
		if (object instanceof UnitValuePropertyInstance) {
			UnitValuePropertyInstance uvpi = (UnitValuePropertyInstance) object;
			String value = uvpi.getValue() == null ? "" : uvpi.getValue();
			String unit = uvpi.getUnit() == null ? "" : uvpi.getUnit().getSymbol();
			return uvpi.getType().getName() + ": " + value + unit;
		}
		
		return super.getText(object);
	}

	@Override
	protected Command createSetCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value) {
		return new SetValuePropertyInstanceCommand(domain, owner, feature, value);
	}
}
