/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator;

import de.dlr.sc.virsat.model.concept.generator.ConceptPreprocessor;
import de.dlr.sc.virsat.model.concept.generator.IConceptGeneratorEnablement;
import de.dlr.sc.virsat.model.concept.generator.beans.GenerateCategoryBeans;
import de.dlr.sc.virsat.model.concept.generator.beans.GenerateStructuralElementBeans;
import de.dlr.sc.virsat.model.concept.generator.commands.GenerateArrayCreateAddCommand;
import de.dlr.sc.virsat.model.concept.generator.commands.GenerateCategoryCreateAddCommand;
import de.dlr.sc.virsat.model.concept.generator.dmf.GenerateDmfCategories;
import de.dlr.sc.virsat.model.concept.generator.handler.GenerateCategoryAddHandler;
import de.dlr.sc.virsat.model.concept.generator.handler.GenerateStructuralElementInstanceAddHandler;
import de.dlr.sc.virsat.model.concept.generator.migrator.GenerateMigrator;
import de.dlr.sc.virsat.model.concept.generator.plugin.GenerateConceptImages;
import de.dlr.sc.virsat.model.concept.generator.plugin.GeneratePluginXml;
import de.dlr.sc.virsat.model.concept.generator.plugin.GenerateUiPluginXml;
import de.dlr.sc.virsat.model.concept.generator.plugin.PluginXmlReader;
import de.dlr.sc.virsat.model.concept.generator.propertyTester.GenerateConceptEnabledTester;
import de.dlr.sc.virsat.model.concept.generator.snippets.GenerateCategoryUiSnippetArrayTable;
import de.dlr.sc.virsat.model.concept.generator.snippets.GenerateCategoryUiSnippetPropertySection;
import de.dlr.sc.virsat.model.concept.generator.snippets.GenerateCategoryUiSnippetTable;
import de.dlr.sc.virsat.model.concept.generator.tests.GenerateAllTests;
import de.dlr.sc.virsat.model.concept.generator.tests.GenerateCategoryTests;
import de.dlr.sc.virsat.model.concept.generator.tests.GenerateMigratorTests;
import de.dlr.sc.virsat.model.concept.generator.tests.GenerateStructuralElementTests;
import de.dlr.sc.virsat.model.concept.generator.tests.GenerateValidatorTests;
import de.dlr.sc.virsat.model.concept.generator.validator.GenerateDeprecatedValidator;
import de.dlr.sc.virsat.model.concept.generator.validator.GenerateValidator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGenerator2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.xbase.lib.Exceptions;

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
@SuppressWarnings("all")
public class ConceptLanguageGenerator implements IGenerator2 {
  private final String ID_EXTENSION_POINT_GENERATOR = "de.dlr.sc.virsat.model.concept.generator";
  
  private final String ID_EXTENSION_POINT_GENERATOR_ENABLEMENT_CLASS = "class";
  
  @Override
  public void afterGenerate(final Resource input, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
  }
  
  @Override
  public void beforeGenerate(final Resource input, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
  }
  
  /**
   * Entry method from the xtend generator fragment
   */
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    try {
      final IExtensionRegistry extensionPointRegistry = Platform.getExtensionRegistry();
      final IConfigurationElement[] configElements = extensionPointRegistry.getConfigurationElementsFor(this.ID_EXTENSION_POINT_GENERATOR);
      boolean generateCode = true;
      for (final IConfigurationElement configElement : configElements) {
        {
          Object _createExecutableExtension = configElement.createExecutableExtension(this.ID_EXTENSION_POINT_GENERATOR_ENABLEMENT_CLASS);
          final IConceptGeneratorEnablement generatorEnablement = ((IConceptGeneratorEnablement) _createExecutableExtension);
          generateCode = (generateCode && generatorEnablement.isGeneratorEnabled());
        }
      }
      if (generateCode) {
        final ConceptPreprocessor conceptPreprocessor = new ConceptPreprocessor(fsa);
        final Concept dataModel = conceptPreprocessor.process(resource);
        String _replace = dataModel.getName().replace(".", "/");
        String _plus = ("../src/" + _replace);
        String _plus_1 = (_plus + "/validator/StructuralElementInstanceValidator.java");
        final boolean hasDeprecatedValidator = fsa.isFile(_plus_1);
        new GenerateDmfCategories().serializeModel(dataModel, fsa);
        new GenerateConceptImages().serializeModel(dataModel, fsa);
        new GenerateCategoryBeans().serializeModel(dataModel, fsa);
        new GenerateStructuralElementBeans().serializeModel(dataModel, fsa);
        new GenerateCategoryAddHandler().serializeModel(dataModel, fsa);
        new GenerateStructuralElementInstanceAddHandler().serializeModel(dataModel, fsa);
        new GenerateCategoryCreateAddCommand().serializeModel(dataModel, fsa);
        new GenerateArrayCreateAddCommand().serializeModel(dataModel, fsa);
        new GenerateCategoryUiSnippetTable().serializeModel(dataModel, fsa);
        new GenerateCategoryUiSnippetArrayTable().serializeModel(dataModel, fsa);
        new GenerateCategoryUiSnippetPropertySection().serializeModel(dataModel, fsa);
        GenerateUiPluginXml _generateUiPluginXml = new GenerateUiPluginXml();
        PluginXmlReader _pluginXmlReader = new PluginXmlReader();
        _generateUiPluginXml.serializeModel(dataModel, _pluginXmlReader, fsa);
        GeneratePluginXml _generatePluginXml = new GeneratePluginXml();
        PluginXmlReader _pluginXmlReader_1 = new PluginXmlReader();
        _generatePluginXml.serializeModel(dataModel, _pluginXmlReader_1, fsa);
        new GenerateValidator().serializeModel(dataModel, fsa);
        new GenerateMigrator().serializeModel(dataModel, fsa);
        new GenerateConceptEnabledTester().serializeModel(dataModel, fsa);
        new GenerateCategoryTests().serializeModel(dataModel, fsa);
        new GenerateStructuralElementTests().serializeModel(dataModel, fsa);
        new GenerateMigratorTests().serializeModel(dataModel, fsa);
        new GenerateValidatorTests().serializeModel(dataModel, fsa);
        new GenerateAllTests().serializeModel(dataModel, fsa);
        if ((hasDeprecatedValidator == true)) {
          new GenerateDeprecatedValidator().serializeModel(dataModel, fsa);
          GeneratePluginXml _generatePluginXml_1 = new GeneratePluginXml();
          PluginXmlReader _pluginXmlReader_2 = new PluginXmlReader();
          _generatePluginXml_1.serializeModelDeprecatedValidator(dataModel, _pluginXmlReader_2, fsa);
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
