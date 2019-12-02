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
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.generator.IFileSystemAccess
import de.dlr.sc.virsat.model.concept.types.factory.BeanRegistry

/**
 * This class generates the test cases for the StructruralElements
 */
class GenerateStructuralElementTests extends AGeneratorGapGenerator<StructuralElement> {
	
	static def getConcreteClassName(StructuralElement typeDefinition) {
		typeDefinition.name.toFirstUpper + "Test"
	}
	
	static def getAbstractClassName(StructuralElement typeDefinition) {
		"A" + typeDefinition.name.toFirstUpper + "Test"
	}
	
	override createConcreteClassFileName(Concept concept, StructuralElement conceptPart) {
		"../../" + concept.name + ".test/src/" + concept.packageFolder + "/" +conceptPart.concreteClassName + ".java"
	}
	
	override createAbstractClassFileName(Concept concept, StructuralElement conceptPart) {
		"../../" + concept.name + ".test/src-gen/" + concept.packageFolder + "/" +conceptPart.abstractClassName + ".java"
	}
	
	override protected getPackage(Concept concept) {
		concept.name + "." + PACKAGE_FOLDER;
	}
	
	public static val PACKAGE_FOLDER = BeanRegistry.BEAN_PACKAGE_NAME;
	
	override serializeModel(Concept concept, IFileSystemAccess fsa) {
		var iterator = EcoreUtil.getAllContents(concept, true);
		//System.out.println("hello in here");
		
		iterator.forEach[  
			if (it instanceof StructuralElement) {
				
				//System.out.println("hello in there for" + it);
				
				// ************************************************************************************
				// Abstract Class
				// ************************************************************************************
				var fileOutputAClass = createAbstractClass(concept, it);
				fsa.generateFile(createAbstractClassFileName(concept, it), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);

				// ************************************************************************************
				// Concrete Class
				// ************************************************************************************
				var fileOutputClass = createConcreteClass(concept, it);
				fsa.generateFile(createConcreteClassFileName(concept, it), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
			}
		]	
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
	override declareImports(ImportManager importManager, Concept concept, StructuralElement conceptPart, String conceptPackage) '''
	// *****************************************************************
	// * Import Statements
	// *****************************************************************
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
	override declareAClass(Concept concept, StructuralElement se, ImportManager importManager) '''
	«importManager.register(Exception)»
	«importManager.register(Concept)»
	
	// *****************************************************************
	// * Class Declaration
	// *****************************************************************
	
	import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertNotNull;
	import static org.junit.Assert.assertNull;
	
	import org.junit.After;
	import org.junit.Before;
	import org.junit.Test;
	
	«ConceptGeneratorUtil.generateAClassHeader(se)»
	public abstract class A«se.name.toFirstUpper»Test {
	
		protected Concept concept;
		
		@Before
		public void setUp() throws Exception {
			String conceptXmiPluginPath = "«concept.name»/concept/concept.xmi";
			concept = de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPath);
		}
	
		@After
		public void tearDown() throws Exception {
		}
		
		«declareConstructorTest(se, importManager)»
	}
	'''

	override declareClass(Concept concept, StructuralElement se, ImportManager importManager) '''
	// *****************************************************************
	// * Class Declaration
	// *****************************************************************
	
	«ConceptGeneratorUtil.generateClassHeader(se)»
	public class «se.name.toFirstUpper»Test extends A«se.name.toFirstUpper»Test {
		
	}
	'''

	// *************************************************************************************
	// * generate constructors
	// *************************************************************************************
	/**
	 * Method to create the constructor of the java category bean
	 */		
	protected def declareConstructorTest(StructuralElement se, ImportManager importManager) '''
	«importManager.register(StructuralElementInstance)»
	«importManager.register(StructuralFactory)»
	«importManager.register(Concept)»
	«importManager.register(se)»
		
	// *****************************************************************
	// * Constructor Test Cases
	// *****************************************************************

	@Test
	public void test«se.name.toFirstUpper»() {
		«se.name.toFirstUpper» test«se.name.toFirstUpper» = new «se.name.toFirstUpper»();

		assertNull("There is no internal DVLM object", test«se.name.toFirstUpper».getStructuralElementInstance());
	}

	@Test
	public void test«se.name.toFirstUpper»Concept() {
		«se.name.toFirstUpper» test«se.name.toFirstUpper» = new «se.name.toFirstUpper»(concept);
		
		assertNotNull("There is an internal DVLM object", test«se.name.toFirstUpper».getStructuralElementInstance());
	}

	@Test
	public void test«se.name.toFirstUpper»StructuralElementInstance() {
		StructuralElementInstance testSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		«se.name.toFirstUpper» test«se.name.toFirstUpper» = new «se.name.toFirstUpper»(testSei);
		
		assertEquals("DVLM object has been set as specified", testSei, test«se.name.toFirstUpper».getStructuralElementInstance());
	}

	'''
}

