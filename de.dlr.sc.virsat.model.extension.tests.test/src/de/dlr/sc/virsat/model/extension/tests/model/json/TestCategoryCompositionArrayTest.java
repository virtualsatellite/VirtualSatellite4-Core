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

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.JAXBUtility;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
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
		
		testArray.getATypeInstance().setUuid(new VirSatUuid("f5d016ac-65fa-4b9d-ae94-582d4f73138a"));
		IBeanList<TestCategoryAllProperty> staticArray = testArray.getTestCompositionArrayStatic();
		TestCategoryAllProperty tcAllProperty;
		for (int i = 0; i < staticArray.size(); i++) {
			tcAllProperty = staticArray.get(i);
			JsonTestHelper.setTestCategoryAllPropertyUuids(tcAllProperty, concept, i);
		}
		
		JsonTestHelper.createRepositoryWithUnitManagement(concept);	
	}
	
	@Test
	public void testJsonMarshalling() throws JAXBException, IOException {
		testArray.getTestCompositionArrayStatic().get(0).getTestStringBean().setValue(JsonTestHelper.TEST_STRING);
		
		Marshaller jsonMarshaller = jaxbUtility.getJsonMarshaller();
		
		StringWriter sw = new StringWriter();
		jsonMarshaller.marshal(testArray, sw);
		
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
		resourceImpl.getContents().add(testArray.getATypeInstance());
		
		Unmarshaller jsonUnmarshaller = jaxbUtility.getJsonUnmarshaller(resourceSet);
		
		String inputJson = TestActivator.getResourceContentAsString(RESOURCE);
		System.out.println(inputJson);
		StringReader sr = new StringReader(inputJson);

		assertEquals(null, testArray.getTestCompositionArrayStatic().get(0).getTestStringBean().getValue());
		
		JAXBElement<TestCategoryCompositionArray> jaxbElement = jsonUnmarshaller.unmarshal(new StreamSource(sr), TestCategoryCompositionArray.class);
		TestCategoryCompositionArray createdArray = jaxbElement.getValue();
		
		assertEquals(JsonTestHelper.TEST_STRING, createdArray.getTestCompositionArrayStatic().get(0).getTestStringBean().getValue());
	}
}