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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.requirements.command.InitializeRequirementCommand;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.ui.snippet.dialog.ExistenceVerificationTargetEditingDialog;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.uiengine.ui.dialog.ReferenceSelectionDialog;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetSectionRequirement extends AUiSnippetSectionRequirement implements IUiSnippet {

	protected static final String BUTTON_EDIT_TRACE_TEXT = "Edit Forward Tracing";
	
	@Override
	protected void createComposedPropertyWidgets(FormToolkit toolkit, Composite sectionBody, ComposedProperty property) {
		Text textPropertyComposedName = toolkit.createText(sectionBody, "");
		textPropertyComposedName.setEditable(false);
		textPropertyComposedName.setLayoutData(createDefaultGridData());

		Button buttonDrillDown = toolkit.createButton(sectionBody, BUTTON_EDIT_TRACE_TEXT, SWT.PUSH);
		GridData gridData = createDefaultGridData();
		gridData.horizontalSpan = UI_LAYOUT_SPAN_COLUMNS_2;
		buttonDrillDown.setLayoutData(gridData);

		String propertyFqn = property.getFullQualifiedName();

		mapPropertyToTextComposedName.put(propertyFqn, textPropertyComposedName);

		buttonDrillDown.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				ComposedPropertyInstance propertyInstance = (ComposedPropertyInstance) caHelper.getPropertyInstance(property);
				ATypeInstance referencedTypeInstance = propertyInstance.getTypeInstance();
				
				
				EditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(referencedTypeInstance);
				if (referencedTypeInstance != null) {
					Dialog dialog = new ExistenceVerificationTargetEditingDialog(
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), toolkit, editingDomain,
							referencedTypeInstance);
					dialog.open();
				}
			}
		});
	}
	
	@Override
	protected void executeReferenceSelectionDialog(EditingDomain editingDomain, String propertyFqn) {
		Category viewerCategory = acHelper.getCategory(conceptId, categoryId);
		AProperty typeProperty = ActiveConceptHelper.getProperty(viewerCategory, Requirement.PROPERTY_REQTYPE);
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) caHelper
				.getPropertyInstance(propertyFqn);
		if (propertyInstance.getType().equals(typeProperty)) {

			ATypeDefinition referencePropertyType = ((ReferenceProperty) propertyInstance.getType()).getReferenceType();
			ATypeInstance toSelect = propertyInstance.getReference();

			ReferenceSelectionDialog dialog = createReferenceDialogAndSetInput(propertyInstance, referencePropertyType);
			dialog.setInitialSelection(toSelect);
			dialog.setAllowMultiple(false);
			dialog.setDoubleClickSelects(true);
			if (dialog.open() == Dialog.OK) {
				Object selection = dialog.getFirstResult();
				if (selection instanceof CategoryAssignment) {
					CategoryAssignment selectedCa = (CategoryAssignment) selection;

					Requirement requirement = new Requirement(caModel);
					RequirementType reqType = new RequirementType(selectedCa);
					
					updateRequirementToNewType(editingDomain, requirement, reqType);
					
				}
			}
		} else {
			super.executeReferenceSelectionDialog(editingDomain, propertyFqn);
		}
	}
	
	/**
	 * Update the existing requirement to its new type by trying to match old values to new field
	 * 
	 * @param editingDomain the editing domain
	 * @param requirement the requirement 
	 * @param reqType the new type
	 */
	protected void updateRequirementToNewType(EditingDomain editingDomain, Requirement requirement, RequirementType reqType) {
		
		CompoundCommand updateTypeCommand = new CompoundCommand();

		// Set the actual reference to the new type
		Command setCommand = requirement.setReqType(editingDomain,
				 reqType);
		updateTypeCommand.append(setCommand);

		// Cache and clear old values
		Map<String, String> mapOldValueToTypeCache = new HashMap<>();
		List<String> orderedValueList = new ArrayList<String>();
		if (!requirement.getElements().isEmpty()) {
			for (AttributeValue value : requirement.getElements()) {
				orderedValueList.add(value.getValue());
				mapOldValueToTypeCache.put(value.getValue(), value.getAttType().getType());
			}
			updateTypeCommand.append(new RecordingCommand((TransactionalEditingDomain) editingDomain) {
				@Override
				protected void doExecute() {
					requirement.getElements().clear();
				}
			});
		}
		
		// Initialize the new type's attributes
		Command cmdInitialize = InitializeRequirementCommand.create(
				(TransactionalEditingDomain) editingDomain, caModel,
				(CategoryAssignment) reqType.getTypeInstance());
		updateTypeCommand.append(cmdInitialize);
		
		editingDomain.getCommandStack().execute(updateTypeCommand);
		

		// Try to apply old values to new requirement type
		CompoundCommand fillNewTypeCommand = new CompoundCommand();
		//Requirement newRequirement = new Requirement(caModel); // Old enum still has cached values
		for (String oldValue : orderedValueList) {
			int i = orderedValueList.indexOf(oldValue);
			if (requirement.getElements().size() > i) {
				
				// Check if types are matching
				AttributeValue newAttribute = requirement.getElements().get(i);
				String typeString = newAttribute.getAttType().getType();
				if (typeString.equals(mapOldValueToTypeCache.get(oldValue))) {
					fillNewTypeCommand.append(newAttribute.setValue(editingDomain, oldValue));
				}
			}
		}
		editingDomain.getCommandStack().execute(fillNewTypeCommand);
	}

}
