/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.command.AddCommand;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.RolesPackage;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import de.dlr.sc.virsat.model.extension.tests.test.ATestConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.tests.EnumTestEnum;
import de.dlr.sc.virsat.project.resources.command.AssignDisciplineCommand;
import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommand;
import de.dlr.sc.virsat.server.dataaccess.FlattenedCategoryAssignment;
import de.dlr.sc.virsat.server.dataaccess.FlattenedConcept;
import de.dlr.sc.virsat.server.dataaccess.FlattenedDiscipline;
import de.dlr.sc.virsat.server.dataaccess.FlattenedPropertyInstance;
import de.dlr.sc.virsat.server.dataaccess.FlattenedStructuralElementInstance;

public class RepoModelAccessControllerTest extends ATestConceptTestCase {
	
	private RepoModelAccessController repoModelAccessController;
	private TestStructuralElement testSei1;
	private TestStructuralElement testSei2;
	private TestCategoryAllProperty testCa;
	private FlattenedStructuralElementInstance flatTestSei1;
	private FlattenedStructuralElementInstance flatTestSei2;
	private FlattenedCategoryAssignment flatTestCa;
	
	private static final double TEST_VALUE_DOUBLE = 10.5d;
	private static final int TEST_VALUE_INT = 10;
	private static final String TEST_VALUE_STRING = "Hello";
	private static final boolean TEST_VALUE_BOOL = true;
	private static final URI TEST_VALUE_RESOURCE = URI.createPlatformResourceURI("testdata/test.data", true);
	private static final EnumTestEnum TEST_VALUE_ENUM = EnumTestEnum.HIGH;
	
	private static final String TEST_BEAN_NAME = "World";
	
	private static final String NAME = "Updated";
	private static final String DESCRIPTION = "Got updated";
	private static final String READ_ONLY = "Should not change";
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		
		addEditingDomainAndRepository();

		// Load the test concepts
		executeAsCommand(() -> loadTestConcept());
		
		// Save all changes
		rs.saveAllResources(new NullProgressMonitor());

		// Create the controller with the ModelAPI instance
		repoModelAccessController = new RepoModelAccessController(editingDomain);
		
		// Create test seis
		testSei1 = createRootSei("RootSei1");
		testSei2 = createRootSei("RootSei2");
		
		// Create test ca
		testCa = new TestCategoryAllProperty(testConcept);
		testCa.setName("TestCa");
		
		// Add test properties
		testCa.setTestBool(TEST_VALUE_BOOL);
		testCa.setTestFloat(TEST_VALUE_DOUBLE);
		testCa.setTestInt(TEST_VALUE_INT);
		testCa.setTestString(TEST_VALUE_STRING);
		testCa.setTestResource(TEST_VALUE_RESOURCE);
		testCa.setTestEnum(TEST_VALUE_ENUM.getLiteral());
		testCa.setName(TEST_BEAN_NAME);
		
		// Add ca to sei
		executeAsCommand(() -> testSei1.add(testCa));
		
		rs.saveAllResources(new NullProgressMonitor());
		
		flatTestSei1 = new FlattenedStructuralElementInstance(testSei1.getStructuralElementInstance());
		flatTestSei2 = new FlattenedStructuralElementInstance(testSei2.getStructuralElementInstance());
		flatTestCa = new FlattenedCategoryAssignment(testCa.getTypeInstance());
	}
	
	/**
	 * Creates a sei and adds it to the repository
	 * @param name name of the sei
	 * @return the created TestStructuralElement
	 */
	private TestStructuralElement createRootSei(String name) {
		// Create a new TestStructuralElement with a StructuralElementInstance
		TestStructuralElement tsei = new TestStructuralElement(testConcept);
		StructuralElementInstance sei = tsei.getStructuralElementInstance();
		sei.setName(name);
		
		// Now add the new SEI to the Repository
		Command createAddSei = CreateAddSeiWithFileStructureCommand.create(editingDomain, repository, sei);
		editingDomain.getCommandStack().execute(createAddSei);
		
		return tsei;
	}
	
	@Test 
	public void testGetDisciplines() {
		List<FlattenedDiscipline> disciplines = repoModelAccessController.getDisciplines();
		assertEquals("Initially only one system discipline found", 1, disciplines.size());
		
		// Add a discipline
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		RoleManagement rm = rs.getRoleManagement();
		Command addDisciplineCommand = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		editingDomain.getCommandStack().execute(addDisciplineCommand);
		TestStructuralElement testSei = createRootSei("RootSei1");
		Command assignDisciplineCommand = new AssignDisciplineCommand(rs, testSei.getStructuralElementInstance(), discipline);
		editingDomain.getCommandStack().execute(assignDisciplineCommand);
		
		disciplines = repoModelAccessController.getDisciplines();
		assertEquals("Now two disciplines are found", 2, disciplines.size());
		assertThat("Correct discipline found", new FlattenedDiscipline(discipline), is(samePropertyValuesAs(disciplines.get(1))));
	}
	
	@Test
	public void testGetActiveConcepts() {
		List<FlattenedConcept> activeConcepts = repoModelAccessController.getActiveConcepts();
		
		assertEquals("Two concepts found", 2, activeConcepts.size());
	}
	
	@Test
	public void testGetRootSeis() {
		List<FlattenedStructuralElementInstance> seis = repoModelAccessController.getRootSeis();
		assertEquals("Two root seis found", 2, seis.size());
		assertThat("Correct sei found", flatTestSei1, is(samePropertyValuesAs(seis.get(0))));
		assertThat("Correct sei found", flatTestSei2, is(samePropertyValuesAs(seis.get(1))));
	}
	
	@Test
	public void testGetSei() throws CoreException {
		// Get one sei by uuid
		String uuid = testSei1.getStructuralElementInstance().getUuid().toString();
		FlattenedStructuralElementInstance seiByUuid = repoModelAccessController.getSei(uuid);
		assertThat("Right sei found", flatTestSei1, is(samePropertyValuesAs(seiByUuid)));
	}
	
	@Test
	@Ignore
	// TODO: if creating is a high level api task, maybe it makes sense to also handle deletion there?
	// This test case fails if run in alltests because of ui dependencies in CreateRemoveSeiWithFileStructureCommand
	// Will be fixed in seperate ticket
	public void testDeleteSei() throws CoreException, IOException {
		// Delete one sei
		String uuid = testSei1.getStructuralElementInstance().getUuid().toString();
		repoModelAccessController.deleteSei(uuid);
		assertEquals("Only one sei left", 1, repoModelAccessController.getRootSeis().size());	
	}
	
	@Test(expected = NullPointerException.class)
	public void testPutSeiWithNewUuid() throws CoreException, IOException {
		repoModelAccessController.putSei(new FlattenedStructuralElementInstance(), new VirSatUuid().toString());
	}
	
	@Test
	public void testPutUpdateSei() throws CoreException, IOException {
		flatTestSei2.setName(NAME);
		flatTestSei2.setDescription(DESCRIPTION);
		// These should not do anything
		flatTestSei2.setParent(READ_ONLY);
		flatTestSei2.setSeFullQualifiedName(READ_ONLY);
		
		repoModelAccessController.putSei(flatTestSei2, flatTestSei2.getUuid());
		FlattenedStructuralElementInstance updatedSei = repoModelAccessController.getSei(flatTestSei2.getUuid());
		
		assertEquals("Name changed correctly", NAME, updatedSei.getName());
		assertEquals("Description changed correctly", DESCRIPTION, updatedSei.getDescription());
		assertNotEquals("This value can't be changed by the API", READ_ONLY, updatedSei.getParent());
		assertNotEquals("This value can't be changed by the API", READ_ONLY, updatedSei.getSeFullQualifiedName());
	}
	
	@Test
	public void testPutUpdateSeiDiscipline() throws CoreException, IOException {
		assertEquals("Sei has no discipline initally", flatTestSei1.getDiscipline(), null);
		String uuid = repository.getRoleManagement().getDisciplines().get(0).getUuid().toString();
		flatTestSei1.setDiscipline(uuid);
		
		FlattenedStructuralElementInstance updatedSei1;
		
		repoModelAccessController.putSei(flatTestSei1, flatTestSei1.getUuid());
		updatedSei1 = repoModelAccessController.getSei(flatTestSei1.getUuid());
		assertEquals("Discipline got posted", updatedSei1.getDiscipline(), uuid);
		
		flatTestSei1.setDiscipline(null);
		
		repoModelAccessController.putSei(flatTestSei1, flatTestSei1.getUuid());
		updatedSei1 = repoModelAccessController.getSei(flatTestSei1.getUuid());
		assertEquals("Discipline got removed", updatedSei1.getDiscipline(), null);
	}
	
	@Test
	public void testPutUpdateSeiChildren() throws CoreException, IOException {
		assertTrue("Parent has no children", flatTestSei1.getChildren().isEmpty());
		assertEquals("Child has no parent yet", flatTestSei2.getParent(), null);
		
		flatTestSei1.setChildren(Arrays.asList(flatTestSei2.getUuid()));
		
		FlattenedStructuralElementInstance updatedSei1;
		FlattenedStructuralElementInstance updatedSei2;
		
		repoModelAccessController.putSei(flatTestSei1, flatTestSei1.getUuid());
		updatedSei1 = repoModelAccessController.getSei(flatTestSei1.getUuid());
		updatedSei2 = repoModelAccessController.getSei(flatTestSei2.getUuid());
		
		assertEquals("Parent in model has one children", updatedSei1.getChildren().size(), 1);
		assertEquals("Child in model has a parent yet", updatedSei2.getParent(), updatedSei1.getUuid());
		
		flatTestSei1.setChildren(new ArrayList<String>());
		
		repoModelAccessController.putSei(flatTestSei1, flatTestSei1.getUuid());
		updatedSei1 = repoModelAccessController.getSei(flatTestSei1.getUuid());
		updatedSei2 = repoModelAccessController.getSei(flatTestSei2.getUuid());
		
		assertTrue("Parent in models child got removed successfully", updatedSei1.getChildren().isEmpty());
		assertEquals("Child in model parent got also removed", updatedSei2.getParent(), null);
	}
	
	@Test
	public void testPutUpdateSeiSuperSeis() throws CoreException, IOException {
		assertTrue("Sei has no super seis", flatTestSei1.getSuperSeis().isEmpty());
		
		flatTestSei1.setSuperSeis(Arrays.asList(flatTestSei2.getUuid()));
		
		FlattenedStructuralElementInstance updatedSei1;
		
		repoModelAccessController.putSei(flatTestSei1, flatTestSei1.getUuid());
		updatedSei1 = repoModelAccessController.getSei(flatTestSei1.getUuid());
		
		assertEquals("Sei has super sei now", updatedSei1.getSuperSeis().size(), 1);
		
		flatTestSei1.setSuperSeis(new ArrayList<String>());
		
		repoModelAccessController.putSei(flatTestSei1, flatTestSei1.getUuid());
		updatedSei1 = repoModelAccessController.getSei(flatTestSei1.getUuid());
		
		assertTrue("Super sei got deleted", updatedSei1.getSuperSeis().isEmpty());
	}
	
	@Test
	public void testPutUpdateSeiCategoryAssignments() throws CoreException, IOException {
		assertTrue("Sei has no cas", flatTestSei2.getCategoryAssignments().isEmpty());
		
		flatTestSei2.setCategoryAssignments(Arrays.asList(testCa.getUuid()));
		
		FlattenedStructuralElementInstance updatedSei;
		
		repoModelAccessController.putSei(flatTestSei2, flatTestSei2.getUuid());
		updatedSei = repoModelAccessController.getSei(flatTestSei2.getUuid());
		
		assertEquals("Sei has ca now", updatedSei.getCategoryAssignments().size(), 1);
		
		flatTestSei1.setCategoryAssignments(new ArrayList<String>());
		
		repoModelAccessController.putSei(flatTestSei2, flatTestSei2.getUuid());
		updatedSei = repoModelAccessController.getSei(flatTestSei2.getUuid());
		
		assertTrue("Ca got deleted", updatedSei.getSuperSeis().isEmpty());
	}
	
	@Test
	public void testGetCaAndProperties() throws CoreException {
		FlattenedCategoryAssignment caByUuid = repoModelAccessController.getCa(testCa.getUuid());
		assertEquals("Right ca found", flatTestCa.getFullQualifiedInstanceName(), caByUuid.getFullQualifiedInstanceName());
		
		List<FlattenedPropertyInstance> flattenedProperties = flatTestCa.getProperties();
		
		PropertyInstanceValueSwitch valueSwitch = new PropertyInstanceValueSwitch();
		valueSwitch.setShowUnitForUnitValues(false);
		valueSwitch.setShowResourceOnlyLastSegment(false);
		valueSwitch.setShowEnumValueDefinitionValues(false);
		
		// Check for the expected values
		assertThat(flattenedProperties, matchItem(testCa.getTestBoolBean(), valueSwitch));
		assertThat(flattenedProperties, matchItem(testCa.getTestFloatBean(), valueSwitch));
		assertThat(flattenedProperties, matchItem(testCa.getTestIntBean(), valueSwitch));
		assertThat(flattenedProperties, matchItem(testCa.getTestStringBean(), valueSwitch));
		assertThat(flattenedProperties, matchItem(testCa.getTestResourceBean(), valueSwitch));
		assertThat(flattenedProperties, matchItem(testCa.getTestEnumBean(), valueSwitch));
	}
	
	private Matcher<Iterable<? super FlattenedPropertyInstance>> matchItem(ABeanObject<?> bean, PropertyInstanceValueSwitch valueSwitch) {
		return hasItem(hasProperty("value", equalTo(valueSwitch.getValueString(bean.getATypeInstance()))));
	}
	
	// TODO: shorter test setup
	@Test(expected = NullPointerException.class)
	public void testPutNewCa() throws CoreException, IOException {
		repoModelAccessController.putCa(new FlattenedCategoryAssignment(), new VirSatUuid().toString());
	}
	
	@Test
	public void testPutUpdateCaAndProperties() throws CoreException, IOException {
		flatTestCa.setName(NAME);
		flatTestCa.setType(READ_ONLY);
		flatTestCa.setFullQualifiedInstanceName(READ_ONLY);
		
		// Change properties
		List<FlattenedPropertyInstance> properties = flatTestCa.getProperties();
		
		// TODO:
		int boolIdx = -1;
		int intIdx = -1;
		int floatIdx = -1;
		int stringIdx = -1;
		int resourceIdx = -1;
		int enumIdx = -1;
		
		for (int i = 0; i < properties.size(); i++) {
			String name = properties.get(i).getTypeFullQualifiedInstanceName();
			if (name.contains("Float")) {
				floatIdx = i;
			} else if (name.contains("Bool")) {
				boolIdx = i;
			} else if (name.contains("Int")) {
				intIdx = i;
			} else if (name.contains("String")) {
				stringIdx = i;
			} else if (name.contains("Resource")) {
				resourceIdx = i;
			} else if (name.contains("Enum")) {
				enumIdx = i;
			}
		}
		
		FlattenedPropertyInstance testBool = properties.get(boolIdx);
		FlattenedPropertyInstance testFloat = properties.get(floatIdx);
		FlattenedPropertyInstance testInt = properties.get(intIdx);
		FlattenedPropertyInstance testString = properties.get(stringIdx);
		FlattenedPropertyInstance testResource = properties.get(resourceIdx);
		FlattenedPropertyInstance testEnum = properties.get(enumIdx);
		
		String newBool = Boolean.toString(false);
		String newFloat = Double.toString(1.2);
		String newInt = Integer.toString(666);
		String newString = "New value";
		String newResource = URI.createPlatformResourceURI("testdata/new_test.data", true).toString();
		String newEnum = EnumTestEnum.INCREDIBLE.getName();
		String newUnit = "Gram";
		
		testFloat.setFullQualifiedInstanceName(READ_ONLY);
		testFloat.setTypeFullQualifiedInstanceName(READ_ONLY);
		testFloat.setUnitName(newUnit);
		testFloat.setQuanitityKindName(READ_ONLY);
		
		testBool.setValue(newBool);
		testFloat.setValue(newFloat);
		testInt.setValue(newInt);
		testString.setValue(newString);
		testResource.setValue(newResource);
		testEnum.setValue(newEnum);
		
		repoModelAccessController.putCa(flatTestCa, flatTestCa.getUuid());
		FlattenedCategoryAssignment updatedCa = repoModelAccessController.getCa(flatTestCa.getUuid());
		
		// CA
		assertEquals("Name changed correctly", NAME, updatedCa.getName());
		assertNotEquals("This value can't be changed by the API", READ_ONLY, updatedCa.getType());
		assertNotEquals("This value can't be changed by the API", READ_ONLY, updatedCa.getFullQualifiedInstanceName());
		
		// Property
		List<FlattenedPropertyInstance> updatedProperties = updatedCa.getProperties();
		FlattenedPropertyInstance updatedFloat = updatedProperties.get(floatIdx);
		assertNotEquals("This value can't be changed by the API", READ_ONLY, updatedFloat.getUuid());
		assertNotEquals("This value can't be changed by the API", READ_ONLY, updatedFloat.getFullQualifiedInstanceName());
		assertNotEquals("This value can't be changed by the API", READ_ONLY, updatedFloat.getTypeFullQualifiedInstanceName());
		
		// Property values
		assertEquals("Boolean property value changed correctly", newBool, updatedProperties.get(boolIdx).getValue());
		assertEquals("Float property value changed correctly", newFloat + "00", updatedFloat.getValue());
		assertEquals("Int property value changed correctly", newInt, updatedProperties.get(intIdx).getValue());
		assertEquals("String property value changed correctly", newString, updatedProperties.get(stringIdx).getValue());
		assertEquals("Resource property value changed correctly", newResource, updatedProperties.get(resourceIdx).getValue());
		assertEquals("Enum property value changed correctly", newEnum, updatedProperties.get(enumIdx).getValue());
	
		// QudvTypeProperty specific
		assertEquals("Unit changed correctly", newUnit, updatedFloat.getUnitName());
		assertNotEquals("This value can't be changed by the API", READ_ONLY, updatedFloat.getQuanitityKindName());
		
	}
	
	// TODO: resource to get and update property instances?
}
