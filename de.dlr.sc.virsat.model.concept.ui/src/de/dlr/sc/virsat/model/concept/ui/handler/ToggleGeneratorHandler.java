/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.ui.handler;

import org.eclipse.core.commands.AbstractHandlerWithState;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.State;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.RegistryToggleState;

import de.dlr.sc.virsat.model.concept.generator.IConceptGeneratorEnablement;

/**
 * Handler that controls the toggle state for enabling or disabling the generator
 * @author fisc_ph
 *
 */
public class ToggleGeneratorHandler extends AbstractHandlerWithState implements IHandler, IConceptGeneratorEnablement {

	@Override
	public void handleStateChange(State state, Object oldValue) {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
	    HandlerUtil.toggleCommandState(command);
		return null;
	}

	public static final String ID_TOGGLE_GENERATOR_COMMAND = "de.dlr.sc.virsat.model.concept.ui.command.toggle.generators";
	
	@Override
	public boolean isGeneratorEnabled() {
		// Ask the command's state if it is toggled to enabled or not
		ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
		Command command = service.getCommand(ID_TOGGLE_GENERATOR_COMMAND);
		State state = command.getState(RegistryToggleState.STATE_ID);
		
		// In case there is something wrong with the state, we want to generate code
		if (state == null) {
			return true;
		}
		if (!(state.getValue() instanceof Boolean)) {
			return true;
		}

		// get the actual value from the toggled button
		boolean currentValue = ((Boolean) state.getValue()).booleanValue();
		return currentValue;
	}
}
