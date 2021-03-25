/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.cost.test;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptTestCase;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;
import de.dlr.sc.virsat.model.extension.budget.cost.activator.Activator;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostEquipment;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostType;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostTypesCollection;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTree;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTreeDomain;

/**
 * Abstract class for import tests
 */
public class ExcelTestCase extends AConceptTestCase {
	protected static final String CONCEPT_ID_EGSCC = de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId();
	protected static final String CONCEPT_ID_COST = Activator.getPluginId();
	protected static final int NUMBER_OF_COST_TYPES = 3;

	protected Repository repository;
	protected CostTypesCollection costTypesCollection;
	protected ElementDefinition elementDef;
	protected ProductTree productTree;
	protected ProductTreeDomain productTreeDomain;
	protected ElementConfiguration elementConf;
	protected ConfigurationTree configTree;
	protected QudvUnitHelper qudvUnitHelper;

	protected Concept conceptEgscc;
	protected Concept conceptCost;

	@Before
	public void setUp() throws CoreException {
		UserRegistry.getInstance().setSuperUser(true);

		conceptEgscc = loadConceptFromPlugin(CONCEPT_ID_EGSCC);
		conceptCost = loadConceptFromPlugin(CONCEPT_ID_COST);

		repository = DVLMFactory.eINSTANCE.createRepository();
		repository.getActiveConcepts().add(conceptEgscc);
		
		UnitManagement unitManagement = UnitsFactory.eINSTANCE.createUnitManagement();
		
		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		
		unitManagement.setSystemOfUnit(sou);
		repository.setUnitManagement(unitManagement);
		repository.getActiveConcepts().add(conceptCost);

		ActiveConceptHelper.getCategory(conceptCost, CostEquipment.class.getSimpleName()).setIsApplicableForAll(true);
		
		// Interface Type Collection to be changed
		costTypesCollection = new CostTypesCollection(conceptCost);
		costTypesCollection.setName("CostTypesCollection");
		costTypesCollection.getStructuralElementInstance().setUuid(new VirSatUuid("505748e0-5d88-47b3-bce8-f53acacb7ccb"));
		CostType costType1 = new CostType(conceptCost);
		costType1.getTypeInstance().setUuid(new VirSatUuid("ea816464-cea3-4db7-ae91-31d37c60a63c"));
		costType1.setName("KILL");
		CostType costType2 = new CostType(conceptCost);
		costType2.getTypeInstance().setUuid(new VirSatUuid("1cd42892-eb5f-42ac-881d-e8ef4825254a"));
		costType2.setName("MILL");
		CostType costType3 = new CostType(conceptCost);
		costType3.getTypeInstance().setUuid(new VirSatUuid("88544856-794e-49fd-a873-266a4008a3fc"));
		costType3.setName("HILL");
		costTypesCollection.add(costType1);
		costTypesCollection.add(costType2);
		costTypesCollection.add(costType3);

		// add product tree, poductTreeDomain and elementDef

		productTree = new ProductTree(conceptEgscc);
		productTreeDomain = new ProductTreeDomain(conceptEgscc);

		productTree.add(productTreeDomain);

		// Element Definition to be changed
		elementDef = new ElementDefinition(conceptEgscc);
		elementDef.setName("BATTERY");
		elementDef.getStructuralElementInstance().setUuid(new VirSatUuid("74ccc93a-281b-4ab8-acec-b7f2b9827d4b"));

		productTreeDomain.add(elementDef);

		CostEquipment costEquipment1 = new CostEquipment(conceptCost);
		costEquipment1.setName("POW_MAU");
		costEquipment1.getTypeInstance().setUuid(new VirSatUuid("a2643ddb-e6a1-4015-9b80-be931f0c5a37"));
		costEquipment1.setType(costType1);
		CostEquipment costEquipment2 = new CostEquipment(conceptCost);
		costEquipment2.setName("POW_WAU");
		costEquipment2.getTypeInstance().setUuid(new VirSatUuid("9f8b18e1-f6a1-4cf6-bcb7-0d4a5f0a86de"));
		costEquipment2.setType(costType2);
		CostEquipment costEquipment3 = new CostEquipment(conceptCost);
		costEquipment3.setName("POW_LOW");
		costEquipment3.getTypeInstance().setUuid(new VirSatUuid("e13ad850-21d8-43d7-aa61-20a3f8d1749b"));
		costEquipment3.setType(costType3);

		elementDef.add(costEquipment1);
		elementDef.add(costEquipment2);
		elementDef.add(costEquipment3);
		


		// will be used for test 3

		configTree = new ConfigurationTree(conceptEgscc);
		configTree.setName("ConfigurationTree1");

		CostEquipment costEquipment4 = new CostEquipment(conceptCost);
		costEquipment4.setName("POW_IN");
		costEquipment4.getTypeInstance().setUuid(new VirSatUuid("a2643ddb-e6a1-4015-9b80-be931f0c5a37"));
		costEquipment4.setType(costType1);
		CostEquipment costEquipment5 = new CostEquipment(conceptCost);
		costEquipment5.setName("POW_SOMETHING");
		costEquipment5.getTypeInstance().setUuid(new VirSatUuid("9f8b18e1-f6a1-4cf6-bcb7-0d4a5f0a86de"));
		costEquipment5.setType(costType2);
		CostEquipment costEquipment6 = new CostEquipment(conceptCost);
		costEquipment6.setName("POW_OUT");
		costEquipment6.getTypeInstance().setUuid(new VirSatUuid("e13ad850-21d8-43d7-aa61-20a3f8d1749b"));
		costEquipment6.setType(costType3);

		// to hold cost equipment
		elementConf = new ElementConfiguration(conceptEgscc);
		elementConf.setName("CostEquipment");
		elementConf.add(costEquipment4);
		elementConf.add(costEquipment5);
		elementConf.add(costEquipment6);

		configTree.add(elementConf);
	}

	/**
	 * adds the all 3 elements to the repository when needed
	 */
	protected void addElementsToRepository() {
		repository.getRootEntities().add(costTypesCollection.getStructuralElementInstance());
		repository.getRootEntities().add(productTree.getStructuralElementInstance());
		repository.getRootEntities().add(configTree.getStructuralElementInstance());
	}
}
