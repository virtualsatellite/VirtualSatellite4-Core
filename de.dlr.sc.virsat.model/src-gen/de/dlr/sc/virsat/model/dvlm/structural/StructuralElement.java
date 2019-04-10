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

import de.dlr.sc.virsat.model.dvlm.inheritance.ICanInheritFrom;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElement#isIsRootStructuralElement <em>Is Root Structural Element</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getStructuralElement()
 * @model
 * @generated
 */
public interface StructuralElement extends IQualifiedName, IDescription, IApplicableFor, IConceptTypeDefinition, ICanInheritFrom {
	/**
	 * Returns the value of the '<em><b>Is Root Structural Element</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Root Structural Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Root Structural Element</em>' attribute.
	 * @see #setIsRootStructuralElement(boolean)
	 * @see de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage#getStructuralElement_IsRootStructuralElement()
	 * @model default="false"
	 * @generated
	 */
	boolean isIsRootStructuralElement();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElement#isIsRootStructuralElement <em>Is Root Structural Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Root Structural Element</em>' attribute.
	 * @see #isIsRootStructuralElement()
	 * @generated
	 */
	void setIsRootStructuralElement(boolean value);

} // StructuralElement
