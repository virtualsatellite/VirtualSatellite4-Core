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
import de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionInput;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionSectionContainer;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.Category;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;

import de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;

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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryImpl#getApplicableFor <em>Applicable For</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryImpl#isIsApplicableForAll <em>Is Applicable For All</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryImpl#getCardinality <em>Cardinality</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryImpl#getEquationDefinitions <em>Equation Definitions</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryImpl#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryImpl#getExtendsCategory <em>Extends Category</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryImpl#isIsVerification <em>Is Verification</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CategoryImpl extends ATypeDefinitionImpl implements Category {
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
	 * The cached value of the '{@link #getEquationDefinitions() <em>Equation Definitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEquationDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList<EquationDefinition> equationDefinitions;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<AProperty> properties;

	/**
	 * The default value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ABSTRACT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean isAbstract = IS_ABSTRACT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExtendsCategory() <em>Extends Category</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendsCategory()
	 * @generated
	 * @ordered
	 */
	protected Category extendsCategory;

	/**
	 * The default value of the '{@link #isIsVerification() <em>Is Verification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsVerification()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_VERIFICATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsVerification() <em>Is Verification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsVerification()
	 * @generated
	 * @ordered
	 */
	protected boolean isVerification = IS_VERIFICATION_EDEFAULT;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CategoryImpl() {
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
		return CategoriesPackage.Literals.CATEGORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<StructuralElement>}'.
	 * @generated
	 */
	public EList<StructuralElement> getApplicableFor() {
		if (applicableFor == null) {
			applicableFor = new EObjectResolvingEList<StructuralElement>(StructuralElement.class, this, CategoriesPackage.CATEGORY__APPLICABLE_FOR);
		 
		
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
			eNotify(new ENotificationImpl(this, Notification.SET, CategoriesPackage.CATEGORY__IS_APPLICABLE_FOR_ALL, oldIsApplicableForAll, isApplicableForAll));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code int}'.
	 * @generated
	 */
	public int getCardinality_() {
		return cardinality;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCardinality() {
		// If the cardinality is 0, check if we are extending from some category and then take their cardinality
		return cardinality == 0 && getExtendsCategory() != null ? getExtendsCategory().getCardinality() : cardinality;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CategoriesPackage.CATEGORY__CARDINALITY, oldCardinality, cardinality));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<EquationDefinition>}'.
	 * @generated
	 */
	public EList<EquationDefinition> getEquationDefinitions() {
		if (equationDefinitions == null) {
			equationDefinitions = new EObjectContainmentEList.Resolving<EquationDefinition>(EquationDefinition.class, this, CategoriesPackage.CATEGORY__EQUATION_DEFINITIONS);
		 
		
		}
		return equationDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code EList<AProperty>}'.
	 * @generated
	 */
	public EList<AProperty> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList.Resolving<AProperty>(AProperty.class, this, CategoriesPackage.CATEGORY__PROPERTIES);
		 
		
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code boolean}'.
	 * @generated
	 */
	public boolean isIsAbstract() {
		return isAbstract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsAbstract(boolean newIsAbstract) {
		boolean oldIsAbstract = isAbstract;
		isAbstract = newIsAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CategoriesPackage.CATEGORY__IS_ABSTRACT, oldIsAbstract, isAbstract));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code Category}'.
	 * @generated
	 */
	public Category getExtendsCategory() {
		if (extendsCategory != null && extendsCategory.eIsProxy()) {
			InternalEObject oldExtendsCategory = (InternalEObject)extendsCategory;
			extendsCategory = (Category)eResolveProxy(oldExtendsCategory);
			if (extendsCategory != oldExtendsCategory) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CategoriesPackage.CATEGORY__EXTENDS_CATEGORY, oldExtendsCategory, extendsCategory));
			}
		}
		return extendsCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code Category}'.
	 * @generated
	 */
	public Category basicGetExtendsCategory() {
		return extendsCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtendsCategory(Category newExtendsCategory) {
		Category oldExtendsCategory = extendsCategory;
		extendsCategory = newExtendsCategory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CategoriesPackage.CATEGORY__EXTENDS_CATEGORY, oldExtendsCategory, extendsCategory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code boolean}'.
	 * @generated
	 */
	public boolean isIsVerification() {
		return isVerification;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsVerification(boolean newIsVerification) {
		boolean oldIsVerification = isVerification;
		isVerification = newIsVerification;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CategoriesPackage.CATEGORY__IS_VERIFICATION, oldIsVerification, isVerification));
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AProperty> getAllProperties() {
		EList<AProperty> allProperties = new org.eclipse.emf.common.util.BasicEList<>();
		allProperties.addAll(getProperties());
		
		// In case this category inherits from another one (extends another category) that their properties should eb added as well
		if (extendsCategory != null) {
			allProperties.addAll(extendsCategory.getAllProperties());
		}
			
		return allProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExtensionOf(final ATypeDefinition typeDefinition) {
		Category category = this;
				
		while (category != null) {
			if (category.equals(typeDefinition)) {
				return true;
			}
					
			category = category.getExtendsCategory();
		}
				
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EquationDefinition> getAllEquationDefinitions() {
		EList<EquationDefinition> allEquationDefinitions = new org.eclipse.emf.common.util.BasicEList<>();
		allEquationDefinitions.addAll(getEquationDefinitions());
		
		// In case this category inherits from another one (extends another category) that their equation definitions should be added as well
		if (extendsCategory != null) {
			allEquationDefinitions.addAll(extendsCategory.getAllEquationDefinitions());
		}
			
		return allEquationDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CategoriesPackage.CATEGORY__EQUATION_DEFINITIONS:
				return ((InternalEList<?>)getEquationDefinitions()).basicRemove(otherEnd, msgs);
			case CategoriesPackage.CATEGORY__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
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
			case CategoriesPackage.CATEGORY__APPLICABLE_FOR:
				return getApplicableFor();
			case CategoriesPackage.CATEGORY__IS_APPLICABLE_FOR_ALL:
				return isIsApplicableForAll();
			case CategoriesPackage.CATEGORY__CARDINALITY:
				return getCardinality();
			case CategoriesPackage.CATEGORY__EQUATION_DEFINITIONS:
				return getEquationDefinitions();
			case CategoriesPackage.CATEGORY__PROPERTIES:
				return getProperties();
			case CategoriesPackage.CATEGORY__IS_ABSTRACT:
				return isIsAbstract();
			case CategoriesPackage.CATEGORY__EXTENDS_CATEGORY:
				if (resolve) return getExtendsCategory();
				return basicGetExtendsCategory();
			case CategoriesPackage.CATEGORY__IS_VERIFICATION:
				return isIsVerification();
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
			case CategoriesPackage.CATEGORY__APPLICABLE_FOR:
				getApplicableFor().clear();
				getApplicableFor().addAll((Collection<? extends StructuralElement>)newValue);
				return;
			case CategoriesPackage.CATEGORY__IS_APPLICABLE_FOR_ALL:
				setIsApplicableForAll((Boolean)newValue);
				return;
			case CategoriesPackage.CATEGORY__CARDINALITY:
				setCardinality((Integer)newValue);
				return;
			case CategoriesPackage.CATEGORY__EQUATION_DEFINITIONS:
				getEquationDefinitions().clear();
				getEquationDefinitions().addAll((Collection<? extends EquationDefinition>)newValue);
				return;
			case CategoriesPackage.CATEGORY__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends AProperty>)newValue);
				return;
			case CategoriesPackage.CATEGORY__IS_ABSTRACT:
				setIsAbstract((Boolean)newValue);
				return;
			case CategoriesPackage.CATEGORY__EXTENDS_CATEGORY:
				setExtendsCategory((Category)newValue);
				return;
			case CategoriesPackage.CATEGORY__IS_VERIFICATION:
				setIsVerification((Boolean)newValue);
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
			case CategoriesPackage.CATEGORY__APPLICABLE_FOR:
				getApplicableFor().clear();
				return;
			case CategoriesPackage.CATEGORY__IS_APPLICABLE_FOR_ALL:
				setIsApplicableForAll(IS_APPLICABLE_FOR_ALL_EDEFAULT);
				return;
			case CategoriesPackage.CATEGORY__CARDINALITY:
				setCardinality(CARDINALITY_EDEFAULT);
				return;
			case CategoriesPackage.CATEGORY__EQUATION_DEFINITIONS:
				getEquationDefinitions().clear();
				return;
			case CategoriesPackage.CATEGORY__PROPERTIES:
				getProperties().clear();
				return;
			case CategoriesPackage.CATEGORY__IS_ABSTRACT:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case CategoriesPackage.CATEGORY__EXTENDS_CATEGORY:
				setExtendsCategory((Category)null);
				return;
			case CategoriesPackage.CATEGORY__IS_VERIFICATION:
				setIsVerification(IS_VERIFICATION_EDEFAULT);
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
			case CategoriesPackage.CATEGORY__APPLICABLE_FOR:
				return applicableFor != null && !applicableFor.isEmpty();
			case CategoriesPackage.CATEGORY__IS_APPLICABLE_FOR_ALL:
				return isApplicableForAll != IS_APPLICABLE_FOR_ALL_EDEFAULT;
			case CategoriesPackage.CATEGORY__CARDINALITY:
				return cardinality != CARDINALITY_EDEFAULT;
			case CategoriesPackage.CATEGORY__EQUATION_DEFINITIONS:
				return equationDefinitions != null && !equationDefinitions.isEmpty();
			case CategoriesPackage.CATEGORY__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case CategoriesPackage.CATEGORY__IS_ABSTRACT:
				return isAbstract != IS_ABSTRACT_EDEFAULT;
			case CategoriesPackage.CATEGORY__EXTENDS_CATEGORY:
				return extendsCategory != null;
			case CategoriesPackage.CATEGORY__IS_VERIFICATION:
				return isVerification != IS_VERIFICATION_EDEFAULT;
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
		if (baseClass == IApplicableFor.class) {
			switch (derivedFeatureID) {
				case CategoriesPackage.CATEGORY__APPLICABLE_FOR: return StructuralPackage.IAPPLICABLE_FOR__APPLICABLE_FOR;
				case CategoriesPackage.CATEGORY__IS_APPLICABLE_FOR_ALL: return StructuralPackage.IAPPLICABLE_FOR__IS_APPLICABLE_FOR_ALL;
				case CategoriesPackage.CATEGORY__CARDINALITY: return StructuralPackage.IAPPLICABLE_FOR__CARDINALITY;
				default: return -1;
			}
		}
		if (baseClass == IEquationDefinitionSectionContainer.class) {
			switch (derivedFeatureID) {
				case CategoriesPackage.CATEGORY__EQUATION_DEFINITIONS: return CalculationPackage.IEQUATION_DEFINITION_SECTION_CONTAINER__EQUATION_DEFINITIONS;
				default: return -1;
			}
		}
		if (baseClass == IEquationDefinitionInput.class) {
			switch (derivedFeatureID) {
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
		if (baseClass == IApplicableFor.class) {
			switch (baseFeatureID) {
				case StructuralPackage.IAPPLICABLE_FOR__APPLICABLE_FOR: return CategoriesPackage.CATEGORY__APPLICABLE_FOR;
				case StructuralPackage.IAPPLICABLE_FOR__IS_APPLICABLE_FOR_ALL: return CategoriesPackage.CATEGORY__IS_APPLICABLE_FOR_ALL;
				case StructuralPackage.IAPPLICABLE_FOR__CARDINALITY: return CategoriesPackage.CATEGORY__CARDINALITY;
				default: return -1;
			}
		}
		if (baseClass == IEquationDefinitionSectionContainer.class) {
			switch (baseFeatureID) {
				case CalculationPackage.IEQUATION_DEFINITION_SECTION_CONTAINER__EQUATION_DEFINITIONS: return CategoriesPackage.CATEGORY__EQUATION_DEFINITIONS;
				default: return -1;
			}
		}
		if (baseClass == IEquationDefinitionInput.class) {
			switch (baseFeatureID) {
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
			case CategoriesPackage.CATEGORY___GET_ALL_PROPERTIES:
				return getAllProperties();
			case CategoriesPackage.CATEGORY___IS_EXTENSION_OF__ATYPEDEFINITION:
				return isExtensionOf((ATypeDefinition)arguments.get(0));
			case CategoriesPackage.CATEGORY___GET_ALL_EQUATION_DEFINITIONS:
				return getAllEquationDefinitions();
			case CategoriesPackage.CATEGORY___GET_CARDINALITY:
				return getCardinality();
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
		result.append(" (isApplicableForAll: ");
		result.append(isApplicableForAll);
		result.append(", cardinality: ");
		result.append(cardinality);
		result.append(", isAbstract: ");
		result.append(isAbstract);
		result.append(", isVerification: ");
		result.append(isVerification);
		result.append(')');
		return result.toString();
	}

} //CategoryImpl
