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

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

public class DotExportHelperTest {

	@Test
	public void getFile() {
		DotExportHelper nusmv = new DotExportHelper();
		File file = new File("test");
		nusmv.setFile(file);
		assertEquals("NuSMV exporter file management is working", nusmv.getFile().getAbsoluteFile(), file.getAbsoluteFile());
	}

}
