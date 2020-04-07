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

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.nature.VirSatProjectNature;

/**
 * Class to handle the general layout and file structure of 
 * a VirSat project. This class is supposed to know where files are located
 * where they can be found etc.
 * @author fisc_ph
 *
 */
public class VirSatProjectCommons {

	public static final String FOLDERNAME_DATA = "data";
	public static final String FOLDERNAME_UNVERSIONED = "unversioned";

	public static final String FILENAME_EXTENSION = "dvlm";
	public static final String FILENAME_REPOSITORY = "Repository.dvlm";
	public static final String FILENAME_UNIT_MANAGEMENT = "UnitManagement.dvlm";
	public static final String FILENAME_ROLE_MANAGEMENT = "RoleManagement.dvlm";
	public static final String FILENAME_EMPTY = ".empty";

	public static final String XTEXT_NATURE_ID = "org.eclipse.xtext.ui.shared.xtextNature";


	private IProject project;
	
	/**
	 * Constructor for the project commons
	 * @param project the project on which this commons class is supposed to work on
	 */
	public VirSatProjectCommons(IProject project) {
		this.project = project;
	}
	
	/**
	 * This method creates the folder Structure within a new Project. The folder
	 * structure follows the agreed VirtualSatellite Conventions
	 * @param pm A ProgressMonitor for reporting
	 * @return true in case the structure was successfully created
	 */
	public boolean createProjectStructure(IProgressMonitor pm) {
		boolean performFinish = true;

		IFolder folderData = project.getFolder(FOLDERNAME_DATA);
		IFolder folderUnversioned = project.getFolder(FOLDERNAME_UNVERSIONED);

		try {
			createFolderWithEmptyFile(folderData, pm);
			createFolderWithEmptyFile(folderUnversioned, pm);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Could not create folder or file in project!", e));
			performFinish = false;
		}

		return performFinish;
	}

	public static final String FOLDERNAME_STRUCTURAL_ELEMENT_PREFIX = "ise_";
	public static final String FOLDERNAME_STRUCTURAL_ELEMENT_DOCUMENTS = "documents";

	public static final String FILENAME_STRUCTURAL_ELEMENT = "StructuralElement.dvlm";

	/**
	 * This method hands back the path and the filename as String based path of a given SEI
	 * @param sei The SEI of which to get the Path
	 * @return the path including the Filename as string
	 */
	public static String getStructuralElementInstanceFullPath(StructuralElementInstance sei) {
		return getStructuralElementInstancePath(sei) + "/" + FILENAME_STRUCTURAL_ELEMENT;
	}
	
	/**
	 * Static method to create a String based path for the given SEI telling where it should be stored
	 * @param sei The StructuralElementInstance for which to get a path
	 * @return the correct path in which to store the SEI based on a String
	 */
	public static String getStructuralElementInstancePath(StructuralElementInstance sei) {
		return FOLDERNAME_DATA + "/" + FOLDERNAME_STRUCTURAL_ELEMENT_PREFIX + sei.getUuid().toString();
	}

	/**
	 * Static method to get the documents folder as string based Path for a given SEI
	 * @param sei The SEI for which to get the Path 
	 * @return the Path in forms of a String 
	 */
	public static String getStructuralElementInstanceDocumentPath(StructuralElementInstance sei) {
		return getStructuralElementInstancePath(sei) + "/" + FOLDERNAME_STRUCTURAL_ELEMENT_DOCUMENTS;
	}
	
	/**
	 * Method to create folder structure for a structural element instance
	 * @param sei the structural element instance for which to create the folder structure
	 * @param pm the progress monitor for reporting to the framework or dialogs
	 * @return true in case things worked out correctly
	 */
	public boolean createFolderStructure(StructuralElementInstance sei, IProgressMonitor pm) {
		boolean performFinish = true;

		//****************************************************************
		// Current Folder Structure
		//****************************************************************
		// Project
		// + Data
		//   + SEI_UUID
		//     + Documents
		//   + SEI_UUID
		//     + Documents
		//****************************************************************
		
		IFolder folderData = project.getFolder(FOLDERNAME_DATA);

		if (!folderData.exists()) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "The Projetc Structure is missing the data folder!"));
			performFinish = false;
		}

		IFolder folderSei = project.getFolder(new Path(getStructuralElementInstancePath(sei)));
		IFolder folderSeiDocuments = project.getFolder(new Path(getStructuralElementInstanceDocumentPath(sei)));
		
		try {
			createFolderWithEmptyFile(folderSei, pm);
			createFolderWithEmptyFile(folderSeiDocuments, pm);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Could not create folder or file for new structural element!", e));
			performFinish = false;
		}

		return performFinish;
	}
	
	/**
	 * Method for easy access to SEI folder
	 * @param sei the SEI for which to get the folder
	 * @return the IFolder object
	 */
	public IFolder getStructuralElemntInstanceFolder(StructuralElementInstance sei) {
		String folderName = FOLDERNAME_STRUCTURAL_ELEMENT_PREFIX + sei.getUuid().toString();
		IFolder folderData = project.getFolder(FOLDERNAME_DATA);
		return folderData.getFolder(folderName);	
	}

	/**
	 * Method to remove folder structure for a structural element instance
	 * @param sei the structural element instance for which to remove the folder structure
	 * @param pm the progress monitor for reporting to the framework or dialogs
	 * @return true in case things worked out correctly
	 */
	public boolean removeFolderStructure(StructuralElementInstance sei, IProgressMonitor pm) {
		boolean performFinish = true;
		
		IFolder folderData = project.getFolder(FOLDERNAME_DATA);

		if (!folderData.exists()) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "The Projetc Structure is missing the data folder!"));
			performFinish = false;
		}

		String folderName = FOLDERNAME_STRUCTURAL_ELEMENT_PREFIX + sei.getUuid().toString();
		IFolder folderStructuralElement = folderData.getFolder(folderName);
		IFolder folderStructuralElementDocuments = folderStructuralElement.getFolder(FOLDERNAME_STRUCTURAL_ELEMENT_DOCUMENTS);
		
		try {
			if (folderStructuralElement.exists()) {
				folderStructuralElement.delete(true,  pm);
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatProjectCommons: Successfully deleted folder " + folderName, null));				
			}
			if (folderStructuralElementDocuments.exists()) {
				folderStructuralElementDocuments.delete(true,  pm);
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatProjectCommons: Successfully deleted folder " + FOLDERNAME_STRUCTURAL_ELEMENT_DOCUMENTS, null));
			}
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Could not delete folder or file for new structural element!", e));
			performFinish = false;
		}

		return performFinish;
	}
	
	/**
	 * Call this method to add the VirSat Project Nature
	 * to a given Eclipse Workspace Project
	 * @return true in case things worked out correctly
	 */
	public boolean attachProjectNature() {
		return attachProjectNature(null);
	}
	
	/**
	 * Call this method to add the VirSat Project Nature
	 * to a given Eclipse Workspace Project
	 * @param externalNatureId further ID to be added if needed
	 * @return true in case things worked out correctly
	 */
	public boolean attachProjectNature(String externalNatureId) {
		boolean performFinish = true;

		// trying to assign a nature to our project
		try {
			attachNature(project, VirSatProjectNature.NATURE_ID);
					
			if (externalNatureId != null) {
				attachNature(project, externalNatureId);
			}
			
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatProjectCommons: Successfully applied natures to new project", null));

		} catch (CoreException e) {
			e.printStackTrace();
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Could not assign the nature to the project!", e));
			performFinish = false;
		}

		return performFinish;
	}

	/**
	 * method to add natures if they do not yet exist in the project description
	 * @param project The project to which to add a nature
	 * @param natureId the id of the nature
	 * @throws CoreException is thrown when nature could not be applied
	 */
	public static void attachNature(IProject project, final String natureId) throws CoreException {

		if (!project.hasNature(natureId)) {
			IProjectDescription description = project.getDescription();
			String[] natures = description.getNatureIds();
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = natureId;
			description.setNatureIds(newNatures);
			project.setDescription(description, null);
		}
	}
	
	/**
	 * Method to add a builder to a given project. The method takes care of not adding duplicates
	 * @param project the project where to add the builder to
	 * @param builderId the id of the builder that should eb added
	 * @throws CoreException Exception in cas the builder cannot be added
	 */
	public static void attachBuilder(IProject project, String builderId) throws CoreException {
		IProjectDescription projDesc = project.getDescription();
		ICommand[] buildSpec = projDesc.getBuildSpec();
		
		// Don't add the builder if it already exists
		for (ICommand command : buildSpec) {
			if (command.getBuilderName().equals(builderId)) {
				return;
			}
		}

		ICommand[] newBuildSpec = new ICommand[buildSpec.length + 1];
		System.arraycopy(buildSpec, 0, newBuildSpec, 0, buildSpec.length);
		ICommand command = projDesc.newCommand();
		command.setBuilderName(builderId);
		newBuildSpec[buildSpec.length] = command;
		projDesc.setBuildSpec(newBuildSpec);
		
		project.setDescription(projDesc, null);
	}

	/**
	 * This method crawls the current workspace and hands back all projects which have the virsat nature
	 * @param workspace the workspace to be crawled
	 * @return a list of projects having the virsat nature
	 */
	public static List<IProject> getAllVirSatProjects(IWorkspace workspace) {
		List<IProject> virSatProjects = new ArrayList<IProject>();
		IProject[] foundProjects = workspace.getRoot().getProjects();
		try {
			for (IProject project : foundProjects) {
				if (project.isOpen() && isVirSatProject(project)) {
					virSatProjects.add(project);
				}
			}
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to access project nature", e));
		}

		return virSatProjects;
	}

	/**
	 * Use this method to test if a workspace project has the Virtual Satellite Nature attached
	 * @param project the project to be tested
	 * @return true in case the Virtual Satellite project nature could be found.
	 * @throws CoreException
	 */
	public static boolean isVirSatProject(IProject project) throws CoreException {
		IProjectDescription description = project.getDescription();

		List<String> natures = Arrays.asList(description.getNatureIds());
		return natures.contains(VirSatProjectNature.NATURE_ID);
	}
	
	/**
	 * This method hands back the documents folder that we provide by convention.
	 * @param sei The Structural Element Instance for which to get the folder
	 * @return the folder within the workspace
	 */
	public static IFolder getDocumentFolder(EObject sei) {
		Resource resource = sei.eResource();
		URI seiUri = resource.getURI();
		
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFile seiFile = workspaceRoot.getFile(new Path(seiUri.toPlatformString(true)));
		IFolder seiDocumentsFolder = seiFile.getParent().getFolder(new Path(FOLDERNAME_STRUCTURAL_ELEMENT_DOCUMENTS));
		return seiDocumentsFolder;
	}
	
	/**
	 * a method to get the actual persisted file of the given structural element instance
	 * @param sei the {@link StructuralElementInstance} of which you want to get the file
	 * @return IFile the file which contains the structural element instance
	 */
	public IFile getStructuralElementInstanceFile(StructuralElementInstance sei) {
		IFolder folderData = project.getFolder(FOLDERNAME_DATA);
		IFolder folderStructuralElement = folderData.getFolder(FOLDERNAME_STRUCTURAL_ELEMENT_PREFIX + sei.getUuid());
		IFile seiFile = folderStructuralElement.getFile(FILENAME_STRUCTURAL_ELEMENT);
		if (!folderStructuralElement.exists() || !seiFile.exists()) {
			createFolderStructure(sei, null);
		}
		return seiFile;		
	}
	
	/**
	 * Method to hand back the named file of a VirSat DataModel
	 * @return the file as part of the VirSat Data Model
	 */
	public IFile getRepositoryFile() {
		IFolder folderData = project.getFolder(FOLDERNAME_DATA);
		IFile repositoryFile = folderData.getFile(FILENAME_REPOSITORY);
		return repositoryFile;		
	}

	/**
	 * Method to hand back the named file of a VirSat DataModel
	 * @return the file as part of the VirSat Data Model
	 */
	public IFile getUnitManagementFile() {
		IFolder folderData = project.getFolder(FOLDERNAME_DATA);
		IFile repositoryFile = folderData.getFile(FILENAME_UNIT_MANAGEMENT);
		return repositoryFile;		
	}

	/**
	 * Method to hand back the named file of a VirSat DataModel
	 * @return the file as part of the VirSat Data Model
	 */
	public IFile getRoleManagementFile() {
		IFolder folderData = project.getFolder(FOLDERNAME_DATA);
		IFile repositoryFile = folderData.getFile(FILENAME_ROLE_MANAGEMENT);
		return repositoryFile;		
	}

	/**
	 * tells you if the file has the extension VirSat uses to store the DVLM model
	 * @param iFile IFile that should be checked
	 * @return true in case the file has the correct file ending
	 */
	public static boolean isDvlmFile(IFile iFile) {
		if (iFile == null) {
			return false;
		}
		
		String fileExtension = iFile.getFileExtension();
		return ((fileExtension != null) && (fileExtension.startsWith(FILENAME_EXTENSION)));
	}
	
	/**
	 * Method to get Workspace Resource for a given EObject
	 * @param eObject The eObject for which to hand back a Workspace Resource
	 * @return The Workspace Resource that contains the eObject as IResource
	 */
	public static IResource getWorkspaceResource(EObject eObject) {
		if (eObject != null && eObject.eResource() != null) {
			URI eResourceUri = eObject.eResource().getURI();
			IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
			String resourcePlatformUri = eResourceUri.toPlatformString(true);
			if (resourcePlatformUri != null) {
				IFile iFileOfEObject = wsRoot.getFile(new Path(resourcePlatformUri));
				if (iFileOfEObject.exists()) {
					return iFileOfEObject;
				}
			}
		}
		return null;
	}
	
	/**
	 * This method creates a folder containing an empty file using the eclipse resource API
	 * @param folder the folder which to create if it does not exist
	 * @param pm a progress monitor
	 * @return the folder which has been created
	 * @throws CoreException exception forwarded from creating the folder or file.
	 */
	public IFolder createFolderWithEmptyFile(IFolder folder, IProgressMonitor pm) throws CoreException {
		if (!folder.exists()) {
			folder.create(IResource.NONE, true, pm);
			Activator.getDefault().getLog().log(
				new Status(
					Status.INFO,
					Activator.getPluginId(),
					"VirSatProjectCommons: Successfully created folder " + folder.getName()
				)
			);
		}
		
		// Create a .empty file, this is needed for e.g. git, otherwise folders are not persisted
		IFile emptyFile = folder.getFile(FILENAME_EMPTY);
		if (!emptyFile.exists()) {
			emptyFile.create(new ByteArrayInputStream("".getBytes()), true, pm);
			Activator.getDefault().getLog().log(
				new Status(
					Status.INFO,
					Activator.getPluginId(),
					"VirSatProjectCommons: Successfully created empty file in folder " + folder.getName()
				)
			);
		}
		
		return folder;
	}
	
	public static final int PROGRESS_MONITOR_NEW_PROJECT_RUNNABLE_STEPS = 4;
	
	/**
	 * This method creates a runnable to create a new Project in Virtual Satellite
	 * @param ed the Editing DOmain to be used to create the Project
	 * @param newProject The new Project which should be initialized
	 * @return the runnable which initializes the whole project
	 */
	public static IWorkspaceRunnable createNewProjectRunnable(IProject newProject) {
		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			@Override
			public void run(IProgressMonitor monitor) throws CoreException {
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatProjectWizard: Started VirSat Project Initialization"));
				// Set up the Workspace Modification Unit to create the VirSat DataModel folder
				// layout and to initialize the Data Model files with correct content
				VirSatResourceSet resSet = VirSatResourceSet.getResourceSet(newProject);
				VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(newProject);

				SubMonitor progressMonitor = SubMonitor.convert(monitor, PROGRESS_MONITOR_NEW_PROJECT_RUNNABLE_STEPS);
	
				// Create the file structure and the files and add the Xtext Nature
				VirSatProjectCommons projectCommons = new VirSatProjectCommons(newProject);
				projectCommons.createProjectStructure(progressMonitor.split(1));
				projectCommons.attachProjectNature(XTEXT_NATURE_ID);

				// Create the actual resources and ResourceSet
				Command initializeProjectCommand = resSet.initializeModelsAndResourceSet(progressMonitor.split(1), ed);
			
				// Trigger the command stack to save all files after they have been created
				progressMonitor.split(1).setTaskName("Executing Project Initialization Command");
				ed.getVirSatCommandStack().triggerSaveAll();
				ed.getVirSatCommandStack().execute(initializeProjectCommand);

				newProject.refreshLocal(IResource.DEPTH_INFINITE, progressMonitor.split(1));
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatProjectWizard: Finished VirSat Project Initialization"));
			}
		};
		
		return runnable;
	}
}
