/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.resources;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;

/**
 * Tests for the Util class
 * @author fisc_ph
 *
 */
public class VirSatResourceSetUtilTest {

	public static final String FILE_TEST_RESOURCE = "referencedObject.dvlm";
	public static final String TEST_PROJECT = "testResourceChanged";
	
	private URI uriFileResource;
	
	private Resource testResource;
	
	@Before
	public void setUp() throws Exception {
		Resource.Factory.Registry fileTypeRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> fileTypeToPersistance = fileTypeRegistry.getExtensionToFactoryMap();
		fileTypeToPersistance.put("dvlm", new XMIResourceFactoryImpl());

		ResourceSet resSet = new ResourceSetImpl();
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT);
		if (!project.exists()) {
			project.create(null);
		}
		project.open(null);
		IFile filereferenced = project.getFile(FILE_TEST_RESOURCE);
		
		uriFileResource = URI.createPlatformResourceURI(filereferenced.getFullPath().toString(), true);
		
		testResource = resSet.createResource(uriFileResource);
	}

	@After
	public void tearDown() throws Exception {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT);
		project.delete(true, null);
	}
	
	@Test
	public void testIsChanged() throws IOException {
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		
		assertTrue("resource is not yet saved", VirSatResourceSetUtil.isChanged(testResource, Collections.EMPTY_MAP, Collections.EMPTY_MAP));

		testResource.save(Collections.EMPTY_MAP);

		assertFalse("resource is saved and empty", VirSatResourceSetUtil.isChanged(testResource, Collections.EMPTY_MAP, Collections.EMPTY_MAP));
		
		testResource.getContents().add(ca);

		assertTrue("in memory ahs extra content", VirSatResourceSetUtil.isChanged(testResource, Collections.EMPTY_MAP, Collections.EMPTY_MAP));

		testResource.save(Collections.EMPTY_MAP);

		assertFalse("both are the same", VirSatResourceSetUtil.isChanged(testResource, Collections.EMPTY_MAP, Collections.EMPTY_MAP));
	}
}
