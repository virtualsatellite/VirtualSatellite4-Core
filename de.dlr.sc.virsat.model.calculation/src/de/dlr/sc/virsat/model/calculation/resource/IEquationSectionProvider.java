/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.resource;

import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;

/**
 * A provider for equation sections (for example xtext editors)
 * @author muel_s8
 *
 */

public interface IEquationSectionProvider {
	
	/**
	 * Get an equation section associated with this object
	 * @return the equation section
	 */
	EquationSection getEquationSection();
}
