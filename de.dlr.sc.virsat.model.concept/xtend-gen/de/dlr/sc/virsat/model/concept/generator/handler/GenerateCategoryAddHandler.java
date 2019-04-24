/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.handler;

import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator;
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider;
import de.dlr.sc.virsat.model.concept.generator.ImportManager;
import de.dlr.sc.virsat.model.concept.generator.commands.GenerateCategoryCreateAddCommand;
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class GenerateCategoryAddHandler extends AGeneratorGapGenerator<Category> {
  @Override
  protected String getPackage(final Concept concept) {
    String _name = concept.getName();
    return (_name + ".ui.handler");
  }
  
  public static String getConcreteClassName(final Category category) {
    String _firstUpper = StringExtensions.toFirstUpper(category.getName());
    String _plus = ("Add" + _firstUpper);
    return (_plus + "Handler");
  }
  
  public static String getAbstractClassName(final Category category) {
    String _firstUpper = StringExtensions.toFirstUpper(category.getName());
    String _plus = ("AAdd" + _firstUpper);
    return (_plus + "Handler");
  }
  
  @Override
  public String createConcreteClassFileName(final Concept concept, final Category category) {
    String _name = concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".ui/src/");
    String _packageFolder = this.getPackageFolder(concept);
    String _plus_2 = (_plus_1 + _packageFolder);
    String _plus_3 = (_plus_2 + "/");
    String _concreteClassName = GenerateCategoryAddHandler.getConcreteClassName(category);
    String _plus_4 = (_plus_3 + _concreteClassName);
    return (_plus_4 + ".java");
  }
  
  @Override
  public String createAbstractClassFileName(final Concept concept, final Category category) {
    String _name = concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".ui/src-gen/");
    String _packageFolder = this.getPackageFolder(concept);
    String _plus_2 = (_plus_1 + _packageFolder);
    String _plus_3 = (_plus_2 + "/");
    String _abstractClassName = GenerateCategoryAddHandler.getAbstractClassName(category);
    String _plus_4 = (_plus_3 + _abstractClassName);
    return (_plus_4 + ".java");
  }
  
  @Override
  public void serializeModel(final Concept concept, final IFileSystemAccess fsa) {
    final Consumer<Category> _function = (Category it) -> {
      CharSequence fileOutputAClass = this.createAbstractClass(concept, it);
      fsa.generateFile(this.createAbstractClassFileName(concept, it), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);
      CharSequence fileOutputClass = this.createConcreteClass(concept, it);
      fsa.generateFile(this.createConcreteClassFileName(concept, it), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
    };
    concept.getNonAbstractCategories().forEach(_function);
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
  
  @Override
  public CharSequence declareImports(final ImportManager importManager, final Concept concept, final Category conceptPart, final String conceptPackage) {
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
  
  @Override
  protected CharSequence declareClass(final Concept concept, final Category category, final ImportManager manager) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateClassHeader = ConceptGeneratorUtil.generateClassHeader(category);
    _builder.append(_generateClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public class ");
    String _concreteClassName = GenerateCategoryAddHandler.getConcreteClassName(category);
    _builder.append(_concreteClassName);
    _builder.append(" extends ");
    String _abstractClassName = GenerateCategoryAddHandler.getAbstractClassName(category);
    _builder.append(_abstractClassName);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  @Override
  public CharSequence declareAClass(final Concept concept, final Category category, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import de.dlr.sc.virsat.project.ui.transactional.handler.ATransactionalAddCategoryHandler;");
    _builder.newLine();
    _builder.append("import ");
    String _name = concept.getName();
    _builder.append(_name);
    _builder.append(".ui.command.");
    String _concreteClassName = GenerateCategoryCreateAddCommand.getConcreteClassName(category);
    _builder.append(_concreteClassName);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _generateAClassHeader = ConceptGeneratorUtil.generateAClassHeader(category);
    _builder.append(_generateAClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public abstract class ");
    String _abstractClassName = GenerateCategoryAddHandler.getAbstractClassName(category);
    _builder.append(_abstractClassName);
    _builder.append(" extends ATransactionalAddCategoryHandler {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected String getConceptName() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return \"");
    String _name_1 = concept.getName();
    _builder.append(_name_1, "\t\t");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected ");
    String _serialize = importManager.serialize(Command.class);
    _builder.append(_serialize, "\t");
    _builder.append(" createAddCommand(");
    String _serialize_1 = importManager.serialize(EditingDomain.class);
    _builder.append(_serialize_1, "\t");
    _builder.append(" editingDomain, ");
    String _serialize_2 = importManager.serialize(EObject.class);
    _builder.append(_serialize_2, "\t");
    _builder.append(" owner, ");
    String _serialize_3 = importManager.serialize(Concept.class);
    _builder.append(_serialize_3, "\t");
    _builder.append(" activeConcept) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return new ");
    String _concreteClassName_1 = GenerateCategoryCreateAddCommand.getConcreteClassName(category);
    _builder.append(_concreteClassName_1, "\t\t");
    _builder.append("().create(editingDomain, owner, activeConcept);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
