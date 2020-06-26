/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.util;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.DynamicArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.IUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;

/**
 * 
 * @author fisc_ph
 *
 */
public class CategoryInstantiatorTest {

	private Category testCategory;
	private CategoryInstantiator categoryInstantiator;
	private static final String TEST_CATEGORY_ASSIGNMENT_NAME = "de.dlr.model.test.category.assignment";
	private static final String COMPOSED_PROPERTY_NAME = "test.composed.property.name";
	private static final int ARRAY_SIZE_FIVE = 5;
	private static final int ARRAY_SIZE_TWO = 2;



	@Before
	public void setUp() throws Exception {
		final String TEST_CATEGORY_NAME = "de.dlr.model.test.category.simple";
		final String TEST_CATEGORY_DESC = "This is a very simple Test Category";

		testCategory = CategoriesFactory.eINSTANCE.createCategory();
		testCategory.setName(TEST_CATEGORY_NAME);
		testCategory.setDescription(TEST_CATEGORY_DESC);

		categoryInstantiator = new CategoryInstantiator();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGenerateTwoDifferentInstancesOfOneCategory() {
		final String TEST_CA_INSTANCE_NAME_ONE = "de.dlr.model.test.ca.simple.one";
		final String TEST_CA_INSTANCE_NAME_TWO = "de.dlr.model.test.ca.simple.two";

		CategoryAssignment generatedCaOne = categoryInstantiator.generateInstance(testCategory, TEST_CA_INSTANCE_NAME_ONE);
		CategoryAssignment generatedCaTwo = categoryInstantiator.generateInstance(testCategory, TEST_CA_INSTANCE_NAME_TWO);

		assertNotNull("The Category Assignment is well created", generatedCaOne);
		assertNotNull("The Category Assignment is well created", generatedCaTwo);

		assertEquals("The CategoryAssignment is typed correctly", testCategory, generatedCaOne.getType());
		assertEquals("The CategoryAssignment is typed correctly", testCategory, generatedCaTwo.getType());

		assertNotSame("The Instantiator created individual instances", generatedCaOne, generatedCaTwo);

		assertEquals("CategoryAssignment has correct name", TEST_CA_INSTANCE_NAME_ONE, generatedCaOne.getName());
		assertEquals("CategoryAssignment has correct name", TEST_CA_INSTANCE_NAME_TWO, generatedCaTwo.getName());
	}

	@Test
	public void testGenerateInstanceOfCategoryWithIntProperty() {
		IntProperty intProperty = createIntPropertyInCategory(testCategory);
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);

		assertEquals("There is one propery instance in the generated category assignment", 1, generatedCa.getPropertyInstances().size());

		APropertyInstance propertyInstance = generatedCa.getPropertyInstances().get(0);
		assertTrue("PropertyInstance is UnitValuePropertyInstance", propertyInstance instanceof UnitValuePropertyInstance);
		assertEquals("PropertyInstance is correctly typed by int property", intProperty, propertyInstance.getType());
	}

	@Test
	public void testGenerateInstanceOfCategoryWithFloatProperty() {
		FloatProperty floatProperty = createFloatPropertyInCategory(testCategory);
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);

		assertEquals("There is one propery instance in the generated category assignment", 1, generatedCa.getPropertyInstances().size());

		APropertyInstance propertyInstance = generatedCa.getPropertyInstances().get(0);
		assertTrue("PropertyInstance is UnitValuePropertyInstance", propertyInstance instanceof UnitValuePropertyInstance);
		assertEquals("PropertyInstance is correctly typed by float property", floatProperty, propertyInstance.getType());
	}
	
	@Test
	public void testGenerateInstanceOfCategoryWithResourceProperty() {
		ResourceProperty resourceProperty = PropertydefinitionsFactory.eINSTANCE.createResourceProperty();
		testCategory.getProperties().add(resourceProperty);
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);

		assertEquals("There is one propery instance in the generated category assignment", 1, generatedCa.getPropertyInstances().size());

		APropertyInstance propertyInstance = generatedCa.getPropertyInstances().get(0);
		assertTrue("PropertyInstance is UnitValuePropertyInstance", propertyInstance instanceof ResourcePropertyInstance);
		assertEquals("PropertyInstance is correctly typed by float property", resourceProperty, propertyInstance.getType());
	}


	@Test
	public void testGenerateInstanceOfCategoryWithStringProperty() {
		StringProperty stringProperty = createStringPropertyInCategory(testCategory);
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);

		assertEquals("There is one propery instance in the generated category assignment", 1, generatedCa.getPropertyInstances().size());

		APropertyInstance propertyInstance = generatedCa.getPropertyInstances().get(0);
		assertTrue("PropertyInstance is ValuePropertyInstance", propertyInstance instanceof ValuePropertyInstance);
		assertEquals("PropertyInstance is correctly typed by string property", stringProperty, propertyInstance.getType());
	}

	@Test
	public void testGenerateInstanceOfCategoryWithBooleanProperty() {
		BooleanProperty boolProperty = createBooleanPropertyInCategory(testCategory);
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);

		assertEquals("There is one propery instance in the generated category assignment", 1, generatedCa.getPropertyInstances().size());

		APropertyInstance propertyInstance = generatedCa.getPropertyInstances().get(0);
		assertTrue("PropertyInstance is ValuePropertyInstance", propertyInstance instanceof ValuePropertyInstance);
		assertEquals("PropertyInstance is correctly typed by boolean property", boolProperty, propertyInstance.getType());
	}

	@Test
	public void testGenerateInstanceOfCategoryWithEnumProperty() {
		
		// Setup a repo with a unit
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		repo.getActiveConcepts().add(concept);
		UnitManagement unitManagement = UnitsFactory.eINSTANCE.createUnitManagement();
		unitManagement.setSystemOfUnit(QudvFactory.eINSTANCE.createSystemOfUnits());
		repo.setUnitManagement(unitManagement);
		concept.getCategories().add(testCategory);
		
		AUnit unit = QudvFactory.eINSTANCE.createSimpleUnit();
		unit.setName("No Unit");
		repo.getUnitManagement().getSystemOfUnit().getUnit().add(unit);
		
		EnumValueDefinition evd1 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		EnumValueDefinition evd2 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		
		EnumProperty enumProperty = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
		testCategory.getProperties().add(enumProperty);
		enumProperty.getValues().add(evd1);
		enumProperty.getValues().add(evd2);
		enumProperty.setDefaultValue(evd2);
		enumProperty.setUnitName(unit.getName());
		
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);
		
		assertEquals("There is one propery instance in the generated category assignment", 1, generatedCa.getPropertyInstances().size());

		APropertyInstance propertyInstance = generatedCa.getPropertyInstances().get(0);
		assertTrue("PropertyInstance is ValuePropertyInstance", propertyInstance instanceof EnumUnitPropertyInstance);
		assertEquals("PropertyInstance is correctly typed by enum property", enumProperty, propertyInstance.getType());
		
		EnumUnitPropertyInstance enumUnitPropertyInstance = (EnumUnitPropertyInstance) propertyInstance;
		assertEquals("Enum is correctly initialized", evd2, enumUnitPropertyInstance.getValue());
		assertEquals("Unit has been correctly set", unit, enumUnitPropertyInstance.getUnit());
	}

	@Test
	public void testGenerateInstanceOfCategoryWithSeveralProperties() {
		IntProperty intProperty = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		StringProperty stringProperty = PropertydefinitionsFactory.eINSTANCE.createStringProperty();

		testCategory.getProperties().add(intProperty);
		testCategory.getProperties().add(stringProperty);

		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);

		assertEquals("There are two propery instances in the generated category assignment", 2, generatedCa.getPropertyInstances().size());

		APropertyInstance propertyInstanceOne = generatedCa.getPropertyInstances().get(0);
		APropertyInstance propertyInstanceTwo = generatedCa.getPropertyInstances().get(1);

		if (!propertyInstanceOne.getType().equals(intProperty)) {
			APropertyInstance tmp = propertyInstanceOne;
			propertyInstanceOne = propertyInstanceTwo;
			propertyInstanceTwo = tmp;
		}

		assertTrue("PropertyInstances have correct linguistical type", propertyInstanceOne instanceof UnitValuePropertyInstance);
		assertTrue("PropertyInstances have correct linguistical type", propertyInstanceTwo instanceof ValuePropertyInstance);
		assertEquals("PropertyInstances are correctly typed by corresponding properties", intProperty, propertyInstanceOne.getType());
		assertEquals("PropertyInstances are correctly typed by corresponding properties", stringProperty, propertyInstanceTwo.getType());
	}


	@Test
	public void testGenerateInstanceOfCategoryWithComposedProperty() {
		Category containedCategory = CategoriesFactory.eINSTANCE.createCategory();
		IntProperty intProperty = createIntPropertyInCategory(containedCategory);

		ComposedProperty composedProperty = PropertydefinitionsFactory.eINSTANCE.createComposedProperty();
		composedProperty.setName(COMPOSED_PROPERTY_NAME);
		composedProperty.setType(containedCategory);
		testCategory.getProperties().add(composedProperty);

		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);
		assertEquals("There is one propery instance in the generated category assignment", 1, generatedCa.getPropertyInstances().size());

		APropertyInstance propertyInstance = generatedCa.getPropertyInstances().get(0);
		assertTrue("PropertyInstance is ComposedPropertyInstance", propertyInstance instanceof ComposedPropertyInstance);
		assertEquals("PropertyInstance is correctly typed by composed property", composedProperty, propertyInstance.getType());

		CategoryAssignment containedCA = (CategoryAssignment) ((ComposedPropertyInstance) propertyInstance).getTypeInstance();
		assertEquals("Contained CategoryAssignment is typed by the contained Category", containedCategory, containedCA.getType());
		assertEquals("Contained CategoryAssignment has the same name as the ComposedProperty", COMPOSED_PROPERTY_NAME, containedCA.getName());

		assertEquals("There is one propery instance in the contained category assignment", 1, containedCA.getPropertyInstances().size());
		assertEquals("Contained PropertyInstance is correctly typed by int property", intProperty, containedCA.getPropertyInstances().get(0).getType());
	}

	@Test
	public void testUpdateCategoryAssignmentAddNewProperty() {
		IntProperty intProperty = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		testCategory.getProperties().add(intProperty);
		
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);
		assertEquals("There is one propery instance in the initial generated category assignment", 1, generatedCa.getPropertyInstances().size());
		
		APropertyInstance firstPropertyInstance = generatedCa.getPropertyInstances().get(0);
		
		StringProperty stringProperty = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		testCategory.getProperties().add(stringProperty);

		categoryInstantiator.updateCategoryAssignment(generatedCa);

		assertEquals("There are two propery instances after the update", 2, generatedCa.getPropertyInstances().size());

		APropertyInstance propertyInstanceOne = generatedCa.getPropertyInstances().get(0);
		APropertyInstance propertyInstanceTwo = generatedCa.getPropertyInstances().get(1);

		if (!propertyInstanceOne.getType().equals(intProperty)) {
			APropertyInstance tmp = propertyInstanceOne;
			propertyInstanceOne = propertyInstanceTwo;
			propertyInstanceTwo = tmp;
		}

		assertEquals("Property Instances are correctly typed by corresponding properties", intProperty, propertyInstanceOne.getType());
		assertEquals("Property Instances are correctly typed by corresponding properties", stringProperty, propertyInstanceTwo.getType());
		assertEquals("First Property Instance is not changed", firstPropertyInstance, propertyInstanceOne);
	}

	@Test
	public void testUpdateCategoryRemoveProperty() {
		IntProperty intProperty = createIntPropertyInCategory(testCategory);
		StringProperty stringProperty = createStringPropertyInCategory(testCategory);

		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);
		assertEquals("There are two propery instances in the initial generated category assignment", 2, generatedCa.getPropertyInstances().size());

		testCategory.getProperties().remove(intProperty);
		categoryInstantiator.updateCategoryAssignment(generatedCa);

		assertEquals("There is one propery instance after the update", 1, generatedCa.getPropertyInstances().size());

		APropertyInstance propertyInstance = generatedCa.getPropertyInstances().get(0);
		assertEquals("PropertyInstance is correctly typed by string property", stringProperty, propertyInstance.getType());
	}

	@Test
	public void testUpdateCategoryAddPropertyInContainedCategory() {
		Category containedCategory = CategoriesFactory.eINSTANCE.createCategory();
		IntProperty intProperty = createIntPropertyInCategory(containedCategory);

		ComposedProperty composedProperty = PropertydefinitionsFactory.eINSTANCE.createComposedProperty();
		composedProperty.setType(containedCategory);
		testCategory.getProperties().add(composedProperty);

		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);
		StringProperty stringProperty = createStringPropertyInCategory(containedCategory);

		categoryInstantiator.updateCategoryAssignment(generatedCa);

		ComposedPropertyInstance composedPropertyInstance = (ComposedPropertyInstance) generatedCa.getPropertyInstances().get(0);
		CategoryAssignment containedCA = composedPropertyInstance.getTypeInstance();

		assertEquals("There are two propery instances in the generated category assignment", 2, containedCA.getPropertyInstances().size());

		APropertyInstance propertyInstanceOne = containedCA.getPropertyInstances().get(0);
		APropertyInstance propertyInstanceTwo = containedCA.getPropertyInstances().get(1);

		if (!propertyInstanceOne.getType().equals(intProperty)) {
			APropertyInstance tmp = propertyInstanceOne;
			propertyInstanceOne = propertyInstanceTwo;
			propertyInstanceTwo = tmp;
		}

		assertEquals("PropertyInstances are correctly typed by corresponding properties", intProperty, propertyInstanceOne.getType());
		assertEquals("PropertyInstances are correctly typed by corresponding properties", stringProperty, propertyInstanceTwo.getType());
	}

	/**
	 * helper method to create an IntProperty within a category
	 * @param c the category in which to create the property
	 * @return the property that was added to the category
	 */
	private IntProperty createIntPropertyInCategory(Category c) {
		IntProperty property = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		c.getProperties().add(property);
		return property;
	}

	/**
	 * helper method to create a FloatProperty within a category
	 * @param c the category in which to create the property
	 * @return the property that was added to the category
	 */
	private FloatProperty createFloatPropertyInCategory(Category c) {
		FloatProperty property = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		c.getProperties().add(property);
		return property;
	}

	/**
	 * helper method to create a BooleanProperty within a category
	 * @param c the category in which to create the property
	 * @return the property that was added to the category
	 */
	private BooleanProperty createBooleanPropertyInCategory(Category c) {
		BooleanProperty property = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		c.getProperties().add(property);
		return property;
	}
	
	/**
	 * helper method to create an String within a category
	 * @param c the category in which to create the property
	 * @return the property that was added to the category
	 */
	private StringProperty createStringPropertyInCategory(Category c) {
		StringProperty property = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		c.getProperties().add(property);
		return property;
	}

	//*************************************************************************
	//* 
	//*	USE Case 1 - Static and Dynamic Array for a Float/IntProperty 
	//* 
	//*************************************************************************
	@Test
	public void testInstantiateArrayForStaticFloatProperty() {
		Category categoryWithArrayProperty = CategoriesFactory.eINSTANCE.createCategory();	
		AProperty staticfloatArrayProperty = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		
		StaticArrayModifier modifierStatic = PropertydefinitionsFactory.eINSTANCE.createStaticArrayModifier();
		modifierStatic.setArraySize(ARRAY_SIZE_FIVE);
		staticfloatArrayProperty.setArrayModifier(modifierStatic);
		categoryWithArrayProperty.getProperties().add(staticfloatArrayProperty);
		
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(categoryWithArrayProperty, TEST_CATEGORY_ASSIGNMENT_NAME);

		// the category assignment should have an ArrayInstance which is
		// typed correctly and it should already contain the desired instances to the FloatProperty
		assertEquals("There should be only one propertyinstance of type ArrayInstance", 1, generatedCa.getPropertyInstances().size());
		
		ArrayInstance arrayInstance = (ArrayInstance) generatedCa.getPropertyInstances().get(0);
		assertEquals("The Array is typed correctly to the Property", staticfloatArrayProperty, arrayInstance.getType());
		
		assertEquals("The Array contains correct amount of instances", ARRAY_SIZE_FIVE, arrayInstance.getArrayInstances().size());
		arrayInstance.getArrayInstances().forEach((typeInstance) -> {
			assertTrue("typeInstance is of correct Class", typeInstance instanceof UnitValuePropertyInstance);
			assertTrue("type of type Instance is correct", typeInstance.getType().equals(staticfloatArrayProperty));
		});
	}
	
	@Test
	public void testInstantiateArrayForDynamicFloatProperty() {
		AUnit testUnit = QudvFactory.eINSTANCE.createSimpleUnit();
		testUnit.setName("TestUnit");
		
		// Mock the setting of the default unit since this is not what is being tested here
		// We just have to make sure that it is being called
		CategoryInstantiator categoryInstantiator = new CategoryInstantiator() {
			protected void setDefaultUnit(IUnitPropertyInstance uvpi, EObject parentInstance) {
				uvpi.setUnit(testUnit);
			};
		};
		
		Category categoryWithArrayProperty = CategoriesFactory.eINSTANCE.createCategory();
		FloatProperty dynamicFloatArrayProperty = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		
		dynamicFloatArrayProperty.setUnitName("No Unit");
		
		DynamicArrayModifier modifierDynamic = PropertydefinitionsFactory.eINSTANCE.createDynamicArrayModifier();
		dynamicFloatArrayProperty.setArrayModifier(modifierDynamic);
		categoryWithArrayProperty.getProperties().add(dynamicFloatArrayProperty);
		
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(categoryWithArrayProperty, TEST_CATEGORY_ASSIGNMENT_NAME);

		assertEquals("There should be only one propertyinstance of type ArrayInstance", 1, generatedCa.getPropertyInstances().size());
		
		ArrayInstance arrayInstance = (ArrayInstance) generatedCa.getPropertyInstances().get(0);
		assertEquals("The Array contains no elements initially", 0, arrayInstance.getArrayInstances().size());
		assertEquals("The Array is typed correctly to the Property", dynamicFloatArrayProperty, arrayInstance.getType());

		// the Instantiator cannot add the newly create instance by itself. When the model and instantiator is used
		// within a transactional editing domain, the method would not be allowed to add the newly created instance to
		// the array instance.
		arrayInstance.getArrayInstances().add(categoryInstantiator.generateInstance(arrayInstance));
		arrayInstance.getArrayInstances().add(categoryInstantiator.generateInstance(arrayInstance));
		
		assertEquals("The Array contains correct amount of instances", ARRAY_SIZE_TWO, arrayInstance.getArrayInstances().size());
		arrayInstance.getArrayInstances().forEach((typeInstance) -> {
			assertTrue("typeInstance is of correct Class", typeInstance instanceof UnitValuePropertyInstance);
			assertTrue("type of type Instance is correct", typeInstance.getType().equals(dynamicFloatArrayProperty));
			
			UnitValuePropertyInstance uvpi = (UnitValuePropertyInstance) typeInstance;
			assertTrue("unit of Instance is correct", uvpi.getUnit().equals(testUnit));
		});
	}
	
	@Test
	public void testInstantiateArrayForStaticReferenceProperty() {
		// this is the category that will be referenced 
		Category categoryXY = CategoriesFactory.eINSTANCE.createCategory();
		categoryXY.getProperties().add(PropertydefinitionsFactory.eINSTANCE.createFloatProperty());
		categoryXY.getProperties().add(PropertydefinitionsFactory.eINSTANCE.createFloatProperty());
		
		// Now we create the category with a property that references the first one. but as an array
		Category categoryWithArrayProperty = CategoriesFactory.eINSTANCE.createCategory();
		ReferenceProperty staticRefArrayProperty = PropertydefinitionsFactory.eINSTANCE.createReferenceProperty();
		StaticArrayModifier modifierStatic = PropertydefinitionsFactory.eINSTANCE.createStaticArrayModifier();
		modifierStatic.setArraySize(ARRAY_SIZE_FIVE);
		staticRefArrayProperty.setArrayModifier(modifierStatic);
		staticRefArrayProperty.setReferenceType(categoryXY);
		categoryWithArrayProperty.getProperties().add(staticRefArrayProperty);
			
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(categoryWithArrayProperty, TEST_CATEGORY_ASSIGNMENT_NAME);
		assertEquals("There should be only one propertyinstance of type ArrayInstance", 1, generatedCa.getPropertyInstances().size());
		
		ArrayInstance arrayInstance = (ArrayInstance) generatedCa.getPropertyInstances().get(0);
		assertEquals("The Array is typed correctly to the Property", staticRefArrayProperty, arrayInstance.getType());
		
		assertEquals("The Array contains correct amount of instances", ARRAY_SIZE_FIVE, arrayInstance.getArrayInstances().size());
		arrayInstance.getArrayInstances().forEach((typeInstance) -> {
			assertTrue("typeInstance is of correct Class", typeInstance instanceof ReferencePropertyInstance);
			assertNull("no reference set to other instances", ((ReferencePropertyInstance) typeInstance).getReference());
			assertTrue("type of type Instance is correct", typeInstance.getType().equals(staticRefArrayProperty));
		});
	}
	
	@Test
	public void testInstantiateArrayForDynamicReferenceProperty() {	
		// this is the reference category 
		Category categoryXY = CategoriesFactory.eINSTANCE.createCategory();
		categoryXY.getProperties().add(PropertydefinitionsFactory.eINSTANCE.createFloatProperty());
		categoryXY.getProperties().add(PropertydefinitionsFactory.eINSTANCE.createFloatProperty());
		
		Category categoryWithArrayProperty = CategoriesFactory.eINSTANCE.createCategory();
		ReferenceProperty dynamicRefArrayProperty = PropertydefinitionsFactory.eINSTANCE.createReferenceProperty();
		dynamicRefArrayProperty.setReferenceType(categoryXY);
		
		DynamicArrayModifier modifierDynamic = PropertydefinitionsFactory.eINSTANCE.createDynamicArrayModifier();
		dynamicRefArrayProperty.setArrayModifier(modifierDynamic);
		categoryWithArrayProperty.getProperties().add(dynamicRefArrayProperty);
		
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(categoryWithArrayProperty, TEST_CATEGORY_ASSIGNMENT_NAME);
		assertEquals("There should be only one propertyinstance of type ArrayInstance", 1, generatedCa.getPropertyInstances().size());
		
		ArrayInstance arrayInstance = (ArrayInstance) generatedCa.getPropertyInstances().get(0);
		assertEquals("The Array is typed correctly to the Property", dynamicRefArrayProperty, arrayInstance.getType());
		
		arrayInstance.getArrayInstances().add(categoryInstantiator.generateInstance(arrayInstance));
		arrayInstance.getArrayInstances().add(categoryInstantiator.generateInstance(arrayInstance));
		
		assertEquals("The Array contains correct amount of instances", ARRAY_SIZE_TWO, arrayInstance.getArrayInstances().size());
		arrayInstance.getArrayInstances().forEach((typeInstance) -> {
			assertTrue("typeInstance is of correct Class", typeInstance instanceof ReferencePropertyInstance);
			assertNull("no reference set to other instances", ((ReferencePropertyInstance) typeInstance).getReference());
			assertTrue("type of type Instance is correct", typeInstance.getType().equals(dynamicRefArrayProperty));
		});
	}
	
	@Test
	public void testInstantiateComposedProperty() {
		// this is the composed category 
		Category categoryXY = CategoriesFactory.eINSTANCE.createCategory();
		FloatProperty fpX = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		FloatProperty fpY = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		
		categoryXY.getProperties().add(fpX);
		categoryXY.getProperties().add(fpY);
		
		Category categoryLine = CategoriesFactory.eINSTANCE.createCategory();
		ComposedProperty composedPropertyStart = PropertydefinitionsFactory.eINSTANCE.createComposedProperty();
		composedPropertyStart.setType(categoryXY);
		categoryLine.getProperties().add(composedPropertyStart);
		ComposedProperty composedPropertyStop = PropertydefinitionsFactory.eINSTANCE.createComposedProperty();
		composedPropertyStop.setType(categoryXY);
		categoryLine.getProperties().add(composedPropertyStop);
		
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(categoryLine, TEST_CATEGORY_ASSIGNMENT_NAME);
		assertEquals("There should be two composed propertyinstances", 2, generatedCa.getPropertyInstances().size());
		
		ComposedPropertyInstance arrayInstanceStart = (ComposedPropertyInstance) generatedCa.getPropertyInstances().get(0);
		ComposedPropertyInstance arrayInstanceStop = (ComposedPropertyInstance) generatedCa.getPropertyInstances().get(1);
		assertEquals("The Array is typed correctly to the Property", composedPropertyStart, arrayInstanceStart.getType());
		assertEquals("The Array is typed correctly to the Property", composedPropertyStop, arrayInstanceStop.getType());

		CategoryAssignment caStart = (CategoryAssignment) arrayInstanceStart.getTypeInstance();
		assertEquals("The categoryAssigment is typed correctly to the Category", categoryXY, caStart.getType());
		assertEquals("Sub Category Assignment has correct amount of PropertyInstances", 2, caStart.getPropertyInstances().size());
		assertEquals("First propertyinstance in Ca is typed correctly", fpX, caStart.getPropertyInstances().get(0).getType());
		assertEquals("Second propertyinstance in Ca is typed correctly", fpY, caStart.getPropertyInstances().get(1).getType());
	}
	
	
	@Test
	public void testInstantiateComposedStaticProperty() {
		// this is the composed category 
		Category categoryXY = CategoriesFactory.eINSTANCE.createCategory();
		FloatProperty fpX = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		FloatProperty fpY = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		
		categoryXY.getProperties().add(fpX);
		categoryXY.getProperties().add(fpY);
		
		Category categoryGraph = CategoriesFactory.eINSTANCE.createCategory();
		ComposedProperty composedPropertyPoints = PropertydefinitionsFactory.eINSTANCE.createComposedProperty();
		composedPropertyPoints.setType(categoryXY);
		categoryGraph.getProperties().add(composedPropertyPoints);
		
		StaticArrayModifier modifierStatic = PropertydefinitionsFactory.eINSTANCE.createStaticArrayModifier();
		modifierStatic.setArraySize(ARRAY_SIZE_TWO);
		composedPropertyPoints.setArrayModifier(modifierStatic);
		
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(categoryGraph, TEST_CATEGORY_ASSIGNMENT_NAME);
		assertEquals("There should be two composed propertyinstances", 1, generatedCa.getPropertyInstances().size());
		
		ArrayInstance arrayInstance = (ArrayInstance) generatedCa.getPropertyInstances().get(0);
		assertEquals("The Array contains correct amount of instances", ARRAY_SIZE_TWO, arrayInstance.getArrayInstances().size());
		arrayInstance.getArrayInstances().forEach((typeInstance) -> {
			// First make sure that the array contains composed property instances
			assertTrue("typeInstance is of correct Class", typeInstance instanceof ComposedPropertyInstance);
			assertTrue("type of typeInstance is correct", typeInstance.getType().equals(composedPropertyPoints));
			
			// then make sure that this Composed Property instances point to the new category assignment
			ComposedPropertyInstance cpi = (ComposedPropertyInstance) typeInstance;
			assertTrue("Instantiated Type of Composed Proeprty Instance is correct", cpi.getTypeInstance() instanceof CategoryAssignment);
			assertTrue("Category Assignment of ComposedProperty is correct", cpi.getTypeInstance().getType().equals(categoryXY));
		});	
	}

	@Test
	public void testInstantiateComposedDynamicProperty() {
		// this is the composed category 
		Category categoryXY = CategoriesFactory.eINSTANCE.createCategory();
		FloatProperty fpX = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		FloatProperty fpY = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		
		categoryXY.getProperties().add(fpX);
		categoryXY.getProperties().add(fpY);
		
		Category categoryGraph = CategoriesFactory.eINSTANCE.createCategory();
		ComposedProperty composedPropertyPoints = PropertydefinitionsFactory.eINSTANCE.createComposedProperty();
		composedPropertyPoints.setType(categoryXY);
		categoryGraph.getProperties().add(composedPropertyPoints);
		
		DynamicArrayModifier modifierDynamic = PropertydefinitionsFactory.eINSTANCE.createDynamicArrayModifier();
		composedPropertyPoints.setArrayModifier(modifierDynamic);
		
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(categoryGraph, TEST_CATEGORY_ASSIGNMENT_NAME);
		assertEquals("There should be two composed propertyinstances", 1, generatedCa.getPropertyInstances().size());
		
		ArrayInstance arrayInstance = (ArrayInstance) generatedCa.getPropertyInstances().get(0);
		arrayInstance.getArrayInstances().add(categoryInstantiator.generateInstance(arrayInstance));
		arrayInstance.getArrayInstances().add(categoryInstantiator.generateInstance(arrayInstance));
		
		assertEquals("The Array contains correct amount of instances", ARRAY_SIZE_TWO, arrayInstance.getArrayInstances().size());
		arrayInstance.getArrayInstances().forEach((typeInstance) -> {
			// First make sure that the array contains composed property instances
			assertTrue("typeInstance is of correct Class", typeInstance instanceof ComposedPropertyInstance);
			assertTrue("type of typeInstance is correct", typeInstance.getType().equals(composedPropertyPoints));
			
			// Then make sure that this Composed Property instances point to the new category assignment
			ComposedPropertyInstance cpi = (ComposedPropertyInstance) typeInstance;
			assertTrue("Instantiated Type of Composed Proeprty Instance is correct", cpi.getTypeInstance() instanceof CategoryAssignment);
			assertTrue("Category Assignment of ComposedProperty is correct", cpi.getTypeInstance().getType().equals(categoryXY));
		});
	}
	
	@Test
	public void testIntPropertyWithDefaultValue() {
		String defaultValue = "-10";
		IntProperty intProperty = createIntPropertyInCategory(testCategory);
		intProperty.setDefaultValue(defaultValue);
		
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);
		
		ValuePropertyInstance propertyInstance = (ValuePropertyInstance) generatedCa.getPropertyInstances().get(0);
		assertEquals("Property has correct value copied from default", defaultValue, propertyInstance.getValue());
	}
	
	@Test
	public void testBooleanPropertyWithDefaultValue() {
		String defaultValue = "false";
		BooleanProperty boolProperty = createBooleanPropertyInCategory(testCategory);
		boolProperty.setDefaultValue(defaultValue);
		
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);
		
		ValuePropertyInstance propertyInstance = (ValuePropertyInstance) generatedCa.getPropertyInstances().get(0);
		assertEquals("Property has correct value copied from default", defaultValue, propertyInstance.getValue());
	}
	
	@Test
	public void testFloatStaticArrayWithDefaultValue() {
		String defaultValue = "-10.5";
		FloatProperty floatArrayProperty = createFloatPropertyInCategory(testCategory);
		floatArrayProperty.setDefaultValue(defaultValue);
		
		StaticArrayModifier modifierStatic = PropertydefinitionsFactory.eINSTANCE.createStaticArrayModifier();
		modifierStatic.setArraySize(ARRAY_SIZE_FIVE);
		floatArrayProperty.setArrayModifier(modifierStatic);

		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);

		ArrayInstance arrayInstance = (ArrayInstance) generatedCa.getPropertyInstances().get(0);
		arrayInstance.getArrayInstances().forEach((arrayElement) -> {
			assertEquals("Array element has correct value copied from default", defaultValue, ((ValuePropertyInstance) arrayElement).getValue());
		});		
	}

	@Test
	public void testStringDynamicArrayWithDefaultValue() {
		String defaultValue = "empty";
		StringProperty stringArrayProperty = createStringPropertyInCategory(testCategory);
		stringArrayProperty.setDefaultValue(defaultValue);
		
		DynamicArrayModifier modifierDynamic = PropertydefinitionsFactory.eINSTANCE.createDynamicArrayModifier();
		stringArrayProperty.setArrayModifier(modifierDynamic);
		
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);

		ArrayInstance arrayInstance = (ArrayInstance) generatedCa.getPropertyInstances().get(0);

		arrayInstance.getArrayInstances().add(categoryInstantiator.generateInstance(arrayInstance));
		arrayInstance.getArrayInstances().add(categoryInstantiator.generateInstance(arrayInstance));
		
		arrayInstance.getArrayInstances().forEach((arrayElement) -> {
			assertEquals("Array element has correct value copied from default", defaultValue, ((ValuePropertyInstance) arrayElement).getValue());
		});
	}
	
	@Test
	public void testGenerateInstanceWithExtendedCategory() {
		
		Category testCategoryBase = CategoriesFactory.eINSTANCE.createCategory();
		testCategory.setExtendsCategory(testCategoryBase);
		
		IntProperty propertyOne = createIntPropertyInCategory(testCategoryBase);
		IntProperty propertyTwo = createIntPropertyInCategory(testCategory);
		
		CategoryAssignment generatedCaBase = categoryInstantiator.generateInstance(testCategoryBase, TEST_CATEGORY_ASSIGNMENT_NAME);
		List<ATypeDefinition> generatedPropertyInstancesBase = generatedCaBase.getPropertyInstances().stream().map(pi -> pi.getType()).collect(Collectors.toList());
		assertThat("Generated Ca for base category has instance instance only for own property", generatedPropertyInstancesBase, allOf(hasItem(propertyOne), not(hasItem(propertyTwo))));
		
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);
		List<ATypeDefinition> generatedPropertyInstances = generatedCa.getPropertyInstances().stream().map(pi -> pi.getType()).collect(Collectors.toList());
	
		assertEquals("Generated Ca has 2 property instances", 2, generatedPropertyInstances.size());
		assertThat("Generated Ca has instance for own propertyTwo and instance for inherited propertyOne", generatedPropertyInstances, hasItems(propertyOne, propertyTwo));
	}
	
	@Test
	public void testGenerateInstanceWithAbstractCategory() {
		testCategory.setIsAbstract(true);
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);
		assertNull("Generated Ca for base category has instance instance only for own property", generatedCa);
	}
	
	@Test
	public void testGenerateCategoryWithPropertyWithDefaultUnit() {
		FloatProperty fp = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		fp.setUnitName(QudvUnitHelper.UNIT_NAME_KILO_GRAMM);
		testCategory.getProperties().add(fp);
		
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.getCategories().add(testCategory);
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		repo.getActiveConcepts().add(concept);
		
		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "This is the system of units for this study", "N/A");
		repo.setUnitManagement(UnitsFactory.eINSTANCE.createUnitManagement());
		repo.getUnitManagement().setSystemOfUnit(sou);
		
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);
		UnitValuePropertyInstance uvpi = (UnitValuePropertyInstance) generatedCa.getPropertyInstances().get(0);
		assertEquals("Correct default unit has been assigned", QudvUnitHelper.UNIT_NAME_KILO_GRAMM, uvpi.getUnit().getName());
	}
	
	@Test
	public void testGenerateCategoryWithComposedPropertyWithDefaultUnit() {
		ComposedProperty cp = PropertydefinitionsFactory.eINSTANCE.createComposedProperty();
		testCategory.getProperties().add(cp);
		
		// Create a category that will be used as a type for the testCategory
		FloatProperty fp = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		fp.setUnitName(QudvUnitHelper.UNIT_NAME_KILO_GRAMM);
		Category floatCategory = CategoriesFactory.eINSTANCE.createCategory();
		floatCategory.getProperties().add(fp);
		cp.setType(floatCategory);
		
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.getCategories().add(testCategory);
		concept.getCategories().add(floatCategory);
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		repo.getActiveConcepts().add(concept);
		
		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "This is the system of units for this study", "N/A");
		repo.setUnitManagement(UnitsFactory.eINSTANCE.createUnitManagement());
		repo.getUnitManagement().setSystemOfUnit(sou);
		
		CategoryAssignment generatedCa = categoryInstantiator.generateInstance(testCategory, TEST_CATEGORY_ASSIGNMENT_NAME);
		ComposedPropertyInstance cpi = (ComposedPropertyInstance) generatedCa.getPropertyInstances().get(0);
		CategoryAssignment ca = (CategoryAssignment) cpi.getTypeInstance();
		UnitValuePropertyInstance uvpi = (UnitValuePropertyInstance) ca.getPropertyInstances().get(0);
		assertEquals("Correct default unit has been assigned", QudvUnitHelper.UNIT_NAME_KILO_GRAMM, uvpi.getUnit().getName());
	}
	
}
