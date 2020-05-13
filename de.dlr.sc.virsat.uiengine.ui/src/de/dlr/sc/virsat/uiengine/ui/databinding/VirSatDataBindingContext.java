/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.databinding;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import de.dlr.sc.virsat.uieingine.ui.DVLMEditorPlugin;
import de.dlr.sc.virsat.uiengine.ui.databinding.properties.IVirSatObservableValue;

/**
 * Customized implementation of the DataBindingContext from EMF DataBinding.
 * The customization provides a changed UpdateStrategy which is calling a 
 * canSetValue method before calling the setValue method.
 * 
 * @author fisc_ph
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class VirSatDataBindingContext extends DataBindingContext {

	@Override
	protected UpdateValueStrategy createTargetToModelUpdateValueStrategy(IObservableValue fromValue, IObservableValue toValue) {
		return new VirSatUpdateValueStrategy();
	}
	
	/**
	 * Implementation of the Strategy which checks for the existence of 
	 * IVirSatObservableValue Interfaces and is calling the canSetValue before
	 * calling the setValue method. 
	 * @author fisc_ph
	 *
	 */
	static class VirSatUpdateValueStrategy extends UpdateValueStrategy {
		@Override
		protected IStatus doSet(IObservableValue observableValue, Object value) {
			if (observableValue instanceof IVirSatObservableValue) {
				IVirSatObservableValue observable = (IVirSatObservableValue) observableValue;

				if (!observable.canSetValue(value)) {
					return new Status(Status.WARNING, DVLMEditorPlugin.ID, "Cannot set value of: " + value);
				}
			}
			return super.doSet(observableValue, value);
		}
	}
}
