/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.test;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptTestCase;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceTypeCollection;
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
	protected static final String CONCEPT_ID_FUNCELECTRICAL = Activator.getPluginId();
	protected static final int NUMBER_OF_INTERFACES = 3;

	protected Repository repository;
	protected InterfaceTypeCollection ifaceTypeCollection;
	protected ElementDefinition elementDef;
	protected ProductTree productTree;
	protected ProductTreeDomain productTreeDomain;
	protected ElementConfiguration elementConf;
	protected ElementConfiguration elementConf2;
	protected ConfigurationTree configTree;

	protected Interface iface;
	protected Interface iface2;

	protected Concept conceptEgscc;
	protected Concept conceptFuncelectrical;

	@Before
	public void setUp() throws CoreException {
		UserRegistry.getInstance().setSuperUser(true);

		conceptEgscc = loadConceptFromPlugin(CONCEPT_ID_EGSCC);
		conceptFuncelectrical = loadConceptFromPlugin(CONCEPT_ID_FUNCELECTRICAL);

		repository = DVLMFactory.eINSTANCE.createRepository();
		repository.getActiveConcepts().add(conceptEgscc);
		repository.getActiveConcepts().add(conceptFuncelectrical);

		ActiveConceptHelper.getCategory(conceptFuncelectrical, InterfaceEnd.class.getSimpleName()).setIsApplicableForAll(true);
		ActiveConceptHelper.getCategory(conceptFuncelectrical, Interface.class.getSimpleName()).setIsApplicableForAll(true);

		// Interface Type Collection to be changed
		ifaceTypeCollection = new InterfaceTypeCollection(conceptFuncelectrical);
		ifaceTypeCollection.setName("InterfaceTypeCollection");
		ifaceTypeCollection.getStructuralElementInstance().setUuid(new VirSatUuid("505748e0-5d88-47b3-bce8-f53acacb7ccb"));
		InterfaceType ifaceType1 = new InterfaceType(conceptFuncelectrical);
		ifaceType1.getTypeInstance().setUuid(new VirSatUuid("ea816464-cea3-4db7-ae91-31d37c60a63c"));
		ifaceType1.setName("KILL");
		InterfaceType ifaceType2 = new InterfaceType(conceptFuncelectrical);
		ifaceType2.getTypeInstance().setUuid(new VirSatUuid("1cd42892-eb5f-42ac-881d-e8ef4825254a"));
		ifaceType2.setName("MILL");
		InterfaceType ifaceType3 = new InterfaceType(conceptFuncelectrical);
		ifaceType3.getTypeInstance().setUuid(new VirSatUuid("88544856-794e-49fd-a873-266a4008a3fc"));
		ifaceType3.setName("HILL");
		ifaceTypeCollection.add(ifaceType1);
		ifaceTypeCollection.add(ifaceType2);
		ifaceTypeCollection.add(ifaceType3);

		// add product tree, poductTreeDomain and elementDef

		productTree = new ProductTree(conceptEgscc);
		productTreeDomain = new ProductTreeDomain(conceptEgscc);

		productTree.add(productTreeDomain);

		// Element Definition to be changed
		elementDef = new ElementDefinition(conceptEgscc);
		elementDef.setName("BATTERY");
		elementDef.getStructuralElementInstance().setUuid(new VirSatUuid("74ccc93a-281b-4ab8-acec-b7f2b9827d4b"));

		productTreeDomain.add(elementDef);

		InterfaceEnd ifaceEnd1 = new InterfaceEnd(conceptFuncelectrical);
		ifaceEnd1.setName("POW_IN");
		ifaceEnd1.getTypeInstance().setUuid(new VirSatUuid("a2643ddb-e6a1-4015-9b80-be931f0c5a37"));
		ifaceEnd1.setType(ifaceType1);
		InterfaceEnd ifaceEnd2 = new InterfaceEnd(conceptFuncelectrical);
		ifaceEnd2.setName("POW_SOMETHING");
		ifaceEnd2.getTypeInstance().setUuid(new VirSatUuid("9f8b18e1-f6a1-4cf6-bcb7-0d4a5f0a86de"));
		ifaceEnd2.setType(ifaceType2);
		InterfaceEnd ifaceEnd3 = new InterfaceEnd(conceptFuncelectrical);
		ifaceEnd3.setName("POW_OUT");
		ifaceEnd3.getTypeInstance().setUuid(new VirSatUuid("e13ad850-21d8-43d7-aa61-20a3f8d1749b"));
		ifaceEnd3.setType(ifaceType3);

		elementDef.add(ifaceEnd1);
		elementDef.add(ifaceEnd2);
		elementDef.add(ifaceEnd3);

		// will be used for test 3

		configTree = new ConfigurationTree(conceptEgscc);
		configTree.setName("ConfigurationTree1");

		InterfaceEnd ifaceEnd4 = new InterfaceEnd(conceptFuncelectrical);
		ifaceEnd4.setName("POW_IN");
		ifaceEnd4.getTypeInstance().setUuid(new VirSatUuid("a2643ddb-e6a1-4015-9b80-be931f0c5a37"));
		ifaceEnd4.setType(ifaceType1);
		InterfaceEnd ifaceEnd5 = new InterfaceEnd(conceptFuncelectrical);
		ifaceEnd5.setName("POW_SOMETHING");
		ifaceEnd5.getTypeInstance().setUuid(new VirSatUuid("9f8b18e1-f6a1-4cf6-bcb7-0d4a5f0a86de"));
		ifaceEnd5.setType(ifaceType2);
		InterfaceEnd ifaceEnd6 = new InterfaceEnd(conceptFuncelectrical);
		ifaceEnd6.setName("POW_OUT");
		ifaceEnd6.getTypeInstance().setUuid(new VirSatUuid("e13ad850-21d8-43d7-aa61-20a3f8d1749b"));
		ifaceEnd6.setType(ifaceType3);

		iface = new Interface(conceptFuncelectrical);
		iface.setInterfaceEndFrom(ifaceEnd4);
		iface.setInterfaceEndTo(ifaceEnd6);

		iface2 = new Interface(conceptFuncelectrical);
		iface2.getTypeInstance().setUuid(new VirSatUuid("e9e077d0-2320-4229-954a-42297b37004b"));
		iface2.setInterfaceEndFrom(ifaceEnd5);
		iface2.setInterfaceEndTo(ifaceEnd6);

		// to hold interfaces
		elementConf = new ElementConfiguration(conceptEgscc);
		elementConf.setName("Interfaces");
		elementConf.getStructuralElementInstance().setUuid(new VirSatUuid("2c325de4-f52f-467c-8867-bdddda7723f6"));
		elementConf.add(iface);

		// to hold interface ends
		elementConf2 = new ElementConfiguration(conceptEgscc);
		elementConf2.setName("InterfaceEnds");
		elementConf2.add(ifaceEnd4);
		elementConf2.add(ifaceEnd5);
		elementConf2.add(ifaceEnd6);

		configTree.add(elementConf);
		configTree.add(elementConf2);
	}

	/**
	 * adds the all 3 elements to the repository when needed
	 */
	protected void addElementsToRepository() {
		repository.getRootEntities().add(ifaceTypeCollection.getStructuralElementInstance());
		repository.getRootEntities().add(productTree.getStructuralElementInstance());
		repository.getRootEntities().add(configTree.getStructuralElementInstance());
	}
}
