/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui.snippet;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.EnumValueDefinitionImpl;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;
import de.dlr.sc.virsat.model.extension.visualisation.ui.calculations.CalculateColor;
import de.dlr.sc.virsat.model.extension.visualisation.ui.dialogs.TransparencyDialog;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatTransactionalAdapterFactoryContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;


/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class UiSnippetSectionVisualisation extends AUiSnippetSectionVisualisation implements IUiSnippet {
	
	private Map<String, Widget> mapPropertyToVisualisationWidget = new HashMap<>();
	
	/**
	 * 
	 */
	public UiSnippetSectionVisualisation() {
		setStyle(STYLE_NO_ENUM_UNIT_COMBO_BOX);
	}
	
	@Override
	protected void createCustomPropertyWidgets(FormToolkit toolkit, EditingDomain editingDomain, Composite sectionBody, AProperty property) {
		
		if (property.getName().equals(Visualisation.PROPERTY_COLOR)) {
			createColorPropertyWidgets(toolkit, sectionBody, property);
		} else if (property.getName().equals(Visualisation.PROPERTY_TRANSPARENCY)) {
			createTransparencyPropertyWidgets(toolkit, sectionBody, property);
		} else {
			super.createCustomPropertyWidgets(toolkit, editingDomain, sectionBody, property);
		}
	}

	@Override
	protected ViewerComparator createEnumComboboxComparator() {
		return null;
	}
	
	@Override
	protected VirSatTransactionalAdapterFactoryContentProvider createContentProviderForEnumProperty() {
		return new VirSatTransactionalAdapterFactoryContentProvider(adapterFactory);
	}
	
	@Override
	protected VirSatTransactionalAdapterFactoryLabelProvider createLabelProviderForEnumProperty() {
		VirSatTransactionalAdapterFactoryLabelProvider labelProvider = new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory) {
			@Override
			public String getText(Object object) {
				return ((EnumValueDefinitionImpl) object).getName();
			}
		};
		return labelProvider;
	}
	
	/**
	 * Method to create property specific widgets
	 * @param toolkit the toolkit which should be used to create the widgets
	 * @param sectionBody the section body in which the widgets should be placed
	 * @param property the property on which these widgets should act
	 */
	private void createColorPropertyWidgets(FormToolkit toolkit, Composite sectionBody, AProperty property) { 
		GridData gridData = createDefaultGridData();
		gridData.horizontalSpan = UI_LAYOUT_SPAN_COLUMNS_3;
		Text textPropertyValue = toolkit.createText(sectionBody, "");
		textPropertyValue.setLayoutData(gridData);
		textPropertyValue.setEditable(false);
		
		textPropertyValue.addListener(SWT.FOCUSED, new Listener() {				
			@Override
			public void handleEvent(Event event) {
				ColorDialog colorDialog = new ColorDialog(textPropertyValue.getShell());
				RGB rgbColor = colorDialog.open();
				if (rgbColor != null) {
					int intColor = CalculateColor.calculateRGBToInt(rgbColor.red, rgbColor.green, rgbColor.blue);
					textPropertyValue.setText(String.valueOf(intColor));
					textPropertyValue.setBackground(new Color(null, rgbColor));
				}	
			}
		});
		
		String propertyFqn = property.getFullQualifiedName();
		mapPropertyToVisualisationWidget.put(propertyFqn, textPropertyValue);
	}

	/**
	 * Method to create property specific widgets
	 * @param toolkit the toolkit which should be used to create the widgets
	 * @param sectionBody the section body in which the widgets should be placed
	 * @param property the property on which these widgets should act
	 */
	private void createTransparencyPropertyWidgets(FormToolkit toolkit, Composite sectionBody, AProperty property) {
		final double ONEHUNDERED = 100f;
		GridData gridData = createDefaultGridData();
		gridData.horizontalSpan = UI_LAYOUT_SPAN_COLUMNS_3;
		
		Text textPropertyValue = toolkit.createText(sectionBody, "");
		textPropertyValue.setLayoutData(gridData);
		textPropertyValue.setEditable(false);
		
		textPropertyValue.addListener(SWT.FOCUSED, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					TransparencyDialog dialog = new TransparencyDialog(textPropertyValue.getShell());
					double initialTransparency = Double.parseDouble(textPropertyValue.getText());
					dialog.setInitialValue(initialTransparency);
					if (dialog.open() == Dialog.OK) {
						int percentageTransparency = dialog.getValue();
						textPropertyValue.setText(String.valueOf(percentageTransparency / ONEHUNDERED));
					}
				}	

			});
		
		String propertyFqn = property.getFullQualifiedName();
		mapPropertyToVisualisationWidget.put(propertyFqn, textPropertyValue);
	}		


	@Override
	public void setDataBinding(DataBindingContext dbCtx, EditingDomain editingDomain, EObject model) {

		super.setDataBinding(dbCtx, editingDomain, model); 
			
		// Loop over all the property instances get their type
		// and see if this section has some widget for it
		for (APropertyInstance propertyInstance : caModel.getPropertyInstances()) {
			AProperty property = (AProperty) propertyInstance.getType();
			String propertyFqn = property.getFullQualifiedName();
			
			Widget visualisationWidget = mapPropertyToVisualisationWidget.get(propertyFqn); 
			if (visualisationWidget != null) {
				@SuppressWarnings("unchecked")
				IValueProperty<EObject, ?> vpiValueProperty = EMFEditProperties.value(editingDomain, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE);
				if (visualisationWidget instanceof CCombo) {
					dbCtx.bindValue(WidgetProperties.ccomboSelection().observe((CCombo) visualisationWidget), vpiValueProperty.observe(propertyInstance));
				} else if (visualisationWidget instanceof Text) { 
					dbCtx.bindValue(WidgetProperties.text(SWT.Modify).observe(visualisationWidget), vpiValueProperty.observe(propertyInstance));
				} 	
			}
			
		}
	}	
}
	
	