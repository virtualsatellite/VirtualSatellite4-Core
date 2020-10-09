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

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.budget.power.model.PowerEquipment;
import de.dlr.sc.virsat.model.extension.budget.power.model.PowerState;
import de.dlr.sc.virsat.model.extension.budget.power.model.PowerSummary;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;

public class PowerSummaryTest extends ASwtBotTestCase {
	private SWTBotTreeItem repositoryNavigatorItem;
	
	private SWTBotTreeItem configurationTree;
	private SWTBotTreeItem elementConfiguration1;
	private SWTBotTreeItem elementConfiguration2;

	private static final Double AOCS_POWER = 20.0;
	private static final Double AOCS_DUTYCYCLE = 20.0;
	
	private static final Double DATA_HANDLING_POWER = 50.0;
	private static final Double DATA_HANDLING_DUTYCYCLE = 50.0;

	private SWTBotTreeItem powerEquipment1;
	private SWTBotTreeItem powerEquipment2;

	private Concept conceptPower;
	
	@Before
	public void before() throws Exception {
		super.before();
		//create necessary items for the test
		conceptPower = ConceptXmiLoader.loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.budget.power.Activator.getPluginId() + "/concept/concept.xmi");

		repositoryNavigatorItem = bot.tree().expandNode(SWTBOT_TEST_PROJECTNAME, "Repository");

		configurationTree = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);

		elementConfiguration1 = addElement(ElementConfiguration.class, conceptPs, configurationTree);
		rename(elementConfiguration1, "AOCS");
		
		powerEquipment1 = addElement(PowerEquipment.class, conceptPower, elementConfiguration1);
		openEditor(powerEquipment1);

		bot.button("Add PowerState").click();
		bot.table().select("powerValues");
		bot.button("Drill-Down").click();
		renameField(PowerState.PROPERTY_POWER, String.valueOf(AOCS_POWER));
		renameField(PowerState.PROPERTY_DUTYCYCLE, String.valueOf(AOCS_DUTYCYCLE));

		elementConfiguration2 = addElement(ElementConfiguration.class, conceptPs, configurationTree);
		rename(elementConfiguration2, "DataHandling");
		
		powerEquipment2 = addElement(PowerEquipment.class, conceptPower, elementConfiguration2);
		openEditor(powerEquipment2);

		bot.button("Add PowerState").click();
		bot.table().select("powerValues");
		bot.button("Drill-Down").click();

		renameField(PowerState.PROPERTY_POWER, String.valueOf(DATA_HANDLING_POWER));
		renameField(PowerState.PROPERTY_DUTYCYCLE, String.valueOf(DATA_HANDLING_DUTYCYCLE));

		addElement(PowerSummary.class, conceptPower, configurationTree);
		save();
	}

	@Test
	public void calculationsForPowerSummaryTest() {
		openEditor(configurationTree);
		SWTBotTable allPropertyTable = getSWTBotTable(configurationTree, "Section for: PowerSummary");

		final int AVERAGE_POWER_INDEX = 1; //averagePower is located in second column of power summary table
		String averagePower = allPropertyTable.getTableItem(0).getText(AVERAGE_POWER_INDEX);
		final String expectedAveragePower = AOCS_POWER * (AOCS_DUTYCYCLE / 100) + DATA_HANDLING_POWER * (DATA_HANDLING_DUTYCYCLE / 100) + "00 [W]";
		assertEquals(expectedAveragePower, averagePower);

		final int MIN_POWER_INDEX = 2; // minPower is located in third column of power summary table
		String minPower = allPropertyTable.getTableItem(0).getText(MIN_POWER_INDEX);
		final String expectedMinPower = AOCS_POWER + DATA_HANDLING_POWER + "00 [W]";
		assertEquals(expectedMinPower, minPower);
	}
}
