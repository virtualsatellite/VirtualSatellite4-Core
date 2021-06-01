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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.json.BeanPropertyTypeAdapter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

/**
 * Core functionality for a Property Bean and abstract implementation to the interface
 *
 * @param <P_TYPE> The property bean type
 * @param <V_TYPE> The value type of the bean
 */
@ApiModel(discriminator = "propertyType",
	description = "Abstract model class for bean properties."
		+ " Resources that return this will instead return concrete bean properties.",
	subTypes = {
		ABeanValueProperty.class,
		BeanPropertyComposed.class,
		BeanPropertyEnum.class,
		BeanPropertyReference.class,
		BeanPropertyResource.class
	})
public abstract class ABeanProperty<P_TYPE extends APropertyInstance, V_TYPE> extends ABeanObject<P_TYPE> implements IBeanProperty<P_TYPE, V_TYPE> {

	public ABeanProperty() {
		super();
	}
	
	public ABeanProperty(APropertyInstance ti) {
		super(ti);
	}
	
	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(BeanPropertyTypeAdapter.class)
	@ApiModelProperty(
		accessMode = AccessMode.READ_ONLY,
		value = "Enum to idenify a property by it's type")
	public abstract BeanPropertyType getPropertyType();
}
