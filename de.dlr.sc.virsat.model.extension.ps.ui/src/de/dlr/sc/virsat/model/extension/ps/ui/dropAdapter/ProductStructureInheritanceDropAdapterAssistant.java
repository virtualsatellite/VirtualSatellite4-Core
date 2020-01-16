package de.dlr.sc.virsat.model.extension.ps.ui.dropAdapter;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.navigator.CommonDropAdapter;
import de.dlr.sc.virsat.project.ui.navigator.dropAssist.ADVLMDropAdapaterAssistant;

public class ProductStructureInheritanceDropAdapterAssistant extends ADVLMDropAdapaterAssistant {
	
	@Override
	public IStatus validateDrop(Object dropObject, int operation, TransferData transferType) {
		return Status.CANCEL_STATUS;
	}

	@Override
	public IStatus handleDrop(CommonDropAdapter aDropAdapter, DropTargetEvent aDropTargetEvent, Object handleDropObject) {
		return Status.CANCEL_STATUS;
	}

	@Override
	protected Command createDropCommand(AdapterFactoryEditingDomain ed, Collection<Object> dragObjects, int operation, EObject dropObject) {
		return null;
	}

	@Override
	protected Collection<Object> getSelectedDragObjects() {
		return null;
	}
}
