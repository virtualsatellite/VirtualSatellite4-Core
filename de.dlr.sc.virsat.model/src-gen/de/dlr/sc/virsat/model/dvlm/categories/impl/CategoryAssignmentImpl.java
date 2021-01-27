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


import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IName;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Category Assignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryAssignmentImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryAssignmentImpl#getEquationSection <em>Equation Section</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryAssignmentImpl#getPropertyInstances <em>Property Instances</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CategoryAssignmentImpl extends ATypeInstanceImpl implements CategoryAssignment {
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
	 * The cached value of the '{@link #getEquationSection() <em>Equation Section</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEquationSection()
	 * @generated
	 * @ordered
	 */
	protected EquationSection equationSection;

	/**
	 * The cached value of the '{@link #getPropertyInstances() <em>Property Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<APropertyInstance> propertyInstances;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CategoryAssignmentImpl() {
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
		return CategoriesPackage.Literals.CATEGORY_ASSIGNMENT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CategoriesPackage.CATEGORY_ASSIGNMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EquationSection getEquationSection() {
		if (equationSection != null && equationSection.eIsProxy()) {
			InternalEObject oldEquationSection = (InternalEObject)equationSection;
			equationSection = (EquationSection)eResolveProxy(oldEquationSection);
			if (equationSection != oldEquationSection) {
				InternalEObject newEquationSection = (InternalEObject)equationSection;
				NotificationChain msgs = oldEquationSection.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CategoriesPackage.CATEGORY_ASSIGNMENT__EQUATION_SECTION, null, null);
				if (newEquationSection.eInternalContainer() == null) {
					msgs = newEquationSection.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CategoriesPackage.CATEGORY_ASSIGNMENT__EQUATION_SECTION, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CategoriesPackage.CATEGORY_ASSIGNMENT__EQUATION_SECTION, oldEquationSection, equationSection));
			}
		}
		return equationSection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code EquationSection}'.
	 * @generated
	 */
	public EquationSection basicGetEquationSection() {
		return equationSection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newEquationSection new value to be of type '{@code EquationSection}' to be set. 
	 * @param msgs notifications of type '{@code NotificationChain}'.
	 * @return notification of type '{@code NotificationChain}'.
	 * @generated
	 */
	public NotificationChain basicSetEquationSection(EquationSection newEquationSection, NotificationChain msgs) {
		EquationSection oldEquationSection = equationSection;
		equationSection = newEquationSection;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CategoriesPackage.CATEGORY_ASSIGNMENT__EQUATION_SECTION, oldEquationSection, newEquationSection);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEquationSection(EquationSection newEquationSection) {
		if (newEquationSection != equationSection) {
			NotificationChain msgs = null;
			if (equationSection != null)
				msgs = ((InternalEObject)equationSection).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CategoriesPackage.CATEGORY_ASSIGNMENT__EQUATION_SECTION, null, msgs);
			if (newEquationSection != null)
				msgs = ((InternalEObject)newEquationSection).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CategoriesPackage.CATEGORY_ASSIGNMENT__EQUATION_SECTION, null, msgs);
			msgs = basicSetEquationSection(newEquationSection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CategoriesPackage.CATEGORY_ASSIGNMENT__EQUATION_SECTION, newEquationSection, newEquationSection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<APropertyInstance> getPropertyInstances() {
		if (propertyInstances == null) {
			propertyInstances = new EObjectContainmentEList.Resolving<APropertyInstance>(APropertyInstance.class, this, CategoriesPackage.CATEGORY_ASSIGNMENT__PROPERTY_INSTANCES);
		 
		
		}
		return propertyInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CategoriesPackage.CATEGORY_ASSIGNMENT__EQUATION_SECTION:
				return basicSetEquationSection(null, msgs);
			case CategoriesPackage.CATEGORY_ASSIGNMENT__PROPERTY_INSTANCES:
				return ((InternalEList<?>)getPropertyInstances()).basicRemove(otherEnd, msgs);
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
			case CategoriesPackage.CATEGORY_ASSIGNMENT__NAME:
				return getName();
			case CategoriesPackage.CATEGORY_ASSIGNMENT__EQUATION_SECTION:
				if (resolve) return getEquationSection();
				return basicGetEquationSection();
			case CategoriesPackage.CATEGORY_ASSIGNMENT__PROPERTY_INSTANCES:
				return getPropertyInstances();
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
			case CategoriesPackage.CATEGORY_ASSIGNMENT__NAME:
				setName((String)newValue);
				return;
			case CategoriesPackage.CATEGORY_ASSIGNMENT__EQUATION_SECTION:
				setEquationSection((EquationSection)newValue);
				return;
			case CategoriesPackage.CATEGORY_ASSIGNMENT__PROPERTY_INSTANCES:
				getPropertyInstances().clear();
				getPropertyInstances().addAll((Collection<? extends APropertyInstance>)newValue);
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
			case CategoriesPackage.CATEGORY_ASSIGNMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CategoriesPackage.CATEGORY_ASSIGNMENT__EQUATION_SECTION:
				setEquationSection((EquationSection)null);
				return;
			case CategoriesPackage.CATEGORY_ASSIGNMENT__PROPERTY_INSTANCES:
				getPropertyInstances().clear();
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
			case CategoriesPackage.CATEGORY_ASSIGNMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CategoriesPackage.CATEGORY_ASSIGNMENT__EQUATION_SECTION:
				return equationSection != null;
			case CategoriesPackage.CATEGORY_ASSIGNMENT__PROPERTY_INSTANCES:
				return propertyInstances != null && !propertyInstances.isEmpty();
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
		if (baseClass == IName.class) {
			switch (derivedFeatureID) {
				case CategoriesPackage.CATEGORY_ASSIGNMENT__NAME: return GeneralPackage.INAME__NAME;
				default: return -1;
			}
		}
		if (baseClass == IEquationInput.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == IEquationSectionContainer.class) {
			switch (derivedFeatureID) {
				case CategoriesPackage.CATEGORY_ASSIGNMENT__EQUATION_SECTION: return CalculationPackage.IEQUATION_SECTION_CONTAINER__EQUATION_SECTION;
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
		if (baseClass == IName.class) {
			switch (baseFeatureID) {
				case GeneralPackage.INAME__NAME: return CategoriesPackage.CATEGORY_ASSIGNMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == IEquationInput.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == IEquationSectionContainer.class) {
			switch (baseFeatureID) {
				case CalculationPackage.IEQUATION_SECTION_CONTAINER__EQUATION_SECTION: return CategoriesPackage.CATEGORY_ASSIGNMENT__EQUATION_SECTION;
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
		result.append(')');
		return result.toString();
	}

} //CategoryAssignmentImpl
