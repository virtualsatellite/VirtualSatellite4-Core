/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.validator

import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider
import de.dlr.sc.virsat.model.concept.generator.ImportManager
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.generator.IFileSystemAccess

class GenerateDeprecatedValidator extends AGeneratorGapGenerator<EObject> {
	
	override getPackage(Concept concept) {
		concept.name + "." + PACKAGE_FOLDER;
	}
	
	static def getAbstractClassName(EObject typeDefinition) {
		"AStructuralElementInstanceValidator";
	}
	
	override createAbstractClassFileName(Concept concept, EObject eObject) {
		concept.packageFolder + "/" + "AStructuralElementInstanceValidator.java"
	}
		
	public static val PACKAGE_FOLDER = "validator";
	
	override serializeModel(Concept concept, IFileSystemAccess fsa) {
		// ************************************************************************************
		// Old Class
		// ************************************************************************************
		var fileOutputAClass = createAbstractClass(concept, concept);
		fsa.generateFile(createAbstractClassFileName(concept, concept), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);
	}

	/**
	 *  This method just creates the package declaration. The concept name needs to fit to the package name  
	 */
	override protected declarePackage(String packageId) '''
	package «packageId»;
	'''
	
	/**
	 * Handle the import statements
	 */
	override protected declareImports(ImportManager importManager, Concept concept, EObject conceptPart, String conceptPackage) '''
	«IF !importManager.importedClasses.empty»
		«FOR clazz : importManager.importedClasses»
			import «clazz»;
		«ENDFOR»
  	«ENDIF»
	'''

	// *************************************************************************************
	// * generate class
	// *************************************************************************************
	
	/**
	 *	Entry method to write the class body 
	 */
	override protected declareAClass(Concept concept, EObject conceptPart, ImportManager importManager) '''
	«importManager.register(StructuralElementInstance)»
	«importManager.register("de.dlr.sc.virsat.build.validator.external.IStructuralElementInstanceValidator")»
	
	// *****************************************************************
	// * Class Declaration
	// *****************************************************************
	
	«ConceptGeneratorUtil.generateAClassHeader(concept)»
	@Deprecated
	public abstract class «conceptPart.abstractClassName» implements IStructuralElementInstanceValidator {

		@Override
		public boolean validate(StructuralElementInstance sei) {
			return true;
		}
	}
	'''
	
	override protected declareClass(Concept concept, EObject type, ImportManager manager) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override createConcreteClassFileName(Concept concept, EObject conceptPart) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
}