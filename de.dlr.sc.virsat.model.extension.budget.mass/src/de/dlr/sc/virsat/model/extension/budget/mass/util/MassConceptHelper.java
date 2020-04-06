/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.mass.util;

import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.budget.mass.model.MassEquipment;
import de.dlr.sc.virsat.model.extension.budget.mass.model.MassSummary;

/**
 * Mass concept helper to help getting the mass Beans from a SEI
 * @author fisc_ph
 *
 */
public class MassConceptHelper {
	
	//CHECKSTYLE:OFF
	public BeanStructuralElementInstance bSei = new BeanStructuralElementInstance();
	public MassSummary massSummary;
	public MassEquipment massEquipment;
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
		if (object instanceof StructuralElementInstance) {
			bSei = new BeanStructuralElementInstance();
			bSei.setStructuralElementInstance((StructuralElementInstance) object);
			
			massSummary = bSei.getFirst(MassSummary.class);
			massEquipment = bSei.getFirst(MassEquipment.class);
			return true;
		}
		
		massSummary = null;
		massEquipment = null;
		return false;
	}
}