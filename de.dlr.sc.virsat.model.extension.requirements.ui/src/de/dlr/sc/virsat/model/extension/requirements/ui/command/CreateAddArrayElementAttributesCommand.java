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
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class CreateAddArrayElementAttributesCommand extends ACreateAddArrayElementAttributesCommand {
	
	@Override
	public Command create(EditingDomain editingDomain, ArrayInstance arrayInstance, Category type) {
		if (editingDomain == null) {
			return UnexecutableCommand.INSTANCE;
		}
		ComposedPropertyInstance ati = (ComposedPropertyInstance) new CategoryInstantiator().generateInstance(arrayInstance, type);
		RequirementAttribute attDef = new RequirementAttribute(ati.getTypeInstance());
		attDef.setType(RequirementAttribute.TYPE_String_NAME);
		attDef.setName("NewAttribute");
		return AddCommand.create(editingDomain, arrayInstance, PropertyinstancesPackage.Literals.ARRAY_INSTANCE__ARRAY_INSTANCES, ati);
	}
	
}
