/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
/**
 */
package de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IName;

import de.dlr.sc.virsat.model.dvlm.types.TypesFactory;
import de.dlr.sc.virsat.model.dvlm.types.TypesPackage;

import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TMPackage;
import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Trace Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TraceElementImpl#getUuid <em>Uuid</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TraceElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TraceElementImpl#getSourceTraceElement <em>Source Trace Element</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TraceElementImpl#getTargetTraceElement <em>Target Trace Element</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TraceElementImpl#getValidationEngineName <em>Validation Engine Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.requirements.tracing.model.traceModel.impl.TraceElementImpl#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TraceElementImpl extends MinimalEObjectImpl.Container implements TraceElement {
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
	 * The cached value of the '{@link #getSourceTraceElement() <em>Source Trace Element</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceTraceElement()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> sourceTraceElement;

	/**
	 * The cached value of the '{@link #getTargetTraceElement() <em>Target Trace Element</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetTraceElement()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> targetTraceElement;

	/**
	 * The default value of the '{@link #getValidationEngineName() <em>Validation Engine Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidationEngineName()
	 * @generated
	 * @ordered
	 */
	protected static final String VALIDATION_ENGINE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValidationEngineName() <em>Validation Engine Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidationEngineName()
	 * @generated
	 * @ordered
	 */
	protected String validationEngineName = VALIDATION_ENGINE_NAME_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TraceElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TMPackage.Literals.TRACE_ELEMENT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TMPackage.TRACE_ELEMENT__UUID, oldUuid, uuid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TMPackage.TRACE_ELEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EObject> getSourceTraceElement() {
		if (sourceTraceElement == null) {
			sourceTraceElement = new EObjectResolvingEList<EObject>(EObject.class, this, TMPackage.TRACE_ELEMENT__SOURCE_TRACE_ELEMENT);
		}
		return sourceTraceElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EObject> getTargetTraceElement() {
		if (targetTraceElement == null) {
			targetTraceElement = new EObjectResolvingEList<EObject>(EObject.class, this, TMPackage.TRACE_ELEMENT__TARGET_TRACE_ELEMENT);
		}
		return targetTraceElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValidationEngineName() {
		return validationEngineName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidationEngineName(String newValidationEngineName) {
		String oldValidationEngineName = validationEngineName;
		validationEngineName = newValidationEngineName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TMPackage.TRACE_ELEMENT__VALIDATION_ENGINE_NAME, oldValidationEngineName, validationEngineName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TMPackage.TRACE_ELEMENT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TMPackage.TRACE_ELEMENT__UUID:
				return getUuid();
			case TMPackage.TRACE_ELEMENT__NAME:
				return getName();
			case TMPackage.TRACE_ELEMENT__SOURCE_TRACE_ELEMENT:
				return getSourceTraceElement();
			case TMPackage.TRACE_ELEMENT__TARGET_TRACE_ELEMENT:
				return getTargetTraceElement();
			case TMPackage.TRACE_ELEMENT__VALIDATION_ENGINE_NAME:
				return getValidationEngineName();
			case TMPackage.TRACE_ELEMENT__DESCRIPTION:
				return getDescription();
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
			case TMPackage.TRACE_ELEMENT__UUID:
				setUuid((VirSatUuid)newValue);
				return;
			case TMPackage.TRACE_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case TMPackage.TRACE_ELEMENT__SOURCE_TRACE_ELEMENT:
				getSourceTraceElement().clear();
				getSourceTraceElement().addAll((Collection<? extends EObject>)newValue);
				return;
			case TMPackage.TRACE_ELEMENT__TARGET_TRACE_ELEMENT:
				getTargetTraceElement().clear();
				getTargetTraceElement().addAll((Collection<? extends EObject>)newValue);
				return;
			case TMPackage.TRACE_ELEMENT__VALIDATION_ENGINE_NAME:
				setValidationEngineName((String)newValue);
				return;
			case TMPackage.TRACE_ELEMENT__DESCRIPTION:
				setDescription((String)newValue);
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
			case TMPackage.TRACE_ELEMENT__UUID:
				setUuid(UUID_EDEFAULT);
				return;
			case TMPackage.TRACE_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TMPackage.TRACE_ELEMENT__SOURCE_TRACE_ELEMENT:
				getSourceTraceElement().clear();
				return;
			case TMPackage.TRACE_ELEMENT__TARGET_TRACE_ELEMENT:
				getTargetTraceElement().clear();
				return;
			case TMPackage.TRACE_ELEMENT__VALIDATION_ENGINE_NAME:
				setValidationEngineName(VALIDATION_ENGINE_NAME_EDEFAULT);
				return;
			case TMPackage.TRACE_ELEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
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
			case TMPackage.TRACE_ELEMENT__UUID:
				return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
			case TMPackage.TRACE_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TMPackage.TRACE_ELEMENT__SOURCE_TRACE_ELEMENT:
				return sourceTraceElement != null && !sourceTraceElement.isEmpty();
			case TMPackage.TRACE_ELEMENT__TARGET_TRACE_ELEMENT:
				return targetTraceElement != null && !targetTraceElement.isEmpty();
			case TMPackage.TRACE_ELEMENT__VALIDATION_ENGINE_NAME:
				return VALIDATION_ENGINE_NAME_EDEFAULT == null ? validationEngineName != null : !VALIDATION_ENGINE_NAME_EDEFAULT.equals(validationEngineName);
			case TMPackage.TRACE_ELEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
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
				case TMPackage.TRACE_ELEMENT__NAME: return GeneralPackage.INAME__NAME;
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
				case GeneralPackage.INAME__NAME: return TMPackage.TRACE_ELEMENT__NAME;
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
		result.append(", name: ");
		result.append(name);
		result.append(", ValidationEngineName: ");
		result.append(validationEngineName);
		result.append(", Description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //TraceElementImpl
