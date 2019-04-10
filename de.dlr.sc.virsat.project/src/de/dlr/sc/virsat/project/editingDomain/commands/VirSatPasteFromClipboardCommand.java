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

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.StrictCompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.roles.RolesPackage;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.command.CreateAddStructuralElementInstanceCommand;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCopiedNameHelper;
import de.dlr.sc.virsat.model.dvlm.util.command.DVLMCopierCommand;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatEditingDomainClipBoard.ClipboardState;
import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommand;

/**
 * EMD Compatible Command, to Paste a copied DVLM Model Object to some other  
 * @author fisc_ph
 *
 */
public class VirSatPasteFromClipboardCommand  extends CompoundCommand {
	
	/**
	 * Static factory method to create the actual correct command
	 * @param ed The Editing Domain in which to create the command
	 * @param owner The owner where to paste the currently pending ClipBoard contents
	 * @return a command that will paste the items when executed
	 */
	public static Command create(AdapterFactoryEditingDomain ed, EObject owner) {
		Collection<?> collection = VirSatEditingDomainClipBoard.INSTANCE.getCollection(ed);
		ClipboardState state = VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(ed);
		return create(ed, owner, collection, state);
	}
	
	/**
	 * Static factory method to create the actual correct command
	 * @param ed The Editing Domain in which to create the command
	 * @param owner The owner where to paste the currently pending ClipBoard contents
	 * @param collection a collection of objects to be pasted to the owner
	 * @param state The ClipBoardState indicating if objects are copied or moved meaning cut or pasted
	 * @return a command that will paste the items when executed
	 */
	public static Command create(AdapterFactoryEditingDomain ed, EObject owner, Collection<?> collection, ClipboardState state) {
		EObject realOwner;
		
		// Detect if we currently selected a sibling CA and refer back to its SE as container / owner
		// So in case we select a CA in the editor, we press copy, and than we press paste,
		// the selected "owner" is still the CA, but actually the selected CA should inserted into the SEI 
		if (owner instanceof CategoryAssignment) {
			realOwner = ((CategoryAssignment) owner).getCategoryAssignmentContainer();
		} else if (owner instanceof APropertyInstance) {
			APropertyInstance pi = (APropertyInstance) owner;
			EObject container = pi.eContainer();
			if (container instanceof ArrayInstance) {
				realOwner = container;
			} else {
				// ÄFFÄKTÄVLI FEINÄL
				realOwner = owner;
			}
		} else {
			realOwner = owner;
		}
		
		// Now after defining the correct owner, build up the command for pasting
		VirSatPasteFromClipboardCommand pasteCommand = new VirSatPasteFromClipboardCommand(ed);
		boolean isValidOwner = VirSatClipboardCommandHelper.isValidTarget(realOwner);
		boolean isValidCollection = VirSatClipboardCommandHelper.isValidCollection(collection);
	
		// The CUT & Paste Section
		if (isValidCollection && isValidOwner && state == (ClipboardState.CUT)) {
			if (realOwner instanceof StructuralElementInstance) {
				if (VirSatClipboardCommandHelper.isCollectionSei(collection)) {
					pasteCommand.append(VirSatCutToClipboardCommand.createRemoveAllDVLMObjects(ed, collection));
					pasteCommand.append(CreateAddStructuralElementInstanceCommand.create(ed, realOwner, collection));
				} else if (VirSatClipboardCommandHelper.isCollectionCategoryAssignment(collection)) {
					pasteCommand.append(VirSatCutToClipboardCommand.createRemoveAllDVLMObjects(ed, collection));
					pasteCommand.append(AddCommand.create(ed, realOwner, CategoriesPackage.Literals.ICATEGORY_ASSIGNMENT_CONTAINER__CATEGORY_ASSIGNMENTS, collection));
				}
			} else if (realOwner instanceof Repository) {
				if (VirSatClipboardCommandHelper.isCollectionSei(collection)) {
					pasteCommand.append(VirSatCutToClipboardCommand.createRemoveAllDVLMObjects(ed, collection));
					pasteCommand.append(CreateAddStructuralElementInstanceCommand.create(ed, realOwner, collection));
				}
			} else if (realOwner instanceof RoleManagement) {
				if (VirSatClipboardCommandHelper.isCollectionDiscipline(collection)) {
					pasteCommand.append(VirSatCutToClipboardCommand.createRemoveAllDVLMObjects(ed, collection));
					pasteCommand.append(AddCommand.create(ed, realOwner, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, collection));
				}
			} else if (realOwner instanceof ArrayInstance) {
				if (VirSatClipboardCommandHelper.isCollectionPi(collection)) {
					pasteCommand.append(VirSatCutToClipboardCommand.createRemoveAllDVLMObjects(ed, collection));
					pasteCommand.append(AddCommand.create(ed, realOwner, PropertyinstancesPackage.Literals.ARRAY_INSTANCE__ARRAY_INSTANCES, collection));
				}
			} 
			return pasteCommand;
		}
		
		// The Copy and Paste Section
		
		if (isValidCollection && isValidOwner && state == (ClipboardState.COPY)) {
			// The Strict Compound Command is needed to first execute the Copy before the
			// paste of the copied objects is executed. The Strict Compound command is explicitly
			// designed for this purpose
			StrictCompoundCommand copyAndAppendCopiedCommand = new StrictCompoundCommand();
			Command copyCommand = new DVLMCopierCommand(collection);
			copyAndAppendCopiedCommand.append(copyCommand);
			copyAndAppendCopiedCommand.append(new CommandWrapper() {
				@Override
				protected Command createCommand() {
					Collection<?> copiedCollection = copyCommand.getResult();
					changeDisciplineByCopy(realOwner, copiedCollection);
					DVLMCopiedNameHelper dcnh = new DVLMCopiedNameHelper();
					if (realOwner instanceof StructuralElementInstance) {
						if (VirSatClipboardCommandHelper.isCollectionSei(copiedCollection)) {
							dcnh.updateCopiedNames(((StructuralElementInstance) realOwner).getChildren(), copiedCollection);
							return CreateAddSeiWithFileStructureCommand.create(ed, realOwner, copiedCollection);
						} else if (VirSatClipboardCommandHelper.isCollectionCategoryAssignment(copiedCollection)) {
							dcnh.updateCopiedNames(((StructuralElementInstance) realOwner).getCategoryAssignments(), copiedCollection);
							return AddCommand.create(ed, realOwner, CategoriesPackage.Literals.ICATEGORY_ASSIGNMENT_CONTAINER__CATEGORY_ASSIGNMENTS, copiedCollection);
						}
					} else if (realOwner instanceof Repository) {
						if (VirSatClipboardCommandHelper.isCollectionSei(copiedCollection)) {
							dcnh.updateCopiedNames(((Repository) realOwner).getRootEntities(), copiedCollection);
							return CreateAddSeiWithFileStructureCommand.create(ed, realOwner, copiedCollection);
						}
					} else if (realOwner instanceof RoleManagement) {
						if (VirSatClipboardCommandHelper.isCollectionDiscipline(copiedCollection)) {
							dcnh.updateCopiedNames(((RoleManagement) realOwner).getDisciplines(), copiedCollection);
							return AddCommand.create(ed, realOwner, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, copiedCollection);
						}
					} else if (realOwner instanceof ArrayInstance) {
						if (VirSatClipboardCommandHelper.isCollectionPi(copiedCollection)) {
							return AddCommand.create(ed, realOwner, PropertyinstancesPackage.Literals.ARRAY_INSTANCE__ARRAY_INSTANCES, copiedCollection);
						}
					} 
					return UnexecutableCommand.INSTANCE;
				}
			});
			pasteCommand.append(copyAndAppendCopiedCommand);
			return pasteCommand;
		} 
		
		pasteCommand.append(UnexecutableCommand.INSTANCE);
		return pasteCommand;
	}
	
	private AdapterFactoryEditingDomain ed;
	
	/**
	 * Private constructor of the command that allows to set the ED for
	 * accessing the correct ClipBoard when the command is executed
	 * @param ed The editing domain from which this command will use the ClipBoard
	 */
	private VirSatPasteFromClipboardCommand(AdapterFactoryEditingDomain ed) {
		this.ed = ed;
	}
	
	@Override
	public void execute() {
		// Call the canExecute first, since it does some important preparations
		// of the command in particular with the wrapped commands. If not called,
		// before execution the command will fail to work as expected.
		if (canExecute()) {
			super.execute();
	
			// Clear the ClipBoard after a CUT and PASTE action
			// Don't clear it for a copy to allow for multiple copies
			ClipboardState state = VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(ed);
			if (state == ClipboardState.CUT) {
				VirSatEditingDomainClipBoard.INSTANCE.flushClipboard(ed);
			}
		}
	}
	
	/**
	 * Private method that check which discipline the object has into which we are pasting and set it in the Copy.
	 * @param realOwner the object into which we are pasting
	 * @param copiedCollection copied Objects
	 */
	private static void changeDisciplineByCopy(EObject realOwner, Collection<?> copiedCollection) {
		if (realOwner instanceof IAssignedDiscipline) {
			IAssignedDiscipline assignedDiscipline = (IAssignedDiscipline) realOwner;
			Discipline discipline = assignedDiscipline.getAssignedDiscipline();
			for (Object object : copiedCollection) {
				if (object instanceof IAssignedDiscipline) {
					IAssignedDiscipline copiedAssignedDiscipline = (IAssignedDiscipline) object;
					copiedAssignedDiscipline.setAssignedDiscipline(discipline);
				}
			}
		}
	}
	
	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		super.undo();
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}
}
