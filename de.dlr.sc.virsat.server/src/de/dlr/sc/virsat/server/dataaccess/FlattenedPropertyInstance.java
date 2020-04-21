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

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;

public class FlattenedPropertyInstance {

	private String fullQualifiedInstanceName;
	private String value;
	
	public FlattenedPropertyInstance() { }
	
	public FlattenedPropertyInstance(APropertyInstance propertyInstance) {
		setFullQualifiedInstanceName(propertyInstance.getFullQualifiedInstanceName());
		
		PropertyInstanceValueSwitch valueSwitch = new PropertyInstanceValueSwitch();
		setValue(valueSwitch.getValueString(propertyInstance));
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
}
