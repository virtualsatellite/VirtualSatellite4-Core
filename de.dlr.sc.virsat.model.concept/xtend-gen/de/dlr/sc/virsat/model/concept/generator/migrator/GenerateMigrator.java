/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.migrator;

import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator;
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider;
import de.dlr.sc.virsat.model.concept.generator.ImportManager;
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;

@SuppressWarnings("all")
public class GenerateMigrator extends AGeneratorGapGenerator<EObject> {
  @Override
  protected String getPackage(final Concept concept) {
    String _name = concept.getName();
    String _plus = (_name + ".");
    return (_plus + GenerateMigrator.PACKAGE_FOLDER);
  }
  
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
    String _packageFolder = this.getPackageFolder(concept);
    String _plus = (_packageFolder + "/");
    String _concreteClassName = GenerateMigrator.getConcreteClassName(concept);
    String _plus_1 = (_plus + _concreteClassName);
    return (_plus_1 + ".java");
  }
  
  @Override
  public String createAbstractClassFileName(final Concept concept, final EObject eObject) {
    String _packageFolder = this.getPackageFolder(concept);
    String _plus = (_packageFolder + "/");
    String _abstractClassName = GenerateMigrator.getAbstractClassName(concept);
    String _plus_1 = (_plus + _abstractClassName);
    return (_plus_1 + ".java");
  }
  
  public static final String PACKAGE_FOLDER = "migrator";
  
  @Override
  public void serializeModel(final Concept concept, final IFileSystemAccess fsa) {
    CharSequence fileOutputAClass = this.createAbstractClass(concept, concept);
    fsa.generateFile(this.createAbstractClassFileName(concept, concept), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);
    CharSequence fileOutputClass = this.createConcreteClass(concept, concept);
    fsa.generateFile(this.createConcreteClassFileName(concept, concept), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
  }
  
  /**
   * This method just creates the package declaration. The concept name needs to fit to the package name
   */
  @Override
  protected CharSequence declarePackage(final String packageId) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(packageId);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  /**
   * Handle the import statements
   */
  @Override
  protected CharSequence declareImports(final ImportManager importManager, final Concept concept, final EObject conceptPart, final String conceptPackage) {
    StringConcatenation _builder = new StringConcatenation();
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
  protected CharSequence declareAClass(final Concept concept, final EObject conceptPart, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register("de.dlr.sc.virsat.model.concept.migrator.AMigrator");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Class Declaration");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.newLine();
    CharSequence _generateAClassHeader = ConceptGeneratorUtil.generateAClassHeader(concept);
    _builder.append(_generateAClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public abstract class ");
    String _abstractClassName = GenerateMigrator.getAbstractClassName(concept);
    _builder.append(_abstractClassName);
    _builder.append(" extends AMigrator {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Entry method to write the class body
   */
  @Override
  protected CharSequence declareClass(final Concept concept, final EObject conceptPart, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register(Concept.class);
    _builder.newLineIfNotEmpty();
    importManager.register("de.dlr.sc.virsat.model.concept.migrator.IMigrator");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Class Declaration");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.newLine();
    CharSequence _generateClassHeader = ConceptGeneratorUtil.generateClassHeader(concept);
    _builder.append(_generateClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public class ");
    String _concreteClassName = GenerateMigrator.getConcreteClassName(concept);
    _builder.append(_concreteClassName);
    _builder.append(" extends ");
    String _abstractClassName = GenerateMigrator.getAbstractClassName(concept);
    _builder.append(_abstractClassName);
    _builder.append(" implements IMigrator {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void migrate(Concept concept, IMigrator previousMigrator) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("//TODO: Implement custom concept migration for version ");
    String _version = concept.getVersion();
    _builder.append(_version, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super.migrate(concept, previousMigrator);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
