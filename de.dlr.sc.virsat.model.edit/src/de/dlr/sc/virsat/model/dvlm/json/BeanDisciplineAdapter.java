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

import de.dlr.sc.virsat.model.concept.types.roles.BeanDiscipline;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;

/**
 * Adapter for a referenced ABeanStructuralElementInstance from/to a UUID
 * that uses the IUuidAdapter
 */
public class BeanDisciplineAdapter extends XmlAdapter<String, BeanDiscipline> {

	private ResourceSet resourceSet;
	
	public BeanDisciplineAdapter() { };
	
	public BeanDisciplineAdapter(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	@Override
	public String marshal(BeanDiscipline v) throws Exception {
		if (v == null) {
			return null;
		} else {
			return v.getUuid();
		}
	}
	
	@Override
	public BeanDiscipline unmarshal(String uuid) throws Exception {
		// Get the discipline from the uuid
		IUuidAdapter uuidInstanceAdapter = new IUuidAdapter(resourceSet);
		Discipline discipline = (Discipline) uuidInstanceAdapter.unmarshal(uuid);
		
		return new BeanDiscipline(discipline);
	}

}
