/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept;

import com.google.inject.Binder;
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider;
import de.dlr.sc.virsat.model.concept.naming.ConceptLanguageQualifiedNameProvider;
import de.dlr.sc.virsat.model.concept.validation.ConceptLanguageExtendedValidator;
import de.dlr.sc.virsat.model.concept.validation.ConceptLanguageValidator;
import javax.inject.Singleton;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.naming.IQualifiedNameProvider;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
@SuppressWarnings("all")
public class ConceptLanguageRuntimeModule extends AbstractConceptLanguageRuntimeModule {
  @Override
  public void configure(final Binder binder) {
    super.configure(binder);
    binder.<IOutputConfigurationProvider>bind(IOutputConfigurationProvider.class).to(ConceptOutputConfigurationProvider.class).in(Singleton.class);
  }
  
  @Override
  public Class<? extends ConceptLanguageValidator> bindConceptLanguageValidator() {
    return ConceptLanguageExtendedValidator.class;
  }
  
  @Override
  public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
    return ConceptLanguageQualifiedNameProvider.class;
  }
}
