/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util;

import java.util.List;

/**
 * Interface for getting registered type instance setters
 * @author muel_s8
 *
 * @param <E> Clients should insert they specific subclass of the type instance setter here.
 */

public interface ITypeInstanceSetterProvider<E extends ITypeInstanceSetter> {
	
	/**
	 * Gets a list of registered type instance setters
	 * @return a list of registered type instance setters
	 */
	List<E> getTypeInstanceSetters();
}
