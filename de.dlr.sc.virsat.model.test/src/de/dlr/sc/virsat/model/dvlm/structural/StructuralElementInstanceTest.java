/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural;


import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;

/**
 * This class tests some additional functionality which has been added
 * to the StructuralElementInstance
 * @author fisc_ph
 *
 */
public class StructuralElementInstanceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDeepSubStructuralElementInstances() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setIsApplicableForAll(true);
		
		StructuralElementInstance seiRoot = new StructuralInstantiator().generateInstance(se, "SeiRoot");
		StructuralElementInstance seiLevel11 = new StructuralInstantiator().generateInstance(se, "SeiLevelOne");
		StructuralElementInstance seiLevel12 = new StructuralInstantiator().generateInstance(se, "SeiLevelOne");
		StructuralElementInstance seiLevel21 = new StructuralInstantiator().generateInstance(se, "SeiLevel2");
		StructuralElementInstance seiLevel22 = new StructuralInstantiator().generateInstance(se, "SeiLevel2");

		seiRoot.getChildren().add(seiLevel11);
		seiRoot.getChildren().add(seiLevel12);
		seiLevel11.getChildren().add(seiLevel21);
		seiLevel11.getChildren().add(seiLevel22);
		
		List<StructuralElementInstance> deepListLevel11 = seiLevel11.getDeepChildren();
		List<StructuralElementInstance> deepListLevel12 = seiLevel12.getDeepChildren();
		List<StructuralElementInstance> deepListRoot = seiRoot.getDeepChildren();

		assertTrue("List is empty and ahs no children", deepListLevel12.isEmpty());
		
		assertThat("Child Objects are present in List", deepListLevel11, hasItems(seiLevel21, seiLevel22));
		assertThat("Root and above are not present in List", deepListLevel11, not(hasItem(seiRoot)));
		assertThat("Root and above are not present in List", deepListLevel11, not(hasItem(seiLevel11)));
		assertThat("Root and above are not present in List", deepListLevel11, not(hasItem(seiLevel12)));

		assertThat("Child Objects are present in List", deepListRoot, hasItems(seiLevel11, seiLevel12, seiLevel21, seiLevel22));
		assertThat("Root and above are not present in List", deepListRoot, not(hasItem(seiRoot)));
	}

	@Test
	public void testGetContainedIAssignedDisciplines() {
		//Copy-paste of the testGetDeepSubStructuralElementInstances()
		//Because contained IAssignedDisciplines are all SubStructuralElementInstances
		
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setIsApplicableForAll(true);
		
		StructuralElementInstance seiRoot = new StructuralInstantiator().generateInstance(se, "SeiRoot");
		StructuralElementInstance seiLevel11 = new StructuralInstantiator().generateInstance(se, "SeiLevelOne");
		StructuralElementInstance seiLevel12 = new StructuralInstantiator().generateInstance(se, "SeiLevelOne");
		StructuralElementInstance seiLevel21 = new StructuralInstantiator().generateInstance(se, "SeiLevel2");
		StructuralElementInstance seiLevel22 = new StructuralInstantiator().generateInstance(se, "SeiLevel2");

		seiRoot.getChildren().add(seiLevel11);
		seiRoot.getChildren().add(seiLevel12);
		seiLevel11.getChildren().add(seiLevel21);
		seiLevel11.getChildren().add(seiLevel22);
		
		List<IAssignedDiscipline> deepListLevel11 = seiLevel11.getContainedIAssignedDisciplines();
		List<IAssignedDiscipline> deepListLevel12 = seiLevel12.getContainedIAssignedDisciplines();
		List<IAssignedDiscipline> deepListRoot = seiRoot.getContainedIAssignedDisciplines();

		assertTrue("List is empty and has no children", deepListLevel12.isEmpty());
		
		assertThat("Child Objects are present in List", deepListLevel11, hasItems(seiLevel21, seiLevel22));
		assertThat("Root and above are not present in List", deepListLevel11, not(hasItem(seiRoot)));
		assertThat("Root and above are not present in List", deepListLevel11, not(hasItem(seiLevel11)));
		assertThat("Root and above are not present in List", deepListLevel11, not(hasItem(seiLevel12)));

		assertThat("Child Objects are present in List", deepListRoot, hasItems(seiLevel11, seiLevel12, seiLevel21, seiLevel22));
		assertThat("Root and above are not present in List", deepListRoot, not(hasItem(seiRoot)));
	}

}
