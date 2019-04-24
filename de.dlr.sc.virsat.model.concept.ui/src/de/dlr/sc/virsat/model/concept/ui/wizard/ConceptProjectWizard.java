/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.ui.wizard;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import de.dlr.sc.virsat.model.concept.Activator;
import de.dlr.sc.virsat.model.concept.project.ConceptProjectGenerationRunnable;


/**
 * Eclipse Standard Project Wizard that will create all necessary Plugins for
 * extending our data model with a new concept.
 * @author fisc_ph
 *
 */
@SuppressWarnings("restriction")
public class ConceptProjectWizard extends BasicNewProjectResourceWizard implements INewWizard {

	public static final String NEW_CONCEPT_NAME_PATTERN = "de.dlr.sc.virsat.model.extension.";
	
	public static final String NEW_CONCEPT_TITLE = "New Concept";
	public static final String NEW_CONCEPT_DESCRIPTION = "Set the name and location for the new concept";
	
	public static final String CONECPT_LOCATION_EXISTS = "Concept already exists. Will generate non existing parts only!";
	public static final String CONCEPT_LOCATION_PATTERN_WARN = "Location should follow pattern: " + NEW_CONCEPT_NAME_PATTERN; 
	
	/**
	 * Override the New project Main Page, so we can override the validation method
	 * This page should allow to create project in a location where one already exists
	 * @author fisc_ph
	 *
	 */
	class WizardNewConceptCreationPage extends WizardNewProjectCreationPage {

		/**
		 * Constructor for the New Concept Wizzard Page
		 * @param pageName the name / ID of the page
		 */
		WizardNewConceptCreationPage(String pageName) {
			super(pageName);
		}
		
		@Override
		protected boolean validatePage() {
			boolean validatePage = super.validatePage();
			
			// The original dialog does not allow to create a project where one has already been created
			// The case is different for this Concept Generator. Since it creates a set of features, plugins and fragments, the one
			// that is missing should be regenerated. The runnable creating them takes care of it. Accordingly we will
			// check for the current error in the dialog. if it sais, that the project already exists, than we will change the error 
			// o a warning, and let the user continue.
			
			String currentErrorMessage = getErrorMessage();
			
			if (validatePage && !getProjectHandle().getName().startsWith(NEW_CONCEPT_NAME_PATTERN)) {
				setMessage(CONCEPT_LOCATION_PATTERN_WARN, WARNING);
			}
			
			if (IDEWorkbenchMessages.WizardNewProjectCreationPage_projectExistsMessage.equals(currentErrorMessage)) {
				setErrorMessage(null);
				setMessage(CONECPT_LOCATION_EXISTS, INFORMATION);
				return true;
			}
			
			return validatePage;
		}
	}

	private WizardNewConceptCreationPage mainPage;
	
	@Override
	public void addPages() {
		mainPage = new WizardNewConceptCreationPage("basicNewProjectPage") { //$NON-NLS-1$
			@Override
			public void createControl(Composite parent) {
				super.createControl(parent);
				createWorkingSetGroup(
						(Composite) getControl(),
						getSelection(),
						new String[] { "org.eclipse.ui.resourceWorkingSetPage" }); //$NON-NLS-1$
				Dialog.applyDialogFont(getControl());
			}
		};
		mainPage.setTitle(NEW_CONCEPT_TITLE);
		mainPage.setDescription(NEW_CONCEPT_DESCRIPTION);
		
		this.addPage(mainPage);
	}
	
	@Override
	public boolean performFinish() {
	
		// get a project descriptor from the main page
		URI location = null;
		if (!mainPage.useDefaults()) {
			location = mainPage.getLocationURI();
		}

		// create the new project operation that creates all the plugins fragments etc
		IRunnableWithProgress op = new ConceptProjectGenerationRunnable(location, mainPage.getProjectName(), IResource.KEEP_HISTORY);

		// run the new project creation operation
		try {
			getContainer().run(true, true, op);
		} catch (InvocationTargetException | InterruptedException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Failed to create Concept Project", e));
			return false;
		}
		
		return true;
	}
}
