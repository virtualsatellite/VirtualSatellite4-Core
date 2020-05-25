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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.EReferenceTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryComposition;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryCompositionArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReference;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElementOther;
import de.dlr.sc.virsat.model.extension.tests.test.ATestConceptTestCase;
import de.dlr.sc.virsat.model.external.tests.ExternalModelTestHelper;
import de.dlr.sc.virsat.model.external.tests.ExternalTestType;
import de.dlr.sc.virsat.project.resources.command.CreateSeiResourceAndFileCommand;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.MatFile;
import us.hebi.matlab.mat.types.Struct;

public class MatImporterTest extends ATestConceptTestCase {

	private StructuralElementInstance sei;
	private TestStructuralElement tsei;
	private MatExporter exporter;
	private MatImporter importer;
	private MatFile mat;
	private static final String TEST_STRING = "testString";
	private static final int TEST_INT = 1;
	private static final float TEST_FLOAT = 2;
	private static final String TEST_ENUM_VALUE = "HIGH";
	private static final String TEST_SEI = "testsei";
	private static final Boolean TEST_BOOL = true;
	private static final String TEST_UNIT = "Kilogram";

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
	public void testWithoutExport() throws IOException {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		tsei.add(tc);
		//Create Mat
		MatFile matfile = Mat5.newMatFile();
		
		Struct boolPropertyStruct = Mat5.newStruct();
		boolPropertyStruct.set(MatHelper.VALUE, Mat5.newLogicalScalar(TEST_BOOL));
		
		Struct stringPropertyStruct = Mat5.newStruct();
		stringPropertyStruct.set(MatHelper.VALUE, Mat5.newString(TEST_STRING));
		
		Struct intPropertyStruct = Mat5.newStruct();
		intPropertyStruct.set(MatHelper.UNIT, Mat5.newString(""));
		intPropertyStruct.set(MatHelper.VALUE, Mat5.newScalar(TEST_INT));
		
		Struct floatPropertyStruct = Mat5.newStruct();
		floatPropertyStruct.set(MatHelper.UNIT, Mat5.newString(""));
		floatPropertyStruct.set(MatHelper.VALUE, Mat5.newScalar(TEST_FLOAT));
		
		Struct enumPropertyStruct = Mat5.newStruct();
		enumPropertyStruct.set(MatHelper.UNIT, Mat5.newString(TEST_UNIT));
		enumPropertyStruct.set(MatHelper.VALUE, Mat5.newString("25"));
		enumPropertyStruct.set(MatHelper.NAME, Mat5.newString(TEST_ENUM_VALUE));

		Struct resourcePropertyStruct = Mat5.newStruct();
		resourcePropertyStruct.set(MatHelper.URI, Mat5.newString(""));
		
		Struct caStruct = Mat5.newStruct();
		caStruct.set("testString", stringPropertyStruct);
		caStruct.set("testInt", intPropertyStruct);
		caStruct.set("testFloat", floatPropertyStruct);
		caStruct.set("testResource", resourcePropertyStruct);
		caStruct.set("testEnum", enumPropertyStruct);
		caStruct.set("testBool", boolPropertyStruct);
		
		Struct struct = Mat5.newStruct();
		struct.set(MatHelper.TYPE, Mat5.newString(sei.getType().getName()))
			  .set(MatHelper.UUID, Mat5.newString(sei.getUuid().toString()));
		struct.set(tc.getName(), caStruct);
		matfile.addArray(sei.getName(), struct);
		Command cmd = importer.importSei(editingDomain, sei, matfile);
		editingDomain.getCommandStack().execute(cmd);
		assertEquals("Is the same", TEST_STRING, tc.getTestString());
		assertEquals("Is the same", TEST_ENUM_VALUE, tc.getTestEnum());
		assertEquals("Is the same", TEST_BOOL, tc.getTestBool());
		assertEquals("Is the same", TEST_INT, tc.getTestInt());
		assertEquals(TEST_FLOAT, tc.getTestFloat(), 0);
		assertEquals("Is the same", null, tc.getTestResource());
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
	public void testImportUnit() throws IOException {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		tsei.add(tc);
		tc.getTestFloatBean().setUnit("Kilogram");
		mat = exporter.exportSei(sei);
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName(TEST_SEI);
		sei2.setUuid(sei.getUuid());
		TestCategoryAllProperty tc1 = new TestCategoryAllProperty(testConcept);
		tsei2.add(tc1);
		editingDomain.getCommandStack().execute(importer.importSei(editingDomain, sei2, mat));
		assertEquals("Unit set", "Kilogram", tc1.getTestFloatBean().getUnit());
	}
	
	@Test
	public void testImportCalculated() throws IOException {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		tsei.add(tc);
		tc.setTestInt(TEST_INT);
		tc.setTestFloat(TEST_FLOAT);
		mat = exporter.exportSei(sei);
		
		final int NEW_TEST_INT = 2;
		final float NEW_TEST_FLOAT = 3;
		tc.setTestInt(NEW_TEST_INT);
		tc.setTestFloat(NEW_TEST_FLOAT);
		
		// inject a piHelper that states that the testInt property is calculated
		PropertyInstanceHelper piHelper = new PropertyInstanceHelper() {
			public boolean isCalculated(ATypeInstance instance) {
				return instance.getType().getName().equals("testInt");
			}
		};
		importer = new MatImporter() {
			protected PropertyInstanceHelper getPiHelper() {
				return piHelper;
			}
		};
		
		editingDomain.getCommandStack().execute(importer.importSei(editingDomain, sei, mat));
		
		assertEquals("calculated int value has not been modified", NEW_TEST_INT, tc.getTestInt());
		assertEquals("uncalculated float value has not been modified", TEST_FLOAT, tc.getTestInt(), 0);
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
		editingDomain.getCommandStack().execute(importer.importSei(editingDomain, sei2, mat));

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
		editingDomain.getCommandStack().execute(importer.importSei(editingDomain, sei2, mat));

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
		editingDomain.getCommandStack().execute(importer.importSei(editingDomain, sei, mat));

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
		editingDomain.getCommandStack().execute(importer.importSei(editingDomain, sei, mat));

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
		//Reference test target
		final TestCategoryAllProperty TEST_REFERENCE_TARGET = new TestCategoryAllProperty(testConcept);
		
		//prepare test data
		TestCategoryReference tc = new TestCategoryReference(testConcept);
		Command cmd = tsei.add(editingDomain, tc);
		editingDomain.getCommandStack().execute(cmd);
		mat = exporter.exportSei(sei);
		MatFile mat1 = exporter.exportSei(sei);
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		executeAsCommand(() -> repository.getRootEntities().add(sei2));
		TestCategoryReference tc1 = new TestCategoryReference(testConcept);
		cmd = tsei2.add(editingDomain, tc1);
		editingDomain.getCommandStack().execute(cmd);
		sei2.setName(TEST_SEI);
		sei2.setUuid(sei.getUuid());
		
		//Add SEI into resource to be able to resolve references in the resourceset
		sei.getCategoryAssignments().add(TEST_REFERENCE_TARGET.getTypeInstance());	//Add it to the resource so that it can be found
		editingDomain.getCommandStack().execute(new CreateSeiResourceAndFileCommand(rs, sei));
		
		//Check that no value is added if imported empty
		cmd = importer.importSei(editingDomain, sei2, mat);
		editingDomain.getCommandStack().execute(cmd);
		assertEquals("same Reference from empty to empty", tc.getTestRefCategory(), tc1.getTestRefCategory()); //empty to empty
		
		//Check that values are overwritten if deleted externaly
		cmd = tc1.setTestRefCategory(editingDomain, TEST_REFERENCE_TARGET);  //Add reference value internally
		editingDomain.getCommandStack().execute(cmd);
		cmd = importer.importSei(editingDomain, sei2, mat1);
		editingDomain.getCommandStack().execute(cmd);
		assertNull("same Reference from value to empty", tc.getTestRefCategory());

		//Set new reference and validate that it is imported
		editingDomain.getCommandStack().execute(tc1.setTestRefCategory(editingDomain, TEST_REFERENCE_TARGET));
		mat = exporter.exportSei(sei2); //export new reference to check import
		cmd = importer.importSei(editingDomain, sei, mat);
		editingDomain.getCommandStack().execute(cmd);
		assertEquals("same Reference from empty to value", TEST_REFERENCE_TARGET, tc.getTestRefCategory());

		//Check that value is not wrongly removed or changed
		tc.setTestRefCategory(editingDomain, TEST_REFERENCE_TARGET); 
		tc1.setTestRefCategory(editingDomain, TEST_REFERENCE_TARGET);
		editingDomain.saveAll();
		mat = exporter.exportSei(sei2);
		cmd = importer.importSei(editingDomain, sei, mat);
		editingDomain.getCommandStack().execute(cmd);
		assertEquals("same Reference from value to value", TEST_REFERENCE_TARGET, tc.getTestRefCategory());
		System.out.println(tc.getTestRefCategory());
	}

	@Test
	public void testImportOfValuesERef() throws IOException {
		//Test reference value
		final ExternalTestType TEST_EREF_MODEL_VALUE = new ExternalModelTestHelper().loadExternalTypeInstance();
		
		//Prepare test data
		EReferenceTest tc = new EReferenceTest(testConcept);
		tsei.add(tc);
		mat = exporter.exportSei(sei);
		MatFile mat1 = exporter.exportSei(sei);
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		EReferenceTest tc1 = new EReferenceTest(testConcept);
		tsei2.add(tc1);
		sei2.setName(TEST_SEI);
		sei2.setUuid(sei.getUuid());
		
		//Check that no value is added if imported empty
		Command cmd = importer.importSei(editingDomain, sei2, mat);
		editingDomain.getCommandStack().execute(cmd);
		assertEquals("No value should have been added", tc.getEReferenceTest(), tc1.getEReferenceTest());

		//Try import of empty value and check that old value is removed
		cmd = tc1.setEReferenceTest(editingDomain, TEST_EREF_MODEL_VALUE);
		editingDomain.getCommandStack().execute(cmd);
		cmd = importer.importSei(editingDomain, sei2, mat1);
		editingDomain.getCommandStack().execute(cmd);
		assertNull("Value should be removed", tc.getEReferenceTest());

		//Try import reference value
		cmd = tc1.setEReferenceTest(editingDomain, TEST_EREF_MODEL_VALUE);
		editingDomain.getCommandStack().execute(cmd);
		mat = exporter.exportSei(sei2);
		cmd = importer.importSei(editingDomain, sei, mat);
		editingDomain.getCommandStack().execute(cmd);
		assertNotNull("Value should be added", tc.getEReferenceTest());
		// URI + fragment combination is only clear identifier of external model elements
		assertEquals("Value should be added and in the same resource as test element", 
				EcoreUtil.getURI(TEST_EREF_MODEL_VALUE), EcoreUtil.getURI(tc.getEReferenceTest()));

		//Check that value is not wrongly removed or changed
		cmd = tc1.setEReferenceTest(editingDomain, TEST_EREF_MODEL_VALUE);
		editingDomain.getCommandStack().execute(cmd);
		cmd = tc.setEReferenceTest(editingDomain, TEST_EREF_MODEL_VALUE);
		editingDomain.getCommandStack().execute(cmd);
		mat = exporter.exportSei(sei2);
		cmd = importer.importSei(editingDomain, sei, mat);
		editingDomain.getCommandStack().execute(cmd);
		assertNotNull("Value should not have bean removed", tc.getEReferenceTest());
		// URI + fragment combination is only clear identifier of external model elements
		assertEquals("Value should be from same resource as before", 
				EcoreUtil.getURI(TEST_EREF_MODEL_VALUE), EcoreUtil.getURI(tc.getEReferenceTest()));
	}
	
	@Test 
	public void testImportOfValuesComposend() throws IOException {
		
		//delete Value with mat
		TestCategoryComposition tc = new TestCategoryComposition(testConcept);
		Command cmd = tsei.add(editingDomain, tc);
		editingDomain.getCommandStack().execute(cmd);
		mat = exporter.exportSei(sei);
		
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		TestCategoryComposition tc1 = new TestCategoryComposition(testConcept);
		cmd = tc1.getTestSubCategory().setTestString(editingDomain, TEST_STRING);
		editingDomain.getCommandStack().execute(cmd);
		cmd = tsei2.add(editingDomain, tc1);
		editingDomain.getCommandStack().execute(cmd);
		sei2.setName(TEST_SEI);
		sei2.setUuid(sei.getUuid());
		cmd = importer.importSei(editingDomain, sei2, mat);
		editingDomain.getCommandStack().execute(cmd);
		assertEquals("same composed Category", null, tc1.getTestSubCategory().getTestString());
		
		//set Value with mat
		cmd = tc.getTestSubCategory().setTestString(editingDomain, TEST_STRING);
		editingDomain.getCommandStack().execute(cmd);
		mat = exporter.exportSei(sei);
		cmd = importer.importSei(editingDomain, sei2, mat);
		editingDomain.getCommandStack().execute(cmd);
		assertEquals("same composed Category", TEST_STRING, tc1.getTestSubCategory().getTestString());
	}
	
	@Test
	public void testImportOfValuesArray() throws IOException {
		TestCategoryCompositionArray tc = new TestCategoryCompositionArray(testConcept);
		Command cmd = tsei.add(editingDomain, tc);
		editingDomain.getCommandStack().execute(cmd);
		mat = exporter.exportSei(sei);
		
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		TestCategoryCompositionArray tc1 = new TestCategoryCompositionArray(testConcept);
		cmd = tc1.getTestCompositionArrayStatic().get(0).setTestString(editingDomain, TEST_STRING);
		editingDomain.getCommandStack().execute(cmd);
		cmd = tsei2.add(editingDomain, tc1);
		editingDomain.getCommandStack().execute(cmd);
		sei2.setName(TEST_SEI);
		sei2.setUuid(sei.getUuid());
		cmd = importer.importSei(editingDomain, sei2, mat);
		editingDomain.getCommandStack().execute(cmd);
		assertEquals("same composed Category", null, tc1.getTestCompositionArrayStatic().get(0).getTestString());
		
		//set Value with mat
		cmd = tc1.getTestCompositionArrayStatic().get(0).setTestString(editingDomain, TEST_STRING);
		editingDomain.getCommandStack().execute(cmd);
		mat = exporter.exportSei(sei2);
		cmd = importer.importSei(editingDomain, sei, mat);
		editingDomain.getCommandStack().execute(cmd);
		assertEquals("same composed Category", TEST_STRING, tc1.getTestCompositionArrayStatic().get(0).getTestString());
	}
}
