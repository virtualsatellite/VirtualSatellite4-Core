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
import java.net.URISyntaxException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
import org.eclipse.equinox.security.storage.StorageException;
import org.eclipse.lyo.client.exception.ResourceNotFoundException;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.requirements.doors.client.DoorsSynchroClient;
import de.dlr.sc.virsat.model.extension.requirements.model.ImportConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollection;
import de.dlr.sc.virsat.model.extension.requirements.model.SynchronizationConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.ui.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.uiengine.ui.wizard.AImportExportPage;

/**
 * A page to add a new Doors synchronization configuration
 *
 */
public class DoorsAddSynchroConfigurationPage extends AImportExportPage {

	private static final String PAGE_TITEL = "Requirements Doors Synchronizer";
	private static final String DOORS_SERVER_URL_LABEL = "Doors Server Url:";
	private static final String DOORS_PROJECT_NAME_LABEL = "Doors Project Name:";
	private static final String DOORS_USER_ID_LABEL = "Doors User ID:";
	private static final String DOORS_PASSWORD_LABEL = "Doors Password:";

	private Text serverUrlField;
	private Text projectNameField;
	private Text userIdField;
	private Text passwordField;

	private String server;
	private String project;
	private String password;
	private String user;

	private Concept concept;

	protected static final int COLUMNS = 3;
	protected static final int WITH_TEXT = 200;

	private DoorsChooseImportConfigurationPage importConfig;
	private DoorsSynchroClient doorsSynchroClient = new DoorsSynchroClient();

	/**
	 * Standard constructor
	 * 
	 * @param model            the root model
	 * @param importPage
	 * @param containingWizard the containing wizard
	 */
	protected DoorsAddSynchroConfigurationPage(IContainer model, DoorsChooseImportConfigurationPage importPage) {
		super(PAGE_TITEL);
		setTitle(PAGE_TITEL);
		setModel(model);
		this.importConfig = importPage;
		setDescription("Please add a synchronization configuration and choose an import configuration.");
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		createDoorsPropertiesUI(parent);
	}

	@Override
	public boolean isSelectionValid() {
		Object selection = getSelection();
		if (selection instanceof CategoryAssignment) {
			String categoryFqn = ((CategoryAssignment) selection).getType().getFullQualifiedName();
			return categoryFqn.equals(ImportConfiguration.FULL_QUALIFIED_CATEGORY_NAME);
		}
		return false;
	}

	@Override
	public boolean canFlipToNextPage() {
		return super.canFlipToNextPage() && !canFinish();
	}

	@Override
	public boolean isComplete() {
		return isCurrentPage() && isDoorsConnectionValid();
	}

	/**
	 * If an existing configuration is selected the wizard can be finished otherwise
	 * we need the next side
	 * 
	 * @return if wizard can be finished
	 */
	public boolean canFinish() {
		return isDoorsConnectionValid();
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
	 * Create the UI for Doors properties
	 * 
	 * @param parent the parent composite
	 */
	private void createDoorsPropertiesUI(Composite parent) {
		Composite doorsComposite = new Composite((Composite) getControl(), SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		doorsComposite.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);

		Label serverUrlLabel = new Label(doorsComposite, SWT.NONE);
		serverUrlLabel.setText(DOORS_SERVER_URL_LABEL);

		serverUrlField = new Text(doorsComposite, SWT.SINGLE | SWT.BORDER);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.widthHint = WITH_TEXT;
		serverUrlField.setLayoutData(gridData);

		Label projectNameLabel = new Label(doorsComposite, SWT.NONE);
		projectNameLabel.setText(DOORS_PROJECT_NAME_LABEL);

		projectNameField = new Text(doorsComposite, SWT.SINGLE | SWT.BORDER);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.widthHint = WITH_TEXT;
		projectNameField.setLayoutData(gridData);

		Label userIdLabel = new Label(doorsComposite, SWT.NONE);
		userIdLabel.setText(DOORS_USER_ID_LABEL);

		userIdField = new Text(doorsComposite, SWT.SINGLE | SWT.BORDER);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 1;
		gridData.widthHint = WITH_TEXT;
		userIdField.setLayoutData(gridData);

		Label passwordLabel = new Label(doorsComposite, SWT.NONE);
		passwordLabel.setText(DOORS_PASSWORD_LABEL);

		passwordField = new Text(doorsComposite, SWT.SINGLE | SWT.BORDER | SWT.PASSWORD);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 1;
		gridData.widthHint = WITH_TEXT;
		passwordField.setLayoutData(gridData);

		Button button = new Button(doorsComposite, SWT.BUTTON1);
		button.setText("Add Synchronization Configuration");
		button.addSelectionListener(SelectionListener.widgetSelectedAdapter(e -> addSynchronizationConfiguration()));

		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		button.setLayoutData(gridData);
	}

	public void selectionChanged() {
		setPageComplete(isComplete());
		if (getWizard().getContainer() != null) {
			getWizard().getContainer().updateButtons();
		}
	}

	/**
	 * Check if client can build a connection to the selected doors server
	 * @return true if connection valid or false
	 */
	public boolean isDoorsConnectionValid() {
		server = serverUrlField.getText();
		project = projectNameField.getText();
		user = userIdField.getText();
		password = passwordField.getText();
		StructuralElementInstance reqConfiguration = importConfig.getStructuralElementInstance();
		if (reqConfiguration != null) {
			concept = (Concept) reqConfiguration.getType().eContainer();
			try {
				if (!server.isEmpty()) {
					doorsSynchroClient.init(server, user, password, project);
					ServiceProvider serviceProvider = DoorsSynchroClient.getServiceProvider();
					String title = serviceProvider.getTitle();
					if (title.equals(project)) {
						saveUserCredentials(user, password, project);
						return true;
					}
				}
			} catch (UnrecoverableKeyException | CertificateException | IOException e) {
				e.printStackTrace();
				Activator.getDefault().getLog().error(e.getMessage());
			} catch (ResourceNotFoundException | URISyntaxException | NullPointerException e) {
				openMessageBox();
			}
		}
		return false;
	}

	/**
	 * Adds the synchronization config to a Doors server
	 */
	private void addSynchronizationConfiguration() {
		if (isDoorsConnectionValid()) {
			SynchronizationConfiguration synchroConfig = new SynchronizationConfiguration(concept);
			synchroConfig.setName(project + " Synchronization");
			synchroConfig.setServerUrl(server);
			synchroConfig.setProjectName(project);

			EObject reqConfiguration = importConfig.getStructuralElementInstance();
			RequirementsConfigurationCollection collection = new RequirementsConfigurationCollection(
					(StructuralElementInstance) reqConfiguration);
			Repository repository = (Repository) concept.eContainer();
			VirSatTransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(repository);
			CompoundCommand cc = new CompoundCommand();
			cc.append(collection.add(editingDomain, synchroConfig));
			editingDomain.getCommandStack().execute(cc);
			selectionChanged();
		} else {
			openMessageBox();
		}
	}

	/**
	 * Stores the user credentials to the eclipse storage store
	 * @param user
	 * @param password
	 * @param projectName
	 */
	public void saveUserCredentials(String user, String password, String projectName) {
		ISecurePreferences securePreferences = SecurePreferencesFactory.getDefault();
		ISecurePreferences node = securePreferences.node("DoorsNG user credentials (" + projectName + ")");
		try {
			node.put("user", user, true);
			node.put("password", password, true);
		} catch (StorageException e1) {
			e1.printStackTrace();
		}
	}
	
	private void openMessageBox() {
		MessageBox mb = new MessageBox(getShell(), SWT.ICON_WARNING);
		mb.setText("Warning");
		mb.setMessage("Connection to Doors server not possible. Please retry.");
		mb.open();
	}

	@Override
	public DoorsAddSynchronizationWizard getWizard() {
		return (DoorsAddSynchronizationWizard) super.getWizard();
	}
}