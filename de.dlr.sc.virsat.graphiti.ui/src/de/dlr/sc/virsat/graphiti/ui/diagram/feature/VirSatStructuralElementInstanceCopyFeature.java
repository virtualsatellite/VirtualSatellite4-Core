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
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.AbstractCopyFeature;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;

/**
 * Feature handling the copying of elements.
 * @author bell_Er
 *
 */
public class VirSatStructuralElementInstanceCopyFeature extends AbstractCopyFeature {
	 
	/**
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	
    public VirSatStructuralElementInstanceCopyFeature(IFeatureProvider fp) {
        super(fp);
    }

    @Override
	public void copy(ICopyContext context) {
		// get the business-objects for all pictogram-elements
        // we already verified, that all business-objets are EClasses
        PictogramElement[] pes = context.getPictogramElements();
        ABeanStructuralElementInstance temp = null;
        Object [] bos =  new Object[pes.length ];
        for (int i = 0; i < pes.length; i++) {
            PictogramElement pe = pes[i];
            Object o = getBusinessObjectForPictogramElement(pe);
            if (o instanceof ABeanStructuralElementInstance) {
            	temp = (ABeanStructuralElementInstance) getBusinessObjectForPictogramElement(pe);
			}
            bos[i] = temp.getStructuralElementInstance();
        }
        // put all business objects to the clipboard 

        putToClipboard(bos);


	}
    @Override
	public boolean canCopy(ICopyContext context) {
		final PictogramElement[] pes = context.getPictogramElements();
		if (pes == null || pes.length == 0) {  // nothing selected
			return false;
		}

		// return true, if all selected elements are a EClasses
		for (PictogramElement pe : pes) {
			final Object bo = getBusinessObjectForPictogramElement(pe);
			if (!(bo instanceof ABeanStructuralElementInstance)) {
				return false;
			}
		}
		return true;
	}
	
}