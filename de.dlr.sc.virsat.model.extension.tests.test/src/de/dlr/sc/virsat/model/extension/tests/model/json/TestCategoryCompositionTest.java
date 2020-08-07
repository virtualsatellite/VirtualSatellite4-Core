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
import static org.junit.Assert.assertNull;

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

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryComposition;
import de.dlr.sc.virsat.model.extension.tests.test.TestActivator;

public class TestCategoryCompositionTest extends AConceptTestCase {

	private JAXBUtility jaxbUtility;
	private TestCategoryComposition tcComposition;
	private TestCategoryAllProperty tcAllProperty;
	private Concept concept;
	
	private static final String RESOURCE = "/resources/json/TestCategoryComposition_Marshaling.json";
	
	@Before
	public void setup() throws JAXBException {
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryComposition.class});
		
		concept = loadConceptFromPlugin();
		
		tcComposition = new TestCategoryComposition(concept);
		tcComposition.getATypeInstance().setUuid(new VirSatUuid("028a6e1b-e7c4-4937-886b-d65452426bfd"));
		tcComposition.getTestSubCategoryBean().getATypeInstance().setUuid(new VirSatUuid("128a6e1b-e7c4-4937-886b-d65452426bfd"));
		
		tcAllProperty = tcComposition.getTestSubCategory();
		JsonTestHelper.setTestCategoryAllPropertyUuids(tcAllProperty);
		JsonTestHelper.createRepositoryWithUnitManagement(concept);
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		tcAllProperty.setTestString(JsonTestHelper.TEST_STRING);
		
		Marshaller jsonMarshaller = jaxbUtility.getJsonMarshaller();
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(tcComposition, sw);
		
		String expectedJson = TestActivator.getResourceContentAsString(RESOURCE);
		assertEqualsNoWs("Json is as expected", expectedJson, sw.toString());
	}
	
	@Test
	public void testJsonUnmarshalling() throws JAXBException, IOException {
		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().add(tcComposition.getATypeInstance());
		resourceImpl.getContents().add(tcAllProperty.getATypeInstance());
		
		Unmarshaller jsonUnmarshaller = jaxbUtility.getJsonUnmarshaller(resourceSet);
		
		String inputJson = TestActivator.getResourceContentAsString(RESOURCE);
		StringReader sr = new StringReader(inputJson);

		assertNull(tcAllProperty.getTestString());
		
		jsonUnmarshaller.unmarshal(new StreamSource(sr), TestCategoryComposition.class);
		
		assertEquals("Composed element changed", JsonTestHelper.TEST_STRING, tcComposition.getTestSubCategory().getTestString());
	}
}
