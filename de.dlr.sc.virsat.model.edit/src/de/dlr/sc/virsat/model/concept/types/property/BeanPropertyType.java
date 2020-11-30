/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.property;

public enum BeanPropertyType {
	BOOLEAN,
	STRING,
	INT,
	FLOAT,
	ENUM,
	RESOURCE,
	REFERENCE,
	EREFERENCE,
	COMPOSED;
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}

	/**
	 * Get a BeanPropertyType from a String typeName
	 * @param typeName String
	 * @return the BeanPropertyType
	 */
	public static BeanPropertyType fromString(String typeName) {
		for (BeanPropertyType v : BeanPropertyType.values()) {
			if (v.toString().equals(typeName)) {
				return v;
			}
		}
		throw new IllegalArgumentException(typeName);
	}
}
