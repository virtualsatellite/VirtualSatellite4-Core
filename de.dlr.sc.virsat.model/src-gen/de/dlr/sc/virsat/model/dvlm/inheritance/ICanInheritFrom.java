/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.inheritance;


import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ICan Inherit From</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The canInheritFrom tells which SystemComponent can Inherit from which other ones. This can be used to make a ConfigurationElement inherit from a DefinitionElement but not the other way. This interface should only be used for Structural Elements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.inheritance.ICanInheritFrom#getCanInheritFrom <em>Can Inherit From</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.inheritance.ICanInheritFrom#isIsCanInheritFromAll <em>Is Can Inherit From All</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage#getICanInheritFrom()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ICanInheritFrom extends EObject {
	/**
	 * Returns the value of the '<em><b>Can Inherit From</b></em>' reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Can Inherit From</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Can Inherit From</em>' reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage#getICanInheritFrom_CanInheritFrom()
	 * @model
	 * @generated
	 */
	EList<StructuralElement> getCanInheritFrom();

	/**
	 * Returns the value of the '<em><b>Is Can Inherit From All</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Can Inherit From All</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Can Inherit From All</em>' attribute.
	 * @see #setIsCanInheritFromAll(boolean)
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage#getICanInheritFrom_IsCanInheritFromAll()
	 * @model default="false"
	 * @generated
	 */
	boolean isIsCanInheritFromAll();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.inheritance.ICanInheritFrom#isIsCanInheritFromAll <em>Is Can Inherit From All</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Can Inherit From All</em>' attribute.
	 * @see #isIsCanInheritFromAll()
	 * @generated
	 */
	void setIsCanInheritFromAll(boolean value);

} // ICanInheritFrom
