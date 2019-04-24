/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.calculation.scoping;

import com.google.common.base.Predicate;
import com.google.inject.Inject;
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionVirSatAwareXtextResourceSet;
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionXtextResource;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.DefaultGlobalScopeProvider;

@SuppressWarnings("all")
public class EquationSectionGlobalScopeProvider extends DefaultGlobalScopeProvider {
  @Inject
  private IQualifiedNameProvider qualifiedNameProvider;
  
  @Override
  public IScope getScope(final Resource resource, final EReference reference, final Predicate<IEObjectDescription> filter) {
    final EClass referenceType = reference.getEReferenceType();
    final Class<?> eObjectClazz = referenceType.getInstanceClass();
    if ((eObjectClazz.isAssignableFrom(IEquationInput.class) || eObjectClazz.isAssignableFrom(IInstance.class))) {
      if ((resource instanceof EquationSectionXtextResource)) {
        final EquationSectionXtextResource equationSectionXtextResource = ((EquationSectionXtextResource)resource);
        final Resource equationSectionVirSatResource = equationSectionXtextResource.getContainerResource();
        ResourceSet _resourceSet = ((EquationSectionXtextResource)resource).getResourceSet();
        final EquationSectionVirSatAwareXtextResourceSet equationSectionXtextResourceSet = ((EquationSectionVirSatAwareXtextResourceSet) _resourceSet);
        final List<ResourceSet> virSatResourceSets = equationSectionXtextResourceSet.getReferencedResourceSets();
        Class<?> _instanceClass = referenceType.getInstanceClass();
        final List<EObject> listOfGlobalScopes = VirSatEcoreUtil.<EObject>getAllContentsOfTypefromResourceSets(virSatResourceSets, equationSectionVirSatResource, ((Class<EObject>) _instanceClass), true);
        final IScope scopes = Scopes.<EObject>scopeFor(listOfGlobalScopes, this.qualifiedNameProvider, IScope.NULLSCOPE);
        return scopes;
      }
    }
    final IScope scope = super.getScope(resource, reference, filter);
    return scope;
  }
}
