/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.contentProvider;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.ui.model.WorkbenchContentProvider;

/**
 * Content provider for folders
 * @author muel_s8
 *
 */

public class VirSatObjectFolderContentProvider extends WorkbenchContentProvider {

	/**
	 * Default public constructor.
	 */
	
	public VirSatObjectFolderContentProvider() {
		super();
	}

	@Override
	protected void processDelta(IResourceDelta delta) {
		IResourceDelta[] affectedChildren = delta.getAffectedChildren(IResourceDelta.ADDED);
		
		for (IResourceDelta resourceDelta : affectedChildren) {
			if (resourceDelta.getResource() instanceof IProject) {
				return;
			}
		}
		
		super.processDelta(delta);
	}
	
}