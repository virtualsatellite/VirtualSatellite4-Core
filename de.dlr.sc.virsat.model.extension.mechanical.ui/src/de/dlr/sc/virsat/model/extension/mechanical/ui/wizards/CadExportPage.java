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
 * This class configures the export page by providing the selection
 * mechanisms for selecting what object should be objected and to
 * where it should be exported
 *
 */

public class CadExportPage extends ACadImportExportPage {

	/**
	 * Standard constructor
	 * @param model the root model
	 */
	protected CadExportPage(IContainer model) {
		super("Cad JSON Export");
		setTitle("Cad JSON Export (Beta)");
		setModel(model);
		setDescription("Please select a tree to export and an export destination.");
	}


	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		
		createTreeViewer();
		createFileDestinationUI();
		
		Label label = new Label((Composite) getControl(), SWT.NONE);
		label.setText("All attached Geometry files will be copied into the same directory as the JSON file.");
	}
	

}
