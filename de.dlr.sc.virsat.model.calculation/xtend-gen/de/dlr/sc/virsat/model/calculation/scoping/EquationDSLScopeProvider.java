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

import com.google.inject.Inject;
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionVirSatAwareXtextResourceSet;
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionXtextResource;
import de.dlr.sc.virsat.model.dvlm.calculation.Import;
import de.dlr.sc.virsat.model.dvlm.calculation.SetFunction;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;

/**
 * This class creates the correct scopes for the References into the DVLM data model.
 * This class is important for the cross linking in the editor to provide meaningful names.
 * but it is also important for linking the names back to the actual objects once the
 * textual representation of the equations is deserialized back into the DVLM model.
 * This class makes use of the EquationSeciton Resource and ResourceSet which allow
 * the cross linking across other ResourceSets.
 */
@SuppressWarnings("all")
public class EquationDSLScopeProvider extends AbstractDeclarativeScopeProvider {
  @Inject
  private IQualifiedNameProvider qualifiedNameProvider;
  
  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    if ((context instanceof Import)) {
      final Resource resource = ((Import)context).eResource();
      if ((resource instanceof EquationSectionXtextResource)) {
        final EquationSectionXtextResource equationSectionXtextResource = ((EquationSectionXtextResource)resource);
        ResourceSet _resourceSet = equationSectionXtextResource.getResourceSet();
        final EquationSectionVirSatAwareXtextResourceSet equationSectionContainerVirSatAwareResourceSet = ((EquationSectionVirSatAwareXtextResourceSet) _resourceSet);
        final List<ResourceSet> virSatResourceSet = equationSectionContainerVirSatAwareResourceSet.getReferencedResourceSets();
        final List<IInstance> listOfLocalValueProeprtyInstances = VirSatEcoreUtil.<IInstance>getAllContentsOfTypefromResourceSets(virSatResourceSet, equationSectionXtextResource, IInstance.class, true);
        return Scopes.<EObject>scopeFor(listOfLocalValueProeprtyInstances, this.qualifiedNameProvider, IScope.NULLSCOPE);
      }
    } else {
      if ((context instanceof SetFunction)) {
        final Resource resource_1 = ((SetFunction)context).eResource();
        if ((resource_1 instanceof EquationSectionXtextResource)) {
          final EquationSectionXtextResource equationSectionXtextResource_1 = ((EquationSectionXtextResource)resource_1);
          ResourceSet _resourceSet_1 = equationSectionXtextResource_1.getResourceSet();
          final EquationSectionVirSatAwareXtextResourceSet equationSectionContainerVirSatAwareResourceSet_1 = ((EquationSectionVirSatAwareXtextResourceSet) _resourceSet_1);
          final List<ResourceSet> virSatResourceSet_1 = equationSectionContainerVirSatAwareResourceSet_1.getReferencedResourceSets();
          final List<ATypeDefinition> listOfTypeDefinitions = VirSatEcoreUtil.<ATypeDefinition>getAllContentsOfTypefromResourceSets(virSatResourceSet_1, equationSectionXtextResource_1, ATypeDefinition.class, true);
          final IScope scopes = Scopes.<EObject>scopeFor(listOfTypeDefinitions, this.qualifiedNameProvider, IScope.NULLSCOPE);
          return scopes;
        }
      }
    }
    final IScope scopes_1 = super.getScope(context, reference);
    return scopes_1;
  }
}
