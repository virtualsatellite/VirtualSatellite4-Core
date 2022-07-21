/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.cost.model.util;

import java.util.LinkedList;
import java.util.List;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostType;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostTypesCollection;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;


/**
 * 
 * Helper class for working with the cost collection.
 */
public class CostArchitectureHelper {

	public static final String COST_COLLECTION_FQN = CostTypesCollection.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME;

	/**
	 * This method returns all CostTypes from a given Repository
	 * @param repo The repository in which to look for CostTypes
	 * @return A List with all CostTypes it could find
	 */
	public List<CostType> getAllCostTypes(Repository repo) {

		List<StructuralElementInstance> seiTypeContainers = new LinkedList<>();
		
		//Get all Structural Element instances which are typed as a a container for CostTypes 
		for (StructuralElementInstance sei : repo.getRootEntities()) {
			if (COST_COLLECTION_FQN.equals(sei.getType().getFullQualifiedName())) {
				seiTypeContainers.add(sei);
			}
		}
		
		// Now start accumulating all CostTypes into a common list
		List<CostType> allCostTypes = new LinkedList<>();
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		
		for (StructuralElementInstance sei : seiTypeContainers) {
			List<CostType> seiCostTypes = bCaHelper.getAllBeanCategories(sei, CostType.class);
			allCostTypes.addAll(seiCostTypes);
		}
		
		return allCostTypes;
	}		
}
