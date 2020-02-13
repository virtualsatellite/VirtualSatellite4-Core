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

import org.eclipse.emf.common.notify.Adapter;

/**
 * Extension to the generic property instances item provider adapter factory
 * @author muel_s8
 *
 */

public class DVLMPropertyinstancesItemProviderAdapterFactory extends PropertyinstancesItemProviderAdapterFactory {
	/**
	 * Constructor for the Factory
	 */
	public DVLMPropertyinstancesItemProviderAdapterFactory() {
		super();
	}
	
	@Override
	public Adapter createEnumUnitPropertyInstanceAdapter() {
		if (enumUnitPropertyInstanceItemProvider == null) {
			enumUnitPropertyInstanceItemProvider = new DVLMEnumUnitPropertyInstanceItemProvider(this);
		}
		return enumUnitPropertyInstanceItemProvider;
	}
	
	@Override
	public Adapter createValuePropertyInstanceAdapter() {
		if (valuePropertyInstanceItemProvider == null) {
			valuePropertyInstanceItemProvider = new DVLMValuePropertyInstanceItemProvider(this);
		}

		return valuePropertyInstanceItemProvider;
	}
	
	@Override
	public Adapter createResourcePropertyInstanceAdapter() {
		if (resourcePropertyInstanceItemProvider == null) {
			resourcePropertyInstanceItemProvider = new DVLMResourcePropertyInstanceItemProvider(this);
		}

		return resourcePropertyInstanceItemProvider;
	}
	
	@Override
	public Adapter createReferencePropertyInstanceAdapter() {
		if (referencePropertyInstanceItemProvider == null) {
			referencePropertyInstanceItemProvider = new DVLMRefererencePropertyInstanceItemProvider(this);
		}

		return referencePropertyInstanceItemProvider;
	}
	
	@Override
	public Adapter createEReferencePropertyInstanceAdapter() {
		if (eReferencePropertyInstanceItemProvider == null) {
			eReferencePropertyInstanceItemProvider = new DVLMERefererencePropertyInstanceItemProvider(this);
		}

		return eReferencePropertyInstanceItemProvider;
	}
	
	@Override
	public Adapter createUnitValuePropertyInstanceAdapter() {
		if (unitValuePropertyInstanceItemProvider == null) {
			unitValuePropertyInstanceItemProvider = new DVLMUnitValuePropertyInstanceItemProvider(this);
		}

		return unitValuePropertyInstanceItemProvider;
	}
	
	@Override
	public Adapter createArrayInstanceAdapter() {
		if (arrayInstanceItemProvider == null) {
			arrayInstanceItemProvider = new DVLMArrayInstanceItemProvider(this);
		}

		return arrayInstanceItemProvider;
	}
	
	@Override
	public Adapter createComposedPropertyInstanceAdapter() {
		if (composedPropertyInstanceItemProvider == null) {
			composedPropertyInstanceItemProvider = new DVLMComposedPropertyInstanceItemProvider(this);
		}

		return composedPropertyInstanceItemProvider;
	}
}
