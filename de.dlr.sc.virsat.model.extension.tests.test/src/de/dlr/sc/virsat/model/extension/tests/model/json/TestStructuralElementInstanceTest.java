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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanA;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;

public class TestStructuralElementInstanceTest extends AConceptTestCase {

	private JAXBUtility jaxbUtility;
	private Concept concept;
	
	private static final String RESOURCE_DEFAULTS = "/resources/json/TestStructuralElementInstance_Marshaling_Defaults.json";
	private static final String RESOURCE = "/resources/json/TestStructuralElementInstance_Marshaling.json";
	private static final String NAME = "name";
	
	private TestStructuralElement tse;
	private TestStructuralElement tseParent;
	private StructuralElementInstance sei;
	private StructuralElementInstance seiParent;
	
	private TestStructuralElement tseChild1;
	private TestStructuralElement tseChild2;
	
	private TestStructuralElement tseSuperSei1;
	private TestStructuralElement tseSuperSei2;

	private TestCategoryBeanA tcBA;
	private TestCategoryAllProperty tcAP;
	
	@Before
	public void setup() throws JAXBException {
		jaxbUtility = new JAXBUtility(new Class[] {TestStructuralElement.class});
		
		prepareEditingDomain();
		concept = loadConceptFromPlugin();
		
		tse = new TestStructuralElement(concept);
		sei = tse.getStructuralElementInstance();
		tseParent = new TestStructuralElement(concept);
		seiParent = tseParent.getStructuralElementInstance();
		
		tseChild1 = new TestStructuralElement(concept);
		tseChild2 = new TestStructuralElement(concept);
		
		tseSuperSei1 = new TestStructuralElement(concept);
		tseSuperSei2 = new TestStructuralElement(concept);
		
		tcBA = new TestCategoryBeanA(concept);
		tcAP = new TestCategoryAllProperty(concept);
		
		tse.getStructuralElementInstance().setUuid(new VirSatUuid("f2d3ef60-389f-4fec-87a6-bd928da08f2c"));
		tseParent.getStructuralElementInstance().setUuid(new VirSatUuid("f2d3ef60-389f-4fec-87a6-bd928da08f2a"));
		
		tseChild1.getStructuralElementInstance().setUuid(new VirSatUuid("e8be1f69-86b1-45b2-9077-0d9b6b846cac"));
		tseChild2.getStructuralElementInstance().setUuid(new VirSatUuid("c280a34d-c6f7-4444-8a0a-4fad738ffef3"));
		
		tseSuperSei1.getStructuralElementInstance().setUuid(new VirSatUuid("3a9c9f8a-66e0-4f54-9052-f0d54bb18f92"));
		tseSuperSei2.getStructuralElementInstance().setUuid(new VirSatUuid("e04ef537-36fb-4b16-b776-579c7fdacc8b"));
		
		tcBA.getATypeInstance().setUuid(new VirSatUuid("98197a0b-78ec-416c-a685-5d7416808585"));
		tcAP.getATypeInstance().setUuid(new VirSatUuid("19e979e8-944b-4ae5-9906-0d4c5a634b9f"));
	}
	
	/**
	 * Sets the initial values of the tse
	 */
	private void setInitialValues() {
		tse.setName(NAME);
		sei.setParent(seiParent);
		
		tse.add(tcBA);
		tse.add(tcAP);
		
		tse.add(tseChild1);
		tse.add(tseChild2);
		
		tse.addSuperSei(tseSuperSei1);
		tse.addSuperSei(tseSuperSei2);
	}
	
	@Test
	public void testJsonMarshallingDefaults() throws JAXBException, IOException {
		JsonTestHelper.assertMarshall(jaxbUtility, RESOURCE_DEFAULTS, tse);
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		setInitialValues();
		
		JsonTestHelper.assertMarshall(jaxbUtility, RESOURCE, tse);
	}
	
	@Test
	public void testJsonUnmarshalling() throws JAXBException, IOException {
		Unmarshaller jsonUnmarshaller = JsonTestHelper.getUnmarshaller(jaxbUtility, Arrays.asList(
				sei,
				seiParent,
				tseChild1.getStructuralElementInstance(),
				tseChild2.getStructuralElementInstance(),
				tseSuperSei1.getStructuralElementInstance(),
				tseSuperSei2.getStructuralElementInstance(),
				tcBA.getATypeInstance(),
				tcAP.getATypeInstance()
		));
		
		assertNull("Initial no name", sei.getName());
		assertNull("Initial no parent", sei.getParent());
		assertTrue("Initial no cas", sei.getCategoryAssignments().isEmpty());
		assertTrue("Initial no children", sei.getChildren().isEmpty());
		assertTrue("Initial no superSeis", sei.getSuperSeis().isEmpty());
		
		StreamSource inputSource = JsonTestHelper.getResourceAsStreamSource(RESOURCE);
		jsonUnmarshaller.unmarshal(inputSource, TestStructuralElement.class);

		assertEquals("Name set correctly", NAME, sei.getName());
		assertEquals("Parent set correctly", seiParent, sei.getParent());
		
		assertEquals("Cas got added correctly", 2, sei.getCategoryAssignments().size());
		assertTrue(sei.getCategoryAssignments().contains(tcBA.getATypeInstance()));
		assertTrue(sei.getCategoryAssignments().contains(tcAP.getATypeInstance()));
		
		assertEquals("Children got added correctly", 2, sei.getChildren().size());
		assertTrue(sei.getChildren().contains(tseChild1.getStructuralElementInstance()));
		assertTrue(sei.getChildren().contains(tseChild1.getStructuralElementInstance()));
		
		assertEquals("SuperSeis got added correctly", 2, sei.getSuperSeis().size());
		assertTrue(sei.getSuperSeis().contains(tseSuperSei1.getStructuralElementInstance()));
		assertTrue(sei.getSuperSeis().contains(tseSuperSei2.getStructuralElementInstance()));
		
		// Check that unmarshalling again is idempotent
		inputSource = JsonTestHelper.getResourceAsStreamSource(RESOURCE);
		jsonUnmarshaller.unmarshal(inputSource, TestStructuralElement.class);
		
		assertEquals("Name set correctly", NAME, sei.getName());
		assertEquals("Parent set correctly", seiParent, sei.getParent());
		assertEquals("Cas got added correctly", 2, sei.getCategoryAssignments().size());
		assertEquals("Children got added correctly", 2, sei.getChildren().size());
		assertEquals("SuperSeis got added correctly", 2, sei.getSuperSeis().size());
	}
	
}
