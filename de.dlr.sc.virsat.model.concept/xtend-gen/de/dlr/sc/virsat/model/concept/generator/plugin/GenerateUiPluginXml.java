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
import de.dlr.sc.virsat.model.concept.generator.handler.GenerateCategoryAddHandler;
import de.dlr.sc.virsat.model.concept.generator.handler.GenerateStructuralElementInstanceAddHandler;
import de.dlr.sc.virsat.model.concept.generator.plugin.GenerateConceptImages;
import de.dlr.sc.virsat.model.concept.generator.plugin.PluginXmlReader;
import de.dlr.sc.virsat.model.concept.generator.snippets.GenerateCategoryUiSnippetArrayTable;
import de.dlr.sc.virsat.model.concept.generator.snippets.GenerateCategoryUiSnippetPropertySection;
import de.dlr.sc.virsat.model.concept.generator.snippets.GenerateCategoryUiSnippetTable;
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.DynamicArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class GenerateUiPluginXml {
  @Accessors
  private PluginXmlReader pluginXmlReader;
  
  public void serializeModel(final Concept concept, final PluginXmlReader pluginXmlReader, final IFileSystemAccess fsa) {
    this.pluginXmlReader = pluginXmlReader;
    final String fileName = "plugin.xml";
    String _name = concept.getName();
    final String uiPlugin = (_name + ".ui/");
    CharSequence fileOutput = this.createUiXml(concept, uiPlugin);
    final String uiFolder = (("../../" + uiPlugin) + "/");
    fsa.generateFile((uiFolder + fileName), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutput);
  }
  
  public CharSequence createUiXml(final Concept concept, final String uiPlugin) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("<?eclipse version=\"3.4\"?>");
    _builder.newLine();
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<extension point=\"org.eclipse.core.expressions.propertyTesters\">");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("<propertyTester");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("id=\"");
    String _name = concept.getName();
    _builder.append(_name, "\t    ");
    _builder.append(".ui.propertyTester.conceptEnabledTester\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t    ");
    _builder.append("type=\"org.eclipse.emf.ecore.EObject\"");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("namespace=\"");
    String _name_1 = concept.getName();
    _builder.append(_name_1, "\t    ");
    _builder.append(".ui.propertyTester\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t    ");
    _builder.append("properties=\"conceptEnabled\"");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("class=\"");
    String _name_2 = concept.getName();
    _builder.append(_name_2, "\t    ");
    _builder.append(".propertyTester.ConceptEnabledTester\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t  ");
    _builder.append("</propertyTester>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</extension>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<extension point=\"de.dlr.sc.virsat.model.edit.ConceptImageContribution\">");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _declareConceptImageExtension = this.declareConceptImageExtension(concept);
    _builder.append(_declareConceptImageExtension, "\t");
    _builder.newLineIfNotEmpty();
    {
      EList<Category> _categories = concept.getCategories();
      for(final Category category : _categories) {
        _builder.append("\t");
        CharSequence _declareConceptImageExtension_1 = this.declareConceptImageExtension(category);
        _builder.append(_declareConceptImageExtension_1, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<StructuralElement> _structuralElements = concept.getStructuralElements();
      for(final StructuralElement structuralElement : _structuralElements) {
        _builder.append("\t");
        CharSequence _declareConceptImageExtension_2 = this.declareConceptImageExtension(structuralElement);
        _builder.append(_declareConceptImageExtension_2, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("</extension>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<extension point=\"org.eclipse.ui.commands\">");
    _builder.newLine();
    {
      EList<Category> _nonAbstractCategories = concept.getNonAbstractCategories();
      for(final Category category_1 : _nonAbstractCategories) {
        _builder.append("\t");
        CharSequence _declareCommands = this.declareCommands(concept, category_1);
        _builder.append(_declareCommands, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<StructuralElement> _structuralElements_1 = concept.getStructuralElements();
      for(final StructuralElement structuralElement_1 : _structuralElements_1) {
        _builder.append("\t");
        CharSequence _declareCommands_1 = this.declareCommands(concept, structuralElement_1);
        _builder.append(_declareCommands_1, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("</extension>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<extension point=\"org.eclipse.ui.commandImages\">");
    _builder.newLine();
    {
      EList<Category> _nonAbstractCategories_1 = concept.getNonAbstractCategories();
      for(final Category category_2 : _nonAbstractCategories_1) {
        _builder.append("\t");
        CharSequence _declareCommandsImages = this.declareCommandsImages(concept, category_2);
        _builder.append(_declareCommandsImages, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<StructuralElement> _structuralElements_2 = concept.getStructuralElements();
      for(final StructuralElement structuralElement_2 : _structuralElements_2) {
        _builder.append("\t");
        CharSequence _declareCommandsImages_1 = this.declareCommandsImages(concept, structuralElement_2);
        _builder.append(_declareCommandsImages_1, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("</extension>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<extension point=\"org.eclipse.ui.handlers\">");
    _builder.newLine();
    {
      EList<Category> _nonAbstractCategories_2 = concept.getNonAbstractCategories();
      for(final Category category_3 : _nonAbstractCategories_2) {
        _builder.append("\t");
        CharSequence _declareHandler = this.declareHandler(concept, category_3);
        _builder.append(_declareHandler, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<StructuralElement> _structuralElements_3 = concept.getStructuralElements();
      for(final StructuralElement structuralElement_3 : _structuralElements_3) {
        _builder.append("\t");
        CharSequence _declareHandler_1 = this.declareHandler(concept, structuralElement_3);
        _builder.append(_declareHandler_1, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("</extension>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<extension point=\"org.eclipse.ui.menus\">");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _declareAddConceptSubMenuMenuContributions = this.declareAddConceptSubMenuMenuContributions(concept);
    _builder.append(_declareAddConceptSubMenuMenuContributions, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("<menuContribution allPopups=\"false\"");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("locationURI=\"popup:");
    String _name_3 = concept.getName();
    _builder.append(_name_3, "\t\t\t");
    _builder.append(".menu?after=categories\">");
    _builder.newLineIfNotEmpty();
    {
      EList<Category> _nonAbstractCategories_3 = concept.getNonAbstractCategories();
      for(final Category category_4 : _nonAbstractCategories_3) {
        _builder.append("\t\t");
        CharSequence _declareAddCategoryMenuContributions = this.declareAddCategoryMenuContributions(concept, category_4);
        _builder.append(_declareAddCategoryMenuContributions, "\t\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("</menuContribution>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<menuContribution allPopups=\"false\"");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("locationURI=\"popup:");
    String _name_4 = concept.getName();
    _builder.append(_name_4, "\t\t\t");
    _builder.append(".menu?after=structuralElements\">");
    _builder.newLineIfNotEmpty();
    {
      EList<StructuralElement> _structuralElements_4 = concept.getStructuralElements();
      for(final StructuralElement structuralElement_4 : _structuralElements_4) {
        _builder.append("\t\t");
        CharSequence _declareAddStructuralElementMenuContributions = this.declareAddStructuralElementMenuContributions(concept, structuralElement_4);
        _builder.append(_declareAddStructuralElementMenuContributions, "\t\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("</menuContribution>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</extension>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _declareEditorSection = this.declareEditorSection(concept);
    _builder.append(_declareEditorSection, "\t");
    _builder.newLineIfNotEmpty();
    {
      boolean _isEmpty = concept.getNonAbstractCategories().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("\t");
        _builder.append("<extension point=\"de.dlr.sc.virsat.uiengine.ui.EditorUiSnippets\">");
        _builder.newLine();
        {
          EList<Category> _nonAbstractCategories_4 = concept.getNonAbstractCategories();
          for(final Category category_5 : _nonAbstractCategories_4) {
            _builder.append("\t");
            _builder.append("\t");
            CharSequence _declareUiSnippetTables = this.declareUiSnippetTables(concept, category_5);
            _builder.append(_declareUiSnippetTables, "\t\t");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            CharSequence _declareUiSnippetSections = this.declareUiSnippetSections(concept, category_5);
            _builder.append(_declareUiSnippetSections, "\t\t");
            _builder.newLineIfNotEmpty();
            {
              EList<AProperty> _allProperties = category_5.getAllProperties();
              for(final AProperty property : _allProperties) {
                {
                  IArrayModifier _arrayModifier = property.getArrayModifier();
                  boolean _tripleNotEquals = (_arrayModifier != null);
                  if (_tripleNotEquals) {
                    {
                      if (((property.getArrayModifier() instanceof DynamicArrayModifier) && (property instanceof ComposedProperty))) {
                        _builder.append("\t");
                        _builder.append("\t");
                        final Category baseCategory = ((ComposedProperty) property).getType();
                        _builder.newLineIfNotEmpty();
                        {
                          EList<Category> _nonAbstractCategories_5 = concept.getNonAbstractCategories();
                          for(final Category extendingCategory : _nonAbstractCategories_5) {
                            {
                              boolean _isExtensionOf = extendingCategory.isExtensionOf(baseCategory);
                              if (_isExtensionOf) {
                                _builder.append("\t");
                                _builder.append("\t");
                                CharSequence _declareUiSnippetArrayTables = this.declareUiSnippetArrayTables(concept, property, category_5, extendingCategory);
                                _builder.append(_declareUiSnippetArrayTables, "\t\t");
                                _builder.newLineIfNotEmpty();
                              }
                            }
                          }
                        }
                      } else {
                        _builder.append("\t");
                        _builder.append("\t");
                        CharSequence _declareUiSnippetArrayTables_1 = this.declareUiSnippetArrayTables(concept, property, category_5);
                        _builder.append(_declareUiSnippetArrayTables_1, "\t\t");
                        _builder.newLineIfNotEmpty();
                      }
                    }
                  }
                }
              }
            }
          }
        }
        _builder.append("\t");
        _builder.append("</extension>");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("<!-- ");
    _builder.append(PluginXmlReader.PR_START, "\t");
    _builder.append(" -->");
    _builder.newLineIfNotEmpty();
    String _trim = this.pluginXmlReader.init(uiPlugin).extractProtectedRegion(0).trim();
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
  
  public CharSequence declareCommandsImages(final Concept concept, final IQualifiedName qualifiedName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<image");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("commandId=\"");
    String _commandId = this.getCommandId(concept, qualifiedName);
    _builder.append(_commandId, "      ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("disabledIcon=\"resources/icons/");
    String _fileNameDisabled = GenerateConceptImages.getFileNameDisabled(qualifiedName);
    _builder.append(_fileNameDisabled, "      ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("icon=\"resources/icons/");
    String _fileNameStandard = GenerateConceptImages.getFileNameStandard(qualifiedName);
    _builder.append(_fileNameStandard, "      ");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("</image>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareCommands(final Concept concept, final IQualifiedName qualifiedNameObject) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<command");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("id=\"");
    String _commandId = this.getCommandId(concept, qualifiedNameObject);
    _builder.append(_commandId, "\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("name=\"Add ");
    String _name = qualifiedNameObject.getName();
    _builder.append(_name, "\t");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("</command>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareHandler(final Concept concept, final Category category) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<handler");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("class=\"");
    String _name = concept.getName();
    _builder.append(_name, "\t");
    _builder.append(".ui.handler.");
    String _concreteClassName = GenerateCategoryAddHandler.getConcreteClassName(category);
    _builder.append(_concreteClassName, "\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("commandId=\"");
    String _commandId = this.getCommandId(concept, category);
    _builder.append(_commandId, "\t");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("</handler>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareHandler(final Concept concept, final StructuralElement structuralElement) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<handler");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("class=\"");
    String _name = concept.getName();
    _builder.append(_name, "\t");
    _builder.append(".ui.handler.");
    String _concreteClassName = GenerateStructuralElementInstanceAddHandler.getConcreteClassName(structuralElement);
    _builder.append(_concreteClassName, "\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("commandId=\"");
    String _commandId = this.getCommandId(concept, structuralElement);
    _builder.append(_commandId, "\t");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("</handler>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareAddConceptSubMenuMenuContributions(final Concept concept) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<menuContribution");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("allPopups=\"false\"");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("locationURI=\"popup:de.dlr.sc.virsat.project.ui.navigator.menu#PopupMenu?after=concepts\">");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("<menu");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("id=\"");
    String _name = concept.getName();
    _builder.append(_name, "        ");
    _builder.append(".menu\" ");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("label=\"");
    String _readableConceptName = this.getReadableConceptName(concept);
    _builder.append(_readableConceptName, "        ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("icon=\"resources/icons/");
    String _name_1 = concept.getName();
    _builder.append(_name_1, "        ");
    _builder.append(".gif\"");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("tooltip=\"");
    String _description = concept.getDescription();
    _builder.append(_description, "        ");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("<separator");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("name=\"categories\"");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("visible=\"true\">");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("</separator>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<separator");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("name=\"structuralElements\"");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("visible=\"true\">");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("</separator>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<separator");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("name=\"tools\"");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("visible=\"true\">");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("</separator>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<separator");
    _builder.newLine();
    _builder.append("        \t");
    _builder.append("name=\"additions\"");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("visible=\"true\">");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("</separator>");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("<visibleWhen");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("checkEnabled=\"true\">");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<with");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("variable=\"selection\">");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<iterate");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("ifEmpty=\"false\"");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("operator=\"and\">");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<test ");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("property=\"");
    String _name_2 = concept.getName();
    _builder.append(_name_2, "\t\t\t\t\t\t");
    _builder.append(".ui.propertyTester.conceptEnabled\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("value=\"true\" ");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("forcePluginActivation=\"true\">");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("</test>\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("</iterate>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("</with>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</visibleWhen>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</menu>");
    _builder.newLine();
    _builder.append("</menuContribution>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareAddCategoryMenuContributions(final Concept concept, final Category category) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<command");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("commandId=\"");
    String _commandId = this.getCommandId(concept, category);
    _builder.append(_commandId, "\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("style=\"push\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<visibleWhen");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("checkEnabled=\"true\">");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<with");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("variable=\"selection\">");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<iterate");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("ifEmpty=\"false\"");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("operator=\"and\">");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<test ");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("property=\"");
    String _name = concept.getName();
    _builder.append(_name, "\t\t\t\t\t");
    _builder.append(".ui.propertyTester.conceptEnabled\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t\t");
    _builder.append("value=\"true\" ");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("forcePluginActivation=\"true\">");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("</test>\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<instanceof");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("value=\"de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer\">");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("</instanceof>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("</iterate>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</with>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</visibleWhen>");
    _builder.newLine();
    _builder.append("</command>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareAddStructuralElementMenuContributions(final Concept concept, final StructuralElement structuralElement) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<command");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("commandId=\"");
    String _commandId = this.getCommandId(concept, structuralElement);
    _builder.append(_commandId, "\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("style=\"push\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<visibleWhen");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("checkEnabled=\"true\">");
    _builder.newLine();
    _builder.append("\t\t ");
    _builder.append("<with");
    _builder.newLine();
    _builder.append("         \t");
    _builder.append("variable=\"selection\">");
    _builder.newLine();
    _builder.append("          \t");
    _builder.append("<iterate");
    _builder.newLine();
    _builder.append("            \t");
    _builder.append("ifEmpty=\"false\"");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("operator=\"and\">");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("<test ");
    _builder.newLine();
    _builder.append("              \t\t");
    _builder.append("property=\"");
    String _name = concept.getName();
    _builder.append(_name, "              \t\t");
    _builder.append(".ui.propertyTester.conceptEnabled\"");
    _builder.newLineIfNotEmpty();
    _builder.append("              \t\t");
    _builder.append("value=\"true\" ");
    _builder.newLine();
    _builder.append("              \t\t");
    _builder.append("forcePluginActivation=\"true\">");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("</test>\t");
    _builder.newLine();
    _builder.append("              \t");
    _builder.append("<or>");
    _builder.newLine();
    _builder.append("                \t");
    _builder.append("<instanceof");
    _builder.newLine();
    _builder.append("                       ");
    _builder.append("value=\"de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance\">");
    _builder.newLine();
    _builder.append("                 \t");
    _builder.append("</instanceof>");
    _builder.newLine();
    _builder.append("                 \t");
    _builder.append("<instanceof");
    _builder.newLine();
    _builder.append("                       ");
    _builder.append("value=\"de.dlr.sc.virsat.model.dvlm.Repository\">");
    _builder.newLine();
    _builder.append("                 \t");
    _builder.append("</instanceof>");
    _builder.newLine();
    _builder.append("              \t");
    _builder.append("</or>");
    _builder.newLine();
    _builder.append("           \t");
    _builder.append("</iterate>");
    _builder.newLine();
    _builder.append("           \t");
    _builder.append("<and>");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("<count");
    _builder.newLine();
    _builder.append("                    ");
    _builder.append("value=\"1\">");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("</count>");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("</and>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("</with>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</visibleWhen>");
    _builder.newLine();
    _builder.append("</command>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareEditorSection(final Concept concept) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<extension");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("point=\"de.dlr.sc.virsat.uiengine.ui.EditorSection\">");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<editorSection");
    _builder.newLine();
    _builder.append("          ");
    _builder.append("id=\"");
    String _name = concept.getName();
    _builder.append(_name, "          ");
    _builder.append(".ui.Section\"");
    _builder.newLineIfNotEmpty();
    _builder.append("          ");
    _builder.append("topRanking=\"1000\">");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("</editorSection>");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("</extension>\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareUiSnippetTables(final Concept concept, final ATypeDefinition typeDefinition) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<uiSnippet");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("id=\"");
    String _name = concept.getName();
    _builder.append(_name, "      ");
    _builder.append(".table.uiSnippet");
    String _name_1 = typeDefinition.getName();
    _builder.append(_name_1, "      ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("section=\"");
    String _name_2 = concept.getName();
    _builder.append(_name_2, "      ");
    _builder.append(".ui.Section\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("snippet=\"");
    String _name_3 = concept.getName();
    _builder.append(_name_3, "      ");
    _builder.append(".ui.snippet.");
    String _concreteClassName = GenerateCategoryUiSnippetTable.getConcreteClassName(typeDefinition);
    _builder.append(_concreteClassName, "      ");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("</uiSnippet>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareUiSnippetArrayTables(final Concept concept, final AProperty property, final Category category, final Category extendingCategory) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<uiSnippet");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("id=\"");
    String _name = concept.getName();
    _builder.append(_name, "      ");
    _builder.append(".table.uiSnippet");
    String _propertyId = ConceptGeneratorUtil.getPropertyId(property, category);
    String _firstUpper = StringExtensions.toFirstUpper(extendingCategory.getName());
    String _plus = (_propertyId + _firstUpper);
    _builder.append(_plus, "      ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("section=\"");
    String _name_1 = concept.getName();
    _builder.append(_name_1, "      ");
    _builder.append(".ui.Section\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("snippet=\"");
    String _name_2 = concept.getName();
    _builder.append(_name_2, "      ");
    _builder.append(".ui.snippet.");
    String _concreteClassName = GenerateCategoryUiSnippetArrayTable.getConcreteClassName(property, category, extendingCategory);
    _builder.append(_concreteClassName, "      ");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("</uiSnippet>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareUiSnippetArrayTables(final Concept concept, final AProperty property, final Category category) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<uiSnippet");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("id=\"");
    String _name = concept.getName();
    _builder.append(_name, "      ");
    _builder.append(".table.uiSnippet");
    String _propertyId = ConceptGeneratorUtil.getPropertyId(property, category);
    _builder.append(_propertyId, "      ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("section=\"");
    String _name_1 = concept.getName();
    _builder.append(_name_1, "      ");
    _builder.append(".ui.Section\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("snippet=\"");
    String _name_2 = concept.getName();
    _builder.append(_name_2, "      ");
    _builder.append(".ui.snippet.");
    String _concreteClassName = GenerateCategoryUiSnippetArrayTable.getConcreteClassName(property, category);
    _builder.append(_concreteClassName, "      ");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("</uiSnippet>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareConceptImageExtension(final IQualifiedName qualifiedNameObject) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<conceptImage");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("fullQualifiedID=\"");
    String _fullQualifiedName = qualifiedNameObject.getFullQualifiedName();
    _builder.append(_fullQualifiedName, "\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("pathToImage=\"resources/icons/");
    String _name = qualifiedNameObject.getName();
    _builder.append(_name, "\t");
    _builder.append(".gif\">");
    _builder.newLineIfNotEmpty();
    _builder.append("</conceptImage>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence declareUiSnippetSections(final Concept concept, final Category category) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<uiSnippet");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("id=\"");
    String _name = concept.getName();
    _builder.append(_name, "      ");
    _builder.append(".section.uiSnippet");
    String _name_1 = category.getName();
    _builder.append(_name_1, "      ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("section=\"");
    String _name_2 = concept.getName();
    _builder.append(_name_2, "      ");
    _builder.append(".ui.Section\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("snippet=\"");
    String _name_3 = concept.getName();
    _builder.append(_name_3, "      ");
    _builder.append(".ui.snippet.");
    String _concreteClassName = GenerateCategoryUiSnippetPropertySection.getConcreteClassName(category);
    _builder.append(_concreteClassName, "      ");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("</uiSnippet>");
    _builder.newLine();
    return _builder;
  }
  
  public String getCommandId(final Concept concept, final IQualifiedName qualifiedNameObject) {
    String _name = concept.getName();
    String _plus = (_name + ".ui.command.Add");
    String _name_1 = qualifiedNameObject.getName();
    return (_plus + _name_1);
  }
  
  /**
   * Call thus method to hand back the name of the current concept
   * In case the short name is set it will be returned otherwise the usual name will be handed back
   * which is usually the name including the FQN to the concept. Therefore the shortName should be used in a way such as Product Structures
   */
  public String getReadableConceptName(final Concept concept) {
    String _xifexpression = null;
    if (((concept.getDisplayName() != null) && (!concept.getDisplayName().isEmpty()))) {
      return concept.getDisplayName();
    } else {
      _xifexpression = concept.getName();
    }
    return _xifexpression;
  }
  
  @Pure
  public PluginXmlReader getPluginXmlReader() {
    return this.pluginXmlReader;
  }
  
  public void setPluginXmlReader(final PluginXmlReader pluginXmlReader) {
    this.pluginXmlReader = pluginXmlReader;
  }
}
