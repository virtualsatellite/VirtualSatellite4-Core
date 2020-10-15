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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
public class TestCategoryAllPropertyTest extends AConceptTestCase {

	private TestCategoryAllProperty tcAllProperty;
	private JAXBUtility jaxbUtility;
	private Concept concept;
	
	private static final String RESOURCE_WITH_DEFAULTS = "/resources/json/TestCategoryAllProperty_Marshaling_Defaults.json";
	private static final String RESOURCE_WITH_VALUES = "/resources/json/TestCategoryAllProperty_Marshaling.json";
	
	private static final boolean TEST_BOOL = true;
	private static final int TEST_INT = 1;
	private static final double TEST_FLOAT = 0.0;
	private static final String TEST_STRING = "this is a test";
	private static final String TEST_ENUM = "HIGH";
	private static final String TEST_RESOURCE = "resources/file[1].xls";
	private static final String TEST_RESOURCE_STRING = "/" + TEST_RESOURCE;
	
	private static final double EPSILON = 0.000001;
	
	@Before
	public void setup() throws JAXBException {
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryAllProperty.class});
		
		prepareEditingDomain();
		concept = loadConceptFromPlugin();
		
		tcAllProperty  = new TestCategoryAllProperty(concept);
		JsonTestHelper.setTestCategoryAllPropertyUuids(tcAllProperty);
		JsonTestHelper.createRepositoryWithUnitManagement(concept);
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
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		initProperties();
		
		JsonTestHelper.assertMarshall(jaxbUtility, RESOURCE_WITH_VALUES, tcAllProperty);
	}
	
	@Test
	public void testJsonMarshallingWithDefaultValues() throws JAXBException, IOException {
		JsonTestHelper.assertMarshall(jaxbUtility, RESOURCE_WITH_DEFAULTS, tcAllProperty);
	}
	
	/**
	 * Unmarshall the resource
	 * @param resource containing the input JSON
	 * @throws JAXBException
	 * @throws IOException
	 */
	public void unmarshalWithResource(String resource) throws JAXBException, IOException {
		Unmarshaller jsonUnmarshaller = JsonTestHelper.getUnmarshaller(jaxbUtility, Arrays.asList(
				tcAllProperty.getATypeInstance()
		));
		
		StreamSource inputSource = JsonTestHelper.getResourceAsStreamSource(resource);
		
		jsonUnmarshaller.unmarshal(inputSource, TestCategoryAllProperty.class);
	}
	
	@Test
	public void testJsonUnmarshalling() throws JAXBException, IOException {
		// Initial default values are set
		assertEqualsDefaultValues();
		
		// Unmarshall the resource containing the new values
		unmarshalWithResource(RESOURCE_WITH_VALUES);
		
		// The values are correctly overwritten
		assertEqualsTestValues();
		
		// Unmarshall again to test idempotency
		unmarshalWithResource(RESOURCE_WITH_VALUES);
		assertEqualsTestValues();
	}
	
	@Test
	public void testJsonUnmarshallingWithDefaultValues() throws JAXBException, IOException {
		// Set to the new values
		initProperties();
		assertEqualsTestValues();
		
		// Unmarshall the resource containing the default values
		unmarshalWithResource(RESOURCE_WITH_DEFAULTS);
		
		// The values are correctly overwritten
		assertEqualsDefaultValues();
	}

	/**
	 * Assert that the new values are set correctly
	 */
	private void assertEqualsTestValues() {
		assertEquals(TEST_INT, tcAllProperty.getTestInt());
		assertEquals(TEST_STRING, tcAllProperty.getTestString());
		assertEquals(TEST_RESOURCE_STRING, tcAllProperty.getTestResource().toPlatformString(false));
		assertEquals(TEST_ENUM, tcAllProperty.getTestEnum());
		assertEquals(TEST_BOOL, tcAllProperty.getTestBool());
		assertEquals(TEST_FLOAT, tcAllProperty.getTestFloat(), EPSILON);
	}
	
	/**
	 * Assert that the default values are set correctly
	 */
	private void assertEqualsDefaultValues() {
		assertNull(tcAllProperty.getTestIntBean().getTypeInstance().getValue());
		assertNull(tcAllProperty.getTestString());
		assertNull(tcAllProperty.getTestEnum());
		assertNull(tcAllProperty.getTestResource());
		assertFalse(tcAllProperty.getTestBool());
		assertTrue(Double.isNaN(tcAllProperty.getTestFloat()));
	}
}
