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
 * Class for ghost shapes which were removed in previous version
 */
public class GhostShapeDelta {
	
	/**
	 * Empty Constructor
	 */
	public GhostShapeDelta() {
	};
	
	/**
	 * Ghost Shape Constructor
	 * @param parentId The parentId where to plug the ghost in
	 * @param shape the shape to be plugged in to the given parentID Shape
	 */
	public GhostShapeDelta(String parentId, Shape shape) {
		super();
		this.parentId = parentId;
		this.shape = shape;
	}

	//CHECKSTYLE:OFF
	public String parentId;
	public Shape shape;
	//CHECKSTYLE:ON
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((shape == null) ? 0 : shape.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		GhostShapeDelta other = (GhostShapeDelta) obj;
		if (parentId == null) {
			if (other.parentId != null) {
				return false;
			}
		} else if (!parentId.equals(other.parentId)) {
			return false;
		}
		if (shape == null) {
			if (other.shape != null) {
				return false;
			}
		} else if (!shape.equals(other.shape)) {
			return false;
		}
		return true;
	}
}
