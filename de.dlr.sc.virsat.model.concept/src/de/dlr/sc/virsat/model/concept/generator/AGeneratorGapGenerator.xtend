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

import org.eclipse.emf.ecore.EObject
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil

abstract class AGeneratorGapGenerator<TYPE extends EObject> implements IGeneratorGapGenerator<TYPE> {
	
	/**
	 * This method hands back the Package as folder. It replaces the . with /
	 */
	def getPackageFolder(Concept concept) {
		concept.package.replace(".","/");
	}
	
	override createConcreteClass(Concept concept, TYPE conceptPart) {
		val imCClass = new ImportManager(concept.package)
	
		val bodyClass = declareClass(concept, conceptPart, imCClass)
		var fileOutputClass = '''
		«ConceptGeneratorUtil.generateFileHeader»
		«declarePackage(concept.package)»
		
		«declareImports(imCClass, concept, conceptPart, concept.package)»
		
		«bodyClass»
		'''
		return fileOutputClass;
	}
	
	override createAbstractClass(Concept concept, TYPE conceptPart) {
		val imAClass = new ImportManager(concept.package)
		val bodyAClass = declareAClass(concept, conceptPart, imAClass)
		var fileOutputAClass = '''
		«ConceptGeneratorUtil.generateFileHeader»
		«declarePackage(concept.package)»
		
		«declareImports(imAClass, concept, conceptPart, concept.package)»
		
		«bodyAClass»
		'''
		return fileOutputAClass;
	}
	
	protected def String getPackage(Concept concept);
	
	protected def CharSequence declareImports(ImportManager importManager, Concept concept, TYPE type, String conceptPackage);
	
	protected def CharSequence declarePackage(String packageId);
	
	protected def CharSequence declareClass(Concept concept, TYPE type, ImportManager manager)
	
	protected def CharSequence declareAClass(Concept concept, TYPE type, ImportManager manager)
	
}