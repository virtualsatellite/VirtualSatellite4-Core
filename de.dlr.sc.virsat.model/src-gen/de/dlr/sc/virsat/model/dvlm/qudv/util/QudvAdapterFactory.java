/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.qudv.util;

import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import de.dlr.sc.virsat.model.dvlm.qudv.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage
 * @generated
 */
public class QudvAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static QudvPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QudvAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = QudvPackage.eINSTANCE;
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
	protected QudvSwitch<Adapter> modelSwitch =
		new QudvSwitch<Adapter>() {
			@Override
			public Adapter caseAUnit(AUnit object) {
				return createAUnitAdapter();
			}
			@Override
			public Adapter caseUnitFactor(UnitFactor object) {
				return createUnitFactorAdapter();
			}
			@Override
			public Adapter caseAConversionBasedUnit(AConversionBasedUnit object) {
				return createAConversionBasedUnitAdapter();
			}
			@Override
			public Adapter caseLinearConversionUnit(LinearConversionUnit object) {
				return createLinearConversionUnitAdapter();
			}
			@Override
			public Adapter caseAffineConversionUnit(AffineConversionUnit object) {
				return createAffineConversionUnitAdapter();
			}
			@Override
			public Adapter casePrefixedUnit(PrefixedUnit object) {
				return createPrefixedUnitAdapter();
			}
			@Override
			public Adapter caseSimpleUnit(SimpleUnit object) {
				return createSimpleUnitAdapter();
			}
			@Override
			public Adapter caseDerivedUnit(DerivedUnit object) {
				return createDerivedUnitAdapter();
			}
			@Override
			public Adapter casePrefix(Prefix object) {
				return createPrefixAdapter();
			}
			@Override
			public Adapter caseAQuantityKind(AQuantityKind object) {
				return createAQuantityKindAdapter();
			}
			@Override
			public Adapter caseQuantityKind(QuantityKind object) {
				return createQuantityKindAdapter();
			}
			@Override
			public Adapter caseSimpleQuantityKind(SimpleQuantityKind object) {
				return createSimpleQuantityKindAdapter();
			}
			@Override
			public Adapter caseDerivedQuantityKind(DerivedQuantityKind object) {
				return createDerivedQuantityKindAdapter();
			}
			@Override
			public Adapter caseSystemOfUnits(SystemOfUnits object) {
				return createSystemOfUnitsAdapter();
			}
			@Override
			public Adapter caseSystemOfQuantities(SystemOfQuantities object) {
				return createSystemOfQuantitiesAdapter();
			}
			@Override
			public Adapter caseDimension(Dimension object) {
				return createDimensionAdapter();
			}
			@Override
			public Adapter caseDimensionFactor(DimensionFactor object) {
				return createDimensionFactorAdapter();
			}
			@Override
			public Adapter caseQuantityKindFactor(QuantityKindFactor object) {
				return createQuantityKindFactorAdapter();
			}
			@Override
			public Adapter caseIName(IName object) {
				return createINameAdapter();
			}
			@Override
			public Adapter caseIUuid(IUuid object) {
				return createIUuidAdapter();
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
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.AUnit <em>AUnit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AUnit
	 * @generated
	 */
	public Adapter createAUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor <em>Unit Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor
	 * @generated
	 */
	public Adapter createUnitFactorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit <em>AConversion Based Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit
	 * @generated
	 */
	public Adapter createAConversionBasedUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit <em>Linear Conversion Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit
	 * @generated
	 */
	public Adapter createLinearConversionUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit <em>Affine Conversion Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit
	 * @generated
	 */
	public Adapter createAffineConversionUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit <em>Prefixed Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit
	 * @generated
	 */
	public Adapter createPrefixedUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.SimpleUnit <em>Simple Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SimpleUnit
	 * @generated
	 */
	public Adapter createSimpleUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit <em>Derived Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit
	 * @generated
	 */
	public Adapter createDerivedUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.Prefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.Prefix
	 * @generated
	 */
	public Adapter createPrefixAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind <em>AQuantity Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind
	 * @generated
	 */
	public Adapter createAQuantityKindAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.QuantityKind <em>Quantity Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QuantityKind
	 * @generated
	 */
	public Adapter createQuantityKindAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.SimpleQuantityKind <em>Simple Quantity Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SimpleQuantityKind
	 * @generated
	 */
	public Adapter createSimpleQuantityKindAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind <em>Derived Quantity Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind
	 * @generated
	 */
	public Adapter createDerivedQuantityKindAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits <em>System Of Units</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits
	 * @generated
	 */
	public Adapter createSystemOfUnitsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities <em>System Of Quantities</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities
	 * @generated
	 */
	public Adapter createSystemOfQuantitiesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.Dimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.Dimension
	 * @generated
	 */
	public Adapter createDimensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.DimensionFactor <em>Dimension Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.DimensionFactor
	 * @generated
	 */
	public Adapter createDimensionFactorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor <em>Quantity Kind Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor
	 * @generated
	 */
	public Adapter createQuantityKindFactorAdapter() {
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

} //QudvAdapterFactory
