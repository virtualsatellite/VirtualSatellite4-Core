/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.snippets;

import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator;
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider;
import de.dlr.sc.virsat.model.concept.generator.ImportManager;
import de.dlr.sc.virsat.model.concept.generator.commands.GenerateArrayCreateAddCommand;
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.DynamicArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * This class generates the UiSnippets for the Categories and properties which are
 * defined as arrays. It takes care about static and dynamic arrays as well and sets up
 * the classes accordingly.
 */
@SuppressWarnings("all")
public class GenerateCategoryUiSnippetArrayTable extends AGeneratorGapGenerator<AProperty> {
  @Override
  protected String getPackage(final Concept concept) {
    String _name = concept.getName();
    return (_name + ".ui.snippet");
  }
  
  public static String getConcreteClassName(final AProperty property, final Category category) {
    String _firstUpper = StringExtensions.toFirstUpper(ConceptGeneratorUtil.getPropertyId(property, category));
    return ("UiSnippetTable" + _firstUpper);
  }
  
  public static String getAbstractClassName(final AProperty property, final Category category) {
    String _firstUpper = StringExtensions.toFirstUpper(ConceptGeneratorUtil.getPropertyId(property, category));
    return ("AUiSnippetTable" + _firstUpper);
  }
  
  public static String getConcreteClassName(final AProperty property, final Category category, final Category extendingCategory) {
    String _concreteClassName = GenerateCategoryUiSnippetArrayTable.getConcreteClassName(property, category);
    String _firstUpper = StringExtensions.toFirstUpper(extendingCategory.getName());
    return (_concreteClassName + _firstUpper);
  }
  
  public static String getAbstractClassName(final AProperty property, final Category category, final Category extendingCategory) {
    String _abstractClassName = GenerateCategoryUiSnippetArrayTable.getAbstractClassName(property, category);
    String _firstUpper = StringExtensions.toFirstUpper(extendingCategory.getName());
    return (_abstractClassName + _firstUpper);
  }
  
  @Override
  public String createConcreteClassFileName(final Concept concept, final AProperty property) {
    String _name = concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".ui/src/");
    String _packageFolder = this.getPackageFolder(concept);
    String _plus_2 = (_plus_1 + _packageFolder);
    return (_plus_2 + "/");
  }
  
  @Override
  public String createAbstractClassFileName(final Concept concept, final AProperty property) {
    String _name = concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".ui/src-gen/");
    String _packageFolder = this.getPackageFolder(concept);
    String _plus_2 = (_plus_1 + _packageFolder);
    return (_plus_2 + "/");
  }
  
  public String createConcreteClassFileName(final Concept concept, final AProperty property, final Category category, final Category extendingCategory) {
    String _createConcreteClassFileName = this.createConcreteClassFileName(concept, property);
    String _concreteClassName = GenerateCategoryUiSnippetArrayTable.getConcreteClassName(property, category, extendingCategory);
    String _plus = (_createConcreteClassFileName + _concreteClassName);
    return (_plus + ".java");
  }
  
  public String createAbstractClassFileName(final Concept concept, final AProperty property, final Category category, final Category extendingCategory) {
    String _createAbstractClassFileName = this.createAbstractClassFileName(concept, property);
    String _abstractClassName = GenerateCategoryUiSnippetArrayTable.getAbstractClassName(property, category, extendingCategory);
    String _plus = (_createAbstractClassFileName + _abstractClassName);
    return (_plus + ".java");
  }
  
  public String createConcreteClassFileName(final Concept concept, final AProperty property, final Category category) {
    String _createConcreteClassFileName = this.createConcreteClassFileName(concept, property);
    String _concreteClassName = GenerateCategoryUiSnippetArrayTable.getConcreteClassName(property, category);
    String _plus = (_createConcreteClassFileName + _concreteClassName);
    return (_plus + ".java");
  }
  
  public String createAbstractClassFileName(final Concept concept, final AProperty property, final Category category) {
    String _createAbstractClassFileName = this.createAbstractClassFileName(concept, property);
    String _abstractClassName = GenerateCategoryUiSnippetArrayTable.getAbstractClassName(property, category);
    String _plus = (_createAbstractClassFileName + _abstractClassName);
    return (_plus + ".java");
  }
  
  @Override
  public void serializeModel(final Concept concept, final IFileSystemAccess fsa) {
    final Consumer<Category> _function = (Category it) -> {
      final Category category = it;
      final Consumer<AProperty> _function_1 = (AProperty it_1) -> {
        final AProperty property = it_1;
        IArrayModifier _arrayModifier = property.getArrayModifier();
        boolean _tripleNotEquals = (_arrayModifier != null);
        if (_tripleNotEquals) {
          if (((property.getArrayModifier() instanceof DynamicArrayModifier) && (property instanceof ComposedProperty))) {
            final Category baseCategory = ((ComposedProperty) property).getType();
            final Consumer<Category> _function_2 = (Category it_2) -> {
              final Category extendingCategory = it_2;
              boolean _isExtensionOf = extendingCategory.isExtensionOf(baseCategory);
              if (_isExtensionOf) {
                String fileOutputAClass = this.createAbstractClass(concept, property, category, extendingCategory);
                fsa.generateFile(this.createAbstractClassFileName(concept, property, category, extendingCategory), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);
                String fileOutputClass = this.createConcreteClass(concept, property, category, extendingCategory);
                fsa.generateFile(this.createConcreteClassFileName(concept, property, category, extendingCategory), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
              }
            };
            concept.getNonAbstractCategories().forEach(_function_2);
          } else {
            String fileOutputAClass = this.createAbstractClass(concept, property, category, null);
            fsa.generateFile(this.createAbstractClassFileName(concept, property, category), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);
            String fileOutputClass = this.createConcreteClass(concept, property, category);
            fsa.generateFile(this.createConcreteClassFileName(concept, property, category), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
          }
        }
      };
      it.getAllProperties().forEach(_function_1);
    };
    concept.getNonAbstractCategories().forEach(_function);
  }
  
  public String createConcreteClass(final Concept concept, final AProperty conceptPart, final Category category) {
    String _package = this.getPackage(concept);
    final ImportManager imCClass = new ImportManager(_package);
    final CharSequence bodyClass = this.declareClass(concept, conceptPart, category, imCClass);
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateFileHeader = ConceptGeneratorUtil.generateFileHeader();
    _builder.append(_generateFileHeader);
    _builder.newLineIfNotEmpty();
    CharSequence _declarePackage = this.declarePackage(this.getPackage(concept));
    _builder.append(_declarePackage);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _declareImports = this.declareImports(imCClass, concept, conceptPart, this.getPackage(concept));
    _builder.append(_declareImports);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append(bodyClass);
    _builder.newLineIfNotEmpty();
    String fileOutputClass = _builder.toString();
    return fileOutputClass;
  }
  
  public String createConcreteClass(final Concept concept, final AProperty conceptPart, final Category category, final Category extendingCategory) {
    String _package = this.getPackage(concept);
    final ImportManager imCClass = new ImportManager(_package);
    final CharSequence bodyClass = this.declareClass(concept, conceptPart, category, extendingCategory, imCClass);
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateFileHeader = ConceptGeneratorUtil.generateFileHeader();
    _builder.append(_generateFileHeader);
    _builder.newLineIfNotEmpty();
    CharSequence _declarePackage = this.declarePackage(this.getPackage(concept));
    _builder.append(_declarePackage);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _declareImports = this.declareImports(imCClass, concept, conceptPart, this.getPackage(concept));
    _builder.append(_declareImports);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append(bodyClass);
    _builder.newLineIfNotEmpty();
    String fileOutputClass = _builder.toString();
    return fileOutputClass;
  }
  
  public String createAbstractClass(final Concept concept, final AProperty conceptPart, final Category category, final Category extendingCategory) {
    String _package = this.getPackage(concept);
    final ImportManager imAClass = new ImportManager(_package);
    final CharSequence bodyAClass = this.declareAClass(concept, conceptPart, category, extendingCategory, imAClass);
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateFileHeader = ConceptGeneratorUtil.generateFileHeader();
    _builder.append(_generateFileHeader);
    _builder.newLineIfNotEmpty();
    CharSequence _declarePackage = this.declarePackage(this.getPackage(concept));
    _builder.append(_declarePackage);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _declareImports = this.declareImports(imAClass, concept, conceptPart, this.getPackage(concept));
    _builder.append(_declareImports);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append(bodyAClass);
    _builder.newLineIfNotEmpty();
    String fileOutputAClass = _builder.toString();
    return fileOutputAClass;
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
  protected CharSequence declareImports(final ImportManager importManager, final Concept concept, final AProperty conceptPart, final String conceptPackage) {
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
  protected CharSequence declareClass(final Concept concept, final AProperty type, final ImportManager importManager) {
    CharSequence _xblockexpression = null;
    {
      EObject _eContainer = type.eContainer();
      final Category category = ((Category) _eContainer);
      _xblockexpression = this.declareClass(concept, type, category, category, importManager);
    }
    return _xblockexpression;
  }
  
  protected CharSequence declareClass(final Concept concept, final AProperty type, final Category category, final Category extendingCategory, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _generateClassHeader = ConceptGeneratorUtil.generateClassHeader(type);
    _builder.append(_generateClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public class ");
    String _concreteClassName = GenerateCategoryUiSnippetArrayTable.getConcreteClassName(type, category, extendingCategory);
    _builder.append(_concreteClassName);
    _builder.append(" extends ");
    String _abstractClassName = GenerateCategoryUiSnippetArrayTable.getAbstractClassName(type, category, extendingCategory);
    _builder.append(_abstractClassName);
    _builder.append(" implements IUiSnippet {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence declareClass(final Concept concept, final AProperty type, final Category category, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _generateClassHeader = ConceptGeneratorUtil.generateClassHeader(type);
    _builder.append(_generateClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public class ");
    String _concreteClassName = GenerateCategoryUiSnippetArrayTable.getConcreteClassName(type, category);
    _builder.append(_concreteClassName);
    _builder.append(" extends ");
    String _abstractClassName = GenerateCategoryUiSnippetArrayTable.getAbstractClassName(type, category);
    _builder.append(_abstractClassName);
    _builder.append(" implements IUiSnippet {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  @Override
  protected CharSequence declareAClass(final Concept concept, final AProperty arrayProperty, final ImportManager importManager) {
    CharSequence _xblockexpression = null;
    {
      EObject _eContainer = arrayProperty.eContainer();
      final Category category = ((Category) _eContainer);
      _xblockexpression = this.declareAClass(concept, arrayProperty, category, category, importManager);
    }
    return _xblockexpression;
  }
  
  protected CharSequence declareAClass(final Concept concept, final AProperty arrayProperty, final Category category, final Category extendingCategory, final ImportManager importManager) {
    CharSequence _xifexpression = null;
    if ((arrayProperty instanceof ComposedProperty)) {
      CharSequence _xifexpression_1 = null;
      IArrayModifier _arrayModifier = ((ComposedProperty)arrayProperty).getArrayModifier();
      if ((_arrayModifier instanceof StaticArrayModifier)) {
        _xifexpression_1 = this.declareStaticArrayCategoryTableClass(concept, category, arrayProperty, importManager);
      } else {
        CharSequence _xifexpression_2 = null;
        IArrayModifier _arrayModifier_1 = ((ComposedProperty)arrayProperty).getArrayModifier();
        if ((_arrayModifier_1 instanceof DynamicArrayModifier)) {
          _xifexpression_2 = this.declareDynamicArrayCategoryTableClass(concept, category, extendingCategory, arrayProperty, importManager);
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    } else {
      CharSequence _xifexpression_3 = null;
      IArrayModifier _arrayModifier_2 = arrayProperty.getArrayModifier();
      if ((_arrayModifier_2 instanceof StaticArrayModifier)) {
        _xifexpression_3 = this.declareStaticArrayPropertyTableClass(concept, category, arrayProperty, importManager);
      } else {
        CharSequence _xifexpression_4 = null;
        IArrayModifier _arrayModifier_3 = arrayProperty.getArrayModifier();
        if ((_arrayModifier_3 instanceof DynamicArrayModifier)) {
          _xifexpression_4 = this.declareDynamicArrayPropertyTableClass(concept, category, arrayProperty, importManager);
        }
        _xifexpression_3 = _xifexpression_4;
      }
      _xifexpression = _xifexpression_3;
    }
    return _xifexpression;
  }
  
  /**
   * This method handles the UiSnippet for a Table of Dynamic Array of Properties
   */
  public CharSequence declareDynamicArrayPropertyTableClass(final Concept concept, final Category category, final AProperty arrayProperty, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetArrayInstancePropertyTable");
    _builder.newLineIfNotEmpty();
    importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet");
    _builder.newLineIfNotEmpty();
    String _name = concept.getName();
    String _plus = (_name + ".ui.command.");
    String _concreteClassName = GenerateArrayCreateAddCommand.getConcreteClassName(arrayProperty);
    String _plus_1 = (_plus + _concreteClassName);
    importManager.register(_plus_1);
    _builder.newLineIfNotEmpty();
    importManager.register(Command.class);
    _builder.newLineIfNotEmpty();
    importManager.register(EditingDomain.class);
    _builder.newLineIfNotEmpty();
    importManager.register(Concept.class);
    _builder.append("\t");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _generateAClassHeader = ConceptGeneratorUtil.generateAClassHeader(category);
    _builder.append(_generateAClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public abstract class ");
    String _abstractClassName = GenerateCategoryUiSnippetArrayTable.getAbstractClassName(arrayProperty, category);
    _builder.append(_abstractClassName);
    _builder.append(" extends AUiSnippetArrayInstancePropertyTable implements IUiSnippet {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _abstractClassName_1 = GenerateCategoryUiSnippetArrayTable.getAbstractClassName(arrayProperty, category);
    _builder.append(_abstractClassName_1, "\t");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super(\"");
    String _name_1 = concept.getName();
    _builder.append(_name_1, "\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("\"");
    String _name_2 = arrayProperty.getName();
    _builder.append(_name_2, "\t\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("\"");
    String _name_3 = category.getName();
    _builder.append(_name_3, "\t\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("STYLE_ADD_BUTTON | STYLE_REMOVE_BUTTON | STYLE_EDITOR_BUTTON);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected Command createAddCommand(EditingDomain editingDomain, Concept activeConcept) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return new ");
    String _concreteClassName_1 = GenerateArrayCreateAddCommand.getConcreteClassName(arrayProperty);
    _builder.append(_concreteClassName_1, "\t\t");
    _builder.append("().create(editingDomain, getArrayInstance(model), null);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * This method handles the UiSnippet for a Table of Static Array of Properties
   */
  public CharSequence declareStaticArrayPropertyTableClass(final Concept concept, final Category category, final AProperty arrayProperty, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetArrayInstancePropertyTable");
    _builder.newLineIfNotEmpty();
    importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet");
    _builder.newLineIfNotEmpty();
    importManager.register(Command.class);
    _builder.newLineIfNotEmpty();
    importManager.register(UnexecutableCommand.class);
    _builder.newLineIfNotEmpty();
    importManager.register(EditingDomain.class);
    _builder.newLineIfNotEmpty();
    importManager.register(Concept.class);
    _builder.append("\t");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _generateAClassHeader = ConceptGeneratorUtil.generateAClassHeader(category);
    _builder.append(_generateAClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public abstract class ");
    String _abstractClassName = GenerateCategoryUiSnippetArrayTable.getAbstractClassName(arrayProperty, category);
    _builder.append(_abstractClassName);
    _builder.append(" extends AUiSnippetArrayInstancePropertyTable implements IUiSnippet {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _abstractClassName_1 = GenerateCategoryUiSnippetArrayTable.getAbstractClassName(arrayProperty, category);
    _builder.append(_abstractClassName_1, "\t");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super(\"");
    String _name = concept.getName();
    _builder.append(_name, "\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("\"");
    String _name_1 = arrayProperty.getName();
    _builder.append(_name_1, "\t\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("\"");
    String _name_2 = category.getName();
    _builder.append(_name_2, "\t\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("STYLE_NONE);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected Command createAddCommand(EditingDomain editingDomain, Concept activeConcept) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return UnexecutableCommand.INSTANCE;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * This method handles the UiSnippet for a Table of Static Array of Categories
   */
  public CharSequence declareStaticArrayCategoryTableClass(final Concept concept, final Category category, final AProperty arrayProperty, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetArrayInstanceCategoryTable");
    _builder.newLineIfNotEmpty();
    importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet");
    _builder.newLineIfNotEmpty();
    importManager.register(Command.class);
    _builder.newLineIfNotEmpty();
    importManager.register(UnexecutableCommand.class);
    _builder.newLineIfNotEmpty();
    importManager.register(EditingDomain.class);
    _builder.newLineIfNotEmpty();
    importManager.register(Concept.class);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _generateAClassHeader = ConceptGeneratorUtil.generateAClassHeader(category);
    _builder.append(_generateAClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public abstract class ");
    String _abstractClassName = GenerateCategoryUiSnippetArrayTable.getAbstractClassName(arrayProperty, category);
    _builder.append(_abstractClassName);
    _builder.append(" extends AUiSnippetArrayInstanceCategoryTable implements IUiSnippet {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _abstractClassName_1 = GenerateCategoryUiSnippetArrayTable.getAbstractClassName(arrayProperty, category);
    _builder.append(_abstractClassName_1, "\t");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super(\"");
    String _name = concept.getName();
    _builder.append(_name, "\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("\"");
    String _name_1 = this.getArrayType(arrayProperty).getName();
    _builder.append(_name_1, "\t\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("\"");
    String _name_2 = arrayProperty.getName();
    _builder.append(_name_2, "\t\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("\"");
    String _name_3 = category.getName();
    _builder.append(_name_3, "\t\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("\"");
    String _fullQualifiedName = this.getArrayType(arrayProperty).getFullQualifiedName();
    _builder.append(_fullQualifiedName, "\t\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("STYLE_EDITOR_BUTTON);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected Command createAddCommand(EditingDomain editingDomain, Concept activeConcept) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return UnexecutableCommand.INSTANCE;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * This method handles the UiSnippet for a Table of Dynamic Array of Categories
   */
  public CharSequence declareDynamicArrayCategoryTableClass(final Concept concept, final Category category, final Category extendingCategory, final AProperty arrayProperty, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetArrayInstanceCategoryTable");
    _builder.newLineIfNotEmpty();
    importManager.register("de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet");
    _builder.newLineIfNotEmpty();
    String _name = concept.getName();
    String _plus = (_name + ".ui.command.");
    String _concreteClassName = GenerateArrayCreateAddCommand.getConcreteClassName(arrayProperty);
    String _plus_1 = (_plus + _concreteClassName);
    importManager.register(_plus_1);
    _builder.newLineIfNotEmpty();
    importManager.register(Command.class);
    _builder.newLineIfNotEmpty();
    importManager.register(EditingDomain.class);
    _builder.newLineIfNotEmpty();
    importManager.register(Concept.class);
    _builder.newLineIfNotEmpty();
    importManager.register(ActiveConceptHelper.class);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _generateAClassHeader = ConceptGeneratorUtil.generateAClassHeader(category);
    _builder.append(_generateAClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public abstract class ");
    String _abstractClassName = GenerateCategoryUiSnippetArrayTable.getAbstractClassName(arrayProperty, category, extendingCategory);
    _builder.append(_abstractClassName);
    _builder.append(" extends AUiSnippetArrayInstanceCategoryTable implements IUiSnippet {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _abstractClassName_1 = GenerateCategoryUiSnippetArrayTable.getAbstractClassName(arrayProperty, category, extendingCategory);
    _builder.append(_abstractClassName_1, "\t");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super(\"");
    String _name_1 = concept.getName();
    _builder.append(_name_1, "\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("\"");
    String _name_2 = extendingCategory.getName();
    _builder.append(_name_2, "\t\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("\"");
    String _name_3 = arrayProperty.getName();
    _builder.append(_name_3, "\t\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("\"");
    String _name_4 = category.getName();
    _builder.append(_name_4, "\t\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("\"");
    String _fullQualifiedName = extendingCategory.getFullQualifiedName();
    _builder.append(_fullQualifiedName, "\t\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("STYLE_ADD_BUTTON | STYLE_REMOVE_BUTTON | STYLE_EDITOR_BUTTON);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected Command createAddCommand(EditingDomain editingDomain, Concept activeConcept) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return new ");
    String _concreteClassName_1 = GenerateArrayCreateAddCommand.getConcreteClassName(arrayProperty);
    _builder.append(_concreteClassName_1, "\t\t");
    _builder.append("().create(editingDomain, getArrayInstance(model),  ActiveConceptHelper.getCategory(activeConcept, \"");
    String _name_5 = extendingCategory.getName();
    _builder.append(_name_5, "\t\t");
    _builder.append("\"));");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * This method hands back the correct reference depending
   * on if it is a composed or reference property.
   */
  public ATypeDefinition getArrayType(final AProperty arrayProperty) {
    if ((arrayProperty instanceof ComposedProperty)) {
      final ComposedProperty cp = ((ComposedProperty) arrayProperty);
      return cp.getType();
    } else {
      if ((arrayProperty instanceof ReferenceProperty)) {
        final ReferenceProperty rp = ((ReferenceProperty) arrayProperty);
        return rp.getReferenceType();
      }
    }
    return null;
  }
}
