/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.model.json;

import static org.junit.Assert.assertEquals;
import static de.dlr.sc.virsat.model.extension.tests.test.TestActivator.assertEqualsNoWs;


import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.extension.tests.model.ATestCategoryBeanATest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanA;
import de.dlr.sc.virsat.model.extension.tests.test.TestActivator;

public class TestCategoryBeanATest extends ATestCategoryBeanATest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
	}

	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		
		// Setup the properties for the Jaxb Context and the Marshaler
		Map<String, Object> properties = new HashMap<>();
		properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
		
		JAXBContext jaxbCtx = JAXBContext.newInstance(new Class[] {TestCategoryBeanA.class}, properties);
		Marshaller jsonMarshaller = jaxbCtx.createMarshaller();
		jsonMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		TestCategoryBeanA testCategoryBean = new TestCategoryBeanA(concept);
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(testCategoryBean, sw);
		
		String expectedJson = TestActivator.getResourceContentAsString("/resources/json/TestCategoryBeanA_Marshaling.json");
		assertEqualsNoWs("Json is as expected", expectedJson, sw.toString());
	}
	
}
