/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.types.impl;

import java.util.UUID;

/**
 * Implements a UUID class for Virtual Satellite Data Model.
 * The Original UUID has difficulties with Serialization and needs manual
 * code changes in the generated EMF code.
 * @author fisc_ph
 *
 */
public class VirSatUuid {
	
	public static final String UUID_NA = "VIRSAT-UUID-NO-UUID-YET-SET";
	private UUID uuid;
	
	/**
	 * COnstructor generating a random UUID
	 */
	public VirSatUuid() {
		this.uuid = UUID.randomUUID();
	}
	
	/**
	 * Constructor to generate a UUID with a given String
	 * @param uuidString the UUID as String
	 */
	public VirSatUuid(String uuidString) {
		if ((uuidString != null) && (!uuidString.isEmpty())) {
			this.uuid = UUID.fromString(uuidString);
		} else {
			uuid = UUID.randomUUID();
		}
	}
	
	@Override
	public String toString() {
		return uuid.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj instanceof VirSatUuid) {
			return this.uuid.equals(((VirSatUuid) obj).uuid);
		} 
		return false;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
