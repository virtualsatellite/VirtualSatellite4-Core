/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

import de.dlr.sc.virsat.model.extension.statemachines.model.AllowsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.ForbidsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
/**
 * Makes icons available for Graphiti. 
 * @author bell_Er * */
public class StateMachineDiagramImageProvider extends AbstractImageProvider {

	@Override
	protected void addAvailableImages() {
		addImageFilePath(State.FULL_QUALIFIED_CATEGORY_NAME, "resources/icons/State.gif");
		addImageFilePath(Transition.FULL_QUALIFIED_CATEGORY_NAME, "resources/icons/Transition.gif");
		addImageFilePath(ForbidsConstraint.FULL_QUALIFIED_CATEGORY_NAME, "resources/icons/ForbidsConstraint.gif");
		addImageFilePath(AllowsConstraint.FULL_QUALIFIED_CATEGORY_NAME, "resources/icons/AllowsConstraint.gif");
		addImageFilePath(StateMachine.PROPERTY_INITIALSTATE, "resources/icons/InitialState.gif");
		addImageFilePath("OpenEditor", "platform:/plugin/de.dlr.sc.virsat.uiengine.ui/icons/VirSat_Component_Edit.png");
		addImageFilePath("Checked", "resources/icons/Checked.gif");
		addImageFilePath("Unchecked", "resources/icons/Unchecked.gif");
	}

}
