/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl;


import de.dlr.sc.virsat.model.dvlm.categories.impl.ATypeInstanceImpl;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AProperty Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class APropertyInstanceImpl extends ATypeInstanceImpl implements APropertyInstance {
	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected APropertyInstanceImpl() {
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
		return PropertyinstancesPackage.Literals.APROPERTY_INSTANCE;
	}

} //APropertyInstanceImpl
