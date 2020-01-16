package de.dlr.sc.virsat.model.extension.ps.ui.dropAdapter;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.navigator.CommonDropAdapter;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.ps.dnd.ProductStructureDragAndDropInheritanceCommandHelper;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.ui.navigator.dropAssist.ADVLMDropAdapaterAssistant;
import de.dlr.sc.virsat.project.ui.navigator.dropAssist.DVLMDefaultInheritanceDropAdapterAssistant;

public class ProductStructureInheritanceDropAdapterAssistant extends DVLMDefaultInheritanceDropAdapterAssistant {
	
	public ProductStructureInheritanceDropAdapterAssistant() {
		psDndCommandHelper = new ProductStructureDragAndDropInheritanceCommandHelper();
	}
	
	protected ProductStructureDragAndDropInheritanceCommandHelper psDndCommandHelper;
	
	@Override
	protected Command createDropCommand(VirSatTransactionalEditingDomain ed, Collection<Object> dragObjects, int operation, EObject dropObject) {
		System.out.println("Drag and Drop " + operation);
		
		if ((operation == DND.DROP_LINK) && (dropObject instanceof StructuralElementInstance)) {
			StructuralElementInstance dropSei = (StructuralElementInstance) dropObject;
			Command createSeiAndAddInheritanceCommand = psDndCommandHelper.createDropCommand(ed, dragObjects, dropSei);
			return createSeiAndAddInheritanceCommand;
		}
		return UnexecutableCommand.INSTANCE;
	}
}
