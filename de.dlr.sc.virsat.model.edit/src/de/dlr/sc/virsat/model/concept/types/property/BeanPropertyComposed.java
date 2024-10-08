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

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.json.ComposedBeanCategoryAssigmentAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;


/**
 * Class to wrap a ComposedPropertyInstance that doesn't support to set values.
 * @param <BEAN_TYPE> the reference type
 */
public class BeanPropertyComposed<BEAN_TYPE extends IBeanCategoryAssignment> extends ABeanProperty<ComposedPropertyInstance, BEAN_TYPE> {

	public BeanPropertyComposed() { }
	
	/**
	 * Constructor to directly set the type instance
	 * @param cpi the type instance to be used
	 */
	public BeanPropertyComposed(ComposedPropertyInstance cpi) {
		super(cpi);
	}
	
	@Override
	public void setValue(BEAN_TYPE value) {
		// Can't set the composed ca on the bean level
	}

	@Override
	public Command setValue(EditingDomain ed, BEAN_TYPE value) {
		// Can't set the composed ca on the bean level
		return UnexecutableCommand.INSTANCE;
	}

	@XmlElement(nillable = true)
	@XmlJavaTypeAdapter(ComposedBeanCategoryAssigmentAdapter.class)
	@Schema(
		// reference = "ABeanCategoryAssignment",
		description = "Returns the bean of the composed Category Assignment\n"
				+ "This can't be via the API.",
		accessMode = AccessMode.READ_ONLY)
	@SuppressWarnings("unchecked")
	@Override
	public BEAN_TYPE getValue() {
		BEAN_TYPE composedBean = null;
		
		if (isSet()) {
			CategoryAssignment composedCa = ti.getTypeInstance();
			
			BeanCategoryAssignmentFactory beanCaFactory = new BeanCategoryAssignmentFactory();
			try {
				composedBean = (BEAN_TYPE) beanCaFactory.getInstanceFor((CategoryAssignment) composedCa);
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
		// Can't unset the composed ca on the bean level
	}
	
	@Schema(
			description = "Always returns constant: \"composed\"", 
			example = "composed",
			accessMode = AccessMode.READ_ONLY)
	@Override
	public BeanPropertyType getPropertyType() {
		return BeanPropertyType.COMPOSED;
	}
	
	@Override
	public boolean getIsCalculated() {
		// As the isCalculated property for this bean depends on the composed property
		// It can only be evaluated if a composed property is set
		return isSet() && super.getIsCalculated();
	}
}
