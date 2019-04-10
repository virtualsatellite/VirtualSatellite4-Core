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

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;

import de.dlr.sc.virsat.model.dvlm.inheritance.IOverridableInheritanceLink;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;

import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Unit Property Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.EnumUnitPropertyInstanceImpl#isOverride <em>Override</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.EnumUnitPropertyInstanceImpl#getUnit <em>Unit</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.EnumUnitPropertyInstanceImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnumUnitPropertyInstanceImpl extends APropertyInstanceImpl implements EnumUnitPropertyInstance {
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
	 * The cached value of the '{@link #getUnit() <em>Unit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
	protected AUnit unit;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected EnumValueDefinition value;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumUnitPropertyInstanceImpl() {
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
		return PropertyinstancesPackage.Literals.ENUM_UNIT_PROPERTY_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
			eNotify(new ENotificationImpl(this, Notification.SET, PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__OVERRIDE, oldOverride, override));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AUnit getUnit() {
		if (unit != null && unit.eIsProxy()) {
			InternalEObject oldUnit = (InternalEObject)unit;
			unit = (AUnit)eResolveProxy(oldUnit);
			if (unit != oldUnit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__UNIT, oldUnit, unit));
			}
		}
		return unit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
			eNotify(new ENotificationImpl(this, Notification.SET, PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__UNIT, oldUnit, unit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumValueDefinition getValue() {
		if (value != null && value.eIsProxy()) {
			InternalEObject oldValue = (InternalEObject)value;
			value = (EnumValueDefinition)eResolveProxy(oldValue);
			if (value != oldValue) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__VALUE, oldValue, value));
			}
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumValueDefinition basicGetValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(EnumValueDefinition newValue) {
		EnumValueDefinition oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__OVERRIDE:
				return isOverride();
			case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__UNIT:
				if (resolve) return getUnit();
				return basicGetUnit();
			case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__VALUE:
				if (resolve) return getValue();
				return basicGetValue();
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
			case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__OVERRIDE:
				setOverride((Boolean)newValue);
				return;
			case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__UNIT:
				setUnit((AUnit)newValue);
				return;
			case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__VALUE:
				setValue((EnumValueDefinition)newValue);
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
			case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__OVERRIDE:
				setOverride(OVERRIDE_EDEFAULT);
				return;
			case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__UNIT:
				setUnit((AUnit)null);
				return;
			case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__VALUE:
				setValue((EnumValueDefinition)null);
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
			case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__OVERRIDE:
				return override != OVERRIDE_EDEFAULT;
			case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__UNIT:
				return unit != null;
			case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__VALUE:
				return value != null;
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
				case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__OVERRIDE: return InheritancePackage.IOVERRIDABLE_INHERITANCE_LINK__OVERRIDE;
				default: return -1;
			}
		}
		if (baseClass == IUnitPropertyInstance.class) {
			switch (derivedFeatureID) {
				case PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__UNIT: return PropertyinstancesPackage.IUNIT_PROPERTY_INSTANCE__UNIT;
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
				case InheritancePackage.IOVERRIDABLE_INHERITANCE_LINK__OVERRIDE: return PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__OVERRIDE;
				default: return -1;
			}
		}
		if (baseClass == IUnitPropertyInstance.class) {
			switch (baseFeatureID) {
				case PropertyinstancesPackage.IUNIT_PROPERTY_INSTANCE__UNIT: return PropertyinstancesPackage.ENUM_UNIT_PROPERTY_INSTANCE__UNIT;
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
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (override: ");
		result.append(override);
		result.append(')');
		return result.toString();
	}

} //EnumUnitPropertyInstanceImpl
