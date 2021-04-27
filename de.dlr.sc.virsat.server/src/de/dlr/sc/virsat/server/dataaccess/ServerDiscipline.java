/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.dataaccess;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@XmlRootElement
@XmlType(name = "Discipline")
@XmlAccessorType(XmlAccessType.NONE)
@ApiModel(value = "Discipline",
	description = "A discipline with an assigned user that has rights over assigned elements.")
public class ServerDiscipline {
	
	private Discipline discipline;
	
	public ServerDiscipline() { }
	
	public ServerDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	
	@ApiModelProperty(
			value = "Unique identifier",
			accessMode = AccessMode.READ_ONLY)
	@XmlElement
	public String getUuid() {
		return discipline.getUuid().toString();
	}
	
	@ApiModelProperty(value = "Name of the discipline")
	@XmlElement
	public String getName() {
		return discipline.getName();
	}
	
	public void setName(String name) {
		discipline.setName(null);
	}
	
	@ApiModelProperty(value = "Name of the user assigned to the discipline")
	@XmlElement
	public String getUser() {
		return discipline.getUser();
	}
	
	public void setUser(String user) {
		discipline.setUser(user);
	}
}
