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

import org.eclipse.emf.common.util.EList;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.MatFile;


public class ExporterTest extends AConceptTestCase{
	
	private Concept concept;
	
	@Before
	public void setUp() throws IOException {
		prepareEditingDomain();
		concept = loadConceptFromPlugin();
//		final int testNumber1 = 5;
//		final double testNumber2 = 3.4;
//		final double testNumber3 = 4.2;
//		final int testNumber4 = 4;
//		final int testNumber5 = 2;
//		int [] input = {testNumber1, testNumber4, testNumber5};
//		MatFile matfile = Mat5.newMatFile();
//		matfile.addArray("var1", Mat5.newLogicalScalar(true));
//		matfile.addArray("var2", Mat5.newString("Test"));
//		matfile.addArray("var3", Mat5.newScalar(testNumber1));
//		matfile.addArray("var4", Mat5.newStruct()
//				.set("a", Mat5.newComplexScalar(testNumber2, testNumber3))
//				.set("b", Mat5.newStruct()
//						.set("I", Mat5.newComplexScalar(testNumber3, testNumber1))
//						.set("II", Mat5.newScalar(testNumber3))));
//		Mat5.writeToFile(matfile, "Testfile.mat");
	}
	
	@Test
	public void ExportSeiTest() throws IOException {
		TestCategoryAllProperty tc = new TestCategoryAllProperty(concept);
		EList<Category> ca = tc.getConcept().getCategories();
		TestStructuralElement tsei = new TestStructuralElement(concept);
		StructuralElementInstance sei = tsei.getStructuralElementInstance();
		sei.setName("testsei");
		//Exporter.newMatFile("data.mat");
		Mat5.writeToFile(Exporter.exportSei(sei), "Testfile.mat");
		Mat5.writeToFile(Exporter.exportSei(sei, ca), "Testfile.mat");
	}

}
