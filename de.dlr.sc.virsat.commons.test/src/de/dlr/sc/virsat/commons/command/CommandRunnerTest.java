/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.commons.command;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;


/**
 * Test cases for the dependency tree.
 * @author maur_pa
 *
 */
public class CommandRunnerTest {
	
	private CommandRunner cr;
	private ByteArrayOutputStream out;
	private ByteArrayOutputStream err;
	
	@Before
	public void setUp() throws Exception {
		cr = new CommandRunner();
		out = new ByteArrayOutputStream();
		err = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
	    System.setErr(new PrintStream(err));
	}

	@Test
	public void testStartCommandRunner() {
		cr.startCommandRunner("", "eclipse");
		assertEquals(cr.runCommand("exit"), true);
	}
	
	@Test
	public void testExitCommandRunner() {
		cr.startCommandRunner("", "eclipse");
		cr.exitCommand();
		assertEquals(cr.runCommand("exit"), false);
	}
	
	@Test
	public void testInputStreamConsumer() {
		cr.startCommandRunner("", "eclipse");
		cr.exitCommand();
		assertEquals(cr.runCommand("exit"), false);
		assertEquals(out.toString(), "Process terminated with 0\r\n");
	}
	
}
