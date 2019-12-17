/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.validator;

import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator;
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider;
import de.dlr.sc.virsat.model.concept.generator.ImportManager;
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;

@SuppressWarnings("all")
public class GenerateDeprecatedValidator extends AGeneratorGapGenerator<EObject> {
  @Override
  public String getPackage(final Concept concept) {
    String _name = concept.getName();
    String _plus = (_name + ".");
    return (_plus + GenerateDeprecatedValidator.PACKAGE_FOLDER);
  }
  
  public static String getAbstractClassName(final EObject typeDefinition) {
    return "AStructuralElementInstanceValidator";
  }
  
  @Override
  public String createAbstractClassFileName(final Concept concept, final EObject eObject) {
    String _packageFolder = this.getPackageFolder(concept);
    String _plus = (_packageFolder + "/");
    return (_plus + "AStructuralElementInstanceValidator.java");
  }
  
  public static final String PACKAGE_FOLDER = "validator";
  
  @Override
  public void serializeModel(final Concept concept, final IFileSystemAccess fsa) {
    CharSequence fileOutputAClass = this.createAbstractClass(concept, concept);
    fsa.generateFile(this.createAbstractClassFileName(concept, concept), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);
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
    importManager.register(StructuralElementInstance.class);
    _builder.newLineIfNotEmpty();
    importManager.register("de.dlr.sc.virsat.build.validator.external.IStructuralElementInstanceValidator");
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
    _builder.append("@Deprecated");
    _builder.newLine();
    _builder.append("public abstract class ");
    String _abstractClassName = GenerateDeprecatedValidator.getAbstractClassName(conceptPart);
    _builder.append(_abstractClassName);
    _builder.append(" implements IStructuralElementInstanceValidator {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public boolean validate(StructuralElementInstance sei) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return true;");
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
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Override
  public String createConcreteClassFileName(final Concept concept, final EObject conceptPart) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
}
