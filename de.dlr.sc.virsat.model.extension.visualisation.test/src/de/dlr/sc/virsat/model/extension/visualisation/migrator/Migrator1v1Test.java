/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.migrator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;

/**
 * This class tests the custom implementation for the migration of the visualisation concept
 * from version 1.0 to version 1.1
 * @author muel_s8
 *
 */

public class Migrator1v1Test extends AMigrator1v1Test {

	@Test
	public void testMigrateChangePropertyStringToEnum() {
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		repository.getActiveConcepts().add(conceptCurrent);
		
		conceptCurrent.setName("de.dlr.test.concept.migrate.se");
		conceptNext.setName("de.dlr.test.concept.migrate.se");
		
		StructuralElement seCurrent = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seNext = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seCurrent.setName("StructuralElement");
		seNext.setName("StructuralElement");
		seCurrent.setIsRootStructuralElement(true);
		seNext.setIsRootStructuralElement(true);
		
		Category catCurrent = CategoriesFactory.eINSTANCE.createCategory();
		Category catNext = CategoriesFactory.eINSTANCE.createCategory();
		
		catCurrent.setName("Category");
		catNext.setName("Category");
		catCurrent.setIsApplicableForAll(true);
		catNext.setIsApplicableForAll(true);
		
		StringProperty propCurrent = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		EnumProperty propNext = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
		
		propCurrent.setName(Visualisation.PROPERTY_SHAPE);
		propNext.setName(Visualisation.PROPERTY_SHAPE);
		
		propCurrent.setDefaultValue("NONE");
		EList<EnumValueDefinition> enumValues = propNext.getValues();
		EnumValueDefinition none = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		none.setValue("1");
		none.setName("NONE");
		enumValues.add(none);
		EnumValueDefinition box = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		box.setValue("2");
		box.setName("BOX");
		enumValues.add(box);
		
		propNext.setDefaultValue(none);
		
		catCurrent.getProperties().add(propCurrent);
		catNext.getProperties().add(propNext);
		
		conceptCurrent.getCategories().add(catCurrent);
		conceptNext.getCategories().add(catNext);
		
		Concept conceptPrevious = EcoreUtil.copy(conceptCurrent);
		
		StructuralInstantiator si = new StructuralInstantiator();
		StructuralElementInstance seiCurrent = si.generateInstance(seCurrent, "StructuralElementInstance");
		
		CategoryInstantiator ci = new CategoryInstantiator();
		CategoryAssignment caCurrent = ci.generateInstance(catCurrent, "CategoryAssignment");
		
		seiCurrent.getCategoryAssignments().add(caCurrent);
		
		repository.getRootEntities().add(seiCurrent);
		
		Migrator1v1 migrator = new Migrator1v1();
		migrator.migrate(conceptPrevious, conceptCurrent, conceptNext);
		
		APropertyInstance pi = caCurrent.getPropertyInstances().get(0);
		assertTrue("Property instance has correct type", pi instanceof EnumUnitPropertyInstance);
		
		EnumUnitPropertyInstance eupi = (EnumUnitPropertyInstance) pi;
		assertEquals("Property has been updated correctly", none.getName(), eupi.getValue().getName());
	}

}
