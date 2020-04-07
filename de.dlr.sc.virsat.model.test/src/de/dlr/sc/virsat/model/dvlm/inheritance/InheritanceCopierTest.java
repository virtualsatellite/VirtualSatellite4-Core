/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.inheritance;



import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.calculation.AExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationResult;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedDefinitionInput;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeDefinitionResult;
import de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Test Cases for the inheritance functionality
 * 
 */
public class InheritanceCopierTest extends AInheritanceCopierTest {
	
	
	/**
	 * This method helps to update an SEI and directly check the needs update method along the call
	 * only checks one inheritance level before
	 * @param ic The Inheritance Copier Instance to be used
	 * @param subSei the Sub SEI
	 * @return a List of CAs that has been copied/updated
	 */
	private Set<CategoryAssignment> updateAssertSei(InheritanceCopier ic, StructuralElementInstance subSei) {
		assertTrue("Needs an update", ic.needsUpdateStep(subSei));
		Set<CategoryAssignment> copiedCas = ic.updateStep(subSei);
		assertFalse("Update is not needed anymore", ic.needsUpdateStep(subSei));
		return copiedCas;
	}

	
	// ----- Tests for the Updates only from one inheritance level before ----- 
	
	@Test
	public void testUpdateStep() {
		CategoryAssignment caRwIfe = attachInterfaceEnd(seiEdRw, "IfeRw");

		InheritanceCopier ic = new InheritanceCopier();
		Set<CategoryAssignment>copiedCas = updateAssertSei(ic, seiEcRwI);
		
		assertNotNull("copied CAs is well returned", copiedCas);
		assertEquals("Now we should have exactly one CA in the RWI", 1, seiEcRwI.getCategoryAssignments().size());
		CategoryAssignment caCopied = seiEcRwI.getCategoryAssignments().get(0); 
		assertEquals("Now we should have exactly one CA in the RWI", 1, caCopied.getPropertyInstances().size());
		
		// Now check for the Links being set correctly
		assertThat("Link of Category Assignment has been set correctly", caCopied.getSuperTis(), hasItems(caRwIfe));
	}
	
	@Test
	public void testUniqueUuuidsOfCopiedCas() {
		CategoryAssignment caRwIfe = attachInterfaceEnd(seiEdRw, "IfeRw");
		
		InheritanceCopier ic = new InheritanceCopier();
		
		List<CategoryAssignment> subCas = ic.getSubCategoryAssignments(seiEcRwI, seiEdRw);
		
		CategoryAssignment caCopied = subCas.get(0); 
		assertNotSame("UUIDs should be different", caRwIfe.getUuid(), caCopied.getUuid());
	}
	
	@Test
	public void testUpdateStepRWIWithNewInformation() {
		// in this test case we want to check, that the correct information from the RW Definition will be copied to the
		// RWI. In this TestCase RWI does not have any other information present.
		CategoryAssignment caRwIfe = attachInterfaceEnd(seiEdRw, "IfeRw");

		InheritanceCopier ic = new InheritanceCopier();
		List<CategoryAssignment> subCas = ic.getSubCategoryAssignments(seiEcRwI, seiEdRw);
		
		assertEquals("Now we should have exactly one CA in the RWI", 1, subCas.size());
		CategoryAssignment caCopied = subCas.get(0); 
		assertEquals("Now we should have exactly one CA in the RWI", 1, caCopied.getPropertyInstances().size());

		
		// Now check for the Links being set correctly
		assertThat("Link of Category Assignment has been set correctly", caCopied.getSuperTis(), hasItems(caRwIfe));
	}
	
	@Test
	public void testDontCopyTwice() {
		// We want to make sure that information that is copied once will not be added a second time to the
		// Sub SEI. If the Sub SEI has already the Category Assignment from its parent it should not be copied again
		attachInterfaceEnd(seiEdRw, "IfeRw");

		InheritanceCopier ic = new InheritanceCopier();
		List<CategoryAssignment> subCas = ic.getSubCategoryAssignments(seiEcRwI, seiEdRw);
		CategoryAssignment caCopy1 = subCas.get(0);
		assertEquals("Now we should have exactly one CA in the RWI", 1, subCas.size());

		seiEcRwI.getCategoryAssignments().addAll(subCas);
		
		// Now call copier a second time and the reaction wheel should still have one CA only
		List<CategoryAssignment> subCas2 = ic.getSubCategoryAssignments(seiEcRwI, seiEdRw);
		CategoryAssignment caCopy2 = subCas2.get(0);
		assertEquals("Now we should have exactly one CA in the RWI", 1, subCas2.size());
		
		// The CA that we have stored already and the one that just got copied have to be the same objects
		assertSame("Both CAs are exactly the same", caCopy1, caCopy2);
	}
	
	@Test
	public void testGetInheritedATypeInstanceFor() {
		CategoryAssignment caRwIfe = attachInterfaceEnd(seiEdRw, "IfeRw");

		InheritanceCopier ic = new InheritanceCopier();
		List<CategoryAssignment> subCas = ic.getSubCategoryAssignments(seiEcRwI, seiEdRw);

		IInheritanceLink subATypeInstance = ic.getInheritedIInheritanceLinkFor(caRwIfe, seiEcRwI);
		assertNull("CA has not yet been copiedl, so it should not be found", subATypeInstance);
		
		// Do the copy manually by adding the copied CAs to the RW Configuration Element
		seiEcRwI.getCategoryAssignments().addAll(subCas);
		CategoryAssignment caCopy1 = subCas.get(0);
		
		subATypeInstance = ic.getInheritedIInheritanceLinkFor(caRwIfe, seiEcRwI);
		assertEquals("Now it has been copied, should be the CA we have added", subATypeInstance, caCopy1);
	}
	
	@Test
	public void testUpdateStepNonPropagatingCa() {
		attachSpecification(seiErRwA, "specData");
		
		assertEquals("ER has specification category attached", 1, seiErRwA.getCategoryAssignments().size());
		assertEquals("ER has specification category attached", catSpec, seiErRwA.getCategoryAssignments().get(0).getType());
		
		InheritanceCopier ic = new InheritanceCopier();
		
		// Now updating the EO should first of all not be required, since the 
		// SPEC Category is only applicable for the ER but not the EO therefore it
		// should not be inherited
		assertFalse("The EO should not need an update", ic.needsUpdateStep(seiEo1RwI));
		ic.updateStep(seiEo1RwI);
		assertFalse("The EO should not need an update", ic.needsUpdateStep(seiEo1RwI));
			
		// There should be no new category at the EO
		assertTrue("there are no CAs at the EO", seiEo1RwI.getCategoryAssignments().isEmpty());
	}
	
	@Test
	public void testNeedsUpdateStep() {						//!!!!!
		attachInterfaceEnd(seiEdRw, "IfeRw");

		InheritanceCopier ic = new InheritanceCopier();
		boolean needsUpdate = ic.needsUpdateStep(seiEcRwI);
		
		assertTrue("CA is not added, hence seiRWIConf needs Update", needsUpdate);
		
		ic.updateStep(seiEcRwI);
		needsUpdate = ic.needsUpdateStep(seiEcRwI);
		
		assertFalse("Update done, hence no Update should be needed", needsUpdate);
	}
	
	@Test
	public void testInheritReferencePropertyInstance() {
		CategoryAssignment caRwIfe1 = attachInterfaceEnd(seiEcRwI, "IfeRw1");
		CategoryAssignment caRwIfe2 = attachInterfaceEnd(seiEcRwI, "IfeRw2");
		CategoryAssignment caRwIf = attachInterface(seiEcRwI, "If", caRwIfe1, caRwIfe2, caIftMil);
		ReferencePropertyInstance rpiRwIfIft = (ReferencePropertyInstance) caRwIf.getPropertyInstances().get(2);
		
		InheritanceCopier ic = new InheritanceCopier();
		updateAssertSei(ic, seiEo1RwI);
		
		//CHECKSTYLE:OFF
		assertEquals("Now we should have exactly three CAs in the RWI", 3, seiEo1RwI.getCategoryAssignments().size());
		CategoryAssignment copiedCaRwIfe1 = seiEo1RwI.getCategoryAssignments().get(0);
		CategoryAssignment copiedCaRwIfe2 = seiEo1RwI.getCategoryAssignments().get(1);
		
		CategoryAssignment copiedCaRwIf = seiEo1RwI.getCategoryAssignments().get(2);
		ReferencePropertyInstance copiedRpiRwIfIfe1 = (ReferencePropertyInstance) copiedCaRwIf.getPropertyInstances().get(0);
		ReferencePropertyInstance copiedRpiRwIfIfe2 = (ReferencePropertyInstance) copiedCaRwIf.getPropertyInstances().get(1);
		assertEquals("The Reference should point to copied CA", copiedCaRwIfe1, copiedRpiRwIfIfe1.getReference());
		assertEquals("The Reference should point to copied CA", copiedCaRwIfe2, copiedRpiRwIfIfe2.getReference());

		ReferencePropertyInstance copiedRpiRwIfIft = (ReferencePropertyInstance) copiedCaRwIf.getPropertyInstances().get(2);
		assertEquals("The InterfaceType is still correctly referenced", caIftMil, copiedRpiRwIfIft.getReference());
		
		// Now change the interface type and update again
		rpiRwIfIft.setReference(caIftCan);
		updateAssertSei(ic, seiEo1RwI);

		assertEquals("The InterfaceType is still correctly referenced", caIftCan, copiedRpiRwIfIft.getReference());
		//CHECKSTYLE:ON
	}

	@Test
	public void testInheritReferencePropertyInstanceFromDifferentSei() {
		CategoryAssignment caRwIfe1 = attachInterfaceEnd(seiEcRwI, "IfeRw1");
		CategoryAssignment caObcIfe2 = attachInterfaceEnd(seiEcObc, "IfeObcRwI");
		CategoryAssignment caObcIf = attachInterface(seiEcObc, "If", caRwIfe1, caObcIfe2, caIftMil);
		ReferencePropertyInstance rpiObcIfIft = (ReferencePropertyInstance) caObcIf.getPropertyInstances().get(2);
		
		attachSpecification(seiErRwA, "specData");
		assertEquals("ER has specification category attached", 1, seiErRwA.getCategoryAssignments().size());
		assertEquals("ER has specification category attached", catSpec, seiErRwA.getCategoryAssignments().get(0).getType());
		
		InheritanceCopier ic = new InheritanceCopier();
		updateAssertSei(ic, seiEo1RwI);
		updateAssertSei(ic, seiEo1Obc);
		
		assertEquals("Now we should have exactly one IFE in the RWI", 1, seiEo1RwI.getCategoryAssignments().size());
		CategoryAssignment copiedEoRwIFe = seiEo1RwI.getCategoryAssignments().get(0);

		assertEquals("Now we should have exactly one IF and one IFE in the OBC", 2, seiEo1Obc.getCategoryAssignments().size());

		CategoryAssignment copiedEoObcIFe = seiEo1Obc.getCategoryAssignments().get(0);
		CategoryAssignment copiedEoObcIF = seiEo1Obc.getCategoryAssignments().get(1);

		ReferencePropertyInstance copiedEoObcIfIfe1 = (ReferencePropertyInstance) copiedEoObcIF.getPropertyInstances().get(0);
		ReferencePropertyInstance copiedEoObcIfIfe2 = (ReferencePropertyInstance) copiedEoObcIF.getPropertyInstances().get(1);
		
		assertEquals("The Reference of IF should point to RW IFE CA", copiedEoRwIFe, copiedEoObcIfIfe1.getReference());
		assertEquals("The Reference of IF should point to OBC IFE CA", copiedEoObcIFe, copiedEoObcIfIfe2.getReference());
	
		ReferencePropertyInstance copiedRpiObcIfIft = (ReferencePropertyInstance) copiedEoObcIF.getPropertyInstances().get(2);
		assertEquals("The InterfaceType is still correctly referenced", caIftMil, copiedRpiObcIfIft.getReference());
	
		// Now change the interface type and update again
		rpiObcIfIft.setReference(caIftCan);
		updateAssertSei(ic, seiEo1Obc);

		assertEquals("The InterfaceType is still correctly referenced", caIftCan, copiedRpiObcIfIft.getReference());
	}
	
	@Test
	public void testInheritReferencePropertyInstanceFromDifferentSeiWithPreloading() {
		CategoryAssignment caRwIfe1 = attachInterfaceEnd(seiEcRwI, "IfeRw1");
		CategoryAssignment caObcIfe2 = attachInterfaceEnd(seiEcObc, "IfeObcRwI");
		attachInterface(seiEcObc, "If", caRwIfe1, caObcIfe2, caIftMil);

		// First update the RW so that all IFEs are copied from the ED
		InheritanceCopier ic = new InheritanceCopier();
		updateAssertSei(ic, seiEo1RwI);
		assertEquals("Now we should have exactly one IFE in the RWI", 1, seiEo1RwI.getCategoryAssignments().size());
		CategoryAssignment copiedEoRwIFe = seiEo1RwI.getCategoryAssignments().get(0);

		// Now instantiate a new copier, which does not actually know about the copied
		// InterfaceEnds at the RW. The pre-load mechanisms has to identify them by itself
		InheritanceCopier ic2 = new InheritanceCopier();
		updateAssertSei(ic2, seiEo1Obc);

		assertEquals("Now we should have exactly one IF and one IFE in the OBC", 2, seiEo1Obc.getCategoryAssignments().size());

		CategoryAssignment copiedEoObcIFe = seiEo1Obc.getCategoryAssignments().get(0);
		CategoryAssignment copiedEoObcIF = seiEo1Obc.getCategoryAssignments().get(1);

		ReferencePropertyInstance copiedEoObcIfIfe1 = (ReferencePropertyInstance) copiedEoObcIF.getPropertyInstances().get(0);
		ReferencePropertyInstance copiedEoObcIfIfe2 = (ReferencePropertyInstance) copiedEoObcIF.getPropertyInstances().get(1);
		
		assertEquals("The Reference of IF should point to RW IFE CA", copiedEoRwIFe, copiedEoObcIfIfe1.getReference());
		assertEquals("The Reference of IF should point to OBC IFE CA", copiedEoObcIFe, copiedEoObcIfIfe2.getReference());
	
		ReferencePropertyInstance copiedRpiObcIfIft = (ReferencePropertyInstance) copiedEoObcIF.getPropertyInstances().get(2);
		assertEquals("The InterfaceType is still correctly referenced", caIftMil, copiedRpiObcIfIft.getReference());
	}
	
	@Test
	public void testInheritOverrideIntPropertyFromRw() {
		CategoryAssignment caIfe = attachInterfaceEnd(seiEdRw, "canBus1");
		APropertyInstance api = caIfe.getPropertyInstances().get(0);
		ValuePropertyInstance vpi = (ValuePropertyInstance) api; 

		final String TEST_INT_VAL_1 = "1234";
		vpi.setValue(TEST_INT_VAL_1);
		
		InheritanceCopier ic = new InheritanceCopier();
		
		updateAssertSei(ic, seiEcRwI);
		
		// now check the RW Configuration if the CA with the value got well copied
		CategoryAssignment copiedCa = seiEcRwI.getCategoryAssignments().get(0);
		ValuePropertyInstance copiedVpi = (ValuePropertyInstance) copiedCa.getPropertyInstances().get(0);
		
		assertEquals("Received the correct Value at Config Level", TEST_INT_VAL_1, copiedVpi.getValue());
		
		// checking Update with new value
		final String TEST_INT_VAL_2 = "2345";
		vpi.setValue(TEST_INT_VAL_2);
		
		updateAssertSei(ic, seiEcRwI);
		
		// now check the RW Configuration if the value got well copied
		CategoryAssignment copiedCa2 = seiEcRwI.getCategoryAssignments().get(0);
		ValuePropertyInstance copiedVpi2 = (ValuePropertyInstance) copiedCa2.getPropertyInstances().get(0);
		
		assertSame("CA should not be touched", copiedCa, copiedCa2);
		assertEquals("Received the correct Value at Config Level", TEST_INT_VAL_2, copiedVpi2.getValue());
		
		// checking Update with new value but having the receiving property being set to override, meaning that its actual
		// values should not be changed.
		final String TEST_INT_VAL_3 = "3456";
		vpi.setValue(TEST_INT_VAL_3);
		copiedVpi2.setOverride(true);

		assertFalse("Actually we would not need an update, since the override flag tells to not update the part of the model", ic.needsUpdateStep(seiEcRwI));
		ic.updateStep(seiEcRwI);
		assertFalse("Nothing should have changed after the update should be the same state as before", ic.needsUpdateStep(seiEcRwI));
		
		// now check the RW Configuration if the value got well copied
		CategoryAssignment copiedCa3 = seiEcRwI.getCategoryAssignments().get(0);
		ValuePropertyInstance copiedVpi3 = (ValuePropertyInstance) copiedCa3.getPropertyInstances().get(0);
		
		assertSame("CA should not be touched", copiedCa, copiedCa3);
		assertEquals("Received the correct Value at Config Level, value is set to override", TEST_INT_VAL_2, copiedVpi3.getValue());
	}
	
	@Test
	public void testInheritOverrideReferencePropertyInstance() {
		CategoryAssignment caRwIfeE1 = attachInterfaceEnd(seiEcRwI, "IfeRw1");
		attachInterfaceEnd(seiEcRwI, "IfeRw2");
		CategoryAssignment caRwIfeS = attachInterfaceEnd(seiEcRwI, "IfeRw2");
		attachInterface(seiEcRwI, "If", caRwIfeS, caRwIfeE1, caIftMil);

		InheritanceCopier ic = new InheritanceCopier();
		updateAssertSei(ic, seiEo1RwI);
		
		//CHECKSTYLE:OFF
		assertEquals("Now we should have exactly two CA in the RWI", 4, seiEo1RwI.getCategoryAssignments().size());
		seiEo1RwI.getCategoryAssignments().get(0);
		CategoryAssignment copiedCaRwIfeE2 = seiEo1RwI.getCategoryAssignments().get(1);
		seiEo1RwI.getCategoryAssignments().get(2);
		CategoryAssignment copiedCaRwIf = seiEo1RwI.getCategoryAssignments().get(3);
		//CHECKSTYLE:ON

		// Now we use the second RPI to bend the interface end to the second one
		// we set the RPI to override so it will not inherit the original reference from its super SEI.
		ReferencePropertyInstance copiedRpiRwIfIfe2 = (ReferencePropertyInstance) copiedCaRwIf.getPropertyInstances().get(1);
		copiedRpiRwIfIfe2.setOverride(true);
		copiedRpiRwIfIfe2.setReference(copiedCaRwIfeE2);
		
		assertFalse("Override set to true, no change needed", ic.needsUpdateStep(seiEo1RwI));
		ic.updateStep(seiEo1RwI);
		assertFalse("Still no update needed", ic.needsUpdateStep(seiEo1RwI));
		
		assertEquals("The Reference should point to copied CA", copiedCaRwIfeE2, copiedRpiRwIfIfe2.getReference());
	}
	
	@Test
	public void testInheritOverrideReferencePropertyInstanceFromDifferentSei() {
		CategoryAssignment caRwIfe1 = attachInterfaceEnd(seiEcRwI, "IfeRw1");
		attachInterfaceEnd(seiEcRwI, "IfeRw2");
		CategoryAssignment caObcIfe2 = attachInterfaceEnd(seiEcObc, "IfeObcRwI");
		attachInterface(seiEcObc, "If", caRwIfe1, caObcIfe2, caIftMil);

		InheritanceCopier ic = new InheritanceCopier();
		updateAssertSei(ic, seiEo1RwI);
		updateAssertSei(ic, seiEo1Obc);
		
		assertEquals("Now we should have two IFE in the RWI", 2, seiEo1RwI.getCategoryAssignments().size());
		assertEquals("Now we should have an IFE and IF on in the OBC", 2, seiEo1Obc.getCategoryAssignments().size());
		CategoryAssignment copiedEoRwIFe2 = seiEo1RwI.getCategoryAssignments().get(1);

		CategoryAssignment copiedEoObcIF = seiEo1Obc.getCategoryAssignments().get(1);

		ReferencePropertyInstance copiedEoObcIfIfe1 = (ReferencePropertyInstance) copiedEoObcIF.getPropertyInstances().get(0);
		copiedEoObcIfIfe1.setOverride(true);
		
		copiedEoObcIfIfe1.setReference(copiedEoRwIFe2);
		
		assertFalse("Override set to true, no change needed", ic.needsUpdateStep(seiEo1RwI));
		ic.updateStep(seiEo1RwI);
		assertFalse("Still no update needed", ic.needsUpdateStep(seiEo1RwI));
		
		assertFalse("Override set to true, no change needed", ic.needsUpdateStep(seiEo1Obc));
		ic.updateStep(seiEo1Obc);
		assertEquals("Now we should have two IFE in the RWI", 2, seiEo1RwI.getCategoryAssignments().size());
		assertEquals("Now we should have an IFE and IF on in the OBC", 2, seiEo1Obc.getCategoryAssignments().size());
		assertFalse("Still no update needed", ic.needsUpdateStep(seiEo1Obc));
		
		assertEquals("The Reference of IF should point to RW IFE CA", copiedEoRwIFe2, copiedEoObcIfIfe1.getReference());
	}
	
	@Test
	public void testUpdateMultipleTimes() {
		final String TEST_VAL_1 = "1234";
		final String TEST_VAL_2 = "2345";
		
		CategoryAssignment ca = attachInterfaceEnd(seiEdRw, "RwIe");
		setInterfaceEndSn(ca, TEST_VAL_1);
		
		InheritanceCopier ic = new InheritanceCopier();
		
		ic.updateStep(seiEcRwI);
		assertEquals("Should be a single Ca", 1, seiEcRwI.getCategoryAssignments().size());

		setInterfaceEndSn(ca, TEST_VAL_2);
		ic.updateStep(seiEcRwI);
		assertEquals("Second call does not generate second Ca", 1, seiEcRwI.getCategoryAssignments().size());
	}
	
	@Test
	public void testUpdateWithMultiInheritance() {
		final String TEST_VAL_1 = "1234";
		final String TEST_VAL_2 = "2345";
		
		CategoryAssignment ca = attachInterfaceEnd(seiEdRw, "RwIe");
		setInterfaceEndSn(ca, TEST_VAL_1);
		
		InheritanceCopier ic = new InheritanceCopier();
		
		ic.updateStep(seiEcRwI);
		ic.updateStep(seiErRwA);
		ic.updateStep(seiEo1RwI);
		
		assertEquals("Should be a single Ca", 1, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiErRwA.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEo1RwI.getCategoryAssignments().size());
		
		CategoryAssignment copiedEcRwIIfe = seiEcRwI.getCategoryAssignments().get(0);
		CategoryAssignment copiedErRwAIfe = seiErRwA.getCategoryAssignments().get(0);
		
		assertNotSame("CAs in RwA and RW1 of CT have to be individual objects", copiedEcRwIIfe, copiedErRwAIfe);
		
		assertEquals("Should be a single Ca", 1, seiEo1RwI.getCategoryAssignments().size());
		CategoryAssignment copiedCaEo = seiEo1RwI.getCategoryAssignments().get(0);
		assertEquals("Value correctly inherited", TEST_VAL_1, getInterfaceEndSn(copiedCaEo));
		
		CategoryAssignment copiedCaEr = seiErRwA.getCategoryAssignments().get(0);
		setInterfaceEndSn(copiedCaEr, TEST_VAL_2);
		
		ic.updateStep(seiEo1RwI);
		
		assertEquals("Should be a single Ca", 1, seiEo1RwI.getCategoryAssignments().size());
		CategoryAssignment copiedCaEo2 = seiEo1RwI.getCategoryAssignments().get(0);
		assertEquals("Value correctly inherited", TEST_VAL_2, getInterfaceEndSn(copiedCaEo2));

		assertEquals("SuperSei has correct order", copiedEcRwIIfe, copiedCaEo2.getSuperTis().get(0));
		assertEquals("SuperSei has correct order", copiedErRwAIfe, copiedCaEo2.getSuperTis().get(1));
		
		// Now try to change the order of inheritance
		seiEo1RwI.getSuperSeis().clear();
		seiEo1RwI.getSuperSeis().add(seiErRwA);
		seiEo1RwI.getSuperSeis().add(seiEcRwI);
		
		ic.updateStep(seiEo1RwI);
		
		assertEquals("Link has been sucessfully swapped", copiedErRwAIfe, copiedCaEo2.getSuperTis().get(0));
		assertEquals("Link has been sucessfully swapped", copiedEcRwIIfe, copiedCaEo2.getSuperTis().get(1));	
	}

	
	// ----- Tests of the additional methods -----
	
	@Test
	public void testGetReferencesInTree() {
		InheritanceCopier ic = new InheritanceCopier();
		
		Set<StructuralElementInstance> referencedSeis0 = ic.getReferencesInTree(seiEo1Obc);
		assertTrue("There are no RPIs yet", referencedSeis0.isEmpty());

		CategoryAssignment caRwIfe1 = attachInterfaceEnd(seiEcRwI, "IfeRw1");
		CategoryAssignment caObcIfe1 = attachInterfaceEnd(seiEcObc, "IfeObcRwI");
		attachInterface(seiEcObc, "IfRw1", caRwIfe1, caObcIfe1, caIftMil);
		
		Set<StructuralElementInstance> referencedSeis1 = ic.getReferencesInTree(seiEo1Obc);
		assertThat("Contains correct elements", referencedSeis1, hasItems(seiEo1RwI));
		assertThat("Contains correct elements", referencedSeis1, not(hasItem(seiEo1RwII)));
		assertThat("Contains correct elements", referencedSeis1, not(hasItem(seiEo1Obc)));
		assertEquals("Only one other SEI is refered to", 1, referencedSeis1.size());

		CategoryAssignment caRwIfe2 = attachInterfaceEnd(seiEcRwII, "IfeRw2");
		CategoryAssignment caObcIfe2 = attachInterfaceEnd(seiEcObc, "IfeObcRwII");
		attachInterface(seiEcObc, "IfRw2", caRwIfe2, caObcIfe2, caIftMil);
		
		Set<StructuralElementInstance> referencedSeis2 = ic.getReferencesInTree(seiEo1Obc);
		assertEquals("Two SEI are referenced", 2, referencedSeis2.size());
		assertThat("Contains correct elements", referencedSeis2, hasItems(seiEo1RwI, seiEo1RwII));
	}
	
	@Test
	public void testGetCompleteTree() {
		InheritanceCopier ic = new InheritanceCopier();

		//CHECKSTYLE:OFF
		Set<StructuralElementInstance> treeEo1 = ic.getCompleteTree(seiEo1RwII);
		assertEquals("Has correct number of SEIs", 4, treeEo1.size());
		assertThat("Contains correct elements", treeEo1, hasItems(seiEo1Sc, seiEo1Obc, seiEo1RwI, seiEo1RwII));

		Set<StructuralElementInstance> treeEo1FromRoot = ic.getCompleteTree(seiEo1Sc);
		assertEquals("Has correct number of SEIs", 4, treeEo1FromRoot.size());
		assertThat("Contains correct elements", treeEo1FromRoot, hasItems(seiEo1Sc, seiEo1Obc, seiEo1RwI, seiEo1RwII));

		Set<StructuralElementInstance> treeEd = ic.getCompleteTree(seiEdRw);
		assertEquals("Has correct number of SEIs", 3, treeEd.size());
		assertThat("Contains correct elements", treeEd, hasItems(seiEdSc, seiEdObc, seiEdRw));
		//CHECKSTYLE:ON
	}
	
	@Test
	public void testGetInheritedATypeInstanceForWithMultiInheritance() {
		attachInterfaceEnd(seiEdRw, "RwIfe");
		
		InheritanceCopier ic = new InheritanceCopier();
		
		ic.updateStep(seiEcRwI);
		ic.updateStep(seiErRwA);
		
		assertEquals("Should be a single Ca", 1, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiErRwA.getCategoryAssignments().size());
		assertEquals("Should be no CA yet Ca", 0, seiEo1RwI.getCategoryAssignments().size());
		
		CategoryAssignment copiedEcRwIIfe = seiEcRwI.getCategoryAssignments().get(0);
		CategoryAssignment copiedErRwAIfe = seiErRwA.getCategoryAssignments().get(0);
		
		assertNotSame("CAs in RwA and RW1 of CT have to be individual objects", copiedEcRwIIfe, copiedErRwAIfe);
		
		// Manual Update of the EO to actually just copy the CAs of the EC but not of the ER
		List<CategoryAssignment> copiedCas = ic.getSubCategoryAssignments(seiEo1RwI, seiEcRwI);
		seiEo1RwI.getCategoryAssignments().addAll(copiedCas);

		assertEquals("Should be a single Ca", 1, seiEo1RwI.getCategoryAssignments().size());
		CategoryAssignment copiedEoRwIIfeFromEc = seiEo1RwI.getCategoryAssignments().get(0);
		
		assertThat("Categories are correctly linked", copiedEoRwIIfeFromEc.getSuperTis(), hasItems(copiedEcRwIIfe));
		
		IInheritanceLink referencingATypeInstance = ic.getInheritedIInheritanceLinkFor(copiedEcRwIIfe, seiEo1RwI);
		assertEquals("Found copied CA of RW Eo that is referencing the EC IFE", copiedEoRwIIfeFromEc, referencingATypeInstance);
		
		IInheritanceLink referencingATypeInstance2 = ic.getInheritedIInheritanceLinkFor(copiedErRwAIfe, seiEo1RwI);
		assertSame("The CategoryAssignment of IFE in ER is not yet copied", referencingATypeInstance, referencingATypeInstance2);
		
		// Manual Update of the EO to actually just copy the CAs of the EC but not of the ER
		copiedCas = ic.getSubCategoryAssignments(seiEo1RwI, seiErRwA);
		seiEo1RwI.getCategoryAssignments().addAll(copiedCas);

		assertEquals("Should be a single Ca", 1, seiEo1RwI.getCategoryAssignments().size());
		CategoryAssignment copiedEoRwIIfeFromEr = seiEo1RwI.getCategoryAssignments().get(0);
	
		assertThat("Categories are correctly linked", copiedEoRwIIfeFromEr.getSuperTis(), hasItems(copiedErRwAIfe, copiedEcRwIIfe));
		
		IInheritanceLink referencingATypeInstanceFromEc = ic.getInheritedIInheritanceLinkFor(copiedEcRwIIfe, seiEo1RwI);
		assertEquals("Found copied CA of RW Eo that is referencing the EC IFE", copiedEoRwIIfeFromEc, referencingATypeInstance);
		
		IInheritanceLink referencingATypeInstanceFromEr = ic.getInheritedIInheritanceLinkFor(copiedErRwAIfe, seiEo1RwI);
		assertEquals("Found copied CA of RW Eo that is referencing the EC IFE", copiedEoRwIIfeFromEr, referencingATypeInstance);
		
		assertEquals("Er and Ec deliver into the same TI in EO", referencingATypeInstanceFromEc, referencingATypeInstanceFromEr);
	}
	
	@Test
	public void testGetInheritedATypeInstanceForWithMultiInheritanceCardinality1AndNoCommonAncestor() {
		catIfe.setCardinality(1);
		
		CategoryAssignment ecRwIIfe = attachInterfaceEnd(seiEcRwI, "RwIfe");
		attachInterfaceEnd(seiErRwA, "RwIfe");
		InheritanceCopier ic = new InheritanceCopier();
		
		assertEquals("Should be no CA yet Ca", 0, seiEo1RwI.getCategoryAssignments().size());
		
		ic.updateStep(seiEo1RwI);

		assertEquals("Should be a single Ca", 1, seiEo1RwI.getCategoryAssignments().size());
		CategoryAssignment copiedEoRwIIfeFromEc = seiEo1RwI.getCategoryAssignments().get(0);
		
		assertThat("Categories are correctly linked", copiedEoRwIIfeFromEc.getSuperTis(), hasItems(ecRwIIfe));

		IInheritanceLink referencingATypeInstance = ic.getInheritedIInheritanceLinkFor(ecRwIIfe, seiEo1RwI);
		assertEquals("Found copied CA of RW Eo that is referencing the EC IFE", copiedEoRwIIfeFromEc, referencingATypeInstance);
	}
	
	@Test
	public void testGetAllTypeInstances() {
		CategoryAssignment caEdRwIfe = attachInterfaceEnd(seiEdRw, "RwIe");
		
		Set<IInheritanceLink> allTis = InheritanceCopier.getAllTypeInstances(seiEdRw);
		
		APropertyInstance api = caEdRwIfe.getPropertyInstances().get(0);
		
		assertEquals("In the Set should only be two Elements", 2, allTis.size());
		assertThat("Those two Elements should be in the Set of ATypeInstances", allTis, hasItems(caEdRwIfe, api));
		
		attachInterfaceEnd(seiEdRw, "RwIe2");
		allTis = InheritanceCopier.getAllTypeInstances(seiEdRw);
		
		//CHECKSTYLE:OFF
		assertEquals("In the Set should only be two Elements", 4, allTis.size());
		assertThat("Those two Elements should be in the Set of ATypeInstances", allTis, hasItems(caEdRwIfe, api));
		//CHECKSTYLE:ON		
	}
	
	@Test
	public void testOrderByInheritance() {
		Set<StructuralElementInstance> severalSeis = new HashSet<StructuralElementInstance>();

		severalSeis.add(seiEcObc);
		severalSeis.add(seiEcRwI);
		severalSeis.add(seiEo2RwI);
		severalSeis.add(seiEo1RwI);
		severalSeis.add(seiEdObc);
		severalSeis.add(seiErRwA);
		severalSeis.add(seiEo2Obc);
		severalSeis.add(seiEdRw);
		
		InheritanceCopier ic = new InheritanceCopier();
		List<StructuralElementInstance> ordered = ic.orderByInheritance(severalSeis);
		assertTrue("Definition needs to be updated before Configuration", ordered.indexOf(seiEdObc) < ordered.indexOf(seiEcObc));
		assertTrue("Configuration needs to be updated before Occurence", ordered.indexOf(seiEcObc) < ordered.indexOf(seiEo2Obc));
		
		assertTrue("Definition needs to be updated before Configuration", ordered.indexOf(seiEdRw) < ordered.indexOf(seiEcRwI));
		assertTrue("Definition needs to be updated before Realization", ordered.indexOf(seiEdRw) < ordered.indexOf(seiErRwA));
		assertTrue("Configuration needs to be updated before Occurence", ordered.indexOf(seiEcRwI) < ordered.indexOf(seiEo1RwI));
		assertTrue("Realization needs to be updated before Occurence", ordered.indexOf(seiErRwA) < ordered.indexOf(seiEo1RwI));
		assertTrue("Configuration needs to be updated before Occurence", ordered.indexOf(seiEcRwI) < ordered.indexOf(seiEo2RwI));
				
		assertFalse("InheritanceLinks still exist", seiEcObc.getSuperSeis().isEmpty());
		assertFalse("InheritanceLinks still exist", seiEcRwI.getSuperSeis().isEmpty());
		assertFalse("InheritanceLinks still exist", seiEo2RwI.getSuperSeis().isEmpty());
		assertFalse("InheritanceLinks still exist", seiEo1RwI.getSuperSeis().isEmpty());
		assertFalse("InheritanceLinks still exist", seiErRwA.getSuperSeis().isEmpty());
		assertFalse("InheritanceLinks still exist", seiEo2Obc.getSuperSeis().isEmpty());
		

		// In this test we remove the ED Reactionwheel in the past we had a bug which is now removing
		// or destroying the inheritance links. this we want to check.
		Set<StructuralElementInstance> severalSeis2 = new HashSet<StructuralElementInstance>();

		severalSeis2.add(seiEo2RwI);
		severalSeis2.add(seiEo1RwI);
		severalSeis2.add(seiEcRwI);

		ordered = ic.orderByInheritance(severalSeis2);

		assertTrue("Configuration needs to be updated before Occurence", ordered.indexOf(seiEcRwI) < ordered.indexOf(seiEo1RwI));
		assertTrue("Configuration needs to be updated before Occurence", ordered.indexOf(seiEcRwI) < ordered.indexOf(seiEo2RwI));
		
		assertThat("InheritanceLinks still exist", seiEcRwI.getSuperSeis(), hasItems(seiEdRw));
		assertThat("InheritanceLinks still exist", seiEo2RwI.getSuperSeis(), hasItems(seiEcRwI));
		assertThat("InheritanceLinks still exist", seiEo1RwI.getSuperSeis(), hasItems(seiEcRwI, seiErRwA));
	}
	
	// ----- Now the Tests for the Inheritance over all the levels before -----
	@Test
	public void testGetSuperSeisInheritanceUnordered() {
		InheritanceCopier ic = new InheritanceCopier();
		final String TEST_VAL_1 = "1234";
		
		CategoryAssignment ca = attachInterfaceEnd(seiEdRw, "RwIe");
		setInterfaceEndSn(ca, TEST_VAL_1);
		
		Set<StructuralElementInstance> unorderedSeis = ic.getSuperSeisInheritanceUnordered(seiEcRwI);
		assertEquals("Contians correct elements", 2, unorderedSeis.size());
		assertThat("Contians correct elements", unorderedSeis, hasItems(seiEdRw, seiEcRwI));
		
		Set<StructuralElementInstance> unorderedSeis2 = ic.getSuperSeisInheritanceUnordered(seiEo1RwI);

		//CHECKSTYLE:OFF
		assertEquals("Contians correct elements", 4, unorderedSeis2.size());
		assertThat("Contians correct elements", unorderedSeis2, hasItems(seiEo1RwI, seiEcRwI, seiEdRw, seiErRwA));
		
		Set<StructuralElementInstance> unorderedSeis3 = ic.getSuperSeisInheritanceUnordered(seiEo1RwII);

		assertEquals("Contians correct elements", 3, unorderedSeis3.size());
		assertThat("Contians correct elements", unorderedSeis3, hasItems(seiEo1RwII, seiEcRwII, seiEdRw));
		//CHECKSTYLE:ON
	}
	
	@Test
	public void testGetSuperSeisInheritanceOrder() {
		InheritanceCopier ic = new InheritanceCopier();
		List<StructuralElementInstance> orderedSeis = ic.getSuperSeisInheritanceOrder(this.seiEcRwI);

		assertEquals("Found correct Number", 2, orderedSeis.size());
		assertEquals("Found correct Order for Updates", seiEdRw, orderedSeis.get(0));
		assertEquals("Found correct Order for Updates", seiEcRwI, orderedSeis.get(1));
		
		//CHECKSTYLE:OFF
		
		List<StructuralElementInstance> orderedSeis2 = ic.getSuperSeisInheritanceOrder(this.seiEo1RwII);

		assertEquals("Found correct Number", 3, orderedSeis2.size());
		assertEquals("Found correct Order for Updates", seiEdRw, orderedSeis2.get(0));
		assertEquals("Found correct Order for Updates", seiEcRwII, orderedSeis2.get(1));
		assertEquals("Found correct Order for Updates", seiEo1RwII, orderedSeis2.get(2));
		//CHECKSTYLE:ON
	}
	
	@Test
	public void testGetRootSuperTypeInstance() {
		CategoryAssignment ca = attachInterfaceEnd(seiEdRw, "RwIe");
		
		InheritanceCopier ic = new InheritanceCopier();
		
		ic.updateStep(seiEcRwI);
		ic.updateStep(seiErRwA);
		ic.updateStep(seiEo1RwI);
		
		CategoryAssignment copiedEdRwIfe = ca;
		CategoryAssignment copiedErRwAIfe = seiErRwA.getCategoryAssignments().get(0);
		CategoryAssignment copiedEoRwIIfe = seiEo1RwI.getCategoryAssignments().get(0);
		
		Set<IInheritanceLink> rootTis1 = InheritanceCopier.getRootSuperTypeInstance(copiedEdRwIfe);
		assertThat("CA at ED is root", rootTis1, hasItems(copiedEdRwIfe));
		assertEquals("Set is always size 1", 1, rootTis1.size());

		Set<IInheritanceLink> rootTis2 = InheritanceCopier.getRootSuperTypeInstance(copiedErRwAIfe);
		assertThat("CA at ED is root", rootTis2, hasItems(copiedEdRwIfe));
		assertEquals("Set is always size 1", 1, rootTis2.size());

		Set<IInheritanceLink> rootTis3 = InheritanceCopier.getRootSuperTypeInstance(copiedEoRwIIfe);
		assertThat("CA at ED is root", rootTis3, hasItems(copiedEdRwIfe));
		assertEquals("Set is always size 1", 1, rootTis3.size());
	}
	
	//CHECKSTYLE:OFF
	@Test 
	public void testUpdateInOrderManuallyStepped() {
		final String TEST_VAL_1 = "1234";
		final String TEST_VAL_2 = "2345";
		final String TEST_VAL_3 = "3456";
		
		CategoryAssignment caEdIfeRw = attachInterfaceEnd(seiEdRw, "RwIfe");
		setInterfaceEndSn(caEdIfeRw, TEST_VAL_1);
		setInterfaceEndSn(caEdIfeRw, TEST_VAL_1);
		
		InheritanceCopier ic = new InheritanceCopier();
		
		Set<CategoryAssignment> updatedCas = ic.updateStep(seiEdRw);
		assertTrue("Ed does not inherit at all", updatedCas.isEmpty());
		assertEquals("Correct Amount of Categories", 1, seiEdRw.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 0, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 0, seiErRwA.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 0, seiEo1RwI.getCategoryAssignments().size());

		updatedCas = ic.updateStep(seiEcRwI);
		assertEquals("Copied correct Amount of categories", 1, updatedCas.size());
		assertEquals("Correct Amount of Categories", 1, seiEdRw.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 1, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 0, seiErRwA.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 0, seiEo1RwI.getCategoryAssignments().size());

		updatedCas = ic.updateStep(seiErRwA);
		assertEquals("Copied correct Amount of categories", 1, updatedCas.size());
		assertEquals("Correct Amount of Categories", 1, seiEdRw.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 1, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 1, seiErRwA.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 0, seiEo1RwI.getCategoryAssignments().size());

		updatedCas = ic.updateStep(seiEo1RwI);
		assertEquals("Copied correct Amount of categories", 1, updatedCas.size());
		assertEquals("Correct Amount of Categories", 1, seiEdRw.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 1, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 1, seiErRwA.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 1, seiEo1RwI.getCategoryAssignments().size());

		CategoryAssignment copiedCaEd = seiEdRw.getCategoryAssignments().get(0);
		CategoryAssignment copiedCaEc = seiEcRwI.getCategoryAssignments().get(0);
		CategoryAssignment copiedCaEr = seiErRwA.getCategoryAssignments().get(0);
		CategoryAssignment copiedCaEo = seiEo1RwI.getCategoryAssignments().get(0);

		ValuePropertyInstance copiedSnEd = (ValuePropertyInstance) copiedCaEd.getPropertyInstances().get(0);
		ValuePropertyInstance copiedSnEc = (ValuePropertyInstance) copiedCaEc.getPropertyInstances().get(0);
		ValuePropertyInstance copiedSnEr = (ValuePropertyInstance) copiedCaEr.getPropertyInstances().get(0);
		ValuePropertyInstance copiedSnEo = (ValuePropertyInstance) copiedCaEo.getPropertyInstances().get(0);

		assertEquals("Value is correct", TEST_VAL_1, copiedSnEd.getValue());
		assertEquals("Value is correct", TEST_VAL_1, copiedSnEc.getValue());
		assertEquals("Value is correct", TEST_VAL_1, copiedSnEr.getValue());
		assertEquals("Value is correct", TEST_VAL_1, copiedSnEo.getValue());
		
		attachSpecification(seiErRwA, "Spec");
		
		// Everything correct until here :-D
		
		setInterfaceEndSn(copiedCaEd, TEST_VAL_2);
		setInterfaceEndSn(copiedCaEc, TEST_VAL_3);
		copiedSnEc.setOverride(true);
		
		updatedCas = ic.updateStep(seiEdRw);
		assertTrue("Ed does not inherit at all", updatedCas.isEmpty());
		assertEquals("Correct Amount of Categories", 1, seiEdRw.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 1, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 2, seiErRwA.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 1, seiEo1RwI.getCategoryAssignments().size());
		
		copiedCaEd = seiEdRw.getCategoryAssignments().get(0);
		copiedCaEc = seiEcRwI.getCategoryAssignments().get(0);
		copiedCaEr = seiErRwA.getCategoryAssignments().get(0);
		copiedCaEo = seiEo1RwI.getCategoryAssignments().get(0);

		copiedSnEd = (ValuePropertyInstance) copiedCaEd.getPropertyInstances().get(0);
		copiedSnEc = (ValuePropertyInstance) copiedCaEc.getPropertyInstances().get(0);
		copiedSnEr = (ValuePropertyInstance) copiedCaEr.getPropertyInstances().get(0);
		copiedSnEo = (ValuePropertyInstance) copiedCaEo.getPropertyInstances().get(0);

		assertEquals("Value is correct", TEST_VAL_2, copiedSnEd.getValue());
		assertEquals("Value is correct", TEST_VAL_3, copiedSnEc.getValue());
		assertEquals("Value is correct", TEST_VAL_1, copiedSnEr.getValue());
		assertEquals("Value is correct", TEST_VAL_1, copiedSnEo.getValue());

		updatedCas = ic.updateStep(seiEcRwI);
		assertEquals("Copied correct Amount of categories", 1, updatedCas.size());
		assertEquals("Correct Amount of Categories", 1, seiEdRw.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 1, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 2, seiErRwA.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 1, seiEo1RwI.getCategoryAssignments().size());
		
		copiedCaEd = seiEdRw.getCategoryAssignments().get(0);
		copiedCaEc = seiEcRwI.getCategoryAssignments().get(0);
		copiedCaEr = seiErRwA.getCategoryAssignments().get(0);
		copiedCaEo = seiEo1RwI.getCategoryAssignments().get(0);

		copiedSnEd = (ValuePropertyInstance) copiedCaEd.getPropertyInstances().get(0);
		copiedSnEc = (ValuePropertyInstance) copiedCaEc.getPropertyInstances().get(0);
		copiedSnEr = (ValuePropertyInstance) copiedCaEr.getPropertyInstances().get(0);
		copiedSnEo = (ValuePropertyInstance) copiedCaEo.getPropertyInstances().get(0);

		assertEquals("Value is correct", TEST_VAL_2, copiedSnEd.getValue());
		assertEquals("Value is correct", TEST_VAL_3, copiedSnEc.getValue());
		assertEquals("Value is correct", TEST_VAL_1, copiedSnEr.getValue());
		assertEquals("Value is correct", TEST_VAL_1, copiedSnEo.getValue());
		
		updatedCas = ic.updateStep(seiErRwA);
		assertEquals("Copied correct Amount of categories", 1, updatedCas.size());
		assertEquals("Correct Amount of Categories", 1, seiEdRw.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 1, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 2, seiErRwA.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 1, seiEo1RwI.getCategoryAssignments().size());
		
		copiedCaEd = seiEdRw.getCategoryAssignments().get(0);
		copiedCaEc = seiEcRwI.getCategoryAssignments().get(0);
		copiedCaEr = seiErRwA.getCategoryAssignments().get(0);
		copiedCaEo = seiEo1RwI.getCategoryAssignments().get(0);

		copiedSnEd = (ValuePropertyInstance) copiedCaEd.getPropertyInstances().get(0);
		copiedSnEc = (ValuePropertyInstance) copiedCaEc.getPropertyInstances().get(0);
		copiedSnEr = (ValuePropertyInstance) copiedCaEr.getPropertyInstances().get(0);
		copiedSnEo = (ValuePropertyInstance) copiedCaEo.getPropertyInstances().get(0);

		assertEquals("Value is correct", TEST_VAL_2, copiedSnEd.getValue());
		assertEquals("Value is correct", TEST_VAL_3, copiedSnEc.getValue());
		assertEquals("Value is correct", TEST_VAL_2, copiedSnEr.getValue());
		assertEquals("Value is correct", TEST_VAL_1, copiedSnEo.getValue());
		
		updatedCas = ic.updateStep(seiEo1RwI);
		assertEquals("Copied correct Amount of categories", 1, updatedCas.size());
		assertEquals("Correct Amount of Categories", 1, seiEdRw.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 1, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 2, seiErRwA.getCategoryAssignments().size());
		assertEquals("Correct Amount of Categories", 1, seiEo1RwI.getCategoryAssignments().size());
		
		copiedCaEd = seiEdRw.getCategoryAssignments().get(0);
		copiedCaEc = seiEcRwI.getCategoryAssignments().get(0);
		copiedCaEr = seiErRwA.getCategoryAssignments().get(0);
		copiedCaEo = seiEo1RwI.getCategoryAssignments().get(0);

		copiedSnEd = (ValuePropertyInstance) copiedCaEd.getPropertyInstances().get(0);
		copiedSnEc = (ValuePropertyInstance) copiedCaEc.getPropertyInstances().get(0);
		copiedSnEr = (ValuePropertyInstance) copiedCaEr.getPropertyInstances().get(0);
		copiedSnEo = (ValuePropertyInstance) copiedCaEo.getPropertyInstances().get(0);

		assertEquals("Value is correct", TEST_VAL_2, copiedSnEd.getValue());
		assertEquals("Value is correct", TEST_VAL_3, copiedSnEc.getValue());
		assertEquals("Value is correct", TEST_VAL_2, copiedSnEr.getValue());
		assertEquals("Value is correct", TEST_VAL_2, copiedSnEo.getValue());
	}
	//CHECKSTYLE:ON
	
	
	// ----- Now the Tests for the Inheritance over all the levels following -----
	
	@Test
	public void testGetSubSeisInheritanceOrderWithCommonEObject() {
		
		// Try the first case where all trees reside in one common EContainer
		StructuralElement seCommonRoot = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance seiCommonRoot = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiCommonRoot.setType(seCommonRoot);
		
		seEd.setIsApplicableForAll(true);
		seEc.setIsApplicableForAll(true);
		seEo.setIsApplicableForAll(true);
		seEr.setIsApplicableForAll(true);
		
		seiCommonRoot.getChildren().add(seiEcSc);
		seiCommonRoot.getChildren().add(seiEo1Sc);
		seiCommonRoot.getChildren().add(seiEdSc);
		seiCommonRoot.getChildren().add(seiErRwA);
		
		InheritanceCopier ic = new InheritanceCopier();
		List<StructuralElementInstance> subSeis = ic.getSubSeisInheritanceOrder(seiEcRwI, repo);
		
		assertEquals("Has correct number of Items", 2, subSeis.size());
		assertThat("Contains correct elements", subSeis, hasItems(seiEo1RwI, seiEo2RwI));
		
		List<StructuralElementInstance> subSeis2 = ic.getSubSeisInheritanceOrder(seiEdObc, repo);

		//CHECKSTYLE:OFF
		assertEquals("Has correct number of Items", 3, subSeis2.size());
		assertThat("Contains correct elements", subSeis2, hasItems(seiEo1Obc, seiEo2Obc, seiEcObc));
		assertTrue("Configuration needs to be updated before Occurence", subSeis2.indexOf(seiEcObc) < subSeis2.indexOf(seiEo1Obc));
		assertTrue("Configuration needs to be updated before Occurence", subSeis2.indexOf(seiEcObc) < subSeis2.indexOf(seiEo2Obc));

		List<StructuralElementInstance> subSeis3 = ic.getSubSeisInheritanceOrder(seiEdRw, repo);

		assertEquals("Has correct number of Items", 7, subSeis3.size());
		assertThat("Contains correct elements", subSeis3, hasItems(seiEo1RwI, seiEo1RwII, seiEo2RwI, seiEo2RwII, seiEcRwI, seiEcRwII, seiErRwA));
		assertTrue("Configuration needs to be updated before Occurence", subSeis3.indexOf(seiEcRwI) < subSeis3.indexOf(seiEo1RwI));
		assertTrue("Configuration needs to be updated before Occurence", subSeis3.indexOf(seiEcRwI) < subSeis3.indexOf(seiEo2RwI));
		assertTrue("Configuration needs to be updated before Occurence", subSeis3.indexOf(seiEcRwII) < subSeis3.indexOf(seiEo1RwI));
		assertTrue("Configuration needs to be updated before Occurence", subSeis3.indexOf(seiEcRwII) < subSeis3.indexOf(seiEo2RwII));
		assertTrue("Realization needs to be updated before Occurence", subSeis3.indexOf(seiErRwA) < subSeis3.indexOf(seiEo1RwI));
		//CHECKSTYLE:ON
	}
	
	@Test
	public void testGetSubSeisInheritanceOrderWithRepo() {
		
		InheritanceCopier ic = new InheritanceCopier();
		List<StructuralElementInstance> subSeis = ic.getSubSeisInheritanceOrder(seiEcRwI, repo);
		
		assertEquals("Has correct number of Items", 2, subSeis.size());
		assertThat("Contains correct elements", subSeis, hasItems(seiEo1RwI, seiEo2RwI));
		
		List<StructuralElementInstance> subSeis2 = ic.getSubSeisInheritanceOrder(seiEdObc, repo);

		//CHECKSTYLE:OFF
		assertEquals("Has correct number of Items", 3, subSeis2.size());
		assertThat("Contains correct elements", subSeis2, hasItems(seiEo1Obc, seiEo2Obc, seiEcObc));
		assertTrue("Configuration needs to be updated before Occurence", subSeis2.indexOf(seiEcObc) < subSeis2.indexOf(seiEo1Obc));
		assertTrue("Configuration needs to be updated before Occurence", subSeis2.indexOf(seiEcObc) < subSeis2.indexOf(seiEo2Obc));

		List<StructuralElementInstance> subSeis3 = ic.getSubSeisInheritanceOrder(seiEdRw, repo);

		assertEquals("Has correct number of Items", 7, subSeis3.size());
		assertThat("Contains correct elements", subSeis3, hasItems(seiEo1RwI, seiEo1RwII, seiEo2RwI, seiEo2RwII, seiEcRwI, seiEcRwII, seiErRwA));
		assertTrue("Configuration needs to be updated before Occurence", subSeis3.indexOf(seiEcRwI) < subSeis3.indexOf(seiEo1RwI));
		assertTrue("Configuration needs to be updated before Occurence", subSeis3.indexOf(seiEcRwI) < subSeis3.indexOf(seiEo2RwI));
		assertTrue("Configuration needs to be updated before Occurence", subSeis3.indexOf(seiEcRwII) < subSeis3.indexOf(seiEo1RwI));
		assertTrue("Configuration needs to be updated before Occurence", subSeis3.indexOf(seiEcRwII) < subSeis3.indexOf(seiEo2RwII));
		assertTrue("Realization needs to be updated before Occurence", subSeis3.indexOf(seiErRwA) < subSeis3.indexOf(seiEo1RwI));
		//CHECKSTYLE:ON
	}
	
	// ----- Combine all the stuff -----
	@Test 
	public void testNeedsUpdateInOrder() {
		final String TEST_VAL_1 = "1234";
		final String TEST_VAL_2 = "2345";
		final String TEST_VAL_3 = "3456";
		final String TEST_VAL_4 = "4567";
		
		CategoryAssignment ca = attachInterfaceEnd(seiEdRw, "RwIe");
		setInterfaceEndSn(ca, TEST_VAL_1);
		
		InheritanceCopier ic = new InheritanceCopier();
		
		assertFalse("Update is neccessary", ic.needsUpdateInOrder(seiEdRw));
		assertTrue("Update is neccessary", ic.needsUpdateInOrder(seiEcRwI));
		assertTrue("Update is neccessary", ic.needsUpdateInOrder(seiEcRwII));
		assertTrue("Update is neccessary", ic.needsUpdateInOrder(seiEo1RwI));
		assertTrue("Update is neccessary", ic.needsUpdateInOrder(seiEo1RwII));
		assertTrue("Update is neccessary", ic.needsUpdateInOrder(seiEo2RwI));
		assertTrue("Update is neccessary", ic.needsUpdateInOrder(seiEo2RwII));
		assertTrue("Update is neccessary", ic.needsUpdateInOrder(seiErRwA));
		
		ic.updateAllInOrder(repo, new NullProgressMonitor());
		assertFalse("Update is done", ic.needsUpdateInOrder(seiEdRw));
		assertFalse("Update is done", ic.needsUpdateInOrder(seiEcRwI));
		assertFalse("Update is done", ic.needsUpdateInOrder(seiEcRwII));
		assertFalse("Update is done", ic.needsUpdateInOrder(seiEo1RwI));
		assertFalse("Update is done", ic.needsUpdateInOrder(seiEo1RwII));
		assertFalse("Update is done", ic.needsUpdateInOrder(seiEo2RwI));
		assertFalse("Update is done", ic.needsUpdateInOrder(seiEo2RwII));
		assertFalse("Update is done", ic.needsUpdateInOrder(seiErRwA));
		
		attachSpecification(seiErRwA, "Spec");
		
		assertFalse("Specification of Ralization is not applicable for Occurence, hence no update needed", ic.needsUpdateInOrder(seiEo1RwI));
		
		CategoryAssignment copiedCaEd = seiEdRw.getCategoryAssignments().get(0);
		setInterfaceEndSn(copiedCaEd, TEST_VAL_2);
				
		CategoryAssignment copiedCaEc = seiEcRwI.getCategoryAssignments().get(0);
		setInterfaceEndSn(copiedCaEc, TEST_VAL_3);
		
		ValuePropertyInstance copiedSnEc = (ValuePropertyInstance) copiedCaEc.getPropertyInstances().get(0);
		copiedSnEc.setOverride(true);
		
		assertTrue("Now Update is again neccessary", ic.needsUpdateInOrder(seiEo1RwI));
		ic.updateAllInOrder(repo, new NullProgressMonitor());
		assertFalse("Update is done", ic.needsUpdateInOrder(seiEo1RwI));
		
		copiedCaEc = seiEcRwI.getCategoryAssignments().get(0);
		setInterfaceEndSn(copiedCaEc, TEST_VAL_4);
		
		assertFalse("Update is not needed since the Configuration is not the last inherited", ic.needsUpdateInOrder(seiEo1RwI));
	}
	
	
	@Test
	public void testUpdateWithAssignedDisciplines() {
		final String TEST_VAL_1 = "1234";
		final int USER_VALIDTITY_LIFETIME_255_DAYS = 255;
		
		CategoryAssignment caEdRwIfe = attachInterfaceEnd(seiEdRw, "RwIfe");
		setInterfaceEndSn(caEdRwIfe, TEST_VAL_1);
		
		InheritanceCopier ic = new InheritanceCopier();
		
		rm = RolesFactory.eINSTANCE.createRoleManagement();
		dA = RolesFactory.eINSTANCE.createDiscipline();
		dB = RolesFactory.eINSTANCE.createDiscipline();

		dA.setName("workA");
		dB.setName("workB");
		dA.setUser("Alice");
		dB.setUser("Bob");
		
		rm.getDisciplines().add(dA);
		rm.getDisciplines().add(dB);
		
		seiEdRw.setAssignedDiscipline(dA);
		seiErRwA.setAssignedDiscipline(dB);
		seiEcRwI.setAssignedDiscipline(dA);
		seiEcRwII.setAssignedDiscipline(dA);
		seiEo1RwI.setAssignedDiscipline(dA);
		seiEo1RwII.setAssignedDiscipline(dA);
		seiEo2RwI.setAssignedDiscipline(dB);
		seiEo2RwII.setAssignedDiscipline(dB);
		
		UserRegistry.getInstance().setSuperUser(false);
		UserRegistry.getInstance().setUser("Alice", USER_VALIDTITY_LIFETIME_255_DAYS);
	
		assertTrue("Update needs to be done ", ic.needsUpdateInOrder(seiEcRwI));
		assertTrue("Update needs to be done ", ic.needsUpdateInOrder(seiEcRwII));
		assertTrue("Update needs to be done ", ic.needsUpdateInOrder(seiErRwA));
		assertTrue("Update needs to be done ", ic.needsUpdateInOrder(seiEo1RwI));
		assertTrue("Update needs to be done ", ic.needsUpdateInOrder(seiEo1RwII));
		assertTrue("Update needs to be done ", ic.needsUpdateInOrder(seiEo2RwI));
		assertTrue("Update needs to be done ", ic.needsUpdateInOrder(seiEo2RwII));
		
		ic.updateAllInOrder(repo, new NullProgressMonitor());

		assertFalse("Update needs to be done ", ic.needsUpdateInOrder(seiEcRwI));
		assertFalse("Update needs to be done ", ic.needsUpdateInOrder(seiEcRwII));
		assertTrue("Update needs to be done ", ic.needsUpdateInOrder(seiErRwA));
		assertTrue("Update needs to be done ", ic.needsUpdateInOrder(seiEo1RwI));
		assertFalse("Update needs to be done ", ic.needsUpdateInOrder(seiEo1RwII));
		assertTrue("Update needs to be done ", ic.needsUpdateInOrder(seiEo2RwI));
		assertTrue("Update needs to be done ", ic.needsUpdateInOrder(seiEo2RwII));
		
		assertTrue("Update is done, but one of its superSeis needs Update", ic.needsUpdateInOrder(seiEo1RwI));
		assertEquals("O1 should have one CA", 1, seiEo1RwI.getCategoryAssignments().size());
		assertSame("CAs of same Type", caEdRwIfe.getType(), seiEo1RwI.getCategoryAssignments().get(0).getType());
		
		UserRegistry.getInstance().setUser("Bob", USER_VALIDTITY_LIFETIME_255_DAYS);
		ic.updateAllInOrder(repo, new NullProgressMonitor());
		
		assertFalse("Update needs to be done ", ic.needsUpdateInOrder(seiEcRwI));
		assertFalse("Update needs to be done ", ic.needsUpdateInOrder(seiEcRwII));
		assertFalse("Update needs to be done ", ic.needsUpdateInOrder(seiErRwA));
		assertTrue("Update needs to be done ", ic.needsUpdateInOrder(seiEo1RwI));
		assertFalse("Update needs to be done ", ic.needsUpdateInOrder(seiEo1RwII));
		assertFalse("Update needs to be done ", ic.needsUpdateInOrder(seiEo2RwI));
		assertFalse("Update needs to be done ", ic.needsUpdateInOrder(seiEo2RwII));
		// The seiEo1RwI still needs an update since its TIs don't yet link to the CA of the RWa
		// It was not possible to update them, since they actually belong to Alice. We would now
		// need an update from Alice to get the Data Consistent!
		
		assertEquals("O1 should have one CA", 1, seiEo1RwI.getCategoryAssignments().size());
		
		UserRegistry.getInstance().setSuperUser(true);
		assertFalse("Update is done", ic.needsUpdateInOrder(seiEdRw));
		assertFalse("Update was done before", ic.needsUpdateInOrder(seiEcRwI));
		assertFalse("Update is done", ic.needsUpdateInOrder(seiEcRwII));
		assertFalse("Update is done", ic.needsUpdateInOrder(seiErRwA));
		assertFalse("Update is done", ic.needsUpdateInOrder(seiEo1RwII));
		assertFalse("Update is done", ic.needsUpdateInOrder(seiEo2RwI));
		ic.updateAllInOrder(repo, new NullProgressMonitor());

		assertFalse("Update is done", ic.needsUpdateStep(seiEdRw));
		assertFalse("Update was done before", ic.needsUpdateStep(seiEcRwI));
		assertFalse("Update is done", ic.needsUpdateStep(seiEcRwII));
		assertFalse("Update is done", ic.needsUpdateStep(seiErRwA));
		assertFalse("Update is done", ic.needsUpdateStep(seiEo1RwII));
		assertFalse("Update is done", ic.needsUpdateStep(seiEo2RwI));
		
		assertFalse("Update was done before, now all superSeis are updated, but no additional information was transferred with this", ic.needsUpdateInOrder(seiEo1RwI));
	}
	
	/**
	 * Test if a type instance link is removed, if there is no
	 * is no inheritance link between the seis
	 */
	@Test
	public void testCleanSuperTisInvalidLink() {
		seiEo1RwI.getSuperSeis().clear();
		CategoryAssignment ife = attachInterfaceEnd(seiEo1RwI, "Ife");
		CategoryAssignment superIfe = attachInterfaceEnd(seiEcRwI, "IfeSuper");
		
		ife.getSuperTis().add(superIfe);
		
		InheritanceCopier ic = new InheritanceCopier();
		ic.cleanSuperTis(seiEo1RwI);
		
		assertTrue("Super TIs with invalid links have been correctly cleaned", ife.getSuperTis().isEmpty());
	}
	
	/**
	 * test that the type instance link is maintained in case
	 * there is also a link between the seis
	 */
	@Test
	public void testCleanSuperTisValidLink() {
		CategoryAssignment ife = attachInterfaceEnd(seiEo1RwI, "Ife");
		CategoryAssignment superIfe = attachInterfaceEnd(seiEcRwI, "Ife");
		
		ife.getSuperTis().add(superIfe);
		
		InheritanceCopier ic = new InheritanceCopier();
		ic.cleanSuperTis(seiEo1RwI);
		
		assertThat("Super TIs with invalid links have been correctly cleaned", ife.getSuperTis(), hasItems(superIfe));
	}
	
	/**
	 * Test that type instance links from two sources are correctly maintained. 
	 * First keep both links, then remove an invalid one
	 */
	@Test
	public void testCleanSuperTisValidAndInvalidLink() {
		CategoryAssignment ife = attachInterfaceEnd(seiEo1RwI, "Ife");
		CategoryAssignment superIfe1 = attachInterfaceEnd(seiEcRwI, "IfeSuper1");
		CategoryAssignment superIfe2 = attachInterfaceEnd(seiErRwA, "IfeSuper2");
		
		ife.getSuperTis().add(superIfe1);
		ife.getSuperTis().add(superIfe2);
		
		InheritanceCopier ic = new InheritanceCopier();
		
		// the links between the seis are correct, nothing should be deleted
		ic.cleanSuperTis(seiEo1RwI);
		
		assertThat("Super TIs with valid links have been correctly retained", ife.getSuperTis(), hasItem(superIfe1));
		assertThat("Super TIs with invalid links have been correctly cleaned", ife.getSuperTis(), hasItems(superIfe2));
		
		// invalidate one of the links, make sure the links for the type isnatnces get updated correctly
		seiEo1RwI.getSuperSeis().remove(seiEcRwI);
		ic.cleanSuperTis(seiEo1RwI);
		
		assertThat("Super TIs with valid links have been correctly retained", ife.getSuperTis(), not(hasItem(superIfe1)));
		assertThat("Super TIs with invalid links have been correctly cleaned", ife.getSuperTis(), hasItems(superIfe2));
	}
	
	/**
	 * test case to make sure that tis which are inherited, but do not longer
	 * have a link to a super ti get delete correctly. Therefore this test
	 * has an interface which is referencing an interface end which gets deleted.
	 * The reference from that interface has to disappear as well
	 */
	@Test
	public void testCleanCasIfNoSuperTi() {
		// Create an Ife and an IF which is referencing that IFE twice.
		CategoryAssignment ecIfe = attachInterfaceEnd(seiEo1RwI, "Ife");
		CategoryAssignment eoIfe = attachInterfaceEnd(seiEo1RwI, "Ife");
		eoIfe.getSuperTis().add(ecIfe);
		eoIfe.setIsInherited(true);
		CategoryAssignment caIf = attachInterface(seiEo1RwI, "If", eoIfe, eoIfe, caIftMil);
		ReferencePropertyInstance rpi = (ReferencePropertyInstance) caIf.getPropertyInstances().get(0);
		
		assertEquals("Reference to Interface end is set", rpi.getReference(), eoIfe);
		
		// running the clean should not change anything. The ife seems to be modeled in the EO
		InheritanceCopier ic = new InheritanceCopier();
		ic.cleanCas(seiEo1RwI);
		
		assertThat("That Ca Still exists", seiEo1RwI.getCategoryAssignments(), hasItem(eoIfe));
		assertEquals("Reference to Interface end still exists", eoIfe, rpi.getReference());
		
		// now remove the super ti. since the sub ti is set as inherited it should be removed
		eoIfe.getSuperTis().clear();
		ic.cleanCas(seiEo1RwI);
		
		assertThat("Inherited Cas with invalid links have been correctly removed", seiEo1RwI.getCategoryAssignments(), not(hasItem(eoIfe)));
		assertNull("Reference to Interface end has been correctly deleted", rpi.getReference());
	}
	
	@Test
	public void testSetIsInherited() {
		attachInterfaceEnd(seiEcRwI, "IfeEc");
		attachInterfaceEnd(seiEo1RwI, "IfeEo");
		InheritanceCopier ic = new InheritanceCopier();
		ic.updateStep(seiEo1RwI);
		
		assertFalse("Not Inherited IFE doesnt have inheritance flag set", seiEo1RwI.getCategoryAssignments().get(0).isIsInherited());
		assertTrue("Inherited IFE has inheritance flag set", seiEo1RwI.getCategoryAssignments().get(1).isIsInherited());
	}
	
	@Test
	public void testCleanRootTis() {
		
		InheritanceCopier ic = new InheritanceCopier();
		CategoryAssignment ife = attachInterfaceEnd(seiEo1RwI, "Ife");
		CategoryAssignment ifeSuper1 = attachInterfaceEnd(seiEcRwI, "IfeSuper1");
		
		ife.getSuperTis().add(ifeSuper1);
		
		ic.cleanRootTis(seiEo1RwI);
		assertThat("Super TI with unique root not touched", seiEo1RwI.getCategoryAssignments(), hasItems(ife));
		
		CategoryAssignment ifeSuper2 = attachInterfaceEnd(seiErRwA, "IfeSuper2");
		ife.getSuperTis().add(ifeSuper2);
		
		ic.cleanRootTis(seiEo1RwI);
		assertTrue("Super TIs with multiple root TIs cleaned", seiEo1RwI.getCategoryAssignments().isEmpty());
	}
	
	@Test
	public void testCleanRootTisValid() {
		CategoryAssignment ife = attachInterfaceEnd(seiEo1RwI, "Ife");
		CategoryAssignment superIfe1 = attachInterfaceEnd(seiEcRwI, "IfeSuper1");
		CategoryAssignment superIfe2 = attachInterfaceEnd(seiErRwA, "IfeSuper2");
		CategoryAssignment superSuperIfe = attachInterfaceEnd(seiEdRw, "IfeSuperSuper");
		
		ife.getSuperTis().add(superIfe1);
		ife.getSuperTis().add(superIfe2);
		superIfe1.getSuperTis().add(superSuperIfe);
		superIfe2.getSuperTis().add(superSuperIfe);
		
		assertThat("Element has correct CA", seiEo1RwI.getCategoryAssignments(), hasItems(ife));
		
		InheritanceCopier ic = new InheritanceCopier();
		ic.cleanRootTis(seiEo1RwI);

		assertThat("Element still has correct CA", seiEo1RwI.getCategoryAssignments(), hasItems(ife));
	}
	
	@Test
	public void testCopyComposedPropertyInstance() {
		
		// Create a category coontaining a composed property having as type a category
		// that is not applicable for anything
		
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		Category catComposed = CategoriesFactory.eINSTANCE.createCategory();
		ComposedProperty cp = PropertydefinitionsFactory.eINSTANCE.createComposedProperty();
		cp.setType(cat);
		catComposed.getProperties().add(cp);
		catComposed.setIsApplicableForAll(true);
		CategoryAssignment caCp = new CategoryInstantiator().generateInstance(catComposed, "CaCP");
		seiEcRwI.getCategoryAssignments().add(caCp);
		
		InheritanceCopier ic = new InheritanceCopier();
		ic.updateAllInOrder(repo, new NullProgressMonitor());
		
		CategoryAssignment caCopied = seiEo1RwI.getCategoryAssignments().get(0);
		ComposedPropertyInstance cpiCopied = (ComposedPropertyInstance) caCopied.getPropertyInstances().get(0);
		assertNotNull("Copied CPI has a type instance", cpiCopied.getTypeInstance());
		assertEquals("Copied CPI has right type", cpiCopied.getTypeInstance().getType(), cat);
	}
	
	@Test
	public void testCopyEquations() {
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setIsApplicableForAll(true);
		IntProperty ip1 = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		IntProperty ip2 = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		cat.getProperties().add(ip1);
		cat.getProperties().add(ip2);
		
		// Create the equation definition for
		// Ref: ip1 = ip2;
		
		EquationDefinition eqDef = CalculationFactory.eINSTANCE.createEquationDefinition();
		TypeDefinitionResult typeDefResult = CalculationFactory.eINSTANCE.createTypeDefinitionResult();
		typeDefResult.setReference(ip1);
		eqDef.setResult(typeDefResult);
		
		ReferencedDefinitionInput refDefInput = CalculationFactory.eINSTANCE.createReferencedDefinitionInput();
		refDefInput.setReference(ip2);
		eqDef.setExpression(refDefInput);
		
		cat.getEquationDefinitions().add(eqDef);
		
		CategoryAssignment caSuper = new CategoryInstantiator().generateInstance(cat, "ca");
		seiEcRwI.getCategoryAssignments().add(caSuper);
		
		InheritanceCopier ic = new InheritanceCopier();
		ic.updateAllInOrder(repo, new NullProgressMonitor());
		
		CategoryAssignment caCopied = seiEo1RwI.getCategoryAssignments().get(0);
		ValuePropertyInstance vpi1 = (ValuePropertyInstance) caCopied.getPropertyInstances().get(0);
		assertNotNull("Copied CA has an equation section", caCopied.getEquationSection());
		
		EquationSection eqSection = caCopied.getEquationSection();
		assertFalse("Equation section of copied CA has an equation", eqSection.getEquations().isEmpty());
	
		Equation eq = eqSection.getEquations().get(0);
		
		IEquationResult eqResult = eq.getResult();
		assertTrue("Referenced result of equation of copied CA has correct type", eqResult instanceof TypeInstanceResult);
		
		TypeInstanceResult tir = (TypeInstanceResult) eqResult;
		assertEquals("Equation of copied CA has correct result instance", vpi1, tir.getReference());
		
		AExpression expression = eq.getExpression();
		assertTrue("Input expression of equation of copied CA has correct type", expression instanceof ReferencedInput);
		
		ReferencedInput refInput = (ReferencedInput) expression;
		assertEquals("Equation of copied CA has correct result instance", refDefInput, refInput.getDefinition());
	}
	
	@Test
	public void testCopyIntPropertyInstance() {
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setIsApplicableForAll(true);
		IntProperty ip = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		cat.getProperties().add(ip);
		
		CategoryAssignment caSuper = new CategoryInstantiator().generateInstance(cat, "Ca");
		seiEcRwI.getCategoryAssignments().add(caSuper);
		ValuePropertyInstance vpiSuper = (ValuePropertyInstance) caSuper.getPropertyInstances().get(0);
		vpiSuper.setValue("1");
		
		// Propagate with initial value 1
		
		InheritanceCopier ic = new InheritanceCopier();
		ic.updateAllInOrder(repo, new NullProgressMonitor());
		
		CategoryAssignment caCopied = seiEo1RwI.getCategoryAssignments().get(0);
		ValuePropertyInstance vpiCopied = (ValuePropertyInstance) caCopied.getPropertyInstances().get(0);
		assertEquals("Copied VPI has right value", vpiSuper.getValue(), vpiCopied.getValue());
		
		// Propagate with new value 2
		
		vpiSuper.setValue("2");
		ic.updateAllInOrder(repo, new NullProgressMonitor());
		assertEquals("Copied VPI has right value", vpiSuper.getValue(), vpiCopied.getValue());
	}
	
	@Test
	public void testCopyWithCardinality() {
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cat.setIsApplicableForAll(true);
		IntProperty ip = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		cat.getProperties().add(ip);
		cat.setCardinality(1);
		
		CategoryAssignment caSuper = new CategoryInstantiator().generateInstance(cat, "Ca");
		seiEcRwI.getCategoryAssignments().add(caSuper);
		ValuePropertyInstance vpiSuper = (ValuePropertyInstance) caSuper.getPropertyInstances().get(0);
		vpiSuper.setValue("1");
		
		// Propagate with initial value 1
		
		InheritanceCopier ic = new InheritanceCopier();
		ic.updateAllInOrder(repo, new NullProgressMonitor());
		
		CategoryAssignment caCopied = seiEo1RwI.getCategoryAssignments().get(0);
		ValuePropertyInstance vpiCopied = (ValuePropertyInstance) caCopied.getPropertyInstances().get(0);
		assertEquals("Copied VPI has right value", vpiSuper.getValue(), vpiCopied.getValue());
		
		// Propagate with new value 2
		
		vpiSuper.setValue("2");
		ic = new InheritanceCopier();
		ic.updateAllInOrder(repo, new NullProgressMonitor());
		assertEquals("Copied VPI has right value", vpiSuper.getValue(), vpiCopied.getValue());
		
		attachInterfaceEnd(seiEcRwI, "Another Category Assignment");
		
		// Propagate with the new CA attached
		
		assertEquals("Another Category Assignment has not been been propagated yet", 1, seiEo1RwI.getCategoryAssignments().size());
		ic = new InheritanceCopier();
		ic.updateAllInOrder(repo, new NullProgressMonitor());
		assertEquals("Another Category Assignment has been propagated to the inheriting element", 2, seiEo1RwI.getCategoryAssignments().size());
	}
	
	@Test
	public void testOrderByInheritanceWithDifferentInheritanceOrders() {
		StructuralElementInstance seiEc1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiEc2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEc1.setName("Ec1");
		seiEc2.setName("Ec2");
		seiEc1.setType(seEc);
		seiEc2.setType(seEc);
		
		StructuralElementInstance seiEd1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiEd2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEd1.setName("Ed1");
		seiEd2.setName("Ed2");
		seiEd1.setType(seEd);
		seiEd2.setType(seEd);
		seiEc1.getSuperSeis().add(seiEd1);
		seiEc1.getSuperSeis().add(seiEd2);
		seiEc2.getSuperSeis().add(seiEd2);
		seiEc2.getSuperSeis().add(seiEd1);
		
		Set<StructuralElementInstance> unorderedSeis = new HashSet<>();
		unorderedSeis.add(seiEc1);
		unorderedSeis.add(seiEc2);
		unorderedSeis.add(seiEd1);
		unorderedSeis.add(seiEd2);
		
		InheritanceCopier ic = new InheritanceCopier();
		List<StructuralElementInstance> ordered = ic.orderByInheritance(unorderedSeis);
		
		assertTrue("Configuration needs to be updated before Occurence", ordered.indexOf(seiEd1) < ordered.indexOf(seiEc1));
		assertTrue("Configuration needs to be updated before Occurence", ordered.indexOf(seiEd2) < ordered.indexOf(seiEc2));
	}
}
