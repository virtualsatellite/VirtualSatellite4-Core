/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.util;

import de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionInput;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.*;

import de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition;

import de.dlr.sc.virsat.model.dvlm.general.IDescription;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage
 * @generated
 */
public class PropertydefinitionsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PropertydefinitionsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertydefinitionsSwitch() {
		if (modelPackage == null) {
			modelPackage = PropertydefinitionsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case PropertydefinitionsPackage.APROPERTY: {
				AProperty aProperty = (AProperty)theEObject;
				T result = caseAProperty(aProperty);
				if (result == null) result = caseATypeDefinition(aProperty);
				if (result == null) result = caseIDescription(aProperty);
				if (result == null) result = caseIConceptTypeDefinition(aProperty);
				if (result == null) result = caseIQualifiedName(aProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertydefinitionsPackage.COMPOSED_PROPERTY: {
				ComposedProperty composedProperty = (ComposedProperty)theEObject;
				T result = caseComposedProperty(composedProperty);
				if (result == null) result = caseAProperty(composedProperty);
				if (result == null) result = caseIEquationDefinitionInput(composedProperty);
				if (result == null) result = caseATypeDefinition(composedProperty);
				if (result == null) result = caseIDescription(composedProperty);
				if (result == null) result = caseIConceptTypeDefinition(composedProperty);
				if (result == null) result = caseIQualifiedName(composedProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertydefinitionsPackage.REFERENCE_PROPERTY: {
				ReferenceProperty referenceProperty = (ReferenceProperty)theEObject;
				T result = caseReferenceProperty(referenceProperty);
				if (result == null) result = caseAProperty(referenceProperty);
				if (result == null) result = caseATypeDefinition(referenceProperty);
				if (result == null) result = caseIDescription(referenceProperty);
				if (result == null) result = caseIConceptTypeDefinition(referenceProperty);
				if (result == null) result = caseIQualifiedName(referenceProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertydefinitionsPackage.EREFERENCE_PROPERTY: {
				EReferenceProperty eReferenceProperty = (EReferenceProperty)theEObject;
				T result = caseEReferenceProperty(eReferenceProperty);
				if (result == null) result = caseAProperty(eReferenceProperty);
				if (result == null) result = caseATypeDefinition(eReferenceProperty);
				if (result == null) result = caseIDescription(eReferenceProperty);
				if (result == null) result = caseIConceptTypeDefinition(eReferenceProperty);
				if (result == null) result = caseIQualifiedName(eReferenceProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertydefinitionsPackage.IINTRINSIC_TYPE_PROPERTY: {
				IIntrinsicTypeProperty iIntrinsicTypeProperty = (IIntrinsicTypeProperty)theEObject;
				T result = caseIIntrinsicTypeProperty(iIntrinsicTypeProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY: {
				AQudvTypeProperty aQudvTypeProperty = (AQudvTypeProperty)theEObject;
				T result = caseAQudvTypeProperty(aQudvTypeProperty);
				if (result == null) result = caseIEquationDefinitionInput(aQudvTypeProperty);
				if (result == null) result = caseAProperty(aQudvTypeProperty);
				if (result == null) result = caseATypeDefinition(aQudvTypeProperty);
				if (result == null) result = caseIDescription(aQudvTypeProperty);
				if (result == null) result = caseIConceptTypeDefinition(aQudvTypeProperty);
				if (result == null) result = caseIQualifiedName(aQudvTypeProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertydefinitionsPackage.INT_PROPERTY: {
				IntProperty intProperty = (IntProperty)theEObject;
				T result = caseIntProperty(intProperty);
				if (result == null) result = caseAQudvTypeProperty(intProperty);
				if (result == null) result = caseIIntrinsicTypeProperty(intProperty);
				if (result == null) result = caseIEquationDefinitionInput(intProperty);
				if (result == null) result = caseAProperty(intProperty);
				if (result == null) result = caseATypeDefinition(intProperty);
				if (result == null) result = caseIDescription(intProperty);
				if (result == null) result = caseIConceptTypeDefinition(intProperty);
				if (result == null) result = caseIQualifiedName(intProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertydefinitionsPackage.FLOAT_PROPERTY: {
				FloatProperty floatProperty = (FloatProperty)theEObject;
				T result = caseFloatProperty(floatProperty);
				if (result == null) result = caseAQudvTypeProperty(floatProperty);
				if (result == null) result = caseIIntrinsicTypeProperty(floatProperty);
				if (result == null) result = caseIEquationDefinitionInput(floatProperty);
				if (result == null) result = caseAProperty(floatProperty);
				if (result == null) result = caseATypeDefinition(floatProperty);
				if (result == null) result = caseIDescription(floatProperty);
				if (result == null) result = caseIConceptTypeDefinition(floatProperty);
				if (result == null) result = caseIQualifiedName(floatProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertydefinitionsPackage.STRING_PROPERTY: {
				StringProperty stringProperty = (StringProperty)theEObject;
				T result = caseStringProperty(stringProperty);
				if (result == null) result = caseAProperty(stringProperty);
				if (result == null) result = caseIIntrinsicTypeProperty(stringProperty);
				if (result == null) result = caseATypeDefinition(stringProperty);
				if (result == null) result = caseIDescription(stringProperty);
				if (result == null) result = caseIConceptTypeDefinition(stringProperty);
				if (result == null) result = caseIQualifiedName(stringProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertydefinitionsPackage.BOOLEAN_PROPERTY: {
				BooleanProperty booleanProperty = (BooleanProperty)theEObject;
				T result = caseBooleanProperty(booleanProperty);
				if (result == null) result = caseAProperty(booleanProperty);
				if (result == null) result = caseIIntrinsicTypeProperty(booleanProperty);
				if (result == null) result = caseATypeDefinition(booleanProperty);
				if (result == null) result = caseIDescription(booleanProperty);
				if (result == null) result = caseIConceptTypeDefinition(booleanProperty);
				if (result == null) result = caseIQualifiedName(booleanProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertydefinitionsPackage.ENUM_PROPERTY: {
				EnumProperty enumProperty = (EnumProperty)theEObject;
				T result = caseEnumProperty(enumProperty);
				if (result == null) result = caseAQudvTypeProperty(enumProperty);
				if (result == null) result = caseIEquationDefinitionInput(enumProperty);
				if (result == null) result = caseAProperty(enumProperty);
				if (result == null) result = caseATypeDefinition(enumProperty);
				if (result == null) result = caseIDescription(enumProperty);
				if (result == null) result = caseIConceptTypeDefinition(enumProperty);
				if (result == null) result = caseIQualifiedName(enumProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertydefinitionsPackage.ENUM_VALUE_DEFINITION: {
				EnumValueDefinition enumValueDefinition = (EnumValueDefinition)theEObject;
				T result = caseEnumValueDefinition(enumValueDefinition);
				if (result == null) result = caseIName(enumValueDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertydefinitionsPackage.RESOURCE_PROPERTY: {
				ResourceProperty resourceProperty = (ResourceProperty)theEObject;
				T result = caseResourceProperty(resourceProperty);
				if (result == null) result = caseAProperty(resourceProperty);
				if (result == null) result = caseATypeDefinition(resourceProperty);
				if (result == null) result = caseIDescription(resourceProperty);
				if (result == null) result = caseIConceptTypeDefinition(resourceProperty);
				if (result == null) result = caseIQualifiedName(resourceProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertydefinitionsPackage.STATIC_ARRAY_MODIFIER: {
				StaticArrayModifier staticArrayModifier = (StaticArrayModifier)theEObject;
				T result = caseStaticArrayModifier(staticArrayModifier);
				if (result == null) result = caseIArrayModifier(staticArrayModifier);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertydefinitionsPackage.IARRAY_MODIFIER: {
				IArrayModifier iArrayModifier = (IArrayModifier)theEObject;
				T result = caseIArrayModifier(iArrayModifier);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertydefinitionsPackage.DYNAMIC_ARRAY_MODIFIER: {
				DynamicArrayModifier dynamicArrayModifier = (DynamicArrayModifier)theEObject;
				T result = caseDynamicArrayModifier(dynamicArrayModifier);
				if (result == null) result = caseIArrayModifier(dynamicArrayModifier);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AProperty</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AProperty</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAProperty(AProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composed Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composed Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComposedProperty(ComposedProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceProperty(ReferenceProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EReference Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EReference Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEReferenceProperty(EReferenceProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IIntrinsic Type Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IIntrinsic Type Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIIntrinsicTypeProperty(IIntrinsicTypeProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AQudv Type Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AQudv Type Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAQudvTypeProperty(AQudvTypeProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Int Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Int Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntProperty(IntProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Float Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Float Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFloatProperty(FloatProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringProperty(StringProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBooleanProperty(BooleanProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enum Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enum Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumProperty(EnumProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enum Value Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enum Value Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumValueDefinition(EnumValueDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceProperty(ResourceProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Static Array Modifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Static Array Modifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStaticArrayModifier(StaticArrayModifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IArray Modifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IArray Modifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIArrayModifier(IArrayModifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dynamic Array Modifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dynamic Array Modifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDynamicArrayModifier(DynamicArrayModifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IQualified Name</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IQualified Name</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIQualifiedName(IQualifiedName object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IDescription</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IDescription</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIDescription(IDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IConcept Type Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IConcept Type Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIConceptTypeDefinition(IConceptTypeDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AType Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AType Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseATypeDefinition(ATypeDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IEquation Definition Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IEquation Definition Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIEquationDefinitionInput(IEquationDefinitionInput object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IName</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IName</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIName(IName object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //PropertydefinitionsSwitch
