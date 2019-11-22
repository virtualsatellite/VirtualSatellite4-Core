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


import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;

import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.model.dvlm.general.IDescription;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritsFrom;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getType <em>Type</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getRelationInstances <em>Relation Instances</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getChildren <em>Children</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getParent <em>Parent</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getStructuralElementInstance()
 * @model
 * @generated
 */
public interface StructuralElementInstance extends IUuid, IDescription, IName, IAssignedDiscipline, ICategoryAssignmentContainer, IInstance, IInheritsFrom {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(StructuralElement)
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getStructuralElementInstance_Type()
	 * @model
	 * @generated
	 */
	StructuralElement getType();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(StructuralElement value);

	/**
	 * Returns the value of the '<em><b>Relation Instances</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.structural.RelationInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relation Instances</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relation Instances</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getStructuralElementInstance_RelationInstances()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<RelationInstance> getRelationInstances();

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance}.
	 * It is bidirectional and its opposite is '{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getStructuralElementInstance_Children()
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getParent
	 * @model opposite="parent" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<StructuralElementInstance> getChildren();

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(StructuralElementInstance)
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getStructuralElementInstance_Parent()
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getChildren
	 * @model opposite="children" transient="false"
	 * @generated
	 */
	StructuralElementInstance getParent();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(StructuralElementInstance value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This method hands back all StructuralElement Instances which are nested by a TreeRelation
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<StructuralElementInstance> getDeepChildren();

} // StructuralElementInstance
