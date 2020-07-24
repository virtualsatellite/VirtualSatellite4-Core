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

import javax.json.Json;
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

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryIntrinsicArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReference;
import de.dlr.sc.virsat.model.extension.tests.test.TestActivator;

public class TestCategoryReferenceTest extends AConceptTestCase {

	private TestCategoryReference tcReference;
	private JAXBUtility jaxbUtility;
	private Concept concept;
	private BeanPropertyString bpString;
	private BeanPropertyReference<TestCategoryAllProperty> refCatBean;
	private TestCategoryAllProperty tcAllProperty;
	private BeanPropertyReference<BeanPropertyString> refPropBean;
	
	private static final String RESOURCE = "/resources/json/TestCategoryReference_Marshaling.json";
	
	@Before
	public void setup() throws JAXBException {
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryReference.class});
		
		concept = loadConceptFromPlugin();
		tcReference = new TestCategoryReference(concept);
		tcReference.getATypeInstance().setUuid(new VirSatUuid("0370d14d-e6a1-4660-83f1-5bb98fa840ac"));
		
		bpString = JsonTestHelper.createTestStringBean(concept);
		tcReference.setTestRefProperty(bpString);
		
		refPropBean = tcReference.getTestRefPropertyBean();
		refPropBean.getATypeInstance().setUuid(new VirSatUuid("0dee3e78-fbcd-4294-8dba-5fa3d4760249"));
		
		refCatBean = tcReference.getTestRefCategoryBean();
		refCatBean.getATypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		tcAllProperty = new TestCategoryAllProperty(concept);
		JsonTestHelper.setTestCategoryAllPropertyUuids(tcAllProperty, concept);
		JsonTestHelper.createRepositoryWithUnitManagement(concept);
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		bpString.setValue(JsonTestHelper.TEST_STRING);
		tcReference.setTestRefCategory(tcAllProperty);
		
		Marshaller jsonMarshaller = jaxbUtility.getJsonMarshaller();
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(tcReference, sw);
		
		System.out.println(sw.toString());
		
		String expectedJson = TestActivator.getResourceContentAsString(RESOURCE);
		assertEqualsNoWs("Json is as expected", expectedJson, sw.toString());
	}
	
	@Test
	public void testJsonUnmarshalling() throws JAXBException, IOException {
		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().add(tcReference.getATypeInstance());
		resourceImpl.getContents().add(bpString.getATypeInstance());
		resourceImpl.getContents().add(refCatBean.getATypeInstance());
		resourceImpl.getContents().add(tcAllProperty.getATypeInstance());
		
		Unmarshaller jsonUnmarshaller = jaxbUtility.getJsonUnmarshaller(resourceSet);
		
		String inputJson = TestActivator.getResourceContentAsString(RESOURCE);
		System.out.println(inputJson);
		StringReader sr = new StringReader(inputJson);

		JAXBElement<TestCategoryReference> jaxbElement = jsonUnmarshaller.unmarshal(new StreamSource(sr), TestCategoryReference.class);
		TestCategoryReference createdCategory = jaxbElement.getValue();
		
		assertEquals(JsonTestHelper.TEST_STRING, createdCategory.getTestRefProperty().getValue());
	}
}
