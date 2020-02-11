/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage
 * @generated
 */
public interface PropertyinstancesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PropertyinstancesFactory eINSTANCE = de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Value Property Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Property Instance</em>'.
	 * @generated
	 */
	ValuePropertyInstance createValuePropertyInstance();

	/**
	 * Returns a new object of class '<em>Unit Value Property Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unit Value Property Instance</em>'.
	 * @generated
	 */
	UnitValuePropertyInstance createUnitValuePropertyInstance();

	/**
	 * Returns a new object of class '<em>Reference Property Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reference Property Instance</em>'.
	 * @generated
	 */
	ReferencePropertyInstance createReferencePropertyInstance();

	/**
	 * Returns a new object of class '<em>EReference Property Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EReference Property Instance</em>'.
	 * @generated
	 */
	EReferencePropertyInstance createEReferencePropertyInstance();

	/**
	 * Returns a new object of class '<em>Composed Property Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composed Property Instance</em>'.
	 * @generated
	 */
	ComposedPropertyInstance createComposedPropertyInstance();

	/**
	 * Returns a new object of class '<em>Array Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Array Instance</em>'.
	 * @generated
	 */
	ArrayInstance createArrayInstance();

	/**
	 * Returns a new object of class '<em>Resource Property Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Property Instance</em>'.
	 * @generated
	 */
	ResourcePropertyInstance createResourcePropertyInstance();

	/**
	 * Returns a new object of class '<em>Enum Unit Property Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Enum Unit Property Instance</em>'.
	 * @generated
	 */
	EnumUnitPropertyInstance createEnumUnitPropertyInstance();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PropertyinstancesPackage getPropertyinstancesPackage();

} //PropertyinstancesFactory
