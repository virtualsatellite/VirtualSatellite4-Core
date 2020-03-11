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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.EReferenceTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryComposition;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElementOther;
import de.dlr.sc.virsat.model.extension.tests.test.ATestConceptTestCase;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.MatFile;

public class ImporterTest extends ATestConceptTestCase {

	private StructuralElementInstance sei;
	private TestStructuralElement tsei;
	private MatExporter exporter;
	private MatImporter importer;
	private MatFile mat;
	private static final int NUMBEROFELEMENTS = 6;

	@Before
	public void setUp() throws CoreException {
		exporter = new MatExporter();
		importer = new MatImporter();
		super.setUp();
		addResourceSetAndRepository();
		loadTestConcept();
		tsei = new TestStructuralElement(testConcept);
		sei = tsei.getStructuralElementInstance();
		sei.setName("testsei");
		repository.getRootEntities().add(sei);
		mat = exporter.exportSei(sei);
	}

	@Test
	public void testCheckIfCorrectSeiCorrect() {
		assertTrue("Is the same", importer.checkIfCorrectSei(sei, mat));
		sei.setName("Testsein");
		assertFalse("Not the same", importer.checkIfCorrectSei(sei, mat));
	}

	@Test
	public void testCheckIfCorrectSeiWrongUUID() {
		TestStructuralElementOther tsei2 = new TestStructuralElementOther(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName("testsei");
		sei2.setType(sei.getType());
		assertFalse("Not the same uuid", importer.checkIfCorrectSei(sei2, mat));
	}

	@Test
	public void testCheckIfCorrectSeiWrongType() {
		TestStructuralElementOther tsei2 = new TestStructuralElementOther(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName("testsei");
		sei2.setUuid(sei.getUuid());
		assertFalse("Not the same type", importer.checkIfCorrectSei(sei2, mat));
	}

	@Test
	public void testCheckIfCorrectSeiWrongNumberOfChildren() {
		TestStructuralElementOther tsei2 = new TestStructuralElementOther(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName("testsei");
		sei2.setParent(sei);
		assertFalse("Not the same number of children", importer.checkIfCorrectSei(sei, mat));
	}

	@Test
	public void testCheckIfCorrectSeiWrongChildren() {
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName("testsei");
		sei2.setParent(sei);
		mat = exporter.exportSei(sei);
		TestStructuralElementOther tsei3 = new TestStructuralElementOther(testConcept);
		StructuralElementInstance sei3 = tsei3.getStructuralElementInstance();
		sei3.setName("testseiChild");
		TestStructuralElementOther tsei4 = new TestStructuralElementOther(testConcept);
		StructuralElementInstance sei4 = tsei4.getStructuralElementInstance();
		sei4.setName("testseiChild2");
		sei2.setParent(sei3);
		sei4.setParent(sei);
		assertFalse("Not the same children", importer.checkIfCorrectSei(sei, mat));
	}

	@Test
	public void testImportCasDeleteCas() {
		TestCategoryAllProperty tc2 = new TestCategoryAllProperty(testConcept);
		tsei.add(tc2);
		mat = exporter.exportSei(sei);
		TestCategoryComposition tc1 = new TestCategoryComposition(testConcept);
		tsei.add(tc1);
		assertTrue("Sei has two CategoryAssinments", sei.getCategoryAssignments().size() == 2);
		importer.importSei(sei, mat.getStruct(sei.getName()));
		assertTrue("Only one CategoryAssinment", sei.getCategoryAssignments().size() == 1);
		assertTrue("Right CategoryAssinment included",
				sei.getCategoryAssignments().get(0).getName().equals(tc2.getName()));
	}

	@Test
	public void testImportAPIDeleteAPI() throws IOException {
		
		EReferenceTest tc2 = new EReferenceTest(testConcept);
		tsei.add(tc2);
		TestCategoryAllProperty tc3 = new TestCategoryAllProperty(testConcept);
		tsei.add(tc3);
		APropertyInstance nInstance = sei.getCategoryAssignments().get(0).getPropertyInstances().get(0);
		sei.getCategoryAssignments().remove(0);
		mat = exporter.exportSei(sei);
		sei.getCategoryAssignments().get(0).getPropertyInstances().add(nInstance);
		assertEquals("CategoryAssinment has seven PropertyInstances", sei.getCategoryAssignments().get(0).getPropertyInstances().size(), NUMBEROFELEMENTS + 1);
		importer.importSei(sei, mat.getStruct(sei.getName()));
		assertTrue("Instance deleted", sei.getCategoryAssignments().get(0).getPropertyInstances().size() == NUMBEROFELEMENTS);
	}

	@Test
	public void testImportOfValuesRemoveAll() throws IOException {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		tsei.add(tc);
		mat = exporter.exportSei(sei);
		tc.setTestBool(true);
		tc.setTestFloat(2);
		tc.setTestEnum("HIGH");
		tc.setTestString("test");
		tc.setTestInt(1);
		tc.setTestResource(URI.createFileURI("TestFile.mat"));
		Mat5.writeToFile(exporter.exportSei(sei), "TestFile.mat");
		importer.importSei(sei, mat.getStruct(sei.getName()));
		
	}
	
//	@Test
//	public void testCheck() throws IOException {
//		TestStructuralElementOther tsei4 = new TestStructuralElementOther(testConcept);
//		StructuralElementInstance sei2 = tsei4.getStructuralElementInstance();
//		sei2.setName("child1");
//		sei2.setParent(sei);
//		TestStructuralElementOther tsei1 = new TestStructuralElementOther(testConcept);
//		StructuralElementInstance sei3 = tsei1.getStructuralElementInstance();
//		sei3.setName("child2");
//		sei3.setParent(sei);
//		TestCategoryAllProperty tc2 = new TestCategoryAllProperty(testConcept);
//		tsei.add(tc2);
//		TestCategoryIntrinsicArray tc = new TestCategoryIntrinsicArray(testConcept);
//		tsei.add(tc);	//TestCategoryIntrinsicArray
//		TestCategoryComposition tc1 = new TestCategoryComposition(testConcept);
//		tsei.add(tc1);
//		tc.getTestStringArrayStatic().get(0).setValue("hallo");
//		mat = exporter.exportSei(sei);
//		Mat5.writeToFile(mat, "TestFile.mat");
//		
//
//		importer.importSei(sei, "TestFile2.mat");
//		
//		mat = exporter.exportSei(sei);
//		Mat5.writeToFile(mat, "TestFile1.mat");
//	}
}