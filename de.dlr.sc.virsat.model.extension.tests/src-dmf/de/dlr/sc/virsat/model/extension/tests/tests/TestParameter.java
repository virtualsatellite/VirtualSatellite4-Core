/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.extension.tests.tests;

import de.dlr.sc.virsat.model.ext.core.core.GenericCategory;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestParameter#getDefaultValue <em>Default Value</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestParameter()
 * @model
 * @generated
 */
public interface TestParameter extends GenericCategory {
	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value</em>' attribute.
	 * @see #setDefaultValue(double)
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestParameter_DefaultValue()
	 * @model
	 * @generated
	 */
	double getDefaultValue();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestParameter#getDefaultValue <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' attribute.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(double value);

} // TestParameter
