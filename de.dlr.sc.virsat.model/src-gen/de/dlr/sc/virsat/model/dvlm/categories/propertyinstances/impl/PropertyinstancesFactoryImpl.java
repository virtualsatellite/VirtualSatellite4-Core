/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PropertyinstancesFactoryImpl extends EFactoryImpl implements PropertyinstancesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PropertyinstancesFactory init() {
		try {
			PropertyinstancesFactory thePropertyinstancesFactory = (PropertyinstancesFactory)EPackage.Registry.INSTANCE.getEFactory(PropertyinstancesPackage.eNS_URI);
			if (thePropertyinstancesFactory != null) {
				return thePropertyinstancesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PropertyinstancesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyinstancesFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PropertyinstancesPackage.VALUE_PROPERTY_INSTANCE: return createValuePropertyInstance();
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE: return createUnitValuePropertyInstance();
			case PropertyinstancesPackage.REFERENCE_PROPERTY_INSTANCE: return createReferencePropertyInstance();
			case PropertyinstancesPackage.EREFERENCE_PROPERTY_INSTANCE: return createEReferencePropertyInstance();
			case PropertyinstancesPackage.COMPOSED_PROPERTY_INSTANCE: return createComposedPropertyInstance();
			case PropertyinstancesPackage.ARRAY_INSTANCE: return createArrayInstance();
			case PropertyinstancesPackage.RESOURCE_PROPERTY_INSTANCE: return createResourcePropertyInstance();
			case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE: return createEnumUnitPropertyInstance();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValuePropertyInstance createValuePropertyInstance() {
		ValuePropertyInstanceImpl valuePropertyInstance = new ValuePropertyInstanceImpl();
		return valuePropertyInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnitValuePropertyInstance createUnitValuePropertyInstance() {
		UnitValuePropertyInstanceImpl unitValuePropertyInstance = new UnitValuePropertyInstanceImpl();
		return unitValuePropertyInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferencePropertyInstance createReferencePropertyInstance() {
		ReferencePropertyInstanceImpl referencePropertyInstance = new ReferencePropertyInstanceImpl();
		return referencePropertyInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReferencePropertyInstance createEReferencePropertyInstance() {
		EReferencePropertyInstanceImpl eReferencePropertyInstance = new EReferencePropertyInstanceImpl();
		return eReferencePropertyInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposedPropertyInstance createComposedPropertyInstance() {
		ComposedPropertyInstanceImpl composedPropertyInstance = new ComposedPropertyInstanceImpl();
		return composedPropertyInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayInstance createArrayInstance() {
		ArrayInstanceImpl arrayInstance = new ArrayInstanceImpl();
		return arrayInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourcePropertyInstance createResourcePropertyInstance() {
		ResourcePropertyInstanceImpl resourcePropertyInstance = new ResourcePropertyInstanceImpl();
		return resourcePropertyInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumUnitPropertyInstance createEnumUnitPropertyInstance() {
		EnumUnitPropertyInstanceImpl enumUnitPropertyInstance = new EnumUnitPropertyInstanceImpl();
		return enumUnitPropertyInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyinstancesPackage getPropertyinstancesPackage() {
		return (PropertyinstancesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PropertyinstancesPackage getPackage() {
		return PropertyinstancesPackage.eINSTANCE;
	}

} //PropertyinstancesFactoryImpl
