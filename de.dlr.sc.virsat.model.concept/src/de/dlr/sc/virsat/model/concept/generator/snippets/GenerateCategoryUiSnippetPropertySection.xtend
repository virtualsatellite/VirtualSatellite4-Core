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
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition
import de.dlr.sc.virsat.model.dvlm.categories.Category
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import org.eclipse.xtext.generator.IFileSystemAccess

/**
 * This class generates the UiSnippets for the sections when you open a 
 * Category Assignment directly into an Editor rather than  looking to it
 * in the table of its container class such as the StructuralElementInstance
 */
class GenerateCategoryUiSnippetPropertySection extends AGeneratorGapGenerator<Category> {
	
	override protected getPackage(Concept concept) {
		concept.name + ".ui.snippet";
	}
	
	static def getConcreteClassName(Category typeDefinition) {
		"UiSnippetSection" + typeDefinition.name.toFirstUpper;
	}
	
	static def getAbstractClassName(Category typeDefinition) {
		"AUiSnippetSection" + typeDefinition.name.toFirstUpper;
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
	override protected declareImports(ImportManager importManager, Concept concept, Category conceptPart,String conceptPackage) '''
	«IF !importManager.importedClasses.empty»
		«FOR clazz : importManager.importedClasses»
			import «clazz»;
		«ENDFOR»
  	«ENDIF»
	'''
	
	override protected declareClass(Concept concept, Category category, ImportManager importManager) '''
	«importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet")»
	
	«ConceptGeneratorUtil.generateClassHeader(category)»
	public class «category.concreteClassName» extends «category.abstractClassName» implements IUiSnippet {
	}
	'''
	
	/**
	 * This method declares the non Table UI Snippet with the Section for all properties
	 */
	override protected declareAClass(Concept concept, Category category, ImportManager importManager) '''
	«importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetGenericPropertyInstances")»
	«importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet")»
	
	«ConceptGeneratorUtil.generateAClassHeader(category)»
	public abstract class «category.abstractClassName» extends AUiSnippetGenericPropertyInstances implements IUiSnippet {
		public «category.abstractClassName»() {
			super("«concept.name»", "«category.name»");
		}
	}
	'''

	/**
	 * This method hands back the correct reference depending
	 * on if it is a composed or reference property.  
	 */
	def getArrayType(AProperty arrayProperty) {
		if (arrayProperty instanceof ComposedProperty) {
			val cp = arrayProperty as ComposedProperty;
			return cp.type;
		} else if (arrayProperty instanceof ReferenceProperty) {
			val rp = arrayProperty as ReferenceProperty;
			return  rp.referenceType;
		}
		return null;
	}	
	
	static def createUiSnippetSectionName(ATypeDefinition typeDefinition) {
		return "UiSnippetSection" + typeDefinition.name.toFirstUpper;
	}
}
