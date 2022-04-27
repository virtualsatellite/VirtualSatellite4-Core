/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

public class IUuidAdapterTest extends AUuidAdapterTest {

	private StructuralElementInstance sei;
	private IUserContext userContext;
	private Discipline discipline;
	private static final String USER = "user";

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setUuid(UUID);
		resourceImpl.getContents().add(sei);
		
		adapter = new IUuidAdapter(resourceSet);
		
		userContext = new IUserContext() {
			@Override
			public boolean isSuperUser() {
				return false;
			}

			@Override
			public String getUserName() {
				return USER;
			}
		};
		
		discipline = RolesFactory.eINSTANCE.createDiscipline();
		discipline.setUser(USER);
		
		sei.setAssignedDiscipline(discipline);
	}

	@Override
	public void testMarshallNull() throws Exception {
		assertThrows("The IUuid should not be null",
			NullPointerException.class, () -> {
				adapter.marshal(null);
			}
		);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testMarhall() throws Exception {
		
		String uuid = ((XmlAdapter<String, IUuid>) adapter).marshal(sei);
		assertEquals("The right uuid was returned", UUID.toString(), uuid);
		
		sei.setUuid(null);
		assertThrows("The type instance should have a uuid",
			NullPointerException.class, () -> {
				((XmlAdapter<String, IUuid>) adapter).marshal(sei);
			}
		);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testUnmarhall() throws Exception {
		IUuid unmarshalledSei = ((XmlAdapter<String, IUuid>) adapter).unmarshal(UUID.toString());
		assertEquals("The right sei was returned", sei, unmarshalledSei);		
	}
	
	@Override
	public void testUnmarshallNull() {
		adapterWithoutResourceSet = new IUuidAdapter();
		super.testUnmarshallNull();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testUnmarshallEmptyRs() {
		adapterWithEmptyResourceSet = new IUuidAdapter(new ResourceSetImpl());
		assertThrows("No mapping found",
			IllegalArgumentException.class, () -> {
				((XmlAdapter<String, IUuid>) adapterWithEmptyResourceSet).unmarshal(UUID.toString());
			}
		);
	}
	
	@Test
	public void testUnmarshallRolemanagement() throws Exception {
		IUuidAdapter adapterNullUserContext = new IUuidAdapter(resourceSet, null);
		IUuid unmarshalledSei = adapterNullUserContext.unmarshal(UUID.toString());
		assertEquals("The right sei was returned", sei, unmarshalledSei);
		
		IUserContext wrongUserContext = new IUserContext() {
			@Override
			public boolean isSuperUser() {
				return false;
			}
			
			@Override
			public String getUserName() {
				return "user2";
			}
		};
		
		IUuidAdapter adapterWrongUserContext = new IUuidAdapter(resourceSet, wrongUserContext);
		assertThrows("UserContext should not be null",
			IllegalArgumentException.class, () -> {
				adapterWrongUserContext.unmarshal(UUID.toString());
			}
		);
		
		IUuidAdapter adapterWithUserContext = new IUuidAdapter(resourceSet, userContext);
		unmarshalledSei = adapterWithUserContext.unmarshal(UUID.toString());
		assertEquals("The right sei was returned", sei, unmarshalledSei);
	}

}
