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

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CompoundCommand;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * This class tests the VirSatRecordingCommand
 * @author muel_s8
 *
 */

public class VirSatRecordingCommandTest {

	/**
	 * An implementation of a recordable command.
	 * @author muel_s8
	 *
	 */
	
	private static class RecordableCommand extends AbstractCommand implements IVirSatRecordableCommand {
		@Override
		public void execute() {
		}

		@Override
		public void redo() {
		}
	}
	
	/**
	 * An implementation of a non recordable command.
	 * @author muel_s8
	 *
	 */
	
	private static class NonRecordableCommand extends AbstractCommand {
		@Override
		public void execute() {
		}

		@Override
		public void redo() {
		}
	}
	
	@Test
	public void testChainCommand() {
		AbstractCommand recordableCmd = new RecordableCommand();
		AbstractCommand nonRecordableCmd = new NonRecordableCommand();
		
		VirSatRecordingCommand recCmd = new VirSatRecordingCommand(new AbstractCommand() {

			@Override
			public void execute() {
			}

			@Override
			public void redo() {	
			}
		});
		
		recCmd.chain(recordableCmd);
		recCmd.chain(nonRecordableCmd);
		
		assertThat("Recordable command has been recorded", recCmd.getRecordedCommands(), hasItem(recordableCmd));
		assertThat("Recordable command has been recorded", recCmd.getRecordedCommands(), not(hasItem(nonRecordableCmd)));
	
		recCmd.getRecordedCommands().clear();
		
		CompoundCommand cc = new CompoundCommand();
		cc.append(recordableCmd);
		cc.append(nonRecordableCmd);
		
		recCmd.chain(cc);
		
		assertThat("Recordable command has been recorded", recCmd.getRecordedCommands(), hasItem(recordableCmd));
		assertThat("Recordable command has been recorded", recCmd.getRecordedCommands(), not(hasItem(nonRecordableCmd)));
	
	}

}
