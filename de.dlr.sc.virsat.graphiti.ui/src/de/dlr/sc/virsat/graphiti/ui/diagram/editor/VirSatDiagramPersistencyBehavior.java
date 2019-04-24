/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.ui.diagram.editor;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.editor.DefaultPersistencyBehavior;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import de.dlr.sc.virsat.graphiti.ui.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * Persistency behavior for VirSat diagrams.
 * @author muel_s8
 *
 */

public class VirSatDiagramPersistencyBehavior extends DefaultPersistencyBehavior {

	/**
	 * Standard constructor
	 * @param diagramBehavior the diagram behavior
	 */
	public VirSatDiagramPersistencyBehavior(DiagramBehavior diagramBehavior) {
		super(diagramBehavior);
	}

	@Override
	public void saveDiagram(IProgressMonitor monitor) {
		// set version info.
		final Diagram diagram = diagramBehavior.getDiagramTypeProvider().getDiagram();
		setDiagramVersion(diagram);
		
		VirSatTransactionalEditingDomain editingDomain = (VirSatTransactionalEditingDomain) diagramBehavior.getEditingDomain();
		
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor progressMonitor) throws CoreException {
				// Store it through the editing domain so it keeps track of further
				// further changes. This is important to have exact knowledge about the
				// resources dirty state.
				editingDomain.saveAll();
			}
		};
		
		try {
			operation.run(new NullProgressMonitor());
		} catch (InvocationTargetException | InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to save resource through saveables!", e));
		}
		
		diagramBehavior.getDiagramContainer().updateDirtyState();
	}

	@Override
	public boolean isDirty() {
		final Diagram diagram = diagramBehavior.getDiagramTypeProvider().getDiagram();
		setDiagramVersion(diagram);
		
		VirSatTransactionalEditingDomain editingDomain = (VirSatTransactionalEditingDomain) diagramBehavior.getEditingDomain();
		
		if (editingDomain != null) {
			Resource emfDiagramResource = diagram.eResource();
			return editingDomain.isDirty(emfDiagramResource);
		} else {
			return false;
		}
	}	
}
