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


import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ecore Import</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport#getImportedNsURI <em>Imported Ns URI</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport#getImportedGenModel <em>Imported Gen Model</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getEcoreImport()
 * @model
 * @generated
 */
public interface EcoreImport extends EObject {
	/**
	 * Returns the value of the '<em><b>Imported Ns URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imported Ns URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imported Ns URI</em>' attribute.
	 * @see #setImportedNsURI(String)
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getEcoreImport_ImportedNsURI()
	 * @model
	 * @generated
	 */
	String getImportedNsURI();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport#getImportedNsURI <em>Imported Ns URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Imported Ns URI</em>' attribute.
	 * @see #getImportedNsURI()
	 * @generated
	 */
	void setImportedNsURI(String value);

	/**
	 * Returns the value of the '<em><b>Imported Gen Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imported Gen Model</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imported Gen Model</em>' attribute.
	 * @see #setImportedGenModel(String)
	 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getEcoreImport_ImportedGenModel()
	 * @model
	 * @generated
	 */
	String getImportedGenModel();

	/**
	 * Sets the value of the '{@link de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport#getImportedGenModel <em>Imported Gen Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Imported Gen Model</em>' attribute.
	 * @see #getImportedGenModel()
	 * @generated
	 */
	void setImportedGenModel(String value);

} // EcoreImport
