/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.qudv;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage
 * @generated
 */
public interface QudvFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	QudvFactory eINSTANCE = de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Unit Factor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unit Factor</em>'.
	 * @generated
	 */
	UnitFactor createUnitFactor();

	/**
	 * Returns a new object of class '<em>Linear Conversion Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Linear Conversion Unit</em>'.
	 * @generated
	 */
	LinearConversionUnit createLinearConversionUnit();

	/**
	 * Returns a new object of class '<em>Affine Conversion Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Affine Conversion Unit</em>'.
	 * @generated
	 */
	AffineConversionUnit createAffineConversionUnit();

	/**
	 * Returns a new object of class '<em>Prefixed Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Prefixed Unit</em>'.
	 * @generated
	 */
	PrefixedUnit createPrefixedUnit();

	/**
	 * Returns a new object of class '<em>Simple Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simple Unit</em>'.
	 * @generated
	 */
	SimpleUnit createSimpleUnit();

	/**
	 * Returns a new object of class '<em>Derived Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Derived Unit</em>'.
	 * @generated
	 */
	DerivedUnit createDerivedUnit();

	/**
	 * Returns a new object of class '<em>Prefix</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Prefix</em>'.
	 * @generated
	 */
	Prefix createPrefix();

	/**
	 * Returns a new object of class '<em>Quantity Kind</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Quantity Kind</em>'.
	 * @generated
	 */
	QuantityKind createQuantityKind();

	/**
	 * Returns a new object of class '<em>Simple Quantity Kind</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simple Quantity Kind</em>'.
	 * @generated
	 */
	SimpleQuantityKind createSimpleQuantityKind();

	/**
	 * Returns a new object of class '<em>Derived Quantity Kind</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Derived Quantity Kind</em>'.
	 * @generated
	 */
	DerivedQuantityKind createDerivedQuantityKind();

	/**
	 * Returns a new object of class '<em>System Of Units</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>System Of Units</em>'.
	 * @generated
	 */
	SystemOfUnits createSystemOfUnits();

	/**
	 * Returns a new object of class '<em>System Of Quantities</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>System Of Quantities</em>'.
	 * @generated
	 */
	SystemOfQuantities createSystemOfQuantities();

	/**
	 * Returns a new object of class '<em>Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dimension</em>'.
	 * @generated
	 */
	Dimension createDimension();

	/**
	 * Returns a new object of class '<em>Dimension Factor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dimension Factor</em>'.
	 * @generated
	 */
	DimensionFactor createDimensionFactor();

	/**
	 * Returns a new object of class '<em>Quantity Kind Factor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Quantity Kind Factor</em>'.
	 * @generated
	 */
	QuantityKindFactor createQuantityKindFactor();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	QudvPackage getQudvPackage();

} //QudvFactory
