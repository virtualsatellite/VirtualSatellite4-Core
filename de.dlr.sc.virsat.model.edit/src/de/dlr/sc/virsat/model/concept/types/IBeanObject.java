/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import io.swagger.annotations.ApiModelProperty;

/**
 * interface of a Concept Bean
 * @author leps_je
 *
 * @param <CP_TYPE>
 */
public interface IBeanObject<CP_TYPE extends ATypeInstance> extends IBeanUuid {

	/**
	 * this method returns the type instance of a bean object
	 * @return CP_TYPE
	 */
	@ApiModelProperty(hidden = true)
	CP_TYPE getTypeInstance();
	
	/**
	 * this method set the type instance of a bean object
	 * @param ti the type instance
	 */
	void setTypeInstance(CP_TYPE ti);

	/**
	 * this method get the abstract type instance of a bean object
	 * @return the abstract type instance 
	 */
	@ApiModelProperty(name = "uuid", dataType = "string")
	ATypeInstance getATypeInstance();
	
	/**
	 * this method set the abstract type instance 
	 * @param ti the abstract type instance
	 */
	void setATypeInstance(ATypeInstance ti);
	
}
