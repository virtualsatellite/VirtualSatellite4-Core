package de.dlr.sc.virsat.model.extension.tests.model.json;

import static de.dlr.sc.virsat.model.extension.tests.test.TestActivator.assertEqualsNoWs;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryCompositionArray;
import de.dlr.sc.virsat.model.extension.tests.test.TestActivator;

public class TestCategoryCompositionArrayTest extends AConceptTestCase {
	
	private TestCategoryCompositionArray testArray;
	private JAXBUtility jaxbUtility;
	private static final String RESOURCE = "/resources/json/TestCategoryCompositionArray_Marshaling.json";

	@Before
	public void setup() throws JAXBException {
		jaxbUtility = new JAXBUtility(new Class[] {TestCategoryCompositionArray.class});
		
		// Load the concept to create the test object
		prepareEditingDomain();
		Concept concept = loadConceptFromPlugin();
		testArray = new TestCategoryCompositionArray(concept);
		testArray.getTypeInstance().setUuid(new VirSatUuid("f34d30b0-80f5-4c96-864f-29ab4d3ae9f2"));
		testArray.getTestCompositionArrayDynamic().getArrayInstance().setUuid(new VirSatUuid("ee6e1025-4a77-4b32-9c62-cb459ed76ce8"));		
		testArray.getTestCompositionArrayStatic().get(0).getATypeInstance().setUuid(new VirSatUuid("4efe0002-f081-49c0-9917-6f4a6e7dd9ce"));
		testArray.getTestCompositionArrayStatic().get(1).getATypeInstance().setUuid(new VirSatUuid("6ad3d35a-a0b4-48e8-9bfd-e6edf438eee5"));
		testArray.getTestCompositionArrayStatic().get(2).getATypeInstance().setUuid(new VirSatUuid("8fd96e3b-5bf3-41e1-a02a-64f8bff99107"));
		testArray.getTestCompositionArrayStatic().get(3).getATypeInstance().setUuid(new VirSatUuid("c38d7185-fcc3-480c-bfb4-28e6fcc09d34"));
		testArray.getTestCompositionArrayStatic().getArrayInstance().setUuid(new VirSatUuid("98218bbf-a5ee-432d-b01c-da48f4f9495b"));
	
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		
		Marshaller jsonMarshaller = jaxbUtility.getJsonMarshaller();
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(testArray, sw);
		
		System.out.println(sw.toString());
		
		String expectedJson = TestActivator.getResourceContentAsString(RESOURCE);
		assertEqualsNoWs("Json is as expected", expectedJson, sw.toString());
	}
}
