/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.cost.summaryTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostEquipment;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostSummary;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostType;

public class SummaryTypes {

	/**
	 * 
	 * @param costSummary 
	 * @return the CostType with the Value of the cost.
	 */
	public Map<CostType, Double[]> summaryTyp(CostSummary costSummary) {
		Map<CostType, Double[]> map = new HashMap<>();
		
		StructuralElementInstance sei = (StructuralElementInstance) costSummary
				.getTypeInstance().getCategoryAssignmentContainer();
		
		if (sei == null) {
			return map;
		}
		
		IBeanStructuralElementInstance beanParent = new BeanStructuralElementInstance(sei);
		
		List<IBeanStructuralElementInstance> beanSeis = new ArrayList<>(beanParent.
				getDeepChildren(IBeanStructuralElementInstance.class));
		beanSeis.add(beanParent);
		
		for (IBeanStructuralElementInstance beanSei : beanSeis) {
			List<CostEquipment> costEquipments = beanSei.getAll(CostEquipment.class);
			for (CostEquipment costEquipment : costEquipments) {
				Double costs = costEquipment.getCost();
				Double margin = costEquipment.getMargin();
				CostType type = costEquipment.getType();
				Double[] oldValues = map.getOrDefault(type, new Double[] { 0d, 0d });
				map.put(type, new Double[] {oldValues[0] + costs, oldValues[1] + margin}); 
			} 
		} 
		return map;
	}
}
