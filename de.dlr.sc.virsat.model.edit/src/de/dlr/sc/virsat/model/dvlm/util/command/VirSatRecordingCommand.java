/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;

/**
 * Wraps a command and memorizes all IVirSatRecordableCommands executed
 * during the execution of this command. This allows to handle additional
 * business logic such as removing files from SEIs within a Recording command.
 * Such logic can be chained to this command. Within the VirSatCommand Stack,
 * such IVirSatRecordableCommands will be automatically added and redorder to this
 * command. Recorded commands will be undone/redone accordingly.
 *
 */

public class VirSatRecordingCommand extends AbstractCommand {

	private Command cmd;
	private List<Command> commands = new ArrayList<>();
	
	/**
	 * Standard constructor.
	 * @param cmd the wrapped command
	 */
	
	public VirSatRecordingCommand(Command cmd) {
		super(cmd.getLabel());
		this.cmd = cmd;
	}
	
	@Override
	public void execute() {
		cmd.execute();
	}
	
	@Override
	public boolean canExecute() {
		// Only executed the actual command, since the commands that
		// get chained to this command are already executed by the
		// WorkspaceCommand Stack
		return cmd.canExecute();
	}

	@Override
	public boolean canUndo() {
		if (!cmd.canUndo()) {
			return false;
		}
		
		for (Command cmd : commands) {
			if (!cmd.canUndo()) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public String getDescription() {
		return cmd.getDescription();
	}
	
	@Override
	public String getLabel() {
		return label != null ? label : super.getLabel();
	}

	@Override
	public void undo() {
		for (int i = commands.size() - 1; i >= 0; --i) {
			commands.get(i).undo();
		}
		cmd.undo();
	}
	
	@Override
	public void redo() {
		cmd.redo();
		for (Command cmd : commands) {
			cmd.redo();
		}
	}
	
	@Override
	public Command chain(Command command) {
		
		if (command instanceof IVirSatRecordableCommand) {
			commands.add(command);
		} else if (command instanceof CompoundCommand) {
			CompoundCommand cc = (CompoundCommand) command;
			for (Command cmd : cc.getCommandList()) {
				chain(cmd);
			}
		}
		
		return this;
	}
	
	/**
	 * Gets the list of recorded commands
	 * @return the list of recorded commands
	 */
	public List<Command> getRecordedCommands() {
		return commands;
	}
}
