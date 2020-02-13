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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;

/**
 * Class for exporting excel
 */
public class ExcelExporter {
	private static final String IEXPORT_ID = "de.dlr.sc.virsat.excel.export";
	private IExtensionRegistry registry;

	/**
	 * Default constructor
	 */
	public ExcelExporter() {
		registry = Platform.getExtensionRegistry();
	}

	/**
	 * Constructor for injecting an extension registry. Needed for testing.
	 * @param registry the registry
	 */
	public ExcelExporter(IExtensionRegistry registry) {
		this.registry = registry;
	}

	/**
	 * Export method calls extension points to do the job
	 * @param eObject object to export
	 * @param path where to export
	 * @param useDefaultTemplate using the default template if true
	 * @param templatePath path of the user given template
	 * @throws CoreException
	 */
	public void export(EObject eObject, String path, boolean useDefaultTemplate, String templatePath) throws CoreException {
		IConfigurationElement[] config = registry.getConfigurationElementsFor(IEXPORT_ID);
		for (IConfigurationElement iConfElement : config) {
			Object object = iConfElement.createExecutableExtension("class");
			if (object instanceof IExport) {
				((IExport) object).export(eObject, path, useDefaultTemplate, templatePath);
			}
		}
	}

	/**
	 * Class for exporting excel
	 * @param object object to export
	 * @return true if exportable false otherwise
	 * @throws CoreException
	 */
	public boolean canExport(Object object) throws CoreException {
		boolean canBeExported = false;
		IConfigurationElement[] config = registry.getConfigurationElementsFor(IEXPORT_ID);
		for (IConfigurationElement iConfElement : config) {
			Object obj = iConfElement.createExecutableExtension("class");
			if (obj instanceof IExport) {
				canBeExported = ((IExport) obj).canExport(object);
				if (canBeExported) {
					break;
				}
			}
		}
		return canBeExported;
	}
}
