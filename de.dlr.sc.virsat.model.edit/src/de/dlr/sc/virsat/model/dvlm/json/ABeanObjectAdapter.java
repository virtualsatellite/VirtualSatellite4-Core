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

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.concept.types.factory.BeanTypeInstanceFactory;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;

@SuppressWarnings("rawtypes")
/**
 * Adapter for a referenced ABeanObject from/to a UUID
 * that uses the IUuidAdapter
 */
public class ABeanObjectAdapter extends XmlAdapter<String, ABeanObject> {

	private ResourceSet resourceSet;
	
	public ABeanObjectAdapter() { };
	
	public ABeanObjectAdapter(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	@Override
	public String marshal(ABeanObject v) throws Exception {
		if (v == null) {
			return null;
		} else {
			return v.getUuid();
		}
	}
	
	@Override
	public ABeanObject unmarshal(String uuid) throws Exception {
		// Get the type instance from the uuid
		IUuidAdapter typeInstanceAdapter = new IUuidAdapter(resourceSet);
		ATypeInstance object = (ATypeInstance) typeInstanceAdapter.unmarshal(uuid);
		
		return (ABeanObject) new BeanTypeInstanceFactory().getInstanceFor(object);
	}

}
