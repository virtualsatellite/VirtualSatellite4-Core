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


import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.model.dvlm.general.IDescription;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.general.IName;

import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritsFrom;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;

import de.dlr.sc.virsat.model.dvlm.list.DVLMFilteredContainingResolvingEList;
import de.dlr.sc.virsat.model.dvlm.list.DVLMFilteredContainingWithInverseResolvingEList;
import de.dlr.sc.virsat.model.dvlm.list.DVLMFilteredResolvingEList;

import de.dlr.sc.virsat.model.dvlm.roles.Discipline;

import de.dlr.sc.virsat.model.dvlm.structural.RelationInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;

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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementInstanceImpl#getUuid <em>Uuid</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementInstanceImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementInstanceImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementInstanceImpl#getAssignedDiscipline <em>Assigned Discipline</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementInstanceImpl#getCategoryAssignments <em>Category Assignments</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementInstanceImpl#getSuperSeis <em>Super Seis</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementInstanceImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementInstanceImpl#getRelationInstances <em>Relation Instances</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementInstanceImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementInstanceImpl#getParent <em>Parent</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StructuralElementInstanceImpl extends MinimalEObjectImpl.Container implements StructuralElementInstance {
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
	 * The cached value of the '{@link #getAssignedDiscipline() <em>Assigned Discipline</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignedDiscipline()
	 * @generated
	 * @ordered
	 */
	protected Discipline assignedDiscipline;

	/**
	 * The cached value of the '{@link #getCategoryAssignments() <em>Category Assignments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategoryAssignments()
	 * @generated
	 * @ordered
	 */
	protected EList<CategoryAssignment> categoryAssignments;

	/**
	 * The cached value of the '{@link #getSuperSeis() <em>Super Seis</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperSeis()
	 * @generated
	 * @ordered
	 */
	protected EList<StructuralElementInstance> superSeis;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected StructuralElement type;

	/**
	 * The cached value of the '{@link #getRelationInstances() <em>Relation Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelationInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<RelationInstance> relationInstances;

	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<StructuralElementInstance> children;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StructuralElementInstanceImpl() {
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
		return StructuralPackage.Literals.STRUCTURAL_ELEMENT_INSTANCE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__UUID, oldUuid, uuid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__DESCRIPTION, oldDescription, description));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__NAME, oldName, name));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__ASSIGNED_DISCIPLINE, oldAssignedDiscipline, assignedDiscipline));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__ASSIGNED_DISCIPLINE, oldAssignedDiscipline, assignedDiscipline));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<CategoryAssignment>}'.
	 * @generated
	 */
	public EList<CategoryAssignment> getCategoryAssignments() {
		if (categoryAssignments == null) {
       		categoryAssignments = new DVLMFilteredContainingResolvingEList<CategoryAssignment>(CategoryAssignment.class, this, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CATEGORY_ASSIGNMENTS);
       	 
		
		}
		return categoryAssignments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<StructuralElementInstance>}'.
	 * @generated
	 */
	public EList<StructuralElementInstance> getSuperSeis() {
		if (superSeis == null) {
       		superSeis = new DVLMFilteredResolvingEList<StructuralElementInstance>(StructuralElementInstance.class, this, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__SUPER_SEIS);
       	 
		
		}
		return superSeis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code StructuralElement}'.
	 * @generated
	 */
	public StructuralElement getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (StructuralElement)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code StructuralElement}'.
	 * @generated
	 */
	public StructuralElement basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(StructuralElement newType) {
		StructuralElement oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<RelationInstance>}'.
	 * @generated
	 */
	public EList<RelationInstance> getRelationInstances() {
		if (relationInstances == null) {
       		relationInstances = new DVLMFilteredContainingResolvingEList<RelationInstance>(RelationInstance.class, this, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__RELATION_INSTANCES);
       	 
		
		}
		return relationInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<StructuralElementInstance>}'.
	 * @generated
	 */
	public EList<StructuralElementInstance> getChildren() {
		if (children == null) {
       		children = new DVLMFilteredContainingWithInverseResolvingEList<StructuralElementInstance>(StructuralElementInstance.class, this, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__PARENT);
       	 
		
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code StructuralElementInstance}'.
	 * @generated
	 */
	public StructuralElementInstance getParent() {
		if (eContainerFeatureID() != StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__PARENT) return null;
		return (StructuralElementInstance)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code StructuralElementInstance}'.
	 * @generated
	 */
	public StructuralElementInstance basicGetParent() {
		if (eContainerFeatureID() != StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__PARENT) return null;
		return (StructuralElementInstance)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newParent new value to be of type '{@code StructuralElementInstance}' to be set. 
	 * @param msgs notifications of type '{@code NotificationChain}'.
	 * @return notification of type '{@code NotificationChain}'.
	 * @generated
	 */
	public NotificationChain basicSetParent(StructuralElementInstance newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(StructuralElementInstance newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN, StructuralElementInstance.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__PARENT, newParent, newParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StructuralElementInstance> getDeepChildren() {
		EList<StructuralElementInstance> children = new  org.eclipse.emf.common.util.BasicEList<>();
		for (StructuralElementInstance structuralElementInstance : getChildren()) {
			children.add(structuralElementInstance);
			EList<StructuralElementInstance> nestedChildren = structuralElementInstance.getDeepChildren();
			children.addAll(nestedChildren);
		}
		
		return children;
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((StructuralElementInstance)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CATEGORY_ASSIGNMENTS:
				return ((InternalEList<?>)getCategoryAssignments()).basicRemove(otherEnd, msgs);
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__RELATION_INSTANCES:
				return ((InternalEList<?>)getRelationInstances()).basicRemove(otherEnd, msgs);
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__PARENT:
				return basicSetParent(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__PARENT:
				return eInternalContainer().eInverseRemove(this, StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN, StructuralElementInstance.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__UUID:
				return getUuid();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__DESCRIPTION:
				return getDescription();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__NAME:
				return getName();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__ASSIGNED_DISCIPLINE:
				if (resolve) return getAssignedDiscipline();
				return basicGetAssignedDiscipline();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CATEGORY_ASSIGNMENTS:
				return getCategoryAssignments();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__SUPER_SEIS:
				return getSuperSeis();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__RELATION_INSTANCES:
				return getRelationInstances();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN:
				return getChildren();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__PARENT:
				if (resolve) return getParent();
				return basicGetParent();
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
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__UUID:
				setUuid((VirSatUuid)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__NAME:
				setName((String)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__ASSIGNED_DISCIPLINE:
				setAssignedDiscipline((Discipline)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CATEGORY_ASSIGNMENTS:
				getCategoryAssignments().clear();
				getCategoryAssignments().addAll((Collection<? extends CategoryAssignment>)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__SUPER_SEIS:
				getSuperSeis().clear();
				getSuperSeis().addAll((Collection<? extends StructuralElementInstance>)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__TYPE:
				setType((StructuralElement)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__RELATION_INSTANCES:
				getRelationInstances().clear();
				getRelationInstances().addAll((Collection<? extends RelationInstance>)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends StructuralElementInstance>)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__PARENT:
				setParent((StructuralElementInstance)newValue);
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
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__UUID:
				setUuid(UUID_EDEFAULT);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__ASSIGNED_DISCIPLINE:
				setAssignedDiscipline((Discipline)null);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CATEGORY_ASSIGNMENTS:
				getCategoryAssignments().clear();
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__SUPER_SEIS:
				getSuperSeis().clear();
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__TYPE:
				setType((StructuralElement)null);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__RELATION_INSTANCES:
				getRelationInstances().clear();
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN:
				getChildren().clear();
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__PARENT:
				setParent((StructuralElementInstance)null);
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
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__UUID:
				return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__ASSIGNED_DISCIPLINE:
				return assignedDiscipline != null;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CATEGORY_ASSIGNMENTS:
				return categoryAssignments != null && !categoryAssignments.isEmpty();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__SUPER_SEIS:
				return superSeis != null && !superSeis.isEmpty();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__TYPE:
				return type != null;
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__RELATION_INSTANCES:
				return relationInstances != null && !relationInstances.isEmpty();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN:
				return children != null && !children.isEmpty();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__PARENT:
				return basicGetParent() != null;
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
				case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__DESCRIPTION: return GeneralPackage.IDESCRIPTION__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IName.class) {
			switch (derivedFeatureID) {
				case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__NAME: return GeneralPackage.INAME__NAME;
				default: return -1;
			}
		}
		if (baseClass == IAssignedDiscipline.class) {
			switch (derivedFeatureID) {
				case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__ASSIGNED_DISCIPLINE: return GeneralPackage.IASSIGNED_DISCIPLINE__ASSIGNED_DISCIPLINE;
				default: return -1;
			}
		}
		if (baseClass == ICategoryAssignmentContainer.class) {
			switch (derivedFeatureID) {
				case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CATEGORY_ASSIGNMENTS: return CategoriesPackage.ICATEGORY_ASSIGNMENT_CONTAINER__CATEGORY_ASSIGNMENTS;
				default: return -1;
			}
		}
		if (baseClass == IInstance.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == IInheritsFrom.class) {
			switch (derivedFeatureID) {
				case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__SUPER_SEIS: return InheritancePackage.IINHERITS_FROM__SUPER_SEIS;
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
				case GeneralPackage.IDESCRIPTION__DESCRIPTION: return StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IName.class) {
			switch (baseFeatureID) {
				case GeneralPackage.INAME__NAME: return StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__NAME;
				default: return -1;
			}
		}
		if (baseClass == IAssignedDiscipline.class) {
			switch (baseFeatureID) {
				case GeneralPackage.IASSIGNED_DISCIPLINE__ASSIGNED_DISCIPLINE: return StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__ASSIGNED_DISCIPLINE;
				default: return -1;
			}
		}
		if (baseClass == ICategoryAssignmentContainer.class) {
			switch (baseFeatureID) {
				case CategoriesPackage.ICATEGORY_ASSIGNMENT_CONTAINER__CATEGORY_ASSIGNMENTS: return StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__CATEGORY_ASSIGNMENTS;
				default: return -1;
			}
		}
		if (baseClass == IInstance.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == IInheritsFrom.class) {
			switch (baseFeatureID) {
				case InheritancePackage.IINHERITS_FROM__SUPER_SEIS: return StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE__SUPER_SEIS;
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
		if (baseClass == IDescription.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == IName.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == IAssignedDiscipline.class) {
			switch (baseOperationID) {
				case GeneralPackage.IASSIGNED_DISCIPLINE___GET_CONTAINED_IASSIGNED_DISCIPLINES: return StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE___GET_CONTAINED_IASSIGNED_DISCIPLINES;
				default: return -1;
			}
		}
		if (baseClass == ICategoryAssignmentContainer.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == IInstance.class) {
			switch (baseOperationID) {
				case GeneralPackage.IINSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME: return StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME;
				default: return -1;
			}
		}
		if (baseClass == IInheritsFrom.class) {
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
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE___GET_DEEP_CHILDREN:
				return getDeepChildren();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE___GET_FULL_QUALIFIED_INSTANCE_NAME:
				return getFullQualifiedInstanceName();
			case StructuralPackage.STRUCTURAL_ELEMENT_INSTANCE___GET_CONTAINED_IASSIGNED_DISCIPLINES:
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
		result.append(", description: ");
		result.append(description);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //StructuralElementInstanceImpl
