/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.ui.command;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumPropertyHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.extension.requirements.model.IVerification;

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class CreateAddArrayElementVerificationCommand extends ACreateAddArrayElementVerificationCommand {
	
	@Override
	public Command create(EditingDomain editingDomain, ArrayInstance arrayInstance, Category type) {
		ATypeInstance ati = new CategoryInstantiator().generateInstance(arrayInstance, type);
		CategoryAssignment verificationInstance = (CategoryAssignment) ((ComposedPropertyInstance) ati).getTypeInstance();
		configureIVerification(verificationInstance, type);
		return AddCommand.create(editingDomain, arrayInstance, PropertyinstancesPackage.Literals.ARRAY_INSTANCE__ARRAY_INSTANCES, ati);
	}
	
	/**
	 * Configure the verification instance with its type
	 * @param verificationInstance the instance
	 * @param type the type
	 * 
	 * Cannot use bean here as IVerification is abstract
	 */
	protected void configureIVerification(CategoryAssignment verificationInstance, Category type) {
		CategoryAssignmentHelper caHelper = new CategoryAssignmentHelper(verificationInstance);
		EnumPropertyHelper enumHelper = new EnumPropertyHelper();
		
		verificationInstance.setName(type.getName());
		EnumUnitPropertyInstance piStatus = (EnumUnitPropertyInstance) caHelper.getPropertyInstance(IVerification.PROPERTY_STATUS);
		piStatus.setValue(enumHelper.getEvdForName((EnumProperty) piStatus.getType(), IVerification.STATUS_Open_NAME));
	}
	
}
