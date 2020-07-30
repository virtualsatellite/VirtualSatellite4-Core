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

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.tests.model.ATestCategoryIntrinsicArrayTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryIntrinsicArray;
import de.dlr.sc.virsat.model.extension.tests.test.TestActivator;

public class TestCategoryIntrinsicArrayTest extends ATestCategoryIntrinsicArrayTest {
	
	private JAXBUtility jaxbUtility;
	private BeanPropertyString bpString;
	private TestCategoryIntrinsicArray testArray;
	
	private static final String RESOURCE = "/resources/json/TestCategoryIntrinsicArray_Marshaling.json";
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryIntrinsicArray.class});
		
		bpString = JsonTestHelper.createTestStringBean(concept);

		testArray = new TestCategoryIntrinsicArray(concept);
		testArray.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		testArray.getTestStringArrayDynamicBean().getArrayInstance().setUuid(new VirSatUuid("ee6e1025-4a77-4b32-9c62-cb459ed76ce8"));		
		IBeanList<BeanPropertyString> list = testArray.getTestStringArrayStaticBean();
		JsonTestHelper.setStaticIBeanListUuids(list);
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		bpString.setValue(JsonTestHelper.TEST_STRING);
		Marshaller jsonMarshaller = jaxbUtility.getJsonMarshaller();
		
		testArray.getTestStringArrayStaticBean().get(0).setValue(JsonTestHelper.TEST_STRING);
		testArray.getTestStringArrayDynamicBean().add(bpString);
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(testArray, sw);
		
		String expectedJson = TestActivator.getResourceContentAsString(RESOURCE);
		assertEqualsNoWs("Json is as expected", expectedJson, sw.toString());
	}
	
	@Test
	public void testJsonUnmarshalling() throws JAXBException, IOException {
		ArrayInstance originalArrayInstance = testArray.getTestStringArrayStaticBean().getArrayInstance();
		
		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().add(testArray.getATypeInstance());
		resourceImpl.getContents().add(originalArrayInstance);
		resourceImpl.getContents().add(bpString.getATypeInstance());
		
		Unmarshaller jsonUnmarshaller = jaxbUtility.getJsonUnmarshaller(resourceSet);
		
		assertEquals(testArray.getTestStringArrayStaticBean().get(0).getValue(), null);
		assertEquals(testArray.getTestStringArrayDynamicBean().size(), 0);
		
		String inputJson = TestActivator.getResourceContentAsString(RESOURCE);
		StringReader sr = new StringReader(inputJson);

		jsonUnmarshaller.unmarshal(new StreamSource(sr), TestCategoryIntrinsicArray.class);
		assertEquals("Element in static element changed", JsonTestHelper.TEST_STRING, testArray.getTestStringArrayStaticBean().get(0).getValue());
		assertEquals("Successfully added an element", 1, testArray.getTestStringArrayDynamicBean().size());
	}
}
