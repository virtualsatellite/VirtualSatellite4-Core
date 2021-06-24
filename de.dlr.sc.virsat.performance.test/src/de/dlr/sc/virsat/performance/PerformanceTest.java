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
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import de.dlr.sc.virsat.apps.api.external.ModelAPI;
import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBase;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;

/**
 * This class creates study with several element instances and check the system performance
 * 
 * @author desh_me
 *
 */
@RunWith(Parameterized.class)
public class PerformanceTest extends AConceptProjectTestCase {
    
    public static final int MIN_STUDY_SIZE = 200;
    public static final int MAX_STUDY_SIZE = 10000;
    public static final int STUDY_SIZE_INCREASE_STEP = 200;
    
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

	private static List<String> performanceMeasuresNoInheritance;
	private static List<String> performanceMeasuresInheritance;
	
	private ModelAPI modelAPI;
	private Concept testConcept;

	@Override
	public void initializeTimeOutRule() {
		// Disable timeout
		globalTimeout = Timeout.seconds(0);
	}
	
	@Before
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

	/**
	 * method to create new structural element
	 * @param name name of the structural element
	 * @return structural element
	 * @throws IOException 
	 */
	public TestStructuralElement createSE(String name) throws IOException {
		TestStructuralElement se = new TestStructuralElement(testConcept);
		se.setName(name);
		modelAPI.createSeiStorage(se);
		return se;
	}

	@Parameter
	//CHECKSTYLE:OFF
	// For injecting parameters of a parameterized test
	public int testNumberOfElements;
	//CHECKSTYLE:ON
	
	/**
	 * Before the tests are executed prepare an array for timing of all tests
	 */
	@BeforeClass
	public static void init() {
		performanceMeasuresNoInheritance = new ArrayList<>();
		performanceMeasuresNoInheritance.add("Number of elements\tCreate time\tInheritance\tSave time\tLoad time");

		performanceMeasuresInheritance = new ArrayList<>();
		performanceMeasuresInheritance.add("Number of elements\tCreate time\tInitial inheritance propagation\tSecond inheritance\tSave time\tLoad time");
	}

	/**
	 * After the tests are executed write the test timings into a file
	 * @throws IOException 
	 */
	@AfterClass
	public static void end() throws IOException {
		Files.write(Paths.get("performanceTestReport.tsv"), performanceMeasuresNoInheritance);
		Files.write(Paths.get("performanceTestReportInheritance.tsv"), performanceMeasuresInheritance);
	}
	
	@Test
	public final void testSaveAndLoad() throws CoreException, IOException {
		long start = System.currentTimeMillis();
		
		generateRandomTreeWithReferences(testNumberOfElements);
		
		
		long timestampCreated = System.currentTimeMillis();
		modelAPI.performInheritance();
		long timestampInheritance = System.currentTimeMillis();
		
		modelAPI.saveAll();
		
		long timestampAllSaved = System.currentTimeMillis();
		
		reloadResources();
		assertProjectHasElements();
		long timestampAllLoaded = System.currentTimeMillis();

		long timeToCreate = timestampCreated - start;
		long timeToDoInheritance = timestampInheritance - timestampCreated;
		long timeToSave = timestampAllSaved - timestampInheritance;
		long timeToLoad = timestampAllLoaded - timestampAllSaved;
		performanceMeasuresNoInheritance.add(testNumberOfElements + "\t" + timeToCreate + "\t" + timeToDoInheritance + "\t" + timeToSave + "\t" + timeToLoad);
	}

	@Test
	public final void testInheritance() throws CoreException, IOException {
		long start = System.currentTimeMillis();
		
		generateRandomTreeWithInheritance(testNumberOfElements);
		
		long timestampCreated = System.currentTimeMillis();
		modelAPI.performInheritance();
		long timestampInitialInheritance = System.currentTimeMillis();
		assertAllSeisHaveSameInheritedValue();
		changeRandomInheritanceParameters();
		
		long timestampBeforeSecondInheritance = System.currentTimeMillis();
		modelAPI.performInheritance();
		long timestampAfterSecondInheritance = System.currentTimeMillis();
		assertNotAllSeisHaveSameInheritedValue();
		long timestampBeforeSave = System.currentTimeMillis();
		modelAPI.saveAll();
		
		long timestampAllSaved = System.currentTimeMillis();
		
		reloadResources();
		assertProjectHasElements();
		long timestampAllLoaded = System.currentTimeMillis();

		long timeToCreate = timestampCreated - start;
		long timeToDoInitialInheritance = timestampInitialInheritance - timestampCreated;
		long timeToDoSecondInheritance = timestampAfterSecondInheritance - timestampBeforeSecondInheritance;
		long timeToSave = timestampAllSaved - timestampBeforeSave;
		long timeToLoad = timestampAllLoaded - timestampAllSaved;
		performanceMeasuresInheritance.add(testNumberOfElements + "\t" + timeToCreate + "\t" + timeToDoInitialInheritance + "\t" + timeToDoSecondInheritance + "\t" + timeToSave + "\t" + timeToLoad);
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
	 * 
	 */
	private void changeRandomInheritanceParameters() {
		changeValueInInheritanceRoot();
		randomlyChangeOverrideFlagAndInheritedValueInInheritedSeis();
	}

	/**
	 * 
	 */
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
	 * method to create random tree with structural element
	 * @param n size of the tree
	 * @throws IOException 
	 */
	private void generateRandomTreeWithReferences(int n) throws IOException {
		TestStructuralElement root = createSE("root");
		TestCategoryBase cat = new TestCategoryBase(testConcept);
		cat.setTestBaseProperty(ThreadLocalRandom.current().nextInt());
		root.add(cat);
		modelAPI.addRootSei(root);
		List<TestStructuralElement> allElements = new ArrayList<>();
		allElements.add(root);
		for (int i = 0; i < n - 1; i++) {
			TestStructuralElement newElement = createSE("element_" + (i + 1));
			TestStructuralElement parent = allElements.get(ThreadLocalRandom.current().nextInt(allElements.size()));
			parent.add(newElement);

			TestStructuralElement referenceTo = allElements.get(ThreadLocalRandom.current().nextInt(allElements.size()));
			TestCategoryBase referencedCa = referenceTo.getAll(TestCategoryBase.class).get(0);
			cat = new TestCategoryBase(testConcept);
			cat.setTestBaseProperty(ThreadLocalRandom.current().nextInt());
			cat.setTestReference(referencedCa);
			newElement.add(cat);
			
			allElements.add(newElement);
		}
	}

	/**
	 * method to create random tree with inheritance
	 * @param n size of the tree
	 * @throws IOException 
	 */
	private void generateRandomTreeWithInheritance(int n) throws IOException {
		TestStructuralElement root = createSE("root");
		TestCategoryBase cat = new TestCategoryBase(testConcept);
		cat.setTestBaseProperty(ThreadLocalRandom.current().nextInt());
		root.add(cat);
		modelAPI.addRootSei(root);
		List<TestStructuralElement> allElements = new ArrayList<>();
		allElements.add(root);
		for (int i = 0; i < n - 1; i++) {
			TestStructuralElement newElement = createSE("element_" + (i + 1));
			TestStructuralElement parent = allElements.get(ThreadLocalRandom.current().nextInt(allElements.size()));
			parent.add(newElement);

			TestStructuralElement inheritsFrom = allElements.get(ThreadLocalRandom.current().nextInt(allElements.size()));
			newElement.addSuperSei(inheritsFrom);
			
			allElements.add(newElement);
		}
	}

}
