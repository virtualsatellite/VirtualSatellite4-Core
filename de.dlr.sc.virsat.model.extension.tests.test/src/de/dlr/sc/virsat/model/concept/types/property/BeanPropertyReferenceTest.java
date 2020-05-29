package de.dlr.sc.virsat.model.concept.types.property;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReference;

public class BeanPropertyReferenceTest extends AConceptTestCase {

	private Concept concept;

	@Before
	public void setup() {
		prepareEditingDomain();

		concept = loadConceptFromPlugin();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetValueType() {
		TestCategoryAllProperty testCategoryAllProperty = new TestCategoryAllProperty(concept);
		TestCategoryReference testCategoryReference = new TestCategoryReference(concept);
		
		assertNull("No reference is set", testCategoryReference.getTestRefCategory());
		
		CategoryAssignment testCategoryRefernceCa = testCategoryReference.getTypeInstance();
		CategoryAssignmentHelper caHelper = new CategoryAssignmentHelper(testCategoryRefernceCa);
		ReferencePropertyInstance rpi = (ReferencePropertyInstance) caHelper.getPropertyInstance(TestCategoryReference.PROPERTY_TESTREFCATEGORY);

		BeanPropertyReference<TestCategoryAllProperty> beanTestRefCategory = new BeanPropertyReference<>(rpi);
		beanTestRefCategory.setValue(testCategoryAllProperty);

		assertEquals("Correct reference has been set", rpi.getReference(), testCategoryAllProperty.getATypeInstance());
		assertEquals("Correct Category assignment has been set", testCategoryAllProperty, testCategoryReference.getTestRefCategory());
	}

	@Test
	public void testSetValueEditingDomainType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsSet() {
		fail("Not yet implemented");
	}

	@Test
	public void testUnset() {
		fail("Not yet implemented");
	}

}
