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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.requirements.doors.client.DoorsSynchronizer;
import de.dlr.sc.virsat.model.extension.requirements.model.ImportConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.SpecificationMapping;
import de.dlr.sc.virsat.model.extension.requirements.ui.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * A wizard for the synchronization with Doors requirements by activating an existing synchronization configuration
 *
 */
public class DoorsActivateSynchronizationWizard extends Wizard implements IWorkbenchWizard {

	public static final String ID = "de.dlr.sc.virsat.model.extension.requirements.ui.wizard.doorsActivateSynchronizationWizard";
	private static final int NUMBER_PROGRESS_TICKS_IMPORT = 5;

	private DoorsChooseImportConfigurationPage importPage;
	private DoorsActivateSynchroConfigurationPage activateSynchroPage;
	private DoorsSynchronizer synchronizer = new DoorsSynchronizer();

	private IContainer model;

	/**
	 * Default constructor
	 */
	public DoorsActivateSynchronizationWizard() {
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
	public boolean canFinish() {
		return super.canFinish() || activateSynchroPage.canFinish();
	}

	@Override
	public boolean performFinish() {
		final EObject reqConfiguration = (EObject) importPage.getSelection();

		// Do the import
		Job importJob = new Job("Performing Requirements Synchronization") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				VirSatTransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE
						.getEd(reqConfiguration);
				ImportConfiguration importConfiguration = new ImportConfiguration(
						(CategoryAssignment) reqConfiguration);
				IBeanList<SpecificationMapping> specs = importConfiguration.getMappedSpecifications();
				synchronize(editingDomain, specs, monitor);
				return Status.OK_STATUS;
			}

			private void synchronize(EditingDomain editingDomain, IBeanList<SpecificationMapping> specs,
					IProgressMonitor monitor) {
				SubMonitor importSubMonitor = SubMonitor.convert(monitor, NUMBER_PROGRESS_TICKS_IMPORT);

				// Do the actual imports
				editingDomain.getCommandStack().execute(synchronizer.updateRequirements(editingDomain, specs));
				importSubMonitor.worked(1);

//				//Import the requirement links
//				editingDomain.getCommandStack().execute(synchronizer.updateRequirementLinks(editingDomain, specs));
//				importSubMonitor.worked(1);
			}
		};
		importJob.schedule();

		return true;
	}

	@Override
	public void addPages() {
		importPage = new DoorsChooseImportConfigurationPage(model);
		activateSynchroPage = new DoorsActivateSynchroConfigurationPage(model);
		addPage(importPage);
		addPage(activateSynchroPage);
	}
}