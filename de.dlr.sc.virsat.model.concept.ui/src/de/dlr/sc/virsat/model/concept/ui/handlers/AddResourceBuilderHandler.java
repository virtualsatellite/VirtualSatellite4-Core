/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.ui.handlers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.model.concept.Activator;
import de.dlr.sc.virsat.model.concept.ui.internal.ConceptActivator;

import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * This handler enables and disables resource generator
 * @author bell_er
 */
public class AddResourceBuilderHandler extends AbstractHandler {

	
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
		boolean oldValue = HandlerUtil.toggleCommandState(command);
		// get workbench window
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		// set selection service
		ISelectionService service = window.getSelectionService();
		// set structured selection
		IStructuredSelection structured = (IStructuredSelection) service.getSelection();
		// check if it is an IFile
		if (structured.getFirstElement() instanceof IFile) {
			// get the selected file
			IFile file = (IFile) structured.getFirstElement();
			if (!oldValue) {
				addResourceBuilder(file);
			} else {
				removeResourceBuilder(file);
			}

		}
		return null;
	}
	/**
	 * Adds the resource access builder
	 * @param file the project file
	 */
	private void addResourceBuilder(IFile file) {
		boolean commandAdded = false;
		try {
			List<String> newLines = new ArrayList<>();
			for (String line : Files.readAllLines(Paths.get(file.getLocationURI()), StandardCharsets.UTF_8)) {
				if (line.contains("<buildCommand>") && !commandAdded) {
					newLines.add(line);
					newLines.add("<name>de.dlr.sc.virsat.model.concept.resourceAccessBuilder</name>");
					commandAdded = true;
				} else {
					newLines.add(line);
				}
			}
			
			Files.write(Paths.get(file.getLocationURI()), newLines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			ConceptActivator.getInstance().getLog().log(
					new Status(Status.ERROR, Activator.getPluginId(), Status.OK, "ResourceAccessBuilder: Cannot add the builder", null));

		}
	}
	/**
	 * Removes the resource access builder
	 * @param file the project file
	 */
	private void removeResourceBuilder(IFile file) {
		try {
			List<String> newLines = new ArrayList<>();
			
			for (String line : Files.readAllLines(Paths.get(file.getLocationURI()), StandardCharsets.UTF_8)) {
				if (!line.contains("<name>de.dlr.sc.virsat.model.concept.resourceAccessBuilder</name>")) {
					newLines.add(line);
				}
			}
			Files.write(Paths.get(file.getLocationURI()), newLines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			ConceptActivator.getInstance().getLog().log(
					new Status(Status.ERROR, Activator.getPluginId(), Status.OK, "ResourceAccessBuilder: Cannot remove the builder", null));

		}
	}


}
