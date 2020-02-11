/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util;

import de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.*;

import de.dlr.sc.virsat.model.dvlm.general.IComment;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink;
import de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink;

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
 * @see de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage
 * @generated
 */
public class PropertyinstancesSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PropertyinstancesPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyinstancesSwitch() {
		if (modelPackage == null) {
			modelPackage = PropertyinstancesPackage.eINSTANCE;
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
			case PropertyinstancesPackage.IUNIT_PROPERTY_INSTANCE: {
				IUnitPropertyInstance iUnitPropertyInstance = (IUnitPropertyInstance)theEObject;
				T result = caseIUnitPropertyInstance(iUnitPropertyInstance);
				if (result == null) result = caseATypeInstance(iUnitPropertyInstance);
				if (result == null) result = caseIUuid(iUnitPropertyInstance);
				if (result == null) result = caseIComment(iUnitPropertyInstance);
				if (result == null) result = caseIInstance(iUnitPropertyInstance);
				if (result == null) result = caseIInheritanceLink(iUnitPropertyInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyinstancesPackage.APROPERTY_INSTANCE: {
				APropertyInstance aPropertyInstance = (APropertyInstance)theEObject;
				T result = caseAPropertyInstance(aPropertyInstance);
				if (result == null) result = caseATypeInstance(aPropertyInstance);
				if (result == null) result = caseIUuid(aPropertyInstance);
				if (result == null) result = caseIComment(aPropertyInstance);
				if (result == null) result = caseIInstance(aPropertyInstance);
				if (result == null) result = caseIInheritanceLink(aPropertyInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyinstancesPackage.VALUE_PROPERTY_INSTANCE: {
				ValuePropertyInstance valuePropertyInstance = (ValuePropertyInstance)theEObject;
				T result = caseValuePropertyInstance(valuePropertyInstance);
				if (result == null) result = caseAPropertyInstance(valuePropertyInstance);
				if (result == null) result = caseIEquationInput(valuePropertyInstance);
				if (result == null) result = caseIOverridableInheritanceLink(valuePropertyInstance);
				if (result == null) result = caseATypeInstance(valuePropertyInstance);
				if (result == null) result = caseIUuid(valuePropertyInstance);
				if (result == null) result = caseIComment(valuePropertyInstance);
				if (result == null) result = caseIInstance(valuePropertyInstance);
				if (result == null) result = caseIInheritanceLink(valuePropertyInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE: {
				UnitValuePropertyInstance unitValuePropertyInstance = (UnitValuePropertyInstance)theEObject;
				T result = caseUnitValuePropertyInstance(unitValuePropertyInstance);
				if (result == null) result = caseValuePropertyInstance(unitValuePropertyInstance);
				if (result == null) result = caseIUnitPropertyInstance(unitValuePropertyInstance);
				if (result == null) result = caseAPropertyInstance(unitValuePropertyInstance);
				if (result == null) result = caseATypeInstance(unitValuePropertyInstance);
				if (result == null) result = caseIEquationInput(unitValuePropertyInstance);
				if (result == null) result = caseIOverridableInheritanceLink(unitValuePropertyInstance);
				if (result == null) result = caseIUuid(unitValuePropertyInstance);
				if (result == null) result = caseIComment(unitValuePropertyInstance);
				if (result == null) result = caseIInstance(unitValuePropertyInstance);
				if (result == null) result = caseIInheritanceLink(unitValuePropertyInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyinstancesPackage.REFERENCE_PROPERTY_INSTANCE: {
				ReferencePropertyInstance referencePropertyInstance = (ReferencePropertyInstance)theEObject;
				T result = caseReferencePropertyInstance(referencePropertyInstance);
				if (result == null) result = caseAPropertyInstance(referencePropertyInstance);
				if (result == null) result = caseIOverridableInheritanceLink(referencePropertyInstance);
				if (result == null) result = caseATypeInstance(referencePropertyInstance);
				if (result == null) result = caseIUuid(referencePropertyInstance);
				if (result == null) result = caseIComment(referencePropertyInstance);
				if (result == null) result = caseIInstance(referencePropertyInstance);
				if (result == null) result = caseIInheritanceLink(referencePropertyInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyinstancesPackage.EREFERENCE_PROPERTY_INSTANCE: {
				EReferencePropertyInstance eReferencePropertyInstance = (EReferencePropertyInstance)theEObject;
				T result = caseEReferencePropertyInstance(eReferencePropertyInstance);
				if (result == null) result = caseAPropertyInstance(eReferencePropertyInstance);
				if (result == null) result = caseIOverridableInheritanceLink(eReferencePropertyInstance);
				if (result == null) result = caseATypeInstance(eReferencePropertyInstance);
				if (result == null) result = caseIUuid(eReferencePropertyInstance);
				if (result == null) result = caseIComment(eReferencePropertyInstance);
				if (result == null) result = caseIInstance(eReferencePropertyInstance);
				if (result == null) result = caseIInheritanceLink(eReferencePropertyInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyinstancesPackage.COMPOSED_PROPERTY_INSTANCE: {
				ComposedPropertyInstance composedPropertyInstance = (ComposedPropertyInstance)theEObject;
				T result = caseComposedPropertyInstance(composedPropertyInstance);
				if (result == null) result = caseAPropertyInstance(composedPropertyInstance);
				if (result == null) result = caseATypeInstance(composedPropertyInstance);
				if (result == null) result = caseIUuid(composedPropertyInstance);
				if (result == null) result = caseIComment(composedPropertyInstance);
				if (result == null) result = caseIInstance(composedPropertyInstance);
				if (result == null) result = caseIInheritanceLink(composedPropertyInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyinstancesPackage.ARRAY_INSTANCE: {
				ArrayInstance arrayInstance = (ArrayInstance)theEObject;
				T result = caseArrayInstance(arrayInstance);
				if (result == null) result = caseAPropertyInstance(arrayInstance);
				if (result == null) result = caseATypeInstance(arrayInstance);
				if (result == null) result = caseIUuid(arrayInstance);
				if (result == null) result = caseIComment(arrayInstance);
				if (result == null) result = caseIInstance(arrayInstance);
				if (result == null) result = caseIInheritanceLink(arrayInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyinstancesPackage.RESOURCE_PROPERTY_INSTANCE: {
				ResourcePropertyInstance resourcePropertyInstance = (ResourcePropertyInstance)theEObject;
				T result = caseResourcePropertyInstance(resourcePropertyInstance);
				if (result == null) result = caseAPropertyInstance(resourcePropertyInstance);
				if (result == null) result = caseIOverridableInheritanceLink(resourcePropertyInstance);
				if (result == null) result = caseATypeInstance(resourcePropertyInstance);
				if (result == null) result = caseIUuid(resourcePropertyInstance);
				if (result == null) result = caseIComment(resourcePropertyInstance);
				if (result == null) result = caseIInstance(resourcePropertyInstance);
				if (result == null) result = caseIInheritanceLink(resourcePropertyInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE: {
				EnumUnitPropertyInstance enumUnitPropertyInstance = (EnumUnitPropertyInstance)theEObject;
				T result = caseEnumUnitPropertyInstance(enumUnitPropertyInstance);
				if (result == null) result = caseAPropertyInstance(enumUnitPropertyInstance);
				if (result == null) result = caseIEquationInput(enumUnitPropertyInstance);
				if (result == null) result = caseIOverridableInheritanceLink(enumUnitPropertyInstance);
				if (result == null) result = caseIUnitPropertyInstance(enumUnitPropertyInstance);
				if (result == null) result = caseATypeInstance(enumUnitPropertyInstance);
				if (result == null) result = caseIUuid(enumUnitPropertyInstance);
				if (result == null) result = caseIComment(enumUnitPropertyInstance);
				if (result == null) result = caseIInstance(enumUnitPropertyInstance);
				if (result == null) result = caseIInheritanceLink(enumUnitPropertyInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IUnit Property Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IUnit Property Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIUnitPropertyInstance(IUnitPropertyInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AProperty Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AProperty Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAPropertyInstance(APropertyInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Property Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Property Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValuePropertyInstance(ValuePropertyInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unit Value Property Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unit Value Property Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnitValuePropertyInstance(UnitValuePropertyInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference Property Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference Property Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferencePropertyInstance(ReferencePropertyInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EReference Property Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EReference Property Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEReferencePropertyInstance(EReferencePropertyInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composed Property Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composed Property Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComposedPropertyInstance(ComposedPropertyInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Array Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Array Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArrayInstance(ArrayInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Property Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Property Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourcePropertyInstance(ResourcePropertyInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enum Unit Property Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enum Unit Property Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumUnitPropertyInstance(EnumUnitPropertyInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IUuid</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IUuid</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIUuid(IUuid object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IComment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IComment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIComment(IComment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IInstance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IInstance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIInstance(IInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IInheritance Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IInheritance Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIInheritanceLink(IInheritanceLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AType Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AType Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseATypeInstance(ATypeInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IEquation Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IEquation Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIEquationInput(IEquationInput object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IOverridable Inheritance Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IOverridable Inheritance Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIOverridableInheritanceLink(IOverridableInheritanceLink object) {
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

} //PropertyinstancesSwitch
