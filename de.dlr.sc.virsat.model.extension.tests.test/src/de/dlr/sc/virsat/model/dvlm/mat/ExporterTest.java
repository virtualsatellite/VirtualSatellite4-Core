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

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
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
	public void testShorterString() {
		String test = "123456789";
		assertEquals("Same String", test.substring(1, test.length() - 1), Exporter.shorter(test));
	}
	
	@Test
	public void testExportSeiCanMatlabReadIt() throws IOException {
		Mat5.writeToFile(Exporter.exportSei(sei), "Testfile.mat");
	}
	@Test
	public void testExportSeitypeUUID() throws IOException {
		MatFile testmat = Exporter.exportSei(sei);
		assertEquals("Same UUID", sei.getUuid().toString(), Exporter.shorter(testmat.getStruct(sei.getName()).get("UUID").toString()));
		assertEquals("Same Type", sei.getType().getName(), Exporter.shorter(testmat.getStruct(sei.getName()).get("Type").toString()));
		
	}
	@Test
	public void testExportSeiallCategories() throws IOException {
		MatFile testmat = Exporter.exportSei(sei);
		Struct matStruct = testmat.getStruct("testsei");
		List<String> matCategoryAssinments = matStruct.getFieldNames();		
		assertEquals("Number of CategoryAssinments", sei.getCategoryAssignments().size(), matStruct.getFieldNames().size() - NUMBEROFELEMENTS);
		EList<CategoryAssignment> seiCategories = sei.getCategoryAssignments();
		for (int i = 0; i < seiCategories.size(); i++) {
			assertTrue("Included CategoryAssisnment" + seiCategories.get(i).getName(), matCategoryAssinments.contains(seiCategories.get(i).getName()));
		}		
		assertThat("Includes all CategoryAssinments", matCategoryAssinments, hasItems(TestCategoryAllProperty.class.getSimpleName(), TestCategoryComposition.class.getSimpleName()));
		
	}
	

}
