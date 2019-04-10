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


import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dimension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.Dimension#getSymbolicExpression <em>Symbolic Expression</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.Dimension#getFactor <em>Factor</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getDimension()
 * @model
 * @generated
 */
public interface Dimension extends EObject {
	/**
	 * Returns the value of the '<em><b>Symbolic Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Symbolic Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Symbolic Expression</em>' attribute.
	 * @see #setSymbolicExpression(String)
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getDimension_SymbolicExpression()
	 * @model required="true"
	 * @generated
	 */
	String getSymbolicExpression();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.Dimension#getSymbolicExpression <em>Symbolic Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Symbolic Expression</em>' attribute.
	 * @see #getSymbolicExpression()
	 * @generated
	 */
	void setSymbolicExpression(String value);

	/**
	 * Returns the value of the '<em><b>Factor</b></em>' reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.qudv.DimensionFactor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Factor</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Factor</em>' reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getDimension_Factor()
	 * @model
	 * @generated
	 */
	EList<DimensionFactor> getFactor();

} // Dimension
