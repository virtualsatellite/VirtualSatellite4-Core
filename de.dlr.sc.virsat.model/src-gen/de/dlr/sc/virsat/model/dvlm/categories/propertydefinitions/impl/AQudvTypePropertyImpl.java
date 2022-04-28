/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl;


import de.dlr.sc.virsat.model.dvlm.calculation.impl.IEquationDefinitionInputImpl;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IVerificationSpecification;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;

import de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition;

import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IDescription;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AQudv Type Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.AQudvTypePropertyImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.AQudvTypePropertyImpl#getFullQualifiedName <em>Full Qualified Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.AQudvTypePropertyImpl#getShortName <em>Short Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.AQudvTypePropertyImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.AQudvTypePropertyImpl#getArrayModifier <em>Array Modifier</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.AQudvTypePropertyImpl#getVerification <em>Verification</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.AQudvTypePropertyImpl#getQuantityKindName <em>Quantity Kind Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.AQudvTypePropertyImpl#getUnitName <em>Unit Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AQudvTypePropertyImpl extends IEquationDefinitionInputImpl implements AQudvTypeProperty {
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
	 * The cached value of the '{@link #getArrayModifier() <em>Array Modifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrayModifier()
	 * @generated
	 * @ordered
	 */
	protected IArrayModifier arrayModifier;

	/**
	 * The cached value of the '{@link #getVerification() <em>Verification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerification()
	 * @generated
	 * @ordered
	 */
	protected IVerificationSpecification verification;

	/**
	 * The default value of the '{@link #getQuantityKindName() <em>Quantity Kind Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantityKindName()
	 * @generated
	 * @ordered
	 */
	protected static final String QUANTITY_KIND_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getQuantityKindName() <em>Quantity Kind Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantityKindName()
	 * @generated
	 * @ordered
	 */
	protected String quantityKindName = QUANTITY_KIND_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getUnitName() <em>Unit Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnitName()
	 * @generated
	 * @ordered
	 */
	protected static final String UNIT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUnitName() <em>Unit Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnitName()
	 * @generated
	 * @ordered
	 */
	protected String unitName = UNIT_NAME_EDEFAULT;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AQudvTypePropertyImpl() {
		super();
		
	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PropertydefinitionsPackage.Literals.AQUDV_TYPE_PROPERTY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__SHORT_NAME, oldShortName, shortName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code IArrayModifier}'.
	 * @generated
	 */
	public IArrayModifier getArrayModifier() {
		if (arrayModifier != null && arrayModifier.eIsProxy()) {
			InternalEObject oldArrayModifier = (InternalEObject)arrayModifier;
			arrayModifier = (IArrayModifier)eResolveProxy(oldArrayModifier);
			if (arrayModifier != oldArrayModifier) {
				InternalEObject newArrayModifier = (InternalEObject)arrayModifier;
				NotificationChain msgs = oldArrayModifier.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER, null, null);
				if (newArrayModifier.eInternalContainer() == null) {
					msgs = newArrayModifier.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER, oldArrayModifier, arrayModifier));
			}
		}
		return arrayModifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value of type '{@code IArrayModifier}'.
	 * @generated
	 */
	public IArrayModifier basicGetArrayModifier() {
		return arrayModifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newArrayModifier new value to be of type '{@code IArrayModifier}' to be set. 
	 * @param msgs notifications of type '{@code NotificationChain}'.
	 * @return notification of type '{@code NotificationChain}'.
	 * @generated
	 */
	public NotificationChain basicSetArrayModifier(IArrayModifier newArrayModifier, NotificationChain msgs) {
		IArrayModifier oldArrayModifier = arrayModifier;
		arrayModifier = newArrayModifier;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER, oldArrayModifier, newArrayModifier);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArrayModifier(IArrayModifier newArrayModifier) {
		if (newArrayModifier != arrayModifier) {
			NotificationChain msgs = null;
			if (arrayModifier != null)
				msgs = ((InternalEObject)arrayModifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER, null, msgs);
			if (newArrayModifier != null)
				msgs = ((InternalEObject)newArrayModifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER, null, msgs);
			msgs = basicSetArrayModifier(newArrayModifier, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER, newArrayModifier, newArrayModifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code String}'.
	 * @generated
	 */
	public IVerificationSpecification getVerification() {
		if (verification != null && verification.eIsProxy()) {
			InternalEObject oldVerification = (InternalEObject)verification;
			verification = (IVerificationSpecification)eResolveProxy(oldVerification);
			if (verification != oldVerification) {
				InternalEObject newVerification = (InternalEObject)verification;
				NotificationChain msgs = oldVerification.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__VERIFICATION, null, null);
				if (newVerification.eInternalContainer() == null) {
					msgs = newVerification.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__VERIFICATION, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__VERIFICATION, oldVerification, verification));
			}
		}
		return verification;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IVerificationSpecification basicGetVerification() {
		return verification;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVerification(IVerificationSpecification newVerification, NotificationChain msgs) {
		IVerificationSpecification oldVerification = verification;
		verification = newVerification;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__VERIFICATION, oldVerification, newVerification);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVerification(IVerificationSpecification newVerification) {
		if (newVerification != verification) {
			NotificationChain msgs = null;
			if (verification != null)
				msgs = ((InternalEObject)verification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__VERIFICATION, null, msgs);
			if (newVerification != null)
				msgs = ((InternalEObject)newVerification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__VERIFICATION, null, msgs);
			msgs = basicSetVerification(newVerification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__VERIFICATION, newVerification, newVerification));
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getQuantityKindName() {
		return quantityKindName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuantityKindName(String newQuantityKindName) {
		String oldQuantityKindName = quantityKindName;
		quantityKindName = newQuantityKindName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__QUANTITY_KIND_NAME, oldQuantityKindName, quantityKindName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return value or object of type '{@code String}'.
	 * @generated
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnitName(String newUnitName) {
		String oldUnitName = unitName;
		unitName = newUnitName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__UNIT_NAME, oldUnitName, unitName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER:
				return basicSetArrayModifier(null, msgs);
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__VERIFICATION:
				return basicSetVerification(null, msgs);
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
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__NAME:
				return getName();
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__FULL_QUALIFIED_NAME:
				return getFullQualifiedName();
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__SHORT_NAME:
				return getShortName();
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__DESCRIPTION:
				return getDescription();
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER:
				if (resolve) return getArrayModifier();
				return basicGetArrayModifier();
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__VERIFICATION:
				if (resolve) return getVerification();
				return basicGetVerification();
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__QUANTITY_KIND_NAME:
				return getQuantityKindName();
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__UNIT_NAME:
				return getUnitName();
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
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__NAME:
				setName((String)newValue);
				return;
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__SHORT_NAME:
				setShortName((String)newValue);
				return;
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER:
				setArrayModifier((IArrayModifier)newValue);
				return;
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__VERIFICATION:
				setVerification((IVerificationSpecification)newValue);
				return;
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__QUANTITY_KIND_NAME:
				setQuantityKindName((String)newValue);
				return;
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__UNIT_NAME:
				setUnitName((String)newValue);
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
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__SHORT_NAME:
				setShortName(SHORT_NAME_EDEFAULT);
				return;
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER:
				setArrayModifier((IArrayModifier)null);
				return;
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__VERIFICATION:
				setVerification((IVerificationSpecification)null);
				return;
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__QUANTITY_KIND_NAME:
				setQuantityKindName(QUANTITY_KIND_NAME_EDEFAULT);
				return;
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__UNIT_NAME:
				setUnitName(UNIT_NAME_EDEFAULT);
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
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__FULL_QUALIFIED_NAME:
				return FULL_QUALIFIED_NAME_EDEFAULT == null ? getFullQualifiedName() != null : !FULL_QUALIFIED_NAME_EDEFAULT.equals(getFullQualifiedName());
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__SHORT_NAME:
				return SHORT_NAME_EDEFAULT == null ? shortName != null : !SHORT_NAME_EDEFAULT.equals(shortName);
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER:
				return arrayModifier != null;
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__VERIFICATION:
				return verification != null;
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__QUANTITY_KIND_NAME:
				return QUANTITY_KIND_NAME_EDEFAULT == null ? quantityKindName != null : !QUANTITY_KIND_NAME_EDEFAULT.equals(quantityKindName);
			case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__UNIT_NAME:
				return UNIT_NAME_EDEFAULT == null ? unitName != null : !UNIT_NAME_EDEFAULT.equals(unitName);
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
		if (baseClass == IQualifiedName.class) {
			switch (derivedFeatureID) {
				case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__NAME: return GeneralPackage.IQUALIFIED_NAME__NAME;
				case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__FULL_QUALIFIED_NAME: return GeneralPackage.IQUALIFIED_NAME__FULL_QUALIFIED_NAME;
				case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__SHORT_NAME: return GeneralPackage.IQUALIFIED_NAME__SHORT_NAME;
				default: return -1;
			}
		}
		if (baseClass == IDescription.class) {
			switch (derivedFeatureID) {
				case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__DESCRIPTION: return GeneralPackage.IDESCRIPTION__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IConceptTypeDefinition.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ATypeDefinition.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == AProperty.class) {
			switch (derivedFeatureID) {
				case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER: return PropertydefinitionsPackage.APROPERTY__ARRAY_MODIFIER;
				case PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__VERIFICATION: return PropertydefinitionsPackage.APROPERTY__VERIFICATION;
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
		if (baseClass == IQualifiedName.class) {
			switch (baseFeatureID) {
				case GeneralPackage.IQUALIFIED_NAME__NAME: return PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__NAME;
				case GeneralPackage.IQUALIFIED_NAME__FULL_QUALIFIED_NAME: return PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__FULL_QUALIFIED_NAME;
				case GeneralPackage.IQUALIFIED_NAME__SHORT_NAME: return PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__SHORT_NAME;
				default: return -1;
			}
		}
		if (baseClass == IDescription.class) {
			switch (baseFeatureID) {
				case GeneralPackage.IDESCRIPTION__DESCRIPTION: return PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IConceptTypeDefinition.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ATypeDefinition.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == AProperty.class) {
			switch (baseFeatureID) {
				case PropertydefinitionsPackage.APROPERTY__ARRAY_MODIFIER: return PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__ARRAY_MODIFIER;
				case PropertydefinitionsPackage.APROPERTY__VERIFICATION: return PropertydefinitionsPackage.AQUDV_TYPE_PROPERTY__VERIFICATION;
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
		result.append(", quantityKindName: ");
		result.append(quantityKindName);
		result.append(", unitName: ");
		result.append(unitName);
		result.append(')');
		return result.toString();
	}

} //AQudvTypePropertyImpl
