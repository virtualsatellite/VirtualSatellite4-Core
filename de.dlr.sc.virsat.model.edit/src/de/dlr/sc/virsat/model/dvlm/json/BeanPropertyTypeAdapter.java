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

public class BeanPropertyTypeAdapter extends XmlAdapter<String, BeanPropertyType> {

	@Override
	public BeanPropertyType unmarshal(String s) throws Exception {
		if (s == null) {
			return null;
		} else {
			return BeanPropertyType.fromString(s);
		}
	}

	@Override
	public String marshal(BeanPropertyType bpt) throws Exception {
		if (bpt == null) {
			return null;
		} else {
			return bpt.getType();
		}
	}

}
