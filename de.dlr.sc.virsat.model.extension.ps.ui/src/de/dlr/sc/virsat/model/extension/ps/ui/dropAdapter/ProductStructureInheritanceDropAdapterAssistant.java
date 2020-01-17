/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.ps.ui.dropAdapter;

import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.dnd.DND;

import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.ps.Activator;
import de.dlr.sc.virsat.model.extension.ps.dnd.ProductStructureDragAndDropInheritanceCommandHelper;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.ui.navigator.dropAssist.DVLMDefaultInheritanceDropAdapterAssistant;

/**
 * This class handles the drag and drop of PS elements when ALT is pressed.
 * In that case a new PS element is created and added to the drop target. The new
 * element will be typed by the dragged object.
 *
 */
public class ProductStructureInheritanceDropAdapterAssistant extends DVLMDefaultInheritanceDropAdapterAssistant {
	
	private BeanStructuralElementInstanceFactory bsf;
	protected ProductStructureDragAndDropInheritanceCommandHelper psDndCommandHelper;
	
	public ProductStructureInheritanceDropAdapterAssistant() {
		psDndCommandHelper = new ProductStructureDragAndDropInheritanceCommandHelper();
		bsf = new BeanStructuralElementInstanceFactory();
	}
	
	@Override
	protected Command createDropCommand(VirSatTransactionalEditingDomain ed, Collection<Object> dragObjects, int operation, EObject dropObject) {
		try {
			// Product Structure DND Operations only if ALT is pressed with drag and drop
			if ((operation == DND.DROP_LINK) && (dropObject instanceof StructuralElementInstance)) {
				StructuralElementInstance dropSei = (StructuralElementInstance) dropObject;
				IBeanStructuralElementInstance dopBeanSei = bsf.getInstanceFor(dropSei);
				Command createSeiAndAddInheritanceCommand = psDndCommandHelper.createDropCommand(ed, dragObjects, dopBeanSei);
				return createSeiAndAddInheritanceCommand;
			}
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(
					Status.INFO,
					Activator.getPluginId(),
					"Encountered a problem when trying to cast a Bean SEI for PS inheritance drag and drop operation: " + e.getMessage()
			));
		}
		return UnexecutableCommand.INSTANCE;
	}
}
