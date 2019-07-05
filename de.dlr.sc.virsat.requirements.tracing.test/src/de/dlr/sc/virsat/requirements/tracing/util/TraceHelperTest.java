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
package de.dlr.sc.virsat.requirements.tracing.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.rmf.reqif10.ReqIF10Factory;
import org.eclipse.rmf.reqif10.SpecHierarchy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TMFactory;
import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement;
import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceabilityLinkContainer;

/**
 * @author fran_tb
 *
 */
public class TraceHelperTest {

	public static final String REQUIREMENTS_MODEL_PATH = "C:\\Users\\fran_tb\\Workspace\\Projects\\Requirements\\runtime-virsat_requirements\\test\\data\\ise_9c6f9c39-8d26-4e89-9209-1462424a2c6c\\documents\\ReactionWheel.reqif";
	public static final String TRACE_MODEL_PATH = "C:\\Users\\fran_tb\\Workspace\\Projects\\Requirements\\runtime-virsat_requirements\\test\\data\\ise_9c6f9c39-8d26-4e89-9209-1462424a2c6c\\documents\\ReactionWheel.tm";

	public static final String TRACE_MODEL_EXTENSION = "tm";
	public static final String REQUIREMENTS_MODEL_EXTENSION = "reqif";

	public static final String TEST_REQUIREMENTS_MODEL = "ReactionWheel.reqif";
	public static final String TEST_TRACE_MODEL = "ReactionWheel.tm";
	public static final String TEST_WRONG_TRACE_MODEL = "WrongTraceModel.tm";
	public static final String TEST_PROJECT = "testBaseProject";

	URI reqURI;
	URI traceModelURI;

	IFile fileRequirements;
	Resource resourceRequirements;
	SpecHierarchy testRequirementElement;

	IFile testWrongTraceFile;
	Resource testWrongTraceResource;
	
	ResourceSet testResSet;
	IProject project;

	TraceElement testTraceToTestRequirement;

	@Before
	public void setUp() throws Exception {
		reqURI = URI.createFileURI(REQUIREMENTS_MODEL_PATH);
		traceModelURI = URI.createFileURI(TRACE_MODEL_PATH);

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
		testRequirementElement = ReqIF10Factory.eINSTANCE.createSpecHierarchy();
		resourceRequirements.getContents().add(testRequirementElement);
		resourceRequirements.save(Collections.EMPTY_MAP);

		// Create a trace element
		testTraceToTestRequirement = TMFactory.eINSTANCE.createTraceElement();
		testTraceToTestRequirement.getSourceTraceElement().add(testRequirementElement);
		
		// Create missformated trace model
		testWrongTraceFile = project.getFile(TEST_WRONG_TRACE_MODEL);
		testWrongTraceResource = testResSet
				.createResource(URI.createPlatformResourceURI(testWrongTraceFile.getFullPath().toString(), true));
		testWrongTraceResource.getContents().add(TMFactory.eINSTANCE.createTraceElement());

	}

	@After
	public void tearDown() throws Exception {
		project.delete(true, null);
		resourceRequirements.delete(null);
	}

	@Test
	public void testGetTraceModelURI() {

		URI result = TraceHelper.getTraceModelURI(reqURI);

		assertEquals("URI transformation correct", traceModelURI, result);
	}

	@Test
	public void testGetIResourceValueWithoutResource() {

		IResource resource = TraceHelper.getIResourceValue(testTraceToTestRequirement);

		assertEquals("Non-persistet element must not have IResource", null, resource);

	}

	@Test
	public void testGetIResourceValueWithResource() throws IOException {

		IResource resource = TraceHelper.getIResourceValue(testRequirementElement);

		IResource expectedIResource = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(fileRequirements.getFullPath());

		assertNotNull("Persistet element must have an IResource", resource);
		assertEquals("Persistet element must have correct IResource", expectedIResource, resource);

	}

	@Test
	public void testIsTraceModel() {

		IFile fileTraceModel = project.getFile(TEST_TRACE_MODEL);
		URI uriFileTraceModel = URI.createPlatformResourceURI(fileTraceModel.getFullPath().toString(), true);

		IFile fileRequirements = project.getFile(TEST_REQUIREMENTS_MODEL);
		URI uriFileRequirements = URI.createPlatformResourceURI(fileRequirements.getFullPath().toString(), true);

		assertEquals(true, TraceHelper.isTraceModel(fileTraceModel));
		assertEquals(true, TraceHelper.isTraceModel(uriFileTraceModel));
		assertEquals(false, TraceHelper.isTraceModel(fileRequirements));
		assertEquals(false, TraceHelper.isTraceModel(uriFileRequirements));
		assertEquals(false, TraceHelper.isTraceModel((URI) null));
		assertEquals(false, TraceHelper.isTraceModel((IFile) null));

		
	}

	@Test
	public void testPersistTraceElement() {

		TraceHelper.persistTraceElement(testTraceToTestRequirement);

		assertNotNull("Resource attribute is set", testTraceToTestRequirement.eResource());

		fileRequirements = project.getFile(TEST_TRACE_MODEL);
		IResource traceIResource = ResourcesPlugin.getWorkspace().getRoot().findMember(fileRequirements.getFullPath());

		assertNotNull("Trace model exists as eclipse resource", traceIResource);

		// Check if second link to requirement is persisted in same file
		TraceElement secondTraceToTestRequirement = TMFactory.eINSTANCE.createTraceElement();
		secondTraceToTestRequirement.getSourceTraceElement().add(testRequirementElement);
		TraceHelper.persistTraceElement(secondTraceToTestRequirement);

		assertNotNull("Resource attribute of second trace is set", secondTraceToTestRequirement.eResource());
		assertEquals("Resource of both traces to the same requirement are the same",
				testTraceToTestRequirement.eResource(), secondTraceToTestRequirement.eResource());
	}

	@Test
	public void testGetTraceModel() throws IOException, CoreException {

		TraceHelper.persistTraceElement(testTraceToTestRequirement);

		//Get trace model from requirements model resource
		TraceabilityLinkContainer traceModelRootFromRequirement = TraceHelper.getTraceModel(resourceRequirements);
		assertEquals("Gained trace model root is in same file as the requirements trace element",
				testTraceToTestRequirement.eResource().getURI(), traceModelRootFromRequirement.eResource().getURI());
		
		//Get trace model from trace model resource
		EObject traceModelRootFromTraceResource = TraceHelper.getTraceModel(traceModelRootFromRequirement.eResource());
		assertEquals("Resource parameter can also be a trace resource",
				testTraceToTestRequirement.eResource().getURI(), traceModelRootFromTraceResource.eResource().getURI());
		
		TraceHelper.getIResourceValue(traceModelRootFromTraceResource).delete(true, null);
		assertNull("Return value for non-existing should be null", TraceHelper.getTraceModel(traceModelRootFromRequirement.eResource()));
		assertNull("Return value for wrong formated trace model should be null", TraceHelper.getTraceModel(testWrongTraceResource));
	}
	
	@Test
	public void testProtectedConstructor() {
		
		TraceHelper testHelper = new TraceHelperTestExtension();
		
		assertNotNull(testHelper);
		
	}

}
