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

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.PropertyinstancesFactoryImpl;
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
	private static final String RESOURCE = "/resources/json/TestCategoryReferenceArray_Marshaling.json";
	private static final String TEST_STRING = "test";

	@Before
	public void setup() throws JAXBException {
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryReferenceArray.class});

		// Load the concept to create the test object
		prepareEditingDomain();
		Concept concept = loadConceptFromPlugin();
		testArray = new TestCategoryReferenceArray(concept);
		
		TestCategoryAllProperty tcAllProperty = new TestCategoryAllProperty(concept);
		
		// Set uuids to match the test resource
		tcAllProperty.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		tcAllProperty.getTestBoolBean().getATypeInstance().setUuid(new VirSatUuid("b9bfb08f-2778-4fe9-a774-3d8b0ad638db"));
		tcAllProperty.getTestEnumBean().getATypeInstance().setUuid(new VirSatUuid("ed62d73c-dbba-409c-b73c-f0d3d9f4939d"));
		tcAllProperty.getTestFloatBean().getATypeInstance().setUuid(new VirSatUuid("2870876e-4d6c-4128-801d-54fa109f382d"));
		tcAllProperty.getTestIntBean().getATypeInstance().setUuid(new VirSatUuid("0f37aff6-ccc0-436f-a592-bd466f74bd86"));
		tcAllProperty.getTestResourceBean().getATypeInstance().setUuid(new VirSatUuid("fa822159-51a5-4bf2-99cf-e565b67e0ebd"));
		tcAllProperty.getTestStringBean().getATypeInstance().setUuid(new VirSatUuid("7256e7a2-9a1f-443c-85f8-7b766eac3f50"));
		
		testArray.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		testArray.getTestCategoryReferenceArrayDynamic().getArrayInstance().setUuid(new VirSatUuid("ee6e1025-4a77-4b32-9c62-cb459ed76ce8"));		
		testArray.getTestCategoryReferenceArrayStaticBean().get(0).setValue(tcAllProperty);
		testArray.getTestCategoryReferenceArrayStaticBean().get(1).setValue(tcAllProperty);
		testArray.getTestCategoryReferenceArrayStaticBean().get(2).setValue(tcAllProperty);
		testArray.getTestCategoryReferenceArrayStaticBean().get(3).setValue(tcAllProperty);
		testArray.getTestCategoryReferenceArrayStatic().getArrayInstance().setUuid(new VirSatUuid("98218bbf-a5ee-432d-b01c-da48f4f9495b"));
		testArray.getTestPropertyReferenceArrayDynamic().getArrayInstance().setUuid(new VirSatUuid("ee6e1025-4a77-4b32-9c62-cb459ed76ce1"));		
		testArray.getTestPropertyReferenceArrayStaticBean().get(0).setValue(createVpiWithUuid("d7ed5b62-f096-4347-88ef-eae61c5f1665"));
		testArray.getTestPropertyReferenceArrayStaticBean().get(1).setValue(createVpiWithUuid("d7ed5b62-f096-4347-88ef-eae61c5f1666"));
		testArray.getTestPropertyReferenceArrayStaticBean().get(2).setValue(createVpiWithUuid("d7ed5b62-f096-4347-88ef-eae61c5f1667"));
		testArray.getTestPropertyReferenceArrayStaticBean().get(3).setValue(createVpiWithUuid("d7ed5b62-f096-4347-88ef-eae61c5f1668"));
		testArray.getTestPropertyReferenceArrayStatic().getArrayInstance().setUuid(new VirSatUuid("98218bbf-a5ee-432d-b01c-da48f4f94951"));
		
		testArray.getTestPropertyReferenceArrayStatic();
		// TODO: no typeinstance for the bean initially???
		// results in error when marshalling
		// TODO: can't set typeinstance on bean directly???????????????????????
	}
	
	private BeanPropertyString createVpiWithUuid(String uuid) {
		ValuePropertyInstance vpi = PropertyinstancesFactoryImpl.eINSTANCE.createValuePropertyInstance();
		vpi.setValue(TEST_STRING);
		vpi.setUuid(new VirSatUuid(uuid));
		return new BeanPropertyString(vpi);
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
	
	//TODO: unmarshalling
}
