/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.resources;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import de.dlr.sc.virsat.project.resources.IResourceMigratorQuestion;

/**
 * Asks the user if he wants to migrate to a newer DVLM version
 * @author muel_s8
 *
 */
public class DVLMDataSetMigrationQuestion implements IResourceMigratorQuestion {
	
	private boolean shallMigrate = false;
	
	@Override
	public boolean questionMigration(IProject migrationProject) {
		shallMigrate = false;
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				DVLMDataSetMigrationQuestion.this.shallMigrate = MessageDialog.openQuestion(null, "Data migration necessary", "A migration of your project is needed: " + migrationProject.getName() + " Do you want to proceed?");
			}
		});		

		return shallMigrate;
	}		
}
