/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.structural;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import de.dlr.sc.virsat.model.dvlm.json.IUuidAdapterNoRoleManagement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;


/**
 * Lightweight POJO class that represents a reference to a SEI on the bean level
 * used to avoid deep nested element
 */
@Schema(description = "Reference to a bean SEI that can be used for every concrete SEI."
		+ " Instances of this only contain the fields uuid and name.")
@XmlType(propOrder = { "structuralElementInstance", "name" })
public class BeanStructuralElementInstanceReference {

	private StructuralElementInstance sei;

	/**
	 * Default constructor used by JAXB
	 */
	public BeanStructuralElementInstanceReference() {
	};

	/**
	 * Convenience constructor that directly wraps the passed sei
	 * 
	 * @param sei the structural element instance to be wrapped by this bean
	 */
	public BeanStructuralElementInstanceReference(StructuralElementInstance sei) {
		this.sei = sei;
	}

	public String getName() {
		return sei.getName();
	}

	@XmlElement(nillable = true)
	@Schema(
		requiredMode = RequiredMode.REQUIRED,
		accessMode = AccessMode.READ_ONLY)
	public void setName(String seiName) {
		// As this bean has no role management, we can't set the name here
	}

	public StructuralElementInstance getStructuralElementInstance() {
		return sei;
	}

	@XmlElement(name = "uuid")
	@Schema(name = "uuid", required = true,
		description = "Unique identifier for a bean",
		example = "b168b0df-84b6-4b7f-bede-69298b215f40")
	@XmlJavaTypeAdapter(IUuidAdapterNoRoleManagement.class)
	public void setStructuralElementInstance(StructuralElementInstance sei) {
		this.sei = sei;
	}

}
