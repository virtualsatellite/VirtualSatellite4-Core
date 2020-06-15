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

import static de.dlr.sc.virsat.model.extension.tests.test.TestActivator.assertEqualsNoWs;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.json.TypeInstanceAdapter;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
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
		properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
		
		JAXBContext jaxbCtx = JAXBContext.newInstance(new Class[] {TestCategoryBeanA.class}, properties);
		Marshaller jsonMarshaller = jaxbCtx.createMarshaller();
		jsonMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		TestCategoryBeanA testCategoryBean = new TestCategoryBeanA(concept);
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(testCategoryBean, sw);
		
		String expectedJson = TestActivator.getResourceContentAsString("/resources/json/TestCategoryBeanA_Marshaling.json");
		assertEqualsNoWs("Json is as expected", expectedJson, sw.toString());
	}
	
	@Test
	public void testJsonUnMarshalling() throws JAXBException, IOException {
		
		// Setup the properties for the Jaxb Context and the Marshaler
		Map<String, Object> properties = new HashMap<>();
		properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
		properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
		
		TestCategoryBeanA originCatgeoryBean = new TestCategoryBeanA(concept);
		originCatgeoryBean.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		
		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().add(originCatgeoryBean.getATypeInstance());
		
		JAXBContext jaxbCtx = JAXBContext.newInstance(new Class[] {TestCategoryBeanA.class}, properties);
		
		Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
		TypeInstanceAdapter typeInstanceAdapter = new TypeInstanceAdapter(resourceSet);
		unmarshaller.setAdapter(typeInstanceAdapter);
		
		Marshaller marshaller = jaxbCtx.createMarshaller();
		marshaller.marshal(originCatgeoryBean, System.out);
		/*
		unmarshaller.setListener(new Listener() {
			@Override
			public void beforeUnmarshal(Object target, Object parent) {
				IBeanObject<ATypeInstance> beanA =  (IBeanObject<ATypeInstance>) target;
			
				ATypeInstance typeInstance = originCatgeoryBean.getTypeInstance();
				beanA.setTypeInstance(typeInstance);
			}
			
		});
		*/
		
		String inputJson = TestActivator.getResourceContentAsString("/resources/json/TestCategoryBeanA_Marshaling.json");
		StringReader sr = new StringReader(inputJson);
		
		JAXBElement createdBeanA = (JAXBElement) unmarshaller.unmarshal(sr);
		
		System.out.println(createdBeanA);
		
	}
	
}
