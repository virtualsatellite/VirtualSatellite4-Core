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

import de.dlr.sc.virsat.model.concept.types.factory.BeanUnitFactory;
import de.dlr.sc.virsat.model.concept.types.qudv.ABeanUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;

@SuppressWarnings("rawtypes")
/**
 * Adapter for a referenced ABeanUnit from/to a UUID
 * that uses the IUuidAdapter
 */
public class ABeanUnitAdapter extends XmlAdapter<String, ABeanUnit> {

	private ResourceSet resourceSet;
	
	public ABeanUnitAdapter() { };
	
	public ABeanUnitAdapter(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	@Override
	public String marshal(ABeanUnit v) throws Exception {
		if (v == null) {
			return null;
		} else {
			return v.getUuid();
		}
	}
	
	@Override
	public ABeanUnit unmarshal(String uuid) throws Exception {
		// Get the type instance from the uuid
		IUuidAdapter uuidAdapter = new IUuidAdapter(resourceSet);
		AUnit object = (AUnit) uuidAdapter.unmarshal(uuid);
		
		return (ABeanUnit) new BeanUnitFactory().getInstanceFor(object);
	}

}
