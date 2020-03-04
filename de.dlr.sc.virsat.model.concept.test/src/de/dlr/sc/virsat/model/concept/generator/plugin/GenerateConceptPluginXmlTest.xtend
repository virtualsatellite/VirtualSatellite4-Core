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

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage
import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert
import javax.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(ConceptLanguageTestInjectorProvider)
class GenerateConceptPluginXmlTest {

	@Inject extension ParseHelper<Concept>

	Concept concept
	val TEST_CONCEPT_NAME = "testConcept"
	val TEST_CATEGORY_NAME = "testCategory"
	val TEST_STRUCTURAL_1_NAME = "testStructural1";
	val TEST_STRUCTURAL_2_NAME = "testStructural2";
	val TEST_EXTRACT_PROTECTED_REGION = "testExtractProtectedRegion"
	val TEST_VERSION = "1.1"
	
	val pluginGenerator = new GeneratePluginXml

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
	def void testCreateXml() {
		concept = '''
			Concept «TEST_CONCEPT_NAME» version «TEST_VERSION» {
				
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
		
		val expected = pluginGenerator.createXml(concept, concept.name)
		GeneratorJunitAssert.assertEqualContent(expected, "/resources/expectedOutputFilesForGenerators/ConceptPlugin.xml");
	}
	
	@Test
	def void createXmlDeprecatedValidator() {
		concept = '''
			Concept «TEST_CONCEPT_NAME» version «TEST_VERSION» {
				
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
		
		val expected = pluginGenerator.createXmlDeprecatedValidator(concept, concept.name)
		GeneratorJunitAssert.assertEqualContent(expected, "/resources/expectedOutputFilesForGenerators/ConceptPluginDeprecatedValidator.xml");
	}
}
