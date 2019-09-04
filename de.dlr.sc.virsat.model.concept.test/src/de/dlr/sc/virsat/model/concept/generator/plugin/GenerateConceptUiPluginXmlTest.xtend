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

import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import javax.inject.Inject
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(ConceptLanguageTestInjectorProvider)
class GenerateConceptUiPluginXmlTest {

	@Inject extension ParseHelper<Concept>

	Concept concept
	val TEST_CONCEPT_NAME = "testConcept"
	val TEST_CATEGORY_NAME = "testCategory"
	val TEST_STRUCTURAL_1_NAME = "testStructural1";
	val TEST_STRUCTURAL_2_NAME = "testStructural2";
	val TEST_EXTRACT_PROTECTED_REGION = "testExtractProtectedRegion"
	
	val pluginGenerator = new GenerateUiPluginXml

	@Before
	def void setUp() {
		ConceptsPackage.eINSTANCE.eClass
		CategoriesPackage.eINSTANCE.eClass
		PropertydefinitionsPackage.eINSTANCE.eClass
		
		pluginGenerator.pluginXmlReader = new PluginXmlReader() {
			override init(String pluginId) {
				return this
			}
			
			override extractProtectedRegion(int regionID) {
				TEST_EXTRACT_PROTECTED_REGION
			}
		}
		
	}
	
	@Test
    def void testCreateUiXml() {
    	concept = '''
			Concept «TEST_CONCEPT_NAME»{
				
				StructuralElement «TEST_STRUCTURAL_1_NAME» {
					IsRootStructuralElement;
				}
				
				StructuralElement «TEST_STRUCTURAL_2_NAME» {
					Applicable For [«TEST_STRUCTURAL_1_NAME»];
				}				
				
				Category «TEST_CATEGORY_NAME» {
					StringProperty tpSringArrayDynamic[];
					StringProperty tpSringArrayStatic[5];
					IntProperty tpIntArrayDynamic[];
					IntProperty tpIntArrayStatic[6];
					FloatProperty tpFloatArrayDynamic[];
					FloatProperty tpFloatArrayStatic[7];
					BooleanProperty tpBooleanArrayDynamic[];
					BooleanProperty tpBooleanArrayStatic[8];
					Resource tpResourceDynamich[];
					Resource tpResourceStatic[9];
					Applicable For All;
				}
			}
		'''.parse
		
		val expected = pluginGenerator.createUiXml(concept, concept.name + ".ui/")
		GeneratorJunitAssert.assertEqualContent(expected, "/resources/expectedOutputFilesForGenerators/ConceptUiPlugin.xml");

		// now do the same thing, but store the just generated content into a temporary file,
		// thus it can be read again by the PluginXML Reader, which should preserve the protected region
		// then generate again, an d the output should not have any difference. Previously the
		// protected region got indented by each generation with an additional TAB
		val project = ResourcesPlugin.workspace.root.getProject("PluginXmlGenerator");
		if (!project.exists) {
			project.create(null);
		}
		project.open

		val TEMPORARY_PLUGIN_XML = "TestGeneratorUiPlugin.xml";
		val iFile = project.getFile(TEMPORARY_PLUGIN_XML);
		val iFileLocation = iFile.getRawLocation().toOSString();
		Files.write(Paths.get(iFileLocation), expected.toString.bytes);
		pluginGenerator.pluginXmlReader = new PluginXmlReader() {
			override init(String pluginId) {
				try {
					lines = Files.readAllLines(Paths.get(iFileLocation));
				} catch (IOException e) {
					throw new RuntimeException("Could not read plugin.xml in project " + pluginId);
				}
				return this;
			}
		}
	
		val expectedFromRealPlugin = pluginGenerator.createUiXml(concept, concept.name + ".ui/")
		
		Assert.assertEquals("Both file outputs are exactly the same. No addition indentation for plugin.XML", expected.toString, expectedFromRealPlugin.toString);
	}
}
