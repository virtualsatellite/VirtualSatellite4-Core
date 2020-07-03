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
import de.dlr.sc.virsat.uiengine.ui.wizard.ATreeViewerPage;

/**
 * A page to select a requirement type for imported requirements
 *
 */
public class CsvImportTargetSelectionPage extends ATreeViewerPage {

	private static final String PAGE_TITLE = "Import Target Selection";
	
	/**
	 * Default constructor
	 * @param model the model
	 */
	protected CsvImportTargetSelectionPage(IContainer model) {
		super(PAGE_TITLE);
		setTitle(PAGE_TITLE);
		setModel(model);
		setDescription("Please select a requirement specification element in which the requirements should be imported to.");
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		createTreeViewer();
	}
	
	
	/**
	 * Create a tree viewer with filters to show only relevant tree target elements 
	 */
	protected void createTreeViewer() {
		TreeViewer treeViewer = createTreeUI();
		VirSatFilteredWrappedTreeContentProvider filteredCp = (VirSatFilteredWrappedTreeContentProvider) treeViewer
				.getContentProvider();
		filteredCp.addClassFilter(CategoryAssignment.class);
		filteredCp.addClassFilter(ArrayInstance.class);
		filteredCp.addClassFilter(ComposedPropertyInstance.class);

		filteredCp.addStructuralElementIdFilter(ConfigurationTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addStructuralElementIdFilter(ElementConfiguration.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addStructuralElementIdFilter(AssemblyTree.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addStructuralElementIdFilter(ElementOccurence.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addCategoryIdFilter(RequirementsSpecification.FULL_QUALIFIED_CATEGORY_NAME);
		filteredCp.addCategoryIdFilter(RequirementGroup.FULL_QUALIFIED_CATEGORY_NAME);
	}
	
	
	@Override
	public boolean isSelectionValid() {
		Object selection = getSelection();
		if (selection instanceof CategoryAssignment) {
			String categoryFqn = ((CategoryAssignment) selection).getType().getFullQualifiedName();
			return (categoryFqn.equals(RequirementsSpecification.FULL_QUALIFIED_CATEGORY_NAME)
					|| categoryFqn.equals(RequirementGroup.FULL_QUALIFIED_CATEGORY_NAME));
		}
		return false;
	}
	
	
}
