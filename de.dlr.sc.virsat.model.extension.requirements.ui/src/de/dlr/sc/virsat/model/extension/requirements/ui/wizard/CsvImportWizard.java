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
import de.dlr.sc.virsat.model.extension.requirements.csv.CsvFileReader;
import de.dlr.sc.virsat.model.extension.requirements.csv.RequirementsImporter;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementGroup;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementObject;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;
import de.dlr.sc.virsat.model.extension.requirements.ui.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * A wizard for the import of requirements from a CSV file
 *
 */
public class CsvImportWizard extends Wizard implements IWorkbenchWizard {

	public static final String ID = "de.dlr.sc.virsat.model.extension.requirements.ui.wizard.csvImport";
	private static final int NUMBER_PROGRESS_TICKS = 3;

	private CsvFileReqTypeSelectionPage importPage;
	private CsvTypeReviewPage reviewTypePage;
	private CsvImportTargetSelectionPage targetSelectionPage;
	private IContainer model;

	private CsvFileReader reader;
	private RequirementsImporter importer;

	/**
	 * Default constructor
	 */
	public CsvImportWizard() {
		super();

		// Setup persistency
		IDialogSettings pluginSettings = Activator.getDefault().getDialogSettings();
		IDialogSettings wizardSettings = pluginSettings.getSection(ID);
		if (wizardSettings == null) {
			wizardSettings = new DialogSettings(ID);
			pluginSettings.addSection(wizardSettings);
		}
		setDialogSettings(wizardSettings);
		reader = new CsvFileReader();
		importer = new RequirementsImporter();
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
		reader.setDataStartLine(importPage.getFristDataLineNumber());
		reader.setDataEndLine(importPage.getLastDataLineNumber());
		Map<Integer, RequirementAttribute> attributeMapping = reviewTypePage.getAttributeMapping();

		// Do the import
		Job importJob = new Job("Performing Requirements CSV Import") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				SubMonitor importSubMonitor = SubMonitor.convert(monitor, NUMBER_PROGRESS_TICKS);
				
				// Prepare the data
				List<List<String>> csvContentMatrix;
				try {
					csvContentMatrix = reader.readCsvData(selectedFilePath);
					importSubMonitor.worked(1);
				} catch (IOException e) {
					Status status = new Status(Status.ERROR, Activator.getPluginId(),
							"CSVImportWizard: Failed to refresh the workspace!", e);
					StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
					return Status.CANCEL_STATUS;
				}
				importSubMonitor.worked(1);

				// Import them
				Command importCommand;
				VirSatTransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE
						.getEd(selectedSpec);
				if (reqConfiguration.getType().getFullQualifiedName()
						.equals(RequirementType.FULL_QUALIFIED_CATEGORY_NAME)) {
					importCommand = importer.loadRequirements(editingDomain, csvContentMatrix, targetedReqList,
							attributeMapping, new RequirementType(reqConfiguration));
				} else {
					importCommand = importer.loadRequirements(editingDomain, csvContentMatrix, targetedReqList,
							attributeMapping, new RequirementsConfiguration(reqConfiguration),
							reviewTypePage.getRequirementType());
				}
				editingDomain.getVirSatCommandStack().execute(importCommand);
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
		reviewTypePage = new CsvTypeReviewPage();
		importPage = new CsvFileReqTypeSelectionPage(model, reviewTypePage);
		addPage(importPage);
		addPage(reviewTypePage);
		targetSelectionPage = new CsvImportTargetSelectionPage(model);
		addPage(targetSelectionPage);
	}

	public CsvFileReader getReader() {
		return reader;
	}

	public void setReader(CsvFileReader reader) {
		this.reader = reader;
	}

	public RequirementsImporter getImporter() {
		return importer;
	}

	public void setImporter(RequirementsImporter importer) {
		this.importer = importer;
	}

}
