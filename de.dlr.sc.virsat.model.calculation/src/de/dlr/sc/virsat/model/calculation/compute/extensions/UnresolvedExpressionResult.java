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

import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.calculation.compute.IExpressionResult;
import de.dlr.sc.virsat.model.dvlm.general.IName;

/**
 * An expression whose value has not been resolved by a get or could not be resolved
 * by an evaluator due to incompatible operations.
 * Useful for functions that do not care about the value itself, such as count().
 * @author muel_s8
 *
 */

public class UnresolvedExpressionResult implements IExpressionResult {
	private EObject unresolved;
	
	/**
	 * Constructor for unresolved gets
	 * @param unresolved the unresolved eobject
	 */
	
	public UnresolvedExpressionResult(EObject unresolved) {
		this.unresolved = unresolved;
	}
	
	/**
	 * Constructor for results coming from incompatible evaluator calls
	 */
	
	public UnresolvedExpressionResult() {
		
	}
	
	/**
	 * Get the unresolved object wrapped by this class
	 * @return the unresolved eobject wrapped by this class
	 */
	
	public EObject getUnresolved() {
		return unresolved;
	}
	
	@Override
	public String toString() {
		if (unresolved != null && unresolved instanceof IName) {
			return ((IName) unresolved).getName();
		}
		
		return "NaN";
	}

	@Override
	public boolean equals(IExpressionResult obj, double eps) {
		return equals(obj);
	}
}
