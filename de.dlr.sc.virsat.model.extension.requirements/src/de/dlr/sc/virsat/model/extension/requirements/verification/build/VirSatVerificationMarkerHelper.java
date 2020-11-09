/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.verification.build;

import de.dlr.sc.virsat.project.markers.IMarkerHelper;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;

/**
 * Helper class for managing markers which appear during the validation 
 * @author lobe_el
 *
 */
public class VirSatVerificationMarkerHelper extends VirSatProblemMarkerHelper implements IMarkerHelper {
	
	static final String ID_VERIFICATION_PROBLEM_MARKER = "de.dlr.sc.virsat.problem.markers.requirements.verification";
	
	/**
	 * VIRSAT_PROBLEM_MARKER
	 * 		|
	 *		L___ VERIFICATION_PROBLEM_MARKER
	 * 
	 */		

	@Override
	protected String getMarkerID() {
		return ID_VERIFICATION_PROBLEM_MARKER;
	}
	
}
