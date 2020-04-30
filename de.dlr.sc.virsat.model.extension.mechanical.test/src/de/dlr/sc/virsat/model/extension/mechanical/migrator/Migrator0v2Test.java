/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.migrator;

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
 * Concept for mechanical engineering
 * 
 */
public class Migrator0v2Test extends AMigrator0v2Test {		
	
	@Test
	public void testMigrator0v2() {
		Migrator0v2 testMigrator0v2 = new Migrator0v2();
		
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		repository.getActiveConcepts().add(conceptMigrateFromRepository);
		
		
		testMigrator0v2.migrate(conceptMigrateFrom, conceptMigrateFromRepository, conceptMigrateTo);
		
	}
	
}
