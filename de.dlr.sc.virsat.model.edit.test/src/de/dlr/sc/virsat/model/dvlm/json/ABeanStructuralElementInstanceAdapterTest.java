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

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

//TODO: add abstract test case and fusion with abeanobjectadaptertest and iuuid test?
public class ABeanStructuralElementInstanceAdapterTest {

	private ABeanStructuralElementInstanceAdapter adapter;
	private StructuralElementInstance sei;
	private BeanStructuralElementInstance bean;
	private static final VirSatUuid UUID = new VirSatUuid();
	
	@Before
	public void setUp() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		
		sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setUuid(UUID);
		resourceImpl.getContents().add(sei);
		
		bean = new BeanStructuralElementInstance(sei);
		
		adapter = new ABeanStructuralElementInstanceAdapter(resourceSet);
	}

	@Test
	public void testMarshal() throws Exception {
		String uuid = adapter.marshal(null);
		assertNull("No bean returns null", uuid);
		
		uuid = adapter.marshal(bean);
		assertEquals("The right uuid was returned", UUID.toString(), uuid);
		
		sei.setUuid(null);
		assertThrows("The sei should have a uuid",
			NullPointerException.class, () -> {
				adapter.marshal(bean);
			}
		);
	}
	
	@Test
	public void testUnmarshal() throws Exception {
		ABeanStructuralElementInstanceAdapter adapterNoRs = new ABeanStructuralElementInstanceAdapter();
		assertThrows("A resource set should be set",
			NullPointerException.class, () -> {
				adapterNoRs.unmarshal(null);
			}
		);
		
		assertThrows("No mapping found",
			IllegalArgumentException.class, () -> {
				adapter.unmarshal(null);
			}
		);
		
		ABeanStructuralElementInstance unmarshalledBean = adapter.unmarshal(UUID.toString());
		assertEquals("The right bean was returned", bean, (BeanStructuralElementInstance) unmarshalledBean);
	}
	
}
