/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBase;
import de.dlr.sc.virsat.model.extension.tests.test.ATestConceptTestCase;

/**
 *
 */
public class CategoryAssignmentHelperModelTest extends ATestConceptTestCase {
	
	private TestCategoryBase baseElement;
	private TestCategoryBase nestedElement1;
	private TestCategoryBase nestedElement2;
	private TestCategoryBase deepNestedElement;
	
	@Before
	public void setUpModel() throws CoreException, IOException {
		super.setUp();
		addResourceSetAndRepository();
		loadTestConcept();
		
		baseElement = new TestCategoryBase(testConcept);
		
		nestedElement1 = new TestCategoryBase(testConcept);
		nestedElement2 = new TestCategoryBase(testConcept);
		baseElement.getTestArray().add(nestedElement1);
		baseElement.getTestArray().add(nestedElement2);
		
		deepNestedElement = new TestCategoryBase(testConcept);
		nestedElement1.getTestArray().add(deepNestedElement);
	}
	
	@Test
	public void testGetNestedCategoryAssignments() {
		List<CategoryAssignment> result = CategoryAssignmentHelper.getNestedCategoryAssignments(
				baseElement.getTypeInstance(), 
				TestCategoryBase.FULL_QUALIFIED_CATEGORY_NAME);
		
		assertThat(result, hasItem(nestedElement1.getTypeInstance()));
		assertThat(result, hasItem(nestedElement2.getTypeInstance()));
		
		assertThat(result, not(hasItem(deepNestedElement.getTypeInstance())));
	}
	
	@Test
	public void testGetNestedCategoryAssignmentsDeepSearch() {
		List<CategoryAssignment> result = CategoryAssignmentHelper.getNestedCategoryAssignments(
				baseElement.getTypeInstance(), 
				TestCategoryBase.FULL_QUALIFIED_CATEGORY_NAME,
				true);
		
		assertThat(result, hasItem(nestedElement1.getTypeInstance()));
		assertThat(result, hasItem(nestedElement2.getTypeInstance()));
		assertThat(result, hasItem(deepNestedElement.getTypeInstance()));
	}
}
