/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.ps.migrator;

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
 * Product Structure Concept
 * 
 */
public class Migrator1v1Test extends AMigrator1v1Test {		
	
	@Test
	public void testMigrator1v1() {
		Migrator1v1 testMigrator1v1 = new Migrator1v1();
		
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		repository.getActiveConcepts().add(conceptCurrent);
		
		Concept conceptPrevious = EcoreUtil.copy(conceptCurrent);
		
		testMigrator1v1.migrate(conceptPrevious, conceptCurrent, conceptNext);
	}
	
}
