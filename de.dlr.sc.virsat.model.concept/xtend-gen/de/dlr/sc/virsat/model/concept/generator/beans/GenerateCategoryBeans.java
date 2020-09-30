/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.beans;

import com.google.common.collect.Iterables;
import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator;
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider;
import de.dlr.sc.virsat.model.concept.generator.ImportManager;
import de.dlr.sc.virsat.model.concept.generator.ereference.ExternalGenModelHelper;
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeArrayInstanceList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyBeanList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeEReferenceArrayInstanceList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyBeanList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyInstanceList;
import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanRegistry;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEReference;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.util.PropertydefinitionsSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.json.ABeanObjectAdapter;
import java.util.Set;
import java.util.function.Consumer;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * This class is the generator for the category beans of our model extension.
 * The beans will give easier access to the model of categories and properties.
 * Furthermore it will provide strong typing
 */
@SuppressWarnings("all")
public class GenerateCategoryBeans extends AGeneratorGapGenerator<Category> {
  public static String getConcreteClassName(final ATypeDefinition typeDefinition) {
    return StringExtensions.toFirstUpper(typeDefinition.getName());
  }
  
  public static String getAbstractClassName(final ATypeDefinition typeDefinition) {
    String _firstUpper = StringExtensions.toFirstUpper(typeDefinition.getName());
    return ("A" + _firstUpper);
  }
  
  @Override
  public String createConcreteClassFileName(final Concept concept, final Category conceptPart) {
    String _packageFolder = this.getPackageFolder(concept);
    String _plus = (_packageFolder + "/");
    String _concreteClassName = GenerateCategoryBeans.getConcreteClassName(conceptPart);
    String _plus_1 = (_plus + _concreteClassName);
    return (_plus_1 + ".java");
  }
  
  @Override
  public String createAbstractClassFileName(final Concept concept, final Category conceptPart) {
    String _packageFolder = this.getPackageFolder(concept);
    String _plus = (_packageFolder + "/");
    String _abstractClassName = GenerateCategoryBeans.getAbstractClassName(conceptPart);
    String _plus_1 = (_plus + _abstractClassName);
    return (_plus_1 + ".java");
  }
  
  @Override
  protected String getPackage(final Concept concept) {
    String _name = concept.getName();
    String _plus = (_name + ".");
    return (_plus + GenerateCategoryBeans.PACKAGE_FOLDER);
  }
  
  public static final String PACKAGE_FOLDER = BeanRegistry.BEAN_PACKAGE_NAME;
  
  @Override
  public void serializeModel(final Concept concept, final IFileSystemAccess fsa) {
    final Consumer<Category> _function = (Category it) -> {
      CharSequence fileOutputAClass = this.createAbstractClass(concept, it);
      fsa.generateFile(this.createAbstractClassFileName(concept, it), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);
      CharSequence fileOutputClass = this.createConcreteClass(concept, it);
      fsa.generateFile(this.createConcreteClassFileName(concept, it), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
    };
    concept.getCategories().forEach(_function);
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
  public CharSequence declareImports(final ImportManager importManager, final Concept concept, final Category conceptPart, final String conceptPackage) {
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
  public CharSequence declareAClass(final Concept concept, final Category category, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register(CategoryAssignment.class);
    _builder.newLineIfNotEmpty();
    {
      Category _extendsCategory = category.getExtendsCategory();
      boolean _tripleEquals = (_extendsCategory == null);
      if (_tripleEquals) {
        importManager.register(ABeanCategoryAssignment.class);
        _builder.newLineIfNotEmpty();
      } else {
        importManager.register(category.getExtendsCategory());
        _builder.newLineIfNotEmpty();
      }
    }
    importManager.register(IBeanCategoryAssignment.class);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Class Declaration");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.newLine();
    CharSequence _generateAClassHeader = ConceptGeneratorUtil.generateAClassHeader(category);
    _builder.append(_generateAClassHeader);
    _builder.newLineIfNotEmpty();
    importManager.register(XmlRootElement.class);
    _builder.newLineIfNotEmpty();
    importManager.register(XmlAccessorType.class);
    _builder.newLineIfNotEmpty();
    importManager.register(XmlAccessType.class);
    _builder.newLineIfNotEmpty();
    _builder.append("@XmlRootElement");
    _builder.newLine();
    _builder.append("@XmlAccessorType(XmlAccessType.NONE)");
    _builder.newLine();
    _builder.append("public abstract class A");
    String _firstUpper = StringExtensions.toFirstUpper(category.getName());
    _builder.append(_firstUpper);
    _builder.append(" extends ");
    String _declareExtendedClass = this.declareExtendedClass(category);
    _builder.append(_declareExtendedClass);
    _builder.append(" implements IBeanCategoryAssignment {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    final String fullQualifiedCategoryId = ActiveConceptHelper.getFullQualifiedId(category);
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("public static final String FULL_QUALIFIED_CATEGORY_NAME = \"");
    _builder.append(fullQualifiedCategoryId, "\t");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("* Call this method to get the full qualified name of the underlying category");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("* @return The FQN of the category as String");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String getFullQualifiedCategoryName() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return FULL_QUALIFIED_CATEGORY_NAME;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _declarePropertyNameConstants = this.declarePropertyNameConstants(category);
    _builder.append(_declarePropertyNameConstants, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _declareEnumValues = this.declareEnumValues(category);
    _builder.append(_declareEnumValues, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _declareConstructor = this.declareConstructor(category, importManager);
    _builder.append(_declareConstructor, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _declareAttributes = this.declareAttributes(category, importManager);
    _builder.append(_declareAttributes, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected String declareExtendedClass(final Category category) {
    Category _extendsCategory = category.getExtendsCategory();
    boolean _tripleNotEquals = (_extendsCategory != null);
    if (_tripleNotEquals) {
      return StringExtensions.toFirstUpper(category.getExtendsCategory().getName());
    } else {
      return "ABeanCategoryAssignment";
    }
  }
  
  protected CharSequence declarePropertyNameConstants(final Category category) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// property name constants");
    _builder.newLine();
    {
      EList<AProperty> _properties = category.getProperties();
      for(final AProperty property : _properties) {
        _builder.append("public static final String PROPERTY_");
        String _upperCase = property.getName().toUpperCase();
        _builder.append(_upperCase);
        _builder.append(" = \"");
        String _name = property.getName();
        _builder.append(_name);
        _builder.append("\";");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  protected CharSequence declareEnumValues(final Category category) {
    StringConcatenation _builder = new StringConcatenation();
    {
      final Function1<EnumProperty, Boolean> _function = (EnumProperty it) -> {
        boolean _isEmpty = it.getValues().isEmpty();
        return Boolean.valueOf((!_isEmpty));
      };
      Iterable<EnumProperty> _filter = IterableExtensions.<EnumProperty>filter(Iterables.<EnumProperty>filter(category.getProperties(), EnumProperty.class), _function);
      for(final EnumProperty enumeration : _filter) {
        _builder.append("// ");
        String _firstUpper = StringExtensions.toFirstUpper(enumeration.getName());
        _builder.append(_firstUpper);
        _builder.append(" enumeration value names");
        _builder.newLineIfNotEmpty();
        {
          EList<EnumValueDefinition> _values = enumeration.getValues();
          for(final EnumValueDefinition value : _values) {
            _builder.append("public static final String ");
            String _upperCase = enumeration.getName().toUpperCase();
            _builder.append(_upperCase);
            _builder.append("_");
            String _name = value.getName();
            _builder.append(_name);
            _builder.append("_NAME = \"");
            String _name_1 = value.getName();
            _builder.append(_name_1);
            _builder.append("\";");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("// ");
        String _firstUpper_1 = StringExtensions.toFirstUpper(enumeration.getName());
        _builder.append(_firstUpper_1);
        _builder.append(" enumeration values");
        _builder.newLineIfNotEmpty();
        {
          final Function1<EnumValueDefinition, Boolean> _function_1 = (EnumValueDefinition it) -> {
            String _value = it.getValue();
            return Boolean.valueOf((_value != null));
          };
          Iterable<EnumValueDefinition> _filter_1 = IterableExtensions.<EnumValueDefinition>filter(enumeration.getValues(), _function_1);
          for(final EnumValueDefinition value_1 : _filter_1) {
            _builder.append("public static final String ");
            String _upperCase_1 = enumeration.getName().toUpperCase();
            _builder.append(_upperCase_1);
            _builder.append("_");
            String _name_2 = value_1.getName();
            _builder.append(_name_2);
            _builder.append("_VALUE = \"");
            String _value = value_1.getValue();
            _builder.append(_value);
            _builder.append("\";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  @Override
  public CharSequence declareClass(final Concept concept, final Category category, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register(Concept.class);
    _builder.newLineIfNotEmpty();
    importManager.register(CategoryAssignment.class);
    _builder.newLineIfNotEmpty();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Class Declaration");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.newLine();
    CharSequence _generateClassHeader = ConceptGeneratorUtil.generateClassHeader(category);
    _builder.append(_generateClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public ");
    String _declareIfAbstract = this.declareIfAbstract(category);
    _builder.append(_declareIfAbstract);
    _builder.append(" class ");
    String _firstUpper = StringExtensions.toFirstUpper(category.getName());
    _builder.append(_firstUpper);
    _builder.append(" extends A");
    String _firstUpper_1 = StringExtensions.toFirstUpper(category.getName());
    _builder.append(_firstUpper_1);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Constructor of Concept Class");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _firstUpper_2 = StringExtensions.toFirstUpper(category.getName());
    _builder.append(_firstUpper_2, "\t");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Constructor of Concept Class which will instantiate ");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* a CategoryAssignment in the background from the given concept");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @param concept the concept where it will find the correct Category to instantiate from");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _firstUpper_3 = StringExtensions.toFirstUpper(category.getName());
    _builder.append(_firstUpper_3, "\t");
    _builder.append("(Concept concept) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super(concept);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}\t");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Constructor of Concept Class that can be initialized manually by a given Category Assignment");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @param categoryAssignment The category Assignment to be used for background initialization of the Category bean");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _firstUpper_4 = StringExtensions.toFirstUpper(category.getName());
    _builder.append(_firstUpper_4, "\t");
    _builder.append("(CategoryAssignment categoryAssignment) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super(categoryAssignment);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected String declareIfAbstract(final Category category) {
    boolean _isIsAbstract = category.isIsAbstract();
    if (_isIsAbstract) {
      return "abstract";
    }
    return "";
  }
  
  /**
   * Method to create the constructor of the java category bean
   */
  protected CharSequence declareConstructor(final Category category, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register(Concept.class);
    _builder.newLineIfNotEmpty();
    importManager.register(Category.class);
    _builder.newLineIfNotEmpty();
    importManager.register(ActiveConceptHelper.class);
    _builder.newLineIfNotEmpty();
    importManager.register(CategoryInstantiator.class);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Class Constructors");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public A");
    String _firstUpper = StringExtensions.toFirstUpper(category.getName());
    _builder.append(_firstUpper);
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public A");
    String _firstUpper_1 = StringExtensions.toFirstUpper(category.getName());
    _builder.append(_firstUpper_1);
    _builder.append("(Concept concept) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("Category categoryFromActiveCategories = ActiveConceptHelper.getCategory(concept, \"");
    String _name = category.getName();
    _builder.append(_name, "\t");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("CategoryAssignment categoryAssignement = new CategoryInstantiator().generateInstance(categoryFromActiveCategories, \"");
    String _name_1 = category.getName();
    _builder.append(_name_1, "\t");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("setTypeInstance(categoryAssignement);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public A");
    String _firstUpper_2 = StringExtensions.toFirstUpper(category.getName());
    _builder.append(_firstUpper_2);
    _builder.append("(CategoryAssignment categoryAssignement) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("setTypeInstance(categoryAssignement);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence declareAttributes(final Category category, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<AProperty> _properties = category.getProperties();
      for(final AProperty property : _properties) {
        CharSequence _declareSingleAttributesOrArray = this.declareSingleAttributesOrArray(property, importManager);
        _builder.append(_declareSingleAttributesOrArray);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  protected CharSequence declareSingleAttributesOrArray(final AProperty property, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    {
      IArrayModifier _arrayModifier = property.getArrayModifier();
      boolean _tripleEquals = (_arrayModifier == null);
      if (_tripleEquals) {
        _builder.append("// *****************************************************************");
        _builder.newLine();
        _builder.append("// * Attribute: ");
        String _name = property.getName();
        _builder.append(_name);
        _builder.newLineIfNotEmpty();
        _builder.append("// *****************************************************************");
        _builder.newLine();
        CharSequence _declareAttributesSetterAndGetter = this.declareAttributesSetterAndGetter(property, importManager);
        _builder.append(_declareAttributesSetterAndGetter);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
      } else {
        _builder.append("// *****************************************************************");
        _builder.newLine();
        _builder.append("// * Array Attribute: ");
        String _name_1 = property.getName();
        _builder.append(_name_1);
        _builder.newLineIfNotEmpty();
        _builder.append("// *****************************************************************");
        _builder.newLine();
        CharSequence _declareArrayAttributesSetterAndGetter = this.declareArrayAttributesSetterAndGetter(property, importManager);
        _builder.append(_declareArrayAttributesSetterAndGetter);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  protected CharSequence declareSafeAccessArrayMethod(final AProperty property, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register(ArrayInstance.class);
    _builder.newLineIfNotEmpty();
    _builder.append("private void ");
    String _propertyMethodSafeAccess = this.propertyMethodSafeAccess(property);
    _builder.append(_propertyMethodSafeAccess);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (");
    String _name = property.getName();
    _builder.append(_name, "\t");
    _builder.append(".getArrayInstance() == null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    String _name_1 = property.getName();
    _builder.append(_name_1, "\t\t");
    _builder.append(".setArrayInstance((ArrayInstance) helper.getPropertyInstance(\"");
    String _name_2 = property.getName();
    _builder.append(_name_2, "\t\t");
    _builder.append("\"));");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence declareSafeAccessArrayBeanMethod(final AProperty property, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register(ArrayInstance.class);
    _builder.newLineIfNotEmpty();
    _builder.append("private void ");
    String _propertyMethodSafeAccessBean = this.propertyMethodSafeAccessBean(property);
    _builder.append(_propertyMethodSafeAccessBean);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (");
    String _name = property.getName();
    _builder.append(_name, "\t");
    _builder.append("Bean.getArrayInstance() == null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    String _name_1 = property.getName();
    _builder.append(_name_1, "\t\t");
    _builder.append("Bean.setArrayInstance((ArrayInstance) helper.getPropertyInstance(\"");
    String _name_2 = property.getName();
    _builder.append(_name_2, "\t\t");
    _builder.append("\"));");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence declareArrayAttributesSetterAndGetter(final AProperty property, final ImportManager importManager) {
    return new PropertydefinitionsSwitch<CharSequence>() {
      @Override
      public CharSequence caseFloatProperty(final FloatProperty property) {
        importManager.register(BeanPropertyFloat.class);
        importManager.register(IBeanList.class);
        importManager.register(TypeSafeArrayInstanceList.class);
        importManager.register(XmlElement.class);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("private IBeanList<BeanPropertyFloat> ");
        String _name = property.getName();
        _builder.append(_name);
        _builder.append("Bean = new TypeSafeArrayInstanceList<>(BeanPropertyFloat.class);");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        CharSequence _declareSafeAccessArrayBeanMethod = GenerateCategoryBeans.this.declareSafeAccessArrayBeanMethod(property, importManager);
        _builder.append(_declareSafeAccessArrayBeanMethod);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("@XmlElement");
        _builder.newLine();
        _builder.append("public IBeanList<BeanPropertyFloat> ");
        String _propertyMethodGetBean = GenerateCategoryBeans.this.propertyMethodGetBean(property);
        _builder.append(_propertyMethodGetBean);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccessBean = GenerateCategoryBeans.this.propertyMethodSafeAccessBean(property);
        _builder.append(_propertyMethodSafeAccessBean, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_1 = property.getName();
        _builder.append(_name_1, "\t");
        _builder.append("Bean;");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        return _builder;
      }
      
      @Override
      public CharSequence caseIntProperty(final IntProperty property) {
        importManager.register(BeanPropertyInt.class);
        importManager.register(IBeanList.class);
        importManager.register(TypeSafeArrayInstanceList.class);
        importManager.register(XmlElement.class);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("private IBeanList<BeanPropertyInt> ");
        String _name = property.getName();
        _builder.append(_name);
        _builder.append("Bean = new TypeSafeArrayInstanceList<>(BeanPropertyInt.class);");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        CharSequence _declareSafeAccessArrayBeanMethod = GenerateCategoryBeans.this.declareSafeAccessArrayBeanMethod(property, importManager);
        _builder.append(_declareSafeAccessArrayBeanMethod);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("@XmlElement");
        _builder.newLine();
        _builder.append("public IBeanList<BeanPropertyInt> ");
        String _propertyMethodGetBean = GenerateCategoryBeans.this.propertyMethodGetBean(property);
        _builder.append(_propertyMethodGetBean);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccessBean = GenerateCategoryBeans.this.propertyMethodSafeAccessBean(property);
        _builder.append(_propertyMethodSafeAccessBean, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_1 = property.getName();
        _builder.append(_name_1, "\t");
        _builder.append("Bean;");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        return _builder;
      }
      
      @Override
      public CharSequence caseBooleanProperty(final BooleanProperty property) {
        importManager.register(BeanPropertyBoolean.class);
        importManager.register(IBeanList.class);
        importManager.register(TypeSafeArrayInstanceList.class);
        importManager.register(XmlElement.class);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("private IBeanList<BeanPropertyBoolean> ");
        String _name = property.getName();
        _builder.append(_name);
        _builder.append("Bean = new TypeSafeArrayInstanceList<>(BeanPropertyBoolean.class);");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        CharSequence _declareSafeAccessArrayBeanMethod = GenerateCategoryBeans.this.declareSafeAccessArrayBeanMethod(property, importManager);
        _builder.append(_declareSafeAccessArrayBeanMethod);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("@XmlElement");
        _builder.newLine();
        _builder.append("public IBeanList<BeanPropertyBoolean> ");
        String _propertyMethodGetBean = GenerateCategoryBeans.this.propertyMethodGetBean(property);
        _builder.append(_propertyMethodGetBean);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccessBean = GenerateCategoryBeans.this.propertyMethodSafeAccessBean(property);
        _builder.append(_propertyMethodSafeAccessBean, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_1 = property.getName();
        _builder.append(_name_1, "\t");
        _builder.append("Bean;");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        return _builder;
      }
      
      @Override
      public CharSequence caseStringProperty(final StringProperty property) {
        importManager.register(BeanPropertyString.class);
        importManager.register(IBeanList.class);
        importManager.register(TypeSafeArrayInstanceList.class);
        importManager.register(XmlElement.class);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("private IBeanList<BeanPropertyString> ");
        String _name = property.getName();
        _builder.append(_name);
        _builder.append("Bean = new TypeSafeArrayInstanceList<>(BeanPropertyString.class);");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        CharSequence _declareSafeAccessArrayBeanMethod = GenerateCategoryBeans.this.declareSafeAccessArrayBeanMethod(property, importManager);
        _builder.append(_declareSafeAccessArrayBeanMethod);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("@XmlElement");
        _builder.newLine();
        _builder.append("public IBeanList<BeanPropertyString> ");
        String _propertyMethodGetBean = GenerateCategoryBeans.this.propertyMethodGetBean(property);
        _builder.append(_propertyMethodGetBean);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccessBean = GenerateCategoryBeans.this.propertyMethodSafeAccessBean(property);
        _builder.append(_propertyMethodSafeAccessBean, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_1 = property.getName();
        _builder.append(_name_1, "\t");
        _builder.append("Bean;");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        return _builder;
      }
      
      @Override
      public CharSequence caseResourceProperty(final ResourceProperty object) {
        importManager.register(BeanPropertyResource.class);
        importManager.register(IBeanList.class);
        importManager.register(TypeSafeArrayInstanceList.class);
        importManager.register(XmlElement.class);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("private IBeanList<BeanPropertyResource> ");
        String _name = property.getName();
        _builder.append(_name);
        _builder.append("Bean = new TypeSafeArrayInstanceList<>(BeanPropertyResource.class);");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        CharSequence _declareSafeAccessArrayBeanMethod = GenerateCategoryBeans.this.declareSafeAccessArrayBeanMethod(property, importManager);
        _builder.append(_declareSafeAccessArrayBeanMethod);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("@XmlElement");
        _builder.newLine();
        _builder.append("public IBeanList<BeanPropertyResource> ");
        String _propertyMethodGetBean = GenerateCategoryBeans.this.propertyMethodGetBean(property);
        _builder.append(_propertyMethodGetBean);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccessBean = GenerateCategoryBeans.this.propertyMethodSafeAccessBean(property);
        _builder.append(_propertyMethodSafeAccessBean, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_1 = property.getName();
        _builder.append(_name_1, "\t");
        _builder.append("Bean;");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        return _builder;
      }
      
      @Override
      public CharSequence caseEnumProperty(final EnumProperty property) {
        importManager.register(BeanPropertyEnum.class);
        importManager.register(IBeanList.class);
        importManager.register(TypeSafeArrayInstanceList.class);
        importManager.register(XmlElement.class);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("private IBeanList<BeanPropertyEnum> ");
        String _name = property.getName();
        _builder.append(_name);
        _builder.append("Bean = new TypeSafeArrayInstanceList<>(BeanPropertyEnum.class);");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        CharSequence _declareSafeAccessArrayBeanMethod = GenerateCategoryBeans.this.declareSafeAccessArrayBeanMethod(property, importManager);
        _builder.append(_declareSafeAccessArrayBeanMethod);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("@XmlElement");
        _builder.newLine();
        _builder.append("public IBeanList<BeanPropertyEnum> ");
        String _propertyMethodGetBean = GenerateCategoryBeans.this.propertyMethodGetBean(property);
        _builder.append(_propertyMethodGetBean);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccessBean = GenerateCategoryBeans.this.propertyMethodSafeAccessBean(property);
        _builder.append(_propertyMethodSafeAccessBean, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_1 = property.getName();
        _builder.append(_name_1, "\t");
        _builder.append("Bean;");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        return _builder;
      }
      
      @Override
      public CharSequence caseComposedProperty(final ComposedProperty property) {
        importManager.register(property.getType());
        importManager.register(IBeanList.class);
        importManager.register(TypeSafeComposedPropertyInstanceList.class);
        importManager.register(BeanPropertyComposed.class);
        importManager.register(TypeSafeComposedPropertyBeanList.class);
        importManager.register(XmlElement.class);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("private IBeanList<");
        String _name = property.getType().getName();
        _builder.append(_name);
        _builder.append("> ");
        String _name_1 = property.getName();
        _builder.append(_name_1);
        _builder.append(" = new TypeSafeComposedPropertyInstanceList<>(");
        String _name_2 = property.getType().getName();
        _builder.append(_name_2);
        _builder.append(".class);");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        CharSequence _declareSafeAccessArrayMethod = GenerateCategoryBeans.this.declareSafeAccessArrayMethod(property, importManager);
        _builder.append(_declareSafeAccessArrayMethod);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("public IBeanList<");
        String _name_3 = property.getType().getName();
        _builder.append(_name_3);
        _builder.append("> ");
        String _propertyMethodGet = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_4 = property.getName();
        _builder.append(_name_4, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("private IBeanList<BeanPropertyComposed<");
        String _name_5 = property.getType().getName();
        _builder.append(_name_5);
        _builder.append(">> ");
        String _name_6 = property.getName();
        _builder.append(_name_6);
        _builder.append("Bean = new TypeSafeComposedPropertyBeanList<>();");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        CharSequence _declareSafeAccessArrayBeanMethod = GenerateCategoryBeans.this.declareSafeAccessArrayBeanMethod(property, importManager);
        _builder.append(_declareSafeAccessArrayBeanMethod);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("@XmlElement");
        _builder.newLine();
        _builder.append("public IBeanList<BeanPropertyComposed<");
        String _name_7 = property.getType().getName();
        _builder.append(_name_7);
        _builder.append(">> ");
        String _propertyMethodGetBean = GenerateCategoryBeans.this.propertyMethodGetBean(property);
        _builder.append(_propertyMethodGetBean);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccessBean = GenerateCategoryBeans.this.propertyMethodSafeAccessBean(property);
        _builder.append(_propertyMethodSafeAccessBean, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_8 = property.getName();
        _builder.append(_name_8, "\t");
        _builder.append("Bean;");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        return _builder;
      }
      
      @Override
      public CharSequence caseReferenceProperty(final ReferenceProperty property) {
        importManager.register(IBeanList.class);
        importManager.register(BeanPropertyReference.class);
        importManager.register(TypeSafeReferencePropertyBeanList.class);
        importManager.register(XmlElement.class);
        ATypeDefinition _referenceType = property.getReferenceType();
        if ((_referenceType instanceof Category)) {
          importManager.register(property.getReferenceType());
          importManager.register(TypeSafeReferencePropertyInstanceList.class);
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("\t");
          _builder.append("private IBeanList<");
          String _name = property.getReferenceType().getName();
          _builder.append(_name, "\t");
          _builder.append("> ");
          String _name_1 = property.getName();
          _builder.append(_name_1, "\t");
          _builder.append(" = new TypeSafeReferencePropertyInstanceList<>(");
          String _name_2 = property.getReferenceType().getName();
          _builder.append(_name_2, "\t");
          _builder.append(".class);");
          _builder.newLineIfNotEmpty();
          _builder.newLine();
          _builder.append("\t");
          CharSequence _declareSafeAccessArrayMethod = GenerateCategoryBeans.this.declareSafeAccessArrayMethod(property, importManager);
          _builder.append(_declareSafeAccessArrayMethod, "\t");
          _builder.newLineIfNotEmpty();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("public IBeanList<");
          String _name_3 = property.getReferenceType().getName();
          _builder.append(_name_3, "\t");
          _builder.append("> ");
          String _propertyMethodGet = GenerateCategoryBeans.this.propertyMethodGet(property);
          _builder.append(_propertyMethodGet, "\t");
          _builder.append("() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          String _propertyMethodSafeAccess = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
          _builder.append(_propertyMethodSafeAccess, "\t\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("return ");
          String _name_4 = property.getName();
          _builder.append(_name_4, "\t\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("private IBeanList<BeanPropertyReference<");
          String _name_5 = property.getReferenceType().getName();
          _builder.append(_name_5, "\t");
          _builder.append(">> ");
          String _name_6 = property.getName();
          _builder.append(_name_6, "\t");
          _builder.append("Bean = new TypeSafeReferencePropertyBeanList<>();");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          CharSequence _declareSafeAccessArrayBeanMethod = GenerateCategoryBeans.this.declareSafeAccessArrayBeanMethod(property, importManager);
          _builder.append(_declareSafeAccessArrayBeanMethod, "\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("@XmlElement");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("public IBeanList<BeanPropertyReference<");
          String _name_7 = property.getReferenceType().getName();
          _builder.append(_name_7, "\t");
          _builder.append(">> ");
          String _propertyMethodGetBean = GenerateCategoryBeans.this.propertyMethodGetBean(property);
          _builder.append(_propertyMethodGetBean, "\t");
          _builder.append("() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          String _propertyMethodSafeAccessBean = GenerateCategoryBeans.this.propertyMethodSafeAccessBean(property);
          _builder.append(_propertyMethodSafeAccessBean, "\t\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("return ");
          String _name_8 = property.getName();
          _builder.append(_name_8, "\t\t");
          _builder.append("Bean;");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
          return _builder;
        } else {
          ATypeDefinition _referenceType_1 = property.getReferenceType();
          AProperty referencedProperty = ((AProperty) _referenceType_1);
          Class<?> referencedPropertyType = GenerateCategoryBeans.getReferencePropertyType(referencedProperty);
          importManager.register(TypeSafeReferencePropertyInstanceList.class);
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("\t");
          _builder_1.append("private IBeanList<");
          String _name_9 = referencedPropertyType.getName();
          _builder_1.append(_name_9, "\t");
          _builder_1.append("> ");
          String _name_10 = property.getName();
          _builder_1.append(_name_10, "\t");
          _builder_1.append(" = new TypeSafeReferencePropertyInstanceList<>(");
          String _name_11 = referencedPropertyType.getName();
          _builder_1.append(_name_11, "\t");
          _builder_1.append(".class);");
          _builder_1.newLineIfNotEmpty();
          _builder_1.newLine();
          _builder_1.append("\t");
          CharSequence _declareSafeAccessArrayMethod_1 = GenerateCategoryBeans.this.declareSafeAccessArrayMethod(property, importManager);
          _builder_1.append(_declareSafeAccessArrayMethod_1, "\t");
          _builder_1.newLineIfNotEmpty();
          _builder_1.newLine();
          _builder_1.append("\t");
          _builder_1.append("public IBeanList<");
          String _name_12 = referencedPropertyType.getName();
          _builder_1.append(_name_12, "\t");
          _builder_1.append("> ");
          String _propertyMethodGet_1 = GenerateCategoryBeans.this.propertyMethodGet(property);
          _builder_1.append(_propertyMethodGet_1, "\t");
          _builder_1.append("() {");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t\t");
          String _propertyMethodSafeAccess_1 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
          _builder_1.append(_propertyMethodSafeAccess_1, "\t\t");
          _builder_1.append(";");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t\t");
          _builder_1.append("return ");
          String _name_13 = property.getName();
          _builder_1.append(_name_13, "\t\t");
          _builder_1.append(";");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          _builder_1.append("}");
          _builder_1.newLine();
          _builder_1.append("\t");
          _builder_1.newLine();
          _builder_1.append("\t");
          _builder_1.append("private IBeanList<BeanPropertyReference<");
          String _name_14 = referencedPropertyType.getName();
          _builder_1.append(_name_14, "\t");
          _builder_1.append(">> ");
          String _name_15 = property.getName();
          _builder_1.append(_name_15, "\t");
          _builder_1.append("Bean = new TypeSafeReferencePropertyBeanList<>();");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          _builder_1.newLine();
          _builder_1.append("\t");
          CharSequence _declareSafeAccessArrayBeanMethod_1 = GenerateCategoryBeans.this.declareSafeAccessArrayBeanMethod(property, importManager);
          _builder_1.append(_declareSafeAccessArrayBeanMethod_1, "\t");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          _builder_1.newLine();
          _builder_1.append("\t");
          _builder_1.append("@XmlElement");
          _builder_1.newLine();
          _builder_1.append("\t");
          _builder_1.append("public IBeanList<BeanPropertyReference<");
          String _name_16 = referencedPropertyType.getName();
          _builder_1.append(_name_16, "\t");
          _builder_1.append(">> ");
          String _propertyMethodGetBean_1 = GenerateCategoryBeans.this.propertyMethodGetBean(property);
          _builder_1.append(_propertyMethodGetBean_1, "\t");
          _builder_1.append("() {");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t\t");
          String _propertyMethodSafeAccessBean_1 = GenerateCategoryBeans.this.propertyMethodSafeAccessBean(property);
          _builder_1.append(_propertyMethodSafeAccessBean_1, "\t\t");
          _builder_1.append(";");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t\t");
          _builder_1.append("return ");
          String _name_17 = property.getName();
          _builder_1.append(_name_17, "\t\t");
          _builder_1.append("Bean;");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          _builder_1.append("}");
          _builder_1.newLine();
          return _builder_1;
        }
      }
      
      @Override
      public CharSequence caseEReferenceProperty(final EReferenceProperty property) {
        importManager.register(BeanPropertyEReference.class);
        importManager.register(IBeanList.class);
        importManager.register(TypeSafeEReferenceArrayInstanceList.class);
        final String typeClass = new ExternalGenModelHelper().getEObjectClass(property);
        final boolean genPackageSpecified = (typeClass != null);
        if (genPackageSpecified) {
          importManager.register(typeClass);
        }
        StringConcatenation _builder = new StringConcatenation();
        {
          if (genPackageSpecified) {
            String _name = property.getReferenceType().getName();
            _builder.append(_name);
          } else {
            _builder.append("EObject");
          }
        }
        final String referenceType = _builder.toString();
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("private IBeanList<BeanPropertyEReference<");
        _builder_1.append(referenceType);
        _builder_1.append(">> ");
        String _name_1 = property.getName();
        _builder_1.append(_name_1);
        _builder_1.append("Bean = new TypeSafeEReferenceArrayInstanceList<");
        _builder_1.append(referenceType);
        _builder_1.append(">();");
        _builder_1.newLineIfNotEmpty();
        _builder_1.newLine();
        CharSequence _declareSafeAccessArrayBeanMethod = GenerateCategoryBeans.this.declareSafeAccessArrayBeanMethod(property, importManager);
        _builder_1.append(_declareSafeAccessArrayBeanMethod);
        _builder_1.newLineIfNotEmpty();
        _builder_1.newLine();
        _builder_1.append("public IBeanList<BeanPropertyEReference<");
        _builder_1.append(referenceType);
        _builder_1.append(">> ");
        String _propertyMethodGetBean = GenerateCategoryBeans.this.propertyMethodGetBean(property);
        _builder_1.append(_propertyMethodGetBean);
        _builder_1.append("() {");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("\t");
        String _propertyMethodSafeAccessBean = GenerateCategoryBeans.this.propertyMethodSafeAccessBean(property);
        _builder_1.append(_propertyMethodSafeAccessBean, "\t");
        _builder_1.append(";");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("\t");
        _builder_1.append("return ");
        String _name_2 = property.getName();
        _builder_1.append(_name_2, "\t");
        _builder_1.append("Bean;");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("}");
        _builder_1.newLine();
        return _builder_1;
      }
    }.doSwitch(property);
  }
  
  protected CharSequence declareSafeAccessAttributeMethod(final AProperty property, final Class<? extends APropertyInstance> castTypeClass) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private void ");
    String _propertyMethodSafeAccess = this.propertyMethodSafeAccess(property);
    _builder.append(_propertyMethodSafeAccess);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (");
    String _name = property.getName();
    _builder.append(_name, "\t");
    _builder.append(".getTypeInstance() == null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    String _name_1 = property.getName();
    _builder.append(_name_1, "\t\t");
    _builder.append(".setTypeInstance((");
    String _simpleName = castTypeClass.getSimpleName();
    _builder.append(_simpleName, "\t\t");
    _builder.append(") helper.getPropertyInstance(\"");
    String _name_2 = property.getName();
    _builder.append(_name_2, "\t\t");
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
   * Entry method to write the needed getters and setters
   * This method actually dispatches due to the type of property that is used
   */
  protected CharSequence declareAttributesSetterAndGetter(final AProperty property, final ImportManager importManager) {
    return new PropertydefinitionsSwitch<CharSequence>() {
      @Override
      public CharSequence caseFloatProperty(final FloatProperty property) {
        importManager.register(BeanPropertyFloat.class);
        importManager.register(Command.class);
        importManager.register(EditingDomain.class);
        importManager.register(UnitValuePropertyInstance.class);
        importManager.register(XmlElement.class);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("private BeanPropertyFloat ");
        String _name = property.getName();
        _builder.append(_name);
        _builder.append(" = new BeanPropertyFloat();");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        CharSequence _declareSafeAccessAttributeMethod = GenerateCategoryBeans.this.declareSafeAccessAttributeMethod(property, UnitValuePropertyInstance.class);
        _builder.append(_declareSafeAccessAttributeMethod);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("public Command ");
        String _propertyMethodSet = GenerateCategoryBeans.this.propertyMethodSet(property);
        _builder.append(_propertyMethodSet);
        _builder.append("(EditingDomain ed, double value) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return this.");
        String _name_1 = property.getName();
        _builder.append(_name_1, "\t");
        _builder.append(".setValue(ed, value);");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public void ");
        String _propertyMethodSet_1 = GenerateCategoryBeans.this.propertyMethodSet(property);
        _builder.append(_propertyMethodSet_1);
        _builder.append("(double value) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_1 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_1, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("this.");
        String _name_2 = property.getName();
        _builder.append(_name_2, "\t");
        _builder.append(".setValue(value);");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public double ");
        String _propertyMethodGet = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_2 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_2, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_3 = property.getName();
        _builder.append(_name_3, "\t");
        _builder.append(".getValue();");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public boolean ");
        String _propertyMethodIsSet = GenerateCategoryBeans.this.propertyMethodIsSet(property);
        _builder.append(_propertyMethodIsSet);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_3 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_3, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_4 = property.getName();
        _builder.append(_name_4, "\t");
        _builder.append(".isSet();");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("@XmlElement");
        _builder.newLine();
        _builder.append("public BeanPropertyFloat ");
        String _propertyMethodGet_1 = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet_1);
        _builder.append("Bean() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_4 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_4, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_5 = property.getName();
        _builder.append(_name_5, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        return _builder;
      }
      
      @Override
      public CharSequence caseIntProperty(final IntProperty property) {
        importManager.register(BeanPropertyInt.class);
        importManager.register(Command.class);
        importManager.register(EditingDomain.class);
        importManager.register(UnitValuePropertyInstance.class);
        importManager.register(XmlElement.class);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("private BeanPropertyInt ");
        String _name = property.getName();
        _builder.append(_name);
        _builder.append(" = new BeanPropertyInt();");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        CharSequence _declareSafeAccessAttributeMethod = GenerateCategoryBeans.this.declareSafeAccessAttributeMethod(property, UnitValuePropertyInstance.class);
        _builder.append(_declareSafeAccessAttributeMethod);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("public Command ");
        String _propertyMethodSet = GenerateCategoryBeans.this.propertyMethodSet(property);
        _builder.append(_propertyMethodSet);
        _builder.append("(EditingDomain ed, long value) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return this.");
        String _name_1 = property.getName();
        _builder.append(_name_1, "\t");
        _builder.append(".setValue(ed, value);");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public void ");
        String _propertyMethodSet_1 = GenerateCategoryBeans.this.propertyMethodSet(property);
        _builder.append(_propertyMethodSet_1);
        _builder.append("(long value) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_1 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_1, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("this.");
        String _name_2 = property.getName();
        _builder.append(_name_2, "\t");
        _builder.append(".setValue(value);");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public long ");
        String _propertyMethodGet = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_2 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_2, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_3 = property.getName();
        _builder.append(_name_3, "\t");
        _builder.append(".getValue();");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public boolean ");
        String _propertyMethodIsSet = GenerateCategoryBeans.this.propertyMethodIsSet(property);
        _builder.append(_propertyMethodIsSet);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_3 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_3, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_4 = property.getName();
        _builder.append(_name_4, "\t");
        _builder.append(".isSet();");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("@XmlElement");
        _builder.newLine();
        _builder.append("public BeanPropertyInt ");
        String _propertyMethodGet_1 = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet_1);
        _builder.append("Bean() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_4 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_4, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_5 = property.getName();
        _builder.append(_name_5, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        return _builder;
      }
      
      @Override
      public CharSequence caseBooleanProperty(final BooleanProperty property) {
        importManager.register(BeanPropertyBoolean.class);
        importManager.register(Command.class);
        importManager.register(EditingDomain.class);
        importManager.register(ValuePropertyInstance.class);
        importManager.register(XmlElement.class);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("private BeanPropertyBoolean ");
        String _name = property.getName();
        _builder.append(_name);
        _builder.append(" = new BeanPropertyBoolean();");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        CharSequence _declareSafeAccessAttributeMethod = GenerateCategoryBeans.this.declareSafeAccessAttributeMethod(property, ValuePropertyInstance.class);
        _builder.append(_declareSafeAccessAttributeMethod);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("public Command ");
        String _propertyMethodSet = GenerateCategoryBeans.this.propertyMethodSet(property);
        _builder.append(_propertyMethodSet);
        _builder.append("(EditingDomain ed, boolean value) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return this.");
        String _name_1 = property.getName();
        _builder.append(_name_1, "\t");
        _builder.append(".setValue(ed, value);");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public void ");
        String _propertyMethodSet_1 = GenerateCategoryBeans.this.propertyMethodSet(property);
        _builder.append(_propertyMethodSet_1);
        _builder.append("(boolean value) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_1 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_1, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("this.");
        String _name_2 = property.getName();
        _builder.append(_name_2, "\t");
        _builder.append(".setValue(value);");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public boolean ");
        String _propertyMethodGet = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_2 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_2, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_3 = property.getName();
        _builder.append(_name_3, "\t");
        _builder.append(".getValue();");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("@XmlElement");
        _builder.newLine();
        _builder.append("public BeanPropertyBoolean ");
        String _propertyMethodGet_1 = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet_1);
        _builder.append("Bean() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_3 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_3, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_4 = property.getName();
        _builder.append(_name_4, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        return _builder;
      }
      
      @Override
      public CharSequence caseStringProperty(final StringProperty property) {
        importManager.register(BeanPropertyString.class);
        importManager.register(Command.class);
        importManager.register(EditingDomain.class);
        importManager.register(ValuePropertyInstance.class);
        importManager.register(XmlElement.class);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("private BeanPropertyString ");
        String _name = property.getName();
        _builder.append(_name);
        _builder.append(" = new BeanPropertyString();");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        CharSequence _declareSafeAccessAttributeMethod = GenerateCategoryBeans.this.declareSafeAccessAttributeMethod(property, ValuePropertyInstance.class);
        _builder.append(_declareSafeAccessAttributeMethod);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("public Command ");
        String _propertyMethodSet = GenerateCategoryBeans.this.propertyMethodSet(property);
        _builder.append(_propertyMethodSet);
        _builder.append("(EditingDomain ed, String value) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return this.");
        String _name_1 = property.getName();
        _builder.append(_name_1, "\t");
        _builder.append(".setValue(ed, value);");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public void ");
        String _propertyMethodSet_1 = GenerateCategoryBeans.this.propertyMethodSet(property);
        _builder.append(_propertyMethodSet_1);
        _builder.append("(String value) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_1 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_1, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("this.");
        String _name_2 = property.getName();
        _builder.append(_name_2, "\t");
        _builder.append(".setValue(value);");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public String ");
        String _propertyMethodGet = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_2 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_2, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_3 = property.getName();
        _builder.append(_name_3, "\t");
        _builder.append(".getValue();");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("@XmlElement");
        _builder.newLine();
        _builder.append("public BeanPropertyString ");
        String _propertyMethodGet_1 = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet_1);
        _builder.append("Bean() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_3 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_3, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_4 = property.getName();
        _builder.append(_name_4, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        return _builder;
      }
      
      @Override
      public CharSequence caseResourceProperty(final ResourceProperty object) {
        importManager.register(BeanPropertyResource.class);
        importManager.register(Command.class);
        importManager.register(EditingDomain.class);
        importManager.register(ResourcePropertyInstance.class);
        importManager.register(URI.class);
        importManager.register(XmlElement.class);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("private BeanPropertyResource ");
        String _name = property.getName();
        _builder.append(_name);
        _builder.append(" = new BeanPropertyResource();");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        CharSequence _declareSafeAccessAttributeMethod = GenerateCategoryBeans.this.declareSafeAccessAttributeMethod(property, ResourcePropertyInstance.class);
        _builder.append(_declareSafeAccessAttributeMethod);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("public Command ");
        String _propertyMethodSet = GenerateCategoryBeans.this.propertyMethodSet(property);
        _builder.append(_propertyMethodSet);
        _builder.append("(EditingDomain ed, URI value) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return this.");
        String _name_1 = property.getName();
        _builder.append(_name_1, "\t");
        _builder.append(".setValue(ed, value);");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public void ");
        String _propertyMethodSet_1 = GenerateCategoryBeans.this.propertyMethodSet(property);
        _builder.append(_propertyMethodSet_1);
        _builder.append("(URI value) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_1 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_1, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("this.");
        String _name_2 = property.getName();
        _builder.append(_name_2, "\t");
        _builder.append(".setValue(value);");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public URI ");
        String _propertyMethodGet = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_2 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_2, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_3 = property.getName();
        _builder.append(_name_3, "\t");
        _builder.append(".getValue();");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("@XmlElement");
        _builder.newLine();
        _builder.append("public BeanPropertyResource ");
        String _propertyMethodGet_1 = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet_1);
        _builder.append("Bean() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_3 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_3, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_4 = property.getName();
        _builder.append(_name_4, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        return _builder;
      }
      
      @Override
      public CharSequence caseEnumProperty(final EnumProperty property) {
        importManager.register(BeanPropertyEnum.class);
        importManager.register(Command.class);
        importManager.register(EditingDomain.class);
        importManager.register(EnumUnitPropertyInstance.class);
        importManager.register(XmlElement.class);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("private BeanPropertyEnum ");
        String _name = property.getName();
        _builder.append(_name);
        _builder.append(" = new BeanPropertyEnum();");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        CharSequence _declareSafeAccessAttributeMethod = GenerateCategoryBeans.this.declareSafeAccessAttributeMethod(property, EnumUnitPropertyInstance.class);
        _builder.append(_declareSafeAccessAttributeMethod);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("public Command ");
        String _propertyMethodSet = GenerateCategoryBeans.this.propertyMethodSet(property);
        _builder.append(_propertyMethodSet);
        _builder.append("(EditingDomain ed, String value) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return this.");
        String _name_1 = property.getName();
        _builder.append(_name_1, "\t");
        _builder.append(".setValue(ed, value);");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public void ");
        String _propertyMethodSet_1 = GenerateCategoryBeans.this.propertyMethodSet(property);
        _builder.append(_propertyMethodSet_1);
        _builder.append("(String value) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_1 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_1, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("this.");
        String _name_2 = property.getName();
        _builder.append(_name_2, "\t");
        _builder.append(".setValue(value);");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public String ");
        String _propertyMethodGet = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_2 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_2, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_3 = property.getName();
        _builder.append(_name_3, "\t");
        _builder.append(".getValue();");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public double ");
        String _propertyMethodGet_1 = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet_1);
        _builder.append("Enum() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_3 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_3, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_4 = property.getName();
        _builder.append(_name_4, "\t");
        _builder.append(".getEnumValue();");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("@XmlElement");
        _builder.newLine();
        _builder.append("public BeanPropertyEnum ");
        String _propertyMethodGet_2 = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet_2);
        _builder.append("Bean() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_4 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_4, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_5 = property.getName();
        _builder.append(_name_5, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        return _builder;
      }
      
      @Override
      public CharSequence caseComposedProperty(final ComposedProperty property) {
        importManager.register(ComposedPropertyInstance.class);
        importManager.register(property.getType());
        importManager.register(BeanPropertyComposed.class);
        importManager.register(XmlElement.class);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("private BeanPropertyComposed<");
        String _name = property.getType().getName();
        _builder.append(_name);
        _builder.append("> ");
        String _name_1 = property.getName();
        _builder.append(_name_1);
        _builder.append(" = new BeanPropertyComposed<>();");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("private void ");
        String _propertyMethodSafeAccess = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess);
        _builder.append(" {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("if (");
        String _name_2 = property.getName();
        _builder.append(_name_2, "\t");
        _builder.append(".getTypeInstance() == null) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("ComposedPropertyInstance propertyInstance = (ComposedPropertyInstance) helper.getPropertyInstance(\"");
        String _name_3 = property.getName();
        _builder.append(_name_3, "\t\t");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        String _name_4 = property.getName();
        _builder.append(_name_4, "\t\t");
        _builder.append(".setTypeInstance(propertyInstance);");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("@XmlElement(nillable = true)");
        _builder.newLine();
        _builder.append("public ");
        String _name_5 = property.getType().getName();
        _builder.append(_name_5);
        _builder.append(" ");
        String _propertyMethodGet = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_1 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_1, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_6 = property.getName();
        _builder.append(_name_6, "\t");
        _builder.append(".getValue();");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public BeanPropertyComposed<");
        String _name_7 = property.getType().getName();
        _builder.append(_name_7);
        _builder.append("> ");
        String _propertyMethodGet_1 = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder.append(_propertyMethodGet_1);
        _builder.append("Bean() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _propertyMethodSafeAccess_2 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder.append(_propertyMethodSafeAccess_2, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_8 = property.getName();
        _builder.append(_name_8, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        return _builder;
      }
      
      @Override
      public CharSequence caseReferenceProperty(final ReferenceProperty property) {
        importManager.register(ReferencePropertyInstance.class);
        importManager.register(CategoryAssignment.class);
        importManager.register(Command.class);
        importManager.register(EditingDomain.class);
        importManager.register(BeanPropertyReference.class);
        importManager.register(ABeanObjectAdapter.class);
        importManager.register(XmlJavaTypeAdapter.class);
        importManager.register(XmlElement.class);
        ATypeDefinition _referenceType = property.getReferenceType();
        if ((_referenceType instanceof Category)) {
          importManager.register(property.getReferenceType());
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("private BeanPropertyReference<");
          String _name = property.getReferenceType().getName();
          _builder.append(_name);
          _builder.append("> ");
          String _name_1 = property.getName();
          _builder.append(_name_1);
          _builder.append(" = new BeanPropertyReference<>();");
          _builder.newLineIfNotEmpty();
          _builder.newLine();
          _builder.append("private void ");
          String _propertyMethodSafeAccess = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
          _builder.append(_propertyMethodSafeAccess);
          _builder.append(" {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance(\"");
          String _name_2 = property.getName();
          _builder.append(_name_2, "\t");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _name_3 = property.getName();
          _builder.append(_name_3, "\t");
          _builder.append(".setTypeInstance(propertyInstance);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("@XmlElement(nillable = true)");
          _builder.newLine();
          _builder.append("@XmlJavaTypeAdapter(ABeanObjectAdapter.class)");
          _builder.newLine();
          _builder.append("public ");
          String _name_4 = property.getReferenceType().getName();
          _builder.append(_name_4);
          _builder.append(" ");
          String _propertyMethodGet = GenerateCategoryBeans.this.propertyMethodGet(property);
          _builder.append(_propertyMethodGet);
          _builder.append("() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _propertyMethodSafeAccess_1 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
          _builder.append(_propertyMethodSafeAccess_1, "\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("return ");
          String _name_5 = property.getName();
          _builder.append(_name_5, "\t");
          _builder.append(".getValue();");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("public Command ");
          String _propertyMethodSet = GenerateCategoryBeans.this.propertyMethodSet(property);
          _builder.append(_propertyMethodSet);
          _builder.append("(EditingDomain ed, ");
          String _name_6 = property.getReferenceType().getName();
          _builder.append(_name_6);
          _builder.append(" value) {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _propertyMethodSafeAccess_2 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
          _builder.append(_propertyMethodSafeAccess_2, "\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("return ");
          String _name_7 = property.getName();
          _builder.append(_name_7, "\t");
          _builder.append(".setValue(ed, value);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("public void ");
          String _propertyMethodSet_1 = GenerateCategoryBeans.this.propertyMethodSet(property);
          _builder.append(_propertyMethodSet_1);
          _builder.append("(");
          String _name_8 = property.getReferenceType().getName();
          _builder.append(_name_8);
          _builder.append(" value) {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _propertyMethodSafeAccess_3 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
          _builder.append(_propertyMethodSafeAccess_3, "\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _name_9 = property.getName();
          _builder.append(_name_9, "\t");
          _builder.append(".setValue(value);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("public BeanPropertyReference<");
          String _name_10 = property.getReferenceType().getName();
          _builder.append(_name_10);
          _builder.append("> ");
          String _propertyMethodGet_1 = GenerateCategoryBeans.this.propertyMethodGet(property);
          _builder.append(_propertyMethodGet_1);
          _builder.append("Bean() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _propertyMethodSafeAccess_4 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
          _builder.append(_propertyMethodSafeAccess_4, "\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("return ");
          String _name_11 = property.getName();
          _builder.append(_name_11, "\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          return _builder;
        } else {
          ATypeDefinition _referenceType_1 = property.getReferenceType();
          AProperty referencedProperty = ((AProperty) _referenceType_1);
          Class<?> referencedPropertyType = GenerateCategoryBeans.getReferencePropertyType(referencedProperty);
          importManager.register(referencedPropertyType);
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("private BeanPropertyReference<");
          String _simpleName = referencedPropertyType.getSimpleName();
          _builder_1.append(_simpleName);
          _builder_1.append("> ");
          String _name_12 = property.getName();
          _builder_1.append(_name_12);
          _builder_1.append(" = new BeanPropertyReference<>();");
          _builder_1.newLineIfNotEmpty();
          _builder_1.newLine();
          _builder_1.append("private void ");
          String _propertyMethodSafeAccess_5 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
          _builder_1.append(_propertyMethodSafeAccess_5);
          _builder_1.append(" {");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          _builder_1.append("ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) helper.getPropertyInstance(\"");
          String _name_13 = property.getName();
          _builder_1.append(_name_13, "\t");
          _builder_1.append("\");");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          String _name_14 = property.getName();
          _builder_1.append(_name_14, "\t");
          _builder_1.append(".setTypeInstance(propertyInstance);");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("}");
          _builder_1.newLine();
          _builder_1.newLine();
          _builder_1.append("@XmlElement(nillable = true)");
          _builder_1.newLine();
          _builder_1.append("@XmlJavaTypeAdapter(ABeanObjectAdapter.class)");
          _builder_1.newLine();
          _builder_1.append("public ");
          String _simpleName_1 = referencedPropertyType.getSimpleName();
          _builder_1.append(_simpleName_1);
          _builder_1.append(" ");
          String _propertyMethodGet_2 = GenerateCategoryBeans.this.propertyMethodGet(property);
          _builder_1.append(_propertyMethodGet_2);
          _builder_1.append("() {");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          String _propertyMethodSafeAccess_6 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
          _builder_1.append(_propertyMethodSafeAccess_6, "\t");
          _builder_1.append(";");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          _builder_1.append("return ");
          String _name_15 = property.getName();
          _builder_1.append(_name_15, "\t");
          _builder_1.append(".getValue();");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("}");
          _builder_1.newLine();
          _builder_1.newLine();
          _builder_1.append("public Command ");
          String _propertyMethodSet_2 = GenerateCategoryBeans.this.propertyMethodSet(property);
          _builder_1.append(_propertyMethodSet_2);
          _builder_1.append("(EditingDomain ed, ");
          String _simpleName_2 = referencedPropertyType.getSimpleName();
          _builder_1.append(_simpleName_2);
          _builder_1.append(" value) {");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          String _propertyMethodSafeAccess_7 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
          _builder_1.append(_propertyMethodSafeAccess_7, "\t");
          _builder_1.append(";");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          _builder_1.append("return ");
          String _name_16 = property.getName();
          _builder_1.append(_name_16, "\t");
          _builder_1.append(".setValue(ed, value);");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("}");
          _builder_1.newLine();
          _builder_1.newLine();
          _builder_1.append("public void ");
          String _propertyMethodSet_3 = GenerateCategoryBeans.this.propertyMethodSet(property);
          _builder_1.append(_propertyMethodSet_3);
          _builder_1.append("(");
          String _simpleName_3 = referencedPropertyType.getSimpleName();
          _builder_1.append(_simpleName_3);
          _builder_1.append(" value) {");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          String _propertyMethodSafeAccess_8 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
          _builder_1.append(_propertyMethodSafeAccess_8, "\t");
          _builder_1.append(";");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          String _name_17 = property.getName();
          _builder_1.append(_name_17, "\t");
          _builder_1.append(".setValue(value);");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("}");
          _builder_1.newLine();
          _builder_1.newLine();
          _builder_1.append("public BeanPropertyReference<");
          String _simpleName_4 = referencedPropertyType.getSimpleName();
          _builder_1.append(_simpleName_4);
          _builder_1.append("> ");
          String _propertyMethodGet_3 = GenerateCategoryBeans.this.propertyMethodGet(property);
          _builder_1.append(_propertyMethodGet_3);
          _builder_1.append("Bean() {");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          String _propertyMethodSafeAccess_9 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
          _builder_1.append(_propertyMethodSafeAccess_9, "\t");
          _builder_1.append(";");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          _builder_1.append("return ");
          String _name_18 = property.getName();
          _builder_1.append(_name_18, "\t");
          _builder_1.append(";");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("}");
          _builder_1.newLine();
          return _builder_1;
        }
      }
      
      @Override
      public CharSequence caseEReferenceProperty(final EReferenceProperty property) {
        importManager.register(CategoryAssignment.class);
        importManager.register(Command.class);
        importManager.register(EditingDomain.class);
        importManager.register(BeanPropertyEReference.class);
        importManager.register(EReferencePropertyInstance.class);
        final String typeClass = new ExternalGenModelHelper().getEObjectClass(property);
        final boolean genPackageSpecified = (typeClass != null);
        if (genPackageSpecified) {
          importManager.register(typeClass);
        } else {
          importManager.register(EObject.class);
        }
        StringConcatenation _builder = new StringConcatenation();
        {
          if (genPackageSpecified) {
            String _name = property.getReferenceType().getName();
            _builder.append(_name);
          } else {
            _builder.append("EObject");
          }
        }
        final String referenceType = _builder.toString();
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("private BeanPropertyEReference<");
        _builder_1.append(referenceType);
        _builder_1.append("> ");
        String _name_1 = property.getName();
        _builder_1.append(_name_1);
        _builder_1.append(" = new BeanPropertyEReference<");
        _builder_1.append(referenceType);
        _builder_1.append(">();");
        _builder_1.newLineIfNotEmpty();
        _builder_1.newLine();
        CharSequence _declareSafeAccessAttributeMethod = GenerateCategoryBeans.this.declareSafeAccessAttributeMethod(property, EReferencePropertyInstance.class);
        _builder_1.append(_declareSafeAccessAttributeMethod);
        _builder_1.newLineIfNotEmpty();
        _builder_1.newLine();
        _builder_1.append("public Command ");
        String _propertyMethodSet = GenerateCategoryBeans.this.propertyMethodSet(property);
        _builder_1.append(_propertyMethodSet);
        _builder_1.append("(EditingDomain ed, ");
        _builder_1.append(referenceType);
        _builder_1.append(" value) {");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("\t");
        String _propertyMethodSafeAccess = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder_1.append(_propertyMethodSafeAccess, "\t");
        _builder_1.append(";");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("\t");
        _builder_1.append("return this.");
        String _name_2 = property.getName();
        _builder_1.append(_name_2, "\t");
        _builder_1.append(".setValue(ed, value);");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.newLine();
        _builder_1.append("public void ");
        String _propertyMethodSet_1 = GenerateCategoryBeans.this.propertyMethodSet(property);
        _builder_1.append(_propertyMethodSet_1);
        _builder_1.append("(");
        _builder_1.append(referenceType);
        _builder_1.append(" value) {");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("\t");
        String _propertyMethodSafeAccess_1 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder_1.append(_propertyMethodSafeAccess_1, "\t");
        _builder_1.append(";");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("\t");
        _builder_1.append("this.");
        String _name_3 = property.getName();
        _builder_1.append(_name_3, "\t");
        _builder_1.append(".setValue(value);");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.newLine();
        _builder_1.append("public ");
        _builder_1.append(referenceType);
        _builder_1.append(" ");
        String _propertyMethodGet = GenerateCategoryBeans.this.propertyMethodGet(property);
        _builder_1.append(_propertyMethodGet);
        _builder_1.append("() {");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("\t");
        String _propertyMethodSafeAccess_2 = GenerateCategoryBeans.this.propertyMethodSafeAccess(property);
        _builder_1.append(_propertyMethodSafeAccess_2, "\t");
        _builder_1.append(";");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("\t");
        _builder_1.append("return ");
        String _name_4 = property.getName();
        _builder_1.append(_name_4, "\t");
        _builder_1.append(".getValue();");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.newLine();
        return _builder_1;
      }
    }.doSwitch(property);
  }
  
  protected String propertyMethodGet(final AProperty property) {
    String _firstUpper = StringExtensions.toFirstUpper(property.getName());
    return ("get" + _firstUpper);
  }
  
  protected String propertyMethodGetBean(final AProperty property) {
    String _firstUpper = StringExtensions.toFirstUpper(property.getName());
    String _plus = ("get" + _firstUpper);
    return (_plus + "Bean");
  }
  
  protected String propertyMethodSet(final AProperty property) {
    String _firstUpper = StringExtensions.toFirstUpper(property.getName());
    return ("set" + _firstUpper);
  }
  
  protected String propertyMethodCreate(final AProperty property) {
    String _firstUpper = StringExtensions.toFirstUpper(property.getName());
    return ("create" + _firstUpper);
  }
  
  protected String propertyMethodIsSet(final AProperty property) {
    String _firstUpper = StringExtensions.toFirstUpper(property.getName());
    return ("isSet" + _firstUpper);
  }
  
  protected String propertyMethodSafeAccess(final AProperty property) {
    String _firstUpper = StringExtensions.toFirstUpper(property.getName());
    String _plus = ("safeAccess" + _firstUpper);
    return (_plus + "()");
  }
  
  protected String propertyMethodSafeAccessBean(final AProperty property) {
    String _firstUpper = StringExtensions.toFirstUpper(property.getName());
    String _plus = ("safeAccess" + _firstUpper);
    return (_plus + "Bean()");
  }
  
  /**
   * This method hands back the class type
   */
  public static Class<?> getReferencePropertyType(final AProperty refProperty) {
    return new PropertydefinitionsSwitch<Class<?>>() {
      @Override
      public Class<?> caseFloatProperty(final FloatProperty object) {
        return BeanPropertyFloat.class;
      }
      
      @Override
      public Class<?> caseBooleanProperty(final BooleanProperty object) {
        return BeanPropertyBoolean.class;
      }
      
      @Override
      public Class<?> caseIntProperty(final IntProperty object) {
        return BeanPropertyInt.class;
      }
      
      @Override
      public Class<?> caseEnumProperty(final EnumProperty object) {
        return BeanPropertyEnum.class;
      }
      
      @Override
      public Class<?> caseStringProperty(final StringProperty object) {
        return BeanPropertyString.class;
      }
      
      @Override
      public Class<?> caseResourceProperty(final ResourceProperty object) {
        return BeanPropertyResource.class;
      }
    }.doSwitch(refProperty);
  }
}
