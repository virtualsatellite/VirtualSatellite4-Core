/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept;

import com.google.inject.Guice;
import com.google.inject.Injector;


/**
 * Method to initialize the Guice Injector for testing
 * @author fisc_ph
 *
 */
public class ConceptLanguageStandaloneTestSetup extends ConceptLanguageStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(new ConceptLanguageTestRuntimeModule());
	}
	
}
