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
	private static String TEST_STRING = "test";
	
	private static final String RESOURCE = "/resources/json/TestCategoryReference_Marshaling.json";
	
	@Before
	public void setup() throws JAXBException {
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryReference.class});
		
		// Load the concept to create the test object
		concept = loadConceptFromPlugin();
		tcReference = new TestCategoryReference(concept);
		tcReference.getATypeInstance().setUuid(new VirSatUuid("0370d14d-e6a1-4660-83f1-5bb98fa840ac"));
		
		bpString = new TestCategoryAllProperty(concept).getTestStringBean();
		bpString.getATypeInstance().setUuid(new VirSatUuid("c258ee29-a453-42fa-b16a-dcc7b73d5e61"));
		tcReference.setTestRefProperty(bpString);
		
		refPropBean = tcReference.getTestRefPropertyBean();
		refPropBean.getATypeInstance().setUuid(new VirSatUuid("0dee3e78-fbcd-4294-8dba-5fa3d4760249"));
		
		refCatBean = tcReference.getTestRefCategoryBean();
		// TODO: will be overwritten with the next get
		refCatBean.getATypeInstance().setUuid(new VirSatUuid("0dee3e79-fbcd-4294-8dba-5fa3d4760249"));
		tcAllProperty = new TestCategoryAllProperty(concept);
		// TODO: helper function?
		// Highly redundant to TestCategoryAllPropertyTest
		tcAllProperty.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		tcAllProperty.getTestBoolBean().getATypeInstance().setUuid(new VirSatUuid("b9bfb08f-2778-4fe9-a774-3d8b0ad638db"));
		tcAllProperty.getTestEnumBean().getATypeInstance().setUuid(new VirSatUuid("ed62d73c-dbba-409c-b73c-f0d3d9f4939d"));
		tcAllProperty.getTestFloatBean().getATypeInstance().setUuid(new VirSatUuid("2870876e-4d6c-4128-801d-54fa109f382d"));
		tcAllProperty.getTestIntBean().getATypeInstance().setUuid(new VirSatUuid("0f37aff6-ccc0-436f-a592-bd466f74bd86"));
		tcAllProperty.getTestResourceBean().getATypeInstance().setUuid(new VirSatUuid("fa822159-51a5-4bf2-99cf-e565b67e0ebd"));
		tcAllProperty.getTestStringBean().getATypeInstance().setUuid(new VirSatUuid("7256e7a2-9a1f-443c-85f8-7b766eac3f50"));
		// Setup a repo for the unit management
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		UnitManagement unitManagement = UnitsFactory.eINSTANCE.createUnitManagement();
		
		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		
		unitManagement.setSystemOfUnit(sou);
		repo.setUnitManagement(unitManagement);
		repo.getActiveConcepts().add(concept);
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		bpString.setValue(TEST_STRING);
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
		
		assertEquals(TEST_STRING, createdCategory.getTestRefProperty().getValue());
	}
}
