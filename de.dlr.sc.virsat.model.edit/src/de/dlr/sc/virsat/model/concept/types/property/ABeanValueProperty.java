/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.property;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import io.swagger.annotations.ApiModel;

/**
 * Abstract implementation to the interface dealing with Attributes without QUDV unit
 * 
 * @param <P_TYPE> The property bean type
 * @param <V_TYPE> Value type of the Bean
 *
 */
@ApiModel(
	description = "Abstract model class for bean value properties.",
	subTypes = {
		ABeanUnitValueProperty.class,
		BeanPropertyBoolean.class,
		BeanPropertyString.class
	})
public abstract class ABeanValueProperty<P_TYPE extends ValuePropertyInstance, V_TYPE> extends ABeanProperty<P_TYPE, V_TYPE> {
	
	public ABeanValueProperty() {
		super();
	}
	
	public ABeanValueProperty(P_TYPE vpi) {
		super(vpi);
	}
	
	@Override
	public boolean isSet() {
		return ti.getValue() != null;
	}
	
	@Override
	public void unset() {
		ti.setValue(null);
	}

}