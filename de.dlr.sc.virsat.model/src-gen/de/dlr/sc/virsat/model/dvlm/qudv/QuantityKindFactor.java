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


import de.dlr.sc.virsat.model.dvlm.general.IUuid;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quantity Kind Factor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor#getExponent <em>Exponent</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor#getQuantityKind <em>Quantity Kind</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getQuantityKindFactor()
 * @model
 * @generated
 */
public interface QuantityKindFactor extends IUuid {
	/**
	 * Returns the value of the '<em><b>Exponent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exponent</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exponent</em>' attribute.
	 * @see #setExponent(double)
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getQuantityKindFactor_Exponent()
	 * @model required="true"
	 * @generated
	 */
	double getExponent();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor#getExponent <em>Exponent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exponent</em>' attribute.
	 * @see #getExponent()
	 * @generated
	 */
	void setExponent(double value);

	/**
	 * Returns the value of the '<em><b>Quantity Kind</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quantity Kind</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quantity Kind</em>' reference.
	 * @see #setQuantityKind(AQuantityKind)
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getQuantityKindFactor_QuantityKind()
	 * @model required="true"
	 * @generated
	 */
	AQuantityKind getQuantityKind();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor#getQuantityKind <em>Quantity Kind</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quantity Kind</em>' reference.
	 * @see #getQuantityKind()
	 * @generated
	 */
	void setQuantityKind(AQuantityKind value);

} // QuantityKindFactor
