/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.property;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.concept.types.factory.BeanPropertyFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;


public class BeanPropertyFactoryTest {

	@Test
	public void testBeanPropertyFloatFromFactory() {
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		FloatProperty type = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		vpi.setType(type);

		BeanPropertyFactory bpf = new BeanPropertyFactory();
		IBeanObject<? extends APropertyInstance> beanPropertyFloat = bpf.getInstanceFor(vpi);

		assertTrue(beanPropertyFloat instanceof BeanPropertyFloat);
		assertEquals(vpi, beanPropertyFloat.getTypeInstance());
	}

	@Test
	public void testBeanPropertyIntFromFactory() {
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		IntProperty type = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		vpi.setType(type);

		BeanPropertyFactory bpf = new BeanPropertyFactory();
		IBeanObject<? extends APropertyInstance> beanPropertyInt = bpf.getInstanceFor(vpi);
		assertTrue(beanPropertyInt instanceof BeanPropertyInt);
		assertEquals(vpi, beanPropertyInt.getTypeInstance());
	}

	@Test
	public void testBeanPropertyBooleanFromFactory() {
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		BooleanProperty type = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		vpi.setType(type);

		BeanPropertyFactory bpf = new BeanPropertyFactory();
		IBeanObject<? extends APropertyInstance> beanPropertyBoolean = bpf.getInstanceFor(vpi);
		assertTrue(beanPropertyBoolean instanceof BeanPropertyBoolean);
		assertEquals(vpi, beanPropertyBoolean.getTypeInstance());
	}

	@Test
	public void testBeanPropertyEnumFromFactory() {
		EnumUnitPropertyInstance eupi = PropertyinstancesFactory.eINSTANCE.createEnumUnitPropertyInstance();
		EnumProperty enumProperty = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
		eupi.setType(enumProperty);

		BeanPropertyFactory bpf = new BeanPropertyFactory();
		IBeanObject<? extends APropertyInstance> beanPropertyEnum = bpf.getInstanceFor(eupi);

		assertTrue(beanPropertyEnum instanceof BeanPropertyEnum);
		assertEquals(eupi, beanPropertyEnum.getTypeInstance());
	}

	@Test
	public void testBeanPropertyRessourceFromFactory() {
		ResourcePropertyInstance rpi = PropertyinstancesFactory.eINSTANCE.createResourcePropertyInstance();
		ResourceProperty type = PropertydefinitionsFactory.eINSTANCE.createResourceProperty();
		rpi.setType(type);

		BeanPropertyFactory bpf = new BeanPropertyFactory();
		IBeanObject<? extends APropertyInstance> beanPropertyRessource = bpf.getInstanceFor(rpi);

		assertTrue(beanPropertyRessource instanceof BeanPropertyResource);
		assertEquals(rpi, beanPropertyRessource.getTypeInstance());
	}

	@Test
	public void testBeanPropertyStringFromFactory() {
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		StringProperty type = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		vpi.setType(type);

		BeanPropertyFactory bpf = new BeanPropertyFactory();
		IBeanObject<? extends APropertyInstance> beanPropertyString = bpf.getInstanceFor(vpi);

		assertTrue(beanPropertyString instanceof BeanPropertyString);
		assertEquals(vpi, beanPropertyString.getTypeInstance());
	}

	@Test
	public void testBeanPropertyEReferenceFromFactory() {
		EReferencePropertyInstance erpi = PropertyinstancesFactory.eINSTANCE.createEReferencePropertyInstance();
		EReferenceProperty type = PropertydefinitionsFactory.eINSTANCE.createEReferenceProperty();
		erpi.setType(type);

		BeanPropertyFactory bpf = new BeanPropertyFactory();
		IBeanObject<? extends APropertyInstance> beanPropertyEreference = bpf.getInstanceFor(erpi);

		assertTrue(beanPropertyEreference instanceof BeanPropertyEReference);
		assertEquals(erpi, beanPropertyEreference.getTypeInstance());
	}
}
