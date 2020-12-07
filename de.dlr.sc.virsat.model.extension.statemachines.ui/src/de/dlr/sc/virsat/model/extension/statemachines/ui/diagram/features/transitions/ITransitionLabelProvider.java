/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.transitions;

import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;

/**
 * Interface for transition label provides to extend the representation of transition in diagrams
 *
 */
public interface ITransitionLabelProvider {
	
	/**
	 * Get a transition's label
	 * @param transition the transition for which we need the label
	 * @return the label of the transition shown in the diagram
	 */
	String getLabel(Transition transition);

}
