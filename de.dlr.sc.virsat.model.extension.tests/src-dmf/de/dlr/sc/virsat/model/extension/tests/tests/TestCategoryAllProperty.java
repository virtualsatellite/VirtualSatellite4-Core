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
 * A representation of the model object '<em><b>Test Category All Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestString <em>Test String</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestInt <em>Test Int</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestFloat <em>Test Float</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#isTestBool <em>Test Bool</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestResource <em>Test Resource</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestEnum <em>Test Enum</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryAllProperty()
 * @model
 * @generated
 */
public interface TestCategoryAllProperty extends GenericCategory {
	/**
	 * Returns the value of the '<em><b>Test String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test String</em>' attribute.
	 * @see #setTestString(String)
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryAllProperty_TestString()
	 * @model
	 * @generated
	 */
	String getTestString();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestString <em>Test String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test String</em>' attribute.
	 * @see #getTestString()
	 * @generated
	 */
	void setTestString(String value);

	/**
	 * Returns the value of the '<em><b>Test Int</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Int</em>' attribute.
	 * @see #setTestInt(int)
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryAllProperty_TestInt()
	 * @model
	 * @generated
	 */
	int getTestInt();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestInt <em>Test Int</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Int</em>' attribute.
	 * @see #getTestInt()
	 * @generated
	 */
	void setTestInt(int value);

	/**
	 * Returns the value of the '<em><b>Test Float</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Float</em>' attribute.
	 * @see #setTestFloat(double)
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryAllProperty_TestFloat()
	 * @model
	 * @generated
	 */
	double getTestFloat();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestFloat <em>Test Float</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Float</em>' attribute.
	 * @see #getTestFloat()
	 * @generated
	 */
	void setTestFloat(double value);

	/**
	 * Returns the value of the '<em><b>Test Bool</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Bool</em>' attribute.
	 * @see #setTestBool(boolean)
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryAllProperty_TestBool()
	 * @model
	 * @generated
	 */
	boolean isTestBool();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#isTestBool <em>Test Bool</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Bool</em>' attribute.
	 * @see #isTestBool()
	 * @generated
	 */
	void setTestBool(boolean value);

	/**
	 * Returns the value of the '<em><b>Test Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Resource</em>' attribute.
	 * @see #setTestResource(String)
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryAllProperty_TestResource()
	 * @model
	 * @generated
	 */
	String getTestResource();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestResource <em>Test Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Resource</em>' attribute.
	 * @see #getTestResource()
	 * @generated
	 */
	void setTestResource(String value);

	/**
	 * Returns the value of the '<em><b>Test Enum</b></em>' attribute.
	 * The literals are from the enumeration {@link de.dlr.sc.virsat.model.extension.tests.tests.EnumTestEnum}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Enum</em>' attribute.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.EnumTestEnum
	 * @see #setTestEnum(EnumTestEnum)
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.TestsPackage#getTestCategoryAllProperty_TestEnum()
	 * @model
	 * @generated
	 */
	EnumTestEnum getTestEnum();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.extension.tests.tests.TestCategoryAllProperty#getTestEnum <em>Test Enum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Enum</em>' attribute.
	 * @see de.dlr.sc.virsat.model.extension.tests.tests.EnumTestEnum
	 * @see #getTestEnum()
	 * @generated
	 */
	void setTestEnum(EnumTestEnum value);

} // TestCategoryAllProperty
