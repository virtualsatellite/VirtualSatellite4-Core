/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.migrator;

// *****************************************************************
// * Import Statements
// *****************************************************************


import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;

// *****************************************************************
// * Class Declaration
// *****************************************************************

import org.junit.Test;

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * Extension for Requirement Specification
 * 
 */
public class Migrator0v5Test extends AMigrator0v5Test {		
	
	@Test
	public void testMigrator0v5() {
		Migrator0v5 testMigrator0v5 = new Migrator0v5();
		
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		repository.getActiveConcepts().add(conceptMigrateFromRepository);
		
		testMigrator0v5.migrate(conceptMigrateFrom, conceptMigrateFromRepository, conceptMigrateTo);
		
	}
	
}
