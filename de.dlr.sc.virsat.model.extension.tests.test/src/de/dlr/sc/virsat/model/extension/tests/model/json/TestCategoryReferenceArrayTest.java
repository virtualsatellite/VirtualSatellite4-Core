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
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReferenceArray;
import de.dlr.sc.virsat.model.extension.tests.test.TestActivator;

public class TestCategoryReferenceArrayTest extends AConceptTestCase {

	private TestCategoryReferenceArray tcReferenceArray;
	private JAXBUtility jaxbUtility;
	private Concept concept;
	private BeanPropertyString bpString;
	private TestCategoryAllProperty tcAllProperty;
	private static final String RESOURCE = "/resources/json/TestCategoryReferenceArray_Marshaling.json";
	private static final String RESOURCE_NULL_REFERENCE = "/resources/json/TestCategoryReferenceArray_Marshaling_NullReference.json";
	private static final String RESOURCE_CHANGED_REFERENCE = "/resources/json/TestCategoryReferenceArray_Marshaling_ChangeReference.json";

	@Before
	public void setup() throws JAXBException {
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryReferenceArray.class});

		// Load the concept to create the test object
		prepareEditingDomain();
		concept = loadConceptFromPlugin();
		tcReferenceArray = new TestCategoryReferenceArray(concept);
		
		tcAllProperty = new TestCategoryAllProperty(concept);
		
		// Set uuids to match the test resource
		JsonTestHelper.setTestCategoryAllPropertyUuids(tcAllProperty);
		
		bpString = JsonTestHelper.createTestStringBean(concept);
		
		tcReferenceArray.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		
		IBeanList<BeanPropertyReference<TestCategoryAllProperty>> staticCategoryArray = tcReferenceArray.getTestCategoryReferenceArrayStaticBean();
		for (int i = 0; i < staticCategoryArray.size(); i++) {
			BeanPropertyReference<TestCategoryAllProperty> bean = staticCategoryArray.get(i);
			bean.getATypeInstance().setUuid(new VirSatUuid("8872b433-968c-4f48-b9a3-d734c6e239a" + Integer.toString(i)));
			
			bean.setValue(tcAllProperty);
		}
		
		IBeanList<BeanPropertyReference<BeanPropertyString>> staticPropertyArray = tcReferenceArray.getTestPropertyReferenceArrayStaticBean();
		for (int i = 0; i < staticPropertyArray.size(); i++) {
			BeanPropertyReference<BeanPropertyString> bean = staticPropertyArray.get(i);
			bean.getATypeInstance().setUuid(new VirSatUuid("49177554-f1e4-4529-bf1b-3036abb1ee3" + i));
			
			bean.setValue(bpString);
		}
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		JsonTestHelper.assertMarshall(jaxbUtility, RESOURCE, tcReferenceArray);
	}
	
	@Test
	public void testJsonUnmarshalling() throws JAXBException, IOException {
		BeanPropertyString bpString2 = new TestCategoryAllProperty(concept).getTestStringBean();
		bpString2.getATypeInstance().setUuid(new VirSatUuid("1256e7a2-9a1f-443c-85f8-7b766eac3f50"));
		
		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().add(tcReferenceArray.getATypeInstance());
		resourceImpl.getContents().add(tcAllProperty.getATypeInstance());
		resourceImpl.getContents().add(bpString.getATypeInstance());
		resourceImpl.getContents().add(bpString2.getATypeInstance());
		
		Unmarshaller jsonUnmarshaller = jaxbUtility.getJsonUnmarshaller(resourceSet);
		
		String inputJson = TestActivator.getResourceContentAsString(RESOURCE_CHANGED_REFERENCE);
		StringReader sr = new StringReader(inputJson);
		
		assertEquals(bpString.getUuid(), tcReferenceArray.getTestPropertyReferenceArrayStatic().get(0).getUuid());
		
		jsonUnmarshaller.unmarshal(new StreamSource(sr), TestCategoryReferenceArray.class);
		
		assertEquals("Referenced bean changed successfully", bpString2.getUuid(), tcReferenceArray.getTestPropertyReferenceArrayStatic().get(0).getUuid());
	}
	
	@Test
	public void testJsonMarshallingNull() throws JAXBException, IOException {
		tcReferenceArray.getTestCategoryReferenceArrayStaticBean().get(0).setValue(null);
		tcReferenceArray.getTestPropertyReferenceArrayStaticBean().get(0).setValue(null);
		
		JsonTestHelper.assertMarshall(jaxbUtility, RESOURCE_NULL_REFERENCE, tcReferenceArray);
	}
	
	@Test
	public void testJsonUnmarshallingNull() throws JAXBException, IOException {
		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().add(tcReferenceArray.getATypeInstance());
		resourceImpl.getContents().add(tcAllProperty.getATypeInstance());
		resourceImpl.getContents().add(bpString.getATypeInstance());
		
		Unmarshaller jsonUnmarshaller = jaxbUtility.getJsonUnmarshaller(resourceSet);
		
		String inputJson = TestActivator.getResourceContentAsString(RESOURCE_NULL_REFERENCE);
		StringReader sr = new StringReader(inputJson);
		
		assertNotNull(tcReferenceArray.getTestCategoryReferenceArrayStaticBean().get(0).getValue());
		assertNotNull(tcReferenceArray.getTestPropertyReferenceArrayStaticBean().get(0).getValue());
		assertNotNull(tcReferenceArray.getTestCategoryReferenceArrayStatic().get(0));
		assertNotNull(tcReferenceArray.getTestPropertyReferenceArrayStatic().get(0));
		
		jsonUnmarshaller.unmarshal(new StreamSource(sr), TestCategoryReferenceArray.class);
		
		assertNull(tcReferenceArray.getTestCategoryReferenceArrayStaticBean().get(0).getValue());
		assertNull(tcReferenceArray.getTestPropertyReferenceArrayStaticBean().get(0).getValue());
		// Currently this list will return a ca without a type instance
		assertNull(tcReferenceArray.getTestCategoryReferenceArrayStatic().get(0).getTypeInstance());
		assertNull(tcReferenceArray.getTestPropertyReferenceArrayStatic().get(0).getTypeInstance());
	}
}
