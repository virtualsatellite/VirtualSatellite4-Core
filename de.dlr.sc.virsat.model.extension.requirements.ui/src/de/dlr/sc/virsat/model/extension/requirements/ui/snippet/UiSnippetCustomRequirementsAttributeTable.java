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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.requirements.command.InitializeRequirementCommand;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollection;
import de.dlr.sc.virsat.model.extension.requirements.ui.Activator;
import de.dlr.sc.virsat.model.extension.requirements.ui.celleditor.RequirementsAttributeValuePerColumnEditingSupport;
import de.dlr.sc.virsat.model.extension.requirements.ui.provider.RequirementsAttributeLabelProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;
import de.dlr.sc.virsat.uiengine.ui.dialog.ReferenceSelectionDialog;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetArrayInstanceCategoryTable;

/**
 * @author fran_tb
 *
 */
public abstract class UiSnippetCustomRequirementsAttributeTable extends AUiSnippetArrayInstanceCategoryTable implements ControlListener {

	protected Button buttonAdd;

	protected static final String COLUMN_TEXT_STATUS = "Status";
	protected static final String COLUMN_ATTRIBUTE_SEPARATOR = " / ";

	protected static final String FQN_PROPERTY_REQUIREMENT_TYPE = Requirement.FULL_QUALIFIED_CATEGORY_NAME + "." + Requirement.PROPERTY_REQTYPE;

	private static final int STATUS_COLUMN_WIDTH = 100;
	private static final String COLUMN_PREFIX = "attColumn";
	
	protected final String arrayInstanceID;

	protected Set<RequirementType> requirementTypes = new HashSet<RequirementType>();
	protected int maxNumberAttributes = 0;

	protected TableViewerColumn colStatus = null;
	protected List<TableViewerColumn> attColumns;
	
	protected boolean controlListenerActive = true;

	/**
	 * constructor of the abstract UI snippet array instance category table
	 * instantiate the ID of the concept and the active category id
	 * 
	 * @param conceptId the id of the concept
	 * @param categoryId the id of the category
	 * @param arrayInstanceId the array instance id
	 * @param activeForCategoryId the active for the category id
	 * @param fullQualifiedCategoryId the full qualified category id
	 * @param style The style as binary mask defining which buttons should be
	 *            displayed with the given table (e.g. Add/Remove/Drill-Down)
	 */
	public UiSnippetCustomRequirementsAttributeTable(String conceptId, String categoryId, String arrayInstanceId,
			String activeForCategoryId, String fullQualifiedCategoryId, int style) {
		super(conceptId, categoryId, arrayInstanceId, activeForCategoryId, fullQualifiedCategoryId, style);
		this.arrayInstanceID = arrayInstanceId;
	}

	/**
	 * Implementation of the createTableColumns Method
	 * 
	 * @param editingDomain the editing domain which shall be used to provide the editing
	 *            support in the columns
	 */
	protected void createTableColumns(EditingDomain editingDomain) {

		// Status column
		if (colStatus == null) {
			colStatus = (TableViewerColumn) createDefaultColumn(COLUMN_TEXT_STATUS);

			colStatus.setEditingSupport(createEditingSupport(editingDomain, categoryModel.getProperties()
					.get(RequirementsAttributeLabelProvider.REQUIREMENT_STATUS_PROPERTY_NUMBER)));

			colStatus.getColumn().setWidth(STATUS_COLUMN_WIDTH);

			// initialize list for attribute column
			attColumns = new ArrayList<>();
		}

		if (model instanceof CategoryAssignment) {

			// Find all necessary requirement types
			CategoryAssignmentHelper helper = new CategoryAssignmentHelper((CategoryAssignment) model);
			ArrayInstance array = (ArrayInstance) helper.getPropertyInstance(arrayInstanceID);
			List<APropertyInstance> arrayInstances = array.getArrayInstances();

			// Some ugly casting is necessary here because beans cannot be used as the array
			// property can be in different CAs
			for (APropertyInstance arrayInstance : arrayInstances) {
				CategoryAssignment reqObject = ((ComposedPropertyInstance) arrayInstance).getTypeInstance();
				if (reqObject.getType().getFullQualifiedName()
						.equals(Requirement.FULL_QUALIFIED_CATEGORY_NAME)) {
					RequirementType requirementType = new Requirement(reqObject).getReqType();
					if (requirementType != null) {
						requirementTypes.add(requirementType);
						int numberAttributes = requirementType.getAttributes().size();
						maxNumberAttributes = Math.max(maxNumberAttributes, numberAttributes);
					}
				}
			}

			// Add necessary table columns
			for (int i = 0; i < maxNumberAttributes; i++) {
				StringBuilder columnName = new StringBuilder();
				for (RequirementType requirementType : requirementTypes) {

					if (requirementType.getAttributes().size() > i) {
						String name = requirementType.getAttributes().get(i).getName();
						boolean notYetSpecified = columnName.indexOf(name) != -1;
						
						// Add separator if column is used for different attributes
						if (columnName.length() != 0 && notYetSpecified) {
							columnName.append(COLUMN_ATTRIBUTE_SEPARATOR);
						}

						if (notYetSpecified) {
							columnName.append(name);
						}
					}
				}

				if (attColumns.size() > i) {
					attColumns.get(i).getColumn().setText(columnName.toString());
				} else {
					TableViewerColumn newColumn = (TableViewerColumn) createDefaultColumn(columnName.toString());
					newColumn.getColumn().addControlListener(this);
					newColumn.setEditingSupport(new RequirementsAttributeValuePerColumnEditingSupport(editingDomain, columnViewer, i));
					attColumns.add(newColumn);
				}
			}
		}
		restoreColumnWitdh();
	}
	

	/**
	 * this method get the label provider
	 * 
	 * @return the table column provider
	 */
	protected ITableLabelProvider getTableLabelProvider() {
		VirSatTransactionalAdapterFactoryLabelProvider labelProvider;
		labelProvider = new RequirementsAttributeLabelProvider(adapterFactory);
		return labelProvider;
	}

	/**
	 * Refresh the table if a requirement with new type was added
	 * 
	 * @param editingDomain the editing domain
	 */
	protected void refreshTable(EditingDomain editingDomain) {
		createTableColumns(editingDomain);
		ITableLabelProvider labelProvider = getTableLabelProvider();
		columnViewer.setLabelProvider(labelProvider);
	}
	
	
	/**
	 * this method creates the add button and his functionality
	 * 
	 * @param toolkit the toolkit to be used to create the button
	 * @param editingDomain the editing domain an which the button might act on
	 * @param compositeButtons the composite in which the button should be placed
	 */
	protected void createAddButton(FormToolkit toolkit, EditingDomain editingDomain, Composite compositeButtons) {
		if ((style & STYLE_ADD_BUTTON) != 0) {
			buttonAdd = toolkit.createButton(compositeButtons, BUTTON_ADD_TEXT + getTypeInformation(), SWT.PUSH);
			buttonAdd.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {

					Concept activeConcept = acHelper.getConcept(conceptId);
					Command addCommand = createAddCommand(editingDomain, activeConcept);
					editingDomain.getCommandStack().execute(addCommand);
					Collection<?> affectedObjects = addCommand.getAffectedObjects();
					ComposedPropertyInstance arrayInstance = (ComposedPropertyInstance) affectedObjects.iterator()
							.next();

					CategoryAssignment newRequirement = arrayInstance.getTypeInstance();
					CategoryAssignmentHelper caHelper = new CategoryAssignmentHelper(newRequirement);

					ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) caHelper
							.getPropertyInstance(FQN_PROPERTY_REQUIREMENT_TYPE);
					ATypeDefinition referencePropertyType = ((ReferenceProperty) propertyInstance.getType())
							.getReferenceType();

					createAddRequirementDialog(editingDomain, affectedObjects, arrayInstance, newRequirement,
							referencePropertyType);
				}

				

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
			checkWriteAccess(buttonAdd);
		}
	}

	/**
	 * @param editingDomain the editing domain of the new requirement
	 * @param affectedObjects affected elements of the add operation
	 * @param arrayInstance the array instance of the requirement table
	 * @param newRequirement the new requirement
	 * @param referencePropertyType the property type of the requirement
	 */
	protected void createAddRequirementDialog(EditingDomain editingDomain, Collection<?> affectedObjects,
			ComposedPropertyInstance arrayInstance, CategoryAssignment newRequirement,
			ATypeDefinition referencePropertyType) {
		ReferenceSelectionDialog dialog = ReferenceSelectionDialog.createRefernceSelectionDialog(
				Display.getCurrent().getActiveShell(), referencePropertyType, adapterFactory);
		dialog.setInput(model.eResource());

		// select first config collection
		for (StructuralElementInstance sei : CategoryAssignmentHelper.getRepository(arrayInstance)
				.getRootEntities()) {
			if (sei.getType().getFullQualifiedName()
					.equals(RequirementsConfigurationCollection.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME)) {
				dialog.setInitialSelection(sei);
				break;
			}
		}

		dialog.setAllowMultiple(false);
		dialog.setDoubleClickSelects(true);

		if (dialog.open() == Dialog.OK) {
			Object selection = dialog.getFirstResult();
			if (selection instanceof CategoryAssignment) {
				initializeRequirement(editingDomain, (CategoryAssignment) selection, newRequirement);
			} else if (selection instanceof StructuralElementInstance) {
				List<CategoryAssignment> reqTypesOfSelection = CategoryAssignmentHelper
						.getNestedCategoryAssignments((StructuralElementInstance) selection,
								RequirementType.FULL_QUALIFIED_CATEGORY_NAME);
				if (!reqTypesOfSelection.isEmpty()) {
					initializeRequirement(editingDomain, reqTypesOfSelection.get(0), newRequirement);
				}
			}
		} else {
			// Clean up
			Command cmd = createDeleteCommand(editingDomain, affectedObjects);
			editingDomain.getCommandStack().execute(cmd);
		}
	}
	
	
	/**
	 * Initialize a new requirement from a given requirement type
	 * 
	 * @param editingDomain the editing domain
	 * @param requirementType the req type
	 * @param newRequirement the new requirment
	 */
	protected void initializeRequirement(EditingDomain editingDomain, CategoryAssignment requirementType,
			CategoryAssignment newRequirement) {
		CategoryAssignment selectedTypeInstance = (CategoryAssignment) requirementType;
		Command cmd = InitializeRequirementCommand.create((TransactionalEditingDomain) editingDomain, newRequirement,
				selectedTypeInstance);
		editingDomain.getCommandStack().execute(cmd);
		refreshTable(editingDomain);
	}
	
	
	/**
	 * Persist the width of all columns
	 */
	protected void saveColumnWidth() {
		IFile file = getModelIFile();
		for (TableViewerColumn column : attColumns) {
			try {
				String width = String.valueOf(column.getColumn().getWidth());
				QualifiedName key = getColumnWithPropertyQualifiedName(attColumns.indexOf(column));
				file.setPersistentProperty(key, width);
			} catch (CoreException e) {
				Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Could not save column width"));
			}
		}
	}
	
	/**
	 * Load the width of all columns
	 */
	protected void restoreColumnWitdh() {
		IFile file = getModelIFile();
		controlListenerActive = false; //disable listner otherwise it triggers in these resize events
		for (TableViewerColumn column : attColumns) {
			try {
				QualifiedName key = getColumnWithPropertyQualifiedName(attColumns.indexOf(column));
				String width = file.getPersistentProperty(key);
				if (width != null) {
					column.getColumn().setWidth(Integer.parseInt(width));
				}
			} catch (CoreException e) {
				Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Could not save column width"));
			}
		}
		controlListenerActive = true;
	}
	
	/**
	 * Create a qualified id for the column width
	 * @param column the column index
	 * @return the qualified id
	 */
	protected QualifiedName getColumnWithPropertyQualifiedName(int column) {
		
		String qualifier = null; 
		if (model instanceof CategoryAssignment) {
			qualifier = ((CategoryAssignment) model).getUuid().toString();
			qualifier += COLUMN_PREFIX + column;
		}
		
		return new QualifiedName(qualifier, COLUMN_PREFIX + column);
	}
	
	/**
	 * Get IFile of the current model element
	 * @return the ifile
	 */
	private IFile getModelIFile() {
		URI uri = model.eResource().getURI();
		IPath path = new Path(uri.toPlatformString(false)); 
		return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
	}
	
	@Override
	public void controlResized(ControlEvent e) {
		if (controlListenerActive) {
			saveColumnWidth();
		}
	}
	
	@Override
	public void controlMoved(ControlEvent e) {
	}

}
