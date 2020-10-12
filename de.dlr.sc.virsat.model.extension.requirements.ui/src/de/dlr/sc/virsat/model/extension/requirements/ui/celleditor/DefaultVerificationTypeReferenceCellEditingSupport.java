/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.ui.celleditor;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.extension.requirements.model.DefaultVerification;
import de.dlr.sc.virsat.model.extension.requirements.model.VerificationType;
import de.dlr.sc.virsat.uiengine.ui.cellEditor.aproperties.ReferencePropertyCellEditingSupport;

/**
 * Class simply using bean setters for default verification type
 *
 */
public class DefaultVerificationTypeReferenceCellEditingSupport extends ReferencePropertyCellEditingSupport {

	/**
	 * @param editingDomain
	 * @param viewer
	 * @param property
	 */
	public DefaultVerificationTypeReferenceCellEditingSupport(EditingDomain editingDomain, ColumnViewer viewer,
			ReferenceProperty property) {
		super(editingDomain, viewer, property);
	}

	@Override
	protected Command createSetCommand(Object element, Object userInputValue) {
		// Checking the element
		CategoryAssignment caDefaultVerification = null;
		if (element instanceof CategoryAssignment) {
			caDefaultVerification = (CategoryAssignment) element;
		} else if (element instanceof ComposedPropertyInstance) {
			caDefaultVerification = ((ComposedPropertyInstance) element).getTypeInstance();
		} else {
			return super.createSetCommand(element, userInputValue);
		}
		
		// Checking the user input
		if (userInputValue instanceof CategoryAssignment  
				&& ((CategoryAssignment) userInputValue).getType().getFullQualifiedName().equals(VerificationType.FULL_QUALIFIED_CATEGORY_NAME)
				&& caDefaultVerification.getType().getFullQualifiedName().equals(DefaultVerification.FULL_QUALIFIED_CATEGORY_NAME)) {
			return new DefaultVerification(caDefaultVerification).setVerificationType(editingDomain, new VerificationType((CategoryAssignment) userInputValue));
		} else {
			return super.createSetCommand(element, userInputValue);
		}
	}

}
