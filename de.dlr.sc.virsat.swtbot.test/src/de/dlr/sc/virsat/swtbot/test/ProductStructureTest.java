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
import static org.junit.Assert.assertNotNull;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
/**
 * This class tests the Product Structure
 * @author bell_Er
 *
 */ 
public class ProductStructureTest extends ASwtBotTestCase {
	private static final int THREE = 3; 
	private SWTBotTreeItem repositoryNavigatorItem;
	
	private SWTBotTreeItem configurationTree;
	private SWTBotTreeItem aocs;
	private SWTBotTreeItem rw1;
	private SWTBotTreeItem rw2;
	
	@Before
	public void before() throws Exception {
		super.before();
		// create the necessary items for the test
		repositoryNavigatorItem = bot.tree().expandNode(SWTBOT_TEST_PROJECTNAME, "Repository");
		configurationTree = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		aocs = addElement(ElementConfiguration.class, conceptPs, configurationTree);
		rename(aocs, "AOCS");
		rw1 = addElement(ElementConfiguration.class, conceptPs, aocs);
		rename(rw1, "RW1");
		rw2 = addElement(ElementConfiguration.class, conceptPs, aocs);
		rename(rw2, "RW2");
	}
	
	@Test
	public void generateProductTest() {
		configurationTree.contextMenu().menu("Product Structure").menu("Generate Product Wizard").click();
		bot.tree().getTreeItem("EO: AOCS").select();
		bot.button("Duplicate").click();
		bot.button("Rename").click();
		bot.text().setText("BATTERY");
		renameField("Assembly Tree Name", "New AssemblyTree");
		finishDialog();
		
		SWTBotTreeItem configurationTree = repositoryNavigatorItem.getNode("AT: New AssemblyTree");
		expand(configurationTree);
		SWTBotTreeItem configurationTreeDomain = configurationTree.getNode("EO: BATTERY");
		assertNotNull(configurationTreeDomain);
		expand(configurationTreeDomain);
		SWTBotTreeItem elementDefinition = configurationTreeDomain.getNode("EO: RW1");
		assertNotNull(elementDefinition);
		int elements = configurationTreeDomain.getItems().length;
		assertEquals(THREE, elements);
	}	
	
	@Test
	public void configureProductTest() {
		configurationTree.contextMenu().menu("Product Structure").menu("Configure Product Wizard").click();
		bot.tree(1).getTreeItem("EC: AOCS").select();
		bot.button("Duplicate").click();
		bot.tree(1).getTreeItem("EC: AOCS").select();
		bot.button("Rename").click();
		bot.text().setText("NewAOCS");
		bot.tree(1).getTreeItem("EC: NewAOCS").select();
		bot.button("Add New Element").click();
		bot.tree(1).getTreeItem("EC: AOCS").select();
		bot.button("Delete Element").click();
		finishDialog();
		
		waitForEditingDomainAndUiThread();
		
		SWTBotTreeItem configurationTree = repositoryNavigatorItem.getNode("CT: ConfigurationTree");
		assertNotNull(configurationTree);
		assertEquals(2, configurationTree.getItems().length);
		
		waitForEditingDomainAndUiThread();
		
		SWTBotTreeItem aocs = configurationTree.getNode("EC: NewAOCS");
		assertNotNull(aocs);
		expand(aocs);
		assertNotNull(aocs.getNode("EC: RW1"));
		assertNotNull(aocs.getNode("EC: RW2"));
		assertNotNull(aocs.getNode("EC: ElementConfiguration"));
	}
}
