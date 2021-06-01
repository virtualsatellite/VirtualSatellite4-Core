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
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor;
import de.dlr.sc.virsat.model.dvlm.qudv.provider.QudvItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;

public class BeanFactorUnitTest {
	
	private AdapterFactoryEditingDomain ed;
	
	private UnitFactor factor;
	private BeanFactorUnit factorBean;
	private SimpleUnit testUnit;

	private BeanUnitSimple testUnitBean;
	
	private static final double TEST_EXPONENT = 20.56;
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
		
		factor = QudvFactory.eINSTANCE.createUnitFactor();
		factorBean = new BeanFactorUnit();
		factorBean.setFactor(factor);
		
		testUnit = QudvFactory.eINSTANCE.createSimpleUnit();
		testUnitBean = new BeanUnitSimple(testUnit);
	}
	
	@Test
	public void testGetUuid() {
		String beanUuid = factorBean.getUuid();
		String unitUuid = factor.getUuid().toString();
		assertEquals("Got correct UUID of bean", unitUuid, beanUuid);
	}
	
	@Test
	public void testGetExponent() {
		factor.setExponent(TEST_EXPONENT);
		assertEquals("Got right value", TEST_EXPONENT, factorBean.getExponent(), EPSILON);
	}
	
	@Test
	public void testSetExponent() {
		assertEquals("Initial value", 0.0, factor.getExponent(), EPSILON);
		factorBean.setExponent(TEST_EXPONENT);
		
		assertEquals("Value correctly set", TEST_EXPONENT, factor.getExponent(), EPSILON);
	}
	
	@Test
	public void testSetExponentEditingDomain() {
		assertEquals("Initial value", 0.0, factor.getExponent(), EPSILON);
		Command cmd = factorBean.setExponent(ed, TEST_EXPONENT);
		cmd.execute();
		
		assertEquals("Value correctly set", TEST_EXPONENT, factor.getExponent(), EPSILON);
	}
	
	@Test
	public void testGetUnit() {
		factor.setUnit(testUnit);
		assertEquals("Got right value", testUnit, factorBean.getUnitBean().getUnit());
	}
	
	@Test
	public void testSetUnit() {
		assertNull("Initial value", factor.getUnit());
		factorBean.setUnitBean(testUnitBean);
		
		assertEquals("Value correctly set", testUnit, factor.getUnit());
	}
	
	@Test
	public void testSetUnitEditingDomain() {
		assertNull("Initial value", factor.getUnit());
		Command cmd = factorBean.setUnitBean(ed, testUnitBean);
		cmd.execute();
		
		assertEquals("Value correctly set", testUnit, factor.getUnit());
	}
}
