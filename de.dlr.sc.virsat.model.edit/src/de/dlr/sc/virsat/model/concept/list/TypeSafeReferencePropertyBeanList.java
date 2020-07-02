/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.list;

import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;

public class TypeSafeReferencePropertyBeanList<REFERENCED_BEAN extends IBeanObject<? extends ATypeInstance>> extends TypeSafeArrayInstanceList<BeanPropertyReference<REFERENCED_BEAN>> {

	public TypeSafeReferencePropertyBeanList() {
		super();
		
		beanClazz = BeanPropertyReference.class;
	}

}
