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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryCompositionArray;
import de.dlr.sc.virsat.model.extension.tests.test.TestActivator;

public class TestCategoryCompositionArrayTest extends AConceptTestCase {
	
	private TestCategoryCompositionArray tcCompositionArray;
	private JAXBUtility jaxbUtility;
	private Concept concept;
	private static final String RESOURCE = "/resources/json/TestCategoryCompositionArray_Marshaling.json";
	private static final String RESOURCE_NULL_COMPOSITION = "/resources/json/TestCategoryCompositionArray_Marshaling_NullComposition.json";
	private static final String RESOURCE_CHANGE_ELEMENT = "/resources/json/TestCategoryCompositionArray_Marshaling_ChangeElement.json";

	@Before
	public void setup() throws JAXBException {
		// Because a composed bean is generic we need an adapter
		// And because of the adapter we have to manually add the composed
		// classes to the marshaller.
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryCompositionArray.class, TestCategoryAllProperty.class});
		
		// Load the concept to create the test object
		prepareEditingDomain();
		concept = loadConceptFromPlugin();
		tcCompositionArray = new TestCategoryCompositionArray(concept);
		
		tcCompositionArray.getATypeInstance().setUuid(new VirSatUuid("f5d016ac-65fa-4b9d-ae94-582d4f73138a"));
		IBeanList<BeanPropertyComposed<TestCategoryAllProperty>> staticArray = tcCompositionArray.getTestCompositionArrayStaticBean();
		for (int i = 0; i < staticArray.size(); i++) {
			BeanPropertyComposed<TestCategoryAllProperty> bean = staticArray.get(i);
			bean.getATypeInstance().setUuid(new VirSatUuid("45e18c9d-ef85-4ab8-ba2a-c5916697a0b" + i));
			
			TestCategoryAllProperty tcAllProperty = bean.getValue();
			JsonTestHelper.setTestCategoryAllPropertyUuids(tcAllProperty, i);
		}
		
		JsonTestHelper.createRepositoryWithUnitManagement(concept);	
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		tcCompositionArray.getTestCompositionArrayStatic().get(0).getTestStringBean().setValue(JsonTestHelper.TEST_STRING);
		
		JsonTestHelper.assertMarshall(jaxbUtility, RESOURCE, tcCompositionArray);
	}
	
	@Test
	public void testJsonUnmarshalling() throws JAXBException, IOException {
		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().add(tcCompositionArray.getATypeInstance());
		
		Unmarshaller jsonUnmarshaller = jaxbUtility.getJsonUnmarshaller(resourceSet);
		
		String inputJson = TestActivator.getResourceContentAsString(RESOURCE);
		StringReader sr = new StringReader(inputJson);

		assertNull(tcCompositionArray.getTestCompositionArrayStatic().get(0).getTestStringBean().getValue());
		
		jsonUnmarshaller.unmarshal(new StreamSource(sr), TestCategoryCompositionArray.class);
		
		assertEquals(JsonTestHelper.TEST_STRING, tcCompositionArray.getTestCompositionArrayStatic().get(0).getTestStringBean().getValue());
	}
	
	@Test
	public void testJsonUnmarshallingChangeWholeElement() throws JAXBException, IOException {
		TestCategoryAllProperty tcAllPropertyNew = new TestCategoryAllProperty(concept);
		final int LAST_NUMBER = 9;
		JsonTestHelper.setTestCategoryAllPropertyUuids(tcAllPropertyNew, LAST_NUMBER);

		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().add(tcCompositionArray.getATypeInstance());
		resourceImpl.getContents().add(tcAllPropertyNew.getATypeInstance());
		
		Unmarshaller jsonUnmarshaller = jaxbUtility.getJsonUnmarshaller(resourceSet);
		
		String inputJson = TestActivator.getResourceContentAsString(RESOURCE_CHANGE_ELEMENT);
		StringReader sr = new StringReader(inputJson);

		jsonUnmarshaller.unmarshal(new StreamSource(sr), TestCategoryCompositionArray.class);
		
		// The set value function of the bean doesn't have any effect at the moment
		assertNotEquals("No new element set", tcAllPropertyNew.getATypeInstance().getUuid(), tcCompositionArray.getTestCompositionArrayStatic().get(0).getUuid());
	}
	
	@Test
	public void testJsonMarshallingNull() throws JAXBException, IOException {
		tcCompositionArray.getTestCompositionArrayStaticBean().get(0).getTypeInstance().setTypeInstance(null);
		
		JsonTestHelper.assertMarshall(jaxbUtility, RESOURCE_NULL_COMPOSITION, tcCompositionArray);
	}
	
	@Test
	public void testJsonUnmarshallingNull() throws JAXBException, IOException {
		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().add(tcCompositionArray.getATypeInstance());
		
		Unmarshaller jsonUnmarshaller = jaxbUtility.getJsonUnmarshaller(resourceSet);
		
		String inputJson = TestActivator.getResourceContentAsString(RESOURCE_NULL_COMPOSITION);
		StringReader sr = new StringReader(inputJson);

		assertNotNull(tcCompositionArray.getTestCompositionArrayStatic().get(0));
		
		jsonUnmarshaller.unmarshal(new StreamSource(sr), TestCategoryCompositionArray.class);
		
		// The set value function of the bean doesn't have any effect at the moment
		assertNotNull(tcCompositionArray.getTestCompositionArrayStatic().get(0));
	}
}
