/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaceends;




import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.AbstractPasteFeature;

import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCopier;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;

/**
 * Feature handling the pasting of elements.
 * @author bell_Er
 *
 */
public class InterfaceEndPasteFeature extends AbstractPasteFeature {
	/**
	 * Default constructor.
	 * @param fp the feature provider.
	 */

	public InterfaceEndPasteFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canPaste(IPasteContext context) {
		// only support pasting directly in the diagram (nothing else selected)
	
		// can paste, if all objects on the clipboard are EClasses
		Object[] fromClipboard = getFromClipboard();
		if (fromClipboard == null || fromClipboard.length == 0) {
			return false;
		}
		
		for (PictogramElement pe : context.getPictogramElements()) {
			Object bo = getBusinessObjectForPictogramElement(pe);
			if (!DiagramHelper.hasBothWritePermission(bo, pe)) {
				return false;
			}
		}
		
		for (Object object : fromClipboard) {
			if (!(object instanceof CategoryAssignment)) {
				return false;
			}
		}
		return true;
	}
	@Override
	public void paste(IPasteContext context) {
		// we already verified, that we paste directly in the diagram
		PictogramElement[] pes = context.getPictogramElements();
		for (PictogramElement pe: pes) {
			Object objectPe = getBusinessObjectForPictogramElement(pe);
			if (objectPe instanceof ElementDefinition || objectPe instanceof ElementConfiguration || objectPe instanceof ElementOccurence) {	
				StructuralElementInstance sei = ((ABeanStructuralElementInstance) objectPe).getStructuralElementInstance();
				Object[] objects = getFromClipboard();			
				for (Object object : objects) {
					if (object instanceof CategoryAssignment) {
						CategoryAssignment copy = (CategoryAssignment) DVLMCopier.makeCopy((CategoryAssignment) object);				
						Command add =  AddCommand.create(VirSatEditingDomainRegistry.INSTANCE.getEd(sei), sei, null, copy);
						VirSatEditingDomainRegistry.INSTANCE.getEd(sei).getCommandStack().execute(add);
						AddContext ac = new AddContext();
						ac.setNewObject(objectPe);
						ac.setTargetContainer((ContainerShape) pe);
						ac.setLocation(context.getX(), context.getY()); 
						// For simplicity paste all objects at the location given in the
						// context (no stacking or similar)
						addGraphicalRepresentation(ac, copy);
					}
				}	
			}
		}
		
	}

}
