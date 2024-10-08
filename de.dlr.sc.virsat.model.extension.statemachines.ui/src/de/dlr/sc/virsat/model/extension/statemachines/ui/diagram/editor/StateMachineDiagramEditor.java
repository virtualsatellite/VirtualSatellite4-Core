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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;

import de.dlr.sc.virsat.graphiti.ui.diagram.editor.VirsatDiagramEditor;
import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.statemachines.statespace.StateSpaceExplorer;
import de.dlr.sc.virsat.model.extension.statemachines.statespace.TraceState;
import de.dlr.sc.virsat.model.extension.statemachines.ui.views.SimulatorView;

/**
 * Diagram editor for State Machine diagrams.
 *
 */
public class StateMachineDiagramEditor extends VirsatDiagramEditor {
	
	private ISelectionListener selectionListener;
	private IPartListener partListener;
	
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
		
		selectionListener = new ISelectionListener() {
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				if (!(selection instanceof IStructuredSelection)) {
					return;
				}
				
				for (var selectedElement : (IStructuredSelection) selection) {
					if (selectedElement instanceof TraceState
							|| selectedElement instanceof StateSpaceExplorer.SystemTransition) {
						getDiagramTypeProvider().getDiagramBehavior().refresh();
					}
				}
			}
		};
		site.getPage().addSelectionListener(selectionListener);
		
		partListener = new IPartListener() {
			@Override
			public void partOpened(IWorkbenchPart part) {
			}
			
			@Override
			public void partDeactivated(IWorkbenchPart part) {
			}
			
			@Override
			public void partClosed(IWorkbenchPart part) {
				if (part.getSite().getId().equals(SimulatorView.ID)) {
					getDiagramTypeProvider().getDiagramBehavior().refresh();
				}
			}
			
			@Override
			public void partBroughtToTop(IWorkbenchPart part) {
			}
			
			@Override
			public void partActivated(IWorkbenchPart part) {	
			}
		};
		site.getPage().addPartListener(partListener);
	}
	
	@Override
	public void refreshTitle() {
		Diagram diagram = getDiagramTypeProvider().getDiagram();
		StructuralElementInstance sei = DiagramHelper.getOwningStructuralElementInstance(diagram);
		String partName = sei.getName() + " -> StateMachine Editor";
		
		setPartName(partName);
	}
	
	@Override
	public void dispose() {
		if (selectionListener != null) {
			getEditorSite().getPage().removeSelectionListener(selectionListener);
		}
		
		if (partListener != null) {
			getEditorSite().getPage().removePartListener(partListener);
		}
	}
}





