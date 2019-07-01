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
package de.dlr.sc.virsat.requirements.tracing.validation.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.rmf.reqif10.ReqIF10Factory;
import org.eclipse.rmf.reqif10.SpecHierarchy;
import org.eclipse.rmf.reqif10.SpecObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.test.AProjectTestCase;
import de.dlr.sc.virsat.requirements.tracing.traceModel.TMFactory;
import de.dlr.sc.virsat.requirements.tracing.traceModel.TraceElement;
import de.dlr.sc.virsat.requirements.tracing.util.TraceHelper;
import de.dlr.sc.virsat.requirements.tracing.validation.IHistoryBased;
import de.dlr.sc.virsat.requirements.tracing.validation.IValidationEngine;
import de.dlr.sc.virsat.requirements.tracing.validation.engines.InspectionWarningEngine;

/**
 * @author fran_tb
 *
 */
public class InspectionWarningEngineTest extends AProjectTestCase {

	public static final String TEST_PROJECT = "testBaseProject";
	private static final String ENGINE_NAME = "Inspection";

	IFile fileRequirements;
	Resource resourceRequirements;

	SpecObject testTracedRequirementElement;
	SpecHierarchy testTracedRequirement;
	TraceElement traceElement;
	StructuralElementInstance targetStructuralElementInstance;

	IValidationEngine testEngine;

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();

		testTracedRequirementElement = ReqIF10Factory.eINSTANCE.createSpecObject();
		testTracedRequirementElement.setIdentifier(UUID.randomUUID().toString());
		testTracedRequirement = ReqIF10Factory.eINSTANCE.createSpecHierarchy();
		testTracedRequirement.setObject(testTracedRequirementElement);
		traceElement = TMFactory.eINSTANCE.createTraceElement();
		targetStructuralElementInstance = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		traceElement.getSourceTraceElement().add(testTracedRequirement);
		traceElement.getTargetTraceElement().add(targetStructuralElementInstance);

		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				repository.getRootEntities().add(targetStructuralElementInstance);
				VirSatResourceSet.getResourceSet(testProject, false)
						.getAndAddStructuralElementInstanceResource(targetStructuralElementInstance);
			}
		});

		testEngine = new InspectionWarningEngine();

	}

	@After
	public void tearDown() throws CoreException {
		super.tearDown();
	}

	@Test
	public void testInspectionWithNullChange() throws CoreException {
		((IHistoryBased) testEngine).setChangedSpecObjects(null);
		IResource resource = TraceHelper.getIResourceValue(targetStructuralElementInstance);

		assertFalse("Without changes the return should be false", testEngine.validate(traceElement));
		assertEquals("There should not be any markers", 0, resource.findMarkers(IMarker.PROBLEM, true, 1).length);
	}

	@Test
	public void testInspectionWithNoChange() throws CoreException {
		((IHistoryBased) testEngine).setChangedSpecObjects(Collections.emptyList());
		IResource resource = TraceHelper.getIResourceValue(targetStructuralElementInstance);

		assertFalse("Without changes the return should be false", testEngine.validate(traceElement));
		assertEquals("There should not be any markers", 0, resource.findMarkers(IMarker.PROBLEM, true, 1).length);
	}
	
	@Test
	public void testInspectionWithChange() throws CoreException {
		List<SpecObject> changedObjects = new ArrayList<>();
		changedObjects.add(testTracedRequirementElement);
		((IHistoryBased) testEngine).setChangedSpecObjects(changedObjects);
		IResource resource = TraceHelper.getIResourceValue(targetStructuralElementInstance);

		assertTrue("With changes the return should be true", testEngine.validate(traceElement));
		assertEquals("There should be a marker now", 1, resource.findMarkers(IMarker.PROBLEM, true, 1).length);
	}

	@Test
	public void testEnginePropertySpecifications() {

		assertEquals(ENGINE_NAME, testEngine.getValidationEngineName());
		assertFalse(testEngine.canProvideSemantic());
		assertTrue(testEngine.canValidate(traceElement));

	}

}
