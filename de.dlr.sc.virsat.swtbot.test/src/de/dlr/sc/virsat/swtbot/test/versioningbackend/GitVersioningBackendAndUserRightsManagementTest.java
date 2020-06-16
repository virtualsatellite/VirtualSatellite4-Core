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

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;

public class GitVersioningBackendAndUserRightsManagementTest extends AVersioningBackendAndUserRightsManagementTest {

	public static final String TEST_REPO_PATH_UPSTREAM = "SwtBotGitBackendUpstreamRepo";
	public static final String TEST_REPO_PATH_LOCAL = "SwtBotGitBackendLocalRepo";
	
	protected String upstreamRepoPathName;
	protected String localRepoPathName;
	
	protected Path upstreamRepoPath;
	protected Path localRepoPath;
	
	@Override
	protected void setUpVersioningBackend() throws IOException {
		// Create the temporary directories necessary to set up the repositories
		upstreamRepoPath = VirSatFileUtils.createAutoDeleteTempDirectory(TEST_REPO_PATH_UPSTREAM);
		localRepoPath = VirSatFileUtils.createAutoDeleteTempDirectory(TEST_REPO_PATH_LOCAL);
		
		upstreamRepoPathName = upstreamRepoPath.toString();
		localRepoPathName = localRepoPath.toString();
		
		// First step open the perspective for git operations
		openGitPerspective();
		
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

	/**
	 * This method opens the standard Eclipse Git perspective
	 */
	protected void openGitPerspective() {
		bot.toolbarButtonWithTooltip("Open Perspective").click();
		bot.table().select("Git");
		bot.button("Open").click();
	}
	
	@Override
	protected void shareTestProjectWithVersioningBackend() {
		openProjectExplorerView();
		openShareProjectDialog();

		bot.table().select("Git");
		bot.button("Next >").click();
		bot.comboBox().setSelection(0);
		bot.button("Finish").click();
		
		openVirtualSatelliteNavigatorView();
	}

	@Override
	protected void tearDownVersioningBackend() throws IOException {
		// First step open the perspective for git operations
		openGitPerspective();
		
		// Remove the local repository first
		SWTBotTreeItem treeLocalRepo = getTreeNodeStartingWith(localRepoPath.getFileName().toString());
		treeLocalRepo.contextMenu("Delete Repository...").click();
		bot.checkBox("Delete Git repository data and history:").click();
		bot.button("Delete").click();
		
		// Than remove the bare upstream repository
		SWTBotTreeItem treeUpstreamRepo = getTreeNodeStartingWith(upstreamRepoPath.getFileName().toString());
		treeUpstreamRepo.contextMenu("Delete Repository...").click();
		bot.button("OK").click();
	}

	@Override
	protected void testCommitProjectAssert() {
	
		openGitPerspective();
		
		// Assert that commit message arrived in Local Repository
		bot.viewByTitle("Git Repositories").show();
		getTreeNodeContaining(TEST_REPO_PATH_LOCAL).select();
		bot.viewById("org.eclipse.team.ui.GenericHistoryView").show();
		SWTBotTableItem historyTable = bot.table().getTableItem(0);
		
		String message = historyTable.getText();
		String message0 = historyTable.getText(0);
		String message1 = historyTable.getText(1);
		
	}

}
