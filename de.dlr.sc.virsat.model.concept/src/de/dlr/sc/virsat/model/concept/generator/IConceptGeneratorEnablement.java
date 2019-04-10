/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator;

/**
 * Interface to contribute an enable state to the code generator.
 * Usually this interface is used by extension points defined in the UI plugin
 * to contribute the state of a toggle button to the generator.
 * @author fisc_ph
 *
 */
public interface IConceptGeneratorEnablement {
	
	/**
	 * Call this method to get an answer if the contribution wants to generate code or not
	 * @return return true if you want to generate code.
	 */
	boolean isGeneratorEnabled();
}
