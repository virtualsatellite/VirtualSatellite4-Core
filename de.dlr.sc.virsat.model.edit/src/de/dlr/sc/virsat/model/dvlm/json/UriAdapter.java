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

import org.eclipse.emf.common.util.URI;

/**
 * Adapter for a referenced URI from/to a string
 */
public class UriAdapter extends XmlAdapter<String, URI> {

	@Override
	public URI unmarshal(String v) throws Exception {
		if (v == null) {
			return null;
		} else {
			return URI.createPlatformPluginURI(v, false);
		}
	}

	@Override
	public String marshal(URI v) throws Exception {
		if (v == null) {
			return null;
		} else {
			return v.toPlatformString(false);
		}
	}

}
