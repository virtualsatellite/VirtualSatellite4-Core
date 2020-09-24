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

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

public class ABeanObjectAdapterTest {

	private ABeanObjectAdapter adapter;
	private ValuePropertyInstance vpi;
	private BeanPropertyString bean;
	private static final VirSatUuid UUID = new VirSatUuid();
	
	@Before
	public void setUp() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		
		vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		vpi.setUuid(UUID);
		vpi.setType(PropertydefinitionsFactory.eINSTANCE.createStringProperty());
		resourceImpl.getContents().add(vpi);
		
		bean = new BeanPropertyString(vpi);
		
		adapter = new ABeanObjectAdapter(resourceSet);
	}

	@Test
	public void testMarshalABeanObject() throws Exception {
		String uuid = adapter.marshal(null);
		assertNull("No bean returns null", uuid);
		
		uuid = adapter.marshal(bean);
		assertEquals("The right uuid was returned", UUID.toString(), uuid);
		
		vpi.setUuid(null);
		assertThrows("The type instance should have a uuid",
			NullPointerException.class, () -> {
				adapter.marshal(bean);
			}
		);
	}
	
	@Test
	public void testUnmarshalString() throws Exception {
		IUuidAdapter adapterNoRs = new IUuidAdapter();
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
		
		@SuppressWarnings("rawtypes")
		ABeanObject unmarshalledBean = adapter.unmarshal(UUID.toString());
		assertEquals("The right bean was returned", bean, (BeanPropertyString) unmarshalledBean);
	}

}
