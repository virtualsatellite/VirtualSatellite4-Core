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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;

public class JAXBUtilityTest {

	private JAXBUtility jaxbUtility;
	@SuppressWarnings("rawtypes")
	private static final Class[] REGISTER_CLASSES = new Class[] { Object.class };

	@Before
	public void setUp() throws Exception {
		jaxbUtility = new JAXBUtility();
	}

	private void assertIsInitialized() {
		assertEquals("System property set", JAXBUtility.CLASSPATH_JAXB_CONTEXT_FACTORY, System.getProperty(JAXBUtility.SYSTEM_PROP_CONTEXT_FACTORY));
		
		//CHECKSTYLE:OFF
		assertEquals("Two properties set", 3, jaxbUtility.getProperties().size());
		//CHECKSTYLE:OFF
	}
	
	@Test
	public void testEmptyConstructor() {
		assertIsInitialized();
	}
	
	@Test
	public void testConstructor() throws JAXBException {
		jaxbUtility = new JAXBUtility(REGISTER_CLASSES);
		
		assertNotNull(jaxbUtility.getContext());
		assertIsInitialized();
	}

	@Test
	public void testCreateContext() throws JAXBException {
		assertThrows(IllegalArgumentException.class, () -> {
			jaxbUtility.createContext(null);
		});
		
		JAXBContext context = jaxbUtility.createContext(new Class[] {});
		assertNotNull(context);
		
		context = jaxbUtility.createContext(REGISTER_CLASSES);
		assertNotNull(context);
	}

	@Test
	public void testGetJsonMarshaller() throws JAXBException {
		assertNull(jaxbUtility.getJsonMarshaller());
		
		jaxbUtility.createContext(REGISTER_CLASSES);
		Marshaller marshaller = jaxbUtility.getJsonMarshaller();
		assertNotNull(marshaller);
		assertNotNull(marshaller.getEventHandler());
		assertEquals(true, marshaller.getProperty(Marshaller.JAXB_FORMATTED_OUTPUT));
	}

	@Test
	public void testGetJsonUnmarshaller() throws JAXBException {
		assertNull(jaxbUtility.getJsonUnmarshaller(null));
		
		jaxbUtility.createContext(REGISTER_CLASSES);
		Unmarshaller unmarshaller = jaxbUtility.getJsonUnmarshaller(null);
		assertNotNull(unmarshaller);
		assertNotNull(unmarshaller.getEventHandler());
		
		assertNotNull("Adapter is set", unmarshaller.getAdapter(IUuidAdapter.class));
		assertNotNull("Adapter is set", unmarshaller.getAdapter(ABeanObjectAdapter.class));
		assertNotNull("Adapter is set", unmarshaller.getAdapter(ABeanStructuralElementInstanceAdapter.class));
	}

}
