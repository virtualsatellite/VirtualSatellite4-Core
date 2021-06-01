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

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@ApiModel(description = "A concept that can be activated on the current repository. "
		+ "It is identified by it's full qualified name and contains it's categories and structural elements. "
		+ "Which can be instantiated as CategoryAssignments and StructurelElementInstances.")
public class ServerConcept {
	
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
	
	@ApiModelProperty(
			value = "List of available categories",
			accessMode = AccessMode.READ_ONLY)
	@XmlElement
	public List<String> getCategories() {
		return getFqns(concept.getCategories());
	}
	
	@ApiModelProperty(
			value = "List of available categories that are not abstract (can be instantiated)",
			accessMode = AccessMode.READ_ONLY)
	@XmlElement
	public List<String> getNonAbstractCategories() {
		return getFqns(concept.getNonAbstractCategories());
	}
	
	@ApiModelProperty(
			value = "List of available structural elements",
			accessMode = AccessMode.READ_ONLY)
	@XmlElement
	public List<String> getStructuralElements() {
		return getFqns(concept.getStructuralElements());
	}
	
	@ApiModelProperty(
			value = "Version of the concept",
			accessMode = AccessMode.READ_ONLY)
	@XmlElement
	public String getVersion() {
		return concept.getVersion();
	}
	
	@ApiModelProperty(
			value = "If the concept is still in a beta version",
			accessMode = AccessMode.READ_ONLY)
	@XmlElement
	public boolean getIsBeta() {
		return concept.isBeta();
	}
	
	@ApiModelProperty(
			value = "Description of the concept",
			accessMode = AccessMode.READ_ONLY)
	@XmlElement
	public String getDescription() {
		return concept.getDescription();
	}
	
	@ApiModelProperty(
			value = "Full qualified name of the concept",
			accessMode = AccessMode.READ_ONLY)
	@XmlElement
	public String getFullQualifiedName() {
		return concept.getFullQualifiedName();
	}
}
