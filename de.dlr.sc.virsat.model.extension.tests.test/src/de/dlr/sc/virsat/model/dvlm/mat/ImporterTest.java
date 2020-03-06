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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryComposition;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElementOther;
import de.dlr.sc.virsat.model.extension.tests.test.ATestConceptTestCase;
import us.hebi.matlab.mat.types.MatFile;

public class ImporterTest extends ATestConceptTestCase {

	private StructuralElementInstance sei;
	private TestStructuralElement tsei;
	private MatExporter exporter;
	private Importer importer;
	private MatFile mat;

	@Before
	public void setUp() throws CoreException {
		exporter = new MatExporter();
		importer = new Importer();
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
	public void testCheckIfCorrectSeiCorrect() throws IOException {
		assertTrue("Is the same", importer.checkIfCorrectSei(sei, mat));
		sei.setName("Testsein");
		assertFalse("Not the same", importer.checkIfCorrectSei(sei, mat));
	}
	
	@Test
	public void testCheckIfCorrectSeiWrongName() throws IOException {
		sei.setName("Testsein");
		assertFalse("Not the same Name", importer.checkIfCorrectSei(sei, mat));
	}
	
	@Test
	public void testCheckIfCorrectSeiWrongUUID() throws IOException {
		TestStructuralElementOther tsei2 = new TestStructuralElementOther(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName("testsei");
		sei2.setType(sei.getType());
		assertFalse("Not the same type", importer.checkIfCorrectSei(sei2, mat));
	}
	
	@Test
	public void testCheckIfCorrectSeiWrongType() throws IOException {
		TestStructuralElementOther tsei2 = new TestStructuralElementOther(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName("testsei");
		sei2.setUuid(sei.getUuid());
		assertFalse("Not the same type", importer.checkIfCorrectSei(sei2, mat));
	}
	
	@Test
	public void testCheckIfCorrectSeiWrongNumberOfChildren() throws IOException {
		TestStructuralElementOther tsei2 = new TestStructuralElementOther(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName("testsei");
		sei2.setParent(sei);
		assertFalse("Not the same children", importer.checkIfCorrectSei(sei, mat));
	}
	
	@Test
	public void testCheckIfCorrectSeiWrongChildren() throws IOException {
		TestStructuralElement tsei2 = new TestStructuralElement(testConcept);
		StructuralElementInstance sei2 = tsei2.getStructuralElementInstance();
		sei2.setName("testsei");
		sei2.setParent(sei);
		mat = exporter.exportSei(sei);

		TestStructuralElementOther tsei3 = new TestStructuralElementOther(testConcept);
		StructuralElementInstance sei3 = tsei3.getStructuralElementInstance();
		sei3.setName("testseiChild");
		sei2.setParent(sei3);
		sei3.setParent(sei);
		

		assertFalse("Not the same children", importer.checkIfCorrectSei(sei, mat));
	}
	
	@Test
	public void testCheckIfCorrectSeiWrongCAs() throws IOException {
		TestCategoryComposition tc = new TestCategoryComposition(testConcept);
		tsei.add(tc);
		assertFalse("Not the same children", importer.checkIfCorrectSei(sei, mat));
	}
}