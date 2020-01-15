/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.comparison;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptTestCase;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestMassParameters;
import de.dlr.sc.virsat.model.extension.visualisation.delta.ColorDelta;
import de.dlr.sc.virsat.model.extension.visualisation.delta.VisualisationDeltaModel;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

/**
 * Test cases to verify the comparison algorithm
 * @author desh_me
 *
 */
public class ModelPropertyColorMapTest extends AConceptTestCase {	
	
	private static final int TEST_MASS_BAT = 80;
	private static final int TEST_MASS_OBC = 50;
	
	private Concept conceptPs;
	private Concept conceptVis;
	private Concept conceptTest;
	
	private Repository repo;
		
	private ElementConfiguration ecSc;
	private ElementConfiguration ecBat;
	private ElementConfiguration ecObc;
	
	private Visualisation visBat;
	private Visualisation visObc;
	
	private TestMassParameters massParamBat;
	private TestMassParameters massParamObc;

	private String propertyFqn;
	
	@Before
	public void setUp() throws Exception {
		conceptPs = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId());
		conceptVis = loadConceptFromPlugin(de.dlr.sc.virsat.model.extension.visualisation.Activator.getPluginId());
		conceptTest = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.tests");
		
		repo = DVLMFactory.eINSTANCE.createRepository();
		repo.getActiveConcepts().add(conceptPs);
		repo.getActiveConcepts().add(conceptVis);
		repo.getActiveConcepts().add(conceptTest);

		UnitManagement unitManagement = UnitsFactory.eINSTANCE.createUnitManagement();
		SystemOfUnits sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		unitManagement.setSystemOfUnit(sou);
		repo.setUnitManagement(unitManagement);
		
		ecSc = new ElementConfiguration(conceptPs);
		ecBat = new ElementConfiguration(conceptPs);
		ecObc = new ElementConfiguration(conceptPs);
		
        
		repo.getRootEntities().add(ecSc.getStructuralElementInstance());

		ecSc.add(ecBat);
		ecSc.add(ecObc);
		

		visBat = new Visualisation(conceptVis);
		visObc = new Visualisation(conceptVis);
		
		ecBat.add(visBat);
		ecObc.add(visObc);
		
		
		massParamBat = new TestMassParameters(conceptTest);
		massParamObc = new TestMassParameters(conceptTest);
		
		massParamBat.getMass().setDefaultValue(TEST_MASS_BAT);
		massParamObc.getMass().setDefaultValue(TEST_MASS_OBC);
		
		ecBat.add(massParamBat);
		ecObc.add(massParamObc);

		propertyFqn = "de.dlr.sc.virsat.model.extension.tests.TestMassParameters.mass";

		TestCategoryAllProperty testCaBat = new TestCategoryAllProperty(conceptTest);
		TestCategoryAllProperty testCaObc = new TestCategoryAllProperty(conceptTest);
		
		ecBat.add(testCaBat);
		ecObc.add(testCaObc);
		
		testCaBat.setTestFloat(TEST_MASS_BAT);
		testCaObc.setTestFloat(TEST_MASS_OBC);		
	}

	@Test
	public void testColorMap() {
		final int COLOR_DELTA_SIZE = 2;
		final String propertyFqn = TestCategoryAllProperty.FULL_QUALIFIED_CATEGORY_NAME + "." + TestCategoryAllProperty.PROPERTY_TESTFLOAT;
		
		VisualisationDeltaModel vdm = new ModelPropertyColorMap(new NullProgressMonitor()).colorMap(repo, propertyFqn);
		
		assertEquals("There are not clone shapes", 0, vdm.cloneShapeDeltas.size());
		assertEquals("There are not ghost shapes", 0, vdm.ghostShapeDeltas.size());
		
		assertEquals("There are some delta colors", COLOR_DELTA_SIZE, vdm.colorDeltas.size());
		
		final ColorDelta EXPECTED_CD_1 = new ColorDelta(ecBat.getUuid(), ACompareModelAlgorithm.COLOR_RED);
		final ColorDelta EXPECTED_CD_2 = new ColorDelta(ecObc.getUuid(), ACompareModelAlgorithm.COLOR_BLUE);
		
		assertThat("Make sure all ColorDeltas are contained in the list of processed deltas", vdm.colorDeltas.values(), hasItems(EXPECTED_CD_1, EXPECTED_CD_2));	
	}
	
	@Test
	public void testPropertyNotAdded() {
		
		// In this test case we test element which does not contain specified property 
		
		final int COLOR_DELTA_SIZE = 3;
		
		//Add a new element to the repository with out a mass parameter
		//it should be displayed grey
		//other elements should get color between blue to red based on their property value
		ElementConfiguration ecRw = new ElementConfiguration(conceptPs);
		ecSc.add(ecRw);
		
		Visualisation visRw = new Visualisation(conceptVis);
		ecRw.add(visRw);
		
		VisualisationDeltaModel vdm = new ModelPropertyColorMap(new NullProgressMonitor()).colorMap(repo, propertyFqn);
		
		assertEquals("There are not clone shapes", 0, vdm.cloneShapeDeltas.size());
		assertEquals("There are not ghost shapes", 0, vdm.ghostShapeDeltas.size());
		
		assertEquals("There are some delta colors", COLOR_DELTA_SIZE, vdm.colorDeltas.size());
		
		final ColorDelta EXPECTED_CD_1 = new ColorDelta(ecBat.getUuid(), ACompareModelAlgorithm.COLOR_RED);
		final ColorDelta EXPECTED_CD_2 = new ColorDelta(ecObc.getUuid(), ACompareModelAlgorithm.COLOR_BLUE);
		final ColorDelta EXPECTED_CD_3 = new ColorDelta(ecRw.getUuid(), ACompareModelAlgorithm.COLOR_GREY);
		
		assertThat("Make sure all ColorDeltas are contained in the list of processed deltas", vdm.colorDeltas.values(), hasItems(EXPECTED_CD_1, EXPECTED_CD_2, EXPECTED_CD_3));
	}
	
}
