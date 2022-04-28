/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions;


import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Verification Type Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.VerificationTypeSpecification#getVerificationType <em>Verification Type</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage#getVerificationTypeSpecification()
 * @model
 * @generated
 */
public interface VerificationTypeSpecification extends IVerificationSpecification {
	/**
	 * Returns the value of the '<em><b>Verification Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Verification Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Verification Type</em>' reference.
	 * @see #setVerificationType(ATypeDefinition)
	 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage#getVerificationTypeSpecification_VerificationType()
	 * @model
	 * @generated
	 */
	ATypeDefinition getVerificationType();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.VerificationTypeSpecification#getVerificationType <em>Verification Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Verification Type</em>' reference.
	 * @see #getVerificationType()
	 * @generated
	 */
	void setVerificationType(ATypeDefinition value);

} // VerificationTypeSpecification
