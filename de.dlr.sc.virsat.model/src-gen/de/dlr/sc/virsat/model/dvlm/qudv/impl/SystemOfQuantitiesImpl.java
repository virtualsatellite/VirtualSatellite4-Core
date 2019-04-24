/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.qudv.impl;


import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.Dimension;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;

import de.dlr.sc.virsat.model.dvlm.types.TypesFactory;
import de.dlr.sc.virsat.model.dvlm.types.TypesPackage;

import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException;
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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Of Quantities</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfQuantitiesImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfQuantitiesImpl#getUuid <em>Uuid</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfQuantitiesImpl#getSymbol <em>Symbol</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfQuantitiesImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfQuantitiesImpl#getDefinitionURI <em>Definition URI</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfQuantitiesImpl#getQuantityKind <em>Quantity Kind</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfQuantitiesImpl#getDimension <em>Dimension</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SystemOfQuantitiesImpl extends MinimalEObjectImpl.Container implements SystemOfQuantities {
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
	 * The default value of the '{@link #getSymbol() <em>Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSymbol()
	 * @generated
	 * @ordered
	 */
	protected static final String SYMBOL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSymbol() <em>Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSymbol()
	 * @generated
	 * @ordered
	 */
	protected String symbol = SYMBOL_EDEFAULT;

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
	 * The default value of the '{@link #getDefinitionURI() <em>Definition URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinitionURI()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFINITION_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefinitionURI() <em>Definition URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinitionURI()
	 * @generated
	 * @ordered
	 */
	protected String definitionURI = DEFINITION_URI_EDEFAULT;

	/**
	 * The cached value of the '{@link #getQuantityKind() <em>Quantity Kind</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantityKind()
	 * @generated
	 * @ordered
	 */
	protected EList<AQuantityKind> quantityKind;

	/**
	 * The cached value of the '{@link #getDimension() <em>Dimension</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDimension()
	 * @generated
	 * @ordered
	 */
	protected EList<Dimension> dimension;

	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemOfQuantitiesImpl() {
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
		return QudvPackage.Literals.SYSTEM_OF_QUANTITIES;
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
			eNotify(new ENotificationImpl(this, Notification.SET, QudvPackage.SYSTEM_OF_QUANTITIES__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, QudvPackage.SYSTEM_OF_QUANTITIES__UUID, oldUuid, uuid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSymbol(String newSymbol) {
		String oldSymbol = symbol;
		symbol = newSymbol;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QudvPackage.SYSTEM_OF_QUANTITIES__SYMBOL, oldSymbol, symbol));
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
			eNotify(new ENotificationImpl(this, Notification.SET, QudvPackage.SYSTEM_OF_QUANTITIES__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefinitionURI() {
		return definitionURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefinitionURI(String newDefinitionURI) {
		String oldDefinitionURI = definitionURI;
		definitionURI = newDefinitionURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QudvPackage.SYSTEM_OF_QUANTITIES__DEFINITION_URI, oldDefinitionURI, definitionURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AQuantityKind> getQuantityKind() {
		if (quantityKind == null) {
			quantityKind = new EObjectContainmentEList.Resolving<AQuantityKind>(AQuantityKind.class, this, QudvPackage.SYSTEM_OF_QUANTITIES__QUANTITY_KIND);
		 
		
		}
		return quantityKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Dimension> getDimension() {
		if (dimension == null) {
			dimension = new EObjectResolvingEList.Unsettable<Dimension>(Dimension.class, this, QudvPackage.SYSTEM_OF_QUANTITIES__DIMENSION);
		 
		
		}
		return dimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDimension() {
		if (dimension != null) ((InternalEList.Unsettable<?>)dimension).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDimension() {
		return dimension != null && ((InternalEList.Unsettable<?>)dimension).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QudvPackage.SYSTEM_OF_QUANTITIES__QUANTITY_KIND:
				return ((InternalEList<?>)getQuantityKind()).basicRemove(otherEnd, msgs);
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
			case QudvPackage.SYSTEM_OF_QUANTITIES__NAME:
				return getName();
			case QudvPackage.SYSTEM_OF_QUANTITIES__UUID:
				return getUuid();
			case QudvPackage.SYSTEM_OF_QUANTITIES__SYMBOL:
				return getSymbol();
			case QudvPackage.SYSTEM_OF_QUANTITIES__DESCRIPTION:
				return getDescription();
			case QudvPackage.SYSTEM_OF_QUANTITIES__DEFINITION_URI:
				return getDefinitionURI();
			case QudvPackage.SYSTEM_OF_QUANTITIES__QUANTITY_KIND:
				return getQuantityKind();
			case QudvPackage.SYSTEM_OF_QUANTITIES__DIMENSION:
				return getDimension();
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
			case QudvPackage.SYSTEM_OF_QUANTITIES__NAME:
				setName((String)newValue);
				return;
			case QudvPackage.SYSTEM_OF_QUANTITIES__UUID:
				setUuid((VirSatUuid)newValue);
				return;
			case QudvPackage.SYSTEM_OF_QUANTITIES__SYMBOL:
				setSymbol((String)newValue);
				return;
			case QudvPackage.SYSTEM_OF_QUANTITIES__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case QudvPackage.SYSTEM_OF_QUANTITIES__DEFINITION_URI:
				setDefinitionURI((String)newValue);
				return;
			case QudvPackage.SYSTEM_OF_QUANTITIES__QUANTITY_KIND:
				getQuantityKind().clear();
				getQuantityKind().addAll((Collection<? extends AQuantityKind>)newValue);
				return;
			case QudvPackage.SYSTEM_OF_QUANTITIES__DIMENSION:
				getDimension().clear();
				getDimension().addAll((Collection<? extends Dimension>)newValue);
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
			case QudvPackage.SYSTEM_OF_QUANTITIES__NAME:
				setName(NAME_EDEFAULT);
				return;
			case QudvPackage.SYSTEM_OF_QUANTITIES__UUID:
				setUuid(UUID_EDEFAULT);
				return;
			case QudvPackage.SYSTEM_OF_QUANTITIES__SYMBOL:
				setSymbol(SYMBOL_EDEFAULT);
				return;
			case QudvPackage.SYSTEM_OF_QUANTITIES__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case QudvPackage.SYSTEM_OF_QUANTITIES__DEFINITION_URI:
				setDefinitionURI(DEFINITION_URI_EDEFAULT);
				return;
			case QudvPackage.SYSTEM_OF_QUANTITIES__QUANTITY_KIND:
				getQuantityKind().clear();
				return;
			case QudvPackage.SYSTEM_OF_QUANTITIES__DIMENSION:
				unsetDimension();
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
			case QudvPackage.SYSTEM_OF_QUANTITIES__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case QudvPackage.SYSTEM_OF_QUANTITIES__UUID:
				return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
			case QudvPackage.SYSTEM_OF_QUANTITIES__SYMBOL:
				return SYMBOL_EDEFAULT == null ? symbol != null : !SYMBOL_EDEFAULT.equals(symbol);
			case QudvPackage.SYSTEM_OF_QUANTITIES__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case QudvPackage.SYSTEM_OF_QUANTITIES__DEFINITION_URI:
				return DEFINITION_URI_EDEFAULT == null ? definitionURI != null : !DEFINITION_URI_EDEFAULT.equals(definitionURI);
			case QudvPackage.SYSTEM_OF_QUANTITIES__QUANTITY_KIND:
				return quantityKind != null && !quantityKind.isEmpty();
			case QudvPackage.SYSTEM_OF_QUANTITIES__DIMENSION:
				return isSetDimension();
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
				case QudvPackage.SYSTEM_OF_QUANTITIES__UUID: return GeneralPackage.IUUID__UUID;
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
				case GeneralPackage.IUUID__UUID: return QudvPackage.SYSTEM_OF_QUANTITIES__UUID;
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
		result.append(", uuid: ");
		result.append(uuid);
		result.append(", symbol: ");
		result.append(symbol);
		result.append(", description: ");
		result.append(description);
		result.append(", definitionURI: ");
		result.append(definitionURI);
		result.append(')');
		return result.toString();
	}

} //SystemOfQuantitiesImpl
