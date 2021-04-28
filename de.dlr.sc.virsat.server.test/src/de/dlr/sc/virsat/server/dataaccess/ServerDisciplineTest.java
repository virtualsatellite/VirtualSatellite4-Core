/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.dataaccess;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;

public class ServerDisciplineTest {
	private Discipline testDiscipline;
	
	private ServerDiscipline serverDiscipline;
	private static final String NAME = "name";
	private static final String USER = "user";
	
	@Before
	public void setUp() {
		testDiscipline = RolesFactory.eINSTANCE.createDiscipline();
		serverDiscipline = new ServerDiscipline();
	}
	
	@Test
	public void testGetAndSet() throws JAXBException, IOException {
		assertEquals(null, serverDiscipline.getDiscipline());
		serverDiscipline.setDiscipline(testDiscipline);
		assertEquals(testDiscipline, serverDiscipline.getDiscipline());
		
		assertEquals("", serverDiscipline.getUser());
		serverDiscipline.setUser(USER);
		assertEquals(USER, testDiscipline.getUser());
		
		assertEquals(null, serverDiscipline.getName());
		serverDiscipline.setName(NAME);
		assertEquals(NAME, testDiscipline.getName());
	}

}
