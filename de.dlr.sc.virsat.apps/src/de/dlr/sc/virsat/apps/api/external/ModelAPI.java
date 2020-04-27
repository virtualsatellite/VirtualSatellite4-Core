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

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.util.BeanStructuralElementInstanceHelper;
import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritanceCopier;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * This class provides simplified access to the model
 * without dealing to much with EMF etc.
*/
public class ModelAPI {

	protected static ResourceSet resourceSet;
	private static Resource resource;
	private static final String JAVA_SYSTEM_PROPERTY_WORKING_DIR = "user.dir";
	
	/**
	 * Constructor for the API that automatically loads the DVLM model
	 * @throws CoreException 
	 */
	public ModelAPI() {
		initialize();
	}
	
	/**
	 * This method uses the plain java api to load the resource and resourceset from the file system
	 */
	protected void initialize() {
		resourceSet = new ResourceSetImpl();
		
		// Setting up the resources factory to deal with the model extension
		Resource.Factory.Registry resourceRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = resourceRegistry.getExtensionToFactoryMap();
		m.put(VirSatProjectCommons.FILENAME_EXTENSION, new XMIResourceFactoryImpl());

		DVLMPackage.eINSTANCE.eClass();

		System.out.println("---------------- System properties: ----------");
		System.getProperties().forEach((key, value) -> {
			System.out.println("SysProp: " + key + ": " + value);
		});
		System.out.println("---------------- App output: --------------");

		String resourceFullPath = Paths.get(getCurrentProjectAbsolutePath(), VirSatProjectCommons.FOLDERNAME_DATA + "/" + VirSatProjectCommons.FILENAME_REPOSITORY).toAbsolutePath().toString();
		URI modelUri = URI.createFileURI(resourceFullPath);

		resource = resourceSet.getResource(modelUri, true);
	}
	
	/**
	 * Call this method to save all your modifications
	 * @throws IOException may be thrown if saving was not possible
	 */
	public void saveAll() throws IOException {
		for (Resource res : resourceSet.getResources()) {
			res.save(Collections.EMPTY_MAP);
		}
	}
	
	/**
	 * Example Method to give simplified access to repository
	 * @return the DVLm Repository
	 */
	public Repository getRepository() {
		return (Repository) resource.getContents().get(0);
	}
	
	/**
	 * Example Method of the API to give access to the Unit Management
	 * @return The Unit Management of the current Model
	 */
	public UnitManagement getUnitManagement() {
		return getRepository().getUnitManagement();
	}
	
	/**
	 * Call this method to get a currently active concept
	 * @param conceptFqn The ID of the concept which should be handed back from the study repository
	 * @return The concept in case it was found, otherwise null
	 */
	public Concept getConcept(String conceptFqn) {
		
		for (Concept concept : getRepository().getActiveConcepts()) {
			if (concept.getFullQualifiedName().equals(conceptFqn)) {
				return concept;
			}
		}
		
		return null;
	}

	/**
	 * Call this method to delete all root SEIs and its containing SEIs. the delete also
	 * removes all file structures.
	 * @throws IOException in case files cannot be deleted
	 */
	public void deleteAllRootSeiAndStorage() throws IOException {
		List<StructuralElementInstance> deleteRootSeis = new ArrayList<>(getRepository().getRootEntities());
		for (StructuralElementInstance deleteRootSei : deleteRootSeis) {
			deleteSeiAndStorage(deleteRootSei);
		}
	}
	
	/**
	 * Delete a given Bean and all its file structures.
	 * @param beanSei the BeanStructuralElementInstance to be deleted
	 * @throws IOException in case files cannot be deleted
	 */
	public void deleteSeiAndStorage(IBeanStructuralElementInstance beanSei) throws IOException {
		deleteSeiAndStorage(beanSei.getStructuralElementInstance());
	}
		
	/**
	 * Delete a given Bean and all its file structures.
	 * @param sei the StructuralElementInstance to be deleted
	 * @throws IOException in case files cannot be deleted
	 */
	public void deleteSeiAndStorage(StructuralElementInstance sei) throws IOException {
		List<StructuralElementInstance> seisToBeDeleted = sei.getDeepChildren();
		seisToBeDeleted.add(sei);

		VirSatEcoreUtil.delete(sei);
		
		for (StructuralElementInstance seiToBeDeleted : seisToBeDeleted) {
			String pathFolderSei = VirSatProjectCommons.getStructuralElementInstancePath(seiToBeDeleted);
			String pathFileSei = VirSatProjectCommons.getStructuralElementInstanceFullPath(seiToBeDeleted);

			// Delete the folder of the SEI
			deleteFolderStructures(Paths.get(getCurrentProjectAbsolutePath(), pathFolderSei));

			String resourceFullPath = Paths.get(getCurrentProjectAbsolutePath(), pathFileSei).toAbsolutePath().toString();
			URI modelUri = URI.createFileURI(resourceFullPath);

			Resource resource = resourceSet.getResource(modelUri, false);
			resourceSet.getResources().remove(resource);
		}
	}
	
	/**
	 * Private Method that takes care of deleting a folder and all its containing
	 * files and sub folders. this method is used together with deleting the SEIs from
	 * the file system and the study within this simple ModelAPI.
	 * @param directory The directory to be removed
	 * @throws IOException Exception in case a file or directory could not be removed
	 */
	private void deleteFolderStructures(Path directory) throws IOException {
		Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path subFile, BasicFileAttributes basicFileAttributes) throws IOException {
				Files.delete(subFile);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path subDirectory, IOException ioException) throws IOException {
				// recursive Call to delete the next Directory
				if (subDirectory != directory) {
					deleteFolderStructures(subDirectory);
				}
				return FileVisitResult.CONTINUE;
			}
		});

		// Finally delete the directory.
		Files.delete(directory);
	}
	
	/**
	 * Call this Method to prepare the file storage of a new StructuralElementInstance.
	 * The method takes care of creating the correct file structure etc.
	 * @param beanSei The Bean StructuralElementInstance which is new, not yet persisted and needs a file resource to be stored in
	 * @throws IOException In case something goes wrong when creating the file or folders
	 */
	public void createSeiStorage(IBeanStructuralElementInstance beanSei) throws IOException {
		String pathFolderSei = VirSatProjectCommons.getStructuralElementInstancePath(beanSei.getStructuralElementInstance());
		String pathFolderSeiDocument = VirSatProjectCommons.getStructuralElementInstanceDocumentPath(beanSei.getStructuralElementInstance());		
		String pathFileSei = VirSatProjectCommons.getStructuralElementInstanceFullPath(beanSei.getStructuralElementInstance());
		
		// Now create the folders and open the new resource
		Files.createDirectories(Paths.get(getCurrentProjectAbsolutePath(), pathFolderSei));
		Files.createDirectories(Paths.get(getCurrentProjectAbsolutePath(), pathFolderSeiDocument));

		String resourceFullPath = Paths.get(getCurrentProjectAbsolutePath(), pathFileSei).toAbsolutePath().toString();
		URI modelUri = URI.createFileURI(resourceFullPath);

		Resource resource = resourceSet.createResource(modelUri);

		StructuralElementInstance sei = beanSei.getStructuralElementInstance();

		List<Discipline> disciplines = getRepository().getRoleManagement().getDisciplines();
		String userName = System.getProperty("user.name");

		Discipline foundDiscipline = null;
		for (Discipline discipline : disciplines) {
			if (discipline.getUser().equals(userName)) {
				foundDiscipline = discipline;
				break;
			}
		}

		if (foundDiscipline == null) {
			foundDiscipline = RolesFactory.eINSTANCE.createDiscipline();
			foundDiscipline.setName("APP");
			foundDiscipline.setUser(userName);
			getRepository().getRoleManagement().getDisciplines().add(foundDiscipline);
		}

		sei.setAssignedDiscipline(foundDiscipline);

		resource.getContents().add(sei);
	}
	
	/**
	 * Use this method to get the absolute Path to the Documents Folder of a Structural
	 * Element Instance. The path is returned as string.
	 * @param beanSei the Bean representing the SEI for which one to get the documents folder.
	 * @return the returned absolute path to the documents folder as String.
	 */
	public String getSeiStorageDocumentPath(IBeanStructuralElementInstance beanSei) {
		String pathFolderSeiDocument = VirSatProjectCommons.getStructuralElementInstanceDocumentPath(beanSei.getStructuralElementInstance());
		String pathProjectRoot = getCurrentProjectAbsolutePath();
		return pathProjectRoot + File.separator + pathFolderSeiDocument;
	}
	
	/**
	 * method to get current Project path in runtime workspace, by default it uses eclipse workspace
	 * @return the path
	 */ 
	public String getCurrentProjectAbsolutePath() {
		return System.getProperty(JAVA_SYSTEM_PROPERTY_WORKING_DIR);
	}
	
	/**
	 * methode to get the current workspace in the runtime
	 * @return the workspace
	 */
	public String getCurrentWorkspacePath() {
		Path workspacePath = Paths.get(getCurrentProjectAbsolutePath()).getParent();
		if (workspacePath != null) {
			return workspacePath.toString();
		}
		return null;
	}
	
	/**
	 * Call this method to place a StructuralElementInstance as a root Component.
	 * Root Components are directly displayed under the Repository in the VirSat Navigator
	 * @param beanSei The Bean StructuralElementInstance to be added as root Component
	 */
	public void addRootSei(IBeanStructuralElementInstance beanSei) {
		getRepository().getRootEntities().add(beanSei.getStructuralElementInstance());
	}
	
	/**
	 * Executes the inheritance builder on the current repository
	 */
	public void performInheritance() {
		InheritanceCopier ic = new InheritanceCopier(new IUserContext() {
			@Override
			public boolean isSuperUser() {
				return true;
			}
			
			@Override
			public String getUserName() {
				return "ModelAPI";
			}
		});
		System.out.println("---------------- Starting Inheritance Builder: ----------------------");
		ic.updateAllInOrder(
			getRepository(),
			new NullProgressMonitor()
		);
		System.out.println("---------------- Finished Inheritance Builder: ----------------------");
	}
	
	/**
	 * Get all root elements in the repository with a certain type
	 * @param beanSeiClazz the type
	 * @param <SEI_TYPE> the SEI type
	 * @return a list of bean structural elements instances wrapping the root structural element instances in the repository
	 */
	public <SEI_TYPE extends IBeanStructuralElementInstance> List<SEI_TYPE> getRootSeis(Class<SEI_TYPE> beanSeiClazz) {
		BeanStructuralElementInstanceHelper bseiHelper = new BeanStructuralElementInstanceHelper();
		return bseiHelper.wrapAllBeanSeisOfType(getRepository().getRootEntities(), beanSeiClazz);
	}
}
