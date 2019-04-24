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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;

/**
 * Class to represent an Attribute of a Bean. These classes will wrap the Property Instances
 * @author fisc_ph
 *
 * @param <P_TYPE> The property bean type
 * @param <V_TYPE> The value type of the bean
 */
public interface IBeanProperty<P_TYPE extends APropertyInstance, V_TYPE> extends IBeanObject<P_TYPE> {
	
	/**
	 * Sets the property value of the bean
	 * @param value the property value
	 */
	void setValue(V_TYPE value);
	
	/**
	 * Creates the set command for setting the value of the property
	 * @param ed the editing domain
	 * @param value the new value of the property
	 * @return the command to set the property to the specified value
	 */
	Command setValue(EditingDomain ed, V_TYPE value);
	
	/**
	 * Gets the value of this bean property
	 * @return the value of the bean property
	 */
	V_TYPE getValue();
	
	/**
	 * Checks if the bean property has been set
	 * @return true iff the bean value has been set
	 */
	boolean isSet();
	
	/**
	 * Unsets the value of the bean property
	 */
	void unset();
}
