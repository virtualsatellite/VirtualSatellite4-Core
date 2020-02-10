/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;

/**
 * Checks if an an object is a valid referencable object by some object representing a reference
 * (Which in practice should only be ReferencePropertyInstance referencing some type instance)
 * @author muel_s8
 *
 */

public class DVLMReferenceCheck {
	
	/**
	 * Private Constructor
	 */
	private DVLMReferenceCheck() {
	
	}
	
	/**
	 * Performs the validity check
	 * @param object the referencing object
	 * @param referencedObject the referenced object
	 * @return whether object is allowed to be set to reference to referencedObject
	 */
	public static boolean isValid(Object object, Object referencedObject) {
		
		if (object instanceof ReferencePropertyInstance) {
			ReferencePropertyInstance referencePropertyInstance = (ReferencePropertyInstance) object;
			ReferenceProperty referenceProperty = (ReferenceProperty) referencePropertyInstance.getType();
			return isValid(referenceProperty, referencedObject);
		} else if (object instanceof EReferencePropertyInstance) {
			EReferencePropertyInstance referencePropertyInstance = (EReferencePropertyInstance) object;
			EReferenceProperty referenceProperty = (EReferenceProperty) referencePropertyInstance.getType();
			return isValid(referenceProperty, referencedObject);
		}
		
		return true;
	}
	
	/**
	 * Performs the validity check
	 * @param referenceProperty the type of the referencing object
	 * @param referencedObject the referenced object
	 * @return whether object is allowed to be set to reference to referencedObject
	 */
	public static boolean isValid(ReferenceProperty referenceProperty, Object referencedObject) {
		
		if (referencedObject instanceof ATypeInstance) {
			ATypeInstance referencedInstance = (ATypeInstance) referencedObject;
			
			// In case we have a proxy dont know the type of the referencedObject
			if (referencedInstance.eIsProxy()) {
				return true;
			}
			
			// If we can get the type, then compare with to the allowed type
			ATypeDefinition typeDefinition = referencedInstance.getType();
			
			// If we have no types, then we consider them to be of any type
			if (referenceProperty == null || typeDefinition == null) {
				return true;
			}
			
			ATypeDefinition referencedType = referenceProperty.getReferenceType();
			
			if (typeDefinition instanceof Category) {
				Category category = (Category) typeDefinition;
				return category.isExtensionOf(referencedType);
			} else {
				return referencedType.equals(typeDefinition);
			}
		}
		
		return true;
	}
	
	/**
	 * Performs the validity check
	 * @param referenceProperty the type of the referencing object
	 * @param referencedObject the referenced object
	 * @return whether object is allowed to be set to reference to referencedObject
	 */
	public static boolean isValid(EReferenceProperty referenceProperty, Object referencedObject) {
		
		if (referencedObject instanceof EObject) {
			EObject referencedInstance = (EObject) referencedObject;
			
			// In case we have a proxy dont know the type of the referencedObject
			if (referencedInstance.eIsProxy()) {
				return true;
			}
			
			// If we can get the type, then compare with to the allowed type
			EClass typeDefinition = referencedInstance.eClass();
			
			// If we have no types, then we consider them to be of any type
			if (referenceProperty == null || typeDefinition == null) {
				return true;
			}
			
			EClass referencedType = referenceProperty.getReferenceType();
			
			return referencedType.equals(typeDefinition);
		}
		
		return true;
	}
}
