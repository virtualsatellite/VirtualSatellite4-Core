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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation;
import de.dlr.sc.virsat.model.dvlm.structural.RelationInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;

/**
 * This Test Class checks the applicable for paradigm with its different ways of dealing
 * with which object can be added to which other one. such as which category can be added to which
 * Structural Element etc.
 * @author fisc_ph
 *
 */
public class DVLMApplicableForCheckTest {

	private StructuralElement seAccept;
	private StructuralElement seFail;
	
	private StructuralElementInstance seiRoot;
	
	@Before
	public void setUp() throws Exception {
		seAccept = StructuralFactory.eINSTANCE.createStructuralElement();
		seFail = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seAccept.getApplicableFor().add(seAccept);
		seFail.getApplicableFor().add(seFail);
		
		seiRoot = new StructuralInstantiator().generateInstance(seAccept, "Se_Root");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApplicableForWithCollection() {
		StructuralElement seForAccept = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seForFail = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seForAll = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seForNone = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seForAccept.getApplicableFor().add(seAccept);
		seForFail.getApplicableFor().add(seFail);
		seForAll.setIsApplicableForAll(true);
		
		StructuralElementInstance seiForAccept = new StructuralInstantiator().generateInstance(seForAccept, "RelAccept");
		StructuralElementInstance seiForFail = new StructuralInstantiator().generateInstance(seForFail, "relFail");
		StructuralElementInstance seiForAll = new StructuralInstantiator().generateInstance(seForAll, "RelAcceptAll");
		StructuralElementInstance seiForNone = new StructuralInstantiator().generateInstance(seForNone, "RelAcceptNone");
		
		ADVLMExtendedModelCapabilityCheck applicableForCheck = new DVLMApplicableForCheck(seiRoot, true);
	
		List<StructuralElementInstance> listWithFail = new ArrayList<>();
		listWithFail.add(seiForAccept);
		listWithFail.add(seiForAll);
		
		assertTrue("Object can be assigned", applicableForCheck.isValidObjectCollection(listWithFail));

		listWithFail.add(seiForFail);
		listWithFail.add(seiForNone);
		assertFalse("Object can not be assigned", applicableForCheck.isValidObjectCollection(listWithFail));
	}

	@Test
	public void testApplicableForWithCategories() {
		Category categoryForAccept = CategoriesFactory.eINSTANCE.createCategory();
		Category categoryForFail = CategoriesFactory.eINSTANCE.createCategory();
		Category categoryForAll = CategoriesFactory.eINSTANCE.createCategory();
		Category categoryForNone = CategoriesFactory.eINSTANCE.createCategory();
		
		categoryForAll.setIsApplicableForAll(true);
		categoryForAccept.getApplicableFor().add(seAccept);
		categoryForFail.getApplicableFor().add(seFail);
		
		CategoryAssignment caForAccept = new CategoryInstantiator().generateInstance(categoryForAccept, "CaAccept");
		CategoryAssignment caForFail = new CategoryInstantiator().generateInstance(categoryForFail, "CaFail");
		CategoryAssignment caForAll = new CategoryInstantiator().generateInstance(categoryForAll, "CaAcceptAll");
		CategoryAssignment caForNone = new CategoryInstantiator().generateInstance(categoryForNone, "CaAcceptAll");
		
		ADVLMExtendedModelCapabilityCheck applicableForCheck = new DVLMApplicableForCheck(seiRoot, true);
		
		assertTrue("Object can be assigned", applicableForCheck.isValidObject(caForAccept));
		assertTrue("Object can be assigned", applicableForCheck.isValidObject(caForAll));
		assertFalse("Object can not be assigned", applicableForCheck.isValidObject(caForFail));
		assertFalse("Object can not be assigned", applicableForCheck.isValidObject(caForNone));
	}

	@Test
	public void testApplicableForWithRelations() {
		GeneralRelation relationForAccept = StructuralFactory.eINSTANCE.createGeneralRelation();
		GeneralRelation relationForFail = StructuralFactory.eINSTANCE.createGeneralRelation();
		GeneralRelation relationForAll = StructuralFactory.eINSTANCE.createGeneralRelation();
		
		relationForAccept.getApplicableFor().add(seAccept);
		relationForFail.getApplicableFor().add(seFail);
		relationForAll.setIsApplicableForAll(true);
		
		RelationInstance riForAccept = new StructuralInstantiator().generateInstance(relationForAccept, "RelAccept");
		RelationInstance riForFail = new StructuralInstantiator().generateInstance(relationForFail, "relFail");
		RelationInstance riForAll = new StructuralInstantiator().generateInstance(relationForAll, "RelAcceptAll");
		
		ADVLMExtendedModelCapabilityCheck applicableForCheck = new DVLMApplicableForCheck(seiRoot, true);
		
		assertTrue("Object can be assigned", applicableForCheck.isValidObject(riForAccept));
		assertTrue("Object can be assigned", applicableForCheck.isValidObject(riForAll));
		assertFalse("Object can not be assigned", applicableForCheck.isValidObject(riForFail));
	}

	@Test
	public void testApplicableForWithStructralElements() {
		StructuralElement seForAccept = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seForFail = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seForAll = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seForAccept.getApplicableFor().add(seAccept);
		seForFail.getApplicableFor().add(seFail);
		seForAll.setIsApplicableForAll(true);
		
		StructuralElementInstance seiForAccept = new StructuralInstantiator().generateInstance(seForAccept, "RelAccept");
		StructuralElementInstance seiForFail = new StructuralInstantiator().generateInstance(seForFail, "relFail");
		StructuralElementInstance seiForAll = new StructuralInstantiator().generateInstance(seForAll, "RelAcceptAll");
		
		ADVLMExtendedModelCapabilityCheck applicableForCheck = new DVLMApplicableForCheck(seiRoot, true);
		
		assertTrue("Object can be assigned", applicableForCheck.isValidObject(seiForAccept));
		assertTrue("Object can be assigned", applicableForCheck.isValidObject(seiForAll));
		assertFalse("Object can not be assigned", applicableForCheck.isValidObject(seiForFail));
	}

	@Test
	public void testApplicableForWithStructralElementAtRepository() {
		StructuralElement seForAccept = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seForFail = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seForAccept.setIsRootStructuralElement(true);
		seForFail.setIsRootStructuralElement(false);
		
		StructuralElementInstance seiForAccept = new StructuralInstantiator().generateInstance(seForAccept, "RelAccept");
		StructuralElementInstance seiForFail = new StructuralInstantiator().generateInstance(seForFail, "relFail");
		
		Repository testRepo = DVLMFactory.eINSTANCE.createRepository();
		ADVLMExtendedModelCapabilityCheck applicableForCheck = new DVLMApplicableForCheck(testRepo, false);
		
		assertTrue("Object can be assigned", applicableForCheck.isValidObject(seiForAccept));
		assertFalse("Object can not be assigned", applicableForCheck.isValidObject(seiForFail));
	}
}
