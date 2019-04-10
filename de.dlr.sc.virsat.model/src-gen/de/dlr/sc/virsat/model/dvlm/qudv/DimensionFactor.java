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


import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dimension Factor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.DimensionFactor#getExponent <em>Exponent</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.DimensionFactor#getBaseDimension <em>Base Dimension</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getDimensionFactor()
 * @model
 * @generated
 */
public interface DimensionFactor extends EObject {
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
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getDimensionFactor_Exponent()
	 * @model required="true"
	 * @generated
	 */
	double getExponent();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.DimensionFactor#getExponent <em>Exponent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exponent</em>' attribute.
	 * @see #getExponent()
	 * @generated
	 */
	void setExponent(double value);

	/**
	 * Returns the value of the '<em><b>Base Dimension</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Dimension</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Dimension</em>' reference.
	 * @see #setBaseDimension(AQuantityKind)
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getDimensionFactor_BaseDimension()
	 * @model required="true"
	 * @generated
	 */
	AQuantityKind getBaseDimension();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.DimensionFactor#getBaseDimension <em>Base Dimension</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Dimension</em>' reference.
	 * @see #getBaseDimension()
	 * @generated
	 */
	void setBaseDimension(AQuantityKind value);

} // DimensionFactor
