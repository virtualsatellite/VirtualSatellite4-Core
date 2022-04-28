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
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.provider.QudvItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;

public abstract class ABeanUnitTest {

	protected ComposedAdapterFactory adapterFactory;
	protected EditingDomain ed;
	
	protected ABeanUnit<?> aBeanUnit;
	protected AUnit aUnit;
	protected SimpleQuantityKind simpleQuantityKind;
	
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
		
		simpleQuantityKind = QudvFactory.eINSTANCE.createSimpleQuantityKind();
	}
	
	@Test
	public void testGetUuid() {
		String beanUuid = aBeanUnit.getUuid();
		String unitUuid = aUnit.getUuid().toString();
		assertEquals("Got correct UUID of bean", unitUuid, beanUuid);
	}
	
	@Test
	public void testGetName() {
		aUnit.setName(TEST_NAME);
		assertEquals("Got correct name", TEST_NAME, aBeanUnit.getName());
	}

	@Test
	public void testSetName() {
		aBeanUnit.setName(TEST_NAME);
		assertEquals("Got correct name", TEST_NAME, aUnit.getName());
	}

	@Test
	public void testSetNameEditingDomain() {
		Command setCommand = aBeanUnit.setName(ed, TEST_NAME);
		assertNull("Command was not yet executed", aUnit.getName());
		
		setCommand.execute();
		assertEquals("Name is correctly set", TEST_NAME, aUnit.getName());
	}
	
	@Test
	public void testGetSymbol() {
		aUnit.setSymbol(TEST_SYMBOL);
		assertEquals("Got correct symbol", TEST_SYMBOL, aBeanUnit.getSymbol());
	}

	@Test
	public void testSetSymbol() {
		aBeanUnit.setSymbol(TEST_SYMBOL);
		assertEquals("Got correct symbol", TEST_SYMBOL, aUnit.getSymbol());
	}
	
	@Test
	public void testSetSymbolEditingDomain() {
		Command setCommand = aBeanUnit.setSymbol(ed, TEST_SYMBOL);
		assertNull("Command was not yet executed", aUnit.getSymbol());
		
		setCommand.execute();
		assertEquals("Got correct symbol", TEST_SYMBOL, aUnit.getSymbol());
	}
	
	@Test
	public void testGetQuanityKind() {
		assertNull("Got empty bean for no quantityKind", aBeanUnit.getQuantityKindBean());
		aUnit.setQuantityKind(simpleQuantityKind);
		assertEquals("Got correct quantityKind", simpleQuantityKind, aBeanUnit.getQuantityKindBean().getQuantityKind());
	}

	@Test
	public void testSetQuanityKind() {
		aBeanUnit.setQuantityKindBean(new BeanQuantityKindSimple(simpleQuantityKind));
		assertEquals("Got correct quantityKind", simpleQuantityKind, aBeanUnit.getQuantityKindBean().getQuantityKind());
	}
	
	@Test
	public void testSetQuanityKindEditingDomain() {
		Command setCommand = aBeanUnit.setQuantityKindBean(ed, new BeanQuantityKindSimple(simpleQuantityKind));
		assertNull("Command was not yet executed", aBeanUnit.getQuantityKindBean());
		
		setCommand.execute();
		assertEquals("Got correct quantityKind", simpleQuantityKind, aBeanUnit.getQuantityKindBean().getQuantityKind());
	}
}
