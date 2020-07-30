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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.emf.ecore.EObject;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.excel.fault.Fault;
import de.dlr.sc.virsat.excel.importer.ExcelImporter;
import de.dlr.sc.virsat.excel.importer.IImport;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * This class test the ExcelExporter class.
 */
@SuppressWarnings("restriction")
public class ExcelImporterTest {

	/**
	 * Mockup exporter accepting repositories
	 */
	public class RepositoryImporter implements IImport {

		@Override
		public void importExcel(EObject object, Repository repository, XSSFWorkbook wb) {
			executed = true;
		}

		@Override
		public boolean canImport(EObject object) {
			return object instanceof Repository;
		}

		@Override
		public List<Fault> validate(EObject object, XSSFWorkbook wb) {
			return FAULT_LIST;
		}
	}

	private static final List<Fault>FAULT_LIST = new ArrayList<>();
	private IExtensionRegistry registry;
	private boolean executed = false;

	@Before
	public void setUp() {
		registry = new ExtensionRegistry(null, null, null) {
			@Override
			public IConfigurationElement[] getConfigurationElementsFor(String extensionPointId) {
				return new IConfigurationElement[] {
					new ConfigurationElementHandle(null, 0) {
						@Override
						public Object createExecutableExtension(String propertyName) throws CoreException {
							return new RepositoryImporter();
						}
					}
				};
			}
		};
	}

	@Test
	public void testImport() throws CoreException {
		ExcelImporter importer = new ExcelImporter(registry);
		assertFalse("Importer has not been executed yet", executed);
		importer.importExcel(StructuralFactory.eINSTANCE.createStructuralElement(), null, null);
		assertFalse("Importer has still not been executed", executed);
		importer.importExcel(DVLMFactory.eINSTANCE.createRepository(), null, null);
		assertTrue("Importer has been executed", executed);
	}

	@Test
	public void testValidate() throws CoreException {
		ExcelImporter importer = new ExcelImporter(registry);
		List<Fault> invalidImportFaults = importer.validate(StructuralFactory.eINSTANCE.createStructuralElement(), null);
		assertNull("Not a valid input, so return is null", invalidImportFaults);
		List<Fault> validImportFaults = importer.validate(DVLMFactory.eINSTANCE.createRepository(), null);
		assertSame("A valid input, so the fault list of the importer is returned", FAULT_LIST, validImportFaults);
	}
}
