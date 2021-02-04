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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;

import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

public class ABeanStructuralElementInstanceAdapterTest extends AUuidAdapterTest {

	private StructuralElementInstance sei;
	private BeanStructuralElementInstance bean;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setUuid(UUID);
		resourceImpl.getContents().add(sei);
		
		bean = new BeanStructuralElementInstance(sei);
		
		adapter = new ABeanStructuralElementInstanceAdapter(resourceSet);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testMarshallNull() throws Exception {
		String uuid = ((XmlAdapter<String, ABeanStructuralElementInstance>) adapter).marshal(null);
		assertNull("No bean returns null", uuid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testMarhall() throws Exception {
		String uuid = ((XmlAdapter<String, ABeanStructuralElementInstance>) adapter).marshal(bean);
		assertEquals("The right uuid was returned", UUID.toString(), uuid);
		
		sei.setUuid(null);
		assertThrows("The sei should have a uuid",
			NullPointerException.class, () -> {
				((XmlAdapter<String, ABeanStructuralElementInstance>) adapter).marshal(bean);
			}
		);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testUnmarhall() throws Exception {
		ABeanStructuralElementInstance unmarshalledBean = ((XmlAdapter<String, ABeanStructuralElementInstance>) adapter).unmarshal(UUID.toString());
		assertEquals("The right bean was returned", bean, (BeanStructuralElementInstance) unmarshalledBean);
	}
	
	@Override
	public void testUnmarshallNull() {
		adapterWithoutResourceSet = new ABeanStructuralElementInstanceAdapter();
		super.testUnmarshallNull();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testUnmarshallEmptyRs() {
		adapterWithEmptyResourceSet = new ABeanStructuralElementInstanceAdapter(new ResourceSetImpl());
		assertThrows("No mapping found",
			IllegalArgumentException.class, () -> {
				((XmlAdapter<String, ABeanStructuralElementInstance>) adapterWithEmptyResourceSet).unmarshal(UUID.toString());
			}
		);
	}
}
