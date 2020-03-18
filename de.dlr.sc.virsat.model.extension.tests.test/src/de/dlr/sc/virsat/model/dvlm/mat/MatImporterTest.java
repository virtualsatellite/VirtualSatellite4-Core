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
	private static final String TEST_STRING = "testString";
	private static final int TEST_INT = 1;
	private static final float TEST_FLOAT = 2;
	private static final String TEST_ENUM_VALUE = "HIGH";
	private static final String TEST_SEI = "testsei";
	private static final Boolean TEST_BOOL = false;

	@Before
	public void setUp() throws CoreException {
		exporter = new MatExporter();
		importer = new MatImporter();
		super.setUp();
		addEditingDomainAndRepository();
		executeAsCommand(() -> loadTestConcept());
		tsei = new TestStructuralElement(testConcept);
		sei = tsei.getStructuralElementInstance();
		sei.setName(TEST_SEI);
		executeAsCommand(() -> repository.getRootEntities().add(sei));
		mat = exporter.exportSei(sei);
	}

	@Test
	public void testCheckIfCorrectSeiCorrect() {
		assertTrue("Is the same", importer.checkIfCorrectSei(sei, mat));
		sei.setName("Testsein");
		assertFalse("Not the same name", importer.checkIfCorrectSei(sei, mat));
	}

	@Test
	public void testCheckIfCorrectSeiWrongUUID() {
		TestStructuralElementOther tsei2 = new TestStructuralElementOther(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName(TEST_SEI);
		sei2.setType(sei.getType());
		assertFalse("Not the same uuid", importer.checkIfCorrectSei(sei2, mat));
	}

	@Test
	public void testCheckIfCorrectSeiWrongType() {
		TestStructuralElementOther tsei2 = new TestStructuralElementOther(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName(TEST_SEI);
		sei2.setUuid(sei.getUuid());
		assertFalse("Not the same type", importer.checkIfCorrectSei(sei2, mat));
	}

	@Test
	public void testCheckIfCorrectSeiWrongNumberOfChildren() {
		TestStructuralElementOther tsei2 = new TestStructuralElementOther(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName(TEST_SEI);
		sei2.setParent(sei);
		assertFalse("Not the same number of children", importer.checkIfCorrectSei(sei, mat));
	}

	@Test
	public void testCheckIfCorrectSeiWrongChildren() {
		//set up sei with one child
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName(TEST_SEI);
		sei2.setParent(sei);
		mat = exporter.exportSei(sei);

		//change sei to have 2 children and one of this children have also a child
		TestStructuralElementOther tsei3 = new TestStructuralElementOther(testConcept);
		StructuralElementInstance sei3 = tsei3.getStructuralElementInstance();
		sei3.setName("testseiChild");
		sei2.setParent(sei3);

		TestStructuralElementOther tsei4 = new TestStructuralElementOther(testConcept);
		StructuralElementInstance sei4 = tsei4.getStructuralElementInstance();
		sei4.setName("testseiChild2");
		sei4.setParent(sei);

		assertFalse("Not the same children", importer.checkIfCorrectSei(sei, mat));
	}

	@Test
	public void testImportCasDeleteCas() throws IOException {
		TestCategoryAllProperty tc2 = new TestCategoryAllProperty(testConcept);
		tsei.add(tc2);
		mat = exporter.exportSei(sei);
		TestCategoryComposition tc1 = new TestCategoryComposition(testConcept);
		tsei.add(tc1);
		assertTrue("Sei has two CategoryAssinments", sei.getCategoryAssignments().size() == 2);
		importer.importSei(sei, mat);
		assertTrue("Only one CategoryAssinment", sei.getCategoryAssignments().size() == 1);
		assertTrue("Right CategoryAssinment included",
				sei.getCategoryAssignments().get(0).getName().equals(tc2.getName()));
	}

	@Test
	public void testImportAPIDeleteAPI() throws IOException {
		//create a SEI and an PropertyInstance
		EReferenceTest tc2 = new EReferenceTest(testConcept);
		tsei.add(tc2);
		TestCategoryAllProperty tc3 = new TestCategoryAllProperty(testConcept);
		tsei.add(tc3);
		APropertyInstance nInstance = sei.getCategoryAssignments().get(0).getPropertyInstances().get(0);
		sei.getCategoryAssignments().remove(0);

		//get .mat with one Category Assignment 
		mat = exporter.exportSei(sei);

		//add one ProperyInstance to this
		sei.getCategoryAssignments().get(0).getPropertyInstances().add(nInstance);
		assertEquals("CategoryAssinment has seven PropertyInstances", NUMBEROFELEMENTS + 1, sei.getCategoryAssignments().get(0).getPropertyInstances().size());

		//import should delete 7th PropertyInstance
		importer.importSei(sei, mat);
		assertTrue("Instance deleted", sei.getCategoryAssignments().get(0).getPropertyInstances().size() == NUMBEROFELEMENTS);
	}

	@Test
	public void testImportOfValuesRemoveAll() throws IOException {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		tsei.add(tc);
		mat = exporter.exportSei(sei);
		
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		TestCategoryAllProperty tc1 = new TestCategoryAllProperty(testConcept);
		tc1.setTestBool(TEST_BOOL);
		tc1.setTestFloat(TEST_FLOAT);
		tc1.setTestEnum(TEST_ENUM_VALUE);
		tc1.setTestString(TEST_STRING);
		tc1.setTestInt(TEST_INT);
		URI testUri =  URI.createPlatformResourceURI("Testresource", false);
		tc1.setTestResource(testUri);
		sei2.setName(TEST_SEI);
		sei2.setUuid(sei.getUuid());
		tsei2.add(tc1);
		importer.importSei(sei2, mat);

		//checks if the import deletes all values
		EList<APropertyInstance> caSei = sei.getCategoryAssignments().get(0).getPropertyInstances();
		EList<APropertyInstance> caSei2 = sei2.getCategoryAssignments().get(0).getPropertyInstances();
		assertTrue("same number of elements", caSei.size() == caSei2.size());
		assertEquals("same testString", tc.getTestString(), tc1.getTestString());
		assertEquals("same testBool", tc.getTestBool(), tc1.getTestBool());
		assertEquals(tc.getTestFloat(), tc1.getTestFloat(), 0);
		assertEquals("same testResource", tc.getTestResource(), tc1.getTestResource());
		assertEquals("same testEnum", tc.getTestEnum(), tc1.getTestEnum());
		
	}

	@Test
	public void testImportOfValuesAddAll() throws IOException {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		tsei.add(tc);
		tc.setTestBool(TEST_BOOL);
		tc.setTestFloat(TEST_FLOAT);
		tc.setTestEnum(TEST_ENUM_VALUE);
		tc.setTestString(TEST_STRING);
		tc.setTestInt(TEST_INT);
		URI testUri = URI.createPlatformPluginURI("Testresource", true);
		tc.setTestResource(testUri);
		mat = exporter.exportSei(sei);

		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName(TEST_SEI);
		sei2.setUuid(sei.getUuid());
		TestCategoryAllProperty tc1 = new TestCategoryAllProperty(testConcept);
		tsei2.add(tc1);
		importer.importSei(sei2, mat);

		//checks if the import adds all Values
		EList<APropertyInstance> caSei = sei.getCategoryAssignments().get(0).getPropertyInstances();
		EList<APropertyInstance> caSei2 = sei2.getCategoryAssignments().get(0).getPropertyInstances();
		assertTrue("same number of elements", caSei.size() == caSei2.size());
		assertEquals("same testString", TEST_STRING, tc1.getTestString());
		assertEquals("same testBool", TEST_BOOL, tc1.getTestBool());
		assertEquals(TEST_FLOAT, tc1.getTestFloat(), 0);
		assertEquals("same testResource", tc.getTestResource(), tc1.getTestResource());
		assertEquals("same testEnum", TEST_ENUM_VALUE, tc1.getTestEnum());
		assertEquals("same testInt", TEST_INT, tc1.getTestInt());
	}

	@Test
	public void testImportOfValuesChangeNothingEmpty() throws IOException {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		tsei.add(tc);
		mat = exporter.exportSei(sei);

		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName(TEST_SEI);
		sei2.setUuid(sei.getUuid());
		TestCategoryAllProperty tc1 = new TestCategoryAllProperty(testConcept);
		tsei2.add(tc1);
		importer.importSei(sei2, mat);

		//checks that if the same sei is imported and everything is empty, it stays empty
		EList<APropertyInstance> caSei = sei.getCategoryAssignments().get(0).getPropertyInstances();
		EList<APropertyInstance> caSei2 = sei2.getCategoryAssignments().get(0).getPropertyInstances();
		assertTrue("same number of elements", caSei.size() == caSei2.size());
		assertEquals("same testString", tc.getTestString(), tc1.getTestString());
		assertEquals("same testBool", tc.getTestBool(), tc1.getTestBool());
		assertEquals(tc.getTestFloat(), tc1.getTestFloat(), 0);
		assertEquals("same testResource", tc.getTestResource(), tc1.getTestResource());
		assertEquals("same testEnum", tc.getTestEnum(), tc1.getTestEnum());
	}

	@Test
	public void testImportOfValuesChangeNothingValues() throws IOException {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		tsei.add(tc);
		tc.setTestBool(TEST_BOOL);
		tc.setTestFloat(TEST_FLOAT);
		tc.setTestEnum(TEST_ENUM_VALUE);
		tc.setTestString(TEST_STRING);
		tc.setTestInt(TEST_INT);
		URI testUri = URI.createPlatformPluginURI("Testresource", true);
		tc.setTestResource(testUri);
		mat = exporter.exportSei(sei);

		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName(TEST_SEI);
		sei2.setUuid(sei.getUuid());
		TestCategoryAllProperty tc1 = new TestCategoryAllProperty(testConcept);
		tc1.setTestBool(TEST_BOOL);
		tc1.setTestFloat(TEST_FLOAT);
		tc1.setTestEnum(TEST_ENUM_VALUE);
		tc1.setTestString(TEST_STRING);
		tc1.setTestInt(TEST_INT);
		tc1.setTestResource(testUri);
		tsei2.add(tc1);
		importer.importSei(sei2, mat);

		//checks if the same seiis imported and everything has a value, the value doesnt change
		EList<APropertyInstance> caSei = sei.getCategoryAssignments().get(0).getPropertyInstances();
		EList<APropertyInstance> caSei2 = sei2.getCategoryAssignments().get(0).getPropertyInstances();
		assertTrue("same number of elements", caSei.size() == caSei2.size());
		assertEquals("same testString", TEST_STRING, tc1.getTestString());
		assertEquals("same testBool", TEST_BOOL, tc1.getTestBool());
		assertEquals(TEST_FLOAT, tc1.getTestFloat(), 0);
		assertEquals("same testResource", tc.getTestResource(), tc1.getTestResource());
		assertEquals("same testEnumValue", tc.getTestEnumBean().getValue(), tc1.getTestEnumBean().getValue());
		assertEquals("same testEnumValue", tc.getTestEnumBean().getUnit(), tc1.getTestEnumBean().getUnit());
		assertEquals("same testInt", TEST_INT, tc1.getTestInt());
	}

	@Test
	public void testImportOfValuesRef() throws IOException {
		//empty and import empty
		TestCategoryReference tc = new TestCategoryReference(testConcept);
		tsei.add(editingDomain, tc);
		mat = exporter.exportSei(sei);
		MatFile mat1 = exporter.exportSei(sei);
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		TestCategoryReference tc1 = new TestCategoryReference(testConcept);
		tsei2.add(editingDomain, tc1);
		sei2.setName(TEST_SEI);
		sei2.setUuid(sei.getUuid());
		importer.importSei(sei2, mat);
		assertEquals("same EReference from empty to empty", tc.getTestRefCategory(), tc1.getTestRefCategory()); //empty to empty

		//values and import empty
		TestCategoryAllProperty tc2 = new TestCategoryAllProperty(testConcept);
		tc1.setTestRefCategory(editingDomain, tc2);
		importer.importSei(sei2, mat1);
		assertEquals("same EReference from value to empty", tc.getTestRefCategory(), tc1.getTestRefCategory());

		//empty and import values
		tc1.setTestRefCategory(editingDomain, tc2);
		editingDomain.saveAll();
		mat = exporter.exportSei(sei2);
		importer.importSei(sei, mat);
		assertEquals("same Reference from empty to value", tc.getTestRefCategory(), tc1.getTestRefCategory());

		//values and import values
		tc.setTestRefCategory(editingDomain, tc2);
		tc1.setTestRefCategory(editingDomain, tc2);
		editingDomain.saveAll();
		mat = exporter.exportSei(sei2);
		importer.importSei(sei, mat);
		assertEquals("same Reference from value to value", tc.getTestRefCategory(), tc1.getTestRefCategory());
	}

	@Test
	public void testImportOfValuesERef() throws IOException {
		ExternalTestType testERef = de.dlr.sc.virsat.model.external.tests.TestsFactory.eINSTANCE.createExternalTestType();
		
		//empty and import empty
		EReferenceTest tc = new EReferenceTest(testConcept);
		tsei.add(editingDomain, tc);
		mat = exporter.exportSei(sei);
		MatFile mat1 = exporter.exportSei(sei);
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		EReferenceTest tc1 = new EReferenceTest(testConcept);
		tsei2.add(editingDomain, tc1);
		sei2.setName(TEST_SEI);
		sei2.setUuid(sei.getUuid());
		importer.importSei(sei2, mat);
		assertEquals("same Reference from empty to empty", tc.getEReferenceTest(), tc1.getEReferenceTest());

		//values and import empty
		tc1.setEReferenceTest(editingDomain, testERef);
		importer.importSei(sei2, mat1);
		assertEquals("same Reference from value to empty", tc.getEReferenceTest(), tc1.getEReferenceTest());

		//empty and import values
		tc1.setEReferenceTest(editingDomain, testERef);
		editingDomain.saveAll();
		mat = exporter.exportSei(sei2);
		importer.importSei(sei, mat);
		assertEquals("same Reference from empty to value", tc.getEReferenceTest(), tc1.getEReferenceTest());

		//values and import values
		tc1.setEReferenceTest(editingDomain, testERef);
		tc.setEReferenceTest(editingDomain, testERef);
		editingDomain.saveAll();
		mat = exporter.exportSei(sei2);
		importer.importSei(sei, mat);
		assertEquals("same Reference from value to value", tc.getEReferenceTest(), tc1.getEReferenceTest());
	}
}
