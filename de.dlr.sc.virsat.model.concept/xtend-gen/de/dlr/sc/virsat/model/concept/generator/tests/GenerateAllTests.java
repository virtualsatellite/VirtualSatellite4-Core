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
import de.dlr.sc.virsat.model.concept.generator.tests.AllMigratorsReader;
import de.dlr.sc.virsat.model.concept.generator.validator.GenerateValidator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
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
public class GenerateAllTests extends AGeneratorGapGenerator<EObject> {
  public static String getConcreteClassName(final StructuralElement typeDefinition) {
    return StringExtensions.toFirstUpper(typeDefinition.getName());
  }
  
  public static String getAbstractClassName(final StructuralElement typeDefinition) {
    String _firstUpper = StringExtensions.toFirstUpper(typeDefinition.getName());
    return ("A" + _firstUpper);
  }
  
  @Override
  public String createConcreteClassFileName(final Concept concept, final EObject conceptPart) {
    String _name = concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".test/src/");
    String _packageFolder = this.getPackageFolder(concept);
    String _plus_2 = (_plus_1 + _packageFolder);
    String _plus_3 = (_plus_2 + "/");
    return (_plus_3 + "AllTests.java");
  }
  
  @Override
  public String createAbstractClassFileName(final Concept concept, final EObject conceptPart) {
    String _name = concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".test/src-gen/");
    String _packageFolder = this.getPackageFolder(concept);
    String _plus_2 = (_plus_1 + _packageFolder);
    String _plus_3 = (_plus_2 + "/");
    return (_plus_3 + "AllTestsGen.java");
  }
  
  @Override
  protected String getPackage(final Concept concept) {
    String _name = concept.getName();
    String _plus = (_name + ".");
    return (_plus + GenerateAllTests.PACKAGE_FOLDER);
  }
  
  public static final String PACKAGE_FOLDER = "test";
  
  @Override
  public void serializeModel(final Concept concept, final IFileSystemAccess fsa) {
    CharSequence fileOutputAClass = this.createAbstractClass(concept, null);
    fsa.generateFile(this.createAbstractClassFileName(concept, null), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);
    CharSequence fileOutputClass = this.createConcreteClass(concept, null);
    fsa.generateFile(this.createConcreteClassFileName(concept, null), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
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
  public CharSequence declareImports(final ImportManager importManager, final Concept concept, final EObject conceptPart, final String conceptPackage) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Import Statements");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.junit.runner.RunWith;");
    _builder.newLine();
    _builder.append("import org.junit.runners.Suite;");
    _builder.newLine();
    _builder.append("import org.junit.runners.Suite.SuiteClasses;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import junit.framework.JUnit4TestAdapter;");
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
  public CharSequence declareAClass(final Concept concept, final EObject se, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("@RunWith(Suite.class)");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@SuiteClasses({");
    _builder.newLine();
    _builder.append("\t");
    String _listAllGeneratedTests = this.listAllGeneratedTests(concept, importManager);
    _builder.append(_listAllGeneratedTests, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.append("})");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Test Collection");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class AllTestsGen {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Constructor");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private AllTestsGen() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Test Adapter");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @return Executable JUnit Tests");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static junit.framework.Test suite() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return new JUnit4TestAdapter(AllTests.class);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  @Override
  public CharSequence declareClass(final Concept concept, final EObject se, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("@RunWith(Suite.class)");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@SuiteClasses({\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("})");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Test Collection");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class AllTests {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Constructor");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private AllTests() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Test Adapter");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @return Executable JUnit Tests");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static junit.framework.Test suite() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return new JUnit4TestAdapter(AllTests.class);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public String listAllGeneratedTests(final Concept concept, final ImportManager importManager) {
    TreeIterator<Object> iterator = EcoreUtil.<Object>getAllContents(concept, true);
    final ArrayList<String> listOfTests = new ArrayList<String>();
    final Procedure1<Object> _function = (Object it) -> {
      if ((it instanceof Category)) {
        Category category = ((Category) it);
        boolean _isIsAbstract = category.isIsAbstract();
        boolean _not = (!_isIsAbstract);
        if (_not) {
          listOfTests.add(importManager.serializeTest(category));
        }
      } else {
        if ((it instanceof StructuralElement)) {
          StructuralElement se = ((StructuralElement) it);
          listOfTests.add(importManager.serializeTest(se));
        }
      }
    };
    IteratorExtensions.<Object>forEach(iterator, _function);
    final AllMigratorsReader allMigratorsReader = new AllMigratorsReader().init(concept.getFullQualifiedName());
    final Consumer<String> _function_1 = (String it) -> {
      String _fullQualifiedName = concept.getFullQualifiedName();
      String _plus = (_fullQualifiedName + ".migrator.");
      String _plus_1 = (_plus + it);
      String _plus_2 = (_plus_1 + "Test");
      importManager.register(_plus_2);
      listOfTests.add((it + "Test"));
    };
    allMigratorsReader.migrators.forEach(_function_1);
    String _fullQualifiedName = concept.getFullQualifiedName();
    String _plus = (_fullQualifiedName + ".validator.");
    String _validatorName = GenerateValidator.getValidatorName(concept);
    String _plus_1 = (_plus + _validatorName);
    String _plus_2 = (_plus_1 + "Test");
    importManager.register(_plus_2);
    String _validatorName_1 = GenerateValidator.getValidatorName(concept);
    String _plus_3 = (_validatorName_1 + "Test");
    listOfTests.add(_plus_3);
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final String test : listOfTests) {
        _builder.append(test);
        _builder.append(".class,");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }
}
