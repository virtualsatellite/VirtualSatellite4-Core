/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.concepts;


import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IConcept Type Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This interface is needed to detect special Types when importing a concept to a repository.
 * If the importer imports a reference, they are bound to their xmi representation. So if Concept A is referencing objects in Concept B, this needs to be taken into account. But the import has to bend the references to the ConceptA in the Repository and not the original XMI.
 * <!-- end-model-doc -->
 *
 *
 * @see de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage#getIConceptTypeDefinition()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IConceptTypeDefinition extends IQualifiedName {
} // IConceptTypeDefinition
