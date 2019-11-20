/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.resources.dmf;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.resources.command.CreateSeiResourceAndFileCommand;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * Test the command for saving DMF resources. 
 * 
 * Additional tests that require a concept to be loaded can be found in the 
 * DmfResourceTest (in the de.dlr.sc.virsat.model.extension.tests.edit.test project). 
 *
 */

public class DmfResourceSaveCommandTest extends AProjectTestCase {

	private StructuralElementInstance sei;

	@Override
	public void setUp() throws CoreException {
		super.setUp();

		addEditingDomainAndRepository();
		sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		Command cmd = new CreateSeiResourceAndFileCommand(editingDomain.getResourceSet(), sei);
		editingDomain.getCommandStack().execute(cmd);
		editingDomain.saveAll();
	}


	@Test
	public void testSafeGetSei() {

		sei.eResource().unload();
		assertTrue("After unloading its resource the SEI should be a proxy",
				sei.eIsProxy());
		
		DmfResourceSaveCommand saveCommand = new DmfResourceSaveCommand(editingDomain, sei, null);

		assertFalse("safe access methos should ensure that undelying SEI is no proxy",
				saveCommand.safeGetSei().eIsProxy());

	}

}
