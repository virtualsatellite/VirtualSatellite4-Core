/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.project.resources;

import org.eclipse.core.resources.IProject;

/**
 * Interface for for Mi9grator Questions
 * @author muel_s8
 *
 */

public interface IResourceMigratorQuestion {
	
	String EXTENSION_POINT_ID = "de.dlr.sc.virsat.project.resourceMigrator";

	/**
	 * Implement this interface to answer the question if the DataSet should be migarted or not.
	 * Usually this method referes to a MessageDialog 
	 * @param projectToMigrate The project to be migrated
	 * @return true in case a migration of the data set should be done
	 */
	boolean questionMigration(IProject projectToMigrate);
}
