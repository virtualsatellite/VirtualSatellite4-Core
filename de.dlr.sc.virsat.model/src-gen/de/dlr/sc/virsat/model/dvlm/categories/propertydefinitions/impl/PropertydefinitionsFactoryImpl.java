/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.*;

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
public class PropertydefinitionsFactoryImpl extends EFactoryImpl implements PropertydefinitionsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PropertydefinitionsFactory init() {
		try {
			PropertydefinitionsFactory thePropertydefinitionsFactory = (PropertydefinitionsFactory)EPackage.Registry.INSTANCE.getEFactory(PropertydefinitionsPackage.eNS_URI);
			if (thePropertydefinitionsFactory != null) {
				return thePropertydefinitionsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PropertydefinitionsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertydefinitionsFactoryImpl() {
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
			case PropertydefinitionsPackage.COMPOSED_PROPERTY: return createComposedProperty();
			case PropertydefinitionsPackage.REFERENCE_PROPERTY: return createReferenceProperty();
			case PropertydefinitionsPackage.EREFERENCE_PROPERTY: return createEReferenceProperty();
			case PropertydefinitionsPackage.INT_PROPERTY: return createIntProperty();
			case PropertydefinitionsPackage.FLOAT_PROPERTY: return createFloatProperty();
			case PropertydefinitionsPackage.STRING_PROPERTY: return createStringProperty();
			case PropertydefinitionsPackage.BOOLEAN_PROPERTY: return createBooleanProperty();
			case PropertydefinitionsPackage.ENUM_PROPERTY: return createEnumProperty();
			case PropertydefinitionsPackage.ENUM_VALUE_DEFINITION: return createEnumValueDefinition();
			case PropertydefinitionsPackage.RESOURCE_PROPERTY: return createResourceProperty();
			case PropertydefinitionsPackage.STATIC_ARRAY_MODIFIER: return createStaticArrayModifier();
			case PropertydefinitionsPackage.DYNAMIC_ARRAY_MODIFIER: return createDynamicArrayModifier();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposedProperty createComposedProperty() {
		ComposedPropertyImpl composedProperty = new ComposedPropertyImpl();
		return composedProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceProperty createReferenceProperty() {
		ReferencePropertyImpl referenceProperty = new ReferencePropertyImpl();
		return referenceProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReferenceProperty createEReferenceProperty() {
		EReferencePropertyImpl eReferenceProperty = new EReferencePropertyImpl();
		return eReferenceProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntProperty createIntProperty() {
		IntPropertyImpl intProperty = new IntPropertyImpl();
		return intProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatProperty createFloatProperty() {
		FloatPropertyImpl floatProperty = new FloatPropertyImpl();
		return floatProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringProperty createStringProperty() {
		StringPropertyImpl stringProperty = new StringPropertyImpl();
		return stringProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanProperty createBooleanProperty() {
		BooleanPropertyImpl booleanProperty = new BooleanPropertyImpl();
		return booleanProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumProperty createEnumProperty() {
		EnumPropertyImpl enumProperty = new EnumPropertyImpl();
		return enumProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumValueDefinition createEnumValueDefinition() {
		EnumValueDefinitionImpl enumValueDefinition = new EnumValueDefinitionImpl();
		return enumValueDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceProperty createResourceProperty() {
		ResourcePropertyImpl resourceProperty = new ResourcePropertyImpl();
		return resourceProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticArrayModifier createStaticArrayModifier() {
		StaticArrayModifierImpl staticArrayModifier = new StaticArrayModifierImpl();
		return staticArrayModifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicArrayModifier createDynamicArrayModifier() {
		DynamicArrayModifierImpl dynamicArrayModifier = new DynamicArrayModifierImpl();
		return dynamicArrayModifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertydefinitionsPackage getPropertydefinitionsPackage() {
		return (PropertydefinitionsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PropertydefinitionsPackage getPackage() {
		return PropertydefinitionsPackage.eINSTANCE;
	}

} //PropertydefinitionsFactoryImpl
