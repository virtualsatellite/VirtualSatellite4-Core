/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.model;

import javax.xml.bind.annotation.XmlType;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.ext.core.model.GenericCategory;
import de.dlr.sc.virsat.model.extension.requirements.verification.build.steps.IAutomaticVerification;

// *****************************************************************
// * Class Declaration
// *****************************************************************

@XmlType(name = AIValueVerification.FULL_QUALIFIED_CATEGORY_NAME)
/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public abstract class IValueVerification extends AIValueVerification implements IAutomaticVerification {
	
	/**
	 * Constructor of Concept Class
	 */
	public IValueVerification() {
		super();
	}
	
	/**
	 * Update the status of the verification method based on the compliance status
	 * of the values
	 * 
	 * @param isCompliant the values compliance status
	 */
	protected void updateStatus(EditingDomain editingDomain, CompoundCommand cc, boolean isCompliant) {
		if (isCompliant && (getStatus().equals(IVerification.STATUS_Open_NAME)
				|| getStatus().equals(IVerification.STATUS_NonCompliant_NAME))) {
			cc.append(setStatusCompliant(editingDomain));
			// If all values changed to be complaint set status to be complaint
		} else if (!isCompliant) {
			cc.append(setStatusNonCompliant(editingDomain));
			// If status was non or partly compliant and values changed to be non-complaint
			// set status to be non-complaint
		}
	}
	
	/**
	 * Get the value property instance from the given trace target
	 * @param traceTarget the element to be verified
	 * @return the value property instance
	 */
	protected ValuePropertyInstance getPropertyInstanceToCheck(GenericCategory traceTarget) {
		ValuePropertyInstance valuePropertyInstance = null;
		ATypeDefinition typeToVerify = getElementToBeVerified();
		if (traceTarget != null) {
			CategoryAssignmentHelper helper = new CategoryAssignmentHelper(traceTarget.getTypeInstance());
			APropertyInstance pInstance = helper.getPropertyInstance((AProperty) typeToVerify);
			
			if (pInstance instanceof ValuePropertyInstance) {
				valuePropertyInstance = (ValuePropertyInstance) pInstance;
			}
		}
		return valuePropertyInstance;
	}
	
	/**
	 * Check if mass equipment is compliant
	 * 
	 * @param verificationMethod
	 * @param massEquipment
	 * @return
	 */
	protected boolean isCompliant(GenericCategory target) {
		ValuePropertyInstance valueContainer = getPropertyInstanceToCheck(target);
		if (valueContainer != null) {

			double value = Double.parseDouble(((ValuePropertyInstance) valueContainer).getValue());
			return isCompliant(value);
			
		}
		return false;
	}
	
	protected abstract boolean isCompliant(double value);
}
