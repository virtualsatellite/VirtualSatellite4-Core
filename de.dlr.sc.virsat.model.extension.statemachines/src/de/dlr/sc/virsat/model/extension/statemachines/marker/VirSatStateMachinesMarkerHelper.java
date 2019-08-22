/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.marker;

import org.eclipse.core.resources.IMarker;

import de.dlr.sc.virsat.build.marker.util.VirSatValidationMarkerHelper;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.project.markers.IMarkerHelper;

/**
 * Helper class for managing markers which appear during the validation 
 * @author bell_Er
 *
 */
public class VirSatStateMachinesMarkerHelper extends VirSatValidationMarkerHelper implements IMarkerHelper {

	public static final String ID_SM_VALIDATION_PROBLEM_MARKER = "de.dlr.sc.virsat.problem.markers.validation.statemachines";
	
	/**
	 * Method to attach a FEAValidationProblemMarker to the Resource of the given object
	 * @param severity A Severity Level to be displayed with the Marker
	 * @param message A Message to be displayed in the Problem View
	 * @param iUuid An eObject on which the Problem actually occurred.
	 *  This information should be evaluated in out Editor to show the exact place of problem
	 * @return The created Marker
	 */
	public IMarker createSMValidationMarker(int severity, String message, IUuid iUuid) {
		IMarker marker = createMarker(ID_SM_VALIDATION_PROBLEM_MARKER, severity, message, iUuid);
		return marker;
	}
}
