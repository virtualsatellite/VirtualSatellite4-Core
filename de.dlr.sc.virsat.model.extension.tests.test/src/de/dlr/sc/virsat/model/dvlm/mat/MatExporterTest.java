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

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

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
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElementOther;
import de.dlr.sc.virsat.model.extension.tests.test.ATestConceptTestCase;
import us.hebi.matlab.mat.types.MatFile;
import us.hebi.matlab.mat.types.Struct;

public class MatExporterTest extends ATestConceptTestCase {

	private static final int NUMBEROFELEMENTSWITHOUTCAS = 2;
	private static final int ENUMINFORMATION = 3;
	private static final int ARRAYLENGTH = 4;
	private StructuralElementInstance sei;
	private TestStructuralElement tsei;
	private MatExporter exporter;

	@Before
	public void setUp() throws CoreException {
		exporter = new MatExporter();
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
	public void testExportCas() {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		TestCategoryComposition tc1 = new TestCategoryComposition(testConcept);
		tsei.add(tc);
		tsei.add(tc1);
		EList<CategoryAssignment> cas = sei.getCategoryAssignments();
		MatFile testmat = exporter.exportCas(cas);
		assertEquals("Number of Elements", cas.size(), testmat.getStruct("inputs").getFieldNames().size());
	}

	@Test
	public void testExportSeitypeUUID() {
		MatFile testmat = exporter.exportSei(sei);
		assertEquals("Same UUID", sei.getUuid().toString(), exporter.shorter(testmat.getStruct(sei.getName()).get("uuid").toString()));
		assertEquals("Same Type", sei.getType().getName(), exporter.shorter(testmat.getStruct(sei.getName()).get("type").toString()));		
		assertEquals("Number of Elements", NUMBEROFELEMENTSWITHOUTCAS, testmat.getStruct("testsei").getFieldNames().size());
	}

	@Test
	public void testExportSeiHasChildren() throws IOException {
		TestStructuralElementOther tsei2 = new TestStructuralElementOther(testConcept);
		TestStructuralElementOther tsei3 = new TestStructuralElementOther(testConcept);
		TestStructuralElementOther tsei4 = new TestStructuralElementOther(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		StructuralElementInstance sei3 = tsei3.getStructuralElementInstance();
		StructuralElementInstance sei4 = tsei4.getStructuralElementInstance();
		sei2.setName("sei1Child");
		sei3.setName("sei2Child");
		sei4.setName("sei3Child");
		sei2.setParent(sei);
		sei3.setParent(sei2);
		sei4.setParent(sei);
		MatFile testmat = exporter.exportSei(sei);
		Struct struct = testmat.getStruct("testsei").getStruct("children");
		List<String> childnames = struct.getFieldNames();
		
		assertThat("Includes all Children", childnames, hasItems("sei1Child", "sei3Child"));
		assertThat("Child has Child", struct.getStruct("sei1Child").getStruct("children").getFieldNames(), hasItems("sei2Child"));
	}

	@Test
	public void testExportSeiallCategories() {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		TestCategoryComposition tc1 = new TestCategoryComposition(testConcept);
		tsei.add(tc);
		tsei.add(tc1);

		MatFile testmat = exporter.exportSei(sei);
		Struct matStruct = testmat.getStruct("testsei");
		List<String> matCategoryAssinments = matStruct.getFieldNames();
		assertEquals("Number of CategoryAssinments",
				sei.getCategoryAssignments().size(),
				matStruct.getFieldNames().size() - NUMBEROFELEMENTSWITHOUTCAS);
		assertThat("Includes all CategoryAssinments",
				matCategoryAssinments,
				hasItems(TestCategoryAllProperty.class.getSimpleName(), TestCategoryComposition.class.getSimpleName()));
	}

	@Test
	public void testContentOfPropertyTestCategoryAllProperty() {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		tc.setTestBool(true);
		tc.setTestFloat(2);
		tc.setTestEnum("HIGH");
		tc.setTestString("test");

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
		assertEquals("Unit of Float", exporter.shorter(struct.getStruct("testFloat").get(MatHelper.UNIT).toString()), "Kilogram");
		assertEquals("Value of Float", struct.getStruct("testFloat").get(MatHelper.VALUE).toString(), "2.0");
		assertEquals("Value of Enum", struct.getStruct("testEnum").get(MatHelper.VALUE).toString(), "25.0");
		assertEquals("Name of Enum", exporter.shorter(struct.getStruct("testEnum").get(MatHelper.NAME).toString()), "HIGH");
		assertEquals("Value of Bool", struct.getStruct("testBool").get(MatHelper.VALUE).toString(), "true");
		assertEquals("Value of String", exporter.shorter(struct.getStruct("testString").get(MatHelper.VALUE).toString()), "test");
	}

	@Test
	public void testContentOfPropertyTestCategoryComposition() {
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
		assertEquals("Unit of Float", exporter.shorter(struct.getStruct("testFloat").get(MatHelper.UNIT).toString()), "Kilogram");
	}

	@Test
	public void testContentOfPropertyTestCategoryReferenceWithoutReference() {
		TestCategoryReference tc = new TestCategoryReference(testConcept);
		tsei.add(tc);	//TestCategoryReference

		MatFile testmat = exporter.exportSei(sei);
		Struct struct = testmat.getStruct("testsei").getStruct(tc.getName());
		assertEquals("Reference UUID",
				exporter.shorter(struct.getStruct("testRefCategory").get(MatHelper.UUID).toString()),
				"");
		assertEquals("Reference UUID",
				exporter.shorter(struct.getStruct("testRefCategory").get(MatHelper.FULLNAME).toString()),
				"");
	}

	@Test
	public void testContentOfPropertyTestCategoryReferenceWithReference() {
		TestCategoryReference tc = new TestCategoryReference(testConcept);
		TestCategoryAllProperty tc1 = new TestCategoryAllProperty(testConcept);
		tc.setTestRefCategory(tc1);
		tsei.add(tc);	//TestCategoryReference

		MatFile testmat = exporter.exportSei(sei);
		Struct struct = testmat.getStruct("testsei").getStruct(tc.getName());
		assertEquals("Reference UUID",
				exporter.shorter(struct.getStruct("testRefCategory").get(MatHelper.UUID).toString()),
				tc1.getUuid().toString());
		assertEquals("Reference UUID",
				exporter.shorter(struct.getStruct("testRefCategory").get(MatHelper.FULLNAME).toString()),
				tc1.getName());
	}

	@Test
	public void testContentOfPropertyTestCategoryIntrinsicArray() {
		TestCategoryIntrinsicArray tc = new TestCategoryIntrinsicArray(testConcept);
		tsei.add(tc);	//TestCategoryIntrinsicArray
		MatFile testmat = exporter.exportSei(sei);
		Struct struct = testmat.getStruct("testsei").getStruct(tc.getName());
		assertEquals("ArrayLength", struct.getCell("testStringArrayStatic").getNumElements(), ARRAYLENGTH);
	}

	@Test
	public void testContentOfPropertyTestCategoryCompositionArray() {
		TestCategoryCompositionArray tc = new TestCategoryCompositionArray(testConcept);
		tsei.add(tc);	//TestCategoryCompositionArray
		MatFile testmat = exporter.exportSei(sei);
		Struct struct = testmat.getStruct("testsei").getStruct(tc.getName());
		assertEquals("ArrayLength",
				struct.getCell("testCompositionArrayStatic").getNumElements(),
				ARRAYLENGTH);
		assertEquals("Unit of Float",
				exporter.shorter(struct.getCell("testCompositionArrayStatic").getStruct(0).getStruct("testFloat").get(MatHelper.UNIT).toString()),
				"Kilogram");
	}

	@Test
	public void testContentOfPropertyTestCategoryReferenceArray() {
		TestCategoryReferenceArray tc = new TestCategoryReferenceArray(testConcept);
		tsei.add(tc);	//TestCategoryReferenceArray
		MatFile testmat = exporter.exportSei(sei);
		Struct struct = testmat.getStruct("testsei").getStruct(tc.getName());
		assertEquals("Number of Elements", struct.getFieldNames().size(), ARRAYLENGTH);
	}

	@Test
	public void testContentOfPropertyTestCategoryExtends() {
		TestCategoryExtends tc = new TestCategoryExtends(testConcept);
		tsei.add(tc);	//TestCategoryExtends
		MatFile testmat = exporter.exportSei(sei);
		Struct struct = testmat.getStruct("testsei").getStruct(tc.getName());
		assertEquals("Number of Elements", struct.getFieldNames().size(), ARRAYLENGTH);
	}

	@Test
	public void testContentOfPropertyEReferenceTest() {
		EReferenceTest tc = new EReferenceTest(testConcept);
		tsei.add(tc);	//EReferenceTest
		MatFile testmat = exporter.exportSei(sei);
		Struct struct = testmat.getStruct("testsei").getStruct(tc.getName());
		assertEquals("Number of Elements", struct.getFieldNames().size(), 1);
		assertEquals("Number of Elements", struct.getFieldNames().get(0), "eReferenceTest");
	}
}
