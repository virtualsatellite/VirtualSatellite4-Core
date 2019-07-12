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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;

/**
 * Test Cases for the inheritance functionality
 * 
 * @author fisc_ph
 *
 */
public class InheritanceCopierIntegrationTest extends InheritanceCopierTest {
	
	/**
	 * This test case checks the whole update logic starting from a PT
	 * over a CT to an AT. Additionally there is an equipment in the PS
	 * which should finally overwrite the results in the AT
	 */
	@Test 
	public void testUpdateAllInOrderRepo() {
		
		final String TEST_VAL_1 = "1234";
		final String TEST_VAL_2 = "2345";
		final String TEST_VAL_3 = "3456";
		
		// Step 1 - Attach an Interface End to the RW in the PT
		// Call the update on the whole repository and make sure
		// the Interface End shows up in all other trees.
		// Additionally write a fictive serial n.umber to the Interface End
		// Check that the values inherited are ok as well.
		CategoryAssignment ca = attachInterfaceEnd(seiEdRw, "RwIe");
		setInterfaceEndSn(ca, TEST_VAL_1);
		
		InheritanceCopier ic = new InheritanceCopier();
		
		ic.updateAllInOrder(repo, new NullProgressMonitor());
	
		assertEquals("Should be a single Ca", 1, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEcRwII.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiErRwA.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEo1RwI.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEo1RwII.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEo2RwI.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEo2RwII.getCategoryAssignments().size());

		CategoryAssignment caIeEcRwI = seiEcRwI.getCategoryAssignments().get(0);
		CategoryAssignment caIeEcRwII = seiEcRwII.getCategoryAssignments().get(0);
		CategoryAssignment caIeErRwA = seiErRwA.getCategoryAssignments().get(0);
		CategoryAssignment caIeEo1RwI = seiEo1RwI.getCategoryAssignments().get(0);
		CategoryAssignment caIeEo1RwII = seiEo1RwII.getCategoryAssignments().get(0);
		CategoryAssignment caIeEo2RwI = seiEo1RwI.getCategoryAssignments().get(0);
		CategoryAssignment caIeEo2RwII = seiEo1RwII.getCategoryAssignments().get(0);

		assertEquals("Value should be the same as for the RW in the PT", TEST_VAL_1, ((ValuePropertyInstance) caIeEcRwI.getPropertyInstances().get(0)).getValue());
		assertEquals("Value should be the same as for the RW in the PT", TEST_VAL_1, ((ValuePropertyInstance) caIeEcRwII.getPropertyInstances().get(0)).getValue());
		assertEquals("Value should be the same as for the RW in the PT", TEST_VAL_1, ((ValuePropertyInstance) caIeErRwA.getPropertyInstances().get(0)).getValue());
		assertEquals("Value should be the same as for the RW in the PT", TEST_VAL_1, ((ValuePropertyInstance) caIeEo1RwI.getPropertyInstances().get(0)).getValue());
		assertEquals("Value should be the same as for the RW in the PT", TEST_VAL_1, ((ValuePropertyInstance) caIeEo1RwII.getPropertyInstances().get(0)).getValue());
		assertEquals("Value should be the same as for the RW in the PT", TEST_VAL_1, ((ValuePropertyInstance) caIeEo2RwI.getPropertyInstances().get(0)).getValue());
		assertEquals("Value should be the same as for the RW in the PT", TEST_VAL_1, ((ValuePropertyInstance) caIeEo2RwII.getPropertyInstances().get(0)).getValue());

		// Step2 - Now add a specification to the RW in the Product Storage.
		// This specification won't be inherited by the AT it is only applicable for
		// element realizations.
		//
		// The RW in the PT gets a new Value to the InterfaceEnd. This value should propagate
		// To all other instances in the other trees. The value of the RW I in the CT is changed 
		// as well. This should break the inheritance from this point. Thus the RW I of the 
		// first AT should have that value as well.
		//
		// Additionally check that the existing CAs have not been exchanged instance wise.
		
		attachSpecification(seiErRwA, "Spec");
		
		CategoryAssignment copiedCaEd = seiEdRw.getCategoryAssignments().get(0);
		setInterfaceEndSn(copiedCaEd, TEST_VAL_2);
		
		CategoryAssignment copiedCaEc = seiEcRwI.getCategoryAssignments().get(0);
		setInterfaceEndSn(copiedCaEc, TEST_VAL_3);
		
		ValuePropertyInstance copiedSnEc = (ValuePropertyInstance) copiedCaEc.getPropertyInstances().get(0);
		copiedSnEc.setOverride(true);
		
		ic.updateAllInOrder(repo, new NullProgressMonitor());
		
		assertEquals("Should be a single Ca", 1, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEcRwII.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 2, seiErRwA.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEo1RwI.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEo1RwII.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEo2RwI.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEo2RwII.getCategoryAssignments().size());

		assertSame("Instance has not been changed", caIeEcRwI, seiEcRwI.getCategoryAssignments().get(0));
		assertSame("Instance has not been changed", caIeEcRwII, seiEcRwII.getCategoryAssignments().get(0));
		assertSame("Instance has not been changed", caIeErRwA, seiErRwA.getCategoryAssignments().get(0));
		assertSame("Instance has not been changed", caIeEo1RwI, seiEo1RwI.getCategoryAssignments().get(0));
		assertSame("Instance has not been changed", caIeEo1RwII, seiEo1RwII.getCategoryAssignments().get(0));
		assertSame("Instance has not been changed", caIeEo2RwI, seiEo2RwI.getCategoryAssignments().get(0));
		assertSame("Instance has not been changed", caIeEo2RwII, seiEo2RwII.getCategoryAssignments().get(0));
		
		assertEquals("Value has changed in the CT",                      TEST_VAL_3, ((ValuePropertyInstance) caIeEcRwI.getPropertyInstances().get(0)).getValue());
		assertEquals("Value should be the same as for the RW in the PT", TEST_VAL_2, ((ValuePropertyInstance) caIeEcRwII.getPropertyInstances().get(0)).getValue());
		assertEquals("Value should be the same as for the RW in the PT", TEST_VAL_2, ((ValuePropertyInstance) caIeErRwA.getPropertyInstances().get(0)).getValue());
		assertEquals("Value should be the same as for the RW in the CT", TEST_VAL_3, ((ValuePropertyInstance) caIeEo1RwI.getPropertyInstances().get(0)).getValue());
		assertEquals("Value should be the same as for the RW in the PT", TEST_VAL_2, ((ValuePropertyInstance) caIeEo1RwII.getPropertyInstances().get(0)).getValue());
		assertEquals("Value should be the same as for the RW in the CT", TEST_VAL_3, ((ValuePropertyInstance) caIeEo2RwI.getPropertyInstances().get(0)).getValue());
		assertEquals("Value should be the same as for the RW in the PT", TEST_VAL_2, ((ValuePropertyInstance) caIeEo2RwII.getPropertyInstances().get(0)).getValue());
		
		CategoryAssignment copiedCaEr = seiErRwA.getCategoryAssignments().get(0);
		ValuePropertyInstance copiedSnEr = (ValuePropertyInstance) copiedCaEr.getPropertyInstances().get(0);
		
		assertEquals("The serial number should be updated in the realization", TEST_VAL_2, copiedSnEr.getValue());
		
		CategoryAssignment copiedCaEc2 = seiEcRwI.getCategoryAssignments().get(0);
		ValuePropertyInstance copiedSnEc2 = (ValuePropertyInstance) copiedCaEc2.getPropertyInstances().get(0);
		
		assertEquals("The serial number should be not be updated in the configuration", TEST_VAL_3, copiedSnEc2.getValue());
		
		CategoryAssignment copiedCaEo = seiEo1RwI.getCategoryAssignments().get(0);
		ValuePropertyInstance copiedSnEo = (ValuePropertyInstance) copiedCaEo.getPropertyInstances().get(0);
		
		assertEquals("The Occurence should inherit the value from the Realization", TEST_VAL_2, copiedSnEo.getValue());
	}
	
	@Test 
	public void testUpdateInOrderFromOccurence() {
		final String TEST_VAL_1 = "1234";
		final String TEST_VAL_2 = "2345";
		final String TEST_VAL_3 = "3456";
		
		CategoryAssignment ca = attachInterfaceEnd(seiEdRw, "RwIe");
		setInterfaceEndSn(ca, TEST_VAL_1);
		
		InheritanceCopier ic = new InheritanceCopier();
		
		ic.updateInOrderFrom(seiEo1RwI, repo, new NullProgressMonitor());
		
		assertEquals("Should be a single Ca", 1, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiErRwA.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEo1RwI.getCategoryAssignments().size());

		assertEquals("Should be no Ca", 0, seiEcRwII.getCategoryAssignments().size());
		assertEquals("Should be no Ca", 0, seiEo1RwII.getCategoryAssignments().size());
		assertEquals("Should be no Ca", 0, seiEo2RwI.getCategoryAssignments().size());
		assertEquals("Should be no Ca", 0, seiEo2RwII.getCategoryAssignments().size());
		
		attachSpecification(seiErRwA, "Spec");
		
		CategoryAssignment copiedCaEd = seiEdRw.getCategoryAssignments().get(0);
		setInterfaceEndSn(copiedCaEd, TEST_VAL_2);
		
		CategoryAssignment copiedCaEc = seiEcRwI.getCategoryAssignments().get(0);
		setInterfaceEndSn(copiedCaEc, TEST_VAL_3);
		
		ValuePropertyInstance copiedSnEc = (ValuePropertyInstance) copiedCaEc.getPropertyInstances().get(0);
		copiedSnEc.setOverride(true);
		
		ic.updateInOrderFrom(seiEo1RwI, repo, new NullProgressMonitor());
		
		assertEquals("Should be a single Ca", 1, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Should have two Cas", 2, seiErRwA.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEo1RwI.getCategoryAssignments().size());
		
		assertEquals("Should be no Ca", 0, seiEcRwII.getCategoryAssignments().size());
		assertEquals("Should be no Ca", 0, seiEo1RwII.getCategoryAssignments().size());
		assertEquals("Should be no Ca", 0, seiEo2RwI.getCategoryAssignments().size());
		assertEquals("Should be no Ca", 0, seiEo2RwII.getCategoryAssignments().size());
		
		CategoryAssignment copiedCaEr = seiErRwA.getCategoryAssignments().get(0);
		ValuePropertyInstance copiedSnEr = (ValuePropertyInstance) copiedCaEr.getPropertyInstances().get(0);
		
		assertEquals("The serial number should be updated in the realization", TEST_VAL_2, copiedSnEr.getValue());
		
		CategoryAssignment copiedCaEc2 = seiEcRwI.getCategoryAssignments().get(0);
		ValuePropertyInstance copiedSnEc2 = (ValuePropertyInstance) copiedCaEc2.getPropertyInstances().get(0);
		
		assertEquals("The serial number should be not be updated in the configuration", TEST_VAL_3, copiedSnEc2.getValue());
		
		CategoryAssignment copiedCaEo = seiEo1RwI.getCategoryAssignments().get(0);
		ValuePropertyInstance copiedSnEo = (ValuePropertyInstance) copiedCaEo.getPropertyInstances().get(0);
		
		assertEquals("The Occurence should inherit the value from the Realization", TEST_VAL_2, copiedSnEo.getValue());
	}
	
	@Test 
	public void testUpdateInOrderFromConfiguration() {
		final String TEST_VAL_1 = "1234";
		final String TEST_VAL_2 = "2345";
		final String TEST_VAL_3 = "3456";
		
		CategoryAssignment ca = attachInterfaceEnd(seiEdRw, "RwIe");
		setInterfaceEndSn(ca, TEST_VAL_1);
		
		InheritanceCopier ic = new InheritanceCopier();
		
		ic.updateInOrderFrom(seiEcRwI, repo, new NullProgressMonitor());
		
		assertEquals("Should be a single Ca", 1, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEo1RwI.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEo2RwI.getCategoryAssignments().size());

		assertEquals("Should be no Ca", 0, seiErRwA.getCategoryAssignments().size());
		assertEquals("Should be no Ca", 0, seiEcRwII.getCategoryAssignments().size());
		assertEquals("Should be no Ca", 0, seiEo1RwII.getCategoryAssignments().size());
		assertEquals("Should be no Ca", 0, seiEo2RwII.getCategoryAssignments().size());
		
		CategoryAssignment specCa = attachSpecification(seiErRwA, "Spec");
		
		CategoryAssignment copiedCaEd = seiEdRw.getCategoryAssignments().get(0);
		setInterfaceEndSn(copiedCaEd, TEST_VAL_2);
		
		CategoryAssignment copiedCaEc = seiEcRwI.getCategoryAssignments().get(0);
		setInterfaceEndSn(copiedCaEc, TEST_VAL_3);
		
		ValuePropertyInstance copiedSnEc = (ValuePropertyInstance) copiedCaEc.getPropertyInstances().get(0);
		copiedSnEc.setOverride(true);
		
		ic.updateInOrderFrom(seiEcRwI, repo, new NullProgressMonitor());
		
		assertEquals("Should be a single Ca", 1, seiEcRwI.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEo1RwI.getCategoryAssignments().size());
		assertEquals("Should be a single Ca", 1, seiEo2RwI.getCategoryAssignments().size());

		assertEquals("Should be the Specification Ca", 1, seiErRwA.getCategoryAssignments().size());
		assertEquals("Should be no Ca", 0, seiEcRwII.getCategoryAssignments().size());
		assertEquals("Should be no Ca", 0, seiEo1RwII.getCategoryAssignments().size());
		assertEquals("Should be no Ca", 0, seiEo2RwII.getCategoryAssignments().size());
		
		CategoryAssignment copiedCaEr = seiErRwA.getCategoryAssignments().get(0);
		assertEquals("The Realization should ony have the Specification Ca", specCa, copiedCaEr);
		
		CategoryAssignment copiedCaEc2 = seiEcRwI.getCategoryAssignments().get(0);
		ValuePropertyInstance copiedSnEc2 = (ValuePropertyInstance) copiedCaEc2.getPropertyInstances().get(0);
		
		assertEquals("The serial number should be not be updated in the configuration", TEST_VAL_3, copiedSnEc2.getValue());
		
		CategoryAssignment copiedCaEo = seiEo1RwI.getCategoryAssignments().get(0);
		ValuePropertyInstance copiedSnEo = (ValuePropertyInstance) copiedCaEo.getPropertyInstances().get(0);
		
		assertEquals("The Occurence should inherit the value from the Configuration since the Realization is not touched", TEST_VAL_3, copiedSnEo.getValue());
	}
}
