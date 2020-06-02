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
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;

public class BeanPropertyReference<BEAN_TYPE extends IBeanObject<? extends ATypeInstance>> extends ABeanObject<ReferencePropertyInstance> implements IBeanProperty<ReferencePropertyInstance, BEAN_TYPE> {


	public BeanPropertyReference() {
	}

	public BeanPropertyReference(ReferencePropertyInstance rpi) {
		setTypeInstance(rpi);
	}
	
	@Override
	public void setValue(BEAN_TYPE value) {
		ti.setReference(value.getTypeInstance());
	}

	@Override
	public Command setValue(EditingDomain ed, BEAN_TYPE value) {
		return SetCommand.create(ed, ti, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, value.getTypeInstance());
	}

	/*
	 * return property bean
	 * or 
	 * return ca bean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BEAN_TYPE getValue() {
		BEAN_TYPE referencedBean = null;
		
		if (isSet()) {
			ATypeInstance ref = ti.getReference();
			
			if (ref instanceof CategoryAssignment) {
				BeanCategoryAssignmentFactory beanFactory = new BeanCategoryAssignmentFactory();
				try {
					// TODO: is this type cast save? If we could get the super class here, then not
					referencedBean = (BEAN_TYPE) beanFactory.getInstanceFor((CategoryAssignment) ref);
				} catch (CoreException e) {
					throw new RuntimeException(e);
				}
			} else if (ref instanceof APropertyInstance) {
				// TODO: Property Factory
			} 
		}
		
		return referencedBean;
	}

	@Override
	public boolean isSet() {
		return ti.getReference() != null;
	}

	@Override
	public void unset() {
		ti.setReference(null);
	}

	

}
