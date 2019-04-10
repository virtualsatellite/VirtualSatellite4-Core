/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.dropAssist;

import org.eclipse.ui.navigator.resources.ResourceDropAdapterAssistant;

/**
 * This class allows for specific implementations on dropping information into
 * the folder of the Structural Element Instance. We may refuse drop actions in 
 * case the folder does not belong to the user of the StructuralElementInstance.
 * @author fisc_ph
 *
 */
public class VirSatStructuralElementInstanceFolderDropAdapterAssistant extends ResourceDropAdapterAssistant {
}
