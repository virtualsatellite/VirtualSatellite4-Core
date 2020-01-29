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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsArrayWithSize.arrayWithSize;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.Document;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;

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
		repositoryNavigatorItem = bot.tree().expandNode(PROJECTNAME, "Repository");
		configurationTree = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		elementConfiguration = addElement(ElementConfiguration.class, conceptPs, configurationTree);
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
		elementConfiguration.dragAndDrop(configurationTree2);
		waitForEditingDomainAndUiThread();
		
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
		//StructuralElementInstance ec2 = seiCt1.getChildren().stream().filter(sei -> sei.getName().equals("ec_2")).findFirst().get();
		StructuralElementInstance ec2 = seiCt1.getChildren().stream().filter(sei -> sei.getName().equals("ec_X")).findFirst().get();
		
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
}
