/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain.commands.dnd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertFalse;

import java.util.Collections;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

public class VirSatDragAndDropInheritanceCommandHelperTest extends AProjectTestCase {

	private StructuralElementInstance seiTypeA1;
	private StructuralElementInstance seiTypeA2;
	private StructuralElementInstance seiTypeB1;
	private StructuralElementInstance seiTypeB2;
	private StructuralElementInstance seiTypeC;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		
		StructuralElement seTypeA = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seTypeB = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seTypeC = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seTypeC.getCanInheritFrom().add(seTypeA);
		seTypeC.getCanInheritFrom().add(seTypeB);
		
		seiTypeA1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiTypeA2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiTypeB1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiTypeB2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiTypeC = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		seiTypeA1.setType(seTypeA);
		seiTypeA2.setType(seTypeA);
		seiTypeB1.setType(seTypeB);
		seiTypeB2.setType(seTypeB);
		seiTypeC.setType(seTypeC);
	}

	@Test
	public void testCreateDropCommandReplaceInheritance() {
		// There is no inheritance yet, thus adding the first one, should create a command that adds the first one
		VirSatDragAndDropInheritanceCommandHelper.createDropCommand(
				editingDomain,
				Collections.singleton(seiTypeA1),
				seiTypeC
		).execute();
		
		assertThat("List of inheritance objects is correct", seiTypeC.getSuperSeis(), contains(seiTypeA1));
		
		// Add another sei of type B which should also be added instead of replacing the first one
		VirSatDragAndDropInheritanceCommandHelper.createDropCommand(
				editingDomain,
				Collections.singleton(seiTypeB1),
				seiTypeC
		).execute();
		
		assertThat("List of inheritance objects is correct", seiTypeC.getSuperSeis(), contains(seiTypeA1, seiTypeB1));
		
		// Now replace A1 with A2
		VirSatDragAndDropInheritanceCommandHelper.createDropCommand(
				editingDomain,
				Collections.singleton(seiTypeA2),
				seiTypeC
		).execute();
		
		assertThat("List of inheritance objects is correct", seiTypeC.getSuperSeis(), contains(seiTypeA2, seiTypeB1));
		
		// Now replace B1 with B2
		VirSatDragAndDropInheritanceCommandHelper.createDropCommand(
				editingDomain,
				Collections.singleton(seiTypeB2),
				seiTypeC
		).execute();
		
		assertThat("List of inheritance objects is correct", seiTypeC.getSuperSeis(), contains(seiTypeA2, seiTypeB2));
	}
	
	@Test
	public void testCreateDropCommandInvalidInheritance() {
		// Adding A1 as first one		
		Command invalidInheritanceDropCommand = VirSatDragAndDropInheritanceCommandHelper.createDropCommand(
				editingDomain,
				Collections.singleton(seiTypeA1),
				seiTypeB1
		);
				
		assertFalse("Command cannot be executed", invalidInheritanceDropCommand.canExecute());
	}
}
