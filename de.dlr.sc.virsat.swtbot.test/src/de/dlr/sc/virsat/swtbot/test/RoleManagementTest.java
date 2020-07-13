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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTree;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTreeDomain;

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
		expand(navigatorRootItem);
		navigatorRootItem.getNode("Role Management").select();
		expand(navigatorRootItem.getNode("Role Management"));
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
		expand(problemView.bot().tree().getTreeItem("Warnings (2 items)"));
		problemView.bot().tree().getTreeItem("Warnings (2 items)").getNode("There are duplicate names in namespace: 'SubSystem'.").select();

		// Now change the discipline name, save again and check the warnings disappear
		newDisciplineTableItem.click(0);
		SWTBotEditor rmEditor = bot.editorByTitle("Role Management");
		rmEditor.bot().text().setText("SubSystemB");
		rmEditor.bot().table().unselect();
		buildCounter.executeInterlocked(() -> save()); 
		
		final int NUMBER_OF_TRIES = 5;
		assertForTimes("Waiting for problem messages to dissapear", NUMBER_OF_TRIES, () -> {
			SWTBotView problemView2 = bot.viewById("org.eclipse.ui.views.ProblemView");
			problemView2.show();
			SWTBotTreeItem[] test = problemView2.bot().tree().getAllItems();
			return test.length == 0;
		});
	}
	
	@Test
	public void testAssignDisciplineToOtherByRepository() {
		openRoleManagementEditor();
		
		// Add a disciple for the Repository
		SWTBotTableItem newDisciplineTableItem = createNewDiscipline("Repository", null);
		
		// Open the Repository editor
		bot.viewByTitle("VirSat Navigator").show();
		SWTBotTreeItem navigatorRootItem = bot.tree().getTreeItem(SWTBOT_TEST_PROJECTNAME);
		expand(navigatorRootItem);
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
	
	@Test
	public void testAssignDisciplineToOtherByEditor() {
		openRoleManagementEditor();
		
		// Add a disciple for the Repository
		createNewDiscipline("Repository", null);
		createNewDiscipline("Repository2", "other_user");
		
		// Open the Repository editor
		bot.viewByTitle("VirSat Navigator").show();
		SWTBotTreeItem navigatorRootItem = bot.tree().getTreeItem(SWTBOT_TEST_PROJECTNAME);
		expand(navigatorRootItem);
		navigatorRootItem.getNode("Repository").select();
		navigatorRootItem.getNode("Repository").doubleClick();
		
		// Change the discipline in the Repository editor
		bot.editorByTitle("Repository").show();
		bot.comboBox().setSelection("Discipline: Repository");
		bot.button("Apply Discipline").click();
		
		save();
		
		assertTrue("The user should still be able to edit the discipline", bot.button("Apply Discipline").isEnabled());
		
		// Change the discipline in the Repository editor
		bot.editorByTitle("Repository").show();
		bot.comboBox().setSelection("Discipline: Repository2");
		bot.button("Apply Discipline").click();
		
		save();
		
		assertFalse("The discipline has become non-edible", bot.button("Apply Discipline").isEnabled());
	}
	
	@Test
	public void testApplyDisciplineRecursive() {
		openRoleManagementEditor();
		
		// Create two disciplines, one that will be used for the whole PTD
		// and one for a special ED.
		// Both are initially owned by the current user.
		createNewDiscipline("Domain_One", null);
		createNewDiscipline("Domain_Two", "other_user");
		createNewDiscipline("Domain_Three", "third_user");
		
		// Create a PT and PTD
		bot.viewByTitle("VirSat Navigator").show();		
		SWTBotTreeItem repositoryNavigatorItem = bot.tree().expandNode(SWTBOT_TEST_PROJECTNAME, "Repository");
		SWTBotTreeItem productTree = addElement(ProductTree.class, conceptPs, repositoryNavigatorItem);
		SWTBotTreeItem productTreeDomain = addElement(ProductTreeDomain.class, conceptPs, productTree);
		
		save();
		
		// Assign discipline of PTD
		openEditor(productTreeDomain);
		bot.comboBox().setSelection("Discipline: Domain_One");
		bot.button("Apply Discipline").click();
		assertTrue("The PTD can be edited", bot.button("Apply Discipline").isEnabled());
		
		// Create two EDs that inherit the discipline and give them unique names
		SWTBotTreeItem elementDefinition = addElement(ElementDefinition.class, conceptPs, productTreeDomain);
		openEditor(elementDefinition);
		bot.text(1).setText("ED1");
		
		SWTBotTreeItem elementDefinition2 = addElement(ElementDefinition.class, conceptPs, productTreeDomain);
		openEditor(elementDefinition2);
		bot.text(1).setText("ED2");
		
		// This ED is assigned to the third domain which will not be updated recursively
		SWTBotTreeItem elementDefinition3 = addElement(ElementDefinition.class, conceptPs, productTreeDomain);
		openEditor(elementDefinition3);
		bot.text(1).setText("ED3");
		
		bot.comboBox().setSelection("Discipline: Domain_Three");
		bot.button("Apply Discipline").click();
		
		save();
		
		// Check ED1 and ED2 are editable. Three isn'T since it is discipline three
		openEditor(elementDefinition);
		bot.editorByTitle("ED: ED1 -> ProductTree.ProductTreeDomain.ED1").show();
		assertTrue("The ED can be edited", bot.button("Apply Discipline").isEnabled());
		
		bot.editorByTitle("ED: ED2 -> ProductTree.ProductTreeDomain.ED2").show();
		assertTrue("The ED can be edited", bot.button("Apply Discipline").isEnabled());
		
		bot.editorByTitle("ED: ED3 -> ProductTree.ProductTreeDomain.ED3").show();
		assertFalse("The ED can not be edited", bot.button("Apply Discipline").isEnabled());
		
		// Now change the user of the PTD discipline recursively
		// So the PTD and ED2 should become non edible
		bot.editorByTitle("PTD: ProductTreeDomain -> ProductTree.ProductTreeDomain").show();
		bot.comboBox().setSelection("Discipline: Domain_Two");
		bot.button("Apply Discipline Recursive").click();
		
		save();
		
		// Check that the correct editors are non edible
		bot.editorByTitle("PTD: ProductTreeDomain -> ProductTree.ProductTreeDomain").show();
		assertFalse("The PTD is non edible now", bot.button("Apply Discipline").isEnabled());
		
		bot.editorByTitle("ED: ED1 -> ProductTree.ProductTreeDomain.ED1").show();
		assertFalse("This ED is non edible now", bot.button("Apply Discipline").isEnabled());
		
		bot.editorByTitle("ED: ED2 -> ProductTree.ProductTreeDomain.ED2").show();
		assertFalse("This ED is non edible now", bot.button("Apply Discipline").isEnabled());
		
		bot.editorByTitle("ED: ED3 -> ProductTree.ProductTreeDomain.ED3").show();
		assertEquals("Discipline of ED3 is still with third_user", "Discipline: Domain_Three", bot.comboBox().getText());
	}
}
