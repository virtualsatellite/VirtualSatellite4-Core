/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain.commands.dnd;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.ReplaceCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * This is a helper class to create the correct command for replacing an inheritance
 * of a SEI with another SEI.
 */
public class VirSatDragAndDropInheritanceCommandHelper {

	private VirSatDragAndDropInheritanceCommandHelper() {
	}
	
	public enum DndOperation {
		REPLACE_INHERITANCE,
		ADD_INHERITANCE
	}
	
	/**
	 * Call this method to create a command to add or replace the SEI in the list of superSEIs
	 * @param ed The editing domain for which the command should be created
	 * @param dragObject the object which is dragged and shall be used for replacing
	 * @param dropOperation the kind of operation
	 * @param dropObject the SEI where the superSEIs should be changed
	 * @return The command to add or replace a superSEI
	 */
	public static Command createDropCommand(EditingDomain ed, Collection<Object> dragObjects, DndOperation dropOperation, Object dropObject) {
		Object dragObject = dragObjects.iterator().next();
		if (dragObject instanceof StructuralElementInstance && dropObject instanceof StructuralElementInstance) {
			StructuralElementInstance dragSei = (StructuralElementInstance) dragObject;
			StructuralElementInstance dropSei = (StructuralElementInstance) dropObject;

			// Get type of drop object
			StructuralElement dragSeType = dragSei.getType();

			switch (dropOperation) {
				case REPLACE_INHERITANCE:
					// When replacing the rule is as follows:
					// 1. Identify the type of the object to be dropped as new type
					// 2. See if there is an inheritance to a SEI of the identified type.
					// 3. If yes replace it.
					// 4. If not than add the drop object as new inheritance.
					
					// try to identify Type of drop object in the given list of superSeis.
					for (StructuralElementInstance dropSuperSei : dropSei.getSuperSeis()) {
						if (dropSuperSei.getType().equals(dragSeType)) {
							Command replaceCommand = ReplaceCommand.create(
								ed,
								dropSei,
								InheritancePackage.eINSTANCE.getIInheritsFrom_SuperSeis(),
								dropSuperSei,
								Collections.singleton(dragSei)
							);
							return replaceCommand;
						}
					}
				// if there is no SEI found that can be replaced continue with adding the dependency
				case ADD_INHERITANCE:
					// None found, then add it
					Command addCommand = AddCommand.create(
						ed,
						dropSei,
						InheritancePackage.Literals.IINHERITS_FROM__SUPER_SEIS,
						Collections.singleton(dragSei)
					);
					return addCommand;
				default:
					break;
			}
		} 
		return UnexecutableCommand.INSTANCE;
	}	
}
