package de.dlr.sc.virsat.model.extension.ps.util;

import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
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
			
			if (dropOperation == DndOperation.REPLACE_INHERITANCE) {
				StructuralElement dragSeType = dragSei.getType();
				
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
			}

		}
		return UnexecutableCommand.INSTANCE;
	}
	
}
