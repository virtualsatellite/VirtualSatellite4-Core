/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.marker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.calculation.compute.problem.EvaluationProblem;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.project.markers.IMarkerHelper;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;

/**
 * Test class for the VirSat Equation Marker Helper
 * @author lobe_el
 *
 */
public class VirSatEquationMarkerHelperTest extends ATestCase {
	
	VirSatEquationMarkerHelper vemHelper = new VirSatEquationMarkerHelper();
	VirSatProblemMarkerHelper vpmHelper = new VirSatProblemMarkerHelper();

	private static final int THREE = 3;

	protected Equation eq1OfContainingSei;
	protected Equation eq2OfContainingSei;
	protected TypeInstanceResult tirOfEq1;
	protected TypeInstanceResult tirOfEq2;
	protected UnitValuePropertyInstance uvpiOfEq1;
	protected UnitValuePropertyInstance uvpiOfEq2;
	
	@Before
	public void setUp() {
		super.setUp();

		eq1OfContainingSei = CalculationFactory.eINSTANCE.createEquation();
		eq2OfContainingSei = CalculationFactory.eINSTANCE.createEquation();
		eqSectionOfCaEqOfContainingSei.getEquations().add(eq1OfContainingSei);
		eqSectionOfCaEqOfContainingSei.getEquations().add(eq2OfContainingSei);

		tirOfEq1 = CalculationFactory.eINSTANCE.createTypeInstanceResult();
		tirOfEq2 = CalculationFactory.eINSTANCE.createTypeInstanceResult(); 
		eq1OfContainingSei.setResult(tirOfEq1);
		eq2OfContainingSei.setResult(tirOfEq2);
		
		uvpiOfEq1 = PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
		uvpiOfEq2 = PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
		tirOfEq1.setReference(uvpiOfEq1);
		tirOfEq2.setReference(uvpiOfEq2);
		
		caEq.getPropertyInstances().add(uvpiOfEq1);
		caEq.getPropertyInstances().add(uvpiOfEq2);
	}

	@Test
	public void testCreateMarkerHelper() {
		IMarker markerC = vemHelper.createCyclicEquationMarker(eq1OfContainingSei);
		IMarker markerO = vemHelper.createEvaluationProblemMarker(new EvaluationProblem(eq2OfContainingSei));
		
		IMarkerHelper markerHelperC = VirSatProblemMarkerHelper.createMarkerHelper(markerC);
		IMarkerHelper markerHelperO = VirSatProblemMarkerHelper.createMarkerHelper(markerO);
		
		assertTrue("Helper is correct", markerHelperC instanceof VirSatEquationMarkerHelper);
		assertTrue("Helper is correct", markerHelperO instanceof VirSatEquationMarkerHelper);
	}
	
	@Test
	public void testIsAssociatedWith() {
		IMarker markerC = vemHelper.createCyclicEquationMarker(eq1OfContainingSei);
		IMarker markerO = vemHelper.createEvaluationProblemMarker(new EvaluationProblem(eq2OfContainingSei));
		
		assertTrue("Correct association", vemHelper.isAssociatedWith(markerO, eq2OfContainingSei));
		assertTrue("Correct association", vemHelper.isAssociatedWith(markerC, eq1OfContainingSei));

		assertTrue("Correct association", vemHelper.isAssociatedWith(markerO, tirOfEq2));
		assertTrue("Correct association", vemHelper.isAssociatedWith(markerC, tirOfEq1));
		
		assertTrue("Correct association", vemHelper.isAssociatedWith(markerO, uvpiOfEq2));
		assertTrue("Correct association", vemHelper.isAssociatedWith(markerC, uvpiOfEq1));
		
		assertTrue("Correct association", vpmHelper.isAssociatedWithCheckAllHelpers(markerO, eq2OfContainingSei));
		assertTrue("Correct association", vpmHelper.isAssociatedWithCheckAllHelpers(markerC, eq1OfContainingSei));

		assertTrue("Correct association", vpmHelper.isAssociatedWithCheckAllHelpers(markerO, tirOfEq2));
		assertTrue("Correct association", vpmHelper.isAssociatedWithCheckAllHelpers(markerC, tirOfEq1));

		assertTrue("Correct association", vpmHelper.isAssociatedWithCheckAllHelpers(markerO, uvpiOfEq2));
		assertTrue("Correct association", vpmHelper.isAssociatedWithCheckAllHelpers(markerC, uvpiOfEq1));
	}
	
	@Test
	public void testGetAllMarkers() {
		IMarker markerC = vemHelper.createCyclicEquationMarker(eq1OfContainingSei);
		IMarker markerO = vemHelper.createEvaluationProblemMarker(new EvaluationProblem(eq2OfContainingSei));
		
		IMarker vpMarker  = vpmHelper.createVirSatMarker(IMarker.SEVERITY_WARNING, "This is a marker on the Eq1", tirOfEq1);
		
		Set<IMarker> veMarkers = vemHelper.getAllMarkersForObjectAndContents(seiContaining);
		Set<IMarker> vpMarkers = vpmHelper.getAllMarkersForObjectAndContents(seiContaining);
		
		assertEquals("Set has correct size", 2, veMarkers.size());
		assertEquals("Set has correct size", THREE, vpMarkers.size());
		
		assertTrue("Set has correct elements", veMarkers.contains(markerC) && veMarkers.contains(markerO));
		assertTrue("Set has correct elements", vpMarkers.containsAll(veMarkers) && vpMarkers.contains(vpMarker));
	}
	
	@Test
	public void testGetAllOutdatedMarkers() {
		vemHelper.createCyclicEquationMarker(eq1OfContainingSei);
		IMarker markerO = vemHelper.createEvaluationProblemMarker(new EvaluationProblem(eq1OfContainingSei));
		vpmHelper.createVirSatMarker(IMarker.SEVERITY_WARNING, "This is a marker on the Eq1", eq1OfContainingSei);
		
		Set<IMarker> outdatedMarkers = vemHelper.getAllEvaluationProblemMarkers(eq1OfContainingSei);
		assertEquals("Set has correct size", 1, outdatedMarkers.size());
		assertTrue("Set has correct elements", outdatedMarkers.contains(markerO));
	}
	
	@Test
	public void testDeleteAllOutdatedMarkers() {
		IMarker markerC = vemHelper.createCyclicEquationMarker(eq1OfContainingSei);
		vemHelper.createEvaluationProblemMarker(new EvaluationProblem(eq1OfContainingSei));
		IMarker vpMarker = vpmHelper.createVirSatMarker(IMarker.SEVERITY_WARNING, "This is a marker on the Eq1", tirOfEq1);
		
		Set<IMarker> remainingVpMarkers = vpmHelper.getAllMarkersForObjectAndContents(seiContaining);
		Set<IMarker> remainingVeMarkers = vemHelper.getAllMarkersForObjectAndContents(seiContaining);
		assertEquals("Set has correct size", THREE, remainingVpMarkers.size());
		assertEquals("Set has correct size", 2, remainingVeMarkers.size());
		
		vemHelper.deleteAllEquationEvaluationProblemMarkers(eq1OfContainingSei);

		remainingVpMarkers = vpmHelper.getAllMarkersForObjectAndContents(seiContaining);
		remainingVeMarkers = vemHelper.getAllMarkersForObjectAndContents(seiContaining);
		assertEquals("Set has correct size", 2, remainingVpMarkers.size());
		assertEquals("Set has correct size", 1, remainingVeMarkers.size());
		assertTrue("Set has correct elements", remainingVpMarkers.contains(markerC) && remainingVpMarkers.contains(vpMarker));
		assertTrue("Set has correct elements", remainingVeMarkers.contains(markerC));
	}
	
	@Test
	public void testCreateCyclicEquationMarker() throws CoreException {
		IMarker markerC = vemHelper.createCyclicEquationMarker(eq1OfContainingSei);
		
		assertFalse("Marker is created", markerC == null);
		assertTrue("Marker is placed correctly", vemHelper.getAllMarkersForObjectAndContents(seiContaining).contains(markerC));
		
		assertTrue("Marker has correct type", markerC.isSubtypeOf(VirSatEquationMarkerHelper.ID_EQUATION_MARKER));
		assertTrue("Marker has correct type", markerC.isSubtypeOf(VirSatEquationMarkerHelper.ID_CYCLIC_EQUATION_MARKER));
	}
	
	@Test
	public void testCreateOutdatedEquationMarker() throws CoreException {
		IMarker markerO = vemHelper.createEvaluationProblemMarker(new EvaluationProblem(eq2OfContainingSei));
		
		assertFalse("Marker is created", markerO == null);
		assertTrue("Marker is placed correctly", vemHelper.getAllMarkersForObjectAndContents(seiContaining).contains(markerO));
		
		assertTrue("Marker has correct type", markerO.isSubtypeOf(VirSatEquationMarkerHelper.ID_EQUATION_MARKER));
		assertTrue("Marker has correct type", markerO.isSubtypeOf(VirSatEquationMarkerHelper.ID_EVALUATION_MARKER));
	}
}
