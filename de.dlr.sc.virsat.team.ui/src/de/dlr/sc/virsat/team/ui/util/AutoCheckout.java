/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.ui.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URI;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.egit.core.RepositoryUtil;
import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
import org.eclipse.equinox.security.storage.StorageException;
import de.dlr.sc.virsat.commons.exception.AtomicExceptionReference;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.structure.nature.VirSatProjectNature;
import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;
import de.dlr.sc.virsat.team.VersionControlBackendProvider;
import de.dlr.sc.virsat.team.VersionControlSystem;

@SuppressWarnings("restriction")
public class AutoCheckout {

	private VersionControlSystem versionControlSystemType;
	private String remoteUri;
	private String vcsUsername;
	private String vcsPassword;
	private String vcsPath;
	private String projectName;
 
	/**
	 * AutoCheckout class
	 */
	public AutoCheckout() {
		versionControlSystemType = VersionControlSystem.valueOf(System.getenv("VS_VCS_TYPE"));
		remoteUri = System.getenv("VS_VCS_REMOTE_URI");
		vcsUsername = System.getenv("VS_VCS_USERNAME");
		vcsPassword = System.getenv("VS_VCS_PASSWORD");
		projectName = System.getenv("VS_PROJECT_NAME");
		vcsPath = "/vcs";
	}

	/**
	 * This method perform autocheckout
	 */
	public void performAutocheckout() {
		try {
			File localRepositoryHome = new File(vcsPath);
			RepositoryConfiguration repositoryConfiguration = new RepositoryConfiguration(projectName, // gives repo_<>
					projectName, // gives repo_<>/<>
					remoteUri, versionControlSystemType, vcsUsername, vcsPassword);

			File repositoryProjectName = new File(vcsPath + "/" + "repo_" + projectName);
			if (!repositoryProjectName.exists()) {
				ServerRepository serverRepository = new ServerRepository(localRepositoryHome, repositoryConfiguration);
				serverRepository.checkoutRepository();
			} else {
				repositoryConfiguration.getProjectName();
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IProjectDescription projectDescription = workspace.newProjectDescription(projectName);

				String relativeLocalProjectPath = repositoryConfiguration.getLocalPath();
				File projectInLocalRepositoryPath = new File(repositoryProjectName, relativeLocalProjectPath);
				projectDescription.setLocationURI(projectInLocalRepositoryPath.toURI());

				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
				project.create(projectDescription, new NullProgressMonitor());
				project.open(new NullProgressMonitor());
			}

			if (versionControlSystemType == VersionControlSystem.GIT) {
				RepositoryUtil repoUtil = RepositoryUtil.INSTANCE;
				File repo = new File(repositoryProjectName.getAbsolutePath() + "/.git");
				repoUtil.addConfiguredRepository(repo);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Store vcs credentials into the Eclipse credentials
	 */
	public void storeCredentials() {
		switch (versionControlSystemType) {
			case GIT:
				ISecurePreferences preferences = SecurePreferencesFactory.getDefault();
				ISecurePreferences node = preferences.node("GIT/https:\\2f\\2fgithub.com:443");
				try {
					node.put("user", vcsUsername, false);
					node.put("password", vcsPassword, true);
				} catch (StorageException e1) {
					e1.printStackTrace();
				} 
				break;
			default:
				System.out.println("Version ControlSystem Type unrecognized.");
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class ServerRepository {

		public static final String SERVER_REPOSITORY_COMMIT_PUSH_MESSAGE = "Server Local Commit Before Push: ";

		private RepositoryConfiguration repositoryConfiguration;
		private IProject project;
		private VirSatResourceSet resourceSet;
		private VirSatTransactionalEditingDomain ed;
		private IVirSatVersionControlBackend versionControlBackEnd;
		private File localRepository;

		private static final String PREFIX_LOCAL_REPO_NAME = "repo_";

		/**
		 * Constructor for a Server Repository.
		 * 
		 * @param localRepositoryHome     The repository home in which mostly all
		 *                                projects checkout to. The actual checkout will
		 *                                happen into a sub folder with the name of the
		 *                                project.
		 * @param repositoryConfiguration a Repository configuration carrying all
		 *                                important information such as username
		 *                                password remote uri etc.
		 * @throws URISyntaxException Make sure the URI is well formed.
		 */
		public ServerRepository(File localRepositoryHome, RepositoryConfiguration repositoryConfiguration)
				throws URISyntaxException {
			this.repositoryConfiguration = repositoryConfiguration;
			this.localRepository = new File(localRepositoryHome,
					PREFIX_LOCAL_REPO_NAME + repositoryConfiguration.getProjectName());

			if (!localRepository.exists()) {
				localRepository.mkdir();
			}

			// checkout the project to workspace
			String userName = Objects.toString(repositoryConfiguration.getFunctionalAccountName(), "");
			String userPass = Objects.toString(repositoryConfiguration.getFunctionalAccountPassword(), "");

			VersionControlBackendProvider backendProvider = new VersionControlBackendProvider(
					repositoryConfiguration.getBackend(), new URI(repositoryConfiguration.getRemoteUri()), userName,
					userPass);
			versionControlBackEnd = backendProvider.createBackendImplementation();
		}

		public RepositoryConfiguration getRepositoryConfiguration() {
			return repositoryConfiguration;
		}

		public File getLocalRepositoryPath() {
			return localRepository;
		}

		/**
		 * Method to create a project description which makes sure that the location is
		 * set to the correct path within the repository home.
		 * 
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
		 * Call this method to checkout a repository. The method will also take care of
		 * transforming the project into a proper virtual satellite project if it is not
		 * yet one. This means if checking out from an empty repository a new project
		 * will be created which is completly set up and can be shared with the next
		 * commit.
		 * 
		 * @throws Exception gg
		 */
		public void checkoutRepository() throws Exception {
			AtomicExceptionReference<Exception> atomicException = new AtomicExceptionReference<>();

			runInWorkspace((progress) -> {
				try {

					IProjectDescription projectDescription = getProjectDescription();
					File localRepositoryPath = getLocalRepositoryPath();

					project = versionControlBackEnd.checkout(projectDescription, localRepositoryPath,
							repositoryConfiguration.getRemoteUri().toString(), new NullProgressMonitor());

					createVirSatProjectIfNeeded();

					retrieveEdAndResurceSetFromConfiguration();
				} catch (Exception e) {
					atomicException.set(e);
				}
			});

			atomicException.throwIfSet();
		}

		protected void createVirSatProjectIfNeeded() throws CoreException {
			boolean hasVirSatNature = Arrays.asList(project.getDescription().getNatureIds())
					.contains(VirSatProjectNature.NATURE_ID);
			if (!hasVirSatNature) {
				VirSatProjectCommons.createNewProjectRunnable(project, false).run(new NullProgressMonitor());
			}
		}

		public void retrieveProjectFromConfiguration() {
			String projectName = repositoryConfiguration.getProjectName();
			project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		}

		public void retrieveEdAndResurceSetFromConfiguration() {
			retrieveProjectFromConfiguration();

			resourceSet = VirSatResourceSet.getResourceSet(project, false);
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

	public class RepositoryConfiguration {

		// Infrastructure
		private Properties properties = new Properties();

		// Properties key
		public static final String PROJECT_NAME_KEY = "project.name";
		public static final String BACKEND_KEY = "repository.backend";
		public static final String LOCAL_PATH_KEY = "repository.localPath";
		public static final String REMOTE_URL_KEY = "repository.remoteURI";
		public static final String FUNCTIONAL_ACCOUNT_NAME_KEY = "repository.credentials.username";
		public static final String FUNCTIONAL_ACCOUNT_PASSWORD_KEY = "repository.credentials.password";

		public RepositoryConfiguration() {
		}

		/**
		 * Sets up a property file describing a project repository relation
		 * 
		 * @param projectName               Name of the project
		 * @param localPath              of the project in the local repository
		 * @param remoteUri                 remote Uri pointing to the remote repository
		 * @param backend                   Backend to be used for the repository,
		 *                                  usually GIT or SVN
		 * @param functionalAccountName     a user for the functional account to
		 *                                  communicate with the backend
		 * @param functionalAccountPassword a password for the functional account
		 */
		public RepositoryConfiguration(String projectName, String localPath, String remoteUri,
				VersionControlSystem backend, String functionalAccountName, String functionalAccountPassword) {
			setProjectName(projectName);
			setLocalPath(localPath);
			setRemoteUri(remoteUri);
			setBackend(backend);
			setFunctionalAccountName(functionalAccountName);
			setFunctionalAccountPassword(functionalAccountPassword);
		}

		public RepositoryConfiguration(RepositoryConfiguration repositoryConfiguration) {
			update(repositoryConfiguration);
		}

		public RepositoryConfiguration(InputStream repoConfInputStream) throws FileNotFoundException, IOException {
			loadProperties(repoConfInputStream);
		}

		/**
		 * Method to update a repository configuration with new values
		 * 
		 * @param repositoryBackend the repositoryConfiguration to be used to update the
		 *                          current one
		 */
		public void update(RepositoryConfiguration repositoryBackend) {
			this.properties.putAll(repositoryBackend.properties);
		}

		/**
		 * Loads a properties file into memory
		 * 
		 * @param configFileInputStream input stream for the file containing properties
		 *                              in java properties format
		 * @throws FileNotFoundException gg
		 * @throws IOException gg
		 */
		public void loadProperties(InputStream configFileInputStream) throws FileNotFoundException, IOException {
			properties.load(configFileInputStream);
		}

		/**
		 * Saves properties from memory to file
		 * 
		 * @param configFileOutputStream the file output stream
		 * @throws IOException gg
		 */
		public void saveProperties(OutputStream configFileOutputStream) throws IOException {
			properties.store(configFileOutputStream, "");
		}

		public String getRemoteUri() {
			return properties.getProperty(REMOTE_URL_KEY);
		}

		public void setRemoteUri(String remoteUri) {
			properties.setProperty(REMOTE_URL_KEY, remoteUri);
		}

		public VersionControlSystem getBackend() {
			return VersionControlSystem.valueOf(properties.getProperty(BACKEND_KEY));
		}

		public void setBackend(VersionControlSystem backend) {
			properties.setProperty(BACKEND_KEY, backend.name());
		}

		public String getFunctionalAccountName() {
			return properties.getProperty(FUNCTIONAL_ACCOUNT_NAME_KEY);
		}

		public void setFunctionalAccountName(String functionalAccountName) {
			properties.setProperty(FUNCTIONAL_ACCOUNT_NAME_KEY, functionalAccountName);
		}

		public String getFunctionalAccountPassword() {
			return properties.getProperty(FUNCTIONAL_ACCOUNT_PASSWORD_KEY);
		}

		public void setFunctionalAccountPassword(String functionalAccountPassword) {
			properties.setProperty(FUNCTIONAL_ACCOUNT_PASSWORD_KEY, functionalAccountPassword);
		}

		public String getProjectName() {
			return properties.getProperty(PROJECT_NAME_KEY);
		}

		public void setProjectName(String projectName) {
			properties.setProperty(PROJECT_NAME_KEY, projectName);
		}

		public String getLocalPath() {
			return properties.getProperty(LOCAL_PATH_KEY);
		}

		public void setLocalPath(String localPath) {
			properties.setProperty(LOCAL_PATH_KEY, localPath);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((properties == null) ? 0 : properties.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			RepositoryConfiguration other = (RepositoryConfiguration) obj;
			return Objects.equals(properties, other.properties);
		}

		public boolean isValid() {
			return getProjectName() != null && !getProjectName().isEmpty() && getRemoteUri() != null
					&& !getRemoteUri().isEmpty() && getBackend() != null;
		}
	}
}
