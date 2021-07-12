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
import java.util.Arrays;
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
import de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor;
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
		sou.getSystemOfQuantities().get(0).setUuid(new VirSatUuid("ac2cd914-3f98-4272-9537-443c1eb0c1b6"));
		
		if (concept != null) {
			repo.getActiveConcepts().add(concept);
		}
		
		// Set uuids for units used in the test cases
		SimpleUnit mUnit = (SimpleUnit) QudvUnitHelper.getInstance().getUnitByName(sou, "Meter");
		mUnit.setUuid(new VirSatUuid("efb2d32a-42c4-4f33-820c-88a1e61ccfd0"));
		mUnit.getQuantityKind().setUuid(new VirSatUuid("582b2e01-712d-4557-bc25-3158cd4ff316"));
		QudvUnitHelper.getInstance().getUnitByName(sou, "Second").setUuid(new VirSatUuid("408835d3-11b5-474c-a97c-ee9b4ff53a89"));
		
		PrefixedUnit kmUnit = (PrefixedUnit) QudvUnitHelper.getInstance().getUnitByName(sou, "Kilometer");
		kmUnit.setUuid(new VirSatUuid("e2203b42-e703-4429-9bf9-dbd4882c341c"));
		kmUnit.getPrefix().setUuid(new VirSatUuid("ca6c002a-b269-41b1-9afe-b805a30f8c29"));
		kmUnit.getQuantityKind().setUuid(new VirSatUuid("882b2e01-712d-4557-bc25-3158cd4ff316"));
		
		AffineConversionUnit minUnit = (AffineConversionUnit) QudvUnitHelper.getInstance().getUnitByName(sou, "Minute");
		minUnit.setUuid(new VirSatUuid("51e5304f-1645-420a-b949-c72b8c1befcd"));
		minUnit.getQuantityKind().setUuid(new VirSatUuid("b4b5ca4f-cc40-4e66-af06-583d982781e1"));
		LinearConversionUnit bUnit = (LinearConversionUnit) QudvUnitHelper.getInstance().getUnitByName(sou, "Byte");
		bUnit.setUuid(new VirSatUuid("c54347d1-80b2-4db4-9d3d-e10d3b50be08"));
		bUnit.getQuantityKind().setUuid(new VirSatUuid("cf277f96-e167-45a1-85f7-0470e9c85ecb"));
		
		DerivedUnit mpsUnit = (DerivedUnit) QudvUnitHelper.getInstance().getUnitByName(sou, "Meter Per Second");
		mpsUnit.setUuid(new VirSatUuid("ee1d23de-786f-4eb6-ae34-c864fb72ea1a"));
		
		// Because of the underlying hash map when initializing the sou, the ordering is not consistent
		// so we have to manually order the two elements to ensure test consistency
		UnitFactor unitFactor = mpsUnit.getFactor().get(0);
		if (unitFactor.getUnit().equals(mUnit)) {
			mpsUnit.getFactor().remove(0);
			mpsUnit.getFactor().add(unitFactor);
		}
		mpsUnit.getFactor().get(0).setUuid(new VirSatUuid("d4cf8e6e-bc81-425f-af9a-a3458f7f3bd4"));
		mpsUnit.getFactor().get(1).setUuid(new VirSatUuid("05c8c9d3-9bf0-43cb-8372-e71ff8d54840"));
		
		DerivedQuantityKind vQuantityKind = (DerivedQuantityKind) mpsUnit.getQuantityKind();
		vQuantityKind.setUuid(new VirSatUuid("ad796ad1-bc2f-4ff0-932f-8bf70ad0d9c1"));
		
		// Also order the qk factors
		QuantityKindFactor qkFactor = vQuantityKind.getFactor().get(0);
		if (qkFactor.getQuantityKind().equals(mUnit.getQuantityKind())) {
			vQuantityKind.getFactor().remove(0);
			vQuantityKind.getFactor().add(qkFactor);
		}
		vQuantityKind.getFactor().get(0).setUuid(new VirSatUuid("92fd020c-8b12-4d8a-9711-8a8976cfc39b"));
		vQuantityKind.getFactor().get(1).setUuid(new VirSatUuid("e7559cc7-42b9-41c6-b57d-8b31e761f293"));
		
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
	
	public static Unmarshaller getUnmarshaller(JAXBUtility jaxbUtility, EObject modelObject) throws JAXBException, IOException {
		return getUnmarshaller(jaxbUtility, Arrays.asList(modelObject));
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
