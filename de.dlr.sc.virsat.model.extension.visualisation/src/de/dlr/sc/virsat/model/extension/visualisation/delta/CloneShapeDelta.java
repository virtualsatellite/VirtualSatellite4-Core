/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.delta;

import de.dlr.sc.virsat.model.extension.visualisation.shape.Shape;

/**
 * Class for representing a shape delta for a shape with modified geometry
 */
public class CloneShapeDelta {
	
	/**
	 * Constructs the Delta with a given shape
	 * @param shape The shape that should be displayed as clone of an existing one
	 */
	public CloneShapeDelta(Shape shape) {
		this.shape = shape;
	}

	/**
	 * Simple Constructor
	 */
	public CloneShapeDelta() {
	}
		
	//CHECKSTYLE:OFF
	public Shape shape;
	//CHECSTYLE:ON

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((shape == null) ? 0 : shape.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CloneShapeDelta other = (CloneShapeDelta) obj;
		if (shape == null) {
			if (other.shape != null)
				return false;
		} else if (!shape.equals(other.shape))
			return false;
		return true;
	}
}
