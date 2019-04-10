/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.treemanager;

import de.dlr.sc.visproto.VisProto.VisualisationMessage;

/**
 * Calling VisualisationManager to refresh
 * 
 * @author wulk_ja
 *
 */
public interface IVisUpdateHandler {

	/**
	 * Updates corresponding TreeManagern and Visualisation.
	 * 
	 * @param visualisationMessage	contains the SceneGraph message with the updated SceneGraph
	 */
	void updateVisualisationData(VisualisationMessage visualisationMessage);
}
