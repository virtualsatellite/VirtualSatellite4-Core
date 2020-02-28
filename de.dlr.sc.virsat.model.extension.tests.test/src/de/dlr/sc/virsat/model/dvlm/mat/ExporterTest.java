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
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.EReferenceTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanA;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanB;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanConcrete;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryComposition;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryCompositionArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryExtends;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryIntrinsicArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReference;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReferenceArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestCrossLinkedParametersWithCalculation;
import de.dlr.sc.virsat.model.extension.tests.model.TestMassParameters;
import de.dlr.sc.virsat.model.extension.tests.model.TestParameter;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import de.dlr.sc.virsat.model.extension.tests.test.ATestConceptTestCase;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.MatFile;
import us.hebi.matlab.mat.types.Struct;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class ExporterTest extends ATestConceptTestCase {
	
	private static final int NUMBEROFELEMENTS = 3;
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
		assertEquals("Same String", test.substring(1, test.length() - 1), Exporter.shorter(test));
	}
	
	@Test //Delete at the end
	public void testExportSeiCanMatlabReadIt() throws IOException {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(testConcept);
		TestCategoryComposition tc1 = new TestCategoryComposition(testConcept);
		TestParameter tc11 = new TestParameter(testConcept);
		TestMassParameters tc12 = new TestMassParameters(testConcept);
		EReferenceTest tc14 = new  EReferenceTest(testConcept);
		
		TestCategoryBeanA tc6 = new TestCategoryBeanA(testConcept);
		TestCategoryBeanB tc7 = new TestCategoryBeanB(testConcept);
		TestCategoryBeanConcrete tc8 = new TestCategoryBeanConcrete(testConcept);
		TestCrossLinkedParametersWithCalculation tc13 = new TestCrossLinkedParametersWithCalculation(testConcept);
		
		TestCategoryReference tc2 = new TestCategoryReference(testConcept);
		TestCategoryIntrinsicArray tc3 = new TestCategoryIntrinsicArray(testConcept);
		TestCategoryCompositionArray tc4 = new TestCategoryCompositionArray(testConcept);
		TestCategoryReferenceArray tc5 = new TestCategoryReferenceArray(testConcept);
		TestCategoryBase tc9 = new TestCategoryBase(testConcept);
		TestCategoryExtends tc10 = new TestCategoryExtends(testConcept);
		




		
		tsei.add(tc);
		tsei.add(tc1);
		tsei.add(tc11);
		tsei.add(tc12);
		tsei.add(tc14);
		
		tsei.add(tc6);
		tsei.add(tc7);
		tsei.add(tc8);
		tsei.add(tc13);
		
//		tsei.add(tc2);	ERef != Ref
		tsei.add(tc3);
		tsei.add(tc4);		
		tsei.add(tc5);	
		tsei.add(tc9);		
		tsei.add(tc10);		
		


		
		
		Mat5.writeToFile(exporter.exportSei(sei), "Testfile.mat");
	}
	@Test
	public void testExportSeitypeUUID() throws IOException {
		MatFile testmat = exporter.exportSei(sei);
		assertEquals("Same UUID", sei.getUuid().toString(), Exporter.shorter(testmat.getStruct(sei.getName()).get("UUID").toString()));
		assertEquals("Same Type", sei.getType().getName(), Exporter.shorter(testmat.getStruct(sei.getName()).get("Type").toString()));
		
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
		assertEquals("Number of CategoryAssinments", sei.getCategoryAssignments().size(), matStruct.getFieldNames().size() - NUMBEROFELEMENTS);
		assertThat("Includes all CategoryAssinments", matCategoryAssinments, hasItems(TestCategoryAllProperty.class.getSimpleName(), TestCategoryComposition.class.getSimpleName()));
		
	}
	

}
