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

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;

/**
 * Tests that the ABeanObjectAdapter un-/marshalls
 * a ABeanObject as expected
 */
public class ABeanObjectAdapterTest extends AUuidAdapterTest {

	private ValuePropertyInstance vpi;
	private BeanPropertyString bean;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		vpi.setUuid(UUID);
		vpi.setType(PropertydefinitionsFactory.eINSTANCE.createStringProperty());
		resourceImpl.getContents().add(vpi);
		
		bean = new BeanPropertyString(vpi);
		
		adapter = new ABeanObjectAdapter(resourceSet);		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void testMarshallNull() throws Exception {
		String uuid = ((XmlAdapter<String, ABeanObject>) adapter).marshal(null);
		assertNull("No bean returns null", uuid);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void testMarhall() throws Exception {
		String uuid = ((XmlAdapter<String, ABeanObject>) adapter).marshal(bean);
		assertEquals("The right uuid was returned", UUID.toString(), uuid);
		
		vpi.setUuid(null);
		assertThrows("The type instance should have a uuid",
			NullPointerException.class, () -> {
				((XmlAdapter<String, ABeanObject>) adapter).marshal(bean);
			}
		);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testUnmarhall() throws Exception {
		@SuppressWarnings("rawtypes")
		ABeanObject unmarshalledBean = ((XmlAdapter<String, ABeanObject>) adapter).unmarshal(UUID.toString());
		assertEquals("The right bean was returned", bean, (BeanPropertyString) unmarshalledBean);
	}
	
	@Override
	public void testUnmarshallNull() {
		adapterWithoutResourceSet = new ABeanObjectAdapter();
		super.testUnmarshallNull();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testUnmarshallEmptyRs() {
		adapterWithEmptyResourceSet = new ABeanObjectAdapter(new ResourceSetImpl());
		assertThrows("No mapping found",
			IllegalArgumentException.class, () -> {
				((XmlAdapter<String, ABeanObjectAdapter>) adapterWithEmptyResourceSet).unmarshal(UUID.toString());
			}
		);
	}

}
