/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain.commands;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;


import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatEditingDomainClipBoard.ClipboardState;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * Tests for the Copy Clipboard Command
 * @author fisc_ph
 *
 */
public class VirSatCopyToClipboardCommandTest extends AProjectTestCase {

	private VirSatProjectCommons projectCommons;
	private VirSatTransactionalEditingDomain rsEd;
	
	@Override
	public void setUp() throws CoreException {
		super.setUp();
		
		projectCommons = new VirSatProjectCommons(testProject);
		projectCommons.createProjectStructure(null);
		
		VirSatResourceSet.getResourceSet(testProject, false);
		rsEd = VirSatEditingDomainRegistry.INSTANCE.getEd(testProject);
	}

	@Override
	public void tearDown() throws CoreException {
		super.tearDown();
		
		VirSatResourceSet.clear();
		VirSatEditingDomainRegistry.INSTANCE.clear();
	}

	@Test
	public void testCanUndo() {
		List<EObject> seis = new ArrayList<>();
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());

		Command one = VirSatCopyToClipboardCommand.create((VirSatTransactionalEditingDomain) rsEd, seis);
		
		assertTrue("Command can be undone", one.canUndo());
	}
	
	@Test
	public void testCanExecute() {
		List<EObject> seis = new ArrayList<>();
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());

		Command one = VirSatCopyToClipboardCommand.create((VirSatTransactionalEditingDomain) rsEd, seis);
		
		assertTrue("Command can be undone", one.canExecute());
	}

	@Test
	public void testCreate() {
		List<EObject> seis = new ArrayList<>();
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());

		List<EObject> disciplines = new ArrayList<>();
		disciplines.add(RolesFactory.eINSTANCE.createDiscipline());
		disciplines.add(RolesFactory.eINSTANCE.createDiscipline());
		disciplines.add(RolesFactory.eINSTANCE.createDiscipline());
		disciplines.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());

		Command one = VirSatCopyToClipboardCommand.create((VirSatTransactionalEditingDomain) rsEd, seis);
		Command two = VirSatCopyToClipboardCommand.create((VirSatTransactionalEditingDomain) rsEd, disciplines);
		
		assertTrue("Correct Command", one instanceof VirSatCopyToClipboardCommand);
		assertTrue("Correct Command", two instanceof UnexecutableCommand);
	}

	@Test
	public void testExecute() {
		List<EObject> seis = new ArrayList<>();
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());

		Command one = VirSatCopyToClipboardCommand.create((VirSatTransactionalEditingDomain) rsEd, seis);
		one.execute();
		
		assertEquals("Correct Objects in ED", rsEd.getClipboard(), seis);
		assertEquals("Correct Clipboard State", VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd), ClipboardState.COPY);
	}

	@Test
	public void testUndo() {
		List<EObject> seis = new ArrayList<>();
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());

		Command one = VirSatCopyToClipboardCommand.create((VirSatTransactionalEditingDomain) rsEd, seis);
		one.undo();
		
		assertNull("Correct Objects in ED", rsEd.getClipboard());
		assertEquals("Correct Clipboard State", VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd), ClipboardState.EMPTY);
	}

	@Test
	public void testRedo() {
		List<EObject> seis = new ArrayList<>();
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());

		Command one = VirSatCopyToClipboardCommand.create((VirSatTransactionalEditingDomain) rsEd, seis);
		one.undo();
		one.redo();
		
		assertEquals("Correct Objects in ED", rsEd.getClipboard(), seis);
		assertEquals("Correct Clipboard State", VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd), ClipboardState.COPY);
	}
}
