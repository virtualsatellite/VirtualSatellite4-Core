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

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.swtbot.eclipse.finder.matchers.WithTitle;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.hamcrest.core.StringContains;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.wc2.SvnCheckout;
import org.tmatesoft.svn.core.wc2.SvnCommit;
import org.tmatesoft.svn.core.wc2.SvnOperationFactory;
import org.tmatesoft.svn.core.wc2.SvnTarget;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;


public class SvnVersioningBackendAndUserRightsManagementTest extends AVersioningBackendAndUserRightsManagementTest {

	public static final String TEST_REPO_PATH_UPSTREAM = "SwtBotSvnBackendUpstreamRepo";
	public static final String TEST_REPO_PATH_REMOTE = "SwtBotGitBackendRemoteRepo";
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

	@Override
	protected void testCommitProjectAssert() {
		openSvnPerspective();
		
		// Retrieve the commit messages
		String commitMessageUpstream = getCommitMessageFor(TEST_REPO_PATH_UPSTREAM);
		
		// Now get the commit message in the remote repository
		assertEquals("Local Commit Message as expected", SWTBOT_COMMIT_MESSAGE, commitMessageUpstream);
	}
	
	/**
	 * Method to retrieve the latest commit message from a SVN Repository
	 * @param repoName The name of the repository to get the message for
	 * @return The Commit message as string.
	 */
	protected String getCommitMessageFor(String repoName) {
		
		SWTBotTree historyTree = waitForCommit(repoName);
		
		// Get the entry from the commit
		SWTBotTreeItem historyTableItem0 = historyTree.getAllItems()[0];
		String commitMessage = historyTableItem0.cell(4);
		
		return commitMessage;
	}
	
	/**
	 * Waits until the initial commits are present in the history view of a repository
	 * @param repoName The name of the repository to wait for
	 * @return the tree of the commit history view
	 */
	protected SWTBotTree waitForCommit(String repoName) {
		bot.viewByTitle("SVN Repositories").show();
		getTreeNodeContaining(repoName).select();
		
		// Open the history view
		SWTBotView historyView = bot.view(WithTitle.withTitle(StringContains.containsString("History")));
		historyView.show();
		SWTBotTree historyTree = historyView.bot().tree();
		
		// Now wait until the commit is present in the view
		// This is the case if we have three items present in the tree,
		// because we expect that repository creation and project sharing
		// each created a commit.
		while (historyTree.visibleRowCount() < 3) {
			historyTree.contextMenu("Refresh").click();
			waitForEditingDomainAndUiThread();
		}
		return historyTree;
	}

	@Override
	protected void testUpdateProjectChangeAndCommitRemote() throws Exception {
		
		// Wait for the commit so we can checkout
		openSvnPerspective();
		waitForCommit(TEST_REPO_PATH_UPSTREAM);
		
		// Checkout the upstream repository in a new SVN on file system level
		Path remoteRepoPath = VirSatFileUtils.createAutoDeleteTempDirectory(TEST_REPO_PATH_REMOTE);
		final SvnOperationFactory svnOperationFactory = new SvnOperationFactory();
		try {
			final SvnCheckout checkout = svnOperationFactory.createCheckout();
			checkout.setSingleTarget(SvnTarget.fromFile(remoteRepoPath.toFile()));
			checkout.setSource(SvnTarget.fromURL(SVNURL.fromFile(upstreamRepoPath.toFile())));
			checkout.run();
		} finally {
			svnOperationFactory.dispose();
		}
		
		// Now where the repository is cloned change the System Entry
		String repoRoleManagementFileName = SWTBOT_TEST_PROJECTNAME + File.separator
				+ VirSatProjectCommons.FOLDERNAME_DATA + File.separator
				+ VirSatProjectCommons.FILENAME_ROLE_MANAGEMENT;
		Path pathRepoRoleManagementFile = new File(
				remoteRepoPath.toFile(), repoRoleManagementFileName).toPath(); 
				
		String content = new String(Files.readAllBytes(pathRepoRoleManagementFile), StandardCharsets.UTF_8);
		content = content.replaceAll("System", "SubSystem");
		Files.write(pathRepoRoleManagementFile, content.getBytes(StandardCharsets.UTF_8));
		
		// Now commit the changes
		try {
			final SvnCommit commit = svnOperationFactory.createCommit();
			commit.setSingleTarget(SvnTarget.fromFile(pathRepoRoleManagementFile.toFile()));
			commit.setCommitMessage("Commit message");
			commit.run();
		} finally {
			svnOperationFactory.dispose();
		}
	}

}
