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
import de.dlr.sc.virsat.model.dvlm.categories.Category
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.concept.generator.ImportManager
import org.eclipse.xtext.generator.IFileSystemAccess
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper
import java.util.List
import java.util.Set
import java.util.HashSet
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.VerificationTypeSpecification

class GenerateRequirementsVerificationUiSnippet extends AGeneratorGapGenerator<Category>  {
	
	override protected getPackage(Concept concept) {
		concept.name + ".ui.snippet";
	}
	
	static def getConcreteClassName(ATypeDefinition typeDefinition) {
		"UiSnippetTableRequirementVerifications" + typeDefinition.name.toFirstUpper;
	}
	
	static def getAbstractClassName(ATypeDefinition typeDefinition) {
		"AUiSnippetTableRequirementVerifications" + typeDefinition.name.toFirstUpper;
	}
	
	override createConcreteClassFileName(Concept concept, Category category) {
		 "../../" + concept.name + ".ui/src/" + concept.packageFolder + "/" + category.concreteClassName + ".java"
	}
	
	override createAbstractClassFileName(Concept concept, Category category) {
		 "../../" + concept.name + ".ui/src-gen/" + concept.packageFolder + "/" + category.abstractClassName + ".java"
	}
	
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
	
	override protected declarePackage(String packageId) '''
	package «packageId»;
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
 	override protected declareAClass(Concept conceptToBeVerified, Category verificationCategory, ImportManager importManager) '''
	«importManager.register("de.dlr.sc.virsat.model.extension.requirements.ui.snippet.AUISnippetTableRequirementsVerification")»
	
	«ConceptGeneratorUtil.generateAClassHeader(verificationCategory)»
	public abstract class «verificationCategory.abstractClassName» extends AUISnippetTableRequirementsVerification {
		public «verificationCategory.abstractClassName»() {
			super("«conceptToBeVerified.name»",
				"«ActiveConceptHelper.getConcept(verificationCategory).name»",
				"«verificationCategory.name»",
				"«verificationCategory.fullQualifiedName»",
				STYLE_ADD_BUTTON | STYLE_REMOVE_BUTTON | STYLE_EDITOR_BUTTON);
		}

	}
	'''
	
	override serializeModel(Concept concept, IFileSystemAccess fsa) {
		val Set<Category> verificationSet = new HashSet<Category>
		verificationSet.addAll(concept.categories.filter[c | c.isIsVerification]);

		for(category : concept.nonAbstractCategories){
			for(property : category.properties) {
				if(property.verification !== null && property.verification instanceof VerificationTypeSpecification) {
					verificationSet.add(((property.verification) as VerificationTypeSpecification).verificationType as Category)
				}
			}
		}

		verificationSet.forEach[
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
	
}