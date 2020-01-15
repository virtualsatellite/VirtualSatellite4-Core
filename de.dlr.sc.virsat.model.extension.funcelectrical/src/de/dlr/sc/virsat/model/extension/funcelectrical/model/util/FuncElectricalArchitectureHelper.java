/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.model.util;

import java.util.LinkedList;
import java.util.List;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceTypeCollection;


/**
 * 
 * @author bell_er
 *
 */
public class FuncElectricalArchitectureHelper {

	public static final String INTERFACE_TYPES_CONTAINER_ID = InterfaceTypeCollection.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME;

	/**
	 * This method returns all InterafceTypes from a given Repository
	 * @param repo The repository in which to look for InterfaceTypes
	 * @return A List with all InterfaceTypes it could find
	 */
	public List<InterfaceType> getAllInterfaceTypes(Repository repo) {

		List<StructuralElementInstance> seiTypeContainers = new LinkedList<>();
		
		//Get all Structural Element instances which are typed as a a container for InterfaceTypes 
		for (StructuralElementInstance sei : repo.getRootEntities()) {
			if (INTERFACE_TYPES_CONTAINER_ID.equals(sei.getType().getFullQualifiedName())) {
				seiTypeContainers.add(sei);
			}
		}
		
		// Now start accumulating all Interface Types into a common list
		List<InterfaceType> allInterfaceTypes = new LinkedList<>();
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		
		for (StructuralElementInstance sei : seiTypeContainers) {
			List<InterfaceType> seiInterfaceTypes = bCaHelper.getAllBeanCategories(sei, InterfaceType.class);
			allInterfaceTypes.addAll(seiInterfaceTypes);
		}
		
		return allInterfaceTypes;
	}		
}
