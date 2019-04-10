/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain.commands;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RolesPackage;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * Command to place Cut Objects to the Editing Domain ClipBoard
 * @author fisc_ph
 *
 */
public class VirSatCutToClipboardCommand extends AbstractCommand {

	/**
	 * This factory method creates all relevant remove commands on a list of DVLM objects. The removes follow the strategy that is
	 * intended with the Cut and Paste Actions provided by VirSat. All remove commands are appended into a Compound Command
	 * @param ed The Editing Domain to be used to create the remove commands
	 * @param collection the collection of DVLM objects to be removed by a CUT and PASTE action
	 * @return a Compound Command with all Remove Commands
	 */
	protected static CompoundCommand createRemoveAllDVLMObjects(AdapterFactoryEditingDomain ed, Collection<?> collection) {
		CompoundCommand removeAllSeiCommand = new CompoundCommand();
		collection.forEach((obj) -> {
			if (obj instanceof StructuralElementInstance) {
				StructuralElementInstance sei = (StructuralElementInstance) obj;
				EObject eContainer = sei.eContainer();
				if (eContainer != null) {
					removeAllSeiCommand.append(RemoveCommand.create(ed, eContainer, StructuralPackage.Literals.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN, sei));
				} else {
					Repository repo = ((VirSatTransactionalEditingDomain) ed).getResourceSet().getRepository();
					if (repo.getRootEntities().contains(sei)) { 
						removeAllSeiCommand.append(RemoveCommand.create(ed, repo, DVLMPackage.Literals.REPOSITORY__ROOT_ENTITIES, sei));
					}
				}
			} else if (obj instanceof CategoryAssignment) {
				CategoryAssignment ca = (CategoryAssignment) obj;
				EObject eContainer = ca.eContainer();
				if (eContainer != null) {
					removeAllSeiCommand.append(RemoveCommand.create(ed, eContainer, CategoriesPackage.Literals.ICATEGORY_ASSIGNMENT_CONTAINER__CATEGORY_ASSIGNMENTS, ca));
				}
			} else if (obj instanceof APropertyInstance) {
				APropertyInstance pi = (APropertyInstance) obj;
				EObject eContainer = pi.eContainer();
				if (eContainer instanceof ArrayInstance) {
					removeAllSeiCommand.append(RemoveCommand.create(ed, eContainer, PropertyinstancesPackage.Literals.ARRAY_INSTANCE__ARRAY_INSTANCES, pi));
				}
			} else if (obj instanceof Discipline) {
				Discipline disc = (Discipline) obj;
				EObject eContainer = disc.eContainer();
				removeAllSeiCommand.append(RemoveCommand.create(ed, eContainer, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, disc));
			}
		});
		
		return removeAllSeiCommand;
	}
	
	
	/**
	 * Factory Method to create a Cut Command
	 * @param ed The editing domain to which to place the cutted objects
	 * @param collection the collection of objects to be put into the clipboard
	 * @return The command to place the objects or an UnexecutableCommand
	 */
	public static Command create(AdapterFactoryEditingDomain ed, Collection<?> collection) {
		if (VirSatClipboardCommandHelper.isValidCollection(collection)) {
			return new VirSatCutToClipboardCommand(ed, collection);
		} else {
			return UnexecutableCommand.INSTANCE;
		}
	}
	
	private AdapterFactoryEditingDomain ed;
	private Collection<?> collection;
	
	/**
	 * Hidden Constructor to create the Cut Command
	 * @param ed The Editing to which to cut the objects to
	 * @param collection The collection of objects to be placed to the ED
	 */
	private VirSatCutToClipboardCommand(AdapterFactoryEditingDomain ed, Collection<?> collection) {
		this.ed = ed;
		this.collection = new ArrayList<>(collection);
	}
	
	@Override
	public void execute() {
		VirSatEditingDomainClipBoard.INSTANCE.cutClipboard(ed, collection);
	}

	@Override
	protected boolean prepare() {
		return true;
	}
	
	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		VirSatEditingDomainClipBoard.INSTANCE.flushClipboard(ed);
	}

	@Override
	public boolean canExecute() {
		Command cmd = createRemoveAllDVLMObjects(ed, collection);
		return super.canExecute() && cmd.canExecute();
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}
}
