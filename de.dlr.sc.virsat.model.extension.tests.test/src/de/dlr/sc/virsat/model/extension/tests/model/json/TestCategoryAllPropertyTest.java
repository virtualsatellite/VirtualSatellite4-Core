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
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.emf.common.util.URI;
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
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.test.TestActivator;

public class TestCategoryAllPropertyTest extends AConceptTestCase {

	// TODO: add to test suite
	private TestCategoryAllProperty tcAllProperty;
	private JAXBUtility jaxbUtility;
	private Concept concept;
	
	// TODO: order
	private static final int TEST_INT = 1;
	private static final double TEST_FLOAT = 0.0;
	private static final String TEST_STRING = "this is a test";
	private static final String TEST_ENUM = "HIGH";
	private static final double EPSILON = 0.000001;
	private static final String TEST_RESOURCE = "resources/file[1].xls";
	private static final String TEST_RESOURCE_STRING = "/" + TEST_RESOURCE;
	
	@Before
	public void setup() throws JAXBException {
		prepareEditingDomain();

		concept = loadConceptFromPlugin();
		tcAllProperty = new TestCategoryAllProperty(concept);
		
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryAllProperty.class});
		
		// Set uuids to match the test resource
		tcAllProperty.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		tcAllProperty.getTestBoolBean().getATypeInstance().setUuid(new VirSatUuid("b9bfb08f-2778-4fe9-a774-3d8b0ad638db"));
		tcAllProperty.getTestEnumBean().getATypeInstance().setUuid(new VirSatUuid("ed62d73c-dbba-409c-b73c-f0d3d9f4939d"));
		tcAllProperty.getTestFloatBean().getATypeInstance().setUuid(new VirSatUuid("2870876e-4d6c-4128-801d-54fa109f382d"));
		tcAllProperty.getTestIntBean().getATypeInstance().setUuid(new VirSatUuid("0f37aff6-ccc0-436f-a592-bd466f74bd86"));
		tcAllProperty.getTestResourceBean().getATypeInstance().setUuid(new VirSatUuid("fa822159-51a5-4bf2-99cf-e565b67e0ebd"));
		tcAllProperty.getTestStringBean().getATypeInstance().setUuid(new VirSatUuid("7256e7a2-9a1f-443c-85f8-7b766eac3f50"));
	}
	
	// TODO: test with default values
	public void initProperties() {
		// TODO: investigate error if no int is set -> is this a possible state?
		tcAllProperty.setTestInt(TEST_INT);
		tcAllProperty.setTestFloat(TEST_FLOAT);
		
		// Empty elements will not appear!
		// This can be fixed with moxy
		// @XmlNullPolicy(emptyNodeRepresentsNull = true, nullRepresentationForXml = XmlMarshalNullRepresentation.EMPTY_NODE)
		tcAllProperty.setTestEnum(TEST_ENUM);
		tcAllProperty.setTestResource(URI.createPlatformPluginURI(TEST_RESOURCE, false));
		tcAllProperty.setTestString(TEST_STRING);
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		initProperties();
		
		Marshaller jsonMarshaller = jaxbUtility.getJsonMarshaller();
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(tcAllProperty, sw);

		System.out.println(sw.toString());

		String expectedJson = TestActivator.getResourceContentAsString("/resources/json/TestCategoryAllProperty_Marshaling.json");
		assertEqualsNoWs("Json is as expected", expectedJson, sw.toString());
	}
	
	@Test
	public void testJsonUnmarshalling() throws JAXBException, IOException {
		Marshaller jsonMarshaller = jaxbUtility.getJsonMarshaller();
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(tcAllProperty, sw);

		System.out.println(sw.toString());
		
		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().add(tcAllProperty.getATypeInstance());
		
		Unmarshaller jsonUnmarshaller = jaxbUtility.getJsonUnmarshaller(resourceSet);
		
		String inputJson = TestActivator.getResourceContentAsString("/resources/json/TestCategoryAllProperty_Marshaling.json");
		StringReader sr = new StringReader(inputJson);
		
		// no init -> still default values
		assertEqualsDefaultValues(tcAllProperty);
		
		JAXBElement<TestCategoryAllProperty> test = jsonUnmarshaller.unmarshal(new StreamSource(sr), TestCategoryAllProperty.class);
		TestCategoryAllProperty created = test.getValue();
		assertEquals(tcAllProperty, created);
		assertEquals(tcAllProperty.getATypeInstance(), created.getATypeInstance());
		assertEquals(tcAllProperty.getTestBoolBean().getATypeInstance(), created.getTestBoolBean().getATypeInstance());
		// values properly overwritten
		assertEqualsTestValues(tcAllProperty);
	}
	
	private void assertEqualsTestValues(TestCategoryAllProperty testCategory) {
		assertEquals(testCategory.getTestInt(), TEST_INT);
		assertEquals(testCategory.getTestString(), TEST_STRING);
		assertEquals(testCategory.getTestResource().toPlatformString(false), TEST_RESOURCE_STRING);
		assertEquals(testCategory.getTestEnum(), TEST_ENUM);
		assertEquals(testCategory.getTestBool(), false);
		assertEquals(testCategory.getTestFloat(), TEST_FLOAT, EPSILON);
	}
	
	private void assertEqualsDefaultValues(TestCategoryAllProperty testCategory) {
		// TODO: int default broken?
//		assertEquals(testCategory.getTestInt(), 0);
		assertEquals(testCategory.getTestString(), null);
		assertEquals(testCategory.getTestEnum(), null);
		assertEquals(testCategory.getTestBool(), false);
		assertTrue(Double.isNaN(testCategory.getTestFloat()));
	}
}
