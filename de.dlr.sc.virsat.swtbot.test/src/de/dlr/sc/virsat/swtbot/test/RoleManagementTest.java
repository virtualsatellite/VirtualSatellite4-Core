/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.swtbot.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;

public class RoleManagementTest extends ASwtBotTestCase {
	

	
	@Before
	public void before() throws Exception {
		super.before();
		// create the necessary items for the test
	}
	
	/**
	 * Opens the RoleManagementEditor in the VirSat Navigator
	 */
	private void openRoleManagementEditor() {
		bot.viewByTitle("VirSat Navigator").show();
		SWTBotTreeItem navigatorRootItem = bot.tree().getTreeItem(SWTBOT_TEST_PROJECTNAME);
		navigatorRootItem.expand();
		navigatorRootItem.getNode("Role Management").select();
		navigatorRootItem.getNode("Role Management").expand();
		navigatorRootItem.getNode("Role Management").doubleClick();
	}
	
	@Test
	public void testAddNewDiscipline() {
		openRoleManagementEditor();
		
		// Switch to the Editor and add a new Discipline
		createNewDiscipline("SubSystem", null);
		save();
		
		// Add another discipline and give it the same name, this should create a warning
		SWTBotTableItem newDisciplineTableItem = createNewDiscipline("SubSystem", "other_user");
		save();
		
		// Check for the warnings
		SWTBotView problemView = bot.viewByTitle("Problems");
		problemView.show();
		problemView.bot().tree().getTreeItem("Warnings (2 items)").expand();
		problemView.bot().tree().getTreeItem("Warnings (2 items)").getNode("There are duplicate names in namespace: 'SubSystem'.").select();

		// Now change the discipline name, save again and check the warnings disappear
		newDisciplineTableItem.click(0);
		SWTBotEditor rmEditor = bot.editorByTitle("Role Management");
		rmEditor.bot().text().setText("SubSystemB");
		rmEditor.bot().table().unselect();
				
		save();
		
		//CHECKSTYLE:OFF
		
		assertForTimes("Waiting for problem messages to dissapear", 5, () ->{
			SWTBotView problemView2 = bot.viewById("org.eclipse.ui.views.ProblemView");
			problemView2.show();
			SWTBotTreeItem[] test = problemView2.bot().tree().getAllItems();
			return test.length == 0;
		});
		//CHECKSTYLE:ON
	}
	
	@Test
	public void testAssignDisciplineToOther() {
		openRoleManagementEditor();
		
		// Add a disciple for the Repository
		SWTBotTableItem newDisciplineTableItem = createNewDiscipline("Repository", null);
		
		// Open the Repository editor
		bot.viewByTitle("VirSat Navigator").show();
		SWTBotTreeItem navigatorRootItem = bot.tree().getTreeItem(SWTBOT_TEST_PROJECTNAME);
		navigatorRootItem.expand();
		navigatorRootItem.getNode("Repository").select();
		navigatorRootItem.getNode("Repository").doubleClick();
		
		// Change the discipline in the Repository editor
		bot.editorByTitle("Repository").show();
		bot.comboBox().setSelection("Discipline: Repository");
		bot.button("Apply Discipline").click();
		
		assertTrue("The user should still be able to edit the discipline", bot.button("Apply Discipline").isEnabled());
		
		// Now change the user of the Repository discipline
		bot.editorByTitle("Role Management").show();
		newDisciplineTableItem.click(1);
		SWTBotEditor rmEditor = bot.editorByTitle("Role Management");
		rmEditor.bot().text().setText("other_user");
		rmEditor.bot().table().unselect();
		
		save();
		
		// Open the Repository editor again
		bot.editorByTitle("Repository").show();
		assertFalse("The discipline has become non-edible", bot.button("Apply Discipline").isEnabled());
	}
}
