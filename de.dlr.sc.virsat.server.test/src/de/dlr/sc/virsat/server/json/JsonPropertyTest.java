/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.json;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.oxm.json.JsonGeneratorResult;
import org.eclipse.persistence.oxm.json.JsonObjectBuilderResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

/**
 * Class to test the marshaling of Property Beans from and to Json
 */
public class JsonPropertyTest {

	@Before
	public void setUp() throws Exception {
		System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testBeanPropertyStringMarshaling() throws JAXBException {
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		BeanPropertyString beanProperty = new BeanPropertyString();
		beanProperty.setTypeInstance(vpi);
		
		vpi.setUuid(new VirSatUuid());
		vpi.setValue("Test String for Json Marshaling");

		Map<String, Object> properties = new HashMap<>();
		properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
		//properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
		
		JAXBContext jaxbCtx = JAXBContext.newInstance(new Class[] {BeanPropertyString.class}, properties);
		Marshaller jsonMarshaller = jaxbCtx.createMarshaller();
		
		jsonMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		//jsonMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(beanProperty, sw);
		System.out.println(sw.toString());
		
		Unmarshaller jsonUnmarshaller = jaxbCtx.createUnmarshaller();
		
		Object object = jsonUnmarshaller.unmarshal(new StringReader(sw.toString()));
		
		BeanPropertyString beanPropertyRebuild = (BeanPropertyString) object;
		
		assertEquals("Everything is fine", beanProperty, beanPropertyRebuild);
	}
	
}
