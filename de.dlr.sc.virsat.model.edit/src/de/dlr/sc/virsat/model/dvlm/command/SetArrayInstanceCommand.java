/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.command;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.util.DVLMReferenceCheck;

/**
 * This class sets the value of a an entry in the given array instance.
 * 
 * For example in the case of an array of references the the reference 
 * will be set to the initObject.
 * @author muel_s8
 *
 */

public class SetArrayInstanceCommand {

	/**
	 * Hidden constructor
	 */
	private SetArrayInstanceCommand() {
	
	}
	
	/**
	 * This creates the set command
	 * @param editingDomain the editing domain
	 * @param ai the array instance
	 * @param index the index of the to be initialized array instance entry
	 * @param value the set data
	 * @return a command for initializing the newest array instance using the initObject data, null if nothing to be initialized
	 * 			
	 */
	
	public static Command create(EditingDomain editingDomain, ArrayInstance ai, int index, Object value) {
	
		ATypeDefinition td = ai.getType();
		if (td instanceof ReferenceProperty) {
			if (!DVLMReferenceCheck.isValid((ReferenceProperty) td, value)) {
				return UnexecutableCommand.INSTANCE;
			}
			
			AbstractCommand setReferenceCmd = new AbstractCommand() {
				@Override
				public void execute() {
					// grab the added instance so we can set its reference
					ReferencePropertyInstance rpi = (ReferencePropertyInstance) ai.getArrayInstances().get(index);
					rpi.setReference((ATypeInstance) value);
				}

				@Override
				public void redo() {
					execute();
				}
				
				public void undo() {
					
				}
				
				public boolean canExecute() {
					return true;
				}
			};
			
			return setReferenceCmd;
		} 
		
		return null;
	}
	
}
