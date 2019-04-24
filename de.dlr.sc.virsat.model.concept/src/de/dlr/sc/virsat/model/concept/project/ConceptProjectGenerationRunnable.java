/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.project;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.operation.IRunnableWithProgress;
import de.dlr.sc.virsat.model.concept.Activator;
import de.dlr.sc.virsat.model.concept.project.IProjectBuilderInfo.IFileDescription;


/**
 * This runnable handles the generation of a new concept project.
 * This means it creates the Plugin project with the needed folders.
 * It also creates a UI Plugin as well.
 * @author fisc_ph
 *
 */
public class ConceptProjectGenerationRunnable implements IRunnableWithProgress {

	private String projectName;
	private int updateFlags;
	private URI locationURI;
	
	/**
	 * Class Constructor of the concept builder
	 * @param uri the location URI where to actually create the project
	 * @param projectName The name of the project in which to build a concept
	 * @param updateFlags flags of the IResource interface to avoid setting natures when running the plugin creation IResource.AVOID_...
	 */
	public ConceptProjectGenerationRunnable(URI uri, String projectName, int updateFlags) {
		this.locationURI = uri;
		this.projectName = projectName;
		this.updateFlags = updateFlags;
	}
	
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		try {
			// CHECKSTYLE:OFF
			monitor.beginTask("Creating a Model Extension Concept project " + projectName, 4);

			monitor.subTask("Starting to create Plugin project : " + projectName);
			buildPlugin(new ConceptPluginBuilderInfo(projectName), SubMonitor.convert(monitor, 1));

			monitor.subTask("Starting to create Test Plugin project : " + projectName);
			buildPlugin(new ConceptFragmentTestBuilderInfo(projectName), SubMonitor.convert(monitor, 1));
			
			monitor.subTask("Starting to create UI Plugin project for : " + projectName);
			buildPlugin(new ConceptPluginUiBuilderInfo(projectName), SubMonitor.convert(monitor, 1));

			monitor.subTask("Starting to create Feature project for : " + projectName);
			buildPlugin(new ConceptFeatureBuilderInfo(projectName), SubMonitor.convert(monitor, 1));
			// CHECKSTYLE:ON
		} catch (CoreException | URISyntaxException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Failed to create Concept Project", e));
		} finally {
			monitor.done();
		}
	}
	
	/**
	 * Entry method to actually build a plugin / project
	 * @param builderInfo the builder info to be used when building the plugin
	 * @param pm a progress monitor that can be handed over
	 * @throws CoreException throws
	 * @throws URISyntaxException 
	 */
	private void buildPlugin(IProjectBuilderInfo builderInfo, IProgressMonitor pm) throws CoreException, URISyntaxException {
		// CHECKSTYLE:OFF
		pm.beginTask("Creating a Plugin Project " + builderInfo.getProjectName(), 4);
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		// CHECKSTYLE:ON
		
		// Create the project. Therefore get a project handle and create a descriptor for it
		// Here we obey the location URI which is forwarded by the UI Wizzard. This functionality allows
		// to directly generate the projects into the folder of the git repository, which is usually
		// no within the system file structure of the eclipse workspace.
		final IProject project = workspace.getRoot().getProject(builderInfo.getProjectName());
		IProjectDescription projectDesc = workspace.newProjectDescription(project.getName());
		if (locationURI != null) {
			URI locationURIwithProject = new URI(locationURI.toASCIIString() + "/" + project.getName());
			projectDesc.setLocationURI(locationURIwithProject);
		} else {
			projectDesc.setLocationURI(null);
		}
		
		// Check if the project already exists. In that case don'T continue with the generation of it
		if (!project.exists()) {
			project.create(projectDesc, SubMonitor.convert(pm, 1));
	
			// Add the natures and builders
			attachNatures(projectDesc, builderInfo.getNatureIds());
			attachBuilders(projectDesc, builderInfo.getBuilderIds());
	
			// Open and add the updated descriptors
			project.open(SubMonitor.convert(pm, 1));
			project.setDescription(projectDesc, updateFlags, SubMonitor.convert(pm, 1));
			project.setDefaultCharset("UTF-8", SubMonitor.convert(pm, 1));
	
			// Populate the classPathSettings
			createClasspathAndFolders(project, builderInfo.getFolders(), builderInfo.getSourceFolders(), builderInfo.getContainers(), builderInfo.getOutputFolder());
			
			// Create them manifest
			createFiles(project, builderInfo.getFileDesciptors());
		}
		
		// Report the final status with the progress monitor
		pm.worked(1);
	}
	
	/**
	 * Entry method to actually attach the natures to the project
	 * @param projDesc the project descriptor to which to add the natures
	 * @param natureIds the IDs of the natures
	 */
	private void attachNatures(IProjectDescription projDesc, Set<String> natureIds) {
		projDesc.setNatureIds(natureIds.toArray(new String[natureIds.size()]));
	}
	
	/**
	 * Entry method to actually attach the builders to the project
	 * @param projDesc the project descriptor to which to add the builders
	 * @param builderIds the IDs of the builders
	 */
	private void attachBuilders(IProjectDescription projDesc, Set<String> builderIds) {
		Set<ICommand> builders = new HashSet<>();

		for (String builderId : builderIds) {
			ICommand javaCommand = projDesc.newCommand();
			javaCommand.setBuilderName(builderId);
			builders.add(javaCommand);
		}

        projDesc.setBuildSpec(builders.toArray(new ICommand[0]));
	}
	
	/**
	 * Method to create the class paths and folders
	 * @param project the project in which to generate the class paths and folders
	 * @param folders the folders to be generated
	 * @param srcFolders the source folders to be generated
	 * @param containers the containers to be generated
	 * @param outputFolder the output folder to be generated
	 * @throws CoreException throws
	 */
	private void createClasspathAndFolders(IProject project, Set<String> folders, Set<String> srcFolders, Set<String> containers, String outputFolder) throws CoreException {
		Set<IClasspathEntry> classpathEntries = new HashSet<>();

		if (srcFolders == null) {
			return;
		}
		
		// Create folders that are not part of the class path
		for (String folder : folders) {
		    IFolder src = project.getFolder(folder);
		    if (!src.exists()) {
		    	src.create(false, true, new NullProgressMonitor());
		    }
		}
		
		// Create source folders
		for (String srcFolder : srcFolders) {
		    IFolder src = project.getFolder(srcFolder);
		    if (!src.exists()) {
		    	src.create(false, true, new NullProgressMonitor());
		    }
		    IClasspathEntry srcClasspathEntry = JavaCore.newSourceEntry(src.getFullPath());
		    classpathEntries.add(srcClasspathEntry);
		}
		
		// Create containers
		for (String container : containers) {
		    IClasspathEntry srcClasspathEntry = JavaCore.newContainerEntry(new Path(container));
		    classpathEntries.add(srcClasspathEntry);
		}
		
		IJavaProject javaProject = JavaCore.create(project);
		javaProject.setRawClasspath(classpathEntries.toArray(new IClasspathEntry[0]), new NullProgressMonitor());
		javaProject.setOutputLocation(new Path("/" + project.getName() + "/" + outputFolder), new NullProgressMonitor());
	}
	
	/**
	 * Method to actually create a file from the builders file descriptors
	 * @param folder the folder in which to generate the file
	 * @param fileName the name of the file to be generated
	 * @param content the content of the file 
	 * @throws CoreException throws
	 */
	private void createFile(IContainer folder, String fileName, String content) throws CoreException {
		IFile file = folder.getFile(new Path(fileName));
        InputStream stream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        file.create(stream, false, new NullProgressMonitor());
	}
	
	/**
	 * Method that creates all files form the list of descriptors. It takes care of creating the file
	 * and the folders leading to it in case they don't exist yet.
	 * @param project the project in which to generate the file
	 * @param fileDesciptors the descriptor saying what and where the file should be genarated
	 * @throws CoreException throws
	 */
	private void createFiles(IProject project, Set<IFileDescription> fileDesciptors) throws CoreException {
		for (IFileDescription fileDescriptor : fileDesciptors) {
			String folderName = fileDescriptor.getFolderName();
			if ((folderName != null) && (!folderName.isEmpty())) {
				IFolder folder = project.getFolder(folderName);
				if (!folder.exists()) {
					folder.create(false, true, new NullProgressMonitor());
				}
			    createFile(folder, fileDescriptor.getFileName(), fileDescriptor.getFileContent());
			} else {
				createFile(project, fileDescriptor.getFileName(), fileDescriptor.getFileContent());
			}
		}
	}
}
