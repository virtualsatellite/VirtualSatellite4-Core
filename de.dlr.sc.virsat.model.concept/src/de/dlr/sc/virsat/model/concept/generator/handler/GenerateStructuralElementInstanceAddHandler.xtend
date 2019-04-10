/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
 package de.dlr.sc.virsat.model.concept.generator.handler

import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement
import org.eclipse.core.commands.IHandler
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.generator.IFileSystemAccess
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider
import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator
import de.dlr.sc.virsat.model.concept.generator.ImportManager
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil

class GenerateStructuralElementInstanceAddHandler extends AGeneratorGapGenerator<StructuralElement> {
	
	override protected getPackage(Concept concept) { 
		concept.name + ".ui.handler";
	}
	
	static def getConcreteClassName(StructuralElement structuralElement) {
		"AddStructuralElementInstance" + structuralElement.name.toFirstUpper + "Handler"
	}
	
	static def getAbstractClassName(StructuralElement structuralElement) {
		"AAddStructuralElementInstance" + structuralElement.name.toFirstUpper + "Handler"
	}
	
	override createConcreteClassFileName(Concept concept, StructuralElement structuralElement) {
		 "../../" + concept.name + ".ui/src/" + concept.packageFolder + "/" + structuralElement.concreteClassName + ".java"
	}
	
	override createAbstractClassFileName(Concept concept, StructuralElement structuralElement) {
		 "../../" + concept.name + ".ui/src-gen/" + concept.packageFolder + "/" + structuralElement.abstractClassName + ".java"
	}
	
	
	override serializeModel(Concept concept, IFileSystemAccess fsa) {
		var iterator = EcoreUtil.getAllContents(concept, true);
		
		iterator.forEach[  
			if (it instanceof StructuralElement) {
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
	 *  This method just creates the package declaration. The concept name needs to fit to the package name  
	 */
	override protected declarePackage(String packageId) '''
	package «packageId»;
	'''
	
	override declareImports(ImportManager importManager, Concept concept, StructuralElement conceptPart, String conceptPackage)  '''
	«IF !importManager.importedClasses.empty»
		«FOR clazz : importManager.importedClasses»
			import «clazz»;
		«ENDFOR»
  	«ENDIF»
	'''
	
	override protected declareClass(Concept concept, StructuralElement structuralElement, ImportManager manager) '''
	«ConceptGeneratorUtil.generateClassHeader(structuralElement)»
	public class «structuralElement.concreteClassName» extends «structuralElement.abstractClassName» {
	}
	'''
	
	override declareAClass(Concept concept, StructuralElement structuralElement, ImportManager importManager) '''
	import de.dlr.sc.virsat.project.ui.transactional.handler.ATransactionalAddStructuralElementHandler;
	
	«ConceptGeneratorUtil.generateAClassHeader(structuralElement)»
	public abstract class  «structuralElement.abstractClassName» extends ATransactionalAddStructuralElementHandler implements «importManager.serialize(IHandler)» {
	
		@Override
		protected String getConceptName() {
			return "«concept.name»";
		}
	
		@Override
		protected String getStructuralElementName() {
			return "«structuralElement.name»";
		}
	}
	'''
}

