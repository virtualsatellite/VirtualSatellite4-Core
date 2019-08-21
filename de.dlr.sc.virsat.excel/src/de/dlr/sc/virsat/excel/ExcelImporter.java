/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.excel;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.model.extension.ps.Activator;


/**
 * Class for Importing Excel files.
 * 
 * @author bell_er
 *
 */
public class ExcelImporter {
	private static final String IIMPORT_ID = "de.dlr.sc.virsat.excel.import";
	private IExtensionRegistry registry;
	
	/**
	* Simple constructor
	* 
	* @author  Bell_er
	*/
	public ExcelImporter() {
		registry = Platform.getExtensionRegistry();
	}
	
	
	/**
	* Constructor injecting an extension registry. Needed for testing.
	* 
	* @param registry the registry
	* @author  muel_s8
	*/
	public ExcelImporter(IExtensionRegistry registry) {
		this.registry = registry;
	}
	
	/**
	* Imports depending on the type of the Structural element
	* @param object element to be imported
	* @param repository repository of the element
	* @param wb the workbook
	* 
	* 
	* @author  Bell_er
	* 
	*/
	public void importExcel(EObject object, Repository repository, XSSFWorkbook wb) {
		IImport importer = getImporter(object);
		if (importer != null) {
			importer.importExcel(object, repository, wb);
		}
	}
	/**
	* Validates the input excel file 
	* @param object element to be imported
	* @param wb the workbook
	* 
	* @return the fault List
	* @author  Bell_er
	* 
	*/
	public List<Fault> validate(EObject object, XSSFWorkbook wb) {
		IImport importer = getImporter(object);
		if (importer != null) {
			return importer.validate(object, wb);
		}
		return null;
	}
	
	/**
	 * Gets an applicable importer for the given EObject if there is any.
	 * If there is no registered importer for the EObject, null will be returned.
	 * This will take the firstly found importer.
	 * @param object the eObject
	 * @return the importer
	 */
	private IImport getImporter(EObject object) {
		IConfigurationElement[] config = registry.getConfigurationElementsFor(IIMPORT_ID);
		for (IConfigurationElement e : config) {
			Object o = null;
			try {
				o = e.createExecutableExtension("class");
			} catch (CoreException e1) {
				Status status = new Status(Status.ERROR, Activator.getPluginId(), "Failed to perform an excel import operation! ", e1);
				DVLMEditPlugin.getPlugin().getLog().log(status);	
			}
			if (o instanceof IImport) {
				IImport importer = (IImport) o;
				if (importer.canImport(object)) {
					return importer;
				}
			}
		}
		return null;
	}
}
