/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.concepts.util;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptImport;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * 
 */
public class ActiveConceptHelperTest {

	private Repository testRepo;
	
	private Concept testConcept;	
	private Category testCategoryOne;
	private Category testCategoryTwo;
	private Category testCategoryThree;
	
	private StructuralElement testSeOne;
	private StructuralElement testSeTwo;
	
	private ActiveConceptHelper acHelper;
	
	private static final String TEST_CONCEPT_ID = "de.dlr.sc.virsat.model.concept.test"; 
	private static final String TEST_DISPLAY_NAME = "Concept"; 
	private static final String TEST_VERSION = "1.0"; 
	private static final String TEST_INVALID_ID = "invalid"; 
	private static final String TEST_CATEGORY_ID_ONE = "catOne"; 
	private static final String TEST_CATEGORY_ID_TWO = "catTWo"; 
	private static final String TEST_CATEGORY_ID_THREE = "catThree"; 

	private static final String TEST_SE_ID_ONE = "seOne"; 
	private static final String TEST_SE_ID_TWO = "seTWo"; 

	@Before
	public void setup() {
		testRepo = DVLMFactory.eINSTANCE.createRepository();
		
		testConcept = ConceptsFactory.eINSTANCE.createConcept();
		testConcept.setName(TEST_CONCEPT_ID);
		testConcept.setDisplayName(TEST_DISPLAY_NAME);
		testConcept.setVersion(TEST_VERSION);
		
		testCategoryOne = CategoriesFactory.eINSTANCE.createCategory();
		testCategoryTwo = CategoriesFactory.eINSTANCE.createCategory();
		testCategoryThree = CategoriesFactory.eINSTANCE.createCategory();
		testCategoryOne.setIsApplicableForAll(true);
		testCategoryTwo.setIsApplicableForAll(true);
		
		testCategoryOne.setName(TEST_CATEGORY_ID_ONE);
		testCategoryTwo.setName(TEST_CATEGORY_ID_TWO);
		testCategoryThree.setName(TEST_CATEGORY_ID_THREE);
		
		testCategoryThree.setExtendsCategory(testCategoryOne);
		
		testRepo.getActiveConcepts().add(testConcept);
		
		testConcept.getCategories().add(testCategoryOne);
		testConcept.getCategories().add(testCategoryTwo);
		testConcept.getCategories().add(testCategoryThree);
		
		testSeOne = StructuralFactory.eINSTANCE.createStructuralElement();
		testSeTwo = StructuralFactory.eINSTANCE.createStructuralElement();
		
		testSeOne.setName(TEST_SE_ID_ONE);
		testSeTwo.setName(TEST_SE_ID_TWO);
		
		testConcept.getStructuralElements().add(testSeOne);
		testConcept.getStructuralElements().add(testSeTwo);
		
		acHelper = new ActiveConceptHelper(testRepo);
	}
	
	@Test
	public void testGetCategoryByConceptIdAndCategoryId() {
		Category category = acHelper.getCategory(TEST_CONCEPT_ID, TEST_CATEGORY_ID_TWO);
		assertEquals("Found the correct Category", testCategoryTwo, category); 
	}
	
	@Test
	public void testGetCategoryByInvalidConceptIdAndValidCategoryId() {
		Category notFoundCategory = acHelper.getCategory(TEST_INVALID_ID, TEST_CATEGORY_ID_TWO);
		assertNull("Did not find the correct Category", notFoundCategory); 
	}
	
	@Test
	public void testGetConceptById() {
		Concept concept = acHelper.getConcept(TEST_CONCEPT_ID);
		assertEquals("Found the correct Concept", testConcept, concept);
	}
	
	@Test
	public void testGetConceptByInvalidId() {
		Concept notFoundConcept = acHelper.getConcept(TEST_INVALID_ID);
		assertNull("Did not find concept for invalid id", notFoundConcept); 
	}

	@Test
	public void testGetCategoryByConceptAndCategoryId() {
		Category category = ActiveConceptHelper.getCategory(testConcept, TEST_CATEGORY_ID_ONE);
		assertEquals("Found the correct Category", testCategoryOne, category);
	}

	@Test
	public void testGetCategoryByConceptAndCategoryFqn() {
		Category category = ActiveConceptHelper.getCategory(testConcept, TEST_CONCEPT_ID + "." + TEST_CATEGORY_ID_ONE);
		assertEquals("Found the correct Category", testCategoryOne, category);
	}

	@Test
	public void testGetCategoryByConceptAndCategoryIncorrectFqn() {
		Category category = ActiveConceptHelper.getCategory(testConcept, TEST_CONCEPT_ID + ".incorrect." + TEST_CATEGORY_ID_ONE);
		assertNull("Did not find category for invalid fqn", category); 
	}

	@Test
	public void testGetCategoryByConceptAndCategoryNullNameAndFqn() {
		testConcept.setName(null);
		Category category = ActiveConceptHelper.getCategory(testConcept, TEST_CONCEPT_ID + TEST_CATEGORY_ID_ONE);
		assertNull("Did not find category by fqn because category name is null", category); 
	}

	@Test
	public void testGetCategoryByConceptAndCategoryNullNameAndCategoryName() {
		testConcept.setName(null);
		Category category = ActiveConceptHelper.getCategory(testConcept, TEST_CATEGORY_ID_ONE);
		assertEquals("Found the correct Category", testCategoryOne, category);
	}
	
	@Test
	public void testGetCategoryByConceptAndInvalidCategoryId() {
		Category notFoundCategory = ActiveConceptHelper.getCategory(testConcept, TEST_INVALID_ID);
		assertNull("Did not find category for invalid id", notFoundCategory); 
	}
	
	@Test
	public void testGetCategoryIdByTypeDefinition() {
		String categoryId = ActiveConceptHelper.getFullQualifiedId(testCategoryOne);
		assertEquals("found the correct full qualified category id", TEST_CONCEPT_ID + "." +  TEST_CATEGORY_ID_ONE, categoryId);
	}
	
	@Test
	public void testGetAllCategoryIdsByTypeDefinition() {
		Set<String> categoryIds = ActiveConceptHelper.getAllFullQualifiedIds(testCategoryThree);
		assertThat("found the correct full qualified category ids", categoryIds, hasItems(TEST_CONCEPT_ID + "." +  TEST_CATEGORY_ID_ONE, TEST_CONCEPT_ID + "." +  TEST_CATEGORY_ID_THREE));
	}
	
	@Test
	public void testGetAllCategoryNamesByTypeDefinition() {
		Set<String> categoryIds = ActiveConceptHelper.getAllNames(testCategoryThree);
		assertThat("found the correct category names", categoryIds, hasItems(TEST_CATEGORY_ID_ONE, TEST_CATEGORY_ID_THREE));
	}
	
	@Test
	public void testGetCategoryIdByTypeInstance() {
		ValuePropertyInstance valuePropertyInstance = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();	
		String categoryId = ActiveConceptHelper.getFullQualifiedCategoryId(valuePropertyInstance);
		
		assertEquals("category name is empty", "", categoryId);
		
		StringProperty stringProperty = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		testCategoryOne.getProperties().add(stringProperty);
		valuePropertyInstance.setType(testCategoryOne);
		
		categoryId = ActiveConceptHelper.getFullQualifiedCategoryId(valuePropertyInstance);
		
		assertEquals("found the correct full qualified category id", TEST_CONCEPT_ID + "." +  TEST_CATEGORY_ID_ONE, categoryId);
	}
	
	@Test
	public void testSetRepository() {
		ActiveConceptHelper acHelper = new ActiveConceptHelper(null);
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		
		ActiveConceptHelper returnedHelper = acHelper.setRepository(repo);	
		assertEquals("The returned Helper has to be equal to its actualö isntance", returnedHelper, acHelper);
	}
	
	@Test
	public void testGetProperty() {
		AProperty propertyOne = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		AProperty propertyTwo = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		
		final String ID_ONE = "idOne";
		final String ID_TWO = "idTwo";
		
		propertyOne.setName(ID_ONE);
		propertyTwo.setName(ID_TWO);
		
		testCategoryOne.getProperties().add(propertyOne);
		testCategoryOne.getProperties().add(propertyTwo);
		
		AProperty returnedNull = ActiveConceptHelper.getProperty(testCategoryOne, "invalidId");
		assertNull("the property does not existed by an invalid id", returnedNull);
		
		AProperty returnedPropertyOne = ActiveConceptHelper.getProperty(testCategoryOne, ID_ONE);
		assertEquals("the id one one existed in the category", propertyOne, returnedPropertyOne);
	}
	
	@Test
	public void testGetConceptNameWithVersion() {
		Concept concept = testConcept;
		String version = "[" + TEST_VERSION + "]";

		String expectedConceptNameWithVersion = TEST_DISPLAY_NAME + " - " + TEST_CONCEPT_ID + " " + version;
		String conceptNameWithVersion = ActiveConceptHelper.getConceptNameWithVersion(concept);

		assertEquals("The concept with a display name is correctly displayed.", expectedConceptNameWithVersion, conceptNameWithVersion);
		
		concept.setDisplayName(null);
		expectedConceptNameWithVersion = TEST_CONCEPT_ID + " " + version;
		conceptNameWithVersion = ActiveConceptHelper.getConceptNameWithVersion(concept);
		
		assertEquals("The concept without a display name is correctly displayed.", expectedConceptNameWithVersion, conceptNameWithVersion);
	}
	
	@Test
	public void testGetNonArrayProperties() {
		AProperty propertyDynamicArray = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		AProperty propertyNoArray = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		AProperty propertyStaticArray = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();

		propertyDynamicArray.setArrayModifier(PropertydefinitionsFactory.eINSTANCE.createDynamicArrayModifier());
		propertyStaticArray.setArrayModifier(PropertydefinitionsFactory.eINSTANCE.createStaticArrayModifier());
		
		testCategoryOne.getProperties().add(propertyDynamicArray);
		testCategoryOne.getProperties().add(propertyNoArray);
		testCategoryOne.getProperties().add(propertyStaticArray);
		
		List<AProperty> nponArrayProperties = ActiveConceptHelper.getNonArrayProperties(testCategoryOne);
		assertEquals("There is only one property inside", 1, nponArrayProperties.size());
		assertThat("Property Two is part fo that list", nponArrayProperties, hasItem(propertyNoArray));
	}
	
	@Test
	public void testGetTypeDefinitionByFQP() {
		AProperty propertyOne = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		AProperty propertyTwo = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		
		final String ID_ONE = "idOne";
		final String ID_TWO = "idTwo";
		
		propertyOne.setName(ID_ONE);
		propertyTwo.setName(ID_TWO);

		// Property two will not be part of the category
		testCategoryOne.getProperties().add(propertyOne);
		
		List<IQualifiedName> qualifiedNamePath = new ArrayList<>();
		qualifiedNamePath.add(testConcept);
		qualifiedNamePath.add(testCategoryOne);
		
		// See if we can find the category specified by the path
		ATypeDefinition typeDefCategory = acHelper.getTypeDefinitionByFQP(qualifiedNamePath);
		assertEquals("Found the correct category", testCategoryOne, typeDefCategory);

		// See if we can find the property specified by the path
		qualifiedNamePath.add(propertyOne);
		ATypeDefinition typeDefProperty = acHelper.getTypeDefinitionByFQP(qualifiedNamePath);
		assertEquals("Found the correct property", propertyOne, typeDefProperty);
		
		// now search for something that does not exist
		qualifiedNamePath.clear();
		qualifiedNamePath.add(testConcept);
		qualifiedNamePath.add(testCategoryOne);
		qualifiedNamePath.add(propertyTwo);
		ATypeDefinition typeDefNull = acHelper.getTypeDefinitionByFQP(qualifiedNamePath);
		assertNull("As expected we found nothing", typeDefNull);
	}
	
	@Test
	public void testGetConcept() {
		AProperty propertyOne = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();				
		testCategoryOne.getProperties().add(propertyOne);
		
		Concept returnedConceptOfCategory = ActiveConceptHelper.getConcept(testCategoryOne);
		assertEquals("return the correct concept of the category one", testConcept, returnedConceptOfCategory);
		
		Concept returnedConceptOfProperty = ActiveConceptHelper.getConcept(propertyOne);
		assertEquals("return the correct concept of the property one", testConcept, returnedConceptOfProperty);
	}
	
	@Test
	public void testGetConceptStructuralElement() {
		Concept returnedConceptOfSe = ActiveConceptHelper.getConcept(testSeOne);
		assertEquals("return the correct concept of the SE one", testConcept, returnedConceptOfSe);
	}
	
	@Test
	public void testGetStructuralElement() {
		StructuralElement seOne = ActiveConceptHelper.getStructuralElement(testConcept, TEST_SE_ID_ONE);
		assertEquals("Found the correct StructuralElement", testSeOne, seOne);
		
		StructuralElement seNull = ActiveConceptHelper.getStructuralElement(testConcept, TEST_INVALID_ID);
		assertNull("Found null object", seNull);
	}
	
	@Test
	public void testGetContainerQualifedNameForInstance() {
		
		// Create a common root category
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setName("DataPort");
		cat.setIsApplicableForAll(true);
		
		// Add two simple properties that will convert into ValueInstanceProperties
		IntProperty propSerialNo = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		propSerialNo.setName("serialNo");
		IntProperty propConnections = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		propConnections.setName("connections");
		cat.getProperties().add(propSerialNo);
		cat.getProperties().add(propConnections);
		
		CategoryAssignment ca = new CategoryInstantiator().generateInstance(cat, "dataPort1");
		
		StructuralElement seAocs = StructuralFactory.eINSTANCE.createStructuralElement();
		seAocs.setName("AOCS");
		StructuralElementInstance seiAocs = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiAocs.setName("AOCS");
		seiAocs.setType(seAocs);
		seiAocs.getCategoryAssignments().add(ca);

		// Now add the Category assignment to a StructuralElementInstance
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setName("Rw");
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setName("Rw1");
		sei.setType(se);
		sei.getCategoryAssignments().add(ca);
		seiAocs.getChildren().add(seiAocs);
		
		String fullQualifedNameForSerialNo1 = ActiveConceptHelper.getContainerQualifedNameForInstance(ca.getPropertyInstances().get(0));
		String fullQualifedNameForConnections1 = ActiveConceptHelper.getContainerQualifedNameForInstance(ca.getPropertyInstances().get(1));

		assertEquals("Got correct FQN of Instance", "Rw1.dataPort1.serialNo", fullQualifedNameForSerialNo1);
		assertEquals("Got correct FQN of Instance", "Rw1.dataPort1.connections", fullQualifedNameForConnections1);
	}
	
	@Test
	public void testGetFullQualifedNameForInstance() {
		
		// Create a common root category
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setName("DataPort");
		cat.setIsApplicableForAll(true);
		
		// Add two simple properties that will convert into ValueInstanceProperties
		IntProperty propSerialNo = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		propSerialNo.setName("serialNo");
		IntProperty propConnections = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		propConnections.setName("connections");
		cat.getProperties().add(propSerialNo);
		cat.getProperties().add(propConnections);
		
		CategoryAssignment ca = new CategoryInstantiator().generateInstance(cat, "dataPort1");
		
		String fullQualifedNameForSerialNo = ActiveConceptHelper.getFullQualifedNameForInstance(ca.getPropertyInstances().get(0));
		String fullQualifedNameForConnections = ActiveConceptHelper.getFullQualifedNameForInstance(ca.getPropertyInstances().get(1));
		
		assertEquals("Got correct FQN of Instance", "dataPort1.serialNo", fullQualifedNameForSerialNo);
		assertEquals("Got correct FQN of Instance", "dataPort1.connections", fullQualifedNameForConnections);
		
		// Now add the Category assignment to a StructuralElementInstance
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setName("Rw");
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setName("Rw1");
		sei.setType(se);
		sei.getCategoryAssignments().add(ca);
		
		String fullQualifedNameForSerialNo1 = ActiveConceptHelper.getFullQualifedNameForInstance(ca.getPropertyInstances().get(0));
		String fullQualifedNameForConnections1 = ActiveConceptHelper.getFullQualifedNameForInstance(ca.getPropertyInstances().get(1));
		
		assertEquals("Got correct FQN of Instance", "Rw1.dataPort1.serialNo", fullQualifedNameForSerialNo1);
		assertEquals("Got correct FQN of Instance", "Rw1.dataPort1.connections", fullQualifedNameForConnections1);
		
		sei.setName(null);
		String fullQualifedNameWithUnknown = ActiveConceptHelper.getFullQualifedNameForInstance(ca.getPropertyInstances().get(0));
		assertEquals("Got correct FQN of Instance", ActiveConceptHelper.UNSET_NAME_PART + ".dataPort1.serialNo", fullQualifedNameWithUnknown);
		
		// CHECKSTYLE:OFF
		// Now adding an array property to the Category of Type int
		IntProperty propArrayOfCoductivity = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		StaticArrayModifier sam = PropertydefinitionsFactory.eINSTANCE.createStaticArrayModifier();
		sam.setArraySize(5);
		propArrayOfCoductivity.setArrayModifier(sam);
		propArrayOfCoductivity.setName("conductivity");
		cat.getProperties().add(propArrayOfCoductivity);
		
		CategoryAssignment caWithArray = new CategoryInstantiator().generateInstance(cat, "dataPort2");
		
		ArrayInstance ai = (ArrayInstance) caWithArray.getPropertyInstances().get(2);
		String fullQualifedNameForArray = ActiveConceptHelper.getFullQualifedNameForInstance(ai);

		assertEquals("Got correct FQN of Instance", "dataPort2.conductivity", fullQualifedNameForArray);
		
		String fullQualifedNameForArray0 = ActiveConceptHelper.getFullQualifedNameForInstance(ai.getArrayInstances().get(0));
		String fullQualifedNameForArray2 = ActiveConceptHelper.getFullQualifedNameForInstance(ai.getArrayInstances().get(2));
		String fullQualifedNameForArray4 = ActiveConceptHelper.getFullQualifedNameForInstance(ai.getArrayInstances().get(4));
		assertEquals("Got correct FQN of Instance", "dataPort2.conductivity[0]", fullQualifedNameForArray0);
		assertEquals("Got correct FQN of Instance", "dataPort2.conductivity[2]", fullQualifedNameForArray2);
		assertEquals("Got correct FQN of Instance", "dataPort2.conductivity[4]", fullQualifedNameForArray4);
		
		// now we need to test with a composed property
		Category catPin = CategoriesFactory.eINSTANCE.createCategory();
		catPin.setName("pin");
		
		// Add two simple properties that will convert into ValueInstanceProeprties
		IntProperty propVoltage = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		propVoltage.setName("voltage");
		catPin.getProperties().add(propVoltage);
		
		ComposedProperty composedProperty = PropertydefinitionsFactory.eINSTANCE.createComposedProperty();
		composedProperty.setName("pinV");
		composedProperty.setType(catPin);
		cat.getProperties().add(composedProperty);
		
		CategoryAssignment caWithComposedProperty = new CategoryInstantiator().generateInstance(cat, "dataPort2");
		
		ComposedPropertyInstance cpi = (ComposedPropertyInstance) caWithComposedProperty.getPropertyInstances().get(3);
		
		String fullQualifedNameForCpi = ActiveConceptHelper.getFullQualifedNameForInstance(cpi);
		String fullQualifedNameForCpiProperty = ActiveConceptHelper.getFullQualifedNameForInstance(cpi.getTypeInstance().getPropertyInstances().get(0));
		
		assertEquals("Got correct FQN of Instance", "dataPort2.pinV", fullQualifedNameForCpi);
		assertEquals("Got correct FQN of Instance", "dataPort2.pinV.voltage", fullQualifedNameForCpiProperty);
		
		// now change the composite property to an array
		StaticArrayModifier samCp = PropertydefinitionsFactory.eINSTANCE.createStaticArrayModifier();
		samCp.setArraySize(5);
		composedProperty.setArrayModifier(samCp);

		CategoryAssignment caWithComposedPropertyArray = new CategoryInstantiator().generateInstance(cat, "dataPort3");
	
		ArrayInstance aiCpi = (ArrayInstance) caWithComposedPropertyArray.getPropertyInstances().get(3);
		
		ComposedPropertyInstance cpi0 = (ComposedPropertyInstance) aiCpi.getArrayInstances().get(0);
		ComposedPropertyInstance cpi3 = (ComposedPropertyInstance) aiCpi.getArrayInstances().get(3);
		ComposedPropertyInstance cpi4 = (ComposedPropertyInstance) aiCpi.getArrayInstances().get(4);
		
		String fullQualifedNameForCpiProperty0 = ActiveConceptHelper.getFullQualifedNameForInstance(cpi0.getTypeInstance().getPropertyInstances().get(0));
		String fullQualifedNameForCpiProperty3 = ActiveConceptHelper.getFullQualifedNameForInstance(cpi3.getTypeInstance().getPropertyInstances().get(0));
		String fullQualifedNameForCpiProperty4 = ActiveConceptHelper.getFullQualifedNameForInstance(cpi4.getTypeInstance().getPropertyInstances().get(0));

		assertEquals("Got correct FQN of Instance", "dataPort3.pinV[0].voltage", fullQualifedNameForCpiProperty0);
		assertEquals("Got correct FQN of Instance", "dataPort3.pinV[3].voltage", fullQualifedNameForCpiProperty3);
		assertEquals("Got correct FQN of Instance", "dataPort3.pinV[4].voltage", fullQualifedNameForCpiProperty4);
		
		// CHECKSTYLE:ON
	}
	
	@Test
	public void testIsSafeAssignableFrom() {
		
		FloatProperty floatProperty = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		
		assertTrue("Can be assigned", ActiveConceptHelper.isSafeAssignableFrom(PropertydefinitionsPackage.Literals.APROPERTY, floatProperty));
		assertTrue("Can be assigned", ActiveConceptHelper.isSafeAssignableFrom(PropertydefinitionsPackage.Literals.FLOAT_PROPERTY, floatProperty));
		assertFalse("Can not be assigned", ActiveConceptHelper.isSafeAssignableFrom(PropertydefinitionsPackage.Literals.INT_PROPERTY, floatProperty));
	}
	
	@Test
	public void testExtractConceptFromImport() {
		ConceptImport ci1 = ConceptsFactory.eINSTANCE.createConceptImport();
		ConceptImport ci2 = ConceptsFactory.eINSTANCE.createConceptImport();
		
		ci1.setImportedNamespace(TEST_CATEGORY_ID_ONE + ".*");
		ci2.setImportedNamespace(TEST_CATEGORY_ID_ONE + "." + TEST_SE_ID_ONE);
		
		assertEquals("Identified correct concept", TEST_CATEGORY_ID_ONE, ActiveConceptHelper.getConceptFromImport(ci1));
		assertEquals("Identified correct concept", TEST_CATEGORY_ID_ONE, ActiveConceptHelper.getConceptFromImport(ci2));
	}
	
	@Test
	public void testGetConceptDependencies() {
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		ConceptImport ci1 = ConceptsFactory.eINSTANCE.createConceptImport();
		ConceptImport ci2 = ConceptsFactory.eINSTANCE.createConceptImport();
		ConceptImport ci3 = ConceptsFactory.eINSTANCE.createConceptImport();
		
		ci1.setImportedNamespace(TEST_CATEGORY_ID_ONE + ".*");
		ci2.setImportedNamespace(TEST_CATEGORY_ID_ONE + "." + TEST_SE_ID_ONE);
		ci3.setImportedNamespace(TEST_CATEGORY_ID_TWO + "." + TEST_SE_ID_ONE);
		
		concept.getImports().add(ci1);
		concept.getImports().add(ci2);
		concept.getImports().add(ci3);
		
		Set<String> importedConceptIds = ActiveConceptHelper.getConceptDependencies(concept);
		
		assertThat("Identified correct concept", importedConceptIds, hasItems(TEST_CATEGORY_ID_ONE, TEST_CATEGORY_ID_TWO));
		assertEquals("Identified correct amount of IDs", 2, importedConceptIds.size());
	}
}
