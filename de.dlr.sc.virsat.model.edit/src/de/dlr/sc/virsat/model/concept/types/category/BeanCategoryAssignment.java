/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.category;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Convenience class to access CAs and Properties of a Sei more easily
 * @author muel_s8
 *
 */
@Schema(description = "Convenience bean CA that can be used for every concrete CA."
		+ " Instances of this only contain the fields of ABeanCategoryAssignment"
		+ " and no additional properties of the concrete CA.")
public class BeanCategoryAssignment extends ABeanCategoryAssignment {

	@Override
	public String getFullQualifiedCategoryName() {
		return null;
	}

}
