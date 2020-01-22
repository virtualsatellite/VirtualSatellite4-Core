/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.allOf;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;

/**
 * Test Cases for the Unresolved reference Exception and Resource Diagnostic
 * 
 * @author fisc_ph
 *
 */
public class DVLMUnresolvedReferenceExceptionTest {

	public static final String FILE_REFERENCED = "referencedObject.dvlm";
	public static final String FILE_REFERENCING = "referencingObject.dvlm";
	
	private URI uriFileReferenced;
	private URI uriFileReferencing;
	
	private Resource resourceReferenced;
	
	@Before
	public void setUp() throws Exception {
		
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ReferencePropertyInstance rpi = PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
		rpi.setReference(ca);
		
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
		resourceReferenced = resSet.createResource(uriFileReferenced);
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
	public void testHashCode() {
		CategoryAssignment obj1 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		CategoryAssignment obj2 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		
		Exception exceptionSame1 = new DVLMUnresolvedReferenceException(obj1);
		Exception exceptionSame2 = new DVLMUnresolvedReferenceException(obj1);
		Exception exceptionDiff = new DVLMUnresolvedReferenceException(obj2);
		
		assertEquals("Hashcode does not change", exceptionSame1.hashCode(), exceptionSame1.hashCode());
		assertEquals("Hachcode is equal", exceptionSame1.hashCode(), exceptionSame2.hashCode());
		
		assertNotSame("Hashcode defers", exceptionSame1.hashCode(), exceptionDiff.hashCode());
	}

	@Test
	public void testDVLMUnresolvedReferenceException() {
		CategoryAssignment obj1 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		CategoryAssignment obj2 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		
		Exception exceptionSame1 = new DVLMUnresolvedReferenceException(obj1);
		Exception exceptionSame2 = new DVLMUnresolvedReferenceException(obj1);
		Exception exceptionDiff = new DVLMUnresolvedReferenceException(obj2);
		
		assertNotSame("Objects are not teh Same", exceptionSame1, exceptionSame2);
		assertEquals("Exceptions are equal", exceptionSame1, exceptionSame2);
		assertNotSame("Exceptions are different", exceptionSame1, exceptionDiff);
	}

	@Test
	public void testGetMessage() {
		CategoryAssignment obj1 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		
		// Creating the exception this way, the object does not have a proper proxy URI
		Resource.Diagnostic ex = new DVLMUnresolvedReferenceException(obj1);
		String msg = ex.getMessage();
		assertThat("Correct error message", msg, allOf(
				containsString(DVLMUnresolvedReferenceException.ERR_MESSAGE),
				containsString(DVLMUnresolvedReferenceException.ERR_NO_RESOURCE_LOCATION)
				));
		
		// Now load it by the resource set through the corrupted file
		ResourceSet resSet = new ResourceSetImpl();
		Resource resourceReferencing = resSet.getResource(uriFileReferencing, true);
		ReferencePropertyInstance rpi = (ReferencePropertyInstance) resourceReferencing.getContents().get(0);
		
		EObject proxyObject = rpi.getReference();
		Resource.Diagnostic ex2 = new DVLMUnresolvedReferenceException(proxyObject);
		String msg2 = ex2.getMessage();
		assertThat("Correct error message", msg2, allOf(
				containsString(DVLMUnresolvedReferenceException.ERR_MESSAGE),
				containsString(FILE_REFERENCED)
				));
	}

	@Test
	public void testCheckProxyObject() throws IOException {
		ResourceSet resSet2 = new ResourceSetImpl();
		Resource resourceReferencing2 = resSet2.getResource(uriFileReferencing, true);
		ReferencePropertyInstance rpi = (ReferencePropertyInstance) resourceReferencing2.getContents().get(0);
		
		assertTrue("There are no errors yet at teh reosurce", resourceReferencing2.getErrors().isEmpty());
		
		// Check proxy object gets indirectly called and should place an exception/diagnostic to the resource
		// This code indirectly checks the EMF mdoified code generator as well
		EObject proxyObject = rpi.getReference();
	
		assertEquals("There is one error now", 1, resourceReferencing2.getErrors().size());
		
		DVLMUnresolvedReferenceException ex = (DVLMUnresolvedReferenceException) resourceReferencing2.getErrors().get(0);
		assertEquals("Proxy object is the same", proxyObject, ex.getProxy());
	
		// Now save the resource with the reference
		// and make the reosurceSet reload
		// Check if the error gets removed from the resource as expected
		resourceReferenced.save(Collections.EMPTY_MAP);
		Resource resourceReferenced2 = resSet2.getResource(uriFileReferenced, false);
		resourceReferenced2.unload();
		
		assertEquals("There is one error now", 1, resourceReferencing2.getErrors().size());
		
		rpi.getReference();
		
		assertEquals("There are no errors anymore", 0, resourceReferencing2.getErrors().size());
	}

	@Test
	public void testEqualsObject() {
		CategoryAssignment obj1 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		CategoryAssignment obj2 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		
		Exception exceptionSame1 = new DVLMUnresolvedReferenceException(obj1);
		Exception exceptionSame2 = new DVLMUnresolvedReferenceException(obj1);
		Exception exceptionDiff = new DVLMUnresolvedReferenceException(obj2);
		
		assertTrue("Objects are equal", exceptionSame1.equals(exceptionSame2));
		assertFalse("Objects are not equal", exceptionSame1.equals(exceptionDiff));
		assertFalse("Objects are not equal", exceptionSame1.equals(new Object()));
		assertFalse("Objects are not equal", exceptionSame1.equals(null));
	}

	@Test
	public void testGetLine() {
		CategoryAssignment obj1 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		
		Resource.Diagnostic ex = new DVLMUnresolvedReferenceException(obj1);
		assertEquals("Line is set correctly", DVLMUnresolvedReferenceException.LOCATION_CURSOR_UNKNOWN, ex.getLine());
	}

	@Test
	public void testGetColumn() {
		CategoryAssignment obj1 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		
		Resource.Diagnostic ex = new DVLMUnresolvedReferenceException(obj1);
		assertEquals("Line is set correctly", DVLMUnresolvedReferenceException.LOCATION_CURSOR_UNKNOWN, ex.getColumn());
	}
}
