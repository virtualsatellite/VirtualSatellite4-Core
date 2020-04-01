/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.ui.handler.git;

import org.eclipse.egit.ui.internal.credentials.EGitCredentialsProvider;

import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;
import de.dlr.sc.virsat.team.git.VirSatGitVersionControlBackend;
import de.dlr.sc.virsat.team.ui.handler.AVersionControlUpdateHandler;


/**
 * This class performs a git update
 */
@SuppressWarnings("restriction")
public class GitUpdateHandler extends AVersionControlUpdateHandler {
	
	@Override
	protected IVirSatVersionControlBackend createVersionControlBackend() {
		return new VirSatGitVersionControlBackend(new EGitCredentialsProvider());
	}

}
