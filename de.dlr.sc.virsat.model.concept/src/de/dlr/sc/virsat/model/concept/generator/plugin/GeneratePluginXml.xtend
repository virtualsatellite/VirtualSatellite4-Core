/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
 package de.dlr.sc.virsat.model.concept.generator.plugin

import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider
import de.dlr.sc.virsat.model.concept.generator.migrator.GenerateMigrator
import de.dlr.sc.virsat.model.concept.generator.validator.GenerateValidator
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.generator.IFileSystemAccess
import de.dlr.sc.virsat.model.concept.generator.beans.GenerateCategoryBeans
import de.dlr.sc.virsat.model.concept.generator.beans.GenerateStructuralElementBeans
import de.dlr.sc.virsat.model.concept.generator.xmi.GenerateConceptXmi

class GeneratePluginXml {
	
	@Accessors
	PluginXmlReader pluginXmlReader;
	
	def serializeModel(Concept concept, PluginXmlReader pluginXmlReader, IFileSystemAccess fsa) {
		this.pluginXmlReader = pluginXmlReader;
		
		val fileName = "plugin.xml"
		val plugin = concept.name
	
		var fileOutput = createXml(concept, plugin)
		val Folder = "../../" + plugin + "/"
		fsa.generateFile(Folder + fileName, ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutput
		);
	}

	def serializeModelDeprecatedValidator(Concept concept, PluginXmlReader pluginXmlReader, IFileSystemAccess fsa) {
		this.pluginXmlReader = pluginXmlReader;
		
		val fileName = "plugin.xml"
		val plugin = concept.name
	
		var fileOutput = createXmlDeprecatedValidator(concept, plugin)
		val Folder = "../../" + plugin + "/"
		fsa.generateFile(Folder + fileName, ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutput
		);
	}	
	
	def createXml(Concept concept, String plugin)'''
		<?xml version="1.0" encoding="UTF-8"?>
		<?eclipse version="3.4"?>
		<plugin>
			«declareConceptRegistryExtension(concept)»
			«declareDvlmValidatorExtension(concept)»
			«declareConceptCaBeanRegistration(concept)»
			«declareConceptSeiBeanRegistration(concept)»
		«declareConceptMigratorExtensions(concept, plugin)»
			<!-- «PluginXmlReader.PR_START» -->
		«pluginXmlReader.extractProtectedRegion(1).trim»
			<!-- «PluginXmlReader.PR_END» -->
		</plugin>
		'''
	
	def createXmlDeprecatedValidator(Concept concept, String plugin)'''
		<?xml version="1.0" encoding="UTF-8"?>
		<?eclipse version="3.4"?>
		<plugin>
			«declareConceptRegistryExtension(concept)»
			«declareDvlmValidatorExtension(concept)»
			«declareDvlmDeprecatedValidatorExtension(concept)»
			«declareConceptCaBeanRegistration(concept)»
			«declareConceptSeiBeanRegistration(concept)»
		«declareConceptMigratorExtensions(concept, plugin)»
			<!-- «PluginXmlReader.PR_START» -->
		«pluginXmlReader.extractProtectedRegion(1).trim»
			<!-- «PluginXmlReader.PR_END» -->
		</plugin>
		'''

	def declareConceptRegistryExtension(Concept concept) '''
	<extension point="de.dlr.sc.virsat.model.Concept">
		<concept
			id="«concept.name»"
			version="«concept.version»"
			xmi="concept/concept.xmi">
		</concept>
	</extension>
	'''

	def declareDvlmValidatorExtension(Concept concept) '''
	<extension point="de.dlr.sc.virsat.model.DvlmValidator">
		<dvlmValidator>
			<seiValidator
				id="«concept.name»"
				class="«concept.name».«GenerateValidator.PACKAGE_FOLDER».«GenerateValidator.getValidatorName(concept)»">
			</seiValidator>
		</dvlmValidator>
	</extension>
	'''

	def declareDvlmDeprecatedValidatorExtension(Concept concept) '''
	<extension point="de.dlr.sc.virsat.model.DvlmValidator">
		<dvlmValidator>
			<seiValidator
				id="«concept.name».deprecated"
				class="«concept.name».«GenerateValidator.PACKAGE_FOLDER».StructuralElementInstanceValidator">
			</seiValidator>
		</dvlmValidator>
	</extension>
	'''

	def declareConceptMigratorExtensions(Concept concept, String plugin) '''	
		<!-- «PluginXmlReader.PR_START» -->
	«val protectedRegion = pluginXmlReader.init(plugin).extractProtectedRegion(0).trim»
	«val newMigrator = String.valueOf(declareConceptMigratorExtension(concept)).trim»
	«protectedRegion»
	«if (protectedRegion === null || !protectedRegion.contains(newMigrator)) {
		newMigrator
	}»
		<!-- «PluginXmlReader.PR_END» -->
	'''
	
	def declareConceptMigratorExtension(Concept concept) '''
	<extension point="de.dlr.sc.virsat.model.edit.ConceptMigrator">
		<migrator
			id="«concept.name»"
			version="«concept.version»"
			class="«concept.name».«GenerateMigrator.PACKAGE_FOLDER».Migrator«concept.version.replace(".", "v")»"
			xmi="concept/«GenerateConceptXmi.getConceptWithVersionName(concept)»">
		</migrator>
	</extension>
	'''

	def declareConceptCaBeanRegistration(Concept concept) '''
	«IF !concept.nonAbstractCategories.isEmpty»
	<extension point="de.dlr.sc.virsat.model.edit.ConceptTypeFactoryCaBeanRegistration">
		«FOR category : concept.nonAbstractCategories»
		<CategoryAssignmentBean
			id="«category.fullQualifiedName»"
			caBean="«concept.name».model.«GenerateCategoryBeans.getConcreteClassName(category)»">
		</CategoryAssignmentBean>
		«ENDFOR»
	</extension>
	«ENDIF»
	'''
	
	def declareConceptSeiBeanRegistration(Concept concept) '''
	«IF !concept.structuralElements.isEmpty»
	<extension point="de.dlr.sc.virsat.model.edit.ConceptTypeFactorySeiBeanRegistration">
		«FOR structuralElement : concept.structuralElements»
		<StructuralElementInstanceBean
			id="«structuralElement.fullQualifiedName»"
			seiBean="«concept.name».model.«GenerateStructuralElementBeans.getConcreteClassName(structuralElement)»">
		</StructuralElementInstanceBean>
		«ENDFOR»
	</extension>
	«ENDIF»
	'''
}

