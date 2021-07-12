/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.qudv;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.IBeanName;
import de.dlr.sc.virsat.model.concept.types.IBeanUuid;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.json.IUuidAdapter;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlRootElement
@ApiModel(description = "Model class for system of quantites.")
@XmlType(propOrder = {"systemOfQuantites", "name"})
public class BeanSystemOfQuantities implements IBeanUuid, IBeanName {

	private SystemOfQuantities systemOfQuantities;
	
	public BeanSystemOfQuantities() { }
	
	public BeanSystemOfQuantities(SystemOfQuantities systemOfQuantities) {
		this.systemOfQuantities = systemOfQuantities;
	}
	
	@ApiModelProperty(name = "uuid", required = true,
			value = "Unique identifier for a bean",
			example = "b168b0df-84b6-4b7f-bede-69298b215f40")
	@XmlElement(name = "uuid")
	@XmlJavaTypeAdapter(IUuidAdapter.class)
	public SystemOfQuantities getSystemOfQuantites() {
		return systemOfQuantities;
	}

	public void setSystemOfQuantites(SystemOfQuantities systemOfQuantites) {
		this.systemOfQuantities = systemOfQuantites;
	}
	
	@ApiModelProperty(required = true)
	@XmlElement(nillable = true)
	@Override
	public String getName() {
		return systemOfQuantities.getName();
	}

	@Override
	public void setName(String name) {
		systemOfQuantities.setName(name);
	}

	@Override
	public Command setName(EditingDomain ed, String name) {
		return SetCommand.create(ed, systemOfQuantities, GeneralPackage.Literals.INAME__NAME, name);
	}

	@ApiModelProperty(hidden = true)
	@Override
	public String getUuid() {
		return systemOfQuantities.getUuid().toString();
	}

}
