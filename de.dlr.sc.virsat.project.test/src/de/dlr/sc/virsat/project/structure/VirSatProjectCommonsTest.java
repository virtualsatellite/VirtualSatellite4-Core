/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.structure;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.nature.VirSatProjectNature;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * 
 */
public class VirSatProjectCommonsTest extends AProjectTestCase {
	
	@Override
	protected void addProjectFileStructure() {
		// Don't create the test projects file structure.
		// The actual creation is tested in the test cases.
		// Otherwise test cases fail, since folders are already present
	}
	
	@Test
	public void testCreateProjectStructure() {
		VirSatProjectCommons virSatProject = new VirSatProjectCommons(testProject); 
		
		IFolder dataFolder = testProject.getFolder(VirSatProjectCommons.FOLDERNAME_DATA);
		IFolder unversionedFolder = testProject.getFolder(VirSatProjectCommons.FOLDERNAME_UNVERSIONED);
		assertFalse("Folder does not yet exist", dataFolder.exists());
		assertFalse("Folder does not yet exist", unversionedFolder.exists());
		
		IFile dataFile = dataFolder.getFile(VirSatProjectCommons.FILENAME_EMPTY);
		IFile unversionedFile = unversionedFolder.getFile(VirSatProjectCommons.FILENAME_EMPTY);
		
		assertFalse("File does not yet exist", dataFile.exists());
		assertFalse("File does not yet exist", unversionedFile.exists());
		
		boolean result = virSatProject.createProjectStructure(null);
		
		assertTrue("Method was susccesfully executed", result);
		assertTrue("Folder now exist", dataFolder.exists());
		assertTrue("Folder now exist", unversionedFolder.exists());
		assertTrue("File now exist", dataFile.exists());
		assertTrue("File now exist", unversionedFile.exists());
	}
	
	@Test
	public void testAttachProjectNature() throws CoreException {
		VirSatProjectCommons virSatProject = new VirSatProjectCommons(testProject); 

		boolean result = virSatProject.attachProjectNature();
		
		assertTrue("Method was susccesfully executed", result);
		
		List<String> natures = Arrays.asList(testProject.getDescription().getNatureIds());
		
		assertThat("Nature is in the list", natures, hasItem(VirSatProjectNature.NATURE_ID));
	}
	
	@Test
	public void testCreateFolderStructure() throws CoreException {
		VirSatProjectCommons virSatProject = new VirSatProjectCommons(testProject);
		virSatProject.createProjectStructure(null);
		
		// create a structural element instance which will be added to the project
		// this creates a folder and a file in the folder
		// we check if they were correctly created.
		
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		String seiUuid = sei.getUuid().toString();
		
		String fullFolderNameSei = VirSatProjectCommons.FOLDERNAME_DATA + "/" + VirSatProjectCommons.FOLDERNAME_STRUCTURAL_ELEMENT_PREFIX + seiUuid;
		String fullFolderNameSeiDocuments = fullFolderNameSei + "/" + VirSatProjectCommons.FOLDERNAME_STRUCTURAL_ELEMENT_DOCUMENTS;
		
		IFolder seiFolder = testProject.getFolder(fullFolderNameSei);
		IFolder seiDocumentsFolder = testProject.getFolder(fullFolderNameSeiDocuments);
		IFile seiFile = testProject.getFile(fullFolderNameSei + "/" + VirSatProjectCommons.FILENAME_STRUCTURAL_ELEMENT);
		IFile seiFolderFile = seiFolder.getFile(VirSatProjectCommons.FILENAME_EMPTY);
		IFile seiDocumentsFolderFile = seiDocumentsFolder.getFile(VirSatProjectCommons.FILENAME_EMPTY);
		
		assertFalse("Folder does not yet exist", seiFolder.exists());
		assertFalse("Folder does not yet exist", seiDocumentsFolder.exists());
		assertFalse("File does not yet exist", seiFile.exists());
		assertFalse("File does not yet exist", seiFolderFile.exists());
		assertFalse("File does not yet exist", seiDocumentsFolderFile.exists());
		
		virSatProject.createFolderStructure(sei, null);
		
		assertTrue("Folder does exist now", seiFolder.exists());
		assertTrue("Folder does exist now", seiDocumentsFolder.exists());
		// The actual StructuralElement.dvlm file is not created with this method
		// this code magic happens in 
		// Command initAndAddIsteCommand = resourceSet.initializeStructuralElement(iste, ed);
		assertFalse("File does not exist now", seiFile.exists());
		assertTrue("File does exist now", seiFolderFile.exists());
		assertTrue("File does exist now", seiDocumentsFolderFile.exists());
	}
	
	@Test
	public void testRemoveFolderStructure() throws CoreException {
		VirSatProjectCommons virSatProject = new VirSatProjectCommons(testProject);
		virSatProject.createProjectStructure(null);
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		String seiUuid = sei.getUuid().toString();
		virSatProject.createFolderStructure(sei, null);
		
		String fullFolderNameSei = VirSatProjectCommons.FOLDERNAME_DATA + "/" + VirSatProjectCommons.FOLDERNAME_STRUCTURAL_ELEMENT_PREFIX + seiUuid;
		String fullFolderNameSeiDocuments = fullFolderNameSei + "/" + VirSatProjectCommons.FOLDERNAME_STRUCTURAL_ELEMENT_DOCUMENTS;
		
		assertTrue("Folder does exist", testProject.getFolder(fullFolderNameSei).exists());
		assertTrue("Folder does exist", testProject.getFolder(fullFolderNameSeiDocuments).exists());
		
		virSatProject.removeFolderStructure(sei, null);
		
		assertFalse("Folder does not exist after remove operation", testProject.getFolder(fullFolderNameSei).exists());
		assertFalse("Folder does not exist  after remove operation", testProject.getFolder(fullFolderNameSeiDocuments).exists());
	}
	
	@Test
	public void testGetAllVirSatProjects() throws CoreException {
		// Create a workspace with some projects from which some are VirSat ones
		// and others are not. Call the method and see that we retrieve only
		// the projects which are actual VirSat projects
		IProject project1VirSat =  testProject;
		IProject project2Other = createTestProject("testProject2Other");
		IProject project3VirSat = createTestProject("testProject3");

		VirSatProjectCommons projectCommons1 = new VirSatProjectCommons(project1VirSat);
		VirSatProjectCommons projectCommons3 = new VirSatProjectCommons(project3VirSat);
		
		projectCommons1.attachProjectNature();
		projectCommons3.attachProjectNature();
		
		// Now try to get the projects from the workspace
		IWorkspace ws = ResourcesPlugin.getWorkspace();
		List<IProject> virSatProjects = VirSatProjectCommons.getAllVirSatProjects(ws);
		
		assertThat("Project1 and 3 is contained", virSatProjects, hasItems(project1VirSat, project3VirSat));
		assertThat("Project2 is not contained", virSatProjects, not(hasItem(project2Other)));
		
		// Close one project and check if it is not detected anymore
		project1VirSat.close(null);
		List<IProject> virSatProjects2 = VirSatProjectCommons.getAllVirSatProjects(ws);
		assertThat("Project3 is contained", virSatProjects2, hasItems(project3VirSat));
		assertThat("Project1 and 2 is not contained", virSatProjects2, not(hasItems(project1VirSat, project2Other)));
	}
	
	@Test
	public void testIsVirSatProject() throws CoreException {
		IProject project1VirSat =  testProject;
		IProject project2Other = createTestProject("testProject2Other");
		
		VirSatProjectCommons projectCommons1 = new VirSatProjectCommons(project1VirSat);
		projectCommons1.attachProjectNature();
		
		assertTrue("Project 1 has the VirSat Nature", VirSatProjectCommons.isVirSatProject(project1VirSat));
		assertFalse("Project 2 does not have the VirSat Nature", VirSatProjectCommons.isVirSatProject(project2Other));
	}
	
	@Test
	public void testGetRepositoryFile() {
		VirSatProjectCommons projectCommons = new VirSatProjectCommons(testProject);
		projectCommons.createProjectStructure(null);
		IFile file = projectCommons.getRepositoryFile();
		assertEquals("It is the correct file", VirSatProjectCommons.FILENAME_REPOSITORY, file.getName());
	}

	@Test
	public void testGetUnitManagementFile() {
		VirSatProjectCommons projectCommons = new VirSatProjectCommons(testProject);
		projectCommons.createProjectStructure(null);
		IFile file = projectCommons.getUnitManagementFile();
		assertEquals("It is the correct file", VirSatProjectCommons.FILENAME_UNIT_MANAGEMENT, file.getName());
	}

	@Test
	public void testGetRoleManagementFile() {
		VirSatProjectCommons projectCommons = new VirSatProjectCommons(testProject);
		projectCommons.createProjectStructure(null);
		IFile file = projectCommons.getRoleManagementFile();
		assertEquals("It is the correct file", VirSatProjectCommons.FILENAME_ROLE_MANAGEMENT, file.getName());
	}
	
	@Test
	public void testGetStructuralElementFile() {
		VirSatProjectCommons projectCommons = new VirSatProjectCommons(testProject);
		projectCommons.createProjectStructure(null);
		
		// Create two structural elements which we want to store in the project as well
		StructuralElementInstance sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance sei2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		// Now add the folder and file structures for the structural elements
		projectCommons.createFolderStructure(sei1, null);
		projectCommons.createFolderStructure(sei2, null);
		
		IFile file1 = projectCommons.getStructuralElementInstanceFile(sei1);
		IFile file2 = projectCommons.getStructuralElementInstanceFile(sei2);
		IFile file22 = projectCommons.getStructuralElementInstanceFile(sei2);
		
		assertNotSame("Make sure both are individual files for different ISE", file1, file2);
		assertEquals("Make sure files are the same for the same ISE", file2, file22);
	}
	
	@Test
	public void testGetDocumentFolder() {
		VirSatProjectCommons projectCommons = new VirSatProjectCommons(testProject);
		projectCommons.createProjectStructure(null);
		
		// Create two structural elements which we want to store in the project as well
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei1.setType(se);
		
		// Setting up the resources factory to deal with the model extension
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
	    Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
	    m.put(VirSatProjectCommons.FILENAME_EXTENSION, new XMIResourceFactoryImpl());

	    // Create a resource set for correctly containing the SEI and subcomponents
	    IFile seiFile = projectCommons.getStructuralElementInstanceFile(sei1);
	    URI seiUri = URI.createPlatformResourceURI(seiFile.getFullPath().toString(), true);
	    ResourceSet resSet = new ResourceSetImpl();
		Resource resource = resSet.createResource(seiUri);
		resource.getContents().add(sei1);
		
		// Create one CategoryAssignemnt for the SEI
		Category c = CategoriesFactory.eINSTANCE.createCategory();
		c.setIsApplicableForAll(true);
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ca.setType(c);
		sei1.getCategoryAssignments().add(ca);
		
		assertTrue("Make sure that we actually have the CA assigned", sei1.getCategoryAssignments().contains(ca));
		
		// Now add the folder and file structures for the structural elements
		projectCommons.createFolderStructure(sei1, null);
		
		IFolder folderForSei1 = VirSatProjectCommons.getDocumentFolder(sei1);
		IFolder folderForSeiCa = VirSatProjectCommons.getDocumentFolder(ca);
		
		assertTrue("Folder exists", folderForSei1.exists());
		assertTrue("Folder exists", folderForSeiCa.exists());
		
		assertEquals("Folders point to the same location", folderForSei1, folderForSeiCa);
	}

	@Test
	public void testIsDvlmFileIResource() {
		IFile correctFile = testProject.getFile(new Path("correctFVile.dvlm"));
		IFile incorrectFile = testProject.getFile(new Path("incorrectFVile.other"));
		
		assertTrue("Is DVLM file", VirSatProjectCommons.isDvlmFile(correctFile));
		assertFalse("Is No DVLM file", VirSatProjectCommons.isDvlmFile(incorrectFile));
	}
	
	@Test
	public void testIsDvlmFileEMFResource() {
		Resource dvlmResource = new ResourceImpl();
		dvlmResource.setURI(URI.createFileURI("correctFVile.dvlm"));
		Resource nonDvlmResource = new ResourceImpl();
		nonDvlmResource.setURI(URI.createFileURI("correctFVile.other"));
		
		assertTrue("Is DVLM file", VirSatProjectCommons.isDvlmFile(dvlmResource));
		assertFalse("Is No DVLM file", VirSatProjectCommons.isDvlmFile(nonDvlmResource));
		
		assertFalse("Resource has no URI", VirSatProjectCommons.isDvlmFile(new ResourceImpl()));
		Resource resource = null;
		assertFalse("NUll resource is no DVLM file", VirSatProjectCommons.isDvlmFile(resource));
	}
	
	@Test
	public void testGetStructuralElementInstanceFullPath() {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		String seiUuid = sei.getUuid().toString();
		
		String fullFolderNameSei = VirSatProjectCommons.FOLDERNAME_DATA + "/" + VirSatProjectCommons.FOLDERNAME_STRUCTURAL_ELEMENT_PREFIX + seiUuid + "/" + VirSatProjectCommons.FILENAME_STRUCTURAL_ELEMENT;
		
		String path = VirSatProjectCommons.getStructuralElementInstanceFullPath(sei);
		
		assertEquals("path is correct", fullFolderNameSei, path);
	}
	
	@Test
	public void testGetStructuralElementInstancePath() {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		String seiUuid = sei.getUuid().toString();
		
		String fullFolderNameSei = VirSatProjectCommons.FOLDERNAME_DATA + "/" + VirSatProjectCommons.FOLDERNAME_STRUCTURAL_ELEMENT_PREFIX + seiUuid;
		
		String path = VirSatProjectCommons.getStructuralElementInstancePath(sei);
		
		assertEquals("path is correct", fullFolderNameSei, path);
	}

	@Test
	public void testGetStructuralElementInstanceDocumentPath() {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		String seiUuid = sei.getUuid().toString();
		
		String fullFolderNameSei = VirSatProjectCommons.FOLDERNAME_DATA + "/" + VirSatProjectCommons.FOLDERNAME_STRUCTURAL_ELEMENT_PREFIX + seiUuid + "/" + VirSatProjectCommons.FOLDERNAME_STRUCTURAL_ELEMENT_DOCUMENTS;
		
		String path = VirSatProjectCommons.getStructuralElementInstanceDocumentPath(sei);
		
		assertEquals("path is correct", fullFolderNameSei, path);
	}
	
	@Test
	public void testGetWorkspaceResource() throws IOException, CoreException {
		VirSatResourceSet resSet = VirSatResourceSet.createUnmanagedResourceSet(testProject);
		resSet.getResources().clear();
		VirSatProjectCommons projectCommons = new VirSatProjectCommons(testProject); 
		
		// Build StructuralElement concept for this test. will consist of Definitions and Configurations
		StructuralElement seEd = StructuralFactory.eINSTANCE.createStructuralElement();
		seEd.setIsApplicableForAll(true);
		seEd.setName("Definition");
		StructuralElementInstance seiEdSc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEdSc.setType(seEd);
		
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.getStructuralElements().add(seEd);
		
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		repo.getActiveConcepts().add(concept);
		repo.getRootEntities().add(seiEdSc);
		
		Resource resRepo = resSet.getRepositoryResource();
		Resource resSc = resSet.getStructuralElementInstanceResource(seiEdSc);
		
		resRepo.getContents().add(repo);
		resSc.getContents().add(seiEdSc);
		
		resRepo.save(Collections.EMPTY_MAP);
		resSc.save(Collections.EMPTY_MAP);
		
		IFile fileRepo = projectCommons.getRepositoryFile();
		IFile fileSc = projectCommons.getStructuralElementInstanceFile(seiEdSc);
		
		// Test the getWorkspace method with two files that actually exist.
		assertEquals("Got correct Resource", fileRepo, VirSatProjectCommons.getWorkspaceResource(repo));
		assertEquals("Got correct Resource", fileSc, VirSatProjectCommons.getWorkspaceResource(seiEdSc));
		
		// Now remove both files from the workspace, thus the getWorkspaceResource
		fileRepo.delete(true, null);
		fileSc.delete(true, null);
		
		assertNull("There is no resource in the Workspace anymore", VirSatProjectCommons.getWorkspaceResource(repo));
		assertNull("There is no resource in the Workspace anymore", VirSatProjectCommons.getWorkspaceResource(seiEdSc));
	}
	
	@Test
	public void testCreateFolderWithEmptyFile() throws CoreException {
		VirSatProjectCommons virSatProject = new VirSatProjectCommons(testProject); 
		
		// Define an arbitrary folder and check that it gets well created with the .empty file
		IFolder testFolder = testProject.getFolder("testFolder");
		assertFalse("The fodler does not yet exist", testFolder.exists());
		IFolder returnFolder = virSatProject.createFolderWithEmptyFile(testFolder, new NullProgressMonitor());
		assertTrue("The folder does now exist", testFolder.exists());
		assertTrue("The .empty file also exists", testFolder.getFile(VirSatProjectCommons.FILENAME_EMPTY).exists());
		assertEquals("Method hands back correct folder", testFolder, returnFolder);
		
		// Now call the method on a folder which already exists, only the file should be created
		IFolder testFolder2 = testProject.getFolder("testFolder2");
		testFolder2.create(IResource.NONE, true, new NullProgressMonitor());
		assertTrue("The folder already exists", testFolder2.exists());
		virSatProject.createFolderWithEmptyFile(testFolder2, new NullProgressMonitor());
		assertTrue("The folder does still exist", testFolder2.exists());
		assertTrue("The .empty file also exists", testFolder2.getFile(VirSatProjectCommons.FILENAME_EMPTY).exists());
		
		// now call it a second time on the same folder which already exists with the .empty file
		// Noting special should happen no exception should be thrown
		virSatProject.createFolderWithEmptyFile(testFolder2, new NullProgressMonitor());
		assertTrue("The folder does still exist", testFolder2.exists());
		assertTrue("The .empty file still exists", testFolder2.getFile(VirSatProjectCommons.FILENAME_EMPTY).exists());
	}
	
	@Test
	public void testGetProjectNameByUri() {
		
		java.net.URI javaWsFileUri = testProject.getLocationURI();
		String stringWsFileUri = javaWsFileUri.toString();
		URI emfWsFileUri = URI.createURI(stringWsFileUri);
		URI emfPlatformUri = URI.createPlatformResourceURI(getProjectName(), true);
		
		final String EXPECTED_PROJECT_NAME = getProjectName();
		
		assertEquals("Got correct project name from Java File URI", EXPECTED_PROJECT_NAME, VirSatProjectCommons.getProjectNameByUri(javaWsFileUri));
		assertEquals("Got correct project name from String File URI", EXPECTED_PROJECT_NAME, VirSatProjectCommons.getProjectNameByUri(stringWsFileUri));
		assertEquals("Got correct project name from EMF File URI", EXPECTED_PROJECT_NAME, VirSatProjectCommons.getProjectNameByUri(emfWsFileUri));
		assertEquals("Got correct project name from EMF Platform URI", EXPECTED_PROJECT_NAME, VirSatProjectCommons.getProjectNameByUri(emfPlatformUri));
	}
	
	@Test
	public void testGetProjectByUri() {
		java.net.URI javaWsFileUri = testProject.getLocationURI();
		String stringWsFileUri = javaWsFileUri.toString();
		URI emfWsFileUri = URI.createURI(stringWsFileUri);
		
		assertEquals("Got correct project name from Java URI", testProject, VirSatProjectCommons.getProjectByUri(javaWsFileUri));
		assertEquals("Got correct project name from String URI", testProject, VirSatProjectCommons.getProjectByUri(stringWsFileUri));
		assertEquals("Got correct project name from EMF URI", testProject, VirSatProjectCommons.getProjectByUri(emfWsFileUri));
		
		// Change the segment of the project to try to get a project that does not exist
		URI emfPlatformUri = URI.createPlatformResourceURI(getProjectName() + "_Unknown", true);
		IProject projectUnknown = VirSatProjectCommons.getProjectByUri(emfPlatformUri);
		
		assertNotNull("Got the project which is not known", projectUnknown);
		assertFalse("The project does not yet exist", projectUnknown.exists());
	}
}
