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

import de.dlr.sc.virsat.model.concept.types.qudv.ABeanUnit;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitSimple;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleUnit;

public class ABeanUnitAdapterTest extends AUuidAdapterTest {

	private SimpleUnit unit;
	private BeanUnitSimple bean;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		unit = QudvFactory.eINSTANCE.createSimpleUnit();
		unit.setUuid(UUID);
		resourceImpl.getContents().add(unit);
		
		bean = new BeanUnitSimple(unit);
		
		adapter = new ABeanUnitAdapter(resourceSet);
	}

	@Override
	public void testMarshallNull() throws Exception {
		assertNull(adapter.marshal(null));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void testMarhall() throws Exception {
		
		String uuid = ((XmlAdapter<String, ABeanUnit>) adapter).marshal(bean);
		assertEquals("The right uuid was returned", UUID.toString(), uuid);
		
		unit.setUuid(null);
		assertThrows("The type instance should have a uuid",
			NullPointerException.class, () -> {
				((XmlAdapter<String, ABeanUnit>) adapter).marshal(bean);
			}
		);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void testUnmarhall() throws Exception {
		ABeanUnit unmarshalledBean = ((XmlAdapter<String, ABeanUnit>) adapter).unmarshal(UUID.toString());
		assertEquals("The right bean was returned", bean, (BeanUnitSimple) unmarshalledBean);		
	}
	
	@Override
	public void testUnmarshallNull() {
		adapterWithoutResourceSet = new ABeanUnitAdapter();
		super.testUnmarshallNull();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void testUnmarshallEmptyRs() {
		adapterWithEmptyResourceSet = new ABeanUnitAdapter(new ResourceSetImpl());
		assertThrows("No mapping found",
			IllegalArgumentException.class, () -> {
				((XmlAdapter<String, ABeanUnit>) adapterWithEmptyResourceSet).unmarshal(UUID.toString());
			}
		);
	}

}
