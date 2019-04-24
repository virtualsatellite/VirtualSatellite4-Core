/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.databinding.properties;

import org.eclipse.core.databinding.observable.value.IObservableValue;

/**
 * Interface for the DataBinding. In case an Observable is implementing this interface,
 * the according update Strategy from VirSatDataBindingContext will use this interface to call
 * the canSetValue method for checking if setting a value is currently permitted.
 * @author fisc_ph
 *
 */
public interface IVirSatObservableValue extends IObservableValue<Object> {

	/**
	 * Static access to the Method name, needed in Invocation-Proxy
	 */
	String METHOD_CAN_SET_VALUE = "canSetValue";
	
	/**
	 * Call this method to probe the observable if it is allowed to set the given value
	 * @param object the object to be set by the observable
	 * @return true in case the observer is allowed to set the value
	 */
	boolean canSetValue(Object object);
}
