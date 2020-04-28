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
	TestStructuralElement testSei1;
	TestStructuralElement testSei2;
	FlattenedStructuralElementInstance flatTestSei1;
	FlattenedStructuralElementInstance flatTestSei2;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		
		addEditingDomainAndRepository();

		// Load the test concepts
		// TODO: use the right command here
		executeAsCommand(() -> loadTestConcept());
		
		// Save all changes
		rs.saveAllResources(new NullProgressMonitor());

		// Create the controller with the ModelAPI instance
		repoModelAccessController = new RepoModelAccessController(editingDomain);
		
		// Create Test Seis
		testSei1 = createRootSei("RootSei1");
		testSei2 = createRootSei("RootSei2");
		rs.saveAllResources(new NullProgressMonitor());
		
		flatTestSei1 = new FlattenedStructuralElementInstance(testSei1.getStructuralElementInstance());
		flatTestSei2 = new FlattenedStructuralElementInstance(testSei2.getStructuralElementInstance());
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
	public void testCaAndProperties() throws CoreException {
		// Create a new TestStructuralElement with a StructuralElementInstance
		TestStructuralElement tsei = new TestStructuralElement(testConcept);
		StructuralElementInstance sei = tsei.getStructuralElementInstance();
		sei.setName("RootSei");
		
		// Now add the new SEI to the Repository
		Command createAddSei = CreateAddSeiWithFileStructureCommand.create(editingDomain, repository, sei);
		editingDomain.getCommandStack().execute(createAddSei);
		
		// Create ca with test properties
		TestCategoryAllProperty testCa = new TestCategoryAllProperty(testConcept);
		testCa.setName("TestCa");
		
		// Add test properties
		final double TEST_VALUE_DOUBLE = 10.5d;
		final int TEST_VALUE_INT = 10;
		final String TEST_VALUE_STRING = "Hello";
		final boolean TEST_VALUE_BOOL = true;
		final URI TEST_VALUE_RESOURCE = URI.createPlatformResourceURI("testdata/test.data", true);
		final String TEST_BEAN_NAME = "World";
		final EnumTestEnum TEST_VALUE_ENUM = EnumTestEnum.HIGH;
		
		testCa.setTestBool(TEST_VALUE_BOOL);
		testCa.setTestFloat(TEST_VALUE_DOUBLE);
		testCa.setTestInt(TEST_VALUE_INT);
		testCa.setTestString(TEST_VALUE_STRING);
		testCa.setTestResource(TEST_VALUE_RESOURCE);
		testCa.setTestEnum(TEST_VALUE_ENUM.getLiteral());
		testCa.setName(TEST_BEAN_NAME);
		
		// Add ca to sei
		executeAsCommand(() -> tsei.add(testCa));
		
		FlattenedCategoryAssignment flatTestCa = new FlattenedCategoryAssignment(testCa.getTypeInstance());
		FlattenedCategoryAssignment caByUuid = repoModelAccessController.getCa(testCa.getUuid());
		assertEquals("Right ca found", flatTestCa.getFullQualifiedInstanceName(), caByUuid.getFullQualifiedInstanceName());
		
		List<FlattenedPropertyInstance> flattenedProperties = flatTestCa.getProperties();
		
		// Check for the expected values
		assertThat(flattenedProperties, matchItem(testCa.getTestBoolBean()));
		assertThat(flattenedProperties, matchItem(testCa.getTestFloatBean()));
		assertThat(flattenedProperties, matchItem(testCa.getTestIntBean()));
		assertThat(flattenedProperties, matchItem(testCa.getTestStringBean()));
		assertThat(flattenedProperties, matchItem(testCa.getTestResourceBean()));
		assertThat(flattenedProperties, matchItem(testCa.getTestEnumBean()));
	}

	private Matcher<Iterable<? super FlattenedPropertyInstance>> matchItem(ABeanObject<?> bean) {
		PropertyInstanceValueSwitch valueSwitch = new PropertyInstanceValueSwitch();
		return hasItem(hasProperty("value", equalTo(valueSwitch.getValueString(bean.getATypeInstance()))));
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
	
	@Test
	public void testPutUpdateSei() throws CoreException, IOException {
		final String NAME = "Updated Sei";
		final String DESCRIPTION = "This Sei got updated";
		final String READ_ONLY = "Should not change";
		
		flatTestSei2.setName(NAME);
		flatTestSei2.setDescription(DESCRIPTION);
		// These should not do anything
		flatTestSei2.setParent(READ_ONLY);
		flatTestSei2.setSeFullQualifiedName(READ_ONLY);
		
		repoModelAccessController.putSei(flatTestSei2);
		FlattenedStructuralElementInstance updatedSei = repoModelAccessController.getSei(flatTestSei2.getUuid());
		
		assertEquals("Name changed correctly", NAME, updatedSei.getName());
		assertEquals("Description changed correctly", DESCRIPTION, updatedSei.getDescription());
		assertNotEquals("This value can't be changed by the API", READ_ONLY, updatedSei.getParent());
		assertNotEquals("This value can't be changed by the API", READ_ONLY, updatedSei.getSeFullQualifiedName());
	}
	
	@Test(expected = NullPointerException.class)
	public void testPutNewSei() throws CoreException, IOException {
		repoModelAccessController.putSei(new FlattenedStructuralElementInstance());
	}
	
	@Test
	public void testPutUpdateSeiChildren() throws CoreException, IOException {
		assertTrue("Parent has no children", flatTestSei1.getChildren().isEmpty());
		assertEquals("Child has no parent yet", flatTestSei2.getParent(), null);
		
		flatTestSei1.setChildren(Arrays.asList(flatTestSei2.getUuid()));
		
		FlattenedStructuralElementInstance updatedSei1;
		FlattenedStructuralElementInstance updatedSei2;
		
		repoModelAccessController.putSei(flatTestSei1);
		updatedSei1 = repoModelAccessController.getSei(flatTestSei1.getUuid());
		updatedSei2 = repoModelAccessController.getSei(flatTestSei2.getUuid());
		
		assertEquals("Parent in model has one children", updatedSei1.getChildren().size(), 1);
		assertEquals("Child in model has a parent yet", updatedSei2.getParent(), updatedSei1.getUuid());
		
		flatTestSei1.setChildren(new ArrayList<String>());
		
		repoModelAccessController.putSei(flatTestSei1);
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
		
		repoModelAccessController.putSei(flatTestSei1);
		updatedSei1 = repoModelAccessController.getSei(flatTestSei1.getUuid());
		
		assertEquals("Sei has super sei now", updatedSei1.getSuperSeis().size(), 1);
		
		flatTestSei1.setSuperSeis(new ArrayList<String>());
		
		repoModelAccessController.putSei(flatTestSei1);
		updatedSei1 = repoModelAccessController.getSei(flatTestSei1.getUuid());
		
		assertTrue("Super sei got deleted", updatedSei1.getSuperSeis().isEmpty());
	}
	
	@Test
	public void testPutUpdateSeiCategoryAssignments() throws CoreException, IOException {
		assertTrue("Sei has no cas", flatTestSei1.getCategoryAssignments().isEmpty());
		
		TestCategoryAllProperty testCa = new TestCategoryAllProperty(testConcept);
		testCa.setName("TestCa");
		// TODO: Add the ca to the other sei so it get's created
		executeAsCommand(() -> testSei2.add(testCa));
		flatTestSei1.setCategoryAssignments(Arrays.asList(testCa.getUuid()));
		
		FlattenedStructuralElementInstance updatedSei1;
		
		repoModelAccessController.putSei(flatTestSei1);
		updatedSei1 = repoModelAccessController.getSei(flatTestSei1.getUuid());
		
		assertEquals("Sei has ca now", updatedSei1.getCategoryAssignments().size(), 1);
		
		flatTestSei1.setCategoryAssignments(new ArrayList<String>());
		
		repoModelAccessController.putSei(flatTestSei1);
		updatedSei1 = repoModelAccessController.getSei(flatTestSei1.getUuid());
		
		assertTrue("Ca got deleted", updatedSei1.getSuperSeis().isEmpty());
	}
}
