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


import de.dlr.sc.virsat.model.dvlm.categories.Category;

import de.dlr.sc.virsat.model.dvlm.general.IDescription;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;

import de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concept</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#getCategories <em>Categories</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#getStructuralElements <em>Structural Elements</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#getRelations <em>Relations</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#getVersion <em>Version</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#isDMF <em>DMF</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#isBeta <em>Beta</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getConcept()
 * @model
 * @generated
 */
public interface Concept extends IQualifiedName, IDescription, IActiveConcept, IImports, IEImports {
	/**
	 * Returns the value of the '<em><b>Categories</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.categories.Category}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Categories</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Categories</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getConcept_Categories()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Category> getCategories();

	/**
	 * Returns the value of the '<em><b>Structural Elements</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Structural Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Structural Elements</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getConcept_StructuralElements()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<StructuralElement> getStructuralElements();

	/**
	 * Returns the value of the '<em><b>Relations</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relations</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getConcept_Relations()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<GeneralRelation> getRelations();

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getConcept_Version()
	 * @model default="1.0"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>DMF</b></em>' attribute.
	 * The default value is <code>"FALSE"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>DMF</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>DMF</em>' attribute.
	 * @see #setDMF(boolean)
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getConcept_DMF()
	 * @model default="FALSE"
	 * @generated
	 */
	boolean isDMF();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#isDMF <em>DMF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>DMF</em>' attribute.
	 * @see #isDMF()
	 * @generated
	 */
	void setDMF(boolean value);

	/**
	 * Returns the value of the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Name</em>' attribute.
	 * @see #setDisplayName(String)
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getConcept_DisplayName()
	 * @model
	 * @generated
	 */
	String getDisplayName();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#getDisplayName <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Name</em>' attribute.
	 * @see #getDisplayName()
	 * @generated
	 */
	void setDisplayName(String value);

	/**
	 * Returns the value of the '<em><b>Beta</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Beta</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Beta</em>' attribute.
	 * @see #setBeta(boolean)
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getConcept_Beta()
	 * @model
	 * @generated
	 */
	boolean isBeta();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.concepts.Concept#isBeta <em>Beta</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Beta</em>' attribute.
	 * @see #isBeta()
	 * @generated
	 */
	void setBeta(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * returns all categories from the concept which are not abstract.
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<Category> getNonAbstractCategories();

} // Concept
