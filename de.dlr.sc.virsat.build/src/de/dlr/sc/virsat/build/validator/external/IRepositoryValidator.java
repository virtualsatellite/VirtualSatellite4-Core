/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.validator.external;

import de.dlr.sc.virsat.model.dvlm.Repository;

/**
 * <p>
 * Deprecated -> Use instead: {@link de.dlr.sc.virsat.model.dvlm.validator.IRepositoryValidator}
 * 
 * </p>
 * the IRepositoryValidator class defines the interface for our model validators
 * The build will call all registered validators bound to this interface
 *
 */
@Deprecated
public interface IRepositoryValidator {

	/**
	 * method that gets called by the builder to start the verification and validation process
	 * @param repo The Repository to be validated 
	 * @return a boolean indicating successful validation when returning true
	 */
	boolean validate(Repository repo);

}