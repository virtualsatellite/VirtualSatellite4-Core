/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;

import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTree;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.uiengine.ui.wizard.AImportExportPage;

/**
 * This class configures the export page by providing the selection
 * mechanisms for selecting what object should be objected and to
 * where it should be exported
 *
 */

public class CatiaExportPage extends AImportExportPage {

	private static final String DIALOG_TEXT = "File name";
	private static final String[] DIALOG_EXTENSIONS = { "*.json" };

	/**
	 * Standard constructor
	 * @param model the root model
	 */
	protected CatiaExportPage(IContainer model) {
		super("Catia JSON Export");
		setTitle("Catia JSON Export");
		setModel(model);
		setDescription("Please select a tree to export and an export destination.");
	}


	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		TreeViewer treeViewer = createTreeUI();
		VirSatFilteredWrappedTreeContentProvider filteredCp = (VirSatFilteredWrappedTreeContentProvider) treeViewer.getContentProvider();
		filteredCp.addStructuralElementIdFilter(ConfigurationTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addStructuralElementIdFilter(ElementConfiguration.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addStructuralElementIdFilter(AssemblyTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addStructuralElementIdFilter(ElementOccurence.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		
		createFileDestinationUI();
		
		Label label = new Label((Composite) getControl(), SWT.NONE);
		label.setText("All attached Geometry files will be copied into the same directory as the JSON file.");
	}
	
	@Override
	protected String openDialog() {
		FileDialog dialog = new FileDialog(getContainer().getShell(), SWT.SAVE | SWT.SHEET);
		dialog.setText(DIALOG_TEXT);
		dialog.setFilterExtensions(DIALOG_EXTENSIONS);
		return dialog.open();
	}
}
