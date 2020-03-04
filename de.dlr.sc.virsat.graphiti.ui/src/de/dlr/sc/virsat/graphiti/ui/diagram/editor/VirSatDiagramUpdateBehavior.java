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

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer.Delegate;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.editor.DefaultUpdateBehavior;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.editor.IDiagramEditorInput;
import org.eclipse.swt.widgets.Display;

import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain.IResourceEventListener;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * The update behavior for VirSat diagrams.
 * @author muel_s8
 *
 */

public class VirSatDiagramUpdateBehavior extends DefaultUpdateBehavior {
	
	/**
	 * VirSat Resource Event which is triggered by the Workspace Synchronizer
	 * of the Transactional Editing Domain. This listener is used to handle changes
	 * on the model which are done outside of the TED so the resources can be reloaded
	 * and the editor be updated accordingly.
	 */
	private IResourceEventListener eventListener = (Set<Resource> affectedResources, int event) -> {
		Display.getDefault().asyncExec(() -> {
			
			final Diagram diagram = diagramBehavior.getDiagramTypeProvider().getDiagram();
			Resource diagramResource = diagram.eResource();
				
			switch (event) {
				case VirSatTransactionalEditingDomain.EVENT_CHANGED:
					if (diagramResource == null	|| diagramResource.getResourceSet() == null) {
						// If the resource that this editor was responsible for has been removed from the resource set
						// we automatically close the editor. On the other hand, if the resource still exists but the model object
						// is no longer contained anywhere (e.g. if a category assignment has been deleted) then we can also close the editor.
						VirSatDiagramUpdateBehavior.this.closeEditorAsynchronously();
						return;
					}
					
					diagramBehavior.refreshContent();
					refreshDecorators();
					break;
				case VirSatTransactionalEditingDomain.EVENT_RELOAD:
					handleChangedResources();
					break;
				case VirSatTransactionalEditingDomain.EVENT_UNLOAD:
					handleClosedResources(affectedResources);
					break;
				default:
			}
		});
	};
	
	/**
	 * This method closes the editor asynchronously.
	 */
	protected void closeEditorAsynchronously() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				closeContainer();
			}
		});
	}
	
	/**
	 * React to resources getting closed by either handling the change or closing the editor
	 * @param affectedResources the affected resources of some change
	 */
	private void handleClosedResources(Set<Resource> affectedResources) {
		// Now check if the resource is the one of the editor,
		// if yes make it close including the editor. In case it is not, just tell
		// the editor that some other has been closed, and this editor with referenced to it maybe needs to react.
		final Diagram diagram = diagramBehavior.getDiagramTypeProvider().getDiagram();
		Resource diagramResource = diagram.eResource();
	
		if (affectedResources.contains(diagramResource)) {
			closeEditorAsynchronously();
		} else {
			handleChangedResources();
		}		
	}
	
	/**
	 * Handles what to do with changed resources on editor activation.
	 */
	protected void handleChangedResources() {
		// Disable adapters temporarily.
		diagramBehavior.disableAdapters();

		try {
			// We unload our resource such that refreshEditorContent does a
			// complete diagram refresh.
			Diagram diagram = diagramBehavior.getDiagramTypeProvider().getDiagram();
			Resource resource = diagram.eResource();
			if (resource != null && resource.isLoaded()) {
				diagram.eResource().unload();
			}
			diagramBehavior.refreshContent();
		} finally {
			// Re-enable adapters again
			diagramBehavior.enableAdapters();
		}
	}

	/**
	 * Standard constructor
	 * @param diagramBehavior the diagram behavior
	 */
	public VirSatDiagramUpdateBehavior(DiagramBehavior diagramBehavior) {
		super(diagramBehavior);
	}

	@Override
	protected void createEditingDomain(IDiagramEditorInput input) {
		// Dont create a new editing domain, instead get the virsat editing domain
		// associated with the project of this file 
		DiagramEditorInput diagramInput = (DiagramEditorInput) input;
		Path path = new Path(diagramInput.getUri().toPlatformString(false));
		IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
		IProject project = resource.getProject();
		VirSatResourceSet resourceSet = VirSatResourceSet.getResourceSet(project);
		VirSatTransactionalEditingDomain ed =  VirSatEditingDomainRegistry.INSTANCE.getEd(resourceSet);
		
		initializeEditingDomain(ed);
	
		VirSatTransactionalEditingDomain.addResourceEventListener(eventListener);
	}
	
	@Override
	protected void disposeEditingDomain() {
		// We dont dispose our editing domains on the editor but on the project level
		
		VirSatTransactionalEditingDomain.removeResourceEventListener(eventListener);
	}
	
	/**
	 * Refreshes all decorators
	 */
	private void refreshDecorators() {
		Diagram diagram = diagramBehavior.getDiagramTypeProvider().getDiagram();
		diagram.eAllContents().forEachRemaining(object -> {
			if (object instanceof PictogramElement) {
				PictogramElement pe = (PictogramElement) object;
				diagramBehavior.refreshRenderingDecorators(pe);
			}
		});
	}
	
	@Override
	public void handleActivate() {
		refreshDecorators();
	}
	
	@Override
	protected Delegate createWorkspaceSynchronizerDelegate() {
		// Disable the Graphitti default workspace synchronizer
		// Otherwise the Graphiti workspace synchronizer will create his own 
		// workspace synchronization requests in addition to ours
		return null;
	}
	
}
