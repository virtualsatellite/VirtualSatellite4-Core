/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.validator.core;

import de.dlr.sc.virsat.build.marker.util.VirSatValidationMarkerHelper;

/**
 * Abstract class that provides core functionality to retrieve the Workspace
 * Resource from an EObject and to easily set Resource Markers according to the
 * ValidationResult
 * 
 * @author fisc_ph
 *
 */
public abstract class ADvlmCoreValidator {

	protected VirSatValidationMarkerHelper vvmHelper = new VirSatValidationMarkerHelper();
	
}
