/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.util;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.statemachines.model.AConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * Helper class for constraint table
 * @author bell_er
 *
 */
public class ConstraintTableHelper {
	
	/**
	 * Helper class for constraint table
	 * @author bell_er
	 *
	 */
	private ConstraintTableHelper() {
		
	}
	
	/**
	 * This method merges the temporary state machines which are generated through ConstraintTableWizard with the original 
	 * state machines in the data model
	 * @param mainRootSc the selected element
	 * @param tempStateMachines list of modified state machines
	 * @throws CoreException 
	 */
	public static void createAndExecuteCompaundCommandForMerge(StructuralElementInstance mainRootSc, List<StateMachine> tempStateMachines) throws CoreException {	
		BeanStructuralElementInstanceFactory bsf = new BeanStructuralElementInstanceFactory();
		ABeanStructuralElementInstance addedElement = null;
		
		addedElement = (ABeanStructuralElementInstance) bsf.getInstanceFor(mainRootSc);
		 
		VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(mainRootSc); 
		List<StateMachine> stateMachines =  addedElement.getAll(StateMachine.class);
		CompoundCommand cmd  = new CompoundCommand();
		for (StateMachine sm : stateMachines) {
			for (StateMachine tempSm : tempStateMachines) {
				if (sm.getUuid().equals(tempSm.getUuid())) {
					compareAndCreateCommands(sm, tempSm, ed, cmd);
				}
			}
		}
		ed.getCommandStack().execute(cmd);
	}

	/**
	 * The method compares the constraints on the temprorary statemachine with the state machine which is in the model. 
	 * For each deleted constraint it creates a delete command. For each newly added constraint it creates an add command
	 * @param sm original state machine
	 * @param tempSm temporary state machine
	 * @param ed the editing domain
	 * @param compoundCommand the big compound command
	 */
	private static void compareAndCreateCommands(StateMachine sm, StateMachine tempSm, VirSatTransactionalEditingDomain ed, CompoundCommand compoundCommand) {
		List<AConstraint> smConstraints = sm.getConstraints();
		List<AConstraint> tempSmConstraints = tempSm.getConstraints();
		for (AConstraint constraint : smConstraints) {
			if (!isContained(constraint, tempSmConstraints)) {
				Command cmd = sm.getConstraints().remove(ed, constraint);
				compoundCommand.append(cmd);
			}	
		}
		for (AConstraint tempConstraint : tempSmConstraints) {
			if (!isContained(tempConstraint, smConstraints)) {
				Command cmd = sm.getConstraints().add(ed, tempConstraint);
				compoundCommand.append(cmd);
			}			
		}
	}
	
	/**
	 * @param constraint1 first constraint
	 * @param constraint2 second constraint
	 * @return if they match or not
	 */
	public static boolean isMatching(AConstraint constraint1, AConstraint constraint2) {
		boolean result = constraint1.getStateConstraining().getUuid().equals(constraint2.getStateConstraining().getUuid()) && constraint1.getStateInfluenced().getUuid().equals(constraint2.getStateInfluenced().getUuid());
		return 	result;
	}
	
	/**
	 * @param ac the constraint
	 * @param list a list of constraints
	 * @return if ac is inside the list
	 */
	private static boolean isContained(AConstraint ac, List<AConstraint> list) {
		for (AConstraint listConstraint : list) {
			if (isMatching(listConstraint, ac)) {
				return true;
			}
		}
		return false;
	}
}
