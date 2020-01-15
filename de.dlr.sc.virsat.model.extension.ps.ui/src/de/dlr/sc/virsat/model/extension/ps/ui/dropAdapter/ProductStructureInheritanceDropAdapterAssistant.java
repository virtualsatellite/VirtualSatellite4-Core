package de.dlr.sc.virsat.model.extension.ps.ui.dropAdapter;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;

import de.dlr.sc.virsat.model.extension.ps.util.DragAndDropInheritanceHelper;
import de.dlr.sc.virsat.model.extension.ps.util.DragAndDropInheritanceHelper.DndOperation;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

public class ProductStructureInheritanceDropAdapterAssistant extends CommonDropAdapterAssistant {

	protected VirSatTransactionalEditingDomain ed;
	protected Command validatedDropCommand;
	protected Object validatedDropObject;
	protected Object validatedDragObject;
	
	protected EObject getFirstSelectedDragEObject() {
		ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
		EObject firstEObject = new VirSatSelectionHelper(selection).getFirstEObject();
		return firstEObject;
	}
	
	@Override
	public IStatus validateDrop(Object dropObject, int operation, TransferData transferType) {
		if (dropObject instanceof EObject) {
			ed = VirSatEditingDomainRegistry.INSTANCE.getEd((EObject) dropObject);
			validatedDragObject = getFirstSelectedDragEObject();
			validatedDropObject = dropObject;
			validatedDropCommand = DragAndDropInheritanceHelper.createDropCommand(ed, validatedDragObject, DndOperation.REPLACE_INHERITANCE, dropObject);
			
			return validatedDropCommand.canExecute() ? Status.OK_STATUS : Status.CANCEL_STATUS;
		}
		return Status.CANCEL_STATUS;
	}

	@Override
	public IStatus handleDrop(CommonDropAdapter aDropAdapter, DropTargetEvent aDropTargetEvent, Object handleDropObject) {
		Object handleDragObject = getFirstSelectedDragEObject();
		if (handleDropObject == validatedDropObject && handleDragObject == validatedDragObject) {
			ed.getVirSatCommandStack().execute(validatedDropCommand);
			return Status.OK_STATUS;
		}
		
		return Status.CANCEL_STATUS;
	}
}
