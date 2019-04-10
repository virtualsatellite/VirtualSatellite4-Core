/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.calculation;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.dlr.sc.virsat.model.dvlm.calculation.IQualifiedEquationObject;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;

/**
 * Helper class for extending IQualifiedEquationObjects with further functionalities.
 * @author muel_s8
 *
 */
public class QualifiedEquationObjectHelper {
	
	/**
	 * Hidden constructor.
	 */
	private QualifiedEquationObjectHelper() {
		
	}
	
	/**
	 * Gets a full qualified name for a given IQualifiedEquationObject
	 * @param qualifiedEquationObject the equation object for which we want a full qualified name
	 * @return the full qualified name of the given equation object
	 */
	public static String getFullQualifiedName(IQualifiedEquationObject qualifiedEquationObject) {
		EClass eClass = qualifiedEquationObject.eClass();
		String fqn = getFullQualifiedPath(qualifiedEquationObject) + "." + eClass.getName();
		return fqn;
	}
	
	/**
	 * Gets a full qualified name for a given IQualifiedEquationObject
	 * @param qualifiedEquationObject the equation object for which we want a full qualified name
	 * @return the full qualified name of the given equation object
	 */
	private static String getFullQualifiedPath(IQualifiedEquationObject qualifiedEquationObject) {
		EStructuralFeature containingFeature = qualifiedEquationObject.eContainingFeature();
		EObject eContainer = qualifiedEquationObject.eContainer();
		
		String parentFqn = "";
		if (eContainer instanceof IQualifiedName) {
			parentFqn = ((IQualifiedName) eContainer).getFullQualifiedName();
		} else if (eContainer instanceof IQualifiedEquationObject) {
			parentFqn = getFullQualifiedPath((IQualifiedEquationObject) eContainer);
		}
		
		String fqn = parentFqn + "." + containingFeature.getName();
		if (containingFeature.isMany()) {
			// If the feature is an array we also need to get an index to make the full qualified name unique
			List<?> collection = (List<?>) eContainer.eGet(containingFeature);
			int index = collection.indexOf(qualifiedEquationObject);
			fqn += "[" + index + "]";
		}
		
		return fqn;
	}
}
