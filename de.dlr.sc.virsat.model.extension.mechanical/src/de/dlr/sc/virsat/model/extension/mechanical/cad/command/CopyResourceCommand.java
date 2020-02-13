/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.cad.command;

import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.AbstractCommand;

import de.dlr.sc.virsat.model.extension.mechanical.Activator;

/**
 * 
 * EMF command to copy a resource
 *
 */
public class CopyResourceCommand extends AbstractCommand {

	protected final Path sourceFile;
	protected final Path targetLocation;
	protected final CopyOption options;

	/**
	 * Create a command to copy a resource
	 * 
	 * @param source
	 *            the file to copy
	 * @param target
	 *            the target location for the file to be copied
	 * @param options
	 *            the options for the copy operations
	 */
	public CopyResourceCommand(Path source, Path target, CopyOption options) {
		this.sourceFile = source;
		this.targetLocation = target;
		this.options = options;
	}

	/**
	 * Create a command to copy a resource
	 * 
	 * @param source
	 *            the file to copy
	 * @param target
	 *            the target location for the file to be copied
	 */
	public CopyResourceCommand(Path source, Path target) {
		this.sourceFile = source;
		this.targetLocation = target;
		this.options = StandardCopyOption.REPLACE_EXISTING;
	}
	
	@Override
	public boolean canExecute() {
		return sourceFile != null && sourceFile.toFile().exists();
	}

	@Override
	public void execute() {
		try {
			Files.copy(sourceFile, targetLocation, options);
		} catch (Exception e) {
			Activator.getDefault().getLog()
					.log(new Status(Status.INFO, "MechanicalUiPlugin", "ResourceCopyCommand: Could not copy files!", e));
		}

	}

	@Override
	public void undo() {
		if (targetLocation.toFile().exists()) {
			try {
				Files.delete(targetLocation);
			} catch (Exception e) {
				Activator.getDefault().getLog()
					.log(new Status(Status.INFO, "MechanicalUiPlugin", "ResourceCopyCommand: Could not undo copying files!", e));
			}
		}
	}
	
	@Override
	public void redo() {
		execute();
	}

}
