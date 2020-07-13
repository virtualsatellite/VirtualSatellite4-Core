/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;

/**
 * 
 * @author fisc_ph
 *
 */
public class TestCategoryReferenceTest extends AConceptTestCase {

	private TestCategoryReference tcReference;
	private Concept concept;
	
	@Before
	public void setup() {
		prepareEditingDomain();
		// Load the concept to create the test object
		concept = loadConceptFromPlugin();
		tcReference = new TestCategoryReference(concept);
	}

	@After
	public void tearDown() throws Exception {
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	@Test
	public void testReferenceCategory() {
		TestCategoryAllProperty tcAllProeprty = new TestCategoryAllProperty(concept);
		
		tcReference.setTestRefCategory(tcAllProeprty);
		TestCategoryAllProperty referencedCategory = tcReference.getTestRefCategory();
		ReferencePropertyInstance referencedTypeInstance = (ReferencePropertyInstance) tcReference.getTypeInstance().getPropertyInstances().get(0);
		
		assertEquals("Reference got set correctly", tcAllProeprty, referencedCategory);
		assertEquals("Reference on EMF Model set correctly", tcAllProeprty.getATypeInstance(), referencedTypeInstance.getReference());
	
		TestCategoryReference tcReferenceOther = new TestCategoryReference(tcReference.getTypeInstance());
		assertEquals("Reference is set correctly when creating bean after having set the reference", tcReference.getTestRefCategory(), tcReferenceOther.getTestRefCategory());
	
		tcReference.setTestRefCategory(null);
		assertNull("Reference has been set to null", tcReference.getTestRefCategory());
	}

	@Test
	public void testReferenceCategoryEditingDomain() {
		TestCategoryAllProperty tcAllProeprty = new TestCategoryAllProperty(concept);
		
		Command cmd = tcReference.setTestRefCategory(editingDomain, tcAllProeprty);
		editingDomain.getCommandStack().execute(cmd);
		
		TestCategoryAllProperty referencedCategory = tcReference.getTestRefCategory();
		ReferencePropertyInstance referencedTypeInstance = (ReferencePropertyInstance) tcReference.getTypeInstance().getPropertyInstances().get(0);
		
		assertEquals("Reference got set correctly", tcAllProeprty, referencedCategory);
		assertEquals("Reference on EMF Model set correctly", tcAllProeprty.getATypeInstance(), referencedTypeInstance.getReference());
	}
	
	@Test
	public void testReferenceProperty() {
		BeanPropertyString bpString = new TestCategoryAllProperty(concept).getTestStringBean();
		
		tcReference.setTestRefProperty(bpString);
		BeanPropertyString referenceBpString = tcReference.getTestRefProperty();
		ReferencePropertyInstance referencedTypeInstance = (ReferencePropertyInstance) tcReference.getTypeInstance().getPropertyInstances().get(1);
		
		assertEquals("Reference got set correctly", bpString, referenceBpString);
		assertEquals("Reference on EMF Model set correctly", bpString.getATypeInstance(), referencedTypeInstance.getReference());
	}

	@Test
	public void testReferencePropertyEditingDomain() {
		BeanPropertyString bpString = new TestCategoryAllProperty(concept).getTestStringBean();
		
		Command cmd = tcReference.setTestRefProperty(editingDomain, bpString);
		editingDomain.getCommandStack().execute(cmd);
		
		BeanPropertyString referenceBpString = tcReference.getTestRefProperty();
		ReferencePropertyInstance referencedTypeInstance = (ReferencePropertyInstance) tcReference.getTypeInstance().getPropertyInstances().get(1);
		
		assertEquals("Reference got set correctly", bpString, referenceBpString);
		assertEquals("Reference on EMF Model set correctly", bpString.getATypeInstance(), referencedTypeInstance.getReference());
	}
	
	@Test
	public void testDelete() {
		TestCategoryAllProperty tcAllProeprty = new TestCategoryAllProperty(concept);
		tcReference.setTestRefCategory(tcAllProeprty);
		
		Resource resource = editingDomain.getResourceSet().createResource(URI.createURI("TSE"));
		resource.getContents().add(tcAllProeprty.getTypeInstance());
		resource.getContents().add(tcReference.getTypeInstance());
		
		assertNotNull("CA has not been deleted yet", tcReference.getTestRefCategory());
		
		tcAllProeprty.delete();
		
		assertNull("CA has been deleted", tcReference.getTestRefCategory());
	}
	
	@Test
	public void testDeleteCommand() {
		TestCategoryAllProperty tcAllProeprty = new TestCategoryAllProperty(concept);
		tcReference.setTestRefCategory(tcAllProeprty);
		
		Resource resource = editingDomain.getResourceSet().createResource(URI.createURI("TSE"));
		resource.getContents().add(tcAllProeprty.getTypeInstance());
		resource.getContents().add(tcReference.getTypeInstance());
		
		assertNotNull("CA has not been deleted yet", tcReference.getTestRefCategory());
		
		Command command = tcAllProeprty.delete(editingDomain);
		assertTrue("Command can be executed", command.canExecute());
		
		command.execute();
		
		assertNull("CA has been deleted", tcReference.getTestRefCategory());
	}
}
