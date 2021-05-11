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
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import de.dlr.sc.virsat.model.extension.requirements.ui.Activator;

/**
 * A wizard for the import of requirements from a CSV file
 *
 */
public class DoorsImportWizard extends Wizard implements IWorkbenchWizard {

	public static final String ID = "de.dlr.sc.virsat.model.extension.requirements.ui.wizard.doorsImport";

	private DoorsConfigurationSelectionPage importPage;
	private DoorsMappingPage mappingPage;

	private IContainer model;

	/**
	 * Default constructor
	 */
	public DoorsImportWizard() {
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
		return super.canFinish() || importPage.canFinish();
	}
	
	@Override
	public boolean performFinish() {
		return false;
	}
	@Override
	public void addPages() {
		mappingPage = new DoorsMappingPage();
		importPage = new DoorsConfigurationSelectionPage(model, mappingPage);
		addPage(importPage);
		addPage(mappingPage);
	}

}
