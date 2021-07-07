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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;

public class BeanUnitPrefixedTest extends ABeanUnitTest {

	private PrefixedUnit prefixedUnit;
	private BeanUnitPrefixed prefixedBeanUnit;
	private Prefix prefix;
	private BeanPrefix beanPrefix;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		prefixedUnit = QudvFactory.eINSTANCE.createPrefixedUnit();
		prefixedBeanUnit = new BeanUnitPrefixed();
		prefixedBeanUnit.setUnit(prefixedUnit);
		
		aUnit = prefixedUnit;
		aBeanUnit = prefixedBeanUnit;
		
		prefix = QudvFactory.eINSTANCE.createPrefix();
		beanPrefix = new BeanPrefix(prefix);
	}
	
	@Test
	public void getPrefix() {
		prefixedUnit.setPrefix(prefix);
		assertEquals("Got right value", prefix, prefixedBeanUnit.getPrefixBean().getPrefix());
	}
	
	@Test
	public void testSetPrefix() {
		assertNull("Is null initial", prefixedUnit.getPrefix());
		prefixedBeanUnit.setPrefixBean(beanPrefix);
		
		assertEquals("Value correctly set", prefix, prefixedUnit.getPrefix());
	}
	
	@Test
	public void testSetPrefixEditingDomain() {
		assertNull("Is null initial", prefixedUnit.getPrefix());
		Command cmd = prefixedBeanUnit.setPrefixBean(ed, beanPrefix);
		cmd.execute();
		
		assertEquals("Value correctly set", prefix, prefixedUnit.getPrefix());
	}
	
	@Test
	public void getIsInvertable() {
		prefixedUnit.setIsInvertible(true);
		assertEquals("Got right value", prefixedUnit.isIsInvertible(), prefixedBeanUnit.getIsInvertible());
	}
	
	@Test
	public void testSetIsInvertable() {
		assertFalse("Is false initial", prefixedUnit.isIsInvertible());
		prefixedBeanUnit.setIsInvertible(true);
		
		assertTrue("Value correctly set", prefixedUnit.isIsInvertible());
	}
	
	@Test
	public void testSetIsInvertableEditingDomain() {
		assertFalse("Is false initial", prefixedUnit.isIsInvertible());
		Command cmd = prefixedBeanUnit.setIsInvertible(ed, true);
		cmd.execute();
		
		assertTrue("Value correctly set", prefixedUnit.isIsInvertible());
	}
}
