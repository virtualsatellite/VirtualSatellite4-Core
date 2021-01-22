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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import de.dlr.sc.virsat.model.dvlm.json.IUuidAdapter;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * Lightweight POJO class that represents a reference to a SEI on the bean level
 * used to avoid deep nested element
 */
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
	public void setName(String seiName) {
		sei.setName(seiName);
	}

	public StructuralElementInstance getStructuralElementInstance() {
		return sei;
	}

	@XmlElement(name = "uuid")
	@XmlJavaTypeAdapter(IUuidAdapter.class)
	public void setStructuralElementInstance(StructuralElementInstance sei) {
		this.sei = sei;
	}

}
