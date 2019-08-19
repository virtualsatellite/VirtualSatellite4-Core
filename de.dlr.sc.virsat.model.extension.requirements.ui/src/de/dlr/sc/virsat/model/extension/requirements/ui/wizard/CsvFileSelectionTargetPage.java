/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.ui.wizard;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTree;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementGroup;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.uiengine.ui.wizard.AImportExportPage;

/**
 * A page to select a CSV file to import and the target model element
 *
 */
public class CsvFileSelectionTargetPage extends AImportExportPage {
	
	private static final String[] FILE_EXTENSIONS = { "*.csv" };
	
	/**
	 * Standard constructor
	 * @param model the root model
	 */
	protected CsvFileSelectionTargetPage(IContainer model) {
		super("Requirements CSV Import");
		setTitle("Requirements CSV Import");
		setModel(model);
		setDescription("Please select a model element to import to and a CSV file to import from.");
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		
		createFileDestinationUI();
		createTreeViewer();
		
	}
	
	
	/**
	 * Create a tree viewer with filters to show only relevant tree elements for CATIA import /export
	 */
	protected void createTreeViewer() {
		TreeViewer treeViewer = createTreeUI();
		VirSatFilteredWrappedTreeContentProvider filteredCp = (VirSatFilteredWrappedTreeContentProvider) treeViewer.getContentProvider();
		filteredCp.addClassFilter(CategoryAssignment.class);
		filteredCp.addClassFilter(ArrayInstance.class);
		filteredCp.addClassFilter(ComposedPropertyInstance.class);
		
		filteredCp.addStructuralElementIdFilter(ConfigurationTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addStructuralElementIdFilter(ElementConfiguration.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addStructuralElementIdFilter(AssemblyTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addStructuralElementIdFilter(ElementOccurence.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addCategoryIdFilter(RequirementsSpecification.FULL_QUALIFIED_CATEGORY_NAME);
	}
	
	
	@Override
	public boolean isSelectionValid() {
		Object selection = getSelection();
		if (selection instanceof CategoryAssignment) {
			return ((CategoryAssignment) selection).getType().getFullQualifiedName().equals(
					RequirementsSpecification.FULL_QUALIFIED_CATEGORY_NAME)
					|| ((CategoryAssignment) selection).getType().getFullQualifiedName().equals(
							RequirementGroup.FULL_QUALIFIED_CATEGORY_NAME);
		}
		return false;
	}
	
	
	@Override
	protected String[] getSupportedFileEndings() {
		return FILE_EXTENSIONS;
	}

}
