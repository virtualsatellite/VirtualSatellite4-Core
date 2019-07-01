/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.validation;

import java.util.List;

import org.eclipse.rmf.reqif10.SpecObject;

/**
 * Interface for elements that require deltas
 * @author Tobias Franz
	tobias.franz@dlr.de
 *
 */
public interface IHistoryBased {
	
	/**
	 * function to pass the changes to the respected validation engine
	 * @param changedObjects the recently changed requirements
	 */
	void setChangedSpecObjects(List<SpecObject> changedObjects);

}
