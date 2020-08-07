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

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

public class TypeInstanceAdapterTest {

	private TypeInstanceAdapter adapter;
	private ValuePropertyInstance vpi;
	private static final VirSatUuid UUID = new VirSatUuid();
	
	@Before
	public void setUp() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		
		vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		vpi.setUuid(UUID);
		resourceImpl.getContents().add(vpi);
		
		adapter = new TypeInstanceAdapter(resourceSet);
	}

	@Test
	public void testMarshalATypeInstance() throws Exception {
		assertThrows("The type instance should not be null",
			NullPointerException.class, () -> {
				adapter.marshal(null);
			}
		);
		
		String uuid = adapter.marshal(vpi);
		assertEquals("The right uuid was returned", UUID.toString(), uuid);
		
		vpi.setUuid(null);
		assertThrows("The type instance should have a uuid",
			NullPointerException.class, () -> {
				adapter.marshal(vpi);
			}
		);
	}

	@Test
	public void testUnmarshalString() throws Exception {
		TypeInstanceAdapter adapterNoRs = new TypeInstanceAdapter();
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
		
		ATypeInstance unmarshalledTi = adapter.unmarshal(UUID.toString());
		assertEquals("The right vpi was returned", vpi, (ValuePropertyInstance) unmarshalledTi);
	}

}
