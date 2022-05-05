/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.units.impl;


import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;

import de.dlr.sc.virsat.model.dvlm.roles.Discipline;

import de.dlr.sc.virsat.model.dvlm.types.TypesFactory;
import de.dlr.sc.virsat.model.dvlm.types.TypesPackage;

import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsPackage;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unit Management</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.units.impl.UnitManagementImpl#getAssignedDiscipline <em>Assigned Discipline</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.units.impl.UnitManagementImpl#getUuid <em>Uuid</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.units.impl.UnitManagementImpl#getSystemOfUnit <em>System Of Unit</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UnitManagementImpl extends MinimalEObjectImpl.Container implements UnitManagement {
	/**
	 * The cached value of the '{@link #getAssignedDiscipline() <em>Assigned Discipline</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignedDiscipline()
	 * @generated
	 * @ordered
	 */
	protected Discipline assignedDiscipline;

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
	 * The cached value of the '{@link #getSystemOfUnit() <em>System Of Unit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemOfUnit()
	 * @generated
	 * @ordered
	 */
	protected SystemOfUnits systemOfUnit;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnitManagementImpl() {
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
		return UnitsPackage.Literals.UNIT_MANAGEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code Discipline}'.
	 * @generated
	 */
	public Discipline getAssignedDiscipline() {
		if (assignedDiscipline != null && assignedDiscipline.eIsProxy()) {
			InternalEObject oldAssignedDiscipline = (InternalEObject)assignedDiscipline;
			assignedDiscipline = (Discipline)eResolveProxy(oldAssignedDiscipline);
			if (assignedDiscipline != oldAssignedDiscipline) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UnitsPackage.UNIT_MANAGEMENT__ASSIGNED_DISCIPLINE, oldAssignedDiscipline, assignedDiscipline));
			}
		}
		return assignedDiscipline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code Discipline}'.
	 * @generated
	 */
	public Discipline basicGetAssignedDiscipline() {
		return assignedDiscipline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssignedDiscipline(Discipline newAssignedDiscipline) {
		Discipline oldAssignedDiscipline = assignedDiscipline;
		assignedDiscipline = newAssignedDiscipline;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UnitsPackage.UNIT_MANAGEMENT__ASSIGNED_DISCIPLINE, oldAssignedDiscipline, assignedDiscipline));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UnitsPackage.UNIT_MANAGEMENT__UUID, oldUuid, uuid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code SystemOfUnits}'.
	 * @generated
	 */
	public SystemOfUnits getSystemOfUnit() {
		if (systemOfUnit != null && systemOfUnit.eIsProxy()) {
			InternalEObject oldSystemOfUnit = (InternalEObject)systemOfUnit;
			systemOfUnit = (SystemOfUnits)eResolveProxy(oldSystemOfUnit);
			if (systemOfUnit != oldSystemOfUnit) {
				InternalEObject newSystemOfUnit = (InternalEObject)systemOfUnit;
				NotificationChain msgs = oldSystemOfUnit.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UnitsPackage.UNIT_MANAGEMENT__SYSTEM_OF_UNIT, null, null);
				if (newSystemOfUnit.eInternalContainer() == null) {
					msgs = newSystemOfUnit.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UnitsPackage.UNIT_MANAGEMENT__SYSTEM_OF_UNIT, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UnitsPackage.UNIT_MANAGEMENT__SYSTEM_OF_UNIT, oldSystemOfUnit, systemOfUnit));
			}
		}
		return systemOfUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code SystemOfUnits}'.
	 * @generated
	 */
	public SystemOfUnits basicGetSystemOfUnit() {
		return systemOfUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newSystemOfUnit new value to be of type '{@code SystemOfUnits}' to be set. 
	 * @param msgs notifications of type '{@code NotificationChain}'.
	 * @return notification of type '{@code NotificationChain}'.
	 * @generated
	 */
	public NotificationChain basicSetSystemOfUnit(SystemOfUnits newSystemOfUnit, NotificationChain msgs) {
		SystemOfUnits oldSystemOfUnit = systemOfUnit;
		systemOfUnit = newSystemOfUnit;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UnitsPackage.UNIT_MANAGEMENT__SYSTEM_OF_UNIT, oldSystemOfUnit, newSystemOfUnit);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSystemOfUnit(SystemOfUnits newSystemOfUnit) {
		if (newSystemOfUnit != systemOfUnit) {
			NotificationChain msgs = null;
			if (systemOfUnit != null)
				msgs = ((InternalEObject)systemOfUnit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UnitsPackage.UNIT_MANAGEMENT__SYSTEM_OF_UNIT, null, msgs);
			if (newSystemOfUnit != null)
				msgs = ((InternalEObject)newSystemOfUnit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UnitsPackage.UNIT_MANAGEMENT__SYSTEM_OF_UNIT, null, msgs);
			msgs = basicSetSystemOfUnit(newSystemOfUnit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UnitsPackage.UNIT_MANAGEMENT__SYSTEM_OF_UNIT, newSystemOfUnit, newSystemOfUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IAssignedDiscipline> getContainedIAssignedDisciplines() {
		EList<IAssignedDiscipline> containedIAssignedDiscipline = new org.eclipse.emf.common.util.BasicEList<>();
		org.eclipse.emf.common.util.TreeIterator<Object> iter = org.eclipse.emf.ecore.util.EcoreUtil.getAllContents(this, true);
					
		iter.forEachRemaining((object) -> {
			if (object instanceof IAssignedDiscipline) {
				containedIAssignedDiscipline.add((IAssignedDiscipline) object);
			}
		});
				
		return containedIAssignedDiscipline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UnitsPackage.UNIT_MANAGEMENT__SYSTEM_OF_UNIT:
				return basicSetSystemOfUnit(null, msgs);
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
			case UnitsPackage.UNIT_MANAGEMENT__ASSIGNED_DISCIPLINE:
				if (resolve) return getAssignedDiscipline();
				return basicGetAssignedDiscipline();
			case UnitsPackage.UNIT_MANAGEMENT__UUID:
				return getUuid();
			case UnitsPackage.UNIT_MANAGEMENT__SYSTEM_OF_UNIT:
				if (resolve) return getSystemOfUnit();
				return basicGetSystemOfUnit();
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
			case UnitsPackage.UNIT_MANAGEMENT__ASSIGNED_DISCIPLINE:
				setAssignedDiscipline((Discipline)newValue);
				return;
			case UnitsPackage.UNIT_MANAGEMENT__UUID:
				setUuid((VirSatUuid)newValue);
				return;
			case UnitsPackage.UNIT_MANAGEMENT__SYSTEM_OF_UNIT:
				setSystemOfUnit((SystemOfUnits)newValue);
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
			case UnitsPackage.UNIT_MANAGEMENT__ASSIGNED_DISCIPLINE:
				setAssignedDiscipline((Discipline)null);
				return;
			case UnitsPackage.UNIT_MANAGEMENT__UUID:
				setUuid(UUID_EDEFAULT);
				return;
			case UnitsPackage.UNIT_MANAGEMENT__SYSTEM_OF_UNIT:
				setSystemOfUnit((SystemOfUnits)null);
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
			case UnitsPackage.UNIT_MANAGEMENT__ASSIGNED_DISCIPLINE:
				return assignedDiscipline != null;
			case UnitsPackage.UNIT_MANAGEMENT__UUID:
				return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
			case UnitsPackage.UNIT_MANAGEMENT__SYSTEM_OF_UNIT:
				return systemOfUnit != null;
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
				case UnitsPackage.UNIT_MANAGEMENT__UUID: return GeneralPackage.IUUID__UUID;
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
				case GeneralPackage.IUUID__UUID: return UnitsPackage.UNIT_MANAGEMENT__UUID;
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
			case UnitsPackage.UNIT_MANAGEMENT___GET_CONTAINED_IASSIGNED_DISCIPLINES:
				return getContainedIAssignedDisciplines();
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
		result.append(" (uuid: ");
		result.append(uuid);
		result.append(')');
		return result.toString();
	}

} //UnitManagementImpl
