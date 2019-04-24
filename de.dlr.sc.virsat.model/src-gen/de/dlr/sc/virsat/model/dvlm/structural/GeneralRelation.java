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


import de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition;

import de.dlr.sc.virsat.model.dvlm.general.IDescription;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>General Relation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation#getReferencedType <em>Referenced Type</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getGeneralRelation()
 * @model
 * @generated
 */
public interface GeneralRelation extends IQualifiedName, IApplicableFor, IDescription, IConceptTypeDefinition {
	/**
	 * Returns the value of the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Type</em>' reference.
	 * @see #setReferencedType(StructuralElement)
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getGeneralRelation_ReferencedType()
	 * @model required="true"
	 * @generated
	 */
	StructuralElement getReferencedType();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation#getReferencedType <em>Referenced Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Type</em>' reference.
	 * @see #getReferencedType()
	 * @generated
	 */
	void setReferencedType(StructuralElement value);

} // GeneralRelation
