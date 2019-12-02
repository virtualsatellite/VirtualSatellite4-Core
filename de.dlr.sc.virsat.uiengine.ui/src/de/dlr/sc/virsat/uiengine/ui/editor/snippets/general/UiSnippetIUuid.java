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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiEStructuralFeatureSectionSnippet;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * class ui snippet iuuid implements the ui snippet for the uuid ui part
 * @author leps_je
 *
 */
public class UiSnippetIUuid extends AUiEStructuralFeatureSectionSnippet implements IUiSnippet {

	private static final String SECTION_HEADING = "UUID Section";
	private static final int UI_LAYOUT_NR_COLUMNS =  2;	
	
	private Text textUuid;
	
	
	/**
	 * Constructor of the snippet initializing internal helpers etc.
	 */
	public UiSnippetIUuid() {
		super(GeneralPackage.Literals.IUUID__UUID);
	}

	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);
		
		Composite sectionBody = createSectionBody(toolkit, SECTION_HEADING, null, UI_LAYOUT_NR_COLUMNS);
		
		Label label = toolkit.createLabel(sectionBody, "UUID");
		textUuid = toolkit.createText(sectionBody, "");
			
		setUpLabel(label);
		setUpText();
	}

	/**
	 * Method to set up the Uuid Text
	 */
	private void setUpText() {

		Display display = Display.getCurrent();
		Color darkGray = display.getSystemColor(SWT.COLOR_DARK_GRAY);
		Color gray = display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
		
		textUuid.setEditable(false);
		textUuid.setBackground(gray);
		textUuid.setForeground(darkGray);

		GridData gridData = createDefaultGridData();
	    gridData.horizontalSpan = 1;
	    textUuid.setLayoutData(gridData);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setDataBinding(DataBindingContext dbCtx, EditingDomain editingDomain, EObject model) {
		super.setDataBinding(dbCtx, editingDomain, model);
		
		IConverter converter = new IConverter() {

			@Override
			public Object getFromType() {
				return VirSatUuid.class;
			}

			@Override
			public Object getToType() {
				return String.class;
			}

			@Override
			public Object convert(Object fromObject) {
				VirSatUuid uuid = (VirSatUuid) fromObject;
				return uuid.toString();
			}
		};

		if (textUuid != null) {
			IValueProperty<EObject, ?> iUuidProperty = EMFEditProperties.value(editingDomain, GeneralPackage.eINSTANCE.getIUuid_Uuid());
			
			dbCtx.bindValue(WidgetProperties.text(SWT.Modify).observe(textUuid), iUuidProperty.observe(model),
			        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
			        new UpdateValueStrategy().setConverter(converter)
			);
		}
	}

}
