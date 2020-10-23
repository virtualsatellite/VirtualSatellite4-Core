/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.json.IUuidAdapter;

/**
 * Core functionality for a Concept Bean and abstract implementation to the interface
 * @author fisc_ph
 * 
 *@param <CP_TYPE>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public abstract class ABeanObject<CP_TYPE extends ATypeInstance> implements IBeanObject<CP_TYPE> {

	protected CP_TYPE ti;
	
	/**
	 * Constructor that allows to set the type instance
	 * @param ti the Type instance to be used
	 */
	@SuppressWarnings("unchecked")
	public ABeanObject(ATypeInstance ti) {
		this.ti = (CP_TYPE) ti;
	}
	
	/**
	 * Standard constructor without setting the type instance
	 */
	public ABeanObject() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void setATypeInstance(ATypeInstance ti) {
		this.ti = (CP_TYPE) ti;
	}
	
	@XmlElement(name = "uuid")
	@XmlJavaTypeAdapter(IUuidAdapter.class)
	@Override
	public ATypeInstance getATypeInstance() {
		return ti;
	}
	
	@Override
	public void setTypeInstance(CP_TYPE ti) {
		this.ti = ti;
	}

	@Override
	public CP_TYPE getTypeInstance() {
		return ti;
	}

	@Override
	public String getUuid() {
		return ti.getUuid().toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ABeanObject) {
			ABeanObject<?> rhsBeanObject = (ABeanObject<?>) obj;
			return ti.equals(rhsBeanObject.getTypeInstance());
		} else if (obj instanceof CategoryAssignment) {
			return ti.equals(obj);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return ti.hashCode();
	}
}
