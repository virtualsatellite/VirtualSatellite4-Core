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
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.VerificationTypeSpecification;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.requirements.model.ModelVerification;
import de.dlr.sc.virsat.model.extension.requirements.ui.command.CreateAddArrayElementVerificationCommand;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetArrayInstanceCategoryTable;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

public abstract class AUISnippetTableRequirementsVerification extends AUiSnippetArrayInstanceCategoryTable implements IUiSnippet {
	
	private static final String REQUIREMENT_CATEGORY_ID = "Requirement";
	private static final String REQUIREMENT_VERIFICATION_PROPERTY_ID = "verifications";
	
	private static final String GENERIC_SNIPPET_DESCRIPTION = "Add the method to verify elements in the system model.";
	private static final String DESCRIPTION_WITH_SPECIFIC_PROPERTIES = "Add the verification method to verify values in the following properties: ";
	
	protected String activeForConceptID;
	protected Concept verificationConcept;
	protected Concept activeForConcept;
	
	/**
	 * Constructor for requirements verification snippets
	 * @param activeForConceptID the concept ID for which the snippet should be visible when the concept is active
	 * @param verificationConceptId the concept ID of the verification element container
	 * @param verificationId the categoryID of the verification element
	 * @param fullQualifiedCategoryId the full qualified category name of the verification element
	 * @param style
	 */
	public AUISnippetTableRequirementsVerification(String activeForConceptID, String verificationConceptId, String verificationId, String fullQualifiedCategoryId, int style) {
		super(verificationConceptId, verificationId, REQUIREMENT_VERIFICATION_PROPERTY_ID, REQUIREMENT_CATEGORY_ID, fullQualifiedCategoryId, style);
		this.activeForConceptID = activeForConceptID;
	}
	
	@Override
	protected void initializeHelperForModel(EObject newModel) {
		super.initializeHelperForModel(newModel);
		verificationConcept = acHelper.getConcept(conceptId);
		activeForConcept = acHelper.getConcept(activeForConceptID);
	}

	@Override
	public Composite createSectionBody(FormToolkit toolkit, String sectionHeading, String sectionDescription,
			int numberColumns) {
		return super.createSectionBody(toolkit, getSnippetTitle(), getSnippetDescription(), numberColumns);
	}
	
	protected String getSnippetTitle() {
		return "Add a " + categoryId + " for elements of the " + getConceptName(activeForConcept) + " Concept";
	}
	
	protected String getSnippetDescription() {
		if (getPropertyList().isEmpty()) {
			return GENERIC_SNIPPET_DESCRIPTION;
		} else {
			return DESCRIPTION_WITH_SPECIFIC_PROPERTIES + getPropertyList();
		}
	}
	
	protected String getConceptName(Concept concept) {
		if (!concept.getDisplayName().isEmpty()) {
			return concept.getDisplayName();
		} else {
			return concept.getName();
		}
	}
	
	/**
	 * Customize the table to only show the property if needed
	 */
	protected UiSnippetGenericTableImpl createImplementation() {
		return new UiSnippetGenericTableImpl() {
			@Override
			protected TableViewerColumn createPropertyTableColumn(EditingDomain editingDomain, AProperty property) {
				TableViewerColumn columnViewer = super.createPropertyTableColumn(editingDomain, property);
				if (property.getName().equals(ModelVerification.PROPERTY_ELEMENTTOBEVERIFIED) 
						&& getVerifiablePropertiesInConcept().isEmpty()) {
					columnViewer.getColumn().setWidth(0);
				} 
				return columnViewer;
			}
		};
	}
	
	/**
	 * Create a list of verifiable properties in the active concept
	 * @return the list as String
	 */
	protected String getPropertyList() {
		StringBuilder stringBuilder = new StringBuilder();
		List<AProperty> propList = getVerifiablePropertiesInConcept();
		for (AProperty prop : propList) {
			stringBuilder.append(prop.getName() + ((propList.indexOf(prop) < propList.size() - 1) ? "," : "!")); 
		}
		return stringBuilder.toString();
	}
	
	@Override
	protected void createAddButton(FormToolkit toolkit, EditingDomain editingDomain, Composite compositeButtons) {
		List<AProperty> specificProperties = getVerifiablePropertiesInConcept();
		if (specificProperties.isEmpty()) {
			super.createAddButton(toolkit, editingDomain, compositeButtons);
		} else {
			for (AProperty prop : specificProperties) {
				Button buttonAdd = toolkit.createButton(compositeButtons, "Add Verification for " + prop.getName(), SWT.PUSH);
				buttonAdd.addSelectionListener(new SelectionListener() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						Concept activeConcept = acHelper.getConcept(conceptId);
						Command addCommand = createAddCommand(editingDomain, activeConcept, prop);
						editingDomain.getCommandStack().execute(addCommand);
					}
		
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						widgetSelected(e);
					}
				});
				checkWriteAccess(buttonAdd);
			}
		}
		
	}
	
	/**
	 * Return a list of properties that have a verification method defined
	 * @return the list of properties
	 */
	protected List<AProperty> getVerifiablePropertiesInConcept() {
		List<AProperty> verifiedProperties = new ArrayList<AProperty>();
		for (Category category : activeForConcept.getCategories()) {
			for (AProperty property : category.getProperties()) {
				if (property.getVerification() != null && property.getVerification() instanceof VerificationTypeSpecification) {
					VerificationTypeSpecification verificationTypeSpecification = (VerificationTypeSpecification) property.getVerification();
					if (verificationTypeSpecification.getVerificationType().getFullQualifiedName().equals(fullQualifiedCategoryId)) {
						verifiedProperties.add(property);
					}
				}
			}
		}
		return verifiedProperties;
	}
	
	
	/**
	 * Check weather this snippet concepts is active or not
	 * @return true if active
	 */
	protected boolean isConceptActive() {
		if (model == null) {
			return false; 
		}
		return activeForConcept != null;
	}
	
	@Override
	protected Command createAddCommand(EditingDomain editingDomain, Concept activeConcept) {
		return new CreateAddArrayElementVerificationCommand().create(editingDomain, getArrayInstance(model),  ActiveConceptHelper.getCategory(verificationConcept, categoryId));
	}
	
	protected Command createAddCommand(EditingDomain editingDomain, Concept activeConcept, ATypeDefinition propertyDef) {
		return new CreateAddArrayElementVerificationCommand().create(editingDomain, getArrayInstance(model),  ActiveConceptHelper.getCategory(verificationConcept, categoryId), propertyDef);
	}

}
