/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.resource.command;

import java.util.Collection;
import java.util.HashSet;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import de.dlr.sc.virsat.model.dvlm.util.command.IVirSatRecordableCommand;

/**
 * Command that removes resources from the resource set and correctly recreates them
 * when performing an undo
 * @author fisc_ph
 *
 */
public class RemoveResourceCommand extends AbstractCommand implements IVirSatRecordableCommand {

	private Collection<Resource> resources = new HashSet<>();
	private ResourceSet rs;
	
	/**
	 * Private command initializing the resource set
	 * @param owner the resource set that owns the resources to be removed
	 */
	private RemoveResourceCommand(ResourceSet owner) {
		rs = owner;
	}
	
	/**
	 * Constructor for removing one resource
	 * @param owner the resource set that owns the resource to be removed
	 * @param resource the resource to be removed
	 */
	public RemoveResourceCommand(ResourceSet owner, Resource resource) {
		this(owner);
		resources.add(resource);
	}
	
	/**
	 * Constructor for removing a collection of resources
	 * @param owner the resource set that owns the resource to be removed
	 * @param resources the resources to be removed
	 */
	public RemoveResourceCommand(ResourceSet owner, Collection<Resource> resources) {
		this(owner);
		this.resources = resources;
	}

	@Override
	protected boolean prepare() {
		// nothing to be prepared in particular
		return true;
	}
	
	@Override
	public void execute() {
		rs.getResources().removeAll(resources);
	}

	@Override
	public void undo() {
		rs.getResources().addAll(resources);
	}

	@Override
	public void redo() {
		execute();		
	}
}
