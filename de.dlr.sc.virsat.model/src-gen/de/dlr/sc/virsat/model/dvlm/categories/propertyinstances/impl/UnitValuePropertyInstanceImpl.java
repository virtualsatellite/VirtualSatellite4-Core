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


import de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;

import de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;

import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unit Value Property Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.UnitValuePropertyInstanceImpl#isOverride <em>Override</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.UnitValuePropertyInstanceImpl#getValue <em>Value</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.UnitValuePropertyInstanceImpl#getUnit <em>Unit</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UnitValuePropertyInstanceImpl extends APropertyInstanceImpl implements UnitValuePropertyInstance {
	/**
	 * The default value of the '{@link #isOverride() <em>Override</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverride()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OVERRIDE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOverride() <em>Override</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverride()
	 * @generated
	 * @ordered
	 */
	protected boolean override = OVERRIDE_EDEFAULT;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getUnit() <em>Unit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
	protected AUnit unit;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnitValuePropertyInstanceImpl() {
		super();
		this.uuid = new de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid(); 
	}
	
	
	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * This method handles unresolved references into otehr resources.
	 * When ever a proxy object in the DVLM model is resolved
	 * This method will check the object and place an error to the resource
	 * if it cannot be resolved 
	 * @generated
	 */
	@Override
	public EObject eResolveProxy(InternalEObject proxy) {
		EObject resolvedProxy = super.eResolveProxy(proxy);
		return DVLMUnresolvedReferenceException.checkProxyObject(resolvedProxy, proxy, this.eResource());
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PropertyinstancesPackage.Literals.UNIT_VALUE_PROPERTY_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code boolean}'.
	 * @generated
	 */
	public boolean isOverride() {
		return override;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverride(boolean newOverride) {
		boolean oldOverride = override;
		override = newOverride;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__OVERRIDE, oldOverride, override));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code String}'.
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code AUnit}'.
	 * @generated
	 */
	public AUnit getUnit() {
		if (unit != null && unit.eIsProxy()) {
			InternalEObject oldUnit = (InternalEObject)unit;
			unit = (AUnit)eResolveProxy(oldUnit);
			if (unit != oldUnit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__UNIT, oldUnit, unit));
			}
		}
		return unit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code AUnit}'.
	 * @generated
	 */
	public AUnit basicGetUnit() {
		return unit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnit(AUnit newUnit) {
		AUnit oldUnit = unit;
		unit = newUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__UNIT, oldUnit, unit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getValueToBaseUnit() {
		double value = Double.parseDouble(getValue());
		AUnit unit = getUnit();
		if (unit != null) {
			value = de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper.getInstance().convertFromSourceUnitToBaseUnit(unit, value);
		}
				
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueAsBaseUnit(final double value) {
		AUnit unit = getUnit();
		if (unit != null) {
			double targetValue = de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper.getInstance().convertFromBaseUnitToTargetUnit(unit, value);
			String textualValue = Double.toString(targetValue);
			setValue(textualValue);
		} else {
			setValue(Double.toString(value));
		}
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__OVERRIDE:
				return isOverride();
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__VALUE:
				return getValue();
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__UNIT:
				if (resolve) return getUnit();
				return basicGetUnit();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__OVERRIDE:
				setOverride((Boolean)newValue);
				return;
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__VALUE:
				setValue((String)newValue);
				return;
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__UNIT:
				setUnit((AUnit)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__OVERRIDE:
				setOverride(OVERRIDE_EDEFAULT);
				return;
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__UNIT:
				setUnit((AUnit)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__OVERRIDE:
				return override != OVERRIDE_EDEFAULT;
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__UNIT:
				return unit != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == IEquationInput.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == IOverridableInheritanceLink.class) {
			switch (derivedFeatureID) {
				case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__OVERRIDE: return InheritancePackage.IOVERRIDABLE_INHERITANCE_LINK__OVERRIDE;
				default: return -1;
			}
		}
		if (baseClass == ValuePropertyInstance.class) {
			switch (derivedFeatureID) {
				case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__VALUE: return PropertyinstancesPackage.VALUE_PROPERTY_INSTANCE__VALUE;
				default: return -1;
			}
		}
		if (baseClass == IUnitPropertyInstance.class) {
			switch (derivedFeatureID) {
				case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__UNIT: return PropertyinstancesPackage.IUNIT_PROPERTY_INSTANCE__UNIT;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == IEquationInput.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == IOverridableInheritanceLink.class) {
			switch (baseFeatureID) {
				case InheritancePackage.IOVERRIDABLE_INHERITANCE_LINK__OVERRIDE: return PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__OVERRIDE;
				default: return -1;
			}
		}
		if (baseClass == ValuePropertyInstance.class) {
			switch (baseFeatureID) {
				case PropertyinstancesPackage.VALUE_PROPERTY_INSTANCE__VALUE: return PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__VALUE;
				default: return -1;
			}
		}
		if (baseClass == IUnitPropertyInstance.class) {
			switch (baseFeatureID) {
				case PropertyinstancesPackage.IUNIT_PROPERTY_INSTANCE__UNIT: return PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE__UNIT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE___GET_VALUE_TO_BASE_UNIT:
				return getValueToBaseUnit();
			case PropertyinstancesPackage.UNIT_VALUE_PROPERTY_INSTANCE___SET_VALUE_AS_BASE_UNIT__DOUBLE:
				setValueAsBaseUnit((Double)arguments.get(0));
				return null;
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (override: ");
		result.append(override);
		result.append(", value: ");
		result.append(value);
		result.append(')');
		return result.toString();
	}

} //UnitValuePropertyInstanceImpl
