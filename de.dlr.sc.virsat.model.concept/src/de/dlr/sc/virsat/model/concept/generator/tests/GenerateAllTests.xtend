/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.tests

import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider
import de.dlr.sc.virsat.model.concept.generator.ImportManager
import de.dlr.sc.virsat.model.dvlm.categories.Category
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement
import java.util.ArrayList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.generator.IFileSystemAccess
import de.dlr.sc.virsat.model.concept.generator.validator.GenerateValidator

/**
 * This class generates the test cases for the StructruralElements
 */
class GenerateAllTests extends AGeneratorGapGenerator<EObject> {
	
	static def getConcreteClassName(StructuralElement typeDefinition) {
		typeDefinition.name.toFirstUpper
	}
	
	static def getAbstractClassName(StructuralElement typeDefinition) {
		"A" + typeDefinition.name.toFirstUpper
	}
	
	override createConcreteClassFileName(Concept concept, EObject conceptPart) {
		"../../" + concept.name + ".test/src/" + concept.packageFolder + "/" + "AllTests.java"
	}
	
	override createAbstractClassFileName(Concept concept, EObject conceptPart) {
		"../../" + concept.name + ".test/src-gen/" + concept.packageFolder + "/" + "AllTestsGen.java"
	}
	
	override protected getPackage(Concept concept) {
		concept.name + "." + PACKAGE_FOLDER;
	}
	
	public static val PACKAGE_FOLDER = "test";
	
	override serializeModel(Concept concept, IFileSystemAccess fsa) {
		// ************************************************************************************
		// Abstract Class
		// ************************************************************************************
		var fileOutputAClass = createAbstractClass(concept, null);
		fsa.generateFile(createAbstractClassFileName(concept, null), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);

		// ************************************************************************************
		// Concrete Class
		// ************************************************************************************
		var fileOutputClass = createConcreteClass(concept, null);
		fsa.generateFile(createConcreteClassFileName(concept, null), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
	}

	/**
	 * Declare the package statement of the java file 
	 */
	override declarePackage(String pluginPackage) '''
	package «pluginPackage»;
	'''
	
	// *************************************************************************************
	// * generate imports
	// *************************************************************************************
	
	/**
	 * Write down all the import statements needed by this java file
	 */
	override declareImports(ImportManager importManager, Concept concept, EObject conceptPart, String conceptPackage) '''
	// *****************************************************************
	// * Import Statements
	// *****************************************************************
	
	import org.junit.runner.RunWith;
	import org.junit.runners.Suite;
	import org.junit.runners.Suite.SuiteClasses;
	
	import junit.framework.JUnit4TestAdapter;
	
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
	override declareAClass(Concept concept, EObject se, ImportManager importManager) '''
	/**
	 * 
	 */
	@RunWith(Suite.class)
	
	@SuiteClasses({
		«listAllGeneratedTests(concept, importManager)»
					})
	
	/**
	 * 
	 * Test Collection
	 *
	 */
	public class AllTestsGen {
	
		/**
		 * Constructor
		 */
		private AllTestsGen() {
		}
		
		/**
		 * Test Adapter
		 * @return Executable JUnit Tests
		 */
		public static junit.framework.Test suite() {
			return new JUnit4TestAdapter(AllTests.class);
		}	
	}
	'''

	override declareClass(Concept concept, EObject se, ImportManager importManager) '''
	/**
	 * 
	 */
	@RunWith(Suite.class)
	
	@SuiteClasses({		
					})
	
	/**
	 * 
	 * Test Collection
	 *
	 */
	public class AllTests {
	
		/**
		 * Constructor
		 */
		private AllTests() {
		}
		
		/**
		 * Test Adapter
		 * @return Executable JUnit Tests
		 */
		public static junit.framework.Test suite() {
			return new JUnit4TestAdapter(AllTests.class);
		}	
	}
	'''
	
	def listAllGeneratedTests(Concept concept, ImportManager importManager) {
		var iterator = EcoreUtil.getAllContents(concept, true);
		
		val listOfTests = new ArrayList<String>;
		
		iterator.forEach[  
			if (it instanceof Category) {
				var category = it as Category;
				if (!category.isAbstract) {
					listOfTests.add(importManager.serializeTest(category))
				}
			} else if (it instanceof StructuralElement) {
				var se = it as StructuralElement;
				listOfTests.add(importManager.serializeTest(se))
			} 
		]
		
		val allMigratorsReader = new AllMigratorsReader().init(concept.fullQualifiedName);
		allMigratorsReader.migrators.forEach[
			importManager.register(concept.fullQualifiedName + ".migrator." + it + "Test")
			listOfTests.add(it + "Test")
		]
		
		/**
		 *	Add ValidatorTest to import and SuiteClasses
		 */
		
		importManager.register(concept.fullQualifiedName + ".validator." + GenerateValidator.getValidatorName(concept) + "Test")
		listOfTests.add(GenerateValidator.getValidatorName(concept) + "Test")
			
		return '''
		«FOR test : listOfTests»
		«test».class,
		«ENDFOR»
		'''
	}
}
