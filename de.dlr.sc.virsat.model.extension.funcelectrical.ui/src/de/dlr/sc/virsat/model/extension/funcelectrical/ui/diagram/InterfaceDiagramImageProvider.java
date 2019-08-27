/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;

/**
 * Makes icons available for Graphiti.
 * @author muel_s8
 *
 */

public class InterfaceDiagramImageProvider extends AbstractImageProvider {

	@Override
	protected void addAvailableImages() {
		addImageFilePath(InterfaceEnd.FULL_QUALIFIED_CATEGORY_NAME, "resources/icons/InterfaceEnd.gif");
		addImageFilePath(Interface.FULL_QUALIFIED_CATEGORY_NAME, "resources/icons/Interface.gif");
		addImageFilePath("OpenEditor", "platform:/plugin/de.dlr.sc.virsat.uiengine.ui/icons/VirSat_Component_Edit.png");
	}

}
