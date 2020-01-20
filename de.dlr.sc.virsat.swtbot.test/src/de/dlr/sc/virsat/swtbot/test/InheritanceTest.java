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



import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertNotEnabled;
import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.collection.IsArrayWithSize.arrayWithSize;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTree;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.Document;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTree;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTreeDomain;

/**
 * This class tests the inheritance
 */
public class InheritanceTest extends ASwtBotTestCase {
	private SWTBotTreeItem repositoryNavigatorItem;
	
	SWTBotTreeItem productTree;
	SWTBotTreeItem productTreeDomain;
	SWTBotTreeItem elementDefinition;
	SWTBotTreeItem document;

	SWTBotTreeItem configurationTree;
	SWTBotTreeItem elementConfiguration;

	SWTBotTreeItem assemblyTree;
	SWTBotTreeItem elementOccurence;
	static final String NAME = "Name";
	
	@Before
	public void before() throws Exception {
		super.before();
		// create the necessary items for the test
		repositoryNavigatorItem = bot.tree().expandNode(PROJECTNAME, "Repository");
		productTree = addElement(ProductTree.class, conceptPs, repositoryNavigatorItem);
		productTreeDomain = addElement(ProductTreeDomain.class, conceptPs, productTree);
		elementDefinition = addElement(ElementDefinition.class, conceptPs, productTreeDomain);
		document = addElement(Document.class, conceptPs, elementDefinition);

		configurationTree = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		elementConfiguration = addElement(ElementConfiguration.class, conceptPs, configurationTree);

		assemblyTree = addElement(AssemblyTree.class, conceptPs, repositoryNavigatorItem);
		elementOccurence = addElement(ElementOccurence.class, conceptPs, assemblyTree);
	}

	@Test
	public void multiLevelInheritancePropagationUITest() {
		//Create inheritance link between two SEIs, save
		addInheritance(elementConfiguration, "PT: ProductTree", "PTD: ProductTreeDomain", "ED: ElementDefinition");
		//Create another inheritance link from sub-sub Sei to sub Sei
		addInheritance(elementOccurence, "CT: ConfigurationTree", "EC: ElementConfiguration");

		// change the super document
		rename(document, "NewDocument");	
		
		waitForAllBuildersAndUiThread();
		expand(elementConfiguration);
		waitForEditingDomainAndUiThread();
		
		SWTBotTreeItem newDocument = openEditor(elementConfiguration.getNode("D: NewDocument"));
		assertText("NewDocument", bot.textWithLabel(NAME));
		//Make change in sub sei
		renameField(Document.PROPERTY_DOCUMENTNAME, "NewName");
		save();
		assertTrue(bot.checkBoxWithLabel(Document.PROPERTY_DOCUMENTNAME).isChecked());
		
		expand(elementOccurence);
		
		assertText("NewName", bot.textWithLabel(Document.PROPERTY_DOCUMENTNAME));

		openEditor(document);
		// Change a value in super Sei in a field which is overriden in sub Sei
		renameField(Document.PROPERTY_DOCUMENTNAME, "NewNewName");
		waitForAllBuildersAndUiThread();
		openEditor(newDocument); 
		assertText("NewName", bot.textWithLabel(Document.PROPERTY_DOCUMENTNAME));

		// Remove override flag, save
		clickOnComboBox(Document.PROPERTY_DOCUMENTNAME);
		waitForAllBuildersAndUiThread();
		openEditor(newDocument); 
		assertText("NewNewName", bot.textWithLabel(Document.PROPERTY_DOCUMENTNAME));

		//Try to rename inherited CA
		renameField(NAME, "asdasdasd");
		bot.closeAllEditors();
		openEditor(newDocument); 
		assertText("NewDocument", bot.textWithLabel(NAME));

		//Try to delete an inherited CA
		assertNotEnabled(newDocument.contextMenu().menu("Delete"));

		//Delete CA which is inherited
		document.contextMenu().menu("Delete").click();
		waitForAllBuildersAndUiThread();
		assertEquals(1, elementConfiguration.getItems().length);
		assertEquals(1, elementOccurence.getItems().length);
	}
	
	@Test
	public void singleLevelInheritancePropagationUITest() {
		openEditor(document); 
		setText(Document.PROPERTY_DOCUMENTNAME, "docName");
		setText(Document.PROPERTY_NOTE, "docNote");
		addInheritance(elementConfiguration, "PT: ProductTree", "PTD: ProductTreeDomain", "ED: ElementDefinition");
		waitForAllBuildersAndUiThread();
		assertEquals("docName", bot.tableWithId("tableDocument").cell(0, Document.PROPERTY_DOCUMENTNAME));
		assertEquals("docNote", bot.tableWithId("tableDocument").cell(0, Document.PROPERTY_NOTE));
		bot.tableWithId("tableDocument").click(0, 2);
		bot.text("docNote", 0).setText("overriden note");
		
		openEditor(document); 
		setText(Document.PROPERTY_DOCUMENTNAME, "newDocName");
		setText(Document.PROPERTY_NOTE, "newNote");

		openEditor(elementConfiguration);
		waitForAllBuildersAndUiThread();
		assertEquals("newDocName", bot.tableWithId("tableDocument").cell(0, Document.PROPERTY_DOCUMENTNAME));
		assertEquals("overriden note", bot.tableWithId("tableDocument").cell(0, Document.PROPERTY_NOTE));
	}
	
	@Test
	public void inheritanceTypingDragAndDrop() {
		rename(elementConfiguration, "ec_1");
		SWTBotTreeItem elementConfiguration2 = addElement(ElementConfiguration.class, conceptPs, configurationTree);
		rename(elementConfiguration2, "ec_2");
		
		SWTBotTreeItem documentEc1 = addElement(Document.class, conceptPs, elementConfiguration);
		SWTBotTreeItem documentEc2 = addElement(Document.class, conceptPs, elementConfiguration2);

		openEditor(documentEc1);
		setText(Document.PROPERTY_DOCUMENTNAME, "docFromEc1");

		openEditor(documentEc2);
		setText(Document.PROPERTY_DOCUMENTNAME, "docFromEc2");
		
		// There are no documents yet with the ElementOccurrence
		assertThat("There is only the documents folder", elementOccurence.getItems(), arrayWithSize(1));
		
		// Now drag the first inheritance
		elementConfiguration.dragAndDrop(elementOccurence);
		waitForAllBuildersAndUiThread();
		
		openEditor(elementOccurence);
		//waitForAllBuildersAndUiThread();
		assertEquals("Received override from EC1", "docFromEc1", bot.tableWithId("tableDocument").cell(0, Document.PROPERTY_DOCUMENTNAME));
		
		// now drag the other and verify
		elementConfiguration2.dragAndDrop(elementOccurence);
		waitForAllBuildersAndUiThread();
		
		openEditor(elementOccurence);
		//waitForAllBuildersAndUiThread();
		assertEquals("Received override from EC2", "docFromEc2", bot.tableWithId("tableDocument").cell(0, Document.PROPERTY_DOCUMENTNAME));
	}
}
