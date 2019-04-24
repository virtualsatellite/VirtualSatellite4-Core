/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories;


import de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;

import de.dlr.sc.virsat.model.dvlm.general.IName;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Category Assignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment#getPropertyInstances <em>Property Instances</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage#getCategoryAssignment()
 * @model
 * @generated
 */
public interface CategoryAssignment extends ATypeInstance, IName, IEquationInput, IEquationSectionContainer {
	/**
	 * Returns the value of the '<em><b>Property Instances</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Instances</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Instances</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage#getCategoryAssignment_PropertyInstances()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<APropertyInstance> getPropertyInstances();

} // CategoryAssignment
