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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.requirements.model.IRequirementTreeElement;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
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
	
	private static final String BUTTON_UPDATE_TEXT = "Update Filters";
	
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
	protected void createButtons(FormToolkit toolkit, EditingDomain editingDomain, Composite sectionBody) {
		Composite compositeButtons = toolkit.createComposite(sectionBody);
		compositeButtons.setLayoutData(new GridData());
		compositeButtons.setLayout(new FillLayout(SWT.HORIZONTAL));
		createUpdateButton(toolkit, compositeButtons);
		createRemoveButton(toolkit, editingDomain, compositeButtons);
		createEditorButton(toolkit, compositeButtons);
		createExcelButton(toolkit, compositeButtons, columnViewer);
	}
	
	/**
	 * this method creates the button for adding the drill down functionality to the given table
	 * @param toolkit  the toolkit to be used to create the button
	 * @param compositeButtons  the composite in which the button should be placed
	 */
	protected void createUpdateButton(FormToolkit toolkit, Composite compositeButtons) {
		Button buttonUpdate = toolkit.createButton(compositeButtons, BUTTON_UPDATE_TEXT, SWT.PUSH);
		buttonUpdate.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setTableViewerInput();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}
	
	@Override
	protected Collection<APropertyInstance> getTableObjects() {
		return getArrayInstances();
	}

	
	@Override
	protected void setTableViewerInput() {
		columnViewer.setInput(getArrayInstances());
	}
	
	@Override
	protected List<APropertyInstance> getArrayInstances() {
		Set<APropertyInstance> cummulatedResult = new HashSet<APropertyInstance>();
		IBeanCategoryAssignment elementToView = getElementToView();
		if (elementToView != null) {
			addAllChildren(cummulatedResult, elementToView);
		} else {
			IBeanStructuralElementInstance seiBean = getViewElement().getParent();
			for (IBeanCategoryAssignment rootElement : seiBean.getAll(IRequirementTreeElement.class)) {
				addAllChildren(cummulatedResult, rootElement);
			}
		}
		
		return new ArrayList<APropertyInstance>(cummulatedResult);
	}
	
	/**
	 * Adds all child requirements of a CaBean to a given set
	 * @param set the set
	 * @param caBean the ca bean which is searched
	 */
	protected void addAllChildren(Set<APropertyInstance> set, IBeanCategoryAssignment caBean) {
		List<CategoryAssignment> children = CategoryAssignmentHelper.getNestedCategoryAssignments(
				(CategoryAssignment) caBean.getTypeInstance(), 
				Requirement.FULL_QUALIFIED_CATEGORY_NAME,
				getViewElement().getShowDeepChildren());
		
		for (CategoryAssignment child : children) {
			EObject container = child.eContainer();
			if (container instanceof APropertyInstance && isSelectedType(child)) {
				set.add((APropertyInstance) container);
			}
		}
	}
	
	/**
	 * Check weather a requirement is filtered via type or not
	 * @param req the requirement
	 * @return true id requirement should be visible
	 */
	protected boolean isSelectedType(CategoryAssignment req) {
		if (!getViewElement().getReqTypesToView().isEmpty()) {
			Requirement requirement = new Requirement(req);
			for (RequirementType type : getViewElement().getReqTypesToView()) {
				if (requirement.getReqType().getName().equals(type.getName())) {
					return true;
				}
			}
			return false;	// Type not found in list
		}
		return true;		// No types specified, show all
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
		return treeElement;
	}
	
	@Override
	protected IStructuredContentProvider getTableContentProvider() {
		IStructuredContentProvider contentProvider = new ArrayContentProvider();
		return contentProvider;
	}
	
}
