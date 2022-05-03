/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.migrator;

import org.junit.Before;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;

/**
 * Tests the concept migrator
 * @author muel_s8
 *
 */

public abstract class AConceptMigratorTest {
	
	protected static final String DEFAULT_VALUE_A = "100";
	
	protected Concept oldConcept;
	protected StructuralElement structuralElementA;
	protected StructuralElement structuralElementB;
	protected Category categoryA;
	protected StructuralElementInstance seiA;
	protected CategoryAssignment caA;
	protected Repository repository;
	protected Category categoryB;
	protected StructuralElementInstance seiB;
	protected CategoryAssignment caB;
	protected ValuePropertyInstance vpi;
	protected EquationDefinition eqDefinition;
	
	@Before
	public void setup() throws Exception {
		repository = DVLMFactory.eINSTANCE.createRepository();
		
		// Setup some concept for the test cases
		oldConcept = ConceptsFactory.eINSTANCE.createConcept();
		oldConcept.setVersion("1.0");
		oldConcept.setName("de.dlr.sc.virsat.old.concept");
		repository.getActiveConcepts().add(oldConcept);
		
		structuralElementA = StructuralFactory.eINSTANCE.createStructuralElement();
		structuralElementA.setName("StructuralElementA");
		structuralElementA.setIsRootStructuralElement(true);
		oldConcept.getStructuralElements().add(structuralElementA);
		
		structuralElementB = StructuralFactory.eINSTANCE.createStructuralElement();
		structuralElementB.setName("StructuralElementB");
		structuralElementB.getApplicableFor().add(structuralElementA);
		structuralElementB.getCanInheritFrom().add(structuralElementA);
		oldConcept.getStructuralElements().add(structuralElementB);
		
		categoryA = CategoriesFactory.eINSTANCE.createCategory();
		categoryA.setName("CategoryA");
		categoryA.getApplicableFor().add(structuralElementA);
		oldConcept.getCategories().add(categoryA);
		
		eqDefinition = CalculationFactory.eINSTANCE.createEquationDefinition();
		eqDefinition.setExpression(CalculationFactory.eINSTANCE.createValueE());
		categoryA.getEquationDefinitions().add(eqDefinition);
		
		categoryB = CategoriesFactory.eINSTANCE.createCategory();
		categoryB.setName("CategoryB");
		categoryB.getApplicableFor().add(structuralElementB);
		oldConcept.getCategories().add(categoryB);
		
		AProperty propertyA = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		propertyA.setName("a");
		categoryA.getProperties().add(propertyA);
		
		// Setup a system model for the test cases
		/*
		 * SEIA
		 * - CAA
		 * -- A
		 * SEIB
		 * - CAB
		 */
		
		StructuralInstantiator structuralInstantiator = new StructuralInstantiator();
		CategoryInstantiator categoryInstantiator = new CategoryInstantiator();
		
		seiA = structuralInstantiator.generateInstance(structuralElementA, "SEIA");
		repository.getRootEntities().add(seiA);
		
		seiB = structuralInstantiator.generateInstance(structuralElementB, "SEIB");
		repository.getRootEntities().add(seiB);
		
		caA = categoryInstantiator.generateInstance(categoryA, "CAA");
		vpi = (ValuePropertyInstance) caA.getPropertyInstances().get(0);
		vpi.setValue(DEFAULT_VALUE_A);
		seiA.getCategoryAssignments().add(caA);
		
		caB = categoryInstantiator.generateInstance(categoryB, "CAB");
		seiB.getCategoryAssignments().add(caB);
		seiA.getChildren().add(seiB);
	}
}
