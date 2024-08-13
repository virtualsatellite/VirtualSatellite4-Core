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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.factory.BeanUnitFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleUnit;

public class BeanUnitFactoryTest {

	@Test
	public void testBeanUnitSimpleFromFactory() {
		SimpleUnit simpleUnit = QudvFactory.eINSTANCE.createSimpleUnit();
		
		BeanUnitFactory beanUnitFactory = new BeanUnitFactory();
		IBeanUnit<? extends AUnit> beanUnitSimple = beanUnitFactory.getInstanceFor(simpleUnit);
		
		assertThat(beanUnitSimple, instanceOf(BeanUnitSimple.class));
		assertEquals(simpleUnit, beanUnitSimple.getUnit());
	}
	
	@Test
	public void testBeanUnitPrefixedFromFactory() {
		PrefixedUnit prefixedUnit = QudvFactory.eINSTANCE.createPrefixedUnit();
		
		BeanUnitFactory beanUnitFactory = new BeanUnitFactory();
		IBeanUnit<? extends AUnit> beanUnitPrefixed = beanUnitFactory.getInstanceFor(prefixedUnit);
		
		assertThat(beanUnitPrefixed, instanceOf(BeanUnitPrefixed.class));
		assertEquals(prefixedUnit, beanUnitPrefixed.getUnit());
	}
	
	@Test
	public void testBeanUnitDerivedFromFactory() {
		DerivedUnit derivedUnit = QudvFactory.eINSTANCE.createDerivedUnit();
		
		BeanUnitFactory beanUnitFactory = new BeanUnitFactory();
		IBeanUnit<? extends AUnit> beanUnitDerived = beanUnitFactory.getInstanceFor(derivedUnit);
		
		assertThat(beanUnitDerived, instanceOf(BeanUnitDerived.class));
		assertEquals(derivedUnit, beanUnitDerived.getUnit());
	}
	
	@Test
	public void testBeanUnitLinearConversionFromFactory() {
		LinearConversionUnit linearConversionUnit = QudvFactory.eINSTANCE.createLinearConversionUnit();
		
		BeanUnitFactory beanUnitFactory = new BeanUnitFactory();
		IBeanUnit<? extends AUnit> beanUnitLinearConversion = beanUnitFactory.getInstanceFor(linearConversionUnit);
		
		assertThat(beanUnitLinearConversion, instanceOf(BeanUnitLinearConversion.class));
		assertEquals(linearConversionUnit, beanUnitLinearConversion.getUnit());
	}
	
	@Test
	public void testBeanUnitAffineConversionFromFactory() {
		AffineConversionUnit affineConversionUnit = QudvFactory.eINSTANCE.createAffineConversionUnit();
		
		BeanUnitFactory beanUnitFactory = new BeanUnitFactory();
		IBeanUnit<? extends AUnit> beanUnitAffineConversion = beanUnitFactory.getInstanceFor(affineConversionUnit);
		
		assertThat(beanUnitAffineConversion, instanceOf(BeanUnitAffineConversion.class));
		assertEquals(affineConversionUnit, beanUnitAffineConversion.getUnit());
	}
}
