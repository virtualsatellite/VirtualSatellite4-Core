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


import de.dlr.sc.virsat.model.dvlm.general.IDescription;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relation Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.RelationInstance#getType <em>Type</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.RelationInstance#getReferences <em>References</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getRelationInstance()
 * @model
 * @generated
 */
public interface RelationInstance extends IUuid, IDescription, IName {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(GeneralRelation)
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getRelationInstance_Type()
	 * @model
	 * @generated
	 */
	GeneralRelation getType();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.structural.RelationInstance#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(GeneralRelation value);

	/**
	 * Returns the value of the '<em><b>References</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>References</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>References</em>' reference.
	 * @see #setReferences(StructuralElementInstance)
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getRelationInstance_References()
	 * @model
	 * @generated
	 */
	StructuralElementInstance getReferences();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.structural.RelationInstance#getReferences <em>References</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>References</em>' reference.
	 * @see #getReferences()
	 * @generated
	 */
	void setReferences(StructuralElementInstance value);

} // RelationInstance
