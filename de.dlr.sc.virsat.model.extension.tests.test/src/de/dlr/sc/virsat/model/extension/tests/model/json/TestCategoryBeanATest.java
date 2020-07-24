/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.model.json;

import static de.dlr.sc.virsat.model.extension.tests.test.TestActivator.assertEqualsNoWs;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanA;
import de.dlr.sc.virsat.model.extension.tests.test.TestActivator;

public class TestCategoryBeanATest extends AConceptTestCase {

	private JAXBUtility jaxbUtility;
	private Concept concept;
	
	@Before
	public void setUp() throws Exception {
		concept = loadConceptFromPlugin();
		
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryBeanA.class});
	}

	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		
		Marshaller jsonMarshaller = jaxbUtility.getJsonMarshaller();
		
		TestCategoryBeanA testCategoryBean = new TestCategoryBeanA(concept);
		testCategoryBean.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(testCategoryBean, sw);
		
		String expectedJson = TestActivator.getResourceContentAsString("/resources/json/TestCategoryBeanA_Marshaling.json");
		assertEqualsNoWs("Json is as expected", expectedJson, sw.toString());
	}
	
	@Test
	public void testJsonUnMarshalling() throws JAXBException, IOException {
		
		TestCategoryBeanA originCatgeoryBean = new TestCategoryBeanA(concept);
		originCatgeoryBean.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		
		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().add(originCatgeoryBean.getATypeInstance());
		
		Unmarshaller jsonUnmarshaller = jaxbUtility.getJsonUnmarshaller(resourceSet);

		String inputJson = TestActivator.getResourceContentAsString("/resources/json/TestCategoryBeanA_Marshaling.json");
		StringReader sr = new StringReader(inputJson);
		
		JAXBElement<TestCategoryBeanA> jaxbElement = jsonUnmarshaller.unmarshal(new StreamSource(sr), TestCategoryBeanA.class);
		TestCategoryBeanA createdBeanA = jaxbElement.getValue();
		assertEquals(originCatgeoryBean, createdBeanA);
	}
	
}
