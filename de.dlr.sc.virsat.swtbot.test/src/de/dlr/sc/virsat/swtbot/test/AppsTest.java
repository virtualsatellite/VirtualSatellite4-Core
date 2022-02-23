/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.swtbot.test;

import static org.junit.Assert.assertTrue;

import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.junit.Test;

public class AppsTest extends ASwtBotTestCase {

	@Test
	public void runAppTest() throws InterruptedException {
		bot.saveAllEditors();
		
		buildCounter.executeInterlocked(() -> bot.button("Activate / Update Apps").click());

		bot.button("Add App").click();
		bot.treeWithLabel("Table Section for: VirSat Apps").getTreeItem("AppExample1.java").select();
		bot.button("Run App").click();

		waitForView("Console");
		
		//Wait until the app finishes running
		bot.waitWhile(new DefaultCondition() {
			@Override
			public boolean test() throws Exception {
				return bot.toolbarButtonWithTooltip("Terminate").isEnabled();
			}
			
			@Override
			public String getFailureMessage() {
				return "App didn't finish executing.";
			}
		}, MAX_TEST_CASE_TIMEOUT_MILLISECONDS);

		String consoleOutput = bot.styledText().getText();
		assertTrue(consoleOutput.contains("App output:"));
		assertTrue("Default app should print all units to the console", consoleOutput.contains("gram"));
	}
}
