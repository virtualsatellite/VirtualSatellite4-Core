/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui.cellEditorSupport;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColorCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.extension.visualisation.ui.dialogs.TransparencyDialog;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.ValuePropertyCellEditingSupport;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;
import de.dlr.sc.virsat.model.extension.visualisation.ui.calculations.CalculateColor;

/**
 * This class is responsible for handling the different editing of the parameters in the visualisation editor (color picker, slider, combobox).
 * @author wulk_ja
 *
 */
public class VisValuePropertyCellEditingSupport extends ValuePropertyCellEditingSupport {

	/**
	 * Constructor
	 * @param editingDomain 
	 * @param viewer 
	 * @param property 
	 */
	public VisValuePropertyCellEditingSupport(EditingDomain editingDomain, ColumnViewer viewer, AProperty property) {
		super(editingDomain, viewer, property);
	}

	@Override
	protected void setEditor() {
		if (propertyFqn.contains(Visualisation.PROPERTY_SHAPE)) {
			String[] shapes = { "NONE", "BOX", "SPHERE", "CYLINDER", "CONE" };
			editor = new ComboBoxCellEditor((Composite) viewer.getControl(), shapes, SWT.READ_ONLY);
		} else if (propertyFqn.contains(Visualisation.PROPERTY_COLOR)) {
			editor = new ColorCellEditor((Composite) viewer.getControl());

		} else if (propertyFqn.contains(Visualisation.PROPERTY_TRANSPARENCY)) {
			editor = new DialogCellEditor((Composite) viewer.getControl()) {

					@Override
					protected Object openDialogBox(Control cellEditorWindow) {
						final int ONEHUNDRED = 100;
						Object toSelect = getValue();
						TransparencyDialog dialog = new TransparencyDialog(cellEditorWindow.getShell());
						dialog.setInitialValue(Double.parseDouble((String) toSelect));
						dialog.create();
						if (dialog.open() == Dialog.OK) {
							toSelect = String.valueOf(dialog.getValue() / (double) (ONEHUNDRED));
						}
						return toSelect;
					}
	
				};

		} else {
			editor = new TextCellEditor((Composite) viewer.getControl());
		}
	}

	@Override
	protected Object getValue(Object element) {
		String value = (String) super.getValue(element);

		APropertyInstance propertyInstance = getPropertyInstance(element);
		if (propertyInstance.getType().getName().equals(Visualisation.PROPERTY_SHAPE)) {
			return getShapeValue(value);
		} else if (propertyInstance.getType().getName().equals(Visualisation.PROPERTY_COLOR)) {
			return getColorValue(value);
		}
		return value;
	}

	@Override
	protected void setValue(Object element, Object userInputValue) {
		APropertyInstance propertyInstance = getPropertyInstance(element);
		if (propertyInstance.getType().getName().equals(Visualisation.PROPERTY_SHAPE)) {
			int indexShape = ((Integer) userInputValue);
			String[] shapes = ((ComboBoxCellEditor) editor).getItems();
			userInputValue = shapes[indexShape];
		} else if (propertyInstance.getType().getName().equals(Visualisation.PROPERTY_COLOR)) {
			RGB color = ((RGB) userInputValue);
			int intColor = CalculateColor.calculateRGBToInt(color.red, color.green, color.blue);
			userInputValue = String.valueOf(intColor);
		} else if (propertyInstance.getType().getName().equals(Visualisation.PROPERTY_TRANSPARENCY)) {
			userInputValue = String.valueOf(userInputValue);
		}
		super.setValue(element, userInputValue);
	}

	/**
	 * Get the shape value to set to the editor
	 * @param value value of the current propertyInstance
	 * @return shape value (Index)
	 */
	private Object getShapeValue(String value) {
		
		final int NONE_INDEX = 0;
		final int BOX_INDEX = 1;
		final int SPHERE_INDEX = 2;
		final int CYLINDER_INDEX = 3;
		final int CONE_INDEX = 4;
		
		if (value.equals("NONE")) {
			return NONE_INDEX;
		} else if (value.equals("BOX")) {
			return BOX_INDEX;
		} else if (value.equals("SPHERE")) {
			return SPHERE_INDEX;
		} else if (value.equals("CYLINDER")) {
			return CYLINDER_INDEX;
		} else if (value.equals("CONE")) {
			return CONE_INDEX;
		}
		return 0;
	}
	/**
	 * Get the color value to set to the editor
	 * @param value value of the current propertyInstance
	 * @return color value (RGB Color)
	 */
	private Object getColorValue(String value) {
		
		int intColor = Integer.parseInt(value);
		int[] rgb = CalculateColor.calculateIntToRGB(intColor);
		RGB color = new RGB(rgb[0], rgb[1], rgb[2]);
		return color;
	}
}
