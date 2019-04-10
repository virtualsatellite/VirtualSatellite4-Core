/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor.snippets;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.uieingine.ui.DVLMEditorPlugin;

/**
 * These Snippets are are based on categories out of certain concepts
 * @author lobe_el
 *
 */
public abstract class AUiCategorySectionSnippet extends AUiSectionSnippet {

	protected String conceptId;
	protected String categoryId;
	
	protected ActiveConceptHelper acHelper;
	
	/**
	 * Constructor setting the corresponding IDs and initializing the ActiveCategoryHelper depending on the given model
	 * @param conceptId The ID of the concept
	 * @param categoryId The ID of the category in the concept the snippet corresponds to
	 */
	public AUiCategorySectionSnippet(String conceptId, String categoryId) {
		this.conceptId = conceptId;
		this.categoryId = categoryId;
	}
	
	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);
		initializeHelperForModel(initModel);
	}
	
	/**
	 * This method initializes a new ActiveCategoryHelper depending on the given model
	 * @param newModel The model to be registered to the ActiveCategoryHelper
	 */
	void initializeHelperForModel(EObject newModel) {
		if (newModel == null) {
			DVLMEditorPlugin.getPlugin().getLog().log(new Status(Status.WARNING, DVLMEditorPlugin.ID, "New model is null"));
			return;
		}
		// Only initialize if necessary
		if (model == null || !model.equals(newModel) || acHelper == null) {
			Repository repo = VirSatResourceSet.getVirSatResourceSet(newModel).getRepository();
			acHelper = new ActiveConceptHelper(repo);
			model = newModel;
		}
	}
	
	/**
	 * Getter method
	 * @return conceptID
	 */
	public String getConceptId() {
		return conceptId;
	}
	
	/**
	 * Gets current category for this snippet
	 * @return category
	 */
	public Category getCategory() {
		return acHelper.getCategory(conceptId, categoryId);
	}

	@Override
	protected QualifiedName getSectionExpansionStateKey() {
		return new QualifiedName(UI_SECTION_SNIPPET_ID + "." + conceptId + "." + categoryId, getSectionModelUuidString());
	};
	
	@Override
	public boolean isActive(EObject model) {
		if (!(model instanceof CategoryAssignment)) {
			return false;
		}
		
		CategoryAssignment ca = (CategoryAssignment) model;
		String categoryFqn = ca.getType().getFullQualifiedName();
		
		return categoryFqn.equals(conceptId + "." + categoryId);
	}
}
