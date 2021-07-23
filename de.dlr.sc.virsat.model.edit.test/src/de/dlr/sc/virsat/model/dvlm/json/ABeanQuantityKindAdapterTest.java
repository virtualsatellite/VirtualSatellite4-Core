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

import de.dlr.sc.virsat.model.concept.types.qudv.ABeanQuantityKind;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanQuantityKindSimple;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleQuantityKind;

public class ABeanQuantityKindAdapterTest extends AUuidAdapterTest {

	private SimpleQuantityKind qk;
	private BeanQuantityKindSimple bean;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		qk = QudvFactory.eINSTANCE.createSimpleQuantityKind();
		qk.setUuid(UUID);
		resourceImpl.getContents().add(qk);
		
		bean = new BeanQuantityKindSimple(qk);
		
		adapter = new ABeanQuantityKindAdapter(resourceSet);
	}

	@Override
	public void testMarshallNull() throws Exception {
		assertNull(adapter.marshal(null));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void testMarhall() throws Exception {
		
		String uuid = ((XmlAdapter<String, ABeanQuantityKind>) adapter).marshal(bean);
		assertEquals("The right uuid was returned", UUID.toString(), uuid);
		
		qk.setUuid(null);
		assertThrows("The type instance should have a uuid",
			NullPointerException.class, () -> {
				((XmlAdapter<String, ABeanQuantityKind>) adapter).marshal(bean);
			}
		);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void testUnmarhall() throws Exception {
		ABeanQuantityKind unmarshalledBean = ((XmlAdapter<String, ABeanQuantityKind>) adapter).unmarshal(UUID.toString());
		assertEquals("The right bean was returned", bean, (BeanQuantityKindSimple) unmarshalledBean);		
	}
	
	@Override
	public void testUnmarshallNull() {
		adapterWithoutResourceSet = new ABeanQuantityKindAdapter();
		super.testUnmarshallNull();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void testUnmarshallEmptyRs() {
		adapterWithEmptyResourceSet = new ABeanQuantityKindAdapter(new ResourceSetImpl());
		assertThrows("No mapping found",
			IllegalArgumentException.class, () -> {
				((XmlAdapter<String, ABeanQuantityKind>) adapterWithEmptyResourceSet).unmarshal(UUID.toString());
			}
		);
	}

}
