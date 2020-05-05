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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
	
	private static final String ELEMENT_CONFIGURATION_NAME = "ElementConfiguration";

	/**
	 *Sets up the stage for FuncElectrical SWTBot diagram tests to run, by opening a
	 *blank interfaces diagram in the editor
	 */
	@Before
	public void before() throws Exception {
		super.before();
		conceptFuncElectrical = ConceptXmiLoader.loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.funcelectrical.Activator.getPluginId() + "/concept/concept.xmi");
		repositoryNavigatorItem = bot.tree().expandNode(SWTBOT_TEST_PROJECTNAME, "Repository");
		configurationTree = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		elementConfiguration = addElement(ElementConfiguration.class, conceptPs, configurationTree);
				
		createNewDiagramForTreeItem(configurationTree, DiagramType.interfaces);
		diagramEditor = getOpenedDiagramEditorbyTitle("newDiagram");
	}
	
	/**
	 *Drag and Drop element configuration tree item to diagram editor to add an interface
	 *Try to undo/redo this action
	 */
	@Test
	public void addInterfaceDiagramElementUndoRedoTest() {
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor);
		assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
		undo();
		assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
		redo();
		assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
	}	

	/**
	 * Update diagram after displayed element has been deleted from somewhere else 
	 * than within the editor itself
	 */
	@Test
	public void deleteObjectOutsideDiagramUpdateDiagramTest() {
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor);
		assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
		
		delete(elementConfiguration);
		updateActiveDiagram(diagramEditor);
		assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
	}
	
	/**
	 * Drag and Drop configuration tree to editor
	 */
	@Test
	public void dragDropTreeinDiagram() {
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
		dragTreeItemToDiagramEditor(configurationTree, diagramEditor);
		assertTrue(diagramEditor.selectedEditParts().isEmpty());
	}
	
	/**
	 * Adds an interface end to the diagram editor
	 * Try to undo/redo this action
	 */
	@Test
	public void addInterfaceEndDiagramElementUndoRedoTest() {
		addElement(InterfaceEnd.class, conceptFuncElectrical, elementConfiguration);
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor);
		assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
		undo();
		assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
		redo();
		assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
	}
	
	/**
	 * Drag and Drop interface type to interface ends
	 */
	@Test
	public void dragDropAssignInterfaceTypeTest() {
		SWTBotTreeItem interfaceTypeCollectionItem = addElement(InterfaceTypeCollection.class, conceptFuncElectrical, repositoryNavigatorItem);
		SWTBotTreeItem interfaceTypeItem = addElement(InterfaceType.class, conceptFuncElectrical, interfaceTypeCollectionItem);
		SWTBotTreeItem interfaceEndItem = addElement(InterfaceEnd.class, conceptFuncElectrical, elementConfiguration);
		
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor, 0, 0);
		assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
		
		SWTBotGefEditPart interfaceConfiguration = diagramEditor.getEditPart(ELEMENT_CONFIGURATION_NAME);
		SWTBotGefEditPart interfaceEnd = interfaceConfiguration.children().get(0);

		dragTreeItemOnToEditPart(interfaceTypeItem, diagramEditor, interfaceEnd);
				
		String text = interfaceEndItem.expand().getNode(0).getText();
		assertTrue(text.equals("type -> InterfaceType"));		
	}
	
	/**
	 * Removes interface diagram element
	 * Try to undo/redo this action
	 */
	@Test
	public void removeInterfaceDiagramElementUndoRedoTest() {
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor);
		assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
		
		removeEditPartInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME);
		assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));		
		
		undo();
		assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
		redo();
		assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
	}
	
	/**
	 * Deletes interface diagram element
	 * Try to undo/redo this action
	 */
	@Test
	public void deleteInterfaceDiagramElementUndoRedoTest() {
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor);
		assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
		
		String elementConfigurationName = elementConfiguration.getText();
		
		deleteEditPartInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME);
		assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
		assertFalse(isTreeItemPresentInTreeView(elementConfiguration));
		
		diagramEditor.setFocus();
		
		undo();
		assertTrue(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
		
		SWTBotTreeItem elementConfigurationNode = configurationTree.getNode(elementConfigurationName);
		assertNotNull(elementConfigurationNode);
		
		diagramEditor.setFocus();

		redo();
		assertFalse(isEditPartPresentInDiagramEditor(diagramEditor, ELEMENT_CONFIGURATION_NAME));
		assertFalse(isTreeItemPresentInTreeView(elementConfigurationNode));
	}
	
	/**
	 * Connect two interface ends
	 */
	@Test
	public void connectInterfaceEndsTest() {		
		addElement(Interface.class, conceptFuncElectrical, elementConfiguration);
		
		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor, DRAG1_COORDINATES_X, DRAG1_COORDINATES_Y);
		SWTBotGefEditPart interfaceConfiguration1 = diagramEditor.selectedEditParts().get(0);		

		dragTreeItemToDiagramEditor(elementConfiguration, diagramEditor, DRAG2_COORDINATES_X, DRAG2_COORDINATES_Y);
		SWTBotGefEditPart interfaceConfiguration2 = diagramEditor.selectedEditParts().get(0);		
		
		diagramEditor.activateTool("InterfaceEnd");
		diagramEditor.click(interfaceConfiguration1);
		
		diagramEditor.activateTool("InterfaceEnd");
		diagramEditor.click(interfaceConfiguration2);
		
		diagramEditor.activateTool("Interface");
		
		SWTBotGefEditPart interfaceEnd1 = interfaceConfiguration1.children().get(0);
		SWTBotGefEditPart interfaceEnd2 = interfaceConfiguration2.children().get(0);
		
		diagramEditor.click(interfaceEnd1);
		diagramEditor.click(interfaceEnd2);
		bot.button("OK").click();
		
		SWTBotGefEditPart interfaceEnd1Connections = interfaceEnd1.children().get(0);
		
		List<SWTBotGefConnectionEditPart> interfaceEnd1SourceConnections = interfaceEnd1Connections.sourceConnections();
		assertEquals(1, interfaceEnd1SourceConnections.size());
	}
}
