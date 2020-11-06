/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.editor;

import org.eclipse.graphiti.mm.pictograms.Diagram;

import de.dlr.sc.virsat.graphiti.ui.diagram.editor.VirsatDiagramEditor;
import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * Diagram editor for State Machine diagrams.
 *
 */
public class StateMachineDiagramEditor extends VirsatDiagramEditor {
	
	@Override
	public void refreshTitle() {
		Diagram diagram = getDiagramTypeProvider().getDiagram();
		StructuralElementInstance sei = DiagramHelper.getOwningStructuralElementInstance(diagram);
		String partName = sei.getName() + " -> StateMachine Editor";
		
		setPartName(partName);
	}
}





