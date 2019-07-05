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
package de.dlr.sc.virsat.requirements.tracing.builder;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.rmf.reqif10.ReqIF10Factory;
import org.eclipse.rmf.reqif10.ReqIFContent;
import org.eclipse.rmf.reqif10.SpecHierarchy;
import org.eclipse.rmf.reqif10.SpecObject;
import org.eclipse.rmf.reqif10.Specification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.test.AProjectTestCase;
import de.dlr.sc.virsat.requirements.tracing.builder.test.mockup.TestValidationEngine;
import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TMFactory;
import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement;
import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceabilityLinkContainer;

/**
 * @author fran_tb
 *
 */
public class TraceBuilderTest extends AProjectTestCase {

	public static final String TEST_PROJECT = "testBaseProject";
	public static final String TEST_REQUIREMENTS_MODEL = "TestReqs.reqif";
	public static final String TEST_TRACE_MODEL = "TestReqs.tm";
	public static final String REQUIREMENTS_ELEMENT_NAME = "ReqElement";
	public static final String REQUIREMENTS_ELEMENT_CHANGED_NAME = "ReqElement_Changed1";

	protected TraceNature nature;

	IFile fileRequirements;
	Resource resourceRequirements;
	IFile fileRequirementsTrace;
	Resource resourceRequirementsTrace;

	SpecObject testTracedRequirementElement;
	SpecHierarchy testTracedRequirement;
	TraceElement traceElement;
	StructuralElementInstance targetStructuralElementInstance;
	VirSatResourceSet resSetRepository;

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		resSetRepository = VirSatResourceSet.getResourceSet(testProject, false);
		nature = new TraceNature();
		nature.setProject(testProject);
		nature.configure();

		setupRequirementsModel();
		setupTestTrace();

		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				repository.getRootEntities().add(targetStructuralElementInstance);
				VirSatResourceSet.getResourceSet(testProject, false)
						.getAndAddStructuralElementInstanceResource(targetStructuralElementInstance);
			}
		});
		editingDomain.saveAll();

	}

	@After
	public void tearDown() throws CoreException {
		super.tearDown();
	}

	@Test
	public void testFullBuildWithoutChange() throws CoreException {

		TestValidationEngine.getTraceTargets().clear();
		TestValidationEngine.getChangedRequirementsElements().clear();
		
		testProject.build(IncrementalProjectBuilder.FULL_BUILD, TraceNature.BUILDER_TRACE_ID, null, null);
		testProject.build(IncrementalProjectBuilder.FULL_BUILD, TraceNature.BUILDER_TRACE_ID, null, null);

		List<EObject> traceTargets = TestValidationEngine.getTraceTargets();
		List<EObject> changedFiles = TestValidationEngine.getChangedRequirementsElements();
		EObject testTarget = traceTargets.get(0);
		EObject resolvedTarget = null;
		if (testTarget.eIsProxy()) {
			resolvedTarget = EcoreUtil.resolve(testTarget, repository);
		} else {
			resolvedTarget = testTarget;
		}
		assertTrue("Trace target not idenfied as target", ((StructuralElementInstance) resolvedTarget).getUuid()
				.equals(targetStructuralElementInstance.getUuid()));
		assertTrue("Wrong requirement idenfied as changed", changedFiles.size() == 0);
	}
	

	@Test
	public void testIncrementalBuildWithoutChange() throws CoreException, IOException {

		TestValidationEngine.getTraceTargets().clear();
		TestValidationEngine.getChangedRequirementsElements().clear();
		
		testProject.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, TraceNature.BUILDER_TRACE_ID, null, null);

		List<EObject> traceTargets = TestValidationEngine.getTraceTargets();
		List<EObject> changedFiles = TestValidationEngine.getChangedRequirementsElements();
		EObject testTarget = traceTargets.get(0);
		EObject resolvedTarget = null;
		if (testTarget.eIsProxy()) {
			resolvedTarget = EcoreUtil.resolve(testTarget, repository);
		} else {
			resolvedTarget = testTarget;
		}
		assertTrue("Trace target not idenfied as target", ((StructuralElementInstance) resolvedTarget).getUuid()
				.equals(targetStructuralElementInstance.getUuid()));
		assertTrue("Wrong requirement idenfied as changed", changedFiles.size() == 0);
	}
	
	@Test
	public void testIncrementalBuildWithChange() throws CoreException, IOException {

		TestValidationEngine.getTraceTargets().clear();
		TestValidationEngine.getChangedRequirementsElements().clear();
		
		testProject.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, TraceNature.BUILDER_TRACE_ID, null, null);
		
		//Do some modification
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				testTracedRequirementElement.setLongName(REQUIREMENTS_ELEMENT_CHANGED_NAME);
			}
		});
		testTracedRequirementElement.eResource().setModified(true);
		testTracedRequirementElement.eResource().save(Collections.EMPTY_MAP);
		
		testProject.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, TraceNature.BUILDER_TRACE_ID, null, null);

		List<EObject> traceTargets = TestValidationEngine.getTraceTargets();
		List<EObject> changedFiles = TestValidationEngine.getChangedRequirementsElements();
		EObject testTarget = traceTargets.get(0);
		EObject resolvedTarget = null;
		if (testTarget.eIsProxy()) {
			resolvedTarget = EcoreUtil.resolve(testTarget, repository);
		} else {
			resolvedTarget = testTarget;
		}
		assertTrue("Trace target not idenfied as target", ((StructuralElementInstance) resolvedTarget).getUuid()
				.equals(targetStructuralElementInstance.getUuid()));
		assertTrue("Requirement not idenfied as changed", testTracedRequirementElement.getIdentifier()
				.equals(((SpecObject) changedFiles.get(0)).getIdentifier()));
	}


	/**
	 * Set up a simple requirement model
	 */
	protected void setupRequirementsModel() {
		
		ReqIFContent modelRoot = ReqIF10Factory.eINSTANCE.createReqIFContent();
		Specification spec = ReqIF10Factory.eINSTANCE.createSpecification();
		modelRoot.getSpecifications().add(spec);
		
		
		testTracedRequirementElement = ReqIF10Factory.eINSTANCE.createSpecObject();
		testTracedRequirementElement.setIdentifier(UUID.randomUUID().toString());
		testTracedRequirementElement.setLongName(REQUIREMENTS_ELEMENT_NAME);
		testTracedRequirement = ReqIF10Factory.eINSTANCE.createSpecHierarchy();
		testTracedRequirement.setObject(testTracedRequirementElement);

		fileRequirements = testProject.getFile(TEST_REQUIREMENTS_MODEL);
		resourceRequirements = resSetRepository
				.createResource(URI.createPlatformResourceURI(fileRequirements.getFullPath().toString(), true));
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				spec.getChildren().add(testTracedRequirement);
				modelRoot.getSpecObjects().add(testTracedRequirementElement);
				resourceRequirements.getContents().add(modelRoot);
			}
		});
	}

	/**
	 * Set up a trace from requirements to test elements with a test vaildation
	 * engine configured
	 */
	protected void setupTestTrace() {
		TraceabilityLinkContainer container = TMFactory.eINSTANCE.createTraceabilityLinkContainer();

		traceElement = TMFactory.eINSTANCE.createTraceElement();
		targetStructuralElementInstance = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		traceElement.getSourceTraceElement().add(testTracedRequirement);
		traceElement.getTargetTraceElement().add(targetStructuralElementInstance);
		traceElement.setValidationEngineName(TestValidationEngine.ENGINE_NAME);
		container.getTraceElements().add(traceElement);

		fileRequirementsTrace = testProject.getFile(TEST_TRACE_MODEL);
		resourceRequirementsTrace = resSetRepository
				.createResource(URI.createPlatformResourceURI(fileRequirementsTrace.getFullPath().toString(), true));
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				resourceRequirementsTrace.getContents().add(container);
			}
		});
	}

}
