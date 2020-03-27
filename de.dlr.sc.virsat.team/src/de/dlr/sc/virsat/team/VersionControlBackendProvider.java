/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team;

import java.net.URI;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.utility.SVNUtility;

import de.dlr.sc.virsat.team.git.VirSatGitVersionControlBackend;
import de.dlr.sc.virsat.team.svn.VirSatSvnVersionControlBackend;

public class VersionControlBackendProvider {
	
	private VersionControlSystem versionControlSystem;
	private URI remoteUri;
	private String userName;
	private String userPass;
	
	public VersionControlBackendProvider(VersionControlSystem versionControlSystem, URI remoteUri, String userName, String userPass) {
		this.versionControlSystem = versionControlSystem;
		this.remoteUri = remoteUri;
		this.userName = userName;
		this.userPass = userPass;
	}
	
	/**
	 * Creates the appropriate backend implementation for a version control system
	 * @return the backend implementation and null if the version control system is not supported
	 * by an implementation
	 */
	public IVirSatVersionControlBackend createBackendImplementation() {
		switch (versionControlSystem) {
			case GIT:
				CredentialsProvider credentialProvider = new UsernamePasswordCredentialsProvider(userName, userPass);
				return new VirSatGitVersionControlBackend(credentialProvider);
			case SVN:
				// SVN User authentification is done centrally by informing SVN which credentials
				// we would like to use for a given repository location
				IRepositoryLocation repositoryLocation = SVNUtility.asRepositoryResource(remoteUri.toString(), true).getRepositoryLocation();
				repositoryLocation.setUsername(userName);
				repositoryLocation.setAuthorName(userName);
				repositoryLocation.setPassword(userPass);
				return new VirSatSvnVersionControlBackend();
			default:
				Activator.getDefault().getLog().log(new Status(IStatus.WARNING, Activator.getPluginId(), "Unknown Backend in Configuration"));
				return null;
		}
	}
}
