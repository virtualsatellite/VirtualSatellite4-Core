/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.dmf

import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage
import javax.inject.Inject
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty
import de.dlr.sc.virsat.model.external.tests.ExternalModelTestHelper
import de.dlr.sc.virsat.model.concept.test.MockupConceptResourceLoader
import de.dlr.sc.virsat.model.concept.resources.ConceptResourceLoader

@RunWith(XtextRunner)
@InjectWith(ConceptLanguageTestInjectorProvider)
class GenerateDmfCategoriesTest {

	@Inject extension ParseHelper<Concept>
	protected ExternalModelTestHelper helper = new ExternalModelTestHelper;

	static val TEST_CONCEPT_NAME = "testConcept";
	static val TEST_FQN_NAME = "de.dlr.sc.virsat.model.extension" + "." + TEST_CONCEPT_NAME;
	static val TEST_CONCEPT_XMI_PATH = TEST_FQN_NAME + "/concept/concept.xmi"
	static val TEST_CATEGORY_NAME = "testCategory";
	static val TEST_CONCEPT_VERSION = "2";
	val dmfCategoriesGenerator = new GenerateDmfCategories();

	@Before
	def void setUp() {
		ConceptsPackage.eINSTANCE.eClass
		CategoriesPackage.eINSTANCE.eClass
		PropertydefinitionsPackage.eINSTANCE.eClass
		helper.loadExternalPackage()
	}
	
	@Test
    def void testCreateEPackage() {
      	val concept = '''
			Concept «TEST_FQN_NAME» version «TEST_CONCEPT_VERSION» {
				Category «TEST_CATEGORY_NAME» {
					
				}
			}
	    '''.parse

		// Check that ePackage properties are set correctly
		dmfCategoriesGenerator.initResources(concept);
		val ePackage = dmfCategoriesGenerator.createEPackageFromConcept(concept);
		Assert.assertEquals("EPackage name is set correctly", TEST_CONCEPT_NAME, ePackage.name);
		Assert.assertEquals("EPackage nsPrefix is set correctly", GenerateDmfCategories.MODEL_NS_PREFIX_PREFIX + TEST_CONCEPT_NAME, ePackage.nsPrefix);
		Assert.assertEquals("EPackage nsUri is set correctly", ActiveConceptHelper.MODEL_URI_PREFIX + concept.version + "/" + TEST_CONCEPT_NAME, ePackage.nsURI);
	}

	@Test
    def void testCreateEClass() {
      	val concept = '''
			Concept «TEST_FQN_NAME» {
				Category «TEST_CATEGORY_NAME» {
					
				}
			}
	    '''.parse

		dmfCategoriesGenerator.initResources(concept);
		val ePackage = dmfCategoriesGenerator.createEPackageFromConcept(concept);
		dmfCategoriesGenerator.createCategoryEClassesInEPackage(concept, ePackage);
		
		// Check that the EClass has been generated correctly
		Assert.assertEquals("EPackage has correct number of classifiers", concept.categories.size, ePackage.EClassifiers.size);
		Assert.assertTrue("EPackage classifier is an EClass",  ePackage.EClassifiers.get(0) instanceof EClass);
		val eClass = ePackage.EClassifiers.get(0) as EClass;
		val category = concept.categories.get(0);
		Assert.assertEquals("EClass name is set correctly", category.name, eClass.name);
	}	
	
	@Test
    def void testCreateIntrinsicEAttributes() {
      	val concept = '''
			Concept «TEST_FQN_NAME» {
				Category «TEST_CATEGORY_NAME» {
					IntProperty tpInt;
					StringProperty tpSring;
					FloatProperty tpFloat;
					BooleanProperty tpBoolean;
					Resource tpResource;
				}
			}
	    '''.parse

		dmfCategoriesGenerator.initResources(concept);
		val ePackage = dmfCategoriesGenerator.createEPackageFromConcept(concept);
		dmfCategoriesGenerator.createCategoryEClassesInEPackage(concept, ePackage);

		val eClass = ePackage.EClassifiers.get(0) as EClass;
		val category = concept.categories.get(0);
		
		Assert.assertEquals("EClass has correct number of structural features", category.properties.size, eClass.EStructuralFeatures.size);
	
		// Test correctness of the generated integer attribute
		val eIntStructuralFeature = eClass.EStructuralFeatures.get(0);
		val intProperty = category.properties.get(0);
		Assert.assertEquals("Structural feature has correct name", intProperty.name, eIntStructuralFeature.name);
		Assert.assertTrue("Structural feature is an attribute", eIntStructuralFeature instanceof EAttribute);
		val eIntAttribute = eIntStructuralFeature as EAttribute;
		Assert.assertEquals("Attribute has correct type", EcorePackage.Literals.EINT, eIntAttribute.EType);

		// Test correctness of the generated string attribute
		val eStringStructuralFeature = eClass.EStructuralFeatures.get(1);
		val stringProperty = category.properties.get(1);
		Assert.assertEquals("Structural feature has correct name", stringProperty.name, eStringStructuralFeature.name);
		Assert.assertTrue("Structural feature is an attribute", eStringStructuralFeature instanceof EAttribute);
		val eStringAttribute = eStringStructuralFeature as EAttribute;
		Assert.assertEquals("Attribute has correct type", EcorePackage.Literals.ESTRING, eStringAttribute.EType);
	
		// Test correctness of the generated float attribute
		val eFloatStructuralFeature = eClass.EStructuralFeatures.get(2);
		val floatProperty = category.properties.get(2);
		Assert.assertEquals("Structural feature has correct name", floatProperty.name, eFloatStructuralFeature.name);
		Assert.assertTrue("Structural feature is an attribute", eFloatStructuralFeature instanceof EAttribute);
		val eFloatAttribute = eFloatStructuralFeature as EAttribute;
		Assert.assertEquals("Attribute has correct type", EcorePackage.Literals.EDOUBLE, eFloatAttribute.EType);
		
		// Test correctness of the generated boolean attribute
		val eBooleanStructuralFeature = eClass.EStructuralFeatures.get(3);
		val booleanProperty = category.properties.get(3);
		Assert.assertEquals("Structural feature has correct name", booleanProperty.name, eBooleanStructuralFeature.name);
		Assert.assertTrue("Structural feature is an attribute", eBooleanStructuralFeature instanceof EAttribute);
		val eBooleanAttribute = eBooleanStructuralFeature as EAttribute;
		Assert.assertEquals("Attribute has correct type", EcorePackage.Literals.EBOOLEAN, eBooleanAttribute.EType);
	
		// Test correctness of the generated resource attribute
		val eResourceStructuralFeature = eClass.EStructuralFeatures.get(4);
		val resourceProperty = category.properties.get(4);
		Assert.assertEquals("Structural feature has correct name", resourceProperty.name, eResourceStructuralFeature.name);
		Assert.assertTrue("Structural feature is an attribute", eResourceStructuralFeature instanceof EAttribute);
		val eResourceAttribute = eResourceStructuralFeature as EAttribute;
		Assert.assertEquals("Attribute has correct type", EcorePackage.Literals.ESTRING, eResourceAttribute.EType);
	}	
	
	@Test
    def void testCreateArrayEAttributes() {
      	val concept = '''
			Concept «TEST_FQN_NAME» {
				Category «TEST_CATEGORY_NAME» {
					IntProperty[] tpInt;
					IntProperty[5] tpInt;
				}
			}
	    '''.parse

		dmfCategoriesGenerator.initResources(concept);
		val ePackage = dmfCategoriesGenerator.createEPackageFromConcept(concept);
		dmfCategoriesGenerator.createCategoryEClassesInEPackage(concept, ePackage);

		val eClass = ePackage.EClassifiers.get(0) as EClass;
		val category = concept.categories.get(0);
		
		Assert.assertEquals("EClass has correct number of structural features", category.properties.size, eClass.EStructuralFeatures.size);
	
		// Test correctness of the generated dynamic attribute
		val eDynamicStructuralFeature = eClass.EStructuralFeatures.get(0);
		Assert.assertEquals("Upper bound of structural feature is set correctly", -1, eDynamicStructuralFeature.upperBound);
		
		val eStaticStructuralFeature = eClass.EStructuralFeatures.get(1);
		val staticArrayModifier = category.properties.get(1).arrayModifier as StaticArrayModifier;
		Assert.assertEquals("Upper bound of structural feature is set correctly", staticArrayModifier.arraySize, eStaticStructuralFeature.upperBound);
	}
	
	@Test
    def void testCreateEReference() {
      	val concept = '''
			Concept «TEST_FQN_NAME» {
				Category «TEST_CATEGORY_NAME»A {
					
				}
				
				Category «TEST_CATEGORY_NAME»B {
					Reference tpRef of Type «TEST_CATEGORY_NAME»A;
				}
			}
	    '''.parse
	    val testLoader = new MockupConceptResourceLoader()
	    testLoader.addTestConceptInstance(TEST_CONCEPT_XMI_PATH, concept)
	    ConceptResourceLoader.injectInstance(testLoader)
		
		dmfCategoriesGenerator.initResources(concept);
		val ePackage = dmfCategoriesGenerator.createEPackageFromConcept(concept);
		
		// We need to watch out with the URIs since the generated concept is just in memory
		// instead of physically being in the folder pluginName/concept.
		// So we manually set the URI to the expected place after resolving all proxy objects to avoid linking errors
		EcoreUtil.resolve((concept.categories.get(1).properties.get(0) as ReferenceProperty).referenceType, concept.eResource.resourceSet);
		concept.eResource.URI = dmfCategoriesGenerator.ecoreModelResourceSet.resources.get(0).URI;
		
		dmfCategoriesGenerator.createCategoryEClassesInEPackage(concept, ePackage);

		val eClassReferenced = ePackage.EClassifiers.get(0) as EClass;
		val eClassReferencing = ePackage.EClassifiers.get(1) as EClass;
		val categoryReferencing = concept.categories.get(1);
		
		Assert.assertEquals("EClass has correct number of structural features", categoryReferencing.properties.size, eClassReferencing.EStructuralFeatures.size);
	
		// Test correctness of the generated reference attribute
		val eReferenceStructuralFeature = eClassReferencing.EStructuralFeatures.get(0);
		val referenceProperty = categoryReferencing.properties.get(0);
		Assert.assertEquals("Structural feature has correct name", referenceProperty.name, eReferenceStructuralFeature.name);
		Assert.assertTrue("Structural feature is an attribute", eReferenceStructuralFeature instanceof EReference);
		val eReference = eReferenceStructuralFeature as EReference;
		Assert.assertEquals("Reference has correct type set", eClassReferenced, eReference.EType);
	}

	@Test
    def void testCreateExtendingEClass() {
      	val concept = '''
			Concept «TEST_FQN_NAME» {
				Category «TEST_CATEGORY_NAME»A {
					IsAbstract;
				}
				
				Category «TEST_CATEGORY_NAME»B extends «TEST_CATEGORY_NAME»A {
					
				}
			}
	    '''.parse
		
		val testLoader = new MockupConceptResourceLoader()
	    testLoader.addTestConceptInstance(TEST_CONCEPT_XMI_PATH, concept)
	    ConceptResourceLoader.injectInstance(testLoader)
		
		dmfCategoriesGenerator.initResources(concept);
		val ePackage = dmfCategoriesGenerator.createEPackageFromConcept(concept);
		
		// We need to watch out with the URIs since the generated concept is just in memory
		// instead of physically being in the folder pluginName/concept.
		// So we manually set the URI to the expected place after resolving all proxy objects to avoid linking errors
		EcoreUtil.resolve(concept.categories.get(1).extendsCategory, concept.eResource.resourceSet);
		concept.eResource.URI = dmfCategoriesGenerator.ecoreModelResourceSet.resources.get(0).URI;
		
		dmfCategoriesGenerator.createCategoryEClassesInEPackage(concept, ePackage);

		val eClassExtended = ePackage.EClassifiers.get(0) as EClass;
		val eClassExtending = ePackage.EClassifiers.get(1) as EClass;

		Assert.assertTrue("Base EClass is abstract", eClassExtended.abstract);		
		Assert.assertTrue("EClass correctly extends base EClass", eClassExtending.ESuperTypes.contains(eClassExtended));
	}
	
	@Test
	def void testCreateEClassWithConceptEReference() {
		val concept = '''
			Concept testConcept hasDMF {
				
				EImport "http://www.virsat.sc.dlr.de/external/tests";
					
				Category TestCategory {
					
					EReference testEReference of Type tests.ExternalTestType;
					
					EReference testEReferenceArray[] of Type tests.ExternalTestType;
					
				}
				
			}
		'''.parse(helper.resourceSet)
		
		dmfCategoriesGenerator.initResources(concept);
		val ePackage = dmfCategoriesGenerator.createEPackageFromConcept(concept);
		
		dmfCategoriesGenerator.createCategoryEClassesInEPackage(concept, ePackage);

		val eClass = ePackage.EClassifiers.get(0) as EClass;
		val category = concept.categories.get(0);
		
		// Test non-array attribute
		val eStructuralFeature = eClass.EStructuralFeatures.get(0);
		val eProperty = category.properties.get(0) as EReferenceProperty;
		Assert.assertEquals("Structural feature has correct name", eProperty.name, eStructuralFeature.name);
		Assert.assertTrue("Structural feature is a reference", eStructuralFeature instanceof EReference);
		val eReference = eStructuralFeature as EReference;
		Assert.assertEquals("Reference has correct type", eProperty.referenceType, eReference.EType);

		// Test correctness of the generated EReference attribute
		val eStructuralArrayFeature = eClass.EStructuralFeatures.get(1);
		val eArrayProperty = category.properties.get(1) as EReferenceProperty;
		Assert.assertEquals("Structural feature has correct name", eArrayProperty.name, eStructuralArrayFeature.name);
		Assert.assertTrue("Structural feature is a reference", eStructuralFeature instanceof EReference);
		val eArrayReference = eStructuralFeature as EReference;
		Assert.assertEquals("Reference has correct type", eArrayProperty.referenceType, eArrayReference.EType);
		Assert.assertEquals("Upper bound of structural feature is set correctly", -1, eStructuralArrayFeature.upperBound);
		
	}

}
