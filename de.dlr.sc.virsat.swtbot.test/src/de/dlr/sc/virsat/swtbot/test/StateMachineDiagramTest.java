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
import static org.junit.Assert.assertNull;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;



/**
 *
 * SWTBot test class for testing State machine Diagrams
 *
 */
public class StateMachineDiagramTest extends ASwtBotTestCase {

	private static final int STATE_DISTANCE_OFFSET = 50;
	private SWTBotTreeItem repositoryNavigatorItem;
	private SWTBotTreeItem configurationTree;
	private SWTBotTreeItem elementConfiguration;
	private SWTBotGefEditor diagramEditor;

	private Concept conceptStateMachine;



	/**
	 *Sets up the stage for state machine SWTBot diagram tests to run, by opening a
	 *blank stateMachines diagram in the editor
	 */
	@Override
	@Before
	public void before() throws Exception {
		super.before();
		conceptStateMachine = ConceptXmiLoader.loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.statemachines.Activator.getPluginId() + "/concept/concept.xmi");

		repositoryNavigatorItem = bot.tree().expandNode(SWTBOT_TEST_PROJECTNAME, "Repository");
		configurationTree = addElement(ConfigurationTree.class, conceptPs, repositoryNavigatorItem);
		elementConfiguration = addElement(ElementConfiguration.class, conceptPs, configurationTree);

		createNewDiagramForTreeItem(configurationTree, DiagramType.stateMachines);
		diagramEditor = getOpenedDiagramEditorbyTitle("newDiagram");
	}


	/**
	 *Drag and Drop state machine tree item to diagram editor to add a state machine
	 *Try to undo/redo this action
	 */
	@Test
	public void addStateMachineDiagramElementUndoRedoTest() {
		SWTBotTreeItem stateMachineTreeItem = addElement(StateMachine.class, conceptStateMachine, elementConfiguration);

		SWTBotGefEditPart stateMachineEditPart = diagramEditor.getEditPart("StateMachine");
		assertNull(stateMachineEditPart);

		dragTreeItemToDiagramEditor(stateMachineTreeItem, diagramEditor);
		stateMachineEditPart = diagramEditor.getEditPart("StateMachine");
		assertNotNull(stateMachineEditPart);

		undo();
		stateMachineEditPart = diagramEditor.getEditPart("StateMachine");
		assertNull(stateMachineEditPart);

		redo();
		stateMachineEditPart = diagramEditor.getEditPart("StateMachine");
		assertNotNull(stateMachineEditPart);
	}

	/**
	 *Add state to state machine
	 *Try to undo/redo this action
	 */
	@Test
	public void addStateToStateMachineDiagramElementUndoRedoTest() {
		SWTBotTreeItem stateMachineTreeItem = addElement(StateMachine.class, conceptStateMachine, elementConfiguration);
		dragTreeItemToDiagramEditor(stateMachineTreeItem, diagramEditor);

		SWTBotGefEditPart stateEditPart = diagramEditor.getEditPart("State");
		assertNull(stateEditPart);

		diagramEditor.activateTool("State");
		SWTBotGefEditPart stateMachineEditPart = diagramEditor.getEditPart("StateMachine");

		Point centerForEditPart = getCenterForEditPart(stateMachineEditPart);
		diagramEditor.click(centerForEditPart.x, centerForEditPart.y);


		stateEditPart = diagramEditor.getEditPart("State");
		assertNotNull(stateEditPart);

		undo();
		stateEditPart = diagramEditor.getEditPart("State");
		assertNull(stateEditPart);

		redo();
		stateEditPart = diagramEditor.getEditPart("State");
		assertNotNull(stateEditPart);
	}


	/**
	 *Add transition for two states
	 *Try to undo/redo this action
	 */
	@Test
	public void addTransitionForStatesUndoRedoTest() {
		SWTBotTreeItem stateMachineTreeItem = addElement(StateMachine.class, conceptStateMachine, elementConfiguration);
		dragTreeItemToDiagramEditor(stateMachineTreeItem, diagramEditor);

		SWTBotGefEditPart stateMachineEditPart = diagramEditor.getEditPart("StateMachine");
		Point centerForEditPart = getCenterForEditPart(stateMachineEditPart);

		diagramEditor.activateTool("State");
		diagramEditor.click(centerForEditPart.x, centerForEditPart.y - STATE_DISTANCE_OFFSET);
		diagramEditor.activateTool("State");
		diagramEditor.click(centerForEditPart.x, centerForEditPart.y + STATE_DISTANCE_OFFSET);


		SWTBotGefEditPart state1EditPart = stateMachineEditPart.children().get(0);
		SWTBotGefEditPart state2EditPart = stateMachineEditPart.children().get(1);

		diagramEditor.activateTool("Transition");
		diagramEditor.click(state1EditPart);
		diagramEditor.click(state2EditPart);

		SWTBotGefEditPart transitionEditPart = diagramEditor.getEditPart("Transition");
		assertNotNull(transitionEditPart);

		undo();
		transitionEditPart = diagramEditor.getEditPart("Transition");
		assertNull(transitionEditPart);

		redo();
		transitionEditPart = diagramEditor.getEditPart("Transition");
		assertNotNull(transitionEditPart);
	}
}