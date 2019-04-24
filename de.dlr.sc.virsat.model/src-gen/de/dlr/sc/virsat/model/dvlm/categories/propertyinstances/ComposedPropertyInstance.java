/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances;


import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composed Property Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The Composed Property is used to indicate that a Category will be instantaiated within the current Categoryassignment as containment. Not as a reference.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance#getTypeInstance <em>Type Instance</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage#getComposedPropertyInstance()
 * @model
 * @generated
 */
public interface ComposedPropertyInstance extends APropertyInstance {
	/**
	 * Returns the value of the '<em><b>Type Instance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Instance</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Instance</em>' containment reference.
	 * @see #setTypeInstance(CategoryAssignment)
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage#getComposedPropertyInstance_TypeInstance()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	CategoryAssignment getTypeInstance();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance#getTypeInstance <em>Type Instance</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Instance</em>' containment reference.
	 * @see #getTypeInstance()
	 * @generated
	 */
	void setTypeInstance(CategoryAssignment value);

} // ComposedPropertyInstance
