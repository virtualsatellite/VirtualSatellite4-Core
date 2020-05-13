/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.validator.core;

import org.eclipse.core.resources.IMarker;

import de.dlr.sc.virsat.model.concept.migrator.ConceptMigrator;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.validator.IRepositoryValidator;

/**
 * Checks if the installed concepts are up to date or if a newer version is available.
 * @author muel_s8
 *
 */

public class DvlmLatestConceptValidator extends ADvlmCoreValidator implements IRepositoryValidator {

	@Override
	public boolean validate(Repository repo) {
		
		boolean allUpToDate = true;
		
		for (Concept concept : repo.getActiveConcepts()) {
			ConceptMigrator migrator = new ConceptMigrator(concept);
			if (migrator.needsMigration()) {
				vvmHelper.createDVLMValidationMarker(IMarker.SEVERITY_WARNING, "The concept \'" + concept.getFullQualifiedName() + "\' is outdated and requires an update.", concept);
				allUpToDate = false;
			}
		}
		
		return allUpToDate;
	}
	
}
