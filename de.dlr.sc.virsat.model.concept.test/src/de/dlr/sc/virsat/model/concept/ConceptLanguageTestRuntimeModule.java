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

import de.dlr.sc.virsat.model.concept.validation.ConceptLanguageValidator;

/**
 * Class to setup the Test Runtime Modules of Guice
 * @author fisc_ph
 *
 */
public class ConceptLanguageTestRuntimeModule extends ConceptLanguageRuntimeModule {

	@Override
	@org.eclipse.xtext.service.SingletonBinding(eager = true) public Class<? extends ConceptLanguageValidator> bindConceptLanguageValidator() {
		//For tests use core validators
		return ConceptLanguageValidator.class;
	}

}
