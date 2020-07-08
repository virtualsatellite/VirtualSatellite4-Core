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
import org.junit.Ignore;
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
	
	private static String RESOURCE_WITH_DEFAULTS = "/resources/json/TestCategoryAllProperty_Marshaling_Defaults.json";
	private static String RESOURCE_WITH_VALUES = "/resources/json/TestCategoryAllProperty_Marshaling.json";
	
	private static boolean TEST_BOOL = true;
	private static final int TEST_INT = 1;
	private static final double TEST_FLOAT = 0.0;
	private static final String TEST_STRING = "this is a test";
	private static final String TEST_ENUM = "HIGH";
	private static final String TEST_RESOURCE = "resources/file[1].xls";
	private static final String TEST_RESOURCE_STRING = "/" + TEST_RESOURCE;
	
	private static final double EPSILON = 0.000001;
	
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
	
	/**
	 * Set the new values
	 */
	public void initProperties() {
		tcAllProperty.setTestInt(TEST_INT);
		tcAllProperty.setTestFloat(TEST_FLOAT);
		tcAllProperty.setTestEnum(TEST_ENUM);
		tcAllProperty.setTestResource(URI.createPlatformPluginURI(TEST_RESOURCE, false));
		tcAllProperty.setTestString(TEST_STRING);
		tcAllProperty.setTestBool(TEST_BOOL);
	}
	
	/**
	 * Marshall the TestCategoryAllProperty and assert that it equals the resource
	 * @param resource containing the expected JSON
	 * @throws JAXBException
	 * @throws IOException
	 */
	public void assertMarshalWithResource(String resource) throws JAXBException, IOException {
		Marshaller jsonMarshaller = jaxbUtility.getJsonMarshaller();
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(tcAllProperty, sw);

		String expectedJson = TestActivator.getResourceContentAsString(resource);
		assertEqualsNoWs("Json is as expected", expectedJson, sw.toString());
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		initProperties();
		
		assertMarshalWithResource(RESOURCE_WITH_VALUES);
	}
	
	@Test
	public void testJsonMarshallingWithDefaultValues() throws JAXBException, IOException {
		assertMarshalWithResource(RESOURCE_WITH_DEFAULTS);
	}
	
	/**
	 * Unmarshall the resource and assert that the TestCategoryAllProperty got set correctly
	 * @param resource containing the input JSON
	 * @param resource
	 * @throws JAXBException
	 * @throws IOException
	 */
	public void assertUnmarshalWithResource(String resource) throws JAXBException, IOException {
		Marshaller jsonMarshaller = jaxbUtility.getJsonMarshaller();
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(tcAllProperty, sw);

		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().add(tcAllProperty.getATypeInstance());
		
		Unmarshaller jsonUnmarshaller = jaxbUtility.getJsonUnmarshaller(resourceSet);
		
		String inputJson = TestActivator.getResourceContentAsString(resource);
		StringReader sr = new StringReader(inputJson);
		
		JAXBElement<TestCategoryAllProperty> jaxbElement = jsonUnmarshaller.unmarshal(new StreamSource(sr), TestCategoryAllProperty.class);
		TestCategoryAllProperty created = jaxbElement.getValue();
		assertEquals(tcAllProperty, created);
		assertEquals(tcAllProperty.getATypeInstance(), created.getATypeInstance());
		assertEquals(tcAllProperty.getTestBoolBean().getATypeInstance(), created.getTestBoolBean().getATypeInstance());
	}
	
	@Test
	public void testJsonUnmarshalling() throws JAXBException, IOException {
		// Initial default values are set
		assertEqualsDefaultValues();
		
		// Unmarshall the resource containing the new values
		assertUnmarshalWithResource(RESOURCE_WITH_VALUES);
		
		// The values are correctly overwritten
		assertEqualsTestValues();
	}
	
	@Test
	@Ignore
	// TODO: handle default values (null) for this test
	// Empty elements will not appear!
	// This can be fixed with moxy
	// @XmlNullPolicy(emptyNodeRepresentsNull = true, nullRepresentationForXml = XmlMarshalNullRepresentation.EMPTY_NODE)
	// or maybe @XmlElement( nillable = true )
	public void testJsonUnmarshallingWithDefaultValues() throws JAXBException, IOException {
		// Set to the new values
		initProperties();
		assertEqualsTestValues();
		
		// Unmarshall the resource containing the default values
		assertUnmarshalWithResource(RESOURCE_WITH_DEFAULTS);
		
		// The values are correctly overwritten
		assertEqualsDefaultValues();
	}

	/**
	 * Assert that the new values are set correctly
	 */
	private void assertEqualsTestValues() {
		assertEquals(tcAllProperty.getTestInt(), TEST_INT);
		assertEquals(tcAllProperty.getTestString(), TEST_STRING);
		assertEquals(tcAllProperty.getTestResource().toPlatformString(false), TEST_RESOURCE_STRING);
		assertEquals(tcAllProperty.getTestEnum(), TEST_ENUM);
		assertEquals(tcAllProperty.getTestBool(), TEST_BOOL);
		assertEquals(tcAllProperty.getTestFloat(), TEST_FLOAT, EPSILON);
	}
	
	/**
	 * Assert that the default values are set correctly
	 */
	private void assertEqualsDefaultValues() {
		// TODO: int default is Long in the bean and can be null, but is a long in the TestCategoryAllProperty
//		assertEquals(tcAllProperty.getTestInt(), null);
		assertEquals(tcAllProperty.getTestString(), null);
		assertEquals(tcAllProperty.getTestEnum(), null);
		assertEquals(tcAllProperty.getTestBool(), false);
		assertTrue(Double.isNaN(tcAllProperty.getTestFloat()));
	}
}
