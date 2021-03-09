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
 * Class to get a transition label from its properties 
 *
 */
public class TransitionLabelProvider implements ITransitionLabelProvider {
	
	/**
	 * Return a label for a transition based on its properties
	 * @param transition the transition we need the label for
	 * @return the label
	 */
	public String getLabel(Transition transition) {
		String label = "";
		if (transition.getTrigger() != null) {
			label += transition.getTrigger().getName();
			if (transition.getTrigger().getIsSending()) {
				label += " ?";
			} else {
				label += " !";
			}
		} 
		if (transition.getName() != null 
				&& !transition.getName().equals(transition.getTypeInstance().getType().getName())
				&& !transition.getName().equals("")) {
			label += " : " + transition.getName();
		}
		return label;
	
	}

}
