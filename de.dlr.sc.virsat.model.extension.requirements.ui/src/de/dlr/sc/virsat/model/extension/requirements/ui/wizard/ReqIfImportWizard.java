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

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.statushandlers.StatusManager;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementGroup;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementObject;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;
import de.dlr.sc.virsat.model.extension.requirements.ui.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * A wizard for the import of requirements from a CSV file
 *
 */
public class ReqIfImportWizard extends Wizard implements IWorkbenchWizard {

	public static final String ID = "de.dlr.sc.virsat.model.extension.requirements.ui.wizard.csvImport";
	private static final int NUMBER_PROGRESS_TICKS = 3;

	private ReqIfFileReqTypeSelectionPage importPage;
	private ReqIfTypeReviewPage reviewTypePage;
	private ReqIfImportTargetSelectionPage targetSelectionPage;
	private IContainer model;


	/**
	 * Default constructor
	 */
	public ReqIfImportWizard() {
		super();

		// Setup persistency
		IDialogSettings pluginSettings = Activator.getDefault().getDialogSettings();
		IDialogSettings wizardSettings = pluginSettings.getSection(ID);
		if (wizardSettings == null) {
			wizardSettings = new DialogSettings(ID);
			pluginSettings.addSection(wizardSettings);
		}
		setDialogSettings(wizardSettings);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.model = ResourcesPlugin.getWorkspace().getRoot();
		setNeedsProgressMonitor(true);
	}

	@Override
	public boolean performFinish() {

		// Get target list of requirements
		final IBeanList<RequirementObject> targetedReqList;
		final CategoryAssignment selectedSpec = (CategoryAssignment) targetSelectionPage.getSelection();
		if (selectedSpec.getType().getFullQualifiedName()
				.equals(RequirementsSpecification.FULL_QUALIFIED_CATEGORY_NAME)) {
			targetedReqList = new RequirementsSpecification(selectedSpec).getRequirements();
		} else if (selectedSpec.getType().getFullQualifiedName()
				.equals(RequirementGroup.FULL_QUALIFIED_CATEGORY_NAME)) {
			targetedReqList = new RequirementGroup(selectedSpec).getChildren();
		} else {
			targetedReqList = null;
		}
		final CategoryAssignment reqConfiguration = (CategoryAssignment) importPage.getSelection();
		final String selectedFilePath = importPage.getDestination();
		
		Map<Integer, RequirementAttribute> attributeMapping = reviewTypePage.getAttributeMapping();

		// Do the import
		Job importJob = new Job("Performing Requirements CSV Import") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				SubMonitor importSubMonitor = SubMonitor.convert(monitor, NUMBER_PROGRESS_TICKS);
				
				// Prepare the data
//				try {
//					// Do the ReqIF parsing here;
//					importSubMonitor.worked(1);
//				} catch (IOException e) {
//					Status status = new Status(Status.ERROR, Activator.getPluginId(),
//							"ReqIfImportWizard: Failed to refresh the workspace!", e);
//					StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
//					return Status.CANCEL_STATUS;
//				}
				importSubMonitor.worked(1);

				// Import them
				Command importCommand;
				VirSatTransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE
						.getEd(selectedSpec);
				// Do the import here
				//editingDomain.getVirSatCommandStack().execute(importCommand);
				importSubMonitor.worked(1);
				
				// Update workspace
				try {
					ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
				} catch (CoreException e) {
					Status status = new Status(Status.ERROR, Activator.getPluginId(),
							"CSVImportWizard: Failed to refresh the workspace!", e);
					StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
					return Status.CANCEL_STATUS;
				}
				importSubMonitor.worked(1);
				return Status.OK_STATUS;
			}
		};
		importJob.schedule();

		return true;
	}

	@Override
	public void addPages() {
		reviewTypePage = new ReqIfTypeReviewPage();
		importPage = new ReqIfFileReqTypeSelectionPage(model, reviewTypePage);
		addPage(importPage);
		addPage(reviewTypePage);
		targetSelectionPage = new ReqIfImportTargetSelectionPage(model);
		addPage(targetSelectionPage);
	}

}
