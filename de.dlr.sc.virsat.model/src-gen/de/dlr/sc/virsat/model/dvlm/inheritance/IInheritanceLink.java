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


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IInheritance Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This interface will be set by the inheritance mechanism to give information about a assigned categories and properties telling us where the information has been inherited from or if it has been inherited at all. Accordinbgly this interface should only be used for either CategoryAssignments or PropertyInstances.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink#getSuperTis <em>Super Tis</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink#isIsInherited <em>Is Inherited</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage#getIInheritanceLink()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IInheritanceLink extends EObject {
	/**
	 * Returns the value of the '<em><b>Super Tis</b></em>' reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Tis</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Tis</em>' reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage#getIInheritanceLink_SuperTis()
	 * @model
	 * @generated
	 */
	EList<IInheritanceLink> getSuperTis();

	/**
	 * Returns the value of the '<em><b>Is Inherited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Inherited</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Inherited</em>' attribute.
	 * @see #setIsInherited(boolean)
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage#getIInheritanceLink_IsInherited()
	 * @model
	 * @generated
	 */
	boolean isIsInherited();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink#isIsInherited <em>Is Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Inherited</em>' attribute.
	 * @see #isIsInherited()
	 * @generated
	 */
	void setIsInherited(boolean value);

} // IInheritanceLink
