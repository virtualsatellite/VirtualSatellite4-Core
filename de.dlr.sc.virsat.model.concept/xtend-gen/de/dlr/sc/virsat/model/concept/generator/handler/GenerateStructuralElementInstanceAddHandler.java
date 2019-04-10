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
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import java.util.Set;
import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class GenerateStructuralElementInstanceAddHandler extends AGeneratorGapGenerator<StructuralElement> {
  @Override
  protected String getPackage(final Concept concept) {
    String _name = concept.getName();
    return (_name + ".ui.handler");
  }
  
  public static String getConcreteClassName(final StructuralElement structuralElement) {
    String _firstUpper = StringExtensions.toFirstUpper(structuralElement.getName());
    String _plus = ("AddStructuralElementInstance" + _firstUpper);
    return (_plus + "Handler");
  }
  
  public static String getAbstractClassName(final StructuralElement structuralElement) {
    String _firstUpper = StringExtensions.toFirstUpper(structuralElement.getName());
    String _plus = ("AAddStructuralElementInstance" + _firstUpper);
    return (_plus + "Handler");
  }
  
  @Override
  public String createConcreteClassFileName(final Concept concept, final StructuralElement structuralElement) {
    String _name = concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".ui/src/");
    String _packageFolder = this.getPackageFolder(concept);
    String _plus_2 = (_plus_1 + _packageFolder);
    String _plus_3 = (_plus_2 + "/");
    String _concreteClassName = GenerateStructuralElementInstanceAddHandler.getConcreteClassName(structuralElement);
    String _plus_4 = (_plus_3 + _concreteClassName);
    return (_plus_4 + ".java");
  }
  
  @Override
  public String createAbstractClassFileName(final Concept concept, final StructuralElement structuralElement) {
    String _name = concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".ui/src-gen/");
    String _packageFolder = this.getPackageFolder(concept);
    String _plus_2 = (_plus_1 + _packageFolder);
    String _plus_3 = (_plus_2 + "/");
    String _abstractClassName = GenerateStructuralElementInstanceAddHandler.getAbstractClassName(structuralElement);
    String _plus_4 = (_plus_3 + _abstractClassName);
    return (_plus_4 + ".java");
  }
  
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
  public CharSequence declareImports(final ImportManager importManager, final Concept concept, final StructuralElement conceptPart, final String conceptPackage) {
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
  protected CharSequence declareClass(final Concept concept, final StructuralElement structuralElement, final ImportManager manager) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateClassHeader = ConceptGeneratorUtil.generateClassHeader(structuralElement);
    _builder.append(_generateClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public class ");
    String _concreteClassName = GenerateStructuralElementInstanceAddHandler.getConcreteClassName(structuralElement);
    _builder.append(_concreteClassName);
    _builder.append(" extends ");
    String _abstractClassName = GenerateStructuralElementInstanceAddHandler.getAbstractClassName(structuralElement);
    _builder.append(_abstractClassName);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  @Override
  public CharSequence declareAClass(final Concept concept, final StructuralElement structuralElement, final ImportManager importManager) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import de.dlr.sc.virsat.project.ui.transactional.handler.ATransactionalAddStructuralElementHandler;");
    _builder.newLine();
    _builder.newLine();
    CharSequence _generateAClassHeader = ConceptGeneratorUtil.generateAClassHeader(structuralElement);
    _builder.append(_generateAClassHeader);
    _builder.newLineIfNotEmpty();
    _builder.append("public abstract class  ");
    String _abstractClassName = GenerateStructuralElementInstanceAddHandler.getAbstractClassName(structuralElement);
    _builder.append(_abstractClassName);
    _builder.append(" extends ATransactionalAddStructuralElementHandler implements ");
    String _serialize = importManager.serialize(IHandler.class);
    _builder.append(_serialize);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected String getConceptName() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return \"");
    String _name = concept.getName();
    _builder.append(_name, "\t\t");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected String getStructuralElementName() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return \"");
    String _name_1 = structuralElement.getName();
    _builder.append(_name_1, "\t\t");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
