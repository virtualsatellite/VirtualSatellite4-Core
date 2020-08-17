/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.factory;

import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEReference;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.util.PropertydefinitionsSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;

/**
 * This factory class produces Bean Objects, wrapping certain BeanProperties for a given object.
 */
public class BeanPropertyFactory {

	private BeanPropertyFactorySwitch bpfs = new BeanPropertyFactorySwitch();

	/**
	 * @param object Object that shall be wrapped into Bean Object
	 * @return Bean Object wrapper for object
	 */
	public IBeanObject<? extends APropertyInstance> getInstanceFor(ATypeInstance object) {
		ATypeDefinition type = object.getType();
		IBeanObject<? extends APropertyInstance> bean = bpfs.doSwitch(type);
		bean.setATypeInstance(object);
		return bean;
	}

	private static class BeanPropertyFactorySwitch extends PropertydefinitionsSwitch<IBeanObject<? extends APropertyInstance>> {

		@Override
		public IBeanObject<? extends APropertyInstance> caseFloatProperty(FloatProperty object) {
			return new BeanPropertyFloat();
		}

		@Override
		public IBeanObject<? extends APropertyInstance> caseIntProperty(IntProperty object) {
			return new BeanPropertyInt();
		}

		@Override
		public IBeanObject<? extends APropertyInstance> caseBooleanProperty(BooleanProperty object) {
			return new BeanPropertyBoolean();
		}

		@Override
		public IBeanObject<? extends APropertyInstance> caseEnumProperty(EnumProperty object) {
			return new BeanPropertyEnum();
		}

		@Override
		public IBeanObject<? extends APropertyInstance> caseResourceProperty(ResourceProperty object) {
			return new BeanPropertyResource();
		}

		@Override
		public IBeanObject<? extends APropertyInstance> caseStringProperty(StringProperty object) {
			return new BeanPropertyString();
		}

		@Override
		public IBeanObject<? extends APropertyInstance> caseReferenceProperty(ReferenceProperty object) {
			return new BeanPropertyReference<IBeanObject<? extends ATypeInstance>>();
		}

		@Override
		public IBeanObject<? extends APropertyInstance> caseComposedProperty(ComposedProperty object) {
			return new BeanPropertyComposed<IBeanCategoryAssignment>();
		}

		@Override
		public IBeanObject<? extends APropertyInstance> caseEReferenceProperty(EReferenceProperty object) {
			return new BeanPropertyEReference<EObject>();
		}
	}
}
