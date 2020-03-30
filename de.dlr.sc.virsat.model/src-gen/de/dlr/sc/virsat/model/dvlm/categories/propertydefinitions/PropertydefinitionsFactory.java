/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage
 * @generated
 */
public interface PropertydefinitionsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PropertydefinitionsFactory eINSTANCE = de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.PropertydefinitionsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Composed Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composed Property</em>'.
	 * @generated
	 */
	ComposedProperty createComposedProperty();

	/**
	 * Returns a new object of class '<em>Reference Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reference Property</em>'.
	 * @generated
	 */
	ReferenceProperty createReferenceProperty();

	/**
	 * Returns a new object of class '<em>EReference Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EReference Property</em>'.
	 * @generated
	 */
	EReferenceProperty createEReferenceProperty();

	/**
	 * Returns a new object of class '<em>Int Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Int Property</em>'.
	 * @generated
	 */
	IntProperty createIntProperty();

	/**
	 * Returns a new object of class '<em>Float Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Float Property</em>'.
	 * @generated
	 */
	FloatProperty createFloatProperty();

	/**
	 * Returns a new object of class '<em>String Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Property</em>'.
	 * @generated
	 */
	StringProperty createStringProperty();

	/**
	 * Returns a new object of class '<em>Boolean Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Property</em>'.
	 * @generated
	 */
	BooleanProperty createBooleanProperty();

	/**
	 * Returns a new object of class '<em>Enum Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Enum Property</em>'.
	 * @generated
	 */
	EnumProperty createEnumProperty();

	/**
	 * Returns a new object of class '<em>Enum Value Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Enum Value Definition</em>'.
	 * @generated
	 */
	EnumValueDefinition createEnumValueDefinition();

	/**
	 * Returns a new object of class '<em>Resource Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Property</em>'.
	 * @generated
	 */
	ResourceProperty createResourceProperty();

	/**
	 * Returns a new object of class '<em>Static Array Modifier</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Static Array Modifier</em>'.
	 * @generated
	 */
	StaticArrayModifier createStaticArrayModifier();

	/**
	 * Returns a new object of class '<em>Dynamic Array Modifier</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dynamic Array Modifier</em>'.
	 * @generated
	 */
	DynamicArrayModifier createDynamicArrayModifier();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PropertydefinitionsPackage getPropertydefinitionsPackage();

} //PropertydefinitionsFactory
