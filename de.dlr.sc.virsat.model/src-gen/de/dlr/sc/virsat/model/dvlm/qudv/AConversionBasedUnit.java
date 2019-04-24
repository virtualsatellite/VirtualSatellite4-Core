/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.qudv;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AConversion Based Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit#isIsInvertible <em>Is Invertible</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit#getReferenceUnit <em>Reference Unit</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getAConversionBasedUnit()
 * @model abstract="true"
 * @generated
 */
public interface AConversionBasedUnit extends AUnit {
	/**
	 * Returns the value of the '<em><b>Is Invertible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Invertible</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Invertible</em>' attribute.
	 * @see #setIsInvertible(boolean)
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getAConversionBasedUnit_IsInvertible()
	 * @model required="true"
	 * @generated
	 */
	boolean isIsInvertible();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit#isIsInvertible <em>Is Invertible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Invertible</em>' attribute.
	 * @see #isIsInvertible()
	 * @generated
	 */
	void setIsInvertible(boolean value);

	/**
	 * Returns the value of the '<em><b>Reference Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Unit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Unit</em>' reference.
	 * @see #setReferenceUnit(AUnit)
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getAConversionBasedUnit_ReferenceUnit()
	 * @model required="true"
	 * @generated
	 */
	AUnit getReferenceUnit();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit#getReferenceUnit <em>Reference Unit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference Unit</em>' reference.
	 * @see #getReferenceUnit()
	 * @generated
	 */
	void setReferenceUnit(AUnit value);

} // AConversionBasedUnit
