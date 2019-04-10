/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.general;


import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IQualified Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This Interface provides a Qualified ID to the model.
 * It is needed for the categories and properties which will be translated into classes using the Concepts Framework. Therefore the qualified names should follow the java package and classes contract/regulations
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.general.IQualifiedName#getName <em>Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.general.IQualifiedName#getFullQualifiedName <em>Full Qualified Name</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.general.IQualifiedName#getShortName <em>Short Name</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.general.GeneralPackage#getIQualifiedName()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IQualifiedName extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>"de.dlr.sc.model.dvlm.noid"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.dlr.sc.virsat.model.dvlm.general.GeneralPackage#getIQualifiedName_Name()
	 * @model default="de.dlr.sc.model.dvlm.noid" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.general.IQualifiedName#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Full Qualified Name</b></em>' attribute.
	 * The default value is <code>"de.dlr.sc.model.dvlm.noid"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The FullQualifiedName (FQN) is the combination of the current name plus the names of the containers. In the Categories and properties we usually have FQN which follow the pattern of Concept.Category.Property .
	 * WARNING!!! This attribute cannot be implemented through the model by an EOperation. An EOperation cannot be set to be the ID of the class. In contrast this derived feature builds the id of this class and is important to  uniquely identify concepts and its contained elements for registration within a repository.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Full Qualified Name</em>' attribute.
	 * @see de.dlr.sc.virsat.model.dvlm.general.GeneralPackage#getIQualifiedName_FullQualifiedName()
	 * @model default="de.dlr.sc.model.dvlm.noid" id="true" required="true" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getFullQualifiedName();

	/**
	 * Returns the value of the '<em><b>Short Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Short Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Short Name</em>' attribute.
	 * @see #setShortName(String)
	 * @see de.dlr.sc.virsat.model.dvlm.general.GeneralPackage#getIQualifiedName_ShortName()
	 * @model default=""
	 * @generated
	 */
	String getShortName();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.general.IQualifiedName#getShortName <em>Short Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Short Name</em>' attribute.
	 * @see #getShortName()
	 * @generated
	 */
	void setShortName(String value);

} // IQualifiedName
