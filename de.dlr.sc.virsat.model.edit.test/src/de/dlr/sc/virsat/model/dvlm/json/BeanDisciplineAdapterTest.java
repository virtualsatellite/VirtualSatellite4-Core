/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;

import de.dlr.sc.virsat.model.concept.types.roles.BeanDiscipline;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;

public class BeanDisciplineAdapterTest extends AUuidAdapterTest {

	private Discipline discipline;
	private BeanDiscipline bean;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		discipline = RolesFactory.eINSTANCE.createDiscipline();
		discipline.setUuid(UUID);
		resourceImpl.getContents().add(discipline);
		
		bean = new BeanDiscipline(discipline);
		
		adapter = new BeanDisciplineAdapter(resourceSet);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testMarshallNull() throws Exception {
		String uuid = ((XmlAdapter<String, BeanDiscipline>) adapter).marshal(null);
		assertNull("No bean returns null", uuid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testMarhall() throws Exception {
		String uuid = ((XmlAdapter<String, BeanDiscipline>) adapter).marshal(bean);
		assertEquals("The right uuid was returned", UUID.toString(), uuid);
		
		discipline.setUuid(null);
		assertThrows("The type instance should have a uuid",
			NullPointerException.class, () -> {
				((XmlAdapter<String, BeanDiscipline>) adapter).marshal(bean);
			}
		);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testUnmarhall() throws Exception {
		BeanDiscipline unmarshalledBean = ((XmlAdapter<String, BeanDiscipline>) adapter).unmarshal(UUID.toString());
		assertEquals("The right bean was returned", bean, (BeanDiscipline) unmarshalledBean);
	}
	
	@Override
	public void testUnmarshallNull() {
		adapterWithoutResourceSet = new BeanDisciplineAdapter();
		super.testUnmarshallNull();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testUnmarshallEmptyRs() {
		adapterWithEmptyResourceSet = new BeanDisciplineAdapter(new ResourceSetImpl());
		assertThrows("No mapping found",
			IllegalArgumentException.class, () -> {
				((XmlAdapter<String, BeanDisciplineAdapter>) adapterWithEmptyResourceSet).unmarshal(UUID.toString());
			}
		);
	}
	
}
