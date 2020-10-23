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
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.test.TestActivator;

/**
 * Class containing static helper functions 
 * to remove redundancy in JSON test cases
 */
public class JsonTestHelper {
	
	public static final String TEST_STRING = "testString";
	
	private JsonTestHelper() { }
	
	/**
	 * Set all UUIDs of a tcAllProperty
	 * @param tcAllProperty to be set
	 */
	public static void setTestCategoryAllPropertyUuids(TestCategoryAllProperty tcAllProperty) {
		setTestCategoryAllPropertyUuids(tcAllProperty, 0);
	}
	
	/**
	 * Set all UUIDs of a tcAllProperty
	 * The last character can be edited
	 * @param tcAllProperty to be set
	 * @param lastChar of the UUID
	 */
	public static void setTestCategoryAllPropertyUuids(TestCategoryAllProperty tcAllProperty, int lastChar) {
		tcAllProperty.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f" + lastChar));
		tcAllProperty.getTestBoolBean().getATypeInstance().setUuid(new VirSatUuid("b9bfb08f-2778-4fe9-a774-3d8b0ad638d" + lastChar));
		tcAllProperty.getTestEnumBean().getATypeInstance().setUuid(new VirSatUuid("ed62d73c-dbba-409c-b73c-f0d3d9f4939" + lastChar));
		tcAllProperty.getTestFloatBean().getATypeInstance().setUuid(new VirSatUuid("2870876e-4d6c-4128-801d-54fa109f382" + lastChar));
		tcAllProperty.getTestIntBean().getATypeInstance().setUuid(new VirSatUuid("0f37aff6-ccc0-436f-a592-bd466f74bd8" + lastChar));
		tcAllProperty.getTestResourceBean().getATypeInstance().setUuid(new VirSatUuid("fa822159-51a5-4bf2-99cf-e565b67e0eb" + lastChar));
		tcAllProperty.getTestStringBean().getATypeInstance().setUuid(new VirSatUuid("26edbae8-9f05-4ef5-8673-91e1af668aa" + lastChar));
	}
	
	/**
	 * Create a Repository with a UnitManagement from the Concept
	 * @param concept
	 * @return Repository
	 */
	public static Repository createRepositoryWithUnitManagement(Concept concept) {
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		UnitManagement unitManagement = UnitsFactory.eINSTANCE.createUnitManagement();
		
		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		
		unitManagement.setSystemOfUnit(sou);
		repo.setUnitManagement(unitManagement);
		repo.getActiveConcepts().add(concept);
		
		return repo;
	}
	
	public static BeanPropertyString createTestStringBean(Concept concept) {
		BeanPropertyString bpString = new TestCategoryAllProperty(concept).getTestStringBean();
		bpString.getATypeInstance().setUuid(new VirSatUuid("7256e7a2-9a1f-443c-85f8-7b766eac3f50"));
		return bpString;
	}
	
	// CHECKSTYLE:OFF
	public static void setStaticIBeanListUuids(IBeanList<? extends IBeanObject<? extends ATypeInstance>> list) {
		list.get(0).getATypeInstance().setUuid(new VirSatUuid("4efe0002-f081-49c0-9917-6f4a6e7dd9ce"));
		list.get(1).getATypeInstance().setUuid(new VirSatUuid("6ad3d35a-a0b4-48e8-9bfd-e6edf438eee5"));
		list.get(2).getATypeInstance().setUuid(new VirSatUuid("8fd96e3b-5bf3-41e1-a02a-64f8bff99107"));
		list.get(3).getATypeInstance().setUuid(new VirSatUuid("c38d7185-fcc3-480c-bfb4-28e6fcc09d34"));
		list.getArrayInstance().setUuid(new VirSatUuid("98218bbf-a5ee-432d-b01c-da48f4f9495b"));
	}
	// CHECKSTYLE:ON
	
	/**
	 * Marshalls the test object and asserts that the result equals the test resource
	 * @param jaxbUtility the JAXBUtility with the needed classes registered
	 * @param resource the test resource
	 * @param testObject the object to assert with the resource
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static void assertMarshall(JAXBUtility jaxbUtility, String resource, Object testObject) throws JAXBException, IOException {
		Marshaller jsonMarshaller = jaxbUtility.getJsonMarshaller();
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(testObject, sw);
		
		String expectedJson = TestActivator.getResourceContentAsString(resource);
		assertEqualsNoWs("Json is as expected", expectedJson, sw.toString());
	}
	
	/**
	 * Embeds the modelObjects into an ResourceSet to then create the Unmarshaller with the JAXBUtility
	 * @param jaxbUtility the JAXBUtility with the needed classes registered
	 * @param modelObjects the objects to be embedded into the ResourceSet
	 * @return the Unmarshaller
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static Unmarshaller getUnmarshaller(JAXBUtility jaxbUtility, Collection<? extends EObject> modelObjects) throws JAXBException, IOException {
		// Quick mock setup to embed the model into a resource set
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resourceImpl = new ResourceImpl();
		resourceSet.getResources().add(resourceImpl);
		resourceImpl.getContents().addAll(modelObjects);
		
		return jaxbUtility.getJsonUnmarshaller(resourceSet);
	}
	
	public static StreamSource getResourceAsStreamSource(String resource) throws IOException {
		String inputJson = TestActivator.getResourceContentAsString(resource);
		return new StreamSource(new StringReader(inputJson));
	}
}
