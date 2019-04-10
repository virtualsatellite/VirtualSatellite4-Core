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
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

import de.dlr.sc.virsat.qudv.ui.Activator;
import de.dlr.sc.virsat.qudv.ui.wizards.pages.QudvImportMapToProxyPage;
import de.dlr.sc.virsat.qudv.ui.wizards.pages.QudvImportPage;

/**
 * import wizard to 
 * @author scha_vo
 *
 */
public class QudvImportWizard extends Wizard implements IImportWizard {

	private QudvImportPage importPage;
	private QudvImportMapToProxyPage mapToProxyPage;
	
	/**
	 * public constructor
	 */
	public QudvImportWizard() {
		super();
		setWindowTitle("QUDV Import");
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/facets-page-wizban.png"));
	}
	
	@Override
	public void addPages() {
		super.addPages();
		importPage = new QudvImportPage("QUDV Import Page");
		mapToProxyPage = new QudvImportMapToProxyPage("QUDV Mapping to Proxy Page");
		addPage(importPage);
		addPage(mapToProxyPage);
	}
	
	@Override
	public boolean canFinish() {
		return (importPage.canFlipToNextPage() && mapToProxyPage.isPageComplete());
	}

	@Override
	public boolean performFinish() {
		importPage.finish();
		return mapToProxyPage.finish(importPage.getSystemOfUnits()); 
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) { 	
	}
}
