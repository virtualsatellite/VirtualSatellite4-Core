/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.exception;

import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation;
import de.dlr.sc.virsat.model.dvlm.structural.RelationInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Class for unit testing the checking whether instances are correctly typed.
 * CategoryAssignment should be typed by Category
 * RelationInstance should be typed by GeneralRelation
 * StructuralElementInstance should be typed by StructuralElement
 * 
 * @author kova_an
 *
 */
public class DVLMMissingTypeExceptionTest {

	@Test(expected = DVLMMissingTypeException.class)
	public void testCheckAndThrowMissingCategoryFail() {
		CategoryAssignment caWithoutCategory = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		
		DVLMMissingTypeException.checkAndThrowMissingCategory(caWithoutCategory);
	}

	@Test
	public void testCheckAndThrowMissingCategoryPass() {
		Category category = CategoriesFactory.eINSTANCE.createCategory();
		CategoryAssignment caWithCategory = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		caWithCategory.setType(category);
		
		DVLMMissingTypeException.checkAndThrowMissingCategory(caWithCategory);
	}

	@Test(expected = DVLMMissingTypeException.class)
	public void testCheckAndThrowMissingCategoryTypedByPropertyFail() {
		StringProperty property = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		CategoryAssignment caIncorrectlyTypedWithProperty = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		caIncorrectlyTypedWithProperty.setType(property);
		
		DVLMMissingTypeException.checkAndThrowMissingCategory(caIncorrectlyTypedWithProperty);
	}
	
	@Test(expected = DVLMMissingTypeException.class)
	public void testCheckAndThrowMissingRelationFail() {
		RelationInstance relationInstanceWithoutRelation = StructuralFactory.eINSTANCE.createRelationInstance();
		
		DVLMMissingTypeException.checkAndThrowMissingRelation(relationInstanceWithoutRelation);
	}

	@Test
	public void testCheckAndThrowMissingRelationPass() {
		GeneralRelation relation = StructuralFactory.eINSTANCE.createGeneralRelation();
		RelationInstance relationInstanceWithRelation = StructuralFactory.eINSTANCE.createRelationInstance();
		relationInstanceWithRelation.setType(relation);
		
		DVLMMissingTypeException.checkAndThrowMissingRelation(relationInstanceWithRelation);
	}
	
	@Test(expected = DVLMMissingTypeException.class)
	public void testCheckAndThrowMissingStructuralElementFail() {
		StructuralElementInstance seiWithoutSe = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		DVLMMissingTypeException.checkAndThrowMissingStructuralElement(seiWithoutSe);
	}

	@Test
	public void testCheckAndThrowMissingStructuralElementPass() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance seiWithSe = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiWithSe.setType(se);
		
		DVLMMissingTypeException.checkAndThrowMissingStructuralElement(seiWithSe);
	}
}
