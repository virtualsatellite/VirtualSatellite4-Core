/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
/**
 * 
 */
package de.dlr.sc.virsat.requirements.util.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.requirements.reqif.util.ReqIFUtil;

/**
 * @author fran_tb
 *
 */
public class ReqIFUtilTest {
	
	public static final String TEST_FILENAME = "testFile";
	public static final String REQ_IF_FILE_EXTENSION = "reqif";
	public static final String OTHER_FILE_EXTENSION = "test";
	public static final String TEST_PROJECT = "testBaseProject";
	
	ResourceSet testResSet;
	IProject project;
	
	@Before
	public void setUp() throws Exception {


		// Create base project
		testResSet = new ResourceSetImpl();
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT);
		if (!project.exists()) {
			project.create(null);
		}
		project.open(null);

	}

	@After
	public void tearDown() throws Exception {
		project.delete(true, null);
	}
	
	@Test
	public void testIsReqIFFileWithURI() {
		
		URI reqIFURI = URI.createFileURI(TEST_FILENAME + "." + REQ_IF_FILE_EXTENSION);
		URI otherURI = URI.createFileURI(TEST_FILENAME + "." + OTHER_FILE_EXTENSION);
		URI fileURIwithoutExtension = URI.createFileURI(TEST_FILENAME);
		
		assertTrue(ReqIFUtil.isReqIF(reqIFURI));
		assertFalse(ReqIFUtil.isReqIF(otherURI));
		assertFalse(ReqIFUtil.isReqIF(fileURIwithoutExtension));
		
	}
	
	@Test
	public void testIsReqIFFileWithResource() {
		
		IFile reqIFFIle = project.getFile(TEST_FILENAME + "." + REQ_IF_FILE_EXTENSION);
		IFile otherFile = project.getFile(TEST_FILENAME + "." + OTHER_FILE_EXTENSION);
		IFile fileURIwithoutExtension = project.getFile(TEST_FILENAME);
		
		assertTrue(ReqIFUtil.isReqIF(reqIFFIle));
		assertFalse(ReqIFUtil.isReqIF(otherFile));
		assertFalse(ReqIFUtil.isReqIF(fileURIwithoutExtension));
		
	}

}
