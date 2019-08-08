/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor.snippets.general;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.external.lib.commons.cli.Activator;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSectionSnippet;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 *
 * A snippet that warns if the current element is a BETA element or contains one
 *
 */
public class UiSnippetBetaWarning extends AUiSectionSnippet implements IUiSnippet {

	private static final String SECTION_HEADING = "Beta Warning!";
	private static final int UI_LAYOUT_NR_COLUMNS = 1;
	private static final String IGNORE_WARNING = "ignoreBetaConcepts";

	private final boolean ignoreWarning;

	/**
	 * Constructor for the beta warning snippet
	 */
	public UiSnippetBetaWarning() {
		super();
		ignoreWarning = Activator.getCommandLineManager().isCommandLineOptionSet(IGNORE_WARNING);
	}
	
	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);

		Composite sectionBody = createSectionBody(toolkit, SECTION_HEADING, null, UI_LAYOUT_NR_COLUMNS);
		Label label = toolkit.createLabel(sectionBody, createDescriptionLabel());

		//Configure color of the area to be red
		Display display = Display.getCurrent();
		Color red = display.getSystemColor(SWT.COLOR_DARK_RED);
		Color gray = display.getSystemColor(SWT.COLOR_GRAY);
		sectionBody.setBackground(red);
		label.setBackground(red);
		label.setForeground(gray);
		
		setUpLabel(label);

	}

	/**
	 * Method to set up the label 
	 * @param label The label to be set up
	 */
	private void setUpLabel(Label label) {
		GridData gridData = createDefaultGridData();
	    gridData.horizontalSpan = 1;
		label.setLayoutData(gridData);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet#isActive(org.eclipse.
	 * emf.ecore.EObject)
	 */
	@Override
	public boolean isActive(EObject model) {
		
		//If the command line option to ignore the warning is set then never show this snippet
		if (ignoreWarning) {
			return false;
		}
		
		if (model instanceof StructuralElementInstance) {
			StructuralElementInstance sei = (StructuralElementInstance) model;
			
			//Check if the current structural element contains a category assignment from a beta concept
			for (CategoryAssignment ca : sei.getCategoryAssignments()) {
				if (isBetaElement(ca.getType())) {
					return true;
				}
			}
			//Check if the SEI is from a beta concept itself
			return isBetaElement(sei.getType());
			
		} else if (model instanceof CategoryAssignment) {
			
			//Check if current CA is from a beta concept
			return isBetaElement(((CategoryAssignment) model).getType());
		}
		return false;
	}

	/**
	 * Checks if the type definition's containing concept has the beta flag set
	 * 
	 * @param typeDefinition
	 *            the type definition
	 * @return if it is a beta element or not
	 */
	protected boolean isBetaElement(EObject typeDefinition) {

		EObject container = typeDefinition.eContainer();
		if (container != null && container instanceof Concept) {
			Concept concept = (Concept) container;
			return concept.isBeta();
		}
		return false;
	}

	/**
	 * Creates a description as string 
	 * @return the description
	 */
	protected String createDescriptionLabel() {
		String description = "Beta element(s) detected! Concept elements of ";
		
		for (Concept concept : getRelevantBetaConcept()) {
			if (concept.getDisplayName() != null) {
				description += concept.getDisplayName() + " ";
			} else {
				description += concept.getName() + " ";
			}
		}
		
		description += "should not be used in productive envrionment!";
		
		return description;
	}
	
	/**
	 * Get the concepts which are marked as beta
	 * @return a list of concepts that are marked as beta
	 */
	protected Set<Concept> getRelevantBetaConcept() {
		Set<Concept> betaConcept = new HashSet<Concept>();
		
		if (model instanceof StructuralElementInstance) {
			StructuralElementInstance sei = (StructuralElementInstance) model;
			for (CategoryAssignment ca : sei.getCategoryAssignments()) {
				if (isBetaElement(ca.getType())) {
					betaConcept.add((Concept) ca.getType().eContainer());
				}
			}
			if (isBetaElement(sei.getType())) {
				betaConcept.add((Concept) sei.getType().eContainer());
			}
		} else if (model instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) model;
			if (isBetaElement(ca.getType())) {
				betaConcept.add((Concept) ca.getType().eContainer());
			}
		}
		
		return betaConcept;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.
	 * eclipse.jface.viewers.ISelectionChangedListener)
	 */
	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	@Override
	public ISelection getSelection() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(
	 * org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.
	 * viewers.ISelection)
	 */
	@Override
	public void setSelection(ISelection selection) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSectionSnippet#
	 * getSectionExpansionStateKey()
	 */
	@Override
	protected QualifiedName getSectionExpansionStateKey() {
		return new QualifiedName(getSectionModelUuidString(), getSectionModelUuidString());
	}

}
