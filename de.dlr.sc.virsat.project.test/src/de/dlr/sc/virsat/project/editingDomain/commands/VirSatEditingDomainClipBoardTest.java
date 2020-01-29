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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatEditingDomainClipBoard.ClipboardState;

/**
 * test cases for the ClipBoard class that extends the ClipBoard functionality of
 * an AdapterFactoryEditingDomain by providing additional information about the state
 * of the objects in the ClipBoard
 *
 */
public class VirSatEditingDomainClipBoardTest {

	private AdapterFactoryEditingDomain editingDomain;

	@Before
	public void setUp() {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		UserRegistry.getInstance().setSuperUser(true);
		BasicCommandStack commandStack = new BasicCommandStack();
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<Resource, Boolean>());
	}

	@After
	public void tearDown() throws CoreException {
		UserRegistry.getInstance().setSuperUser(false);
	}

	@Test
	public void testRegisterEd() {
		assertNull("Initially ED is not registered", VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
		VirSatEditingDomainClipBoard.INSTANCE.registerEd(editingDomain);
		assertEquals("Initially ED has been registered", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}

	@Test
	public void testDeregisterEd() {
		VirSatEditingDomainClipBoard.INSTANCE.registerEd(editingDomain);
		assertEquals("Initially ED has been registered", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
		VirSatEditingDomainClipBoard.INSTANCE.deregisterEd(editingDomain);
		assertNull("ED is no longer registered", VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}

	@Test
	public void testGetClipboardState() {
		Object object = new Object();
		Collection<Object> collection = Collections.singleton(object);
		VirSatEditingDomainClipBoard.INSTANCE.copyClipboard(editingDomain, collection);
		
		assertEquals("Clipboard state has been obtained correctly", ClipboardState.COPY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}

	@Test
	public void testGetCollection() {
		Object object = new Object();
		Collection<Object> collection = Collections.singleton(object);
		VirSatEditingDomainClipBoard.INSTANCE.copyClipboard(editingDomain, collection);
		
		assertEquals("Clipboard collection has been obtained correctly", collection, VirSatEditingDomainClipBoard.INSTANCE.getCollection(editingDomain));
	}

	@Test
	public void testFlushClipboard() {
		VirSatEditingDomainClipBoard.INSTANCE.flushClipboard(editingDomain);
		assertEquals("new State arrived", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}

	@Test
	public void testCopyClipboard() {
		VirSatEditingDomainClipBoard.INSTANCE.copyClipboard(editingDomain, Collections.EMPTY_LIST);
		assertEquals("new State arrived", ClipboardState.COPY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}

	@Test
	public void testCutClipboard() {
		VirSatEditingDomainClipBoard.INSTANCE.cutClipboard(editingDomain, Collections.EMPTY_LIST);
		assertEquals("new State arrived", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}

}
