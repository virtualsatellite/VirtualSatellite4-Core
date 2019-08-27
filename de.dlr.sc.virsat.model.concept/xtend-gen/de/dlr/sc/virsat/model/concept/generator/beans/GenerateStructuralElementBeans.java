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

import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator;
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider;
import de.dlr.sc.virsat.model.concept.generator.ImportManager;
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil;
import de.dlr.sc.virsat.model.concept.types.factory.BeanRegistry;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import java.util.Set;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * This class is the generator for the StructuralElement beans of our model extension.
 * The beans will give easier access to the model of StructuralElements and their underly Category Assignemtns
 * Furthermore it will provide strong typing
 */
@SuppressWarnings("all")
public class GenerateStructuralElementBeans extends AGeneratorGapGenerator<StructuralElement> {
  public static String getConcreteClassName(final StructuralElement typeDefinition) {
    return StringExtensions.toFirstUpper(typeDefinition.getName());
  }
  
  public static String getAbstractClassName(final StructuralElement typeDefinition) {
    String _firstUpper = StringExtensions.toFirstUpper(typeDefinition.getName());
    return ("A" + _firstUpper);
  }
  
  @Override
  public String createConcreteClassFileName(final Concept concept, final StructuralElement conceptPart) {
    String _packageFolder = this.getPackageFolder(concept);
    String _plus = (_packageFolder + "/");
    String _concreteClassName = GenerateStructuralElementBeans.getConcreteClassName(conceptPart);
    String _plus_1 = (_plus + _concreteClassName);
    return (_plus_1 + ".java");
  }
  
  @Override
  public String createAbstractClassFileName(final Concept concept, final StructuralElement conceptPart) {
    String _packageFolder = this.getPackageFolder(concept);
    String _plus = (_packageFolder + "/");
    String _abstractClassName = GenerateStructuralElementBeans.getAbstractClassName(conceptPart);
    String _plus_1 = (_plus + _abstractClassName);
    return (_plus_1 + ".java");
  }
  
  @Override
  protected String getPackage(final Concept concept) {
    String _name = concept.getName();
    String _plus = (_name + ".");
    return (_plus + GenerateStructuralElementBeans.PACKAGE_FOLDER);
  }
  
  public static final String PACKAGE_FOLDER = BeanRegistry.BEAN_PACKAGE_NAME;
  
  @Override
  public void serializeModel(final Concept concept, final IFileSystemAccess fsa) {
    TreeIterator<Object> iterator = EcoreUtil.<Object>getAllContents(concept, true);
    final Procedure1<Object> _function = (Object it) -> {
      if ((it instanceof StructuralElement)) {
        CharSequence fileOutputAClass = this.createAbstractClass(concept, ((StructuralElement)it));
        fsa.generateFile(this.createAbstractClassFileName(concept, ((StructuralElement)it)), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);
        CharSequence fileOutputClass = this.createConcreteClass(concept, ((StructuralElement)it));
        fsa.generateFile(this.createConcreteClassFileName(concept, ((StructuralElement)it)), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
      }
    };
    IteratorExtensions.<Object>forEach(iterator, _function);
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
  public CharSequence declareImports(final ImportManager importManager, final Concept concept, final StructuralElement conceptPart, final String conceptPackage) {
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
  public CharSequence declareAClass(final Concept concept, final StructuralElement se, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register(ABeanStructuralElementInstance.class);
    _builder.newLineIfNotEmpty();
    importManager.register(IBeanStructuralElementInstance.class);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Class Declaration");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.newLine();
    CharSequence _generateAClassHeader = ConceptGeneratorUtil.generateAClassHeader(se);
    _builder.append(_generateAClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public abstract class A");
    String _firstUpper = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper);
    _builder.append(" extends ABeanStructuralElementInstance implements IBeanStructuralElementInstance {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    final String fullQualifiedSeId = ActiveConceptHelper.getFullQualifiedId(se);
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("public static final String FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME = \"");
    _builder.append(fullQualifiedSeId, "\t");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("* Call this method to get the full qualified name of the underlying Structural Element");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("* @return The FQN of the StructuralElement as String");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String getFullQualifiedSturcturalElementName() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _declareConstructor = this.declareConstructor(se, importManager);
    _builder.append(_declareConstructor, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  @Override
  public CharSequence declareClass(final Concept concept, final StructuralElement se, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register(StructuralElementInstance.class);
    _builder.newLineIfNotEmpty();
    importManager.register(Concept.class);
    _builder.newLineIfNotEmpty();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Class Declaration");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.newLine();
    CharSequence _generateClassHeader = ConceptGeneratorUtil.generateClassHeader(se);
    _builder.append(_generateClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public class ");
    String _firstUpper = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper);
    _builder.append(" extends A");
    String _firstUpper_1 = StringExtensions.toFirstUpper(se.getName());
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
    String _firstUpper_2 = StringExtensions.toFirstUpper(se.getName());
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
    _builder.append(" \t ");
    _builder.append("* Constructor of Concept Class");
    _builder.newLine();
    _builder.append(" \t ");
    _builder.append("* @param concept The concept from where to initialize");
    _builder.newLine();
    _builder.append(" \t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _firstUpper_3 = StringExtensions.toFirstUpper(se.getName());
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
    _builder.append(" \t ");
    _builder.append("* Constructor of Concept Class that can be initialized manually by a given StructuralElementInstance");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @param sei The StructuralElementInstance to be used for background initialization of the StructuralElementInstance bean");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _firstUpper_4 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_4, "\t");
    _builder.append("(StructuralElementInstance sei) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super(sei);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Method to create the constructor of the java category bean
   */
  protected CharSequence declareConstructor(final StructuralElement se, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    importManager.register(StructuralElementInstance.class);
    _builder.newLineIfNotEmpty();
    importManager.register(StructuralElement.class);
    _builder.newLineIfNotEmpty();
    importManager.register(StructuralFactory.class);
    _builder.newLineIfNotEmpty();
    importManager.register(ActiveConceptHelper.class);
    _builder.newLineIfNotEmpty();
    importManager.register(Concept.class);
    _builder.newLineIfNotEmpty();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.append("// * Class Constructors");
    _builder.newLine();
    _builder.append("// *****************************************************************");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Constructor of Concept Class");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public A");
    String _firstUpper = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper);
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Constructor of Concept Class");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param concept The concept from where to initialize");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public A");
    String _firstUpper_1 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_1);
    _builder.append("(Concept concept) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("StructuralElement seFromActiveConcept = ActiveConceptHelper.getStructuralElement(concept, \"");
    String _name = se.getName();
    _builder.append(_name, "\t");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("sei.setType(seFromActiveConcept);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("setStructuralElementInstance(sei);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Constructor of Concept Class that can be initialized manually by a given StructuralElementInstance");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param sei The StructuralElementInstance to be used for background initialization of the StructuralElementInstance bean");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public A");
    String _firstUpper_2 = StringExtensions.toFirstUpper(se.getName());
    _builder.append(_firstUpper_2);
    _builder.append("(StructuralElementInstance sei) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("setStructuralElementInstance(sei);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
}
