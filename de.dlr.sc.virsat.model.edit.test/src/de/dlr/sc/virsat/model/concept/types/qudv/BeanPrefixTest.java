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
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.provider.QudvItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;

public class BeanPrefixTest {

	private AdapterFactoryEditingDomain ed;
	
	private Prefix prefix;
	private BeanPrefix prefixBean;
	private static final String TEST_NAME = "name";
	private static final String TEST_SYMBOL = "symbol";
	private static final double TEST_FACTOR = 20.56;
	private static final double EPSILON = 0.001;
	
	@Before
	public void setUp() throws Exception {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		
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
		
		prefix = QudvFactory.eINSTANCE.createPrefix();
		prefixBean = new BeanPrefix();
		prefixBean.setPrefix(prefix);
	}
	
	@Test
	public void testGetUuid() {
		String beanUuid = prefixBean.getUuid();
		String unitUuid = prefix.getUuid().toString();
		assertEquals("Got correct UUID of bean", unitUuid, beanUuid);
	}
	
	@Test
	public void testGetName() {
		prefix.setName(TEST_NAME);
		assertEquals("Got correct name", TEST_NAME, prefixBean.getName());
	}

	@Test
	public void testSetName() {
		prefixBean.setName(TEST_NAME);
		assertEquals("Got correct name", TEST_NAME, prefix.getName());
	}

	@Test
	public void testSetNameEditingDomain() {
		Command setCommand = prefixBean.setName(ed, TEST_NAME);
		assertNull("Command was not yet executed", prefix.getName());
		
		setCommand.execute();
		assertEquals("Name is correctly set", TEST_NAME, prefix.getName());
	}
	
	@Test
	public void testGetSymbol() {
		prefix.setSymbol(TEST_SYMBOL);
		assertEquals("Got correct name", TEST_SYMBOL, prefixBean.getSymbol());
	}

	@Test
	public void testSetSymbol() {
		prefixBean.setSymbol(TEST_SYMBOL);
		assertEquals("Got correct name", TEST_SYMBOL, prefix.getSymbol());
	}
	
	@Test
	public void testSetSymbolEditingDomain() {
		Command setCommand = prefixBean.setSymbol(ed, TEST_SYMBOL);
		assertNull("Command was not yet executed", prefix.getSymbol());
		
		setCommand.execute();
		assertEquals("Got correct symbol", TEST_SYMBOL, prefix.getSymbol());
	}
	
	@Test
	public void getFactor() {
		prefix.setFactor(TEST_FACTOR);
		assertEquals("Got right value", TEST_FACTOR, prefixBean.getFactor(), EPSILON);
	}
	
	@Test
	public void testSetFactor() {
		assertEquals("Initial value", 0.0, prefix.getFactor(), EPSILON);
		prefixBean.setFactor(TEST_FACTOR);
		
		assertEquals("Value correctly set", TEST_FACTOR, prefix.getFactor(), EPSILON);
	}
	
	@Test
	public void testSetFactorEditingDomain() {
		assertEquals("Initial value", 0.0, prefix.getFactor(), EPSILON);
		Command cmd = prefixBean.setFactor(ed, TEST_FACTOR);
		cmd.execute();
		
		assertEquals("Value correctly set", TEST_FACTOR, prefix.getFactor(), EPSILON);
	}
}
