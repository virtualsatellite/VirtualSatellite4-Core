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

import java.util.Set;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 * Interface allowing to migrate a concept to a newer version.
 * @author muel_s8
 *
 */

public interface IMigrator {
	
	String EXTENSION_POINT_ID = "de.dlr.sc.virsat.model.edit.ConceptMigrator";
	
	/**
	 * Returns new dependencies of the new concept version. These have to be activated
	 * before migration can be done
	 * @param concept the concept to be migrated
	 * @param previousMigrator the previously executed migrator, can be null if none was executed before
	 * @return a list of new required concept names

	 */
	Set<String> getNewDependencies(Concept concept, IMigrator previousMigrator);
	
	/**
	 * Migrates the concept to the internally implemented version
	 * @param concept the concept to be migrated
	 * @param previousMigrator the previously executed migrator
	 */
	void migrate(Concept concept, IMigrator previousMigrator);
	
	/**
	 * Sets the resource of the corresponding concept resource of
	 * the version of the migrator
	 * @param resource the resource
	 */
	void setResource(String resource);
	
	/**
	 * Gets the resource of the corresponding concept resource of
	 * the version of the migrator
	 * @return the resource
	 */
	String getResource();
}
