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

/**
 * This XmlAdapter can be used to marshal any generic Java Object. 
 * This will result in a "type" attribute (XML) or key value pair (JSON)
 * added to the marshalled object, which is needed for unmarshalling.
 * This adapter is needed for interfaces or generics so that JAXB knows
 * the concrete class to unmarshall into (Because this information is not
 * provided by the annotated method otherwise).
 */
public class AnyTypeAdapter extends XmlAdapter<Object, Object> {

	@Override
	public Object unmarshal(Object v) throws Exception {
		return v;
	}

	@Override
	public Object marshal(Object v) throws Exception {
		return v;
	}
}
