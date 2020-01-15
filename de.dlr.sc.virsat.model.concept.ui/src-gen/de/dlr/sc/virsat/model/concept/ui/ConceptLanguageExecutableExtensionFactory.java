/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.ui;

import com.google.inject.Injector;
import de.dlr.sc.virsat.model.concept.ui.internal.ConceptActivator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class ConceptLanguageExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return Platform.getBundle(ConceptActivator.PLUGIN_ID);
	}
	
	@Override
	protected Injector getInjector() {
		ConceptActivator activator = ConceptActivator.getInstance();
		return activator != null ? activator.getInjector(ConceptActivator.DE_DLR_SC_VIRSAT_MODEL_CONCEPT_CONCEPTLANGUAGE) : null;
	}

}
