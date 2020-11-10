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

import de.dlr.sc.virsat.model.concept.types.IBeanDelete;
import de.dlr.sc.virsat.model.concept.types.IBeanName;
import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import io.swagger.annotations.ApiModelProperty;

/**
 * Interface to a Bean that will represent a Category
 * This interface is used by all generated beans from a concept
 * This class gives simplified access to the internal category assignment
 * @author fisc_ph
 *
 */
public interface IBeanCategoryAssignment extends IBeanObject<CategoryAssignment>, IBeanDelete, IBeanName {

	/**
	 * Call this method to get the full qualified name of the underlying category
	 * @return The FQN of the category as String
	 */
	@ApiModelProperty(hidden = true)
	String getFullQualifiedCategoryName();

}