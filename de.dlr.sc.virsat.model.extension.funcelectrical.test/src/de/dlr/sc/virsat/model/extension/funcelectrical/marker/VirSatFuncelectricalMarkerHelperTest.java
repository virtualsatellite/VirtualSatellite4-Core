/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.marker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.project.markers.IMarkerHelper;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;
import de.dlr.sc.virsat.project.test.ATestCase;

/**
 * Test class for the VirSat Validation Marker Helper
 * @author lobe_el
 *
 */
public class VirSatFuncelectricalMarkerHelperTest extends ATestCase {

	VirSatProblemMarkerHelper vpmHelper = new VirSatProblemMarkerHelper();
	VirSatFuncelectricalMarkerHelper vfmHelper = new VirSatFuncelectricalMarkerHelper();

	private static final int THREE = 3;
	
	@Test
	public void testCreateMarkerHelper() {
		IMarker markerS = vfmHelper.createFEAValidationMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		IMarker markerD = vfmHelper.createFEAValidationMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", vpiOfCa1OfContainedSei, vpiOfCa2OfContainedSei);
		
		IMarkerHelper markerHelperS = VirSatProblemMarkerHelper.createMarkerHelper(markerS);
		IMarkerHelper markerHelperD = VirSatProblemMarkerHelper.createMarkerHelper(markerD);
		
		assertTrue("Helper is correct", markerHelperS instanceof VirSatFuncelectricalMarkerHelper);
		assertTrue("Helper is correct", markerHelperD instanceof VirSatFuncelectricalMarkerHelper);
	}
	
	@Test
	public void testIsAssociatedWith() {
		IMarker markerS = vfmHelper.createFEAValidationMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		IMarker markerD = vfmHelper.createFEAValidationMarker(IMarker.SEVERITY_WARNING, "This is a marker on the VPIs", vpiOfCa1OfContainedSei, vpiOfCa2OfContainedSei);
		
		IMarker vpMarker  = vpmHelper.createVirSatMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		
		assertTrue("Correct Association", vfmHelper.isAssociatedWith(markerS, seiContaining));
		assertTrue("Correct Association", vfmHelper.isAssociatedWith(markerD, vpiOfCa1OfContainedSei));
		assertTrue("Correct Association", vfmHelper.isAssociatedWith(markerD, vpiOfCa2OfContainedSei));
		assertTrue("Correct Association", vfmHelper.isAssociatedWith(vpMarker, seiContained));

		assertFalse("Correct Association", vfmHelper.isAssociatedWith(markerD, ca1OfContainedSei));
		assertFalse("Correct Association", vfmHelper.isAssociatedWith(markerD, ca2OfContainedSei));
		assertFalse("Correct Association", vfmHelper.isAssociatedWith(markerD, seiContained));
		
		assertTrue("Correct Association", vpmHelper.isAssociatedWithCheckAllHelpers(markerS, seiContaining));
		assertTrue("Correct Association", vpmHelper.isAssociatedWithCheckAllHelpers(markerD, vpiOfCa1OfContainedSei));
		assertTrue("Correct Association", vpmHelper.isAssociatedWithCheckAllHelpers(markerD, vpiOfCa2OfContainedSei));
		assertTrue("Correct Association", vpmHelper.isAssociatedWithCheckAllHelpers(vpMarker, seiContained));

		assertFalse("Correct Association", vpmHelper.isAssociatedWithCheckAllHelpers(markerD, ca1OfContainedSei));
		assertFalse("Correct Association", vpmHelper.isAssociatedWithCheckAllHelpers(markerD, ca2OfContainedSei));
		assertFalse("Correct Association", vpmHelper.isAssociatedWithCheckAllHelpers(markerD, seiContained));
	}
	
	@Test
	public void testGetAllMarkers() {
		IMarker vfMarkerEmf  = vfmHelper.createEMFValidationMarker(IMarker.SEVERITY_WARNING, "This is an emf marker", seiContained, GeneralPackage.Literals.ICOMMENT__COMMENT);
		IMarker vfMarkerFea  = vfmHelper.createFEAValidationMarker(IMarker.SEVERITY_WARNING, "This is a marker on the VPIs", seiContained);
		
		IMarker vpMarker  = vpmHelper.createVirSatMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContained);
		
		Set<IMarker> vfMarkers = vfmHelper.getAllMarkers(seiContained);
		Set<IMarker> vpMarkers = vpmHelper.getAllMarkers(seiContained);
		
		assertEquals("Set has correct size", 1, vfMarkers.size());
		assertEquals("Set has correct size", THREE, vpMarkers.size());
		
		assertTrue("Set has correct elements", vfMarkers.contains(vfMarkerFea));
		assertTrue("Set has correct elements", vpMarkers.contains(vfMarkerFea) && vpMarkers.contains(vfMarkerEmf) && vpMarkers.contains(vpMarker));
	}

	//CHECKSTYLE:OFF
	@Test
	public void testCreateFEAValidationMarker_OnSingleObject() {
		IMarker markerRepo = vfmHelper.createFEAValidationMarker(IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		IMarker markerSei  = vfmHelper.createFEAValidationMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		
		assertFalse("Marker is created", markerRepo == null);
		assertFalse("Marker is created", markerSei == null);
		assertTrue("Marker is placed correctly", vfmHelper.getAllMarkers(repo).contains(markerRepo));
		assertTrue("Marker is placed correctly", vfmHelper.getAllMarkers(seiContaining).contains(markerSei));
		
		try {
			assertTrue("Marker has correct type", markerRepo.isSubtypeOf(VirSatFuncelectricalMarkerHelper.ID_FEA_VALIDATION_PROBLEM_MARKER));
			assertTrue("Marker has correct type", markerSei.isSubtypeOf(VirSatFuncelectricalMarkerHelper.ID_FEA_VALIDATION_PROBLEM_MARKER));
		} catch (CoreException e) {
			assertTrue("Expected no exception", false);
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateFEAValidationMarker_OnTwoObjects() throws CoreException {
		IMarker markerCas = vfmHelper.createFEAValidationMarker(IMarker.SEVERITY_WARNING, "This is a marker on the CAs", ca1OfContainedSei, ca2OfContainedSei);
		IMarker markerVpis  = vfmHelper.createFEAValidationMarker(IMarker.SEVERITY_WARNING, "This is a marker on the VPIs", vpiOfCa1OfContainedSei, vpiOfCa2OfContainedSei);
		
		assertFalse("Marker is created", markerCas == null);
		assertFalse("Marker is created", markerVpis == null);
		assertTrue("Marker is placed correctly", vfmHelper.getAllMarkers(ca1OfContainedSei).contains(markerCas));
		assertTrue("Marker is placed correctly", vfmHelper.getAllMarkers(ca2OfContainedSei).contains(markerCas));
		assertTrue("Marker is placed correctly", vfmHelper.getAllMarkers(vpiOfCa1OfContainedSei).contains(markerVpis));
		assertTrue("Marker is placed correctly", vfmHelper.getAllMarkers(vpiOfCa2OfContainedSei).contains(markerVpis));
		assertEquals("Not to much marker", 2, vfmHelper.getAllMarkersForObjectAndContents(seiContained).size());
		
		assertTrue("Marker has correct type", markerCas.isSubtypeOf(VirSatFuncelectricalMarkerHelper.ID_FEA_VALIDATION_PROBLEM_MARKER));
		assertTrue("Marker has correct type", markerVpis.isSubtypeOf(VirSatFuncelectricalMarkerHelper.ID_FEA_VALIDATION_PROBLEM_MARKER));
	}
	//CHECKSTYLE:ON
}
