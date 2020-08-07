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

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;

/**
 * Adapter for a ATypeInstance from/to a UUID
 */
public class TypeInstanceAdapter extends XmlAdapter<String, ATypeInstance> {

	private ResourceSet resourceSet;
	private Map<String, ATypeInstance> objectMap = new HashMap<String, ATypeInstance>();
	
	public TypeInstanceAdapter() {
		
	}
	
	public TypeInstanceAdapter(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	@Override
	public String marshal(ATypeInstance ti) throws Exception {
		return ti.getUuid().toString();
	}

	@Override
	public ATypeInstance unmarshal(String uuid) throws Exception {
		if (resourceSet == null) {
			throw new NullPointerException("No resource set for unmarshalling set in the adapter");
		}
		
		// Search for the type instance with the same uuid
		EcoreUtil.getAllContents(resourceSet, true).forEachRemaining(object -> {
			if (object instanceof ATypeInstance) {
				ATypeInstance ti = (ATypeInstance) object;
				if (ti.getUuid().toString().equals(uuid)) {
					objectMap.put(uuid, ti);
				}
			}
		});
		
		ATypeInstance aTypeInstance = objectMap.get(uuid);
		
		if (aTypeInstance == null) {
			throw new IllegalArgumentException("ATypeInstance with uuid " + uuid + " not found");
		}
		return aTypeInstance;
	}
	
}
