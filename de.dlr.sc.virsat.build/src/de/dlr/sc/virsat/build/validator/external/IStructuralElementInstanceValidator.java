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

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * <p>
 * Deprecated -> Use instead: {@link de.dlr.sc.virsat.model.dvlm.validator.IStructuralElementInstanceValidator}
 * 
 * </p>
 * the IStructuralElementInstanceValidator class defines the interface for our model validators
 * The build will call all registered validators bound to this interface
 * @author scha_vo
 *
 */
@Deprecated
public interface IStructuralElementInstanceValidator {

	/**
	 * method that gets called by the builder to start the verification and validation process
	 * @param sei The StructuralElementInstance to be validated 
	 * @return a boolean indicating successful validation when returning true
	 */
	boolean validate(StructuralElementInstance sei);

}