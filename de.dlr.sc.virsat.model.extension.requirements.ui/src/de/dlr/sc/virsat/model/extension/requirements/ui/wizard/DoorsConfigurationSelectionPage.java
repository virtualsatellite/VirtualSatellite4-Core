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
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
import org.eclipse.equinox.security.storage.StorageException;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.lyo.client.OslcClient;
import org.eclipse.lyo.client.exception.ResourceNotFoundException;
import org.eclipse.lyo.oslc.domains.rm.RequirementCollection;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.requirements.doors.client.DoorsSynchroClient;
import de.dlr.sc.virsat.model.extension.requirements.doors.client.util.DoorsUtil;
import de.dlr.sc.virsat.model.extension.requirements.model.ImportConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollection;
import de.dlr.sc.virsat.model.extension.requirements.model.SynchronizationConfiguration;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.uiengine.ui.wizard.AImportExportPage;

/**
 * A page to select a CSV file to import and the target model element
 *
 */
public class DoorsConfigurationSelectionPage extends AImportExportPage {

	private static final String PAGE_TITEL = "Requirements Doors Import";
	private static final String DOORS_SERVER_URL_LABEL = "Doors Server Url:";
	private static final String DOORS_PROJECT_NAME_LABEL = "Doors Project Name:";
	private static final String DOORS_USER_ID_LABEL = "Doors User ID:";
	private static final String DOORS_PASSWORD_LABEL = "Doors Password:";

	private Text serverUrlField;
	private Text projectNameField;
	private Text userIdField;
	private Text passwordField;

	protected static final int COLUMNS = 2;
	protected static final int WITH_TEXT = 200;

	private DoorsMappingPage mappingPage;
	protected RequirementType reqType = null;

	/**
	 * Standard constructor
	 * 
	 * @param model            the root model
	 * @param containingWizard the containing wizard
	 * @param typeReviewPage   the page that receives the requirement type
	 */
	protected DoorsConfigurationSelectionPage(IContainer model, DoorsMappingPage typeReviewPage) {
		super(PAGE_TITEL);
		setTitle(PAGE_TITEL);
		setModel(model);
		this.mappingPage = typeReviewPage;
		setDescription(
				"Please specify a doors connection and a requirement configuration collection to store the configurations in.");
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		createDoorsPropertiesUI(parent);
		createTreeViewer();

	}

	/**
	 * Create the UI for ReqIF properties
	 * 
	 * @param parent the parent composite
	 */
	private void createDoorsPropertiesUI(Composite parent) {
		Composite propertiesComposite = new Composite((Composite) getControl(), SWT.FILL);
		propertiesComposite.setLayout(new GridLayout(COLUMNS, false));
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		propertiesComposite.setLayoutData(data);

		Label serverUrlLabel = new Label(propertiesComposite, SWT.NONE);
		serverUrlLabel.setText(DOORS_SERVER_URL_LABEL);

		serverUrlField = new Text(propertiesComposite, SWT.SINGLE | SWT.BORDER);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		data.horizontalAlignment = SWT.END;
		data.widthHint = WITH_TEXT;
		serverUrlField.setLayoutData(data);

		Label projectNameLabel = new Label(propertiesComposite, SWT.NONE);
		projectNameLabel.setText(DOORS_PROJECT_NAME_LABEL);

		projectNameField = new Text(propertiesComposite, SWT.SINGLE | SWT.BORDER);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		data.horizontalAlignment = SWT.END;
		data.widthHint = WITH_TEXT;
		projectNameField.setLayoutData(data);

		Label userIdLabel = new Label(propertiesComposite, SWT.NONE);
		userIdLabel.setText(DOORS_USER_ID_LABEL);

		userIdField = new Text(propertiesComposite, SWT.SINGLE | SWT.BORDER);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		data.horizontalAlignment = SWT.END;
		data.widthHint = WITH_TEXT;
		userIdField.setLayoutData(data);

		Label passwordLabel = new Label(propertiesComposite, SWT.NONE);
		passwordLabel.setText(DOORS_PASSWORD_LABEL);

		passwordField = new Text(propertiesComposite, SWT.SINGLE | SWT.BORDER | SWT.PASSWORD);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		data.horizontalAlignment = SWT.END;
		data.widthHint = WITH_TEXT;
		passwordField.setLayoutData(data);
	}

	/**
	 * Create a tree viewer with filters to show only relevant tree elements for
	 * Doors import /export
	 */
	protected void createTreeViewer() {
		TreeViewer treeViewer = createTreeUI();
		VirSatFilteredWrappedTreeContentProvider filteredCp = (VirSatFilteredWrappedTreeContentProvider) treeViewer
				.getContentProvider();
		filteredCp.addClassFilter(CategoryAssignment.class);
		filteredCp.addClassFilter(ArrayInstance.class);
		filteredCp.addClassFilter(ComposedPropertyInstance.class);
		filteredCp.addStructuralElementIdFilter(
				RequirementsConfigurationCollection.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		filteredCp.addCategoryIdFilter(ImportConfiguration.FULL_QUALIFIED_CATEGORY_NAME);
	}

	@Override
	public boolean isSelectionValid() {
		Object selection = getSelection();
		if (selection instanceof StructuralElementInstance) {
			String seiFqn = ((StructuralElementInstance) selection).getType().getFullQualifiedName();
			return seiFqn.equals(RequirementsConfigurationCollection.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME);
		} else if (selection instanceof CategoryAssignment) {
			String categoryFqn = ((CategoryAssignment) selection).getType().getFullQualifiedName();
			return categoryFqn.equals(ImportConfiguration.FULL_QUALIFIED_CATEGORY_NAME);
		}
		return false;
	}

	/**
	 * Check if specified destination to doors server is valid Create a
	 * SynchronizationConfiguration
	 * 
	 * @return
	 */
	public boolean isDoorsConnectionValid() {
		Object currentSelection = getSelection();
		if (currentSelection instanceof StructuralElementInstance) {
			Concept concept = (Concept) ((StructuralElementInstance) currentSelection).getType().eContainer();
			String server = serverUrlField.getText();
			String project = projectNameField.getText();
			String user = userIdField.getText();
			String password = passwordField.getText();
			try {
				OslcClient client = DoorsUtil.getClient(password, user, server);
				ServiceProvider serviceProvider = DoorsSynchroClient.getServiceProvider(client, project);
				String title = serviceProvider.getTitle();
				if (title.equals(project)) {
					addSynchronizationConfiguration(concept, server, project);

					saveUserCredentials(user, password, project);
					ArrayList<RequirementCollection> specList = DoorsSynchroClient
							.queryRequirementsSpecifications(client, project);
					DoorsSynchroClient.queryRequirements(client, project);

					Repository repository = VirSatResourceSet.getVirSatResourceSet((EObject) currentSelection)
							.getRepository();
					mappingPage.setInput(specList, repository);
					return true;
				}

			} catch (KeyManagementException e) {
				e.printStackTrace();
			} catch (UnrecoverableKeyException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (KeyStoreException e) {
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
		}
		return false;

	}

	/**
	 * 
	 * @param concept
	 * @param serverUrl
	 * @param projectName
	 */
	private void addSynchronizationConfiguration(Concept concept, String serverUrl, String projectName) {
		SynchronizationConfiguration synchroConfig = new SynchronizationConfiguration(concept);
		synchroConfig.setServerUrl(serverUrl);
		synchroConfig.setProjectName(projectName);

		EObject reqConfiguration = (EObject) getSelection();
		RequirementsConfigurationCollection collection = new RequirementsConfigurationCollection(
				(StructuralElementInstance) reqConfiguration);
		Repository repository = VirSatResourceSet.getVirSatResourceSet((EObject) reqConfiguration).getRepository();
		VirSatTransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(repository);
		CompoundCommand cc = new CompoundCommand();

		cc.append(collection.add(editingDomain, synchroConfig));
		editingDomain.getCommandStack().execute(cc);

	}

	/**
	 * 
	 * @param user
	 * @param password
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
			return categoryFqn.equals(ImportConfiguration.FULL_QUALIFIED_CATEGORY_NAME) && isDoorsConnectionValid();
		}
		return false;
	}

	@Override
	public boolean canFlipToNextPage() {
		return super.canFlipToNextPage() && !canFinish();
	}

	@Override
	public boolean isComplete() {
		return isCurrentPage() && isSelectionValid() && isDoorsConnectionValid();
	}

	@Override
	public Object getSelection() {
		Object selected = super.getSelection();
		if (selected instanceof ComposedPropertyInstance) {
			selected = ((ComposedPropertyInstance) selected).getTypeInstance();
		}
		return selected;
	}

	@Override
	public DoorsImportWizard getWizard() {
		return (DoorsImportWizard) super.getWizard();
	}
}
