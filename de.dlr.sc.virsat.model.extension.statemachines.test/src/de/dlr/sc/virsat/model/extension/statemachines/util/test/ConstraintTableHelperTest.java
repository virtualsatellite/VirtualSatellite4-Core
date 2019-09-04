/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.util.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCopier;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.statemachines.model.AllowsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.util.ConstraintTableHelper;
import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommand;
/**
 * Test Case for ConstraintTable
 * @author bell_er
 *
 */
public class ConstraintTableHelperTest extends AConceptProjectTestCase {
	private static final String CONCEPT_ID_STATE_MACHINES = de.dlr.sc.virsat.model.extension.statemachines.Activator.getPluginId();
	private static final String CONCEPT_ID_PS = de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId();
	
	private ElementConfiguration ec;
	private ConfigurationTree ct;	
	
	private ArrayList<StateMachine> sms;
	
	private Concept conceptPs;
	private Concept conceptStateMachines;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		UserRegistry.getInstance().setSuperUser(true);
		addEditingDomainAndRepository();
		
		conceptPs = loadConceptFromPlugin(CONCEPT_ID_PS);
		conceptStateMachines = loadConceptFromPlugin(CONCEPT_ID_STATE_MACHINES);
		
		// Create A configuration Tree And its Children
		ct = new ConfigurationTree(conceptPs);
		ec = new ElementConfiguration(conceptPs);
		ct.add(ec);
		StateMachine sm = new StateMachine(conceptStateMachines);
		sm.setName("StateMachine1");
		
		State smState1 = new State(conceptStateMachines);
		smState1.setName("smState1");
		State smState2 = new State(conceptStateMachines);
		smState2.setName("smState2");
		sm.getStates().add(smState2);
		sm.getStates().add(smState1);
		
		StateMachine sm2 = new StateMachine(conceptStateMachines);
		sm2.setName("StateMachine2");
		State sm2State1 = new State(conceptStateMachines);
		sm2State1.setName("sm2State1");
		State sm2State2 = new State(conceptStateMachines);
		sm2State2.setName("sm2State2");
		sm2.getStates().add(sm2State2);
		sm2.getStates().add(sm2State1);
		
		AllowsConstraint ac1 = new AllowsConstraint(conceptStateMachines);
		ac1.setStateConstraining(smState1);
		ac1.setStateInfluenced(sm2State1);
		sm.getConstraints().add(ac1);
		
		AllowsConstraint ac2 = new AllowsConstraint(conceptStateMachines);
		ac2.setStateConstraining(sm2State2);
		ac2.setStateInfluenced(smState2);
		sm2.getConstraints().add(ac2);
		
		CategoryAssignment tempStateMachineCategoryAssignment = DVLMCopier.makeCopyKeepUuids(sm.getTypeInstance());
		StateMachine tempStateMachine = new StateMachine(tempStateMachineCategoryAssignment);
		tempStateMachine.getConstraints().get(0).setStateConstraining(smState2);
		tempStateMachine.getConstraints().get(0).setStateInfluenced(sm2State2);
		
		CategoryAssignment tempStateMachine2CategoryAssignment = DVLMCopier.makeCopyKeepUuids(sm.getTypeInstance());
		StateMachine tempStateMachine2 = new StateMachine(tempStateMachine2CategoryAssignment);
		ec.add(sm);
		ec.add(sm2);

		sms = new ArrayList<>();
		sms.add(tempStateMachine);
		sms.add(tempStateMachine2);
	}

	@Override
	public void tearDown() throws CoreException {
		super.tearDown();
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	@Test
	public void testMergeCommand() throws CoreException  { 
		Command commandAddStructuralElementInstance = CreateAddSeiWithFileStructureCommand.create(editingDomain, repository, ct.getStructuralElementInstance());
		editingDomain.getCommandStack().execute(commandAddStructuralElementInstance);
		
		ConstraintTableHelper.createAndExecuteCompaundCommandForMerge(ec.getStructuralElementInstance(), sms);
		
		List<StateMachine>  newStateMachines = ec.getAll(StateMachine.class);
		
		for (StateMachine stateMachine : newStateMachines) {
			if (stateMachine.getName().equals("StateMachine1")) {
				AllowsConstraint ac = (AllowsConstraint) stateMachine.getConstraints().get(0);
				assertEquals(ac.getStateConstraining().getName(), "smState2");
				assertEquals(ac.getStateInfluenced().getName(), "sm2State2");
				
			} else {
				AllowsConstraint ac = (AllowsConstraint) stateMachine.getConstraints().get(0);
				assertEquals(ac.getStateConstraining().getName(), "sm2State2");
				assertEquals(ac.getStateInfluenced().getName(), "smState2");
			}
		}
	}
}
