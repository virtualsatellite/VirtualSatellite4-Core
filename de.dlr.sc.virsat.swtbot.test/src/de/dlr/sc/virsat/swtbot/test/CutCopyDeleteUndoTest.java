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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsArrayWithSize.arrayWithSize;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.Document;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.project.ui.structure.dialog.ReferencedDeleteDialog;

/**
 * This class tests the cut copy delete and paste an Sei 
 * @author bell_Er
 *
 */
public class CutCopyDeleteUndoTest extends ASwtBotTestCase {
	
	SWTBotTreeItem repositoryNavigatorItem;
	SWTBotTreeItem configurationTree;
	SWTBotTreeItem elementConfiguration;
	
	@Before
	public void before() throws Exception {
		super.before();
		repositoryNavigatorItem = bot.tree().expandNode(SWTBOT_TEST_PROJECTNAME, "Repository");
		configurationTree = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		elementConfiguration = addElement(ElementConfiguration.class, conceptPs, configurationTree);
	}
	
	@Override
	public void tearDown() throws CoreException, IOException {
		// In case of cut and paste actions on a SEI, the editing domain is not triggered for a save
		// operation. Since the SEI has been well stored to a parent before, it does not to be stored 
		// after a cut and paste. If the state would get lost (i.e. Not Saving), the SEI would be back
		// nested to it's previous parent. This is a different story for copy and paste, here new SEIs
		// are created which have never been nested before. Here the Create SEI Command will trigger 
		// save accordingly.
		// To avoid the save dialog popping up, we save all editors before shutting down the workbench
		SWTBotMenu saveAllBtn = bot.menu("File").menu("Save All");
		if (saveAllBtn.isEnabled()) {
			buildCounter.executeInterlocked(saveAllBtn::click);
		}
		super.tearDown();
	}
	
	@Test
	public void cutCopyDeleteSeiTest() throws InterruptedException {
		// test delete undo
		delete(elementConfiguration);
		assertThat("An EC got deleted", configurationTree.getItems(), arrayWithSize(1));
		undo(configurationTree);
		assertThat("The EC came back", configurationTree.getItems(), arrayWithSize(2));
		
		// test copy paste
		int oldItemCount = repositoryNavigatorItem.getItems().length;
		copy(configurationTree);
		paste(repositoryNavigatorItem);
		assertThat("Pasted one element more", repositoryNavigatorItem.getItems(), arrayWithSize(oldItemCount + 1));

		// test cut paste		
		SWTBotTreeItem configurationTree2 = repositoryNavigatorItem.getNode("CT: ConfigurationTree_2");
		elementConfiguration = configurationTree.getNode("EC: ElementConfiguration");
		cut(elementConfiguration);
		paste(configurationTree2);
		
		StructuralElementInstance seiCt1 = getRepository(project).getRootEntities().stream().filter(sei -> sei.getName().equals("ConfigurationTree")).findFirst().get();
		StructuralElementInstance seiCt2 = getRepository(project).getRootEntities().stream().filter(sei -> sei.getName().equals("ConfigurationTree_2")).findFirst().get();

		assertNotNull("Correct SEI exists", seiCt1);
		assertNotNull("Correct SEI exists", seiCt2);
		
		assertThat("Found correct amount of children", seiCt1.getChildren(), hasSize(0));
		assertThat("Found correct amount of children", seiCt2.getChildren(), hasSize(2));
	}

	@Test
	public void dragAndDropMoveSei() throws InterruptedException {
		// Create the Copy of the ConfiguratrionTree
		int oldItemCount = repositoryNavigatorItem.getItems().length;
		copy(configurationTree);
		paste(repositoryNavigatorItem);
		assertThat("Pasted one element more", repositoryNavigatorItem.getItems(), arrayWithSize(oldItemCount + 1));

		// test cut paste		
		SWTBotTreeItem configurationTree2 = repositoryNavigatorItem.getNode("CT: ConfigurationTree_2");
		elementConfiguration = configurationTree.getNode("EC: ElementConfiguration");
		
		// Now do the drag and drop move operation
		buildCounter.executeInterlocked(() -> {
			elementConfiguration.dragAndDrop(configurationTree2);
		});
		
		StructuralElementInstance seiCt1 = getRepository(project).getRootEntities().stream().filter(sei -> sei.getName().equals("ConfigurationTree")).findFirst().get();
		StructuralElementInstance seiCt2 = getRepository(project).getRootEntities().stream().filter(sei -> sei.getName().equals("ConfigurationTree_2")).findFirst().get();

		assertNotNull("Correct SEI exists", seiCt1);
		assertNotNull("Correct SEI exists", seiCt2);
		
		assertThat("Found correct amount of children", seiCt1.getChildren(), hasSize(0));
		assertThat("Found correct amount of children", seiCt2.getChildren(), hasSize(2));
	}

	@Test
	public void cutCopyDeleteCaTest() {
		SWTBotTreeItem document = addElement(Document.class, conceptPs, elementConfiguration);
			
		// test delete undo, one element remains because of the documents folder
		delete(document);
		assertThat("An document got deleted", elementConfiguration.getItems(), arrayWithSize(1));
		
		undo(elementConfiguration);
		assertThat("An document came back", elementConfiguration.getItems(), arrayWithSize(2));
		
		// test copy paste
		// CHECKSTYLE:OFF
		document = elementConfiguration.getNode("D: Document");
		copy(document);
		paste(elementConfiguration);
		assertThat("Pasted second document", elementConfiguration.getItems(), arrayWithSize(3));
		// CHECKSTYLE:ON
		
		// Add a new element configuration into the config tree where we want to paste the document
		rename(elementConfiguration, "ec_1");
		SWTBotTreeItem newElementConfiguration = addElement(ElementConfiguration.class, conceptPs, configurationTree);
		rename(newElementConfiguration, "ec_2");
		
		// Cut the document from its previous place and copy it to the new place
		cut(document);
		paste(newElementConfiguration);

		StructuralElementInstance seiCt1 = getRepository(project).getRootEntities().stream().filter(sei -> sei.getName().equals("ConfigurationTree")).findFirst().get();

		StructuralElementInstance ec1 = seiCt1.getChildren().stream().filter(sei -> sei.getName().equals("ec_1")).findFirst().get();
		StructuralElementInstance ec2 = seiCt1.getChildren().stream().filter(sei -> sei.getName().equals("ec_2")).findFirst().get();
		
		assertNotNull("Correct SEI exists", ec1);
		assertNotNull("Correct SEI exists", ec2);
		
		assertThat("Found correct amount of documents", ec1.getCategoryAssignments(), hasSize(1));
		assertThat("Found correct amount of documents", ec2.getCategoryAssignments(), hasSize(1));
	}
	
	@Test
	public void dragAndDropMoveCa() {
		SWTBotTreeItem document = addElement(Document.class, conceptPs, elementConfiguration);
		
		// Add a new element configuration into the config tree where we want to paste the document
		rename(elementConfiguration, "ec_1");
		SWTBotTreeItem newElementConfiguration = addElement(ElementConfiguration.class, conceptPs, configurationTree);
		rename(newElementConfiguration, "ec_2");
		
		// now move the document with drag and drop
		document.dragAndDrop(newElementConfiguration);
		waitForEditingDomainAndUiThread();

		StructuralElementInstance seiCt1 = getRepository(project).getRootEntities().stream().filter(sei -> sei.getName().equals("ConfigurationTree")).findFirst().get();

		StructuralElementInstance ec1 = seiCt1.getChildren().stream().filter(sei -> sei.getName().equals("ec_1")).findFirst().get();
		StructuralElementInstance ec2 = seiCt1.getChildren().stream().filter(sei -> sei.getName().equals("ec_2")).findFirst().get();
		
		assertNotNull("Correct SEI exists", ec1);
		assertNotNull("Correct SEI exists", ec2);
		
		assertThat("Found correct amount of documents", ec1.getCategoryAssignments(), hasSize(0));
		assertThat("Found correct amount of documents", ec2.getCategoryAssignments(), hasSize(1));
	}
	
	@Test
	public void deleteReferencedObjectsTest() {
		rename(elementConfiguration, "ec_1");
		SWTBotTreeItem ife = addElement(InterfaceEnd.class, conceptFea, elementConfiguration);

		SWTBotTreeItem ec2 = addElement(ElementConfiguration.class, conceptPs, configurationTree);
		rename(ec2, "ec_2");
		SWTBotTreeItem interf = addElement(Interface.class, conceptFea, ec2);
		
		// Set a reference from IF to IFE
		openEditor(interf);
		bot.button("Select Reference").click();
		bot.tree().getTreeItem("CT: ConfigurationTree").select();
		bot.table().select("IFE: InterfaceEnd - ConfigurationTree.ec_1.InterfaceEnd");
		bot.button("OK").click();

		// Try to delete referenced CA and SEI and make sure there is a warning popup
		delete(ife);
		assertEquals(ReferencedDeleteDialog.DIALOG_TITLE, bot.activeShell().getText());

		// Check popup mentions referenced and referencing objects
		String expectedReferenced = "ConfigurationTree.ec_1.InterfaceEnd";
		String expectedReferencing = "ConfigurationTree.ec_2.Interface.interfaceEndFrom";
		assertTrue(bot.tree().expandNode(expectedReferenced).getNodes().contains(expectedReferencing));

		bot.button("No").click();
		delete(elementConfiguration);
		assertEquals(ReferencedDeleteDialog.DIALOG_TITLE, bot.activeShell().getText());
		assertTrue(bot.tree().expandNode(expectedReferenced).getNodes().contains(expectedReferencing));

		// Make sure referenced element can still be deleted
		int elementsBeforeDeletion = configurationTree.getNodes().size();
		bot.button("Yes").click();
		waitForEditingDomainAndUiThread();
		assertEquals(elementsBeforeDeletion - 1, configurationTree.getNodes().size());
	}
}
