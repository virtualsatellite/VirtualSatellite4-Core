/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.statespace;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.statemachines.Activator;
import de.dlr.sc.virsat.model.extension.statemachines.model.AllowsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.ForbidsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;

public class StateSpaceExplorerTest extends AConceptProjectTestCase {
	
	private abstract class StateMachineGen {
		final StateMachine stateMachine;
		
		StateMachineGen(String name) {
			stateMachine = new StateMachine(concept);
			stateMachine.setName(name);
		}
		
		public StateMachine getStateMachine() {
			return stateMachine;
		}
		
		protected State state(String name) {
			var s = new State(concept);
			s.setName(name);
			stateMachine.getStates().add(s);
			return s;
		}
		
		protected State initialState(String name) {
			var s = state(name);
			stateMachine.setInitialState(s);
			return s;
		}
		
		/**
		 * Add a transition to the state machine.
		 */
		protected Transition trans(State from, String name, State to) {
			var t = new Transition(concept);
			t.setName(name);
			t.setStateFrom(from);
			t.setStateTo(to);
			stateMachine.getTransitions().add(t);
			return t;
		}
		
		protected ForbidsConstraint forbids(State from, State to) {
			var c = new ForbidsConstraint(concept);
			c.setStateConstraining(from);
			c.setStateInfluenced(to);
			stateMachine.getConstraints().add(c);
			return c;
		}
		
		protected AllowsConstraint requires(State from, State to) {
			var c = new AllowsConstraint(concept);
			c.setStateConstraining(from);
			c.setStateInfluenced(to);
			stateMachine.getConstraints().add(c);
			return c;
		}
	}
	
	private class SimpleStateMachine extends StateMachineGen {
		final State off;
		final State on;
		
		/**
		 * Create a simple state machine which can only be turned on or off.
		 */
		SimpleStateMachine(String name) {
			super(name);
			
			off = initialState(name + "Off");
			on  = state(name + "On");
			
			trans(off, "TurnOn", on);
			trans(on, "TurnOff", off);
		}
	}
	
	private class StandbyStateMachine extends StateMachineGen {
		final State off;
		final State standby;
		final State on;
		
		/**
		 * Create a state machine with three consecutive states.
		 */
		StandbyStateMachine(String name) {
			super(name);
			
			off = initialState(name + "Off");
			standby = state(name + "Standby");
			on = state(name + "On");
			
			trans(off, "IntoStandby", standby);
			trans(standby, "TurnOn", on);
			trans(on, "TurnOff", standby);
			trans(standby, "ShutOff", off);
		}
	}
	
	public static final String CONCEPT_ID_STATEMACHINES = Activator.getPluginId();
	
	private Concept concept;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		concept = loadConceptFromPlugin(CONCEPT_ID_STATEMACHINES);
	}
	
	@Test
	public void testGetInitialStates() {
		var first = new SimpleStateMachine("First");
		var second = new SimpleStateMachine("Second");
		var explorer = createStateSpaceExplorer(first, second);
		
		assertThat(explorer.getInitialStates().get(0).getLocalStates(), hasItems(first.off, second.off));
	}
	
	@Test
	public void testGetInitialStatesInvalid() {
		var first = new SimpleStateMachine("First");
		var second = new SimpleStateMachine("Second");
		first.forbids(first.off, second.off);
		
		var explorer = createStateSpaceExplorer(first, second);
		
		assertTrue(explorer.getInitialStates().isEmpty());
	}
	
	@Test
	public void testGetSuccessors() {
		var first = new SimpleStateMachine("First");
		var second = new SimpleStateMachine("Second");
		var explorer = createStateSpaceExplorer(first, second);
		
		var initState = explorer.getInitialStates().get(0);
		var succStates = explorer.getSuccessors(initState).stream().map(t -> t.getTo().getLocalStates()).collect(Collectors.toList());
		
		assertContainsState(succStates, first.on, second.off);
		assertContainsState(succStates, first.off, second.on);
	}
	
	@Test
	public void testGetReachableStates() {
		var first = new SimpleStateMachine("First");
		var second = new SimpleStateMachine("Second");
		var reach = getReachableStates(first, second);
		
		assertContainsState(reach, first.off, second.off);
		assertContainsState(reach, first.on, second.off);
		assertContainsState(reach, first.off, second.on);
		assertContainsState(reach, first.on, second.on);
	}
	
	@Test
	public void testForbidsConstraint() {
		var first = new SimpleStateMachine("First");
		var second = new SimpleStateMachine("Second");
		first.forbids(first.on, second.on);
		
		assertNotContainsState(getReachableStates(first, second), first.on, second.on);
	}
	
	@Test
	public void testSimpleRequiresConstraint() {
		var first = new SimpleStateMachine("First");
		var second = new SimpleStateMachine("Second");
		first.requires(first.on, second.on);
		
		var reach = getReachableStates(first, second);
		
		assertContainsState(reach, first.off, second.off);
		assertContainsState(reach, first.off, second.on);
		assertContainsState(reach, first.on, second.on);
		assertNotContainsState(reach, first.on, second.off);
	}
	
	@Test
	public void testDisjunctiveRequiresConstraint() {
		var first = new SimpleStateMachine("First");
		var second = new StandbyStateMachine("Second");
		first.requires(first.on, second.standby);
		first.requires(first.on, second.on);
		
		var reach = getReachableStates(first, second);
		
		assertContainsState(reach, first.on, second.standby);
		assertContainsState(reach, first.on, second.on);
		assertNotContainsState(reach, first.on, second.off);
	}
	
	@Test
	public void testConjunctiveRequiresConstraint() {
		var first = new SimpleStateMachine("First");
		var second = new SimpleStateMachine("Second");
		var third = new SimpleStateMachine("Third");
		first.requires(first.on, second.on);
		first.requires(first.on, third.on);
		
		var reach = getReachableStates(first, second, third);
		
		assertContainsState(reach, first.on, second.on, third.on);
		assertNotContainsState(reach, first.on, second.off);
		assertNotContainsState(reach, first.on, third.off);
	}
	
	@Test
	public void testSystemStateConsistency() {
		var first = new SimpleStateMachine("First");
		var second = new SimpleStateMachine("Second");
		
		var sseFirst = createStateSpaceExplorer(first);
		var sseSecond = createStateSpaceExplorer(second);
		
		var firstInit = sseFirst.getInitialStates().get(0);
		
		assertThrows(IllegalArgumentException.class, () -> sseSecond.getSuccessors(firstInit));
	}
	
	private static StateSpaceExplorer createStateSpaceExplorer(StateMachineGen... gens) {
		return new StateSpaceExplorer(Arrays.stream(gens).map(StateMachineGen::getStateMachine).collect(Collectors.toList()));
	}
	
	private static List<List<State>> getReachableStates(StateSpaceExplorer explorer) {
		return explorer.getReachableStates().stream().map(StateSpaceExplorer.SystemState::getLocalStates).collect(Collectors.toList());
	}
	
	private static List<List<State>> getReachableStates(StateMachineGen... gens) {
		return getReachableStates(createStateSpaceExplorer(gens));
	}
	
	private static void assertContainsState(List<List<State>> systemStates, State... localStates) {
		assertThat(systemStates, hasItem(hasItems(localStates)));
	}
	
	private static void assertNotContainsState(List<List<State>> systemStates, State... localStates) {
		assertThat(systemStates, not(hasItem(hasItems(localStates))));
	}
}
