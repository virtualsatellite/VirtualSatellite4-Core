/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.plugin;

import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider;
import de.dlr.sc.virsat.model.concept.generator.beans.GenerateCategoryBeans;
import de.dlr.sc.virsat.model.concept.generator.beans.GenerateStructuralElementBeans;
import de.dlr.sc.virsat.model.concept.generator.migrator.GenerateMigrator;
import de.dlr.sc.virsat.model.concept.generator.plugin.PluginXmlReader;
import de.dlr.sc.virsat.model.concept.generator.validator.GenerateValidator;
import de.dlr.sc.virsat.model.concept.generator.xmi.GenerateConceptXmi;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class GeneratePluginXml {
  @Accessors
  private PluginXmlReader pluginXmlReader;
  
  public void serializeModel(final Concept concept, final PluginXmlReader pluginXmlReader, final IFileSystemAccess fsa) {
    this.pluginXmlReader = pluginXmlReader;
    final String fileName = "plugin.xml";
    final String plugin = concept.getName();
    CharSequence fileOutput = this.createXml(concept, plugin);
    final String Folder = (("../../" + plugin) + "/");
    fsa.generateFile((Folder + fileName), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutput);
  }
  
  public void serializeModelDeprecatedValidator(final Concept concept, final PluginXmlReader pluginXmlReader, final IFileSystemAccess fsa) {
    this.pluginXmlReader = pluginXmlReader;
    final String fileName = "plugin.xml";
    final String plugin = concept.getName();
    CharSequence fileOutput = this.createXmlDeprecatedValidator(concept, plugin);
    final String Folder = (("../../" + plugin) + "/");
    fsa.generateFile((Folder + fileName), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutput);
  }
  
  public CharSequence createXml(final Concept concept, final String plugin) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("<?eclipse version=\"3.4\"?>");
    _builder.newLine();
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _declareConceptRegistryExtension = this.declareConceptRegistryExtension(concept);
    _builder.append(_declareConceptRegistryExtension, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _declareDvlmValidatorExtension = this.declareDvlmValidatorExtension(concept);
    _builder.append(_declareDvlmValidatorExtension, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _declareConceptCaBeanRegistration = this.declareConceptCaBeanRegistration(concept);
    _builder.append(_declareConceptCaBeanRegistration, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _declareConceptSeiBeanRegistration = this.declareConceptSeiBeanRegistration(concept);
    _builder.append(_declareConceptSeiBeanRegistration, "\t");
    _builder.newLineIfNotEmpty();
    CharSequence _declareConceptMigratorExtensions = this.declareConceptMigratorExtensions(concept, plugin);
    _builder.append(_declareConceptMigratorExtensions);
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<!-- ");
    _builder.append(PluginXmlReader.PR_START, "\t");
    _builder.append(" -->");
    _builder.newLineIfNotEmpty();
    String _trim = this.pluginXmlReader.extractProtectedRegion(1).trim();
    _builder.append(_trim);
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<!-- ");
    _builder.append(PluginXmlReader.PR_END, "\t");
    _builder.append(" -->");
    _builder.newLineIfNotEmpty();
    _builder.append("</plugin>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence createXmlDeprecatedValidator(final Concept concept, final String plugin) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("<?eclipse version=\"3.4\"?>");
    _builder.newLine();
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _declareConceptRegistryExtension = this.declareConceptRegistryExtension(concept);
    _builder.append(_declareConceptRegistryExtension, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _declareDvlmValidatorExtension = this.declareDvlmValidatorExtension(concept);
    _builder.append(_declareDvlmValidatorExtension, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _declareDvlmDeprecatedValidatorExtension = this.declareDvlmDeprecatedValidatorExtension(concept);
    _builder.append(_declareDvlmDeprecatedValidatorExtension, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _declareConceptCaBeanRegistration = this.declareConceptCaBeanRegistration(concept);
    _builder.append(_declareConceptCaBeanRegistration, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _declareConceptSeiBeanRegistration = this.declareConceptSeiBeanRegistration(concept);
    _builder.append(_declareConceptSeiBeanRegistration, "\t");
    _builder.newLineIfNotEmpty();
    CharSequence _declareConceptMigratorExtensions = this.declareConceptMigratorExtensions(concept, plugin);
    _builder.append(_declareConceptMigratorExtensions);
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<!-- ");
    _builder.append(PluginXmlReader.PR_START, "\t");
    _builder.append(" -->");
    _builder.newLineIfNotEmpty();
    String _trim = this.pluginXmlReader.extractProtectedRegion(1).trim();
    _builder.append(_trim);
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<!-- ");
    _builder.append(PluginXmlReader.PR_END, "\t");
    _builder.append(" -->");
    _builder.newLineIfNotEmpty();
    _builder.append("</plugin>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareConceptRegistryExtension(final Concept concept) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<extension point=\"de.dlr.sc.virsat.model.Concept\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<concept");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("id=\"");
    String _name = concept.getName();
    _builder.append(_name, "\t\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("version=\"");
    String _version = concept.getVersion();
    _builder.append(_version, "\t\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("xmi=\"concept/concept.xmi\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</concept>");
    _builder.newLine();
    _builder.append("</extension>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareDvlmValidatorExtension(final Concept concept) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<extension point=\"de.dlr.sc.virsat.model.DvlmValidator\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<dvlmValidator>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<seiValidator");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("id=\"");
    String _name = concept.getName();
    _builder.append(_name, "\t\t\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("class=\"");
    String _name_1 = concept.getName();
    _builder.append(_name_1, "\t\t\t");
    _builder.append(".");
    _builder.append(GenerateValidator.PACKAGE_FOLDER, "\t\t\t");
    _builder.append(".");
    String _validatorName = GenerateValidator.getValidatorName(concept);
    _builder.append(_validatorName, "\t\t\t");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("</seiValidator>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</dvlmValidator>");
    _builder.newLine();
    _builder.append("</extension>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareDvlmDeprecatedValidatorExtension(final Concept concept) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<extension point=\"de.dlr.sc.virsat.model.DvlmValidator\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<dvlmValidator>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<seiValidator");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("id=\"");
    String _name = concept.getName();
    _builder.append(_name, "\t\t\t");
    _builder.append(".deprecated\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("class=\"");
    String _name_1 = concept.getName();
    _builder.append(_name_1, "\t\t\t");
    _builder.append(".");
    _builder.append(GenerateValidator.PACKAGE_FOLDER, "\t\t\t");
    _builder.append(".StructuralElementInstanceValidator\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("</seiValidator>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</dvlmValidator>");
    _builder.newLine();
    _builder.append("</extension>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareConceptMigratorExtensions(final Concept concept, final String plugin) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    _builder.append("<!-- ");
    _builder.append(PluginXmlReader.PR_START, "\t");
    _builder.append(" -->");
    _builder.newLineIfNotEmpty();
    final String protectedRegion = this.pluginXmlReader.init(plugin).extractProtectedRegion(0).trim();
    _builder.newLineIfNotEmpty();
    final String newMigrator = String.valueOf(this.declareConceptMigratorExtension(concept)).trim();
    _builder.newLineIfNotEmpty();
    _builder.append(protectedRegion);
    _builder.newLineIfNotEmpty();
    String _xifexpression = null;
    if (((protectedRegion == null) || (!protectedRegion.contains(newMigrator)))) {
      _xifexpression = newMigrator;
    }
    _builder.append(_xifexpression);
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<!-- ");
    _builder.append(PluginXmlReader.PR_END, "\t");
    _builder.append(" -->");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence declareConceptMigratorExtension(final Concept concept) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<extension point=\"de.dlr.sc.virsat.model.edit.ConceptMigrator\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<migrator");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("id=\"");
    String _name = concept.getName();
    _builder.append(_name, "\t\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("version=\"");
    String _version = concept.getVersion();
    _builder.append(_version, "\t\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("class=\"");
    String _name_1 = concept.getName();
    _builder.append(_name_1, "\t\t");
    _builder.append(".");
    _builder.append(GenerateMigrator.PACKAGE_FOLDER, "\t\t");
    _builder.append(".Migrator");
    String _replace = concept.getVersion().replace(".", "v");
    _builder.append(_replace, "\t\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("xmi=\"concept/");
    String _conceptWithVersionName = GenerateConceptXmi.getConceptWithVersionName(concept);
    _builder.append(_conceptWithVersionName, "\t\t");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("</migrator>");
    _builder.newLine();
    _builder.append("</extension>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareConceptCaBeanRegistration(final Concept concept) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isEmpty = concept.getNonAbstractCategories().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("<extension point=\"de.dlr.sc.virsat.model.edit.ConceptTypeFactoryCaBeanRegistration\">");
        _builder.newLine();
        {
          EList<Category> _nonAbstractCategories = concept.getNonAbstractCategories();
          for(final Category category : _nonAbstractCategories) {
            _builder.append("\t");
            _builder.append("<CategoryAssignmentBean");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("id=\"");
            String _fullQualifiedName = category.getFullQualifiedName();
            _builder.append(_fullQualifiedName, "\t\t");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("caBean=\"");
            String _name = concept.getName();
            _builder.append(_name, "\t\t");
            _builder.append(".model.");
            String _concreteClassName = GenerateCategoryBeans.getConcreteClassName(category);
            _builder.append(_concreteClassName, "\t\t");
            _builder.append("\">");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("</CategoryAssignmentBean>");
            _builder.newLine();
          }
        }
        _builder.append("</extension>");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence declareConceptSeiBeanRegistration(final Concept concept) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isEmpty = concept.getStructuralElements().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("<extension point=\"de.dlr.sc.virsat.model.edit.ConceptTypeFactorySeiBeanRegistration\">");
        _builder.newLine();
        {
          EList<StructuralElement> _structuralElements = concept.getStructuralElements();
          for(final StructuralElement structuralElement : _structuralElements) {
            _builder.append("\t");
            _builder.append("<StructuralElementInstanceBean");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("id=\"");
            String _fullQualifiedName = structuralElement.getFullQualifiedName();
            _builder.append(_fullQualifiedName, "\t\t");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("seiBean=\"");
            String _name = concept.getName();
            _builder.append(_name, "\t\t");
            _builder.append(".model.");
            String _concreteClassName = GenerateStructuralElementBeans.getConcreteClassName(structuralElement);
            _builder.append(_concreteClassName, "\t\t");
            _builder.append("\">");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("</StructuralElementInstanceBean>");
            _builder.newLine();
          }
        }
        _builder.append("</extension>");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  @Pure
  public PluginXmlReader getPluginXmlReader() {
    return this.pluginXmlReader;
  }
  
  public void setPluginXmlReader(final PluginXmlReader pluginXmlReader) {
    this.pluginXmlReader = pluginXmlReader;
  }
}
