/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui.dialogs;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatWorkspaceContentProvider;
import de.dlr.sc.virsat.project.ui.navigator.labelProvider.VirSatWorkspaceLabelProvider;

/**
 * This class creates a selection part for picking project properties
 * 
 */
public abstract class ProjectSelectionPart {
	
	protected static final int VIEWER_HEIGHT = 300;
	
	private TableViewer viewerProject;
	private VirSatProjectResource selectedProject;
	
	/**
	 * Default constructor
	 * @param parent the parent
	 */
	public ProjectSelectionPart(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(1, false);
		container.setLayout(layout);
		
		Label labelProjectCompareTo = new Label(container, SWT.NONE);
		labelProjectCompareTo.setText("Compare project to:");
	
		viewerProject = new TableViewer(container);
		viewerProject.setContentProvider(new VirSatWorkspaceContentProvider());
		viewerProject.setLabelProvider(new VirSatWorkspaceLabelProvider());
		
		viewerProject.setInput(ResourcesPlugin.getWorkspace().getRoot());
	
		GridData gridDataViewer = new GridData();
		gridDataViewer.grabExcessHorizontalSpace = true;
		gridDataViewer.grabExcessVerticalSpace = true;
		gridDataViewer.horizontalAlignment = GridData.FILL;
		gridDataViewer.verticalAlignment = GridData.FILL;
		gridDataViewer.heightHint = VIEWER_HEIGHT;
		
		viewerProject.getControl().setLayoutData(gridDataViewer);
		viewerProject.addSelectionChangedListener((obj) -> {
			extractUserSelection();
			validateProject();
		});
	}
	
	/**
	 * This method takes the suer input and stores it in class internal variables
	 */
	public void extractUserSelection() {
		// First store the content that was selected
		// Store the project that was selected
		IStructuredSelection selection = (IStructuredSelection) viewerProject.getSelection();
		if (!selection.isEmpty()) {
			selectedProject = (VirSatProjectResource) selection.getFirstElement();
		}
	}
	
	/**
	 * Validate the project selection
	 */
	public abstract void validateProject();
	
	/**
	 * Call this method to get the selected project
	 * @return the selected project as VirSatProjectResource
	 */
	public VirSatProjectResource getProjectResource() {
		return selectedProject;
	}
}
