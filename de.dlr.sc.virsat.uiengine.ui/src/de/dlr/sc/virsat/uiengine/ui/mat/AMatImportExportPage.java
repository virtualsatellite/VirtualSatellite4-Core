/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.mat;

import org.eclipse.swt.widgets.Composite;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.uiengine.ui.wizard.AImportExportPage;

/**
 * A basic import / export page to select a sei and a file destination for an import / export of a .mat file
 *
 */
public abstract class AMatImportExportPage extends AImportExportPage {

	
	private static final String[] DIALOG_EXTENSIONS = { "*.mat" };
	
	/**
	 * Default constructor override
	 * @param pageName the page name
	 * @param style the SWT style of the page 
	 */
	protected AMatImportExportPage(String pageName, int style) {
		super(pageName, style);
	}
	
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		createTreeUI();
		createFileDestinationUI();
	}
	
	@Override
	public boolean isSelectionValid() {
		Object selection = getSelection();
		return selection instanceof StructuralElementInstance;
	}
	
	@Override
	protected String[] getSupportedFileEndings() {
		return DIALOG_EXTENSIONS;
	}
}
