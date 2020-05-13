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

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory
 * @model kind="package"
 * @generated
 */
public interface QudvPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "qudv";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/dvlm/v8/qudv";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dvlm_qudv";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	QudvPackage eINSTANCE = de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.AUnitImpl <em>AUnit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.AUnitImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getAUnit()
	 * @generated
	 */
	int AUNIT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUNIT__NAME = GeneralPackage.INAME__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUNIT__UUID = GeneralPackage.INAME_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUNIT__SYMBOL = GeneralPackage.INAME_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUNIT__DESCRIPTION = GeneralPackage.INAME_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Definition URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUNIT__DEFINITION_URI = GeneralPackage.INAME_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Quantity Kind</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUNIT__QUANTITY_KIND = GeneralPackage.INAME_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>AUnit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUNIT_FEATURE_COUNT = GeneralPackage.INAME_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>AUnit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUNIT_OPERATION_COUNT = GeneralPackage.INAME_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.UnitFactorImpl <em>Unit Factor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.UnitFactorImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getUnitFactor()
	 * @generated
	 */
	int UNIT_FACTOR = 1;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_FACTOR__UUID = GeneralPackage.IUUID__UUID;

	/**
	 * The feature id for the '<em><b>Exponent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_FACTOR__EXPONENT = GeneralPackage.IUUID_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_FACTOR__UNIT = GeneralPackage.IUUID_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Unit Factor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_FACTOR_FEATURE_COUNT = GeneralPackage.IUUID_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Unit Factor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_FACTOR_OPERATION_COUNT = GeneralPackage.IUUID_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.AConversionBasedUnitImpl <em>AConversion Based Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.AConversionBasedUnitImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getAConversionBasedUnit()
	 * @generated
	 */
	int ACONVERSION_BASED_UNIT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACONVERSION_BASED_UNIT__NAME = AUNIT__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACONVERSION_BASED_UNIT__UUID = AUNIT__UUID;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACONVERSION_BASED_UNIT__SYMBOL = AUNIT__SYMBOL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACONVERSION_BASED_UNIT__DESCRIPTION = AUNIT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Definition URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACONVERSION_BASED_UNIT__DEFINITION_URI = AUNIT__DEFINITION_URI;

	/**
	 * The feature id for the '<em><b>Quantity Kind</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACONVERSION_BASED_UNIT__QUANTITY_KIND = AUNIT__QUANTITY_KIND;

	/**
	 * The feature id for the '<em><b>Is Invertible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACONVERSION_BASED_UNIT__IS_INVERTIBLE = AUNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Reference Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACONVERSION_BASED_UNIT__REFERENCE_UNIT = AUNIT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>AConversion Based Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACONVERSION_BASED_UNIT_FEATURE_COUNT = AUNIT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>AConversion Based Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACONVERSION_BASED_UNIT_OPERATION_COUNT = AUNIT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.LinearConversionUnitImpl <em>Linear Conversion Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.LinearConversionUnitImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getLinearConversionUnit()
	 * @generated
	 */
	int LINEAR_CONVERSION_UNIT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CONVERSION_UNIT__NAME = ACONVERSION_BASED_UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CONVERSION_UNIT__UUID = ACONVERSION_BASED_UNIT__UUID;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CONVERSION_UNIT__SYMBOL = ACONVERSION_BASED_UNIT__SYMBOL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CONVERSION_UNIT__DESCRIPTION = ACONVERSION_BASED_UNIT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Definition URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CONVERSION_UNIT__DEFINITION_URI = ACONVERSION_BASED_UNIT__DEFINITION_URI;

	/**
	 * The feature id for the '<em><b>Quantity Kind</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CONVERSION_UNIT__QUANTITY_KIND = ACONVERSION_BASED_UNIT__QUANTITY_KIND;

	/**
	 * The feature id for the '<em><b>Is Invertible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CONVERSION_UNIT__IS_INVERTIBLE = ACONVERSION_BASED_UNIT__IS_INVERTIBLE;

	/**
	 * The feature id for the '<em><b>Reference Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CONVERSION_UNIT__REFERENCE_UNIT = ACONVERSION_BASED_UNIT__REFERENCE_UNIT;

	/**
	 * The feature id for the '<em><b>Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CONVERSION_UNIT__FACTOR = ACONVERSION_BASED_UNIT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Linear Conversion Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CONVERSION_UNIT_FEATURE_COUNT = ACONVERSION_BASED_UNIT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Linear Conversion Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CONVERSION_UNIT_OPERATION_COUNT = ACONVERSION_BASED_UNIT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.AffineConversionUnitImpl <em>Affine Conversion Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.AffineConversionUnitImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getAffineConversionUnit()
	 * @generated
	 */
	int AFFINE_CONVERSION_UNIT = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINE_CONVERSION_UNIT__NAME = ACONVERSION_BASED_UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINE_CONVERSION_UNIT__UUID = ACONVERSION_BASED_UNIT__UUID;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINE_CONVERSION_UNIT__SYMBOL = ACONVERSION_BASED_UNIT__SYMBOL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINE_CONVERSION_UNIT__DESCRIPTION = ACONVERSION_BASED_UNIT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Definition URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINE_CONVERSION_UNIT__DEFINITION_URI = ACONVERSION_BASED_UNIT__DEFINITION_URI;

	/**
	 * The feature id for the '<em><b>Quantity Kind</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINE_CONVERSION_UNIT__QUANTITY_KIND = ACONVERSION_BASED_UNIT__QUANTITY_KIND;

	/**
	 * The feature id for the '<em><b>Is Invertible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINE_CONVERSION_UNIT__IS_INVERTIBLE = ACONVERSION_BASED_UNIT__IS_INVERTIBLE;

	/**
	 * The feature id for the '<em><b>Reference Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINE_CONVERSION_UNIT__REFERENCE_UNIT = ACONVERSION_BASED_UNIT__REFERENCE_UNIT;

	/**
	 * The feature id for the '<em><b>Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINE_CONVERSION_UNIT__FACTOR = ACONVERSION_BASED_UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINE_CONVERSION_UNIT__OFFSET = ACONVERSION_BASED_UNIT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Affine Conversion Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINE_CONVERSION_UNIT_FEATURE_COUNT = ACONVERSION_BASED_UNIT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Affine Conversion Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINE_CONVERSION_UNIT_OPERATION_COUNT = ACONVERSION_BASED_UNIT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.PrefixedUnitImpl <em>Prefixed Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.PrefixedUnitImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getPrefixedUnit()
	 * @generated
	 */
	int PREFIXED_UNIT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIXED_UNIT__NAME = ACONVERSION_BASED_UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIXED_UNIT__UUID = ACONVERSION_BASED_UNIT__UUID;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIXED_UNIT__SYMBOL = ACONVERSION_BASED_UNIT__SYMBOL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIXED_UNIT__DESCRIPTION = ACONVERSION_BASED_UNIT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Definition URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIXED_UNIT__DEFINITION_URI = ACONVERSION_BASED_UNIT__DEFINITION_URI;

	/**
	 * The feature id for the '<em><b>Quantity Kind</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIXED_UNIT__QUANTITY_KIND = ACONVERSION_BASED_UNIT__QUANTITY_KIND;

	/**
	 * The feature id for the '<em><b>Is Invertible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIXED_UNIT__IS_INVERTIBLE = ACONVERSION_BASED_UNIT__IS_INVERTIBLE;

	/**
	 * The feature id for the '<em><b>Reference Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIXED_UNIT__REFERENCE_UNIT = ACONVERSION_BASED_UNIT__REFERENCE_UNIT;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIXED_UNIT__PREFIX = ACONVERSION_BASED_UNIT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Prefixed Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIXED_UNIT_FEATURE_COUNT = ACONVERSION_BASED_UNIT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Prefixed Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIXED_UNIT_OPERATION_COUNT = ACONVERSION_BASED_UNIT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SimpleUnitImpl <em>Simple Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.SimpleUnitImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getSimpleUnit()
	 * @generated
	 */
	int SIMPLE_UNIT = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_UNIT__NAME = AUNIT__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_UNIT__UUID = AUNIT__UUID;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_UNIT__SYMBOL = AUNIT__SYMBOL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_UNIT__DESCRIPTION = AUNIT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Definition URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_UNIT__DEFINITION_URI = AUNIT__DEFINITION_URI;

	/**
	 * The feature id for the '<em><b>Quantity Kind</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_UNIT__QUANTITY_KIND = AUNIT__QUANTITY_KIND;

	/**
	 * The number of structural features of the '<em>Simple Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_UNIT_FEATURE_COUNT = AUNIT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Simple Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_UNIT_OPERATION_COUNT = AUNIT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.DerivedUnitImpl <em>Derived Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.DerivedUnitImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getDerivedUnit()
	 * @generated
	 */
	int DERIVED_UNIT = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_UNIT__NAME = AUNIT__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_UNIT__UUID = AUNIT__UUID;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_UNIT__SYMBOL = AUNIT__SYMBOL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_UNIT__DESCRIPTION = AUNIT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Definition URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_UNIT__DEFINITION_URI = AUNIT__DEFINITION_URI;

	/**
	 * The feature id for the '<em><b>Quantity Kind</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_UNIT__QUANTITY_KIND = AUNIT__QUANTITY_KIND;

	/**
	 * The feature id for the '<em><b>Factor</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_UNIT__FACTOR = AUNIT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Derived Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_UNIT_FEATURE_COUNT = AUNIT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Derived Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_UNIT_OPERATION_COUNT = AUNIT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.PrefixImpl <em>Prefix</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.PrefixImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getPrefix()
	 * @generated
	 */
	int PREFIX = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX__NAME = GeneralPackage.INAME__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX__UUID = GeneralPackage.INAME_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX__SYMBOL = GeneralPackage.INAME_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX__FACTOR = GeneralPackage.INAME_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Prefix</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_FEATURE_COUNT = GeneralPackage.INAME_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Prefix</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_OPERATION_COUNT = GeneralPackage.INAME_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.AQuantityKindImpl <em>AQuantity Kind</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.AQuantityKindImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getAQuantityKind()
	 * @generated
	 */
	int AQUANTITY_KIND = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUANTITY_KIND__NAME = GeneralPackage.INAME__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUANTITY_KIND__UUID = GeneralPackage.INAME_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUANTITY_KIND__SYMBOL = GeneralPackage.INAME_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUANTITY_KIND__DESCRIPTION = GeneralPackage.INAME_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Definition URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUANTITY_KIND__DEFINITION_URI = GeneralPackage.INAME_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>AQuantity Kind</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUANTITY_KIND_FEATURE_COUNT = GeneralPackage.INAME_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>AQuantity Kind</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AQUANTITY_KIND_OPERATION_COUNT = GeneralPackage.INAME_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.QuantityKindImpl <em>Quantity Kind</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QuantityKindImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getQuantityKind()
	 * @generated
	 */
	int QUANTITY_KIND = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY_KIND__NAME = AQUANTITY_KIND__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY_KIND__UUID = AQUANTITY_KIND__UUID;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY_KIND__SYMBOL = AQUANTITY_KIND__SYMBOL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY_KIND__DESCRIPTION = AQUANTITY_KIND__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Definition URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY_KIND__DEFINITION_URI = AQUANTITY_KIND__DEFINITION_URI;

	/**
	 * The feature id for the '<em><b>General</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY_KIND__GENERAL = AQUANTITY_KIND_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Quantity Kind</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY_KIND_FEATURE_COUNT = AQUANTITY_KIND_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Quantity Kind</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY_KIND_OPERATION_COUNT = AQUANTITY_KIND_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SimpleQuantityKindImpl <em>Simple Quantity Kind</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.SimpleQuantityKindImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getSimpleQuantityKind()
	 * @generated
	 */
	int SIMPLE_QUANTITY_KIND = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_QUANTITY_KIND__NAME = AQUANTITY_KIND__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_QUANTITY_KIND__UUID = AQUANTITY_KIND__UUID;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_QUANTITY_KIND__SYMBOL = AQUANTITY_KIND__SYMBOL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_QUANTITY_KIND__DESCRIPTION = AQUANTITY_KIND__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Definition URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_QUANTITY_KIND__DEFINITION_URI = AQUANTITY_KIND__DEFINITION_URI;

	/**
	 * The number of structural features of the '<em>Simple Quantity Kind</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_QUANTITY_KIND_FEATURE_COUNT = AQUANTITY_KIND_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Simple Quantity Kind</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_QUANTITY_KIND_OPERATION_COUNT = AQUANTITY_KIND_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.DerivedQuantityKindImpl <em>Derived Quantity Kind</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.DerivedQuantityKindImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getDerivedQuantityKind()
	 * @generated
	 */
	int DERIVED_QUANTITY_KIND = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_QUANTITY_KIND__NAME = AQUANTITY_KIND__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_QUANTITY_KIND__UUID = AQUANTITY_KIND__UUID;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_QUANTITY_KIND__SYMBOL = AQUANTITY_KIND__SYMBOL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_QUANTITY_KIND__DESCRIPTION = AQUANTITY_KIND__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Definition URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_QUANTITY_KIND__DEFINITION_URI = AQUANTITY_KIND__DEFINITION_URI;

	/**
	 * The feature id for the '<em><b>Factor</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_QUANTITY_KIND__FACTOR = AQUANTITY_KIND_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Derived Quantity Kind</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_QUANTITY_KIND_FEATURE_COUNT = AQUANTITY_KIND_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Derived Quantity Kind</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_QUANTITY_KIND_OPERATION_COUNT = AQUANTITY_KIND_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfUnitsImpl <em>System Of Units</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfUnitsImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getSystemOfUnits()
	 * @generated
	 */
	int SYSTEM_OF_UNITS = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_UNITS__NAME = GeneralPackage.INAME__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_UNITS__UUID = GeneralPackage.INAME_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_UNITS__SYMBOL = GeneralPackage.INAME_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_UNITS__DESCRIPTION = GeneralPackage.INAME_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Definition URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_UNITS__DEFINITION_URI = GeneralPackage.INAME_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_UNITS__PREFIX = GeneralPackage.INAME_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_UNITS__UNIT = GeneralPackage.INAME_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Base Unit</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_UNITS__BASE_UNIT = GeneralPackage.INAME_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>System Of Quantities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_UNITS__SYSTEM_OF_QUANTITIES = GeneralPackage.INAME_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>System Of Units</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_UNITS_FEATURE_COUNT = GeneralPackage.INAME_FEATURE_COUNT + 8;

	/**
	 * The number of operations of the '<em>System Of Units</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_UNITS_OPERATION_COUNT = GeneralPackage.INAME_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfQuantitiesImpl <em>System Of Quantities</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfQuantitiesImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getSystemOfQuantities()
	 * @generated
	 */
	int SYSTEM_OF_QUANTITIES = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_QUANTITIES__NAME = GeneralPackage.INAME__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_QUANTITIES__UUID = GeneralPackage.INAME_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_QUANTITIES__SYMBOL = GeneralPackage.INAME_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_QUANTITIES__DESCRIPTION = GeneralPackage.INAME_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Definition URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_QUANTITIES__DEFINITION_URI = GeneralPackage.INAME_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Quantity Kind</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_QUANTITIES__QUANTITY_KIND = GeneralPackage.INAME_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_QUANTITIES__DIMENSION = GeneralPackage.INAME_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>System Of Quantities</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_QUANTITIES_FEATURE_COUNT = GeneralPackage.INAME_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>System Of Quantities</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_QUANTITIES_OPERATION_COUNT = GeneralPackage.INAME_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.DimensionImpl <em>Dimension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.DimensionImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getDimension()
	 * @generated
	 */
	int DIMENSION = 15;

	/**
	 * The feature id for the '<em><b>Symbolic Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__SYMBOLIC_EXPRESSION = 0;

	/**
	 * The feature id for the '<em><b>Factor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__FACTOR = 1;

	/**
	 * The number of structural features of the '<em>Dimension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Dimension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.DimensionFactorImpl <em>Dimension Factor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.DimensionFactorImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getDimensionFactor()
	 * @generated
	 */
	int DIMENSION_FACTOR = 16;

	/**
	 * The feature id for the '<em><b>Exponent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_FACTOR__EXPONENT = 0;

	/**
	 * The feature id for the '<em><b>Base Dimension</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_FACTOR__BASE_DIMENSION = 1;

	/**
	 * The number of structural features of the '<em>Dimension Factor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_FACTOR_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Dimension Factor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_FACTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.QuantityKindFactorImpl <em>Quantity Kind Factor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QuantityKindFactorImpl
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getQuantityKindFactor()
	 * @generated
	 */
	int QUANTITY_KIND_FACTOR = 17;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY_KIND_FACTOR__UUID = GeneralPackage.IUUID__UUID;

	/**
	 * The feature id for the '<em><b>Exponent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY_KIND_FACTOR__EXPONENT = GeneralPackage.IUUID_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Quantity Kind</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY_KIND_FACTOR__QUANTITY_KIND = GeneralPackage.IUUID_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Quantity Kind Factor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY_KIND_FACTOR_FEATURE_COUNT = GeneralPackage.IUUID_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Quantity Kind Factor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY_KIND_FACTOR_OPERATION_COUNT = GeneralPackage.IUUID_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.AUnit <em>AUnit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AUnit</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AUnit
	 * @generated
	 */
	EClass getAUnit();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.AUnit#getSymbol <em>Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Symbol</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AUnit#getSymbol()
	 * @see #getAUnit()
	 * @generated
	 */
	EAttribute getAUnit_Symbol();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.AUnit#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AUnit#getDescription()
	 * @see #getAUnit()
	 * @generated
	 */
	EAttribute getAUnit_Description();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.AUnit#getDefinitionURI <em>Definition URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Definition URI</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AUnit#getDefinitionURI()
	 * @see #getAUnit()
	 * @generated
	 */
	EAttribute getAUnit_DefinitionURI();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.qudv.AUnit#getQuantityKind <em>Quantity Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Quantity Kind</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AUnit#getQuantityKind()
	 * @see #getAUnit()
	 * @generated
	 */
	EReference getAUnit_QuantityKind();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor <em>Unit Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unit Factor</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor
	 * @generated
	 */
	EClass getUnitFactor();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor#getExponent <em>Exponent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exponent</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor#getExponent()
	 * @see #getUnitFactor()
	 * @generated
	 */
	EAttribute getUnitFactor_Exponent();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Unit</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor#getUnit()
	 * @see #getUnitFactor()
	 * @generated
	 */
	EReference getUnitFactor_Unit();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit <em>AConversion Based Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AConversion Based Unit</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit
	 * @generated
	 */
	EClass getAConversionBasedUnit();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit#isIsInvertible <em>Is Invertible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Invertible</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit#isIsInvertible()
	 * @see #getAConversionBasedUnit()
	 * @generated
	 */
	EAttribute getAConversionBasedUnit_IsInvertible();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit#getReferenceUnit <em>Reference Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference Unit</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit#getReferenceUnit()
	 * @see #getAConversionBasedUnit()
	 * @generated
	 */
	EReference getAConversionBasedUnit_ReferenceUnit();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit <em>Linear Conversion Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Linear Conversion Unit</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit
	 * @generated
	 */
	EClass getLinearConversionUnit();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit#getFactor <em>Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Factor</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit#getFactor()
	 * @see #getLinearConversionUnit()
	 * @generated
	 */
	EAttribute getLinearConversionUnit_Factor();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit <em>Affine Conversion Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Affine Conversion Unit</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit
	 * @generated
	 */
	EClass getAffineConversionUnit();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit#getFactor <em>Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Factor</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit#getFactor()
	 * @see #getAffineConversionUnit()
	 * @generated
	 */
	EAttribute getAffineConversionUnit_Factor();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit#getOffset <em>Offset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Offset</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit#getOffset()
	 * @see #getAffineConversionUnit()
	 * @generated
	 */
	EAttribute getAffineConversionUnit_Offset();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit <em>Prefixed Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Prefixed Unit</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit
	 * @generated
	 */
	EClass getPrefixedUnit();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit#getPrefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Prefix</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit#getPrefix()
	 * @see #getPrefixedUnit()
	 * @generated
	 */
	EReference getPrefixedUnit_Prefix();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.SimpleUnit <em>Simple Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simple Unit</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SimpleUnit
	 * @generated
	 */
	EClass getSimpleUnit();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit <em>Derived Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Derived Unit</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit
	 * @generated
	 */
	EClass getDerivedUnit();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit#getFactor <em>Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Factor</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit#getFactor()
	 * @see #getDerivedUnit()
	 * @generated
	 */
	EReference getDerivedUnit_Factor();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.Prefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Prefix</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.Prefix
	 * @generated
	 */
	EClass getPrefix();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.Prefix#getSymbol <em>Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Symbol</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.Prefix#getSymbol()
	 * @see #getPrefix()
	 * @generated
	 */
	EAttribute getPrefix_Symbol();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.Prefix#getFactor <em>Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Factor</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.Prefix#getFactor()
	 * @see #getPrefix()
	 * @generated
	 */
	EAttribute getPrefix_Factor();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind <em>AQuantity Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AQuantity Kind</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind
	 * @generated
	 */
	EClass getAQuantityKind();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind#getSymbol <em>Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Symbol</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind#getSymbol()
	 * @see #getAQuantityKind()
	 * @generated
	 */
	EAttribute getAQuantityKind_Symbol();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind#getDescription()
	 * @see #getAQuantityKind()
	 * @generated
	 */
	EAttribute getAQuantityKind_Description();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind#getDefinitionURI <em>Definition URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Definition URI</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind#getDefinitionURI()
	 * @see #getAQuantityKind()
	 * @generated
	 */
	EAttribute getAQuantityKind_DefinitionURI();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.QuantityKind <em>Quantity Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quantity Kind</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QuantityKind
	 * @generated
	 */
	EClass getQuantityKind();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.qudv.QuantityKind#getGeneral <em>General</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>General</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QuantityKind#getGeneral()
	 * @see #getQuantityKind()
	 * @generated
	 */
	EReference getQuantityKind_General();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.SimpleQuantityKind <em>Simple Quantity Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simple Quantity Kind</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SimpleQuantityKind
	 * @generated
	 */
	EClass getSimpleQuantityKind();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind <em>Derived Quantity Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Derived Quantity Kind</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind
	 * @generated
	 */
	EClass getDerivedQuantityKind();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind#getFactor <em>Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Factor</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind#getFactor()
	 * @see #getDerivedQuantityKind()
	 * @generated
	 */
	EReference getDerivedQuantityKind_Factor();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits <em>System Of Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Of Units</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits
	 * @generated
	 */
	EClass getSystemOfUnits();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getSymbol <em>Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Symbol</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getSymbol()
	 * @see #getSystemOfUnits()
	 * @generated
	 */
	EAttribute getSystemOfUnits_Symbol();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getDescription()
	 * @see #getSystemOfUnits()
	 * @generated
	 */
	EAttribute getSystemOfUnits_Description();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getDefinitionURI <em>Definition URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Definition URI</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getDefinitionURI()
	 * @see #getSystemOfUnits()
	 * @generated
	 */
	EAttribute getSystemOfUnits_DefinitionURI();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getPrefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Prefix</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getPrefix()
	 * @see #getSystemOfUnits()
	 * @generated
	 */
	EReference getSystemOfUnits_Prefix();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Unit</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getUnit()
	 * @see #getSystemOfUnits()
	 * @generated
	 */
	EReference getSystemOfUnits_Unit();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getBaseUnit <em>Base Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Base Unit</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getBaseUnit()
	 * @see #getSystemOfUnits()
	 * @generated
	 */
	EReference getSystemOfUnits_BaseUnit();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getSystemOfQuantities <em>System Of Quantities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>System Of Quantities</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits#getSystemOfQuantities()
	 * @see #getSystemOfUnits()
	 * @generated
	 */
	EReference getSystemOfUnits_SystemOfQuantities();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities <em>System Of Quantities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Of Quantities</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities
	 * @generated
	 */
	EClass getSystemOfQuantities();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getSymbol <em>Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Symbol</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getSymbol()
	 * @see #getSystemOfQuantities()
	 * @generated
	 */
	EAttribute getSystemOfQuantities_Symbol();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getDescription()
	 * @see #getSystemOfQuantities()
	 * @generated
	 */
	EAttribute getSystemOfQuantities_Description();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getDefinitionURI <em>Definition URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Definition URI</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getDefinitionURI()
	 * @see #getSystemOfQuantities()
	 * @generated
	 */
	EAttribute getSystemOfQuantities_DefinitionURI();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getQuantityKind <em>Quantity Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Quantity Kind</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getQuantityKind()
	 * @see #getSystemOfQuantities()
	 * @generated
	 */
	EReference getSystemOfQuantities_QuantityKind();

	/**
	 * Returns the meta object for the reference list '{@link de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getDimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dimension</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities#getDimension()
	 * @see #getSystemOfQuantities()
	 * @generated
	 */
	EReference getSystemOfQuantities_Dimension();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.Dimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dimension</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.Dimension
	 * @generated
	 */
	EClass getDimension();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.Dimension#getSymbolicExpression <em>Symbolic Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Symbolic Expression</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.Dimension#getSymbolicExpression()
	 * @see #getDimension()
	 * @generated
	 */
	EAttribute getDimension_SymbolicExpression();

	/**
	 * Returns the meta object for the reference list '{@link de.dlr.sc.virsat.model.dvlm.qudv.Dimension#getFactor <em>Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Factor</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.Dimension#getFactor()
	 * @see #getDimension()
	 * @generated
	 */
	EReference getDimension_Factor();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.DimensionFactor <em>Dimension Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dimension Factor</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.DimensionFactor
	 * @generated
	 */
	EClass getDimensionFactor();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.DimensionFactor#getExponent <em>Exponent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exponent</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.DimensionFactor#getExponent()
	 * @see #getDimensionFactor()
	 * @generated
	 */
	EAttribute getDimensionFactor_Exponent();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.qudv.DimensionFactor#getBaseDimension <em>Base Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Dimension</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.DimensionFactor#getBaseDimension()
	 * @see #getDimensionFactor()
	 * @generated
	 */
	EReference getDimensionFactor_BaseDimension();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor <em>Quantity Kind Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quantity Kind Factor</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor
	 * @generated
	 */
	EClass getQuantityKindFactor();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor#getExponent <em>Exponent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exponent</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor#getExponent()
	 * @see #getQuantityKindFactor()
	 * @generated
	 */
	EAttribute getQuantityKindFactor_Exponent();

	/**
	 * Returns the meta object for the reference '{@link de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor#getQuantityKind <em>Quantity Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Quantity Kind</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor#getQuantityKind()
	 * @see #getQuantityKindFactor()
	 * @generated
	 */
	EReference getQuantityKindFactor_QuantityKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	QudvFactory getQudvFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.AUnitImpl <em>AUnit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.AUnitImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getAUnit()
		 * @generated
		 */
		EClass AUNIT = eINSTANCE.getAUnit();

		/**
		 * The meta object literal for the '<em><b>Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUNIT__SYMBOL = eINSTANCE.getAUnit_Symbol();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUNIT__DESCRIPTION = eINSTANCE.getAUnit_Description();

		/**
		 * The meta object literal for the '<em><b>Definition URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUNIT__DEFINITION_URI = eINSTANCE.getAUnit_DefinitionURI();

		/**
		 * The meta object literal for the '<em><b>Quantity Kind</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUNIT__QUANTITY_KIND = eINSTANCE.getAUnit_QuantityKind();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.UnitFactorImpl <em>Unit Factor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.UnitFactorImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getUnitFactor()
		 * @generated
		 */
		EClass UNIT_FACTOR = eINSTANCE.getUnitFactor();

		/**
		 * The meta object literal for the '<em><b>Exponent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNIT_FACTOR__EXPONENT = eINSTANCE.getUnitFactor_Exponent();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNIT_FACTOR__UNIT = eINSTANCE.getUnitFactor_Unit();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.AConversionBasedUnitImpl <em>AConversion Based Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.AConversionBasedUnitImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getAConversionBasedUnit()
		 * @generated
		 */
		EClass ACONVERSION_BASED_UNIT = eINSTANCE.getAConversionBasedUnit();

		/**
		 * The meta object literal for the '<em><b>Is Invertible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACONVERSION_BASED_UNIT__IS_INVERTIBLE = eINSTANCE.getAConversionBasedUnit_IsInvertible();

		/**
		 * The meta object literal for the '<em><b>Reference Unit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACONVERSION_BASED_UNIT__REFERENCE_UNIT = eINSTANCE.getAConversionBasedUnit_ReferenceUnit();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.LinearConversionUnitImpl <em>Linear Conversion Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.LinearConversionUnitImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getLinearConversionUnit()
		 * @generated
		 */
		EClass LINEAR_CONVERSION_UNIT = eINSTANCE.getLinearConversionUnit();

		/**
		 * The meta object literal for the '<em><b>Factor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINEAR_CONVERSION_UNIT__FACTOR = eINSTANCE.getLinearConversionUnit_Factor();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.AffineConversionUnitImpl <em>Affine Conversion Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.AffineConversionUnitImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getAffineConversionUnit()
		 * @generated
		 */
		EClass AFFINE_CONVERSION_UNIT = eINSTANCE.getAffineConversionUnit();

		/**
		 * The meta object literal for the '<em><b>Factor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AFFINE_CONVERSION_UNIT__FACTOR = eINSTANCE.getAffineConversionUnit_Factor();

		/**
		 * The meta object literal for the '<em><b>Offset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AFFINE_CONVERSION_UNIT__OFFSET = eINSTANCE.getAffineConversionUnit_Offset();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.PrefixedUnitImpl <em>Prefixed Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.PrefixedUnitImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getPrefixedUnit()
		 * @generated
		 */
		EClass PREFIXED_UNIT = eINSTANCE.getPrefixedUnit();

		/**
		 * The meta object literal for the '<em><b>Prefix</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PREFIXED_UNIT__PREFIX = eINSTANCE.getPrefixedUnit_Prefix();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SimpleUnitImpl <em>Simple Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.SimpleUnitImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getSimpleUnit()
		 * @generated
		 */
		EClass SIMPLE_UNIT = eINSTANCE.getSimpleUnit();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.DerivedUnitImpl <em>Derived Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.DerivedUnitImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getDerivedUnit()
		 * @generated
		 */
		EClass DERIVED_UNIT = eINSTANCE.getDerivedUnit();

		/**
		 * The meta object literal for the '<em><b>Factor</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DERIVED_UNIT__FACTOR = eINSTANCE.getDerivedUnit_Factor();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.PrefixImpl <em>Prefix</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.PrefixImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getPrefix()
		 * @generated
		 */
		EClass PREFIX = eINSTANCE.getPrefix();

		/**
		 * The meta object literal for the '<em><b>Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREFIX__SYMBOL = eINSTANCE.getPrefix_Symbol();

		/**
		 * The meta object literal for the '<em><b>Factor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREFIX__FACTOR = eINSTANCE.getPrefix_Factor();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.AQuantityKindImpl <em>AQuantity Kind</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.AQuantityKindImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getAQuantityKind()
		 * @generated
		 */
		EClass AQUANTITY_KIND = eINSTANCE.getAQuantityKind();

		/**
		 * The meta object literal for the '<em><b>Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AQUANTITY_KIND__SYMBOL = eINSTANCE.getAQuantityKind_Symbol();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AQUANTITY_KIND__DESCRIPTION = eINSTANCE.getAQuantityKind_Description();

		/**
		 * The meta object literal for the '<em><b>Definition URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AQUANTITY_KIND__DEFINITION_URI = eINSTANCE.getAQuantityKind_DefinitionURI();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.QuantityKindImpl <em>Quantity Kind</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QuantityKindImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getQuantityKind()
		 * @generated
		 */
		EClass QUANTITY_KIND = eINSTANCE.getQuantityKind();

		/**
		 * The meta object literal for the '<em><b>General</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUANTITY_KIND__GENERAL = eINSTANCE.getQuantityKind_General();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SimpleQuantityKindImpl <em>Simple Quantity Kind</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.SimpleQuantityKindImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getSimpleQuantityKind()
		 * @generated
		 */
		EClass SIMPLE_QUANTITY_KIND = eINSTANCE.getSimpleQuantityKind();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.DerivedQuantityKindImpl <em>Derived Quantity Kind</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.DerivedQuantityKindImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getDerivedQuantityKind()
		 * @generated
		 */
		EClass DERIVED_QUANTITY_KIND = eINSTANCE.getDerivedQuantityKind();

		/**
		 * The meta object literal for the '<em><b>Factor</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DERIVED_QUANTITY_KIND__FACTOR = eINSTANCE.getDerivedQuantityKind_Factor();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfUnitsImpl <em>System Of Units</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfUnitsImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getSystemOfUnits()
		 * @generated
		 */
		EClass SYSTEM_OF_UNITS = eINSTANCE.getSystemOfUnits();

		/**
		 * The meta object literal for the '<em><b>Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_OF_UNITS__SYMBOL = eINSTANCE.getSystemOfUnits_Symbol();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_OF_UNITS__DESCRIPTION = eINSTANCE.getSystemOfUnits_Description();

		/**
		 * The meta object literal for the '<em><b>Definition URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_OF_UNITS__DEFINITION_URI = eINSTANCE.getSystemOfUnits_DefinitionURI();

		/**
		 * The meta object literal for the '<em><b>Prefix</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_OF_UNITS__PREFIX = eINSTANCE.getSystemOfUnits_Prefix();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_OF_UNITS__UNIT = eINSTANCE.getSystemOfUnits_Unit();

		/**
		 * The meta object literal for the '<em><b>Base Unit</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_OF_UNITS__BASE_UNIT = eINSTANCE.getSystemOfUnits_BaseUnit();

		/**
		 * The meta object literal for the '<em><b>System Of Quantities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_OF_UNITS__SYSTEM_OF_QUANTITIES = eINSTANCE.getSystemOfUnits_SystemOfQuantities();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfQuantitiesImpl <em>System Of Quantities</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.SystemOfQuantitiesImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getSystemOfQuantities()
		 * @generated
		 */
		EClass SYSTEM_OF_QUANTITIES = eINSTANCE.getSystemOfQuantities();

		/**
		 * The meta object literal for the '<em><b>Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_OF_QUANTITIES__SYMBOL = eINSTANCE.getSystemOfQuantities_Symbol();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_OF_QUANTITIES__DESCRIPTION = eINSTANCE.getSystemOfQuantities_Description();

		/**
		 * The meta object literal for the '<em><b>Definition URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_OF_QUANTITIES__DEFINITION_URI = eINSTANCE.getSystemOfQuantities_DefinitionURI();

		/**
		 * The meta object literal for the '<em><b>Quantity Kind</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_OF_QUANTITIES__QUANTITY_KIND = eINSTANCE.getSystemOfQuantities_QuantityKind();

		/**
		 * The meta object literal for the '<em><b>Dimension</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_OF_QUANTITIES__DIMENSION = eINSTANCE.getSystemOfQuantities_Dimension();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.DimensionImpl <em>Dimension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.DimensionImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getDimension()
		 * @generated
		 */
		EClass DIMENSION = eINSTANCE.getDimension();

		/**
		 * The meta object literal for the '<em><b>Symbolic Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIMENSION__SYMBOLIC_EXPRESSION = eINSTANCE.getDimension_SymbolicExpression();

		/**
		 * The meta object literal for the '<em><b>Factor</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIMENSION__FACTOR = eINSTANCE.getDimension_Factor();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.DimensionFactorImpl <em>Dimension Factor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.DimensionFactorImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getDimensionFactor()
		 * @generated
		 */
		EClass DIMENSION_FACTOR = eINSTANCE.getDimensionFactor();

		/**
		 * The meta object literal for the '<em><b>Exponent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIMENSION_FACTOR__EXPONENT = eINSTANCE.getDimensionFactor_Exponent();

		/**
		 * The meta object literal for the '<em><b>Base Dimension</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIMENSION_FACTOR__BASE_DIMENSION = eINSTANCE.getDimensionFactor_BaseDimension();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.qudv.impl.QuantityKindFactorImpl <em>Quantity Kind Factor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QuantityKindFactorImpl
		 * @see de.dlr.sc.virsat.model.dvlm.qudv.impl.QudvPackageImpl#getQuantityKindFactor()
		 * @generated
		 */
		EClass QUANTITY_KIND_FACTOR = eINSTANCE.getQuantityKindFactor();

		/**
		 * The meta object literal for the '<em><b>Exponent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUANTITY_KIND_FACTOR__EXPONENT = eINSTANCE.getQuantityKindFactor_Exponent();

		/**
		 * The meta object literal for the '<em><b>Quantity Kind</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUANTITY_KIND_FACTOR__QUANTITY_KIND = eINSTANCE.getQuantityKindFactor_QuantityKind();

	}

} //QudvPackage
