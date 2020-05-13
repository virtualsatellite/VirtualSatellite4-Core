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
import de.dlr.sc.virsat.model.dvlm.validator.IStructuralElementInstanceValidator

class GenerateValidator extends AGeneratorGapGenerator<EObject> {
	
	override protected getPackage(Concept concept) {
		concept.name + "." + PACKAGE_FOLDER;
	}
	
	/**
	 * This method gets the concept name which is usually in a form like this "de.dlr.sc.virsat.model.extension.conceptName". 
	 * It splits the name after every dot and only takes the last element as the name.
	 * The first letter is capitalized, since it will be used as a class name.
	 */
	static def getValidatorName(Concept concept) {
		val name = concept.name
		val String[] arrOfName = name.split("\\.")
		val shortName = arrOfName.last
		
		val validatorName = shortName.substring(0,1).toUpperCase() + shortName.substring(1) 
		return validatorName + "Validator";
	}
	
	static def getConcreteClassName(Concept concept) {
		getValidatorName(concept);
	}
	
	static def getAbstractClassName(Concept concept) {
		"A" + getValidatorName(concept);
	}
	
	override createConcreteClassFileName(Concept concept, EObject eObject) {
		 concept.packageFolder + "/" + concept.concreteClassName + ".java"
	}
	
	override createAbstractClassFileName(Concept concept, EObject eObject) {
		 concept.packageFolder + "/" + concept.abstractClassName + ".java"
	}
	
	public static val PACKAGE_FOLDER = "validator";
	
	override serializeModel(Concept concept, IFileSystemAccess fsa) {
		// ************************************************************************************
		// Abstract Class
		// ************************************************************************************
		var fileOutputAClass = createAbstractClass(concept, concept);
		fsa.generateFile(createAbstractClassFileName(concept, concept), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);

		// ************************************************************************************
		// Concrete Class
		// ************************************************************************************
		var fileOutputClass = createConcreteClass(concept, concept);
		fsa.generateFile(createConcreteClassFileName(concept, concept), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
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
	«importManager.register(IStructuralElementInstanceValidator)»
	
	// *****************************************************************
	// * Class Declaration
	// *****************************************************************
	
	«ConceptGeneratorUtil.generateAClassHeader(concept)»
	public abstract class «concept.abstractClassName» implements IStructuralElementInstanceValidator {

		@Override
		public boolean validate(StructuralElementInstance sei) {
			return true;
		}
	}
	'''

	/**
	 *	Entry method to write the class body 
	 */
	override protected declareClass(Concept concept, EObject conceptPart, ImportManager importManager) '''
	«importManager.register(StructuralElementInstance)»
	«importManager.register("de.dlr.sc.virsat.build.validator.external.IStructuralElementInstanceValidator")»
	
	// *****************************************************************
	// * Class Declaration
	// *****************************************************************
	
	«ConceptGeneratorUtil.generateClassHeader(concept)»
	public class «concept.concreteClassName» extends «concept.abstractClassName» implements IStructuralElementInstanceValidator {

		@Override
		public boolean validate(StructuralElementInstance sei) {
			//TODO: Implement custom validation for concept
			return super.validate(sei);
		}
	}
	'''
}