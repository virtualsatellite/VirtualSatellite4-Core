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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.widgets.Composite;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * Cell editing support for Unit of a QUDV property
 */
public class QudvUnitCellEditingSupport extends ValuePropertyCellEditingSupport {
	
	private List<AUnit> allUnits;
	private AdapterFactoryLabelProvider unitsLabelProvider;
	
	private List<AUnit> currentUnits;
	private Map<AUnit, Integer> currentUnitIndexes;
	
	/**
	 * @param editingDomain 
	 * @param viewer 
	 * @param property 
	 */
	public QudvUnitCellEditingSupport(EditingDomain editingDomain, ColumnViewer viewer, AProperty property) {
		super(editingDomain, viewer, property);
		initializeUnits(property);
	}
	
	/**
	 * Gets all the units from system of units of current study
	 * @param property a property from the study. Only used to get a study resource set
	 */
	private void initializeUnits(AProperty property) {
		VirSatResourceSet virSatResourceSet = VirSatResourceSet.getVirSatResourceSet(property);
		SystemOfUnits systemOfUnits = virSatResourceSet.getUnitManagement().getSystemOfUnit();
		
		allUnits = new ArrayList<>();
		allUnits.addAll(systemOfUnits.getUnit());
		allUnits.sort(new Comparator<AUnit>() {
			@Override
			public int compare(AUnit arg0, AUnit arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}
		});
		
		//we usa a label provider to get the texts for units
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		unitsLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
	}
	
	@Override
	protected boolean canEdit(Object element) {
		setFilteredUnitsAsComboBoxInput(element);
		return super.canEdit(element);
	}
	
	/**
	 * Forms a list of units and passes them to the combo box, filtered by quantity kind if necessary
	 * @param element current table element under edit
	 */
	private void setFilteredUnitsAsComboBoxInput(Object element) {
		String quantityKindToShow = getPropertyQuantityKind(element);
		currentUnits = new ArrayList<>();
		currentUnits.add(null); //empty no unit
		if (quantityKindToShow == null) {
			currentUnits.addAll(allUnits);
		} else {
			for (AUnit unit : allUnits) {
				if (unit.getQuantityKind().getName().equals(quantityKindToShow)) {
					currentUnits.add(unit);
				}
			}
		}
		String[] comboItems = new String[currentUnits.size()];
		currentUnitIndexes = new HashMap<>();
		for (int i = 0; i < currentUnits.size(); i++) {
			AUnit unit = currentUnits.get(i);
			currentUnitIndexes.put(unit, i);
			comboItems[i] = unitsLabelProvider.getText(unit);
		}
		ComboBoxCellEditor cbEditor = (ComboBoxCellEditor) editor;
		cbEditor.setItems(comboItems);
	}

	/**
	 * Gets a quantity kind for the passed property instance element
	 * @param element property instance element under edit
	 * @return quantity kind string or null
	 */
	private String getPropertyQuantityKind(Object element) {
		String quantityKindName = null;
		
		EObject container = getPropertyInstance(element);
		while (quantityKindName == null && container != null) {
			if (container instanceof APropertyInstance) {
				APropertyInstance cpi = (APropertyInstance) container;
				ATypeDefinition property = cpi.getType();
				if (property instanceof AQudvTypeProperty) {
					quantityKindName = ((AQudvTypeProperty) property).getQuantityKindName();
				}
				if (property instanceof ComposedProperty) {
					quantityKindName = ((ComposedProperty) property).getQuantityKindName();
				}
			}
			container = container.eContainer();
		}
		
		return quantityKindName;
	}
		
	@Override
	protected void setEditor() {
		String[] items = {""}; //actual units will be set when in canEdit for the known property
		editor = new ComboBoxCellEditor((Composite) viewer.getControl(), items);
	}

	@Override
	protected Object getValue(Object element) {
		APropertyInstance propertyInstance = getPropertyInstance(element);
		AUnit unit = ((UnitValuePropertyInstance) propertyInstance).getUnit();
		return currentUnitIndexes.get(unit);
	}
	
	@Override
	protected Command createSetCommand(Object element, Object userInputValue) {
		if (userInputValue == CHANGE_VALUE) {
			userInputValue = Integer.valueOf(0);
		}
		
		if (((int) userInputValue) < 0) {
			return UnexecutableCommand.INSTANCE;
		}
		
		AUnit unit = currentUnits.get((int) userInputValue);
		
		APropertyInstance propertyInstance = getPropertyInstance(element);
		if (propertyInstance != null) {
			Command cmd = SetCommand.create(editingDomain, propertyInstance, PropertyinstancesPackage.Literals.IUNIT_PROPERTY_INSTANCE__UNIT, unit); 
			return cmd;
		}
		return UnexecutableCommand.INSTANCE;
	}
}

