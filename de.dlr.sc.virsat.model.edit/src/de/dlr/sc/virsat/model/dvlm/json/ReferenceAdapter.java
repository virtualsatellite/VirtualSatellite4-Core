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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.ResourceSet;

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.concept.types.factory.BeanPropertyFactory;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;

@SuppressWarnings("rawtypes")
public class ReferenceAdapter extends XmlAdapter<String, ABeanObject> {

	private ResourceSet resourceSet;
	
	public ReferenceAdapter() { };
	
	public ReferenceAdapter(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	@Override
	public ABeanObject unmarshal(String uuid) throws Exception {
		TypeInstanceAdapter typeInstanceAdapter = new TypeInstanceAdapter(resourceSet);
		ATypeInstance object = typeInstanceAdapter.unmarshal(uuid);
		
		ABeanObject referencedBean = null;
		// Return the correct ca or property instance bean
		if (object instanceof CategoryAssignment) {
			BeanCategoryAssignmentFactory beanCaFactory = new BeanCategoryAssignmentFactory();
			try {
				referencedBean = (ABeanObject) beanCaFactory.getInstanceFor((CategoryAssignment) object);
			} catch (CoreException e) {
				throw new RuntimeException(e);
			}
		} else if (object instanceof APropertyInstance) {
			BeanPropertyFactory beanPropFactory = new BeanPropertyFactory();
			referencedBean = (ABeanObject) beanPropFactory.getInstanceFor((APropertyInstance) object);
		}
		
		return referencedBean;
	}

	@Override
	public String marshal(ABeanObject v) throws Exception {
		return v.getUuid();
	}

}
