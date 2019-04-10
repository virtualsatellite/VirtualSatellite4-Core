/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.factory;

import org.eclipse.core.runtime.CoreException;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;

/**
 * A factory class for CategoryAssignmentBeans which can create the correct bean
 * for a given ID or CA depending on the registration of Beans
 * @author fisc_ph
 *
 */
public class BeanCategoryAssignmentFactory {

	public static final String EXTENSION_POINT_BEAN_FACTORY_ID = "de.dlr.sc.virsat.model.edit.ConceptTypeFactoryCaBeanRegistration";
	public static final String EXTENSION_POINT_BEAN_FACTORY_ELEMENT_ID = "CategoryAssignmentBean";
	public static final String EXTENSION_POINT_BEAN_FACTORY_ATTRIBUTE_ID = "id";
	public static final String EXTENSION_POINT_BEAN_FACTORY_ATTRIBUTE_CA = "caBean";
	
	BeanRegistry beanRegistry;

	/**
	 * Standard Constructor that directly sets the Bean registry to search for the correct
	 * Extension point and attributes. Another constructor for injecting the registry is
	 * intended for testing purposes.
	 */
	public BeanCategoryAssignmentFactory() {
		beanRegistry = new BeanRegistry(
				EXTENSION_POINT_BEAN_FACTORY_ID,
				EXTENSION_POINT_BEAN_FACTORY_ELEMENT_ID,
				EXTENSION_POINT_BEAN_FACTORY_ATTRIBUTE_ID,
				EXTENSION_POINT_BEAN_FACTORY_ATTRIBUTE_CA);
	}
	
	/**
	 * Call this method to get the correct Bean for a given FQN
	 * @param fullQualifiedName The FQN for which to create a Bean
	 * @return The Bean corresponding to the given FQN
	 * @throws CoreException An Exception in case the Bean cannot be created
	 */
	public IBeanCategoryAssignment getInstanceFor(String fullQualifiedName) throws CoreException {
		IBeanCategoryAssignment beanCa = (IBeanCategoryAssignment) beanRegistry.createBeanInstanceForId(fullQualifiedName);
		return beanCa;
	}
	
	/**
	 * Call this method to create a Bean for a given Category
	 * @param category The category for which the corresponding Bean should be created
	 * @return The Bean that corresponds to the given Category
	 * @throws CoreException An exception in case no corresponding Bean could be created
	 */
	public IBeanCategoryAssignment getInstanceFor(Category category) throws CoreException {
		String fullQualifiedName = category.getFullQualifiedName();
		return getInstanceFor(fullQualifiedName);
	}

	/**
	 * Call this method to create the correct Bean for a given Category Assignment. This
	 * method will also set the Beans internal Type Instance accordingly.
	 * @param categoryAssignment The CategoryAssignment for which to create a Bean
	 * @return the Bean for the given CategoryAssignment with the Type Instance being correctly set
	 * @throws CoreException An exception if a Bean could not be created for the given CA
	 */
	public IBeanCategoryAssignment getInstanceFor(CategoryAssignment categoryAssignment) throws CoreException {
		Category category = (Category) categoryAssignment.getType();
		IBeanCategoryAssignment beanCa = getInstanceFor(category);
		beanCa.setTypeInstance(categoryAssignment);
		return beanCa;
	}
}
