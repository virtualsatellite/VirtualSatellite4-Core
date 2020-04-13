/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.apps.api.external;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.allOf;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanA;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElementOther;
import de.dlr.sc.virsat.model.extension.tests.test.ATestConceptTestCase;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * This class tests the model API functionalities.
 * @author fisc_ph
 *
 */

public class ModelAPITest extends ATestConceptTestCase {

	private ModelAPI modelAPI;
	private UnitManagement virSatUnitManagement;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addResourceSetAndRepository();
		loadTestConcept();

		// Load the concept to create the test object
		rs.saveAllResources(new NullProgressMonitor());
		relaodResourceSet();
		
		modelAPI = new ModelAPI() {			
			@Override
			public String getCurrentProjectAbsolutePath() {
				return testProject.getLocation().toString();
			}
		};
	}
	
	/**
	 * Call this method to reload all resources in the virsat resource set once they have been saved in modelAPI.
	 * This call unloads all resources, reloads them from disk and updates the test variables to what has been read
	 */
	private void relaodResourceSet() {
		rs.realoadAll();
		repository = rs.getRepository();
		virSatUnitManagement = rs.getUnitManagement();
		maturityConcept = repository.getActiveConcepts().get(1);
		testConcept = repository.getActiveConcepts().get(2);
	}

	@Test
	public void testGetUnitManagement() {
		assertEquals("Unit management correctly retrieved", virSatUnitManagement.getUuid(), modelAPI.getUnitManagement().getUuid());
	}

	@Test
	public void testGetConcept() {
		assertEquals("Concept correctly retrieved", testConcept.getFullQualifiedName(), modelAPI.getConcept("de.dlr.sc.virsat.model.extension.tests").getFullQualifiedName());
		assertNull("Concept that does not exist correctly not found", modelAPI.getConcept("sh<kdjfk"));
	}

	@Test
	public void testAddRootSei() throws IOException {
		TestStructuralElement tsei = new TestStructuralElement(testConcept);
		
		assertTrue("No rootEntitiy yet", repository.getRootEntities().isEmpty());
		
		modelAPI.createSeiStorage(tsei);
		modelAPI.addRootSei(tsei);
		modelAPI.saveAll();
		
		relaodResourceSet();
		
		assertEquals("Correct amount of elements", 1, repository.getRootEntities().size());
		assertEquals("Correct Element is placed", repository.getRootEntities().get(0).getUuid(), tsei.getStructuralElementInstance().getUuid());
	}

	@Test
	public void testPerformInheritance() {
		TestStructuralElement tsei1 = new TestStructuralElement(testConcept);
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);

		modelAPI.addRootSei(tsei1);
		modelAPI.addRootSei(tsei2);
		
		tsei2.addSuperSei(tsei1);
		
		TestCategoryBeanA tca = new TestCategoryBeanA(testConcept);
		tsei1.add(tca);
		
		assertTrue("Category not yet propagated", tsei2.getAll(TestCategoryBeanA.class).isEmpty());

		modelAPI.performInheritance();
		
		assertFalse("Category now actually propagated", tsei2.getAll(TestCategoryBeanA.class).isEmpty());
	}

	@Test
	public void testGetRootSeis() {
		TestStructuralElement tsei1 = new TestStructuralElement(testConcept);
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		
		modelAPI.addRootSei(tsei1);
		modelAPI.addRootSei(tsei2);
		
		assertThat("Correct elements", modelAPI.getRootSeis(TestStructuralElement.class), hasItems(tsei1, tsei2));
		assertTrue("Non existing root elements correctly dont exist", modelAPI.getRootSeis(TestStructuralElementOther.class).isEmpty());
	}
	
	@Test
	public void testCreateSeiStorage() throws IOException, CoreException {
		TestStructuralElement tsei = new TestStructuralElement(testConcept);

		IFolder folderNotExist = projectCommons.getStructuralElemntInstanceFolder(tsei.getStructuralElementInstance());
		assertFalse("The folder does not yet exist", folderNotExist.exists());
		
		modelAPI.createSeiStorage(tsei);
		
		// Now check if folders got created accordingly
		testProject.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		IFolder folderExist = projectCommons.getStructuralElemntInstanceFolder(tsei.getStructuralElementInstance());
		
		assertTrue("The folder exists", folderExist.exists());
	}
	
	@Test
	public void getSeiStorageDocumentPath() throws CoreException, IOException {
		TestStructuralElement tsei = new TestStructuralElement(testConcept);
		
		String path = modelAPI.getSeiStorageDocumentPath(tsei);
		
		assertThat("Path is correct", path, allOf(
			containsString(TEST_PROJECT_NAME),
			containsString(tsei.getStructuralElementInstance().getUuid().toString()),
			endsWith(VirSatProjectCommons.FOLDERNAME_STRUCTURAL_ELEMENT_DOCUMENTS)
		));
		
		// Now create the SEI and check that the folder got created
		modelAPI.createSeiStorage(tsei);
		testProject.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		IFolder folderExist = projectCommons.getStructuralElemntInstanceFolder(tsei.getStructuralElementInstance());
		assertTrue("The folder exists", folderExist.exists());		

		// now try to use the full path to access the folder
		File filePath = new File(path);
		assertTrue("Path is valid to access folder", filePath.exists());
	}
	
	@Test
	public void testDeleteAllRootSeiAndStorage() throws CoreException, IOException {
		TestStructuralElement tsei = new TestStructuralElement(testConcept);
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		TestStructuralElement tsei3 = new TestStructuralElement(testConcept);
		
		IFolder folderNotExist = projectCommons.getStructuralElemntInstanceFolder(tsei.getStructuralElementInstance());
		assertFalse("The folder does not yet exist", folderNotExist.exists());
		
		modelAPI.createSeiStorage(tsei);
		modelAPI.addRootSei(tsei);
		modelAPI.createSeiStorage(tsei2);
		modelAPI.addRootSei(tsei2);
		modelAPI.createSeiStorage(tsei3);
		tsei2.add(tsei3);
		
		// Now check if folders got created accordingly
		testProject.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		IFolder folderExist = projectCommons.getStructuralElemntInstanceFolder(tsei.getStructuralElementInstance());
		assertTrue("The folder exists", folderExist.exists());
		
		// Now delete the SEI and check if the folder disappeared
		modelAPI.deleteAllRootSeiAndStorage();
		
		assertTrue("There is no root sei anymore", modelAPI.getRootSeis(TestStructuralElement.class).isEmpty());

		testProject.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		IFolder folderDisappeared = projectCommons.getStructuralElemntInstanceFolder(tsei.getStructuralElementInstance());
		assertFalse("The folder got deleted", folderDisappeared.exists());

		IFolder folderDisappeared2 = projectCommons.getStructuralElemntInstanceFolder(tsei2.getStructuralElementInstance());
		assertFalse("The folder got deleted", folderDisappeared2.exists());
		
		IFolder folderDisappeared3 = projectCommons.getStructuralElemntInstanceFolder(tsei3.getStructuralElementInstance());
		assertFalse("The folder got deleted", folderDisappeared3.exists());
	}
	
	@Test
	public void testDeleteSeiAndStorageBeanSei() throws IOException, CoreException {
		TestStructuralElement tsei = new TestStructuralElement(testConcept);
		
		IFolder folderNotExist = projectCommons.getStructuralElemntInstanceFolder(tsei.getStructuralElementInstance());
		assertFalse("The folder does not yet exist", folderNotExist.exists());
		
		modelAPI.createSeiStorage(tsei);
		modelAPI.addRootSei(tsei);
		
		// Now check if folders got created accordingly
		testProject.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		IFolder folderExist = projectCommons.getStructuralElemntInstanceFolder(tsei.getStructuralElementInstance());
		assertTrue("The folder exists", folderExist.exists());
		
		// Now delete the SEI and check if the folder disappeared
		modelAPI.deleteSeiAndStorage(tsei);
		
		assertTrue("There is no root sei anymore", modelAPI.getRootSeis(TestStructuralElement.class).isEmpty());

		testProject.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		IFolder folderDisappeared = projectCommons.getStructuralElemntInstanceFolder(tsei.getStructuralElementInstance());
		assertFalse("The folder got deleted", folderDisappeared.exists());
	}
		
	@Test
	public void testDeleteSeiAndStorageSei() throws IOException, CoreException {
		TestStructuralElement tsei = new TestStructuralElement(testConcept);
		
		IFolder folderNotExist = projectCommons.getStructuralElemntInstanceFolder(tsei.getStructuralElementInstance());
		assertFalse("The folder does not yet exist", folderNotExist.exists());
		
		modelAPI.createSeiStorage(tsei);
		modelAPI.addRootSei(tsei);
		
		// Now check if folders got created accordingly
		testProject.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		IFolder folderExist = projectCommons.getStructuralElemntInstanceFolder(tsei.getStructuralElementInstance());
		assertTrue("The folder exists", folderExist.exists());
		
		// Now delete the SEI and check if the folder disappeared
		modelAPI.deleteSeiAndStorage(tsei.getStructuralElementInstance());
		
		assertTrue("There is no root sei anymore", modelAPI.getRootSeis(TestStructuralElement.class).isEmpty());

		testProject.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		IFolder folderDisappeared = projectCommons.getStructuralElemntInstanceFolder(tsei.getStructuralElementInstance());
		assertFalse("The folder got deleted", folderDisappeared.exists());
	}
}
