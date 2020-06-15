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


public class SvnVersioningBackendAndUserRightsManagementTest extends AVersioningBackendAndUserRightsManagementTest {

	public static final String TEST_REPO_PATH_UPSTREAM = "SwtBotSvnBackendUpstreamRepo";
	protected Path upstreamRepoPath;
	protected String upstreamRepoPathName;
	protected String upstreamRepositoryPartName;
	
	@Override
	protected void setUpVersioningBackend() throws IOException {

		upstreamRepoPath = VirSatFileUtils.createAutoDeleteTempDirectory(TEST_REPO_PATH_UPSTREAM);
		
		upstreamRepoPathName = upstreamRepoPath.toString();
		upstreamRepositoryPartName = upstreamRepoPath.getFileName().toString();
		
		// First step open the perspective for svn operations
		openSvnPerspective();
		bot.viewByTitle("SVN Repositories").show();
		
		// Open the dialog to create a new remote repository
		bot.toolbarButtonWithTooltip("New Repository").click();
		bot.textWithLabel("Repository Path:").setText(upstreamRepoPathName);
		bot.button("OK").click();
		
	}
	
	/**
	 * This method opens the standard Eclipse SVN perspective
	 */
	protected void openSvnPerspective() {
		bot.toolbarButtonWithTooltip("Open Perspective").click();
		bot.table().select("SVN Repository Exploring");
		bot.button("Open").click();
	}

	@Override
	protected void shareTestProjectWithVersioningBackend() {
		openProjectExplorerView();
		openShareProjectDialog();
		
		// Open the share project dialog and share the project
		bot.table().select("SVN");
		bot.button("Next >").click();
		bot.button("Next >").click();
		bot.button("Next >").click();
		bot.checkBox("Launch the Commit Dialog for the shared resources").click();
		bot.button("Finish").click();
		
		// Now wait until the project is actually shared
		while (getTreeNodeContaining(upstreamRepositoryPartName) == null) {
			waitForEditingDomainAndUiThread();
		}
		
		openVirtualSatelliteNavigatorView();
	}
	
	@Override
	protected void tearDownVersioningBackend() throws IOException {
		openSvnPerspective();
		bot.viewByTitle("SVN Repositories").show();
		
		getTreeNodeContaining(upstreamRepositoryPartName).contextMenu("Discard Location").click();
		bot.button("Yes").click();
	}

}
