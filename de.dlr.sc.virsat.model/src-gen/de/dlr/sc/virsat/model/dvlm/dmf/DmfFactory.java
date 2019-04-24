/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.dmf;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.dvlm.dmf.DmfPackage
 * @generated
 */
public interface DmfFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DmfFactory eINSTANCE = de.dlr.sc.virsat.model.dvlm.dmf.impl.DmfFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>DObject Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DObject Container</em>'.
	 * @generated
	 */
	DObjectContainer createDObjectContainer();

	/**
	 * Returns a new object of class '<em>Unresolveable DObject</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unresolveable DObject</em>'.
	 * @generated
	 */
	UnresolveableDObject createUnresolveableDObject();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DmfPackage getDmfPackage();

} //DmfFactory
