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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.edit.IEMFEditObservable;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * Helper and factory class, to create the Invokation-Proxies around the Observers from the EMF
 * DataBinding, the Proxies will decorate the given observers by the canSetValue method from the
 * IVirSatObservableValue interface
 * 
 * @author fisc_ph
 *
 */
public class VirSatEMFEditObservables {
	
	/**
	 * Hidden constructor since this is a helper factory class
	 */
	private VirSatEMFEditObservables() {
	}
	
	/**
	 * This method creates a decorating proxy around the given IObservableValue.
	 * In case the IObservabkleValue is an EMFEdit one, the invocation handler will redirect the canSetValue
	 * calls to the correct SetCommand from the EMF Command Framework. In case it is not an EMFEdit one, it
	 * will tell the upper framework that it can always execute. All other invocations are directly forwarded
	 * to the delegation observable 
	 * @param observableValue The delegation observable which should be decorated by the given method
	 * @return the result of the forwarded invocation
	 */
	@SuppressWarnings("rawtypes")
	public static IVirSatObservableValue proxy(IObservableValue observableValue) {
		IVirSatObservableValue proxy = (IVirSatObservableValue) Proxy.newProxyInstance(IVirSatObservableValue.class.getClassLoader(), new Class<?>[] { IVirSatObservableValue.class }, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// In case the canSetValue method is accessible than execute the code to create
				// a command for probing if setting the value is currently possible
				if (method.getName().equals(IVirSatObservableValue.METHOD_CAN_SET_VALUE)) {
					if (observableValue instanceof IEMFEditObservable) {
						IEMFEditObservable iEmfEditObservableValue = (IEMFEditObservable) observableValue;
	
						// Build the command and probe it
						Object object = iEmfEditObservableValue.getObserved();
						EStructuralFeature eStructuralFeature = iEmfEditObservableValue.getStructuralFeature();
						EditingDomain ed = iEmfEditObservableValue.getEditingDomain();
						Command command = SetCommand.create(ed, object, eStructuralFeature, args[0]);
						boolean canSetValue = command.canExecute();
						return canSetValue;
					} 
					
					// All other observables which cannot create a command should be treated as if they
					// accept what ever value shall be set to the owning object
					return true;
				}
				
				// All other invokations are simply forwarded to the delegate
				return method.invoke(observableValue, args);
			}
		});
		
		return proxy;
	}
}
