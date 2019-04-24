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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.build.test.ABuilderTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;


/**
 * checks that a concept does not have a default quantity kind which is not defined in Unit Management
 *
 */
 
public class DvlmRepositoryDefaultQuantityKindValidatorTest extends ABuilderTest {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		UnitManagement unitManagement = UnitsFactory.eINSTANCE.createUnitManagement();
		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		unitManagement.setSystemOfUnit(sou);
		repo.setUnitManagement(unitManagement);
	}
	
	@Test
	public void testRepositoryDefaultQuantityKindValidator() throws CoreException, IOException {
		DvlmRepositoryDefaultQuantityKindValidator validator = new DvlmRepositoryDefaultQuantityKindValidator();
		assertTrue("repository does not contain quantity kinds", validator.validate(repo));

		AQudvTypeProperty qudvProperty = (AQudvTypeProperty) repo.getActiveConcepts().get(0).getCategories().get(0).getAllProperties().get(0);
		qudvProperty.setQuantityKindName("Length");
		assertTrue("repository contains proper quantity kind", validator.validate(repo));
		
		qudvProperty.setQuantityKindName("InvalidQK");
		assertFalse("validator detects invalid quantity kind in repository", validator.validate(repo));
	}
}
