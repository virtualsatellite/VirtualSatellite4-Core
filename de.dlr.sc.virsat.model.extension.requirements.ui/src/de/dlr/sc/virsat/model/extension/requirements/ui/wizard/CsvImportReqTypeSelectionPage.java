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
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollection;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.uiengine.ui.wizard.ATreeViewerPage;

/**
 * A page to select a requirement type for imported requirements
 *
 */
public class CsvImportReqTypeSelectionPage extends ATreeViewerPage {

	/**
	 * Default constructor
	 * @param model the model
	 */
	protected CsvImportReqTypeSelectionPage(IContainer model) {
		super("Import Type Selection");
		setTitle("Import Type Selection");
		setModel(model);
		setDescription(
				"Please select a requirement type for the imported requirements or a container configuration if a new type should be created");
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
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
		filteredCp.addStructuralElementIdFilter(RequirementsConfigurationCollection.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);

	}
	
	
	@Override
	public boolean isSelectionValid() {
		Object selection = getSelection();
		if (selection instanceof CategoryAssignment) {
			return ((CategoryAssignment) selection).getType().getFullQualifiedName().equals(
					RequirementsConfiguration.FULL_QUALIFIED_CATEGORY_NAME)
					|| ((CategoryAssignment) selection).getType().getFullQualifiedName().equals(
							RequirementType.FULL_QUALIFIED_CATEGORY_NAME);
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.uiengine.ui.wizard.ATreeViewerPage#getSelection()
	 */
	@Override
	public Object getSelection() {
		Object selected = super.getSelection();
		if (selected instanceof ComposedPropertyInstance) {
			selected = ((ComposedPropertyInstance) selected).getTypeInstance();
		}
		return selected;
	}
	
}
