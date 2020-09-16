/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.factory;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;

/**
 * A factory class for TypeInstanceBeans which can create the correct bean
 * for a given ATypeInstance depending on the registration of Beans
 */
public class BeanTypeInstanceFactory {

	private BeanCategoryAssignmentFactory beanCategoryAssignmentFactory;
	private BeanPropertyFactory beanPropertyFactory;
	
	public BeanTypeInstanceFactory() {
		beanCategoryAssignmentFactory = new BeanCategoryAssignmentFactory();
		beanPropertyFactory = new BeanPropertyFactory();
	}
	
	/**
	 * Call this method to create the correct Bean for a given ATypeInstance. This
	 * method will also set the Beans internal TypeInstance accordingly.
	 * @param typeInstance The ATypeInstance for which to create a Bean
	 * @return the Bean for the given ATypeInstance with the TypeInstance being correctly set
	 * @throws CoreException An exception if a Bean could not be created
	 */
	public IBeanObject<? extends ATypeInstance> getInstanceFor(ATypeInstance typeInstance) throws CoreException {
		// Return the correct category assignment or property instance bean
		if (typeInstance instanceof CategoryAssignment) {
			return beanCategoryAssignmentFactory.getInstanceFor((CategoryAssignment) typeInstance);
		} else if (typeInstance instanceof APropertyInstance) {
			return beanPropertyFactory.getInstanceFor((APropertyInstance) typeInstance);
		} else {
			throw new CoreException(new Status(
					Status.ERROR, 
					DVLMEditPlugin.PLUGIN_ID, 
					"Could not create bean for " + typeInstance.getFullQualifiedInstanceName()));
		}
	}
}
