/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.qudv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.PropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.provider.QudvItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;

public abstract class ABeanQuantityKindTest {
	
	protected ComposedAdapterFactory adapterFactory;
	protected EditingDomain ed;
	
	protected ABeanQuantityKind<?> aBeanQuantityKind;
	protected AQuantityKind aQuantityKind;
	
	protected static final String TEST_NAME = "name";
	protected static final String TEST_SYMBOL = "symbol";
	
	@Before
	public void setUp() throws Exception {
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMDVLMItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ConceptsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RolesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertydefinitionsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertyinstancesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new QudvItemProviderAdapterFactory());
		
		ed = new AdapterFactoryEditingDomain(adapterFactory, new BasicCommandStack());
	}
	
	@Test
	public void testGetUuid() {
		String beanUuid = aBeanQuantityKind.getUuid();
		String unitUuid = aQuantityKind.getUuid().toString();
		assertEquals("Got correct UUID of bean", unitUuid, beanUuid);
	}
	
	@Test
	public void testGetName() {
		aQuantityKind.setName(TEST_NAME);
		assertEquals("Got correct name", TEST_NAME, aBeanQuantityKind.getName());
	}

	@Test
	public void testSetName() {
		aBeanQuantityKind.setName(TEST_NAME);
		assertEquals("Got correct name", TEST_NAME, aQuantityKind.getName());
	}

	@Test
	public void testSetNameEditingDomain() {
		Command setCommand = aBeanQuantityKind.setName(ed, TEST_NAME);
		assertNull("Command was not yet executed", aQuantityKind.getName());
		
		setCommand.execute();
		assertEquals("Name is correctly set", TEST_NAME, aQuantityKind.getName());
	}
	
	@Test
	public void testGetSymbol() {
		aQuantityKind.setSymbol(TEST_SYMBOL);
		assertEquals("Got correct name", TEST_SYMBOL, aBeanQuantityKind.getSymbol());
	}

	@Test
	public void testSetSymbol() {
		aBeanQuantityKind.setSymbol(TEST_SYMBOL);
		assertEquals("Got correct name", TEST_SYMBOL, aQuantityKind.getSymbol());
	}
	
	@Test
	public void testSetSymbolEditingDomain() {
		Command setCommand = aBeanQuantityKind.setSymbol(ed, TEST_SYMBOL);
		assertNull("Command was not yet executed", aQuantityKind.getSymbol());
		
		setCommand.execute();
		assertEquals("Got correct symbol", TEST_SYMBOL, aQuantityKind.getSymbol());
	}
}
