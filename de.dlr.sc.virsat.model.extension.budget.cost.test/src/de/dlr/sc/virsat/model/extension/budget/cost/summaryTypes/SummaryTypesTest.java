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
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostTableEntry;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostType;

public class SummaryTypesTest {

	protected Concept concept;
	protected StructuralInstantiator structInstantiator;
	protected StructuralElement se;
	
	private static final int COSTVALUE_ELEVEN = 11;
	private static final int COSTVALUE_TEN = 10;
	private static final int COSTVALUE_FIVE = 5;
	private static final double COSTVALUE_FIVE_POINT_FIVE = 5.5d;
	private static final double COSTVALUE_FIVE_POINT_ZERO = 5.0d;
	private static final double ROUNDING_VALUE = 0.0001;
	
	
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
		Map<CostType, CostTableEntry> summary = summaryTyp.createSummaryMap(costSummary);
		
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
		
		CostEquipment costEquipment1 = new CostEquipment(concept);
		costEquipment1.setType(materialCost);
		costEquipment1.setCost(COSTVALUE_TEN);
		parent.add(costEquipment1);
		
		CostEquipment costEquipment2 = new CostEquipment(concept);
		costEquipment2.setType(materialCost);
		costEquipment2.setCost(COSTVALUE_FIVE);
		parent.add(costEquipment2);
		
		SummaryTypes summaryTyp = new SummaryTypes();
		
		Map<CostType, CostTableEntry> summary = summaryTyp.createSummaryMap(costSummary);

		assertEquals(1, summary.size());
		CostTableEntry entry = summary.get(materialCost);
		assertEquals(COSTVALUE_TEN + COSTVALUE_FIVE, entry.getCost(), ROUNDING_VALUE);
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

		CostEquipment costEquipment1 = new CostEquipment(concept);
		costEquipment1.setType(materialCost);
		costEquipment1.setCost(COSTVALUE_TEN);
		parent.add(costEquipment1);
		
		CostEquipment costEquipment2 = new CostEquipment(concept);
		costEquipment2.setType(testCosts);
		costEquipment2.setCost(COSTVALUE_FIVE);
		parent.add(costEquipment2);

		SummaryTypes summaryTyp = new SummaryTypes();
		
		Map<CostType, CostTableEntry> summary = summaryTyp.createSummaryMap(costSummary);
		
		assertEquals(2, summary.size());
		CostTableEntry totalMaterial = summary.get(materialCost);
		CostTableEntry totalTest = summary.get(testCosts);
		assertEquals(COSTVALUE_TEN, totalMaterial.getCost(), ROUNDING_VALUE);
		assertEquals(COSTVALUE_FIVE, totalTest.getCost(), ROUNDING_VALUE);

	}	@Test
	public void testSameTypesWithMargin() {
		// Build a simple test model
		// ...
		StructuralElementInstance parentSei = structInstantiator.generateInstance(se, "parent");
		BeanStructuralElementInstance parent = new BeanStructuralElementInstance(parentSei);
		
		CostSummary costSummary = new CostSummary(concept);
		parent.add(costSummary);
		
		CostType materialCost = new CostType(concept);

		CostEquipment costEquipment1 = new CostEquipment(concept);
		costEquipment1.setType(materialCost);
		costEquipment1.setCost(COSTVALUE_FIVE);
		costEquipment1.setCostWithMargin(COSTVALUE_FIVE_POINT_FIVE);
		costEquipment1.setCostMargin(COSTVALUE_FIVE_POINT_ZERO);
		costEquipment1.setMargin(COSTVALUE_TEN);
		parent.add(costEquipment1);
		
		CostEquipment costEquipment2 = new CostEquipment(concept);
		costEquipment2.setType(materialCost);
		costEquipment2.setCost(COSTVALUE_FIVE);
		costEquipment2.setCostWithMargin(COSTVALUE_FIVE_POINT_FIVE);
		costEquipment2.setCostMargin(COSTVALUE_FIVE_POINT_ZERO);
		costEquipment2.setMargin(COSTVALUE_TEN);
		parent.add(costEquipment2);

		SummaryTypes summaryTyp = new SummaryTypes();
		
		Map<CostType, CostTableEntry> summary = summaryTyp.createSummaryMap(costSummary);

		assertEquals(1, summary.size());
		CostTableEntry cost = summary.get(materialCost);
		CostTableEntry costWithMargin = summary.get(materialCost);
		CostTableEntry costMargin = summary.get(materialCost);
		CostTableEntry margin = summary.get(materialCost);
		
		assertEquals(COSTVALUE_TEN, cost.getCost(), ROUNDING_VALUE);
		assertEquals(COSTVALUE_ELEVEN, costWithMargin.getCostWithMargin(), ROUNDING_VALUE);
		assertEquals(1, costMargin.getCostMargin(), ROUNDING_VALUE);
		assertEquals(COSTVALUE_TEN, margin.getMargin(), ROUNDING_VALUE);
	}
}
