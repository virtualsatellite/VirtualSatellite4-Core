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

import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider
import de.dlr.sc.virsat.model.concept.generator.ImportManager
import de.dlr.sc.virsat.model.concept.generator.commands.GenerateCategoryCreateAddCommand
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil
import de.dlr.sc.virsat.model.dvlm.categories.Category
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import org.eclipse.emf.common.command.Command
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.xtext.generator.IFileSystemAccess

class GenerateCategoryAddHandler extends AGeneratorGapGenerator<Category> {
	
	override protected getPackage(Concept concept) { 
		concept.name + ".ui.handler";
	}
	
	static def getConcreteClassName(Category category) {
		"Add" + category.name.toFirstUpper + "Handler"
	}
	
	static def getAbstractClassName(Category category) {
		"AAdd" + category.name.toFirstUpper + "Handler"
	}
	
	override createConcreteClassFileName(Concept concept, Category category) {
		 "../../" + concept.name + ".ui/src/" + concept.packageFolder + "/" + category.concreteClassName + ".java"
	}
	
	override createAbstractClassFileName(Concept concept, Category category) {
		 "../../" + concept.name + ".ui/src-gen/" + concept.packageFolder + "/" + category.abstractClassName + ".java"
	}
	
	override serializeModel(Concept concept, IFileSystemAccess fsa) {
		concept.nonAbstractCategories.forEach[
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
		]
	}

	/**
	 *  This method just creates the package declaration. The concept name needs to fit to the package name  
	 */
	override protected declarePackage(String packageId) '''
	package «packageId»;
	'''
	
	override declareImports(ImportManager importManager, Concept concept, Category conceptPart, String conceptPackage)  '''
	«IF !importManager.importedClasses.empty»
		«FOR clazz : importManager.importedClasses»
			import «clazz»;
		«ENDFOR»
  	«ENDIF»
	'''

	override protected declareClass(Concept concept, Category category, ImportManager manager) '''
	«ConceptGeneratorUtil.generateClassHeader(category)»
	public class «category.concreteClassName» extends «category.abstractClassName» {
	}
	'''
	
	override declareAClass(Concept concept, Category category, ImportManager importManager) '''
	import de.dlr.sc.virsat.project.ui.transactional.handler.ATransactionalAddCategoryHandler;
	import «concept.name».ui.command.«GenerateCategoryCreateAddCommand.getConcreteClassName(category)»;

	«ConceptGeneratorUtil.generateAClassHeader(category)»
	public abstract class «category.abstractClassName» extends ATransactionalAddCategoryHandler {
		
		@Override
		protected String getConceptName() {
			return "«concept.name»";
		}
		
		@Override
		protected «importManager.serialize(Command)» createAddCommand(«importManager.serialize(EditingDomain)» editingDomain, «importManager.serialize(EObject)» owner, «importManager.serialize(Concept)» activeConcept) {
			return new «GenerateCategoryCreateAddCommand.getConcreteClassName(category)»().create(editingDomain, owner, activeConcept);
		}
	}
	'''
}

