/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.json;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyType;

/**
 * Adapter for the BeanPropertyType enum
 * Marshalles the enums to a lower case representation
 * Unmarshalling is case insensitive
 */
public class BeanPropertyTypeAdapter extends XmlAdapter<String, BeanPropertyType> {

	@Override
	public BeanPropertyType unmarshal(String typeName) throws Exception {
		if (typeName == null) {
			return null;
		} else {
			return BeanPropertyType.valueOf(typeName.toUpperCase());
		}
	}

	@Override
	public String marshal(BeanPropertyType bpt) throws Exception {
		if (bpt == null) {
			return null;
		} else {
			return bpt.toString().toLowerCase();
		}
	}

}
