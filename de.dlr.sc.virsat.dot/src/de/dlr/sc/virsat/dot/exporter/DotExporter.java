/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.dot.exporter;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.excel.exporter.IExport;

/**
 * Class for exporting smv
 */
public class DotExporter {
	private static final String IEXPORT_ID = "de.dlr.sc.virsat.dot.export";
	private IExtensionRegistry registry;

	/**
	 * Default constructor
	 */
	public DotExporter() {
		registry = Platform.getExtensionRegistry();
	}

	/**
	 * Constructor for injecting an extension registry. Needed for testing.
	 * @param registry the registry
	 */
	public DotExporter(IExtensionRegistry registry) {
		this.registry = registry;
	}



	public boolean canExport(Object selection) {
		
		return true;
	}

	/**
	 * Exports model into Dot compatible format
	 * @param eObject
	 * @param path
	 * @param useDefaultTemplate
	 * @param templatePath
	 * @throws CoreException
	 */
	public void export(EObject eObject, String path, boolean useDefaultTemplate, String templatePath) throws CoreException {
		IConfigurationElement[] config = registry.getConfigurationElementsFor(IEXPORT_ID);
		for (IConfigurationElement iConfElement : config) {
			Object object;
			try {
				object = iConfElement.createExecutableExtension("class");
				if (object instanceof IExport) {
					IExport exporter = (IExport) object;
					if (exporter.canExport(eObject)) {
						exporter.export(eObject, path, useDefaultTemplate, templatePath);
					}
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Export method calls extension points to do the job
	 * @param eObject object to export
	 * @param path where to export
	 * @param useDefaultTemplate using the default template if true
	 * @param templatePath path of the user given template
	 * @throws CoreException
	 */

}
