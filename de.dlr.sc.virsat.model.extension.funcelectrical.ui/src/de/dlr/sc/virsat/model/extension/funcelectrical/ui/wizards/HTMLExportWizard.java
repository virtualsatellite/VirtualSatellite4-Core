/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;



import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.html.ExportHelper;
import de.dlr.sc.virsat.project.ui.Activator;


/**
 * Wizard for exporting to HTML.
 * 
 * @author bell_er
 *
 */

public class HTMLExportWizard extends Wizard implements INewWizard {
	
	public static final String ID = "de.dlr.sc.virsat.model.extension.funcelectrical.ui.export";
	
	private HTMLExportPage page;
	private IContainer model;
	private ISelection selection;
	/**
	 * Create a new export wizard
	 */

	public HTMLExportWizard() {
		super();
		setWindowTitle("Export to HTML");
	
        IDialogSettings pluginSettings = Activator.getDefault().getDialogSettings();
        IDialogSettings wizardSettings = pluginSettings.getSection(ID);
        if (wizardSettings == null) {
            wizardSettings = new DialogSettings(ID);
            pluginSettings.addSection(wizardSettings);
        }
        setDialogSettings(wizardSettings);
	}
	

	/**
	 * Set the root element for the element selection
	 * 
	 * @param iContainer
	 *            root element for the export selection
	 */

	public void setModel(IContainer iContainer) {
		this.model = iContainer;
	}
	/**
	 * Set the selection for the new window
	 * @param selection to be set
	 */
	public void setSelection(ISelection selection) {
		this.selection = selection;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setModel(ResourcesPlugin.getWorkspace().getRoot());

	}
	/**
	 * Perform Finish
	 * @return return true
	 */

	public boolean performFinish() {
		// Grab the necessary context information
		String path = page.getDestination();
		EObject eObject = (EObject) page.getSelection();
		// Do the actual exporting
		if (eObject instanceof StructuralElementInstance) {
			StructuralElementInstance sc = (StructuralElementInstance) eObject;
			ExportHelper exportHelper = new ExportHelper();
			exportHelper.export(sc, path);
		}
		return true;
	}

	@Override
	public void addPages() {
		super.addPages();
		page = new HTMLExportPage(model, selection);
		addPage(page);
	}
	@Override
	public boolean canFinish() {
		return page.isComplete();
	}
	
}
