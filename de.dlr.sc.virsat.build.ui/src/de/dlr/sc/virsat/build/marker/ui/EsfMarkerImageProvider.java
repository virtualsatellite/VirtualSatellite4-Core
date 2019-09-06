/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.marker.ui;

import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.graphics.Image;

import de.dlr.sc.virsat.build.marker.util.VirSatValidationMarkerHelper;

/**
 * Class to get the images for the EStructuralFeatures in Objects
 * @author lobe_el
 *
 */
public class EsfMarkerImageProvider extends MarkerImageProvider {

	/**
	 * Constructor
	 */
	public EsfMarkerImageProvider() {
		super(new VirSatValidationMarkerHelper());
	}
	
	/**
	 * Get problem image for a specific structural feature of a given eObject
	 * @param eObject 
	 * @param eFeature 
	 * @return image or null if there are no resource markers for this structural feature
	 */
	public Image getProblemImageForStructuralFeatureInEobject(EObject eObject, EStructuralFeature eFeature) {
		Set<IMarker> markers = ((VirSatValidationMarkerHelper) vpmHelper).getEMFValidationMarkers(eObject, eFeature);
		return getProblemImageForSeverity(getHighestSeverityForMarkers(markers));
	}

	/**
	 * Get problem image tooltip for a specific structural feature of a given eObject
	 * @param eObject 
	 * @param eFeature 
	 * @return tooltip text
	 */
	public String getProblemImageToolTipForStructuralFeatureInEobject(EObject eObject, EStructuralFeature eFeature) {
		Set<IMarker> markers = ((VirSatValidationMarkerHelper) vpmHelper).getEMFValidationMarkers(eObject, eFeature);
		return getToolTipForMarkers(markers);
	}
}
