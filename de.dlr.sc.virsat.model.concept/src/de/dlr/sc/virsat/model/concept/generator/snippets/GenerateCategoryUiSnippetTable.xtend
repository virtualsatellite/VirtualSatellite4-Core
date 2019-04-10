/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.snippets

import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider
import de.dlr.sc.virsat.model.concept.generator.ImportManager
import de.dlr.sc.virsat.model.concept.generator.commands.GenerateCategoryCreateAddCommand
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition
import de.dlr.sc.virsat.model.dvlm.categories.Category
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import org.eclipse.emf.common.command.Command
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.xtext.generator.IFileSystemAccess

/**
 * This class generates the UiSnippets for the categories that
 * are defined by the concept. It generates the classes needed for displaying
 * the category as a Table within the Generic Editor.
 */
class GenerateCategoryUiSnippetTable extends AGeneratorGapGenerator<Category> {
	
	override protected getPackage(Concept concept) {
		concept.name + ".ui.snippet";
	}
	
	static def getConcreteClassName(ATypeDefinition typeDefinition) {
		"UiSnippetTable" + typeDefinition.name.toFirstUpper;
	}
	
	static def getAbstractClassName(ATypeDefinition typeDefinition) {
		"AUiSnippetTable" + typeDefinition.name.toFirstUpper;
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
	
	/**
	 * Handle the import statements
	 */
	override protected declareImports(ImportManager importManager, Concept concept, Category conceptPart, String conceptPackage) '''
	«IF !importManager.importedClasses.empty»
		«FOR clazz : importManager.importedClasses»
			import «clazz»;
		«ENDFOR»
  	«ENDIF»
	'''
	
	override protected declareClass(Concept concept, Category category, ImportManager importManager) '''
	// *****************************************************************
	// * Class Declaration
	// *****************************************************************
	
	«ConceptGeneratorUtil.generateClassHeader(category)»
	public class «category.concreteClassName» extends «category.abstractClassName» {
	}
	'''
	
	/**
	 * This method declares the standard table that is displayed on system element level
	 */
 	override protected declareAClass(Concept concept, Category category, ImportManager importManager) '''
	«importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetGenericCategoryAssignmentTable")»
	«importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet")»
	«importManager.register(concept.name +".ui.command." + GenerateCategoryCreateAddCommand.getConcreteClassName(category))»;
	«importManager.register(Command)»
	«importManager.register(EditingDomain)»
	«importManager.register(Concept)»
	
	«ConceptGeneratorUtil.generateAClassHeader(category)»
	public abstract class «category.abstractClassName» extends AUiSnippetGenericCategoryAssignmentTable implements IUiSnippet {
		public «category.abstractClassName»() {
			super("«concept.name»",
				"«category.name»",
				"«category.fullQualifiedName»",
				STYLE_ADD_BUTTON | STYLE_REMOVE_BUTTON | STYLE_EDITOR_BUTTON);
		}

		@Override
		protected Command createAddCommand(EditingDomain editingDomain, Concept activeConcept) {
			return new «GenerateCategoryCreateAddCommand.getConcreteClassName(category)»().create(editingDomain, model, activeConcept);
		}
	}
	'''
}
