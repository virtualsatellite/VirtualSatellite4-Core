/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.roles.impl;


import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.roles.RolesPackage;

import de.dlr.sc.virsat.model.dvlm.types.TypesFactory;
import de.dlr.sc.virsat.model.dvlm.types.TypesPackage;

import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;
import java.lang.reflect.InvocationTargetException;

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
 * An implementation of the model object '<em><b>Role Management</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.roles.impl.RoleManagementImpl#getAssignedDiscipline <em>Assigned Discipline</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.roles.impl.RoleManagementImpl#getUuid <em>Uuid</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.roles.impl.RoleManagementImpl#getDisciplines <em>Disciplines</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RoleManagementImpl extends MinimalEObjectImpl.Container implements RoleManagement {
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
	 * The cached value of the '{@link #getDisciplines() <em>Disciplines</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisciplines()
	 * @generated
	 * @ordered
	 */
	protected EList<Discipline> disciplines;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoleManagementImpl() {
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
		return RolesPackage.Literals.ROLE_MANAGEMENT;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RolesPackage.ROLE_MANAGEMENT__ASSIGNED_DISCIPLINE, oldAssignedDiscipline, assignedDiscipline));
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
			eNotify(new ENotificationImpl(this, Notification.SET, RolesPackage.ROLE_MANAGEMENT__ASSIGNED_DISCIPLINE, oldAssignedDiscipline, assignedDiscipline));
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
			eNotify(new ENotificationImpl(this, Notification.SET, RolesPackage.ROLE_MANAGEMENT__UUID, oldUuid, uuid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<Discipline>}'.
	 * @generated
	 */
	public EList<Discipline> getDisciplines() {
		if (disciplines == null) {
			disciplines = new EObjectContainmentEList.Resolving<Discipline>(Discipline.class, this, RolesPackage.ROLE_MANAGEMENT__DISCIPLINES);
		 
		
		}
		return disciplines;
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
			case RolesPackage.ROLE_MANAGEMENT__DISCIPLINES:
				return ((InternalEList<?>)getDisciplines()).basicRemove(otherEnd, msgs);
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
			case RolesPackage.ROLE_MANAGEMENT__ASSIGNED_DISCIPLINE:
				if (resolve) return getAssignedDiscipline();
				return basicGetAssignedDiscipline();
			case RolesPackage.ROLE_MANAGEMENT__UUID:
				return getUuid();
			case RolesPackage.ROLE_MANAGEMENT__DISCIPLINES:
				return getDisciplines();
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
			case RolesPackage.ROLE_MANAGEMENT__ASSIGNED_DISCIPLINE:
				setAssignedDiscipline((Discipline)newValue);
				return;
			case RolesPackage.ROLE_MANAGEMENT__UUID:
				setUuid((VirSatUuid)newValue);
				return;
			case RolesPackage.ROLE_MANAGEMENT__DISCIPLINES:
				getDisciplines().clear();
				getDisciplines().addAll((Collection<? extends Discipline>)newValue);
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
			case RolesPackage.ROLE_MANAGEMENT__ASSIGNED_DISCIPLINE:
				setAssignedDiscipline((Discipline)null);
				return;
			case RolesPackage.ROLE_MANAGEMENT__UUID:
				setUuid(UUID_EDEFAULT);
				return;
			case RolesPackage.ROLE_MANAGEMENT__DISCIPLINES:
				getDisciplines().clear();
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
			case RolesPackage.ROLE_MANAGEMENT__ASSIGNED_DISCIPLINE:
				return assignedDiscipline != null;
			case RolesPackage.ROLE_MANAGEMENT__UUID:
				return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
			case RolesPackage.ROLE_MANAGEMENT__DISCIPLINES:
				return disciplines != null && !disciplines.isEmpty();
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
				case RolesPackage.ROLE_MANAGEMENT__UUID: return GeneralPackage.IUUID__UUID;
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
				case GeneralPackage.IUUID__UUID: return RolesPackage.ROLE_MANAGEMENT__UUID;
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
			case RolesPackage.ROLE_MANAGEMENT___GET_CONTAINED_IASSIGNED_DISCIPLINES:
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

} //RoleManagementImpl
