/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGenerator2
import org.eclipse.xtext.generator.IGeneratorContext

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
class EquationDSLGenerator implements IGenerator2 {
	
	override afterGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
	}
	
	override beforeGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
	}
	
	override doGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
	}
	
}
