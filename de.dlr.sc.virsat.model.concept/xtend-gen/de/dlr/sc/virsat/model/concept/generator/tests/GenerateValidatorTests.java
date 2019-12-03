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
import de.dlr.sc.virsat.model.concept.generator.validator.GenerateValidator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;

@SuppressWarnings("all")
public class GenerateValidatorTests extends AGeneratorGapGenerator<EObject> {
  public static String getConcreteClassName(final Concept concept) {
    return GenerateValidator.getValidatorName(concept);
  }
  
  public static String getAbstractClassName(final Concept concept) {
    String _validatorName = GenerateValidator.getValidatorName(concept);
    return ("A" + _validatorName);
  }
  
  @Override
  public String createConcreteClassFileName(final Concept concept, final EObject eObject) {
    String _name = concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".test/src/");
    String _packageFolder = this.getPackageFolder(concept);
    String _plus_2 = (_plus_1 + _packageFolder);
    String _plus_3 = (_plus_2 + "/");
    String _concreteClassName = GenerateValidatorTests.getConcreteClassName(concept);
    String _plus_4 = (_plus_3 + _concreteClassName);
    return (_plus_4 + "Test.java");
  }
  
  @Override
  public String createAbstractClassFileName(final Concept concept, final EObject eObject) {
    String _name = concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".test/src-gen/");
    String _packageFolder = this.getPackageFolder(concept);
    String _plus_2 = (_plus_1 + _packageFolder);
    String _plus_3 = (_plus_2 + "/");
    String _abstractClassName = GenerateValidatorTests.getAbstractClassName(concept);
    String _plus_4 = (_plus_3 + _abstractClassName);
    return (_plus_4 + "Test.java");
  }
  
  @Override
  protected String getPackage(final Concept concept) {
    String _name = concept.getName();
    String _plus = (_name + ".");
    return (_plus + GenerateValidatorTests.PACKAGE_FOLDER);
  }
  
  public static final String PACKAGE_FOLDER = GenerateValidator.PACKAGE_FOLDER;
  
  @Override
  public void serializeModel(final Concept concept, final IFileSystemAccess fsa) {
    CharSequence fileOutputAClass = this.createAbstractClass(concept, concept);
    fsa.generateFile(this.createAbstractClassFileName(concept, concept), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);
    CharSequence fileOutputClass = this.createConcreteClass(concept, concept);
    fsa.generateFile(this.createConcreteClassFileName(concept, concept), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
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
  public CharSequence declareImports(final ImportManager importManager, final Concept concept, final EObject eObject, final String conceptPackage) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Import Statements");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.newLine();
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
  public CharSequence declareAClass(final Concept concept, final EObject conceptPart, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register(StructuralElementInstance.class);
    _builder.newLineIfNotEmpty();
    importManager.register(StructuralFactory.class);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Class Declaration");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.junit.Before;");
    _builder.newLine();
    _builder.newLine();
    CharSequence _generateAClassHeader = ConceptGeneratorUtil.generateAClassHeader(concept);
    _builder.append(_generateAClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public abstract class ");
    String _abstractClassName = GenerateValidatorTests.getAbstractClassName(concept);
    _builder.append(_abstractClassName);
    _builder.append("Test {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("StructuralElementInstance testSei;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Before");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void setup() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("testSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  @Override
  protected CharSequence declareClass(final Concept concept, final EObject type, final ImportManager manager) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Class Declaration");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.junit.Test;");
    _builder.newLine();
    _builder.append("import static org.junit.Assert.assertTrue;");
    _builder.newLine();
    _builder.newLine();
    CharSequence _generateClassHeader = ConceptGeneratorUtil.generateClassHeader(concept);
    _builder.append(_generateClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public class ");
    String _concreteClassName = GenerateValidatorTests.getConcreteClassName(concept);
    _builder.append(_concreteClassName);
    _builder.append("Test extends ");
    String _abstractClassName = GenerateValidatorTests.getAbstractClassName(concept);
    _builder.append(_abstractClassName);
    _builder.append("Test {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void test");
    String _concreteClassName_1 = GenerateValidatorTests.getConcreteClassName(concept);
    _builder.append(_concreteClassName_1, "\t");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    String _concreteClassName_2 = GenerateValidatorTests.getConcreteClassName(concept);
    _builder.append(_concreteClassName_2, "\t\t");
    _builder.append(" validator = new ");
    String _concreteClassName_3 = GenerateValidatorTests.getConcreteClassName(concept);
    _builder.append(_concreteClassName_3, "\t\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("assertTrue(validator.validate(testSei));");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
