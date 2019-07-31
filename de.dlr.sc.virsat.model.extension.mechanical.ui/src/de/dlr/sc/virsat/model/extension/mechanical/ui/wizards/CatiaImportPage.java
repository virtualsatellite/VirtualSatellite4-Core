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
 * A page to configure the import from CATIA by specifying a file to import from
 * and a tree on which the import should be applied to
 *
 */
public class CatiaImportPage extends AImportExportPage {

	private static final String DIALOG_TEXT = "File name";
	private static final String[] DIALOG_EXTENSIONS = { "*.json" };
	
	
	/**
	 * Standard constructor
	 * @param model the root model
	 */
	protected CatiaImportPage(IContainer model) {
		super("Catia JSON Import");
		setTitle("Catia JSON Import");
		setModel(model);
		setDescription("Please select a tree to import to and a file to import.");
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
		label.setText("All Geometry files will be copied into documents folder of the .");
	}
	
	@Override
	protected String openDialog() {
		FileDialog dialog = new FileDialog(getContainer().getShell(), SWT.SAVE | SWT.SHEET);
		dialog.setText(DIALOG_TEXT);
		dialog.setFilterExtensions(DIALOG_EXTENSIONS);
		return dialog.open();
	}

}
