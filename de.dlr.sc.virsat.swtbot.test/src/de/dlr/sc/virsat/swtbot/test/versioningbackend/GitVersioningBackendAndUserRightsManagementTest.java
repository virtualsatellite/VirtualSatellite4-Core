/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.swtbot.test.versioningbackend;

import java.io.IOException;
import java.nio.file.Path;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;

public class GitVersioningBackendAndUserRightsManagementTest extends AVersioningBackendAndUserRightsManagementTest {

	public static final String TEST_REPO_PATH_UPSTREAM = "SwtBotGitBackendUpstreamRepo";
	public static final String TEST_REPO_PATH_LOCAL = "SwtBotGitBackendLocalRepo";
	
	@Override
	protected void setUpVersioningBackend() throws IOException {
		//
		Path upstreamRepoPath = VirSatFileUtils.createAutoDeleteTempDirectory(TEST_REPO_PATH_UPSTREAM);
		Path localRepoPath = VirSatFileUtils.createAutoDeleteTempDirectory(TEST_REPO_PATH_LOCAL);
		
		String upstreamRepoPathName = upstreamRepoPath.toString();
		String localRepoPathName = localRepoPath.toString();
		
		// First step open the perspective for git operations
		bot.toolbarButtonWithTooltip("Open Perspective").click();
		bot.table().select("Git");
		bot.button("Open").click();
		
		// Open the dialog for creating a new repository
		// Plan is to create a fake upstream repository as bare repository
		// on the local file system first
		bot.viewByTitle("Git Repositories").show();
		bot.toolbarButtonWithTooltip("Create a new Git Repository and add it to this view").click();

		bot.textWithLabel("Repository &directory:").setText(upstreamRepoPathName);
		bot.checkBox("Create as bare repository").click();
		bot.button("Create").click();
		
		// Now create the local repository which is based on the bare upstream repository
		bot.toolbarButtonWithTooltip("Clone a Git Repository and add the clone to this view").click();
		
		bot.textWithLabel("UR&I:").setText("file://" + upstreamRepoPathName);

		bot.button("Next >").click();
		bot.button("Next >").click();
		
		bot.text().setText(localRepoPathName);
		
		bot.button("Finish").click();
	}

	@Override
	protected void shareTestProjectWithVersioningBackend() {
		// TODO Auto-generated method stub
		
	}

}
