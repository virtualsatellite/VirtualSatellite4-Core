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


import java.util.List;

import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
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
	private SWTBotGefEditor diagramEditor;
	
	private Concept conceptFuncElectrical;
	
	private static final int DRAG1_COORDINATES_X = 0;
	private static final int DRAG1_COORDINATES_Y = 0;
	
	private static final int DRAG2_COORDINATES_X = 250;
	private static final int DRAG2_COORDINATES_Y = 100;
	
	private static final int CLICK1_COORDINATES_X = 150;
	private static final int CLICK1_COORDINATES_Y = 10;
	private static final int CLICK2_COORDINATES_X = 260;
	private static final int CLICK2_COORDINATES_Y = 110;

	@Before
	public void before() throws Exception {
		super.before();
		// create the necessary items for the test
		conceptFuncElectrical = ConceptXmiLoader.loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.funcelectrical.Activator.getPluginId() + "/concept/concept.xmi");
		repositoryNavigatorItem = bot.tree().expandNode(SWTBOT_TEST_PROJECTNAME, "Repository");
		configurationTree = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		elementConfiguration = addElement(ElementConfiguration.class, conceptPs, configurationTree);
				
		createNewDiagramForTreeItem(configurationTree, DiagramType.interfaces);
		diagramEditor = getOpenedDiagramEditorbyTitle("newDiagram");
	}
	
	@Test
	public void addInterfaceDiagramElementUndoRedoTest() {
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
		waitForEditingDomainAndUiThread();
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor);
		waitForEditingDomainAndUiThread();
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		undoCommand();
		waitForEditingDomainAndUiThread();
		Assert.assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		redoCommand();
		waitForEditingDomainAndUiThread();
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
	}
	

//	@Test
//	public void deleteObjectOutsideDiagramUpdateDiagramTest() {
//		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
//		waitForEditingDomainAndUiThread();
//		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor);
//		waitForEditingDomainAndUiThread();
//		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
//		
//		delete(elementConfiguration);
//		Assert.assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
//	}
	
	@Test
	public void dragDropTreeinDiagram() {
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
		waitForEditingDomainAndUiThread();
		dragTreeItemToDiagramEditor(configurationTree, diagramEditor);
		waitForEditingDomainAndUiThread();
		Assert.assertEquals(0, diagramEditor.selectedEditParts().size(), 0);
		
		undoCommand();
		waitForEditingDomainAndUiThread();
	}
	
	@Test
	public void addInterfaceEndDiagramElementUndoRedoTest() {
		addElement(InterfaceEnd.class, conceptFuncElectrical, elementConfiguration);
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor);
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		undoCommand();
		waitForEditingDomainAndUiThread();
		Assert.assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		redoCommand();
		waitForEditingDomainAndUiThread();
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
	}
	
	@Test
	public void removeInterfaceDiagramElementUndoRedoTest() {
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor);
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		
		removeEditPartInDiagramEditor(diagramEditor, "ElementConfiguration");
		Assert.assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));		
		
		undoCommand();
		waitForEditingDomainAndUiThread();
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		redoCommand();
		waitForEditingDomainAndUiThread();
		Assert.assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
	}
	
	@Test
	public void deleteInterfaceDiagramElementUndoRedoTest() {
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor);
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		
		String elementConfigurationName = elementConfiguration.getText();
		
		deleteEditPartInDiagramEditor(diagramEditor, "ElementConfiguration");
		Assert.assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		Assert.assertFalse(isTreeItemPresentInTreeView(elementConfiguration));
		
		diagramEditor.setFocus();
		
		undoCommand();
		waitForEditingDomainAndUiThread();
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		
		SWTBotTreeItem elementConfigurationNode = configurationTree.getNode(elementConfigurationName);
		Assert.assertNotNull(elementConfigurationNode);
		
		diagramEditor.setFocus();

		redoCommand();
		waitForEditingDomainAndUiThread();
		Assert.assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		Assert.assertFalse(isTreeItemPresentInTreeView(elementConfigurationNode));

	}
	
	@Test
	public void connectInterfaceEndsTest() {		
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
		waitForEditingDomainAndUiThread();
		
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor, DRAG1_COORDINATES_X, DRAG1_COORDINATES_Y);
		waitForEditingDomainAndUiThread();				
		SWTBotGefEditPart swtBotGefEditPart1 = diagramEditor.selectedEditParts().get(0);
		
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor, DRAG2_COORDINATES_X, DRAG2_COORDINATES_Y);
		waitForEditingDomainAndUiThread();
		SWTBotGefEditPart swtBotGefEditPart2 = diagramEditor.selectedEditParts().get(0);
		
		diagramEditor.activateTool("InterfaceEnd");
		waitForEditingDomainAndUiThread();
		
		diagramEditor.click(CLICK1_COORDINATES_X, CLICK1_COORDINATES_Y);
		waitForEditingDomainAndUiThread();
		
		diagramEditor.activateTool("InterfaceEnd");
		waitForEditingDomainAndUiThread();
		
		diagramEditor.click(CLICK2_COORDINATES_X, CLICK2_COORDINATES_Y);
		waitForEditingDomainAndUiThread();
		
		diagramEditor.activateTool("Interface");
		waitForEditingDomainAndUiThread();
		swtBotGefEditPart1.children().get(0).click();
		waitForEditingDomainAndUiThread();
		swtBotGefEditPart2.children().get(0).click();
		waitForEditingDomainAndUiThread();
		
		bot.button("OK").click();
		waitForEditingDomainAndUiThread();
		
		List<SWTBotGefConnectionEditPart> sourceConnections = swtBotGefEditPart1.children().get(0).children().get(0).sourceConnections();
		
		waitForEditingDomainAndUiThread();
		Assert.assertEquals(1, sourceConnections.size(), 0);		
	}
}
