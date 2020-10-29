/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.apps.initializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import de.dlr.sc.virsat.apps.Activator;
import de.dlr.sc.virsat.commons.exception.AtomicExceptionReference;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

import java.util.jar.Manifest;


/**
 * This class handles the initialization of the VirSat App Engine for a VirSat
 * Project. This means it takes care that the script folder will be added,
 * Classpath Files will be set etc etc.
 * 
 * @author fisc_ph
 *
 */
public class VirsatAppsInitializer {
	
	public static final String FOLDER_NAME_TARGET = "target";
	public static final String FOLDER_NAME_CLASSES = "classes";
	public static final String FOLDER_NAME_APPS = "apps";
	public static final String FOLDER_NAME_META_INF = "META-INF";
	
	public static final String FILE_NAME_MANIFEST_MF = "MANIFEST.MF";
	public static final String FILE_NAME_BUILD_PROPERTIES = "build.properties";
	
	public static final String FILE_NAME_SCRIPT_EXAMPLE_PATTERN = "AppExample%d";
	public static final String FILE_NAME_SCRIPT_EXAMPLE_EXTENSION = ".java";
	
	public static final int PROGRESS_WORK_TICKS = 8;
	
	private static final int MAX_NUMBER_TRIES = 100;
	
	/**
	 * Call this method to create a new script. It cycles from 1 to 100 to find a file that has not yet been created
	 * @param project the project in which to create the new script
	 * @param pm the progress monitor if wanted
	 */	
	public void initializeVirSatExampleApp(IProject project,  IProgressMonitor pm) {
		IFolder folderApps = project.getFolder(FOLDER_NAME_APPS);
		try {
			for (int i = 1; i < MAX_NUMBER_TRIES; i++) {
				String appName = String.format(FILE_NAME_SCRIPT_EXAMPLE_PATTERN, i);
				IFile fileApp = folderApps.getFile(appName + FILE_NAME_SCRIPT_EXAMPLE_EXTENSION);

				if (!fileApp.exists()) {
					String content = new ContentExampleApp().getContents(appName).toString();
					InputStream stream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
					fileApp.create(stream, false, SubMonitor.convert(pm, 1));
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirsatAppsInitializer: Successfully created app file " + appName, null));
					break;
				}
			}
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Failed after " + MAX_NUMBER_TRIES + " iterations to create App " + FILE_NAME_BUILD_PROPERTIES, e));
		}
	}
	
	private void initializeFolder(IFolder folder, IProgressMonitor pm) throws CoreException {
		if (!folder.exists()) {
			folder.create(IResource.NONE, true, SubMonitor.convert(pm, 1));
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirsatAppsInitializer: Successfully created folder " + folder.getName(), null));				
		}
	}
	
	/**
	 * Call this method to add the needed files to the project to activate
	 * it as a java/plugin project
	 * @param project The project which should be activated for java
	 * @param repo The repository to be used to update the manifest file accordignly
	 * @param pm a progress monitor counting what is going on
	 */
	public void initializeProject(IProject project, Repository repo, IProgressMonitor pm) {
		pm.beginTask("Setting up project to conform VirSat App needs", PROGRESS_WORK_TICKS);

		AtomicExceptionReference<CoreException> coreExceptionReference =  new AtomicExceptionReference<>();
		VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(project);
		
		try {
			ed.runExclusive(() -> {
				try {
					project.refreshLocal(IResource.DEPTH_INFINITE, SubMonitor.convert(pm, 1));
					
					/**
					 * Create folders and files
					 */
					IFolder folderApps = project.getFolder(FOLDER_NAME_APPS);
					initializeFolder(folderApps, pm);
			
					IFolder folderTarget = project.getFolder(FOLDER_NAME_TARGET);
					initializeFolder(folderTarget, pm);
					
					IFolder folderClasses = folderTarget.getFolder(FOLDER_NAME_CLASSES);
					initializeFolder(folderClasses, pm);
			
					IFolder folderMetaInf = project.getFolder(FOLDER_NAME_META_INF);
					initializeFolder(folderMetaInf, pm);
			
					IFile fileManifestMf = folderMetaInf.getFile(FILE_NAME_MANIFEST_MF);
					if (!fileManifestMf.exists()) {
						String content = new ContentManifestMf().getContents(project.getName()).toString();
						InputStream stream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
						fileManifestMf.create(stream, false, SubMonitor.convert(pm, 1));
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirsatAppsInitializer: Successfully created file " + FILE_NAME_MANIFEST_MF, null));
					}
					
					if (fileManifestMf.exists()) {
						try {
							// Read the manifest file
							InputStream isManifestFileForRead = fileManifestMf.getContents();
							Manifest manifest = new Manifest(isManifestFileForRead);
							isManifestFileForRead.close();

							ManifestRequiredBundlesConceptUpdater.updateDependencies(repo, manifest);
							
							// Create an output stream to store the manifest file
							ByteArrayOutputStream stream = new ByteArrayOutputStream();
							manifest.write(stream);

							// Finally use the output stream to create an input stream to store the information using the IFile
							InputStream inputStream = new ByteArrayInputStream(stream.toByteArray());
							fileManifestMf.setContents(inputStream, IResource.FORCE, SubMonitor.convert(pm, 1));
						} catch (IOException e) {
							Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "VirsatAppsInitializer: Failed to update concepts in " + FILE_NAME_BUILD_PROPERTIES, e));
						}
					}
			
					IFile fileBuildProperties = project.getFile(FILE_NAME_BUILD_PROPERTIES);
					if (!fileBuildProperties.exists()) {
						String content = new ContentBuildProperties().getContents().toString();
						InputStream stream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
						fileBuildProperties.create(stream, false, SubMonitor.convert(pm, 1));
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirsatAppsInitializer: Successfully created file " + FILE_NAME_BUILD_PROPERTIES, null));
					}

					/**
					 * Now set the natures. Natures should be set before the classpath,
					 * because otherwise setting the classpath will fail.
					 */
					VirSatProjectCommons.attachNature(project, "org.eclipse.pde.PluginNature");
					VirSatProjectCommons.attachNature(project, "org.eclipse.jdt.core.javanature");
					
					/**
					 * Create class path file
					 */
					Set<IClasspathEntry> classpathEntries = new HashSet<>();
					classpathEntries.add(JavaCore.newSourceEntry(folderApps.getFullPath()));
					classpathEntries.add(JavaCore.newContainerEntry(new Path("org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.8")));
					classpathEntries.add(JavaCore.newContainerEntry(new Path("org.eclipse.pde.core.requiredPlugins")));
					
					IJavaProject javaProject = JavaCore.create(project);
					javaProject.setRawClasspath(classpathEntries.toArray(new IClasspathEntry[0]), SubMonitor.convert(pm, 1));
					javaProject.setOutputLocation(folderClasses.getFullPath(), SubMonitor.convert(pm, 1));
	
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirsatAppsInitializer: Successfully created ClassPath File", null));
					
					/**
					 * Finally attach the builders. Builders should be attached after the classpath
					 * to prevent unconfigured building of java code.
					 */
					VirSatProjectCommons.attachBuilder(project, "org.eclipse.pde.ManifestBuilder");
					VirSatProjectCommons.attachBuilder(project, "org.eclipse.pde.SchemaBuilder");
					VirSatProjectCommons.attachBuilder(project, "org.eclipse.jdt.core.javabuilder");
					
				} catch (CoreException e) {
					coreExceptionReference.set(e);
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Failed to activate VirSat Apps in workspace operation " + project.getName(), e));
				}
			});
			
			coreExceptionReference.throwIfSet();
		} catch (InterruptedException | CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Failed to activate VirSat Apps for project " + project.getName(), e));
		} finally {
			pm.done();
		}
	}
}
