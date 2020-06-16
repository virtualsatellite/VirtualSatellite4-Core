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

import org.eclipse.swtbot.eclipse.finder.matchers.WithTitle;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.hamcrest.core.StringContains;

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

	@Override
	protected void testCommitProjectAssert() {
		openSvnPerspective();
		
		// Retrieve the commit messages
		String commitMessageUpstream = getCommitMessageFor(TEST_REPO_PATH_UPSTREAM);
		
		// Now get the commit message in the remote repository
		assertEquals("Local Commit Message as expected", SWTBOT_COMMIT_MESSAGE, commitMessageUpstream);
	}
	
	protected String getCommitMessageFor(String repoName) {
		// Assert that commit message arrived in Local Repository
		bot.viewByTitle("SVN Repositories").show();
		getTreeNodeContaining(repoName).select();
		
		// Open the history view and get the entry from the commit
		SWTBotView historyView = bot.view(WithTitle.withTitle(StringContains.containsString("History")));
		historyView.show();
		SWTBotTable historyTable =  historyView.bot().table();
		SWTBotTableItem historyTableItem0 = historyTable.getTableItem(0);
	
//		SWTBotTree historyTree = historyView.bot().tree();
//		historyTree.getAllItems();
		
		// Retrieve the commit message
		String commitMessage = historyTableItem0.getText(1);
		return commitMessage;
	}

}
