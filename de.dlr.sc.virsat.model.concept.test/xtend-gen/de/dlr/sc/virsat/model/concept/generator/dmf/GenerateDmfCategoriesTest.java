/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.dmf;

import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider;
import de.dlr.sc.virsat.model.concept.generator.dmf.GenerateDmfCategories;
import de.dlr.sc.virsat.model.concept.resources.ConceptResourceLoader;
import de.dlr.sc.virsat.model.concept.test.MockupConceptResourceLoader;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.external.tests.ExternalModelTestHelper;
import javax.inject.Inject;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(ConceptLanguageTestInjectorProvider.class)
@SuppressWarnings("all")
public class GenerateDmfCategoriesTest {
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;
  
  protected ExternalModelTestHelper helper = new ExternalModelTestHelper();
  
  private static final String TEST_CONCEPT_NAME = "testConcept";
  
  private static final String TEST_FQN_NAME = (("de.dlr.sc.virsat.model.extension" + ".") + GenerateDmfCategoriesTest.TEST_CONCEPT_NAME);
  
  private static final String TEST_CONCEPT_XMI_PATH = (GenerateDmfCategoriesTest.TEST_FQN_NAME + "/concept/concept.xmi");
  
  private static final String TEST_CATEGORY_NAME = "testCategory";
  
  private static final String TEST_CONCEPT_VERSION = "2";
  
  private final GenerateDmfCategories dmfCategoriesGenerator = new GenerateDmfCategories();
  
  @Before
  public void setUp() {
    ConceptsPackage.eINSTANCE.eClass();
    CategoriesPackage.eINSTANCE.eClass();
    PropertydefinitionsPackage.eINSTANCE.eClass();
    this.helper.loadExternalPackage();
  }
  
  @Test
  public void testCreateEPackage() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(GenerateDmfCategoriesTest.TEST_FQN_NAME);
      _builder.append(" version ");
      _builder.append(GenerateDmfCategoriesTest.TEST_CONCEPT_VERSION);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(GenerateDmfCategoriesTest.TEST_CATEGORY_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this.dmfCategoriesGenerator.initResources(concept);
      final EPackage ePackage = this.dmfCategoriesGenerator.createEPackageFromConcept(concept);
      Assert.assertEquals("EPackage name is set correctly", GenerateDmfCategoriesTest.TEST_CONCEPT_NAME, ePackage.getName());
      Assert.assertEquals("EPackage nsPrefix is set correctly", (GenerateDmfCategories.MODEL_NS_PREFIX_PREFIX + GenerateDmfCategoriesTest.TEST_CONCEPT_NAME), ePackage.getNsPrefix());
      String _version = concept.getVersion();
      String _plus = (ActiveConceptHelper.MODEL_URI_PREFIX + _version);
      String _plus_1 = (_plus + "/");
      String _plus_2 = (_plus_1 + GenerateDmfCategoriesTest.TEST_CONCEPT_NAME);
      Assert.assertEquals("EPackage nsUri is set correctly", _plus_2, ePackage.getNsURI());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateEClass() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(GenerateDmfCategoriesTest.TEST_FQN_NAME);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(GenerateDmfCategoriesTest.TEST_CATEGORY_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this.dmfCategoriesGenerator.initResources(concept);
      final EPackage ePackage = this.dmfCategoriesGenerator.createEPackageFromConcept(concept);
      this.dmfCategoriesGenerator.createCategoryEClassesInEPackage(concept, ePackage);
      Assert.assertEquals("EPackage has correct number of classifiers", concept.getCategories().size(), ePackage.getEClassifiers().size());
      EClassifier _get = ePackage.getEClassifiers().get(0);
      Assert.assertTrue("EPackage classifier is an EClass", (_get instanceof EClass));
      EClassifier _get_1 = ePackage.getEClassifiers().get(0);
      final EClass eClass = ((EClass) _get_1);
      final Category category = concept.getCategories().get(0);
      Assert.assertEquals("EClass name is set correctly", category.getName(), eClass.getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateIntrinsicEAttributes() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(GenerateDmfCategoriesTest.TEST_FQN_NAME);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(GenerateDmfCategoriesTest.TEST_CATEGORY_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("IntProperty tpInt;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("StringProperty tpSring;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("FloatProperty tpFloat;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("BooleanProperty tpBoolean;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Resource tpResource;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this.dmfCategoriesGenerator.initResources(concept);
      final EPackage ePackage = this.dmfCategoriesGenerator.createEPackageFromConcept(concept);
      this.dmfCategoriesGenerator.createCategoryEClassesInEPackage(concept, ePackage);
      EClassifier _get = ePackage.getEClassifiers().get(0);
      final EClass eClass = ((EClass) _get);
      final Category category = concept.getCategories().get(0);
      Assert.assertEquals("EClass has correct number of structural features", category.getProperties().size(), eClass.getEStructuralFeatures().size());
      final EStructuralFeature eIntStructuralFeature = eClass.getEStructuralFeatures().get(0);
      final AProperty intProperty = category.getProperties().get(0);
      Assert.assertEquals("Structural feature has correct name", intProperty.getName(), eIntStructuralFeature.getName());
      Assert.assertTrue("Structural feature is an attribute", (eIntStructuralFeature instanceof EAttribute));
      final EAttribute eIntAttribute = ((EAttribute) eIntStructuralFeature);
      Assert.assertEquals("Attribute has correct type", EcorePackage.Literals.EINT, eIntAttribute.getEType());
      final EStructuralFeature eStringStructuralFeature = eClass.getEStructuralFeatures().get(1);
      final AProperty stringProperty = category.getProperties().get(1);
      Assert.assertEquals("Structural feature has correct name", stringProperty.getName(), eStringStructuralFeature.getName());
      Assert.assertTrue("Structural feature is an attribute", (eStringStructuralFeature instanceof EAttribute));
      final EAttribute eStringAttribute = ((EAttribute) eStringStructuralFeature);
      Assert.assertEquals("Attribute has correct type", EcorePackage.Literals.ESTRING, eStringAttribute.getEType());
      final EStructuralFeature eFloatStructuralFeature = eClass.getEStructuralFeatures().get(2);
      final AProperty floatProperty = category.getProperties().get(2);
      Assert.assertEquals("Structural feature has correct name", floatProperty.getName(), eFloatStructuralFeature.getName());
      Assert.assertTrue("Structural feature is an attribute", (eFloatStructuralFeature instanceof EAttribute));
      final EAttribute eFloatAttribute = ((EAttribute) eFloatStructuralFeature);
      Assert.assertEquals("Attribute has correct type", EcorePackage.Literals.EDOUBLE, eFloatAttribute.getEType());
      final EStructuralFeature eBooleanStructuralFeature = eClass.getEStructuralFeatures().get(3);
      final AProperty booleanProperty = category.getProperties().get(3);
      Assert.assertEquals("Structural feature has correct name", booleanProperty.getName(), eBooleanStructuralFeature.getName());
      Assert.assertTrue("Structural feature is an attribute", (eBooleanStructuralFeature instanceof EAttribute));
      final EAttribute eBooleanAttribute = ((EAttribute) eBooleanStructuralFeature);
      Assert.assertEquals("Attribute has correct type", EcorePackage.Literals.EBOOLEAN, eBooleanAttribute.getEType());
      final EStructuralFeature eResourceStructuralFeature = eClass.getEStructuralFeatures().get(4);
      final AProperty resourceProperty = category.getProperties().get(4);
      Assert.assertEquals("Structural feature has correct name", resourceProperty.getName(), eResourceStructuralFeature.getName());
      Assert.assertTrue("Structural feature is an attribute", (eResourceStructuralFeature instanceof EAttribute));
      final EAttribute eResourceAttribute = ((EAttribute) eResourceStructuralFeature);
      Assert.assertEquals("Attribute has correct type", EcorePackage.Literals.ESTRING, eResourceAttribute.getEType());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateArrayEAttributes() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(GenerateDmfCategoriesTest.TEST_FQN_NAME);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(GenerateDmfCategoriesTest.TEST_CATEGORY_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("IntProperty[] tpInt;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IntProperty[5] tpInt;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      this.dmfCategoriesGenerator.initResources(concept);
      final EPackage ePackage = this.dmfCategoriesGenerator.createEPackageFromConcept(concept);
      this.dmfCategoriesGenerator.createCategoryEClassesInEPackage(concept, ePackage);
      EClassifier _get = ePackage.getEClassifiers().get(0);
      final EClass eClass = ((EClass) _get);
      final Category category = concept.getCategories().get(0);
      Assert.assertEquals("EClass has correct number of structural features", category.getProperties().size(), eClass.getEStructuralFeatures().size());
      final EStructuralFeature eDynamicStructuralFeature = eClass.getEStructuralFeatures().get(0);
      Assert.assertEquals("Upper bound of structural feature is set correctly", (-1), eDynamicStructuralFeature.getUpperBound());
      final EStructuralFeature eStaticStructuralFeature = eClass.getEStructuralFeatures().get(1);
      IArrayModifier _arrayModifier = category.getProperties().get(1).getArrayModifier();
      final StaticArrayModifier staticArrayModifier = ((StaticArrayModifier) _arrayModifier);
      Assert.assertEquals("Upper bound of structural feature is set correctly", staticArrayModifier.getArraySize(), eStaticStructuralFeature.getUpperBound());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateEReference() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(GenerateDmfCategoriesTest.TEST_FQN_NAME);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(GenerateDmfCategoriesTest.TEST_CATEGORY_NAME, "\t");
      _builder.append("A {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(GenerateDmfCategoriesTest.TEST_CATEGORY_NAME, "\t");
      _builder.append("B {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("Reference tpRef of Type ");
      _builder.append(GenerateDmfCategoriesTest.TEST_CATEGORY_NAME, "\t\t");
      _builder.append("A;");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      final MockupConceptResourceLoader testLoader = new MockupConceptResourceLoader();
      testLoader.addTestConceptInstance(GenerateDmfCategoriesTest.TEST_CONCEPT_XMI_PATH, concept);
      ConceptResourceLoader.injectInstance(testLoader);
      this.dmfCategoriesGenerator.initResources(concept);
      final EPackage ePackage = this.dmfCategoriesGenerator.createEPackageFromConcept(concept);
      AProperty _get = concept.getCategories().get(1).getProperties().get(0);
      EcoreUtil.resolve(((ReferenceProperty) _get).getReferenceType(), concept.eResource().getResourceSet());
      Resource _eResource = concept.eResource();
      _eResource.setURI(this.dmfCategoriesGenerator.getEcoreModelResourceSet().getResources().get(0).getURI());
      this.dmfCategoriesGenerator.createCategoryEClassesInEPackage(concept, ePackage);
      EClassifier _get_1 = ePackage.getEClassifiers().get(0);
      final EClass eClassReferenced = ((EClass) _get_1);
      EClassifier _get_2 = ePackage.getEClassifiers().get(1);
      final EClass eClassReferencing = ((EClass) _get_2);
      final Category categoryReferencing = concept.getCategories().get(1);
      Assert.assertEquals("EClass has correct number of structural features", categoryReferencing.getProperties().size(), eClassReferencing.getEStructuralFeatures().size());
      final EStructuralFeature eReferenceStructuralFeature = eClassReferencing.getEStructuralFeatures().get(0);
      final AProperty referenceProperty = categoryReferencing.getProperties().get(0);
      Assert.assertEquals("Structural feature has correct name", referenceProperty.getName(), eReferenceStructuralFeature.getName());
      Assert.assertTrue("Structural feature is an attribute", (eReferenceStructuralFeature instanceof EReference));
      final EReference eReference = ((EReference) eReferenceStructuralFeature);
      Assert.assertEquals("Reference has correct type set", eClassReferenced, eReference.getEType());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateExtendingEClass() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(GenerateDmfCategoriesTest.TEST_FQN_NAME);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(GenerateDmfCategoriesTest.TEST_CATEGORY_NAME, "\t");
      _builder.append("A {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("IsAbstract;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(GenerateDmfCategoriesTest.TEST_CATEGORY_NAME, "\t");
      _builder.append("B extends ");
      _builder.append(GenerateDmfCategoriesTest.TEST_CATEGORY_NAME, "\t");
      _builder.append("A {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder);
      final MockupConceptResourceLoader testLoader = new MockupConceptResourceLoader();
      testLoader.addTestConceptInstance(GenerateDmfCategoriesTest.TEST_CONCEPT_XMI_PATH, concept);
      ConceptResourceLoader.injectInstance(testLoader);
      this.dmfCategoriesGenerator.initResources(concept);
      final EPackage ePackage = this.dmfCategoriesGenerator.createEPackageFromConcept(concept);
      EcoreUtil.resolve(concept.getCategories().get(1).getExtendsCategory(), concept.eResource().getResourceSet());
      Resource _eResource = concept.eResource();
      _eResource.setURI(this.dmfCategoriesGenerator.getEcoreModelResourceSet().getResources().get(0).getURI());
      this.dmfCategoriesGenerator.createCategoryEClassesInEPackage(concept, ePackage);
      EClassifier _get = ePackage.getEClassifiers().get(0);
      final EClass eClassExtended = ((EClass) _get);
      EClassifier _get_1 = ePackage.getEClassifiers().get(1);
      final EClass eClassExtending = ((EClass) _get_1);
      Assert.assertTrue("Base EClass is abstract", eClassExtended.isAbstract());
      Assert.assertTrue("EClass correctly extends base EClass", eClassExtending.getESuperTypes().contains(eClassExtended));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateEClassWithConceptEReference() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept testConcept hasDMF {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("EImport \"http://www.virsat.sc.dlr.de/external/tests\";");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category TestCategory {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("EReference testEReference of Type tests.ExternalTestType;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("EReference testEReferenceArray[] of Type tests.ExternalTestType;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Concept concept = this._parseHelper.parse(_builder, this.helper.getResourceSet());
      this.dmfCategoriesGenerator.initResources(concept);
      final EPackage ePackage = this.dmfCategoriesGenerator.createEPackageFromConcept(concept);
      this.dmfCategoriesGenerator.createCategoryEClassesInEPackage(concept, ePackage);
      EClassifier _get = ePackage.getEClassifiers().get(0);
      final EClass eClass = ((EClass) _get);
      final Category category = concept.getCategories().get(0);
      final EStructuralFeature eStructuralFeature = eClass.getEStructuralFeatures().get(0);
      AProperty _get_1 = category.getProperties().get(0);
      final EReferenceProperty eProperty = ((EReferenceProperty) _get_1);
      Assert.assertEquals("Structural feature has correct name", eProperty.getName(), eStructuralFeature.getName());
      Assert.assertTrue("Structural feature is a reference", (eStructuralFeature instanceof EReference));
      final EReference eReference = ((EReference) eStructuralFeature);
      Assert.assertEquals("Reference has correct type", eProperty.getReferenceType(), eReference.getEType());
      final EStructuralFeature eStructuralArrayFeature = eClass.getEStructuralFeatures().get(1);
      AProperty _get_2 = category.getProperties().get(1);
      final EReferenceProperty eArrayProperty = ((EReferenceProperty) _get_2);
      Assert.assertEquals("Structural feature has correct name", eArrayProperty.getName(), eStructuralArrayFeature.getName());
      Assert.assertTrue("Structural feature is a reference", (eStructuralFeature instanceof EReference));
      final EReference eArrayReference = ((EReference) eStructuralFeature);
      Assert.assertEquals("Reference has correct type", eArrayProperty.getReferenceType(), eArrayReference.getEType());
      Assert.assertEquals("Upper bound of structural feature is set correctly", (-1), eStructuralArrayFeature.getUpperBound());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
