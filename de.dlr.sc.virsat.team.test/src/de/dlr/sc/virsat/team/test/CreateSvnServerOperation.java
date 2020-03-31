/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.test;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.team.svn.core.SVNMessages;
import org.eclipse.team.svn.core.connector.ISVNManager;
import org.eclipse.team.svn.core.extension.CoreExtensionsManager;
import org.eclipse.team.svn.core.operation.AbstractActionOperation;
import org.eclipse.team.svn.core.operation.SVNProgressMonitor;

/**
 * This class is a helper class to enable testing svn repositories
 * by creating local server side svn repos.
 * This is needed since there is no subersive API for creating a repository, 
 * so we have to manually build the svn repo.
 */
public class CreateSvnServerOperation extends AbstractActionOperation {

	private String remoteRepoFilePath;
	
	public CreateSvnServerOperation(String remoteRepoFilePath) {
		super("Operation_CreateRepository", SVNMessages.class);
		this.remoteRepoFilePath = remoteRepoFilePath;
	}

	@Override
	protected void runImpl(IProgressMonitor monitor) throws Exception {
		ISVNManager proxy = CoreExtensionsManager.instance().getSVNConnectorFactory().createManager();
		
		try {
			proxy.create(remoteRepoFilePath, ISVNManager.RepositoryKind.FSFS, null, ISVNManager.Options.NONE, new SVNProgressMonitor(this, monitor, null));
		} finally {
			proxy.dispose();
		}
	}
	
	/**
	 * Executes the run method of the operation and instead of silently failing in the case
	 * of an error, throws an exception for it
	 * @param monitor a progress monitor
	 * @throws CoreException
	 */
	public void runWithExceptionChecking(IProgressMonitor monitor) throws CoreException {
		run(monitor);
		
		if (!getStatus().isOK()) {
			throw new CoreException(getStatus());
		}
	}
}
