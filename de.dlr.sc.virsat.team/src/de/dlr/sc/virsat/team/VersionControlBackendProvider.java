package de.dlr.sc.virsat.team;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import de.dlr.sc.virsat.team.git.VirSatGitVersionControlBackend;
import de.dlr.sc.virsat.team.svn.VirSatSvnVersionControlBackend;

public class VersionControlBackendProvider {
	
	private VersionControlSystem versionControlSystem;
	private String userName;
	private String userPass;
	
	public VersionControlBackendProvider(VersionControlSystem versionControlSystem, String userName, String userPass) {
		this.versionControlSystem = versionControlSystem;
		this.userName = userName;
		this.userPass = userPass;
	}
	
	public IVirSatVersionControlBackend createBackendImplementation() {
		switch (versionControlSystem) {
			case GIT:
				CredentialsProvider credentialProvider = new UsernamePasswordCredentialsProvider(userName, userPass);
				return new VirSatGitVersionControlBackend(credentialProvider);
			case SVN:
				return new VirSatSvnVersionControlBackend();
			default:
				Activator.getDefault().getLog().log(new Status(IStatus.WARNING, Activator.getPluginId(), "Unknown Backend in Configuration"));
				return null;
		}
	}
}
