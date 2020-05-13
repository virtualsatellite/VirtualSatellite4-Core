/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.scoping;

import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;

/**
 * Class that implements the scoping of the concept language. Base class divers from
 * generated stub class to enable implicit imports for core language features
 */
@SuppressWarnings("all")
public class ConceptLanguageScopeProvider extends ImportedNamespaceAwareLocalScopeProvider {
  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    return super.getScope(context, reference);
  }
  
  /**
   * Method that adds a reference to the concept language core concept as implicit import
   */
  @Override
  public List<ImportNormalizer> getImplicitImports(final boolean ignoreCase) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field de is undefined"
      + "\ndlr cannot be resolved"
      + "\nsc cannot be resolved"
      + "\nvirsat cannot be resolved"
      + "\nmodel cannot be resolved"
      + "\next cannot be resolved"
      + "\ncore cannot be resolved"
      + "\nActivator cannot be resolved"
      + "\npluginId cannot be resolved");
  }
}
