/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.snippet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.impl.EnumValueDefinitionImpl;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;
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
public class UiSnippetSectionStateMachine extends AUiSnippetSectionStateMachine implements IUiSnippet {
	@Override
	protected ReferenceSelectionDialog createReferenceDialogAndSetInput(EObject input, ATypeDefinition referencePropertyType) {
		ReferenceSelectionDialog dialog = ReferenceSelectionDialog.createRefernceSelectionDialog(Display.getCurrent().getActiveShell(), referencePropertyType, adapterFactory);
		EObject sm = model.eContainer();
		dialog.setInput(sm);
		return dialog;
	}
	
	/**
	 * To remove the unit combobox
	 */
	public UiSnippetSectionStateMachine() {
		setStyle(STYLE_NO_ENUM_UNIT_COMBO_BOX);
	}

	@Override
	protected VirSatTransactionalAdapterFactoryLabelProvider createLabelProviderForEnumProperty() {
		VirSatTransactionalAdapterFactoryLabelProvider labelProvider = new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory) {
			@Override
			public String getText(Object object) {
				if (object instanceof EnumValueDefinitionImpl) {
					return ((EnumValueDefinitionImpl) object).getName();
				} 
				return "";
			}
		};
		return labelProvider;
	}
}
