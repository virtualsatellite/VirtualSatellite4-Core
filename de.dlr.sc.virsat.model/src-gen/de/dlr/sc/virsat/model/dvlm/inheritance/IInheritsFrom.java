/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.inheritance;


import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IInherits From</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This interface is used to tell a StucturalElementInstance of which other StructuralElementInstance it actually inherits from.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.inheritance.IInheritsFrom#getSuperSeis <em>Super Seis</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage#getIInheritsFrom()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IInheritsFrom extends EObject {
	/**
	 * Returns the value of the '<em><b>Super Seis</b></em>' reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Seis</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Seis</em>' reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage#getIInheritsFrom_SuperSeis()
	 * @model
	 * @generated
	 */
	EList<StructuralElementInstance> getSuperSeis();

} // IInheritsFrom
