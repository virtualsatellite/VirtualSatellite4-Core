/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.contentProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;

import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain.IResourceEventListener;
import de.dlr.sc.virsat.project.resources.IVirsatWrappedResource;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * Modified WorkbenchContentProvider to only show VirSat Projects contained in the Workbench
 * @author fisc_ph
 *
 */
public class VirSatWorkspaceContentProvider implements ITreeContentProvider {

	/**
	 * Listening to EMF events such as changing resources in a resource set.
	 * This event will be registered to the global and SIngleton one provided
	 * by the VirSatResourceSet.
	 */
	private IResourceEventListener eventListener = new IResourceEventListener() {
		@Override
		public void resourceEvent(Set<Resource> resources, int event) {
			if (viewer != null) {
				Display.getDefault().asyncExec(() -> {
					if (!viewer.getControl().isDisposed()) {
						try {
							// Still unsure if we really need this refresh call
							viewer.refresh();
						} catch (RuntimeException e) {
							Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(),
									"Runtime Exception when trying to refresh the neavigator! Reason: " + e.getMessage()));
						}
					}
				});
			}
		}
	};
	
	/**
	 * This one listens to the Workspace in general without knowing anything about
	 * VirSatProjects or resources. It just realizes if a new project has been opened
	 * or close, which will trigger a refresh of the viewer.  
	 */
	private IResourceChangeListener openCloseProjectListener = new IResourceChangeListener() {
		public void resourceChanged(IResourceChangeEvent event) {
			// Step out if event seems to be corrupt
			if (event == null || event.getDelta() == null) {
				return;
			}

			// Otherwise give it a try and see if some project got opened
			try {
				event.getDelta().accept(new IResourceDeltaVisitor() {
					public boolean visit(IResourceDelta delta) throws CoreException {
						// Check if the changed resource is a closed project
						IResource resource = delta.getResource();
						if (resource instanceof IProject) {
							// In case a project got removed or closed, tyr to take it out of
							// the memory of wrapped projects to avoid memory leaks
							IProject project = (IProject) resource;

							if ((delta.getFlags() & IResourceDelta.OPEN) != 0 || !project.isOpen()) {
								// Update the Viewer on project state changes
								Display.getDefault().asyncExec(() -> {
									if (!viewer.getControl().isDisposed()) {
										viewer.refresh();
									}
								});
							}

							if ((delta.getKind() == IResourceDelta.REMOVED) || !project.isOpen()) {
								mapWrappedProjectResource.remove(project);
							}

							// Return false, the visitor does not need to step deeper than
							// the project resource.
							return false;
						}
						return true;
					}
				});
			} catch (CoreException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
						"Failed to handle resource change in Workspace Content Provider!"));
			}
		}
	};
	
	/**
	 * Constructor that already attaches a listener to the VirSatTransactioanlEditingDomain
	 */
	public VirSatWorkspaceContentProvider() {
		mapWrappedProjectResource = Collections.synchronizedMap(new HashMap<>());
		VirSatTransactionalEditingDomain.addResourceEventListener(eventListener);
		ResourcesPlugin.getWorkspace().addResourceChangeListener(openCloseProjectListener); 
	}
	
	@Override
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(openCloseProjectListener); 
		VirSatTransactionalEditingDomain.removeResourceEventListener(eventListener);
	}

	
	@Override
	public Object[] getElements(Object inputElement) {
		Object[] elements = getChildren(inputElement);
		return elements;
	}

	/**
	 * The map is used to match resource changes back to the projects that are displayed by this content provider
	 * The projects for this content provider are wrapped into different type of objects to avoid having standard 
	 * Eclipse UI commands being displayed in the navigator context menus.
	 */
	private Map<IResource, IVirsatWrappedResource> mapWrappedProjectResource;
	
	@Override
	public Object[] getChildren(Object parentElement) {
		List<Object> objects = new ArrayList<>();
		if (parentElement instanceof IWorkspaceRoot) {
			// Try to get all VirSat projects and add them to the content
			IWorkspace workspace = ((IWorkspaceRoot) parentElement).getWorkspace();
			List<IProject> virSatProjects = VirSatProjectCommons.getAllVirSatProjects(workspace);  
			objects.addAll(VirSatProjectResource.getWrappedProjects(virSatProjects, mapWrappedProjectResource));
		} 
		
		return objects.toArray();
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof VirSatProjectResource) {
			return ((VirSatProjectResource) element).getWrappedProject().getWorkspace();
		}		
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	private StructuredViewer viewer;

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = (StructuredViewer) viewer;
		mapWrappedProjectResource.clear();
		if (oldInput != null && !oldInput.equals(newInput)) {
			viewer.refresh();
		}
	}
}
