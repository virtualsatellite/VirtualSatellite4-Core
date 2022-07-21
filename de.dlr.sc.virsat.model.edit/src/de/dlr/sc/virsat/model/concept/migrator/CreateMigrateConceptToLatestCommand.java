/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.migrator;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 * Creates a command for migrating a concept to the latest version by executing
 * all required migrators.
 * @author muel_s8
 *
 */

public class CreateMigrateConceptToLatestCommand {
	
	public static final String COMMAND_LABEL = "Migrate Concept";
	
	/**
	 * Private Constructor
	 */
	private CreateMigrateConceptToLatestCommand() {
		
	}
	
	/**
	 * Creates a recording command to migrate a concept to the latest version
	 * @param concept the concept to migrate
	 * @param editingDomain the editing domain
	 * @param progressMonitor for observing the command execution
	 * @throws CoreException if the creation of migrators fails
	 * @return the migration command
	 */
	public static Command create(Concept concept, TransactionalEditingDomain editingDomain, IProgressMonitor progressMonitor) throws CoreException {
		
		ConceptMigrator cmMigrator = new ConceptMigrator(concept);
		List<IMigrator> migrators = cmMigrator.getSortedMigrators(concept);
			
		// Create the migration command
		RecordingCommand command = new RecordingCommand(editingDomain, COMMAND_LABEL) {
			@Override
			protected void doExecute() {
				ConceptMigrator conceptMigrator = new ConceptMigrator(concept);
				conceptMigrator.migrate(migrators, progressMonitor);
			}
		};
			
		return command;
	}
}
