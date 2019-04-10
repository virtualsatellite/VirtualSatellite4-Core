/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.qudv.util;

import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import de.dlr.sc.virsat.model.dvlm.qudv.*;

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
 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage
 * @generated
 */
public class QudvSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static QudvPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QudvSwitch() {
		if (modelPackage == null) {
			modelPackage = QudvPackage.eINSTANCE;
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
			case QudvPackage.AUNIT: {
				AUnit aUnit = (AUnit)theEObject;
				T result = caseAUnit(aUnit);
				if (result == null) result = caseIName(aUnit);
				if (result == null) result = caseIUuid(aUnit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.UNIT_FACTOR: {
				UnitFactor unitFactor = (UnitFactor)theEObject;
				T result = caseUnitFactor(unitFactor);
				if (result == null) result = caseIUuid(unitFactor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.ACONVERSION_BASED_UNIT: {
				AConversionBasedUnit aConversionBasedUnit = (AConversionBasedUnit)theEObject;
				T result = caseAConversionBasedUnit(aConversionBasedUnit);
				if (result == null) result = caseAUnit(aConversionBasedUnit);
				if (result == null) result = caseIName(aConversionBasedUnit);
				if (result == null) result = caseIUuid(aConversionBasedUnit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.LINEAR_CONVERSION_UNIT: {
				LinearConversionUnit linearConversionUnit = (LinearConversionUnit)theEObject;
				T result = caseLinearConversionUnit(linearConversionUnit);
				if (result == null) result = caseAConversionBasedUnit(linearConversionUnit);
				if (result == null) result = caseAUnit(linearConversionUnit);
				if (result == null) result = caseIName(linearConversionUnit);
				if (result == null) result = caseIUuid(linearConversionUnit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.AFFINE_CONVERSION_UNIT: {
				AffineConversionUnit affineConversionUnit = (AffineConversionUnit)theEObject;
				T result = caseAffineConversionUnit(affineConversionUnit);
				if (result == null) result = caseAConversionBasedUnit(affineConversionUnit);
				if (result == null) result = caseAUnit(affineConversionUnit);
				if (result == null) result = caseIName(affineConversionUnit);
				if (result == null) result = caseIUuid(affineConversionUnit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.PREFIXED_UNIT: {
				PrefixedUnit prefixedUnit = (PrefixedUnit)theEObject;
				T result = casePrefixedUnit(prefixedUnit);
				if (result == null) result = caseAConversionBasedUnit(prefixedUnit);
				if (result == null) result = caseAUnit(prefixedUnit);
				if (result == null) result = caseIName(prefixedUnit);
				if (result == null) result = caseIUuid(prefixedUnit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.SIMPLE_UNIT: {
				SimpleUnit simpleUnit = (SimpleUnit)theEObject;
				T result = caseSimpleUnit(simpleUnit);
				if (result == null) result = caseAUnit(simpleUnit);
				if (result == null) result = caseIName(simpleUnit);
				if (result == null) result = caseIUuid(simpleUnit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.DERIVED_UNIT: {
				DerivedUnit derivedUnit = (DerivedUnit)theEObject;
				T result = caseDerivedUnit(derivedUnit);
				if (result == null) result = caseAUnit(derivedUnit);
				if (result == null) result = caseIName(derivedUnit);
				if (result == null) result = caseIUuid(derivedUnit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.PREFIX: {
				Prefix prefix = (Prefix)theEObject;
				T result = casePrefix(prefix);
				if (result == null) result = caseIName(prefix);
				if (result == null) result = caseIUuid(prefix);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.AQUANTITY_KIND: {
				AQuantityKind aQuantityKind = (AQuantityKind)theEObject;
				T result = caseAQuantityKind(aQuantityKind);
				if (result == null) result = caseIName(aQuantityKind);
				if (result == null) result = caseIUuid(aQuantityKind);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.QUANTITY_KIND: {
				QuantityKind quantityKind = (QuantityKind)theEObject;
				T result = caseQuantityKind(quantityKind);
				if (result == null) result = caseAQuantityKind(quantityKind);
				if (result == null) result = caseIName(quantityKind);
				if (result == null) result = caseIUuid(quantityKind);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.SIMPLE_QUANTITY_KIND: {
				SimpleQuantityKind simpleQuantityKind = (SimpleQuantityKind)theEObject;
				T result = caseSimpleQuantityKind(simpleQuantityKind);
				if (result == null) result = caseAQuantityKind(simpleQuantityKind);
				if (result == null) result = caseIName(simpleQuantityKind);
				if (result == null) result = caseIUuid(simpleQuantityKind);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.DERIVED_QUANTITY_KIND: {
				DerivedQuantityKind derivedQuantityKind = (DerivedQuantityKind)theEObject;
				T result = caseDerivedQuantityKind(derivedQuantityKind);
				if (result == null) result = caseAQuantityKind(derivedQuantityKind);
				if (result == null) result = caseIName(derivedQuantityKind);
				if (result == null) result = caseIUuid(derivedQuantityKind);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.SYSTEM_OF_UNITS: {
				SystemOfUnits systemOfUnits = (SystemOfUnits)theEObject;
				T result = caseSystemOfUnits(systemOfUnits);
				if (result == null) result = caseIName(systemOfUnits);
				if (result == null) result = caseIUuid(systemOfUnits);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.SYSTEM_OF_QUANTITIES: {
				SystemOfQuantities systemOfQuantities = (SystemOfQuantities)theEObject;
				T result = caseSystemOfQuantities(systemOfQuantities);
				if (result == null) result = caseIName(systemOfQuantities);
				if (result == null) result = caseIUuid(systemOfQuantities);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.DIMENSION: {
				Dimension dimension = (Dimension)theEObject;
				T result = caseDimension(dimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.DIMENSION_FACTOR: {
				DimensionFactor dimensionFactor = (DimensionFactor)theEObject;
				T result = caseDimensionFactor(dimensionFactor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QudvPackage.QUANTITY_KIND_FACTOR: {
				QuantityKindFactor quantityKindFactor = (QuantityKindFactor)theEObject;
				T result = caseQuantityKindFactor(quantityKindFactor);
				if (result == null) result = caseIUuid(quantityKindFactor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AUnit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AUnit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAUnit(AUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unit Factor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unit Factor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnitFactor(UnitFactor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AConversion Based Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AConversion Based Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAConversionBasedUnit(AConversionBasedUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Linear Conversion Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Linear Conversion Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinearConversionUnit(LinearConversionUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Affine Conversion Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Affine Conversion Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAffineConversionUnit(AffineConversionUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Prefixed Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Prefixed Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrefixedUnit(PrefixedUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Simple Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Simple Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSimpleUnit(SimpleUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Derived Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Derived Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDerivedUnit(DerivedUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Prefix</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Prefix</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrefix(Prefix object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AQuantity Kind</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AQuantity Kind</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAQuantityKind(AQuantityKind object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Quantity Kind</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Quantity Kind</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQuantityKind(QuantityKind object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Simple Quantity Kind</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Simple Quantity Kind</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSimpleQuantityKind(SimpleQuantityKind object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Derived Quantity Kind</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Derived Quantity Kind</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDerivedQuantityKind(DerivedQuantityKind object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Of Units</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Of Units</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemOfUnits(SystemOfUnits object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Of Quantities</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Of Quantities</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemOfQuantities(SystemOfQuantities object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDimension(Dimension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dimension Factor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dimension Factor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDimensionFactor(DimensionFactor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Quantity Kind Factor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Quantity Kind Factor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQuantityKindFactor(QuantityKindFactor object) {
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

} //QudvSwitch
