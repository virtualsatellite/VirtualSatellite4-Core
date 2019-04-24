/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.roles;

import org.eclipse.emf.ecore.EObject;

/**
 * Interface for rights helper
 * @author fisc_ph
 *
 */

public interface IRightsHelper {

	/**
	 * Checks if a user has right access to the passed object
	 * @param eObject the eObject that we inquire whether or not the user has right access to
	 * @return true iff right access is granted
	 */
	boolean hasWriteAccess(EObject eObject);
}
