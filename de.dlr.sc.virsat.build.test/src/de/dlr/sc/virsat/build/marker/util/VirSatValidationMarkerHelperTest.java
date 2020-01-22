/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.marker.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.project.markers.IMarkerHelper;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;

/**
 * Test class for the VirSat Validation Marker Helper
 * @author lobe_el
 *
 */
public class VirSatValidationMarkerHelperTest extends ATestCase {

	VirSatValidationMarkerHelper vvmHelper = new VirSatValidationMarkerHelper();
	VirSatProblemMarkerHelper vpmHelper = new VirSatProblemMarkerHelper();
	
	private static final int THREE = 3;
	private static final int FOUR = 4;
	
	@Test
	public void testCreateMarkerHelper() {
		IMarker markerInh  = vvmHelper.createInheritanceValidationMarker(IMarker.SEVERITY_WARNING, "This is an inheritance marker", seiContaining, seiContained);
		IMarker markerEmf  = vvmHelper.createEMFValidationMarker(IMarker.SEVERITY_WARNING, "This is an inheritance marker", seiContaining, GeneralPackage.Literals.ICOMMENT__COMMENT);
		IMarker markerDvlm = vvmHelper.createDVLMValidationMarker(IMarker.SEVERITY_WARNING, "This is an inheritance marker", seiContaining);
		
		IMarkerHelper helperMarkerInh  = VirSatProblemMarkerHelper.createMarkerHelper(markerInh);
		IMarkerHelper helperMarkerEmf  = VirSatProblemMarkerHelper.createMarkerHelper(markerEmf);
		IMarkerHelper helperMarkerDvlm = VirSatProblemMarkerHelper.createMarkerHelper(markerDvlm);
		
		assertTrue("Helper is correct", helperMarkerInh instanceof VirSatValidationMarkerHelper);
		assertTrue("Helper is correct", helperMarkerEmf instanceof VirSatValidationMarkerHelper);
		assertTrue("Helper is correct", helperMarkerDvlm instanceof VirSatValidationMarkerHelper);
	}

	@Test
	public void testGetAllMarkers() {
		IMarker vvMarkerInh  = vvmHelper.createInheritanceValidationMarker(IMarker.SEVERITY_WARNING, "This is an inheritance marker", seiContaining, seiContained);
		IMarker vvMarkerEmf  = vvmHelper.createEMFValidationMarker(IMarker.SEVERITY_WARNING, "This is an emf marker", seiContaining, GeneralPackage.Literals.ICOMMENT__COMMENT);
		IMarker vvMarkerDvlm = vvmHelper.createDVLMValidationMarker(IMarker.SEVERITY_WARNING, "This is an dvlm marker", seiContaining);
		
		IMarker vpMarker  = vpmHelper.createVirSatMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		
		Set<IMarker> vvmMarkers = vvmHelper.getAllMarkers(seiContaining);
		Set<IMarker> vpMarkers = vpmHelper.getAllMarkers(seiContaining);
		
		assertEquals("Set has correct size", THREE, vvmMarkers.size());
		assertEquals("Set has correct size", FOUR, vpMarkers.size());
		
		assertTrue("Set has correct elements", vvmMarkers.contains(vvMarkerDvlm) && vvmMarkers.contains(vvMarkerEmf) && vvmMarkers.contains(vvMarkerInh));
		assertTrue("Set has correct elements", vpMarkers.containsAll(vvmMarkers) && vpMarkers.contains(vpMarker));
	}
	
	@Test
	public void testCreateDVLMValidationMarker() throws CoreException {
		IMarker markerRepo = vvmHelper.createDVLMValidationMarker(IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		IMarker markerSei  = vvmHelper.createDVLMValidationMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		
		assertFalse("Marker is created", markerRepo == null);
		assertFalse("Marker is created", markerSei == null);
		assertTrue("Marker is placed correctly", vvmHelper.getAllMarkers(repo).contains(markerRepo));
		assertTrue("Marker is placed correctly", vvmHelper.getAllMarkers(seiContaining).contains(markerSei));

		assertTrue("Marker has correct type", markerRepo.isSubtypeOf(VirSatValidationMarkerHelper.ID_VALIDATION_PROBLEM_MARKER));
		assertTrue("Marker has correct type", markerSei.isSubtypeOf(VirSatValidationMarkerHelper.ID_VALIDATION_PROBLEM_MARKER));
		assertTrue("Marker has correct type", markerRepo.isSubtypeOf(VirSatValidationMarkerHelper.ID_DVLM_VALIDATION_PROBLEM_MARKER));
		assertTrue("Marker has correct type", markerSei.isSubtypeOf(VirSatValidationMarkerHelper.ID_DVLM_VALIDATION_PROBLEM_MARKER));
	}
	
	@Test
	public void testCreateInheritanceValidationMarker() throws CoreException {
		IMarker markerRepo = vvmHelper.createInheritanceValidationMarker(IMarker.SEVERITY_WARNING, "This is an inheritance marker", repo, seiContained);
		IMarker markerSei  = vvmHelper.createInheritanceValidationMarker(IMarker.SEVERITY_WARNING, "This is an inheritance marker", seiContaining, seiContained);
		
		assertFalse("Marker is created", markerRepo == null);
		assertFalse("Marker is created", markerSei == null);
		assertTrue("Marker is placed correctly", vvmHelper.getAllMarkers(repo).contains(markerRepo));
		assertTrue("Marker is placed correctly", vvmHelper.getAllMarkers(seiContaining).contains(markerSei));
		
		assertTrue("Marker has correct type", markerRepo.isSubtypeOf(VirSatValidationMarkerHelper.ID_VALIDATION_PROBLEM_MARKER));
		assertTrue("Marker has correct type", markerSei.isSubtypeOf(VirSatValidationMarkerHelper.ID_VALIDATION_PROBLEM_MARKER));
		assertTrue("Marker has correct type", markerRepo.isSubtypeOf(VirSatValidationMarkerHelper.ID_INH_VALIDATION_PROBLEM_MARKER));
		assertTrue("Marker has correct type", markerSei.isSubtypeOf(VirSatValidationMarkerHelper.ID_INH_VALIDATION_PROBLEM_MARKER));
	}
	
	@Test
	public void testCreateEMFValidationMarker() throws CoreException {
		IMarker markerRepo = vvmHelper.createEMFValidationMarker(IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo, DVLMPackage.Literals.REPOSITORY__ACTIVE_CONCEPTS);
		IMarker markerSei  = vvmHelper.createEMFValidationMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining, GeneralPackage.Literals.IDESCRIPTION__DESCRIPTION);
		
		assertFalse("Marker is created", markerRepo == null);
		assertFalse("Marker is created", markerSei == null);
		assertTrue("Marker is placed correctly", vvmHelper.getAllMarkers(repo).contains(markerRepo));
		assertTrue("Marker is placed correctly", vvmHelper.getAllMarkers(seiContaining).contains(markerSei));
		
		assertTrue("Marker has correct type", markerRepo.isSubtypeOf(VirSatValidationMarkerHelper.ID_VALIDATION_PROBLEM_MARKER));
		assertTrue("Marker has correct type", markerSei.isSubtypeOf(VirSatValidationMarkerHelper.ID_VALIDATION_PROBLEM_MARKER));
		assertTrue("Marker has correct type", markerRepo.isSubtypeOf(VirSatValidationMarkerHelper.ID_EMF_VALIDATION_PROBLEM_MARKER));
		assertTrue("Marker has correct type", markerSei.isSubtypeOf(VirSatValidationMarkerHelper.ID_EMF_VALIDATION_PROBLEM_MARKER));
	}
	
	@Test
	public void testGetEMFValidationMarkers() {
		vvmHelper.createDVLMValidationMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		vvmHelper.createInheritanceValidationMarker(IMarker.SEVERITY_WARNING, "This is an inheritance marker", seiContaining, seiContained);
		IMarker markerSeiEmf  = vvmHelper.createEMFValidationMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining, GeneralPackage.Literals.IDESCRIPTION__DESCRIPTION);
		
		Set<IMarker> markersName = vvmHelper.getEMFValidationMarkers(seiContaining, GeneralPackage.Literals.INAME__NAME);
		Set<IMarker> markersDesc = vvmHelper.getEMFValidationMarkers(seiContaining, GeneralPackage.Literals.IDESCRIPTION__DESCRIPTION);
		
		assertEquals("Set has correct size", 0, markersName.size());
		assertEquals("Set has correct size", 1, markersDesc.size());
	
		assertTrue("Marker is placed correctly", markersDesc.contains(markerSeiEmf));
	}
	
}
