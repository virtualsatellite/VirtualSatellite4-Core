/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.impl;


import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IComment;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;

import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;

import de.dlr.sc.virsat.model.dvlm.types.TypesFactory;
import de.dlr.sc.virsat.model.dvlm.types.TypesPackage;

import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AType Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.ATypeInstanceImpl#getUuid <em>Uuid</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.ATypeInstanceImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.ATypeInstanceImpl#getSuperTis <em>Super Tis</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.ATypeInstanceImpl#isIsInherited <em>Is Inherited</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.ATypeInstanceImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ATypeInstanceImpl extends MinimalEObjectImpl.Container implements ATypeInstance {
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
	 * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected String comment = COMMENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSuperTis() <em>Super Tis</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperTis()
	 * @generated
	 * @ordered
	 */
	protected EList<IInheritanceLink> superTis;

	/**
	 * The default value of the '{@link #isIsInherited() <em>Is Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInherited()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_INHERITED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsInherited() <em>Is Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInherited()
	 * @generated
	 * @ordered
	 */
	protected boolean isInherited = IS_INHERITED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ATypeDefinition type;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ATypeInstanceImpl() {
		super();
		this.uuid = new de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid(); 
	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CategoriesPackage.Literals.ATYPE_INSTANCE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CategoriesPackage.ATYPE_INSTANCE__UUID, oldUuid, uuid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComment(String newComment) {
		String oldComment = comment;
		comment = newComment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CategoriesPackage.ATYPE_INSTANCE__COMMENT, oldComment, comment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IInheritanceLink> getSuperTis() {
		if (superTis == null) {
			superTis = new EObjectResolvingEList<IInheritanceLink>(IInheritanceLink.class, this, CategoriesPackage.ATYPE_INSTANCE__SUPER_TIS);
		 
		
		}
		return superTis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsInherited() {
		return isInherited;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsInherited(boolean newIsInherited) {
		boolean oldIsInherited = isInherited;
		isInherited = newIsInherited;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CategoriesPackage.ATYPE_INSTANCE__IS_INHERITED, oldIsInherited, isInherited));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATypeDefinition getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (ATypeDefinition)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CategoriesPackage.ATYPE_INSTANCE__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATypeDefinition basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(ATypeDefinition newType) {
		ATypeDefinition oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CategoriesPackage.ATYPE_INSTANCE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ICategoryAssignmentContainer getCategoryAssignmentContainer() {
		return de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper.getContainerFor(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFullQualifiedInstanceName() {
		return de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper.getFullQualifedNameForInstance(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CategoriesPackage.ATYPE_INSTANCE__UUID:
				return getUuid();
			case CategoriesPackage.ATYPE_INSTANCE__COMMENT:
				return getComment();
			case CategoriesPackage.ATYPE_INSTANCE__SUPER_TIS:
				return getSuperTis();
			case CategoriesPackage.ATYPE_INSTANCE__IS_INHERITED:
				return isIsInherited();
			case CategoriesPackage.ATYPE_INSTANCE__TYPE:
				if (resolve) return getType();
				return basicGetType();
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
			case CategoriesPackage.ATYPE_INSTANCE__UUID:
				setUuid((VirSatUuid)newValue);
				return;
			case CategoriesPackage.ATYPE_INSTANCE__COMMENT:
				setComment((String)newValue);
				return;
			case CategoriesPackage.ATYPE_INSTANCE__SUPER_TIS:
				getSuperTis().clear();
				getSuperTis().addAll((Collection<? extends IInheritanceLink>)newValue);
				return;
			case CategoriesPackage.ATYPE_INSTANCE__IS_INHERITED:
				setIsInherited((Boolean)newValue);
				return;
			case CategoriesPackage.ATYPE_INSTANCE__TYPE:
				setType((ATypeDefinition)newValue);
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
			case CategoriesPackage.ATYPE_INSTANCE__UUID:
				setUuid(UUID_EDEFAULT);
				return;
			case CategoriesPackage.ATYPE_INSTANCE__COMMENT:
				setComment(COMMENT_EDEFAULT);
				return;
			case CategoriesPackage.ATYPE_INSTANCE__SUPER_TIS:
				getSuperTis().clear();
				return;
			case CategoriesPackage.ATYPE_INSTANCE__IS_INHERITED:
				setIsInherited(IS_INHERITED_EDEFAULT);
				return;
			case CategoriesPackage.ATYPE_INSTANCE__TYPE:
				setType((ATypeDefinition)null);
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
			case CategoriesPackage.ATYPE_INSTANCE__UUID:
				return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
			case CategoriesPackage.ATYPE_INSTANCE__COMMENT:
				return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
			case CategoriesPackage.ATYPE_INSTANCE__SUPER_TIS:
				return superTis != null && !superTis.isEmpty();
			case CategoriesPackage.ATYPE_INSTANCE__IS_INHERITED:
				return isInherited != IS_INHERITED_EDEFAULT;
			case CategoriesPackage.ATYPE_INSTANCE__TYPE:
				return type != null;
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
		if (baseClass == IComment.class) {
			switch (derivedFeatureID) {
				case CategoriesPackage.ATYPE_INSTANCE__COMMENT: return GeneralPackage.ICOMMENT__COMMENT;
				default: return -1;
			}
		}
		if (baseClass == IInstance.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == IInheritanceLink.class) {
			switch (derivedFeatureID) {
				case CategoriesPackage.ATYPE_INSTANCE__SUPER_TIS: return InheritancePackage.IINHERITANCE_LINK__SUPER_TIS;
				case CategoriesPackage.ATYPE_INSTANCE__IS_INHERITED: return InheritancePackage.IINHERITANCE_LINK__IS_INHERITED;
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
		if (baseClass == IComment.class) {
			switch (baseFeatureID) {
				case GeneralPackage.ICOMMENT__COMMENT: return CategoriesPackage.ATYPE_INSTANCE__COMMENT;
				default: return -1;
			}
		}
		if (baseClass == IInstance.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == IInheritanceLink.class) {
			switch (baseFeatureID) {
				case InheritancePackage.IINHERITANCE_LINK__SUPER_TIS: return CategoriesPackage.ATYPE_INSTANCE__SUPER_TIS;
				case InheritancePackage.IINHERITANCE_LINK__IS_INHERITED: return CategoriesPackage.ATYPE_INSTANCE__IS_INHERITED;
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
		if (baseClass == IComment.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == IInstance.class) {
			switch (baseOperationID) {
				case GeneralPackage.IINSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME: return CategoriesPackage.ATYPE_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME;
				default: return -1;
			}
		}
		if (baseClass == IInheritanceLink.class) {
			switch (baseOperationID) {
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
			case CategoriesPackage.ATYPE_INSTANCE___GET_CATEGORY_ASSIGNMENT_CONTAINER:
				return getCategoryAssignmentContainer();
			case CategoriesPackage.ATYPE_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME:
				return getFullQualifiedInstanceName();
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
		result.append(", comment: ");
		result.append(comment);
		result.append(", isInherited: ");
		result.append(isInherited);
		result.append(')');
		return result.toString();
	}

} //ATypeInstanceImpl
