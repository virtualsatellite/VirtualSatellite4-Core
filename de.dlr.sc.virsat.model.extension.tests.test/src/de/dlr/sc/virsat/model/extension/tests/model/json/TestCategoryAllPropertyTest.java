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

import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.test.TestActivator;

public class TestCategoryAllPropertyTest extends AConceptTestCase {

	private TestCategoryAllProperty tcAllProperty;
	private JAXBUtility jaxbUtility;
	private Concept concept;
	
	@Before
	public void setup() throws JAXBException {
		prepareEditingDomain();

		concept = loadConceptFromPlugin();
		tcAllProperty = new TestCategoryAllProperty(concept);
		
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryAllProperty.class});
		
		// Set uuids for the beans
		tcAllProperty.getTestBoolBean().getATypeInstance().setUuid(new VirSatUuid("b9bfb08f-2778-4fe9-a774-3d8b0ad638db"));
		tcAllProperty.getTestEnumBean().getATypeInstance().setUuid(new VirSatUuid("ed62d73c-dbba-409c-b73c-f0d3d9f4939d"));
		tcAllProperty.getTestFloatBean().getATypeInstance().setUuid(new VirSatUuid("2870876e-4d6c-4128-801d-54fa109f382d"));
		tcAllProperty.getTestIntBean().getATypeInstance().setUuid(new VirSatUuid("0f37aff6-ccc0-436f-a592-bd466f74bd86"));
		tcAllProperty.getTestResourceBean().getATypeInstance().setUuid(new VirSatUuid("fa822159-51a5-4bf2-99cf-e565b67e0ebd"));
		tcAllProperty.getTestStringBean().getATypeInstance().setUuid(new VirSatUuid("7256e7a2-9a1f-443c-85f8-7b766eac3f50"));
		
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		
		Marshaller jsonMarshaller = jaxbUtility.getJsonMarshaller();
		
		tcAllProperty.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		
		// TODO: investigate error if no int is set -> is this a possible state?
		tcAllProperty.setTestInt(1);
		
		// Empty elements will not appear!
		// This can be fixed with moxy
		// @XmlNullPolicy(emptyNodeRepresentsNull = true, nullRepresentationForXml = XmlMarshalNullRepresentation.EMPTY_NODE)
		tcAllProperty.setTestEnum("HIGH");
		tcAllProperty.setTestResource(URI.createPlatformPluginURI("Testresource", true));
		tcAllProperty.setTestString("this is a test");
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(tcAllProperty, sw);

		System.out.println(sw.toString());

		String expectedJson = TestActivator.getResourceContentAsString("/resources/json/TestCategoryAllProperty_Marshaling.json");
		assertEqualsNoWs("Json is as expected", expectedJson, sw.toString());
	}
}
