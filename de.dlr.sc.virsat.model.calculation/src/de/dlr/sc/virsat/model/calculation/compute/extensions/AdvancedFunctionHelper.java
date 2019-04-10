/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.compute.extensions;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import de.dlr.sc.virsat.model.calculation.compute.IExpressionResult;
import de.dlr.sc.virsat.model.calculation.compute.AAdvancedFunctionOp;

/**
 * General helper class for set functions independent of the underlying expression result type.
 * @author muel_s8
 *
 */

public class AdvancedFunctionHelper {
	
	private String setOperator;
	
	
	/**
	 * Constructor
	 * @param setOperator wrapped set operator
	 */
	
	public AdvancedFunctionHelper(String setOperator) {
		this.setOperator = setOperator;
	}

	/**
	 * Get the set function with this name
	 * @return a set function operation with the passed name
	 */
	
	public AAdvancedFunctionOp getSetFunctionOp() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] setFunctions = registry.getConfigurationElementsFor(AAdvancedFunctionOp.EXTENSION_POINT_ID);

		for (IConfigurationElement configElement : setFunctions) {
			if (configElement.getAttribute("name").equals(setOperator)) {
				try {
					AAdvancedFunctionOp op = (AAdvancedFunctionOp) configElement.createExecutableExtension("class");
					return op;
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Gets the registered set function operations
	 * @return an array containing the names of the registered set function operations
	 */
	
	public static String[] getSetFunctionOps() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] setFunctions = registry.getConfigurationElementsFor(AAdvancedFunctionOp.EXTENSION_POINT_ID);
		
		String[] setFunctionOps = new String[setFunctions.length];
		
		for (int i = 0; i < setFunctions.length; ++i) {
			setFunctionOps[i] = setFunctions[i].getAttribute("name");
		}
		
		return setFunctionOps;
	}
	
	/**
	 * Applies a set function to a set of inputs containing an unresolved input
	 * @param inputs the inputs
	 * @return the result of the operation
	 */
	public IExpressionResult handleUnresolved(List<IExpressionResult> inputs) {
		AAdvancedFunctionOp op = getSetFunctionOp();
		return op.handleUnresolved(inputs);
	}
}
