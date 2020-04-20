/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.dataaccess;

import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

public class FlattenedDiscipline {

	private VirSatUuid uuid;
	private String name;
	private String user;
	
	public FlattenedDiscipline() { }

	public FlattenedDiscipline(Discipline discipline) {
		setUuid(discipline.getUuid());
		setName(discipline.getName());
		setUser(discipline.getUser());
	}

	public VirSatUuid getUuid() {
		return uuid;
	}

	public void setUuid(VirSatUuid uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
}
