/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.ui.wizard.page;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;

import de.dlr.sc.virsat.requirements.tracing.ui.ISystemModelContentProvider;
import de.dlr.sc.virsat.requirements.tracing.ui.impl.DVLMSystemModelContentProvider;

/**
 * @author Tobias Franz tobias.franz@dlr.de
 *
 */
public class TraceArtifactSelectionPage extends WizardPage {

	private static final int ROWS = 1;
	private ISelection selection;
	private IProject project;
	private ISystemModelContentProvider modelContentProivder = null;

	private ComposedAdapterFactory composedAdapterFactory;

	/**
	 * constructor
	 * 
	 * @param pageName
	 *            given name
	 */
	public TraceArtifactSelectionPage(String pageName) {
		super(pageName);
		modelContentProivder = new DVLMSystemModelContentProvider();
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		ITreeContentProvider contentProvider = null;
		ILabelProvider labelProvider = null;
		Object input = null;

		contentProvider = (ITreeContentProvider) modelContentProivder.getContentProvider();
		labelProvider = modelContentProivder.getLabelProvider();
		input = modelContentProivder.getInput(project);
		container.setLayout(new GridLayout(ROWS, false));


		TreeViewer treeViewer = new TreeViewer(container, SWT.BORDER);

		if (contentProvider == null || labelProvider == null) {
			return;
		}

		treeViewer.setContentProvider(contentProvider);
		treeViewer.setLabelProvider(labelProvider);
		Tree tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, ROWS));

		treeViewer.setInput(input);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				TreeEditor treeEditor = new TreeEditor(tree);
				Control oldEditor = treeEditor.getEditor();
				if (oldEditor != null) {
					oldEditor.dispose();
				}
				selection = event.getSelection();
			}
		});


	}



	/**
	 * @return the selection of validation
	 */
	public ISelection getSelection() {
		return selection;
	}

	/**
	 * Return an ComposedAdapterFactory for all registered modesl
	 * 
	 * @return a ComposedAdapterFactory
	 */
	protected AdapterFactory getAdapterFactory() {
		if (composedAdapterFactory == null) {
			composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		}
		return composedAdapterFactory;
	}

	/**
	 * @param project
	 *            the project
	 */
	public void setProject(IProject project) {
		this.project = project;
	}

}
