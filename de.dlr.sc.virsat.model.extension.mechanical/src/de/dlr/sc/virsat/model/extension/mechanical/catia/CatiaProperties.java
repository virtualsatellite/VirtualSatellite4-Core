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

/**
 * Defines the keys to access Catia properties
 *
 */

public class CatiaProperties {
	
	/**
	 * Private constructor
	 */
	private CatiaProperties() {
		
	}
	
	public static final String PARTS = "Parts";
	public static final String PRODUCTS = "Products";
	
	public static final String UUID = "uuid";
	public static final String NAME = "name";
	
	public static final String PART_SHAPE = "shape";
	public static final String PART_STL_PATH = "STL_path";
	public static final String PART_COLOR = "color";
	public static final String PART_RADIUS = "radius";
	public static final String PART_LENGTH_X = "lengthX";
	public static final String PART_LENGTH_Y = "lengthY";
	public static final String PART_LENGTH_Z = "lengthZ";

	public static final String PRODUCT_REFERENCE_NAME = "partName";
	public static final String PRODUCT_ED_UUID = "uuidED";
	
	public static final String PRODUCT_POS_X = "posX";
	public static final String PRODUCT_POS_Y = "posY";
	public static final String PRODUCT_POS_Z = "posZ";
	
	public static final String PRODUCT_ROT_X = "rotX";
	public static final String PRODUCT_ROT_Y = "rotY";
	public static final String PRODUCT_ROT_Z = "rotZ";
	
	public static final String PRODUCT_SHAPE = "shape";
	
	public static final String PRODUCT_CHILDREN = "children";

}
