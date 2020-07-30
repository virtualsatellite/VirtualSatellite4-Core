/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.concepts;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
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
 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory
 * @model kind="package"
 * @generated
 */
public interface ConceptsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "concepts";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.virsat.sc.dlr.de/dvlm/v8/c";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dvlm_c";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ConceptsPackage eINSTANCE = de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl <em>Concept</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl#getConcept()
	 * @generated
	 */
	int CONCEPT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__NAME = GeneralPackage.IQUALIFIED_NAME__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__FULL_QUALIFIED_NAME = GeneralPackage.IQUALIFIED_NAME__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__SHORT_NAME = GeneralPackage.IQUALIFIED_NAME__SHORT_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__DESCRIPTION = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__ACTIVE = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Imports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__IMPORTS = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ecore Imports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__ECORE_IMPORTS = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__CATEGORIES = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Structural Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__STRUCTURAL_ELEMENTS = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Relations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__RELATIONS = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__VERSION = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>DMF</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__DMF = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__DISPLAY_NAME = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Beta</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__BETA = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Concept</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_FEATURE_COUNT = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 11;

	/**
	 * The operation id for the '<em>Get Non Abstract Categories</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT___GET_NON_ABSTRACT_CATEGORIES = GeneralPackage.IQUALIFIED_NAME_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Concept</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_OPERATION_COUNT = GeneralPackage.IQUALIFIED_NAME_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImportImpl <em>Concept Import</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImportImpl
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl#getConceptImport()
	 * @generated
	 */
	int CONCEPT_IMPORT = 1;

	/**
	 * The feature id for the '<em><b>Imported Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_IMPORT__IMPORTED_NAMESPACE = 0;

	/**
	 * The number of structural features of the '<em>Concept Import</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_IMPORT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Concept Import</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_IMPORT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.concepts.IActiveConcept <em>IActive Concept</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.IActiveConcept
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl#getIActiveConcept()
	 * @generated
	 */
	int IACTIVE_CONCEPT = 2;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IACTIVE_CONCEPT__ACTIVE = 0;

	/**
	 * The number of structural features of the '<em>IActive Concept</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IACTIVE_CONCEPT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IActive Concept</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IACTIVE_CONCEPT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.concepts.IImports <em>IImports</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.IImports
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl#getIImports()
	 * @generated
	 */
	int IIMPORTS = 3;

	/**
	 * The feature id for the '<em><b>Imports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIMPORTS__IMPORTS = 0;

	/**
	 * The number of structural features of the '<em>IImports</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIMPORTS_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IImports</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIMPORTS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition <em>IConcept Type Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl#getIConceptTypeDefinition()
	 * @generated
	 */
	int ICONCEPT_TYPE_DEFINITION = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONCEPT_TYPE_DEFINITION__NAME = GeneralPackage.IQUALIFIED_NAME__NAME;

	/**
	 * The feature id for the '<em><b>Full Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONCEPT_TYPE_DEFINITION__FULL_QUALIFIED_NAME = GeneralPackage.IQUALIFIED_NAME__FULL_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONCEPT_TYPE_DEFINITION__SHORT_NAME = GeneralPackage.IQUALIFIED_NAME__SHORT_NAME;

	/**
	 * The number of structural features of the '<em>IConcept Type Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONCEPT_TYPE_DEFINITION_FEATURE_COUNT = GeneralPackage.IQUALIFIED_NAME_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>IConcept Type Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONCEPT_TYPE_DEFINITION_OPERATION_COUNT = GeneralPackage.IQUALIFIED_NAME_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.EcoreImportImpl <em>Ecore Import</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.EcoreImportImpl
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl#getEcoreImport()
	 * @generated
	 */
	int ECORE_IMPORT = 5;

	/**
	 * The feature id for the '<em><b>Imported Ns URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECORE_IMPORT__IMPORTED_NS_URI = 0;

	/**
	 * The feature id for the '<em><b>Imported Gen Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECORE_IMPORT__IMPORTED_GEN_MODEL = 1;

	/**
	 * The number of structural features of the '<em>Ecore Import</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECORE_IMPORT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Ecore Import</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECORE_IMPORT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.IEImportsImpl <em>IE Imports</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.IEImportsImpl
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl#getIEImports()
	 * @generated
	 */
	int IE_IMPORTS = 6;

	/**
	 * The feature id for the '<em><b>Ecore Imports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IE_IMPORTS__ECORE_IMPORTS = 0;

	/**
	 * The number of structural features of the '<em>IE Imports</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IE_IMPORTS_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IE Imports</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IE_IMPORTS_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concept</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.Concept
	 * @generated
	 */
	EClass getConcept();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#getCategories <em>Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Categories</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.Concept#getCategories()
	 * @see #getConcept()
	 * @generated
	 */
	EReference getConcept_Categories();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#getStructuralElements <em>Structural Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Structural Elements</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.Concept#getStructuralElements()
	 * @see #getConcept()
	 * @generated
	 */
	EReference getConcept_StructuralElements();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#getRelations <em>Relations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relations</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.Concept#getRelations()
	 * @see #getConcept()
	 * @generated
	 */
	EReference getConcept_Relations();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.Concept#getVersion()
	 * @see #getConcept()
	 * @generated
	 */
	EAttribute getConcept_Version();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#isDMF <em>DMF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>DMF</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.Concept#isDMF()
	 * @see #getConcept()
	 * @generated
	 */
	EAttribute getConcept_DMF();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Name</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.Concept#getDisplayName()
	 * @see #getConcept()
	 * @generated
	 */
	EAttribute getConcept_DisplayName();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#isBeta <em>Beta</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Beta</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.Concept#isBeta()
	 * @see #getConcept()
	 * @generated
	 */
	EAttribute getConcept_Beta();

	/**
	 * Returns the meta object for the '{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#getNonAbstractCategories() <em>Get Non Abstract Categories</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Non Abstract Categories</em>' operation.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.Concept#getNonAbstractCategories()
	 * @generated
	 */
	EOperation getConcept__GetNonAbstractCategories();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.concepts.ConceptImport <em>Concept Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concept Import</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptImport
	 * @generated
	 */
	EClass getConceptImport();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.concepts.ConceptImport#getImportedNamespace <em>Imported Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Imported Namespace</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptImport#getImportedNamespace()
	 * @see #getConceptImport()
	 * @generated
	 */
	EAttribute getConceptImport_ImportedNamespace();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.concepts.IActiveConcept <em>IActive Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IActive Concept</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.IActiveConcept
	 * @generated
	 */
	EClass getIActiveConcept();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.concepts.IActiveConcept#isActive <em>Active</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Active</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.IActiveConcept#isActive()
	 * @see #getIActiveConcept()
	 * @generated
	 */
	EAttribute getIActiveConcept_Active();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.concepts.IImports <em>IImports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IImports</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.IImports
	 * @generated
	 */
	EClass getIImports();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.concepts.IImports#getImports <em>Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Imports</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.IImports#getImports()
	 * @see #getIImports()
	 * @generated
	 */
	EReference getIImports_Imports();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition <em>IConcept Type Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IConcept Type Definition</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition
	 * @generated
	 */
	EClass getIConceptTypeDefinition();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport <em>Ecore Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ecore Import</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport
	 * @generated
	 */
	EClass getEcoreImport();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport#getImportedNsURI <em>Imported Ns URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Imported Ns URI</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport#getImportedNsURI()
	 * @see #getEcoreImport()
	 * @generated
	 */
	EAttribute getEcoreImport_ImportedNsURI();

	/**
	 * Returns the meta object for the attribute '{@link de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport#getImportedGenModel <em>Imported Gen Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Imported Gen Model</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport#getImportedGenModel()
	 * @see #getEcoreImport()
	 * @generated
	 */
	EAttribute getEcoreImport_ImportedGenModel();

	/**
	 * Returns the meta object for class '{@link de.dlr.sc.virsat.model.dvlm.concepts.IEImports <em>IE Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IE Imports</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.IEImports
	 * @generated
	 */
	EClass getIEImports();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dlr.sc.virsat.model.dvlm.concepts.IEImports#getEcoreImports <em>Ecore Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ecore Imports</em>'.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.IEImports#getEcoreImports()
	 * @see #getIEImports()
	 * @generated
	 */
	EReference getIEImports_EcoreImports();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ConceptsFactory getConceptsFactory();

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
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl <em>Concept</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImpl
		 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl#getConcept()
		 * @generated
		 */
		EClass CONCEPT = eINSTANCE.getConcept();

		/**
		 * The meta object literal for the '<em><b>Categories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCEPT__CATEGORIES = eINSTANCE.getConcept_Categories();

		/**
		 * The meta object literal for the '<em><b>Structural Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCEPT__STRUCTURAL_ELEMENTS = eINSTANCE.getConcept_StructuralElements();

		/**
		 * The meta object literal for the '<em><b>Relations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCEPT__RELATIONS = eINSTANCE.getConcept_Relations();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT__VERSION = eINSTANCE.getConcept_Version();

		/**
		 * The meta object literal for the '<em><b>DMF</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT__DMF = eINSTANCE.getConcept_DMF();

		/**
		 * The meta object literal for the '<em><b>Display Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT__DISPLAY_NAME = eINSTANCE.getConcept_DisplayName();

		/**
		 * The meta object literal for the '<em><b>Beta</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT__BETA = eINSTANCE.getConcept_Beta();

		/**
		 * The meta object literal for the '<em><b>Get Non Abstract Categories</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation CONCEPT___GET_NON_ABSTRACT_CATEGORIES = eINSTANCE.getConcept__GetNonAbstractCategories();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImportImpl <em>Concept Import</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptImportImpl
		 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl#getConceptImport()
		 * @generated
		 */
		EClass CONCEPT_IMPORT = eINSTANCE.getConceptImport();

		/**
		 * The meta object literal for the '<em><b>Imported Namespace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT_IMPORT__IMPORTED_NAMESPACE = eINSTANCE.getConceptImport_ImportedNamespace();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.concepts.IActiveConcept <em>IActive Concept</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.concepts.IActiveConcept
		 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl#getIActiveConcept()
		 * @generated
		 */
		EClass IACTIVE_CONCEPT = eINSTANCE.getIActiveConcept();

		/**
		 * The meta object literal for the '<em><b>Active</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IACTIVE_CONCEPT__ACTIVE = eINSTANCE.getIActiveConcept_Active();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.concepts.IImports <em>IImports</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.concepts.IImports
		 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl#getIImports()
		 * @generated
		 */
		EClass IIMPORTS = eINSTANCE.getIImports();

		/**
		 * The meta object literal for the '<em><b>Imports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IIMPORTS__IMPORTS = eINSTANCE.getIImports_Imports();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition <em>IConcept Type Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition
		 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl#getIConceptTypeDefinition()
		 * @generated
		 */
		EClass ICONCEPT_TYPE_DEFINITION = eINSTANCE.getIConceptTypeDefinition();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.EcoreImportImpl <em>Ecore Import</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.EcoreImportImpl
		 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl#getEcoreImport()
		 * @generated
		 */
		EClass ECORE_IMPORT = eINSTANCE.getEcoreImport();

		/**
		 * The meta object literal for the '<em><b>Imported Ns URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ECORE_IMPORT__IMPORTED_NS_URI = eINSTANCE.getEcoreImport_ImportedNsURI();

		/**
		 * The meta object literal for the '<em><b>Imported Gen Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ECORE_IMPORT__IMPORTED_GEN_MODEL = eINSTANCE.getEcoreImport_ImportedGenModel();

		/**
		 * The meta object literal for the '{@link de.dlr.sc.virsat.model.dvlm.concepts.impl.IEImportsImpl <em>IE Imports</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.IEImportsImpl
		 * @see de.dlr.sc.virsat.model.dvlm.concepts.impl.ConceptsPackageImpl#getIEImports()
		 * @generated
		 */
		EClass IE_IMPORTS = eINSTANCE.getIEImports();

		/**
		 * The meta object literal for the '<em><b>Ecore Imports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IE_IMPORTS__ECORE_IMPORTS = eINSTANCE.getIEImports_EcoreImports();

	}

} //ConceptsPackage
