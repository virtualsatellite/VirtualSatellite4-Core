/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.visualisation.shape;

/**
 * Interface for observing changes in TreeManager that came from network
 */
public interface IShapeEditObserver {

	/**
	 * Notify that a given shape was edited
	 * @param editedShape shape with new parameter values
	 */
	void notifyShapeEdited(Shape editedShape);
}
