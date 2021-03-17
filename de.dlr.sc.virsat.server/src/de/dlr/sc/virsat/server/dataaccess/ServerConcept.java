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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;

// TODO: swagger doc
@XmlRootElement
@XmlType(name = ServerConcept.NAME)
@XmlAccessorType(XmlAccessType.NONE)
public class ServerConcept {

	public static final String NAME = "Concept";
	
	private Concept concept;
	
	public ServerConcept() { }
	
	public ServerConcept(Concept concept) {
		this.concept = concept;
	}
	
	/**
	 * Returns a list of full qualified names for an input list
	 * @param input list containing elements with full qualified names
	 * @return list of full qualified names
	 */
	private List<String> getFqns(List<? extends IQualifiedName> input) {
		List<String> fqns = new ArrayList<String>();
		
		for (IQualifiedName element : input) {
			fqns.add(element.getFullQualifiedName());
		}
		
		return fqns;
	}
	
	@XmlElement
	public List<String> getCategories() {
		return getFqns(concept.getCategories());
	}
	
	@XmlElement
	public List<String> getNonAbstractCategories() {
		return getFqns(concept.getNonAbstractCategories());
	}
	
	@XmlElement
	public List<String> getStructuralElements() {
		return getFqns(concept.getStructuralElements());
	}
	
	@XmlElement
	public String getVersion() {
		return concept.getVersion();
	}
	
	@XmlElement
	public boolean getIsBeta() {
		return concept.isBeta();
	}
	
	@XmlElement
	public String getDescription() {
		return concept.getDescription();
	}
	
	@XmlElement
	public String getFullQualifiedName() {
		return concept.getFullQualifiedName();
	}
}
