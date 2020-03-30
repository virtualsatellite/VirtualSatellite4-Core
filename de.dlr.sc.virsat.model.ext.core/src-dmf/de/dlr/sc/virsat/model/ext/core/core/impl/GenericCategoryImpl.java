/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.ext.core.core.impl;

import de.dlr.sc.virsat.model.dvlm.dmf.impl.DObjectImpl;

import de.dlr.sc.virsat.model.ext.core.core.CorePackage;
import de.dlr.sc.virsat.model.ext.core.core.GenericCategory;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class GenericCategoryImpl extends DObjectImpl implements GenericCategory {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericCategoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.GENERIC_CATEGORY;
	}

} //GenericCategoryImpl
