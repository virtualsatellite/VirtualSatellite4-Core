/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.qudv.provider;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;

/**
 * This class tests the SystemOfQuantitiesItemProvider implementation.
 * @author muel_s8
 *
 */

public class DVLMSystemOfQuantitiesItemProviderTest {

	@Before
	public void setup() {
		UserRegistry.getInstance().setSuperUser(true);
	}
	
	@After
	public void tearDown() {
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	@Test
	public void testCreateRemoveQuantityKindCommand() {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new DVLMQudvItemProviderAdapterFactory());
		
		BasicCommandStack commandStack = new BasicCommandStack();
		EditingDomain ed = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<Resource, Boolean>());
	
		// Build the following structure:
		// SystemOfUnits
		//	- SystemOfQuanties
		//		- qk
		//	- unit
		
		SystemOfUnits sou = QudvFactory.eINSTANCE.createSystemOfUnits();
		SystemOfQuantities soq = QudvFactory.eINSTANCE.createSystemOfQuantities();
		sou.getSystemOfQuantities().add(soq);
		AQuantityKind qk = QudvFactory.eINSTANCE.createQuantityKind();
		AUnit unit = QudvFactory.eINSTANCE.createSimpleUnit();
		sou.getUnit().add(unit);
		soq.getQuantityKind().add(qk);
		
		assertThat("SystemOfQuanties contains Quantity kind", soq.getQuantityKind(), hasItems(qk));
		
		Command removeCommand = RemoveCommand.create(ed, qk);
		assertTrue("Command is executable", removeCommand.canExecute());
		
		unit.setQuantityKind(qk);
		
		Resource resource1 = ed.getResourceSet().createResource(URI.createURI("modeltest/sou"));
		resource1.getContents().add(sou);
		
		Command removeCommand2 = RemoveCommand.create(ed, qk);
		assertFalse("Command is executable", removeCommand2.canExecute());
	}

}
