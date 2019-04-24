/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.ui.diagram.feature;


import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.AbstractPasteFeature;

import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * Feature handling the pasting of elements.
 * @author bell_Er
 *
 */
public class VirSatStructuralElementInstancePasteFeature extends AbstractPasteFeature {
	/**
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	
	public VirSatStructuralElementInstancePasteFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canPaste(IPasteContext context) {
		// only support pasting directly in the diagram (nothing else selected)
		PictogramElement[] pes = context.getPictogramElements();
		if (pes.length != 1 || !(pes[0] instanceof Diagram)) {
			return false;
		}
		
		for (PictogramElement pe : context.getPictogramElements()) {
			if (!DiagramHelper.hasDiagramWritePermission(pe)) {
				return false;
			}
		}
		
		// can paste, if all objects on the clipboard are EClasses
		Object[] fromClipboard = getFromClipboard();
		if (fromClipboard == null || fromClipboard.length == 0) {
			return false;
		}
		for (Object object : fromClipboard) {
			if (!(object instanceof StructuralElementInstance)) {
				return false;
			}
		}
		return true;
	}
	@Override
	public void paste(IPasteContext context) {
		// we already verified, that we paste directly in the diagram
		PictogramElement[] pes = context.getPictogramElements();
		Diagram diagram = (Diagram) pes[0];
		// get the EClasses from the clipboard without copying them
		// (only copy the pictogram element, not the business object)
		// then create new pictogram elements using the add feature
		Object[] objects = getFromClipboard();
		for (Object object : objects) {
			StructuralElementInstance main =  (StructuralElementInstance)  object;
			AddContext ac = new AddContext();
			// For simplicity paste all objects at the location given in the
			// context (no stacking or similar)
			ac.setLocation(context.getX(), context.getY()); 
			ac.setTargetContainer(diagram);
			addGraphicalRepresentation(ac, main);
		}
	}
}
