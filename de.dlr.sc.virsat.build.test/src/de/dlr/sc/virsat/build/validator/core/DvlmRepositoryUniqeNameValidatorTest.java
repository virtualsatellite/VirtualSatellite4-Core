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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.build.test.ABuilderTest;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.validator.IRepositoryValidator;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * test Case for checking the Duplicate Naming Validator
 * @author fisc_ph
 *
 */
public class DvlmRepositoryUniqeNameValidatorTest extends ABuilderTest {
	
	protected IFile fileNewSeiEdSc;
	protected IFile fileSeiEcSc;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		Resource repositoryResource = resSet.getRepositoryResource();
		
		// here we add a new SEI to the RootEntities of the Repository which has the same name as a already existing one
		StructuralElementInstance newSeiEdSc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		newSeiEdSc.setName(seiEdSc.getName());
		newSeiEdSc.setType(se);
		repo.getRootEntities().add(newSeiEdSc);
		Resource resNewSeiEdSc = resSet.getStructuralElementInstanceResource(newSeiEdSc);
		resNewSeiEdSc.getContents().clear();
		resNewSeiEdSc.getContents().add(newSeiEdSc);
		
		// a new SE is created and added to the concept
		StructuralElement seEc = StructuralFactory.eINSTANCE.createStructuralElement();
		seEc.setIsApplicableForAll(true);
		seEc.setName("Configuration");
		concept.getStructuralElements().add(seEc);
		
		// here we add a new SEI to the RootEntities of the Repository which has the same name as a already existing one
		// but has another type as the other ones
		StructuralElementInstance seiEcSc = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEcSc.setName(seiEdSc.getName());
		seiEcSc.setType(seEc);
		repo.getRootEntities().add(seiEcSc);
		Resource resSeiEcSc = resSet.getStructuralElementInstanceResource(seiEcSc);
		resSeiEcSc.getContents().clear();
		resSeiEcSc.getContents().add(seiEcSc);
		
		// now save everything
		repositoryResource.save(Collections.EMPTY_MAP);
		resNewSeiEdSc.save(Collections.EMPTY_MAP);
		resSeiEcSc.save(Collections.EMPTY_MAP);
		
		// get the file for our created SEIs
		VirSatProjectCommons projectCommons = new VirSatProjectCommons(project);
		fileNewSeiEdSc = projectCommons.getStructuralElementInstanceFile(newSeiEdSc);
		fileSeiEcSc = projectCommons.getStructuralElementInstanceFile(seiEcSc);
	}
	
	@Test
	public void testValidateOnRootSeis() throws CoreException, IOException {
		
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0, fileNewSeiEdSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0, fileSeiEcSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
		IRepositoryValidator repoValidator = new DvlmRepositoryUniqeNameValidator();
		
		assertFalse("validator finds duplicate name", repoValidator.validate(repo));

		assertEquals("There are still no markers", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are markers now", 1, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are markers now", 1, fileNewSeiEdSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are still no markers", 0, fileSeiEcSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
	}
}
