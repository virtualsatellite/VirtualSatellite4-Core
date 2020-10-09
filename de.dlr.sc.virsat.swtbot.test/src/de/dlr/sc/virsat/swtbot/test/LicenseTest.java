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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.junit.Test;

public class LicenseTest extends ASwtBotTestCase {
	
	@Test
	public void licenseTest() {
		final String VIRSAT_PACKAGE_PREFIX = "de.dlr.sc.virsat";
		final String EXPECTED_PROVIDER = "DLR (German Aerospace Center)";
		
		final int COLUMN_PLUGIN_ID = 4;
		
		bot.menu("Help").menu("About VirSat 4 - Core").click();
		bot.button("Installation Details").click();

		bot.cTabItem("Plug-ins").activate();
		
		// Wait until plugin table is populated
		bot.waitWhile(Conditions.tableHasRows(bot.table(), 0), ASwtBotTestCase.MAX_TEST_CASE_TIMEOUT_MILLISECONDS, ASwtBotTestCase.SWTBOT_RETRY_WAIT_TIME);
		
		assertTrue("There is at least one VirSat plugin", bot.table().containsText(EXPECTED_PROVIDER));
		
		int rowCount = bot.table().rowCount();
		for (int i = 0; i < rowCount; i++) {
			SWTBotTableItem row = bot.table().getTableItem(i);
			String pluginId = row.getText(COLUMN_PLUGIN_ID);
			if (pluginId.startsWith(VIRSAT_PACKAGE_PREFIX)) {
				bot.table().select(i);
				assertEquals("Correct provider for plugin " + pluginId, EXPECTED_PROVIDER, row.getText(1));
				assertTrue("Licensing information is set (about.html is present and added to build.properties) for plugin " + pluginId, bot.button("Legal Info").isEnabled());
			}
		}
		
		// Close installation details and about window
		bot.button("Close").click();
		bot.button("Close").click();
	}
	
}
