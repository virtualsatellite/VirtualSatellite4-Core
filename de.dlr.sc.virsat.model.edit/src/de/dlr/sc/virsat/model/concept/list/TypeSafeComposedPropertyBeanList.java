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

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;

public class TypeSafeComposedPropertyBeanList<COMPOSED_BEAN extends IBeanCategoryAssignment> extends TypeSafeArrayInstanceList<BeanPropertyComposed<COMPOSED_BEAN>> {

	public TypeSafeComposedPropertyBeanList() {
		super();
		
		beanClazz = BeanPropertyComposed.class;
	}
}
