/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.commands

import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider
import de.dlr.sc.virsat.model.concept.generator.ImportManager
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import org.eclipse.emf.common.command.Command
import org.eclipse.emf.edit.command.AddCommand
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.xtext.generator.IFileSystemAccess
import de.dlr.sc.virsat.model.dvlm.categories.Category

class GenerateArrayCreateAddCommand extends AGeneratorGapGenerator<AProperty> {
	
	override protected getPackage(Concept concept) { 
		concept.name + ".ui.command";
	}
	
	static def getConcreteClassName(ATypeDefinition typeDefinition) {
		"CreateAddArrayElement" + typeDefinition.name.toFirstUpper + "Command"
	}
	
	static def getAbstractClassName(ATypeDefinition typeDefinition) {
		"ACreateAddArrayElement" + typeDefinition.name.toFirstUpper + "Command"
	}
	
	override createConcreteClassFileName(Concept concept, AProperty category) {
		 "../../" + concept.name + ".ui/src/" + concept.packageFolder + "/" + category.concreteClassName + ".java"
	}
	
	override createAbstractClassFileName(Concept concept, AProperty category) {
		 "../../" + concept.name + ".ui/src-gen/" + concept.packageFolder + "/" + category.abstractClassName + ".java"
	}
	
	override serializeModel(Concept concept, IFileSystemAccess fsa) {
		concept.nonAbstractCategories.forEach[
			it.allProperties.forEach[
				if (it.arrayModifier !== null) {
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
		]
	}

	/**
	 *  This method just creates the package declaration. The concept name needs to fit to the package name  
	 */
	override protected declarePackage(String packageId) '''
	package «packageId»;
	'''
	
	override declareImports(ImportManager importManager, Concept concept, AProperty conceptPart, String conceptPackage)  '''
	«IF !importManager.importedClasses.empty»
		«FOR clazz : importManager.importedClasses»
			import «clazz»;
		«ENDFOR»
  	«ENDIF»
	'''
	
	override protected declareClass(Concept concept, AProperty property, ImportManager manager) '''
	«ConceptGeneratorUtil.generateClassHeader(property)»
	public class «property.concreteClassName» extends «property.abstractClassName» {
	}
	'''
	
	override protected declareAClass(Concept concept, AProperty property, ImportManager importManager) '''
	«importManager.register(Command)»
	«importManager.register(EditingDomain)»
	«importManager.register(Category)»
	«importManager.register(ArrayInstance)»
	«importManager.register(ATypeInstance)»
	«importManager.register(AddCommand)»
	«importManager.register(PropertyinstancesPackage)»
	«importManager.register(CategoryInstantiator)»
	
	«ConceptGeneratorUtil.generateAClassHeader(property)»
	public abstract class «property.abstractClassName» {
		
		public Command create(EditingDomain editingDomain, ArrayInstance arrayInstance, Category type) {
			ATypeInstance ati = new CategoryInstantiator().generateInstance(arrayInstance, type);
			return AddCommand.create(editingDomain, arrayInstance, PropertyinstancesPackage.Literals.ARRAY_INSTANCE__ARRAY_INSTANCES, ati);
		}
	}
	'''
}
