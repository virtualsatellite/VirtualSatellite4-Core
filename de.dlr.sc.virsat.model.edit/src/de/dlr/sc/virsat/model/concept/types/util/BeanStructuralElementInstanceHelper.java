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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralElementInstanceHelper;

/**
 * This class encapsulates some functionality that helps handling structural element instances on the level of beans
 * @author fisc_ph
 *
 */

public class BeanStructuralElementInstanceHelper {

	/**
	 * This gets all the direct children structural element instances of a structural element instance that have a certain type
	 * @param sei the parent structural element instance
	 * @param beanSeiClazz generic bean type of the structural element instance
	 * @param <SEI_TYPE> Generic type of the Method derived from BeanCategory. Should be set to one of the concepts generated Bean Categories.
	 * @return a list of children of the desired type
	 */
	public <SEI_TYPE extends IBeanStructuralElementInstance> List<SEI_TYPE> getAllSubBeanSeis(StructuralElementInstance sei, Class<SEI_TYPE> beanSeiClazz) {
		List<SEI_TYPE> beanSeis = wrapAllBeanSeisOfType(sei.getChildren(), beanSeiClazz);
		
		return Collections.unmodifiableList(beanSeis);
	}
	
	/**
	 * This gets takes a list of SEIs and tries to wrap them into a given Type of Bean.
	 * @param subSeis a List of SEIs from which the specific SEI-types should be filtered from
	 * @param beanSeiClazz generic bean type of the structural element instance
	 * @param <SEI_TYPE> Generic type of the Method derived from BeanCategory. Should be set to one of the concepts generated Bean Categories.
	 * @return a list of Beans of the given type that wrap the matching SEIs
	 */
	@SuppressWarnings("unchecked")
	public <SEI_TYPE extends IBeanStructuralElementInstance> List<SEI_TYPE> wrapAllBeanSeisOfType(Collection<StructuralElementInstance> subSeis, Class<SEI_TYPE> beanSeiClazz) {	
		List<SEI_TYPE> beanSeis = new LinkedList<>();

		BeanStructuralElementInstanceFactory beanSeiFactory = new BeanStructuralElementInstanceFactory();

		for (StructuralElementInstance sei : subSeis) {
			try {
				ABeanStructuralElementInstance beanSei = (ABeanStructuralElementInstance) beanSeiFactory.getInstanceFor(sei);
				if (beanSeiClazz.isAssignableFrom(beanSei.getClass())) {
					beanSeis.add((SEI_TYPE) beanSei);
				}
			} catch (CoreException e) {
				// The factory failed to provide a bean for this sei, which means this sei is from some
				// unknown concept which is not locally installed and we thus dont have
				// the class for this bean. We ignore it then.
			}
		}

		return Collections.unmodifiableList(beanSeis);
	}
	
	/**
	 * Call this method to get the parent SEI of this CA which is of a given type
	 * @param eObject an object from where to start searching for the parent
	 * @param beanSeiClazz the BeanClass defining the type to look for
	 * @param <SEI_TYPE> Generic class type
	 * @return the type or null in case it could not be found
	 */
	public <SEI_TYPE extends IBeanStructuralElementInstance> SEI_TYPE getParentOfClass(EObject eObject, Class<SEI_TYPE> beanSeiClazz) {
		try {
			EObject eContainer = eObject.eContainer();
			SEI_TYPE beanSei = beanSeiClazz.newInstance();
			String seiID = beanSei.getFullQualifiedSturcturalElementName();
			
			while (eContainer != null) {
				if (eContainer instanceof StructuralElementInstance) {
					StructuralElementInstance seiContainer = (StructuralElementInstance) eContainer;
					if (seiContainer.getType().getFullQualifiedName().equals(seiID)) {
						beanSei.setStructuralElementInstance(seiContainer);
						return beanSei;
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
	 * This method hands back all the super Bean SEIs of a given type from which the current one inherits directly
	 * @param sei the SEI for which to get all the inherited Beans
	 * @param beanSeiClazz the type of Bean to look for
	 * @param <SEI_TYPE> the Generic Type which has to be subclass of IBeanStructuralElementInstance
	 * @return a list of all beans from which the current one inherits from filtered by the given type
	 */
	public <SEI_TYPE extends IBeanStructuralElementInstance> List<SEI_TYPE> getSuperBeanSeis(StructuralElementInstance sei, Class<SEI_TYPE> beanSeiClazz) {
		List<SEI_TYPE> beanSeis = wrapAllBeanSeisOfType(sei.getSuperSeis(), beanSeiClazz);
		return Collections.unmodifiableList(beanSeis);
	}

	/**
	 * This method hands back all the super Bean SEIs of a given type from which the current one inherits directly or indirectly
	 * @param sei the SEI for which to get all the inherited Beans
	 * @param beanSeiClazz the type of Bean to look for
	 * @param <SEI_TYPE> the Generic Type which has to be subclass of IBeanStructuralElementInstance
	 * @return a list of all beans from which the current one inherits from filtered by the given type
	 */
	public <SEI_TYPE extends IBeanStructuralElementInstance> Set<SEI_TYPE> getAllSuperBeanSeis(StructuralElementInstance sei, Class<SEI_TYPE> beanSeiClazz) {
		Set<SEI_TYPE> beanSeis = new HashSet<>(wrapAllBeanSeisOfType(StructuralElementInstanceHelper.getAllSuperSeis(sei), beanSeiClazz));
		return Collections.unmodifiableSet(beanSeis);
	}
}
