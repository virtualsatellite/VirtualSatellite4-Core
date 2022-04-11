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


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.Before;
import org.junit.Test;
import de.dlr.sc.virsat.excel.exporter.IExport;

@SuppressWarnings("restriction")
public class DotExporterTest {
	
	private ExtensionRegistry registry;
	private boolean executed = false;
	
	public class EObjectExporter implements IExport {

		@Override
		public void export(EObject eObject, String path, boolean useDefaultTemplate, String templatePath) {
			executed = true;
		}

		@Override
		public boolean canExport(Object selection) {
			return selection instanceof EObject;
		}
	}
	
	@Before
	public void setUp() {
		registry = new ExtensionRegistry(null, null, null) {
			
			@Override
			public IConfigurationElement[] getConfigurationElementsFor(String extensionPointId) {
				return new IConfigurationElement[] {
					new ConfigurationElementHandle(null, 0) {
						@Override
						public Object createExecutableExtension(String propertyName) throws CoreException {
							return new EObjectExporter();
						}
					}
				};
			}
		};
	}
	@Test
	public void testNusmvExporter() {
		DotExporter nusmv = new DotExporter(registry);
		assertFalse(executed);
		try {
			nusmv.export(EcoreFactory.eINSTANCE.createEObject(), null, false, null);
		
			assertTrue(executed);
		
			executed = false;
			nusmv.export(null, null, false, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		assertFalse(executed);
	}

}
