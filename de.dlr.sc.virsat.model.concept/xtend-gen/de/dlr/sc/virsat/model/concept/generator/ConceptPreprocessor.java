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

import de.dlr.sc.virsat.model.concept.generator.core.ConceptLanguageImplicitSuperTypeHandler;
import de.dlr.sc.virsat.model.concept.generator.xmi.GenerateConceptXmi;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess;

/**
 * Prepares a concept for an enhanced serialization in XMI and activates
 * concept language core features such as a GenericCategory
 * 
 * Transformations of the concept model that are required before generation should
 * be done within this class
 */
@SuppressWarnings("all")
public class ConceptPreprocessor {
  public static final String MODEL_TYPE_XMI_EXTENSION = "xmi";
  
  private final ConceptLanguageImplicitSuperTypeHandler conceptLanguageHandler = new ConceptLanguageImplicitSuperTypeHandler();
  
  private final IFileSystemAccess fileSystemAcesss;
  
  public ConceptPreprocessor(final IFileSystemAccess fsa) {
    this.fileSystemAcesss = fsa;
  }
  
  public Concept process(final Resource resource) {
    EObject _get = resource.getContents().get(0);
    final Concept originalConcept = ((Concept) _get);
    final Concept processedConcept = this.processContent(originalConcept);
    final URI xmiURI = this.getXmiUriFromConcept(resource);
    this.serializeContent(processedConcept, xmiURI);
    final Resource newContainer = resource.getResourceSet().getResource(xmiURI, true);
    EObject _get_1 = newContainer.getContents().get(0);
    return ((Concept) _get_1);
  }
  
  /**
   * Update the concept model content
   */
  public Concept processContent(final Concept originalConcept) {
    final Concept processedConcept = this.conceptLanguageHandler.addImplicitSuperType(originalConcept);
    return processedConcept;
  }
  
  /**
   * Serialize the processed concept into their new containers
   */
  public void serializeContent(final Concept concept, final URI xmiURI) {
    new GenerateConceptXmi().serializeModel(concept, this.fileSystemAcesss);
  }
  
  /**
   * Derive the XMI URI from the original URI
   */
  public URI getXmiUriFromConcept(final Resource resource) {
    URI rawUri = resource.getURI();
    URI targetUri = rawUri.trimFileExtension();
    targetUri = targetUri.appendFileExtension(ConceptPreprocessor.MODEL_TYPE_XMI_EXTENSION);
    return targetUri;
  }
}
