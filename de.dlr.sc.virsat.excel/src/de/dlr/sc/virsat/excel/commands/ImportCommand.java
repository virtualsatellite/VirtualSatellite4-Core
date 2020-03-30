/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.excel.commands;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import de.dlr.sc.virsat.excel.importer.ExcelImporter;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * Executable command that performs the actual import operation.
 */
public class ImportCommand extends RecordingCommand {

	private EObject eObject;
	private XSSFWorkbook wb;
	private VirSatTransactionalEditingDomain domain;
	private ExcelImporter excelImporter;

	/**
	 * Create a new import command
	 * @param eObject will be used as the root object for integrating the data
	 * @param wb the excel file
	 * @param domain transaction domain
	 */
	public ImportCommand(EObject eObject, XSSFWorkbook wb, TransactionalEditingDomain domain) {
		this(eObject, wb, domain, new ExcelImporter());
	}

	/**
	 * Create a new import command
	 * @param eObject will be used as the root object for integrating the data
	 * @param wb the excel file
	 * @param domain transaction domain
	 * @param excelImporter excel importer to use
	 */
	public ImportCommand(EObject eObject, XSSFWorkbook wb, TransactionalEditingDomain domain, ExcelImporter excelImporter) {
		super(domain);

		this.excelImporter = excelImporter;
		this.domain = (VirSatTransactionalEditingDomain) domain;
		this.eObject = eObject;
		this.wb = wb;
	}

	@Override
	protected void doExecute() {
		Repository repository = domain.getResourceSet().getRepository();
		excelImporter.importExcel(eObject, repository, wb);
	}
}
