/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMPropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredListContentProvider;
import de.dlr.sc.virsat.uiengine.ui.dialog.ReferenceSelectionDialog;

/**
 * This class handles the functionality for creating a CellEditingSUpport for
 * IntPropertyInstances String PropertyInstances and FloatPropertyInstances
 * 
 * @author fisc_ph
 *
 */
public class ReferencePropertyCellEditingSupport extends APropertyCellEditingSupport {
	
	private ElementTreeSelectionDialog dialog;
	
	/**
	 * constructor for reference property cell editing support instantiate the referenced type
	 * @param editingDomain the editing domain
	 * @param viewer the column viewer
	 * @param property the referenced property
	 */
	public ReferencePropertyCellEditingSupport(EditingDomain editingDomain, ColumnViewer viewer, ReferenceProperty property) {
		super(editingDomain, viewer, property);
	}
	
	@Override
	protected CellEditor getCellEditor(Object element) {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMDVLMItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ConceptsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RolesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertydefinitionsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMPropertyinstancesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		
		ReferencePropertyInstance propertyInstance = (ReferencePropertyInstance) getPropertyInstance(element);
		ATypeDefinition referencePropertyType = ((ReferenceProperty) propertyInstance.getType()).getReferenceType();
		
		editor = new DialogCellEditor((Composite) viewer.getControl()) {
			
			@Override
			protected Object openDialogBox(Control cellEditorWindow) {
				Object toSelect = getValue();
				dialog = ReferenceSelectionDialog.createRefernceSelectionDialog(Display.getCurrent().getActiveShell(), referencePropertyType, adapterFactory);
				dialog.setAllowMultiple(false);
				dialog.setDoubleClickSelects(true);
				setReferenceDialogInput(propertyInstance.eResource());
				setFiltersForDialog();
				dialog.setInitialSelection(toSelect);
				if (dialog.open() == Dialog.OK) {
					Object selection = dialog.getFirstResult();
					if (selection instanceof ATypeInstance) {
						return dialog.getFirstResult();
					}
				} 
				return null;
			}
			
			@Override
			protected Button createButton(final Composite parent) {
				// This override is needed to the following eclipse bug
				// https://bugs.eclipse.org/bugs/show_bug.cgi?id=193081
				Button button = super.createButton(parent);

				// This listener hands back traversal control to the cell rather the button. 
				// This is important if TableEditor functionality is used. if the button handles the traverse 
				// signal, it will try to select the next button but not the next cell.
				button.addListener(SWT.Traverse, (event) -> parent.notifyListeners(SWT.Traverse, event));
				return button;
			}
		};
		return editor;
	}
	
	protected void setFiltersForDialog() {
		VirSatFilteredListContentProvider cp = (VirSatFilteredListContentProvider) ((ReferenceSelectionDialog) dialog).getContentProvider();
		getResultFilters().forEach(cp::addFunctionFilterToGetElement);
	}
	
	/**
	 * An overridable method to filter the dialogs selectable objects
	 * 
	 * @return filter functions
	 */
	protected List<Function<Object, Boolean>> getResultFilters() {
		return new ArrayList<>();
	}

	/**
	 * An overridable method to set dialog input
	 * @param input the input for the dialog
	 */
	protected void setReferenceDialogInput(Object input) {
		dialog.setInput(input);
	}
	
	@Override
	protected Object getValue(Object element) {
		APropertyInstance propertyInstance = getPropertyInstance(element);
		ATypeInstance value = ((ReferencePropertyInstance) propertyInstance).getReference();
		return value;
	}
	
	@Override
	protected void initializeCellEditorValue(CellEditor cellEditor, ViewerCell cell) {
		cellEditor.setValue(cell.getText());
	}
	
	@Override
	protected boolean canEdit(Object element) {
		boolean canEdit = createSetCommand(element, null).canExecute();
		return canEdit;
	}
	
	@Override
	protected Command createSetCommand(Object element, Object userInputValue) {
		APropertyInstance propertyInstance = getPropertyInstance(element);
		if (propertyInstance != null) {
			Command cmd = SetCommand.create(editingDomain, propertyInstance, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, userInputValue); 
			return cmd;
		}
		return UnexecutableCommand.INSTANCE;
	}
	
	@Override
	protected void setValue(Object element, Object userInputValue) {
		Command cmd = createSetCommand(element, userInputValue);  
		editingDomain.getCommandStack().execute(cmd);
		viewer.update(element, null);
	}
}