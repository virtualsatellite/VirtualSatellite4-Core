/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.xmi;

import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class GenerateConceptXmi {
  public static final String MODEL_TYPE_XMI_EXTENSION = "xmi";
  
  public static final String CONCEPT_BASE_FILENAME = "concept";
  
  public static final String CONCEPT_XMI_FILENAME = ((GenerateConceptXmi.CONCEPT_BASE_FILENAME + ".") + GenerateConceptXmi.MODEL_TYPE_XMI_EXTENSION);
  
  /**
   * This method serialized the data model into the given format
   * @param fileNameExtension the extension of the target format to serialize to. Can be either XMI or XML for the  moment
   * @param dataModel access to the root model object
   * @param fsa access to the local file system
   */
  public void serializeModel(final Concept dataModel, final IFileSystemAccess fsa) {
    final Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
    Map<String, Object> _extensionToFactoryMap = resourceRegistry.getExtensionToFactoryMap();
    XMIResourceFactoryImpl _xMIResourceFactoryImpl = new XMIResourceFactoryImpl();
    _extensionToFactoryMap.put(GenerateConceptXmi.MODEL_TYPE_XMI_EXTENSION, _xMIResourceFactoryImpl);
    try {
      final ResourceSetImpl targetResourceSet = new ResourceSetImpl();
      final Resource targetResource = targetResourceSet.createResource(URI.createURI(GenerateConceptXmi.CONCEPT_XMI_FILENAME));
      targetResource.getContents().add(dataModel);
      final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      targetResource.save(outputStream, null);
      fsa.generateFile(GenerateConceptXmi.CONCEPT_XMI_FILENAME, ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_CONCEPT, outputStream.toString());
    } catch (final Throwable _t) {
      if (_t instanceof IOException) {
        final IOException e = (IOException)_t;
        throw new RuntimeException(e);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    try {
      final ResourceSetImpl targetResourceSet = new ResourceSetImpl();
      final String filenameWithVersion = GenerateConceptXmi.getConceptWithVersionName(dataModel);
      final Resource targetResource = targetResourceSet.createResource(URI.createURI(filenameWithVersion));
      targetResource.getContents().add(dataModel);
      final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      targetResource.save(outputStream, null);
      fsa.generateFile(filenameWithVersion, ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_CONCEPT, outputStream.toString());
    } catch (final Throwable _t) {
      if (_t instanceof IOException) {
        final IOException e = (IOException)_t;
        throw new RuntimeException(e);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  public static String getConceptWithVersionName(final Concept concept) {
    final String version = concept.getVersion().replace(".", "_");
    return ((((GenerateConceptXmi.CONCEPT_BASE_FILENAME + "_v") + version) + ".") + GenerateConceptXmi.MODEL_TYPE_XMI_EXTENSION);
  }
}
