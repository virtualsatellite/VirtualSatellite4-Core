/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.apps.api.external.ModelAPITest;
import de.dlr.sc.virsat.build.validator.core.DvlmLatestConceptValidatorTest;
import de.dlr.sc.virsat.build.validator.core.RepoValidatorsInstantiatorTest;
import de.dlr.sc.virsat.model.concept.list.ArrayInstanceListIteratorTest;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactoryTest;
import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactoryTest;
import de.dlr.sc.virsat.model.concept.types.structural.level.HierarchyLevelCheckerTest;
import de.dlr.sc.virsat.model.concept.types.structural.tree.BeanStructuralTreeTraverserTest;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelperTest;
import de.dlr.sc.virsat.model.concept.types.util.BeanStructuralElementInstanceHelperTest;
import de.dlr.sc.virsat.model.dvlm.mat.MatExporterTest;
import de.dlr.sc.virsat.model.dvlm.mat.MatImporterTest;
import de.dlr.sc.virsat.model.dvlm.structural.command.DeleteStructuralElementInstanceCommandAcceptanceTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllPropertyTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryCompositionArrayDynamicTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryCompositionArrayStaticTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryCompositionTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryIntrinsicArrayDynamicTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryIntrinsicArrayStaticTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReferenceCategoryArrayDynamicTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReferenceCategoryArrayStaticTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReferencePropertyArrayDynamicTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReferencePropertyArrayStaticTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReferenceTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElementInstanceTest;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomainNonDVLMTest;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatClipboardCommandAcceptanceTest;
import junit.framework.JUnit4TestAdapter;

/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({	DvlmLatestConceptValidatorTest.class, 
				VirSatClipboardCommandAcceptanceTest.class,
				VirSatTransactionalEditingDomainNonDVLMTest.class,
				DeleteStructuralElementInstanceCommandAcceptanceTest.class,	
				TestCategoryAllPropertyTest.class,
				TestCategoryCompositionTest.class,
				ArrayInstanceListIteratorTest.class,
				TestCategoryReferenceTest.class,
				TestCategoryIntrinsicArrayDynamicTest.class,
				TestCategoryIntrinsicArrayStaticTest.class,
				TestCategoryCompositionArrayDynamicTest.class,
				TestCategoryCompositionArrayStaticTest.class,
				TestCategoryReferenceCategoryArrayDynamicTest.class,
				TestCategoryReferencePropertyArrayDynamicTest.class,
				TestCategoryReferenceCategoryArrayStaticTest.class,
				TestCategoryReferencePropertyArrayStaticTest.class,
				BeanCategoryAssignmentHelperTest.class,
				TestStructuralElementInstanceTest.class,
				BeanStructuralElementInstanceHelperTest.class,
				BeanCategoryAssignmentFactoryTest.class,
				BeanStructuralElementInstanceFactoryTest.class,
				BeanStructuralTreeTraverserTest.class,
				HierarchyLevelCheckerTest.class,
				ModelAPITest.class,
				RepoValidatorsInstantiatorTest.class,
				MatImporterTest.class,
				MatExporterTest.class
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