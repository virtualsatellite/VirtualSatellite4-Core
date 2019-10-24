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

import org.eclipse.jface.viewers.TreeViewer;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTree;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.uiengine.ui.wizard.AImportExportPage;

/**
 * A basic import / export page to select a tree element and a file destination for an import / export JSON file
 *
 */
public abstract class ACadImportExportPage extends AImportExportPage {

	
	private static final String[] DIALOG_EXTENSIONS = { "*.json" };
	
	/**
	 * Default constructor override
	 * @param pageName the page name
	 */
	protected ACadImportExportPage(String pageName) {
		super(pageName);
	}
	
	/**
	 * Default constructor override
	 * @param pageName the page name
	 * @param style the SWT style of the page 
	 */
	protected ACadImportExportPage(String pageName, int style) {
		super(pageName, style);
	}
	
	/**
	 * Create a tree viewer with filters to show only relevant tree elements for CAD import /export
	 */
	protected void createTreeViewer() {
		TreeViewer treeViewer = createTreeUI();
		VirSatFilteredWrappedTreeContentProvider filteredCp = (VirSatFilteredWrappedTreeContentProvider) treeViewer.getContentProvider();
		filteredCp.addStructuralElementIdFilter(ConfigurationTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addStructuralElementIdFilter(ElementConfiguration.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addStructuralElementIdFilter(AssemblyTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addStructuralElementIdFilter(ElementOccurence.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
	}
	
	
	@Override
	public boolean isSelectionValid() {
		Object selection = getSelection();
		return selection instanceof StructuralElementInstance;
	}
	
	
	@Override
	protected String[] getSupportedFileEndings() {
		return DIALOG_EXTENSIONS;
	}

}
