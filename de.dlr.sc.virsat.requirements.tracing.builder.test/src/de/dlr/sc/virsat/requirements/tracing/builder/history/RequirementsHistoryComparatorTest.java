/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
/**
 * 
 */
package de.dlr.sc.virsat.requirements.tracing.builder.history;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.rmf.reqif10.ReqIF10Factory;
import org.eclipse.rmf.reqif10.SpecObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author fran_tb
 *
 */
public class RequirementsHistoryComparatorTest {

	public static final String TRACE_MODEL_EXTENSION = "tm";
	public static final String REQUIREMENTS_MODEL_EXTENSION = "reqif";
	public static final String TEST_PROJECT = "testBaseProject";
	public static final String TEST_REQUIREMENT_NAME = "TestRequirement";
	public static final String TEST_REQUIREMENT_NAME_CHANGED = "TestRequirement_Changed";
	public static final String TEST_REQUIREMENT_2_NAME = "TestRequirement2";
	public static final String TEST_REQUIREMENTS_MODEL = "TestReqs.reqif";
	
	IFile fileRequirements;
	Resource resourceRequirements;
	

	ResourceSet testResSet;
	IProject project;

	
	@Before
	public void setUp() throws CoreException, IOException {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put(TRACE_MODEL_EXTENSION, new XMIResourceFactoryImpl());
		m.put(REQUIREMENTS_MODEL_EXTENSION, new XMIResourceFactoryImpl());

		// Create base project
		testResSet = new ResourceSetImpl();
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT);
		if (!project.exists()) {
			project.create(null);
		}
		project.open(null);

		// Create test requirements model
		fileRequirements = project.getFile(TEST_REQUIREMENTS_MODEL);
		resourceRequirements = testResSet
				.createResource(URI.createPlatformResourceURI(fileRequirements.getFullPath().toString(), true));
		
	}
	
	
	@After
	public void tearDown() throws CoreException, IOException {
		project.delete(true, null);
		resourceRequirements.delete(null);
	}
	
	@Test
	public void testCompare() throws IOException, CoreException {
		
		//Create initial model with two requirements
		SpecObject testRequirementElement = ReqIF10Factory.eINSTANCE.createSpecObject();
		testRequirementElement.setLongName(TEST_REQUIREMENT_NAME);
		testRequirementElement.setIdentifier(UUID.randomUUID().toString());
		resourceRequirements.getContents().add(testRequirementElement);
		
		SpecObject testRequirementElement2 = ReqIF10Factory.eINSTANCE.createSpecObject();
		testRequirementElement2.setLongName(TEST_REQUIREMENT_2_NAME);
		testRequirementElement2.setIdentifier(UUID.randomUUID().toString());
		resourceRequirements.getContents().add(testRequirementElement2);
		
		//Save the model
		resourceRequirements.save(Collections.EMPTY_MAP);
		
		//Change the model
		testRequirementElement.setLongName(TEST_REQUIREMENT_NAME_CHANGED);
		
		//Save the model
		resourceRequirements.save(Collections.EMPTY_MAP);
		
		//Compare
		RequirementsHistoryComparator comparator = new RequirementsHistoryComparator();
		List<SpecObject> changedElements = comparator.compareWithLatestFromHistory(fileRequirements.getFullPath().toString());
		
		assertEquals("Exactly one element changed", 1, changedElements.size());
		assertTrue("The first element changed only", testRequirementElement.getIdentifier().equals(changedElements.get(0).getIdentifier()));
		assertFalse("The second element did not change", testRequirementElement2.getIdentifier().equals(changedElements.get(0).getIdentifier()));
		
	}
	
	
	
}
