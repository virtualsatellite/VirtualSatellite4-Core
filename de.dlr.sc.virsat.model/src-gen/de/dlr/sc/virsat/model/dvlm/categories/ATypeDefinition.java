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


import de.dlr.sc.virsat.model.dvlm.concepts.IConceptTypeDefinition;

import de.dlr.sc.virsat.model.dvlm.general.IDescription;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AType Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * We use an abstract class for the types since it is considered to overide the operation for getting the types. This may allow to restrict and be more specific on the access of a specific PropertyInstance to its PropertyType.
 * <!-- end-model-doc -->
 *
 *
 * @see de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage#getATypeDefinition()
 * @model abstract="true"
 * @generated
 */
public interface ATypeDefinition extends IQualifiedName, IDescription, IConceptTypeDefinition {
} // ATypeDefinition
