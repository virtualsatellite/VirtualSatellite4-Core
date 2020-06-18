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

import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertEnabled;
import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertNotEnabled;
import static org.eclipse.swtbot.swt.finder.waits.Conditions.widgetIsEnabled;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.qudv.ui.editor.snippets.UiSnippetQuantityKindManagement;
import de.dlr.sc.virsat.qudv.ui.editor.snippets.UiSnippetUnitManagement;
import de.dlr.sc.virsat.qudv.ui.wizards.pages.AQuantityKindWizardPage;
import de.dlr.sc.virsat.qudv.ui.wizards.pages.AUnitWizardPage;
import de.dlr.sc.virsat.swtbot.util.SwtBotSection;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetEStructuralFeatureTable;

public class QudvWizardTest extends ASwtBotTestCase {

	private SWTBotTreeItem unitManagement;

	@Override
	protected void addAllConcepts(String projectName) {
		// This test case doesnt need any concepts
		// To reduce testing time, skip the concept adding step
	}
	
	@Before
	public void before() throws Exception {
		super.before();
		unitManagement = bot.tree().expandNode(SWTBOT_TEST_PROJECTNAME, "Unit Management");
	}
	
	@Test
	public void testAddAndEditSimpleQuantityKind() {
		unitManagement.doubleClick();
		bot.editorByTitle("Unit Management");
		
		bot.button(UiSnippetQuantityKindManagement.BUTTON_ADD_TEXT).click();
		
		bot.comboBox().setSelection("QUDV Simple Quantity Kind Wizard");
		nextPageDialog();
		
		SWTBotButton finishButton = bot.button("Finish");
		
		// We cannot create a QK with empty name
		assertNotEnabled(finishButton);
		
		// We can create a QK if at least the name is set
		bot.textWithLabel(AQuantityKindWizardPage.NAME_FIELD).setText("testQk");
		assertEnabled(finishButton);
		
		// If we unset the name again, then the QK cannot be created anymore
		bot.textWithLabel(AQuantityKindWizardPage.NAME_FIELD).setText("");
		assertNotEnabled(finishButton);
		
		// Fill in some dummy test data and create a test QK
		bot.textWithLabel(AQuantityKindWizardPage.NAME_FIELD).setText("testQk");
		bot.textWithLabel(AQuantityKindWizardPage.SYMBOL_FIELD).setText("testSymbol");
		bot.textWithLabel(AQuantityKindWizardPage.DESCRIPTION_FIELD).setText("testDescription");
		bot.textWithLabel(AQuantityKindWizardPage.DEFINITION_URI_FIELD).setText("testDefinition");
		bot.waitUntil(widgetIsEnabled(finishButton));
		finishDialog();
		
		// The new qk appears in the table
		assertTrue(bot.table().containsItem("testQk"));
		
		// Change the name of the qk
		bot.table().select("testQk");
		bot.button(UiSnippetQuantityKindManagement.BUTTON_EDIT_TEXT).click();
		bot.textWithLabel(AQuantityKindWizardPage.NAME_FIELD).setText("testQkEdit");
		finishDialog();
		
		// There is no longer a qk with the old name and only a qk with the new name
		assertFalse(bot.table().containsItem("testQk"));
		assertTrue(bot.table().containsItem("testQkEdit"));
	}
	
	@Test
	public void testAddAndEditSimpleUnit() {
		unitManagement.doubleClick();
		bot.editorByTitle("Unit Management");
		
		bot.button(UiSnippetUnitManagement.BUTTON_ADD_TEXT).click();
		
		bot.comboBox().setSelection("QUDV Simple Unit Wizard");
		nextPageDialog();
		
		SWTBotButton finishButton = bot.button("Finish");
		
		// We cannot create a unit with empty name
		assertNotEnabled(finishButton);
		
		// We can create a unit if at least the name is set
		bot.textWithLabel(AUnitWizardPage.NAME_FIELD).setText("testUnit");
		assertEnabled(finishButton);
		
		// If we unset the name again, then the unit cannot be created anymore
		bot.textWithLabel(AUnitWizardPage.NAME_FIELD).setText("");
		assertNotEnabled(finishButton);
		
		// Fill in some dummy test data and create a test unit
		bot.textWithLabel(AUnitWizardPage.NAME_FIELD).setText("testUnit");
		bot.textWithLabel(AUnitWizardPage.SYMBOL_FIELD).setText("testSymbol");
		bot.textWithLabel(AUnitWizardPage.DESCRIPTION_FIELD).setText("testDescription");
		bot.textWithLabel(AUnitWizardPage.DEFINITION_URI_FIELD).setText("testDefinition");
		bot.comboBox().setSelection("Dimensionless - U");
		bot.waitUntil(widgetIsEnabled(finishButton));
		finishDialog();
		
		// Get the table containg the units
		String unitSectionName = AUiSnippetEStructuralFeatureTable.SECTION_HEADING + UiSnippetUnitManagement.SECTION_NAME;
		SwtBotSection unitSection = getSWTBotSection(unitSectionName);
		SWTBotTable unitTable = unitSection.getSWTBotTable();
		
		// The new qk appears in the table
		assertTrue(unitTable.containsItem("testUnit"));
		
		// Change the name of the unit
		unitTable.select("testUnit");
		bot.button(UiSnippetUnitManagement.BUTTON_EDIT_TEXT).click();
		bot.textWithLabel(AUnitWizardPage.NAME_FIELD).setText("testUnitEdit");
		finishDialog();
		
		// There is no longer a unit with the old name and only a unit with the new name
		assertFalse(unitTable.containsItem("testUnit"));
		assertTrue(unitTable.containsItem("testUnitEdit"));
	}
}
