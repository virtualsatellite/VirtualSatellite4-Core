/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.diagram;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEReference;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
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
		assertTrue(beanPropertyFloat.getTypeInstance() instanceof ValuePropertyInstance);
	}

	@Test
	public void testBeanPropertyIntFromFactory() {
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		IntProperty type = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		vpi.setType(type);

		BeanPropertyFactory bpf = new BeanPropertyFactory();
		IBeanObject<? extends APropertyInstance> beanPropertyInt = bpf.getInstanceFor(vpi);
		assertTrue(beanPropertyInt instanceof BeanPropertyInt);
		assertTrue(beanPropertyInt.getTypeInstance() instanceof ValuePropertyInstance);
	}

	@Test
	public void testBeanPropertyBooleanFromFactory() {
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		BooleanProperty type = PropertydefinitionsFactory.eINSTANCE.createBooleanProperty();
		vpi.setType(type);

		BeanPropertyFactory bpf = new BeanPropertyFactory();
		IBeanObject<? extends APropertyInstance> beanPropertyBoolean = bpf.getInstanceFor(vpi);
		assertTrue(beanPropertyBoolean instanceof BeanPropertyBoolean);
		assertTrue(beanPropertyBoolean.getTypeInstance() instanceof ValuePropertyInstance);
	}

	@Test
	public void testBeanPropertyEnumFromFactory() {
		EnumValueDefinition evd1 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		EnumProperty enumProperty = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
		enumProperty.getValues().add(evd1);
		EnumUnitPropertyInstance eupi = PropertyinstancesFactory.eINSTANCE.createEnumUnitPropertyInstance();
		eupi.setType(enumProperty);
		BeanPropertyFactory bpf = new BeanPropertyFactory();
		IBeanObject<? extends APropertyInstance> beanPropertyEnum = bpf.getInstanceFor(eupi);

		assertTrue(beanPropertyEnum instanceof BeanPropertyEnum);
		assertTrue(beanPropertyEnum.getTypeInstance() instanceof EnumUnitPropertyInstance);
	}

	@Test
	public void testBeanPropertyRessourceFromFactory() {
		ResourcePropertyInstance rpi = PropertyinstancesFactory.eINSTANCE.createResourcePropertyInstance();
		ResourceProperty type = PropertydefinitionsFactory.eINSTANCE.createResourceProperty();
		rpi.setType(type);

		BeanPropertyFactory bpf = new BeanPropertyFactory();
		IBeanObject<? extends APropertyInstance> beanPropertyRessource = bpf.getInstanceFor(rpi);

		assertTrue(beanPropertyRessource instanceof BeanPropertyResource);
		assertTrue(beanPropertyRessource.getTypeInstance() instanceof ResourcePropertyInstance);
	}

	@Test
	public void testBeanPropertyStringFromFactory() {
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		StringProperty type = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		vpi.setType(type);

		BeanPropertyFactory bpf = new BeanPropertyFactory();
		IBeanObject<? extends APropertyInstance> beanPropertyString = bpf.getInstanceFor(vpi);

		assertTrue(beanPropertyString instanceof BeanPropertyString);
		assertTrue(beanPropertyString.getTypeInstance() instanceof ValuePropertyInstance);
	}

	@Test
	public void testBeanPropertyEReferenceFromFactory() {
		EReferencePropertyInstance erpi = PropertyinstancesFactory.eINSTANCE.createEReferencePropertyInstance();
		EReferenceProperty type = PropertydefinitionsFactory.eINSTANCE.createEReferenceProperty();
		erpi.setType(type);

		BeanPropertyFactory bpf = new BeanPropertyFactory();
		IBeanObject<? extends APropertyInstance> beanPropertyEreference = bpf.getInstanceFor(erpi);

		assertTrue(beanPropertyEreference instanceof BeanPropertyEReference);
		assertTrue(beanPropertyEreference.getTypeInstance() instanceof EReferencePropertyInstance);
	}

}
