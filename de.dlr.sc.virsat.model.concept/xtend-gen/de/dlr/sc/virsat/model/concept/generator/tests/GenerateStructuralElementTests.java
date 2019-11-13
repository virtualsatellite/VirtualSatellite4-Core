/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.tests;

import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator;
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider;
import de.dlr.sc.virsat.model.concept.generator.ImportManager;
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil;
import de.dlr.sc.virsat.model.concept.types.factory.BeanRegistry;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import java.util.Set;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * This class generates the test cases for the StructruralElements
 */
@SuppressWarnings("all")
public class GenerateStructuralElementTests extends AGeneratorGapGenerator<StructuralElement> {
  public static String getConcreteClassName(final StructuralElement typeDefinition) {
    String _firstUpper = StringExtensions.toFirstUpper(typeDefinition.getName());
    return (_firstUpper + "Test");
  }
  
  public static String getAbstractClassName(final StructuralElement typeDefinition) {
    String _firstUpper = StringExtensions.toFirstUpper(typeDefinition.getName());
    String _plus = ("A" + _firstUpper);
    return (_plus + "Test");
  }
  
  @Override
  public String createConcreteClassFileName(final Concept concept, final StructuralElement conceptPart) {
    String _name = concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".test/src/");
    String _packageFolder = this.getPackageFolder(concept);
    String _plus_2 = (_plus_1 + _packageFolder);
    String _plus_3 = (_plus_2 + "/");
    String _concreteClassName = GenerateStructuralElementTests.getConcreteClassName(conceptPart);
    String _plus_4 = (_plus_3 + _concreteClassName);
    return (_plus_4 + ".java");
  }
  
  @Override
  public String createAbstractClassFileName(final Concept concept, final StructuralElement conceptPart) {
    String _name = concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".test/src-gen/");
    String _packageFolder = this.getPackageFolder(concept);
    String _plus_2 = (_plus_1 + _packageFolder);
    String _plus_3 = (_plus_2 + "/");
    String _abstractClassName = GenerateStructuralElementTests.getAbstractClassName(conceptPart);
    String _plus_4 = (_plus_3 + _abstractClassName);
    return (_plus_4 + ".java");
  }
  
  @Override
  protected String getPackage(final Concept concept) {
    String _name = concept.getName();
    String _plus = (_name + ".");
    return (_plus + GenerateStructuralElementTests.PACKAGE_FOLDER);
  }
  
  public static final String PACKAGE_FOLDER = BeanRegistry.BEAN_PACKAGE_NAME;
  
  @Override
  public void serializeModel(final Concept concept, final IFileSystemAccess fsa) {
    TreeIterator<Object> iterator = EcoreUtil.<Object>getAllContents(concept, true);
    final Procedure1<Object> _function = (Object it) -> {
      if ((it instanceof StructuralElement)) {
        CharSequence fileOutputAClass = this.createAbstractClass(concept, ((StructuralElement)it));
        fsa.generateFile(this.createAbstractClassFileName(concept, ((StructuralElement)it)), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);
        CharSequence fileOutputClass = this.createConcreteClass(concept, ((StructuralElement)it));
        fsa.generateFile(this.createConcreteClassFileName(concept, ((StructuralElement)it)), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
      }
    };
    IteratorExtensions.<Object>forEach(iterator, _function);
  }
  
  /**
   * Declare the package statement of the java file
   */
  @Override
  public CharSequence declarePackage(final String pluginPackage) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(pluginPackage);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  /**
   * Write down all the import statements needed by this java file
   */
  @Override
  public CharSequence declareImports(final ImportManager importManager, final Concept concept, final StructuralElement conceptPart, final String conceptPackage) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Import Statements");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    {
      boolean _isEmpty = importManager.getImportedClasses().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        {
          Set<String> _importedClasses = importManager.getImportedClasses();
          for(final String clazz : _importedClasses) {
            _builder.append("import ");
            _builder.append(clazz);
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  /**
   * Entry method to write the class body
   */
  @Override
  public CharSequence declareAClass(final Concept concept, final StructuralElement se, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register(Exception.class);
    _builder.newLineIfNotEmpty();
    importManager.register(Concept.class);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Class Declaration");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import static org.junit.Assert.assertEquals;");
    _builder.newLine();
    _builder.append("import static org.junit.Assert.assertNotNull;");
    _builder.newLine();
    _builder.append("import static org.junit.Assert.assertNull;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.junit.After;");
    _builder.newLine();
    _builder.append("import org.junit.Before;");
    _builder.newLine();
    _builder.append("import org.junit.Test;");
    _builder.newLine();
    _builder.newLine();
    CharSequence _generateAClassHeader = ConceptGeneratorUtil.generateAClassHeader(se);
    _builder.append(_generateAClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public abstract class A");
    String _firstUpper = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper);
    _builder.append("Test {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected Concept concept;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Before");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void setUp() throws Exception {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String conceptXmiPluginPath = \"");
    String _name = concept.getName();
    _builder.append(_name, "\t\t");
    _builder.append("/concept/concept.xmi\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("concept = de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPath);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@After");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void tearDown() throws Exception {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _declareConstructorTest = this.declareConstructorTest(se, importManager);
    _builder.append(_declareConstructorTest, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  @Override
  public CharSequence declareClass(final Concept concept, final StructuralElement se, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Class Declaration");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.newLine();
    CharSequence _generateClassHeader = ConceptGeneratorUtil.generateClassHeader(se);
    _builder.append(_generateClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public class ");
    String _firstUpper = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper);
    _builder.append("Test extends A");
    String _firstUpper_1 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_1);
    _builder.append("Test {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Method to create the constructor of the java category bean
   */
  protected CharSequence declareConstructorTest(final StructuralElement se, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register(StructuralElementInstance.class);
    _builder.newLineIfNotEmpty();
    importManager.register(StructuralFactory.class);
    _builder.newLineIfNotEmpty();
    importManager.register(Concept.class);
    _builder.newLineIfNotEmpty();
    importManager.register(se);
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Constructor Test Cases");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Test");
    _builder.newLine();
    _builder.append("public void test");
    String _firstUpper = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper);
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _firstUpper_1 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_1, "\t");
    _builder.append(" test");
    String _firstUpper_2 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_2, "\t");
    _builder.append(" = new ");
    String _firstUpper_3 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_3, "\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("assertNull(\"There is no internal DVLM object\", test");
    String _firstUpper_4 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_4, "\t");
    _builder.append(".getStructuralElementInstance());");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Test");
    _builder.newLine();
    _builder.append("public void test");
    String _firstUpper_5 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_5);
    _builder.append("Concept() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _firstUpper_6 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_6, "\t");
    _builder.append(" test");
    String _firstUpper_7 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_7, "\t");
    _builder.append(" = new ");
    String _firstUpper_8 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_8, "\t");
    _builder.append("(concept);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("assertNotNull(\"There is an internal DVLM object\", test");
    String _firstUpper_9 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_9, "\t");
    _builder.append(".getStructuralElementInstance());");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Test");
    _builder.newLine();
    _builder.append("public void test");
    String _firstUpper_10 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_10);
    _builder.append("StructuralElementInstance() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("StructuralElementInstance testSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();");
    _builder.newLine();
    _builder.append("\t");
    String _firstUpper_11 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_11, "\t");
    _builder.append(" test");
    String _firstUpper_12 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_12, "\t");
    _builder.append(" = new ");
    String _firstUpper_13 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_13, "\t");
    _builder.append("(testSei);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("assertEquals(\"DVLM object has been set as specified\", testSei, test");
    String _firstUpper_14 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_14, "\t");
    _builder.append(".getStructuralElementInstance());");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
}
