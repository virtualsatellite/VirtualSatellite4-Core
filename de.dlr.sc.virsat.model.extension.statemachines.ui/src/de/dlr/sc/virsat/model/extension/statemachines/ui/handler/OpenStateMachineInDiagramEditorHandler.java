/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.handler;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.editor.DiagramEditor;

import de.dlr.sc.virsat.graphiti.ui.handler.AOpenInDiagramEdiorHandler;

/**
 * A class that handles opening state machines in the state machine editor
 *
 */

public class OpenStateMachineInDiagramEditorHandler extends AOpenInDiagramEdiorHandler {

	private static final String TYPE_ID = "stateMachines";
	private static final String EDITOR_ID = "de.dlr.sc.virsat.model.extension.statemachines.ui.diagramTypeProvider.editor";
	private static final String EXTENSION = "dvlmsm";
	
	private static final int DEFAULT_X = 100;
	private static final int DEFAULT_Y = 100;
	
	@Override
	protected void onCreateNewDiagram(DiagramEditor editor, Diagram diagram) {
		AddContext addContext = new AddContext();
		addContext.setNewObject(firstSelectedEObject);
		addContext.setTargetContainer(diagram);
		addContext.setX(DEFAULT_X);
		addContext.setY(DEFAULT_Y);
		IFeatureProvider fp = editor.getDiagramTypeProvider().getFeatureProvider();
		fp.addIfPossible(addContext);
		
		ed.saveAll();
		
		editor.updateDirtyState();
	}

	@Override
	protected String getTypeID() {
		return TYPE_ID;
	}

	@Override
	protected String getEditorID() {
		return EDITOR_ID;
	}
	
	@Override
	protected String getExtension() {
		return EXTENSION;
	}
}
