/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.impl;


import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.Repository;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;

import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

import de.dlr.sc.virsat.model.dvlm.types.TypesFactory;
import de.dlr.sc.virsat.model.dvlm.types.TypesPackage;

import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;

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

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.impl.RepositoryImpl#getUuid <em>Uuid</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.impl.RepositoryImpl#getAssignedDiscipline <em>Assigned Discipline</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.impl.RepositoryImpl#getRootEntities <em>Root Entities</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.impl.RepositoryImpl#getRoleManagement <em>Role Management</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.impl.RepositoryImpl#getUnitManagement <em>Unit Management</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.impl.RepositoryImpl#getActiveConcepts <em>Active Concepts</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.impl.RepositoryImpl#getSuppressedValidators <em>Suppressed Validators</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RepositoryImpl extends MinimalEObjectImpl.Container implements Repository {
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
	 * The cached value of the '{@link #getAssignedDiscipline() <em>Assigned Discipline</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignedDiscipline()
	 * @generated
	 * @ordered
	 */
	protected Discipline assignedDiscipline;

	/**
	 * The cached value of the '{@link #getRootEntities() <em>Root Entities</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRootEntities()
	 * @generated
	 * @ordered
	 */
	protected EList<StructuralElementInstance> rootEntities;

	/**
	 * The cached value of the '{@link #getRoleManagement() <em>Role Management</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleManagement()
	 * @generated
	 * @ordered
	 */
	protected RoleManagement roleManagement;

	/**
	 * The cached value of the '{@link #getUnitManagement() <em>Unit Management</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnitManagement()
	 * @generated
	 * @ordered
	 */
	protected UnitManagement unitManagement;

	/**
	 * The cached value of the '{@link #getActiveConcepts() <em>Active Concepts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveConcepts()
	 * @generated
	 * @ordered
	 */
	protected EList<Concept> activeConcepts;

	/**
	 * The cached value of the '{@link #getSuppressedValidators() <em>Suppressed Validators</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuppressedValidators()
	 * @generated
	 * @ordered
	 */
	protected EList<String> suppressedValidators;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RepositoryImpl() {
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
		return DVLMPackage.Literals.REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
			eNotify(new ENotificationImpl(this, Notification.SET, DVLMPackage.REPOSITORY__UUID, oldUuid, uuid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Discipline getAssignedDiscipline() {
		if (assignedDiscipline != null && assignedDiscipline.eIsProxy()) {
			InternalEObject oldAssignedDiscipline = (InternalEObject)assignedDiscipline;
			assignedDiscipline = (Discipline)eResolveProxy(oldAssignedDiscipline);
			if (assignedDiscipline != oldAssignedDiscipline) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DVLMPackage.REPOSITORY__ASSIGNED_DISCIPLINE, oldAssignedDiscipline, assignedDiscipline));
			}
		}
		return assignedDiscipline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
			eNotify(new ENotificationImpl(this, Notification.SET, DVLMPackage.REPOSITORY__ASSIGNED_DISCIPLINE, oldAssignedDiscipline, assignedDiscipline));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StructuralElementInstance> getRootEntities() {
		if (rootEntities == null) {
			rootEntities = new EObjectResolvingEList<StructuralElementInstance>(StructuralElementInstance.class, this, DVLMPackage.REPOSITORY__ROOT_ENTITIES);
		 
		
		}
		return rootEntities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleManagement getRoleManagement() {
		if (roleManagement != null && roleManagement.eIsProxy()) {
			InternalEObject oldRoleManagement = (InternalEObject)roleManagement;
			roleManagement = (RoleManagement)eResolveProxy(oldRoleManagement);
			if (roleManagement != oldRoleManagement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DVLMPackage.REPOSITORY__ROLE_MANAGEMENT, oldRoleManagement, roleManagement));
			}
		}
		return roleManagement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleManagement basicGetRoleManagement() {
		return roleManagement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoleManagement(RoleManagement newRoleManagement) {
		RoleManagement oldRoleManagement = roleManagement;
		roleManagement = newRoleManagement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DVLMPackage.REPOSITORY__ROLE_MANAGEMENT, oldRoleManagement, roleManagement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnitManagement getUnitManagement() {
		if (unitManagement != null && unitManagement.eIsProxy()) {
			InternalEObject oldUnitManagement = (InternalEObject)unitManagement;
			unitManagement = (UnitManagement)eResolveProxy(oldUnitManagement);
			if (unitManagement != oldUnitManagement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DVLMPackage.REPOSITORY__UNIT_MANAGEMENT, oldUnitManagement, unitManagement));
			}
		}
		return unitManagement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnitManagement basicGetUnitManagement() {
		return unitManagement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnitManagement(UnitManagement newUnitManagement) {
		UnitManagement oldUnitManagement = unitManagement;
		unitManagement = newUnitManagement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DVLMPackage.REPOSITORY__UNIT_MANAGEMENT, oldUnitManagement, unitManagement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Concept> getActiveConcepts() {
		if (activeConcepts == null) {
			activeConcepts = new EObjectContainmentEList.Resolving<Concept>(Concept.class, this, DVLMPackage.REPOSITORY__ACTIVE_CONCEPTS);
		 
		
		}
		return activeConcepts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getSuppressedValidators() {
		if (suppressedValidators == null) {
			suppressedValidators = new EDataTypeUniqueEList<String>(String.class, this, DVLMPackage.REPOSITORY__SUPPRESSED_VALIDATORS);
		 
		
		}
		return suppressedValidators;
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
			case DVLMPackage.REPOSITORY__ACTIVE_CONCEPTS:
				return ((InternalEList<?>)getActiveConcepts()).basicRemove(otherEnd, msgs);
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
			case DVLMPackage.REPOSITORY__UUID:
				return getUuid();
			case DVLMPackage.REPOSITORY__ASSIGNED_DISCIPLINE:
				if (resolve) return getAssignedDiscipline();
				return basicGetAssignedDiscipline();
			case DVLMPackage.REPOSITORY__ROOT_ENTITIES:
				return getRootEntities();
			case DVLMPackage.REPOSITORY__ROLE_MANAGEMENT:
				if (resolve) return getRoleManagement();
				return basicGetRoleManagement();
			case DVLMPackage.REPOSITORY__UNIT_MANAGEMENT:
				if (resolve) return getUnitManagement();
				return basicGetUnitManagement();
			case DVLMPackage.REPOSITORY__ACTIVE_CONCEPTS:
				return getActiveConcepts();
			case DVLMPackage.REPOSITORY__SUPPRESSED_VALIDATORS:
				return getSuppressedValidators();
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
			case DVLMPackage.REPOSITORY__UUID:
				setUuid((VirSatUuid)newValue);
				return;
			case DVLMPackage.REPOSITORY__ASSIGNED_DISCIPLINE:
				setAssignedDiscipline((Discipline)newValue);
				return;
			case DVLMPackage.REPOSITORY__ROOT_ENTITIES:
				getRootEntities().clear();
				getRootEntities().addAll((Collection<? extends StructuralElementInstance>)newValue);
				return;
			case DVLMPackage.REPOSITORY__ROLE_MANAGEMENT:
				setRoleManagement((RoleManagement)newValue);
				return;
			case DVLMPackage.REPOSITORY__UNIT_MANAGEMENT:
				setUnitManagement((UnitManagement)newValue);
				return;
			case DVLMPackage.REPOSITORY__ACTIVE_CONCEPTS:
				getActiveConcepts().clear();
				getActiveConcepts().addAll((Collection<? extends Concept>)newValue);
				return;
			case DVLMPackage.REPOSITORY__SUPPRESSED_VALIDATORS:
				getSuppressedValidators().clear();
				getSuppressedValidators().addAll((Collection<? extends String>)newValue);
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
			case DVLMPackage.REPOSITORY__UUID:
				setUuid(UUID_EDEFAULT);
				return;
			case DVLMPackage.REPOSITORY__ASSIGNED_DISCIPLINE:
				setAssignedDiscipline((Discipline)null);
				return;
			case DVLMPackage.REPOSITORY__ROOT_ENTITIES:
				getRootEntities().clear();
				return;
			case DVLMPackage.REPOSITORY__ROLE_MANAGEMENT:
				setRoleManagement((RoleManagement)null);
				return;
			case DVLMPackage.REPOSITORY__UNIT_MANAGEMENT:
				setUnitManagement((UnitManagement)null);
				return;
			case DVLMPackage.REPOSITORY__ACTIVE_CONCEPTS:
				getActiveConcepts().clear();
				return;
			case DVLMPackage.REPOSITORY__SUPPRESSED_VALIDATORS:
				getSuppressedValidators().clear();
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
			case DVLMPackage.REPOSITORY__UUID:
				return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
			case DVLMPackage.REPOSITORY__ASSIGNED_DISCIPLINE:
				return assignedDiscipline != null;
			case DVLMPackage.REPOSITORY__ROOT_ENTITIES:
				return rootEntities != null && !rootEntities.isEmpty();
			case DVLMPackage.REPOSITORY__ROLE_MANAGEMENT:
				return roleManagement != null;
			case DVLMPackage.REPOSITORY__UNIT_MANAGEMENT:
				return unitManagement != null;
			case DVLMPackage.REPOSITORY__ACTIVE_CONCEPTS:
				return activeConcepts != null && !activeConcepts.isEmpty();
			case DVLMPackage.REPOSITORY__SUPPRESSED_VALIDATORS:
				return suppressedValidators != null && !suppressedValidators.isEmpty();
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
		if (baseClass == IAssignedDiscipline.class) {
			switch (derivedFeatureID) {
				case DVLMPackage.REPOSITORY__ASSIGNED_DISCIPLINE: return GeneralPackage.IASSIGNED_DISCIPLINE__ASSIGNED_DISCIPLINE;
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
		if (baseClass == IAssignedDiscipline.class) {
			switch (baseFeatureID) {
				case GeneralPackage.IASSIGNED_DISCIPLINE__ASSIGNED_DISCIPLINE: return DVLMPackage.REPOSITORY__ASSIGNED_DISCIPLINE;
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
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == IAssignedDiscipline.class) {
			switch (baseOperationID) {
				case GeneralPackage.IASSIGNED_DISCIPLINE___GET_CONTAINED_IASSIGNED_DISCIPLINES: return DVLMPackage.REPOSITORY___GET_CONTAINED_IASSIGNED_DISCIPLINES;
				default: return -1;
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case DVLMPackage.REPOSITORY___GET_CONTAINED_IASSIGNED_DISCIPLINES:
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
		result.append(", suppressedValidators: ");
		result.append(suppressedValidators);
		result.append(')');
		return result.toString();
	}

} //RepositoryImpl
