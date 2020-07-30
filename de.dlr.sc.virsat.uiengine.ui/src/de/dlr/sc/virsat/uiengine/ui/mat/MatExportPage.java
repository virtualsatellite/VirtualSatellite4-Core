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
 * This class configures the export page by providing the selection
 * mechanisms for selecting what tree should be objected and to
 * where it should be exported
 */

public class MatExportPage extends AMatImportExportPage {

	/**
	 * Standard constructor
	 * @param model the root model
	 */
	protected MatExportPage(IContainer model) {
		super("Mat Export", SWT.SAVE);
		setTitle("Mat Export");
		setModel(model);
		setDescription("Please select a tree to export and an export destination.");
	}
}
