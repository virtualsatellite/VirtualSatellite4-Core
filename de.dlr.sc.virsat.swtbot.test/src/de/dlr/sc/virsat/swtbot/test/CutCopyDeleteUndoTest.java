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
		assertEquals(1, configurationTree.getItems().length);
		undo(configurationTree);
		assertEquals(2, configurationTree.getItems().length);
		
		// test copy paste
		int oldItemCount = repositoryNavigatorItem.getItems().length;
		copy(configurationTree);
		paste(repositoryNavigatorItem);
		assertEquals(oldItemCount + 1, repositoryNavigatorItem.getItems().length);

		// test cut paste		
		SWTBotTreeItem configurationTree2 = repositoryNavigatorItem.getNode("CT: ConfigurationTree_2");
		elementConfiguration = configurationTree.getNode("EC: ElementConfiguration");
		cut(elementConfiguration);
		paste(configurationTree2);
		
		for (StructuralElementInstance sc : getRepository(project).getRootEntities()) {
			if (sc.getName().equals("CT: ConfigurationTree_2")) {
				assertEquals(sc.getChildren().size(), 2);
			}
			if (sc.getName().equals("ConfigurationTree")) {
				assertEquals(sc.getChildren().size(), 0);
			}
		}
	}
	
	@Test
	public void cutCopyDeleteCaTest() {
		SWTBotTreeItem document = addElement(Document.class, conceptPs, elementConfiguration);
			
		// test delete undo
		delete(document);
		assertEquals(1, elementConfiguration.getItems().length);
		
		undo(elementConfiguration);
		assertEquals(2, elementConfiguration.getItems().length);
		
		// test copy paste
		document = elementConfiguration.getNode("D: Document");
		copy(document);
		paste(elementConfiguration);
		rename(elementConfiguration, "EC1");
		
		// Renaming the original document to IE1
		rename(document, "IE1");

		// Add a new element configuration into the config tree where we want to paste the document
		SWTBotTreeItem newElementConfiguration = addElement(ElementConfiguration.class, conceptPs, configurationTree);
		
		// Cut the document from its previous place and copy it to the new place
		cut(document);
		paste(newElementConfiguration);

		// now search for the Element Configuration and check if there is a new CA attached to it
		for (StructuralElementInstance sc : getRepository(project).getRootEntities()) {
			for (StructuralElementInstance scChild : sc.getChildren()) {
				if (scChild.getName().equals("ElementConfiguration")) {				
					assertEquals(1, scChild.getCategoryAssignments().size());
				}
			}
		}
	}
}
