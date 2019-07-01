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
package de.dlr.sc.virsat.requirements.tracing.traceModel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.requirements.tracing.traceModel.TMPackage
 * @generated
 */
public interface TMFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TMFactory eINSTANCE = de.dlr.sc.virsat.requirements.tracing.traceModel.impl.TMFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Trace Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Trace Element</em>'.
	 * @generated
	 */
	TraceElement createTraceElement();

	/**
	 * Returns a new object of class '<em>Traceability Link Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Traceability Link Container</em>'.
	 * @generated
	 */
	TraceabilityLinkContainer createTraceabilityLinkContainer();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TMPackage getTMPackage();

} //TMFactory
