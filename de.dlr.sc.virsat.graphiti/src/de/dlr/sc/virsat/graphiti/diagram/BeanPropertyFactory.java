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

import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEReference;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.BooleanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.util.PropertydefinitionsSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;

public class BeanPropertyFactory {


	/**
	 * @param object ATypeinstance object
	 * @return beanobject
	 */
	public IBeanObject<? extends APropertyInstance> getInstanceFor(ATypeInstance object) {
		ATypeDefinition type = object.getType();
		BeanPropertyFactorySwitch bpfs = new BeanPropertyFactorySwitch();
		IBeanObject<? extends APropertyInstance> bean = bpfs.doSwitch(type);
		bean.setATypeInstance(object);
		return bean;
	}

	static class BeanPropertyFactorySwitch extends PropertydefinitionsSwitch<IBeanObject<? extends APropertyInstance>> {

		@Override
		public IBeanObject<? extends APropertyInstance> caseFloatProperty(FloatProperty object) {
			IBeanObject<? extends APropertyInstance> beanObjectPropertyFloat = new BeanPropertyFloat();
			return beanObjectPropertyFloat;
		}

		@Override
		public IBeanObject<? extends APropertyInstance> caseIntProperty(IntProperty object) {
			IBeanObject<? extends APropertyInstance> beanObjectPropertyInt = new BeanPropertyInt();
			return beanObjectPropertyInt;
		}

		@Override
		public IBeanObject<? extends APropertyInstance> caseBooleanProperty(BooleanProperty object) {
			IBeanObject<? extends APropertyInstance> beanPropertyBoolean = new BeanPropertyBoolean();
			return beanPropertyBoolean;
		}

		@Override
		public IBeanObject<? extends APropertyInstance> caseEnumProperty(EnumProperty object) {
			IBeanObject<? extends APropertyInstance> beanPropertyEnum = new BeanPropertyEnum();
			return beanPropertyEnum;
		}

		@Override
		public IBeanObject<? extends APropertyInstance> caseResourceProperty(ResourceProperty object) {
			IBeanObject<? extends APropertyInstance> beanPropertyResource = new BeanPropertyResource();
			return beanPropertyResource;
		}

		@Override
		public IBeanObject<? extends APropertyInstance> caseStringProperty(StringProperty object) {
			IBeanObject<? extends APropertyInstance> beanPropertyString = new BeanPropertyString();
			return beanPropertyString;
		}

		@Override
		public IBeanObject<? extends APropertyInstance> caseEReferenceProperty(EReferenceProperty object) {
			IBeanObject<? extends APropertyInstance> beanPropertyEReference = new BeanPropertyEReference<EObject>();
			return beanPropertyEReference;
		}
	}
}
