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
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceTypeCollection;
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
	
	private static final int DRAG1_COORDINATES_X = 100;
	private static final int DRAG1_COORDINATES_Y = 100;
	
	private static final int DRAG2_COORDINATES_X = 100;
	private static final int DRAG2_COORDINATES_Y = 200;

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
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor);
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		undoCommand();
		Assert.assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		redoCommand();
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
	}	

	@Test
	public void deleteObjectOutsideDiagramUpdateDiagramTest() {
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor);
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		
		delete(elementConfiguration);
		updateActiveDiagram(diagramEditor);
		Assert.assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
	}
	
	@Test
	public void dragDropTreeinDiagram() {
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
		dragTreeItemToDiagramEditor(configurationTree, diagramEditor);
		Assert.assertEquals(0, diagramEditor.selectedEditParts().size(), 0);
	}
	
	@Test
	public void addInterfaceEndDiagramElementUndoRedoTest() {
		addElement(InterfaceEnd.class, conceptFuncElectrical, elementConfiguration);
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor);
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		undoCommand();
		Assert.assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		redoCommand();
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
	}
	
	@Test
	public void dragDropAssignInterfaceTypeTest() {
		SWTBotTreeItem interfaceTypeCollectionItem = addElement(InterfaceTypeCollection.class, conceptFuncElectrical, repositoryNavigatorItem);
		SWTBotTreeItem interfaceTypeItem = addElement(InterfaceType.class, conceptFuncElectrical, interfaceTypeCollectionItem);
		SWTBotTreeItem interfaceEndItem = addElement(InterfaceEnd.class, conceptFuncElectrical, elementConfiguration);
		
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor, 0, 0);
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		
		SWTBotGefEditPart editPart = diagramEditor.getEditPart("ElementConfiguration");
		SWTBotGefEditPart child = editPart.children().get(0);

		dragTreeItemOnToEditPart(interfaceTypeItem, diagramEditor, child);
				
		String text = interfaceEndItem.expand().getNode(0).getText();
		Assert.assertTrue(text.equals("type -> InterfaceType"));		
	}
	
	@Test
	public void removeInterfaceDiagramElementUndoRedoTest() {
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor);
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		
		removeEditPartInDiagramEditor(diagramEditor, "ElementConfiguration");
		Assert.assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));		
		
		undoCommand();
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		redoCommand();
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
		Assert.assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		
		SWTBotTreeItem elementConfigurationNode = configurationTree.getNode(elementConfigurationName);
		Assert.assertNotNull(elementConfigurationNode);
		
		diagramEditor.setFocus();

		redoCommand();
		Assert.assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, "ElementConfiguration"));
		Assert.assertFalse(isTreeItemPresentInTreeView(elementConfigurationNode));
	}
	
	@Test
	public void connectInterfaceEndsTest() {		
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
		
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor, DRAG1_COORDINATES_X, DRAG1_COORDINATES_Y);
		SWTBotGefEditPart swtBotGefEditPart1 = diagramEditor.selectedEditParts().get(0);		

		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor, DRAG2_COORDINATES_X, DRAG2_COORDINATES_Y);
		SWTBotGefEditPart swtBotGefEditPart2 = diagramEditor.selectedEditParts().get(0);		
		
		diagramEditor.activateTool("InterfaceEnd");
		diagramEditor.click(swtBotGefEditPart1);
		
		diagramEditor.activateTool("InterfaceEnd");
		diagramEditor.click(swtBotGefEditPart2);
		
		diagramEditor.activateTool("Interface");
		diagramEditor.click(swtBotGefEditPart1.children().get(0));
		diagramEditor.click(swtBotGefEditPart2.children().get(0));
		bot.button("OK").click();
		
		List<SWTBotGefConnectionEditPart> sourceConnections = swtBotGefEditPart1.children().get(0).children().get(0).sourceConnections();
		Assert.assertEquals(1, sourceConnections.size(), 0);
	}
}
