/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.property;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;

public class BeanPropertyComposed<BEAN_TYPE extends IBeanCategoryAssignment> extends ABeanObject<ComposedPropertyInstance> implements IBeanProperty<ComposedPropertyInstance, BEAN_TYPE> {

	public BeanPropertyComposed() { }
	
	public BeanPropertyComposed(ComposedPropertyInstance cpi) {
		setTypeInstance(cpi);
	}
	
	@Override
	public void setValue(BEAN_TYPE value) {
		// We can't set the composed ca on the bean level
	}

	@Override
	public Command setValue(EditingDomain ed, BEAN_TYPE value) {
		// We can't set the composed ca on the bean level
		return new CompoundCommand();
	}

	@SuppressWarnings("unchecked")
	@Override
	public BEAN_TYPE getValue() {
		BEAN_TYPE composedBean = null;
		
		if (isSet()) {
			CategoryAssignment ref = ti.getTypeInstance();
			
			BeanCategoryAssignmentFactory beanCaFactory = new BeanCategoryAssignmentFactory();
			try {
				composedBean = (BEAN_TYPE) beanCaFactory.getInstanceFor((CategoryAssignment) ref);
			} catch (CoreException e) {
				throw new RuntimeException(e);
			}
		}
		
		return composedBean;
	}

	@Override
	public boolean isSet() {
		return ti.getTypeInstance() != null;
	}

	@Override
	public void unset() {
		// We can't set the composed ca on the bean level
	}

}
