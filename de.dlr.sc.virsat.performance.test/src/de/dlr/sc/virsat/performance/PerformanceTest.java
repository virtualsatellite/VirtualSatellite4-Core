/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.performance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import de.dlr.sc.virsat.apps.api.external.ModelAPI;
import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBase;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;

/**
 * This class creates a study with many elements and tests system performance on different operations
 */
@RunWith(Parameterized.class)
public class PerformanceTest extends AConceptProjectTestCase {
    
    public static final int MIN_STUDY_SIZE = 200;
    public static final int MAX_STUDY_SIZE = 10000;
    public static final int STUDY_SIZE_INCREASE_STEP = 200;

	protected ModelAPI modelAPI;
	protected Concept testConcept;

    /**
	 * (Study size -> (Measure -> Elapsed Time))
     */
    protected static Map<Integer, Map<String, Long>> performanceMeasures;

	/**
	 * For injecting parameters of a parameterized test
	 */
	@Parameter
	//CHECKSTYLE:OFF
	public int testNumberOfElements;
	//CHECKSTYLE:ON

    /**
     * Collection of study sizes for parameterized test. These values will be injected into field testNumberOfElements
     * @return collection of arrays of size one containing study sizes
     */
    @Parameters
    public static Collection<Object[]> data() {
    	List<Object[]> studySizes = new ArrayList<>();
    	for (int i = MIN_STUDY_SIZE; i <= MAX_STUDY_SIZE; i += STUDY_SIZE_INCREASE_STEP) {
    		studySizes.add(new Object[] {i});
    	}
        return studySizes;
    }

	@Override
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		activateCoreConcept();
		
		// Test concept depends on maturity concept
		executeAsCommand(() -> loadConceptAndInstallToRepository(de.dlr.sc.virsat.model.extension.maturity.Activator.getPluginId()));
		testConcept = executeAsCommand(() -> loadConceptAndInstallToRepository(de.dlr.sc.virsat.model.extension.tests.Activator.getPluginId()));
		
		modelAPI = new ModelAPI() {			
			@Override
			public String getCurrentProjectAbsolutePath() {
				return testProject.getLocation().toString();
			}
		};
	}

	@Override
	public void initializeTimeOutRule() {
		// No timeout
	}

	/**
	 * Prepare storage for timing results of all tests
	 */
	@BeforeClass
	public static void init() {
		performanceMeasures = new LinkedHashMap<>();
	}
	
	/**
	 * After the tests are executed write the test timings into a file
	 * @throws IOException 
	 */
	@AfterClass
	public static void end() throws IOException {
		Files.write(Paths.get("performanceTestReport.csv"), getPerformanceReportCsv());
	}

	/**
	 * Formats collected timing values as a CSV table
	 * @return lines of a CSV file
	 */
	protected static List<String> getPerformanceReportCsv() {
		List<String> result = new ArrayList<>();
		List<String> measures = new ArrayList<>(performanceMeasures.get(MIN_STUDY_SIZE).keySet());
		String header = "Number of elements," + String.join(",", measures);
		result.add(header);
		
		for (Map.Entry<Integer, Map<String, Long>> e : performanceMeasures.entrySet()) {
			StringBuilder sb = new StringBuilder();
			sb.append(e.getKey());
			for (String measure : measures) {
				sb.append("," + e.getValue().get(measure));
			}
			result.add(sb.toString());
		}
		return result;
	}


	@Test
	public final void testSaveAndLoad() throws CoreException, IOException {
		long start = System.currentTimeMillis();

		// Add a category with random values to all SEIs
		new StudyGenerator() {
			
			@Override
			public IBeanStructuralElementInstance createSei(String name) {
				IBeanStructuralElementInstance sei = super.createSei(name);

				TestCategoryBase cat = new TestCategoryBase(testConcept);
				cat.setTestBaseProperty(ThreadLocalRandom.current().nextInt());
				cat.setTestReference(getRandomPreviousSei().getFirst(TestCategoryBase.class));

				sei.add(cat);
				return sei;
			}
		}.generate();
		
		long timestampCreated = System.currentTimeMillis();
		modelAPI.performInheritance();
		long timestampInheritance = System.currentTimeMillis();
		
		modelAPI.saveAll();
		
		long timestampAllSaved = System.currentTimeMillis();
		
		reloadResources();
		assertProjectHasElements();
		long timestampAllLoaded = System.currentTimeMillis();

		logTime("Create", timestampCreated - start);
		logTime("Empty Inheritance Propagation", timestampInheritance - timestampCreated);
		logTime("Save", timestampAllSaved - timestampInheritance);
		logTime("Reload", timestampAllLoaded - timestampAllSaved);
	}

	@Test
	public final void testInheritance() throws CoreException, IOException {

		// Add random inheritance to the generated SEIs, and a CA only to the root SEI
		new StudyGenerator() {
			
			public IBeanStructuralElementInstance createRootSei(String name) {
				IBeanStructuralElementInstance sei = super.createSei(name);

				TestCategoryBase cat = new TestCategoryBase(testConcept);
				cat.setTestBaseProperty(ThreadLocalRandom.current().nextInt());
				cat.setTestReference((TestCategoryBase) getRandomPreviousSei());
				sei.add(cat);

				return sei;
			};
			
			public IBeanStructuralElementInstance createSei(String name) {
				IBeanStructuralElementInstance sei = super.createSei(name);
				IBeanStructuralElementInstance superSei = getRandomPreviousSei();
				sei.addSuperSei(superSei);
				return sei;
			}
		}.generate();
		
		long timestampCreated = System.currentTimeMillis();
		modelAPI.performInheritance();
		long timestampInitialInheritance = System.currentTimeMillis();
		assertAllSeisHaveSameInheritedValue();
		changeRandomInheritanceParameters();
		
		long timestampBeforeSecondInheritance = System.currentTimeMillis();
		modelAPI.performInheritance();
		long timestampAfterSecondInheritance = System.currentTimeMillis();
		assertNotAllSeisHaveSameInheritedValue();

		modelAPI.saveAll();
		
		long timestampAllSaved = System.currentTimeMillis();
		
		reloadResources();
		assertProjectHasElements();
		long timestampAllLoaded = System.currentTimeMillis();

		logTime("Initial Inheritance Propagation", timestampInitialInheritance - timestampCreated);
		logTime("Inheritance Propagation After Change", timestampAfterSecondInheritance - timestampBeforeSecondInheritance);
		logTime("Reload With Inheritance", timestampAllLoaded - timestampAllSaved);
	}
	
		
	/**
	 * Logs a performance measurement result
	 */
	private void logTime(String measure, long value) {
		if (!performanceMeasures.containsKey(testNumberOfElements)) {
			performanceMeasures.put(testNumberOfElements, new LinkedHashMap<>());
		}
		assertFalse("No duplicate measures", performanceMeasures.get(testNumberOfElements).containsKey(measure));
		performanceMeasures.get(testNumberOfElements).put(measure, value);
	}
	

	/**
	 * Make sure that all SEIs have inherited the same value from root super SEI
	 */
	private void assertAllSeisHaveSameInheritedValue() {
		TestStructuralElement rootSei = modelAPI.getRootSeis(TestStructuralElement.class).get(0);
		TestCategoryBase rootCat = rootSei.getAll(TestCategoryBase.class).get(0);
		long rootValue = rootCat.getTestBaseProperty();
		EList<StructuralElementInstance> allInherited = rootSei.getStructuralElementInstance().getDeepChildren();
		assertEquals(testNumberOfElements - 1, allInherited.size());
		for (StructuralElementInstance sei : allInherited) {
			assertEquals(1, sei.getCategoryAssignments().size());
			CategoryAssignment ca = sei.getCategoryAssignments().get(0);
			TestCategoryBase caBean = new TestCategoryBase(ca);
			assertEquals(rootValue, caBean.getTestBaseProperty());
		}
	}

	/**
	 * Make sure that after changing random inheritance parameters some values are different
	 * from root super SEI value
	 */
	private void assertNotAllSeisHaveSameInheritedValue() {
		TestStructuralElement rootSei = modelAPI.getRootSeis(TestStructuralElement.class).get(0);
		TestCategoryBase rootCat = rootSei.getAll(TestCategoryBase.class).get(0);
		long rootValue = rootCat.getTestBaseProperty();
		EList<StructuralElementInstance> allInherited = rootSei.getStructuralElementInstance().getDeepChildren();
		assertEquals(testNumberOfElements - 1, allInherited.size());
		boolean differentValue = false;
		for (StructuralElementInstance sei : allInherited) {
			assertEquals(1, sei.getCategoryAssignments().size());
			CategoryAssignment ca = sei.getCategoryAssignments().get(0);
			TestCategoryBase caBean = new TestCategoryBase(ca);
			if (rootValue != caBean.getTestBaseProperty()) {
				differentValue = true;
				break;
			}
		}
		assertTrue(differentValue);
	}

	private void changeRandomInheritanceParameters() {
		changeValueInInheritanceRoot();
		randomlyChangeOverrideFlagAndInheritedValueInInheritedSeis();
	}

	private void changeValueInInheritanceRoot() {
		TestStructuralElement rootSei = modelAPI.getRootSeis(TestStructuralElement.class).get(0);
		TestCategoryBase rootCat = rootSei.getAll(TestCategoryBase.class).get(0);
		rootCat.setTestBaseProperty(rootCat.getTestBaseProperty() + 1);
	}

	/**
	 * Randomly sets override flag and changes value in some percent of SEIs
	 */
	private void randomlyChangeOverrideFlagAndInheritedValueInInheritedSeis() {
		final double MODIFICATION_PROBABILITY = 0.1d;
		int numberOfRandomChanges = (int) (testNumberOfElements * MODIFICATION_PROBABILITY);
		TestStructuralElement rootSei = modelAPI.getRootSeis(TestStructuralElement.class).get(0);
		EList<StructuralElementInstance> allInherited = rootSei.getStructuralElementInstance().getDeepChildren();
		for (int i = 0; i < numberOfRandomChanges; i++) {
			StructuralElementInstance seiToModify = allInherited.get(ThreadLocalRandom.current().nextInt(allInherited.size()));
			CategoryAssignment ca = seiToModify.getCategoryAssignments().get(0);
			TestCategoryBase caBean = new TestCategoryBase(ca);
			caBean.setTestBaseProperty(ThreadLocalRandom.current().nextInt());
			caBean.getTestBasePropertyBean().getTypeInstance().setOverride(true);
		}
	}

	public final void reloadResources() {
		editingDomain.reloadAll();
		repository = rs.getRepository();
	}
	
	public void assertProjectHasElements() {
		int elementCount = 0;
		for (StructuralElementInstance root : repository.getRootEntities()) {
			elementCount += 1 + root.getDeepChildren().size();
		}
		assertEquals(testNumberOfElements, elementCount);
	}


	/**
	 * Generator of a study for the test cases
	 */
	class StudyGenerator {
		protected List<IBeanStructuralElementInstance> addedElements;


		public IBeanStructuralElementInstance createRootSei(String name) {
			TestStructuralElement sei = new TestStructuralElement(testConcept);
			sei.setName(name);
			return sei;
		}

		public IBeanStructuralElementInstance createSei(String name) {
			TestStructuralElement se = new TestStructuralElement(testConcept);
			se.setName(name);
			return se;
		}
		
		/**
		 * Generates study data and adds it to the test project using modelAPI
		 */
		public void generate() throws IOException {
			addedElements = new ArrayList<>();

			IBeanStructuralElementInstance root = createRootSei("root");
			modelAPI.createSeiStorage(root);
			modelAPI.addRootSei(root);
			addedElements.add(root);

			for (int i = 0; i < testNumberOfElements - 1; i++) {
				IBeanStructuralElementInstance newElement = createSei("element_" + (i + 1));
				modelAPI.createSeiStorage(newElement);

				IBeanStructuralElementInstance parent = getRandomPreviousSei();
				parent.add(newElement);

				addedElements.add(newElement);
			}
			
		}
		
		/**
		 * @return one of previously generated SEIs or null if nothing has been generated yet
		 */
		public IBeanStructuralElementInstance getRandomPreviousSei() {
			if (addedElements.isEmpty()) {
				return null;
			}
			return addedElements.get(ThreadLocalRandom.current().nextInt(addedElements.size()));
		}
	}
}
