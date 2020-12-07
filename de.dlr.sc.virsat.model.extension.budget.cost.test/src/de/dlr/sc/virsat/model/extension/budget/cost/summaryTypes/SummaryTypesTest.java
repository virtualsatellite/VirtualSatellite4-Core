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
		
		CostEquipment costEquipment1 = new CostEquipment(concept);
		costEquipment1.setType(materialCost);
		costEquipment1.setCost(10);
		parent.add(costEquipment1);
		
		CostEquipment costEquipment2 = new CostEquipment(concept);
		costEquipment2.setType(materialCost);
		costEquipment2.setCost(5);
		parent.add(costEquipment2);
		
		SummaryTypes summaryTyp = new SummaryTypes();
		
		Map<CostType, Double> summary = summaryTyp.summaryTyp(costSummary);
		
		assertEquals(1, summary.size());
		double totalValue = summary.get(materialCost);
		assertEquals(15, totalValue, 0.0001);
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
		costEquipment1.setCost(10);
		parent.add(costEquipment1);
		
		CostEquipment costEquipment2 = new CostEquipment(concept);
		costEquipment2.setType(testCosts);
		costEquipment2.setCost(5);
		parent.add(costEquipment2);
		
		SummaryTypes summaryTyp = new SummaryTypes();
		
		Map<CostType, Double> summary = summaryTyp.summaryTyp(costSummary);
		
		assertEquals(2, summary.size());
		assertEquals(10, summary.get(materialCost), 0.0001);
		assertEquals(5, summary.get(testCosts), 0.0001);
	}
}
