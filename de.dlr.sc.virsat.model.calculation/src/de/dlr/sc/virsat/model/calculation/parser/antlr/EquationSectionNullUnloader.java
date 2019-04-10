/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.parser.antlr;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.IReferableElementsUnloader;

/**
 * This is an unloader which gets instantiated by the Parser of Xtext.
 * For the equations we don't want to unload elements since there is no advantage, and it
 * creates difficulties when reloading objects. Before Eclipse Oxygen Xtext was using their
 * NullUnloader as default implementation, but they changed to the GenericUnloader,
 * which is not compatible with the way we deal with our Equation Editor and the underlying 
 * EquationSection objects.
 * 
 * This class is bound in the module.
 * 
 * @author fisc_ph
 *
 */
public class EquationSectionNullUnloader implements IReferableElementsUnloader {
	@Override
	public void unloadRoot(EObject root) {
		// Intentionally left empty
	}
}
