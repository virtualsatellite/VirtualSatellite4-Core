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



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IOverridable Inheritance Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This interface is intended for instances of properties only. they can be actually overriden or not. A Category Assignment can not be overriden from our current understanding.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink#isOverride <em>Override</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage#getIOverridableInheritanceLink()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IOverridableInheritanceLink extends IInheritanceLink {
	/**
	 * Returns the value of the '<em><b>Override</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Override</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Override</em>' attribute.
	 * @see #setOverride(boolean)
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage#getIOverridableInheritanceLink_Override()
	 * @model
	 * @generated
	 */
	boolean isOverride();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink#isOverride <em>Override</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Override</em>' attribute.
	 * @see #isOverride()
	 * @generated
	 */
	void setOverride(boolean value);

} // IOverridableInheritanceLink
