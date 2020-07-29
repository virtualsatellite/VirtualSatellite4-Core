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

	private TestCategoryReferenceArray testArray;
	private JAXBUtility jaxbUtility;
	private Concept concept;
	private BeanPropertyString bpString;
	private TestCategoryAllProperty tcAllProperty;
	private static final String RESOURCE = "/resources/json/TestCategoryReferenceArray_Marshaling.json";
	private static final String RESOURCE_CHANGED_REFERENCE = "/resources/json/TestCategoryReferenceArray_Marshaling_ChangeReference.json";

	@Before
	public void setup() throws JAXBException {
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryReferenceArray.class});

		// Load the concept to create the test object
		prepareEditingDomain();
		concept = loadConceptFromPlugin();
		testArray = new TestCategoryReferenceArray(concept);
		
		tcAllProperty = new TestCategoryAllProperty(concept);
		
		// Set uuids to match the test resource
		JsonTestHelper.setTestCategoryAllPropertyUuids(tcAllProperty);
		
		bpString = JsonTestHelper.createTestStringBean(concept);
		
		testArray.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		
		IBeanList<BeanPropertyReference<TestCategoryAllProperty>> staticCategoryArray = testArray.getTestCategoryReferenceArrayStaticBean();
		for (int i = 0; i < staticCategoryArray.size(); i++) {
			BeanPropertyReference<TestCategoryAllProperty> bean = staticCategoryArray.get(i);
			bean.getATypeInstance().setUuid(new VirSatUuid("8872b433-968c-4f48-b9a3-d734c6e239a" + Integer.toString(i)));
			
			bean.setValue(tcAllProperty);
		}
		
		IBeanList<BeanPropertyReference<BeanPropertyString>> staticPropertyArray = testArray.getTestPropertyReferenceArrayStaticBean();
		for (int i = 0; i < staticPropertyArray.size(); i++) {
			BeanPropertyReference<BeanPropertyString> bean = staticPropertyArray.get(i);
			bean.getATypeInstance().setUuid(new VirSatUuid("49177554-f1e4-4529-bf1b-3036abb1ee3" + Integer.toString(i)));
			
			bean.setValue(bpString);
		}
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		
		Marshaller jsonMarshaller = jaxbUtility.getJsonMarshaller();
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(testArray, sw);
		
		System.out.println(sw.toString());
		
		String expectedJson = TestActivator.getResourceContentAsString(RESOURCE);
		assertEqualsNoWs("Json is as expected", expectedJson, sw.toString());
	}
	
	@Test
	public void testJsonUnmarshalling() throws JAXBException, IOException {
		BeanPropertyString bpString2 = new TestCategoryAllProperty(concept).getTestStringBean();
		bpString2.getATypeInstance().setUuid(new VirSatUuid("1256e7a2-9a1f-443c-85f8-7b766eac3f50"));
		
		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().add(testArray.getATypeInstance());
		resourceImpl.getContents().add(tcAllProperty.getATypeInstance());
		resourceImpl.getContents().add(bpString.getATypeInstance());
		resourceImpl.getContents().add(bpString2.getATypeInstance());
		
		Unmarshaller jsonUnmarshaller = jaxbUtility.getJsonUnmarshaller(resourceSet);
		
		String inputJson = TestActivator.getResourceContentAsString(RESOURCE_CHANGED_REFERENCE);
		System.out.println(inputJson);
		StringReader sr = new StringReader(inputJson);
		
		assertEquals(bpString.getUuid(), testArray.getTestPropertyReferenceArrayStatic().get(0).getUuid());
		
		JAXBElement<TestCategoryReferenceArray> jaxbElement = jsonUnmarshaller.unmarshal(new StreamSource(sr), TestCategoryReferenceArray.class);
		TestCategoryReferenceArray createdArray = jaxbElement.getValue();
		
		assertEquals(bpString2.getUuid(), createdArray.getTestPropertyReferenceArrayStatic().get(0).getUuid());
	}
}
