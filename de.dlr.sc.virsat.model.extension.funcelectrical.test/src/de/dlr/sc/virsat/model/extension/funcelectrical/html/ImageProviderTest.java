/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.html;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;

/**
 * This class tests the ImageProvider class.
 * @author muel_s8
 *
 */

public class ImageProviderTest {

	@Test
	public void testIsExportable() {
		ImageProvider imageProvider = new ImageProvider();
		
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		
		se.setName(ConfigurationTree.class.getSimpleName());
		assertTrue("Structural Element Instance with with Structural name ConfigurationTree is exportable", imageProvider.isExportable(sei));
		
		se.setName(InterfaceEnd.class.getSimpleName());
		assertFalse("Structural Element Instance with with Structural name InterfaceEnd is not exportable", imageProvider.isExportable(sei));
	}

}
