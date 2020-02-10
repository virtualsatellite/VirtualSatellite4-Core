/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.ereference;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.EcoreImport;
import java.util.List;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

@SuppressWarnings("all")
public class ExternalGenModelHelper {
  /**
   * Get the full qualified java class name of property
   */
  public String getEObjectClass(final EReferenceProperty property) {
    String typeClass = null;
    GenPackage genPackage = this.resolveGenPackage(property);
    if ((genPackage != null)) {
      typeClass = "";
      String _basePackage = genPackage.getBasePackage();
      boolean _tripleNotEquals = (_basePackage != null);
      if (_tripleNotEquals) {
        String _basePackage_1 = genPackage.getBasePackage();
        String _plus = (_basePackage_1 + ".");
        typeClass = _plus;
      }
      String _typeClass = typeClass;
      String _nSName = genPackage.getNSName();
      typeClass = (_typeClass + _nSName);
      String _typeClass_1 = typeClass;
      String _name = property.getReferenceType().getName();
      String _plus_1 = ("." + _name);
      typeClass = (_typeClass_1 + _plus_1);
    }
    return typeClass;
  }
  
  /**
   * Try to resolve the GenPackage of a given EReferenceProperty
   */
  public GenPackage resolveGenPackage(final EReferenceProperty property) {
    EObject _eContainer = property.getReferenceType().eContainer();
    final EPackage eClassPackage = ((EPackage) _eContainer);
    EObject _get = property.eResource().getContents().get(0);
    final Concept concept = ((Concept) _get);
    final Resource resource = property.eResource();
    final String nsURI = eClassPackage.getNsURI();
    GenPackage genPackage = null;
    EList<EcoreImport> _ecoreImports = concept.getEcoreImports();
    for (final EcoreImport eImport : _ecoreImports) {
      String _importedGenModel = eImport.getImportedGenModel();
      boolean _tripleNotEquals = (_importedGenModel != null);
      if (_tripleNotEquals) {
        final URI genModelURI = URI.createPlatformPluginURI(eImport.getImportedGenModel(), true);
        genPackage = this.loadGenPackage(genModelURI, nsURI, resource.getResourceSet());
      }
    }
    if (((genPackage == null) && (eClassPackage.eResource() != null))) {
      final URI ecoreURI = eClassPackage.eResource().getURI();
      final URI genModelURI_1 = ecoreURI.trimFileExtension().appendFileExtension("genmodel");
      genPackage = this.loadGenPackage(genModelURI_1, nsURI, resource.getResourceSet());
    }
    return genPackage;
  }
  
  /**
   * Load a GenModel package of a specified EPackage from a GenModel URI
   */
  public GenPackage loadGenPackage(final URI genModelUri, final String nsUri, final ResourceSet resourceSet) {
    Resource genModelResource = null;
    genModelResource = resourceSet.getResource(genModelUri, true);
    if (((genModelResource != null) && (genModelResource.getContents().get(0) instanceof GenModel))) {
      EObject _get = genModelResource.getContents().get(0);
      GenModel loadedGenModel = ((GenModel) _get);
      List<GenPackage> _allGenPackagesWithClassifiers = loadedGenModel.getAllGenPackagesWithClassifiers();
      for (final GenPackage package_ : _allGenPackagesWithClassifiers) {
        boolean _equals = package_.getNSURI().equals(nsUri);
        if (_equals) {
          return package_;
        }
      }
    }
    return null;
  }
}
