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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostEquipment;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostSummary;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostType;

public class SummaryTypesTest {

	protected Concept concept;
	protected StructuralInstantiator structInstantiator;
	protected StructuralElement se;
	
	@Before
	public void setUp() throws Exception {
		String conceptXmiPluginPath = "de.dlr.sc.virsat.model.extension.budget.cost/concept/concept.xmi";
		concept = de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPath);
	
		se = StructuralFactory.eINSTANCE.createStructuralElement();
		structInstantiator = new StructuralInstantiator();
	}
	
	@Test
	public void testEmpty() {
		CostSummary costSummary = new CostSummary(concept);
		SummaryTypes summaryTyp = new SummaryTypes();
		Map<CostType, Double> summary = summaryTyp.summaryTyp(costSummary);
		
		assertTrue(summary.isEmpty());
	}
	
	@Test
	public void testSameTypes() {
		// Build a simple test model
		// ...
		StructuralElementInstance parentSei = structInstantiator.generateInstance(se, "parent");
		BeanStructuralElementInstance parent = new BeanStructuralElementInstance(parentSei);
		
		CostSummary costSummary = new CostSummary(concept);
		parent.add(costSummary);
		
		CostType materialCost = new CostType(concept);
		
		//CHECKSTYLE:OFF
		CostEquipment costEquipment1 = new CostEquipment(concept);
		costEquipment1.setType(materialCost);
		costEquipment1.setCost(10);
		parent.add(costEquipment1);
		
		CostEquipment costEquipment2 = new CostEquipment(concept);
		costEquipment2.setType(materialCost);
		costEquipment2.setCost(5);
		parent.add(costEquipment2);
		//CHECKSTYLE:ON
		
		SummaryTypes summaryTyp = new SummaryTypes();
		
		Map<CostType, Double> summary = summaryTyp.summaryTyp(costSummary);
		//CHECKSTYLE:OFF
		assertEquals(1, summary.size());
		double totalValue = summary.get(materialCost);
		assertEquals(15, totalValue, 0.0001);
		//CHECKSTYLE:ON
	}

	@Test
	public void testDifferentTypes() {
		// Build a simple test model
		// ...
		StructuralElementInstance parentSei = structInstantiator.generateInstance(se, "parent");
		BeanStructuralElementInstance parent = new BeanStructuralElementInstance(parentSei);
		
		CostSummary costSummary = new CostSummary(concept);
		parent.add(costSummary);
		
		CostType materialCost = new CostType(concept);
		CostType testCosts = new CostType(concept);
		//CHECKSTYLE:OFF
		CostEquipment costEquipment1 = new CostEquipment(concept);
		costEquipment1.setType(materialCost);
		costEquipment1.setCost(10);
		parent.add(costEquipment1);
		
		CostEquipment costEquipment2 = new CostEquipment(concept);
		costEquipment2.setType(testCosts);
		costEquipment2.setCost(5);
		parent.add(costEquipment2);
		//CHECKSTYLE:ON
		SummaryTypes summaryTyp = new SummaryTypes();
		
		Map<CostType, Double> summary = summaryTyp.summaryTyp(costSummary);
		//CHECKSTYLE:OFF
		assertEquals(2, summary.size());
		assertEquals(10, summary.get(materialCost), 0.0001);
		assertEquals(5, summary.get(testCosts), 0.0001);
		//CHECKSTYLE:ON
	}	@Test
	public void testSameTypesWithMargin() {
		// Build a simple test model
		// ...
		StructuralElementInstance parentSei = structInstantiator.generateInstance(se, "parent");
		BeanStructuralElementInstance parent = new BeanStructuralElementInstance(parentSei);
		
		CostSummary costSummary = new CostSummary(concept);
		parent.add(costSummary);
		
		CostType materialCost = new CostType(concept);
		
		//CHECKSTYLE:OFF
		CostEquipment costEquipment1 = new CostEquipment(concept);
		costEquipment1.setType(materialCost);
		costEquipment1.setCost(10);
		costEquipment1.setMargin(10);
		parent.add(costEquipment1);
		
		CostEquipment costEquipment2 = new CostEquipment(concept);
		costEquipment2.setType(materialCost);
		costEquipment2.setCost(5);
		costEquipment2.setMargin(20);
		parent.add(costEquipment2);
		//CHECKSTYLE:ON
		
		SummaryTypes summaryTyp = new SummaryTypes();
		
		Map<CostType, Double> summary = summaryTyp.summaryTyp(costSummary);
		//CHECKSTYLE:OFF
		assertEquals(1, summary.size());
		double totalValue = summary.get(materialCost);
		double margin = costEquipment1.getCostMargin();
		double costWithMargin = totalValue + margin;
		
		assertEquals(15, totalValue, 0.0001);
		assertEquals(17, costWithMargin, 0.0001);
		assertEquals(2, margin, 0.0001);
		//CHECKSTYLE:ON
	}
	
	@Test
	public void testDifferentTypesWithMargin() {
		// Build a simple test model
		// ...
		StructuralElementInstance parentSei = structInstantiator.generateInstance(se, "parent");
		BeanStructuralElementInstance parent = new BeanStructuralElementInstance(parentSei);
		
		CostSummary costSummary = new CostSummary(concept);
		parent.add(costSummary);
		
		CostType materialCost = new CostType(concept);
		CostType testCosts = new CostType(concept);
		//CHECKSTYLE:OFF
		CostEquipment costEquipment1 = new CostEquipment(concept);
		costEquipment1.setType(materialCost);
		costEquipment1.setCost(10);
		costEquipment1.setCostMargin(10);
		parent.add(costEquipment1);
		
		CostEquipment costEquipment2 = new CostEquipment(concept);
		costEquipment2.setType(testCosts);
		costEquipment2.setCost(5);
		parent.add(costEquipment2);
		//CHECKSTYLE:ON
		SummaryTypes summaryTyp = new SummaryTypes();
		
		Map<CostType, Double> summary = summaryTyp.summaryTyp(costSummary);
		//CHECKSTYLE:OFF
		assertEquals(2, summary.size());
		assertEquals(10, summary.get(materialCost), 0.0001);
		assertEquals(5, summary.get(testCosts), 0.0001);
		//CHECKSTYLE:ON
	}
}
