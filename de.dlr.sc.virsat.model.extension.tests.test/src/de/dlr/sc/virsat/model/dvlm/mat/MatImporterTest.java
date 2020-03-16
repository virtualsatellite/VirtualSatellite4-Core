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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.EReferenceTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryComposition;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReference;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElementOther;
import de.dlr.sc.virsat.model.extension.tests.test.ATestConceptTestCase;
import de.dlr.sc.virsat.model.external.tests.ExternalTestType;
import us.hebi.matlab.mat.types.MatFile;

public class MatImporterTest extends ATestConceptTestCase {

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
		addEditingDomainAndRepository();
		executeAsCommand(() -> loadTestConcept());
		tsei = new TestStructuralElement(testConcept);
		sei = tsei.getStructuralElementInstance();
		sei.setName("testsei");
		executeAsCommand(() -> repository.getRootEntities().add(sei));
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
	public void testImportAPIDeleteAPI() {
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
	public void testImportOfValuesRemoveAll() {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		tsei.add(tc);
		mat = exporter.exportSei(sei);
		
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		TestCategoryAllProperty tc1 = new TestCategoryAllProperty(testConcept);
		tc1.setTestBool(false);
		tc1.setTestFloat(2);
		tc1.setTestEnum("HIGH");
		tc1.setTestString("test");
		tc1.setTestInt(1);
		URI testUri =  URI.createPlatformResourceURI("Testresource", false);
		tc1.setTestResource(testUri);
		sei2.setName("testsei");
		sei2.setUuid(sei.getUuid());
		tsei2.add(tc1);
		importer.importSei(sei2, mat.getStruct(sei2.getName()));

		EList<APropertyInstance> caSei = sei.getCategoryAssignments().get(0).getPropertyInstances();
		EList<APropertyInstance> caSei2 = sei2.getCategoryAssignments().get(0).getPropertyInstances();
		assertTrue("same number of elements", caSei.size() == caSei2.size());
		assertEquals("same testString", tc1.getTestString(), tc.getTestString());
		assertEquals("same testBool", tc1.getTestBool(), tc.getTestBool());
		assertEquals(tc1.getTestFloat(), tc.getTestFloat(), 0);
		assertEquals("same testResource", tc1.getTestResource(), tc.getTestResource());
		assertEquals("same testEnum", tc1.getTestEnum(), tc.getTestEnum());
		
	}

	@Test
	public void testImportOfValuesAddAll() {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		tsei.add(tc);
		tc.setTestBool(true);
		tc.setTestFloat(2);
		tc.setTestEnum("HIGH");
		tc.setTestString("test");
		tc.setTestInt(1);
		URI testUri = URI.createPlatformPluginURI("Testresource", true);
		tc.setTestResource(testUri);
		mat = exporter.exportSei(sei);

		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName("testsei");
		sei2.setUuid(sei.getUuid());
		TestCategoryAllProperty tc1 = new TestCategoryAllProperty(testConcept);
		tsei2.add(tc1);
		importer.importSei(sei2, mat.getStruct(sei2.getName()));

		EList<APropertyInstance> caSei = sei.getCategoryAssignments().get(0).getPropertyInstances();
		EList<APropertyInstance> caSei2 = sei2.getCategoryAssignments().get(0).getPropertyInstances();
		assertTrue("same number of elements", caSei.size() == caSei2.size());
		assertEquals("same testString", tc1.getTestString(), tc.getTestString());
		assertEquals("same testBool", tc1.getTestBool(), tc.getTestBool());
		assertEquals(tc1.getTestFloat(), tc.getTestFloat(), 0);
		assertEquals("same testResource", tc1.getTestResource(), tc.getTestResource());
		assertEquals("same testEnum", tc1.getTestEnum(), tc.getTestEnum());
		assertEquals("same testInt", tc1.getTestInt(), tc.getTestInt());
	}

	@Test
	public void testImportOfValuesChangeNothingEmpty() {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		tsei.add(tc);
		mat = exporter.exportSei(sei);

		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName("testsei");
		sei2.setUuid(sei.getUuid());
		TestCategoryAllProperty tc1 = new TestCategoryAllProperty(testConcept);
		tsei2.add(tc1);
		importer.importSei(sei2, mat.getStruct(sei2.getName()));

		EList<APropertyInstance> caSei = sei.getCategoryAssignments().get(0).getPropertyInstances();
		EList<APropertyInstance> caSei2 = sei2.getCategoryAssignments().get(0).getPropertyInstances();
		assertTrue("same number of elements", caSei.size() == caSei2.size());
		assertEquals("same testString", tc1.getTestString(), tc.getTestString());
		assertEquals("same testBool", tc1.getTestBool(), tc.getTestBool());
		assertEquals(tc1.getTestFloat(), tc.getTestFloat(), 0);
		assertEquals("same testResource", tc1.getTestResource(), tc.getTestResource());
		assertEquals("same testEnum", tc1.getTestEnum(), tc.getTestEnum());
	}

	@Test
	public void testImportOfValuesChangeNothingValues() {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		tsei.add(tc);
		tc.setTestBool(true);
		tc.setTestFloat(2);
		tc.setTestEnum("HIGH");
		tc.setTestString("test");
		tc.setTestInt(1);
		URI testUri = URI.createPlatformPluginURI("Testresource", true);
		tc.setTestResource(testUri);
		mat = exporter.exportSei(sei);

		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName("testsei");
		sei2.setUuid(sei.getUuid());
		TestCategoryAllProperty tc1 = new TestCategoryAllProperty(testConcept);
		tc1.setTestBool(true);
		tc1.setTestFloat(2);
		tc1.setTestEnum("HIGH");
		tc1.setTestString("test");
		tc1.setTestInt(1);
		tc1.setTestResource(testUri);
		tsei2.add(tc1);
		importer.importSei(sei2, mat.getStruct(sei2.getName()));

		EList<APropertyInstance> caSei = sei.getCategoryAssignments().get(0).getPropertyInstances();
		EList<APropertyInstance> caSei2 = sei2.getCategoryAssignments().get(0).getPropertyInstances();
		assertTrue("same number of elements", caSei.size() == caSei2.size());
		assertEquals("same testString", tc1.getTestString(), tc.getTestString());
		assertEquals("same testBool", tc1.getTestBool(), tc.getTestBool());
		assertEquals(tc1.getTestFloat(), tc.getTestFloat(), 0);
		assertEquals("same testResource", tc1.getTestResource(), tc.getTestResource());
		assertEquals("same testEnum", tc1.getTestEnum(), tc.getTestEnum());
		assertEquals("same testInt", tc1.getTestInt(), tc.getTestInt());
	}

	@Test
	public void testImportOfValuesRef() {
		//empty and import empty
		TestCategoryReference tc = new TestCategoryReference(testConcept);
		tsei.add(editingDomain, tc);
		mat = exporter.exportSei(sei);
		MatFile mat1 = exporter.exportSei(sei);
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		TestCategoryReference tc1 = new TestCategoryReference(testConcept);
		tsei2.add(editingDomain, tc1);
		sei2.setName("testsei");
		sei2.setUuid(sei.getUuid());
		importer.importSei(sei2, mat.getStruct(sei.getName()));
		assertEquals("same Reference empty", tc1.getTestRefCategory(), tc.getTestRefCategory()); //empty to empty

		//values and import empty
		TestCategoryAllProperty tc2 = new TestCategoryAllProperty(testConcept);
		tc1.setTestRefCategory(editingDomain, tc2);
		importer.importSei(sei2, mat1.getStruct(sei.getName()));
		assertEquals("same Reference", tc1.getTestRefCategory(), tc.getTestRefCategory());

		//empty and import values
		tc1.setTestRefCategory(editingDomain, tc2);
		editingDomain.saveAll();
		mat = exporter.exportSei(sei2);
		importer.importSei(sei, mat.getStruct(sei.getName()));
		assertEquals("same Reference", tc1.getTestRefCategory(), tc.getTestRefCategory());

		//values and import values
		tc.setTestRefCategory(editingDomain, tc2);
		tc1.setTestRefCategory(editingDomain, tc2);
		editingDomain.saveAll();
		mat = exporter.exportSei(sei2);
		importer.importSei(sei, mat.getStruct(sei.getName()));
		assertEquals("same Reference", tc1.getTestRefCategory(), tc.getTestRefCategory());
	}
	
	@Test
	public void testImportOfValuesERef() throws IOException {
		//empty and import empty
		EReferenceTest tc = new EReferenceTest(testConcept);
		tsei.add(editingDomain, tc);
		mat = exporter.exportSei(sei);
		MatFile mat1 = exporter.exportSei(sei);
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		EReferenceTest tc1 = new EReferenceTest(testConcept);
		tsei2.add(editingDomain, tc1);
		sei2.setName("testsei");
		sei2.setUuid(sei.getUuid());
		importer.importSei(sei2, mat.getStruct(sei.getName()));
		assertEquals("same Reference from empty to empty", tc1.getEReferenceTest(), tc.getEReferenceTest());

		//values and import empty
		ExternalTestType testERef = de.dlr.sc.virsat.model.external.tests.TestsFactory.eINSTANCE.createExternalTestType();
		tc1.setEReferenceTest(editingDomain, testERef);
		System.out.println(tc1.getEReferenceTest());
		importer.importSei(sei2, mat1.getStruct(sei.getName()));
		System.out.println(tc1.getEReferenceTest());
		System.out.println(tc.getEReferenceTest());
		assertEquals("same Reference from value to empty", tc1.getEReferenceTest(), tc.getEReferenceTest());

		//works till here
//		//empty and import values
//		tc1.setEReferenceTest(TEST_EREFERENCE_VALUE);
//		mat = exporter.exportSei(sei2);
//		Mat5.writeToFile(mat, "TestFile.mat");
//		importer.importSei(sei, mat.getStruct(sei.getName()));
//		assertEquals("same Reference empty", tc1.getEReferenceTest(), tc.getEReferenceTest()); //empty to empty
//
//		//values and import values
//		MatFile mat2 = exporter.exportSei(sei2);
//		importer.importSei(sei, mat2.getStruct(sei.getName()));
//		assertEquals("same Reference empty", tc1.getEReferenceTest(), tc.getEReferenceTest()); //empty to empty
	}
}
