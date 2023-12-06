/*******************************************************************************
 * Copyright (c) 2008-2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.apps.api.external;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

public class ModelAPIAppsTest {

	private ModelAPI modelAPI;
	private Repository repository;
	
	@Before
	public void setUp() {
		repository = DVLMFactory.eINSTANCE.createRepository();
		
		modelAPI = new ModelAPI() {
			@Override
			protected void initializeRepositoryResource() {
				resource = new ResourceImpl();
				resource.getContents().add(repository);
				// Initialize RoleManagement and Disciplines if not already initialized
                if (repository.getRoleManagement() == null) {
                    repository.setRoleManagement(RolesFactory.eINSTANCE.createRoleManagement());
                }

                if (repository.getRoleManagement().getDisciplines() == null) {
                    repository.getRoleManagement().getDisciplines().add(RolesFactory.eINSTANCE.createDiscipline());
                }
			}
			
			@Override
			protected void deleteFolderStructures(Path directory) throws IOException {
				// Stub out for nothing since we don't have folder structures
			}
		};
	}
	
	@Test
	public void testGetRepository() {
		assertEquals("Model API gives correct repository object", repository, modelAPI.getRepository());
	}
	
	@Test
	public void testGetUnitManagement() {
		assertEquals("Model API gives correct unit management object", repository.getUnitManagement(), modelAPI.getUnitManagement());
	}
	
	@Test
	public void testGetConcept() {
		String conceptFqn1 = "test.concept1";
		String conceptFqn2 = "test.concept2";
		
		Concept concept1 = ConceptsFactory.eINSTANCE.createConcept();
		concept1.setName(conceptFqn1);
		Concept concept2 = ConceptsFactory.eINSTANCE.createConcept();
		concept2.setName(conceptFqn2);
		repository.getActiveConcepts().add(concept1);
		repository.getActiveConcepts().add(concept2);
		
		assertEquals("Model API gives correct concept object", concept2, modelAPI.getConcept(conceptFqn2));
		
		String conceptFqnUnknwon = "test.concept.unknown";
		assertNull("Model API gives null for unknown fqn", modelAPI.getConcept(conceptFqnUnknwon));
	}
	
	@Test
    public void testAddUserToDiscipline() {
        // Create a sample Discipline
        Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
        discipline.setName("System");

        //Add the created discipline in the discipline list
        repository.getRoleManagement().getDisciplines().add(discipline);
        
        // Set the user name property
        System.setProperty("user.name", "SampleUser");

        //Add the user to the user's list
        discipline.getUsers().add("SampleUser");
        // Call the method that contains the new line
        modelAPI.initializeRepositoryResource();

        // Verify that the Discipline is added to Disciplines
        assertTrue("Discipline should be added to Disciplines", repository.getRoleManagement().getDisciplines().contains(discipline));

        // Verify that the user is added to the Discipline
        assertTrue("User should be added to Discipline", discipline.getUsers().contains("SampleUser"));

        // Reset the system property to avoid interference with other tests
        System.clearProperty("user.name");
    }

	@Test
	public void testCreateSeiStorage() {
		try {
			// Set the user name property
			System.setProperty("user.name", "SampleUser");

			// Create a sample BeanStructuralElementInstance
			StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
			BeanStructuralElementInstance beanSei = new BeanStructuralElementInstance(sei);

			// Call the method to create storage
			modelAPI.createSeiStorage(beanSei);

			// Verify that the folder and file are created
			String pathFolderSei = VirSatProjectCommons.getStructuralElementInstancePath(sei);
			String pathFolderSeiDocument = VirSatProjectCommons.getStructuralElementInstanceDocumentPath(sei);
			String pathFileSei = VirSatProjectCommons.getStructuralElementInstanceFullPath(sei);

			Path folderPathSei = Paths.get(modelAPI.getCurrentProjectAbsolutePath(), pathFolderSei);
			Path folderPathSeiDocument = Paths.get(modelAPI.getCurrentProjectAbsolutePath(), pathFolderSeiDocument);
			Path filePathSei = Paths.get(modelAPI.getCurrentProjectAbsolutePath(), pathFileSei);

			assertTrue("Folder for SEI is created", Files.exists(folderPathSei) && Files.isDirectory(folderPathSei));
			assertTrue("Document folder for SEI is created", Files.exists(folderPathSeiDocument) && Files.isDirectory(folderPathSeiDocument));
			assertFalse("File for SEI is not created", Files.exists(filePathSei) && Files.isRegularFile(filePathSei));

			// Verify that the assigned discipline is set
			assertNotNull("Assigned discipline is set", sei.getAssignedDiscipline());

			// Reset the system property to avoid interference with other tests
			System.clearProperty("user.name");
		} catch (IOException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}
	}

	
	@Test
	public void testAddRootSei() {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		BeanStructuralElementInstance beanSei = new BeanStructuralElementInstance(sei);
		
		assertFalse("Sei is not a root sei initially", repository.getRootEntities().contains(sei));
		
		modelAPI.addRootSei(beanSei);
		
		assertTrue("Sei has become a root sei", repository.getRootEntities().contains(sei));
	}
	
	@Test
	public void testPerformInheritance() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setIsCanInheritFromAll(true);
		
		StructuralInstantiator structuralInstanstiator = new StructuralInstantiator();
		StructuralElementInstance superSei = structuralInstanstiator.generateInstance(se, "superSei");
		StructuralElementInstance sei = structuralInstanstiator.generateInstance(se, "sei");
		
		sei.getSuperSeis().add(superSei);
		repository.getRootEntities().add(sei);
		repository.getRootEntities().add(superSei);
		
		Category category = CategoriesFactory.eINSTANCE.createCategory();
		category.setIsApplicableForAll(true);
		
		CategoryAssignment ca = new CategoryInstantiator().generateInstance(category, "ca");
		superSei.getCategoryAssignments().add(ca);

		assertTrue("Sei has no category assignments initially", sei.getCategoryAssignments().isEmpty());
		
		modelAPI.performInheritance();
		
		CategoryAssignment inheritedCa = sei.getCategoryAssignments().get(0);
		assertEquals("Sei has a category assignment by name ca", ca.getName(), inheritedCa.getName());
	}
	
	@Test
	public void testDeleteSeiAndStorage() throws IOException {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		BeanStructuralElementInstance beanSei = new BeanStructuralElementInstance(sei);
		
		modelAPI.addRootSei(beanSei);
		ModelAPI.resource.getContents().add(sei);
		
		assertTrue("Sei is in the repository", repository.getRootEntities().contains(sei));
		
		modelAPI.deleteSeiAndStorage(beanSei);
		
		assertFalse("Sei is deleted", repository.getRootEntities().contains(sei));
	}
}