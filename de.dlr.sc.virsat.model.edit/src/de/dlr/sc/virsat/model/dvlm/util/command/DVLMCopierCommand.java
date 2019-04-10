/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util.command;

import java.util.Collection;

import org.eclipse.emf.common.command.AbstractCommand;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCopier;

/**
 * Command that wraps our DVLM Copier that takes care of UUIDs
 * @author fisc_ph
 *
 */
public class DVLMCopierCommand extends AbstractCommand {

	private Collection<?> collection;
	private Collection<?> copiedCollection;
	
	/**
	 * Constructor with the collection to be copied by the command
	 * @param collection Collection of EObjects to be copied
	 */
	public DVLMCopierCommand(Collection<?> collection) {
		this.collection = collection;
	}
	
	@Override
	public void execute() {
		DVLMCopier copier = new DVLMCopier();
		
		// If we want to copy category assignments disable copying of super TIs
		// If on the other hand we want to copy structural element instances (which may contain category assignments)
		// then we copy the super tis since we also copy the inheritance links for the structural element instances
		boolean copySuperTis = collection.stream().filter(element -> element instanceof CategoryAssignment).count() == 0;
		copier.setCopySuperTis(copySuperTis);
		
		copiedCollection = copier.copyAll(collection);
		copier.copyReferences();
	}

	@Override
	public void redo() {
		execute();		
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	protected boolean prepare() {
	    return true;
	}
	
	@Override
	public Collection<?> getResult() {
		return copiedCollection;
	}
}
