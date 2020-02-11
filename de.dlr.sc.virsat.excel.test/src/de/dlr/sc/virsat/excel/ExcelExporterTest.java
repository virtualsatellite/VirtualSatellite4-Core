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
import static org.junit.Assert.assertTrue;

import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.emf.ecore.EObject;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.excel.exporter.ExcelExporter;
import de.dlr.sc.virsat.excel.exporter.IExport;

/**
 * This class test the ExcelExporter class.
 */
@SuppressWarnings("restriction")
public class ExcelExporterTest {

	/**
	 * Mockup exporter accepting Strings
	 */
	public class StringExporter implements IExport {

		@Override
		public void export(EObject eObject, String path, boolean useDefaultTemplate, String templatePath) {
			executed = true;
		}

		@Override
		public boolean canExport(Object selection) {
			return selection instanceof String;
		}
	}

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
							return new StringExporter();
						}
					}
				};
			}
		};
	}

	@Test
	public void testCanExport() throws CoreException {
		ExcelExporter exporter = new ExcelExporter(registry);
		boolean canExportString = exporter.canExport("Test");
		assertTrue("String exporter can export a String", canExportString);
		boolean canExportObject = exporter.canExport(new Object());
		assertFalse("String exporter cannot export an Object", canExportObject);
	}

	@Test
	public void testExport() throws CoreException {
		ExcelExporter exporter = new ExcelExporter(registry);
		assertFalse(executed);
		exporter.export(null, null, false, null);
		assertTrue(executed);
	}
}
