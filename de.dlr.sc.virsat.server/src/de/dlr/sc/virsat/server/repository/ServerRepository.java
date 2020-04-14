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
import java.net.URI;
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

import de.dlr.sc.virsat.commons.exception.AtomicExceptionReference;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.structure.nature.VirSatProjectNature;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;
import de.dlr.sc.virsat.team.VersionControlBackendProvider;

/**
 * Entry point to the eclipse project.
 * The class maps a project to a repository of either SVN or GIT.
 * It also offers functionality to checkout a project or commit to it.
 */
public class ServerRepository {

	public static final String SERVER_REPOSITORY_COMMIT_PUSH_MESSAGE = "Server Local Commit Before Push: ";
	
	private RepositoryConfiguration repositoryConfiguration;
	private IProject project;
	private VirSatResourceSet resourceSet;
	private VirSatTransactionalEditingDomain ed;
	private IVirSatVersionControlBackend versionControlBackEnd;
	private File localRepository;
	
	protected static final String PREFIX_LOCAL_REPO_NAME = "repo_";
	/**
	 * Constructor for a Server Repository.
	 * @param localRepositoryHome The repository home in which mostly all projects checkout to.
	 *  The actual checkout will happen into a sub folder with the name of the project. 
	 * @param repositoryConfiguration a Repository configuration carrying all important information such as username password remote uri etc.
	 * @throws URISyntaxException Make sure the URI is well formed.
	 */
	public ServerRepository(File localRepositoryHome, RepositoryConfiguration repositoryConfiguration) throws URISyntaxException {
		this.repositoryConfiguration = repositoryConfiguration;
		this.localRepository = new File(localRepositoryHome, PREFIX_LOCAL_REPO_NAME + repositoryConfiguration.getProjectName());
		
		//checkout the project to workspace
		String userName = Objects.toString(repositoryConfiguration.getFunctionalAccountName(), "");
		String userPass = Objects.toString(repositoryConfiguration.getFunctionalAccountPassword(), "");
	
		VersionControlBackendProvider backendProvider = new VersionControlBackendProvider(
				repositoryConfiguration.getBackend(), 
				new URI(repositoryConfiguration.getRemoteUri()), 
				userName, userPass);
		versionControlBackEnd = backendProvider.createBackendImplementation();
	}
	
	public RepositoryConfiguration getRepositoryConfiguration() {
		return repositoryConfiguration;
	}

	public File getLocalRepositoryPath() {
		return localRepository;
	}
	
	/**
	 * Method to create a project description which makes sure that
	 * the location is set to the correct path within the repository home.
	 * @return returns the well constructed project description
	 */
	public IProjectDescription getProjectDescription() {
		String projectName = repositoryConfiguration.getProjectName();		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProjectDescription projectDescription = workspace.newProjectDescription(projectName);
		
		String relativeLocalProjectPath = repositoryConfiguration.getLocalPath();
		File localRepositoryPath = getLocalRepositoryPath();
		File projectInLocalRepositoryPath = new File(localRepositoryPath, relativeLocalProjectPath);
		projectDescription.setLocationURI(projectInLocalRepositoryPath.toURI());
		
		return projectDescription;
	}
	
	/**
	 * Call this method to checkout a repository. The method will also
	 * take care of transforming the project into a proper virtual satellite
	 * project if it is not yet one. This means if checking out from an empty repository
	 * a new project will be created which is completly set up and can be shared
	 * with the next commit.
	 * @throws Exception
	 */
	public void checkoutRepository() throws Exception {
		AtomicExceptionReference<Exception> atomicException = new AtomicExceptionReference<>();
		
		runInWorkspace((progress) -> {
			try {
				
				IProjectDescription projectDescription = getProjectDescription();
				File localRepositoryPath = getLocalRepositoryPath();
								
				project = versionControlBackEnd.checkout(
						projectDescription,
						localRepositoryPath,
						repositoryConfiguration.getRemoteUri().toString(),
						new NullProgressMonitor()
				);
			
				createVirSatProjectIfNeeded();
				
				retrieveEdAndResurceSetFromConfiguration();
			} catch (Exception e) {
				atomicException.set(e);
			}
		});	
		
		atomicException.throwIfSet();
	}
	
	protected void createVirSatProjectIfNeeded() throws CoreException {
		boolean hasVirSatNature = Arrays.asList(project.getDescription().getNatureIds()).contains(VirSatProjectNature.NATURE_ID);
		if (!hasVirSatNature) {
			VirSatProjectCommons.createNewProjectRunnable(project).run(new NullProgressMonitor());
		}
	}
	
	/**
	 * Removes a repository and the project in the workspace as well
	 * @throws CoreException
	 * @throws IOException
	 */
	public void removeRepository() throws CoreException, IOException {
		AtomicExceptionReference<IOException> atomicException = new AtomicExceptionReference<>();
		
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
	
	/**
	 * This method syncs the repository, which means it updates from remote
	 * and then sends all the changes to remote
	 * @throws Exception
	 */
	public void syncRepository() throws Exception {
		AtomicExceptionReference<Exception> atomicException = new AtomicExceptionReference<>();
		
		runInWorkspace((progress) -> {
			try {
				String projectName = repositoryConfiguration.getProjectName();

				// Simple approach for the moment but maybe not enough for git. Maybe a sync has to be implemented for SVN and GIT
				// in the backend. E.g. Usually Git should works like: 1. commit your changes locally. 2. Pull remote changes and merge.
				// 3. push the merged changes.
				versionControlBackEnd.update(project, new NullProgressMonitor());
				versionControlBackEnd.commit(project, SERVER_REPOSITORY_COMMIT_PUSH_MESSAGE + projectName, new NullProgressMonitor());
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

	/**
	 * Checks out or updates the project and adds it into the workspace
	 * @throws Exception 
	 */
	public void updateOrCheckoutProject() throws Exception {
		retrieveProjectFromConfiguration();
		
		if (!project.exists()) {
			checkoutRepository();
		}
		
		syncRepository();
	}
}
