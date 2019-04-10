/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
 package de.dlr.sc.virsat.model.concept.generator.beans

import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance
import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider
import de.dlr.sc.virsat.model.concept.generator.ImportManager
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.generator.IFileSystemAccess
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory
import de.dlr.sc.virsat.model.concept.types.factory.BeanRegistry

/**
 * This class is the generator for the StructuralElement beans of our model extension.
 * The beans will give easier access to the model of StructuralElements and their underly Category Assignemtns
 * Furthermore it will provide strong typing
 */
class GenerateStructuralElementBeans extends AGeneratorGapGenerator<StructuralElement> {
	
	static def getConcreteClassName(StructuralElement typeDefinition) {
		typeDefinition.name.toFirstUpper
	}
	
	static def getAbstractClassName(StructuralElement typeDefinition) {
		"A" + typeDefinition.name.toFirstUpper
	}
	
	override createConcreteClassFileName(Concept concept, StructuralElement conceptPart) {
		concept.packageFolder + "/" +conceptPart.concreteClassName + ".java"
	}
	
	override createAbstractClassFileName(Concept concept, StructuralElement conceptPart) {
		concept.packageFolder + "/" +conceptPart.abstractClassName + ".java"
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
	«importManager.register(ABeanStructuralElementInstance)»
	«importManager.register(IBeanStructuralElementInstance)»
	
	// *****************************************************************
	// * Class Declaration
	// *****************************************************************
	
	«ConceptGeneratorUtil.generateAClassHeader(se)»
	public abstract class A«se.name.toFirstUpper» extends ABeanStructuralElementInstance implements IBeanStructuralElementInstance {

		«val fullQualifiedSeId = ActiveConceptHelper.getFullQualifiedId(se)»
		public static final String FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME = "«fullQualifiedSeId»";
		
		/**
	 	* Call this method to get the full qualified name of the underlying Structural Element
	 	* @return The FQN of the StructuralElement as String
	 	*/
		public String getFullQualifiedSturcturalElementName() {
			return FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME;
		}
		
		«declareConstructor(se, importManager)»
		
	}
	'''

	override declareClass(Concept concept, StructuralElement se, ImportManager importManager) '''
	«importManager.register(StructuralElementInstance)»
	«importManager.register(Concept)»
	// *****************************************************************
	// * Class Declaration
	// *****************************************************************
	
	«ConceptGeneratorUtil.generateClassHeader(se)»
	public class «se.name.toFirstUpper» extends A«se.name.toFirstUpper» {
		
		/**
		 * Constructor of Concept Class
		 */
		public «se.name.toFirstUpper»() {
			super();
		}

		/**
	 	 * Constructor of Concept Class
	 	 * @param concept The concept from where to initialize
	 	 */
		public «se.name.toFirstUpper»(Concept concept) {
			super(concept);
		}	

		/**
	 	 * Constructor of Concept Class that can be initialized manually by a given StructuralElementInstance
		 * @param sei The StructuralElementInstance to be used for background initialization of the StructuralElementInstance bean
		 */
		public «se.name.toFirstUpper»(StructuralElementInstance sei) {
			super(sei);
		}
	}
	'''

	// *************************************************************************************
	// * generate constructors
	// *************************************************************************************
	/**
	 * Method to create the constructor of the java category bean
	 */		
	protected def declareConstructor(StructuralElement se, ImportManager importManager) '''
	«importManager.register(StructuralElementInstance)»
	«importManager.register(StructuralElement)»
	«importManager.register(StructuralFactory)»
	«importManager.register(ActiveConceptHelper)»
	«importManager.register(Concept)»
	// *****************************************************************
	// * Class Constructors
	// *****************************************************************
	
	/**
	 * Constructor of Concept Class
	 */
	public A«se.name.toFirstUpper»() {
	}

	/**
	 * Constructor of Concept Class
	 * @param concept The concept from where to initialize
	 */
	public A«se.name.toFirstUpper»(Concept concept) {
		StructuralElement seFromActiveConcept = ActiveConceptHelper.getStructuralElement(concept, "«se.name»");
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(seFromActiveConcept);
		setStructuralElementInstance(sei);
	}
	
	/**
	 * Constructor of Concept Class that can be initialized manually by a given StructuralElementInstance
	 * @param sei The StructuralElementInstance to be used for background initialization of the StructuralElementInstance bean
	 */
	public A«se.name.toFirstUpper»(StructuralElementInstance sei) {
		setStructuralElementInstance(sei);
	}

	'''
}

