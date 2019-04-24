/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural.impl;


import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IDescription;
import de.dlr.sc.virsat.model.dvlm.general.IName;

import de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation;
import de.dlr.sc.virsat.model.dvlm.structural.RelationInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;

import de.dlr.sc.virsat.model.dvlm.types.TypesFactory;
import de.dlr.sc.virsat.model.dvlm.types.TypesPackage;

import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relation Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.RelationInstanceImpl#getUuid <em>Uuid</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.RelationInstanceImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.RelationInstanceImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.RelationInstanceImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.RelationInstanceImpl#getReferences <em>References</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelationInstanceImpl extends MinimalEObjectImpl.Container implements RelationInstance {
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
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected GeneralRelation type;

	/**
	 * The cached value of the '{@link #getReferences() <em>References</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferences()
	 * @generated
	 * @ordered
	 */
	protected StructuralElementInstance references;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationInstanceImpl() {
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
		return StructuralPackage.Literals.RELATION_INSTANCE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.RELATION_INSTANCE__UUID, oldUuid, uuid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.RELATION_INSTANCE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.RELATION_INSTANCE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralRelation getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (GeneralRelation)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StructuralPackage.RELATION_INSTANCE__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralRelation basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(GeneralRelation newType) {
		GeneralRelation oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.RELATION_INSTANCE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralElementInstance getReferences() {
		if (references != null && references.eIsProxy()) {
			InternalEObject oldReferences = (InternalEObject)references;
			references = (StructuralElementInstance)eResolveProxy(oldReferences);
			if (references != oldReferences) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StructuralPackage.RELATION_INSTANCE__REFERENCES, oldReferences, references));
			}
		}
		return references;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralElementInstance basicGetReferences() {
		return references;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferences(StructuralElementInstance newReferences) {
		StructuralElementInstance oldReferences = references;
		references = newReferences;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.RELATION_INSTANCE__REFERENCES, oldReferences, references));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StructuralPackage.RELATION_INSTANCE__UUID:
				return getUuid();
			case StructuralPackage.RELATION_INSTANCE__DESCRIPTION:
				return getDescription();
			case StructuralPackage.RELATION_INSTANCE__NAME:
				return getName();
			case StructuralPackage.RELATION_INSTANCE__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case StructuralPackage.RELATION_INSTANCE__REFERENCES:
				if (resolve) return getReferences();
				return basicGetReferences();
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
			case StructuralPackage.RELATION_INSTANCE__UUID:
				setUuid((VirSatUuid)newValue);
				return;
			case StructuralPackage.RELATION_INSTANCE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case StructuralPackage.RELATION_INSTANCE__NAME:
				setName((String)newValue);
				return;
			case StructuralPackage.RELATION_INSTANCE__TYPE:
				setType((GeneralRelation)newValue);
				return;
			case StructuralPackage.RELATION_INSTANCE__REFERENCES:
				setReferences((StructuralElementInstance)newValue);
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
			case StructuralPackage.RELATION_INSTANCE__UUID:
				setUuid(UUID_EDEFAULT);
				return;
			case StructuralPackage.RELATION_INSTANCE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case StructuralPackage.RELATION_INSTANCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case StructuralPackage.RELATION_INSTANCE__TYPE:
				setType((GeneralRelation)null);
				return;
			case StructuralPackage.RELATION_INSTANCE__REFERENCES:
				setReferences((StructuralElementInstance)null);
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
			case StructuralPackage.RELATION_INSTANCE__UUID:
				return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
			case StructuralPackage.RELATION_INSTANCE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case StructuralPackage.RELATION_INSTANCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case StructuralPackage.RELATION_INSTANCE__TYPE:
				return type != null;
			case StructuralPackage.RELATION_INSTANCE__REFERENCES:
				return references != null;
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
		if (baseClass == IDescription.class) {
			switch (derivedFeatureID) {
				case StructuralPackage.RELATION_INSTANCE__DESCRIPTION: return GeneralPackage.IDESCRIPTION__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IName.class) {
			switch (derivedFeatureID) {
				case StructuralPackage.RELATION_INSTANCE__NAME: return GeneralPackage.INAME__NAME;
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
		if (baseClass == IDescription.class) {
			switch (baseFeatureID) {
				case GeneralPackage.IDESCRIPTION__DESCRIPTION: return StructuralPackage.RELATION_INSTANCE__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IName.class) {
			switch (baseFeatureID) {
				case GeneralPackage.INAME__NAME: return StructuralPackage.RELATION_INSTANCE__NAME;
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
		result.append(" (uuid: ");
		result.append(uuid);
		result.append(", description: ");
		result.append(description);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //RelationInstanceImpl
