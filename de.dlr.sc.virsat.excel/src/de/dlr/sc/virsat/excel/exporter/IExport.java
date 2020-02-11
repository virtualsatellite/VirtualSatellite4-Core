/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.excel.exporter;

import org.eclipse.emf.ecore.EObject;

/**
 * General Interface To export data on Excel
 */
public interface IExport {

	/**
	 * Export functionality
	 * @param eObject the object to be exported
	 * @param path the export path
	 * @param useDefaultTemplate true if using the default template false otherwise
	 * @param templatePath the path of the user selected template
	 */
	void export(EObject eObject, String path, boolean useDefaultTemplate, String templatePath);

	/**
	 * Is it possible to export the object
	 * @param selection the object to be exported
	 * @return true when can export false otherwise
	 */
	boolean canExport(Object selection);

}
