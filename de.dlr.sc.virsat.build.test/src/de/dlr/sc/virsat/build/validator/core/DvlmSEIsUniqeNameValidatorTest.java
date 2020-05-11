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
import static org.junit.Assert.assertTrue;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.junit.Test;

import de.dlr.sc.virsat.build.test.ABuilderTest;
import de.dlr.sc.virsat.model.dvlm.validator.IStructuralElementInstanceValidator;

/**
 * test Case for checking the Duplicate Naming Validator
 * @author fisc_ph
 *
 */
public class DvlmSEIsUniqeNameValidatorTest extends ABuilderTest {
	
	@Test
	public void testValidateOnChildSeis() throws CoreException {
		
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0, fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0, fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
		IStructuralElementInstanceValidator seiValidator = new DvlmSEIsUniqeNameValidator();
		
		assertTrue("validator brings no error", seiValidator.validate(seiEdSc));
		
		//Now set equal names to child SEIs
		seiEdRw.setName("SeiNewName");
		seiEdObc.setName("SeiNewName");
		
		assertFalse("validator finds duplicate name", seiValidator.validate(seiEdSc));

		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There is a marker now", 1, fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There is a marker now", 1, fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
	}
	
	@Test
	public void testValidateOnChildCas() throws CoreException {
		
		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0, fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0, fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
		IStructuralElementInstanceValidator seiValidator = new DvlmSEIsUniqeNameValidator();
		
		assertTrue("validator brings no error", seiValidator.validate(seiEdSc));
		
		//Now set equal names to child SEIs
		caIftCan.setName("CaNewName");
		caIftMil.setName("CaNewName");
		
		assertFalse("validator finds duplicate name", seiValidator.validate(seiEdSc));

		assertEquals("There are no markers yet", 0, fileRepo.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are markers now", 2, fileSc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0, fileRw.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		assertEquals("There are no markers yet", 0, fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		
	}
}
