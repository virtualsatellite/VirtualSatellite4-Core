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

import java.util.Collections;
import java.util.Set;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.build.marker.ui.EsfMarkerImageProvider;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.project.markers.IMarkerHelper;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiEStructuralFeatureSectionSnippet;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * class ui snippet IDescription implements the interface ui snippet for the description part
 *
 */
public class UiSnippetIDescription extends AUiEStructuralFeatureSectionSnippet implements IUiSnippet {

	public static final String SECTION_HEADING = "Description Section";
	public static final String DESCRIPTION_FIELD = "Description";
	
	private static final int UI_LAYOUT_NR_COLUMNS =  2;	
	
	private EsfMarkerImageProvider emip;
	
	private Text textDescription;
	private Label labelPropertyIcon;

	
	/**
	 * Constructor of the snippet initializing internal helpers etc.
	 */
	public UiSnippetIDescription() {
		super(GeneralPackage.Literals.IDESCRIPTION__DESCRIPTION);
		emip = new EsfMarkerImageProvider();
	}
	
	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);
		
		Composite sectionBody = createSectionBody(toolkit, SECTION_HEADING, null, UI_LAYOUT_NR_COLUMNS);
		
		// Divide left column into new parts
		Composite labelWithIcon = toolkit.createComposite(sectionBody);
		labelWithIcon.setLayout(new GridLayout(2, false));
		
		labelPropertyIcon = toolkit.createLabel(labelWithIcon, "");
		Label labelDescription = toolkit.createLabel(labelWithIcon, DESCRIPTION_FIELD);
		textDescription = toolkit.createText(sectionBody, "", SWT.MULTI | SWT.V_SCROLL | SWT.BORDER | SWT.WRAP);
		
		setUpIcon();
		setUpLabel(labelDescription);		
		setUpText(editingDomain);
	    
		checkWriteAccess(textDescription);
	}

	/**
	 * Method to set up the Text with specific Grid
	 * @param editingDomain the editingDomain
	 */
	private void setUpText(EditingDomain editingDomain) {
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.heightHint = 100;
	    textDescription.setLayoutData(gridData);
	}

	/**
	 * Method to set up the label
	 * @param label The label to be set up
	 */
	private void setUpLabel(Label label) {
	    label.setLayoutData(createDefaultGridData());
	}

	/**
	 * Method to set up the Icon
	 */
	private void setUpIcon() {
		labelPropertyIcon.setLayoutData(new GridData());
		Image dummyImage = emip.getOkImage();
		labelPropertyIcon.setImage(dummyImage);
	}
	
	@Override
	public void setDataBinding(DataBindingContext dbCtx, EditingDomain editingDomain, EObject model) {
		super.setDataBinding(dbCtx, editingDomain, model);
		if (textDescription != null) {
			@SuppressWarnings("unchecked")
			IValueProperty<EObject, ?> descriptionProperty = EMFEditProperties.value(editingDomain, GeneralPackage.Literals.IDESCRIPTION__DESCRIPTION);
			dbCtx.bindValue(WidgetProperties.text(SWT.Modify).observe(textDescription), descriptionProperty.observe(model));
		}
	}

	@Override
	public void updateState(boolean state) {
		super.updateState(state);
		
		Image problemImage = emip.getProblemImageForStructuralFeatureInEobject(model, GeneralPackage.Literals.IDESCRIPTION__DESCRIPTION);
		if (problemImage == null) {
			labelPropertyIcon.setVisible(false);
		} else {
			labelPropertyIcon.setVisible(true);
			labelPropertyIcon.setImage(problemImage);
			labelPropertyIcon.setToolTipText(emip.getProblemImageToolTipForStructuralFeatureInEobject(model, GeneralPackage.Literals.IDESCRIPTION__DESCRIPTION));
		}
	}
	
	@Override
	protected Set<IMarkerHelper> getMarkerHelpers() {
		return Collections.singleton(emip.getVpmHelper());
	}
}
