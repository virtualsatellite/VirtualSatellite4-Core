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
import java.util.Arrays;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReferenceArray;

public class TestCategoryReferenceArrayTest extends AConceptTestCase {

	private TestCategoryReferenceArray tcReferenceArray;
	private JAXBUtility jaxbUtility;
	private Concept concept;
	private BeanPropertyString bpString;
	private TestCategoryAllProperty tcAllProperty;
	private BeanPropertyReference<BeanPropertyString> refPropBean;
	private BeanPropertyReference<TestCategoryAllProperty> refCatBean;
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
			bean.getATypeInstance().setUuid(new VirSatUuid("8872b433-968c-4f48-b9a3-d734c6e239a" + i));
			
			bean.setValue(tcAllProperty);
		}
		
		IBeanList<BeanPropertyReference<BeanPropertyString>> staticPropertyArray = tcReferenceArray.getTestPropertyReferenceArrayStaticBean();
		for (int i = 0; i < staticPropertyArray.size(); i++) {
			BeanPropertyReference<BeanPropertyString> bean = staticPropertyArray.get(i);
			bean.getATypeInstance().setUuid(new VirSatUuid("49177554-f1e4-4529-bf1b-3036abb1ee3" + i));
			
			bean.setValue(bpString);
		}
		
		// For composed / reference beans the wrapping beans
		// have to exist because else adding a new element to
		// the dynamic list in the unmarshalling fails
		ReferencePropertyInstance rpi = PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
		refPropBean = new BeanPropertyReference<BeanPropertyString>(rpi);
		rpi.setUuid(new VirSatUuid("f8932f09-71b7-40b6-bcbd-5d71c2b8d6d7"));
		refPropBean.setValue(bpString);
		
		ReferencePropertyInstance rpi2 = PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
		refCatBean = new BeanPropertyReference<TestCategoryAllProperty>(rpi2);
		rpi2.setUuid(new VirSatUuid("0544cfe4-ec11-4585-8f1b-e875e26ae33c"));
		refCatBean.setValue(tcAllProperty);
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		// Use the bean list here to force the wanted bean (to reuse the same resource as for unmarshalling)
		tcReferenceArray.getTestCategoryReferenceArrayDynamicBean().add(refCatBean);
		tcReferenceArray.getTestPropertyReferenceArrayDynamicBean().add(refPropBean);
		
		JsonTestHelper.assertMarshall(jaxbUtility, RESOURCE, tcReferenceArray);
	}
	
	@Test
	public void testJsonUnmarshalling() throws JAXBException, IOException {
		Unmarshaller jsonUnmarshaller = JsonTestHelper.getUnmarshaller(jaxbUtility, Arrays.asList(
				tcReferenceArray.getATypeInstance(),
				tcAllProperty.getATypeInstance(),
				bpString.getATypeInstance(),
				refCatBean.getATypeInstance(),
				refPropBean.getATypeInstance()
		));
		
		StreamSource inputSource = JsonTestHelper.getResourceAsStreamSource(RESOURCE);
		
		assertEquals("Initial no dynamic element", 0, tcReferenceArray.getTestPropertyReferenceArrayDynamic().size());
		assertEquals("Initial no dynamic element", 0, tcReferenceArray.getTestCategoryReferenceArrayDynamic().size());
		
		jsonUnmarshaller.unmarshal(inputSource, TestCategoryReferenceArray.class);
		
		assertEquals("Element added successfully", 1, tcReferenceArray.getTestPropertyReferenceArrayDynamic().size());
		assertEquals("Element added successfully", 1, tcReferenceArray.getTestCategoryReferenceArrayDynamic().size());

		// Unmarshall again to test idempotency
		inputSource = JsonTestHelper.getResourceAsStreamSource(RESOURCE);
		jsonUnmarshaller.unmarshal(inputSource, TestCategoryReferenceArray.class);
		assertEquals("Same element not added again", 1, tcReferenceArray.getTestPropertyReferenceArrayDynamic().size());
		assertEquals("Same element not added again", 1, tcReferenceArray.getTestCategoryReferenceArrayDynamic().size());
		
	}
	
	@Test
	public void testJsonUnmarshallingChangeReference() throws JAXBException, IOException {
		TestCategoryAllProperty tcAllProperty2 = new TestCategoryAllProperty(concept);
		tcAllProperty2.getATypeInstance().setUuid(new VirSatUuid("134d30b0-80f5-4c96-864f-29ab4d3ae9f0"));
		
		BeanPropertyString bpString2 = tcAllProperty2.getTestStringBean();
		bpString2.getATypeInstance().setUuid(new VirSatUuid("1256e7a2-9a1f-443c-85f8-7b766eac3f50"));
		
		Unmarshaller jsonUnmarshaller = JsonTestHelper.getUnmarshaller(jaxbUtility, Arrays.asList(
				tcReferenceArray.getATypeInstance(),
				tcAllProperty.getATypeInstance(),
				bpString.getATypeInstance(),
				tcAllProperty2.getATypeInstance(),
				bpString2.getATypeInstance()
		));
		
		StreamSource inputSource = JsonTestHelper.getResourceAsStreamSource(RESOURCE_CHANGED_REFERENCE);
		
		assertEquals(bpString.getUuid(), tcReferenceArray.getTestPropertyReferenceArrayStatic().get(0).getUuid());
		assertEquals(tcAllProperty.getUuid(), tcReferenceArray.getTestCategoryReferenceArrayStatic().get(0).getUuid());
		
		jsonUnmarshaller.unmarshal(inputSource, TestCategoryReferenceArray.class);
		
		assertEquals("Referenced bean changed successfully", bpString2.getUuid(), tcReferenceArray.getTestPropertyReferenceArrayStatic().get(0).getUuid());
		assertEquals("Referenced bean changed successfully", tcAllProperty2.getUuid(), tcReferenceArray.getTestCategoryReferenceArrayStatic().get(0).getUuid());
	}
	
	@Test
	public void testJsonMarshallingNull() throws JAXBException, IOException {
		tcReferenceArray.getTestCategoryReferenceArrayStaticBean().get(0).setValue(null);
		tcReferenceArray.getTestPropertyReferenceArrayStaticBean().get(0).setValue(null);
		
		JsonTestHelper.assertMarshall(jaxbUtility, RESOURCE_NULL_REFERENCE, tcReferenceArray);
	}
	
	@Test
	public void testJsonUnmarshallingNull() throws JAXBException, IOException {
		Unmarshaller jsonUnmarshaller = JsonTestHelper.getUnmarshaller(jaxbUtility, Arrays.asList(
				tcReferenceArray.getATypeInstance(),
				tcAllProperty.getATypeInstance(),
				bpString.getATypeInstance()
		));
		
		StreamSource inputSource = JsonTestHelper.getResourceAsStreamSource(RESOURCE_NULL_REFERENCE);
		
		assertNotNull(tcReferenceArray.getTestCategoryReferenceArrayStaticBean().get(0).getValue());
		assertNotNull(tcReferenceArray.getTestPropertyReferenceArrayStaticBean().get(0).getValue());
		assertNotNull(tcReferenceArray.getTestCategoryReferenceArrayStatic().get(0));
		assertNotNull(tcReferenceArray.getTestPropertyReferenceArrayStatic().get(0));
		
		jsonUnmarshaller.unmarshal(inputSource, TestCategoryReferenceArray.class);
		
		assertNull(tcReferenceArray.getTestCategoryReferenceArrayStaticBean().get(0).getValue());
		assertNull(tcReferenceArray.getTestPropertyReferenceArrayStaticBean().get(0).getValue());
		// Currently this list will return a ca without a type instance
		assertNull(tcReferenceArray.getTestCategoryReferenceArrayStatic().get(0).getTypeInstance());
		assertNull(tcReferenceArray.getTestPropertyReferenceArrayStatic().get(0).getTypeInstance());
	}
}
