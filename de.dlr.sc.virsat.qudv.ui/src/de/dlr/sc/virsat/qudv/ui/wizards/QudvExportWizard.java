/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.qudv.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import de.dlr.sc.virsat.qudv.ui.Activator;
import de.dlr.sc.virsat.qudv.ui.wizards.pages.QudvExportPage;

/**
 * an export wizard which exports the complete QUDV library to a file
 * @author scha_vo
 *
 */
public class QudvExportWizard extends Wizard implements IExportWizard {

	private QudvExportPage exportPage;
	
	/**
	 * public constructor
	 */
	public QudvExportWizard() {
		super();
		setWindowTitle("QUDV Export");
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png"));
	}
	

	@Override
	public void addPages() {
		super.addPages();
		exportPage = new QudvExportPage("QUDV Export Page");
		addPage(exportPage);
	}
	
	@Override
	public boolean performFinish() {  
		return exportPage.finish(); 
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		
	}
}
