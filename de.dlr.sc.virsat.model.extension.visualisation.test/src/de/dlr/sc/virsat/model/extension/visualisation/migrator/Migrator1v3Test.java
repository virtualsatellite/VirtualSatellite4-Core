/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.migrator;

// *****************************************************************
// * Import Statements
// *****************************************************************


import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
 * Concept that defines visualisation properties
 * 
 */
public class Migrator1v3Test extends AMigrator1v3Test {		
	
	@Test
	public void testMigrator1v3() {
		Migrator1v3 testMigrator1v3 = new Migrator1v3();
		
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		repository.getActiveConcepts().add(conceptCurrent);
		
		Concept conceptPrevious = EcoreUtil.copy(conceptCurrent);
		
		testMigrator1v3.migrate(conceptPrevious, conceptCurrent, conceptNext);
	}
	
}
