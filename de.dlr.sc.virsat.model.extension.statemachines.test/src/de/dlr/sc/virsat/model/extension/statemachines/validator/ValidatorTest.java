/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.statemachines.model.AllowsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;


/**
 * Test Case for Validator of State Machines
 * 
 * @author bell_er
 *
 */
public class ValidatorTest extends AConceptProjectTestCase {
	private static final String CONCEPT_ID_PS = de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId();
	private static final String CONCEPT_ID_STATE_MACHINES = de.dlr.sc.virsat.model.extension.statemachines.Activator.getPluginId();

	private Concept conceptPs;
	private Concept conceptStateMachines;

	private ConfigurationTree ct;
	private ElementConfiguration ecObc;
	private ElementConfiguration ecRw;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();

		repository = DVLMFactory.eINSTANCE.createRepository();
		VirSatResourceSet resourceSet = VirSatResourceSet.createUnmanagedResourceSet(testProject);
		resourceSet.getRepositoryResource().getContents().add(repository);

		conceptPs = loadConceptFromPlugin(CONCEPT_ID_PS);
		conceptStateMachines = loadConceptFromPlugin(CONCEPT_ID_STATE_MACHINES);
		repository.getActiveConcepts().add(conceptPs);
		repository.getActiveConcepts().add(conceptStateMachines);
		
		ActiveConceptHelper.getCategory(conceptStateMachines, StateMachine.class.getSimpleName()).setIsApplicableForAll(true);

		// Here we start to create the Test Model
		ecObc = new ElementConfiguration(conceptPs);
		ecObc.setName("EC_OBC");

		ecRw = new ElementConfiguration(conceptPs);
		ecRw.setName("EC_RW");

		ct = new ConfigurationTree(conceptPs);
		ct.setName("CONF_TREE");
		ct.add(ecObc);

		// And finally we use the project commons to create the correct workspace paths
		// for the individual resources of the SEIs. We still have to attach the SEI eObjects
		// to their EMF Resources and save them
		Resource resCt = resourceSet.getStructuralElementInstanceResource(ct.getStructuralElementInstance());
		Resource resObc = resourceSet.getStructuralElementInstanceResource(ecObc.getStructuralElementInstance());
		Resource resRw = resourceSet.getStructuralElementInstanceResource(ecRw.getStructuralElementInstance());

		resCt.getContents().add(ct.getStructuralElementInstance());
		resObc.getContents().add(ecObc.getStructuralElementInstance());
		resRw.getContents().add(ecRw.getStructuralElementInstance());
		resourceSet.saveAllResources(null);
	}
	
	@After
	public void tearDown() throws CoreException {
		super.tearDown();
		UserRegistry.getInstance().setSuperUser(false);
	}

	@Test
	public void testValidator() throws Exception {
		StateMachine sm  = new StateMachine(conceptStateMachines);
		StatemachinesValidator seiValidator = new StatemachinesValidator();
		
		State s1 = new State(conceptStateMachines);
		State s2 = new State(conceptStateMachines);
		Transition t1 = new Transition(conceptStateMachines);
		Transition t2 = new Transition(conceptStateMachines);
		t1.setStateFrom(s1);
		t1.setStateTo(s2);
		s1.setName("s1");
		s2.setName("s2");
		
		sm.getStates().add(s2);
		sm.getStates().add(s1);
		sm.getTransitions().add(t1);
		ecObc.add(sm);
		
		assertFalse("InitialState is not selected", seiValidator.validate(ecObc.getStructuralElementInstance()));
	
		sm.setInitialState(s2);
		assertTrue("Everthing is okay", seiValidator.validate(ecObc.getStructuralElementInstance()));
		
		sm.getTransitions().add(t2);
		
		assertFalse("Transition states are not set", seiValidator.validate(ecObc.getStructuralElementInstance()));
		sm.getTransitions().remove(t2);
		
		s1.setName("s2");

		assertFalse("Same state Names", seiValidator.validate(ecObc.getStructuralElementInstance()));
	
		AllowsConstraint ac = new AllowsConstraint(conceptStateMachines);
		sm.getConstraints().add(ac);
		
		assertFalse("AllowsConstraint states are not set", seiValidator.validate(ecObc.getStructuralElementInstance()));
		
		ac.setStateInfluenced(s1);
		ac.setStateConstraining(s1);
		
		assertFalse("AllowsConstraint states are from the same state machine", seiValidator.validate(ecObc.getStructuralElementInstance()));
	}
}
