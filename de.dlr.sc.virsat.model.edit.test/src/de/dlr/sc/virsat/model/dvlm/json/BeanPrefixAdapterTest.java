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

import de.dlr.sc.virsat.model.concept.types.qudv.BeanPrefix;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;

public class BeanPrefixAdapterTest extends AUuidAdapterTest {

	private Prefix prefix;
	private BeanPrefix bean;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		prefix = QudvFactory.eINSTANCE.createPrefix();
		prefix.setUuid(UUID);
		resourceImpl.getContents().add(prefix);
		
		bean = new BeanPrefix(prefix);
		
		adapter = new BeanPrefixAdapter(resourceSet);
	}

	@Override
	public void testMarshallNull() throws Exception {
		assertNull(adapter.marshal(null));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testMarhall() throws Exception {
		
		String uuid = ((XmlAdapter<String, BeanPrefix>) adapter).marshal(bean);
		assertEquals("The right uuid was returned", UUID.toString(), uuid);
		
		prefix.setUuid(null);
		assertThrows("The type instance should have a uuid",
			NullPointerException.class, () -> {
				((XmlAdapter<String, BeanPrefix>) adapter).marshal(bean);
			}
		);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testUnmarhall() throws Exception {
		BeanPrefix unmarshalledBean = ((XmlAdapter<String, BeanPrefix>) adapter).unmarshal(UUID.toString());
		assertEquals("The right bean was returned", bean, (BeanPrefix) unmarshalledBean);		
	}
	
	@Override
	public void testUnmarshallNull() {
		adapterWithoutResourceSet = new BeanPrefixAdapter();
		super.testUnmarshallNull();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testUnmarshallEmptyRs() {
		adapterWithEmptyResourceSet = new BeanPrefixAdapter(new ResourceSetImpl());
		assertThrows("No mapping found",
			IllegalArgumentException.class, () -> {
				((XmlAdapter<String, BeanPrefix>) adapterWithEmptyResourceSet).unmarshal(UUID.toString());
			}
		);
	}

}
