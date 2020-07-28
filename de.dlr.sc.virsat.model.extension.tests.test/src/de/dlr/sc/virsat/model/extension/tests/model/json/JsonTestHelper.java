/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.model.json;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesFactoryImpl;
import de.dlr.sc.virsat.model.dvlm.categories.provider.ATypeDefinitionItemProvider;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;

public class JsonTestHelper {
	
	public static final String TEST_STRING = "testString";
	
	private JsonTestHelper() { }
	
	public static void setTestCategoryAllPropertyUuids(TestCategoryAllProperty tcAllProperty, Concept concept) {
		setTestCategoryAllPropertyUuids(tcAllProperty, concept, 0);
	}
	
	public static void setTestCategoryAllPropertyUuids(TestCategoryAllProperty tcAllProperty, Concept concept, int count) {
		tcAllProperty.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f" + count));
		tcAllProperty.getTestBoolBean().getATypeInstance().setUuid(new VirSatUuid("b9bfb08f-2778-4fe9-a774-3d8b0ad638d" + count));
		tcAllProperty.getTestEnumBean().getATypeInstance().setUuid(new VirSatUuid("ed62d73c-dbba-409c-b73c-f0d3d9f4939" + count));
		tcAllProperty.getTestFloatBean().getATypeInstance().setUuid(new VirSatUuid("2870876e-4d6c-4128-801d-54fa109f382" + count));
		tcAllProperty.getTestIntBean().getATypeInstance().setUuid(new VirSatUuid("0f37aff6-ccc0-436f-a592-bd466f74bd8" + count));
		tcAllProperty.getTestResourceBean().getATypeInstance().setUuid(new VirSatUuid("fa822159-51a5-4bf2-99cf-e565b67e0eb" + count));
		tcAllProperty.getTestStringBean().getATypeInstance().setUuid(new VirSatUuid("26edbae8-9f05-4ef5-8673-91e1af668aa" + count));
	}
	
	public static Repository createRepositoryWithUnitManagement(Concept concept) {
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		UnitManagement unitManagement = UnitsFactory.eINSTANCE.createUnitManagement();
		
		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		
		unitManagement.setSystemOfUnit(sou);
		repo.setUnitManagement(unitManagement);
		repo.getActiveConcepts().add(concept);
		
		return repo;
	}
	
	public static BeanPropertyString createTestStringBean(Concept concept) {
		ValuePropertyInstance vpi = PropertyinstancesFactoryImpl.eINSTANCE.createValuePropertyInstance();
		vpi.setUuid(new VirSatUuid("7256e7a2-9a1f-443c-85f8-7b766eac3f50"));
		// TODO: set type???
		BeanPropertyString bpString = new BeanPropertyString(vpi);
		return bpString;
	}
	
	public static void setStaticIBeanListUuids(IBeanList<? extends IBeanObject<? extends ATypeInstance>> list) {
		list.get(0).getATypeInstance().setUuid(new VirSatUuid("4efe0002-f081-49c0-9917-6f4a6e7dd9ce"));
		list.get(1).getATypeInstance().setUuid(new VirSatUuid("6ad3d35a-a0b4-48e8-9bfd-e6edf438eee5"));
		list.get(2).getATypeInstance().setUuid(new VirSatUuid("8fd96e3b-5bf3-41e1-a02a-64f8bff99107"));
		list.get(3).getATypeInstance().setUuid(new VirSatUuid("c38d7185-fcc3-480c-bfb4-28e6fcc09d34"));
		list.getArrayInstance().setUuid(new VirSatUuid("98218bbf-a5ee-432d-b01c-da48f4f9495b"));
	}
}
