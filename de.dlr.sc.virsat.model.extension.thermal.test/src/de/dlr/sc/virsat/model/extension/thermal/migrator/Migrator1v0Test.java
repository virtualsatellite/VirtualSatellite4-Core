/**
 * This file is part of the VirSat project.
 *
 * Copyright (c) 2008-2016
 * German Aerospace Center (DLR), Simulation and Software Technology, Germany
 * All rights reserved
 *
 */
package de.dlr.sc.virsat.model.extension.thermal.migrator;

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
 * Concept for thermalents, Phd, Masters, Bachelors and research projects
 * 
 */
public class Migrator1v0Test extends AMigrator1v0Test {		
	
	@Test
	public void testMigrator1v0() {
		Migrator1v0 testMigrator1v0 = new Migrator1v0();
		
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		repository.getActiveConcepts().add(conceptMigrateFromRepository);
		
		testMigrator1v0.migrate(conceptMigrateFrom, conceptMigrateFromRepository, conceptMigrateTo);
	}
}
