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

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesFactoryImpl;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.tests.model.ATestCategoryIntrinsicArrayTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryIntrinsicArray;
import de.dlr.sc.virsat.model.extension.tests.test.TestActivator;

public class TestCategoryIntrinsicArrayTest extends ATestCategoryIntrinsicArrayTest {
	
	private JAXBUtility jaxbUtility;
	private BeanPropertyString beanPropertyString;
	private TestCategoryIntrinsicArray testArray;
	
	private static final String RESOURCE = "/resources/json/TestCategoryIntrinsicArray_Marshaling.json";
	private static final String TEST_STRING = "testString";
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryIntrinsicArray.class});
		
		// Create a test string bean
		ValuePropertyInstance vpi = PropertyinstancesFactoryImpl.eINSTANCE.createValuePropertyInstance();
		vpi.setValue(TEST_STRING);
		beanPropertyString = new BeanPropertyString(vpi);
		beanPropertyString.getATypeInstance().setUuid(new VirSatUuid("d7ed5b62-f096-4347-88ef-eae61c5f166b"));
		
		testArray = new TestCategoryIntrinsicArray(concept);
		testArray.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		testArray.getTestStringArrayDynamic().getArrayInstance().setUuid(new VirSatUuid("ee6e1025-4a77-4b32-9c62-cb459ed76ce8"));		
		testArray.getTestStringArrayStatic().get(0).getATypeInstance().setUuid(new VirSatUuid("4efe0002-f081-49c0-9917-6f4a6e7dd9ce"));
		testArray.getTestStringArrayStatic().get(1).getATypeInstance().setUuid(new VirSatUuid("6ad3d35a-a0b4-48e8-9bfd-e6edf438eee5"));
		testArray.getTestStringArrayStatic().get(2).getATypeInstance().setUuid(new VirSatUuid("8fd96e3b-5bf3-41e1-a02a-64f8bff99107"));
		testArray.getTestStringArrayStatic().get(3).getATypeInstance().setUuid(new VirSatUuid("c38d7185-fcc3-480c-bfb4-28e6fcc09d34"));
		testArray.getTestStringArrayStatic().getArrayInstance().setUuid(new VirSatUuid("98218bbf-a5ee-432d-b01c-da48f4f9495b"));
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		
		Marshaller jsonMarshaller = jaxbUtility.getJsonMarshaller();
		
		testArray.getTestStringArrayStatic().get(0).setValue(TEST_STRING);
		testArray.getTestStringArrayDynamic().add(beanPropertyString);
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(testArray, sw);
		
		System.out.println(sw.toString());
		
		String expectedJson = TestActivator.getResourceContentAsString(RESOURCE);
		assertEqualsNoWs("Json is as expected", expectedJson, sw.toString());
	}
	
	@Test
	public void testJsonUnmarshalling() throws JAXBException, IOException {
		ArrayInstance originalArrayInstance = testArray.getTestStringArrayStatic().getArrayInstance();
		
		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().add(testArray.getATypeInstance());
		resourceImpl.getContents().add(originalArrayInstance);
		resourceImpl.getContents().add(beanPropertyString.getATypeInstance());
		
		Unmarshaller jsonUnmarshaller = jaxbUtility.getJsonUnmarshaller(resourceSet);
		
		assertEquals(testArray.getTestStringArrayStatic().get(0).getValue(), null);
		assertEquals(testArray.getTestStringArrayDynamic().size(), 0);
		
		String inputJson = TestActivator.getResourceContentAsString(RESOURCE);
		System.out.println(inputJson);
		StringReader sr = new StringReader(inputJson);

		JAXBElement<TestCategoryIntrinsicArray> jaxbElement = jsonUnmarshaller.unmarshal(new StreamSource(sr), TestCategoryIntrinsicArray.class);
		TestCategoryIntrinsicArray createdArray = jaxbElement.getValue();
		assertEquals(testArray, createdArray);
		assertEquals(originalArrayInstance, createdArray.getTestStringArrayStatic().getArrayInstance());
		assertEquals(testArray.getTestStringArrayStatic().get(0).getValue(), "testString");
		
		assertEquals(testArray.getTestStringArrayDynamic().size(), 1);
	}
}
