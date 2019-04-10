/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.ps.ui.wizards;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
/**
 * Used in treemapper for product structure
 * @author bell_er
 *
 */
public final class StructuralElementInstanceMappingBean { 
	private StructuralElementInstance left; 
	private StructuralElementInstance right;
	
	/**
	 * @return left
	 */
	public StructuralElementInstance getLeft() {
		return left;
	}
	/**
	 * @param left left
	 */
	public void setLeft(StructuralElementInstance left) {
		this.left = left;
	}
	/**
	 * @return right
	 */
	public StructuralElementInstance getRight() {
		return right;
	}
	/**
	 * @param right right
	 */
	public void setRight(StructuralElementInstance right) {
		this.right = right;
	} 
}
