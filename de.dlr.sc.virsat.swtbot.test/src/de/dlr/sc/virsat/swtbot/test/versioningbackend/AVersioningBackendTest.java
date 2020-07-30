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
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.swtbot.test.ASwtBotTestCase;

public abstract class AVersioningBackendTest extends ASwtBotTestCase {

	@Before
	public void before() throws Exception {
		super.before();
		
		// Now prepare the versioning-backends
		setUpVersioningBackend();
		
		// Switch back to virtual Satellite perspective
		openCorePerspective();

		// Share the test project with the backend
		shareTestProjectWithVersioningBackend();
	}
	
	@Override
	public void tearDown() throws CoreException, IOException {
		super.tearDown();
		
		// Remove repositories after the test case execution
		tearDownVersioningBackend();
	}
	
	/**
	 * Abstract method to be implemented to setup the versioning-backend for the test cases
	 * @throws IOException
	 */
	protected abstract void setUpVersioningBackend() throws IOException;
	
	/**
	 * Abstract method to be implemented to clean up and tear down the versioning-backend
	 * @throws IOException
	 */
	protected abstract void tearDownVersioningBackend() throws IOException;
	
	/**
	 * Abstract method to be implemented to share the standard test case project with the versioning-backend
	 */
	protected abstract void shareTestProjectWithVersioningBackend();
	
	/**
	 * Method to open the share project dialog
	 * @throws InterruptedException 
	 */
	protected void openShareProjectDialog() {
		SWTBotTreeItem projectNode = bot.tree().getTreeItem("SWTBotTestProject").select();
		projectNode.contextMenu("Team").menu("Share Project...").click();
	}
	
	public static final String SWTBOT_COMMIT_MESSAGE = "SwtBotTest - Commit Message!";
	public static final String SWTBOT_COMMIT_MESSAGE_REMOTE = "SwtBotTest - Commit from Remote!";
	
	@Test
	public void testCommitProject() {
	
		openVirtualSatelliteNavigatorView();
		
		commitProject();
		
		// Call backend specific assertion of commit results;
		testCommitProjectAssert();
	}	
	
	/**
	 * method to be implemented for backend specific assert code
	 * of the basic Commit test.
	 */
	protected abstract void testCommitProjectAssert();

	@Test
	public void testUpdateProject() throws Exception {
		openVirtualSatelliteNavigatorView();
		
		commitProject();
		
		// Call backend specific update function the role management with
		// In this test case the name of the discipline is changed
		testUpdateProjectChangeAndCommitRemote("System", "SubSystem");
		
		// Now open the VirSat Navigator and the RoleManagement Editor
		// Run the update and check that both the navigator and the editor received the correct update
		openCorePerspective();
		SWTBotView navigatorView = openVirtualSatelliteNavigatorView();
		
		// Prepare the Navigator to display the Discipline and open the editor
		SWTBotTreeItem roleManagementNode = navigatorView.bot().tree().getTreeItem("SWTBotTestProject").getNode("Role Management");
		roleManagementNode.expand();
		roleManagementNode.getNode("Discipline: System");
		roleManagementNode.doubleClick();
		
		// Run the update in Virtual Satellite
		updateProject();
		
		// Assert that the node got correctly updated in the navigator
		navigatorView.bot().tree().getTreeItem("SWTBotTestProject").getNode("Role Management").getNode("Discipline: SubSystem");
		
		// open the editor and check if the discipline has been updated
		SWTBotEditor rmEditor = bot.editorByTitle("Role Management");
		rmEditor.show();
		String editorDisciplineUpdate = rmEditor.bot().table().getTableItem(0).getText();
	
		assertEquals("Got correct discipline", "SubSystem", editorDisciplineUpdate);
	}

	/**
	 * Implement this method to change the repository via a remote instance of it.
	 * A good change could be to give something a new name, etc.
	 * @param replace String to be replaced
	 * @param with String to replace with
	 * @throws Exception
	 */
	
	protected abstract void testUpdateProjectChangeAndCommitRemote(String replace, String with) throws Exception;
	
	@Test
	public void testUpdateProjectChangedDiscipline() throws Exception {
		openVirtualSatelliteNavigatorView();
		
		commitProject();
		
		// Call backend specific update function the role management with
		// In this test case the assigned user is changed
		testUpdateProjectChangeAndCommitRemote(System.getProperty("user.name"), "somebody_else");
		
		// Now open the VirSat Navigator and the RoleManagement Editor
		// Run the update and check that both the navigator and the editor received the correct update
		openCorePerspective();
		openVirtualSatelliteNavigatorView();
		
		// Prepare the Navigator to display the Discipline and open the editor
		SWTBotTreeItem roleManagementNode = bot.tree().getTreeItem("SWTBotTestProject").getNode("Role Management");
		roleManagementNode.expand();
		roleManagementNode.getNode("Discipline: System");
		roleManagementNode.doubleClick();
		
		SWTBotEditor rmEditor = bot.editorByTitle("Role Management");
		rmEditor.show();
		
		assertTrue("The button is enabled", rmEditor.bot().button("Add Discipline").isEnabled());
		
		// Run the update in Virtual Satellite
		updateProject();
		
		// Open the editor and check if that the button is disabled
		SWTBotEditor rmEditor2 = bot.editorByTitle("Role Management");
		rmEditor2.show();
		
		assertForTimes("Check that the Add Discipline button is not enabled anymore", SWTBOT_TRY_10_TIME, 
			() -> !rmEditor2.bot().button("Add Discipline").isEnabled()
		);
	}
	
	/**
	 * Use the context menu to commit the project and add a message
	 * into the commit dialog. The message will be used for testing 
	 * later, if the commit has arrived as expected.
	 */
	public void commitProject() {
		buildCounter.executeInterlocked(() -> {
			SWTBotTreeItem projectNode = bot.tree().getTreeItem("SWTBotTestProject");
			projectNode.select();
			projectNode.contextMenu("Commit Project to Repository").click();
			bot.text().setText(SWTBOT_COMMIT_MESSAGE);
			bot.button("OK").click();
		});
	}
	
	/**
	 * Use the context menu to update the project.
	 */
	public void updateProject() {
		buildCounter.executeInterlocked(() -> {
			SWTBotTreeItem projectNode = bot.tree().getTreeItem("SWTBotTestProject");
			projectNode.select();
			projectNode.contextMenu("Update Project from Repository").click();
		});
	}
}
