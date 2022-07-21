/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions;


import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AProperty</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty#getArrayModifier <em>Array Modifier</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty#getVerification <em>Verification</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage#getAProperty()
 * @model abstract="true"
 * @generated
 */
public interface AProperty extends ATypeDefinition {
	/**
	 * Returns the value of the '<em><b>Array Modifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Array Modifier</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Array Modifier</em>' containment reference.
	 * @see #setArrayModifier(IArrayModifier)
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage#getAProperty_ArrayModifier()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	IArrayModifier getArrayModifier();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty#getArrayModifier <em>Array Modifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Array Modifier</em>' containment reference.
	 * @see #getArrayModifier()
	 * @generated
	 */
	void setArrayModifier(IArrayModifier value);

	/**
	 * Returns the value of the '<em><b>Verification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Verification</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Verification</em>' containment reference.
	 * @see #setVerification(IVerificationSpecification)
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage#getAProperty_Verification()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	IVerificationSpecification getVerification();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty#getVerification <em>Verification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Verification</em>' containment reference.
	 * @see #getVerification()
	 * @generated
	 */
	void setVerification(IVerificationSpecification value);

} // AProperty
