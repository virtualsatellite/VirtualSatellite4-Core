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

import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;

import org.junit.Assert;

/**
 * 
 * SWTBot test class for testing Funcelectrical Diagrams
 *
 */
public class FuncElectricalDiagramTest extends ASwtBotTestCase {
	
	private SWTBotTreeItem repositoryNavigatorItem;
	private SWTBotTreeItem configurationTree;
	private SWTBotTreeItem elementConfiguration;	

	@Before
	public void before() throws Exception {
		super.before();
		// create the necessary items for the test
		Concept conceptFuncElectrical = ConceptXmiLoader.loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.funcelectrical.Activator.getPluginId() + "/concept/concept.xmi");
		repositoryNavigatorItem = bot.tree().expandNode(SWTBOT_TEST_PROJECTNAME, "Repository");
		configurationTree = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		elementConfiguration = addElement(ElementConfiguration.class, conceptPs, configurationTree);
		
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
	}
	
	@Test
	public void createInterfaceDiagramTest() {
		createNewDiagramForTreeItem(configurationTree, DiagramType.interfaces);
		waitForEditingDomainAndUiThread();
		SWTBotGefEditor diagramEditor = getOpenedDiagramEditorbyTitle("newDiagram");
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor);
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
	}	
}
