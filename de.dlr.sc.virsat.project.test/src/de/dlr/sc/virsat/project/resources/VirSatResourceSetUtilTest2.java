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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;

/**
 * Tests for the Util class
 * @author fisc_ph
 *
 */
public class VirSatResourceSetUtilTest2 {

	public static final String FILE_REFERENCED = "referencedObject.dvlm";
	public static final String FILE_REFERENCING = "referencingObject.dvlm";
	
	private URI uriFileReferenced;
	private URI uriFileReferencing;
	
	@Before
	public void setUp() throws Exception {
		
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ReferencePropertyInstance rpi = PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
		rpi.setReference(ca);
		rpi.getSuperTis().add(ca);
		
		Resource.Factory.Registry fileTypeRegistry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> fileTypeToPersistance = fileTypeRegistry.getExtensionToFactoryMap();
		fileTypeToPersistance.put("dvlm", new XMIResourceFactoryImpl());

		ResourceSet resSet = new ResourceSetImpl();
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("testUnresolvedReference");
		if (!project.exists()) {
			project.create(null);
		}
		project.open(null);
		IFile filereferenced = project.getFile(FILE_REFERENCED);
		IFile fileReferencing = project.getFile(FILE_REFERENCING);
		
		uriFileReferencing = URI.createPlatformResourceURI(fileReferencing.getFullPath().toString(), true);
		uriFileReferenced = URI.createPlatformResourceURI(filereferenced.getFullPath().toString(), true);
		
		Resource resourceReferencing = resSet.createResource(uriFileReferencing);
		Resource resourceReferenced = resSet.createResource(uriFileReferenced);
		resourceReferencing.getContents().add(rpi);
		resourceReferenced.getContents().add(ca);

		// Save only the RPI and create a dangling non resource reference
		resourceReferencing.save(Collections.EMPTY_MAP);
	}

	@After
	public void tearDown() throws Exception {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("testUnresolvedReference");
		project.delete(true, null);
	}

	
	@Test
	public void testRemoveDanglingReferences() throws IOException {
		Map<Object, Object> saveOptions = new HashMap<Object, Object>();

		saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		saveOptions.put(Resource.OPTION_LINE_DELIMITER, Resource.OPTION_LINE_DELIMITER_UNSPECIFIED);
		saveOptions.put(XMIResource.OPTION_PROCESS_DANGLING_HREF, XMIResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);

		ResourceSet resSet = new ResourceSetImpl();
		Resource resourceReferencing = resSet.getResource(uriFileReferencing, true);
		ReferencePropertyInstance rpi = (ReferencePropertyInstance) resourceReferencing.getContents().get(0);
		
		assertTrue("There are no errors yet at teh reosurce", resourceReferencing.getErrors().isEmpty());
		
		// Check proxy object gets indirectly called and should place an exception/diagnostic to the resource
		// This code indirectly checks the EMF modified code generator as well
		EObject proxyObject = rpi.getReference();
	
		assertEquals("There is one error now", 1, resourceReferencing.getErrors().size());
		
		VirSatResourceSetUtil.removeDanglingReferences(resourceReferencing);
		resourceReferencing.save(saveOptions);
		
		ResourceSet resSet2 = new ResourceSetImpl();
		Resource resourceReferencing2 = resSet2.getResource(uriFileReferencing, true);

		rpi = (ReferencePropertyInstance) resourceReferencing2.getContents().get(0);
		proxyObject = rpi.getReference();
		
		assertNull("After removing the dangling references the reference should not exist anymore", proxyObject);
	}
}
