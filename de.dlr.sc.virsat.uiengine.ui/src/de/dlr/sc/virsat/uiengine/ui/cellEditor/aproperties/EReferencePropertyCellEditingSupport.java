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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferencePropertyHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMPropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;
import de.dlr.sc.virsat.uiengine.ui.dialog.ReferenceSelectionDialog;

/**
 * This class handles the functionality for creating a CellEditingSUpport for
 * EReferencePropertyInstances
 * 
 *
 */
public class EReferencePropertyCellEditingSupport extends APropertyCellEditingSupport {
	
	private ElementTreeSelectionDialog dialog;
	
	/**
	 * constructor for reference property cell editing support instantiate the referenced type
	 * @param editingDomain the editing domain
	 * @param viewer the column viewer
	 * @param property the referenced property
	 */
	public EReferencePropertyCellEditingSupport(EditingDomain editingDomain, ColumnViewer viewer, EReferenceProperty property) {
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
		
		EReferencePropertyInstance propertyInstance = (EReferencePropertyInstance) caHelper.getPropertyInstance(propertyFqn);
		EReferenceProperty propertyDefinition = ((EReferenceProperty) propertyInstance.getType());
		
		EReferencePropertyHelper propertyHelper = new EReferencePropertyHelper();
		
		Set<String> supportedFileExtensions = new HashSet<String>();
		final EClass resolvedType = propertyHelper.getResolvedEClassType(propertyDefinition);
		supportedFileExtensions.add(propertyHelper.getEPackageOfType(propertyDefinition).getName());
		
		editor = new DialogCellEditor((Composite) viewer.getControl()) {
			
			@Override
			protected Object openDialogBox(Control cellEditorWindow) {
				Object toSelect = getValue();
				dialog = ReferenceSelectionDialog.createERefernceSelectionDialog(Display.getCurrent().getActiveShell(), resolvedType, supportedFileExtensions, adapterFactory);
				dialog.setAllowMultiple(false);
				dialog.setDoubleClickSelects(true);
				setReferenceDialogInput(propertyInstance.eResource());
				dialog.setInitialSelection(toSelect);
				if (dialog.open() == Dialog.OK) {
					Object selection = dialog.getFirstResult();
					if (selection instanceof ATypeInstance) {
						return dialog.getFirstResult();
					}
				} 
				return null;
			}
			
		};
		return editor;
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
		EObject value = ((EReferencePropertyInstance) propertyInstance).getReference();
		return value;
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
			Command cmd = SetCommand.create(editingDomain, propertyInstance, PropertyinstancesPackage.Literals.EREFERENCE_PROPERTY_INSTANCE__REFERENCE, userInputValue); 
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