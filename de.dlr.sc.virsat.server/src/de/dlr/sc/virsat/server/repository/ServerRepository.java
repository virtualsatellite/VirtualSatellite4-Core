/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.repository;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

import de.dlr.sc.virsat.commons.exception.AtomicException;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.structure.nature.VirSatProjectNature;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;
import de.dlr.sc.virsat.team.VersionControlBackendProvider;

/**
 * Entry point to the eclipse project
 */
public class ServerRepository {
	
	private RepositoryConfiguration repositoryConfiguration;
	private IProject project;
	private VirSatResourceSet resourceSet;
	private VirSatTransactionalEditingDomain ed;
	private IVirSatVersionControlBackend versionControlBackEnd;
	private File localRepositoryHome;
	private File localRepository;
	
	public ServerRepository(File localRepositoryHome, RepositoryConfiguration repositoryConfiguration) throws URISyntaxException {
		this.repositoryConfiguration = repositoryConfiguration;
		this.localRepositoryHome = localRepositoryHome;
		this.localRepository = new File(localRepositoryHome, repositoryConfiguration.getProjectName());
		
		//checkout the project to workspace
		String userName = Objects.toString(repositoryConfiguration.getFunctionalAccountName(), "");
		String userPass = Objects.toString(repositoryConfiguration.getFunctionalAccountPassword(), "");
	
		VersionControlBackendProvider backendProvider = new VersionControlBackendProvider(
				repositoryConfiguration.getBackend(), 
				repositoryConfiguration.getRemoteUri(), 
				userName, userPass);
		versionControlBackEnd = backendProvider.createBackendImplementation();
	}
	
	public RepositoryConfiguration getRepositoryConfiguration() {
		return repositoryConfiguration;
	}

	public File getLocalRepositoryPath() {
		return localRepository;
	}
	
	public IProjectDescription getProjectDescription() {
		String projectName = repositoryConfiguration.getProjectName();		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProjectDescription projectDescription = workspace.newProjectDescription(projectName);
		
		File relativeLocalProjectPath = repositoryConfiguration.getLocalPath();
		File localRepositoryPath = getLocalRepositoryPath();
		File projectInLocalRepositoryPath = new File(localRepositoryPath, relativeLocalProjectPath.toString());
		projectDescription.setLocationURI(projectInLocalRepositoryPath.toURI());
		
		return projectDescription;
	}
	
	public void checkoutRepository() throws Exception {
		AtomicException<Exception> atomicException = new AtomicException<>();
		
		runInWorkspace((progress) -> {
			try {
				
				IProjectDescription projectDescription = getProjectDescription();
				File localRepositoryPath = getLocalRepositoryPath();
								
				versionControlBackEnd.checkout(
						projectDescription,
						localRepositoryPath,
						repositoryConfiguration.getRemoteUri().toString(),
						new NullProgressMonitor()
				);
			
				retrieveProjectFromConfiguration();
				project.create(projectDescription, new NullProgressMonitor());
				project.open(new NullProgressMonitor());
				
				versionControlBackEnd.connect(project, localRepositoryPath, new NullProgressMonitor());
				
				createVirSatProjectIfNeeded();
				
				retrieveEdAndResurceSetFromConfiguration();
			} catch (Exception e) {
				atomicException.set(e);
			}
		});	
		
		atomicException.throwIfSet();
	}
	
	public void createVirSatProjectIfNeeded() throws CoreException {
		boolean hasVirSatNature = Arrays.asList(project.getDescription().getNatureIds()).contains(VirSatProjectNature.NATURE_ID);
		if (!hasVirSatNature) {
			VirSatProjectCommons.createNewProjectRunnable(project).run(new NullProgressMonitor());
		}
	}
	
	public void removeRepository() throws CoreException, IOException {
		AtomicException<IOException> atomicException = new AtomicException<>();
		
		runInWorkspace((progress) -> {
			try {
				retrieveProjectFromConfiguration();
				project.delete(true, true, progress);

				Path localRepositoryPath = getLocalRepositoryPath().toPath();
				Files.walk(localRepositoryPath).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);

				project = null;
				resourceSet = null;
				ed = null;

			} catch (IOException e) {
				atomicException.set(e);
			}
		});
		
		atomicException.throwIfSet();
	}
	
	public static final String SERVER_REPOSITORY_COMMIT_MESSAGE = "Server Commit for Project: ";
	
	public void syncRepository() throws Exception {
		AtomicException<Exception> atomicException = new AtomicException<>();
		
		runInWorkspace((progress) -> {
			try {
				String projectName = repositoryConfiguration.getProjectName();

				// Simple approach for the moment but maybe not enough for git. Maybe a sync has to be implemented for SVN and GIT
				// in the backend. E.g. Usually Git should works like: 1. commit your changes locally. 2. Pull remote changes and merge.
				// 3. push the merged changes.
				versionControlBackEnd.update(project, new NullProgressMonitor());
				versionControlBackEnd.commit(project, SERVER_REPOSITORY_COMMIT_MESSAGE + projectName, new NullProgressMonitor());
			} catch (Exception e) {
				atomicException.set(e);
			}
		});
		
		atomicException.throwIfSet();
	}
	
	public void retrieveProjectFromConfiguration() {
		String projectName = repositoryConfiguration.getProjectName();
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}
	
	public void retrieveEdAndResurceSetFromConfiguration() {
		retrieveProjectFromConfiguration();
		
		resourceSet = VirSatResourceSet.getResourceSet(project);
		ed = VirSatEditingDomainRegistry.INSTANCE.getEd(resourceSet);
	}
	
	private void runInWorkspace(IWorkspaceRunnable runnable) throws CoreException {
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		ResourcesPlugin.getWorkspace().run(runnable, wsRoot, 0, null);
	}

	public IProject getProject() {
		return project;
	}

	public VirSatResourceSet getResourceSet() {
		return resourceSet;
	}

	public VirSatTransactionalEditingDomain getEd() {
		return ed;
	}
}
