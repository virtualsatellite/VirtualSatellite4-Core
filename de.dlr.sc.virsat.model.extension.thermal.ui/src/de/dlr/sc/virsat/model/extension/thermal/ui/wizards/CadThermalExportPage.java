/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.thermal.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTree;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalAnalysis;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.uiengine.ui.wizard.AImportExportPage;

/**
 * Wizard page to select thermal analysis to export and a file destination where to
 *
 */
public class CadThermalExportPage extends AImportExportPage {

	/**
	 * Standard constructor
	 * @param model the root model
	 */
	protected CadThermalExportPage(IContainer model) {
		super("CAD Thermal Export");
		setTitle("CAD Thermal Export (Beta)");
		setModel(model);
		setDescription("Please select a thermal analysis definition to export and an export destination.");
	}


	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		
		createTreeViewer();
		createFileDestinationUI();
	}
	
	/**
	 * Create a tree viewer with filters to show only relevant tree elements for CAD import /export
	 */
	protected void createTreeViewer() {
		TreeViewer treeViewer = createTreeUI();
		VirSatFilteredWrappedTreeContentProvider filteredCp = (VirSatFilteredWrappedTreeContentProvider) treeViewer.getContentProvider();
		filteredCp.addClassFilter(CategoryAssignment.class);
		filteredCp.addStructuralElementIdFilter(ConfigurationTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addStructuralElementIdFilter(ElementConfiguration.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addStructuralElementIdFilter(AssemblyTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addStructuralElementIdFilter(ElementOccurence.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addCategoryIdFilter(ThermalAnalysis.FULL_QUALIFIED_CATEGORY_NAME);
	}
	
	@Override
	protected String openDialog() {
		DirectoryDialog dialog = new DirectoryDialog(getContainer().getShell(), dialogStyle);
		dialog.setText("Select Folder");
		return dialog.open();
	}
	

	@Override
	public boolean isSelectionValid() {
		Object selection = getSelection();
		if (selection instanceof CategoryAssignment) {
			String categoryFqn = ((CategoryAssignment) selection).getType().getFullQualifiedName();
			return categoryFqn.equals(ThermalAnalysis.FULL_QUALIFIED_CATEGORY_NAME);
		}
		return false;
	}

}
