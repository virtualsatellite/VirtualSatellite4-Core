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

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * Representation of a model CA that contains only a list of the flattened property instances
 * This properties can be read and written into, but changes to the list (add or delete)
 * are not handled on this low level representation
 */
public class FlattenedCategoryAssignmentWithProperties extends AFlattenedCategoryAssignment {

	// API read and write
	private List<FlattenedPropertyInstance> properties; //add+delete on high lvl
	
	public FlattenedCategoryAssignmentWithProperties() { }
	
	/**
	 * Create a FlattenedCategoryAssignment from the ca
	 * @param ca CategoryAssignment
	 */
	public FlattenedCategoryAssignmentWithProperties(CategoryAssignment ca) {
		super(ca);
		setProperties(flattenProperties(ca.getPropertyInstances()));
	}

	/**
	 * Flattens List<APropertyInstance> to a representative List<FlattenedPropertyInstance>
	 * @param properties of the ca
	 * @return List<FlattenedPropertyInstance>
	 */
	private List<FlattenedPropertyInstance> flattenProperties(List<APropertyInstance> properties) {
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
	@Override
	public Command unflatten(VirSatTransactionalEditingDomain editingDomain, CategoryAssignment ca) {
		CompoundCommand updateCaCommand = (CompoundCommand) super.unflatten(editingDomain, ca);
		
		CategoryAssignmentHelper helper = new CategoryAssignmentHelper(ca);
		
		for (FlattenedPropertyInstance flattenedPropertyInstance : getProperties()) {
			APropertyInstance propertyInstance = helper.tryGetPropertyInstance(flattenedPropertyInstance.getTypeFullQualifiedInstanceName());
			if (propertyInstance != null) {
				updateCaCommand.append(flattenedPropertyInstance.unflatten(editingDomain, propertyInstance));
			}
		}
		
		return (Command) updateCaCommand;
	}

	public List<FlattenedPropertyInstance> getProperties() {
		return properties;
	}

	public void setProperties(List<FlattenedPropertyInstance> properties) {
		this.properties = properties;
	}
	
}
