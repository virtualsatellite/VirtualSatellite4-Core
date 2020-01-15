/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.commands;

import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator;
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider;
import de.dlr.sc.virsat.model.concept.generator.ImportManager;
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class GenerateArrayCreateAddCommand extends AGeneratorGapGenerator<AProperty> {
  @Override
  protected String getPackage(final Concept concept) {
    String _name = concept.getName();
    return (_name + ".ui.command");
  }
  
  public static String getConcreteClassName(final ATypeDefinition typeDefinition) {
    String _firstUpper = StringExtensions.toFirstUpper(typeDefinition.getName());
    String _plus = ("CreateAddArrayElement" + _firstUpper);
    return (_plus + "Command");
  }
  
  public static String getAbstractClassName(final ATypeDefinition typeDefinition) {
    String _firstUpper = StringExtensions.toFirstUpper(typeDefinition.getName());
    String _plus = ("ACreateAddArrayElement" + _firstUpper);
    return (_plus + "Command");
  }
  
  @Override
  public String createConcreteClassFileName(final Concept concept, final AProperty category) {
    String _name = concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".ui/src/");
    String _packageFolder = this.getPackageFolder(concept);
    String _plus_2 = (_plus_1 + _packageFolder);
    String _plus_3 = (_plus_2 + "/");
    String _concreteClassName = GenerateArrayCreateAddCommand.getConcreteClassName(category);
    String _plus_4 = (_plus_3 + _concreteClassName);
    return (_plus_4 + ".java");
  }
  
  @Override
  public String createAbstractClassFileName(final Concept concept, final AProperty category) {
    String _name = concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".ui/src-gen/");
    String _packageFolder = this.getPackageFolder(concept);
    String _plus_2 = (_plus_1 + _packageFolder);
    String _plus_3 = (_plus_2 + "/");
    String _abstractClassName = GenerateArrayCreateAddCommand.getAbstractClassName(category);
    String _plus_4 = (_plus_3 + _abstractClassName);
    return (_plus_4 + ".java");
  }
  
  @Override
  public void serializeModel(final Concept concept, final IFileSystemAccess fsa) {
    final Consumer<Category> _function = (Category it) -> {
      final Consumer<AProperty> _function_1 = (AProperty it_1) -> {
        IArrayModifier _arrayModifier = it_1.getArrayModifier();
        boolean _tripleNotEquals = (_arrayModifier != null);
        if (_tripleNotEquals) {
          CharSequence fileOutputAClass = this.createAbstractClass(concept, it_1);
          fsa.generateFile(this.createAbstractClassFileName(concept, it_1), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);
          CharSequence fileOutputClass = this.createConcreteClass(concept, it_1);
          fsa.generateFile(this.createConcreteClassFileName(concept, it_1), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
        }
      };
      it.getAllProperties().forEach(_function_1);
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
  public CharSequence declareImports(final ImportManager importManager, final Concept concept, final AProperty conceptPart, final String conceptPackage) {
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
  protected CharSequence declareClass(final Concept concept, final AProperty property, final ImportManager manager) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateClassHeader = ConceptGeneratorUtil.generateClassHeader(property);
    _builder.append(_generateClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public class ");
    String _concreteClassName = GenerateArrayCreateAddCommand.getConcreteClassName(property);
    _builder.append(_concreteClassName);
    _builder.append(" extends ");
    String _abstractClassName = GenerateArrayCreateAddCommand.getAbstractClassName(property);
    _builder.append(_abstractClassName);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  @Override
  protected CharSequence declareAClass(final Concept concept, final AProperty property, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register(Command.class);
    _builder.newLineIfNotEmpty();
    importManager.register(EditingDomain.class);
    _builder.newLineIfNotEmpty();
    importManager.register(Category.class);
    _builder.newLineIfNotEmpty();
    importManager.register(ArrayInstance.class);
    _builder.newLineIfNotEmpty();
    importManager.register(ATypeInstance.class);
    _builder.newLineIfNotEmpty();
    importManager.register(AddCommand.class);
    _builder.newLineIfNotEmpty();
    importManager.register(PropertyinstancesPackage.class);
    _builder.newLineIfNotEmpty();
    importManager.register(CategoryInstantiator.class);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _generateAClassHeader = ConceptGeneratorUtil.generateAClassHeader(property);
    _builder.append(_generateAClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public abstract class ");
    String _abstractClassName = GenerateArrayCreateAddCommand.getAbstractClassName(property);
    _builder.append(_abstractClassName);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Command create(EditingDomain editingDomain, ArrayInstance arrayInstance, Category type) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("ATypeInstance ati = new CategoryInstantiator().generateInstance(arrayInstance, type);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return AddCommand.create(editingDomain, arrayInstance, PropertyinstancesPackage.Literals.ARRAY_INSTANCE__ARRAY_INSTANCES, ati);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
