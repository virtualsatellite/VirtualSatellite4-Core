/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.property;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.concept.types.factory.BeanTypeInstanceFactory;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.json.ABeanObjectAdapter;

/**
 * Bean class to wrap the referenced beans of ReferencePropertyInstances
 * @param <BEAN_TYPE> type of the referenced bean
 */
public class BeanPropertyReference<BEAN_TYPE extends IBeanObject<? extends ATypeInstance>> extends ABeanObject<ReferencePropertyInstance> implements IBeanProperty<ReferencePropertyInstance, BEAN_TYPE> {

	public BeanPropertyReference() { }

	/**
	 * Constructor to directly set the type instance
	 * @param rpi the type instance to be used
	 */
	public BeanPropertyReference(ReferencePropertyInstance rpi) {
		setTypeInstance(rpi);
	}
	
	private ATypeInstance safeGetTypeInstance(BEAN_TYPE value) {
		if (value == null) {
			return null;
		} else {
			return value.getTypeInstance();
		}
	}
	
	@Override
	public void setValue(BEAN_TYPE value) {
		ATypeInstance reference = safeGetTypeInstance(value);
		ti.setReference(reference);
	}
	
	@Override
	public Command setValue(EditingDomain ed, BEAN_TYPE value) {
		ATypeInstance reference = safeGetTypeInstance(value);
		
		return SetCommand.create(ed, ti, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, reference);
	}

	@SuppressWarnings("unchecked")
	@Override
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	@XmlElement(nillable = true)
	public BEAN_TYPE getValue() {
		BEAN_TYPE referencedBean = null;
		
		// Return null if no reference is set
		if (isSet()) {
			ATypeInstance ref = ti.getReference();
			
			try {
				return (BEAN_TYPE) new BeanTypeInstanceFactory().getInstanceFor(ref);
			} catch (CoreException e) {
				throw new RuntimeException(e);
			}
		}
		
		return referencedBean;
	}

	@Override
	public boolean isSet() {
		return ti.getReference() != null;
	}

	@Override
	public void unset() {
		ti.setReference(null);
	}
}
