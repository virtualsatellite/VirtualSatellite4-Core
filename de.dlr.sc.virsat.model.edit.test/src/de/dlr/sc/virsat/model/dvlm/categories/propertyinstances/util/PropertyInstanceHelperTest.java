/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.property.ABeanPropertyTest;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;

/**
 * TestCases for the PropertyInstancehelper which is mainly used in
 * the beans for the moment. the BeanPropertyTest just delivers the needed editing domain
 * @author fisc_ph
 *
 */
public class PropertyInstanceHelperTest extends ABeanPropertyTest {

	private EnumUnitPropertyInstance eupi;

	private Repository repo;
	private Concept concept;
	private UnitManagement unitManagement;
	private SystemOfUnits sou;
	
	private Category testCategory;
	
	private EnumProperty propertyOne;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		EnumValueDefinition evd1 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		EnumValueDefinition evd2 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();

		evd1.setName("HIGH");
		evd2.setName("LOW");
		evd1.setValue("12");
		evd2.setValue("3");
		
		EnumProperty enumProperty = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
		enumProperty.getValues().add(evd1);
		enumProperty.getValues().add(evd2);
		enumProperty.setDefaultValue(evd2);
			
		eupi = PropertyinstancesFactory.eINSTANCE.createEnumUnitPropertyInstance();
		eupi.setType(enumProperty);
		UserRegistry.getInstance().setSuperUser(true);

		testCategory = CategoriesFactory.eINSTANCE.createCategory();

		propertyOne = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
		testCategory.getProperties().add(propertyOne);
		testCategory.setIsApplicableForAll(true);

		repo = DVLMFactory.eINSTANCE.createRepository();
		concept = ConceptsFactory.eINSTANCE.createConcept();
		unitManagement = UnitsFactory.eINSTANCE.createUnitManagement();
		
		sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		
		unitManagement.setSystemOfUnit(sou);
		repo.setUnitManagement(unitManagement);
		concept.getCategories().add(testCategory);
		concept.setName("testConcept");
		repo.getActiveConcepts().add(concept);
		
		eupi.setType(propertyOne);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSetUnitEditingDomain() {
		new PropertyInstanceHelper().setUnit(ed, eupi, "Kilogram").execute();
		assertEquals("Unit has been set correctly", "Kilogram", eupi.getUnit().getName());
		
		new PropertyInstanceHelper().setUnit(ed, eupi, "Gram").execute();
		assertEquals("Unit has been changed correctly", "Gram", eupi.getUnit().getName());

		Command cmd = new PropertyInstanceHelper().setUnit(ed, eupi, "Gargl");
		assertFalse("The unit has not been changed", cmd.canExecute());
	}

	@Test
	public void testSetUnit() {
		boolean changed;
		
		changed = new PropertyInstanceHelper().setUnit(eupi, "Kilogram");
		assertTrue("The unit has been changed", changed);
		assertEquals("Unit has been set correctly", "Kilogram", eupi.getUnit().getName());
		
		changed = new PropertyInstanceHelper().setUnit(eupi, "Gram");
		assertTrue("The unit has been changed", changed);
		assertEquals("Unit has been changed correctly", "Gram", eupi.getUnit().getName());

		changed = new PropertyInstanceHelper().setUnit(eupi, "Gargl");
		assertFalse("The unit has not been changed", changed);
		assertEquals("Unit gargl still needs to be invented, thus gramm should be the truth", "Gram", eupi.getUnit().getName());
	}
	
	@Test
	public void testGetUnit() {
		new PropertyInstanceHelper().setUnit(eupi, "Kilogram");
		assertEquals("Unit has been set correctly", "Kilogram", eupi.getUnit().getName());
		assertEquals("Unit has been set correctly", "Kilogram", new PropertyInstanceHelper().getUnit(eupi));
		
		new PropertyInstanceHelper().setUnit(eupi, "Gram");
		assertEquals("Unit has been changed correctly", "Gram", eupi.getUnit().getName());
		assertEquals("Unit has been changed correctly", "Gram", new PropertyInstanceHelper().getUnit(eupi));

		new PropertyInstanceHelper().setUnit(eupi, "Gargl");
		assertEquals("Unit gargl still needs to be invented, thus gramm should be the truth", "Gram", eupi.getUnit().getName());
		assertEquals("Unit gargl still needs to be invented, thus gramm should be the truth", "Gram", new PropertyInstanceHelper().getUnit(eupi));
	}
	
	
	@Test
	public void testIsCalculated() {
		Category testCategory = CategoriesFactory.eINSTANCE.createCategory();
		AProperty testProperty = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		testCategory.getProperties().add(testProperty);
		
		CategoryAssignment caCalculated = new CategoryInstantiator().generateInstance(testCategory, "testCa1");
		CategoryAssignment caNotCalculated = new CategoryInstantiator().generateInstance(testCategory, "testCa2");
		
		UnitValuePropertyInstance vpiCalculated = (UnitValuePropertyInstance) caCalculated.getPropertyInstances().get(0);
		UnitValuePropertyInstance vpiNotCalculated = (UnitValuePropertyInstance) caNotCalculated.getPropertyInstances().get(0);
		
		// Setup some simple equation referencing vpiCalculated
		EquationSection eqSection = CalculationFactory.eINSTANCE.createEquationSection();
		Equation eq = CalculationFactory.eINSTANCE.createEquation();
		TypeInstanceResult tir = CalculationFactory.eINSTANCE.createTypeInstanceResult();
		tir.setReference(vpiCalculated);
		eq.setResult(tir);
		NumberLiteral nl = CalculationFactory.eINSTANCE.createNumberLiteral();
		nl.setValue("1");
		eq.setExpression(nl);
		eqSection.getEquations().add(eq);
		
		caCalculated.setEquationSection(eqSection);
		
		PropertyInstanceHelper piHelper = new PropertyInstanceHelper() {
			@Override
			protected List<ITypeInstanceSetterProvider<?>> loadExtensionPoint() {
				// Don' t load the extension point to check for default behavior of this method
				// set an empty list instead 
				return Collections.emptyList();
			}
		};
		
		assertTrue("This is a calculated value", piHelper.isCalculated(vpiCalculated));
		assertFalse("This is not a calculated value", piHelper.isCalculated(vpiNotCalculated));
	}
	
	@Test
	public void testIsCalculatedWithSetterProvider() {
		Category testCategory = CategoriesFactory.eINSTANCE.createCategory();
		AProperty testProperty = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		testCategory.getProperties().add(testProperty);
		
		CategoryAssignment caCalculated = new CategoryInstantiator().generateInstance(testCategory, "testCa1");
		
		UnitValuePropertyInstance vpiCalculated = (UnitValuePropertyInstance) caCalculated.getPropertyInstances().get(0);
		
		// Setup some simple equation referencing vpiCalculated
		EquationSection eqSection = CalculationFactory.eINSTANCE.createEquationSection();
		Equation eq = CalculationFactory.eINSTANCE.createEquation();
		TypeInstanceResult tir = CalculationFactory.eINSTANCE.createTypeInstanceResult();
		tir.setReference(vpiCalculated);
		eq.setResult(tir);
		NumberLiteral nl = CalculationFactory.eINSTANCE.createNumberLiteral();
		nl.setValue("1");
		eq.setExpression(nl);
		eqSection.getEquations().add(eq);
		
		caCalculated.setEquationSection(eqSection);
		
		final TestTypeInstanceSetterProvider provider = new TestTypeInstanceSetterProvider();
		
		PropertyInstanceHelper piHelper = new PropertyInstanceHelper() {
			@Override
			protected List<ITypeInstanceSetterProvider<?>> loadExtensionPoint() {
				List<ITypeInstanceSetterProvider<?>> providers = new ArrayList<ITypeInstanceSetterProvider<?>>();
				providers.add(provider);
				return providers; 
			}
		};
		
		assertFalse("The TypeInstanceSetter will have no clue about the correct type", piHelper.isCalculated(vpiCalculated));
		assertTrue("The TypeInstanceSetter got correctly called by the underlying methods", provider.hitIsApplicableFor);
		assertTrue("The TypeInstanceSetter got correctly called by the underlying methods", provider.hitGetAffectedTypeInstance);
	}
	
	/**
	 * TypeIsntanceSetterProvider mock for correctly testing is calculated method
	 * @author fisc_ph
	 *
	 */
	static class TestTypeInstanceSetterProvider implements ITypeInstanceSetterProvider<ITypeInstanceSetter> {
		
		//CHECKSTYLE:OFF
		public boolean hitIsApplicableFor = false;
		public boolean hitGetAffectedTypeInstance = false;
		//CHECKSTYLE:ON
		
		protected ITypeInstanceSetter testTypeInstanceSetter = new ITypeInstanceSetter() {
			
			@Override
			public boolean isApplicableFor(ATypeInstance instance) {
				hitIsApplicableFor = true;
				return true;
			}
			
			@Override
			public List<ATypeInstance> getAffectedTypeInstances(ATypeInstance instance) {
				hitGetAffectedTypeInstance = true;
				return Collections.emptyList();
			}
		};
		
		@Override
		public List<ITypeInstanceSetter> getTypeInstanceSetters() {
			List<ITypeInstanceSetter> typeInstanceSetters = new ArrayList<ITypeInstanceSetter>();
			typeInstanceSetters.add(testTypeInstanceSetter);
			return typeInstanceSetters;
		}
	};
	
}
