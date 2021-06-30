/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
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

import de.dlr.sc.virsat.model.concept.types.factory.BeanQuantityKindFactory;
import de.dlr.sc.virsat.model.concept.types.qudv.ABeanQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;

// TODO: test
@SuppressWarnings("rawtypes")
/**
 * Adapter for a referenced AQuantityKind from/to a UUID
 * that uses the IUuidAdapter
 */
public class ABeanQuantityKindAdapter extends XmlAdapter<String, ABeanQuantityKind> {

	private ResourceSet resourceSet;
	
	public ABeanQuantityKindAdapter() { };
	
	public ABeanQuantityKindAdapter(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	@Override
	public String marshal(ABeanQuantityKind v) throws Exception {
		if (v == null) {
			return null;
		} else {
			return v.getUuid();
		}
	}
	
	@Override
	public ABeanQuantityKind unmarshal(String uuid) throws Exception {
		// Get the type instance from the uuid
		IUuidAdapter uuidAdapter = new IUuidAdapter(resourceSet);
		AQuantityKind object = (AQuantityKind) uuidAdapter.unmarshal(uuid);
		
		return (ABeanQuantityKind) new BeanQuantityKindFactory().getInstanceFor(object);
	}

}
