/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferencePropertyHelper;

public class EReferencePropertyInstanceImplCustom extends EReferencePropertyInstanceImpl {
	
	@Override
	public void setReference(EObject newReference) {
		if (isValueTypeValid(newReference)) {
			super.setReference(newReference);
		}
	}
	
	/**
	 * Check if the new reference value is of the correct ECLass type
	 * @param newValue the new reference value
	 * @return true if value is of valid type
	 */
	protected boolean isValueTypeValid(EObject newValue) {
		if (newValue == null) {
			return true;
		}
		if (newValue.eIsProxy()) {
			return true;
		}
		EClass type = new EReferencePropertyHelper().getResolvedEClassType((EReferenceProperty) this.getType());
		EClass newInstanceEClass = newValue.eClass();
		if (equalsEClass(type, newInstanceEClass)) {
			return true;
		} else {
			for (EClass superType : type.getEAllSuperTypes()) {
				if (equalsEClass(newInstanceEClass, superType)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Compare EClass objects by their name and container package NS_URI 
	 * 
	 * Allows comparing EClasses derived from a dynamic and a static package object (EPackage or its generated concrete class XXXPackage)
	 * @param firstEClass first class to compare
	 * @param secondEClass second class to compare
	 * @return true if EClass are equal
	 */
	protected boolean equalsEClass(EClass firstEClass, EClass secondEClass) {
		if (firstEClass.getName().equals(secondEClass.getName())) {
			EPackage packageFirstEClass = (EPackage) firstEClass.eContainer();
			EPackage packageSecondEClass = (EPackage) secondEClass.eContainer();
			if (packageFirstEClass.getNsURI().equals(packageSecondEClass.getNsURI())) {
				return true;
			}
		}
		return false;
	}

}
