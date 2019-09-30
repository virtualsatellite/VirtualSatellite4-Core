/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.migrator;

import org.eclipse.emf.ecore.util.EcoreUtil;

// *****************************************************************
// * Class Declaration
// *****************************************************************

import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;

// *****************************************************************
// * Import Statements
// *****************************************************************


import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;

/**
 * Auto Generated Class inheriting from Generator Gap Class
 *
 * This class is generated once, do your changes here
 *
 * Concept for modelling Functional Electrical Architecture (FEA)
 *
 */
public class Migrator1v0Test extends AMigrator2v0Test {

	@Test
	public void testMigrator2v0() {
		Migrator2v0 testMigrator2v0 = new Migrator2v0();

		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();

		repository.getActiveConcepts().add(conceptCurrent);

		Concept conceptPrevious = EcoreUtil.copy(conceptCurrent);

		testMigrator2v0.migrate(conceptPrevious, conceptCurrent, conceptNext);
	}

}
