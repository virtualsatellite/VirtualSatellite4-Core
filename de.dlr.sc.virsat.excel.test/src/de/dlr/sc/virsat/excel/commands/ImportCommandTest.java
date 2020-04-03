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

import static org.junit.Assert.assertEquals;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.excel.importer.ExcelImporter;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Test class for the import command
 */
public class ImportCommandTest extends AConceptProjectTestCase {

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
	}

	/**
	 * Mock ExcelImporter
	 */
	public static class MockExcelImporter extends ExcelImporter {
		EObject object;
		Repository repository;
		XSSFWorkbook wb;

		@Override
		public void importExcel(EObject object, Repository repository, XSSFWorkbook wb) {
			this.object = object;
			this.repository = repository;
			this.wb = wb;
		}
	}

	@Test
	public void testImportCommand() {
		MockExcelImporter mockImporter = new MockExcelImporter();
		StructuralElement object = StructuralFactory.eINSTANCE.createStructuralElement();
		XSSFWorkbook wb = new XSSFWorkbook();
		ImportCommand command = new ImportCommand(object, wb, editingDomain, mockImporter);
		command.execute();

		assertEquals(object, mockImporter.object);
		assertEquals(wb, mockImporter.wb);
		assertEquals(editingDomain.getResourceSet().getRepository(), mockImporter.repository);
	}
}