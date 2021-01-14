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
import de.dlr.sc.virsat.server.Activator;
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
		
		if (!localRepository.exists()) {
			localRepository.mkdir();
		}
		
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
//			VirSatProjectCommons.createNewProjectRunnable(project, false).run(new NullProgressMonitor());
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
				// could already be removed by project delete
				if (localRepositoryPath.toFile().exists()) {
					Files.walk(localRepositoryPath).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
				}

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
		
		// I think it is a good question when and from where we run this function
		// Currently it is run from the TransactionalJsonProvider in the readFrom method
		// so that after a model element is written via the REST API, the changes are committed
		//
		// We currently completely ignore having changes on the repository e.g. from virsat clients
		// So for that we would have to pull before every get / writeTo call in the provider
		// The problem is that the writeTo methods gets called with an object (bean) it should marshall
		// But on a reload this beans typeinstance loses its resource and still holds old data
		// So the reload has to happen outside of the provider before the writeTo method gets called
		// e.g. in the GET Request method
		//
		// So I came to the conclusion that we shouldn't handle the synchronization in the provider at all
		// Because maybe even in the model resource there are some endpoints were we don't want to sync the repo
		// So I think it's most convenient to handle it in the methods them self and the provider
		// only marshalles and unmarshalles the object from / into the model.
		// So there should be some synchronization testing in the ServerRepositoryTest and in the ModelAccessResourceTest
		//
		// One major question is if this behavior is too slow, e.g. reloading all resources every time
		// Two different approaches come to mind here:
		// 1. Automatically sync with the repo after a certain period of time
		// 2. Let the clients notify the server that it should sync via a HTTP request?
		//   This could be triggered automatically or by the user. But comes with an overhead in the clients
		runInWorkspace((progress) -> {
			try {
				String projectName = repositoryConfiguration.getProjectName();
				Activator.getDefault().getLog().info("Server synchronizing project with backend: " + projectName);
				Activator.getDefault().getLog().info("Server synchronization: " + "Saving all resources");

				// Ensure that all changes are saved on FS level
				if (ed.isDirty()) {
					ed.saveAll();
				}
				
				// Get remote changes
				Activator.getDefault().getLog().info("Server synchronization: " + "Update from remote");
				versionControlBackEnd.update(project, new NullProgressMonitor());
				
				// If there are changes:
				// 1. Reload changed resources
				// The Workspace Listener should trigger a full reload by itself
				// if the FS got changes by the pull
				// Wouldn't it be more cost efficient to only reload the changed
				// resources there instead of always triggering a full reload?
				ed.reloadAll();
				
				// 2. Run builders
				// The builders always run after this function is executed somehow
				// But I think it would make more sense here, because else
				// we don't need the commit step below
				// As long as this is in runInWorkspace there are no builders running
				// But running the builders here still reverts changes for some reason
				
				// Commit and push the new state to the repository
				// If we don't have changes between the update step and this (e.g. by builders)
				// We will never have a new commit and just push the commits from the update step
				Activator.getDefault().getLog().info("Server synchronization: " + "Push changes");
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
		
		resourceSet = VirSatResourceSet.getResourceSet(project); //, false);
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
		} else {
			retrieveEdAndResurceSetFromConfiguration();
		}
		
		syncRepository();
	}
}
