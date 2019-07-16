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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptTestCase;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCopier;
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
public class CompareModelPropertyTest extends AConceptTestCase {	
	
	private static final int TEST_MASS_BAT = 80;
	private static final int TEST_MASS_OBC = 50;
	
	private Concept conceptPs;
	private Concept conceptVis;
	private Concept conceptTest;
	
	private Repository repo;
	private Repository repo2;
	
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
		
		repo2 = DVLMFactory.eINSTANCE.createRepository();
		repo2.getActiveConcepts().add(conceptPs);
		repo2.getActiveConcepts().add(conceptVis);
		repo2.setUnitManagement(unitManagement);
		
		StructuralElementInstance seiScCopy = DVLMCopier.makeCopyKeepUuids(ecSc.getStructuralElementInstance());
		
		repo2.getRootEntities().add(seiScCopy);
		
		assertEquals("Have or have not same UUID :-D", ecSc.getStructuralElementInstance().getUuid(), seiScCopy.getUuid());
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testCompareBothStudiesEqual() {
		
		final int COLOR_DELTA_SIZE = 3;
		
		VisualisationDeltaModel vdm = new CompareModelProperty(new NullProgressMonitor()).compare(repo, repo2, propertyFqn);
		
		assertEquals("There are no clone shapes", 0, vdm.cloneShapeDeltas.size());
		assertEquals("There are no ghost shapes", 0, vdm.ghostShapeDeltas.size());
		
		assertEquals("There are some delta colors", COLOR_DELTA_SIZE, vdm.colorDeltas.size());
		
		final ColorDelta EXPECTED_CD_1 = new ColorDelta(ecBat.getUuid(), ACompareModelAlgorithm.COLOR_GREY);
		final ColorDelta EXPECTED_CD_2 = new ColorDelta(ecObc.getUuid(), ACompareModelAlgorithm.COLOR_GREY);
		final ColorDelta EXPECTED_CD_3 = new ColorDelta(ecSc.getUuid(), ACompareModelAlgorithm.COLOR_GREY);
		
		// Both models are equals, thus the delta model should change all visualized parts to become grey
		assertThat("Make sure both ColorDeltas are contained in the list of processed deltas", vdm.colorDeltas.values(), hasItems(EXPECTED_CD_1, EXPECTED_CD_2, EXPECTED_CD_3));
	}
	
	@Test
	public void testCompareBothStudiesEqualQudvPropertyInstance() {
		final String propertyFqn = TestCategoryAllProperty.FULL_QUALIFIED_CATEGORY_NAME + "." + TestCategoryAllProperty.PROPERTY_TESTFLOAT;
		
		final int COLOR_DELTA_SIZE = 3;
		
		VisualisationDeltaModel vdm = new CompareModelProperty(new NullProgressMonitor()).compare(repo, repo2, propertyFqn);
		
		assertEquals("There are no clone shapes", 0, vdm.cloneShapeDeltas.size());
		assertEquals("There are no ghost shapes", 0, vdm.ghostShapeDeltas.size());
		assertEquals("There are some delta colors", COLOR_DELTA_SIZE, vdm.colorDeltas.size());
		
		final ColorDelta EXPECTED_CD_1 = new ColorDelta(ecBat.getUuid(), ACompareModelAlgorithm.COLOR_GREY);
		final ColorDelta EXPECTED_CD_2 = new ColorDelta(ecObc.getUuid(), ACompareModelAlgorithm.COLOR_GREY);
		final ColorDelta EXPECTED_CD_3 = new ColorDelta(ecSc.getUuid(), ACompareModelAlgorithm.COLOR_GREY);
		
		// Both models are equals, thus the delta model should change all visualized parts to become grey
		assertThat("Make sure both ColorDeltas are contained in the list of processed deltas", vdm.colorDeltas.values(), hasItems(EXPECTED_CD_1, EXPECTED_CD_2, EXPECTED_CD_3));
	}
	
	
	@Test
	public void testCompareBothStudiesChangeParamValue() {
		// In this test case we change the equal objects
		
		final int TEST_MASS_BAT_CHANGED = 100; // changed to higher
		final int TEST_MASS_OBC_CHANGED = 40; //changed to lower
		final int COLOR_DELTA_SIZE = 3;
		
		massParamBat.getMass().setDefaultValue(TEST_MASS_BAT_CHANGED);
		massParamObc.getMass().setDefaultValue(TEST_MASS_OBC_CHANGED);
		
		VisualisationDeltaModel vdm = new CompareModelProperty(new NullProgressMonitor()).compare(repo, repo2, propertyFqn);
		
		assertEquals("There are no clone shapes", 0, vdm.cloneShapeDeltas.size());
		assertEquals("There are no ghost shapes", 0, vdm.ghostShapeDeltas.size());
		
		assertEquals("There are some delta colors", COLOR_DELTA_SIZE, vdm.colorDeltas.size());
		
		final ColorDelta EXPECTED_CD_1 = new ColorDelta(ecBat.getUuid(), ACompareModelAlgorithm.COLOR_BLUE);
		final ColorDelta EXPECTED_CD_2 = new ColorDelta(ecObc.getUuid(), ACompareModelAlgorithm.COLOR_CYAN);
		final ColorDelta EXPECTED_CD_3 = new ColorDelta(ecSc.getUuid(), ACompareModelAlgorithm.COLOR_GREY);
		
		assertThat("Make sure both ColorDeltas are contained in the list of processed deltas", vdm.colorDeltas.values(), hasItems(EXPECTED_CD_1, EXPECTED_CD_2, EXPECTED_CD_3));
	}
	
	@Test
	public void testCompareBothStudiesParamAdded() {
		// In this test case we add a parameter to the existing element from one repo
		
		final int COLOR_DELTA_SIZE = 3;
		
		TestMassParameters massParamSc = new TestMassParameters(conceptTest);
		ecSc.add(massParamSc);
		
		VisualisationDeltaModel vdm = new CompareModelProperty(new NullProgressMonitor()).compare(repo, repo2, propertyFqn);
		
		assertEquals("There are no clone shapes", 0, vdm.cloneShapeDeltas.size());
		assertEquals("There are no ghost shapes", 0, vdm.ghostShapeDeltas.size());
		
		assertEquals("There are some delta colors", COLOR_DELTA_SIZE, vdm.colorDeltas.size());
		
		final ColorDelta EXPECTED_CD_1 = new ColorDelta(ecBat.getUuid(), ACompareModelAlgorithm.COLOR_GREY);
		final ColorDelta EXPECTED_CD_2 = new ColorDelta(ecObc.getUuid(), ACompareModelAlgorithm.COLOR_GREY);
		final ColorDelta EXPECTED_CD_3 = new ColorDelta(ecSc.getUuid(), ACompareModelAlgorithm.COLOR_RED);
		
		assertThat("Make sure all ColorDeltas are contained in the list of processed deltas", vdm.colorDeltas.values(), hasItems(EXPECTED_CD_1, EXPECTED_CD_2, EXPECTED_CD_3));
	}
	
	@Test
	public void testCompareBothStudiesParamRemoved() {
		// In this test case we remove a parameter from an element from one repo
		final int COLOR_DELTA_SIZE = 3;
		
		ecBat.remove(massParamBat);
		
		VisualisationDeltaModel vdm = new CompareModelProperty(new NullProgressMonitor()).compare(repo, repo2, propertyFqn);
		
		assertEquals("There are no clone shapes", 0, vdm.cloneShapeDeltas.size());
		assertEquals("There are no ghost shapes", 0, vdm.ghostShapeDeltas.size());
		
		assertEquals("There are some delta colors", COLOR_DELTA_SIZE, vdm.colorDeltas.size());
		
		final ColorDelta EXPECTED_CD_1 = new ColorDelta(ecBat.getUuid(), ACompareModelAlgorithm.COLOR_GREEN);
		final ColorDelta EXPECTED_CD_2 = new ColorDelta(ecObc.getUuid(), ACompareModelAlgorithm.COLOR_GREY);
		final ColorDelta EXPECTED_CD_3 = new ColorDelta(ecSc.getUuid(), ACompareModelAlgorithm.COLOR_GREY);
		
		assertThat("Make sure all ColorDeltas are contained in the list of processed deltas", vdm.colorDeltas.values(), hasItems(EXPECTED_CD_1, EXPECTED_CD_2, EXPECTED_CD_3));
		
	}
}
