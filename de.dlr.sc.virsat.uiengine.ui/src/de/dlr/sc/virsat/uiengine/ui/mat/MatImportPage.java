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

import org.eclipse.core.resources.IContainer;
import org.eclipse.swt.SWT;

/**
 * A page to configure the import from a .mat file by specifying a file to import from
 * and a tree on which the import should be applied to
 */
public class MatImportPage extends AMatImportExportPage {

	/**
	 * Standard constructor
	 * @param model the root model
	 */
	protected MatImportPage(IContainer model) {
		super("Mat Import", SWT.OPEN);
		setTitle("Mat Import");
		setModel(model);
		setDescription("Please select a tree to import to and a .mat file to import.");
	}
}
