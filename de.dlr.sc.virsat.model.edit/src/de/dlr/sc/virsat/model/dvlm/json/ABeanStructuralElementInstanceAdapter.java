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

import org.eclipse.emf.ecore.resource.ResourceSet;

import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * Adapter for a referenced ABeanStructuralElementInstance from/to a UUID
 * that uses the IUuidAdapter
 */
public class ABeanStructuralElementInstanceAdapter extends XmlAdapter<String, ABeanStructuralElementInstance> {

	private ResourceSet resourceSet;
	
	public ABeanStructuralElementInstanceAdapter() { };
	
	public ABeanStructuralElementInstanceAdapter(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	@Override
	public String marshal(ABeanStructuralElementInstance v) throws Exception {
		if (v == null) {
			return null;
		} else {
			return v.getUuid();
		}
	}
	
	@Override
	public ABeanStructuralElementInstance unmarshal(String uuid) throws Exception {
		// Get the sei from the uuid
		IUuidAdapter typeInstanceAdapter = new IUuidAdapter(resourceSet);
		StructuralElementInstance sei = (StructuralElementInstance) typeInstanceAdapter.unmarshal(uuid);
		
		return new BeanStructuralElementInstance(sei);
	}

}
