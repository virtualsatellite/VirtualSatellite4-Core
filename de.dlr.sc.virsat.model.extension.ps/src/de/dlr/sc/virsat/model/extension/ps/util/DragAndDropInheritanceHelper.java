package de.dlr.sc.virsat.model.extension.ps.util;

import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.ReplaceCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;

public class DragAndDropInheritanceHelper {

	public enum DndOperation {
		REPLACE_INHERITANCE,
		ADD_INHERITANCE,
		CREATE_SEI_WITH_INHERITANCE
	}
	
	public static Command createDropCommand(EditingDomain ed, Object dragObject, DndOperation dropOperation, Object dropObject) {
		if (dragObject instanceof StructuralElementInstance && dropObject instanceof StructuralElementInstance) {
			StructuralElementInstance dragSei = (StructuralElementInstance) dragObject;
			StructuralElementInstance dropSei = (StructuralElementInstance) dropObject;
			
			// When replacing the rule is as follows:
			// 1. Identify the type of the object to be dropped as new type
			// 2. See if there is an inheritance to a SEI of the identified type.
			// 3. If yes replace it.
			// 4. If not than add the drop object as new inheritance.
			if (dropOperation == DndOperation.REPLACE_INHERITANCE) {
				
				// Get type of drop object
				StructuralElement dragSeType = dragSei.getType();
				
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
				
				// None found, then add it
				Command addCommand = AddCommand.create(
					ed,
					dropSei,
					InheritancePackage.Literals.IINHERITS_FROM__SUPER_SEIS,
					Collections.singleton(dragSei)
				);
				return addCommand;
			}

		}
		return UnexecutableCommand.INSTANCE;
	}
	
}
