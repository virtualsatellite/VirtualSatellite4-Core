/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.markers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.project.test.ATestCase;

/**
 * Test class for the VirSatProblemMarkerHelper
 * @author lobe_el
 *
 */
public class VirSatProblemMarkerHelperTest extends ATestCase {
	
	private static final String ID_TESTMARKER = "de.dlr.sc.virsat.problem.marker.testmarker";

	private VirSatProblemMarkerHelper vpmh;
	
	@Override
	public void setUp() {
		vpmh = new VirSatProblemMarkerHelper();
		super.setUp();
	}

	//CHECKSTYLE:OFF
	@Test
	public void testCreateMarkerHelper() throws CoreException {
		IMarker markerRepo = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		IMarker markerCa = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the CaOfContainingSei", caOfContainingSei);

		IMarker markerSei = null;
		markerSei = fileSeiContaining.createMarker(IMarker.PROBLEM);
		
		assertFalse("There is a marker created", markerSei == null);
		
		IMarkerHelper helperMarkerRepo = VirSatProblemMarkerHelper.createMarkerHelper(markerRepo);
		IMarkerHelper helperMarkerSei  = VirSatProblemMarkerHelper.createMarkerHelper(markerSei);
		IMarkerHelper helperMarkerCa   = VirSatProblemMarkerHelper.createMarkerHelper(markerCa);
		
		assertTrue("Helper is correct", helperMarkerRepo instanceof VirSatProblemMarkerHelper);
		assertTrue("Helper is correct", helperMarkerCa instanceof VirSatProblemMarkerHelper);
		assertTrue("Helper is correct", helperMarkerSei == null);
	}
	
	@Test
	public void testCheckExistence() {
		IMarker markerRepo = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		IMarker markerConcept = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Concept", concept);
		IMarker markerSeiContaining = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		IMarker markerSeiContained = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		IMarker markerCaOfContainingSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the CaOfContainingSei", caOfContainingSei);
		IMarker markerCa1OfContainedSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the ca1OfContainedSei", ca1OfContainedSei);
		IMarker markerVpiOfCaOfContainingSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCaOfContainingSei", vpiOfCaOfContainingSei);
		
		Set<IMarker> markers = new HashSet<IMarker>();
		markers.add(markerRepo);
		markers.add(markerConcept);
		markers.add(markerSeiContaining);
		markers.add(markerSeiContained);
		markers.add(markerCaOfContainingSei);
		markers.add(markerCa1OfContainedSei);
		markers.add(markerVpiOfCaOfContainingSei);
		
		assertTrue("Marker exists", markerRepo.exists());
		assertTrue("Marker exists", markerConcept.exists());
		assertTrue("Marker exists", markerSeiContaining.exists());
		assertTrue("Marker exists", markerSeiContained.exists());
		assertTrue("Marker exists", markerCaOfContainingSei.exists());
		assertTrue("Marker exists", markerCa1OfContainedSei.exists());
		assertTrue("Marker exists", markerVpiOfCaOfContainingSei.exists());
		
		Set<IMarker> existingMarkers = vpmh.checkExistence(markers);
		assertEquals("Sets are equal", markers, existingMarkers);
		
		vpmh.deleteAllMarkers(repo);
		vpmh.deleteAllMarkers(caOfContainingSei);
		
		assertFalse("Marker exists", markerRepo.exists());
		assertTrue("Marker exists", markerConcept.exists());
		assertTrue("Marker exists", markerSeiContaining.exists());
		assertTrue("Marker exists", markerSeiContained.exists());
		assertFalse("Marker exists", markerCaOfContainingSei.exists());
		assertTrue("Marker exists", markerCa1OfContainedSei.exists());
		assertTrue("Marker exists", markerVpiOfCaOfContainingSei.exists());
		
		existingMarkers = vpmh.checkExistence(markers);
		assertFalse("Sets are not equal", markers.equals(existingMarkers));
		assertFalse("Set does not contain anymore", existingMarkers.contains(markerRepo));
		assertFalse("Set does not contain anymore", existingMarkers.contains(markerCaOfContainingSei));
		
		markers.remove(markerRepo);
		markers.remove(markerCaOfContainingSei);
		assertTrue("Rest of Sets are equal", markers.equals(existingMarkers));
	}
	
	@Test
	public void testGetIdentifierValue() {
		String idValueRepo = vpmh.getIdentifierValue(repo);
		String idValueConcept = vpmh.getIdentifierValue(concept);

		String idValueSe = vpmh.getIdentifierValue(se);
		String idValueCategory = vpmh.getIdentifierValue(category);
		String idValueIp = vpmh.getIdentifierValue(ip);
		
		String idValueSeiContaining = vpmh.getIdentifierValue(seiContaining);
		String idValueSeiContained = vpmh.getIdentifierValue(seiContained);
		
		String idValueCaOfContainingSei = vpmh.getIdentifierValue(caOfContainingSei);
		String idValueCa1OfContainedSei = vpmh.getIdentifierValue(ca1OfContainedSei);
		String idValueCa2OfContainedSei = vpmh.getIdentifierValue(ca2OfContainedSei);
		
		String idValueVpiOfCaOfContainingSei = vpmh.getIdentifierValue(vpiOfCaOfContainingSei);
		String idValueVpiOfCa1OfContainedSei = vpmh.getIdentifierValue(vpiOfCa1OfContainedSei);
		String idValueVpiOfCa2OfContainedSei = vpmh.getIdentifierValue(vpiOfCa2OfContainedSei);
		
		String idValueProject = vpmh.getIdentifierValue(project);
		String whatever = "WhatEver";
		String idValueOfWhatever = vpmh.getIdentifierValue(whatever);
		
		assertEquals("Correct Identifier Value", repo.getUuid().toString(), idValueRepo);
		assertEquals("Correct Identifier Value", concept.getFullQualifiedName().toString(), idValueConcept);
		
		assertEquals("Correct Identifier Value", se.getFullQualifiedName().toString(), idValueSe);
		assertEquals("Correct Identifier Value", category.getFullQualifiedName().toString(), idValueCategory);
		assertEquals("Correct Identifier Value", ip.getFullQualifiedName().toString(), idValueIp);
		
		assertEquals("Correct Identifier Value", seiContaining.getUuid().toString(), idValueSeiContaining);
		assertEquals("Correct Identifier Value", seiContained.getUuid().toString(), idValueSeiContained);
		
		assertEquals("Correct Identifier Value", caOfContainingSei.getUuid().toString(), idValueCaOfContainingSei);
		assertEquals("Correct Identifier Value", ca1OfContainedSei.getUuid().toString(), idValueCa1OfContainedSei);
		assertEquals("Correct Identifier Value", ca2OfContainedSei.getUuid().toString(), idValueCa2OfContainedSei);
		
		assertEquals("Correct Identifier Value", vpiOfCaOfContainingSei.getUuid().toString(), idValueVpiOfCaOfContainingSei);
		assertEquals("Correct Identifier Value", vpiOfCa1OfContainedSei.getUuid().toString(), idValueVpiOfCa1OfContainedSei);
		assertEquals("Correct Identifier Value", vpiOfCa2OfContainedSei.getUuid().toString(), idValueVpiOfCa2OfContainedSei);
		
		assertEquals("Correct Identifier Value", VirSatProblemMarkerHelper.ID_VALUE_NOT_VALID, idValueProject);
		assertEquals("Correct Identifier Value", VirSatProblemMarkerHelper.ID_VALUE_NOT_VALID, idValueOfWhatever);
	}
	
	@Test
	public void testGetIdentifierType() {
		String idTypeRepo = vpmh.getIdentifierType(repo);
		String idTypeConcept = vpmh.getIdentifierType(concept);

		String idTypeSe = vpmh.getIdentifierType(se);
		String idTypeCategory = vpmh.getIdentifierType(category);
		String idTypeIp = vpmh.getIdentifierType(ip);
		
		String idTypeSeiContaining = vpmh.getIdentifierType(seiContaining);
		String idTypeSeiContained = vpmh.getIdentifierType(seiContained);
		
		String idTypeCaOfContainingSei = vpmh.getIdentifierType(caOfContainingSei);
		String idTypeCa1OfContainedSei = vpmh.getIdentifierType(ca1OfContainedSei);
		String idTypeCa2OfContainedSei = vpmh.getIdentifierType(ca2OfContainedSei);
		
		String idTypeVpiOfCaOfContainingSei = vpmh.getIdentifierType(vpiOfCaOfContainingSei);
		String idTypeVpiOfCa1OfContainedSei = vpmh.getIdentifierType(vpiOfCa1OfContainedSei);
		String idTypeVpiOfCa2OfContainedSei = vpmh.getIdentifierType(vpiOfCa2OfContainedSei);
		
		String idTypeProject = vpmh.getIdentifierType(project);
		
		String whatever = "WhatEver";
		String idTypeOfWhatever = vpmh.getIdentifierType(whatever);
		
		assertEquals("Correct Identifier Type", VirSatProblemMarkerHelper.ID_TYPE_IUUID, idTypeRepo);
		assertEquals("Correct Identifier Type", VirSatProblemMarkerHelper.ID_TYPE_IQUALIFIEDNAME, idTypeConcept);
		
		assertEquals("Correct Identifier Type", VirSatProblemMarkerHelper.ID_TYPE_IQUALIFIEDNAME, idTypeSe);
		assertEquals("Correct Identifier Type", VirSatProblemMarkerHelper.ID_TYPE_IQUALIFIEDNAME, idTypeCategory);
		assertEquals("Correct Identifier Type", VirSatProblemMarkerHelper.ID_TYPE_IQUALIFIEDNAME, idTypeIp);
		
		assertEquals("Correct Identifier Type", VirSatProblemMarkerHelper.ID_TYPE_IUUID, idTypeSeiContaining);
		assertEquals("Correct Identifier Type", VirSatProblemMarkerHelper.ID_TYPE_IUUID, idTypeSeiContained);
		
		assertEquals("Correct Identifier Type", VirSatProblemMarkerHelper.ID_TYPE_IUUID, idTypeCaOfContainingSei);
		assertEquals("Correct Identifier Type", VirSatProblemMarkerHelper.ID_TYPE_IUUID, idTypeCa1OfContainedSei);
		assertEquals("Correct Identifier Type", VirSatProblemMarkerHelper.ID_TYPE_IUUID, idTypeCa2OfContainedSei);
		
		assertEquals("Correct Identifier Type", VirSatProblemMarkerHelper.ID_TYPE_IUUID, idTypeVpiOfCaOfContainingSei);
		assertEquals("Correct Identifier Type", VirSatProblemMarkerHelper.ID_TYPE_IUUID, idTypeVpiOfCa1OfContainedSei);
		assertEquals("Correct Identifier Type", VirSatProblemMarkerHelper.ID_TYPE_IUUID, idTypeVpiOfCa2OfContainedSei);
		
		assertEquals("Correct Identifier Type", VirSatProblemMarkerHelper.ID_TYPE_NOT_VALID, idTypeProject);
		assertEquals("Correct Identifier Type", VirSatProblemMarkerHelper.ID_TYPE_NOT_VALID, idTypeOfWhatever);
	}
	
	private void assertEqualsMarkerHierarchy(IResource res, int eq1, int eq2, int eq3) throws CoreException {
		assertEquals("Correct Amount of all Markers", eq1, res.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("Correct Amount of VirSat Markers", eq2, res.findMarkers(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, true, IResource.DEPTH_INFINITE).length);
		assertEquals("Correct Amount of VirSat Test Markers", eq3, res.findMarkers(ID_TESTMARKER, true, IResource.DEPTH_INFINITE).length);
	}
	
	@Test
	public void testCreateMarker_OnResource() throws CoreException {
		assertEqualsMarkerHierarchy(fileRepo, 0, 0, 0);
		assertEqualsMarkerHierarchy(fileSeiContaining, 0, 0, 0);
		assertEqualsMarkerHierarchy(fileSeiContained, 0, 0, 0);
		
		vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", fileRepo);
		
		assertEqualsMarkerHierarchy(fileRepo, 1, 1, 0);
		assertEqualsMarkerHierarchy(fileSeiContaining, 0, 0, 0);
		assertEqualsMarkerHierarchy(fileSeiContained, 0, 0, 0);
		
		IMarker marker = vpmh.createMarker(IMarker.PROBLEM, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining Resource", fileSeiContaining);
		
		assertTrue("Marker is not created", marker == null);
		assertEqualsMarkerHierarchy(fileRepo, 1, 1, 0);
		assertEqualsMarkerHierarchy(fileSeiContaining, 0, 0, 0);
		assertEqualsMarkerHierarchy(fileSeiContained, 0, 0, 0);
		
		vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining Resource", fileSeiContaining);
		
		assertEqualsMarkerHierarchy(fileRepo, 1, 1, 0);
		assertEqualsMarkerHierarchy(fileSeiContaining, 1, 1, 0);
		assertEqualsMarkerHierarchy(fileSeiContained, 0, 0, 0);

		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained Resource", fileSeiContained);
		
		assertEqualsMarkerHierarchy(fileRepo, 1, 1, 0);
		assertEqualsMarkerHierarchy(fileSeiContaining, 1, 1, 0);
		assertEqualsMarkerHierarchy(fileSeiContained, 1, 1, 1);
		
		IResource nullResource = null;
		marker = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on a null Resource", nullResource);
		assertTrue("The Marker is null", null == marker);
	}
	
	@Test
	public void testCreateMarker_OnObject() throws CoreException {
		assertEqualsMarkerHierarchy(fileRepo, 0, 0, 0);
		assertEqualsMarkerHierarchy(fileSeiContaining, 0, 0, 0);
		assertEqualsMarkerHierarchy(fileSeiContained, 0, 0, 0);
		
		vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		
		assertEqualsMarkerHierarchy(fileRepo, 1, 1, 0);
		assertEqualsMarkerHierarchy(fileSeiContaining, 0, 0, 0);
		assertEqualsMarkerHierarchy(fileSeiContained, 0, 0, 0);
		
		IMarker marker = vpmh.createMarker(IMarker.PROBLEM, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		
		assertTrue("Marker is not created", marker == null);
		assertEqualsMarkerHierarchy(fileRepo, 1, 1, 0);
		assertEqualsMarkerHierarchy(fileSeiContaining, 0, 0, 0);
		assertEqualsMarkerHierarchy(fileSeiContained, 0, 0, 0);

		vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		
		assertEqualsMarkerHierarchy(fileRepo, 1, 1, 0);
		assertEqualsMarkerHierarchy(fileSeiContaining, 1, 1, 0);
		assertEqualsMarkerHierarchy(fileSeiContained, 0, 0, 0);
		
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		
		assertEqualsMarkerHierarchy(fileRepo, 1, 1, 0);
		assertEqualsMarkerHierarchy(fileSeiContaining, 1, 1, 0);
		assertEqualsMarkerHierarchy(fileSeiContained, 1, 1, 1);
		
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the CaOfContainingSei", caOfContainingSei);

		assertEqualsMarkerHierarchy(fileRepo, 1, 1, 0);
		assertEqualsMarkerHierarchy(fileSeiContaining, 2, 2, 1);
		assertEqualsMarkerHierarchy(fileSeiContained, 1, 1, 1);
		
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);

		assertEqualsMarkerHierarchy(fileRepo, 1, 1, 0);
		assertEqualsMarkerHierarchy(fileSeiContaining, 2, 2, 1);
		assertEqualsMarkerHierarchy(fileSeiContained, 2, 2, 2);		
		
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Concept", concept);

		assertEqualsMarkerHierarchy(fileRepo, 2, 2, 1);
		assertEqualsMarkerHierarchy(fileSeiContaining, 2, 2, 1);
		assertEqualsMarkerHierarchy(fileSeiContained, 2, 2, 2);		
				
		fileSeiContained.delete(true, null);
		
		marker = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained with a deleted resource", fileSeiContained);
		assertTrue("The Marker is null", null == marker);
		
		EAttribute whatever = GeneralPackage.Literals.IQUALIFIED_NAME__NAME;
		marker = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on some EAttribute", whatever);
		assertTrue("The Marker is null", null == marker);
		
		marker = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the EquationSection", eqSectionOfCaEqOfContainingSei);
		assertTrue("The Marker is null", null == marker);
		
		EObject nullObject = null;
		marker = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on a null object", nullObject);
		assertTrue("The Marker is null", null == marker);
	}
	
	@Test 
	public void testCreateVirSatMarker() throws CoreException {
		vpmh.createVirSatMarker(IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		assertEquals("Correct Amount of VirSat Markers", 1, fileRepo.findMarkers(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, true, IResource.DEPTH_INFINITE).length);
	}

	@Test
	public void testIsAssociatedWith() {
		IMarker markerRepo = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		IMarker markerConcept = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Concept", concept);
		IMarker markerSeiContaining = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		IMarker markerSeiContained = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		IMarker markerCaOfContainingSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the CaOfContainingSei", caOfContainingSei);
		IMarker markerCa1OfContainedSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the ca1OfContainedSei", ca1OfContainedSei);
		IMarker markerCa2OfContainedSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the ca2OfContainedSei", ca2OfContainedSei);
		IMarker markerVpiOfCaOfContainingSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCaOfContainingSei", vpiOfCaOfContainingSei);
		IMarker markerVpiOfCa1OfContainedSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);
		IMarker markerVpiOfCa2OfContainedSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa2OfContainedSei", vpiOfCa2OfContainedSei);
		
		assertTrue("Correct Association", vpmh.isAssociatedWith(markerRepo, repo));
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerConcept, repo));
		assertTrue("Correct Association", vpmh.isAssociatedWith(markerConcept, concept));
		
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerRepo, seiContaining));
		assertTrue("Correct Association", vpmh.isAssociatedWith(markerSeiContaining, seiContaining));
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerSeiContained, seiContaining));
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerCaOfContainingSei, seiContaining));
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerVpiOfCaOfContainingSei, seiContaining));
		
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerRepo, seiContained));
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerSeiContaining, seiContained));
		assertTrue("Correct Association", vpmh.isAssociatedWith(markerSeiContained, seiContained));
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerCa1OfContainedSei, seiContained));
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerCa2OfContainedSei, seiContained));
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerVpiOfCa1OfContainedSei, seiContained));
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerVpiOfCa2OfContainedSei, seiContained));
		
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerRepo, caOfContainingSei));
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerSeiContaining, caOfContainingSei));
		assertTrue("Correct Association", vpmh.isAssociatedWith(markerCaOfContainingSei, caOfContainingSei));
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerVpiOfCaOfContainingSei, caOfContainingSei));
		
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerRepo, vpiOfCa2OfContainedSei));
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerSeiContained, vpiOfCa2OfContainedSei));
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerCa1OfContainedSei, vpiOfCa2OfContainedSei));
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerCa2OfContainedSei, vpiOfCa2OfContainedSei));
		assertFalse("Correct Association", vpmh.isAssociatedWith(markerVpiOfCa1OfContainedSei, vpiOfCa2OfContainedSei));
		assertTrue("Correct Association", vpmh.isAssociatedWith(markerVpiOfCa2OfContainedSei, vpiOfCa2OfContainedSei));
		
	}
	
	@Test
	public void testGetMarkers_OnResource() {
		IMarker markerRepo = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		IMarker markerConcept = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Concept", concept);
		IMarker markerSeiContaining = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		IMarker markerSeiContained = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		IMarker markerCa = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the CaOfContainingSei", caOfContainingSei);
		IMarker markerVpi = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);
		
		Set<IMarker> virsatMarkersRepo = vpmh.getMarkers(fileRepo, VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER);
		Set<IMarker> testMarkersRepo = vpmh.getMarkers(fileRepo, ID_TESTMARKER);
		
		assertEquals("Set has correct size", 2, virsatMarkersRepo.size());
		assertEquals("Set has correct size", 1, testMarkersRepo.size());
		
		assertTrue("Correct Markers in Set", virsatMarkersRepo.contains(markerRepo) && virsatMarkersRepo.contains(markerConcept));
		assertTrue("Correct Markers in Set", testMarkersRepo.contains(markerConcept));
		
		Set<IMarker> virsatMarkersSeiContaining = vpmh.getMarkers(fileSeiContaining, VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER);
		Set<IMarker> testMarkersSeiContaining = vpmh.getMarkers(fileSeiContaining, ID_TESTMARKER);
		
		assertEquals("Set has correct size", 2, virsatMarkersSeiContaining.size());
		assertEquals("Set has correct size", 1, testMarkersSeiContaining.size());
		
		assertTrue("Correct Markers in Set", virsatMarkersSeiContaining.contains(markerSeiContaining) && virsatMarkersSeiContaining.contains(markerCa));
		assertTrue("Correct Markers in Set", testMarkersSeiContaining.contains(markerCa));
		
		Set<IMarker> virsatMarkersSeiContained = vpmh.getMarkers(fileSeiContained, VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER);
		Set<IMarker> testMarkersSeiContained = vpmh.getMarkers(fileSeiContained, ID_TESTMARKER);
		
		assertEquals("Set has correct size", 2, virsatMarkersSeiContained.size());
		assertEquals("Set has correct size", 2, testMarkersSeiContained.size());
		
		assertTrue("Correct Markers in Set", virsatMarkersSeiContained.contains(markerSeiContained) && virsatMarkersSeiContained.contains(markerVpi));
		assertTrue("Correct Markers in Set", testMarkersSeiContained.contains(markerSeiContained) && testMarkersSeiContained.contains(markerVpi));
	}
	
	@Test
	public void testGetMarkersOfResource() {
		IMarker markerRepo = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		IMarker markerConcept = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Concept", concept);
		IMarker markerSeiContaining = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		IMarker markerSeiContained = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		IMarker markerCa = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the CaOfContainingSei", caOfContainingSei);
		IMarker markerVpi = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);
		
		Set<IMarker> repoMarkersRepo = vpmh.getMarkersOfResource(repo, ID_TESTMARKER);
		Set<IMarker> repoMarkersConcept = vpmh.getMarkersOfResource(concept, ID_TESTMARKER);
		
		assertEquals("Sets are equal", repoMarkersRepo, repoMarkersConcept);
		assertEquals("Set has correct size", 2, repoMarkersRepo.size());
		assertTrue("Correct Markers in Set", repoMarkersRepo.contains(markerRepo) && repoMarkersRepo.contains(markerConcept));
		
		Set<IMarker> repoMarkersSeiContaining = vpmh.getMarkersOfResource(seiContaining, ID_TESTMARKER);
		Set<IMarker> repoMarkersCa = vpmh.getMarkersOfResource(caOfContainingSei, ID_TESTMARKER);
		
		assertEquals("Sets are equal", repoMarkersSeiContaining, repoMarkersCa);
		assertEquals("Set has correct size", 2, repoMarkersSeiContaining.size());
		assertTrue("Correct Markers in Set", repoMarkersSeiContaining.contains(markerSeiContaining) && repoMarkersSeiContaining.contains(markerCa));
		
		Set<IMarker> repoMarkersSeiContained = vpmh.getMarkersOfResource(seiContained, ID_TESTMARKER);
		Set<IMarker> repoMarkersVpi = vpmh.getMarkersOfResource(vpiOfCa1OfContainedSei, ID_TESTMARKER);
		
		assertEquals("Sets are equal", repoMarkersSeiContained, repoMarkersVpi);
		assertEquals("Set has correct size", 2, repoMarkersSeiContained.size());
		assertTrue("Correct Markers in Set", repoMarkersSeiContained.contains(markerSeiContained) && repoMarkersSeiContained.contains(markerVpi));
	}
	
	@Test
	public void testGetMarkers_OnObject() {
		IMarker markerRepo = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		IMarker markerConcept = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Concept", concept);
		IMarker markerSeiContaining = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		IMarker markerSeiContained = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		IMarker markerCa = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the CaOfContainingSei", caOfContainingSei);
		IMarker markerVpi = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);
		
		Set<IMarker> markersRepo = vpmh.getMarkers(repo, ID_TESTMARKER);
		Set<IMarker> markersConcept = vpmh.getMarkers(concept, ID_TESTMARKER);
		Set<IMarker> markersSeiContaining = vpmh.getMarkers(seiContaining, ID_TESTMARKER);
		Set<IMarker> markersCa = vpmh.getMarkers(caOfContainingSei, ID_TESTMARKER);
		Set<IMarker> markersSeiContained = vpmh.getMarkers(seiContained, ID_TESTMARKER);
		Set<IMarker> markersVpi = vpmh.getMarkers(vpiOfCa1OfContainedSei, ID_TESTMARKER);
		
		assertEquals("Set has correct size", 1, markersRepo.size());
		assertEquals("Set has correct size", 1, markersConcept.size());
		assertEquals("Set has correct size", 1, markersSeiContaining.size());
		assertEquals("Set has correct size", 1, markersCa.size());
		assertEquals("Set has correct size", 1, markersSeiContained.size());
		assertEquals("Set has correct size", 1, markersVpi.size());
		
		assertTrue("Correct Markers in Set", markersRepo.contains(markerRepo));
		assertTrue("Correct Markers in Set", markersConcept.contains(markerConcept));
		assertTrue("Correct Markers in Set", markersSeiContaining.contains(markerSeiContaining));
		assertTrue("Correct Markers in Set", markersSeiContained.contains(markerSeiContained));
		assertTrue("Correct Markers in Set", markersCa.contains(markerCa));
		assertTrue("Correct Markers in Set", markersVpi.contains(markerVpi));
	}
	
	@Test
	public void testGetAllMarkers() {
		IMarker testMarkerRepo = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		IMarker virsatMarkerRepo = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		
		IMarker testMarkerConcept = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Concept", concept);
		IMarker virsatMarkerConcept = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", concept);
		
		IMarker testMarkerSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		IMarker virsatMarkerSei = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", seiContained);

		Set<IMarker> markersRepo = vpmh.getAllMarkers(repo);
		Set<IMarker> markersConcept = vpmh.getAllMarkers(concept);
		Set<IMarker> markersSei = vpmh.getAllMarkers(seiContained);
		
		assertEquals("Set has correct size", 2, markersRepo.size());
		assertEquals("Set has correct size", 2, markersConcept.size());
		assertEquals("Set has correct size", 2, markersSei.size());
		
		assertTrue("Correct Markers in Set", markersRepo.contains(testMarkerRepo) && markersRepo.contains(virsatMarkerRepo));
		assertTrue("Correct Markers in Set", markersConcept.contains(testMarkerConcept) && markersConcept.contains(virsatMarkerConcept));
		assertTrue("Correct Markers in Set", markersSei.contains(testMarkerSei) && markersSei.contains(virsatMarkerSei));
	}
	
	@Test
	public void testGetMarkersForObjectAndContents() {
		IMarker markerRepo = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		IMarker markerConcept = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Concept", concept);
		IMarker markerSeiContaining = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		IMarker markerSeiContained = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		IMarker markerCaOfContainingSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the CaOfContainingSei", caOfContainingSei);
		IMarker markerCa2OfContainedSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Ca2OfContainedSei", ca2OfContainedSei);
		IMarker markerVpiOfCa1OfContainedSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);
		
		Set<IMarker> markersRepo = vpmh.getMarkersForObjectAndContents(repo, ID_TESTMARKER);
		Set<IMarker> markersConcept = vpmh.getMarkersForObjectAndContents(concept, ID_TESTMARKER);
		Set<IMarker> markersSeiContaining = vpmh.getMarkersForObjectAndContents(seiContaining, ID_TESTMARKER);
		Set<IMarker> markersSeiContained = vpmh.getMarkersForObjectAndContents(seiContained, ID_TESTMARKER);
		Set<IMarker> markersCaOfContainingSei = vpmh.getMarkersForObjectAndContents(caOfContainingSei, ID_TESTMARKER);
		Set<IMarker> markersCa2OfContainedSei = vpmh.getMarkersForObjectAndContents(ca2OfContainedSei, ID_TESTMARKER);
		Set<IMarker> markersVpiOfCa1OfContainedSei = vpmh.getMarkersForObjectAndContents(vpiOfCa1OfContainedSei, ID_TESTMARKER);
		
		assertEquals("Set has correct size", 2, markersRepo.size());
		assertEquals("Set has correct size", 1, markersConcept.size());
		assertEquals("Set has correct size", 2, markersSeiContaining.size());
		assertEquals("Set has correct size", 1, markersCaOfContainingSei.size());
		assertEquals("Set has correct size", 3, markersSeiContained.size());
		assertEquals("Set has correct size", 1, markersCa2OfContainedSei.size());
		assertEquals("Set has correct size", 1, markersVpiOfCa1OfContainedSei.size());
		
		assertTrue("Correct Markers in Set", markersRepo.contains(markerRepo) && markersRepo.contains(markerConcept));
		assertTrue("Correct Markers in Set", markersConcept.contains(markerConcept));
		assertTrue("Correct Markers in Set", markersSeiContaining.contains(markerSeiContaining) && markersSeiContaining.contains(markerCaOfContainingSei));
		assertTrue("Correct Markers in Set", markersCaOfContainingSei.contains(markerCaOfContainingSei));
		assertTrue("Correct Markers in Set", markersSeiContained.contains(markerSeiContained) && markersSeiContained.contains(markerCa2OfContainedSei) && markersSeiContained.contains(markerVpiOfCa1OfContainedSei));
		assertTrue("Correct Markers in Set", markersCa2OfContainedSei.contains(markerCa2OfContainedSei));
		assertTrue("Correct Markers in Set", markersVpiOfCa1OfContainedSei.contains(markerVpiOfCa1OfContainedSei));
	}
	
	@Test
	public void testGetAllMarkersForObjectAndContents() {
		IMarker testMarkerSeiContained = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		IMarker testMarkerCa2OfContainedSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Ca2OfContainedSei", ca2OfContainedSei);
		IMarker testMarkerVpiOfCa1OfContainedSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);
		IMarker virsatMarkerSeiContained = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		IMarker virsatMarkerCa2OfContainedSei = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Ca2OfContainedSei", ca2OfContainedSei);
		IMarker virsatMarkerVpiOfCa1OfContainedSei = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);
		
		Set<IMarker> markersSeiContained = vpmh.getAllMarkersForObjectAndContents(seiContained);
		Set<IMarker> markersCa2OfContainedSei = vpmh.getAllMarkersForObjectAndContents(ca2OfContainedSei);
		Set<IMarker> markersVpiOfCa1OfContainedSei = vpmh.getAllMarkersForObjectAndContents(vpiOfCa1OfContainedSei);
		
		assertEquals("Set has correct size", 6, markersSeiContained.size());
		assertEquals("Set has correct size", 2, markersCa2OfContainedSei.size());
		assertEquals("Set has correct size", 2, markersVpiOfCa1OfContainedSei.size());
		
		assertTrue("Correct Markers in Set", markersSeiContained.contains(testMarkerSeiContained) && markersSeiContained.contains(testMarkerCa2OfContainedSei) && markersSeiContained.contains(testMarkerVpiOfCa1OfContainedSei));
		assertTrue("Correct Markers in Set", markersCa2OfContainedSei.contains(testMarkerCa2OfContainedSei));
		assertTrue("Correct Markers in Set", markersVpiOfCa1OfContainedSei.contains(testMarkerVpiOfCa1OfContainedSei));
		assertTrue("Correct Markers in Set", markersSeiContained.contains(virsatMarkerSeiContained) && markersSeiContained.contains(virsatMarkerCa2OfContainedSei) && markersSeiContained.contains(virsatMarkerVpiOfCa1OfContainedSei));
		assertTrue("Correct Markers in Set", markersCa2OfContainedSei.contains(virsatMarkerCa2OfContainedSei));
		assertTrue("Correct Markers in Set", markersVpiOfCa1OfContainedSei.contains(virsatMarkerVpiOfCa1OfContainedSei));
	}

	@Test
	public void testDeleteMarkers_OnResource() {
		IMarker markerRepo = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Concept", concept);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the CaOfContainingSei", caOfContainingSei);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Ca2OfContainedSei", ca2OfContainedSei);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);

		Set<IMarker> markersRepo = vpmh.getMarkers(fileRepo, VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER);
		Set<IMarker> markersSeiContaining = vpmh.getMarkers(fileSeiContaining, VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER);
		Set<IMarker> markersSeiContained = vpmh.getMarkers(fileSeiContained, VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER);
		
		assertEquals("Set has correct size", 2, markersRepo.size());
		assertEquals("Set has correct size", 2, markersSeiContaining.size());
		assertEquals("Set has correct size", 3, markersSeiContained.size());
		
		vpmh.deleteMarkers(fileRepo, ID_TESTMARKER);
		vpmh.deleteMarkers(fileSeiContained, ID_TESTMARKER);
		vpmh.deleteMarkers(fileSeiContaining, ID_TESTMARKER);
		
		markersRepo = vpmh.getAllMarkers(repo);
		markersSeiContaining = vpmh.getAllMarkers(seiContaining);
		markersSeiContained = vpmh.getAllMarkers(seiContained);
		
		assertEquals("Set has correct size", 1, markersRepo.size());
		assertEquals("Set has correct size", 0, markersSeiContaining.size());
		assertEquals("Set has correct size", 0, markersSeiContained.size());
		
		assertTrue("Contains correct element", markersRepo.contains(markerRepo));
	}

	@Test
	public void testDeleteMarkersOfResource() {
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);
		IMarker marker = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Ca2OfContainedSei", ca2OfContainedSei);
		
		Set<IMarker> markersSeiContained = vpmh.getMarkers(fileSeiContained, VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER);
		assertEquals("Set has correct size", 3, markersSeiContained.size());
		
		vpmh.deleteMarkersOfResource(vpiOfCa2OfContainedSei, ID_TESTMARKER);

		markersSeiContained = vpmh.getMarkers(fileSeiContained, VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER);
		assertEquals("Set has correct size", 1, markersSeiContained.size());
		assertTrue("Remaining element is correct", markersSeiContained.contains(marker));
	}
	
	@Test
	public void testDeleteMarkers_OnObject() {
		IMarker markerRepo = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Concept", concept);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		IMarker markerCaOfContainingSei = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the CaOfContainingSei", caOfContainingSei);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Ca2OfContainedSei", ca2OfContainedSei);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);
		
		Set<IMarker> markersRepo = vpmh.getAllMarkers(repo);
		Set<IMarker> markersConcept = vpmh.getAllMarkers(concept);
		Set<IMarker> markersSeiContaining = vpmh.getAllMarkers(seiContaining);
		Set<IMarker> markersSeiContained = vpmh.getAllMarkers(seiContained);
		Set<IMarker> markersCaOfContainingSei = vpmh.getAllMarkers(caOfContainingSei);
		Set<IMarker> markersCa2OfContainedSei = vpmh.getAllMarkers(ca2OfContainedSei);
		Set<IMarker> markersVpiOfCa1OfContainedSei = vpmh.getAllMarkers(vpiOfCa1OfContainedSei);
		
		assertEquals("Set has correct size", 1, markersRepo.size());
		assertEquals("Set has correct size", 1, markersConcept.size());
		assertEquals("Set has correct size", 1, markersSeiContaining.size());
		assertEquals("Set has correct size", 1, markersCaOfContainingSei.size());
		assertEquals("Set has correct size", 1, markersSeiContained.size());
		assertEquals("Set has correct size", 1, markersCa2OfContainedSei.size());
		assertEquals("Set has correct size", 1, markersVpiOfCa1OfContainedSei.size());
		
		vpmh.deleteMarkers(repo, ID_TESTMARKER);
		vpmh.deleteMarkers(concept, ID_TESTMARKER);
		vpmh.deleteMarkers(seiContaining, ID_TESTMARKER);
		vpmh.deleteMarkers(seiContained, ID_TESTMARKER);
		vpmh.deleteMarkers(caOfContainingSei, ID_TESTMARKER);
		vpmh.deleteMarkers(ca2OfContainedSei, ID_TESTMARKER);
		vpmh.deleteMarkers(vpiOfCa1OfContainedSei, ID_TESTMARKER);
		
		markersRepo = vpmh.getAllMarkers(repo);
		markersConcept = vpmh.getAllMarkers(concept);
		markersSeiContaining = vpmh.getAllMarkers(seiContaining);
		markersSeiContained = vpmh.getAllMarkers(seiContained);
		markersCaOfContainingSei = vpmh.getAllMarkers(caOfContainingSei);
		markersCa2OfContainedSei = vpmh.getAllMarkers(ca2OfContainedSei);
		markersVpiOfCa1OfContainedSei = vpmh.getAllMarkers(vpiOfCa1OfContainedSei);
		
		assertEquals("Set has correct size", 1, markersRepo.size());
		assertEquals("Set has correct size", 0, markersConcept.size());
		assertEquals("Set has correct size", 0, markersSeiContaining.size());
		assertEquals("Set has correct size", 1, markersCaOfContainingSei.size());
		assertEquals("Set has correct size", 0, markersSeiContained.size());
		assertEquals("Set has correct size", 0, markersCa2OfContainedSei.size());
		assertEquals("Set has correct size", 0, markersVpiOfCa1OfContainedSei.size());
		
		assertTrue("Contains correct element", markersRepo.contains(markerRepo));
		assertTrue("Contains correct element", markersCaOfContainingSei.contains(markerCaOfContainingSei));
	}
	
	@Test
	public void testDeleteAllMarkers() {
		vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Concept", concept);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the CaOfContainingSei", caOfContainingSei);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Ca2OfContainedSei", ca2OfContainedSei);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);
		
		Set<IMarker> markersRepo = vpmh.getAllMarkers(repo);
		Set<IMarker> markersConcept = vpmh.getAllMarkers(concept);
		Set<IMarker> markersSeiContaining = vpmh.getAllMarkers(seiContaining);
		Set<IMarker> markersSeiContained = vpmh.getAllMarkers(seiContained);
		Set<IMarker> markersCaOfContainingSei = vpmh.getAllMarkers(caOfContainingSei);
		Set<IMarker> markersCa2OfContainedSei = vpmh.getAllMarkers(ca2OfContainedSei);
		Set<IMarker> markersVpiOfCa1OfContainedSei = vpmh.getAllMarkers(vpiOfCa1OfContainedSei);
		
		assertEquals("Set has correct size", 1, markersRepo.size());
		assertEquals("Set has correct size", 1, markersConcept.size());
		assertEquals("Set has correct size", 1, markersSeiContaining.size());
		assertEquals("Set has correct size", 1, markersCaOfContainingSei.size());
		assertEquals("Set has correct size", 1, markersSeiContained.size());
		assertEquals("Set has correct size", 1, markersCa2OfContainedSei.size());
		assertEquals("Set has correct size", 1, markersVpiOfCa1OfContainedSei.size());
		
		vpmh.deleteAllMarkers(repo);
		vpmh.deleteAllMarkers(concept);
		vpmh.deleteAllMarkers(seiContaining);
		vpmh.deleteAllMarkers(seiContained);
		vpmh.deleteAllMarkers(caOfContainingSei);
		vpmh.deleteAllMarkers(ca2OfContainedSei);
		vpmh.deleteAllMarkers(vpiOfCa1OfContainedSei);
		
		markersRepo = vpmh.getAllMarkers(repo);
		markersConcept = vpmh.getAllMarkers(concept);
		markersSeiContaining = vpmh.getAllMarkers(seiContaining);
		markersSeiContained = vpmh.getAllMarkers(seiContained);
		markersCaOfContainingSei = vpmh.getAllMarkers(caOfContainingSei);
		markersCa2OfContainedSei = vpmh.getAllMarkers(ca2OfContainedSei);
		markersVpiOfCa1OfContainedSei = vpmh.getAllMarkers(vpiOfCa1OfContainedSei);
		
		assertEquals("Set has correct size", 0, markersRepo.size());
		assertEquals("Set has correct size", 0, markersConcept.size());
		assertEquals("Set has correct size", 0, markersSeiContaining.size());
		assertEquals("Set has correct size", 0, markersCaOfContainingSei.size());
		assertEquals("Set has correct size", 0, markersSeiContained.size());
		assertEquals("Set has correct size", 0, markersCa2OfContainedSei.size());
		assertEquals("Set has correct size", 0, markersVpiOfCa1OfContainedSei.size());
	}
	
	@Test
	public void testDeleteMarkersForObjectAndContents() {
		IMarker markerRepo = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Concept", concept);
		IMarker markerSeiContaining = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		IMarker markerSeiContained = vpmh.createMarker( VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the CaOfContainingSei", caOfContainingSei);
		IMarker markerCa2OfContainedSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Ca2OfContainedSei", ca2OfContainedSei);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);
		
		Set<IMarker> markersRepo = vpmh.getAllMarkersForObjectAndContents(repo);
		Set<IMarker> markersConcept = vpmh.getAllMarkersForObjectAndContents(concept);
		Set<IMarker> markersSeiContaining = vpmh.getAllMarkersForObjectAndContents(seiContaining);
		Set<IMarker> markersSeiContained = vpmh.getAllMarkersForObjectAndContents(seiContained);
		Set<IMarker> markersCaOfContainingSei = vpmh.getAllMarkersForObjectAndContents(caOfContainingSei);
		Set<IMarker> markersCa2OfContainedSei = vpmh.getAllMarkersForObjectAndContents(ca2OfContainedSei);
		Set<IMarker> markersVpiOfCa1OfContainedSei = vpmh.getAllMarkersForObjectAndContents(vpiOfCa1OfContainedSei);
		
		assertEquals("Set has correct size", 2, markersRepo.size());
		assertEquals("Set has correct size", 1, markersConcept.size());
		assertEquals("Set has correct size", 2, markersSeiContaining.size());
		assertEquals("Set has correct size", 1, markersCaOfContainingSei.size());
		assertEquals("Set has correct size", 3, markersSeiContained.size());
		assertEquals("Set has correct size", 1, markersCa2OfContainedSei.size());
		assertEquals("Set has correct size", 1, markersVpiOfCa1OfContainedSei.size());
		
		vpmh.deleteMarkersForObjectAndContents(repo, ID_TESTMARKER);
		vpmh.deleteMarkersForObjectAndContents(ca1OfContainedSei, ID_TESTMARKER);
		vpmh.deleteMarkersForObjectAndContents(caOfContainingSei, ID_TESTMARKER);
		
		markersRepo = vpmh.getAllMarkersForObjectAndContents(repo);
		markersConcept = vpmh.getAllMarkersForObjectAndContents(concept);
		markersSeiContaining = vpmh.getAllMarkersForObjectAndContents(seiContaining);
		markersSeiContained = vpmh.getAllMarkersForObjectAndContents(seiContained);
		markersCaOfContainingSei = vpmh.getAllMarkersForObjectAndContents(caOfContainingSei);
		markersCa2OfContainedSei = vpmh.getAllMarkersForObjectAndContents(ca2OfContainedSei);
		markersVpiOfCa1OfContainedSei = vpmh.getAllMarkersForObjectAndContents(vpiOfCa1OfContainedSei);
		
		assertEquals("Set has correct size", 1, markersRepo.size());
		assertEquals("Set has correct size", 0, markersConcept.size());
		assertEquals("Set has correct size", 1, markersSeiContaining.size());
		assertEquals("Set has correct size", 0, markersCaOfContainingSei.size());
		assertEquals("Set has correct size", 2, markersSeiContained.size());
		assertEquals("Set has correct size", 1, markersCa2OfContainedSei.size());
		assertEquals("Set has correct size", 0, markersVpiOfCa1OfContainedSei.size());

		assertTrue("Contains correct element", markersRepo.contains(markerRepo));
		assertTrue("Contains correct element", markersSeiContaining.contains(markerSeiContaining));
		assertTrue("Contains correct element", markersSeiContained.contains(markerSeiContained) && markersSeiContained.contains(markerCa2OfContainedSei));
		assertTrue("Contains correct element", markersCa2OfContainedSei.contains(markerCa2OfContainedSei));
	}

	@Test
	public void testDeleteAllMarkersForObjectAndContents() {
		vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Concept", concept);
		IMarker markerSeiContaining = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		IMarker markerSeiContained = vpmh.createMarker( VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the CaOfContainingSei", caOfContainingSei);
		IMarker markerCa2OfContainedSei = vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Ca2OfContainedSei", ca2OfContainedSei);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);
		
		Set<IMarker> markersRepo = vpmh.getAllMarkersForObjectAndContents(repo);
		Set<IMarker> markersConcept = vpmh.getAllMarkersForObjectAndContents(concept);
		Set<IMarker> markersSeiContaining = vpmh.getAllMarkersForObjectAndContents(seiContaining);
		Set<IMarker> markersSeiContained = vpmh.getAllMarkersForObjectAndContents(seiContained);
		Set<IMarker> markersCaOfContainingSei = vpmh.getAllMarkersForObjectAndContents(caOfContainingSei);
		Set<IMarker> markersCa2OfContainedSei = vpmh.getAllMarkersForObjectAndContents(ca2OfContainedSei);
		Set<IMarker> markersVpiOfCa1OfContainedSei = vpmh.getAllMarkersForObjectAndContents(vpiOfCa1OfContainedSei);
		
		assertEquals("Set has correct size", 2, markersRepo.size());
		assertEquals("Set has correct size", 1, markersConcept.size());
		assertEquals("Set has correct size", 2, markersSeiContaining.size());
		assertEquals("Set has correct size", 1, markersCaOfContainingSei.size());
		assertEquals("Set has correct size", 3, markersSeiContained.size());
		assertEquals("Set has correct size", 1, markersCa2OfContainedSei.size());
		assertEquals("Set has correct size", 1, markersVpiOfCa1OfContainedSei.size());
		
		vpmh.deleteAllMarkersForObjectAndContents(repo);
		vpmh.deleteAllMarkersForObjectAndContents(ca1OfContainedSei);
		vpmh.deleteAllMarkersForObjectAndContents(caOfContainingSei);
		
		markersRepo = vpmh.getAllMarkersForObjectAndContents(repo);
		markersConcept = vpmh.getAllMarkersForObjectAndContents(concept);
		markersSeiContaining = vpmh.getAllMarkersForObjectAndContents(seiContaining);
		markersSeiContained = vpmh.getAllMarkersForObjectAndContents(seiContained);
		markersCaOfContainingSei = vpmh.getAllMarkersForObjectAndContents(caOfContainingSei);
		markersCa2OfContainedSei = vpmh.getAllMarkersForObjectAndContents(ca2OfContainedSei);
		markersVpiOfCa1OfContainedSei = vpmh.getAllMarkersForObjectAndContents(vpiOfCa1OfContainedSei);
		
		assertEquals("Set has correct size", 0, markersRepo.size());
		assertEquals("Set has correct size", 0, markersConcept.size());
		assertEquals("Set has correct size", 1, markersSeiContaining.size());
		assertEquals("Set has correct size", 0, markersCaOfContainingSei.size());
		assertEquals("Set has correct size", 2, markersSeiContained.size());
		assertEquals("Set has correct size", 1, markersCa2OfContainedSei.size());
		assertEquals("Set has correct size", 0, markersVpiOfCa1OfContainedSei.size());
		
		assertTrue("Contains correct element", markersSeiContaining.contains(markerSeiContaining));
		assertTrue("Contains correct element", markersSeiContained.contains(markerSeiContained) && markersSeiContained.contains(markerCa2OfContainedSei));
		assertTrue("Contains correct element", markersCa2OfContainedSei.contains(markerCa2OfContainedSei));
	}
	
	@Test
	public void testDeleteMarkersInWorkspace() {
		IMarker markerRepo = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Concept", concept);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		IMarker markerSeiContained = vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the CaOfContainingSei", caOfContainingSei);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Ca2OfContainedSei", ca2OfContainedSei);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);
		
		Set<IMarker> markersRepo = vpmh.getAllMarkersForObjectAndContents(repo);
		Set<IMarker> markersConcept = vpmh.getAllMarkersForObjectAndContents(concept);
		Set<IMarker> markersSeiContaining = vpmh.getAllMarkersForObjectAndContents(seiContaining);
		Set<IMarker> markersSeiContained = vpmh.getAllMarkersForObjectAndContents(seiContained);
		Set<IMarker> markersCaOfContainingSei = vpmh.getAllMarkersForObjectAndContents(caOfContainingSei);
		Set<IMarker> markersCa2OfContainedSei = vpmh.getAllMarkersForObjectAndContents(ca2OfContainedSei);
		Set<IMarker> markersVpiOfCa1OfContainedSei = vpmh.getAllMarkersForObjectAndContents(vpiOfCa1OfContainedSei);
		
		assertEquals("Set has correct size", 2, markersRepo.size());
		assertEquals("Set has correct size", 1, markersConcept.size());
		assertEquals("Set has correct size", 2, markersSeiContaining.size());
		assertEquals("Set has correct size", 1, markersCaOfContainingSei.size());
		assertEquals("Set has correct size", 3, markersSeiContained.size());
		assertEquals("Set has correct size", 1, markersCa2OfContainedSei.size());
		assertEquals("Set has correct size", 1, markersVpiOfCa1OfContainedSei.size());
		
		vpmh.deleteMarkersInWorkspace(ID_TESTMARKER);

		markersRepo = vpmh.getAllMarkersForObjectAndContents(repo);
		markersConcept = vpmh.getAllMarkersForObjectAndContents(concept);
		markersSeiContaining = vpmh.getAllMarkersForObjectAndContents(seiContaining);
		markersSeiContained = vpmh.getAllMarkersForObjectAndContents(seiContained);
		markersCaOfContainingSei = vpmh.getAllMarkersForObjectAndContents(caOfContainingSei);
		markersCa2OfContainedSei = vpmh.getAllMarkersForObjectAndContents(ca2OfContainedSei);
		markersVpiOfCa1OfContainedSei = vpmh.getAllMarkersForObjectAndContents(vpiOfCa1OfContainedSei);
		
		assertEquals("Set has correct size", 1, markersRepo.size());
		assertEquals("Set has correct size", 0, markersConcept.size());
		assertEquals("Set has correct size", 0, markersSeiContaining.size());
		assertEquals("Set has correct size", 0, markersCaOfContainingSei.size());
		assertEquals("Set has correct size", 1, markersSeiContained.size());
		assertEquals("Set has correct size", 0, markersCa2OfContainedSei.size());
		assertEquals("Set has correct size", 0, markersVpiOfCa1OfContainedSei.size());
		
		assertTrue("Set contains correct element", markersRepo.contains(markerRepo));
		assertTrue("Set contains correct element", markersSeiContained.contains(markerSeiContained));
	}
	
	@Test
	public void testDeleteAllMarkersInWorkspace() {
		vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Repository Resource", repo);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Concept", concept);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContaining", seiContaining);
		vpmh.createMarker(VirSatProblemMarkerHelper.ID_VIRSAT_PROBLEM_MARKER, IMarker.SEVERITY_WARNING, "This is a marker on the SeiContained", seiContained);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the CaOfContainingSei", caOfContainingSei);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the Ca2OfContainedSei", ca2OfContainedSei);
		vpmh.createMarker(ID_TESTMARKER, IMarker.SEVERITY_WARNING, "This is a marker on the VpiOfCa1OfContainedSei", vpiOfCa1OfContainedSei);
		
		Set<IMarker> markersRepo = vpmh.getAllMarkersForObjectAndContents(repo);
		Set<IMarker> markersConcept = vpmh.getAllMarkersForObjectAndContents(concept);
		Set<IMarker> markersSeiContaining = vpmh.getAllMarkersForObjectAndContents(seiContaining);
		Set<IMarker> markersSeiContained = vpmh.getAllMarkersForObjectAndContents(seiContained);
		Set<IMarker> markersCaOfContainingSei = vpmh.getAllMarkersForObjectAndContents(caOfContainingSei);
		Set<IMarker> markersCa2OfContainedSei = vpmh.getAllMarkersForObjectAndContents(ca2OfContainedSei);
		Set<IMarker> markersVpiOfCa1OfContainedSei = vpmh.getAllMarkersForObjectAndContents(vpiOfCa1OfContainedSei);
		
		assertEquals("Set has correct size", 2, markersRepo.size());
		assertEquals("Set has correct size", 1, markersConcept.size());
		assertEquals("Set has correct size", 2, markersSeiContaining.size());
		assertEquals("Set has correct size", 1, markersCaOfContainingSei.size());
		assertEquals("Set has correct size", 3, markersSeiContained.size());
		assertEquals("Set has correct size", 1, markersCa2OfContainedSei.size());
		assertEquals("Set has correct size", 1, markersVpiOfCa1OfContainedSei.size());
		
		vpmh.deleteAllMarkersInWorkspace();

		markersRepo = vpmh.getAllMarkersForObjectAndContents(repo);
		markersConcept = vpmh.getAllMarkersForObjectAndContents(concept);
		markersSeiContaining = vpmh.getAllMarkersForObjectAndContents(seiContaining);
		markersSeiContained = vpmh.getAllMarkersForObjectAndContents(seiContained);
		markersCaOfContainingSei = vpmh.getAllMarkersForObjectAndContents(caOfContainingSei);
		markersCa2OfContainedSei = vpmh.getAllMarkersForObjectAndContents(ca2OfContainedSei);
		markersVpiOfCa1OfContainedSei = vpmh.getAllMarkersForObjectAndContents(vpiOfCa1OfContainedSei);
		
		assertEquals("Set has correct size", 0, markersRepo.size());
		assertEquals("Set has correct size", 0, markersConcept.size());
		assertEquals("Set has correct size", 0, markersSeiContaining.size());
		assertEquals("Set has correct size", 0, markersCaOfContainingSei.size());
		assertEquals("Set has correct size", 0, markersSeiContained.size());
		assertEquals("Set has correct size", 0, markersCa2OfContainedSei.size());
		assertEquals("Set has correct size", 0, markersVpiOfCa1OfContainedSei.size());
	}
	//CHECKSTYLE:ON
}
