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

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.transaction.RecordingCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.command.CreateAddStructuralElementInstanceCommand;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceTypeCollection;

/**
 * Test Case for Functional Electric Architecture Helper
 * 
 * @author lobe_el
 *
 */

public class FuncElectricalArchitectureHelperTest extends AConceptProjectTestCase {

	private static final String CONCEPT_ID_PS = de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId();
	private static final String CONCEPT_ID_FUNCELECTRICAL = Activator.getPluginId();
	private static final int THREE = 3;

	private Concept conceptPs;
	private Concept conceptFea;

	private InterfaceTypeCollection itc;
	private InterfaceType it1;
	private InterfaceType it2;
	private InterfaceType it3;

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();

		UserRegistry.getInstance().setSuperUser(true);

		conceptPs = loadConceptFromPlugin(CONCEPT_ID_PS);
		conceptFea = loadConceptFromPlugin(CONCEPT_ID_FUNCELECTRICAL);

		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

			@Override
			protected void doExecute() {
				repository.getActiveConcepts().add(conceptPs);
				repository.getActiveConcepts().add(conceptFea);
			}
		});

		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				ActiveConceptHelper.getCategory(conceptFea, InterfaceEnd.class.getSimpleName())
						.setIsApplicableForAll(true);
				ActiveConceptHelper.getCategory(conceptFea, Interface.class.getSimpleName())
						.setIsApplicableForAll(true);
			}
		});
	}

	@After
	public void tearDown() throws CoreException {
		super.tearDown();
		UserRegistry.getInstance().setSuperUser(false);
	}

	@Test
	public void testgetAllInterfaceTypes() throws IOException {
		// Here we start to create the Test Model
		it1 = new InterfaceType(conceptFea);
		it1.setName("IFT_MILBUS");
		it2 = new InterfaceType(conceptFea);
		it2.setName("IFT_CANBUS");
		it3 = new InterfaceType(conceptFea);
		it3.setName("IFT_WHATEVERBUS");

		itc = new InterfaceTypeCollection(conceptFea);
		itc.setName("INTERFACE_TYPE_COLLECTION");
		itc.add(it1);
		itc.add(it2);

		editingDomain.getCommandStack().execute(CreateAddStructuralElementInstanceCommand.create(editingDomain,
				repository, itc.getStructuralElementInstance()));

		FuncElectricalArchitectureHelper feaHelper = new FuncElectricalArchitectureHelper();
		List<InterfaceType> ifts = feaHelper.getAllInterfaceTypes(repository);

		assertEquals("List has correct amount of elements", 2, ifts.size());
		assertThat("List contains correct elements", ifts, hasItems(it1, it2));

		itc.add(it3);
		ifts = feaHelper.getAllInterfaceTypes(repository);

		assertEquals("List has correct amount of elements", THREE, ifts.size());
		assertThat("List contains correct elements", ifts, hasItems(it1, it2, it3));

	}
}
