/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.dataaccess;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.util.PropertydefinitionsSwitch;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

public class FlattenedPropertyInstance {

	private String uuid; // r
	private String fullQualifiedInstanceName; // r
	private String value; // rw
	// type //r
	// maybe add. information e.g. quantityKindName
	
	public FlattenedPropertyInstance() { }
	
	public FlattenedPropertyInstance(APropertyInstance propertyInstance) {
		setFullQualifiedInstanceName(propertyInstance.getFullQualifiedInstanceName());
//		propertyInstance.getType()
		
		new PropertydefinitionsSwitch<String>() {
			@Override
			public String caseAQudvTypeProperty(AQudvTypeProperty object) {
				// TODO Auto-generated method stub
				return super.caseAQudvTypeProperty(object);
			}
		}.doSwitch(propertyInstance.getType());
		
		PropertyInstanceValueSwitch valueSwitch = new PropertyInstanceValueSwitch();
		// always use base units?
		setValue(valueSwitch.getValueString(propertyInstance));
		setUuid(propertyInstance.getUuid().toString());
	}

	public String getFullQualifiedInstanceName() {
		return fullQualifiedInstanceName;
	}

	public void setFullQualifiedInstanceName(String fullQualifiedInstanceName) {
		this.fullQualifiedInstanceName = fullQualifiedInstanceName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Command unflatten(VirSatTransactionalEditingDomain editingDomain, APropertyInstance property) {
		CompoundCommand updatePropertyCommand = new CompoundCommand();
		
		return updatePropertyCommand;
	}
}
