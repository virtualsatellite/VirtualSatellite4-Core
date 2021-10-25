/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.requirements.ui.wizard;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
import org.eclipse.equinox.security.storage.StorageException;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.lyo.client.exception.ResourceNotFoundException;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.impl.CategoryAssignmentImpl;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.extension.requirements.doors.client.DoorsSynchroClient;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollection;
import de.dlr.sc.virsat.model.extension.requirements.model.SynchronizationConfiguration;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.uiengine.ui.wizard.AImportExportPage;
/**
 * A page to choose an existing Doors synchronization configuration
 *
 */
public class DoorsActivateSynchroConfigurationPage extends AImportExportPage {
	private static final String PAGE_TITEL = "Requirements Doors Synchronizer";
	private String server;
	private static String project;
	private String user;
	private String password;
	private static final String SECURE_STORAGE_DOORS = "DoorsNG user credentials (" + project + ")";
	private DoorsSynchroClient doorsSynchroClient = new DoorsSynchroClient();

	/**
	 * Standard constructor
	 * 
	 * @param model            the root model
	 */
	protected DoorsActivateSynchroConfigurationPage(IContainer model) {
		super(PAGE_TITEL);
		setTitle(PAGE_TITEL);
		setModel(model);
		setDescription("Please choose an existing Doors synchronization configuration.");
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		createTreeViewer();
	}

	/**
	 * Create a tree viewer with filters to show only relevant tree elements for
	 * Doors import /export
	 */
	protected TreeViewer createTreeViewer() {
		TreeViewer treeViewer = createTreeUI();
		VirSatFilteredWrappedTreeContentProvider filteredCp = (VirSatFilteredWrappedTreeContentProvider) treeViewer
				.getContentProvider();
		filteredCp.addClassFilter(CategoryAssignment.class);
		filteredCp.addClassFilter(ArrayInstance.class);
		filteredCp.addClassFilter(ComposedPropertyInstance.class);
		filteredCp.addStructuralElementIdFilter(
				RequirementsConfigurationCollection.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addCategoryIdFilter(SynchronizationConfiguration.FULL_QUALIFIED_CATEGORY_NAME);
		return treeViewer;
	}

	/**
	 * If an existing configuration is selected the wizard can be finished otherwise
	 * we need the next side
	 * 
	 * @return if wizard can be finished
	 */
	public boolean canFinish() {
		Object selection = getSelection();
		if (selection instanceof CategoryAssignment) {
			String categoryFqn = ((CategoryAssignment) selection).getType().getFullQualifiedName();
			return categoryFqn.equals(SynchronizationConfiguration.FULL_QUALIFIED_CATEGORY_NAME);
		}
		return false;
	}

	@Override
	public boolean canFlipToNextPage() {
		return super.canFlipToNextPage() && !canFinish();
	}

	@Override
	public boolean isComplete() {
		return isCurrentPage() && isSynchronizationConfigValid();
	}

	@Override
	public Object getSelection() {
		Object selected = super.getSelection();
		if (selected instanceof ComposedPropertyInstance) {
			selected = ((ComposedPropertyInstance) selected).getTypeInstance();
		}
		return selected;
	}

	/**
	 * Method to check if the chosen synchronizationConfiguration is valid
	 * 
	 * @return
	 */
	public boolean isSynchronizationConfigValid() {
		Object selection = getSelection();
		if (selection instanceof CategoryAssignment) {
			CategoryAssignmentImpl catAssignment = (CategoryAssignmentImpl) selection;
			SynchronizationConfiguration synchronizationConfig = new SynchronizationConfiguration(catAssignment);
			server = synchronizationConfig.getServerUrl();
			project = synchronizationConfig.getProjectName();
			if (isDoorsConnectionValid()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * Method to check if connection to the doors server is possible
	 * 
	 * @return
	 * @throws ResourceNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private boolean isDoorsConnectionValid() {
		try {
			if (!server.isEmpty()) {
				getUserCredentials();
				doorsSynchroClient.init(server, user, password, project);
				try {
					ServiceProvider serviceProvider = DoorsSynchroClient.getServiceProvider();
					String title = serviceProvider.getTitle();
					if (title.equals(project)) {
						return true;
					}
				} catch (NullPointerException e) {
					MessageBox mb = new MessageBox(getShell(), SWT.ICON_WARNING);
					mb.setMessage("Please check your Doors user credentials");
					mb.open();
					if (mb.open() == Window.OK) {
						isDoorsConnectionValid();
					}
				}
			}
		
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Method to check if user credentials from the Eclipse secure storage are available
	 * @return
	 */
	private void getUserCredentials() {
		ISecurePreferences securePreferences = SecurePreferencesFactory.getDefault();
		ISecurePreferences node = securePreferences.node(SECURE_STORAGE_DOORS);
		try {
			if (!node.get("user", "").isEmpty()) {
				user = node.get("user", "");
				password = node.get("password", "");
			} else {
				DoorsUserCredentialsDialog dialog = new DoorsUserCredentialsDialog(getShell());
				// get the new values from the dialog
				if (dialog.open() == Window.OK) {
					user = dialog.getUser();
					password = dialog.getPassword();
				} else if (dialog.open() == Window.CANCEL) {
					dialog.close();
				}
			}
		} catch (StorageException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public DoorsActivateSynchronizationWizard getWizard() {
		return (DoorsActivateSynchronizationWizard) super.getWizard();
	}
}