/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.catia;

import com.github.cliftonlabs.json_simple.JsonKey;

/**
 * Defines the keys to access Catia properties
 *
 */

public enum CatiaProperties implements JsonKey {
	PARTS("Parts"),
	PRODUCTS("Products"),
	
	UUID("uuid"),
	NAME("name"),
	
	PART_SHAPE("shape"),
	PART_STL_PATH("STL_path"),
	PART_COLOR("color"),
	PART_RADIUS("radius"),
	PART_LENGTH_X("lengthX"),
	PART_LENGTH_Y("lengthY"),
	PART_LENGTH_Z("lengthZ"),

	PRODUCT_REFERENCE_NAME("partName"),
	PRODUCT_ED_UUID("uuidED"),
	
	PRODUCT_POS_X("posX"),
	PRODUCT_POS_Y("posY"),
	PRODUCT_POS_Z("posZ"),
	
	PRODUCT_ROT_X("rotX"),
	PRODUCT_ROT_Y("rotY"),
	PRODUCT_ROT_Z("rotZ"),
	
	PRODUCT_SHAPE("shape"),
	PRODUCT_STL_PATH("STL_path"),
	
	PRODUCT_CHILDREN("children");
	
	private final String property;
	
	/**
	 * Standard constructor
	 * @param property the property
	 */
	CatiaProperties(String property) {
		this.property = property;
	}
	
	@Override
	public String getKey() {
		return property;
	}

	@Override
	public Object getValue() {
		return null;
	}
}
