/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.model.extension.funcelectrical.excel.exporter.FuncElecExporterTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.excel.importer.FuncElecImporterTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.excel.importer.ImportValidatorTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.html.HTMLExporterTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.html.ImageProviderTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.marker.VirSatFuncelectricalMarkerHelperTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.util.FuncElectricalArchitectureHelperTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.validator.ValidatorTest;
import junit.framework.JUnit4TestAdapter;

/**
 * 
 * @author lobe_el
 *
 */
@RunWith(Suite.class)

@SuiteClasses({ ValidatorTest.class,
				HTMLExporterTest.class,
				FuncElecExporterTest.class,
				ImportValidatorTest.class,
				FuncElecImporterTest.class,
				ImageProviderTest.class,
				FuncElectricalArchitectureHelperTest.class, 
				VirSatFuncelectricalMarkerHelperTest.class})

public class AllTests {
	
	/**
	 * Constructor for Test Suite
	 */
	private AllTests() {
	}

	/**
	 * entry point for test suite
	 * @return the test suite
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}	
}