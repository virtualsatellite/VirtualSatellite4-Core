/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.roles;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.IBeanName;
import de.dlr.sc.virsat.model.concept.types.IBeanUuid;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.json.IUuidAdapter;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import io.swagger.v3.oas.annotations.media.Schema;



@XmlRootElement
//Ensure that the discipline (by uuid) gets unmarshalled first
@XmlType(propOrder = {"discipline", "name", "users"})
@XmlAccessorType(XmlAccessType.NONE)
@Schema(description = "A discipline with an assigned user or multiple users that have rights over assigned elements. Note: This discipline may work with multiple users.")
public class BeanDiscipline implements IBeanName, IBeanUuid {

	private Discipline discipline;
	
	public BeanDiscipline() { }
	
	public BeanDiscipline(Discipline discipline) {
		this.discipline = discipline;
		
	}
	
	@Override
	public String getUuid() {
		return discipline.getUuid().toString();
	}
	
	@XmlJavaTypeAdapter(IUuidAdapter.class)
	@Schema(name = "uuid", required = true,
			description = "Unique identifier for a bean",
			example = "b168b0df-84b6-4b7f-bede-69298b215f40")
	@XmlElement(name = "uuid")
	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	
	public Discipline getDiscipline() {
		return discipline;
	}
	
	@Schema(description = "Name of the discipline", required = true)
	@XmlElement(nillable = true)
	@Override
	public String getName() {
		return discipline.getName();
	}
	
	@Override
	public void setName(String name) {
		discipline.setName(name);
	}
	
	@Override
	public Command setName(EditingDomain ed, String name) {
		return SetCommand.create(ed, discipline, GeneralPackage.Literals.INAME__NAME, name);
	}
	
	@Schema(description = "Name of the user or multiple users assigned to the discipline", required = true)
	@XmlElement(nillable = true)
	public EList<String> getUsers() {
		return discipline.getUsers();
	}
	
	public void addUser(String user) {
		discipline.getUsers().add(user);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BeanDiscipline) {
			BeanDiscipline beanDiscipline = (BeanDiscipline) obj;
			return discipline.equals(beanDiscipline.getDiscipline());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return discipline.hashCode();
	}
}
