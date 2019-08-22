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

import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.extension.statemachines.model.AConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.uiengine.ui.dialog.ReferenceSelectionDialog;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;


/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * A state forbids another state
 * 
 */
public class UiSnippetSectionForbidsConstraint extends AUiSnippetSectionForbidsConstraint implements IUiSnippet {
	@Override
	protected ReferenceSelectionDialog createReferenceDialogAndSetInput(EObject input, ATypeDefinition referencePropertyType) {
		ReferencePropertyInstance rp = (ReferencePropertyInstance) input;
		if (rp.getType().getName().equals(AConstraint.PROPERTY_STATECONSTRAINING)) {
			ReferenceSelectionDialog dialog = ReferenceSelectionDialog.createRefernceSelectionDialog(Display.getCurrent().getActiveShell(), referencePropertyType, adapterFactory);
			BeanCategoryAssignmentHelper bcaHelper = new BeanCategoryAssignmentHelper();
			StateMachine sm = bcaHelper.getParentCaBeanOfClass(model, StateMachine.class);
			dialog.setInput(sm.getTypeInstance().eContainer());
			return dialog;
		} else {
			return super.createReferenceDialogAndSetInput(input, referencePropertyType);
		}
	}
}
