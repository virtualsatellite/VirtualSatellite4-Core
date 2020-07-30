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
import de.dlr.sc.virsat.model.concept.generator.commands.GenerateArrayCreateAddCommand
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil
import de.dlr.sc.virsat.model.dvlm.categories.Category
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.DynamicArrayModifier
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import org.eclipse.emf.common.command.Command
import org.eclipse.emf.common.command.UnexecutableCommand
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.xtext.generator.IFileSystemAccess
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper

/**
 * This class generates the UiSnippets for the Categories and properties which are 
 * defined as arrays. It takes care about static and dynamic arrays as well and sets up
 * the classes accordingly.
 */
class GenerateCategoryUiSnippetArrayTable extends AGeneratorGapGenerator<AProperty>{
	
	override protected getPackage(Concept concept) { 
		concept.name + ".ui.snippet";
	}
	
	static def getConcreteClassName(AProperty property, Category category) {
		"UiSnippetTable" + ConceptGeneratorUtil.getPropertyId(property, category).toFirstUpper;
	}
	
	static def getAbstractClassName(AProperty property, Category category) {
		"AUiSnippetTable" + ConceptGeneratorUtil.getPropertyId(property, category).toFirstUpper;
	}
	
	static def getConcreteClassName(AProperty property, Category category, Category extendingCategory) {
		getConcreteClassName(property, category) + extendingCategory.name.toFirstUpper;
	}
	
	static def getAbstractClassName(AProperty property, Category category, Category extendingCategory) {
		getAbstractClassName(property, category) + extendingCategory.name.toFirstUpper;
	}
	
	override createConcreteClassFileName(Concept concept, AProperty property) {
		 "../../" + concept.name + ".ui/src/" + concept.packageFolder + "/" 
	}
	
	override createAbstractClassFileName(Concept concept, AProperty property) {
		 "../../" + concept.name + ".ui/src-gen/" + concept.packageFolder + "/" 
	}

	def createConcreteClassFileName(Concept concept, AProperty property, Category category, Category extendingCategory) {
		 createConcreteClassFileName(concept, property) + getConcreteClassName(property, category, extendingCategory) + ".java"
	}
	
	def createAbstractClassFileName(Concept concept, AProperty property, Category category, Category extendingCategory) {
		 createAbstractClassFileName(concept, property) + getAbstractClassName(property, category, extendingCategory) + ".java"
	}
	
	def createConcreteClassFileName(Concept concept, AProperty property, Category category) {
		 createConcreteClassFileName(concept, property) + getConcreteClassName(property, category) + ".java"
	}
	
	def createAbstractClassFileName(Concept concept, AProperty property, Category category) {
		 createAbstractClassFileName(concept, property) + getAbstractClassName(property, category) + ".java"
	}
	
	override serializeModel(Concept concept, IFileSystemAccess fsa) {
		concept.nonAbstractCategories.forEach[
			val category = it;
			// *********************************************************
			// generate Table for ArrayProperty
			// *********************************************************
			it.allProperties.forEach[
				val property = it;
				if (property.arrayModifier !== null) {
					if (property.arrayModifier instanceof DynamicArrayModifier && property instanceof ComposedProperty) {
						val baseCategory = (property as ComposedProperty).type
						concept.nonAbstractCategories.forEach[
							val extendingCategory = it;
							if (extendingCategory.isExtensionOf(baseCategory)) {
								// ************************************************************************************
								// Abstract Class
								// ************************************************************************************
								var fileOutputAClass = createAbstractClass(concept, property, category, extendingCategory);
								fsa.generateFile(createAbstractClassFileName(concept, property, category, extendingCategory), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);
				
								// ************************************************************************************
								// Concrete Class
								// ************************************************************************************
								var fileOutputClass = createConcreteClass(concept, property, category, extendingCategory);
								fsa.generateFile(createConcreteClassFileName(concept, property, category, extendingCategory), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
							}
						]						
					} else {
							// ************************************************************************************
							// Abstract Class
							// ************************************************************************************
							var fileOutputAClass = createAbstractClass(concept, property, category, null);
							fsa.generateFile(createAbstractClassFileName(concept, property, category), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);
			
							// ************************************************************************************
							// Concrete Class
							// ************************************************************************************
							var fileOutputClass = createConcreteClass(concept, property, category);
							fsa.generateFile(createConcreteClassFileName(concept, property, category), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
					}
				}
			]
		]	
	}

	def createConcreteClass(Concept concept, AProperty conceptPart, Category category) {
		val imCClass = new ImportManager(concept.package)
	
		val bodyClass = declareClass(concept, conceptPart, category, imCClass)
		var fileOutputClass = '''
		«ConceptGeneratorUtil.generateFileHeader»
		«declarePackage(concept.package)»
		
		«declareImports(imCClass, concept, conceptPart, concept.package)»
		
		«bodyClass»
		'''
		return fileOutputClass;
	}

	def createConcreteClass(Concept concept, AProperty conceptPart, Category category, Category extendingCategory) {
		val imCClass = new ImportManager(concept.package)
	
		val bodyClass = declareClass(concept, conceptPart, category, extendingCategory, imCClass)
		var fileOutputClass = '''
		«ConceptGeneratorUtil.generateFileHeader»
		«declarePackage(concept.package)»
		
		«declareImports(imCClass, concept, conceptPart, concept.package)»
		
		«bodyClass»
		'''
		return fileOutputClass;
	}
	
	def createAbstractClass(Concept concept, AProperty conceptPart, Category category, Category extendingCategory) {
		val imAClass = new ImportManager(concept.package)
		val bodyAClass = declareAClass(concept, conceptPart, category, extendingCategory, imAClass)
		var fileOutputAClass = '''
		«ConceptGeneratorUtil.generateFileHeader»
		«declarePackage(concept.package)»
		
		«declareImports(imAClass, concept, conceptPart, concept.package)»
		
		«bodyAClass»
		'''
		return fileOutputAClass;
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
	override protected declareImports(ImportManager importManager, Concept concept, AProperty conceptPart, String conceptPackage) '''
	«IF !importManager.importedClasses.empty»
		«FOR clazz : importManager.importedClasses»
			import «clazz»;
		«ENDFOR»
  	«ENDIF»
	'''
	
	override protected declareClass(Concept concept, AProperty type, ImportManager importManager) {
		val category = type.eContainer as Category;
		declareClass(concept, type, category, category, importManager);
	}
	
	def protected declareClass(Concept concept, AProperty type, Category category, Category extendingCategory, ImportManager importManager) '''
	«importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet")»
	
	«ConceptGeneratorUtil.generateClassHeader(type)»
	public class «getConcreteClassName(type, category, extendingCategory)» extends «getAbstractClassName(type, category, extendingCategory)» implements IUiSnippet {
	}
	'''
	
	def protected declareClass(Concept concept, AProperty type, Category category, ImportManager importManager) '''
	«importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet")»
	
	«ConceptGeneratorUtil.generateClassHeader(type)»
	public class «getConcreteClassName(type, category)» extends «getAbstractClassName(type, category)» implements IUiSnippet {
	}
	'''
	
	override protected declareAClass(Concept concept, AProperty arrayProperty, ImportManager importManager) {
		val category = arrayProperty.eContainer as Category;
		declareAClass(concept, arrayProperty, category, category, importManager);
	}
	
	def protected declareAClass(Concept concept, AProperty arrayProperty, Category category, Category extendingCategory, ImportManager importManager) {
		if (arrayProperty instanceof ComposedProperty) {
			if (arrayProperty.arrayModifier instanceof StaticArrayModifier) {
				declareStaticArrayCategoryTableClass(concept, category, arrayProperty, importManager)
			} else if (arrayProperty.arrayModifier instanceof DynamicArrayModifier) {
				declareDynamicArrayCategoryTableClass(concept, category, extendingCategory, arrayProperty, importManager)
			}
		} else {
			if (arrayProperty.arrayModifier instanceof StaticArrayModifier) {
				declareStaticArrayPropertyTableClass(concept, category, arrayProperty, importManager)
			} else if (arrayProperty.arrayModifier instanceof DynamicArrayModifier) {
				declareDynamicArrayPropertyTableClass(concept, category, arrayProperty, importManager)
			}
		}
	}
	
	/**
	 * This method handles the UiSnippet for a Table of Dynamic Array of Properties  
	 */
	def declareDynamicArrayPropertyTableClass(Concept concept, Category category, AProperty arrayProperty, ImportManager importManager) '''
	«importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetArrayInstancePropertyTable")»
	«importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet")»
	«importManager.register(concept.name +".ui.command." + GenerateArrayCreateAddCommand.getConcreteClassName(arrayProperty))»
	«importManager.register(Command)»
	«importManager.register(EditingDomain)»
	«importManager.register(Concept)»	

	«ConceptGeneratorUtil.generateAClassHeader(category)»
	public abstract class «getAbstractClassName(arrayProperty, category)» extends AUiSnippetArrayInstancePropertyTable implements IUiSnippet {
	
		public «getAbstractClassName(arrayProperty, category)»() {
			super("«concept.name»",
				"«arrayProperty.name»",
				"«category.name»",
				STYLE_ADD_BUTTON | STYLE_REMOVE_BUTTON | STYLE_EDITOR_BUTTON);
		}
	
		@Override
		protected Command createAddCommand(EditingDomain editingDomain, Concept activeConcept) {
			return new «GenerateArrayCreateAddCommand.getConcreteClassName(arrayProperty)»().create(editingDomain, getArrayInstance(model), null);
		}
	}
	'''
	
	/**
	 * This method handles the UiSnippet for a Table of Static Array of Properties  
	 */
	def declareStaticArrayPropertyTableClass(Concept concept, Category category, AProperty arrayProperty, ImportManager importManager) '''
	«importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetArrayInstancePropertyTable")»
	«importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet")»
	«importManager.register(Command)»
	«importManager.register(UnexecutableCommand)»
	«importManager.register(EditingDomain)»
	«importManager.register(Concept)»	

	«ConceptGeneratorUtil.generateAClassHeader(category)»
	public abstract class «getAbstractClassName(arrayProperty, category)» extends AUiSnippetArrayInstancePropertyTable implements IUiSnippet {
	
		public «getAbstractClassName(arrayProperty, category)»() {
			super("«concept.name»",
				"«arrayProperty.name»",
				"«category.name»",
				STYLE_NONE);
		}
	
		@Override
		protected Command createAddCommand(EditingDomain editingDomain, Concept activeConcept) {
			return UnexecutableCommand.INSTANCE;
		}
	}
	'''
	
	/**
	 * This method handles the UiSnippet for a Table of Static Array of Categories  
	 */
	def declareStaticArrayCategoryTableClass(Concept concept, Category category, AProperty arrayProperty, ImportManager importManager) '''
	«importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetArrayInstanceCategoryTable")»
	«importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet")»
	«importManager.register(Command)»
	«importManager.register(UnexecutableCommand)»
	«importManager.register(EditingDomain)»
	«importManager.register(Concept)»
	
	«ConceptGeneratorUtil.generateAClassHeader(category)»
	public abstract class «getAbstractClassName(arrayProperty, category)» extends AUiSnippetArrayInstanceCategoryTable implements IUiSnippet {
	
		public «getAbstractClassName(arrayProperty, category)»() {
			super("«concept.name»",
				"«arrayProperty.arrayType.name»",
				"«arrayProperty.name»",
				"«category.name»",
				"«arrayProperty.arrayType.fullQualifiedName»",
				STYLE_EDITOR_BUTTON);
		}
	
	@Override
		protected Command createAddCommand(EditingDomain editingDomain, Concept activeConcept) {
			return UnexecutableCommand.INSTANCE;
		}	
	}
	'''

	/**
	 * This method handles the UiSnippet for a Table of Dynamic Array of Categories  
	 */
	def declareDynamicArrayCategoryTableClass(Concept concept, Category category, Category extendingCategory, AProperty arrayProperty, ImportManager importManager) '''
	«importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetArrayInstanceCategoryTable")»
	«importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet")»
	«importManager.register(concept.name +".ui.command." + GenerateArrayCreateAddCommand.getConcreteClassName(arrayProperty))»
	«importManager.register(Command)»
	«importManager.register(EditingDomain)»
	«importManager.register(Concept)»
	«importManager.register(ActiveConceptHelper)»
	
	«ConceptGeneratorUtil.generateAClassHeader(category)»
	public abstract class «getAbstractClassName(arrayProperty, category, extendingCategory)» extends AUiSnippetArrayInstanceCategoryTable implements IUiSnippet {
	
		public «getAbstractClassName(arrayProperty, category, extendingCategory)»() {
			super("«concept.name»",
				"«extendingCategory.name»",
				"«arrayProperty.name»",
				"«category.name»",
				"«extendingCategory.fullQualifiedName»",
				STYLE_ADD_BUTTON | STYLE_REMOVE_BUTTON | STYLE_EDITOR_BUTTON);
		}
		
		@Override
		protected Command createAddCommand(EditingDomain editingDomain, Concept activeConcept) {
			return new «GenerateArrayCreateAddCommand.getConcreteClassName(arrayProperty)»().create(editingDomain, getArrayInstance(model),  ActiveConceptHelper.getCategory(activeConcept, "«extendingCategory.name»"));
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
	
}
