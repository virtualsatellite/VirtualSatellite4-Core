/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.marker.util;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.project.markers.IMarkerHelper;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;

/**
 * Helper class for managing markers which appear during the validation 
 * @author lobe_el
 *
 */
public class VirSatInheritanceMarkerHelper extends VirSatProblemMarkerHelper implements IMarkerHelper {
	
	static final String ID_INHERITANCE_PROBLEM_MARKER = "de.dlr.sc.virsat.problem.markers.inheritance";
	
	/**
	 * VIRSAT_PROBLEM_MARKER
	 * 		|
	 *		L___ INHERITANCE_PROBLEM_MARKER
	 * 
	 */		

	@Override
	protected String getMarkerID() {
		return ID_INHERITANCE_PROBLEM_MARKER;
	}
	
	@Override
	public boolean isAssociatedWith(IMarker marker, EObject eObject) {
		// There are no changes for this marker
		return super.isAssociatedWith(marker, eObject);
	}

	/**
	 * Method to attach a DVLMValidationProblemMarker to the Resource of the given object
	 * @param severity A Severity Level to be displayed with the Marker
	 * @param message A Message to be displayed in the Problem View
	 * @param eObject An eObject on which the Problem actually occurred.
	 *  This information should be evaluated in out Editor to show the exact place of problem
	 * @return The created Marker
	 */
	public IMarker createInheritanceMarker(int severity, String message, EObject eObject) {
		IMarker marker = createMarker(ID_INHERITANCE_PROBLEM_MARKER, severity, message, eObject);
		return marker;
	}
	
}
