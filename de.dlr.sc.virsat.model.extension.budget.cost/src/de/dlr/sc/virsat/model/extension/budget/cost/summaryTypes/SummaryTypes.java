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
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostEquipment;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostSummary;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostTableEntry;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostType;

public class SummaryTypes {

	private static final int FACTOR_HUNDRED = 100;

	public SummaryTypes() {
	}

	/**
	 * 
	 * @param costSummary
	 * @return the CostType with the Value of the cost.
	 */
	public Map<CostType, CostTableEntry> summaryTyp(CostSummary costSummary) {
		Map<CostType, CostTableEntry> map = new HashMap<>();

		StructuralElementInstance sei = (StructuralElementInstance) costSummary.getTypeInstance()
				.getCategoryAssignmentContainer();

		if (sei == null) {
			return map;
		}
		IBeanStructuralElementInstance beanParent = new BeanStructuralElementInstance(sei);

		List<IBeanStructuralElementInstance> beanSeis = new ArrayList<>(
				beanParent.getDeepChildren(IBeanStructuralElementInstance.class));
		beanSeis.add(beanParent);

		Concept concept = costSummary.getConcept();

		for (IBeanStructuralElementInstance beanSei : beanSeis) {
			List<CostEquipment> costEquipments = beanSei.getAll(CostEquipment.class);
			for (CostEquipment costEquipment : costEquipments) {
				Double costs = costEquipment.getCost();
				Double costWithMargin = costEquipment.getCostWithMargin();
				CostType type = costEquipment.getType();
				String typeName = type.getName();
				CostTableEntry entry = map.computeIfAbsent(type, key -> new CostTableEntry(concept));
				entry.setCost(costs + entry.getCost());
				entry.setCostWithMargin(costWithMargin + entry.getCostWithMargin());
				entry.setName(typeName);
				entry.setType(type);
			}
		}
		for (CostTableEntry entry : map.values()) {
			
			entry.setCostMargin(entry.getCostWithMargin() - entry.getCost());
			entry.setMargin(FACTOR_HUNDRED * entry.getCostMargin() / entry.getCost());
		}
		return map;
	}
}