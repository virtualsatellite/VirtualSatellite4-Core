/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.ui.decorator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;

import de.dlr.sc.virsat.build.marker.ui.MarkerImageProvider;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;

/**
 * Decorator to show warnings or errors from resources on given EMF DVLM model objects
 * @author fisc_ph
 *
 */
public class VirSatResourceMarkerDecorator implements ILightweightLabelDecorator {

	protected MarkerImageProvider mip;
	
	/**
	 * Constructor setting up the BMP Helper
	 */
	public VirSatResourceMarkerDecorator() {
		mip = new MarkerImageProvider(new VirSatProblemMarkerHelper());
	}
	
	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override 	
	public void decorate(Object element, IDecoration decoration) {
		if (element instanceof EObject) {
			ImageDescriptor decoratorImage = mip.getProblemImageDescriptorForEobject((EObject) element);
			if (decoratorImage != null) {
				decoration.addOverlay(decoratorImage, IDecoration.BOTTOM_LEFT);
			}
		}
	}
}
