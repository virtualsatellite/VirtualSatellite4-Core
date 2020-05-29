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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;

public class BeanPropertyReference<BEAN_TYPE extends IBeanObject<? extends ATypeInstance>> extends ABeanObject<ReferencePropertyInstance> implements IBeanProperty<ReferencePropertyInstance, BEAN_TYPE> {

	public BeanPropertyReference() {
	}

	public BeanPropertyReference(ReferencePropertyInstance rpi) {
		setTypeInstance(rpi);
	}
	
	@Override
	public void setValue(BEAN_TYPE value) {
		ti.setReference(value.getTypeInstance());		
	}

	@Override
	public Command setValue(EditingDomain ed, BEAN_TYPE value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BEAN_TYPE getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSet() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unset() {
		// TODO Auto-generated method stub
		
	}

	

}
