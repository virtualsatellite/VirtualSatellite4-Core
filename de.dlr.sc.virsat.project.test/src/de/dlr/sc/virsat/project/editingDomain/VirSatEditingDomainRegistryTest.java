/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

public class VirSatEditingDomainRegistryTest extends AProjectTestCase {

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
	}

	@After
	public void tearDown() throws CoreException {
		super.tearDown();
	}

	@Test
	public void testGetEdResourceSet() {
		EditingDomain edOk = VirSatEditingDomainRegistry.INSTANCE.getEd(rs);
		
		assertEquals("Got correct Editing Domain", editingDomain, edOk);
		
		EditingDomain edBad = VirSatEditingDomainRegistry.INSTANCE.getEd(new ResourceSetImpl());
		
		assertNull("There is no editing domain for the given ResourceSet", edBad);
	}

	@Test
	public void testGetEdEObject() {
		EditingDomain edOk = VirSatEditingDomainRegistry.INSTANCE.getEd(repository);
		
		assertEquals("Got correct Editing Domain", editingDomain, edOk);
		
		// Create a random eObject, from which nothing should be handed back
		EditingDomain edBad = VirSatEditingDomainRegistry.INSTANCE.getEd(DVLMFactory.eINSTANCE.createRepository());
		
		assertNull("There is no editing domain for the given Repository", edBad);
		
		// A null object should not crash the method
		EObject eObject = null;		
		EditingDomain edBad2 = VirSatEditingDomainRegistry.INSTANCE.getEd(eObject);

		assertNull("There is no editing domain for a null object", edBad2);
	}

	@Test
	public void testGetEdIProject() throws CoreException {
		EditingDomain edOk = VirSatEditingDomainRegistry.INSTANCE.getEd(testProject);
		
		assertEquals("Got correct Editing Domain", editingDomain, edOk);
		
		IProject noEdProject = ResourcesPlugin.getWorkspace().getRoot().getProject("noEdProject");
		noEdProject.create(null);
		
		EditingDomain edBad = VirSatEditingDomainRegistry.INSTANCE.getEd(noEdProject);
		
		assertNull("There is no editing domain for the given project", edBad);
	}
	
	@Test
	public void testGetEdIURI() throws CoreException {
		URI projectWsFileUri = URI.createURI(testProject.getLocationURI().toString());
		
		EditingDomain edOk = VirSatEditingDomainRegistry.INSTANCE.getEd(projectWsFileUri);
		assertEquals("Got correct Editing Domain", editingDomain, edOk);
		
		IProject noEdProject = ResourcesPlugin.getWorkspace().getRoot().getProject("noEdProject");
		noEdProject.create(null);
		URI noEdProjectWsFileUri = URI.createURI(noEdProject.getLocationURI().toString());
		
		EditingDomain edBad = VirSatEditingDomainRegistry.INSTANCE.getEd(noEdProjectWsFileUri);
		assertNull("There is no editing domain for the given project", edBad);
	}

	@Test
	public void testRemoveEd() {
		EditingDomain edOk = VirSatEditingDomainRegistry.INSTANCE.getEd(testProject);
		
		assertEquals("Got correct Editing Domain", editingDomain, edOk);
		
		VirSatEditingDomainRegistry.INSTANCE.removeEd(testProject);
		
		EditingDomain edRemoved = VirSatEditingDomainRegistry.INSTANCE.getEd(testProject);
		
		assertNull("The editing domain got removed as expected", edRemoved);
	}
}
