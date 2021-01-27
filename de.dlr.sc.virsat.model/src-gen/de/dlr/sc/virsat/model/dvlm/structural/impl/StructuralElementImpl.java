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


import de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition;

import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IDescription;

import de.dlr.sc.virsat.model.dvlm.inheritance.ICanInheritFrom;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;

import de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementImpl#getFullQualifiedName <em>Full Qualified Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementImpl#getShortName <em>Short Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementImpl#getApplicableFor <em>Applicable For</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementImpl#isIsApplicableForAll <em>Is Applicable For All</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementImpl#getCardinality <em>Cardinality</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementImpl#getCanInheritFrom <em>Can Inherit From</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementImpl#isIsCanInheritFromAll <em>Is Can Inherit From All</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.impl.StructuralElementImpl#isIsRootStructuralElement <em>Is Root Structural Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StructuralElementImpl extends MinimalEObjectImpl.Container implements StructuralElement {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "de.dlr.sc.model.dvlm.noid";

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
	 * The default value of the '{@link #getFullQualifiedName() <em>Full Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullQualifiedName()
	 * @generated
	 * @ordered
	 */
	protected static final String FULL_QUALIFIED_NAME_EDEFAULT = "de.dlr.sc.model.dvlm.noid";

	/**
	 * The default value of the '{@link #getShortName() <em>Short Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortName()
	 * @generated
	 * @ordered
	 */
	protected static final String SHORT_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getShortName() <em>Short Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortName()
	 * @generated
	 * @ordered
	 */
	protected String shortName = SHORT_NAME_EDEFAULT;

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
	 * The cached value of the '{@link #getApplicableFor() <em>Applicable For</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplicableFor()
	 * @generated
	 * @ordered
	 */
	protected EList<StructuralElement> applicableFor;

	/**
	 * The default value of the '{@link #isIsApplicableForAll() <em>Is Applicable For All</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsApplicableForAll()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_APPLICABLE_FOR_ALL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsApplicableForAll() <em>Is Applicable For All</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsApplicableForAll()
	 * @generated
	 * @ordered
	 */
	protected boolean isApplicableForAll = IS_APPLICABLE_FOR_ALL_EDEFAULT;

	/**
	 * The default value of the '{@link #getCardinality() <em>Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCardinality()
	 * @generated
	 * @ordered
	 */
	protected static final int CARDINALITY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCardinality() <em>Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCardinality()
	 * @generated
	 * @ordered
	 */
	protected int cardinality = CARDINALITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCanInheritFrom() <em>Can Inherit From</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCanInheritFrom()
	 * @generated
	 * @ordered
	 */
	protected EList<StructuralElement> canInheritFrom;

	/**
	 * The default value of the '{@link #isIsCanInheritFromAll() <em>Is Can Inherit From All</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsCanInheritFromAll()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_CAN_INHERIT_FROM_ALL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsCanInheritFromAll() <em>Is Can Inherit From All</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsCanInheritFromAll()
	 * @generated
	 * @ordered
	 */
	protected boolean isCanInheritFromAll = IS_CAN_INHERIT_FROM_ALL_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsRootStructuralElement() <em>Is Root Structural Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRootStructuralElement()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ROOT_STRUCTURAL_ELEMENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsRootStructuralElement() <em>Is Root Structural Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRootStructuralElement()
	 * @generated
	 * @ordered
	 */
	protected boolean isRootStructuralElement = IS_ROOT_STRUCTURAL_ELEMENT_EDEFAULT;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StructuralElementImpl() {
		super();
		
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
		return StructuralPackage.Literals.STRUCTURAL_ELEMENT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.STRUCTURAL_ELEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code String}'.
	 * @generated
	 */
	public String getFullQualifiedName() {
		// *********************************
		//  VirSat Specific Code Generation
		// *********************************
     	return ActiveConceptHelper.getFullQualifiedId(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code String}'.
	 * @generated
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShortName(String newShortName) {
		String oldShortName = shortName;
		shortName = newShortName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.STRUCTURAL_ELEMENT__SHORT_NAME, oldShortName, shortName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.STRUCTURAL_ELEMENT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<StructuralElement>}'.
	 * @generated
	 */
	public EList<StructuralElement> getApplicableFor() {
		if (applicableFor == null) {
			applicableFor = new EObjectResolvingEList<StructuralElement>(StructuralElement.class, this, StructuralPackage.STRUCTURAL_ELEMENT__APPLICABLE_FOR);
		 
		
		}
		return applicableFor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code boolean}'.
	 * @generated
	 */
	public boolean isIsApplicableForAll() {
		return isApplicableForAll;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsApplicableForAll(boolean newIsApplicableForAll) {
		boolean oldIsApplicableForAll = isApplicableForAll;
		isApplicableForAll = newIsApplicableForAll;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.STRUCTURAL_ELEMENT__IS_APPLICABLE_FOR_ALL, oldIsApplicableForAll, isApplicableForAll));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code int}'.
	 * @generated
	 */
	public int getCardinality() {
		return cardinality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCardinality(int newCardinality) {
		int oldCardinality = cardinality;
		cardinality = newCardinality;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.STRUCTURAL_ELEMENT__CARDINALITY, oldCardinality, cardinality));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<StructuralElement>}'.
	 * @generated
	 */
	public EList<StructuralElement> getCanInheritFrom() {
		if (canInheritFrom == null) {
			canInheritFrom = new EObjectResolvingEList<StructuralElement>(StructuralElement.class, this, StructuralPackage.STRUCTURAL_ELEMENT__CAN_INHERIT_FROM);
		 
		
		}
		return canInheritFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code boolean}'.
	 * @generated
	 */
	public boolean isIsCanInheritFromAll() {
		return isCanInheritFromAll;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsCanInheritFromAll(boolean newIsCanInheritFromAll) {
		boolean oldIsCanInheritFromAll = isCanInheritFromAll;
		isCanInheritFromAll = newIsCanInheritFromAll;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.STRUCTURAL_ELEMENT__IS_CAN_INHERIT_FROM_ALL, oldIsCanInheritFromAll, isCanInheritFromAll));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code boolean}'.
	 * @generated
	 */
	public boolean isIsRootStructuralElement() {
		return isRootStructuralElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsRootStructuralElement(boolean newIsRootStructuralElement) {
		boolean oldIsRootStructuralElement = isRootStructuralElement;
		isRootStructuralElement = newIsRootStructuralElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StructuralPackage.STRUCTURAL_ELEMENT__IS_ROOT_STRUCTURAL_ELEMENT, oldIsRootStructuralElement, isRootStructuralElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StructuralPackage.STRUCTURAL_ELEMENT__NAME:
				return getName();
			case StructuralPackage.STRUCTURAL_ELEMENT__FULL_QUALIFIED_NAME:
				return getFullQualifiedName();
			case StructuralPackage.STRUCTURAL_ELEMENT__SHORT_NAME:
				return getShortName();
			case StructuralPackage.STRUCTURAL_ELEMENT__DESCRIPTION:
				return getDescription();
			case StructuralPackage.STRUCTURAL_ELEMENT__APPLICABLE_FOR:
				return getApplicableFor();
			case StructuralPackage.STRUCTURAL_ELEMENT__IS_APPLICABLE_FOR_ALL:
				return isIsApplicableForAll();
			case StructuralPackage.STRUCTURAL_ELEMENT__CARDINALITY:
				return getCardinality();
			case StructuralPackage.STRUCTURAL_ELEMENT__CAN_INHERIT_FROM:
				return getCanInheritFrom();
			case StructuralPackage.STRUCTURAL_ELEMENT__IS_CAN_INHERIT_FROM_ALL:
				return isIsCanInheritFromAll();
			case StructuralPackage.STRUCTURAL_ELEMENT__IS_ROOT_STRUCTURAL_ELEMENT:
				return isIsRootStructuralElement();
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
			case StructuralPackage.STRUCTURAL_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__SHORT_NAME:
				setShortName((String)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__APPLICABLE_FOR:
				getApplicableFor().clear();
				getApplicableFor().addAll((Collection<? extends StructuralElement>)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__IS_APPLICABLE_FOR_ALL:
				setIsApplicableForAll((Boolean)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__CARDINALITY:
				setCardinality((Integer)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__CAN_INHERIT_FROM:
				getCanInheritFrom().clear();
				getCanInheritFrom().addAll((Collection<? extends StructuralElement>)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__IS_CAN_INHERIT_FROM_ALL:
				setIsCanInheritFromAll((Boolean)newValue);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__IS_ROOT_STRUCTURAL_ELEMENT:
				setIsRootStructuralElement((Boolean)newValue);
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
			case StructuralPackage.STRUCTURAL_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__SHORT_NAME:
				setShortName(SHORT_NAME_EDEFAULT);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__APPLICABLE_FOR:
				getApplicableFor().clear();
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__IS_APPLICABLE_FOR_ALL:
				setIsApplicableForAll(IS_APPLICABLE_FOR_ALL_EDEFAULT);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__CARDINALITY:
				setCardinality(CARDINALITY_EDEFAULT);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__CAN_INHERIT_FROM:
				getCanInheritFrom().clear();
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__IS_CAN_INHERIT_FROM_ALL:
				setIsCanInheritFromAll(IS_CAN_INHERIT_FROM_ALL_EDEFAULT);
				return;
			case StructuralPackage.STRUCTURAL_ELEMENT__IS_ROOT_STRUCTURAL_ELEMENT:
				setIsRootStructuralElement(IS_ROOT_STRUCTURAL_ELEMENT_EDEFAULT);
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
			case StructuralPackage.STRUCTURAL_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case StructuralPackage.STRUCTURAL_ELEMENT__FULL_QUALIFIED_NAME:
				return FULL_QUALIFIED_NAME_EDEFAULT == null ? getFullQualifiedName() != null : !FULL_QUALIFIED_NAME_EDEFAULT.equals(getFullQualifiedName());
			case StructuralPackage.STRUCTURAL_ELEMENT__SHORT_NAME:
				return SHORT_NAME_EDEFAULT == null ? shortName != null : !SHORT_NAME_EDEFAULT.equals(shortName);
			case StructuralPackage.STRUCTURAL_ELEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case StructuralPackage.STRUCTURAL_ELEMENT__APPLICABLE_FOR:
				return applicableFor != null && !applicableFor.isEmpty();
			case StructuralPackage.STRUCTURAL_ELEMENT__IS_APPLICABLE_FOR_ALL:
				return isApplicableForAll != IS_APPLICABLE_FOR_ALL_EDEFAULT;
			case StructuralPackage.STRUCTURAL_ELEMENT__CARDINALITY:
				return cardinality != CARDINALITY_EDEFAULT;
			case StructuralPackage.STRUCTURAL_ELEMENT__CAN_INHERIT_FROM:
				return canInheritFrom != null && !canInheritFrom.isEmpty();
			case StructuralPackage.STRUCTURAL_ELEMENT__IS_CAN_INHERIT_FROM_ALL:
				return isCanInheritFromAll != IS_CAN_INHERIT_FROM_ALL_EDEFAULT;
			case StructuralPackage.STRUCTURAL_ELEMENT__IS_ROOT_STRUCTURAL_ELEMENT:
				return isRootStructuralElement != IS_ROOT_STRUCTURAL_ELEMENT_EDEFAULT;
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
				case StructuralPackage.STRUCTURAL_ELEMENT__DESCRIPTION: return GeneralPackage.IDESCRIPTION__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IApplicableFor.class) {
			switch (derivedFeatureID) {
				case StructuralPackage.STRUCTURAL_ELEMENT__APPLICABLE_FOR: return StructuralPackage.IAPPLICABLE_FOR__APPLICABLE_FOR;
				case StructuralPackage.STRUCTURAL_ELEMENT__IS_APPLICABLE_FOR_ALL: return StructuralPackage.IAPPLICABLE_FOR__IS_APPLICABLE_FOR_ALL;
				case StructuralPackage.STRUCTURAL_ELEMENT__CARDINALITY: return StructuralPackage.IAPPLICABLE_FOR__CARDINALITY;
				default: return -1;
			}
		}
		if (baseClass == IConceptTypeDefinition.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ICanInheritFrom.class) {
			switch (derivedFeatureID) {
				case StructuralPackage.STRUCTURAL_ELEMENT__CAN_INHERIT_FROM: return InheritancePackage.ICAN_INHERIT_FROM__CAN_INHERIT_FROM;
				case StructuralPackage.STRUCTURAL_ELEMENT__IS_CAN_INHERIT_FROM_ALL: return InheritancePackage.ICAN_INHERIT_FROM__IS_CAN_INHERIT_FROM_ALL;
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
				case GeneralPackage.IDESCRIPTION__DESCRIPTION: return StructuralPackage.STRUCTURAL_ELEMENT__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IApplicableFor.class) {
			switch (baseFeatureID) {
				case StructuralPackage.IAPPLICABLE_FOR__APPLICABLE_FOR: return StructuralPackage.STRUCTURAL_ELEMENT__APPLICABLE_FOR;
				case StructuralPackage.IAPPLICABLE_FOR__IS_APPLICABLE_FOR_ALL: return StructuralPackage.STRUCTURAL_ELEMENT__IS_APPLICABLE_FOR_ALL;
				case StructuralPackage.IAPPLICABLE_FOR__CARDINALITY: return StructuralPackage.STRUCTURAL_ELEMENT__CARDINALITY;
				default: return -1;
			}
		}
		if (baseClass == IConceptTypeDefinition.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ICanInheritFrom.class) {
			switch (baseFeatureID) {
				case InheritancePackage.ICAN_INHERIT_FROM__CAN_INHERIT_FROM: return StructuralPackage.STRUCTURAL_ELEMENT__CAN_INHERIT_FROM;
				case InheritancePackage.ICAN_INHERIT_FROM__IS_CAN_INHERIT_FROM_ALL: return StructuralPackage.STRUCTURAL_ELEMENT__IS_CAN_INHERIT_FROM_ALL;
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
		result.append(", shortName: ");
		result.append(shortName);
		result.append(", description: ");
		result.append(description);
		result.append(", isApplicableForAll: ");
		result.append(isApplicableForAll);
		result.append(", cardinality: ");
		result.append(cardinality);
		result.append(", isCanInheritFromAll: ");
		result.append(isCanInheritFromAll);
		result.append(", isRootStructuralElement: ");
		result.append(isRootStructuralElement);
		result.append(')');
		return result.toString();
	}

} //StructuralElementImpl
