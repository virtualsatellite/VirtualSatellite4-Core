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
import static org.junit.Assert.assertThrows;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;

public class IUuidAdapterNoRoleManagementTest extends AUuidAdapterTest {

	private ValuePropertyInstance vpi;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		vpi.setUuid(UUID);
		resourceImpl.getContents().add(vpi);
		
		adapter = new IUuidAdapterNoRoleManagement(resourceSet);
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
		
		String uuid = ((XmlAdapter<String, IUuid>) adapter).marshal(vpi);
		assertEquals("The right uuid was returned", UUID.toString(), uuid);
		
		vpi.setUuid(null);
		assertThrows("The type instance should have a uuid",
			NullPointerException.class, () -> {
				((XmlAdapter<String, IUuid>) adapter).marshal(vpi);
			}
		);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testUnmarhall() throws Exception {
		IUuid unmarshalledTi = ((XmlAdapter<String, IUuid>) adapter).unmarshal(UUID.toString());
		assertEquals("The right vpi was returned", vpi, (ValuePropertyInstance) unmarshalledTi);		
	}
	
	@Override
	public void testUnmarshallNull() {
		adapterWithoutResourceSet = new IUuidAdapterNoRoleManagement();
		super.testUnmarshallNull();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testUnmarshallEmptyRs() {
		adapterWithEmptyResourceSet = new IUuidAdapterNoRoleManagement(new ResourceSetImpl());
		assertThrows("No mapping found",
			IllegalArgumentException.class, () -> {
				((XmlAdapter<String, IUuid>) adapterWithEmptyResourceSet).unmarshal(UUID.toString());
			}
		);
	}

}
