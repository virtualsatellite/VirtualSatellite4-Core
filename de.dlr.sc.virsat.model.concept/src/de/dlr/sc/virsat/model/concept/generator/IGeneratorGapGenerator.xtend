/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator

import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.generator.IFileSystemAccess

interface IGeneratorGapGenerator<TYPE extends EObject> {
	
	def void serializeModel(Concept concept, IFileSystemAccess fsa);
	
	def String createConcreteClassFileName(Concept concept, TYPE conceptPart);
	
	def String createAbstractClassFileName(Concept concept, TYPE conceptPart);
	
	def CharSequence createConcreteClass(Concept concept, TYPE conceptPart);
	
	def CharSequence createAbstractClass(Concept concept, TYPE conceptPart);
	
}