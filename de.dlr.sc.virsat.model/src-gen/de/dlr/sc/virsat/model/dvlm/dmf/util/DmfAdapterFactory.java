/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.dmf.util;

import de.dlr.sc.virsat.model.dvlm.dmf.*;

import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.dvlm.dmf.DmfPackage
 * @generated
 */
public class DmfAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DmfPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DmfAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DmfPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DmfSwitch<Adapter> modelSwitch =
		new DmfSwitch<Adapter>() {
			@Override
			public Adapter caseDObject(DObject object) {
				return createDObjectAdapter();
			}
			@Override
			public Adapter caseDObjectContainer(DObjectContainer object) {
				return createDObjectContainerAdapter();
			}
			@Override
			public Adapter caseUnresolveableDObject(UnresolveableDObject object) {
				return createUnresolveableDObjectAdapter();
			}
			@Override
			public Adapter caseIUuid(IUuid object) {
				return createIUuidAdapter();
			}
			@Override
			public Adapter caseIName(IName object) {
				return createINameAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.dmf.DObject <em>DObject</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.dmf.DObject
	 * @generated
	 */
	public Adapter createDObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.dmf.DObjectContainer <em>DObject Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.dmf.DObjectContainer
	 * @generated
	 */
	public Adapter createDObjectContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.dmf.UnresolveableDObject <em>Unresolveable DObject</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.dmf.UnresolveableDObject
	 * @generated
	 */
	public Adapter createUnresolveableDObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.general.IUuid <em>IUuid</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IUuid
	 * @generated
	 */
	public Adapter createIUuidAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.general.IName <em>IName</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IName
	 * @generated
	 */
	public Adapter createINameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //DmfAdapterFactory
