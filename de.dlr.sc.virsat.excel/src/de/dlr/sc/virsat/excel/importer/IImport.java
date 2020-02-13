/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.excel.importer;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.excel.fault.Fault;
import de.dlr.sc.virsat.model.dvlm.Repository;

/**
 * General Interface To import data from Excel
 */
public interface IImport {

	/**
	 * Import functionality
	 * @param object object
	 * @param repository the StructuralElementInstance
	 * @param wb XSSFWorkbook
	 */
	void importExcel(EObject object, Repository repository, XSSFWorkbook wb);

	/**
	 * If that plug in can do the import depending on the type of the Eobject
	 * @param object object
	 * @return true if can import
	 */
	boolean canImport(EObject object);
	
	/**
	 * Import validator functionality
	 * @param object object
	 * @param wb XSSFWorkbooks
	 * @return possible fault list if cannot be imported
	 */
	List<Fault> validate(EObject object, XSSFWorkbook wb);
}
