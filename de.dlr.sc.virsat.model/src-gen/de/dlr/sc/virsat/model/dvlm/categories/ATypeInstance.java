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


import de.dlr.sc.virsat.model.dvlm.general.IComment;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AType Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * We use an abstract class for the types since it is considered to overide the operation for getting the types. This may allow to restrict and be more specific on the access of a specific PropertyInstance to its PropertyType.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage#getATypeInstance()
 * @model abstract="true"
 * @generated
 */
public interface ATypeInstance extends IUuid, IComment, IInstance, IInheritanceLink {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(ATypeDefinition)
	 * @see de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage#getATypeInstance_Type()
	 * @model required="true"
	 * @generated
	 */
	ATypeDefinition getType();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(ATypeDefinition value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Call this method to get a CategoryAssignmentContainer handed back for any TypeInstance. This method returns null in case it could not be found.
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	ICategoryAssignmentContainer getCategoryAssignmentContainer();

} // ATypeInstance
