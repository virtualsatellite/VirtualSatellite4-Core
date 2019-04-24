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
 * A representation of the model object '<em><b>Affine Conversion Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit#getFactor <em>Factor</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit#getOffset <em>Offset</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getAffineConversionUnit()
 * @model
 * @generated
 */
public interface AffineConversionUnit extends AConversionBasedUnit {
	/**
	 * Returns the value of the '<em><b>Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Factor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Factor</em>' attribute.
	 * @see #setFactor(double)
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getAffineConversionUnit_Factor()
	 * @model required="true"
	 * @generated
	 */
	double getFactor();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit#getFactor <em>Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Factor</em>' attribute.
	 * @see #getFactor()
	 * @generated
	 */
	void setFactor(double value);

	/**
	 * Returns the value of the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Offset</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Offset</em>' attribute.
	 * @see #setOffset(double)
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getAffineConversionUnit_Offset()
	 * @model required="true"
	 * @generated
	 */
	double getOffset();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit#getOffset <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Offset</em>' attribute.
	 * @see #getOffset()
	 * @generated
	 */
	void setOffset(double value);

} // AffineConversionUnit
