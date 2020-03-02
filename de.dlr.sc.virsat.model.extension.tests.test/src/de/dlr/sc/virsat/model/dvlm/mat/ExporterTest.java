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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.EReferenceTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryComposition;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryCompositionArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryExtends;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryIntrinsicArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReference;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReferenceArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import de.dlr.sc.virsat.model.extension.tests.test.ATestConceptTestCase;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.MatFile;
import us.hebi.matlab.mat.types.Struct;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ExporterTest extends ATestConceptTestCase {
	
	private static final int NUMBEROFELEMENTSWITHOUTCAS = 3;
	private static final int ENUMINFORMATION = 3;
	private static final double VALUEOFENUM = 25.0;
	private StructuralElementInstance sei;
	private TestStructuralElement tsei;
	private Exporter exporter;
	
	@Before
	public void setUp() throws CoreException {
		exporter = new Exporter();
		super.setUp();
		addResourceSetAndRepository();
		loadTestConcept();
		tsei = new TestStructuralElement(testConcept);
		sei = tsei.getStructuralElementInstance();
		sei.setName("testsei");
		repository.getRootEntities().add(sei);
	}
	
	@Test
	public void testShorterString() {
		String test = "123456789";
		assertEquals("Same String", test.substring(1, test.length() - 1), exporter.shorter(test));
	}
		
	@Test
	public void testexportCas() throws IOException {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		TestCategoryComposition tc1 = new TestCategoryComposition(testConcept);
		tsei.add(tc);
		tsei.add(tc1);
		EList<CategoryAssignment> cas = sei.getCategoryAssignments();
		MatFile testmat = exporter.exportCas(cas);
		assertEquals("Number of Elements", testmat.getStruct("inputs").getFieldNames().size(), cas.size());		
		Mat5.writeToFile(testmat, "TestfileCAS.mat"); //Delete
	}
	
	@Test
	public void testExportSeitypeUUID() throws IOException {
		MatFile testmat = exporter.exportSei(sei);
		assertEquals("Same UUID", sei.getUuid().toString(), exporter.shorter(testmat.getStruct(sei.getName()).get("uuid").toString()));
		assertEquals("Same Type", sei.getType().getName(), exporter.shorter(testmat.getStruct(sei.getName()).get("type").toString()));		
		assertEquals("Number of Elements", testmat.getStruct("testsei").getFieldNames().size(), NUMBEROFELEMENTSWITHOUTCAS);
	}
	
	@Test
	public void testExportSeiallCategories() throws IOException {		
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		TestCategoryComposition tc1 = new TestCategoryComposition(testConcept);
		tsei.add(tc);
		tsei.add(tc1);
		
		MatFile testmat = exporter.exportSei(sei);
		Struct matStruct = testmat.getStruct("testsei");
		List<String> matCategoryAssinments = matStruct.getFieldNames();		
		assertEquals("Number of CategoryAssinments", sei.getCategoryAssignments().size(), matStruct.getFieldNames().size() - NUMBEROFELEMENTSWITHOUTCAS);
		assertThat("Includes all CategoryAssinments", matCategoryAssinments, hasItems(TestCategoryAllProperty.class.getSimpleName(), TestCategoryComposition.class.getSimpleName()));
	}
	
	@Test
	public void testContentOfPropertyTestCategoryAllProperty() throws IOException {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		tc.setTestBool(true);
		tc.setTestFloat(2);
		tc.setTestEnum("HIGH");
		tsei.add(tc);	//TestCategoryAllProperty
		
		MatFile testmat = exporter.exportSei(sei);
		Struct struct = testmat.getStruct("testsei").getStruct(tc.getName());
		assertEquals("Number of Instances", struct.getFieldNames().size(), sei.getCategoryAssignments().get(0).getPropertyInstances().size());
		assertEquals("testString", struct.getStruct("testString").getFieldNames().size(), 1);
		assertEquals("testInt", struct.getStruct("testInt").getFieldNames().size(), 2);
		assertEquals("testFloat", struct.getStruct("testFloat").getFieldNames().size(), 2);
		assertEquals("testBool", struct.getStruct("testBool").getFieldNames().size(), 1);
		assertEquals("testResource", struct.getStruct("testResource").getFieldNames().size(), 1);
		assertEquals("testEnum", struct.getStruct("testEnum").getFieldNames().size(), ENUMINFORMATION);
		assertEquals("Unit of Float", exporter.shorter(struct.getStruct("testFloat").get("unit").toString()), "Kilogram");
		assertEquals("Value of Float", struct.getStruct("testFloat").get("value").toString(), "2.0");
		assertEquals("Value of Enum", struct.getStruct("testEnum").get("value").toString(), "25.0");
		assertEquals("Name of Enum", exporter.shorter(struct.getStruct("testEnum").get("name").toString()), "HIGH");
		assertEquals("Value of Bool", struct.getStruct("testBool").get("value").toString(), "true");
		
		Mat5.writeToFile(exporter.exportSei(sei), "Testfile1.mat");
	}
	
	@Test
	public void testContentOfPropertyTestCategoryComposition() throws IOException {
		TestCategoryComposition tc = new TestCategoryComposition(testConcept);
		tsei.add(tc);	//TestCategoryComposition
		
		MatFile testmat = exporter.exportSei(sei);
		Struct struct = testmat.getStruct("testsei").getStruct(tc.getName());
		assertEquals("Number of Instances", struct.getFieldNames().size(), sei.getCategoryAssignments().get(0).getPropertyInstances().size());
		struct = struct.getStruct("testSubCategory");
		assertEquals("testString", struct.getStruct("testString").getFieldNames().size(), 1);
		assertEquals("testInt", struct.getStruct("testInt").getFieldNames().size(), 2);
		assertEquals("testFloat", struct.getStruct("testFloat").getFieldNames().size(), 2);
		assertEquals("testBool", struct.getStruct("testBool").getFieldNames().size(), 1);
		assertEquals("testResource", struct.getStruct("testResource").getFieldNames().size(), 1);
		assertEquals("testEnum", struct.getStruct("testEnum").getFieldNames().size(), ENUMINFORMATION);
		assertEquals("Unit of Float", exporter.shorter(struct.getStruct("testFloat").get("unit").toString()), "Kilogram");
	}
	
	@Test
	public void testContentOfPropertyTestCategoryReference() throws IOException {
		TestCategoryReference tc = new TestCategoryReference(testConcept);
		tsei.add(tc);	//TestCategoryReference
	
		MatFile testmat = exporter.exportSei(sei);
	}
	
	@Test //Delete at the end
	public void testExportSeiCanMatlabReadIt() throws IOException {
		TestCategoryIntrinsicArray tc3 = new TestCategoryIntrinsicArray(testConcept);
		TestCategoryCompositionArray tc4 = new TestCategoryCompositionArray(testConcept);
		TestCategoryReferenceArray tc5 = new TestCategoryReferenceArray(testConcept);
		TestCategoryExtends tc10 = new TestCategoryExtends(testConcept);
		EReferenceTest tc14 = new  EReferenceTest(testConcept);

		tsei.add(tc3);	//TestCategoryIntrinsicArray
		tsei.add(tc4);	//TestCategoryCompositionArray
		tsei.add(tc5);	//TestCategoryReferenceArray
		tsei.add(tc10);	//TestCategoryExtends
		tsei.add(tc14);	//EReferenceTest

		Mat5.writeToFile(exporter.exportSei(sei), "Testfile.mat");
	}
}
