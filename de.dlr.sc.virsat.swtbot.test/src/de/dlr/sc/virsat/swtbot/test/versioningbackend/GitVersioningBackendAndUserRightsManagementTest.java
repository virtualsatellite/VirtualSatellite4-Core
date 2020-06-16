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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.jgit.api.Git;
import org.eclipse.swtbot.eclipse.finder.matchers.WithTitle;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.hamcrest.core.StringContains;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;

public class GitVersioningBackendAndUserRightsManagementTest extends AVersioningBackendAndUserRightsManagementTest {

	public static final String TEST_REPO_PATH_UPSTREAM = "SwtBotGitBackendUpstreamRepo";
	public static final String TEST_REPO_PATH_LOCAL = "SwtBotGitBackendLocalRepo";
	public static final String TEST_REPO_PATH_REMOTE = "SwtBotGitBackendRemoteRepo";
	
	protected String upstreamRepoPathName;
	protected String localRepoPathName;
	
	protected Path upstreamRepoPath;
	protected Path localRepoPath;
	protected Path remoteRepoPath;
	
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
		SWTBotView botRepoView = bot.viewByTitle("Git Repositories");
		botRepoView.show();
		botRepoView.setFocus();
		
		SWTBotTreeItem treeLocalRepo = getTreeNodeContaining(TEST_REPO_PATH_LOCAL);
		treeLocalRepo.contextMenu("Delete Repository...").click();
		bot.checkBox("Delete Git repository data and history:").click();
		bot.button("Delete").click();
		
		// Than remove the bare upstream repository
		SWTBotTreeItem treeUpstreamRepo = getTreeNodeContaining(TEST_REPO_PATH_UPSTREAM);
		treeUpstreamRepo.contextMenu("Delete Repository...").click();
		bot.button("OK").click();
	}

	@Override
	protected void testCommitProjectAssert() {
	
		openGitPerspective();
		
		// Retrieve the commit messages
		String commitMessageLocal = getCommitMessageFor(TEST_REPO_PATH_LOCAL);
		String commitMessageUpstream = getCommitMessageFor(TEST_REPO_PATH_UPSTREAM);
		
		// Now get the commit message in the remote repository
		assertEquals("Local Commit Message as expected", SWTBOT_COMMIT_MESSAGE, commitMessageLocal);
		assertEquals("Local Commit Message as expected", SWTBOT_COMMIT_MESSAGE, commitMessageUpstream);
	}
	
	/**
	 * Method to retrieve the latest commit message from a Git Repository
	 * @param repoName The name of the Repo to get the message for
	 * @return The Commit message as string.
	 */
	protected String getCommitMessageFor(String repoName) {
		// Assert that commit message arrived in Local Repository
		bot.viewByTitle("Git Repositories").show();
		getTreeNodeContaining(repoName).select();
		
		// Open the history view and get the entry from the commit
		SWTBotView historyView = bot.view(WithTitle.withTitle(StringContains.containsString("History")));
		historyView.show();
		SWTBotTable historyTable =  historyView.bot().table();
		SWTBotTableItem historyTableItem0 = historyTable.getTableItem(0);
	
		// Retrieve the commit message
		String commitMessage = historyTableItem0.getText(1);
		return commitMessage;
	}

	@Override
	protected void testUpdateProjectChangeAndCommitRemote() throws Exception {
		remoteRepoPath = VirSatFileUtils.createAutoDeleteTempDirectory(TEST_REPO_PATH_REMOTE);
		
		Git.cloneRepository()
			.setURI(upstreamRepoPath.toUri().toString())
			.setDirectory(remoteRepoPath.toFile())
			.call();
		
		
		
	}
}
