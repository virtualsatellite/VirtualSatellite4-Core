/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.wizard;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatComposedContentProvider;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatComposedLabelProvider;
import de.dlr.sc.virsat.project.ui.navigator.commonSorter.VirSatNavigatorSeiSorter;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatProjectContentProvider;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatWorkspaceContentProvider;
import de.dlr.sc.virsat.project.ui.navigator.labelProvider.VirSatProjectLabelProvider;
import de.dlr.sc.virsat.project.ui.navigator.labelProvider.VirSatWorkspaceLabelProvider;

/**
 * A abstract page to select a model element
 *
 */
public abstract class ATreeViewerPage  extends WizardPage {
	
	protected Object model;
	protected Object selection;
	
	
	/**
	 * default constructor
	 * 
	 * @param pageName
	 *            Name of the Page
	 */
	protected ATreeViewerPage(String pageName) {
		super(pageName);
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		content.setLayout(new GridLayout());
		content.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		setControl(content);
		setPageComplete(isComplete());
	}

	/**
	 * Create the tree UI and sorted it
	 * @return the created tree viewer
	 */
	public TreeViewer createTreeUI() {
		TreeViewer treeViewer = new TreeViewer((Composite) getControl(), SWT.BORDER);
		treeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		VirSatComposedContentProvider cp = new VirSatComposedContentProvider();
		cp.registerSubContentProvider(new VirSatWorkspaceContentProvider());
		cp.registerSubContentProvider(new VirSatProjectContentProvider());
		
		VirSatComposedLabelProvider lp = new VirSatComposedLabelProvider();
		lp.registerSubLabelProvider(new VirSatWorkspaceLabelProvider());
		lp.registerSubLabelProvider(new VirSatProjectLabelProvider());
		
		// Filter for elements that can be imported and exported together with their
		// parents
		VirSatFilteredWrappedTreeContentProvider filteredCP = new VirSatFilteredWrappedTreeContentProvider(cp);
		filteredCP.addClassFilter(StructuralElementInstance.class);
		filteredCP.addClassFilter(Repository.class);
		filteredCP.addClassFilter(VirSatProjectResource.class);

		treeViewer.setContentProvider(filteredCP);
		treeViewer.setLabelProvider(lp);
		treeViewer.setInput(model);
		treeViewer.setComparator(new VirSatNavigatorSeiSorter());

		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selection = treeViewer.getStructuredSelection().getFirstElement();
				setPageComplete(isComplete());
				if (getWizard().getContainer() != null) {
					getWizard().getContainer().updateButtons();
				}
			}
		});
		
		return treeViewer;
	}
	
	/**
	 * Checks if the page has been sufficiently filled with user data
	 * 
	 * @return true iff the page is complete
	 */
	public boolean isComplete() {
		return isSelectionValid();
	}

	/**
	 * Get the selected object
	 * 
	 * @return the selected object
	 */
	public Object getSelection() {
		return selection;
	}


	/**
	 * Checks if this page is complete
	 * 
	 * @return true if the page is complete
	 */
	public boolean isSelectionValid() {
		return true;
	}

	/**
	 * @param model set the model
	 */
	public void setModel(Object model) {
		this.model = model;
	}
	
}
