/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.concepts;


import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IActive Concept</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.IActiveConcept#isActive <em>Active</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getIActiveConcept()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IActiveConcept extends EObject {
	/**
	 * Returns the value of the '<em><b>Active</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active</em>' attribute.
	 * @see #setActive(boolean)
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getIActiveConcept_Active()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isActive();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.concepts.IActiveConcept#isActive <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active</em>' attribute.
	 * @see #isActive()
	 * @generated
	 */
	void setActive(boolean value);

} // IActiveConcept
