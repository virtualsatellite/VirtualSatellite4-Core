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

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * This class tests the DVLMCopier
 */
public class DVLMCopierTest {

	private StructuralElement se;
	private StructuralElementInstance sei;
	
	private Category category;
	private StringProperty property;
	
	private CategoryAssignment ca;
	
	@Before
	public void setUp() throws Exception {
		se = StructuralFactory.eINSTANCE.createStructuralElement();
		sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		
		category = CategoriesFactory.eINSTANCE.createCategory();
		property = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		
		category.getProperties().add(property);
		category.getApplicableFor().add(se);
		
		CategoryInstantiator caInstanciator = new CategoryInstantiator();
		ca = caInstanciator.generateInstance(category, "TestCA");
		
		sei.getCategoryAssignments().add(ca);
		
		assertEquals("SEI has one CA properly added", 1, sei.getCategoryAssignments().size());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCopy() {
		StructuralElementInstance seiCopy = (StructuralElementInstance) DVLMCopier.makeCopy(sei);
		assertEquals("SEI copy has one CA", 1, seiCopy.getCategoryAssignments().size());
		
		assertEquals("Both Seis have same Type", seiCopy.getType(), sei.getType());
		
		CategoryAssignment caCopy = (CategoryAssignment) DVLMCopier.makeCopy(ca);
		
		assertEquals("Both Seis have same Type", caCopy.getType(), ca.getType());
	}
	
	@Test
	public void testCopyWithInheritance() {
		ca.setIsInherited(true);
		CategoryAssignment caCopy = (CategoryAssignment) DVLMCopier.makeCopy(ca);
		assertEquals("Both Cas have same Type", caCopy.getType(), ca.getType());
		assertFalse("Inheritance flag has been deactivated", caCopy.isIsInherited());
	}
	
	@Test
	public void testSetCopySuperTis() {
		CategoryInstantiator caInstanciator = new CategoryInstantiator();
		CategoryAssignment superCa = caInstanciator.generateInstance(category, "TestSuperCA");
		ca.getSuperTis().add(superCa);
		
		DVLMCopier copier = new DVLMCopier();
		CategoryAssignment caCopy = (CategoryAssignment) copier.copy(ca);
		copier.copyReferences();
		
		assertThat("Copying super TIs enabled per default", caCopy.getSuperTis(), hasItem(superCa));
		
		copier.setCopySuperTis(false);
		caCopy = (CategoryAssignment) copier.copy(ca);
		copier.copyReferences();
		
		assertTrue("No super TIs copied when copySuperTis is set to false", caCopy.getSuperTis().isEmpty());
	}

	@Test
	public void testUuidsAreDifferentInCopy() {
		StructuralElementInstance seiCopy = (StructuralElementInstance) DVLMCopier.makeCopy(sei);
		
		assertNotEquals("Uuid of SEIs are different", sei.getUuid(), seiCopy.getUuid());
		assertNotEquals("Uuid of CAs are different", sei.getCategoryAssignments().get(0).getUuid(), seiCopy.getCategoryAssignments().get(0).getUuid());
	}
	
	@Test
	public void testUuidsAreSameInCopy() {
		StructuralElementInstance seiCopy = (StructuralElementInstance) DVLMCopier.makeCopyKeepUuids(sei);
		
		assertEquals("Uuid of SEIs are same", sei.getUuid(), seiCopy.getUuid());
		assertEquals("Uuid of CAs are same", sei.getCategoryAssignments().get(0).getUuid(), seiCopy.getCategoryAssignments().get(0).getUuid());
	}
}
