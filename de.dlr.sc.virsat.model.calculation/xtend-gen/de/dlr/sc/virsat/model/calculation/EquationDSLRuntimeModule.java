/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.calculation;

import com.google.inject.Binder;
import com.google.inject.name.Names;
import de.dlr.sc.virsat.model.calculation.formatting.EquationDSLFormatter;
import de.dlr.sc.virsat.model.calculation.parser.antlr.EquationSectionNullUnloader;
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionVirSatAwareXtextResourceSet;
import de.dlr.sc.virsat.model.calculation.resource.EquationSectionXtextResource;
import de.dlr.sc.virsat.model.calculation.scoping.EquationSectionGlobalScopeProvider;
import de.dlr.sc.virsat.model.calculation.scoping.EquationSectionImportedNamespaceAwareLocalScopeProvider;
import de.dlr.sc.virsat.model.calculation.scoping.EquationSectionNamesProvider;
import de.dlr.sc.virsat.model.calculation.serializer.EquationTransientValueService;
import de.dlr.sc.virsat.model.calculation.serializer.SafeEquationDSLSemanticSequencer;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.formatting.IFormatter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.parser.antlr.IReferableElementsUnloader;
import org.eclipse.xtext.parsetree.reconstr.ITransientValueService;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.scoping.IGlobalScopeProvider;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
@SuppressWarnings("all")
public class EquationDSLRuntimeModule extends AbstractEquationDSLRuntimeModule {
  @Override
  public Class<? extends ITransientValueService> bindITransientValueService() {
    return EquationTransientValueService.class;
  }
  
  @Override
  public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
    return EquationSectionNamesProvider.class;
  }
  
  @Override
  public Class<? extends ResourceSet> bindResourceSet() {
    return EquationSectionVirSatAwareXtextResourceSet.class;
  }
  
  /**
   * Binding the unloader which takes care of unloading Equations in the EquatioNSection
   * when the partial parser is called. Actually this Unloader is not doing anything. This
   * behavior was implemented by Xtext before the Oxygen Release, but with Oxygen they changed
   * their behavior to use their GenericUnloader. Still unloading our Equations within the Xtext
   * context creates issues, since reloading them is not an implemented standard behavior. In our Xtext
   * context we actually have a non persisted copy of the EquationSection in the resource, thus it cannot
   * be reloaded. On a reload an equation would need to be copied again from the DVLM VirSat Model and
   * would then need deeper considerations in cases such as other equations referencing the one that was
   * just copied. At the moment it looks like the unloading is just done for performance considerations.
   * Hence we don't need it for the moment.
   * @return class of type IReferableElementsUnloader
   */
  public Class<? extends IReferableElementsUnloader> bindIReferableElementsUnloader() {
    return EquationSectionNullUnloader.class;
  }
  
  @Override
  public Class<? extends XtextResourceSet> bindXtextResourceSet() {
    return EquationSectionVirSatAwareXtextResourceSet.class;
  }
  
  @Override
  public Class<? extends XtextResource> bindXtextResource() {
    return EquationSectionXtextResource.class;
  }
  
  @Override
  public Class<? extends IGlobalScopeProvider> bindIGlobalScopeProvider() {
    return EquationSectionGlobalScopeProvider.class;
  }
  
  @Override
  public Class<? extends IFormatter> bindIFormatter() {
    return EquationDSLFormatter.class;
  }
  
  @Override
  public Class<? extends ISemanticSequencer> bindISemanticSequencer() {
    return SafeEquationDSLSemanticSequencer.class;
  }
  
  @Override
  public void configureIScopeProviderDelegate(final Binder binder) {
    binder.<IScopeProvider>bind(IScopeProvider.class).annotatedWith(Names.named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE)).to(EquationSectionImportedNamespaceAwareLocalScopeProvider.class);
  }
}
