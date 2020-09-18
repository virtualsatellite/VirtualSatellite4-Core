/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class NotAbstractBeanCategoryAssignment {
	
	private ABeanCategoryAssignment beanCa;
	private String name;
	
	public NotAbstractBeanCategoryAssignment() { }
	
	public NotAbstractBeanCategoryAssignment(String name, ABeanCategoryAssignment ca) {
		this.setName(name);
		this.setBeanCa(ca);
	}

	public ABeanCategoryAssignment getBeanCa() {
		return beanCa;
	}

	@XmlElement(name = "uuid")
	@XmlJavaTypeAdapter(ABeanObjectAdapter.class)
	public void setBeanCa(ABeanCategoryAssignment beanCa) {
		this.beanCa = beanCa;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

}
