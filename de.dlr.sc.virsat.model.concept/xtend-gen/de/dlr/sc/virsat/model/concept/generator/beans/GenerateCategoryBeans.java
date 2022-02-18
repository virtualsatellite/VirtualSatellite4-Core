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
import de.dlr.sc.virsat.model.concept.types.factory.BeanRegistry;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.util.PropertydefinitionsSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
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
  private static final String IAUTOMATIC_VERIFICATION_FQN = "de.dlr.sc.virsat.model.extension.requirements.verification.build.steps.IAutomaticVerification";
  
  private static final String REQUIREMENT_FQN = "de.dlr.sc.virsat.model.extension.requirements.model.Requirement";
  
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
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field XmlRootElement is undefined"
      + "\nThe method or field XmlAccessorType is undefined"
      + "\nThe method or field XmlAccessType is undefined");
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
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field XmlType is undefined");
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
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field XmlElement is undefined"
      + "\nThe method or field XmlElement is undefined"
      + "\nThe method or field XmlElement is undefined"
      + "\nThe method or field XmlElement is undefined"
      + "\nThe method or field XmlElement is undefined"
      + "\nThe method or field XmlElement is undefined"
      + "\nThe method or field XmlElement is undefined"
      + "\nThe method or field XmlElement is undefined");
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
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field XmlElement is undefined"
      + "\nThe method or field XmlElement is undefined"
      + "\nThe method or field XmlElement is undefined"
      + "\nThe method or field XmlElement is undefined"
      + "\nThe method or field XmlElement is undefined"
      + "\nThe method or field XmlElement is undefined"
      + "\nThe method or field XmlElement is undefined"
      + "\nThe method or field XmlJavaTypeAdapter is undefined"
      + "\nThe method or field XmlElement is undefined");
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
