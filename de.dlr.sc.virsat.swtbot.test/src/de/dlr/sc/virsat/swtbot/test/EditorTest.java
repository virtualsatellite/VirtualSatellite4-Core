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

import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.Document;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.swtbot.util.SwtBotSection;

/**
 * This class tests editor operations
 * @author bell_Er
 *
 */
public class EditorTest extends ASwtBotTestCase {
	private static final int THREE = 3;
	
	SWTBotTreeItem repositoryNavigatorItem;
	SWTBotTreeItem configurationTree;
	SWTBotTreeItem elementConfiguration;
	SWTBotTreeItem document;
	SWTBotTreeItem allProperty;
	
	@Before
	public void before() throws Exception {
		super.before();
		repositoryNavigatorItem = bot.tree().expandNode(SWTBOT_TEST_PROJECTNAME, "Repository");
		configurationTree = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		elementConfiguration = addElement(ElementConfiguration.class, conceptPs, configurationTree);
		document = addElement(Document.class, conceptPs, elementConfiguration);
	}
	
	@Test
	public void openEditorsforSEIsendCAsTest() {
		openEditor(configurationTree);		
		assertText(ConfigurationTree.class.getSimpleName(), bot.textWithLabel("Name"));
		openEditor(document);	
		assertText(Document.class.getSimpleName(), bot.textWithLabel("Name"));
	}
	
	@Test
	public void addAndremoveCategoriesFromSEIEditor() {
		openEditor(elementConfiguration);
		bot.button("Add Document").click();
		assertEquals(THREE, elementConfiguration.getItems().length);
		
		save();
		bot.tableWithLabel(getSectionName(Document.class)).click(0, 0);
		bot.button("Remove Document").click();
		assertEquals(2, elementConfiguration.getItems().length);
	}
	
	@Test
	public void collapseSections() {
		openEditor(elementConfiguration);
		SwtBotSection composite = getSWTBotSection(Document.class);
		composite.click();
		save();
		assertFalse(composite.isExpanded());
	}
	
	@Test 
	public void editStringProperty() {
		// Edit from editor
		openEditor(document);
		renameField(Document.PROPERTY_DOCUMENTNAME, "NewName");
		assertText("NewName", bot.textWithLabel(Document.PROPERTY_DOCUMENTNAME));
		//change string from table
		SWTBotTable documentTable = getSWTBotTable(elementConfiguration, Document.class);
		// change the value from the table
		setTableValue(documentTable, 0, 1, "NewName", "NewNewName");
		// test the new values		
		openEditor(document);
		assertText("NewNewName", bot.textWithLabel(Document.PROPERTY_DOCUMENTNAME));
	}
	
	@Test 
	public void editStringPropertyandFloatProperty() {
		allProperty = addElement(TestCategoryAllProperty.class, conceptTest, elementConfiguration);
		// Edit from editor
		openEditor(allProperty);
		renameField(TestCategoryAllProperty.PROPERTY_TESTSTRING, "NewName");
		renameField(TestCategoryAllProperty.PROPERTY_TESTFLOAT, "6.976");
		assertText("NewName", bot.textWithLabel(TestCategoryAllProperty.PROPERTY_TESTSTRING));
		assertText("6.976", bot.textWithLabel(TestCategoryAllProperty.PROPERTY_TESTFLOAT));
		SWTBotTable allPropertyTable = getSWTBotTable(elementConfiguration, TestCategoryAllProperty.class);
		// change the values from the table
		setTableValue(allPropertyTable, 0, 1, "NewName", "NewNewName");
		setTableValue(allPropertyTable, 0, THREE, "6.976", "8.569");
		
		// test the new values
		openEditor(allProperty);
		assertText("NewNewName", bot.textWithLabel(TestCategoryAllProperty.PROPERTY_TESTSTRING));
		assertText("8.569", bot.textWithLabel(TestCategoryAllProperty.PROPERTY_TESTFLOAT));	
	}
}
