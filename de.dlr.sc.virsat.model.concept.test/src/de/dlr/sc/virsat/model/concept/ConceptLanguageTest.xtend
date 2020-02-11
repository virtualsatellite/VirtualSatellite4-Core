/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept

import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage
import javax.inject.Inject
import org.eclipse.xtext.diagnostics.Diagnostic
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.DynamicArrayModifier
import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider
import de.dlr.sc.virsat.model.concept.validation.ConceptLanguageValidator
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.util.ParseHelper

@RunWith(XtextRunner)
@InjectWith(ConceptLanguageTestInjectorProvider)
class ConceptLanguageTest {

	@Inject extension ValidationTestHelper
	@Inject extension ParseHelper<Concept>
	
	@Before
	def void setUp() {
		ConceptsPackage.eINSTANCE.eClass
		
	}
	
	@Test 
    def void testInvalidConcept() {
        val concept = '''
            invalid
        '''.parse
        
		concept.assertError(ConceptsPackage.eINSTANCE.concept, Diagnostic.SYNTAX_DIAGNOSTIC, "missing 'Concept' at 'invalid'")
		concept.assertError(ConceptsPackage.eINSTANCE.concept, Diagnostic.SYNTAX_DIAGNOSTIC, "mismatched input '<EOF>' expecting '{'")
    }

	@Test 
    def void testMinimalValidConcept() {
    	val conceptName = "de.dlr.sc.virsat.model.concept.test"
        val concept = '''
            Concept «conceptName»{}
        '''.parse
        
        concept.assertNoIssues
        Assert.assertEquals("Concept has proper name", conceptName, concept.name)
    }

	@Test 
    def void testConceptWithDescription() {
    	val description = "Concept description bla bla"
        val concept = '''
            Concept test description '«description»'{}
        '''.parse
        
        concept.assertNoIssues
        Assert.assertEquals("Concept has proper description", description, concept.description)
    }

	@Test 
    def void testValidImport() {
    	val importedConceptName = "testConcept.qwe.qwe";
        val concept = '''
            Concept test{
            	Import «importedConceptName»;
            }
        '''.parse
        
        concept.assertNoIssues
        Assert.assertEquals("There is one imported concept", 1, concept.imports.size)
        Assert.assertEquals("Imported concept has correct name", importedConceptName, concept.imports.get(0).importedNamespace)
    }

	@Test 
    def void testWildcardImport() {
    	val importedConceptName = "testConcept.qwe.qwe.*";
        val concept = '''
            Concept test{
            	Import «importedConceptName»;
            }
        '''.parse
        
        concept.assertNoIssues
        Assert.assertEquals("There is one imported concept", 1, concept.imports.size)
        Assert.assertEquals("Imported concept has correct name", importedConceptName, concept.imports.get(0).importedNamespace)
    }

	@Test 
    def void testTwoImports() {
    	val importedConceptName1 = "testConcept.qwe.qwe";
    	val importedConceptName2 = "testConcept.qwe.qwe.*";
        val concept = '''
            Concept test{
            	Import «importedConceptName1»;
            	Import «importedConceptName2»;
            }
        '''.parse
        
        concept.assertNoIssues
        Assert.assertEquals("There are two imported concepts", 2, concept.imports.size)
        Assert.assertEquals("Imported concept has correct name", importedConceptName1, concept.imports.get(0).importedNamespace)
        Assert.assertEquals("Imported concept has correct name", importedConceptName2, concept.imports.get(1).importedNamespace)
    }

	@Test 
    def void testInvalidImport() {
    	val importedConceptName = "invalidConcept*";
        val concept = '''
            Concept test{
            	Import «importedConceptName»;
            }
        '''.parse
		concept.assertError(ConceptsPackage.eINSTANCE.conceptImport, Diagnostic.SYNTAX_DIAGNOSTIC, "extraneous input '*' expecting ';'")
    }

	@Test 
    def void testSE() {
    	val seName = "testSE"
        val concept = '''
            Concept test{
            	StructuralElement «seName»{}
            }
        '''.parse
        
        concept.assertNoIssues
        Assert.assertEquals("There is one structural element", 1, concept.structuralElements.size)
        val se = concept.structuralElements.get(0)
		
		Assert.assertEquals("SE has correct name", seName, se.name)
        Assert.assertFalse("SE is not root", se.isRootStructuralElement)
        Assert.assertEquals("No applicable for", 0, se.applicableFor.size)
    }

	@Test 
    def void testSEWithDescription() {
    	val seDesc = "SE description bla bla"
        val concept = '''
            Concept test{
            	StructuralElement se description '«seDesc»'{}
            }
        '''.parse
        
        concept.assertNoIssues
        Assert.assertEquals("SE has correct description", seDesc, concept.structuralElements.get(0).description)
    }

	@Test 
    def void testSERoot() {
        val concept = '''
            Concept test{
            	StructuralElement se{IsRootStructuralElement;}
            }
        '''.parse
        
        concept.assertNoIssues
        Assert.assertTrue("SE is root", concept.structuralElements.get(0).isRootStructuralElement)
    }

	@Test 
    def void testSEApplicableFor() {
    	val referencedSeName = "se1";
        val concept = '''
            Concept test{
            	StructuralElement «referencedSeName»{}
            	StructuralElement se2{Applicable For [«referencedSeName»];}
            }
        '''.parse
        
        concept.assertNoIssues
        val se = concept.structuralElements.get(1)
		
        Assert.assertEquals("Applicable for one", 1, se.applicableFor.size)
        Assert.assertEquals("ApplicableFor proper SE", referencedSeName, se.applicableFor.get(0).name);
    }
    
    @Test 
    def void testSEInheritsFrom() {
    	val referencedSeName = "se1";
        val concept = '''
            Concept test{
            	StructuralElement «referencedSeName»{}
            	StructuralElement se2{Inherits From [«referencedSeName»];}
            }
        '''.parse
        
        concept.assertNoIssues
        val se = concept.structuralElements.get(1)
		
        Assert.assertEquals("InheritsFrom one", 1, se.canInheritFrom.size)
        Assert.assertEquals("InheritsFrom proper SE", referencedSeName, se.canInheritFrom.get(0).name);
    }

	@Test 
    def void testSEApplicableForInvalidReference() {
        val concept = '''
            Concept test{
            	StructuralElement se2{Applicable For [invalid];}
            }
        '''.parse
        
		concept.assertError(StructuralPackage.eINSTANCE.structuralElement, Diagnostic.LINKING_DIAGNOSTIC, "Couldn't resolve reference to StructuralElement 'invalid'.")
    }

	@Test 
    def void testSEApplicableForSeveral() {
        val concept = '''
            Concept test{
            	StructuralElement se1{}
            	StructuralElement se2{}
            	StructuralElement se3{Applicable For [se1, se2];}
            }
        '''.parse
        
        concept.assertNoIssues
		
		val se = concept.structuralElements.get(2)
        Assert.assertEquals("Applicable for two", 2, se.applicableFor.size)
    }

	@Test 
    def void testGeneralRelation() {
    	val relName = "relation"
    	val relDesc = "relation description"
        val concept = '''
					Concept test{
					StructuralElement se1{}
					StructuralElement se2{}
					GeneralRelation «relName» description "«relDesc»"{
					Referenced Type se1;
					Applicable For [se2];
					}
					}
        '''.parse
        
        concept.assertNoIssues
        Assert.assertEquals("There is one relation", 1, concept.relations.size)
        val rel = concept.relations.get(0)
		
		Assert.assertEquals("Relation has correct name", relName, rel.name)
		Assert.assertEquals("Relation has correct Description", relDesc, rel.description)
		Assert.assertEquals("Relation references the correct SE", concept.structuralElements.get(0), rel.referencedType)
		Assert.assertEquals("Relation has one Applicable For", 1, rel.applicableFor.size)
		Assert.assertEquals("Relation has correct Applicable For", concept.structuralElements.get(1), rel.applicableFor.get(0))
    }

	@Test 
    def void testInvalidRelation() {
    	val relName = "relation"
        val concept = '''
					Concept test{
					StructuralElement se1{}
					GeneralRelation «relName»{}
					}
        '''.parse
        
		concept.assertError(ConceptsPackage.eINSTANCE.concept, Diagnostic.SYNTAX_DIAGNOSTIC, "mismatched input '}' expecting 'Referenced'")
    }


	@Test 
    def void testCategory() {
    	val categoryName = "category"
        val concept = '''
            Concept test{
            	Category «categoryName»{}
            }
        '''.parse
        
		concept.assertNoIssues
        Assert.assertEquals("There is one category", 1, concept.categories.size)
        val category = concept.categories.get(0)
		
		Assert.assertEquals("Relation has correct name", categoryName, category.name)
    }

	@Test 
    def void testComposedProperty() {
    	val propName = "propName"
        val concept = '''
            Concept test{
            	Category cat{
            		Type «propName» of Category cat;
            	}
            }
        '''.parse
        
		concept.assertNoIssues
        val category = concept.categories.get(0)
        Assert.assertEquals("There is one property", 1, category.properties.size)
        Assert.assertTrue("Property is ComposedProperty", category.properties.get(0) instanceof ComposedProperty)
        val prop = category.properties.get(0) as ComposedProperty
		
		Assert.assertEquals("Property has correct name", propName, prop.name)
		Assert.assertEquals("Composed property points to correct category", category, prop.type)
    }

	@Test 
    def void testReferenceProperty() {
    	val propName = "propName"
        val concept = '''
            Concept test{
            	Category cat{
            		Reference «propName» of Type cat;
            	}
            }
        '''.parse
        
		concept.assertNoIssues
        val category = concept.categories.get(0)
        Assert.assertEquals("There is one property", 1, category.properties.size)
        Assert.assertTrue("Property is ReferenceProperty", category.properties.get(0) instanceof ReferenceProperty)
        val prop = category.properties.get(0) as ReferenceProperty
		
		Assert.assertEquals("Property has correct name", propName, prop.name)
		Assert.assertEquals("Reference property points to correct category", category, prop.referenceType)
    }

	@Test 
    def void testIntProperty() {
    	val propName = "propName"
        val concept = '''
            Concept test{
            	Category cat{
            		IntProperty «propName»;
            	}
            }
        '''.parse
        
		concept.assertNoIssues
        val category = concept.categories.get(0)
        Assert.assertEquals("There is one property", 1, category.properties.size)
        Assert.assertTrue("Property is IntProperty", category.properties.get(0) instanceof IntProperty)
        val prop = category.properties.get(0) as IntProperty
		
		Assert.assertEquals("Property has correct name", propName, prop.name)
    }
    
    @Test 
    def void testFloatProperty() {
    	val propName = "propName"
        val concept = '''
            Concept test{
            	Category cat{
            		FloatProperty «propName»;
            	}
            }
        '''.parse
        
		concept.assertNoIssues
        val category = concept.categories.get(0)
        Assert.assertEquals("There is one property", 1, category.properties.size)
        Assert.assertTrue("Property is FloatProperty", category.properties.get(0) instanceof FloatProperty)
        val prop = category.properties.get(0) as FloatProperty
		
		Assert.assertEquals("Property has correct name", propName, prop.name)
    }
    
    @Test 
    def void testStringProperty() {
    	val propName = "propName"
        val concept = '''
            Concept test{
            	Category cat{
            		StringProperty «propName»;
            	}
            }
        '''.parse
        
		concept.assertNoIssues
        val category = concept.categories.get(0)
        Assert.assertEquals("There is one property", 1, category.properties.size)
        Assert.assertTrue("Property is StringProperty", category.properties.get(0) instanceof StringProperty)
        val prop = category.properties.get(0) as StringProperty
		
		Assert.assertEquals("Property has correct name", propName, prop.name)
    }
    
    @Test 
    def void testBooleanProperty() {
    	val propName = "propName"
        val concept = '''
            Concept test{
            	Category cat{
            		BooleanProperty «propName»;
            	}
            }
        '''.parse
        
		concept.assertNoIssues
        val category = concept.categories.get(0)
        Assert.assertEquals("There is one property", 1, category.properties.size)
        Assert.assertTrue("Property is BooleanProperty", category.properties.get(0) instanceof BooleanProperty)
        val prop = category.properties.get(0) as BooleanProperty
		
		Assert.assertEquals("Property has correct name", propName, prop.name)
    }

    @Test
    def void testEnumProperty() {
    	// enums are not implemented properly yet, so there is no unit test
    }

    @Test 
    def void testStaticArrayProperty() {
    	val arrSize = 5
        val concept = '''
            Concept test{
            	Category cat{
            		IntProperty p [«arrSize»];
            	}
            }
        '''.parse
        
		concept.assertNoIssues
        val category = concept.categories.get(0)
        val prop = category.properties.get(0)
		
		Assert.assertTrue("Property has static array modifier", prop.arrayModifier instanceof StaticArrayModifier)
		Assert.assertEquals("Property has static array of correct size", arrSize, (prop.arrayModifier as StaticArrayModifier).arraySize)
    }

    @Test 
    def void testStaticArrayInvalidSize() {
    	val arrSize = -1
        val concept = '''
            Concept test{
            	Category cat{
            		IntProperty p [«arrSize»];
            	}
            }
        '''.parse
        
		Assert.assertFalse(concept.validate.empty)//should be an error due to negative array size
    }

        
    @Test 
    def void testDynamicArrayProperty() {
        val concept = '''
            Concept test{
            	Category cat{
            		IntProperty p [];
            	}
            }
        '''.parse
        
		concept.assertNoIssues
        val category = concept.categories.get(0)
        val prop = category.properties.get(0)
		
		Assert.assertTrue("Property has static array modifier", prop.arrayModifier instanceof DynamicArrayModifier)
    }
    
    @Test
    def void testTwoCategories() {
    	val cat1Name = "cat1"
    	val cat2Name = "cat2"
        val concept = '''
            Concept test{
            	Category «cat1Name»{}
            	Category «cat2Name»{}
            }
        '''.parse
        
		concept.assertNoIssues
		Assert.assertEquals("Concept has two different categories", 2, concept.categories.size)
		Assert.assertEquals("Category 1 has correct name", cat1Name, concept.categories.get(0).name)
		Assert.assertEquals("Category 2 has correct name", cat2Name, concept.categories.get(1).name)
    }    

    @Test
    def void testDuplicateCategoryNames() {
    	val duplicateName = "duplicateCategoryName"
        val concept = '''
            Concept test{
            	Category «duplicateName»{}
            	Category «duplicateName»{}
            }
        '''.parse

		concept.assertWarning(ConceptsPackage.eINSTANCE.concept, ConceptLanguageValidator.DUPLICATE_CATEGORY_NAME, duplicateName)
    }    

    @Test
    def void testCategoryWithTwoProperties() {
    	val prop1Name = "prop1"
    	val prop2Name = "prop2"
        val concept = '''
            Concept test{
            	Category cat{
            		StringProperty «prop1Name»;
            		StringProperty «prop2Name»;
            	}
            }
        '''.parse
        
		concept.assertNoIssues
        val category = concept.categories.get(0)

		Assert.assertEquals("Concept has two different properties", 2, category.properties.size)
        val prop1 = category.properties.get(0)
        val prop2 = category.properties.get(1)
        
		Assert.assertEquals("Property 1 has correct name", prop1Name, prop1.name)
		Assert.assertEquals("Property 2 has correct name", prop2Name, prop2.name)
    }    

    @Test
    def void testDuplicatePropertyNames() {
    	val duplicatePropName = "DuplicatePropName"
        val concept = '''
            Concept test{
            	Category cat{
            		StringProperty «duplicatePropName»;
            		StringProperty «duplicatePropName»;
            	}
            }
        '''.parse
        
		concept.assertWarning(CategoriesPackage.eINSTANCE.category, ConceptLanguageValidator.DUPLICATE_PROPERTY_NAME, duplicatePropName)
    }

    @Test
    def void testDefaultIntValue() {
    	val defaultValue = "-15"
        val concept = '''
            Concept test{
            	Category cat{
            		IntProperty prop default «defaultValue»;
            	}
            }
        '''.parse
        
		concept.assertNoIssues
        val category = concept.categories.get(0)
        val prop = category.properties.get(0) as IntProperty
		
		Assert.assertEquals("Property has correct defaultValue", defaultValue, prop.defaultValue)
    }
    
    @Test
    def void testDefaultBoolValue() {
    	val defaultValue = "false"
        val concept = '''
            Concept test{
            	Category cat{
            		BooleanProperty prop default «defaultValue»;
            	}
            }
        '''.parse
        
		concept.assertNoIssues
        val category = concept.categories.get(0)
        val prop = category.properties.get(0) as BooleanProperty
		
		Assert.assertEquals("Property has correct defaultValue", defaultValue, prop.defaultValue)
    }

    @Test
    def void testDefaultValueForArrayPropertyProducesInfo() {
        val concept = '''
            Concept test{
            	Category cat{
            		StringProperty prop [] default "empty";
            	}
            }
        '''.parse
		
        concept.assertIssue(PropertydefinitionsPackage.eINSTANCE.IIntrinsicTypeProperty, ConceptLanguageValidator.DEFAULT_VALUE_ON_ARRAY_INFO, Severity.INFO)
    }
}