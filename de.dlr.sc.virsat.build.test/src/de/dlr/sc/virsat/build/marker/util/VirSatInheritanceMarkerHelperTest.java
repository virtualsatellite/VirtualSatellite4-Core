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

import de.dlr.sc.virsat.project.markers.IMarkerHelper;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;

/**
 * Test class for the VirSat Inheritance Marker Helper
 * @author lobe_el
 *
 */
public class VirSatInheritanceMarkerHelperTest extends ATestCase {

	VirSatInheritanceMarkerHelper vimHelper = new VirSatInheritanceMarkerHelper();
	VirSatProblemMarkerHelper vpmHelper = new VirSatProblemMarkerHelper();
	
	@Test
	public void testCreateMarkerHelper() {
		IMarker marker = vimHelper.createInheritanceMarker(IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		IMarkerHelper helperMarker = VirSatProblemMarkerHelper.createMarkerHelper(marker);
		assertTrue("Helper is correct", helperMarker instanceof VirSatInheritanceMarkerHelper);
	}
	
	@Test
	public void testGetAllMarkers() {
		IMarker viMarkerRepo = vimHelper.createInheritanceMarker(IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		IMarker viMarkerSei  = vimHelper.createInheritanceMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		IMarker vpMarkerRepo = vpmHelper.createVirSatMarker(IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		IMarker vpMarkerSei  = vpmHelper.createVirSatMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		
		Set<IMarker> viMarkersRepo = vimHelper.getAllMarkers(repo);
		Set<IMarker> viMarkersSei = vimHelper.getAllMarkers(seiContaining);
		Set<IMarker> vpMarkersRepo = vpmHelper.getAllMarkers(repo);
		Set<IMarker> vpMarkersSei = vpmHelper.getAllMarkers(seiContaining);
		
		assertEquals("Set has correct size", 1, viMarkersRepo.size());
		assertEquals("Set has correct size", 1, viMarkersSei.size());
		assertEquals("Set has correct size", 2, vpMarkersRepo.size());
		assertEquals("Set has correct size", 2, vpMarkersSei.size());
		
		assertTrue("Set has correct elements", viMarkersRepo.contains(viMarkerRepo));
		assertTrue("Set has correct elements", viMarkersSei.contains(viMarkerSei));
		assertTrue("Set has correct elements", vpMarkersRepo.contains(viMarkerRepo) && vpMarkersRepo.contains(vpMarkerRepo));
		assertTrue("Set has correct elements", vpMarkersSei.contains(viMarkerSei) && vpMarkersSei.contains(vpMarkerSei));
	}

	@Test
	public void testDeleteAllMarkersInWorkspace() {
		vimHelper.createInheritanceMarker(IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		vimHelper.createInheritanceMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		IMarker vpMarkerRepo = vpmHelper.createVirSatMarker(IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		IMarker vpMarkerSei  = vpmHelper.createVirSatMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);

		Set<IMarker> viMarkersRepo = vimHelper.getAllMarkers(repo);
		Set<IMarker> viMarkersSei = vimHelper.getAllMarkers(seiContaining);
		Set<IMarker> vpMarkersRepo = vpmHelper.getAllMarkers(repo);
		Set<IMarker> vpMarkersSei = vpmHelper.getAllMarkers(seiContaining);
		
		assertEquals("Set has correct size", 1, viMarkersRepo.size());
		assertEquals("Set has correct size", 1, viMarkersSei.size());
		assertEquals("Set has correct size", 2, vpMarkersRepo.size());
		assertEquals("Set has correct size", 2, vpMarkersSei.size());
		
		vimHelper.deleteAllMarkersInWorkspace();
		
		viMarkersRepo = vimHelper.getAllMarkers(repo);
		viMarkersSei = vimHelper.getAllMarkers(seiContaining);
		vpMarkersRepo = vpmHelper.getAllMarkers(repo);
		vpMarkersSei = vpmHelper.getAllMarkers(seiContaining);
		
		assertEquals("Set has correct size", 0, viMarkersRepo.size());
		assertEquals("Set has correct size", 0, viMarkersSei.size());
		assertEquals("Set has correct size", 1, vpMarkersRepo.size());
		assertEquals("Set has correct size", 1, vpMarkersSei.size());
		
		assertTrue("Correct elements are deleted", vpMarkersRepo.contains(vpMarkerRepo));
		assertTrue("Correct elements are deleted", vpMarkersSei.contains(vpMarkerSei));
		
		vpmHelper.deleteAllMarkersInWorkspace();

		viMarkersRepo = vimHelper.getAllMarkers(repo);
		viMarkersSei = vimHelper.getAllMarkers(seiContaining);
		vpMarkersRepo = vpmHelper.getAllMarkers(repo);
		vpMarkersSei = vpmHelper.getAllMarkers(seiContaining);
		
		assertEquals("Set has correct size", 0, viMarkersRepo.size());
		assertEquals("Set has correct size", 0, viMarkersSei.size());
		assertEquals("Set has correct size", 0, vpMarkersRepo.size());
		assertEquals("Set has correct size", 0, vpMarkersSei.size());
	}
	
	@Test
	public void testCreateInheritanceMarker() throws CoreException {
		IMarker markerRepo = vimHelper.createInheritanceMarker(IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		IMarker markerSei  = vimHelper.createInheritanceMarker(IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		
		assertFalse("Marker is created", markerRepo == null);
		assertFalse("Marker is created", markerSei == null);
		assertTrue("Marker is placed correctly", vimHelper.getAllMarkers(repo).contains(markerRepo));
		assertTrue("Marker is placed correctly", vimHelper.getAllMarkers(seiContaining).contains(markerSei));
		
		assertTrue("Marker has correct type", markerRepo.isSubtypeOf(VirSatInheritanceMarkerHelper.ID_INHERITANCE_PROBLEM_MARKER));
		assertTrue("Marker has correct type", markerSei.isSubtypeOf(VirSatInheritanceMarkerHelper.ID_INHERITANCE_PROBLEM_MARKER));
	}
	
}
