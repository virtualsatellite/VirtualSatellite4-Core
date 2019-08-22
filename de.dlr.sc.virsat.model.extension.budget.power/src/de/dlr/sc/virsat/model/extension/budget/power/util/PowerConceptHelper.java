/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.power.util;

import java.util.List;

import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.budget.power.model.APowerParameters;
import de.dlr.sc.virsat.model.extension.budget.power.model.PowerEquipment;
import de.dlr.sc.virsat.model.extension.budget.power.model.PowerSummary;

/**
 * Power concept helper to help getting the power Beans from a SEI
 * @author muel_s8
 *
 */
public class PowerConceptHelper {
	
	//CHECKSTYLE:OFF
	public BeanStructuralElementInstance bSei = new BeanStructuralElementInstance();
	public PowerSummary powerSummary;
	public PowerEquipment powerEquipment;
	//CHECKSTYLE:ON
	
	/**
	 * This method extracts all important objects from a given object, which means
	 * this method tries to create A BeanSEI and tries to find a MassSummary Bean and
	 * MassEquipment Bean which is needed for further processing. In case the Mass Categories
	 * were not found to be assigned to the object, they will be set to null
	 * @param object The object of which to get the Mass Categories
	 * @return true in case we found a SEI Bean otherwise false
	 */
	public boolean initBeansForObject(Object object) {
		
		if (object instanceof ABeanStructuralElementInstance) {
			object = ((ABeanStructuralElementInstance) object).getStructuralElementInstance();
		}
		
		if (object instanceof StructuralElementInstance) {
			bSei = new BeanStructuralElementInstance();
			bSei.setStructuralElementInstance((StructuralElementInstance) object);
			
			List<PowerSummary> powersSummary = bSei.getAll(PowerSummary.class);
			List<PowerEquipment> powersEquipment = bSei.getAll(PowerEquipment.class);
			
			powerSummary = (powersSummary.size() == 1) ? powersSummary.get(0) : null;
			powerEquipment = (powersEquipment.size() == 1) ? powersEquipment.get(0) : null;
			return true;
		}
		
		powerSummary = null;
		powerEquipment = null;
		
		return false;
	}
	
	/**
	 * Gets the powerSummary if it exists. If not, gets the powerEquipment if it exists.
	 * If not, returns null.
	 * @return a powerParameter
	 */
	public APowerParameters getPowerParameters() {
		return powerSummary != null 
				? powerSummary
				: (powerEquipment != null ? powerEquipment : null);
	}
}