/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * A page to configure the import from CAD by specifying a file to import from
 * and a tree on which the import should be applied to
 *
 */
public class CadImportPage extends ACadImportExportPage {

	/**
	 * Standard constructor
	 * @param model the root model
	 */
	protected CadImportPage(IContainer model) {
		super("Cad JSON Import", SWT.OPEN);
		setTitle("Cad JSON Import (Beta)");
		setModel(model);
		setDescription("Please select a tree to import to and a file to import.");
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		createTreeViewer();
		
		createFileDestinationUI();
		
		Label label = new Label((Composite) getControl(), SWT.NONE);
		label.setText("All Geometry files will be imported into the documents folder of the containing model element.");
	}
	
	
}
