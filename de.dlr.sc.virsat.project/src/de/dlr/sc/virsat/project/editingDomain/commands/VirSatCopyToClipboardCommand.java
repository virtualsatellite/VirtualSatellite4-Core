/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain.commands;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;

/**
 * Command to  add  objects to the Transactional Editing Domain
 * ClipBoard
 * @author fisc_ph
 *
 */
public class VirSatCopyToClipboardCommand  extends AbstractCommand {

	/**
	 * Factory Method to create the command
	 * @param ed The editing domain to which to clip the objects
	 * @param collection a collection of objects to be added to the ClipBoard
	 * @return The COmmand that will ad the objects to the ClipBoard
	 */
	public static Command create(AdapterFactoryEditingDomain ed, Collection<?> collection) {
		if (VirSatClipboardCommandHelper.isValidCollection(collection)) {
			return new VirSatCopyToClipboardCommand(ed, collection);
		} else {
			return UnexecutableCommand.INSTANCE;
		}
	}

	private AdapterFactoryEditingDomain ed;
	private Collection<?> collection;

	/**
	 * Constructor for the Copy to ClipBoard command
	 * @param ed The Editing Domain to which to clip the objects
	 * @param collection The collection of objects to be clipped
	 */
	private VirSatCopyToClipboardCommand(AdapterFactoryEditingDomain ed, Collection<?> collection) {
		this.ed = ed;
		this.collection = new ArrayList<>(collection);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}
	
	@Override
	public void execute() {
		VirSatEditingDomainClipBoard.INSTANCE.copyClipboard(ed, collection);
	}

	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		VirSatEditingDomainClipBoard.INSTANCE.flushClipboard(ed);
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}
}
