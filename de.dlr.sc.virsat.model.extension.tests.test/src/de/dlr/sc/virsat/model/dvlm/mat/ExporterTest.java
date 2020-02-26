/*******************************************************************************
 * Copyright (c) 2008-2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.mat;

import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryComposition;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.MatFile;
import us.hebi.matlab.mat.types.Struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ExporterTest extends AConceptTestCase {
	
	private static final int NUMBEROFELEMENTS = 3;
	private Concept concept;
	private StructuralElementInstance sei;
	
	@Before
	public void setUp() {
		//get Test - StructuralElementInstance
		prepareEditingDomain();
		concept = loadConceptFromPlugin();
		TestCategoryAllProperty tc = new TestCategoryAllProperty(concept);
		TestCategoryComposition tc1 = new TestCategoryComposition(concept);
		TestStructuralElement tsei = new TestStructuralElement(concept);
		tsei.add(tc);
		tsei.add(tc1);
		sei = tsei.getStructuralElementInstance();
		sei.setName("testsei");
	}
	
	@Test
	public void canMatlabReadItTest() throws IOException {
		Mat5.writeToFile(Exporter.exportSei(sei), "Testfile.mat");
	}
	@Test
	public void typeUUIDTest() throws IOException {
		MatFile testmat = Exporter.exportSei(sei);
		assertEquals("Same UUID", sei.getUuid().toString(), shorter(testmat.getStruct(sei.getName()).get("UUID").toString()));
		assertEquals("Same Type", sei.getType().getName(), shorter(testmat.getStruct(sei.getName()).get("Type").toString()));
		
	}
	@Test
	public void allCategoriesTest() throws IOException {
		MatFile testmat = Exporter.exportSei(sei);
		Struct matStruct = testmat.getStruct("testsei");
		List<String> matCategories = matStruct.getFieldNames();
		EList<CategoryAssignment> seiCategories = sei.getCategoryAssignments();
		assertEquals("Number of Categories", sei.getCategoryAssignments().size(), matStruct.getFieldNames().size() - NUMBEROFELEMENTS);
		for (int i = 0; i < seiCategories.size(); i++) {
			assertTrue("Includes all Categories", matCategories.contains(seiCategories.get(i).getName()));
		}
		
	}
	
	//Delete First and Last Character
	public String shorter(String str) {
		return str.substring(1, str.length() - 1);
	}
}
