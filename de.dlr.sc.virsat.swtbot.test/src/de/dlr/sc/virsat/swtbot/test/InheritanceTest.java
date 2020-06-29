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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsArrayWithSize.arrayWithSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTree;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.Document;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTree;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTreeDomain;
import de.dlr.sc.virsat.swtbot.util.SwtBotDebugHelper;

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
		SwtBotDebugHelper.logCodeLine();
		repositoryNavigatorItem = bot.tree().expandNode(SWTBOT_TEST_PROJECTNAME, "Repository");
		SwtBotDebugHelper.logCodeLine();
		productTree = addElement(ProductTree.class, conceptPs, repositoryNavigatorItem);
		SwtBotDebugHelper.logCodeLine();
		productTreeDomain = addElement(ProductTreeDomain.class, conceptPs, productTree);
		SwtBotDebugHelper.logCodeLine();
		elementDefinition = addElement(ElementDefinition.class, conceptPs, productTreeDomain);
		SwtBotDebugHelper.logCodeLine();
		document = addElement(Document.class, conceptPs, elementDefinition);

		SwtBotDebugHelper.logCodeLine();
		configurationTree = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		SwtBotDebugHelper.logCodeLine();
		elementConfiguration = addElement(ElementConfiguration.class, conceptPs, configurationTree);

		SwtBotDebugHelper.logCodeLine();
		assemblyTree = addElement(AssemblyTree.class, conceptPs, repositoryNavigatorItem);
		SwtBotDebugHelper.logCodeLine();
		elementOccurence = addElement(ElementOccurence.class, conceptPs, assemblyTree);
		SwtBotDebugHelper.logCodeLine();
	}

	@Test
	public void multiLevelInheritancePropagationUITest() {
		SwtBotDebugHelper.logCodeLine();
		
		//Create inheritance link between two SEIs, save
		SwtBotDebugHelper.logCodeLine();
		addInheritance(elementConfiguration, "PT: ProductTree", "PTD: ProductTreeDomain", "ED: ElementDefinition");
		
		//Create another inheritance link from sub-sub Sei to sub Sei
		SwtBotDebugHelper.logCodeLine();
		addInheritance(elementOccurence, "CT: ConfigurationTree", "EC: ElementConfiguration");
		
		// change the super document
		SwtBotDebugHelper.logCodeLine();
		rename(document, "NewDocument");	
		
		SwtBotDebugHelper.logCodeLine();
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		SwtBotDebugHelper.logCodeLine();
		expand(elementConfiguration);
		SwtBotDebugHelper.logCodeLine();
		waitForEditingDomainAndUiThread();
		
		SwtBotDebugHelper.logCodeLine();
		SWTBotTreeItem newDocument = openEditor(elementConfiguration.getNode("D: NewDocument"));
		SwtBotDebugHelper.logCodeLine();
		assertText("NewDocument", bot.textWithLabel(NAME));
		//Make change in sub sei
		SwtBotDebugHelper.logCodeLine();
		renameField(Document.PROPERTY_DOCUMENTNAME, "NewName");
		SwtBotDebugHelper.logCodeLine();
		save();
		SwtBotDebugHelper.logCodeLine();
		assertTrue(bot.checkBoxWithLabel(Document.PROPERTY_DOCUMENTNAME).isChecked());
		
		SwtBotDebugHelper.logCodeLine();
		expand(elementOccurence);
		
		SwtBotDebugHelper.logCodeLine();
		assertText("NewName", bot.textWithLabel(Document.PROPERTY_DOCUMENTNAME));

		SwtBotDebugHelper.logCodeLine();
		openEditor(document);
		// Change a value in super Sei in a field which is overriden in sub Sei
		SwtBotDebugHelper.logCodeLine();
		renameField(Document.PROPERTY_DOCUMENTNAME, "NewNewName");
		SwtBotDebugHelper.logCodeLine();
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		SwtBotDebugHelper.logCodeLine();
		openEditor(newDocument); 
		SwtBotDebugHelper.logCodeLine();
		assertText("NewName", bot.textWithLabel(Document.PROPERTY_DOCUMENTNAME));

		// Remove override flag, save
		SwtBotDebugHelper.logCodeLine();
		SwtBotDebugHelper.logCodeLine();
		clickOnComboBox(Document.PROPERTY_DOCUMENTNAME);
		SwtBotDebugHelper.logCodeLine();
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		SwtBotDebugHelper.logCodeLine();
		openEditor(newDocument); 
		SwtBotDebugHelper.logCodeLine();
		assertText("NewNewName", bot.textWithLabel(Document.PROPERTY_DOCUMENTNAME));

		//Try to rename inherited CA
		SwtBotDebugHelper.logCodeLine();
		renameField(NAME, "asdasdasd");
		SwtBotDebugHelper.logCodeLine();
		bot.closeAllEditors();
		SwtBotDebugHelper.logCodeLine();
		openEditor(newDocument); 
		SwtBotDebugHelper.logCodeLine();
		assertText("NewDocument", bot.textWithLabel(NAME));

		//Try to delete an inherited CA
		SwtBotDebugHelper.logCodeLine();
		assertNotEnabled(newDocument.contextMenu().menu("Delete"));

		//Delete CA which is inherited
		SwtBotDebugHelper.logCodeLine();
		document.contextMenu().menu("Delete").click();
		SwtBotDebugHelper.logCodeLine();
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		SwtBotDebugHelper.logCodeLine();
		assertEquals(1, elementConfiguration.getItems().length);
		SwtBotDebugHelper.logCodeLine();
		assertEquals(1, elementOccurence.getItems().length);
		SwtBotDebugHelper.logCodeLine();
	}
	
	@Test
	public void singleLevelInheritancePropagationUITest() {
		openEditor(document); 
		setText(Document.PROPERTY_DOCUMENTNAME, "docName");
		setText(Document.PROPERTY_NOTE, "docNote");
		addInheritance(elementConfiguration, "PT: ProductTree", "PTD: ProductTreeDomain", "ED: ElementDefinition");
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		assertEquals("docName", bot.tableWithId("tableDocument").cell(0, Document.PROPERTY_DOCUMENTNAME));
		assertEquals("docNote", bot.tableWithId("tableDocument").cell(0, Document.PROPERTY_NOTE));
		bot.tableWithId("tableDocument").doubleClick(0, 2);
		bot.text("docNote", 0).setText("overriden note");
		
		openEditor(document); 
		setText(Document.PROPERTY_DOCUMENTNAME, "newDocName");
		setText(Document.PROPERTY_NOTE, "newNote");

		openEditor(elementConfiguration);
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
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
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		
		openEditor(elementOccurence);
		assertEquals("Received override from EC1", "docFromEc1", bot.tableWithId("tableDocument").cell(0, Document.PROPERTY_DOCUMENTNAME));
		
		// now drag the other and verify
		elementConfiguration2.dragAndDrop(elementOccurence);
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		
		openEditor(elementOccurence);
		assertEquals("Received override from EC2", "docFromEc2", bot.tableWithId("tableDocument").cell(0, Document.PROPERTY_DOCUMENTNAME));
	}
	
	@Test
	public void propagateNewCATest() {
		// Create inheritance links
		addInheritance(elementConfiguration, "PT: ProductTree", "PTD: ProductTreeDomain", "ED: ElementDefinition");
		addInheritance(elementOccurence, "CT: ConfigurationTree", "EC: ElementConfiguration");

		// Add an IFE on top level
		addElement(InterfaceEnd.class, conceptFea, elementDefinition);
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		
		// Check IFE propagated via inheritance hierarchy
		expand(elementConfiguration);
		assertTrue(elementConfiguration.getNodes().contains("IFE: InterfaceEnd"));

		expand(elementOccurence);
		assertTrue(elementOccurence.getNodes().contains("IFE: InterfaceEnd"));
	}
	
	@Test
	public void clearReferenceOnDeletionTest() {
		// Create inheritance links
		addInheritance(elementConfiguration, "PT: ProductTree", "PTD: ProductTreeDomain", "ED: ElementDefinition");
		addInheritance(elementOccurence, "CT: ConfigurationTree", "EC: ElementConfiguration");

		// Add an IFE into ElementDefinition
		SWTBotTreeItem ife = addElement(InterfaceEnd.class, conceptFea, elementDefinition);
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());

		// Add an IF into ElementConfiguration
		SWTBotTreeItem interf = addElement(Interface.class, conceptFea, elementConfiguration);
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());

		// Set a reference from IF to IFE in ElementConfiguration
		openEditor(interf);
		bot.button("Select Reference").click();
		bot.tree().getTreeItem("CT: ConfigurationTree").select();
		bot.table().select("IFE: InterfaceEnd - ConfigurationTree.ElementConfiguration.InterfaceEnd");
		bot.button("OK").click();
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());
		
		assertEquals("InterfaceEnd - ElementConfiguration.InterfaceEnd", getPropertyValue(Interface.PROPERTY_INTERFACEENDFROM));
		
		// Check that inherited interface also has a properly set reference
		expand(elementOccurence);
		SWTBotTreeItem inheritedInterf = elementOccurence.getNode("IF: Interface");
		openEditor(inheritedInterf);
		assertEquals("InterfaceEnd - ElementOccurence.InterfaceEnd", getPropertyValue(Interface.PROPERTY_INTERFACEENDFROM));
		
		// Delete top-level IFE
		ife.contextMenu().menu("Delete").click();
		buildCounter.executeInterlocked(() -> bot.saveAllEditors());

		// Check that references to the IFEs are gone
		openEditor(interf);
		assertTrue(getPropertyValue(Interface.PROPERTY_INTERFACEENDFROM).isEmpty());
		openEditor(inheritedInterf);
		assertTrue(getPropertyValue(Interface.PROPERTY_INTERFACEENDFROM).isEmpty());
	}
}
