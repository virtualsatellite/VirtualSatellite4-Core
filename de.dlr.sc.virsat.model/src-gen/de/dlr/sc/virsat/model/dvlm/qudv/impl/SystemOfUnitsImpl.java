/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.qudv.impl;


import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;

import de.dlr.sc.virsat.model.dvlm.types.TypesFactory;
import de.dlr.sc.virsat.model.dvlm.types.TypesPackage;

import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Of Units</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfUnitsImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfUnitsImpl#getUuid <em>Uuid</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfUnitsImpl#getSymbol <em>Symbol</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfUnitsImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfUnitsImpl#getDefinitionURI <em>Definition URI</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfUnitsImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfUnitsImpl#getUnit <em>Unit</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfUnitsImpl#getBaseUnit <em>Base Unit</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfUnitsImpl#getSystemOfQuantities <em>System Of Quantities</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SystemOfUnitsImpl extends MinimalEObjectImpl.Container implements SystemOfUnits {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getUuid() <em>Uuid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUuid()
	 * @generated
	 * @ordered
	 */
	protected static final VirSatUuid UUID_EDEFAULT = (VirSatUuid)TypesFactory.eINSTANCE.createFromString(TypesPackage.eINSTANCE.getUuid(), "");

	/**
	 * The cached value of the '{@link #getUuid() <em>Uuid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUuid()
	 * @generated
	 * @ordered
	 */
	protected VirSatUuid uuid = UUID_EDEFAULT;

	/**
	 * The default value of the '{@link #getSymbol() <em>Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSymbol()
	 * @generated
	 * @ordered
	 */
	protected static final String SYMBOL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSymbol() <em>Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSymbol()
	 * @generated
	 * @ordered
	 */
	protected String symbol = SYMBOL_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefinitionURI() <em>Definition URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinitionURI()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFINITION_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefinitionURI() <em>Definition URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinitionURI()
	 * @generated
	 * @ordered
	 */
	protected String definitionURI = DEFINITION_URI_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPrefix() <em>Prefix</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected EList<Prefix> prefix;

	/**
	 * The cached value of the '{@link #getUnit() <em>Unit</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
	protected EList<AUnit> unit;

	/**
	 * The cached value of the '{@link #getBaseUnit() <em>Base Unit</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseUnit()
	 * @generated
	 * @ordered
	 */
	protected EList<AUnit> baseUnit;

	/**
	 * The cached value of the '{@link #getSystemOfQuantities() <em>System Of Quantities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemOfQuantities()
	 * @generated
	 * @ordered
	 */
	protected EList<SystemOfQuantities> systemOfQuantities;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemOfUnitsImpl() {
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
		return QudvPackage.Literals.SYSTEM_OF_UNITS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code String}'.
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QudvPackage.SYSTEM_OF_UNITS__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code VirSatUuid}'.
	 * @generated
	 */
	public VirSatUuid getUuid() {
		return uuid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUuid(VirSatUuid newUuid) {
		VirSatUuid oldUuid = uuid;
		uuid = newUuid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QudvPackage.SYSTEM_OF_UNITS__UUID, oldUuid, uuid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code String}'.
	 * @generated
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSymbol(String newSymbol) {
		String oldSymbol = symbol;
		symbol = newSymbol;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QudvPackage.SYSTEM_OF_UNITS__SYMBOL, oldSymbol, symbol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code String}'.
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QudvPackage.SYSTEM_OF_UNITS__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code String}'.
	 * @generated
	 */
	public String getDefinitionURI() {
		return definitionURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefinitionURI(String newDefinitionURI) {
		String oldDefinitionURI = definitionURI;
		definitionURI = newDefinitionURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QudvPackage.SYSTEM_OF_UNITS__DEFINITION_URI, oldDefinitionURI, definitionURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<Prefix>}'.
	 * @generated
	 */
	public EList<Prefix> getPrefix() {
		if (prefix == null) {
			prefix = new EObjectContainmentEList.Resolving<Prefix>(Prefix.class, this, QudvPackage.SYSTEM_OF_UNITS__PREFIX);
		 
		
		}
		return prefix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<AUnit>}'.
	 * @generated
	 */
	public EList<AUnit> getUnit() {
		if (unit == null) {
			unit = new EObjectContainmentEList.Resolving<AUnit>(AUnit.class, this, QudvPackage.SYSTEM_OF_UNITS__UNIT);
		 
		
		}
		return unit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<AUnit>}'.
	 * @generated
	 */
	public EList<AUnit> getBaseUnit() {
		if (baseUnit == null) {
			baseUnit = new EObjectContainmentEList.Resolving<AUnit>(AUnit.class, this, QudvPackage.SYSTEM_OF_UNITS__BASE_UNIT);
		 
		
		}
		return baseUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<SystemOfQuantities>}'.
	 * @generated
	 */
	public EList<SystemOfQuantities> getSystemOfQuantities() {
		if (systemOfQuantities == null) {
			systemOfQuantities = new EObjectContainmentEList.Resolving<SystemOfQuantities>(SystemOfQuantities.class, this, QudvPackage.SYSTEM_OF_UNITS__SYSTEM_OF_QUANTITIES);
		 
		
		}
		return systemOfQuantities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QudvPackage.SYSTEM_OF_UNITS__PREFIX:
				return ((InternalEList<?>)getPrefix()).basicRemove(otherEnd, msgs);
			case QudvPackage.SYSTEM_OF_UNITS__UNIT:
				return ((InternalEList<?>)getUnit()).basicRemove(otherEnd, msgs);
			case QudvPackage.SYSTEM_OF_UNITS__BASE_UNIT:
				return ((InternalEList<?>)getBaseUnit()).basicRemove(otherEnd, msgs);
			case QudvPackage.SYSTEM_OF_UNITS__SYSTEM_OF_QUANTITIES:
				return ((InternalEList<?>)getSystemOfQuantities()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QudvPackage.SYSTEM_OF_UNITS__NAME:
				return getName();
			case QudvPackage.SYSTEM_OF_UNITS__UUID:
				return getUuid();
			case QudvPackage.SYSTEM_OF_UNITS__SYMBOL:
				return getSymbol();
			case QudvPackage.SYSTEM_OF_UNITS__DESCRIPTION:
				return getDescription();
			case QudvPackage.SYSTEM_OF_UNITS__DEFINITION_URI:
				return getDefinitionURI();
			case QudvPackage.SYSTEM_OF_UNITS__PREFIX:
				return getPrefix();
			case QudvPackage.SYSTEM_OF_UNITS__UNIT:
				return getUnit();
			case QudvPackage.SYSTEM_OF_UNITS__BASE_UNIT:
				return getBaseUnit();
			case QudvPackage.SYSTEM_OF_UNITS__SYSTEM_OF_QUANTITIES:
				return getSystemOfQuantities();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case QudvPackage.SYSTEM_OF_UNITS__NAME:
				setName((String)newValue);
				return;
			case QudvPackage.SYSTEM_OF_UNITS__UUID:
				setUuid((VirSatUuid)newValue);
				return;
			case QudvPackage.SYSTEM_OF_UNITS__SYMBOL:
				setSymbol((String)newValue);
				return;
			case QudvPackage.SYSTEM_OF_UNITS__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case QudvPackage.SYSTEM_OF_UNITS__DEFINITION_URI:
				setDefinitionURI((String)newValue);
				return;
			case QudvPackage.SYSTEM_OF_UNITS__PREFIX:
				getPrefix().clear();
				getPrefix().addAll((Collection<? extends Prefix>)newValue);
				return;
			case QudvPackage.SYSTEM_OF_UNITS__UNIT:
				getUnit().clear();
				getUnit().addAll((Collection<? extends AUnit>)newValue);
				return;
			case QudvPackage.SYSTEM_OF_UNITS__BASE_UNIT:
				getBaseUnit().clear();
				getBaseUnit().addAll((Collection<? extends AUnit>)newValue);
				return;
			case QudvPackage.SYSTEM_OF_UNITS__SYSTEM_OF_QUANTITIES:
				getSystemOfQuantities().clear();
				getSystemOfQuantities().addAll((Collection<? extends SystemOfQuantities>)newValue);
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
			case QudvPackage.SYSTEM_OF_UNITS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case QudvPackage.SYSTEM_OF_UNITS__UUID:
				setUuid(UUID_EDEFAULT);
				return;
			case QudvPackage.SYSTEM_OF_UNITS__SYMBOL:
				setSymbol(SYMBOL_EDEFAULT);
				return;
			case QudvPackage.SYSTEM_OF_UNITS__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case QudvPackage.SYSTEM_OF_UNITS__DEFINITION_URI:
				setDefinitionURI(DEFINITION_URI_EDEFAULT);
				return;
			case QudvPackage.SYSTEM_OF_UNITS__PREFIX:
				getPrefix().clear();
				return;
			case QudvPackage.SYSTEM_OF_UNITS__UNIT:
				getUnit().clear();
				return;
			case QudvPackage.SYSTEM_OF_UNITS__BASE_UNIT:
				getBaseUnit().clear();
				return;
			case QudvPackage.SYSTEM_OF_UNITS__SYSTEM_OF_QUANTITIES:
				getSystemOfQuantities().clear();
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
			case QudvPackage.SYSTEM_OF_UNITS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case QudvPackage.SYSTEM_OF_UNITS__UUID:
				return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
			case QudvPackage.SYSTEM_OF_UNITS__SYMBOL:
				return SYMBOL_EDEFAULT == null ? symbol != null : !SYMBOL_EDEFAULT.equals(symbol);
			case QudvPackage.SYSTEM_OF_UNITS__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case QudvPackage.SYSTEM_OF_UNITS__DEFINITION_URI:
				return DEFINITION_URI_EDEFAULT == null ? definitionURI != null : !DEFINITION_URI_EDEFAULT.equals(definitionURI);
			case QudvPackage.SYSTEM_OF_UNITS__PREFIX:
				return prefix != null && !prefix.isEmpty();
			case QudvPackage.SYSTEM_OF_UNITS__UNIT:
				return unit != null && !unit.isEmpty();
			case QudvPackage.SYSTEM_OF_UNITS__BASE_UNIT:
				return baseUnit != null && !baseUnit.isEmpty();
			case QudvPackage.SYSTEM_OF_UNITS__SYSTEM_OF_QUANTITIES:
				return systemOfQuantities != null && !systemOfQuantities.isEmpty();
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
		if (baseClass == IUuid.class) {
			switch (derivedFeatureID) {
				case QudvPackage.SYSTEM_OF_UNITS__UUID: return GeneralPackage.IUUID__UUID;
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
		if (baseClass == IUuid.class) {
			switch (baseFeatureID) {
				case GeneralPackage.IUUID__UUID: return QudvPackage.SYSTEM_OF_UNITS__UUID;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", uuid: ");
		result.append(uuid);
		result.append(", symbol: ");
		result.append(symbol);
		result.append(", description: ");
		result.append(description);
		result.append(", definitionURI: ");
		result.append(definitionURI);
		result.append(')');
		return result.toString();
	}

} //SystemOfUnitsImpl
