/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.ui

import de.dlr.sc.virsat.model.calculation.ui.resource.EquationSectionResourceForEditorInputFactory
import org.apache.log4j.Logger
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor

/**
 * Use this class to register components to be used within the Eclipse IDE.
 */
@FinalFieldsConstructor
class EquationDSLUiModule extends AbstractEquationDSLUiModule {
	
	override bindIResourceForEditorInputFactory() {
		return EquationSectionResourceForEditorInputFactory
	}

	// the normal Xtext activator classes to not have such capabilities.
	public static final Logger LOGGER = Logger.getLogger(EquationDSLUiModule);

}
