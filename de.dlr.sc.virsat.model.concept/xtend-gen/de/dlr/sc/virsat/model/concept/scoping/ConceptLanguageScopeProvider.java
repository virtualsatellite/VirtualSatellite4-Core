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

import com.google.common.collect.Iterables;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.ext.core.Activator;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * Class that implements the scoping of the concept language. Base class divers from
 * generated stub class to enable implicit imports for core language features
 */
@SuppressWarnings("all")
public class ConceptLanguageScopeProvider extends ImportedNamespaceAwareLocalScopeProvider {
  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    IScope _xblockexpression = null;
    {
      if (((context instanceof AProperty) && reference.getName().equals("verificationType"))) {
        final IScope scope = super.getScope(context, reference);
        final ArrayList<EObject> elements = new ArrayList<EObject>();
        final Function1<IEObjectDescription, EObject> _function = (IEObjectDescription t) -> {
          return EcoreUtil.resolve(t.getEObjectOrProxy(), context.eResource().getResourceSet());
        };
        final Function1<EObject, Boolean> _function_1 = (EObject t) -> {
          return Boolean.valueOf((((t instanceof Category) && ((Category) t).isIsVerification()) && (!((Category) t).isIsAbstract())));
        };
        Iterables.<EObject>addAll(elements, 
          IterableExtensions.<EObject>filter(IterableExtensions.<IEObjectDescription, EObject>map(scope.getAllElements(), _function), _function_1));
        return Scopes.scopeFor(elements, IScope.NULLSCOPE);
      }
      _xblockexpression = super.getScope(context, reference);
    }
    return _xblockexpression;
  }

  /**
   * Method that adds a reference to the concept language core concept as implicit import
   */
  @Override
  public List<ImportNormalizer> getImplicitImports(final boolean ignoreCase) {
    QualifiedName qualifiedNamespace = new IQualifiedNameConverter.DefaultImpl().toQualifiedName(
      Activator.getPluginId());
    ImportNormalizer coreNamespace = new ImportNormalizer(qualifiedNamespace, true, true);
    ArrayList<ImportNormalizer> implicitImports = new ArrayList<ImportNormalizer>();
    implicitImports.add(coreNamespace);
    return implicitImports;
  }
}
