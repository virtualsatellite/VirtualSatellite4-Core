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

import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * A factory class for StructuralElementInstanceBeans which can create the correct bean
 * for a given ID or Sei depending on the registration of Beans
 * @author fisc_ph
 *
 */
public class BeanStructuralElementInstanceFactory {
	public static final String EXTENSION_POINT_BEAN_FACTORY_ID = "de.dlr.sc.virsat.model.edit.ConceptTypeFactorySeiBeanRegistration";
	public static final String EXTENSION_POINT_BEAN_FACTORY_ELEMENT_ID = "StructuralElementInstanceBean";
	public static final String EXTENSION_POINT_BEAN_FACTORY_ATTRIBUTE_ID = "id";
	public static final String EXTENSION_POINT_BEAN_FACTORY_ATTRIBUTE_SEI = "seiBean";
	
	BeanRegistry beanRegistry;

	/**
	 * Standard Constructor that directly sets the Bean registry to search for the correct
	 * Extension point and attributes. Another constructor for injecting the registry is
	 * intended for testing purposes.
	 */
	public BeanStructuralElementInstanceFactory() {
		beanRegistry = new BeanRegistry(
				EXTENSION_POINT_BEAN_FACTORY_ID,
				EXTENSION_POINT_BEAN_FACTORY_ELEMENT_ID,
				EXTENSION_POINT_BEAN_FACTORY_ATTRIBUTE_ID,
				EXTENSION_POINT_BEAN_FACTORY_ATTRIBUTE_SEI);
	}
	
	/**
	 * Call this method to get the correct Bean for a given FQN
	 * @param fullQualifiedName The FQN for which to create a Bean
	 * @return The Bean corresponding to the given FQN
	 * @throws CoreException An Exception in case the Bean cannot be created
	 */
	public IBeanStructuralElementInstance getInstanceFor(String fullQualifiedName) throws CoreException {
		IBeanStructuralElementInstance beanSei = (IBeanStructuralElementInstance) beanRegistry.createBeanInstanceForId(fullQualifiedName);
		return beanSei;
	}
	
	/**
	 * Call this method to create a Bean for a given SE
	 * @param structuralElement The SE for which the corresponding Bean should be created
	 * @return The Bean that corresponds to the given SE
	 * @throws CoreException An exception in case no corresponding Bean could be created
	 */
	public IBeanStructuralElementInstance getInstanceFor(StructuralElement structuralElement) throws CoreException {
		String fullQualifiedName = structuralElement.getFullQualifiedName();
		return getInstanceFor(fullQualifiedName);
	}

	/**
	 * Call this method to create the correct Bean for a given StructuralElementInstance. This
	 * method will also set the Beans internal Type Instance accordingly.
	 * @param structuralElementInstance The StructuralElementInstance for which to create a Bean
	 * @return the Bean for the given StructuralElementInstance with the Type Instance being correctly set
	 * @throws CoreException An exception if a Bean could not be created for the given StructuralElementInstance
	 */
	public IBeanStructuralElementInstance getInstanceFor(StructuralElementInstance structuralElementInstance) throws CoreException {
		StructuralElement structuralElement = (StructuralElement) structuralElementInstance.getType();
		IBeanStructuralElementInstance beanSei = getInstanceFor(structuralElement);
		beanSei.setStructuralElementInstance(structuralElementInstance);
		return beanSei;
	}
}
