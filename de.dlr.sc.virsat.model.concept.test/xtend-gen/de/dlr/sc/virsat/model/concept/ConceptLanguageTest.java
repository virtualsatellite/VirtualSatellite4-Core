/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept;

import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider;
import de.dlr.sc.virsat.model.concept.validation.ConceptLanguageValidator;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.DynamicArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage;
import de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;
import javax.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.diagnostics.Diagnostic;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(ConceptLanguageTestInjectorProvider.class)
@SuppressWarnings("all")
public class ConceptLanguageTest {
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;
  
  @Before
  public void setUp() {
    ConceptsPackage.eINSTANCE.eClass();
  }
  
  @Test
  public void testInvalidConcept() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("invalid");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertError(concept, ConceptsPackage.eINSTANCE.getConcept(), Diagnostic.SYNTAX_DIAGNOSTIC, "missing \'Concept\' at \'invalid\'");
      this._validationTestHelper.assertError(concept, ConceptsPackage.eINSTANCE.getConcept(), Diagnostic.SYNTAX_DIAGNOSTIC, "mismatched input \'<EOF>\' expecting \'{\'");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testMinimalValidConcept() {
    try {
      final String conceptName = "de.dlr.sc.virsat.model.concept.test";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(conceptName);
      _builder.append("{}");
      _builder.newLineIfNotEmpty();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      Assert.assertEquals("Concept has proper name", conceptName, concept.getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testConceptWithDescription() {
    try {
      final String description = "Concept description bla bla";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test description \'");
      _builder.append(description);
      _builder.append("\'{}");
      _builder.newLineIfNotEmpty();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      Assert.assertEquals("Concept has proper description", description, concept.getDescription());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testValidImport() {
    try {
      final String importedConceptName = "testConcept.qwe.qwe";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Import ");
      _builder.append(importedConceptName, "\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      Assert.assertEquals("There is one imported concept", 1, concept.getImports().size());
      Assert.assertEquals("Imported concept has correct name", importedConceptName, concept.getImports().get(0).getImportedNamespace());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testWildcardImport() {
    try {
      final String importedConceptName = "testConcept.qwe.qwe.*";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Import ");
      _builder.append(importedConceptName, "\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      Assert.assertEquals("There is one imported concept", 1, concept.getImports().size());
      Assert.assertEquals("Imported concept has correct name", importedConceptName, concept.getImports().get(0).getImportedNamespace());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testTwoImports() {
    try {
      final String importedConceptName1 = "testConcept.qwe.qwe";
      final String importedConceptName2 = "testConcept.qwe.qwe.*";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Import ");
      _builder.append(importedConceptName1, "\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Import ");
      _builder.append(importedConceptName2, "\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      Assert.assertEquals("There are two imported concepts", 2, concept.getImports().size());
      Assert.assertEquals("Imported concept has correct name", importedConceptName1, concept.getImports().get(0).getImportedNamespace());
      Assert.assertEquals("Imported concept has correct name", importedConceptName2, concept.getImports().get(1).getImportedNamespace());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testInvalidImport() {
    try {
      final String importedConceptName = "invalidConcept*";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Import ");
      _builder.append(importedConceptName, "\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertError(concept, ConceptsPackage.eINSTANCE.getConceptImport(), Diagnostic.SYNTAX_DIAGNOSTIC, "extraneous input \'*\' expecting \';\'");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testSE() {
    try {
      final String seName = "testSE";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StructuralElement ");
      _builder.append(seName, "\t");
      _builder.append("{}");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      Assert.assertEquals("There is one structural element", 1, concept.getStructuralElements().size());
      final StructuralElement se = concept.getStructuralElements().get(0);
      Assert.assertEquals("SE has correct name", seName, se.getName());
      Assert.assertFalse("SE is not root", se.isIsRootStructuralElement());
      Assert.assertEquals("No applicable for", 0, se.getApplicableFor().size());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testSEWithDescription() {
    try {
      final String seDesc = "SE description bla bla";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StructuralElement se description \'");
      _builder.append(seDesc, "\t");
      _builder.append("\'{}");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      Assert.assertEquals("SE has correct description", seDesc, concept.getStructuralElements().get(0).getDescription());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testSERoot() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StructuralElement se{IsRootStructuralElement;}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      Assert.assertTrue("SE is root", concept.getStructuralElements().get(0).isIsRootStructuralElement());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testSEApplicableFor() {
    try {
      final String referencedSeName = "se1";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StructuralElement ");
      _builder.append(referencedSeName, "\t");
      _builder.append("{}");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("StructuralElement se2{Applicable For [");
      _builder.append(referencedSeName, "\t");
      _builder.append("];}");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      final StructuralElement se = concept.getStructuralElements().get(1);
      Assert.assertEquals("Applicable for one", 1, se.getApplicableFor().size());
      Assert.assertEquals("ApplicableFor proper SE", referencedSeName, se.getApplicableFor().get(0).getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testSEInheritsFrom() {
    try {
      final String referencedSeName = "se1";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StructuralElement ");
      _builder.append(referencedSeName, "\t");
      _builder.append("{}");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("StructuralElement se2{Inherits From [");
      _builder.append(referencedSeName, "\t");
      _builder.append("];}");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      final StructuralElement se = concept.getStructuralElements().get(1);
      Assert.assertEquals("InheritsFrom one", 1, se.getCanInheritFrom().size());
      Assert.assertEquals("InheritsFrom proper SE", referencedSeName, se.getCanInheritFrom().get(0).getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testSEApplicableForInvalidReference() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StructuralElement se2{Applicable For [invalid];}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertError(concept, StructuralPackage.eINSTANCE.getStructuralElement(), Diagnostic.LINKING_DIAGNOSTIC, "Couldn\'t resolve reference to StructuralElement \'invalid\'.");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testSEApplicableForSeveral() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StructuralElement se1{}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StructuralElement se2{}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StructuralElement se3{Applicable For [se1, se2];}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      final StructuralElement se = concept.getStructuralElements().get(2);
      Assert.assertEquals("Applicable for two", 2, se.getApplicableFor().size());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testGeneralRelation() {
    try {
      final String relName = "relation";
      final String relDesc = "relation description";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("StructuralElement se1{}");
      _builder.newLine();
      _builder.append("StructuralElement se2{}");
      _builder.newLine();
      _builder.append("GeneralRelation ");
      _builder.append(relName);
      _builder.append(" description \"");
      _builder.append(relDesc);
      _builder.append("\"{");
      _builder.newLineIfNotEmpty();
      _builder.append("Referenced Type se1;");
      _builder.newLine();
      _builder.append("Applicable For [se2];");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      Assert.assertEquals("There is one relation", 1, concept.getRelations().size());
      final GeneralRelation rel = concept.getRelations().get(0);
      Assert.assertEquals("Relation has correct name", relName, rel.getName());
      Assert.assertEquals("Relation has correct Description", relDesc, rel.getDescription());
      Assert.assertEquals("Relation references the correct SE", concept.getStructuralElements().get(0), rel.getReferencedType());
      Assert.assertEquals("Relation has one Applicable For", 1, rel.getApplicableFor().size());
      Assert.assertEquals("Relation has correct Applicable For", concept.getStructuralElements().get(1), rel.getApplicableFor().get(0));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testInvalidRelation() {
    try {
      final String relName = "relation";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("StructuralElement se1{}");
      _builder.newLine();
      _builder.append("GeneralRelation ");
      _builder.append(relName);
      _builder.append("{}");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertError(concept, ConceptsPackage.eINSTANCE.getConcept(), Diagnostic.SYNTAX_DIAGNOSTIC, "mismatched input \'}\' expecting \'Referenced\'");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCategory() {
    try {
      final String categoryName = "category";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(categoryName, "\t");
      _builder.append("{}");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      Assert.assertEquals("There is one category", 1, concept.getCategories().size());
      final Category category = concept.getCategories().get(0);
      Assert.assertEquals("Relation has correct name", categoryName, category.getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testComposedProperty() {
    try {
      final String propName = "propName";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category cat{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Type ");
      _builder.append(propName, "\t\t");
      _builder.append(" of Category cat;");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      final Category category = concept.getCategories().get(0);
      Assert.assertEquals("There is one property", 1, category.getProperties().size());
      AProperty _get = category.getProperties().get(0);
      Assert.assertTrue("Property is ComposedProperty", (_get instanceof ComposedProperty));
      AProperty _get_1 = category.getProperties().get(0);
      final ComposedProperty prop = ((ComposedProperty) _get_1);
      Assert.assertEquals("Property has correct name", propName, prop.getName());
      Assert.assertEquals("Composed property points to correct category", category, prop.getType());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testReferenceProperty() {
    try {
      final String propName = "propName";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category cat{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Reference ");
      _builder.append(propName, "\t\t");
      _builder.append(" of Type cat;");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      final Category category = concept.getCategories().get(0);
      Assert.assertEquals("There is one property", 1, category.getProperties().size());
      AProperty _get = category.getProperties().get(0);
      Assert.assertTrue("Property is ReferenceProperty", (_get instanceof ReferenceProperty));
      AProperty _get_1 = category.getProperties().get(0);
      final ReferenceProperty prop = ((ReferenceProperty) _get_1);
      Assert.assertEquals("Property has correct name", propName, prop.getName());
      Assert.assertEquals("Reference property points to correct category", category, prop.getReferenceType());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testIntProperty() {
    try {
      final String propName = "propName";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category cat{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IntProperty ");
      _builder.append(propName, "\t\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      final Category category = concept.getCategories().get(0);
      Assert.assertEquals("There is one property", 1, category.getProperties().size());
      AProperty _get = category.getProperties().get(0);
      Assert.assertTrue("Property is IntProperty", (_get instanceof IntProperty));
      AProperty _get_1 = category.getProperties().get(0);
      final IntProperty prop = ((IntProperty) _get_1);
      Assert.assertEquals("Property has correct name", propName, prop.getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testFloatProperty() {
    try {
      final String propName = "propName";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category cat{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("FloatProperty ");
      _builder.append(propName, "\t\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      final Category category = concept.getCategories().get(0);
      Assert.assertEquals("There is one property", 1, category.getProperties().size());
      AProperty _get = category.getProperties().get(0);
      Assert.assertTrue("Property is FloatProperty", (_get instanceof FloatProperty));
      AProperty _get_1 = category.getProperties().get(0);
      final FloatProperty prop = ((FloatProperty) _get_1);
      Assert.assertEquals("Property has correct name", propName, prop.getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testStringProperty() {
    try {
      final String propName = "propName";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category cat{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("StringProperty ");
      _builder.append(propName, "\t\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      final Category category = concept.getCategories().get(0);
      Assert.assertEquals("There is one property", 1, category.getProperties().size());
      AProperty _get = category.getProperties().get(0);
      Assert.assertTrue("Property is StringProperty", (_get instanceof StringProperty));
      AProperty _get_1 = category.getProperties().get(0);
      final StringProperty prop = ((StringProperty) _get_1);
      Assert.assertEquals("Property has correct name", propName, prop.getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testBooleanProperty() {
    try {
      final String propName = "propName";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category cat{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("BooleanProperty ");
      _builder.append(propName, "\t\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      final Category category = concept.getCategories().get(0);
      Assert.assertEquals("There is one property", 1, category.getProperties().size());
      AProperty _get = category.getProperties().get(0);
      Assert.assertTrue("Property is BooleanProperty", (_get instanceof BooleanProperty));
      AProperty _get_1 = category.getProperties().get(0);
      final BooleanProperty prop = ((BooleanProperty) _get_1);
      Assert.assertEquals("Property has correct name", propName, prop.getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testEnumProperty() {
  }
  
  @Test
  public void testStaticArrayProperty() {
    try {
      final int arrSize = 5;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category cat{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IntProperty p [");
      _builder.append(arrSize, "\t\t");
      _builder.append("];");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      final Category category = concept.getCategories().get(0);
      final AProperty prop = category.getProperties().get(0);
      IArrayModifier _arrayModifier = prop.getArrayModifier();
      Assert.assertTrue("Property has static array modifier", (_arrayModifier instanceof StaticArrayModifier));
      IArrayModifier _arrayModifier_1 = prop.getArrayModifier();
      Assert.assertEquals("Property has static array of correct size", arrSize, ((StaticArrayModifier) _arrayModifier_1).getArraySize());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testStaticArrayInvalidSize() {
    try {
      final int arrSize = (-1);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category cat{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IntProperty p [");
      _builder.append(arrSize, "\t\t");
      _builder.append("];");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      Assert.assertFalse(this._validationTestHelper.validate(concept).isEmpty());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testDynamicArrayProperty() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category cat{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IntProperty p [];");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      final Category category = concept.getCategories().get(0);
      final AProperty prop = category.getProperties().get(0);
      IArrayModifier _arrayModifier = prop.getArrayModifier();
      Assert.assertTrue("Property has static array modifier", (_arrayModifier instanceof DynamicArrayModifier));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testTwoCategories() {
    try {
      final String cat1Name = "cat1";
      final String cat2Name = "cat2";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(cat1Name, "\t");
      _builder.append("{}");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(cat2Name, "\t");
      _builder.append("{}");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      Assert.assertEquals("Concept has two different categories", 2, concept.getCategories().size());
      Assert.assertEquals("Category 1 has correct name", cat1Name, concept.getCategories().get(0).getName());
      Assert.assertEquals("Category 2 has correct name", cat2Name, concept.getCategories().get(1).getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testDuplicateCategoryNames() {
    try {
      final String duplicateName = "duplicateCategoryName";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(duplicateName, "\t");
      _builder.append("{}");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(duplicateName, "\t");
      _builder.append("{}");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertWarning(concept, ConceptsPackage.eINSTANCE.getConcept(), ConceptLanguageValidator.DUPLICATE_CATEGORY_NAME, duplicateName);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCategoryWithTwoProperties() {
    try {
      final String prop1Name = "prop1";
      final String prop2Name = "prop2";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category cat{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("StringProperty ");
      _builder.append(prop1Name, "\t\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("StringProperty ");
      _builder.append(prop2Name, "\t\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      final Category category = concept.getCategories().get(0);
      Assert.assertEquals("Concept has two different properties", 2, category.getProperties().size());
      final AProperty prop1 = category.getProperties().get(0);
      final AProperty prop2 = category.getProperties().get(1);
      Assert.assertEquals("Property 1 has correct name", prop1Name, prop1.getName());
      Assert.assertEquals("Property 2 has correct name", prop2Name, prop2.getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testDuplicatePropertyNames() {
    try {
      final String duplicatePropName = "DuplicatePropName";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category cat{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("StringProperty ");
      _builder.append(duplicatePropName, "\t\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("StringProperty ");
      _builder.append(duplicatePropName, "\t\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertWarning(concept, CategoriesPackage.eINSTANCE.getCategory(), ConceptLanguageValidator.DUPLICATE_PROPERTY_NAME, duplicatePropName);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testDefaultIntValue() {
    try {
      final String defaultValue = "-15";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category cat{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IntProperty prop default ");
      _builder.append(defaultValue, "\t\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      final Category category = concept.getCategories().get(0);
      AProperty _get = category.getProperties().get(0);
      final IntProperty prop = ((IntProperty) _get);
      Assert.assertEquals("Property has correct defaultValue", defaultValue, prop.getDefaultValue());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testDefaultBoolValue() {
    try {
      final String defaultValue = "false";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category cat{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("BooleanProperty prop default ");
      _builder.append(defaultValue, "\t\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertNoIssues(concept);
      final Category category = concept.getCategories().get(0);
      AProperty _get = category.getProperties().get(0);
      final BooleanProperty prop = ((BooleanProperty) _get);
      Assert.assertEquals("Property has correct defaultValue", defaultValue, prop.getDefaultValue());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testDefaultValueForArrayPropertyProducesInfo() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept test{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category cat{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("StringProperty prop [] default \"empty\";");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this._validationTestHelper.assertIssue(concept, PropertydefinitionsPackage.eINSTANCE.getIIntrinsicTypeProperty(), ConceptLanguageValidator.DEFAULT_VALUE_ON_ARRAY_INFO, Severity.INFO);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
