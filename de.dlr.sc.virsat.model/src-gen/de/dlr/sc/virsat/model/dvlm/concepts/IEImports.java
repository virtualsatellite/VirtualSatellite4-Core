/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.dvlm.concepts;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IE Imports</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.IEImports#getEcoreImports <em>Ecore Imports</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getIEImports()
 * @model
 * @generated
 */
public interface IEImports extends EObject {
	/**
	 * Returns the value of the '<em><b>Ecore Imports</b></em>' containment reference list.
	 * The list contents are of type {@link de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ecore Imports</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ecore Imports</em>' containment reference list.
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getIEImports_EcoreImports()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<EcoreImport> getEcoreImports();

} // IEImports
