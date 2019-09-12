/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * This class encapsulates some external functionality that helps to handle
 * CategoryAssignments which are wrapped in our Beans model.
 * @author fisc_ph
 *
 */
public class BeanCategoryAssignmentHelper {

	/**
	 * new method to directly convert one CA into a given Type of Bean
	 * @param ca The CA to be converted
	 * @param catBeanClazz The Bean Type to be used
	 * @param <CA_TYPE> The generic Type of the bean wrapping the CA
	 * @return The bean with the wrapped CA or null in case of type incompatibility
	 */
	public <CA_TYPE extends IBeanCategoryAssignment> CA_TYPE getBeanCategory(CategoryAssignment ca, Class<CA_TYPE> catBeanClazz) {
		try {
			IBeanCategoryAssignment beanCategory = catBeanClazz.newInstance();
			String categoryID = beanCategory.getFullQualifiedCategoryName();
			
			if (ca.getType().getFullQualifiedName().equals(categoryID)) {
				CA_TYPE wrappedBeanCa = catBeanClazz.newInstance();
				wrappedBeanCa.setTypeInstance(ca);
				return wrappedBeanCa;
			}
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
		
		return null;
	}
	
	/**
	 * Call this method to get a parent CA of this CA which is of a given type
	 * @param eObject an object from where to start searching for the parent
	 * @param beanCaClazz the BeanClass defining the type to look for
	 * @param <CA_TYPE> Generic class type
	 * @return the type or null in case it could not be found
	 */
	public <CA_TYPE extends IBeanCategoryAssignment> CA_TYPE getParentCaBeanOfClass(EObject eObject, Class<CA_TYPE> beanCaClazz) {
		try {
			EObject eContainer = eObject.eContainer();
			CA_TYPE beanCa = beanCaClazz.newInstance();
			String seiID = beanCa.getFullQualifiedCategoryName();
			
			while (eContainer != null && !(eContainer instanceof StructuralElementInstance)) {
				if (eContainer instanceof CategoryAssignment) {
					CategoryAssignment seiContainer = (CategoryAssignment) eContainer;
					if (seiContainer.getType().getFullQualifiedName().equals(seiID)) {
						beanCa.setTypeInstance(seiContainer);
						return beanCa;
					}
				}
				
				eContainer = eContainer.eContainer();
			}
		
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
		
		return null;
	}
	
	/**
	 * Call this method to hand back the appropriate Type of CategoryAssignment
	 * @param caContainer The contains where the Category Assignments reside. Usually it is a SrurturalElementInstance
	 * @param catBeanClazz the BeanClass that should be used for internal instantiation and also describing the type that will be ahnded back.
	 * @param <CA_TYPE> Generic type of the Method derived from BeanCategory. Should be set to one of the concepts generated Bean Categories.
	 * @return a List of CategoryAssignments which are wrapped into the Beans Model
	 */
	@SuppressWarnings("unchecked")
	public <CA_TYPE extends IBeanCategoryAssignment> List<CA_TYPE> getAllBeanCategories(ICategoryAssignmentContainer caContainer, Class<CA_TYPE> catBeanClazz) {
		List<CA_TYPE> caBeans = new LinkedList<>();
		
		BeanCategoryAssignmentFactory beanCaFactory = new BeanCategoryAssignmentFactory();
		
		for (CategoryAssignment ca : caContainer.getCategoryAssignments()) {
			try {
				ABeanCategoryAssignment beanCa = (ABeanCategoryAssignment) beanCaFactory.getInstanceFor(ca);
				if (catBeanClazz.isAssignableFrom(beanCa.getClass())) {
					caBeans.add((CA_TYPE) beanCa);
				}
			} catch (CoreException e) {
				// The factory failed to provide a bean for this ca, which means this ca is from some
				// unknown concept which is not locally installed and we thus dont have
				// the class for this bean. We ignore it then.
			}
		}
		
		return Collections.unmodifiableList(caBeans);
	}
	
	/**
	 * Call this method to hand back the appropriate Type of CategoryAssignment while also considering category assignments nested in a container
	 * through composed property instances
	 * @param caContainer The contains where the Category Assignments reside. Usually it is a SrurturalElementInstance
	 * @param catBeanClazz the BeanClass that should be used for internal instantiation and also describing the type that will be ahnded back.
	 * @param <CA_TYPE> Generic type of the Method derived from BeanCategory. Should be set to one of the concepts generated Bean Categories.
	 * @return a List of CategoryAssignments which are wrapped into the Beans Model
	 */
	@SuppressWarnings("unchecked")
	public <CA_TYPE extends IBeanCategoryAssignment> List<CA_TYPE> getAllNestedBeanCategories(ICategoryAssignmentContainer caContainer, Class<CA_TYPE> catBeanClazz) {
		List<CA_TYPE> caBeans = new LinkedList<>();
		
		try {
			IBeanCategoryAssignment beanCategory = catBeanClazz.newInstance();
			String categoryID = beanCategory.getFullQualifiedCategoryName();
			
			List<CategoryAssignment> typeCompatibleCas = CategoryAssignmentHelper.getNestedCategoryAssignments(caContainer, categoryID);

			// Now wrap all CAs into the proper Beans Object
			for (CategoryAssignment typeCompatibleCa : typeCompatibleCas) {
				IBeanCategoryAssignment wrappedBeanCa = catBeanClazz.newInstance();
				wrappedBeanCa.setTypeInstance(typeCompatibleCa);
				caBeans.add((CA_TYPE) wrappedBeanCa);
			}
			
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
		
		return caBeans;
	}
	
	/**
	 * Call this method to get the first instance of a given bean that is attached to some sort of Container or SEI
	 * @param caContainer the Container or SEI from where to get the bean from.
	 * @param catBeanClazz The Clazz for the bean
	 * @param <CA_TYPE> Generic type of the Method derived from BeanCategory. Should be set to one of the concepts generated Bean Categories.
	 * @return the bean in case it was found or null in case it does not exist
	 */
	public <CA_TYPE extends IBeanCategoryAssignment> CA_TYPE getFirstBeanCategory(ICategoryAssignmentContainer caContainer, Class<CA_TYPE> catBeanClazz) {
		try {
			IBeanCategoryAssignment beanCategory = catBeanClazz.newInstance();
			String categoryID = beanCategory.getFullQualifiedCategoryName();
			
			CategoryAssignment typeCompatibelCa = CategoryAssignmentHelper.getFirstCategoryAssignment(caContainer, categoryID);
			
			if (typeCompatibelCa != null) {
				CA_TYPE wrappedBeanCa = catBeanClazz.newInstance();
				wrappedBeanCa.setTypeInstance(typeCompatibelCa);
				return wrappedBeanCa;
			}
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
		
		return null;
	}
	
	/**
	 * This method will search the structural element instance tree recursively to find all category assignments of the desired type 
	 * @param caContainerRoot The contains where the Category Assignments reside. Usually it is a StructuralElementInstance
	 * @param catBeanClazz the BeanClass that should be used for internal instantiation and also describing the type that will be handed back.
	 * @param <CA_TYPE> Generic type of the Method derived from BeanCategory. Should be set to one of the concepts generated Bean Categories.
	 * @return a List of CategoryAssignments which are wrapped into the Beans Model
	 */
	public <CA_TYPE extends IBeanCategoryAssignment> List<CA_TYPE> getAllBeanCategoriesFromRoot(ICategoryAssignmentContainer caContainerRoot, Class<CA_TYPE> catBeanClazz) {
		List<CA_TYPE> caBeans = new ArrayList<>();
		List<ICategoryAssignmentContainer> caContainers = new ArrayList<>();

		caContainers.add(caContainerRoot);
		if (caContainerRoot instanceof StructuralElementInstance) {
			caContainers.addAll(((StructuralElementInstance) caContainerRoot).getDeepChildren());
		}
		
		for (ICategoryAssignmentContainer caContainer : caContainers) {
			caBeans.addAll(getAllBeanCategories(caContainer, catBeanClazz));
		}
		
		return caBeans;
	}
}
