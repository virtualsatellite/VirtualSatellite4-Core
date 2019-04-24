/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.junit.Before;
import org.junit.Test;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.PropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.resource.provider.DVLMResourceItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;

/**
 * Test Case for the overridden getText Method of the DVLMCategoryAssignmentItemProvider
 * @author lobe_el
 *
 */
public class DVLMCategoryAssignmentItemProviderTest {
	
	ComposedAdapterFactory adapterFactory;
	Category category;
	CategoryAssignment ca;
	StructuralElement se;
	StructuralElementInstance sei;
	
	@Before
	public void setUp() {
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		category = CategoriesFactory.eINSTANCE.createCategory();
		ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ca.setType(category);
		
		se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setIsApplicableForAll(true);
		se.setIsRootStructuralElement(true);
		
		sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		
		category.setIsApplicableForAll(true);
		
		UserRegistry.getInstance().setSuperUser(true);
	}
	
	@Test
	public void testGetText() {
		UserRegistry.getInstance().setSuperUser(false);
		DVLMCategoryAssignmentItemProvider dcaip = new DVLMCategoryAssignmentItemProvider(adapterFactory);
		
		final String C_NAME = "ThisIsA TestCategory";
		final String CA_NAME = "ThisIsA TestCategoryAssignment";
		final String C_ABBR = "TIATC";
		final String C_ABBR_OTHER = "TC";
		
		category.setName(C_NAME);
		String expectedOutput = C_ABBR + ":";
		String output = dcaip.getText(ca);
		assertEquals("The output is like expected", expectedOutput, output);
		
		ca.setName(CA_NAME);
		expectedOutput = C_ABBR + ": " + CA_NAME;
		output = dcaip.getText(ca);
		assertEquals("The output is like expected", expectedOutput, output);

		category.setShortName(C_ABBR_OTHER);
		expectedOutput = C_ABBR_OTHER + ": " + CA_NAME;
		output = dcaip.getText(ca);
		assertEquals("The output is like expected", expectedOutput, output);
	}
	
	@Test
	public void testCreateRemoveCategoryAssignmentCommand() {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new DVLMResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMDVLMItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ConceptsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RolesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertydefinitionsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertyinstancesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		BasicCommandStack fulkaCommandStack = new BasicCommandStack();
		EditingDomain ed = new AdapterFactoryEditingDomain(adapterFactory, fulkaCommandStack, new HashMap<Resource, Boolean>());
	
		sei.getCategoryAssignments().add(ca);
		
		assertThat("Sei contains CA", sei.getCategoryAssignments(), hasItems(ca));
		
		Command removeCommand = RemoveCommand.create(ed, ca);
		assertTrue("Command is executable", removeCommand.canExecute());
		
		CategoryAssignment ca2 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ca2.setType(category);
		
		ca.getSuperTis().add(ca2);
		
		Command removeCommand2 = RemoveCommand.create(ed, ca);
		assertFalse("Command is executable", removeCommand2.canExecute());
	}
}
