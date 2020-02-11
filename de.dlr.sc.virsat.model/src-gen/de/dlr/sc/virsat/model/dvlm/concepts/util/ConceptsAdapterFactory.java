/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.concepts.util;

import de.dlr.sc.virsat.model.dvlm.concepts.*;

import de.dlr.sc.virsat.model.dvlm.general.IDescription;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage
 * @generated
 */
public class ConceptsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ConceptsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConceptsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ConceptsPackage.eINSTANCE;
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
	protected ConceptsSwitch<Adapter> modelSwitch =
		new ConceptsSwitch<Adapter>() {
			@Override
			public Adapter caseConcept(Concept object) {
				return createConceptAdapter();
			}
			@Override
			public Adapter caseConceptImport(ConceptImport object) {
				return createConceptImportAdapter();
			}
			@Override
			public Adapter caseIActiveConcept(IActiveConcept object) {
				return createIActiveConceptAdapter();
			}
			@Override
			public Adapter caseIImports(IImports object) {
				return createIImportsAdapter();
			}
			@Override
			public Adapter caseIConceptTypeDefinition(IConceptTypeDefinition object) {
				return createIConceptTypeDefinitionAdapter();
			}
			@Override
			public Adapter caseEcoreImport(EcoreImport object) {
				return createEcoreImportAdapter();
			}
			@Override
			public Adapter caseIEImports(IEImports object) {
				return createIEImportsAdapter();
			}
			@Override
			public Adapter caseIQualifiedName(IQualifiedName object) {
				return createIQualifiedNameAdapter();
			}
			@Override
			public Adapter caseIDescription(IDescription object) {
				return createIDescriptionAdapter();
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
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.Concept
	 * @generated
	 */
	public Adapter createConceptAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.concepts.ConceptImport <em>Concept Import</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptImport
	 * @generated
	 */
	public Adapter createConceptImportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.concepts.IActiveConcept <em>IActive Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.IActiveConcept
	 * @generated
	 */
	public Adapter createIActiveConceptAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.concepts.IImports <em>IImports</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.IImports
	 * @generated
	 */
	public Adapter createIImportsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition <em>IConcept Type Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition
	 * @generated
	 */
	public Adapter createIConceptTypeDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport <em>Ecore Import</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport
	 * @generated
	 */
	public Adapter createEcoreImportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.concepts.IEImports <em>IE Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.IEImports
	 * @generated
	 */
	public Adapter createIEImportsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.general.IQualifiedName <em>IQualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IQualifiedName
	 * @generated
	 */
	public Adapter createIQualifiedNameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.dlr.sc.virsat.model.dvlm.general.IDescription <em>IDescription</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.dlr.sc.virsat.model.dvlm.general.IDescription
	 * @generated
	 */
	public Adapter createIDescriptionAdapter() {
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

} //ConceptsAdapterFactory
