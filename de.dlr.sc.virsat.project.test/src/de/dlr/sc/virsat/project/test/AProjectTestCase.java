/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.function.Supplier;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatEditingDomainClipBoard;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * Abstract test case which creates a project for further testing
 */
public abstract class AProjectTestCase {

	protected static final int MAX_TEST_CASE_TIMEOUT_SECONDS = 30;
	
	@Rule
	public TestRule globalTimeout = new DisableOnDebug(Timeout.seconds(MAX_TEST_CASE_TIMEOUT_SECONDS));
	
	protected static final String TEST_PROJECT_NAME = "testProject";
	private static final String JUNIT_DEBUG_PROJECT_TEST_CASE = "JUNIT_DEBUG_PROJECT_TEST_CASE";
	private static final String JUNIT_DEBUG_PROJECT_TEST_CASE_TRUE = "true"; 
	protected IProject testProject;
	protected VirSatTransactionalEditingDomain editingDomain;
	protected Repository repository;
	protected VirSatProjectCommons projectCommons;
	protected VirSatResourceSet rs;
	
	protected List<IProject> testProjects = new ArrayList<>();
	
	/**
	 * Use this method to create a new test project and to remember it for the test case.
	 * Remembering it means that it will be removed by the tear down method.
	 * @param projectName The name of the Test Project
	 * @return The instance of the Test Project in the Workspace as IProject
	 * @throws CoreException May throw an exception which can be handled by the test case itself
	 */
	protected IProject createTestProject(String projectName) throws CoreException {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		testProjects.add(project);
		if (!project.exists()) {
			project.create(null);
			project.open(null);
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "Created new test project " +  project.getName()));
		}
		return project;
	}
	
	@Rule 
	public TestName testMethodName = new TestName();
	
	@Before
	public void setUp() throws CoreException {
		if (JUNIT_DEBUG_PROJECT_TEST_CASE_TRUE.equalsIgnoreCase(System.getenv(JUNIT_DEBUG_PROJECT_TEST_CASE))) {
			System.out.println("AProjectTestCase-Debug: " + this.getClass().getSimpleName() + "." + testMethodName.getMethodName() + " - setUp()");
		}

		testProject = createTestProject(getProjectName());
		projectCommons = new VirSatProjectCommons(testProject);
		addProjectFileStructure();

		previousUser = UserRegistry.getInstance().getUserName();
		setUserAndRights();
		
		VirSatEditingDomainClipBoard.INSTANCE.clear();
		VirSatEditingDomainRegistry.INSTANCE.clear();  
	}

	protected void addProjectFileStructure() {
		projectCommons.createProjectStructure(null);
	}
	
	private String previousUser;
	
	/**
	 * Method to adjust the User rights for the test cases
	 * This method gets called by the constructor
	 */
	protected void setUserAndRights() {
		UserRegistry.getInstance().setSuperUser(true);
	}
	
	@After
	public void tearDown() throws CoreException {
		if (JUNIT_DEBUG_PROJECT_TEST_CASE_TRUE.equalsIgnoreCase(System.getenv(JUNIT_DEBUG_PROJECT_TEST_CASE))) {
			System.out.println("AProjectTestCase-Debug: " + this.getClass().getSimpleName() + "." + testMethodName.getMethodName() + " - tearDown()");
		}

		// make sure all Editing Domains are well removed and disposed
		VirSatEditingDomainRegistry.INSTANCE.clear();
		VirSatTransactionalEditingDomain.clearResourceEventListener();
		VirSatTransactionalEditingDomain.clearAccumulatedRecourceChangeEvents();
		editingDomain = null;
		
		// Make sure all projects that were created get removed again
		boolean failed = true;
		while (failed) {
			try {
				for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
					if (project.exists()) {
						project.refreshLocal(IResource.DEPTH_INFINITE, null);
						project.delete(true, null);
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "Deleted test project " +  project.getName()));
					}
				}
				failed = false;
			} catch (Exception e) {
				Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Failed deleting project: ", e));
			}
		}
		
		//CHECKSTYLE:OFF
		// Bring down user settings to previous state and disable superUser rights
		UserRegistry.getInstance().setSuperUser(false);
		UserRegistry.getInstance().setUser(previousUser, 356);
		//CHECKSTYLE:ON
	}
	
	/**
	 * This method creates an unmanaged ResourceSet which is non transactional.
	 * The method also creates a repository
	 */
	protected void addResourceSetAndRepository() {
		rs = VirSatResourceSet.createUnmanagedResourceSet(testProject);
		rs.initializeModelsAndResourceSet();
		repository = rs.getRepository();
	}
	
	/**
	 * Creates the editing domain and a repository for test cases.
	 * This includes a ResourceSet with Transactional Editing Domain
	 * @param handleExternalWorkspaceChanges weather external resource changes should be handled
	 * 
	 */
	protected void addEditingDomainAndRepository(boolean handleExternalWorkspaceChanges) {
		VirSatResourceSet resSetRepositoryTarget = VirSatResourceSet.getResourceSet(testProject, handleExternalWorkspaceChanges);
		editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(testProject);
		Command cmd = resSetRepositoryTarget.initializeModelsAndResourceSet(null, editingDomain);
		editingDomain.getCommandStack().execute(cmd);
		editingDomain.saveAll();

		repository = resSetRepositoryTarget.getRepository();
		rs = editingDomain.getResourceSet();
	}
	
	/**
	 * Creates the editing domain and a repository for test cases.
	 * This includes a ResourceSet with Transactional Editing Domain
	 * 
	 */
	protected void addEditingDomainAndRepository() {
		addEditingDomainAndRepository(false);
	}
	
	/**
	 * @return name of the test project in the workbench
	 */
	protected String getProjectName() {
		return TEST_PROJECT_NAME + "_" + this.getClass().getSimpleName();
	}
	
	
	/**
	 * Simple method to execute code as part of a recording command
	 * @param runnable the lambda to be executed with no parameters and no return value. 
	 */
	protected <T> void executeAsCommand(Runnable runnable) {
		// Now wrap the lambda into a recording command and execute it
		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				runnable.run();
			}
		});
	}
		
	/**
	 * Simple method to execute code as part of a recording command
	 * @param supplier the lambda to be executed with no parameters. 
	 * @return the result of the executed lambda
	 */
	protected <T> T executeAsCommand(Supplier<T> supplier) {
		
		// Use a final vector to store the return object from the lambda expression
		final Vector<T> store = new Vector<>();
		
		// Now wrap the lambda into a recording command and execute it
		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				// execute the lambda and remember the result
				store.add(supplier.get());
			}
		});
		
		// Finally hand back the result of the executed command
		return store.firstElement();
	}
	
	/**
	 * This is a retry wrapper for assert statements. It allows to retry a single assert statement
	 * a given times waiting for some given time between each retry. It throws an assertion if it fails.
	 * at the last retry
	 * @param retries The amount of retries to be executed
	 * @param milliSeconds The amount of milliseconds to wait before retrying
	 * @param assertRunnable the runnable holding the actual assert Statement
	 */
	protected void assertRetry(int retries, int milliSeconds, Runnable assertRunnable) {
		assertTrue("Cannot execute negative retries", retries > 0);
		assertTrue("Cannot wait a negative amount of time", milliSeconds > 0);
		
		for (int count = 1; count <= retries; count++) {
			try {
				try {
					assertRunnable.run();
					// In case nothing failed just leave the method
					break;
				} catch (AssertionError e) {
					// If it failed in the last retry, than throw the error
					if (count >= retries) {
						String message = String.format(
							"assertRetry failed after %i rerties waiting each for %i milliseconds with the following exception:",
							retries,
							milliSeconds
						);
						throw new AssertionError(message, e);
					}	
				}
				// Apparently we need to retry, lets wait a bit
				Thread.sleep(milliSeconds);
			} catch (InterruptedException e) {
				throw new AssertionError("assertRetry got interrupted", e);
			} 
		}
	}
}
