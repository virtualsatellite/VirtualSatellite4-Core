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

import de.dlr.sc.virsat.model.dvlm.qudv.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class QudvFactoryImpl extends EFactoryImpl implements QudvFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static QudvFactory init() {
		try {
			QudvFactory theQudvFactory = (QudvFactory)EPackage.Registry.INSTANCE.getEFactory(QudvPackage.eNS_URI);
			if (theQudvFactory != null) {
				return theQudvFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new QudvFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QudvFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case QudvPackage.UNIT_FACTOR: return createUnitFactor();
			case QudvPackage.LINEAR_CONVERSION_UNIT: return createLinearConversionUnit();
			case QudvPackage.AFFINE_CONVERSION_UNIT: return createAffineConversionUnit();
			case QudvPackage.PREFIXED_UNIT: return createPrefixedUnit();
			case QudvPackage.SIMPLE_UNIT: return createSimpleUnit();
			case QudvPackage.DERIVED_UNIT: return createDerivedUnit();
			case QudvPackage.PREFIX: return createPrefix();
			case QudvPackage.QUANTITY_KIND: return createQuantityKind();
			case QudvPackage.SIMPLE_QUANTITY_KIND: return createSimpleQuantityKind();
			case QudvPackage.DERIVED_QUANTITY_KIND: return createDerivedQuantityKind();
			case QudvPackage.SYSTEM_OF_UNITS: return createSystemOfUnits();
			case QudvPackage.SYSTEM_OF_QUANTITIES: return createSystemOfQuantities();
			case QudvPackage.DIMENSION: return createDimension();
			case QudvPackage.DIMENSION_FACTOR: return createDimensionFactor();
			case QudvPackage.QUANTITY_KIND_FACTOR: return createQuantityKindFactor();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnitFactor createUnitFactor() {
		UnitFactorImpl unitFactor = new UnitFactorImpl();
		return unitFactor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinearConversionUnit createLinearConversionUnit() {
		LinearConversionUnitImpl linearConversionUnit = new LinearConversionUnitImpl();
		return linearConversionUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AffineConversionUnit createAffineConversionUnit() {
		AffineConversionUnitImpl affineConversionUnit = new AffineConversionUnitImpl();
		return affineConversionUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrefixedUnit createPrefixedUnit() {
		PrefixedUnitImpl prefixedUnit = new PrefixedUnitImpl();
		return prefixedUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleUnit createSimpleUnit() {
		SimpleUnitImpl simpleUnit = new SimpleUnitImpl();
		return simpleUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DerivedUnit createDerivedUnit() {
		DerivedUnitImpl derivedUnit = new DerivedUnitImpl();
		return derivedUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Prefix createPrefix() {
		PrefixImpl prefix = new PrefixImpl();
		return prefix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QuantityKind createQuantityKind() {
		QuantityKindImpl quantityKind = new QuantityKindImpl();
		return quantityKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleQuantityKind createSimpleQuantityKind() {
		SimpleQuantityKindImpl simpleQuantityKind = new SimpleQuantityKindImpl();
		return simpleQuantityKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DerivedQuantityKind createDerivedQuantityKind() {
		DerivedQuantityKindImpl derivedQuantityKind = new DerivedQuantityKindImpl();
		return derivedQuantityKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemOfUnits createSystemOfUnits() {
		SystemOfUnitsImpl systemOfUnits = new SystemOfUnitsImpl();
		return systemOfUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemOfQuantities createSystemOfQuantities() {
		SystemOfQuantitiesImpl systemOfQuantities = new SystemOfQuantitiesImpl();
		return systemOfQuantities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dimension createDimension() {
		DimensionImpl dimension = new DimensionImpl();
		return dimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DimensionFactor createDimensionFactor() {
		DimensionFactorImpl dimensionFactor = new DimensionFactorImpl();
		return dimensionFactor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QuantityKindFactor createQuantityKindFactor() {
		QuantityKindFactorImpl quantityKindFactor = new QuantityKindFactorImpl();
		return quantityKindFactor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QudvPackage getQudvPackage() {
		return (QudvPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static QudvPackage getPackage() {
		return QudvPackage.eINSTANCE;
	}

} //QudvFactoryImpl
