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

import java.util.Objects;

/**
 * Class for representing color change on a specific shape
 */
public class ColorDelta {
	
	/**
	 * Creates the ColorDelta Object
	 * @param shapeId the id of the shape which should get a color override. it is usually the UUID in VirSat
	 * @param newColor the new color Value to be applied
	 */
	public ColorDelta(String shapeId, int newColor) {
		this.shapeId = shapeId;
		this.newColor = newColor;
	}

	/** 
	 * Simple constructor. Don't forget to set the shape id and new color information
	 */
	public ColorDelta() {
	}
	
	//CHECKSTYLE:OFF
	public String shapeId;
	public int newColor;
	//CHECKSTYLE:ON
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + newColor;
		result = prime * result + ((shapeId == null) ? 0 : shapeId.hashCode());
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
		ColorDelta other = (ColorDelta) obj;
		if (newColor != other.newColor) {
			return false;
		}
		if (shapeId == null) {
			if (other.shapeId != null) {
				return false;
			}
		} else if (!shapeId.equals(other.shapeId)) {
			return false;
		}
		return true;
	}
	
	public static final String NO_UUID = "No UUID";
	
	@Override
	public String toString() {
		return "UUID: " + Objects.toString(shapeId, NO_UUID) + " - Color: 0x" + Integer.toHexString(newColor).toUpperCase();
	}
}
