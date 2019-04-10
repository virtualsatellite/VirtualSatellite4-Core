/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural;


import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IApplicable For</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The applicable for is used to decide to which type of structural element something can be assigned to. SO it means that e.g. A Category Assignment can be assigned to a SystemComponent. If the Category is applicable for the StructuralElement the SystemComponent is typed with.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor#getApplicableFor <em>Applicable For</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor#isIsApplicableForAll <em>Is Applicable For All</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor#getCardinality <em>Cardinality</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getIApplicableFor()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IApplicableFor extends EObject {
	/**
	 * Returns the value of the '<em><b>Applicable For</b></em>' reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applicable For</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applicable For</em>' reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getIApplicableFor_ApplicableFor()
	 * @model
	 * @generated
	 */
	EList<StructuralElement> getApplicableFor();

	/**
	 * Returns the value of the '<em><b>Is Applicable For All</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Applicable For All</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Applicable For All</em>' attribute.
	 * @see #setIsApplicableForAll(boolean)
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getIApplicableFor_IsApplicableForAll()
	 * @model default="false"
	 * @generated
	 */
	boolean isIsApplicableForAll();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor#isIsApplicableForAll <em>Is Applicable For All</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Applicable For All</em>' attribute.
	 * @see #isIsApplicableForAll()
	 * @generated
	 */
	void setIsApplicableForAll(boolean value);

	/**
	 * Returns the value of the '<em><b>Cardinality</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cardinality</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cardinality</em>' attribute.
	 * @see #setCardinality(int)
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getIApplicableFor_Cardinality()
	 * @model default="0" unique="false"
	 * @generated
	 */
	int getCardinality();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.structural.IApplicableFor#getCardinality <em>Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cardinality</em>' attribute.
	 * @see #getCardinality()
	 * @generated
	 */
	void setCardinality(int value);

} // IApplicableFor
