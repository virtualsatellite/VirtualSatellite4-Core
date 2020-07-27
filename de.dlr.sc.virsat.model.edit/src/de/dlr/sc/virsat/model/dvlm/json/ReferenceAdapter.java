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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.concept.types.factory.BeanPropertyFactory;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;

@SuppressWarnings("rawtypes")
public class ReferenceAdapter extends XmlAdapter<String, ABeanObject> {

	private ResourceSet resourceSet;
	private Map<String, ABeanObject> objectMap = new HashMap<String, ABeanObject>();
	
	public ReferenceAdapter() { };
	
	public ReferenceAdapter(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	@Override
	public ABeanObject unmarshal(String uuid) throws Exception {
		// TODO: get the same bean instad of creating a new one?
		// Just copy pasted logic from bean independence solver to get something similar going...
		EcoreUtil.getAllContents(resourceSet, true).forEachRemaining(object -> {
			if (object instanceof ATypeInstance) {
				ATypeInstance ref = (ATypeInstance) object;
				if (ref.getUuid().toString().equals(uuid)) {
					ABeanObject referencedBean = null;
					// Return the correct ca or property instance bean
					// TODO: copy pasted from the bean
					if (object instanceof CategoryAssignment) {
						BeanCategoryAssignmentFactory beanCaFactory = new BeanCategoryAssignmentFactory();
						try {
							referencedBean = (ABeanObject) beanCaFactory.getInstanceFor((CategoryAssignment) object);
						} catch (CoreException e) {
							throw new RuntimeException(e);
						}
					} else if (object instanceof APropertyInstance && !(object instanceof ReferencePropertyInstance)) {
						BeanPropertyFactory beanPropFactory = new BeanPropertyFactory();
						referencedBean = (ABeanObject) beanPropFactory.getInstanceFor((APropertyInstance) object);
					}
					if (referencedBean != null) {
						objectMap.put(uuid, referencedBean);
					}
				}
			}
		});
		
		ABeanObject aBeanObject = objectMap.get(uuid);
		
		if (aBeanObject == null) {
			throw new IllegalArgumentException("ABeanObject with uuid " + uuid + " not found");
		}
		return aBeanObject;
	}

	@Override
	public String marshal(ABeanObject v) throws Exception {
		return v.getUuid();
	}

}
