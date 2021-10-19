/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.ui.snippet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsView;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetTableRequirementsViewRequirementsRequirement
		extends UiSnippetCustomRequirementsAttributeTable implements IUiSnippet {
	
	/**
	 * 
	 */
	public UiSnippetTableRequirementsViewRequirementsRequirement() {
		super("de.dlr.sc.virsat.model.extension.requirements",
			"Requirement",
			"requirements",
			"RequirementsView",
			"de.dlr.sc.virsat.model.extension.requirements.Requirement",
			STYLE_REMOVE_BUTTON | STYLE_EDITOR_BUTTON);
	}
	
	@Override
	protected Collection<APropertyInstance> getTableObjects() {
		return getArrayInstances();
	}
	
	@Override
	protected void setTableViewerInput() {
		if (model instanceof CategoryAssignment) {
			columnViewer.setInput(getArrayInstances());
		}
	}
	
	@Override
	protected List<APropertyInstance> getArrayInstances() {
		Set<APropertyInstance> cummulatedResult = new HashSet<APropertyInstance>();
		IBeanCategoryAssignment elementToView = getElementToView();
		
		List<CategoryAssignment> children = CategoryAssignmentHelper.getNestedCategoryAssignments(
				(CategoryAssignment) elementToView.getTypeInstance(), 
				Requirement.FULL_QUALIFIED_CATEGORY_NAME,
				getViewElement().getShowDeepChildren());
		
		for (CategoryAssignment child : children) {
			EObject container = child.eContainer();
			if (container instanceof APropertyInstance) {
				cummulatedResult.add((APropertyInstance) container);
			}
		}
		
		return new ArrayList<APropertyInstance>(cummulatedResult);
	}
	
	@Override
	protected Command createAddCommand(EditingDomain editingDomain, Concept activeConcept) {
		return UnexecutableCommand.INSTANCE;
	}
	
	protected RequirementsView getViewElement() {
		return new RequirementsView((CategoryAssignment) model);
	}
	
	protected IBeanCategoryAssignment getElementToView() {
		IBeanCategoryAssignment treeElement = getViewElement().getTreeElementToView();
		if (treeElement == null) {
			treeElement = getViewElement();
		}
		return treeElement;
	}
	
	@Override
	protected IStructuredContentProvider getTableContentProvider() {
		IStructuredContentProvider contentProvider = new ArrayContentProvider();
		return contentProvider;
	}
	
}
