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
import de.dlr.sc.virsat.model.concept.generator.handler.GenerateCategoryAddHandler
import de.dlr.sc.virsat.model.concept.generator.handler.GenerateStructuralElementInstanceAddHandler
import de.dlr.sc.virsat.model.concept.generator.snippets.GenerateCategoryUiSnippetArrayTable
import de.dlr.sc.virsat.model.concept.generator.snippets.GenerateCategoryUiSnippetPropertySection
import de.dlr.sc.virsat.model.concept.generator.snippets.GenerateCategoryUiSnippetTable
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition
import de.dlr.sc.virsat.model.dvlm.categories.Category
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.DynamicArrayModifier
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.generator.IFileSystemAccess

class GenerateUiPluginXml {
	
	@Accessors
	PluginXmlReader pluginXmlReader;
	
	def serializeModel(Concept concept, PluginXmlReader pluginXmlReader, IFileSystemAccess fsa) {
		this.pluginXmlReader = pluginXmlReader;
		
		val fileName = "plugin.xml"
		val uiPlugin = concept.name + ".ui/"
	
		var fileOutput = createUiXml(concept, uiPlugin)

		val uiFolder = "../../" + uiPlugin + "/"
		fsa.generateFile(uiFolder + fileName, ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutput);
	}
	
	def createUiXml(Concept concept, String uiPlugin) {
		'''
		<?xml version="1.0" encoding="UTF-8"?>
		<?eclipse version="3.4"?>
		<plugin>
			<extension point="org.eclipse.core.expressions.propertyTesters">
			 <propertyTester
			    id="«concept.name».ui.propertyTester.conceptEnabledTester"
			    type="org.eclipse.emf.ecore.EObject"
			    namespace="«concept.name».ui.propertyTester"
			    properties="conceptEnabled"
			    class="«concept.name».propertyTester.ConceptEnabledTester">
			  </propertyTester>
			</extension>
			<extension point="de.dlr.sc.virsat.model.edit.ConceptImageContribution">
			«declareConceptImageExtension(concept)»
			«FOR category : concept.categories»
			«declareConceptImageExtension(category)»
			«ENDFOR» 
			«FOR structuralElement : concept.structuralElements»
			«declareConceptImageExtension(structuralElement)»
			«ENDFOR»
			</extension>
			<extension point="org.eclipse.ui.commands">
			«FOR category : concept.nonAbstractCategories»
			«declareCommands(concept, category)»
			«ENDFOR»
			«FOR structuralElement : concept.structuralElements»
			«declareCommands(concept, structuralElement)»
			«ENDFOR»
			</extension>
			<extension point="org.eclipse.ui.commandImages">
			«FOR category : concept.nonAbstractCategories»
			«declareCommandsImages(concept, category)»
			«ENDFOR»
			«FOR structuralElement : concept.structuralElements»
			«declareCommandsImages(concept, structuralElement)»
			«ENDFOR»
			</extension>
			<extension point="org.eclipse.ui.handlers">
			«FOR category : concept.nonAbstractCategories»
			«declareHandler(concept, category)»
			«ENDFOR»
			«FOR structuralElement : concept.structuralElements»
			«declareHandler(concept, structuralElement)»
			«ENDFOR»
			</extension>
			<extension point="org.eclipse.ui.menus">
				«declareAddConceptSubMenuMenuContributions(concept)»
				<menuContribution allPopups="false"
					locationURI="popup:«concept.name».menu?after=categories">
				«FOR category : concept.nonAbstractCategories»
				«declareAddCategoryMenuContributions(concept, category)»
				«ENDFOR»
				</menuContribution>
				<menuContribution allPopups="false"
					locationURI="popup:«concept.name».menu?after=structuralElements">
				«FOR structuralElement : concept.structuralElements»
				«declareAddStructuralElementMenuContributions(concept, structuralElement)»
				«ENDFOR»
				</menuContribution>
			</extension>
			«declareEditorSection(concept)»
			«IF !concept.nonAbstractCategories.empty»
			<extension point="de.dlr.sc.virsat.uiengine.ui.EditorUiSnippets">
				«FOR category : concept.nonAbstractCategories»
					«declareUiSnippetTables(concept, category)»
					«declareUiSnippetSections(concept, category)»
					«FOR property : category.allProperties»
						«IF (property.arrayModifier !== null)»
							«IF (property.arrayModifier instanceof DynamicArrayModifier && property instanceof ComposedProperty)»
								«val baseCategory = (property as ComposedProperty).type»
								«FOR extendingCategory : concept.nonAbstractCategories»
									«IF (extendingCategory.isExtensionOf(baseCategory))»
									«declareUiSnippetArrayTables(concept, property, category, extendingCategory)»
									«ENDIF»
								«ENDFOR»
							«ELSE»
								«declareUiSnippetArrayTables(concept, property, category)»
							«ENDIF»
						«ENDIF»
					«ENDFOR»
				«ENDFOR»
			</extension>
			«ENDIF»							
			<!-- «PluginXmlReader.PR_START» -->
		«pluginXmlReader.init(uiPlugin).extractProtectedRegion(0).trim»
			<!-- «PluginXmlReader.PR_END» -->
		</plugin>
		'''
	}
	
	def declareCommandsImages(Concept concept, IQualifiedName qualifiedName) '''
	 <image
	       commandId="«getCommandId(concept, qualifiedName)»"
	       disabledIcon="resources/icons/«GenerateConceptImages.getFileNameDisabled(qualifiedName)»"
	       icon="resources/icons/«GenerateConceptImages.getFileNameStandard(qualifiedName)»">
	 </image>
	'''

	def declareCommands(Concept concept, IQualifiedName qualifiedNameObject) '''
	<command
		id="«getCommandId(concept, qualifiedNameObject)»"
		name="Add «qualifiedNameObject.name»">
	</command>
	'''

	def declareHandler(Concept concept, Category category) '''
	<handler
		class="«concept.name».ui.handler.«GenerateCategoryAddHandler.getConcreteClassName(category)»"
		commandId="«getCommandId(concept, category)»">
	</handler>
	'''		

	def declareHandler(Concept concept, StructuralElement structuralElement) '''
	<handler
		class="«concept.name».ui.handler.«GenerateStructuralElementInstanceAddHandler.getConcreteClassName(structuralElement)»"
		commandId="«getCommandId(concept, structuralElement)»">
	</handler>
	'''		

	def declareAddConceptSubMenuMenuContributions(Concept concept) '''
	<menuContribution
	     allPopups="false"
	     locationURI="popup:de.dlr.sc.virsat.project.ui.navigator.menu#PopupMenu?after=concepts">
	     <menu
	        id="«concept.name».menu" 
	        label="«concept.readableConceptName»"
	        icon="resources/icons/«concept.name».gif"
	        tooltip="«concept.description»">
	        <separator
	            name="categories"
	            visible="true">
		    </separator>
	        <separator
		        name="structuralElements"
	            visible="true">
		    </separator>
	        <separator
		        name="tools"
	            visible="true">
		    </separator>
	        <separator
	        	name="additions"
	            visible="true">
	    	</separator>
	    	<visibleWhen
				checkEnabled="true">
				<with
					variable="selection">
					<iterate
						ifEmpty="false"
						operator="and">
						<test 
							property="«concept.name».ui.propertyTester.conceptEnabled"
							value="true" 
							forcePluginActivation="true">
						</test>		
					</iterate>
				</with>
			</visibleWhen>
		</menu>
	</menuContribution>
	'''

	def declareAddCategoryMenuContributions(Concept concept, Category category) '''
	<command
		commandId="«getCommandId(concept, category)»"
		style="push">
		<visibleWhen
			checkEnabled="true">
			<with
				variable="selection">
				<iterate
					ifEmpty="false"
					operator="and">
					<test 
						property="«concept.name».ui.propertyTester.conceptEnabled"
						value="true" 
						forcePluginActivation="true">
					</test>		
					<instanceof
						value="de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer">
					</instanceof>
				</iterate>
			</with>
		</visibleWhen>
	</command>
	'''		
	
	def declareAddStructuralElementMenuContributions(Concept concept, StructuralElement structuralElement) '''
	<command
		commandId="«getCommandId(concept, structuralElement)»"
		style="push">
		<visibleWhen
			checkEnabled="true">
			 <with
	         	variable="selection">
	          	<iterate
	            	ifEmpty="false"
	                operator="and">
	                <test 
	              		property="«concept.name».ui.propertyTester.conceptEnabled"
	              		value="true" 
	              		forcePluginActivation="true">
	                </test>	
	              	<or>
	                	<instanceof
	                       value="de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance">
	                 	</instanceof>
	                 	<instanceof
	                       value="de.dlr.sc.virsat.model.dvlm.Repository">
	                 	</instanceof>
	              	</or>
	           	</iterate>
	           	<and>
	              <count
	                    value="1">
	              </count>
	           </and>
	        </with>
		</visibleWhen>
	</command>
	'''		
	
	def declareEditorSection(Concept concept) '''
	<extension
	       point="de.dlr.sc.virsat.uiengine.ui.EditorSection">
	    <editorSection
	          id="«concept.name».ui.Section"
	          topRanking="1000">
	    </editorSection>
	 </extension>	
	'''
					
	def declareUiSnippetTables(Concept concept, ATypeDefinition typeDefinition) '''
    <uiSnippet
          id="«concept.name».table.uiSnippet«typeDefinition.name»"
          section="«concept.name».ui.Section"
          snippet="«concept.name».ui.snippet.«GenerateCategoryUiSnippetTable.getConcreteClassName(typeDefinition)»">
    </uiSnippet>
	'''						

	def declareUiSnippetArrayTables(Concept concept, AProperty property, Category category, Category extendingCategory) '''
    <uiSnippet
          id="«concept.name».table.uiSnippet«ConceptGeneratorUtil.getPropertyId(property, category) + extendingCategory.name.toFirstUpper»"
          section="«concept.name».ui.Section"
          snippet="«concept.name».ui.snippet.«GenerateCategoryUiSnippetArrayTable.getConcreteClassName(property, category, extendingCategory)»">
    </uiSnippet>
	'''
	
	def declareUiSnippetArrayTables(Concept concept, AProperty property, Category category) '''
    <uiSnippet
          id="«concept.name».table.uiSnippet«ConceptGeneratorUtil.getPropertyId(property, category)»"
          section="«concept.name».ui.Section"
          snippet="«concept.name».ui.snippet.«GenerateCategoryUiSnippetArrayTable.getConcreteClassName(property, category)»">
    </uiSnippet>
	'''
	
	def declareConceptImageExtension(IQualifiedName qualifiedNameObject) '''
	<conceptImage
		fullQualifiedID="«qualifiedNameObject.fullQualifiedName»"
		pathToImage="resources/icons/«qualifiedNameObject.name».gif">
	</conceptImage>
	'''

	def declareUiSnippetSections(Concept concept, Category category) '''
    <uiSnippet
          id="«concept.name».section.uiSnippet«category.name»"
          section="«concept.name».ui.Section"
          snippet="«concept.name».ui.snippet.«GenerateCategoryUiSnippetPropertySection.getConcreteClassName(category)»">
    </uiSnippet>
	'''						

	def getCommandId(Concept concept, IQualifiedName qualifiedNameObject) {
		return concept.name +".ui.command.Add" + qualifiedNameObject.name;
	}

	/**
	 * Call thus method to hand back the name of the current concept
	 * In case the short name is set it will be returned otherwise the usual name will be handed back
	 * which is usually the name including the FQN to the concept. Therefore the shortName should be used in a way such as Product Structures
	 */
	def getReadableConceptName(Concept concept) {
		if (concept.displayName !== null && !concept.displayName.isEmpty) {
			return concept.displayName;
		} else {
			concept.name;
		} 
	} 
}

