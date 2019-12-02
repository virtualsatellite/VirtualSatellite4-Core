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
import de.dlr.sc.virsat.model.concept.generator.migrator.GenerateMigrator;
import de.dlr.sc.virsat.model.concept.generator.tests.AllMigratorsReader;
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;

/**
 * This class is the generator for the migrators test of our model extension.
 */
@SuppressWarnings("all")
public class GenerateMigratorTests extends AGeneratorGapGenerator<EObject> {
  public static String getConcreteClassName(final Concept concept) {
    String _replace = concept.getVersion().replace(".", "v");
    return ("Migrator" + _replace);
  }
  
  public static String getAbstractClassName(final Concept concept) {
    String _replace = concept.getVersion().replace(".", "v");
    return ("AMigrator" + _replace);
  }
  
  @Override
  public String createConcreteClassFileName(final Concept concept, final EObject eObject) {
    String _name = concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".test/src/");
    String _packageFolder = this.getPackageFolder(concept);
    String _plus_2 = (_plus_1 + _packageFolder);
    String _plus_3 = (_plus_2 + "/");
    String _concreteClassName = GenerateMigratorTests.getConcreteClassName(concept);
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
    String _abstractClassName = GenerateMigratorTests.getAbstractClassName(concept);
    String _plus_4 = (_plus_3 + _abstractClassName);
    return (_plus_4 + "Test.java");
  }
  
  @Override
  protected String getPackage(final Concept concept) {
    String _name = concept.getName();
    String _plus = (_name + ".");
    return (_plus + GenerateMigratorTests.PACKAGE_FOLDER);
  }
  
  public static final String PACKAGE_FOLDER = GenerateMigrator.PACKAGE_FOLDER;
  
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
  public CharSequence declareAClass(final Concept concept, final EObject eObject, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register(Concept.class);
    _builder.newLineIfNotEmpty();
    importManager.register(EcoreUtil.class);
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
    String _abstractClassName = GenerateMigratorTests.getAbstractClassName(concept);
    _builder.append(_abstractClassName);
    _builder.append("Test {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("protected Concept conceptMigrateTo;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected Concept conceptMigrateFromRepository;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected Concept conceptMigrateFrom;");
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
    final String currentVersion = concept.getVersion();
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("String conceptXmiPluginPathMigrateTo = \"");
    String _fullQualifiedName = concept.getFullQualifiedName();
    _builder.append(_fullQualifiedName, "\t\t");
    _builder.append("/concept/concept_v");
    String _replaceAll = currentVersion.replaceAll("\\.", "_");
    _builder.append(_replaceAll, "\t\t");
    _builder.append(".xmi\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    final String previousVersion = new AllMigratorsReader().init(concept.getFullQualifiedName()).getPreviousVersion(currentVersion);
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("String conceptXmiPluginPathMigrateFrom = \"");
    String _fullQualifiedName_1 = concept.getFullQualifiedName();
    _builder.append(_fullQualifiedName_1, "\t\t");
    _builder.append("/concept/concept_v");
    String _replaceAll_1 = previousVersion.replaceAll("\\.", "_");
    _builder.append(_replaceAll_1, "\t\t");
    _builder.append(".xmi\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("conceptMigrateTo = de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPathMigrateTo);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("conceptMigrateFromRepository = de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPathMigrateFrom);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("conceptMigrateFrom = EcoreUtil.copy(conceptMigrateFromRepository);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  @Override
  public CharSequence declareClass(final Concept concept, final EObject eObject, final ImportManager importManager) {
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
    _builder.newLine();
    CharSequence _generateClassHeader = ConceptGeneratorUtil.generateClassHeader(concept);
    _builder.append(_generateClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public class ");
    String _concreteClassName = GenerateMigratorTests.getConcreteClassName(concept);
    _builder.append(_concreteClassName);
    _builder.append("Test extends ");
    String _abstractClassName = GenerateMigratorTests.getAbstractClassName(concept);
    _builder.append(_abstractClassName);
    _builder.append("Test {\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _declareTestStub = this.declareTestStub(concept, importManager);
    _builder.append(_declareTestStub, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Stub for a testing method
   */
  protected CharSequence declareTestStub(final Concept concept, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register(Repository.class);
    _builder.newLineIfNotEmpty();
    importManager.register(DVLMFactory.class);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("@Test");
    _builder.newLine();
    _builder.append("public void test");
    String _concreteClassName = GenerateMigratorTests.getConcreteClassName(concept);
    _builder.append(_concreteClassName);
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _concreteClassName_1 = GenerateMigratorTests.getConcreteClassName(concept);
    _builder.append(_concreteClassName_1, "\t");
    _builder.append(" test");
    String _concreteClassName_2 = GenerateMigratorTests.getConcreteClassName(concept);
    _builder.append(_concreteClassName_2, "\t");
    _builder.append(" = new ");
    String _concreteClassName_3 = GenerateMigratorTests.getConcreteClassName(concept);
    _builder.append(_concreteClassName_3, "\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Repository repository = DVLMFactory.eINSTANCE.createRepository();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("repository.getActiveConcepts().add(conceptMigrateFromRepository);");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("//TODO: Setup test instances");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("test");
    String _concreteClassName_4 = GenerateMigratorTests.getConcreteClassName(concept);
    _builder.append(_concreteClassName_4, "\t");
    _builder.append(".migrate(conceptMigrateFrom, conceptMigrateFromRepository, conceptMigrateTo);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("//TODO: Check for correct migration");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
}
