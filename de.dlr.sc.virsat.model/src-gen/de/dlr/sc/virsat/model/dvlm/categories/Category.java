/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories;


import de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionInput;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionSectionContainer;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;

import de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.Category#getProperties <em>Properties</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.Category#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.Category#getExtendsCategory <em>Extends Category</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage#getCategory()
 * @model
 * @generated
 */
public interface Category extends ATypeDefinition, IApplicableFor, IEquationDefinitionSectionContainer, IEquationDefinitionInput {
	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage#getCategory_Properties()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<AProperty> getProperties();

	/**
	 * Returns the value of the '<em><b>Is Abstract</b></em>' attribute.
	 * The default value is <code>"FALSE"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Abstract</em>' attribute.
	 * @see #setIsAbstract(boolean)
	 * @see de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage#getCategory_IsAbstract()
	 * @model default="FALSE"
	 * @generated
	 */
	boolean isIsAbstract();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.categories.Category#isIsAbstract <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Abstract</em>' attribute.
	 * @see #isIsAbstract()
	 * @generated
	 */
	void setIsAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Extends Category</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extends Category</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extends Category</em>' reference.
	 * @see #setExtendsCategory(Category)
	 * @see de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage#getCategory_ExtendsCategory()
	 * @model
	 * @generated
	 */
	Category getExtendsCategory();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.categories.Category#getExtendsCategory <em>Extends Category</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extends Category</em>' reference.
	 * @see #getExtendsCategory()
	 * @generated
	 */
	void setExtendsCategory(Category value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This method hands back all properties of the catgeory including the ones from super (extends) categories
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='EList&lt;AProperty&gt; allProperties = new org.eclipse.emf.common.util.BasicEList&lt;&gt;();\r\nallProperties.addAll(getProperties());\r\n\r\n// In case this category inherits from another one (extends another category) that their properties should eb added as well\r\nif (extendsCategory != null) {\r\n\tallProperties.addAll(extendsCategory.getAllProperties());\r\n}\r\n\t\r\nreturn allProperties;'"
	 * @generated
	 */
	EList<AProperty> getAllProperties();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This method returns true iff it is an instance of the passed typeDefinition
	 * <!-- end-model-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='Category category = this;\r\n\t\t\r\nwhile (category != null) {\r\n\tif (category.equals(typeDefinition)) {\r\n\t\treturn true;\r\n\t}\r\n\t\t\t\r\n\tcategory = category.getExtendsCategory();\r\n}\r\n\t\t\r\nreturn false;'"
	 * @generated
	 */
	boolean isExtensionOf(ATypeDefinition typeDefinition);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This method hands back all equation definitions of the catgeory including the ones from super (extends) categories
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='EList&lt;EquationDefinition&gt; allEquationDefinitions = new org.eclipse.emf.common.util.BasicEList&lt;&gt;();\r\nallEquationDefinitions.addAll(getEquationDefinitions());\r\n\r\n// In case this category inherits from another one (extends another category) that their equation definitions should be added as well\r\nif (extendsCategory != null) {\r\n\tallEquationDefinitions.addAll(extendsCategory.getAllEquationDefinitions());\r\n}\r\n\t\r\nreturn allEquationDefinitions;'"
	 * @generated
	 */
	EList<EquationDefinition> getAllEquationDefinitions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='// If the cardinality is 0, check if we are extending from some category and then take their cardinality\r\nreturn cardinality == 0 &amp;&amp; getExtendsCategory() != null ? getExtendsCategory().getCardinality() : cardinality;'"
	 * @generated
	 */
	int getCardinality();

} // Category
