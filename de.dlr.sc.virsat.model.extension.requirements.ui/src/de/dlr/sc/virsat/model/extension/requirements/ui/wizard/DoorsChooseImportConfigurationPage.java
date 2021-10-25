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
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.requirements.model.ImportConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollection;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.uiengine.ui.wizard.AImportExportPage;
/**
 * A page to choose an existing import configuration
 *
 */
public class DoorsChooseImportConfigurationPage extends AImportExportPage {
	private static final String PAGE_TITEL = "Requirements Doors Synchronizer";

	/**
	 * Standard constructor
	 * @param model
	 */
	protected DoorsChooseImportConfigurationPage(IContainer model) {
		super(PAGE_TITEL);
		setTitle(PAGE_TITEL);
		setModel(model);
		setDescription("Please choose an import configuration to synchronize with a Doors project.");
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		createTreeViewer();
	}

	@Override
	public boolean isSelectionValid() {
		Object selection = getSelection();
		if (selection instanceof CategoryAssignment) {
			String categoryFqn = ((CategoryAssignment) selection).getType().getFullQualifiedName();
			return categoryFqn.equals(ImportConfiguration.FULL_QUALIFIED_CATEGORY_NAME);
		}
		return false;
	}

	/**
	 * Create a tree viewer with filters to show only relevant tree elements for
	 * Doors import /export
	 * @return treeViewer
	 */
	protected TreeViewer createTreeViewer() {
		TreeViewer treeViewer = createTreeUI();
		VirSatFilteredWrappedTreeContentProvider filteredCp = (VirSatFilteredWrappedTreeContentProvider) treeViewer
				.getContentProvider();
		filteredCp.addClassFilter(CategoryAssignment.class);
		filteredCp.addClassFilter(ArrayInstance.class);
		filteredCp.addClassFilter(ComposedPropertyInstance.class);
		filteredCp.addStructuralElementIdFilter(
				RequirementsConfigurationCollection.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addCategoryIdFilter(ImportConfiguration.FULL_QUALIFIED_CATEGORY_NAME);
		return treeViewer;
	}

	/**
	 * Get selected requirement collection
	 * @return current requirement collection or null
	 */
	public StructuralElementInstance getStructuralElementInstance() {
		Object currentSelection = getSelection();
		if (currentSelection instanceof CategoryAssignment) {
			CategoryAssignment catAssignment = (CategoryAssignment) currentSelection;
			StructuralElementInstance currentReqCollection = (StructuralElementInstance) catAssignment.eContainer();
			return currentReqCollection;
		}
		return null;
	}

	@Override
	public boolean canFlipToNextPage() {
		return super.canFlipToNextPage() && isSelectionValid();
	}

	@Override
	public boolean isComplete() {
		return isCurrentPage() && isSelectionValid();
	}

	@Override
	public Object getSelection() {
		Object selected = super.getSelection();
		if (selected instanceof ComposedPropertyInstance) {
			selected = ((ComposedPropertyInstance) selected).getTypeInstance();
		}
		return selected;
	}

	@Override
	public Wizard getWizard() {
		IWizard currentWizard = super.getWizard();
		if (currentWizard instanceof DoorsActivateSynchronizationWizard) {
			return (DoorsActivateSynchronizationWizard) super.getWizard();
		} else if (currentWizard instanceof DoorsAddSynchronizationWizard) {
			return (DoorsAddSynchronizationWizard) super.getWizard();
		}
		return null;
	}
}