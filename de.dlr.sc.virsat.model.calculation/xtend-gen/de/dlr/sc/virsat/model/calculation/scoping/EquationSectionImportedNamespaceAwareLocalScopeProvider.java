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

import com.google.common.collect.Iterables;
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionXtextResource;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationInput;
import de.dlr.sc.virsat.model.dvlm.calculation.Import;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import java.util.Iterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.ISelectable;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;
import org.eclipse.xtext.scoping.impl.MultimapBasedSelectable;
import org.eclipse.xtext.xbase.lib.Exceptions;

/**
 * Implementation for the namespace aware importer. This overrides the get local names
 * which is combining the names of the current resource plus the names of the resource
 * where the current EquationSection is embedded in. This means the inputs form the
 * ContainerStructuralElement
 */
@SuppressWarnings("all")
public class EquationSectionImportedNamespaceAwareLocalScopeProvider extends ImportedNamespaceAwareLocalScopeProvider {
  @Override
  protected IScope getLocalElementsScope(final IScope parent, final EObject context, final EReference reference) {
    return super.getLocalElementsScope(parent, context, reference);
  }
  
  @Override
  public ISelectable internalGetAllDescriptions(final Resource resource) {
    Iterable<EObject> combinedContents = null;
    final Iterable<EObject> allContents = new Iterable<EObject>() {
      @Override
      public Iterator<EObject> iterator() {
        return EcoreUtil.<EObject>getAllContents(resource, false);
      }
    };
    if ((resource instanceof EquationSectionXtextResource)) {
      final EquationSectionXtextResource equationSectionXtextResource = ((EquationSectionXtextResource) resource);
      final Iterable<EObject> allVirSatContents = new Iterable<EObject>() {
        @Override
        public Iterator<EObject> iterator() {
          return VirSatEcoreUtil.<IEquationInput>getAllContentsOfType(equationSectionXtextResource.getContainerResource(), IEquationInput.class, true);
        }
      };
      combinedContents = Iterables.<EObject>concat(allContents, allVirSatContents);
    } else {
      combinedContents = Iterables.<EObject>concat(allContents);
    }
    final Iterable<IEObjectDescription> allDescriptions = Scopes.<EObject>scopedElementsFor(combinedContents, this.getQualifiedNameProvider());
    return new MultimapBasedSelectable(allDescriptions);
  }
  
  @Override
  public String getImportedNamespace(final EObject object) {
    if ((object instanceof Import)) {
      final Import import_ = ((Import) object);
      final IInstance importedNameSpace = import_.getImportedNamespace();
      String _xtrycatchfinallyexpression = null;
      try {
        String _fullQualifiedInstanceName = importedNameSpace.getFullQualifiedInstanceName();
        String _plus = (_fullQualifiedInstanceName + ActiveConceptHelper.FQID_DELIMITER);
        String _wildCard = this.getWildCard();
        _xtrycatchfinallyexpression = (_plus + _wildCard);
      } catch (final Throwable _t) {
        if (_t instanceof NullPointerException) {
          _xtrycatchfinallyexpression = null;
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      return _xtrycatchfinallyexpression;
    }
    return super.getImportedNamespace(object);
  }
}
