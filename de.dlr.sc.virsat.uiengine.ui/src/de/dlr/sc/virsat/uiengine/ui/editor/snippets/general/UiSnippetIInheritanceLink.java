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

import java.util.Set;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.inheritance.IInheritanceLink;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritanceCopier;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;
import de.dlr.sc.virsat.model.ui.editor.input.VirSatUriEditorInput;
import de.dlr.sc.virsat.uieingine.ui.DVLMEditorPlugin;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiEStructuralFeatureSectionSnippet;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * class ui snippet IName implements the interface ui snippet for the name part
 * @author leps_je
 *
 */
public class UiSnippetIInheritanceLink extends AUiEStructuralFeatureSectionSnippet implements IUiSnippet {

	private static final String SECTION_HEADING = "Inheritance Section";
	private static final int UI_LAYOUT_NR_COLUMNS =  2;	
	private static final int UI_LAYOUT_NR_SUB_COLUMNS =  2;	
		
	private Text textOrigin;
	private Button drillDownToOriginButton;
	
	IInheritanceLink rootOfModel;
	
	/**
	 * Constructor of the snippet initializing internal helpers etc.
	 */
	public UiSnippetIInheritanceLink() {
		super(InheritancePackage.Literals.IINHERITANCE_LINK__SUPER_TIS);
	}
	
	@Override
	public void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite composite, EObject initModel) {
		super.createSwt(toolkit, editingDomain, composite, initModel);
		rootOfModel = getRootInstance(model);
		
		Composite sectionBody = createSectionBody(toolkit, SECTION_HEADING, null, UI_LAYOUT_NR_COLUMNS);

		Label label = toolkit.createLabel(sectionBody, "Inherits from");		
		
		// Split the second column again
		Composite textWithButton = toolkit.createComposite(sectionBody);
		textWithButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		textWithButton.setLayout(new GridLayout(UI_LAYOUT_NR_SUB_COLUMNS, false));
		
		textOrigin = toolkit.createText(textWithButton, "");
		drillDownToOriginButton = toolkit.createButton(textWithButton, "Drill down", SWT.PUSH);

		setUpLabel(label);
		setUpTextOrigin();
		setUpButton();
		
		checkWriteAccess(textOrigin);
	}
		
	/**
	 * Method to set up the Button
	 */
	private void setUpButton() {
	    drillDownToOriginButton.setLayoutData(createDefaultGridData());
	    
	    drillDownToOriginButton.addSelectionListener(new SelectionListener() {
	    	@Override
			public void widgetSelected(SelectionEvent e) {
	    		if (rootOfModel != null) {
	    			VirSatUriEditorInput.openDrillDownEditor(rootOfModel);
	    		}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
	    });

	    if (rootOfModel != null) {
			drillDownToOriginButton.setVisible(!model.equals(rootOfModel));
		} else {
			drillDownToOriginButton.setVisible(false);
		}
	}

	/**
	 * Method to set up the Text for the Origin
	 */
	private void setUpTextOrigin() {
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalAlignment = GridData.FILL;
	 	gridData.grabExcessHorizontalSpace = true;
	    textOrigin.setLayoutData(gridData);
	    
		textOrigin.setEditable(false);
		if (rootOfModel != null && rootOfModel instanceof ATypeInstance) {
			textOrigin.setText(((ATypeInstance) rootOfModel).getFullQualifiedInstanceName());
		}
		
	}

	/**
	 * Method to set up the Label
	 * @param label The label to be shown in the front of all 
	 */
	private void setUpLabel(Label label) {
		label.setLayoutData(createDefaultGridData());
	}
	
	/**
	 * Method to get the root TypeInstance from which the model object originally comes from
	 * @param model The model object to be observed
	 * @return The root TypeInstance
	 */
	private IInheritanceLink getRootInstance(EObject model) {
		if (model instanceof IInheritanceLink) {
			Set<IInheritanceLink> rootTis = InheritanceCopier.getRootSuperTypeInstance((IInheritanceLink) model);
			if (!rootTis.isEmpty()) {
				IInheritanceLink possibleRoot = rootTis.iterator().next();
				if (!possibleRoot.eIsProxy()) {
					return possibleRoot;
				}
			}
		}
		return null;
	}

	@Override
	public void updateState(boolean state) {
		super.updateState(state);
		
		rootOfModel = getRootInstance(model);
		if (rootOfModel != null && rootOfModel instanceof ATypeInstance) {
			textOrigin.setText(((ATypeInstance) rootOfModel).getFullQualifiedInstanceName());
		} else {
			textOrigin.setText("");
			DVLMEditorPlugin.getPlugin().getLog().log(new Status(Status.WARNING, DVLMEditorPlugin.ID, "UiSnippetIInheritanceLink: Failed to get root element of model object"));
		}
	}
	
	@Override
	public boolean isActive(EObject model) {
		if (model instanceof IInheritanceLink) {
			return ((IInheritanceLink) model).isIsInherited();
		}
		return false;
	}
}
