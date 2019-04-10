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
 * A representation of the model object '<em><b>Prefixed Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit#getPrefix <em>Prefix</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getPrefixedUnit()
 * @model
 * @generated
 */
public interface PrefixedUnit extends AConversionBasedUnit {
	/**
	 * Returns the value of the '<em><b>Prefix</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prefix</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prefix</em>' reference.
	 * @see #setPrefix(Prefix)
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage#getPrefixedUnit_Prefix()
	 * @model required="true"
	 * @generated
	 */
	Prefix getPrefix();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit#getPrefix <em>Prefix</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefix</em>' reference.
	 * @see #getPrefix()
	 * @generated
	 */
	void setPrefix(Prefix value);

} // PrefixedUnit
