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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.SetCommand;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

public class FlattenedCategoryAssignment {

	// API read only
	private String fullQualifiedInstanceName;
	private String type;
	private String uuid;

	// API read and write
	private String name;
	private List<FlattenedPropertyInstance> properties; //add+delete on high lvl
	
	public FlattenedCategoryAssignment() { }
	
	/**
	 * Create a FlattenedCategoryAssignment from the ca
	 * @param ca CategoryAssignment
	 */
	public FlattenedCategoryAssignment(CategoryAssignment ca) {
		setFullQualifiedInstanceName(ca.getFullQualifiedInstanceName());
		setName(ca.getName());
		setType(ca.getType().getFullQualifiedName());
		setUuid(ca.getUuid().toString());
		setProperties(resolveProperties(ca.getPropertyInstances()));
	}

	/**
	 * Resolves List<APropertyInstance> to a representative List<String>
	 * @param properties of the ca
	 * @return List<String>
	 */
	private List<FlattenedPropertyInstance> resolveProperties(List<APropertyInstance> properties) {
		List<FlattenedPropertyInstance> flattenedProperties = new ArrayList<FlattenedPropertyInstance>();
		
		for (APropertyInstance property : properties) {
			flattenedProperties.add(new FlattenedPropertyInstance(property));
		}
		
		return flattenedProperties;
	}
	
	/**
	 * Create a command to unflatten the properties of this instance into a existing ca
	 * @param editingDomain
	 * @param ca
	 * @return Command
	 */
	public Command unflatten(VirSatTransactionalEditingDomain editingDomain, CategoryAssignment ca) {
		CompoundCommand updateCaCommand = new CompoundCommand();
		
		Command commandSetName = SetCommand.create(editingDomain, ca, GeneralPackage.Literals.INAME__NAME, getName());
		updateCaCommand.append(commandSetName);

		for (APropertyInstance property: ca.getPropertyInstances()) {
			for (FlattenedPropertyInstance flattenedPropertyInstance : getProperties()) {
				if (property.getUuid().toString().equals(flattenedPropertyInstance.getUuid())) {
					updateCaCommand.append(flattenedPropertyInstance.unflatten(editingDomain, property));
					break;
				}
			}
		}
		
		return updateCaCommand;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullQualifiedInstanceName() {
		return fullQualifiedInstanceName;
	}

	public void setFullQualifiedInstanceName(String fullQualifiedInstanceName) {
		this.fullQualifiedInstanceName = fullQualifiedInstanceName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List<FlattenedPropertyInstance> getProperties() {
		return properties;
	}

	public void setProperties(List<FlattenedPropertyInstance> properties) {
		this.properties = properties;
	}
	
}
