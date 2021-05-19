/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.roles;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;

public class BeanDisciplineTest {
	private Discipline testDiscipline;
	private EditingDomain ed;
	
	private BeanDiscipline beanDiscipline;
	private static final String NAME = "name";
	private static final String NAME2 = "name2";
	private static final String USER = "user";
	
	@Before
	public void setUp() {
		testDiscipline = RolesFactory.eINSTANCE.createDiscipline();
		beanDiscipline = new BeanDiscipline();
		
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		ed = new AdapterFactoryEditingDomain(adapterFactory, new BasicCommandStack());
	}
	
	@Test
	public void testGetAndSet() {
		assertEquals(null, beanDiscipline.getDiscipline());
		beanDiscipline.setDiscipline(testDiscipline);
		assertEquals(testDiscipline, beanDiscipline.getDiscipline());
		assertEquals(testDiscipline.getUuid().toString(), beanDiscipline.getUuid());
		
		assertEquals("", beanDiscipline.getUser());
		beanDiscipline.setUser(USER);
		assertEquals(USER, testDiscipline.getUser());
		
		assertEquals(null, beanDiscipline.getName());
		beanDiscipline.setName(NAME);
		assertEquals(NAME, testDiscipline.getName());
		
		Command command = beanDiscipline.setName(ed, NAME2);
		ed.getCommandStack().execute(command);
		assertEquals(NAME2, testDiscipline.getName());
	}
}
