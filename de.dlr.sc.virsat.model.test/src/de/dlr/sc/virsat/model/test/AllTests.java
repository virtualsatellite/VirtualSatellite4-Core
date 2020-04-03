/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstanceTest;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferencePropertyHelperTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumPropertyHelperTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstanceTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstanceTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl.EReferencePropertyInstanceImplCustomTest;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitchTest;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelperTest;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiatorTest;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptTest;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelperTest;
import de.dlr.sc.virsat.model.dvlm.exception.DVLMMissingTypeExceptionTest;
import de.dlr.sc.virsat.model.dvlm.general.IInstanceTest;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritanceCopierIntegrationTest;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritanceCopierTest;
import de.dlr.sc.virsat.model.dvlm.list.DVLMFilteredContainingResolvingEListTest;
import de.dlr.sc.virsat.model.dvlm.list.DVLMFilteredResolvingEListTest;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelperTest;
import de.dlr.sc.virsat.model.dvlm.roles.RightsHelperTest;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistryTest;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstanceTest;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralElementInstanceHelperTest;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiatorTest;
import de.dlr.sc.virsat.model.dvlm.tree.TreeTraverserTest;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuidTest;
import de.dlr.sc.virsat.model.dvlm.util.DVLMApplicableForCheckTest;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCanInheritFromCheckTest;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCopierTest;
import de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceExceptionTest;
import de.dlr.sc.virsat.model.dvlm.util.rules.DVLMApplicableForCardinalityRuleTest;
import de.dlr.sc.virsat.model.dvlm.util.rules.DVLMApplicableForRuleCategoryAssignmentInStructuralElementInstanceTest;
import de.dlr.sc.virsat.model.dvlm.util.rules.DVLMApplicableForRuleRelationInstanceInStructuralElementInstanceTest;
import de.dlr.sc.virsat.model.dvlm.util.rules.DVLMApplicableForRuleStructuralElementInstanceInRepositoryTest;
import de.dlr.sc.virsat.model.dvlm.util.rules.DVLMApplicableForRuleStructuralElementInstanceInStructuralElementInstanceTest;
import de.dlr.sc.virsat.model.dvlm.util.rules.DVLMCanInheritFromRuleStructuralElementInstanceInStructuralElementInstanceTest;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtilTest;
import de.dlr.sc.virsat.model.ecore.xmi.impl.DvlmXMIResourceFactoryImplTest;
import de.dlr.sc.virsat.model.ecore.xmi.impl.DvlmXMIResourceImplTest;
import junit.framework.JUnit4TestAdapter;

/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({
				TreeTraverserTest.class,
				EnumPropertyHelperTest.class,
				VirSatUuidTest.class,
				CategoryInstantiatorTest.class,
				CategoryAssignmentHelperTest.class,
				ActiveConceptHelperTest.class,
				PropertyInstanceValueSwitchTest.class,
				DVLMMissingTypeExceptionTest.class,
				DVLMFilteredContainingResolvingEListTest.class,
				DVLMFilteredResolvingEListTest.class,
				DVLMApplicableForCheckTest.class,
				DVLMCanInheritFromCheckTest.class,
				StructuralInstantiatorTest.class,
				StructuralElementInstanceTest.class,
				DVLMApplicableForRuleStructuralElementInstanceInStructuralElementInstanceTest.class,
				DVLMApplicableForRuleRelationInstanceInStructuralElementInstanceTest.class,
				DVLMApplicableForRuleCategoryAssignmentInStructuralElementInstanceTest.class,
				DVLMApplicableForRuleStructuralElementInstanceInRepositoryTest.class,
				DVLMCanInheritFromRuleStructuralElementInstanceInStructuralElementInstanceTest.class,
				DVLMApplicableForCardinalityRuleTest.class,
				DVLMCopierTest.class,
				IInstanceTest.class,
				ResourcePropertyInstanceTest.class,
				UnitValuePropertyInstanceTest.class,
				InheritanceCopierTest.class,
				InheritanceCopierIntegrationTest.class,
				ATypeInstanceTest.class,
				UserRegistryTest.class,
				RightsHelperTest.class,
				DVLMUnresolvedReferenceExceptionTest.class,
				VirSatEcoreUtilTest.class,
				DvlmXMIResourceFactoryImplTest.class,
				DvlmXMIResourceImplTest.class,
				StructuralElementInstanceHelperTest.class,
				CategoryTest.class,
				ConceptTest.class,
				QudvUnitHelperTest.class,
				EReferencePropertyHelperTest.class,
				EReferencePropertyInstanceImplCustomTest.class
				})

/**
 * 
 * Test Collection
 *
 */
public class AllTests {

	/**
	 * Constructor
	 */
	private AllTests() {
	}
	
	/**
	 * Test Adapter
	 * @return Executable JUnit Tests
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}	
}