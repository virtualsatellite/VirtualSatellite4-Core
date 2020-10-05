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

import java.io.IOException;
import java.util.Arrays;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanA;

public class TestCategoryBeanATest extends AConceptTestCase {

	private static final String UUID = "f34d30b0-80f5-4c96-864f-29ab4d3ae9f2";
	private static final String RESOURCE = "/resources/json/TestCategoryBeanA_Marshaling.json";
	private JAXBUtility jaxbUtility;
	private TestCategoryBeanA tcBeanA;
	private Concept concept;
	
	@Before
	public void setUp() throws Exception {
		concept = loadConceptFromPlugin();
		
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryBeanA.class});
		
		tcBeanA = new TestCategoryBeanA(concept);
		tcBeanA.getTypeInstance().setUuid(new VirSatUuid(UUID));
	}

	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		JsonTestHelper.assertMarshall(jaxbUtility, RESOURCE, tcBeanA);
	}
	
	@Test
	public void testJsonUnMarshalling() throws JAXBException, IOException {
		Unmarshaller jsonUnmarshaller = JsonTestHelper.getUnmarshaller(jaxbUtility, Arrays.asList(
				tcBeanA.getATypeInstance()
		));
		
		StreamSource inputSource = JsonTestHelper.getResourceAsStreamSource(RESOURCE);
		
		TestCategoryBeanA createdBeanA = jsonUnmarshaller.unmarshal(inputSource, TestCategoryBeanA.class).getValue();
		assertEquals(tcBeanA, createdBeanA);
		
		// Unmarshall again to test idempotency
		inputSource = JsonTestHelper.getResourceAsStreamSource(RESOURCE);
		createdBeanA = jsonUnmarshaller.unmarshal(inputSource, TestCategoryBeanA.class).getValue();
		assertEquals(tcBeanA, createdBeanA);
	}
	
}
